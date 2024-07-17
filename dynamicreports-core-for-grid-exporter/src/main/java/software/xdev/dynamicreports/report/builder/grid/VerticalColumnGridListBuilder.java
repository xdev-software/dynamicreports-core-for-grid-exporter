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
package software.xdev.dynamicreports.report.builder.grid;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.grid.DRColumnGridList;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.ListType;


public class VerticalColumnGridListBuilder extends AbstractBuilder<VerticalColumnGridListBuilder, DRColumnGridList>
	implements ColumnGridComponentBuilder
{

	protected VerticalColumnGridListBuilder()
	{
		super(new DRColumnGridList(ListType.VERTICAL));
	}
	
	public VerticalColumnGridListBuilder add(final ColumnGridComponentBuilder... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ColumnGridComponentBuilder component : components)
		{
			this.getObject().addComponent(component.build());
		}
		return this;
	}
	
	public VerticalColumnGridListBuilder add(final VerticalColumnGridListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for(final VerticalColumnGridListCellBuilder cell : cells)
		{
			this.getObject().addCell(cell.build());
		}
		return this;
	}
	
	public VerticalColumnGridListBuilder setGap(final int gap)
	{
		this.getObject().setGap(gap);
		return this;
	}
	
	public DRColumnGridList getColumnGridList()
	{
		return this.build();
	}
}
