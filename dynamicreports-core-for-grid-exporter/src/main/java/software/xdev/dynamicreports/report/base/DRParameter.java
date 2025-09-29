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
package software.xdev.dynamicreports.report.base;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.DRIParameter;


public class DRParameter<T> implements DRIParameter<T>
{

	private final String name;
	private final Class<T> valueClass;
	private T value;
	
	@SuppressWarnings("unchecked")
	public DRParameter(final String name, final T value)
	{
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(value, "value must not be null");
		this.name = name;
		this.valueClass = (Class<T>)value.getClass();
		this.value = value;
	}
	
	public DRParameter(final String name, final Class<T> valueClass)
	{
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.name = name;
		this.valueClass = valueClass;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public Class<T> getValueClass()
	{
		return this.valueClass;
	}
	
	@Override
	public T getValue()
	{
		return this.value;
	}
}
