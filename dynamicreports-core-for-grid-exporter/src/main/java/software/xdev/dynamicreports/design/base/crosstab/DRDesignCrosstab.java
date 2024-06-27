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
package software.xdev.dynamicreports.design.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstab;
import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import software.xdev.dynamicreports.report.constant.RunDirection;


public class DRDesignCrosstab extends DRDesignComponent implements DRIDesignCrosstab
{

	private DRDesignCrosstabDataset dataset;
	private Boolean repeatColumnHeaders;
	private Boolean repeatRowHeaders;
	private Integer columnBreakOffset;
	private Boolean ignoreWidth;
	private RunDirection runDirection;
	private DRDesignCrosstabCellContent whenNoDataCell;
	private DRDesignCrosstabCellContent headerCell;
	private List<DRDesignCrosstabColumnGroup> columnGroups;
	private List<DRDesignCrosstabRowGroup> rowGroups;
	private List<DRDesignCrosstabCell> cells;
	private List<DRIDesignCrosstabMeasure> measures;
	
	public DRDesignCrosstab()
	{
		super("crosstab");
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.columnGroups = new ArrayList<>();
		this.rowGroups = new ArrayList<>();
		this.cells = new ArrayList<>();
		this.measures = new ArrayList<>();
	}
	
	@Override
	public DRDesignCrosstabDataset getDataset()
	{
		return this.dataset;
	}
	
	public void setDataset(final DRDesignCrosstabDataset dataset)
	{
		this.dataset = dataset;
	}
	
	@Override
	public Boolean isRepeatColumnHeaders()
	{
		return this.repeatColumnHeaders;
	}
	
	public void setRepeatColumnHeaders(final Boolean repeatColumnHeaders)
	{
		this.repeatColumnHeaders = repeatColumnHeaders;
	}
	
	@Override
	public Boolean isRepeatRowHeaders()
	{
		return this.repeatRowHeaders;
	}
	
	public void setRepeatRowHeaders(final Boolean repeatRowHeaders)
	{
		this.repeatRowHeaders = repeatRowHeaders;
	}
	
	@Override
	public Integer getColumnBreakOffset()
	{
		return this.columnBreakOffset;
	}
	
	public void setColumnBreakOffset(final Integer columnBreakOffset)
	{
		this.columnBreakOffset = columnBreakOffset;
	}
	
	@Override
	public Boolean getIgnoreWidth()
	{
		return this.ignoreWidth;
	}
	
	public void setIgnoreWidth(final Boolean ignoreWidth)
	{
		this.ignoreWidth = ignoreWidth;
	}
	
	@Override
	public RunDirection getRunDirection()
	{
		return this.runDirection;
	}
	
	public void setRunDirection(final RunDirection runDirection)
	{
		this.runDirection = runDirection;
	}
	
	@Override
	public DRDesignCrosstabCellContent getWhenNoDataCell()
	{
		return this.whenNoDataCell;
	}
	
	public void setWhenNoDataCell(final DRDesignCrosstabCellContent whenNoDataCell)
	{
		this.whenNoDataCell = whenNoDataCell;
	}
	
	@Override
	public DRDesignCrosstabCellContent getHeaderCell()
	{
		return this.headerCell;
	}
	
	public void setHeaderCell(final DRDesignCrosstabCellContent headerCell)
	{
		this.headerCell = headerCell;
	}
	
	@Override
	public List<DRDesignCrosstabColumnGroup> getColumnGroups()
	{
		return this.columnGroups;
	}
	
	public void setColumnGroups(final List<DRDesignCrosstabColumnGroup> columnGroups)
	{
		this.columnGroups = columnGroups;
	}
	
	@Override
	public List<DRDesignCrosstabRowGroup> getRowGroups()
	{
		return this.rowGroups;
	}
	
	public void setRowGroups(final List<DRDesignCrosstabRowGroup> rowGroups)
	{
		this.rowGroups = rowGroups;
	}
	
	@Override
	public List<DRDesignCrosstabCell> getCells()
	{
		return this.cells;
	}
	
	public void setCells(final List<DRDesignCrosstabCell> cells)
	{
		this.cells = cells;
	}
	
	@Override
	public List<DRIDesignCrosstabMeasure> getMeasures()
	{
		return this.measures;
	}
	
	public void setMeasures(final List<DRIDesignCrosstabMeasure> measures)
	{
		this.measures = measures;
	}
}
