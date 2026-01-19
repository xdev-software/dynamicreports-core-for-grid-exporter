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
package software.xdev.dynamicreports.design.transformation;

import java.util.HashMap;
import java.util.Map;

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;


class ColumnGrid
{
	private DRDesignList list;
	private final Map<DRIColumn<?>, DRDesignList> columnsLists;
	private boolean isEmpty;
	
	public ColumnGrid()
	{
		this.columnsLists = new HashMap<>();
		this.isEmpty = true;
	}
	
	public void addList(final DRIColumn<?> column, final DRDesignList list)
	{
		this.columnsLists.put(column, list);
	}
	
	public void addComponent(final DRIColumn<?> column, final DRDesignComponent component)
	{
		if(this.columnsLists.containsKey(column))
		{
			this.columnsLists.get(column).addComponent(component);
		}
	}
	
	public void addComponent(
		final DRIColumn<?> column,
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRDesignComponent component)
	{
		if(this.columnsLists.containsKey(column))
		{
			this.columnsLists.get(column).addComponent(horizontalAlignment, verticalAlignment, component);
		}
	}
	
	public DRDesignList getList()
	{
		return this.list;
	}
	
	public void setList(final DRDesignList list)
	{
		this.list = list;
	}
	
	public boolean isEmpty()
	{
		for(final DRDesignList list : this.columnsLists.values())
		{
			if(!list.isEmpty())
			{
				return false;
			}
		}
		return this.isEmpty;
	}
	
	public void setEmpty(final boolean isEmpty)
	{
		this.isEmpty = isEmpty;
	}
}
