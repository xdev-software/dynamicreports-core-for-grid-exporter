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
package software.xdev.dynamicreports.jasper.transformation.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.design.base.expression.AbstractDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.jasper.base.JasperReportDesign;
import software.xdev.dynamicreports.jasper.transformation.JasperTransform;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.definition.DRICustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.exception.DRException;


public class SubreportExpression extends AbstractDesignComplexExpression
{
	private static final Log LOG = LogFactory.getLog(SubreportExpression.class);
	
	private final String name;
	private final Integer pageWidth;
	private ReportBuilder<?> reportBuilder;
	private final Map<ReportBuilder<?>, JasperReportDesign> reportDesigns;
	private final Map<ReportBuilder<?>, JasperReport> jasperReports;
	
	public SubreportExpression(
		final DRIDesignExpression pageWidthExpression,
		final DRIDesignExpression reportExpression,
		final Integer pageWidth)
	{
		this.addExpression(pageWidthExpression);
		this.addExpression(reportExpression);
		this.pageWidth = pageWidth;
		this.name = ReportUtils.generateUniqueName("subreportExpression");
		this.reportDesigns = new HashMap<>();
		this.jasperReports = new HashMap<>();
	}
	
	@Override
	public Object evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		this.reportBuilder = (ReportBuilder<?>)values.get(1);
		if(this.jasperReports.containsKey(this.reportBuilder))
		{
			return this.jasperReports.get(this.reportBuilder);
		}
		try
		{
			final DRICustomValues customValues =
				(DRICustomValues)reportParameters.getParameterValue(DRICustomValues.NAME);
			final DRIDesignReport report =
				new DRDesignReport(this.reportBuilder.build(), this.pageWidth, customValues.getTocHeadings());
			final JasperReportDesign reportDesign = new JasperReportDesign(report, reportParameters, null);
			final JasperTransform jasperTransform = new JasperTransform(report, reportDesign);
			jasperTransform.transform();
			final JasperReport jasperReport = JasperCompileManager.compileReport(reportDesign.getDesign());
			this.reportDesigns.put(this.reportBuilder, reportDesign);
			this.jasperReports.put(this.reportBuilder, jasperReport);
			return jasperReport;
		}
		catch(final JRException e)
		{
			if(LOG.isErrorEnabled())
			{
				LOG.error("Error encountered while creating subreport design", e);
			}
		}
		catch(final DRException e)
		{
			if(LOG.isErrorEnabled())
			{
				LOG.error("Error encountered while creating subreport design", e);
			}
		}
		return null;
	}
	
	public JasperReportDesign getReportDesign()
	{
		return this.reportDesigns.get(this.reportBuilder);
	}
	
	public ReportBuilder<?> getReportBuilder()
	{
		return this.reportBuilder;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return JasperReport.class;
	}
}
