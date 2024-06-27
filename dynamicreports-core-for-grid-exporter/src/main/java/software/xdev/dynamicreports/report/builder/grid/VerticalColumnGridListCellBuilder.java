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

import software.xdev.dynamicreports.report.base.grid.DRColumnGridListCell;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;


public class VerticalColumnGridListCellBuilder
	extends AbstractBuilder<VerticalColumnGridListCellBuilder, DRColumnGridListCell>
{

	protected VerticalColumnGridListCellBuilder(final ColumnGridComponentBuilder component)
	{
		super(new DRColumnGridListCell(component.build()));
	}
	
	// width
	
	public VerticalColumnGridListCellBuilder widthFixedOnLeft()
	{
		this.getObject().setHorizontalAlignment(HorizontalCellComponentAlignment.LEFT);
		return this;
	}
	
	public VerticalColumnGridListCellBuilder widthFixedOnCenter()
	{
		this.getObject().setHorizontalAlignment(HorizontalCellComponentAlignment.CENTER);
		return this;
	}
	
	public VerticalColumnGridListCellBuilder widthFixedOnRight()
	{
		this.getObject().setHorizontalAlignment(HorizontalCellComponentAlignment.RIGHT);
		return this;
	}
	
	public VerticalColumnGridListCellBuilder widthFloat()
	{
		this.getObject().setHorizontalAlignment(HorizontalCellComponentAlignment.FLOAT);
		return this;
	}
	
	public VerticalColumnGridListCellBuilder widthExpand()
	{
		this.getObject().setHorizontalAlignment(HorizontalCellComponentAlignment.EXPAND);
		return this;
	}
	
	// height
	
	public VerticalColumnGridListCellBuilder heightFixed()
	{
		this.getObject().setVerticalAlignment(VerticalCellComponentAlignment.TOP);
		return this;
	}
	
	public VerticalColumnGridListCellBuilder heightExpand()
	{
		this.getObject().setVerticalAlignment(VerticalCellComponentAlignment.EXPAND);
		return this;
	}
	
	public DRColumnGridListCell getColumnGridListCell()
	{
		return this.build();
	}
}
