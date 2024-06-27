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

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


abstract class CalculationExpression extends AbstractComplexExpression<BigDecimal>
{

	@SafeVarargs
	protected CalculationExpression(final DRIExpression<? extends Number>... expressions)
	{
		Validate.notNull(expressions, "expressions must not be null");
		Validate.noNullElements(expressions, "expressions must not contains null expression");
		for(final DRIExpression<? extends Number> expression : expressions)
		{
			this.addExpression(expression);
		}
	}
	
	@Override
	public BigDecimal evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		BigDecimal result = null;
		for(final Object value : values)
		{
			final BigDecimal bigDecimalValue;
			if(value instanceof final BigDecimal bd)
			{
				bigDecimalValue = bd;
			}
			else
			{
				bigDecimalValue = BigDecimal.valueOf(((Number)value).doubleValue());
			}
			if(result == null)
			{
				result = bigDecimalValue;
			}
			else
			{
				result = this.calculate(result, bigDecimalValue);
			}
		}
		return result;
	}
	
	protected abstract BigDecimal calculate(BigDecimal value1, BigDecimal value2);
}
