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
package software.xdev.dynamicreports.design.base.expression;

import java.util.List;

import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIComplexExpression;


public class DRDesignComplexExpression extends AbstractDesignComplexExpression
{

	private final DRIComplexExpression<?> complexExpression;
	private final String parameterName;
	
	public DRDesignComplexExpression(final DRIComplexExpression<?> complexExpression, final String parameterName)
	{
		this.complexExpression = complexExpression;
		this.parameterName = parameterName;
	}
	
	@Override
	public Object evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		return this.complexExpression.evaluate(values, reportParameters);
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return this.complexExpression.getValueClass();
	}
	
	@Override
	public String getName()
	{
		return this.complexExpression.getName();
	}
	
	@Override
	public String getParameterName()
	{
		return this.parameterName;
	}
}
