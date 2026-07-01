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
package software.xdev.dynamicreports.report.base.component;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.component.DRIXyListCell;


public class DRXyListCell implements DRIXyListCell
{

	private Integer x;
	private Integer y;
	private final DRComponent component;
	
	public DRXyListCell(final Integer x, final Integer y, final DRComponent component)
	{
		Validate.notNull(x, "x must not be null");
		Validate.notNull(y, "y must not be null");
		Validate.notNull(component, "component must not be null");
		this.x = x;
		this.y = y;
		this.component = component;
	}
	
	@Override
	public Integer getX()
	{
		return this.x;
	}
	
	public void setX(final Integer x)
	{
		this.x = x;
	}
	
	@Override
	public Integer getY()
	{
		return this.y;
	}
	
	public void setY(final Integer y)
	{
		this.y = y;
	}
	
	@Override
	public DRComponent getComponent()
	{
		return this.component;
	}
}
