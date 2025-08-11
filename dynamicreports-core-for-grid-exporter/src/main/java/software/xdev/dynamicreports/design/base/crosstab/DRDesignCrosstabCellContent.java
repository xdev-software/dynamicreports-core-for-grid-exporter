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

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;


public class DRDesignCrosstabCellContent implements DRIDesignCrosstabCellContent
{

	private int width;
	private int height;
	private DRDesignList list;
	private DRDesignComponent component;
	private DRIDesignStyle style;
	
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
	
	public DRDesignList getList()
	{
		return this.list;
	}
	
	public void setList(final DRDesignList list)
	{
		this.list = list;
	}
	
	@Override
	public DRDesignComponent getComponent()
	{
		return this.component;
	}
	
	public void setComponent(final DRDesignComponent component)
	{
		this.component = component;
	}
	
	@Override
	public DRIDesignStyle getStyle()
	{
		return this.style;
	}
	
	public void setStyle(final DRIDesignStyle style)
	{
		this.style = style;
	}
}
