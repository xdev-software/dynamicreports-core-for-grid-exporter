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
package software.xdev.dynamicreports.report.definition.crosstab;

import java.util.List;

import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRISimpleStyle;


public interface DRICrosstab extends DRIDimensionComponent
{
	
	public DRICrosstabDataset getDataset();
	
	public Boolean isRepeatColumnHeaders();
	
	public Boolean isRepeatRowHeaders();
	
	public Integer getColumnBreakOffset();
	
	public Boolean getIgnoreWidth();
	
	public RunDirection getRunDirection();
	
	public Integer getCellWidth();
	
	public Integer getCellHeight();
	
	public Boolean getHighlightOddRows();
	
	public DRISimpleStyle getOddRowStyle();
	
	public Boolean getHighlightEvenRows();
	
	public DRISimpleStyle getEvenRowStyle();
	
	public DRIReportStyle getGroupStyle();
	
	public DRIReportStyle getGroupTotalStyle();
	
	public DRIReportStyle getGrandTotalStyle();
	
	public DRIReportStyle getCellStyle();
	
	public DRIReportStyle getMeasureTitleStyle();
	
	public DRICrosstabCellContent getWhenNoDataCell();
	
	public DRICrosstabCellContent getHeaderCell();
	
	public List<DRICrosstabColumnGroup<?>> getColumnGroups();
	
	public List<DRICrosstabRowGroup<?>> getRowGroups();
	
	public List<DRICrosstabVariable<?>> getVariables();
	
	public List<DRICrosstabMeasure<?>> getMeasures();
}
