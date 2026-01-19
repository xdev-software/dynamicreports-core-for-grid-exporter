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
package software.xdev.dynamicreports.report.builder.component;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRXyList;


public class XyListBuilder extends DimensionComponentBuilder<XyListBuilder, DRXyList>
{

	protected XyListBuilder()
	{
		super(new DRXyList());
	}
	
	public XyListBuilder add(final Integer x, final Integer y, final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(x, "x must not be null");
		Validate.notNull(y, "y must not be null");
		Validate.notNull(component, "component must not be null");
		this.getObject().addComponent(x, y, component.getComponent());
		return this;
	}
	
	public XyListBuilder add(
		final Integer x,
		final Integer y,
		final Integer width,
		final Integer height,
		final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(x, "x must not be null");
		Validate.notNull(y, "y must not be null");
		Validate.notNull(component, "component must not be null");
		if(component instanceof DimensionComponentBuilder)
		{
			((DimensionComponentBuilder<?, ?>)component).setWidth(width);
			((DimensionComponentBuilder<?, ?>)component).setHeight(height);
		}
		this.getObject().addComponent(x, y, component.getComponent());
		return this;
	}
	
	public XyListBuilder add(final XyListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for(final XyListCellBuilder cell : cells)
		{
			this.getObject().addCell(cell.build());
		}
		return this;
	}
	
	public DRXyList getXyList()
	{
		return this.build();
	}
}
