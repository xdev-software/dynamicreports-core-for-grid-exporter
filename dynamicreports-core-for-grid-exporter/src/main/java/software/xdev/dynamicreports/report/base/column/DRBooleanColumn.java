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

import software.xdev.dynamicreports.report.base.component.DRBooleanField;
import software.xdev.dynamicreports.report.definition.column.DRIBooleanColumn;
import software.xdev.dynamicreports.report.definition.component.DRIBooleanField;


public class DRBooleanColumn extends DRColumn<DRIBooleanField> implements DRIBooleanColumn
{

	public DRBooleanColumn(final DRBooleanField booleanField)
	{
		super(booleanField);
	}
	
	@Override
	public String getName()
	{
		return this.getComponent().getValueExpression().getName();
	}
	
	@Override
	public Class<Boolean> getValueClass()
	{
		return Boolean.class;
	}
}
