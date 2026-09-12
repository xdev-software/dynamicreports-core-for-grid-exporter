/*
 * dynamicreports-core-for-grid-exporter - dynamicreports-core-for-grid-exporter
 * Copyright © 2023 XDEV Software (https://xdev.software)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package software.xdev.dynamicreports.jasper.transformation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;
import software.xdev.dynamicreports.design.definition.DRIDesignHyperLink;
import software.xdev.dynamicreports.design.definition.DRIDesignMargin;
import software.xdev.dynamicreports.design.definition.DRIDesignPage;
import software.xdev.dynamicreports.design.definition.DRIDesignParameter;
import software.xdev.dynamicreports.design.definition.DRIDesignQuery;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.jasper.base.CustomScriptlet;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.jasper.base.JasperReportParameters;
import software.xdev.dynamicreports.jasper.base.JasperScriptlet;
import software.xdev.dynamicreports.jasper.base.StartPageNumberScriptlet;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.jasper.constant.ValueType;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class ReportTransform
{
	private final JasperTransformAccessor accessor;
	
	public ReportTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	public void transform()
	{
		final DRIDesignReport report = this.accessor.getReport();
		final JasperDesign design = this.accessor.getDesign();
		final Map<String, Object> parameters = this.accessor.getParameters();
		
		if(report.isTableOfContents())
		{
			Map<String, JasperTocHeading> tocHeadings = report.getTableOfContentsHeadings();
			if(tocHeadings == null)
			{
				tocHeadings = new LinkedHashMap<>();
			}
			this.accessor.getCustomValues().setTocHeadings(tocHeadings);
		}
		
		design.setName(report.getReportName());
		parameters.put(JRParameter.REPORT_LOCALE, report.getLocale());
		parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, report.getResourceBundle());
		design.setResourceBundle(report.getResourceBundleName());
		design.setIgnorePagination(report.isIgnorePagination());
		this.setProperties(report.getProperties());
		if(report.getQuery() != null)
		{
			design.setQuery(this.query(report.getQuery()));
		}
		this.page();
		design.setWhenNoDataType(ConstantTransform.whenNoDataType(report.getWhenNoDataType()));
		design.setWhenResourceMissingType(
			ConstantTransform.whenResourceMissingType(report.getWhenResourceMissingType()));
		design.setTitleNewPage(report.isTitleOnANewPage());
		design.setSummaryNewPage(report.isSummaryOnANewPage());
		design.setSummaryWithPageHeaderAndFooter(report.isSummaryWithPageHeaderAndFooter());
		design.setFloatColumnFooter(report.isFloatColumnFooter());
		design.setPrintOrder(ConstantTransform.printOrder(report.getPrintOrder()));
		design.setColumnDirection(ConstantTransform.runDirection(report.getColumnDirection()));
		design.setLanguage(report.getLanguage());
		
		for(final DRIDesignParameter parameter : report.getParameters())
		{
			this.addParameter(parameter);
		}
		
		if(this.accessor.getStartPageNumber() != null)
		{
			this.addScriptlet("startPageNumber", StartPageNumberScriptlet.class);
			this.accessor.getCustomValues().setStartPageNumber(this.accessor.getStartPageNumber());
		}
		
		for(final DRIScriptlet scriptlet : report.getScriptlets())
		{
			this.addScriptlet(scriptlet);
		}
	}
	
	public void transformExpressions()
	{
		this.accessor.getDesign()
			.setFilterExpression(this.accessor.getExpressionTransform()
				.getExpression(this.accessor.getReport().getFilterExpression()));
	}
	
	public void addDependencies()
	{
		final DRIDesignReport report = this.accessor.getReport();
		if(!this.accessor.getCustomValues().isEmpty() || !report.getScriptlets().isEmpty()
			|| this.accessor.getCustomValues().getStartPageNumber() != null || report.isTableOfContents())
		{
			this.addParameter(JasperCustomValues.NAME, JasperCustomValues.class, this.accessor.getCustomValues());
		}
		if(this.accessor.getMasterReportParameters() != null)
		{
			this.addParameter(
				JasperReportParameters.MASTER_REPORT_PARAMETERS,
				ReportParameters.class,
				this.accessor.getMasterReportParameters());
		}
		if(!this.accessor.getCustomValues().isEmpty() || !report.getScriptlets().isEmpty())
		{
			this.addScriptlet(JasperScriptlet.NAME, JasperScriptlet.class);
		}
	}
	
	private <T> void addParameter(final String name, final Class<T> parameterClass, final T value)
	{
		if(!this.accessor.getDesign().getParametersMap().containsKey(name))
		{
			try
			{
				final JRDesignParameter jrParameter = new JRDesignParameter();
				jrParameter.setName(name);
				jrParameter.setValueClass(parameterClass);
				this.accessor.getDesign().addParameter(jrParameter);
			}
			catch(final JRException e)
			{
				throw new JasperDesignException("Registration failed for parameter \"" + name + "\"", e);
			}
		}
		else
		{
			final JRParameter jrParameter = this.accessor.getDesign().getParametersMap().get(name);
			if(!parameterClass.isAssignableFrom(jrParameter.getValueClass()))
			{
				throw new JasperDesignException(
					"Registration failed for parameter \"" + name + "\", parameter is not instance of "
						+ parameterClass.getName());
			}
		}
		if(value != null)
		{
			this.accessor.getParameters().put(name, value);
		}
	}
	
	private void addParameter(final DRIDesignParameter parameter)
	{
		try
		{
			if(!parameter.isExternal())
			{
				this.accessor.getDesign().addParameter(this.parameter(parameter));
			}
			this.accessor.getCustomValues().addValueType(parameter.getName(), ValueType.PARAMETER);
			if(parameter.getValue() != null)
			{
				this.accessor.getParameters().put(parameter.getName(), parameter.getValue());
			}
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for parameter \"" + parameter.getName() + "\"", e);
		}
	}
	
	private void setProperties(final Properties properties)
	{
		for(final Iterator<Object> iterator = properties.keySet().iterator(); iterator.hasNext();)
		{
			final String key = (String)iterator.next();
			this.accessor.getDesign().setProperty(key, properties.getProperty(key));
		}
	}
	
	private void addScriptlet(final DRIScriptlet scriptlet)
	{
		final CustomScriptlet customScriptlet = new CustomScriptlet(scriptlet);
		this.addScriptlet(scriptlet.getName(), customScriptlet.getClass());
		this.accessor.getParameters()
			.put(scriptlet.getName() + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX, customScriptlet);
	}
	
	private void addScriptlet(final String name, final Class<? extends JRAbstractScriptlet> scriptletClass)
	{
		try
		{
			this.accessor.getDesign().addScriptlet(this.scriptlet(name, scriptletClass));
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for scriptlet \"" + name + "\"", e);
		}
	}
	
	public JRDesignHyperlink hyperLink(final DRIDesignHyperLink hyperLink)
	{
		if(hyperLink == null)
		{
			return null;
		}
		
		final JRDesignHyperlink jrHyperLink = new JRDesignHyperlink();
		jrHyperLink.setHyperlinkAnchorExpression(this.accessor.getExpressionTransform()
			.getExpression(hyperLink.getAnchorExpression()));
		jrHyperLink.setHyperlinkPageExpression(this.accessor.getExpressionTransform()
			.getExpression(hyperLink.getPageExpression()));
		jrHyperLink.setHyperlinkReferenceExpression(this.accessor.getExpressionTransform()
			.getExpression(hyperLink.getReferenceExpression()));
		jrHyperLink.setHyperlinkTooltipExpression(this.accessor.getExpressionTransform()
			.getExpression(hyperLink.getTooltipExpression()));
		if(hyperLink.getType() != null)
		{
			final HyperlinkTypeEnum hyperLinkType = ConstantTransform.hyperLinkType(hyperLink.getType());
			if(hyperLinkType != null)
			{
				jrHyperLink.setHyperlinkType(hyperLinkType);
			}
			else
			{
				jrHyperLink.setLinkType(hyperLink.getType());
			}
		}
		if(hyperLink.getTarget() != null)
		{
			final HyperlinkTargetEnum hyperLinkTarget = ConstantTransform.hyperLinkTarget(hyperLink.getTarget());
			if(hyperLinkTarget != null)
			{
				jrHyperLink.setHyperlinkTarget(hyperLinkTarget);
			}
			else
			{
				jrHyperLink.setLinkTarget(hyperLink.getTarget());
			}
		}
		
		return jrHyperLink;
	}
	
	// page
	private void page()
	{
		final DRIDesignPage page = this.accessor.getReport().getPage();
		final DRIDesignMargin margin = page.getMargin();
		final JasperDesign design = this.accessor.getDesign();
		
		design.setPageWidth(page.getWidth());
		design.setPageHeight(page.getHeight());
		design.setOrientation(ConstantTransform.pageOrientation(page.getOrientation()));
		design.setLeftMargin(margin.getLeft());
		design.setRightMargin(margin.getRight());
		design.setTopMargin(margin.getTop());
		design.setBottomMargin(margin.getBottom());
		design.setColumnCount(page.getColumnsPerPage());
		design.setColumnSpacing(page.getColumnSpace());
		design.setColumnWidth(page.getColumnWidth());
	}
	
	// parameter
	private JRDesignParameter parameter(final DRIDesignParameter parameter)
	{
		final JRDesignParameter jrParameter = new JRDesignParameter();
		jrParameter.setName(parameter.getName());
		jrParameter.setValueClass(parameter.getValueClass());
		return jrParameter;
	}
	
	// scriptlet
	
	protected JRDesignScriptlet scriptlet(final String name, final Class<? extends JRAbstractScriptlet> scriptletClass)
	{
		final JRDesignScriptlet jrScriptlet = new JRDesignScriptlet();
		jrScriptlet.setName(name);
		jrScriptlet.setValueClass(scriptletClass);
		return jrScriptlet;
	}
	
	// query
	
	protected JRDesignQuery query(final DRIDesignQuery query)
	{
		final JRDesignQuery jrQuery = new JRDesignQuery();
		jrQuery.setText(query.getText());
		jrQuery.setLanguage(query.getLanguage());
		return jrQuery;
	}
}
