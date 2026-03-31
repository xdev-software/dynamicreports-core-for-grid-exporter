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
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;


public class HorizontalListBuilder extends DimensionComponentBuilder<HorizontalListBuilder, DRList>
{

	private DRList row;
	
	protected HorizontalListBuilder()
	{
		super(new DRList(ListType.VERTICAL));
		this.init();
	}
	
	protected void init()
	{
		this.newRow();
	}
	
	public HorizontalListBuilder add(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.row.addComponent(component.getComponent());
		}
		return this;
	}
	
	public HorizontalListBuilder add(final int gap, final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.add(Components.hListCell(Components.filler().setWidth(gap)).widthFixed());
			this.add(component);
		}
		return this;
	}
	
	public HorizontalListBuilder add(final HorizontalListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		Validate.noNullElements(cells, "cells must not contains null cell");
		for(final HorizontalListCellBuilder cell : cells)
		{
			this.row.addCell(cell.build());
		}
		return this;
	}
	
	public HorizontalListBuilder add(final int gap, final HorizontalListCellBuilder... cells)
	{
		Validate.notNull(cells, "cells must not be null");
		for(final HorizontalListCellBuilder cell : cells)
		{
			this.add(Components.hListCell(Components.filler().setWidth(gap)).widthFixed(), cell);
		}
		return this;
	}
	
	public HorizontalListBuilder newRow()
	{
		return this.newRow(0);
	}
	
	public HorizontalListBuilder newRow(final Integer verticalGap)
	{
		return this.newRow(verticalGap, ListType.HORIZONTAL);
	}
	
	public HorizontalListBuilder newFlowRow()
	{
		return this.newFlowRow(0);
	}
	
	public HorizontalListBuilder newFlowRow(final Integer verticalGap)
	{
		return this.newRow(verticalGap, ListType.HORIZONTAL_FLOW);
	}
	
	private HorizontalListBuilder newRow(final Integer verticalGap, final ListType listType)
	{
		if(verticalGap != null)
		{
			Validate.isTrue(verticalGap >= 0, "verticalGap must be >= 0");
		}
		if(verticalGap != null && verticalGap > 0)
		{
			this.getObject().addComponent(
				HorizontalCellComponentAlignment.CENTER,
				VerticalCellComponentAlignment.TOP,
				Components.filler().setHeight(verticalGap).build());
		}
		this.row = new DRList(listType);
		this.getObject().addComponent(this.row);
		return this;
	}
	
	public HorizontalListBuilder setGap(final Integer gap)
	{
		this.row.setGap(gap);
		return this;
	}
	
	@Override
	public HorizontalListBuilder setStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.row.setStyle(style.build());
		}
		else
		{
			this.row.setStyle(null);
		}
		return this;
	}
	
	public HorizontalListBuilder setBaseStretchType(final StretchType stretchType)
	{
		this.row.setStretchType(stretchType);
		return this;
	}
	
	public HorizontalListBuilder setBaseGap(final Integer gap)
	{
		this.getObject().setGap(gap);
		return this;
	}
	
	public HorizontalListBuilder setBaseStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setStyle(style.build());
		}
		else
		{
			this.getObject().setStyle(null);
		}
		return this;
	}
	
	public HorizontalListBuilder setBaseBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public HorizontalListBuilder setBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.row.setBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public DRList getList()
	{
		return this.build();
	}
}
