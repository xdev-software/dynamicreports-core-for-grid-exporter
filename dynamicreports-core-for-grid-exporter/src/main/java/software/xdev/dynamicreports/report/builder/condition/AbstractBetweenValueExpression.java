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


public abstract class AbstractBetweenValueExpression<T extends Number> extends AbstractSimpleExpression<Boolean>
{

	private final DRIValue<T> value;
	private final Number min;
	private final Number max;
	
	public AbstractBetweenValueExpression(final DRIValue<T> value, final Number min, final Number max)
	{
		Validate.notNull(value, "value must not be null");
		Validate.notNull(min, "min must not be null");
		Validate.notNull(max, "min must not be null");
		Validate.isTrue(min.doubleValue() < max.doubleValue(), "min < max");
		this.value = value;
		this.min = min;
		this.max = max;
	}
	
	@Override
	public Boolean evaluate(final ReportParameters reportParameters)
	{
		final Number actualValue = reportParameters.getValue(this.value);
		if(actualValue != null)
		{
			return this.compare(actualValue, this.min, this.max);
		}
		return false;
	}
	
	@Override
	public Class<Boolean> getValueClass()
	{
		return Boolean.class;
	}
	
	protected abstract Boolean compare(Number actualValue, Number min, Number max);
}
