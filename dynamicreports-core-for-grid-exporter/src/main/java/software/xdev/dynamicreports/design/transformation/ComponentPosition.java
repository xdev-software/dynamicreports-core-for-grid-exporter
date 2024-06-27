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
import java.util.List;

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignListCell;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.exception.DRException;


final class ComponentPosition
{
	private ComponentPosition()
	{
	}
	
	protected static void component(
		final String name,
		final DRDesignComponent component,
		final int maxWidth,
		final int maxHeight)
		throws DRException
	{
		if(component instanceof DRDesignList)
		{
			final DRDesignList list = (DRDesignList)component;
			list.setX(0);
			list.setY(0);
			list(name, list, maxWidth, maxHeight, true);
			list(name, list);
		}
		else
		{
			throw new DRDesignReportException(
				"Component " + component.getClass().getName() + " position not supported");
		}
	}
	
	private static void list(
		final String name,
		final DRDesignList list,
		final int maxWidth,
		final int maxHeight,
		final boolean stretch)
		throws DRException
	{
		width(list);
		alignment(list);
		recalculateWidth(name, list, maxWidth - StyleResolver.getHorizontalPadding(list.getStyle()));
		height(list);
		
		final int availableWidth = maxWidth - list.getWidth();
		if(availableWidth < 0)
		{
			throw new DRException(name + " components reaches outside available width, available width = " + maxWidth
				+ ", components width = " + list.getWidth());
		}
		if(stretch)
		{
			list.setWidth(list.getWidth() + availableWidth);
		}
		
		if(maxHeight > 0)
		{
			final int availableHeight = maxHeight - list.getHeight();
			if(availableHeight < 0)
			{
				throw new DRException(
					name + " components reaches outside available height, available height = " + maxHeight
						+ ", components height = " + list.getHeight());
			}
			if(stretch)
			{
				list.setHeight(list.getHeight() + availableHeight);
			}
		}
		
		listCellWidth(list, availableWidth, list.getWidth() - StyleResolver.getHorizontalPadding(list.getStyle()));
		listCellX(list);
		listCellHeight(list, list.getHeight() - StyleResolver.getVerticalPadding(list.getStyle()));
		listCellY(list);
		
		componentX(list);
		componentY(list);
		componentWidth(list);
		componentHeight(list);
	}
	
	private static void list(final String name, final DRDesignList list) throws DRException
	{
		for(final DRDesignComponent designComponent : list.getComponents())
		{
			if(designComponent instanceof DRDesignList)
			{
				final DRDesignList listComponent = (DRDesignList)designComponent;
				if(!listComponent.isCalculateComponents())
				{
					listComponent.setCalculateComponents(true);
					list(name, listComponent, listComponent.getWidth(), listComponent.getHeight(), false);
				}
				list(name, listComponent);
			}
		}
	}
	
	private static void alignment(final DRDesignList list)
	{
		for(final DRDesignListCell listCell : list.getListCells())
		{
			if(listCell.getComponent() instanceof DRDesignList)
			{
				final DRDesignList comList = (DRDesignList)listCell.getComponent();
				alignment(comList);
				if(listCell.getHorizontalAlignment() == null)
				{
					listCell.setHorizontalAlignment(detectHorizontalCellComponentAlignment(comList));
				}
				if(listCell.getVerticalAlignment() == null)
				{
					listCell.setVerticalAlignment(detectVerticalCellComponentAlignment(comList));
				}
			}
			if(listCell.getHorizontalAlignment() == null)
			{
				listCell.setHorizontalAlignment(Defaults.getDefaults().getHorizontalCellComponentAlignment());
			}
			if(listCell.getVerticalAlignment() == null)
			{
				listCell.setVerticalAlignment(Defaults.getDefaults().getVerticalCellComponentAlignment());
			}
		}
	}
	
