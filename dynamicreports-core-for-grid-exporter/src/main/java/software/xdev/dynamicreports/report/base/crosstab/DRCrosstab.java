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
package software.xdev.dynamicreports.report.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRDimensionComponent;
import software.xdev.dynamicreports.report.base.style.DRSimpleStyle;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRCrosstab extends DRDimensionComponent implements DRICrosstab
{

	private DRCrosstabDataset dataset;
	private Boolean repeatColumnHeaders;
	private Boolean repeatRowHeaders;
	private Integer columnBreakOffset;
	private Boolean ignoreWidth;
	private RunDirection runDirection;
	private Integer cellWidth;
	private Integer cellHeight;
	private Boolean highlightOddRows;
	private DRSimpleStyle oddRowStyle;
	private Boolean highlightEvenRows;
	private DRSimpleStyle evenRowStyle;
	private DRIReportStyle groupStyle;
	private DRIReportStyle groupTotalStyle;
	private DRIReportStyle grandTotalStyle;
	private DRIReportStyle cellStyle;
	private DRIReportStyle measureTitleStyle;
	private DRCrosstabCellContent whenNoDataCell;
	private DRCrosstabCellContent headerCell;
	private List<DRICrosstabColumnGroup<?>> columnGroups;
	private List<DRICrosstabRowGroup<?>> rowGroups;
	private List<DRICrosstabVariable<?>> variables;
	private List<DRICrosstabMeasure<?>> measures;
	
	@Override
	protected void init()
	{
		super.init();
		this.dataset = new DRCrosstabDataset();
		this.columnGroups = new ArrayList<>();
		this.rowGroups = new ArrayList<>();
		this.variables = new ArrayList<>();
		this.measures = new ArrayList<>();
		
		this.whenNoDataCell = new DRCrosstabCellContent();
		this.headerCell = new DRCrosstabCellContent();
	}
	
	@Override
	public DRCrosstabDataset getDataset()
	{
		return this.dataset;
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
	public DRCrosstabCellContent getWhenNoDataCell()
	{
		return this.whenNoDataCell;
	}
	
	@Override
	public DRCrosstabCellContent getHeaderCell()
	{
		return this.headerCell;
	}
	
	@Override
	public List<DRICrosstabColumnGroup<?>> getColumnGroups()
	{
		return this.columnGroups;
	}
	
	@Override
	public Integer getCellWidth()
	{
		return this.cellWidth;
	}
	
	public void setCellWidth(final Integer cellWidth)
	{
		this.cellWidth = cellWidth;
	}
	
	@Override
	public Integer getCellHeight()
	{
		return this.cellHeight;
	}
	
	public void setCellHeight(final Integer cellHeight)
	{
		this.cellHeight = cellHeight;
	}
	
	@Override
	public Boolean getHighlightOddRows()
	{
		return this.highlightOddRows;
	}
	
	public void setHighlightOddRows(final Boolean highlightOddRows)
	{
		this.highlightOddRows = highlightOddRows;
	}
	
	@Override
	public DRSimpleStyle getOddRowStyle()
	{
		return this.oddRowStyle;
	}
	
	public void setOddRowStyle(final DRSimpleStyle oddRowStyle)
	{
		this.oddRowStyle = oddRowStyle;
	}
	
	@Override
	public Boolean getHighlightEvenRows()
	{
		return this.highlightEvenRows;
	}
	
	public void setHighlightEvenRows(final Boolean highlightEvenRows)
	{
		this.highlightEvenRows = highlightEvenRows;
	}
	
	@Override
	public DRSimpleStyle getEvenRowStyle()
	{
		return this.evenRowStyle;
	}
	
	public void setEvenRowStyle(final DRSimpleStyle evenRowStyle)
	{
		this.evenRowStyle = evenRowStyle;
	}
	
	@Override
	public DRIReportStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	public void setGroupStyle(final DRIReportStyle groupStyle)
	{
		this.groupStyle = groupStyle;
	}
	
	@Override
	public DRIReportStyle getGroupTotalStyle()
	{
		return this.groupTotalStyle;
	}
	
	public void setGroupTotalStyle(final DRIReportStyle groupTotalStyle)
	{
		this.groupTotalStyle = groupTotalStyle;
	}
	
	@Override
	public DRIReportStyle getGrandTotalStyle()
	{
		return this.grandTotalStyle;
	}
	
	public void setGrandTotalStyle(final DRIReportStyle grandTotalStyle)
	{
		this.grandTotalStyle = grandTotalStyle;
	}
	
	@Override
	public DRIReportStyle getCellStyle()
	{
		return this.cellStyle;
	}
	
	public void setCellStyle(final DRIReportStyle cellStyle)
	{
		this.cellStyle = cellStyle;
	}
	
	@Override
	public DRIReportStyle getMeasureTitleStyle()
	{
		return this.measureTitleStyle;
	}
	
	public void setMeasureTitleStyle(final DRIReportStyle measureTitleStyle)
	{
		this.measureTitleStyle = measureTitleStyle;
	}
	
	public void addColumnGroup(final DRICrosstabColumnGroup<?> columnGroup)
	{
		Validate.notNull(columnGroup, "columnGroup must not be null");
		this.columnGroups.add(columnGroup);
	}
	
	@Override
	public List<DRICrosstabRowGroup<?>> getRowGroups()
	{
		return this.rowGroups;
	}
	
	public void addRowGroup(final DRICrosstabRowGroup<?> rowGroup)
	{
		Validate.notNull(rowGroup, "rowGroup must not be null");
		this.rowGroups.add(rowGroup);
	}
	
	@Override
	public List<DRICrosstabVariable<?>> getVariables()
	{
		return this.variables;
	}
	
	public void addVariable(final DRICrosstabVariable<?> variable)
	{
		Validate.notNull(variable, "variable must not be null");
		this.variables.add(variable);
	}
	
	@Override
	public List<DRICrosstabMeasure<?>> getMeasures()
	{
		return this.measures;
	}
	
	public void addMeasure(final DRICrosstabMeasure<?> measure)
	{
		Validate.notNull(measure, "measure must not be null");
		this.measures.add(measure);
	}
}
