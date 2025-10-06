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
package software.xdev.dynamicreports.report.builder.condition;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class UnEqualExpression extends AbstractSimpleExpression<Boolean>
{

	private final DRIValue<?> value;
	private final Object[] values;
	
	@SafeVarargs
	public <T> UnEqualExpression(final DRIValue<T> value, final T... values)
	{
		Validate.notNull(value, "value must not be null");
		Validate.noNullElements(values, "values must not contains null value");
		this.value = value;
		this.values = values;
	}
	
	@Override
	public Boolean evaluate(final ReportParameters reportParameters)
	{
		final Object actualValue = reportParameters.getValue(this.value);
		for(final Object value : this.values)
		{
			if(value.equals(actualValue))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Class<Boolean> getValueClass()
	{
		return Boolean.class;
	}
}
