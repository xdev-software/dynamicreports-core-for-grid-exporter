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

import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.definition.DRIPage;


public class DRPage implements DRIPage
{

	private Integer width;
	private Integer height;
	private PageOrientation orientation;
	private DRMargin margin;
	private Integer columnsPerPage;
	private Integer columnSpace;
	private Boolean ignorePageWidth;
	
	public void setPageFormat(final PageType pageType, final PageOrientation orientation)
	{
		Validate.notNull(pageType, "pageType must not be null");
		this.setPageFormat(pageType.getWidth(), pageType.getHeight(), orientation);
	}
	
	public void setPageFormat(final Integer width, final Integer height, final PageOrientation orientation)
	{
		Validate.isTrue(width >= 0, "width must be >= 0");
		Validate.isTrue(height >= 0, "height must be >= 0");
		Validate.notNull(orientation, "orientation must not be null");
		if(orientation.equals(PageOrientation.PORTRAIT))
		{
			this.setWidth(width);
			this.setHeight(height);
		}
		else
		{
			this.setWidth(height);
			this.setHeight(width);
		}
		this.setOrientation(orientation);
	}
	
	@Override
	public Integer getWidth()
	{
		return this.width;
	}

	public void setWidth(final Integer width)
	{
		if(width != null)
		{
			Validate.isTrue(width >= 0, "width must be >= 0");
		}
		this.width = width;
	}
	
	@Override
	public Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final Integer height)
	{
		if(height != null)
		{
			Validate.isTrue(height >= 0, "height must be >= 0");
		}
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
	public DRMargin getMargin()
	{
		return this.margin;
	}
	
	public void setMargin(final DRMargin margin)
	{
		this.margin = margin;
	}
	
	@Override
	public Integer getColumnsPerPage()
	{
		return this.columnsPerPage;
	}
	
	public void setColumnsPerPage(final Integer columnsPerPage)
	{
		if(columnsPerPage != null)
		{
			Validate.isTrue(columnsPerPage >= 1, "columnsPerPage must be >= 1");
		}
		this.columnsPerPage = columnsPerPage;
	}
	
	@Override
	public Integer getColumnSpace()
	{
		return this.columnSpace;
	}
	
	public void setColumnSpace(final Integer columnSpace)
	{
		if(columnSpace != null)
		{
			Validate.isTrue(columnSpace >= 0, "columnSpace must be >= 0");
		}
		this.columnSpace = columnSpace;
	}
	
	@Override
	public Boolean getIgnorePageWidth()
	{
		return this.ignorePageWidth;
	}
	
	public void setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.ignorePageWidth = ignorePageWidth;
	}
}
