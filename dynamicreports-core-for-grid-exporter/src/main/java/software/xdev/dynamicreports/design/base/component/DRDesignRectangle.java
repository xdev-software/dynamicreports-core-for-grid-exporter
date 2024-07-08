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

import software.xdev.dynamicreports.design.base.style.DRDesignPen;
import software.xdev.dynamicreports.design.definition.component.DRIDesignRectangle;


public class DRDesignRectangle extends DRDesignComponent implements DRIDesignRectangle
{

	private Integer radius;
	private DRDesignPen pen;
	
	public DRDesignRectangle()
	{
		super("rectangle");
	}
	
	@Override
	public Integer getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(final Integer radius)
	{
		this.radius = radius;
	}
	
	@Override
	public DRDesignPen getPen()
	{
		return this.pen;
	}
	
	public void setPen(final DRDesignPen pen)
	{
		this.pen = pen;
	}
}
