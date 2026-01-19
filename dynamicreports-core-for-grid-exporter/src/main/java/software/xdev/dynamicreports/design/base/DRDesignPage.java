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

import software.xdev.dynamicreports.design.definition.DRIDesignPage;
import software.xdev.dynamicreports.report.constant.PageOrientation;


public class DRDesignPage implements DRIDesignPage
{

	private int width;
	private int height;
	private PageOrientation orientation;
	private DRDesignMargin margin;
	private int columnsPerPage;
	private int columnSpace;
	private int columnWidth;
	
	@Override
	public int getWidth()
	{
		return this.width;
	}
	
	public void setWidth(final int width)
	{
		this.width = width;
	}
	
	@Override
	public int getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final int height)
	{
		this.height = height;
	}
	
	@Override
	public PageOrientation getOrientation()
	{
		return this.orientation;
	}
	
	public void setOrientation(final PageOrientation orientation)
	{
		this.orientation = orientation;
	}
	
	@Override
	public DRDesignMargin getMargin()
	{
		return this.margin;
	}
	
	public void setMargin(final DRDesignMargin margin)
	{
		this.margin = margin;
	}
	
	@Override
	public int getColumnsPerPage()
	{
		return this.columnsPerPage;
	}
	
	public void setColumnsPerPage(final int columnsPerPage)
	{
		this.columnsPerPage = columnsPerPage;
	}
	
	@Override
	public int getColumnSpace()
	{
		return this.columnSpace;
	}
	
	public void setColumnSpace(final int columnSpace)
	{
		this.columnSpace = columnSpace;
	}
	
	@Override
	public int getColumnWidth()
	{
		return this.columnWidth;
	}
	
	public void setColumnWidth(final int columnWidth)
	{
		this.columnWidth = columnWidth;
	}
}
