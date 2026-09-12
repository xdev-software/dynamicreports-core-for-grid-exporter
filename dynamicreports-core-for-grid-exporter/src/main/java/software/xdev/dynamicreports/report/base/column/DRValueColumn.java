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
package software.xdev.dynamicreports.report.base.column;

import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.component.DRITextField;


public class DRValueColumn<T> extends DRColumn<DRITextField<T>> implements DRIValueColumn<T>
{

	private Boolean printRepeatedDetailValues;
	
	public DRValueColumn(final DRTextField<T> valueField)
	{
		super(valueField);
	}
	
	@Override
	public Boolean getPrintRepeatedDetailValues()
	{
		return this.printRepeatedDetailValues;
	}
	
	public void setPrintRepeatedDetailValues(final Boolean printRepeatedDetailValues)
	{
		this.printRepeatedDetailValues = printRepeatedDetailValues;
	}
	
	@Override
	public String getName()
	{
		return this.getComponent().getValueExpression().getName();
	}
	
	@Override
	public Class<? super T> getValueClass()
	{
		return this.getComponent().getValueExpression().getValueClass();
	}
}
