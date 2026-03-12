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

import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;


public class DRField<T> implements DRIField<T>
{

	private final String name;
	private final Class<? super T> valueClass;
	private DRIDataType<? super T, T> dataType;
	private String description;
	
	public DRField(final String name, final Class<? super T> valueClass)
	{
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.name = name;
		this.valueClass = valueClass;
	}
	
	@Override
	public DRIDataType<? super T, T> getDataType()
	{
		return this.dataType;
	}
	
	public void setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.dataType = dataType;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public Class<? super T> getValueClass()
	{
		return this.valueClass;
	}
	
	@Override
	public String getDescription()
	{
		return this.description;
	}
	
	public void setDescription(final String description)
	{
		this.description = description;
	}
}