	private static HorizontalCellComponentAlignment detectHorizontalCellComponentAlignment(final DRDesignList list)
	{
		final ListType type = list.getType();
		
		if(type == null)
		{
			return null;
		}
		
		switch(type)
		{
			case HORIZONTAL:
			case HORIZONTAL_FLOW:
				for(final DRDesignListCell listCell : list.getListCells())
				{
					switch(listCell.getHorizontalAlignment())
					{
						case EXPAND:
						case FLOAT:
							return null;
						default:
							break;
					}
				}
				return HorizontalCellComponentAlignment.LEFT;
			case VERTICAL:
				HorizontalCellComponentAlignment alignment = null;
				int maxWidth = 0;
				for(final DRDesignComponent designComponent : list.getComponents())
				{
					if(designComponent.getWidth() > maxWidth)
					{
						maxWidth = designComponent.getWidth();
					}
				}
				for(final DRDesignListCell listCell : list.getListCells())
				{
					switch(listCell.getHorizontalAlignment())
					{
						case EXPAND:
							return HorizontalCellComponentAlignment.EXPAND;
						case FLOAT:
							alignment = HorizontalCellComponentAlignment.FLOAT;
							break;
						default:
							if(alignment == null)
							{
								alignment = HorizontalCellComponentAlignment.LEFT;
							}
							else
							{
								if(alignment != HorizontalCellComponentAlignment.LEFT
									&& maxWidth == listCell.getComponent().getWidth())
								{
									return HorizontalCellComponentAlignment.EXPAND;
								}
							}
							break;
					}
				}
				return alignment;
			default:
				throw new DRDesignReportException("List type " + type.name() + " not supported");
		}
	}
	
	private static VerticalCellComponentAlignment detectVerticalCellComponentAlignment(final DRDesignList list)
	{
		final ListType type = list.getType();
		
		if(type == null)
		{
			return null;
		}
		
		switch(type)
		{
			case HORIZONTAL:
			case HORIZONTAL_FLOW:
				VerticalCellComponentAlignment alignment = null;
				for(final DRDesignListCell listCell : list.getListCells())
				{
					switch(listCell.getVerticalAlignment())
					{
						case EXPAND:
							return VerticalCellComponentAlignment.EXPAND;
						default:
							if(alignment == null)
							{
								alignment = VerticalCellComponentAlignment.TOP;
							}
							break;
					}
				}
				return alignment;
			case VERTICAL:
				return null;
			default:
				throw new DRDesignReportException("List type " + type.name() + " not supported");
		}
	}
	
	protected static void width(final DRDesignList list)
	{
		int maxWidth = 0;
		final ListType type = list.getType();
		
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					for(final DRDesignComponent component : list.getComponents())
					{
						if(component instanceof DRDesignList)
						{
							width((DRDesignList)component); // , availableWidth - StyleResolver.getHorizontalPadding
							// (component.getStyle()));
						}
						if(component.getWidth() == null)
						{
							throw new DRDesignReportException("Component has null width");
						}
						maxWidth += component.getWidth() + list.getGap();
					}
					if(!list.getComponents().isEmpty())
					{
						maxWidth -= list.getGap();
					}
					break;
				case VERTICAL:
					for(final DRDesignComponent component : list.getComponents())
					{
						if(component instanceof DRDesignList)
						{
							width((DRDesignList)component); // , availableWidth - StyleResolver.getHorizontalPadding
							// (component.getStyle()));
						}
						if(component.getWidth() == null)
						{
							throw new DRDesignReportException("Component has null width");
						}
						if(component.getWidth() > maxWidth)
						{
							maxWidth = component.getWidth();
						}
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignComponent component : list.getComponents())
			{
				if(component instanceof DRDesignList)
				{
					width((DRDesignList)component); // , availableWidth - StyleResolver.getHorizontalPadding(component
					// .getStyle()));
				}
				if(component.getWidth() == null)
				{
					throw new DRDesignReportException("Component has null width");
				}
				if(component.getWidth() + component.getX() > maxWidth)
				{
					maxWidth = component.getWidth() + component.getX();
				}
			}
		}
		
		maxWidth += StyleResolver.getHorizontalPadding(list.getStyle());
		
