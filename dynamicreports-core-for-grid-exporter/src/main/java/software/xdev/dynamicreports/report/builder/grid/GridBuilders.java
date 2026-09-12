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

public class GridBuilders
{
	
	// horizontal
	
	public HorizontalColumnGridListBuilder horizontalColumnGridList()
	{
		return Grids.horizontalColumnGridList();
	}
	
	public HorizontalColumnGridListBuilder horizontalColumnGridList(final ColumnGridComponentBuilder... components)
	{
		return Grids.horizontalColumnGridList(components);
	}
	
	public HorizontalColumnGridListBuilder horizontalColumnGridList(final HorizontalColumnGridListCellBuilder... cells)
	{
		return Grids.horizontalColumnGridList(cells);
	}
	
	public HorizontalColumnGridListCellBuilder hColumnGridListCell(final ColumnGridComponentBuilder component)
	{
		return Grids.hColumnGridListCell(component);
	}
	
	// horizontal flow
	
	public HorizontalColumnGridListBuilder horizontalFlowColumnGridList()
	{
		return Grids.horizontalFlowColumnGridList();
	}
	
	public HorizontalColumnGridListBuilder horizontalFlowColumnGridList(final ColumnGridComponentBuilder... components)
	{
		return Grids.horizontalFlowColumnGridList(components);
	}
	
	public HorizontalColumnGridListBuilder horizontalFlowColumnGridList(
		final HorizontalColumnGridListCellBuilder... cells)
	{
		return Grids.horizontalFlowColumnGridList(cells);
	}
	
	// vertical
	
	public VerticalColumnGridListBuilder verticalColumnGridList()
	{
		return Grids.verticalColumnGridList();
	}
	
	public VerticalColumnGridListBuilder verticalColumnGridList(final ColumnGridComponentBuilder... components)
	{
		return Grids.verticalColumnGridList(components);
	}
	
	public VerticalColumnGridListBuilder verticalColumnGridList(final VerticalColumnGridListCellBuilder... cells)
	{
		return Grids.verticalColumnGridList(cells);
	}
	
	public VerticalColumnGridListCellBuilder vColumnGridListCell(final ColumnGridComponentBuilder component)
	{
		return Grids.vColumnGridListCell(component);
	}
	
	// title group
	
	public ColumnTitleGroupBuilder titleGroup()
	{
		return Grids.titleGroup();
	}
	
	public ColumnTitleGroupBuilder titleGroup(final ColumnGridComponentBuilder... components)
	{
		return Grids.titleGroup(components);
	}
	
	public ColumnTitleGroupBuilder titleGroup(final String title, final ColumnGridComponentBuilder... components)
	{
		return Grids.titleGroup(title, components);
	}
}
