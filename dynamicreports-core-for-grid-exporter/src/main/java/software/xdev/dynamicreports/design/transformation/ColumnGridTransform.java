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

import software.xdev.dynamicreports.design.base.component.DRDesignFiller;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignListCell;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.base.grid.DRColumnGridList;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.column.DRIBooleanColumn;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGrid;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridList;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridListCell;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnTitleGroup;
import software.xdev.dynamicreports.report.exception.DRException;


public class ColumnGridTransform
{
	private final DesignTransformAccessor accessor;
	private DRIColumnGridList columnGridList;
	
	public ColumnGridTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	public void transform()
	{
		final DRIReport report = this.accessor.getReport();
		final DRIColumnGrid columnGrid = report.getColumnGrid();
		if(columnGrid != null && !columnGrid.getList().getListCells().isEmpty())
		{
			this.columnGridList = columnGrid.getList();
			return;
		}
		
		final DRColumnGridList columnGridList = new DRColumnGridList();
		if(columnGrid != null)
		{
			columnGridList.setGap(columnGrid.getList().getGap());
			columnGridList.setType(columnGrid.getList().getType());
		}
		this.addColumnsToGridList(columnGridList);
		this.columnGridList = columnGridList;
	}
	
	private void addColumnsToGridList(final DRColumnGridList columnGridList)
	{
		final DRIReport report = this.accessor.getReport();
		for(final DRIColumn<?> column : report.getColumns())
		{
			if(!this.accessor.getGroupTransform().getHideGroupColumns().contains(column))
			{
				columnGridList.addComponent(column);
			}
		}
	}
	
	protected ColumnGrid createColumnGrid() throws DRException
	{
		return this.createColumnGrid(this.columnGridList, null, false);
	}
	
	protected ColumnGrid createColumnTitleGrid(final DRDesignStyle groupPaddingStyle) throws DRException
	{
		return this.createColumnGrid(this.columnGridList, groupPaddingStyle, true);
	}
	
	private ColumnGrid createColumnGrid(
		final DRIColumnGridList columnGridList,
		final DRDesignStyle groupPaddingStyle,
		final boolean titleGroup) throws DRException
	{
		final ColumnGrid columnGrid = new ColumnGrid();
		final DRDesignList list = this.list(columnGridList, columnGrid, titleGroup).getList();
		final int groupPadding = this.accessor.getGroupTransform().getGroupPadding();
		if(groupPadding > 0)
		{
			final DRDesignFiller filler = new DRDesignFiller();
			filler.setStyle(groupPaddingStyle);
			filler.setWidth(groupPadding);
			filler.setHeight(0);
			list.addComponent(0, HorizontalCellComponentAlignment.CENTER, null, filler);
		}
		columnGrid.setList(list);
		return columnGrid;
	}
	
