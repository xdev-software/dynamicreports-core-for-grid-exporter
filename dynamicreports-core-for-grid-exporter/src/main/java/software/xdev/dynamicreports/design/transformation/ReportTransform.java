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
package software.xdev.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.base.DRDesignHyperLink;
import software.xdev.dynamicreports.design.base.DRDesignParameter;
import software.xdev.dynamicreports.design.base.DRDesignQuery;
import software.xdev.dynamicreports.design.base.DRDesignTemplateDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignParameter;
import software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.jasper.base.JasperScriptlet;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.DRIParameter;
import software.xdev.dynamicreports.report.definition.DRIQuery;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.exception.DRException;


public class ReportTransform
{
	private final DesignTransformAccessor accessor;
	private DRIDesignTemplateDesign templateDesign;
	private DRDesignQuery query;
	private final List<DRIDesignParameter> parameters;
	private DRIDesignExpression filterExpression;
	
	public ReportTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.parameters = new ArrayList<>();
	}
	
	public void transform() throws DRException
	{
		final DRIReport report = this.accessor.getReport();
		this.templateDesign = new DRDesignTemplateDesign(report.getTemplateDesign());
		if(report.getQuery() != null)
		{
			this.query = this.query(report.getQuery());
		}
		for(final DRIParameter<?> parameter : report.getParameters())
		{
			this.parameters.add(this.parameter(parameter));
		}
		this.filterExpression = this.accessor.getExpressionTransform()
			.transformExpression(report.getFilterExpression(), JasperScriptlet.SCRIPTLET_NAME);
	}
	
	protected DRDesignQuery query(final DRIQuery query)
	{
		final DRDesignQuery designQuery = new DRDesignQuery();
		designQuery.setText(query.getText());
		designQuery.setLanguage(query.getLanguage());
		return designQuery;
	}
	
	private DRDesignParameter parameter(final DRIParameter<?> parameter)
	{
		final DRDesignParameter designParameter = new DRDesignParameter();
		designParameter.setName(parameter.getName());
		designParameter.setValueClass(parameter.getValueClass());
		designParameter.setValue(parameter.getValue());
		designParameter.setExternal(this.accessor.getReport()
			.getTemplateDesign()
			.isDefinedParameter(parameter.getName()));
		return designParameter;
	}
	
	public DRDesignHyperLink hyperlink(final DRIHyperLink hyperLink) throws DRException
	{
		if(hyperLink == null)
		{
			return null;
		}
		
		final DRDesignHyperLink designHyperLink = new DRDesignHyperLink();
		designHyperLink.setAnchorExpression(this.accessor.getExpressionTransform()
			.transformExpression(hyperLink.getAnchorExpression()));
		designHyperLink.setPageExpression(this.accessor.getExpressionTransform()
			.transformExpression(hyperLink.getPageExpression()));
		designHyperLink.setReferenceExpression(this.accessor.getExpressionTransform()
			.transformExpression(hyperLink.getReferenceExpression()));
		designHyperLink.setTooltipExpression(this.accessor.getExpressionTransform()
			.transformExpression(hyperLink.getTooltipExpression()));
		designHyperLink.setType(hyperLink.getType());
		designHyperLink.setTarget(hyperLink.getTarget());
		
		return designHyperLink;
	}
	
	public DRIDesignTemplateDesign getTemplateDesign()
	{
		return this.templateDesign;
	}
	
	public DRDesignQuery getQuery()
	{
		return this.query;
	}
	
	public List<DRIDesignParameter> getParameters()
	{
		return this.parameters;
	}
	
	public DRIDesignExpression getFilterExpression()
	{
		return this.filterExpression;
	}
}
