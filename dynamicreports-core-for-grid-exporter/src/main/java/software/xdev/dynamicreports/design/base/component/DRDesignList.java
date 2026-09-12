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
package software.xdev.dynamicreports.design.base.component;

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.constant.ComponentGroupType;
import software.xdev.dynamicreports.design.definition.component.DRIDesignList;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;


public class DRDesignList extends DRDesignComponent implements DRIDesignList
{

	private List<DRDesignListCell> listCells;
	private List<DRDesignComponent> components;
	private ListType type;
	private ComponentGroupType componentGroupType;
	private int gap;
	private boolean calculateComponents;
	private Boolean removable;
	private DRDesignComponent backgroundComponent;
	
	public DRDesignList()
	{
		this(ListType.HORIZONTAL);
	}
	
	public DRDesignList(final ListType type)
	{
		super("list");
		this.type = type;
		this.calculateComponents = true;
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.listCells = new ArrayList<>();
		this.components = new ArrayList<>();
	}
	
	@Override
	public List<DRDesignComponent> getComponents()
	{
		return this.components;
	}
	
	public void addComponent(final DRDesignComponent component)
	{
		this.components.add(component);
		this.listCells.add(new DRDesignListCell(component));
	}
	
	public void addComponent(final int index, final DRDesignComponent component)
	{
		this.components.add(index, component);
		this.listCells.add(index, new DRDesignListCell(component));
	}
	
	public void addComponent(
		final int index,
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRDesignComponent component)
	{
		this.components.add(index, component);
		this.listCells.add(index, new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
	}
	
	public void addComponent(
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRDesignComponent component)
	{
		this.components.add(component);
		this.listCells.add(new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
	}
	
	public List<DRDesignListCell> getListCells()
	{
		return this.listCells;
	}
	
	public boolean isEmpty()
	{
		return this.components.isEmpty();
	}
	
	@Override
	public ListType getType()
	{
		return this.type;
	}
	
	public void setType(final ListType type)
	{
		this.type = type;
	}
	
	@Override
	public ComponentGroupType getComponentGroupType()
	{
		return this.componentGroupType;
	}
	
	public void setComponentGroupType(final ComponentGroupType componentGroupType)
	{
		this.componentGroupType = componentGroupType;
	}
	
	public int getGap()
	{
		return this.gap;
	}
	
	public void setGap(final int gap)
	{
		this.gap = gap;
	}
	
	public boolean isCalculateComponents()
	{
		return this.calculateComponents;
	}
	
	public void setCalculateComponents(final boolean calculateComponents)
	{
		this.calculateComponents = calculateComponents;
	}
	
	public boolean isRemovable()
	{
		if(this.removable != null)
		{
			return this.removable;
		}
		else
		{
			return ListType.VERTICAL.equals(this.type);
		}
	}
	
	public void setRemovable(final Boolean removable)
	{
		this.removable = removable;
	}
	
	@Override
	public DRDesignComponent getBackgroundComponent()
	{
		return this.backgroundComponent;
	}
	
	public void setBackgroundComponent(final DRDesignComponent backgroundComponent)
	{
		this.backgroundComponent = backgroundComponent;
	}
}
