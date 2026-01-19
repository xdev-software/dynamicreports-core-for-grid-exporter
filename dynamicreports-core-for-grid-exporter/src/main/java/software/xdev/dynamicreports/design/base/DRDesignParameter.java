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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.definition.DRIDesignParameter;


public class DRDesignParameter implements DRIDesignParameter
{

	private String name;
	private Class<?> valueClass;
	private Object value;
	private boolean external;
	
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
	public Class<?> getValueClass()
	{
		return this.valueClass;
	}
	
	public void setValueClass(final Class<?> valueClass)
	{
		this.valueClass = valueClass;
	}
	
	@Override
	public Object getValue()
	{
		return this.value;
	}
	
	public void setValue(final Object value)
	{
		this.value = value;
	}
	
	@Override
	public boolean isExternal()
	{
		return this.external;
	}
	
	public void setExternal(final boolean external)
	{
		this.external = external;
	}
}
