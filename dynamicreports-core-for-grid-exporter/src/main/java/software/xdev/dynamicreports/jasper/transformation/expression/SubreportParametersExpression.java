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

import software.xdev.dynamicreports.design.base.expression.AbstractDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.jasper.base.JasperReportParameters;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class SubreportParametersExpression extends AbstractDesignComplexExpression
{

	private final SubreportExpression subreportExpression;
	
	public SubreportParametersExpression(
		final SubreportExpression subreportExpression,
		final DRIDesignExpression parametersExpression)
	{
		super(ReportUtils.generateUniqueName("subreportParametersExpression"));
		this.subreportExpression = subreportExpression;
		if(parametersExpression != null)
		{
			this.addExpression(parametersExpression);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.putAll(this.subreportExpression.getReportDesign().getParameters());
		if(this.subreportExpression.getReportBuilder().getReport().getParameterValues() != null)
		{
			parameters.putAll(this.subreportExpression.getReportBuilder().getReport().getParameterValues());
		}
		if(!values.isEmpty())
		{
			parameters.putAll((Map<String, Object>)values.get(0));
		}
		parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
		return parameters;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return Map.class;
	}
}
