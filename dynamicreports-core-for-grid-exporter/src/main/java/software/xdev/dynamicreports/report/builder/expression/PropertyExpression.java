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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;


public class PropertyExpression implements DRIPropertyExpression
{

	private String name;
	private DRIExpression<String> valueExpression;
	
	public PropertyExpression(final String name, final DRIExpression<String> valueExpression)
	{
		Validate.notNull(name, "name must not be null");
		Validate.notNull(valueExpression, "valueExpression must not be null");
		this.name = name;
		this.valueExpression = valueExpression;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	@Override
	public DRIExpression<String> getValueExpression()
	{
		return this.valueExpression;
	}
	
	public void setValueExpression(final DRIExpression<String> valueExpression)
	{
		this.valueExpression = valueExpression;
	}
}