	private GridList list(
		final DRIColumnGridComponent columnGridComponent,
		final ColumnGrid columnGrid,
		final boolean titleGroup) throws DRException
	{
		if(columnGridComponent instanceof DRIColumn<?>)
		{
			final DRDesignList list = new DRDesignList(ListType.VERTICAL);
			final DRIColumn<?> column = (DRIColumn<?>)columnGridComponent;
			list.setWidth(this.accessor.getTemplateTransform()
				.getColumnWidth(column, this.accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
			columnGrid.addList(column, list);
			return new GridList(list, null);
		}
		else if(columnGridComponent instanceof DRIColumnGridList)
		{
			return new GridList(
				this.columnGridList((DRIColumnGridList)columnGridComponent, columnGrid, titleGroup),
				null);
		}
		else if(columnGridComponent instanceof DRIColumnTitleGroup)
		{
			return this.columnGridTitleGroup((DRIColumnTitleGroup)columnGridComponent, columnGrid, titleGroup);
		}
		else
		{
			throw new DRDesignReportException(
				"Column grid component " + columnGridComponent.getClass().getName() + " not supported");
		}
	}
	
	private DRDesignList columnGridList(
		final DRIColumnGridList columnGridList,
		final ColumnGrid columnGrid,
		final boolean titleGroup) throws DRException
	{
		final DRDesignList list = new DRDesignList();
		list.setType(columnGridList.getType());
		list.setGap(columnGridList.getGap());
		for(final DRIColumnGridListCell cell : columnGridList.getListCells())
		{
			final DRIColumnGridComponent component = cell.getComponent();
			HorizontalCellComponentAlignment horizontalAlignment = cell.getHorizontalAlignment();
			VerticalCellComponentAlignment verticalAlignment = cell.getVerticalAlignment();
			if(component instanceof DRIColumn<?>)
			{
				final DRIColumn<?> column = (DRIColumn<?>)component;
				if(column instanceof DRIBooleanColumn)
				{
					if(horizontalAlignment == null)
					{
						horizontalAlignment =
							ConstantTransform.toHorizontalCellComponentAlignment(
								((DRIBooleanColumn)column).getComponent()
								.getWidthType());
					}
					if(verticalAlignment == null)
					{
						verticalAlignment =
							ConstantTransform.toVerticalCellComponentAlignment(
								((DRIBooleanColumn)column).getComponent()
								.getHeightType());
					}
				}
				else
				{
					final DRIComponent columnComponent = this.accessor.getColumnTransform().getColumnComponent(column);
					if(columnComponent instanceof DRIDimensionComponent)
					{
						if(horizontalAlignment == null)
						{
							horizontalAlignment =
								ConstantTransform.toHorizontalCellComponentAlignment(
									((DRIDimensionComponent)columnComponent).getWidthType());
						}
						if(verticalAlignment == null)
						{
							verticalAlignment =
								ConstantTransform.toVerticalCellComponentAlignment(
									((DRIDimensionComponent)columnComponent).getHeightType());
						}
					}
				}
			}
			final GridList gridList = this.list(component, columnGrid, titleGroup);
			if(gridList.getHorizontalCellAlignment() != null)
			{
				list.addComponent(
					gridList.getHorizontalCellAlignment(),
					cell.getVerticalAlignment(),
					gridList.getList());
			}
			else
			{
				list.addComponent(horizontalAlignment, cell.getVerticalAlignment(), gridList.getList());
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private GridList columnGridTitleGroup(
		final DRIColumnTitleGroup columnGridTitleGroup,
		final ColumnGrid columnGrid,
		final boolean titleGroup) throws DRException
	{
		final DRDesignList columnList = this.list(columnGridTitleGroup.getList(), columnGrid, titleGroup).getList();
		
		@SuppressWarnings("rawtypes")
		final DRTextField titleGroupField = new DRTextField();
		titleGroupField.setValueExpression(columnGridTitleGroup.getTitleExpression());
		titleGroupField.setStyle(columnGridTitleGroup.getTitleStyle());
		titleGroupField.setHeight(columnGridTitleGroup.getTitleHeight());
		titleGroupField.setHeightType(columnGridTitleGroup.getTitleHeightType());
		titleGroupField.setRows(columnGridTitleGroup.getTitleRows());
		titleGroupField.setTextAdjust(columnGridTitleGroup.getTitleTextAdjust());
		titleGroupField.setPropertyExpressions(columnGridTitleGroup.getTitlePropertyExpressions());
		
		HorizontalCellComponentAlignment hCellAlignment = null;
		if(columnGridTitleGroup.getTitleWidth() == null && columnGridTitleGroup.getTitleColumns() == null)
		{
			int totalWidth = 0;
			for(final DRDesignListCell cell : columnList.getListCells())
			{
				final Integer width = cell.getComponent().getWidth();
				final HorizontalCellComponentAlignment horizontalAlignment = cell.getHorizontalAlignment();
				if(horizontalAlignment == null || horizontalAlignment.equals(HorizontalCellComponentAlignment.EXPAND)
					|| horizontalAlignment.equals(HorizontalCellComponentAlignment.FLOAT))
				{
					totalWidth = 0;
					break;
				}
				if(width != null)
				{
					totalWidth += width;
				}
			}
			if(totalWidth > 0)
			{
				titleGroupField.setWidth(totalWidth);
				hCellAlignment = HorizontalCellComponentAlignment.LEFT;
			}
		}
		else
		{
			if(columnGridTitleGroup.getTitleWidth() != null)
			{
				titleGroupField.setWidth(columnGridTitleGroup.getTitleWidth());
			}
			if(columnGridTitleGroup.getTitleColumns() != null)
			{
				titleGroupField.setColumns(columnGridTitleGroup.getTitleColumns());
			}
			hCellAlignment =
				ConstantTransform.toHorizontalCellComponentAlignment(columnGridTitleGroup.getTitleWidthType());
		}
		
		final DRDesignTextField designTitleGroupField =
			this.accessor.getComponentTransform().textField(titleGroupField, DefaultStyleType.COLUMN_TITLE);
		designTitleGroupField.setUniqueName("columngroup.title");
		
		if(!titleGroup || columnGridTitleGroup.getTitleExpression() == null)
		{
			columnList.setRemovable(true);
			if(hCellAlignment != null)
			{
				final DRDesignList list = new DRDesignList();
				list.setType(ListType.VERTICAL);
				list.addComponent(columnList);
				list.setWidth(designTitleGroupField.getWidth());
				return new GridList(list, hCellAlignment);
			}
			return new GridList(columnList, null);
		}
		
		columnGrid.setEmpty(!titleGroup);
		
		final DRDesignList list = new DRDesignList();
		list.setType(ListType.VERTICAL);
		if(hCellAlignment != null)
		{
			list.addComponent(hCellAlignment, null, designTitleGroupField);
			list.addComponent(columnList);
			list.setWidth(designTitleGroupField.getWidth());
			return new GridList(list, hCellAlignment);
		}
		else
		{
			list.addComponent(designTitleGroupField);
			list.addComponent(columnList);
			return new GridList(list, null);
		}
	}
	
	static class GridList
	{
		private final HorizontalCellComponentAlignment horizontalCellAlignment;
		private final DRDesignList list;
		
		GridList(final DRDesignList list, final HorizontalCellComponentAlignment horizontalCellAlignment)
		{
			this.list = list;
			this.horizontalCellAlignment = horizontalCellAlignment;
		}
		
		public HorizontalCellComponentAlignment getHorizontalCellAlignment()
		{
			return this.horizontalCellAlignment;
		}
		
		public DRDesignList getList()
		{
			return this.list;
		}
	}
}