		if(list.getWidth() == null)
		{
			list.setWidth(maxWidth);
		}
	}
	
	@SuppressWarnings("checkstyle:FinalParameters")
	private static void recalculateWidth(final String name, final DRDesignList list, int availableWidth)
		throws DRException
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		if(list.getWidth() <= availableWidth)
		{
			return;
		}
		
		int maxWidth = 0;
		final ListType type = list.getType();
		
		if(type == null)
		{
			return;
		}
		
		switch(type)
		{
			case HORIZONTAL:
				int floatCells = 0;
				int floatCellsWidth = 0;
				for(final DRDesignListCell listCell : list.getListCells())
				{
					if(listCell.getHorizontalAlignment().equals(HorizontalCellComponentAlignment.FLOAT))
					{
						floatCellsWidth += listCell.getComponent().getWidth();
						floatCells++;
					}
				}
				if(list.getWidth() - floatCellsWidth >= availableWidth)
				{
					throw new DRException(
						name + " components reaches outside available width, available width = " + availableWidth
							+ ", components width = " + list.getWidth());
				}
				availableWidth -= list.getWidth() - floatCellsWidth;
				for(final DRDesignListCell listCell : list.getListCells())
				{
					final DRDesignComponent component = listCell.getComponent();
					if(listCell.getHorizontalAlignment().equals(HorizontalCellComponentAlignment.FLOAT))
					{
						int availableCellWidth = 0;
						if(floatCells > 1)
						{
							availableCellWidth =
								(int)((double)availableWidth / floatCellsWidth * component.getWidth());
							floatCellsWidth -= component.getWidth();
							availableWidth -= availableCellWidth;
						}
						else if(floatCells == 1)
						{
							availableCellWidth = availableWidth;
							availableWidth = 0;
						}
						floatCells--;
						
						if(component instanceof DRDesignList)
						{
							recalculateWidth(
								name,
								(DRDesignList)component,
								availableCellWidth - StyleResolver.getHorizontalPadding(component.getStyle()));
						}
						else
						{
							component.setWidth(availableCellWidth);
						}
					}
					maxWidth += component.getWidth() + list.getGap();
				}
				if(!list.getComponents().isEmpty())
				{
					maxWidth -= list.getGap();
				}
				break;
			case HORIZONTAL_FLOW:
				maxWidth = splitHorizontalListWhenOverflowWidth(name, list, availableWidth);
				break;
			case VERTICAL:
				maxWidth = availableWidth;
				for(final DRDesignListCell listCell : list.getListCells())
				{
					final DRDesignComponent component = listCell.getComponent();
					if(component.getWidth() > availableWidth)
					{
						if(listCell.getHorizontalAlignment().equals(HorizontalCellComponentAlignment.FLOAT))
						{
							if(component instanceof DRDesignList)
							{
								recalculateWidth(
									name,
									(DRDesignList)component,
									availableWidth - StyleResolver.getHorizontalPadding(component.getStyle()));
							}
							else
							{
								component.setWidth(availableWidth);
							}
						}
						else
						{
							throw new DRException(
								name + " components reaches outside available width, available width = "
									+ availableWidth + ", components width = " + component.getWidth());
						}
					}
					if(component.getWidth() > maxWidth)
					{
						maxWidth = component.getWidth();
					}
				}
				break;
			default:
				throw new DRDesignReportException("List type " + type.name() + " not supported");
		}
		maxWidth += StyleResolver.getHorizontalPadding(list.getStyle());
		list.setWidth(maxWidth);
	}
	
	private static int splitHorizontalListWhenOverflowWidth(
		final String name,
		final DRDesignList list,
		final int availableWidth)
		throws DRException
	{
		final List<DRDesignListCell> listCells = new ArrayList<>(list.getListCells());
		list.getListCells().clear();
		list.getComponents().clear();
		
		final HorizontalCellComponentAlignment hAlignment =
			Defaults.getDefaults().getHorizontalCellComponentAlignment();
		final VerticalCellComponentAlignment vAlignment = Defaults.getDefaults().getVerticalCellComponentAlignment();
		
		final DRDesignList column = new DRDesignList(ListType.VERTICAL);
		list.addComponent(hAlignment, vAlignment, column);
		DRDesignList row = new DRDesignList(ListType.HORIZONTAL);
		row.setGap(list.getGap());
		column.addComponent(hAlignment, vAlignment, row);
		
		int maxWidth = 0;
		int rowMaxWidth = 0;
		for(final DRDesignListCell listCell : listCells)
		{
			final DRDesignComponent component = listCell.getComponent();
			if(component.getWidth() > availableWidth)
			{
				throw new DRException(
					name + " components reaches outside available width, available width = " + availableWidth
						+ ", components width = " + component.getWidth());
			}
			if(rowMaxWidth + component.getWidth() > availableWidth)
			{
				final int width = rowMaxWidth - list.getGap();
				if(width > maxWidth)
				{
					maxWidth = width;
				}
				row.setWidth(width);
				row = new DRDesignList(ListType.HORIZONTAL);
				row.setGap(list.getGap());
				column.addComponent(hAlignment, vAlignment, row);
				rowMaxWidth = 0;
			}
			row.addComponent(listCell.getHorizontalAlignment(), listCell.getVerticalAlignment(), component);
			rowMaxWidth += component.getWidth() + list.getGap();
		}
		final int width = rowMaxWidth - list.getGap();
		if(width > maxWidth)
		{
			maxWidth = width;
		}
		row.setWidth(width);
		column.setWidth(maxWidth);
		
		return maxWidth;
	}
	
	protected static void height(final DRDesignList list)
	{
		int maxHeight = 0;
		final ListType type = list.getType();
		
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					for(final DRDesignComponent component : list.getComponents())
					{
						if(component instanceof DRDesignList)
						{
							height((DRDesignList)component);
						}
						if(component.getHeight() == null)
						{
							throw new DRDesignReportException("Component has null height");
						}
						if(component.getHeight() > maxHeight)
						{
							maxHeight = component.getHeight();
						}
					}
					break;
				case VERTICAL:
					for(final DRDesignComponent component : list.getComponents())
					{
						if(component instanceof DRDesignList)
						{
							height((DRDesignList)component);
						}
						if(component.getHeight() == null)
						{
							throw new DRDesignReportException("Component has null height");
						}
						maxHeight += component.getHeight() + list.getGap();
					}
					if(!list.getComponents().isEmpty())
					{
						maxHeight -= list.getGap();
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignComponent component : list.getComponents())
			{
				if(component instanceof DRDesignList)
				{
					height((DRDesignList)component);
				}
				if(component.getHeight() == null)
				{
					throw new DRDesignReportException("Component has null height");
				}
				if(component.getHeight() + component.getY() > maxHeight)
				{
					maxHeight = component.getHeight() + component.getY();
				}
			}
		}
		
		maxHeight += StyleResolver.getVerticalPadding(list.getStyle());
		
		if(list.getHeight() == null)
		{
			list.setHeight(maxHeight);
		}
	}
	
	@SuppressWarnings({"checkstyle:FinalParameters", "java:S3518"})
	private static void listCellWidth(final DRDesignList list, int availableWidth, final int maxWidth)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		final ListType type = list.getType();
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					int expandCellsWidth = 0;
					int expandCells = 0;
					for(final DRDesignListCell listCell : list.getListCells())
					{
						switch(listCell.getHorizontalAlignment())
						{
							case FLOAT:
							case EXPAND:
								expandCellsWidth += listCell.getComponent().getWidth();
								expandCells++;
								break;
							default:
								break;
						}
					}
					
					for(final DRDesignListCell listCell : list.getListCells())
					{
						final DRDesignComponent component = listCell.getComponent();
						switch(listCell.getHorizontalAlignment())
						{
							case FLOAT:
							case EXPAND:
								int availableCellWidth = 0;
								if(expandCells > 1)
								{
									availableCellWidth =
										(int)((double)availableWidth / Math.max(1, expandCellsWidth)
											* component.getWidth());
									expandCellsWidth -= component.getWidth();
									availableWidth -= availableCellWidth;
								}
								else if(expandCells == 1)
								{
									availableCellWidth = availableWidth;
									availableWidth = 0;
								}
								expandCells--;
								
								listCell.setWidth(component.getWidth() + availableCellWidth);
								if(component instanceof DRDesignList)
								{
									listCellWidth(
										(DRDesignList)component,
										availableCellWidth,
										component.getWidth() + availableCellWidth - StyleResolver.getHorizontalPadding(
											component.getStyle()));
								}
								break;
							default:
								listCell.setWidth(component.getWidth());
								if(component instanceof DRDesignList)
								{
									listCellWidth(
										(DRDesignList)component,
										0,
										component.getWidth()
											- StyleResolver.getHorizontalPadding(component.getStyle()));
								}
								break;
						}
					}
					break;
				case VERTICAL:
					for(final DRDesignListCell listCell : list.getListCells())
					{
						final DRDesignComponent component = listCell.getComponent();
						switch(listCell.getHorizontalAlignment())
						{
							case FLOAT:
							case EXPAND:
								listCell.setWidth(maxWidth);
								if(component instanceof DRDesignList)
								{
									listCellWidth(
										(DRDesignList)component,
										maxWidth - component.getWidth(),
										maxWidth - StyleResolver.getHorizontalPadding(component.getStyle()));
								}
								break;
							default:
								listCell.setWidth(maxWidth);
								if(component instanceof DRDesignList)
								{
									listCellWidth(
										(DRDesignList)component,
										0,
										component.getWidth()
											- StyleResolver.getHorizontalPadding(component.getStyle()));
								}
								break;
						}
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignListCell listCell : list.getListCells())
			{
				final DRDesignComponent component = listCell.getComponent();
				listCell.setWidth(component.getWidth());
				if(component instanceof DRDesignList)
				{
					listCellWidth(
						(DRDesignList)component,
						0,
						component.getWidth() - StyleResolver.getHorizontalPadding(component.getStyle()));
				}
			}
		}
	}
	
	private static void listCellX(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		final ListType type = list.getType();
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					int x = 0;
					for(final DRDesignListCell listCell : list.getListCells())
					{
						listCell.setX(x);
						x += listCell.getWidth() + list.getGap();
						if(listCell.getComponent() instanceof DRDesignList)
						{
							listCellX((DRDesignList)listCell.getComponent());
						}
					}
					break;
				case VERTICAL:
					for(final DRDesignListCell listCell : list.getListCells())
					{
						listCell.setX(0);
						if(listCell.getComponent() instanceof DRDesignList)
						{
							listCellX((DRDesignList)listCell.getComponent());
						}
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignListCell listCell : list.getListCells())
			{
				listCell.setX(listCell.getComponent().getX());
				if(listCell.getComponent() instanceof DRDesignList)
				{
					listCellX((DRDesignList)listCell.getComponent());
				}
			}
		}
	}
	
	@SuppressWarnings("checkstyle:FinalParameters")
	private static void listCellHeight(final DRDesignList list, int availableHeight)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		final ListType type = list.getType();
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					int maxHeight = availableHeight;
					for(final DRDesignListCell listCell : list.getListCells())
					{
						if(listCell.getComponent().getHeight() > maxHeight)
						{
							maxHeight = listCell.getComponent().getHeight();
						}
					}
					for(final DRDesignListCell listCell : list.getListCells())
					{
						final DRDesignComponent component = listCell.getComponent();
						switch(listCell.getVerticalAlignment())
						{
							case EXPAND:
								listCell.setHeight(maxHeight);
								if(component instanceof DRDesignList)
								{
									listCellHeight(
										(DRDesignList)component,
										maxHeight - StyleResolver.getVerticalPadding(component.getStyle()));
								}
								break;
							default:
								listCell.setHeight(maxHeight);
								if(component instanceof DRDesignList)
								{
									listCellHeight(
										(DRDesignList)component,
										component.getHeight() - StyleResolver.getVerticalPadding(component.getStyle()));
								}
								break;
						}
					}
					break;
				case VERTICAL:
					maxHeight = 0;
					int expandCellsHeight = 0;
					int expandCells = 0;
					for(final DRDesignListCell listCell : list.getListCells())
					{
						maxHeight += listCell.getComponent().getHeight();
						switch(listCell.getVerticalAlignment())
						{
							case EXPAND:
								expandCellsHeight += listCell.getComponent().getHeight();
								expandCells++;
								break;
							default:
								break;
						}
					}
					availableHeight = availableHeight - maxHeight - list.getGap() * (list.getComponents().size() - 1);
					
					for(final DRDesignListCell listCell : list.getListCells())
					{
						final DRDesignComponent component = listCell.getComponent();
						switch(listCell.getVerticalAlignment())
						{
							case EXPAND:
								int availableCellHeight = 0;
								if(expandCells > 1)
								{
									availableCellHeight =
										(int)((double)availableHeight / expandCellsHeight * component.getHeight());
									expandCellsHeight -= component.getHeight();
									availableHeight -= availableCellHeight;
								}
								else if(expandCells == 1)
								{
									availableCellHeight = availableHeight;
									availableHeight = 0;
								}
								expandCells--;
								
								listCell.setHeight(component.getHeight() + availableCellHeight);
								if(component instanceof DRDesignList)
								{
									listCellHeight(
										(DRDesignList)component,
										listCell.getHeight() - StyleResolver.getVerticalPadding(component.getStyle()));
								}
								break;
							default:
								listCell.setHeight(component.getHeight());
								if(component instanceof DRDesignList)
								{
									listCellHeight(
										(DRDesignList)component,
										listCell.getHeight() - StyleResolver.getVerticalPadding(component.getStyle()));
								}
								break;
						}
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignListCell listCell : list.getListCells())
			{
				final DRDesignComponent component = listCell.getComponent();
				listCell.setHeight(component.getHeight());
				if(component instanceof DRDesignList)
				{
					listCellHeight(
						(DRDesignList)component,
						listCell.getHeight() - StyleResolver.getVerticalPadding(component.getStyle()));
				}
			}
		}
	}
	
	private static void listCellY(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		final ListType type = list.getType();
		if(type != null)
		{
			switch(type)
			{
				case HORIZONTAL:
				case HORIZONTAL_FLOW:
					for(final DRDesignListCell listCell : list.getListCells())
					{
						listCell.setY(0);
						if(listCell.getComponent() instanceof DRDesignList)
						{
							listCellY((DRDesignList)listCell.getComponent());
						}
					}
					break;
				case VERTICAL:
					int y = 0;
					for(final DRDesignListCell listCell : list.getListCells())
					{
						listCell.setY(y);
						y += listCell.getHeight() + list.getGap();
						if(listCell.getComponent() instanceof DRDesignList)
						{
							listCellY((DRDesignList)listCell.getComponent());
						}
					}
					break;
				default:
					throw new DRDesignReportException("List type " + type.name() + " not supported");
			}
		}
		else
		{
			for(final DRDesignListCell listCell : list.getListCells())
			{
				listCell.setY(listCell.getComponent().getY());
				if(listCell.getComponent() instanceof DRDesignList)
				{
					listCellY((DRDesignList)listCell.getComponent());
				}
			}
		}
	}
	
	private static void componentX(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		for(final DRDesignListCell listCell : list.getListCells())
		{
			final DRDesignComponent component = listCell.getComponent();
			final HorizontalCellComponentAlignment alignment = listCell.getHorizontalAlignment();
			switch(alignment)
			{
				case FLOAT:
				case EXPAND:
				case LEFT:
					component.setX(listCell.getX());
					break;
				case CENTER:
					component.setX(listCell.getX() + listCell.getWidth() / 2 - component.getWidth() / 2);
					break;
				case RIGHT:
					component.setX(listCell.getX() + listCell.getWidth() - component.getWidth());
					break;
				default:
					throw new DRDesignReportException("Component alignment " + alignment.name() + " not supported");
			}
			if(component instanceof DRDesignList)
			{
				componentX((DRDesignList)component);
			}
		}
	}
	
	private static void componentY(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		for(final DRDesignListCell listCell : list.getListCells())
		{
			final DRDesignComponent component = listCell.getComponent();
			final VerticalCellComponentAlignment alignment = listCell.getVerticalAlignment();
			switch(alignment)
			{
				case EXPAND:
				case TOP:
					component.setY(listCell.getY());
					break;
				case MIDDLE:
					component.setY(listCell.getY() + listCell.getHeight() / 2 - component.getHeight() / 2);
					break;
				case BOTTOM:
					component.setY(listCell.getY() + listCell.getHeight() - component.getHeight());
					break;
				default:
					throw new DRDesignReportException("Component alignment " + alignment.name() + " not supported");
			}
			if(component instanceof DRDesignList)
			{
				componentY((DRDesignList)component);
			}
		}
	}
	
	private static void componentWidth(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		for(final DRDesignListCell listCell : list.getListCells())
		{
			final DRDesignComponent component = listCell.getComponent();
			final HorizontalCellComponentAlignment alignment = listCell.getHorizontalAlignment();
			switch(alignment)
			{
				case FLOAT:
				case EXPAND:
					component.setWidth(listCell.getWidth());
					break;
				case LEFT:
				case CENTER:
				case RIGHT:
					break;
				default:
					throw new DRDesignReportException("Component alignment " + alignment.name() + " not supported");
			}
			if(component instanceof DRDesignList)
			{
				componentWidth((DRDesignList)component);
			}
		}
	}
	
	private static void componentHeight(final DRDesignList list)
	{
		if(!list.isCalculateComponents())
		{
			return;
		}
		
		for(final DRDesignListCell listCell : list.getListCells())
		{
			final DRDesignComponent component = listCell.getComponent();
			final VerticalCellComponentAlignment alignment = listCell.getVerticalAlignment();
			switch(alignment)
			{
				case EXPAND:
					component.setHeight(listCell.getHeight());
					break;
				case TOP:
				case MIDDLE:
				case BOTTOM:
					break;
				default:
					throw new DRDesignReportException("Component alignment " + alignment.name() + " not supported");
			}
			if(component instanceof DRDesignList)
			{
				componentHeight((DRDesignList)component);
			}
		}
	}
}
