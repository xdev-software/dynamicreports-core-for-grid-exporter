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


public final class Grids
{
	private Grids()
	{
	}
	
	// horizontal
	
	public static HorizontalColumnGridListBuilder horizontalColumnGridList()
	{
		return new HorizontalColumnGridListBuilder();
	}
	
	public static HorizontalColumnGridListBuilder horizontalColumnGridList(
		final ColumnGridComponentBuilder... components)
	{
		return new HorizontalColumnGridListBuilder().add(components);
	}
	
	public static HorizontalColumnGridListBuilder horizontalColumnGridList(
		final HorizontalColumnGridListCellBuilder... cells)
	{
		return new HorizontalColumnGridListBuilder().add(cells);
	}
	
	public static HorizontalColumnGridListCellBuilder hColumnGridListCell(final ColumnGridComponentBuilder component)
	{
		Validate.notNull(component, "component must not be null");
		return new HorizontalColumnGridListCellBuilder(component);
	}
	
	// horizontal flow
	
	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList()
	{
		return new HorizontalFlowColumnGridListBuilder();
	}
	
	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList(
		final ColumnGridComponentBuilder... components)
	{
		return new HorizontalFlowColumnGridListBuilder().add(components);
	}
	
	public static HorizontalColumnGridListBuilder horizontalFlowColumnGridList(
		final HorizontalColumnGridListCellBuilder... cells)
	{
		return new HorizontalFlowColumnGridListBuilder().add(cells);
	}
	
	// vertical
	
	public static VerticalColumnGridListBuilder verticalColumnGridList()
	{
		return new VerticalColumnGridListBuilder();
	}
	
	public static VerticalColumnGridListBuilder verticalColumnGridList(final ColumnGridComponentBuilder... components)
	{
		return new VerticalColumnGridListBuilder().add(components);
	}
	
	public static VerticalColumnGridListBuilder verticalColumnGridList(final VerticalColumnGridListCellBuilder... cells)
	{
		return new VerticalColumnGridListBuilder().add(cells);
	}
	
	public static VerticalColumnGridListCellBuilder vColumnGridListCell(final ColumnGridComponentBuilder component)
	{
		Validate.notNull(component, "component must not be null");
		return new VerticalColumnGridListCellBuilder(component);
	}
	
	// title group
	
	public static ColumnTitleGroupBuilder titleGroup()
	{
		return new ColumnTitleGroupBuilder();
	}
	
	public static ColumnTitleGroupBuilder titleGroup(final ColumnGridComponentBuilder... components)
	{
		return new ColumnTitleGroupBuilder().add(components);
	}
	
	public static ColumnTitleGroupBuilder titleGroup(
		final String title,
		final ColumnGridComponentBuilder... components)
	{
		return titleGroup(components).setTitle(title);
	}
}
