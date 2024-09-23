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
package software.xdev.dynamicreports.design.base.component;

import java.io.Serializable;

import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;


public class DRDesignListCell implements Serializable
{

	private HorizontalCellComponentAlignment horizontalAlignment;
	private VerticalCellComponentAlignment verticalAlignment;
	private final DRDesignComponent component;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	
	protected DRDesignListCell(final DRDesignComponent component)
	{
		this(null, null, component);
	}
	
	protected DRDesignListCell(
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRDesignComponent component)
	{
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.component = component;
	}
	
	public HorizontalCellComponentAlignment getHorizontalAlignment()
	{
		return this.horizontalAlignment;
	}
	
	public void setHorizontalAlignment(final HorizontalCellComponentAlignment horizontalAlignment)
	{
		this.horizontalAlignment = horizontalAlignment;
	}
	
	public VerticalCellComponentAlignment getVerticalAlignment()
	{
		return this.verticalAlignment;
	}
	
	public void setVerticalAlignment(final VerticalCellComponentAlignment verticalAlignment)
	{
		this.verticalAlignment = verticalAlignment;
	}
	
	public DRDesignComponent getComponent()
	{
		return this.component;
	}
	
	public Integer getX()
	{
		return this.x;
	}
	
	public void setX(final Integer x)
	{
		this.x = x;
	}
	
	public Integer getY()
	{
		return this.y;
	}
	
	public void setY(final Integer y)
	{
		this.y = y;
	}
	
	public Integer getWidth()
	{
		return this.width;
	}
	
	public void setWidth(final Integer width)
	{
		this.width = width;
	}
	
	public Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final Integer height)
	{
		this.height = height;
	}
}
