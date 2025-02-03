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

import software.xdev.dynamicreports.report.base.component.DRList;
import software.xdev.dynamicreports.report.constant.ListType;


public class VerticalListBuilder extends DimensionComponentBuilder<VerticalListBuilder, DRList>
{

	protected VerticalListBuilder()
	{
		super(new DRList(ListType.VERTICAL));
	}
	
	public VerticalListBuilder add(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().addComponent(component.getComponent());
		}
		return this;
	}
	
	public VerticalListBuilder add(final Integer gap, final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.add(Components.vListCell(Components.filler().setHeight(gap)).heightFixed());
			this.add(component);
		}
		return this;
	}
	
	public VerticalListBuilder add(final VerticalListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for(final VerticalListCellBuilder cell : cells)
		{
			this.getObject().addCell(cell.build());
		}
		return this;
	}
	
	public VerticalListBuilder add(final Integer gap, final VerticalListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		for(final VerticalListCellBuilder cell : cells)
		{
			this.add(Components.vListCell(Components.filler().setHeight(gap)).heightFixed(), cell);
		}
		return this;
	}
	
	public VerticalListBuilder setGap(final Integer gap)
	{
		this.getObject().setGap(gap);
		return this;
	}
	
	public VerticalListBuilder setBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public DRList getList()
	{
		return this.build();
	}
}
