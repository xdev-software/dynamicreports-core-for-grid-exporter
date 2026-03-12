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
package software.xdev.dynamicreports.report.base.grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridList;


public class DRColumnGridList implements DRIColumnGridList
{

	private final List<DRColumnGridListCell> listCells;
	private ListType type;
	private int gap;
	
	public DRColumnGridList()
	{
		this(ListType.HORIZONTAL);
	}
	
	public DRColumnGridList(final ListType type)
	{
		this.setType(type);
		this.listCells = new ArrayList<>();
	}
	
	@Override
	public List<DRColumnGridListCell> getListCells()
	{
		return this.listCells;
	}
	
	public void addComponent(final DRIColumnGridComponent component)
	{
		this.listCells.add(new DRColumnGridListCell(component));
	}
	
	public void addCell(final DRColumnGridListCell cell)
	{
		Validate.notNull(cell, "cell must not be null");
		this.listCells.add(cell);
	}
	
	public void addComponent(
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRIColumnGridComponent component)
	{
		this.listCells.add(new DRColumnGridListCell(horizontalAlignment, verticalAlignment, component));
	}
	
	@Override
	public ListType getType()
	{
		return this.type;
	}
	
	public void setType(final ListType type)
	{
		Validate.notNull(type, "type must not be null");
		this.type = type;
	}
	
	@Override
	public int getGap()
	{
		return this.gap;
	}
	
	public void setGap(final int gap)
	{
		Validate.notNull(gap < 0, "gap must be >= 0");
		this.gap = gap;
	}
}
