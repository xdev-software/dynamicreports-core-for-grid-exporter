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
package software.xdev.dynamicreports.report.builder;

import software.xdev.dynamicreports.report.base.DRField;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;


public class FieldBuilder<T> extends AbstractBuilder<FieldBuilder<T>, DRField<T>> implements DRIValue<T>
{

	protected FieldBuilder(final String name, final Class<? super T> valueClass)
	{
		super(new DRField<>(name, valueClass));
	}
	
	public FieldBuilder<T> setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.getObject().setDataType(dataType);
		return this;
	}
	
	public FieldBuilder<T> setDescription(final String description)
	{
		this.getObject().setDescription(description);
		return this;
	}
	
	public DRField<T> getField()
	{
		return this.build();
	}
	
	@Override
	public String getName()
	{
		return this.getField().getName();
	}
}
