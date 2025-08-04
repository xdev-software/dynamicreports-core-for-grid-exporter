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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.component.DRIList;


public class DRList extends DRDimensionComponent implements DRIList
{

	private List<DRListCell> listCells;
	private ListType type;
	private Integer gap;
	private DRComponent backgroundComponent;
	
	public DRList()
	{
		this(ListType.HORIZONTAL);
	}
	
	public DRList(final ListType type)
	{
		this.setType(type);
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.listCells = new ArrayList<>();
	}
	
	@Override
	public List<DRListCell> getListCells()
	{
		return this.listCells;
	}
	
	public void addComponent(final DRComponent component)
	{
		this.listCells.add(new DRListCell(component));
	}
	
	public void addCell(final DRListCell cell)
	{
		Validate.notNull(cell, "cell must not be null");
		this.listCells.add(cell);
	}
	
	public void addComponent(
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRComponent component)
	{
		this.listCells.add(new DRListCell(horizontalAlignment, verticalAlignment, component));
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
	public Integer getGap()
	{
		return this.gap;
	}
	
	public void setGap(final Integer gap)
	{
		if(gap != null)
		{
			Validate.isTrue(gap >= 0, "gap must be >= 0");
		}
		this.gap = gap;
	}
	
	@Override
	public DRComponent getBackgroundComponent()
	{
		return this.backgroundComponent;
	}
	
	public void setBackgroundComponent(final DRComponent backgroundComponent)
	{
		this.backgroundComponent = backgroundComponent;
	}
}
