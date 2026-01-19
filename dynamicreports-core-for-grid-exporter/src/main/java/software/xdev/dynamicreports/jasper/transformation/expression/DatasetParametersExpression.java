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
import java.util.Map;

import software.xdev.dynamicreports.design.base.expression.AbstractDesignSimpleExpression;
import software.xdev.dynamicreports.jasper.base.JasperReportParameters;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class DatasetParametersExpression extends AbstractDesignSimpleExpression
{

	private final Map<String, Object> parameters;
	
	public DatasetParametersExpression(final Map<String, Object> parameters)
	{
		super(ReportUtils.generateUniqueName("datasetParametersExpression"));
		this.parameters = parameters;
	}
	
	@Override
	public Object evaluate(final ReportParameters reportParameters)
	{
		final Map<String, Object> parameters = new HashMap<>(this.parameters);
		parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
		return parameters;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return Map.class;
	}
}
