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
package software.xdev.dynamicreports.jasper.definition.export;

import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.report.constant.ImageAnchorType;
import software.xdev.dynamicreports.report.constant.RunDirection;


public interface JasperIExcelExporter extends JasperIExporter
{
	
	public Boolean getOnePagePerSheet();
	
	public Boolean getRemoveEmptySpaceBetweenRows();
	
	public Boolean getRemoveEmptySpaceBetweenColumns();
	
	public Boolean getWhitePageBackground();
	
	public Boolean getDetectCellType();
	
	public List<String> getSheetNames();
	
	public Boolean getFontSizeFixEnabled();
	
	public Boolean getImageBorderFixEnabled();
	
	public Integer getMaxRowsPerSheet();
	
	public Boolean getIgnoreGraphics();
	
	public Boolean getCollapseRowSpan();
	
	public Boolean getIgnoreCellBorder();
	
	public Boolean getIgnoreCellBackground();
	
	public String getPassword();
	
	public Boolean getIgnorePageMargins();
	
	public Boolean getWrapText();
	
	public Boolean getCellLocked();
	
	public Boolean getCellHidden();
	
	public String getSheetHeaderLeft();
	
	public String getSheetHeaderCenter();
	
	public String getSheetHeaderRight();
	
	public String getSheetFooterLeft();
	
	public String getSheetFooterCenter();
	
	public String getSheetFooterRight();
	
	public Map<String, String> getFormatPatternsMap();
	
	public Boolean getIgnoreHyperLink();
	
	public Boolean getIgnoreAnchors();
	
	public Integer getFitWidth();
	
	public Integer getFitHeight();
	
	public Integer getPageScale();
	
	public RunDirection getSheetDirection();
	
	public Float getColumnWidthRatio();
	
	public Boolean getUseTimeZone();
	
	public Integer getFirstPageNumber();
	
	public Boolean getShowGridLines();
	
	public ImageAnchorType getImageAnchorType();
	
	public Boolean getCreateCustomPalette();
	
	public String getWorkbookTemplate();
	
	public Boolean getKeepWorkbookTemplateSheets();
}
