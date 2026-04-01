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
package software.xdev.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignFiller;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCell;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabColumnGroup;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabRowGroup;
import software.xdev.dynamicreports.design.constant.ComponentGroupType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import software.xdev.dynamicreports.report.exception.DRException;


class BandComponentsTransform
{
	private final DesignTransformAccessor accessor;
	private final Map<String, Integer> componentNames;
	
	public BandComponentsTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.componentNames = new HashMap<>();
	}
	
	public DRDesignBand prepareBand(final DRDesignBand band, final int maxWidth, final int templateDesignComponents)
		throws DRException
	{
		if(band == null)
		{
			return null;
		}
		if(band.getBandComponent() != null)
		{
			return band;
		}
		
		final DRDesignComponent component = this.prepareList(band.getName(), band.getList(), maxWidth);
		if(component == null)
		{
			if(band.getList() == null || band.getList().getHeight() == null || band.getList().getHeight() == 0)
			{
				return null;
			}
		}
		band.setHeight(band.getList().getHeight());
		band.setBandComponent(component);
		
		if(band.getBandComponent() != null && templateDesignComponents > 0)
		{
			throw new DRException("Band " + band.getName()
				+ " must not be defined at once in jrxml template design and in dynamic design");
		}
		
		this.prepareListBackgroundComponents(component);
		this.prepareCrosstabs(component);
		
		return band;
	}
	
	private DRDesignComponent prepareList(final String name, final DRDesignList list, final int maxWidth)
		throws DRException
	{
		return this.prepareList(name, list, maxWidth, -1);
	}
	
	private DRDesignComponent prepareList(
		final String name,
		final DRDesignList list,
		final int maxWidth,
		final int maxHeight)
		throws DRException
	{
		if(list == null)
		{
			return null;
		}
		if(list.isEmpty())
		{
			return null;
		}
		
		ComponentPosition.component(name, list, maxWidth, maxHeight);
		
		final DRDesignComponent component = this.removeEmptyComponents(list);
		if(component == null)
		{
			return null;
		}
		this.componentGroupType(component);
		
		this.generateComponentNames(component, name);
		
		return component;
	}
	
	private void generateComponentNames(final DRDesignComponent component, final String bandName)
	{
		final String componentName = bandName + "." + component.getUniqueName();
		if(!this.componentNames.containsKey(componentName))
		{
			this.componentNames.put(componentName, 1);
		}
		else
		{
			this.componentNames.put(componentName, this.componentNames.get(componentName) + 1);
		}
		component.setUniqueName(componentName + this.componentNames.get(componentName));
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			for(final DRDesignComponent lComponent : list.getComponents())
			{
				this.generateComponentNames(lComponent, bandName);
			}
			if(list.getBackgroundComponent() != null)
			{
				list.getBackgroundComponent().setUniqueName(component.getUniqueName() + ".background");
			}
		}
	}
	
	private DRDesignComponent removeEmptyComponents(final DRDesignComponent component)
	{
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			if(list.getComponents().isEmpty())
			{
				return null;
			}
			else if(list.getComponents().size() == 1)
			{
				final DRDesignComponent lComponent = list.getComponents().get(0);
				final DRDesignComponent elm = this.removeEmptyComponents(lComponent);
				if(elm == null)
				{
					if(list.getWidth() > 0 && list.getHeight() > 0 && (list.getStyle() != null
						|| list.getBackgroundComponent() != null))
					{
						list.getComponents().clear();
						return list;
					}
					return null;
				}
				if(lComponent != elm && (!(lComponent instanceof DRDesignList) || lComponent instanceof DRDesignList
					&& !(lComponent.getStyle() == null && lComponent.getPrintWhenExpression() == null
						&& ((DRDesignList)lComponent).getBackgroundComponent() == null)))
				{
					elm.setX(lComponent.getX() + elm.getX());
					elm.setY(lComponent.getY() + elm.getY());
				}
				
				if(list.getStyle() == null && list.getPrintWhenExpression() == null
					&& list.getBackgroundComponent() == null)
				{
					elm.setX(list.getX() + elm.getX());
					elm.setY(list.getY() + elm.getY());
					return elm;
				}
				else
				{
					list.getComponents().clear();
					list.getComponents().add(elm);
					return list;
				}
			}
			else
			{
				final List<DRDesignComponent> components = new ArrayList<>();
				for(final DRDesignComponent listComponent : list.getComponents())
				{
					final DRDesignComponent comp = this.removeEmptyComponents(listComponent);
					if(comp != null)
					{
						components.add(comp);
					}
				}
				if(components.isEmpty())
				{
					if(list.getWidth() > 0 && list.getHeight() > 0 && (list.getStyle() != null
						|| list.getBackgroundComponent() != null))
					{
						list.getComponents().clear();
						return list;
					}
					return null;
				}
				list.getComponents().clear();
				list.getComponents().addAll(components);
				return list;
			}
		}
		else if(component instanceof DRDesignFiller && component.getStyle() == null
			&& component.getPrintWhenExpression() == null)
		{
			return null;
		}
		return component;
	}
	
	private void componentGroupType(final DRDesignComponent component)
	{
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			if(list.isRemovable() && list.getStyle() == null && list.getPrintWhenExpression() == null
				&& list.getBackgroundComponent() == null)
			{
				list.setComponentGroupType(ComponentGroupType.NONE);
				for(final DRDesignComponent listComponent : list.getComponents())
				{
					listComponent.setX(list.getX() + listComponent.getX());
					listComponent.setY(list.getY() + listComponent.getY());
				}
			}
			else
			{
				list.setComponentGroupType(ComponentGroupType.FRAME);
			}
			
			for(final DRDesignComponent listComponent : list.getComponents())
			{
				this.componentGroupType(listComponent);
			}
		}
	}
	
	private void prepareListBackgroundComponents(final DRDesignComponent component) throws DRException
	{
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			if(list.getBackgroundComponent() != null)
			{
				final DRDesignComponent backgroundComponent = list.getBackgroundComponent();
				backgroundComponent.setX(0);
				backgroundComponent.setY(0);
				backgroundComponent.setWidth(list.getWidth() - StyleResolver.getHorizontalPadding(list.getStyle()));
				backgroundComponent.setHeight(list.getHeight() - StyleResolver.getVerticalPadding(list.getStyle()));
				backgroundComponent.setStretchType(StretchType.ELEMENT_GROUP_HEIGHT);
				list.setBackgroundComponent(backgroundComponent);
			}
			for(final DRDesignComponent listComponent : list.getComponents())
			{
				this.prepareListBackgroundComponents(listComponent);
			}
		}
	}
	
	private void prepareCrosstabs(final DRDesignComponent component) throws DRException
	{
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			for(final DRDesignComponent listComponent : list.getComponents())
			{
				this.prepareCrosstabs(listComponent);
			}
		}
		else if(component instanceof DRDesignCrosstab)
		{
			this.prepareCrosstab((DRDesignCrosstab)component);
		}
	}
	
	private void prepareCrosstab(final DRDesignCrosstab crosstab) throws DRException
	{
		this.calculateCellDimensions(crosstab);
		
		final DRDesignCrosstabCellContent whenNoDataCell = crosstab.getWhenNoDataCell();
		
		for(final DRDesignCrosstabColumnGroup columnGroup : crosstab.getColumnGroups())
		{
			final DRDesignCrosstabCellContent header = columnGroup.getHeader();
			if(header != null)
			{
				header.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName(), header));
			}
			final DRDesignCrosstabCellContent totalHeader = columnGroup.getTotalHeader();
			if(totalHeader != null)
			{
				totalHeader.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName(), totalHeader));
			}
		}
		
		for(final DRDesignCrosstabRowGroup rowGroup : crosstab.getRowGroups())
		{
			final DRDesignCrosstabCellContent header = rowGroup.getHeader();
			if(header != null)
			{
				header.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName(), header));
			}
			final DRDesignCrosstabCellContent totalHeader = rowGroup.getTotalHeader();
			if(totalHeader != null)
			{
				totalHeader.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName(), totalHeader));
			}
		}
		
		if(whenNoDataCell != null)
		{
			whenNoDataCell.setComponent(this.prepareCrosstabCell(
				crosstab.getUniqueName() + ".whennodatacell",
				whenNoDataCell));
		}
		
		final DRDesignCrosstabCellContent headerCell = crosstab.getHeaderCell();
		if(headerCell != null)
		{
			crosstab.getHeaderCell()
				.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName() + ".headercell", headerCell));
		}
		
		for(final DRDesignCrosstabCell cell : crosstab.getCells())
		{
			final DRDesignCrosstabCellContent content = cell.getContent();
			if(content != null)
			{
				content.setComponent(this.prepareCrosstabCell(crosstab.getUniqueName(), content));
			}
		}
	}
	
	private DRDesignComponent prepareCrosstabCell(final String name, final DRDesignCrosstabCellContent cell)
		throws DRException
	{
		return this.prepareList(name, cell.getList(), cell.getWidth(), cell.getHeight());
	}
	
	@SuppressWarnings("checkstyle:MethodLength")
	private void calculateCellDimensions(final DRDesignCrosstab designCrosstab)
	{
		final DRICrosstab crosstab = this.accessor.getCrosstabTransform().getCrosstab(designCrosstab);
		final int cellWidth = this.accessor.getTemplateTransform().getCrosstabCellWidth(crosstab, designCrosstab);
		final int cellHeight = this.accessor.getTemplateTransform().getCrosstabCellHeight(crosstab, designCrosstab);
		final Map<String, GroupCellDimension> columnGroups = new HashMap<>();
		final Map<String, GroupCellDimension> rowGroups = new HashMap<>();
		int groupWidth = 0;
		int groupHeight = 0;
		
		GroupCellDimension previousCellDimension = null;
		for(
			int i = crosstab.getColumnGroups().size() - 1; i >= 0; i--)
		{
			final DRICrosstabColumnGroup<?> columnGroup = crosstab.getColumnGroups().get(i);
			int headerWidth = 0;
			int headerHeight = 0;
			int totalHeaderWidth = 0;
			int totalHeaderHeight = 0;
			
			if(previousCellDimension == null)
			{
				headerWidth = cellWidth;
			}
			else
			{
				headerWidth = previousCellDimension.getHeaderWidth() + previousCellDimension.getTotalHeaderWidth();
			}
			headerHeight = this.accessor.getTemplateTransform()
				.getCrosstabColumnGroupHeaderHeight(columnGroup, designCrosstab, groupHeight);
			
			if(this.accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup))
			{
				totalHeaderWidth = this.accessor.getTemplateTransform()
					.getCrosstabColumnGroupTotalHeaderWidth(columnGroup, crosstab.getCellWidth(), designCrosstab);
			}
			totalHeaderHeight = headerHeight;
			if(previousCellDimension != null)
			{
				totalHeaderHeight += previousCellDimension.getTotalHeaderHeight();
			}
			
			final GroupCellDimension groupCellDimension = new GroupCellDimension();
			groupCellDimension.setHeaderWidth(headerWidth);
			groupCellDimension.setHeaderHeight(headerHeight);
			groupCellDimension.setTotalHeaderWidth(totalHeaderWidth);
			groupCellDimension.setTotalHeaderHeight(totalHeaderHeight);
			columnGroups.put(columnGroup.getName(), groupCellDimension);
			previousCellDimension = groupCellDimension;
			
			groupHeight += groupCellDimension.getHeaderHeight();
		}
		
		previousCellDimension = null;
		for(
			int i = crosstab.getRowGroups().size() - 1; i >= 0; i--)
		{
			final DRICrosstabRowGroup<?> rowGroup = crosstab.getRowGroups().get(i);
			int headerWidth = 0;
			int headerHeight = 0;
			int totalHeaderWidth = 0;
			int totalHeaderHeight = 0;
			
			headerWidth = this.accessor.getTemplateTransform().getCrosstabRowGroupHeaderWidth(
				rowGroup,
				designCrosstab);
			if(previousCellDimension == null)
			{
				headerHeight = cellHeight;
			}
			else
			{
				headerHeight = previousCellDimension.getHeaderHeight() + previousCellDimension.getTotalHeaderHeight();
			}
			
			totalHeaderWidth = headerWidth;
			if(previousCellDimension != null)
			{
				totalHeaderWidth += previousCellDimension.getTotalHeaderWidth();
			}
			if(this.accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup))
			{
				totalHeaderHeight = this.accessor.getTemplateTransform()
					.getCrosstabRowGroupTotalHeaderHeight(rowGroup, crosstab.getCellHeight(), designCrosstab);
			}
			
			final GroupCellDimension groupCellDimension = new GroupCellDimension();
			groupCellDimension.setHeaderWidth(headerWidth);
			groupCellDimension.setHeaderHeight(headerHeight);
			groupCellDimension.setTotalHeaderWidth(totalHeaderWidth);
			groupCellDimension.setTotalHeaderHeight(totalHeaderHeight);
			rowGroups.put(rowGroup.getName(), groupCellDimension);
			previousCellDimension = groupCellDimension;
			
			groupWidth += groupCellDimension.getHeaderWidth();
		}
		
		designCrosstab.getWhenNoDataCell().setWidth(designCrosstab.getWidth());
		designCrosstab.getWhenNoDataCell().setHeight(designCrosstab.getHeight());
		designCrosstab.getHeaderCell().setWidth(groupWidth);
		designCrosstab.getHeaderCell().setHeight(groupHeight);
		
		for(final DRDesignCrosstabColumnGroup designColumnGroup : designCrosstab.getColumnGroups())
		{
			final GroupCellDimension groupCellDimension = columnGroups.get(designColumnGroup.getName());
			designColumnGroup.setHeight(groupCellDimension.getHeaderHeight());
			designColumnGroup.getHeader().setWidth(groupCellDimension.getHeaderWidth());
			designColumnGroup.getHeader().setHeight(groupCellDimension.getHeaderHeight());
			if(designColumnGroup.getTotalHeader() != null)
			{
				designColumnGroup.getTotalHeader().setWidth(groupCellDimension.getTotalHeaderWidth());
				designColumnGroup.getTotalHeader().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}
		
		for(final DRDesignCrosstabRowGroup designRowGroup : designCrosstab.getRowGroups())
		{
			final GroupCellDimension groupCellDimension = rowGroups.get(designRowGroup.getName());
			designRowGroup.setWidth(groupCellDimension.getHeaderWidth());
			designRowGroup.getHeader().setWidth(groupCellDimension.getHeaderWidth());
			designRowGroup.getHeader().setHeight(groupCellDimension.getHeaderHeight());
			if(designRowGroup.getTotalHeader() != null)
			{
				designRowGroup.getTotalHeader().setWidth(groupCellDimension.getTotalHeaderWidth());
				designRowGroup.getTotalHeader().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}
		
		for(final DRDesignCrosstabCell designCell : designCrosstab.getCells())
		{
			if(designCell.getColumnTotalGroup() == null && designCell.getRowTotalGroup() == null)
			{
				designCell.getContent().setWidth(cellWidth);
				designCell.getContent().setHeight(cellHeight);
			}
			else if(designCell.getColumnTotalGroup() != null && designCell.getRowTotalGroup() == null)
			{
				final GroupCellDimension groupCellDimension = columnGroups.get(designCell.getColumnTotalGroup());
				designCell.getContent().setWidth(groupCellDimension.getTotalHeaderWidth());
				designCell.getContent().setHeight(cellHeight);
			}
			else if(designCell.getColumnTotalGroup() == null && designCell.getRowTotalGroup() != null)
			{
				final GroupCellDimension groupCellDimension = rowGroups.get(designCell.getRowTotalGroup());
				designCell.getContent().setWidth(cellWidth);
				designCell.getContent().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
			else
			{
				GroupCellDimension groupCellDimension = columnGroups.get(designCell.getColumnTotalGroup());
				designCell.getContent().setWidth(groupCellDimension.getTotalHeaderWidth());
				groupCellDimension = rowGroups.get(designCell.getRowTotalGroup());
				designCell.getContent().setHeight(groupCellDimension.getTotalHeaderHeight());
			}
		}
	}
	
	static class GroupCellDimension
	{
		private int headerWidth;
		private int headerHeight;
		private int totalHeaderWidth;
		private int totalHeaderHeight;
		
		public int getHeaderWidth()
		{
			return this.headerWidth;
		}
		
		public void setHeaderWidth(final int headerWidth)
		{
			this.headerWidth = headerWidth;
		}
		
		public int getHeaderHeight()
		{
			return this.headerHeight;
		}
		
		public void setHeaderHeight(final int headerHeight)
		{
			this.headerHeight = headerHeight;
		}
		
		public int getTotalHeaderWidth()
		{
			return this.totalHeaderWidth;
		}
		
		public void setTotalHeaderWidth(final int totalHeaderWidth)
		{
			this.totalHeaderWidth = totalHeaderWidth;
		}
		
		public int getTotalHeaderHeight()
		{
			return this.totalHeaderHeight;
		}
		
		public void setTotalHeaderHeight(final int totalHeaderHeight)
		{
			this.totalHeaderHeight = totalHeaderHeight;
		}
	}
}
