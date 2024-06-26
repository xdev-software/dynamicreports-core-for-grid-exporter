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
package software.xdev.dynamicreports.design.base.crosstab;

import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCell;


public class DRDesignCrosstabCell implements DRIDesignCrosstabCell
{

	private String name;
	private String rowTotalGroup;
	private String columnTotalGroup;
	private DRDesignCrosstabCellContent content;
	
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
	public String getRowTotalGroup()
	{
		return this.rowTotalGroup;
	}
	
	public void setRowTotalGroup(final String rowTotalGroup)
	{
		this.rowTotalGroup = rowTotalGroup;
	}
	
	@Override
	public String getColumnTotalGroup()
	{
		return this.columnTotalGroup;
	}
	
	public void setColumnTotalGroup(final String columnTotalGroup)
	{
		this.columnTotalGroup = columnTotalGroup;
	}
	
	@Override
	public DRDesignCrosstabCellContent getContent()
	{
		return this.content;
	}
	
	public void setContent(final DRDesignCrosstabCellContent content)
	{
		this.content = content;
	}
}
