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
package software.xdev.dynamicreports.report.builder.expression;

import java.util.List;

import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class PercentageExpression extends AbstractComplexExpression<Double>
{

	public PercentageExpression(
		final DRIExpression<? extends Number> actualExpression,
		final DRIExpression<? extends Number> totalExpression)
	{
		this.addExpression(actualExpression);
		this.addExpression(totalExpression);
	}
	
	@Override
	public Double evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		return ((Number)values.get(0)).doubleValue() / ((Number)values.get(1)).doubleValue();
	}
}
