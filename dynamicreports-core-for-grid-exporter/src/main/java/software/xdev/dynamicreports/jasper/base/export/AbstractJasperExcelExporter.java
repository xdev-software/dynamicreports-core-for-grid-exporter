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
package software.xdev.dynamicreports.jasper.base.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.jasper.definition.export.JasperIExcelExporter;
import software.xdev.dynamicreports.report.constant.ImageAnchorType;
import software.xdev.dynamicreports.report.constant.RunDirection;


public abstract class AbstractJasperExcelExporter extends AbstractJasperExporter implements JasperIExcelExporter
{

	private Boolean onePagePerSheet;
	private Boolean removeEmptySpaceBetweenRows;
	private Boolean removeEmptySpaceBetweenColumns;
	private Boolean whitePageBackground;
	private Boolean detectCellType;
	private List<String> sheetNames;
	private Boolean fontSizeFixEnabled;
	private Boolean imageBorderFixEnabled;
	private Integer maxRowsPerSheet;
	private Boolean ignoreGraphics;
	private Boolean collapseRowSpan;
	private Boolean ignoreCellBorder;
	private Boolean ignoreCellBackground;
	private String password;
	private Boolean ignorePageMargins;
	private Boolean wrapText;
	private Boolean cellLocked;
	private Boolean cellHidden;
	private String sheetHeaderLeft;
	private String sheetHeaderCenter;
	private String sheetHeaderRight;
	private String sheetFooterLeft;
	private String sheetFooterCenter;
	private String sheetFooterRight;
	private Map<String, String> formatPatternsMap;
	private Boolean ignoreHyperLink;
	private Boolean ignoreAnchors;
	private Integer fitWidth;
	private Integer fitHeight;
	private Integer pageScale;
	private RunDirection sheetDirection;
	private Float columnWidthRatio;
	private Boolean useTimeZone;
	private Integer firstPageNumber;
	private Boolean showGridLines;
	private ImageAnchorType imageAnchorType;
	private Boolean createCustomPalette;
	private String workbookTemplate;
	private Boolean keepWorkbookTemplateSheets;
	
	public AbstractJasperExcelExporter()
	{
		this.sheetNames = new ArrayList<>();
	}
	
	@Override
	public Boolean getOnePagePerSheet()
	{
		return this.onePagePerSheet;
	}
	
	public void setOnePagePerSheet(final Boolean onePagePerSheet)
	{
		this.onePagePerSheet = onePagePerSheet;
	}
	
	@Override
	public Boolean getRemoveEmptySpaceBetweenRows()
	{
		return this.removeEmptySpaceBetweenRows;
	}
	
	public void setRemoveEmptySpaceBetweenRows(final Boolean removeEmptySpaceBetweenRows)
	{
		this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
	}
	
	@Override
	public Boolean getRemoveEmptySpaceBetweenColumns()
	{
		return this.removeEmptySpaceBetweenColumns;
	}
	
	public void setRemoveEmptySpaceBetweenColumns(final Boolean removeEmptySpaceBetweenColumns)
	{
		this.removeEmptySpaceBetweenColumns = removeEmptySpaceBetweenColumns;
	}
	
	@Override
	public Boolean getWhitePageBackground()
	{
		return this.whitePageBackground;
	}
	
	public void setWhitePageBackground(final Boolean whitePageBackground)
	{
		this.whitePageBackground = whitePageBackground;
	}
	
	@Override
	public Boolean getDetectCellType()
	{
		return this.detectCellType;
	}
	
	public void setDetectCellType(final Boolean detectCellType)
	{
		this.detectCellType = detectCellType;
	}
	
	@Override
	public List<String> getSheetNames()
	{
		return this.sheetNames;
	}
	
	public void setSheetNames(final List<String> sheetNames)
	{
		this.sheetNames = sheetNames;
	}
	
	public void addSheetName(final String sheetName)
	{
		this.sheetNames.add(sheetName);
	}
	
	@Override
	public Boolean getFontSizeFixEnabled()
	{
		return this.fontSizeFixEnabled;
	}
	
	public void setFontSizeFixEnabled(final Boolean fontSizeFixEnabled)
	{
		this.fontSizeFixEnabled = fontSizeFixEnabled;
	}
	
	@Override
	public Boolean getImageBorderFixEnabled()
	{
		return this.imageBorderFixEnabled;
	}
	
	public void setImageBorderFixEnabled(final Boolean imageBorderFixEnabled)
	{
		this.imageBorderFixEnabled = imageBorderFixEnabled;
	}
	
	@Override
	public Integer getMaxRowsPerSheet()
	{
		return this.maxRowsPerSheet;
	}
	
	public void setMaxRowsPerSheet(final Integer maxRowsPerSheet)
	{
		this.maxRowsPerSheet = maxRowsPerSheet;
	}
	
	@Override
	public Boolean getIgnoreGraphics()
	{
		return this.ignoreGraphics;
	}
	
	public void setIgnoreGraphics(final Boolean ignoreGraphics)
	{
		this.ignoreGraphics = ignoreGraphics;
	}
	
	@Override
	public Boolean getCollapseRowSpan()
	{
		return this.collapseRowSpan;
	}
	
	public void setCollapseRowSpan(final Boolean collapseRowSpan)
	{
		this.collapseRowSpan = collapseRowSpan;
	}
	
	@Override
	public Boolean getIgnoreCellBorder()
	{
		return this.ignoreCellBorder;
	}
	
	public void setIgnoreCellBorder(final Boolean ignoreCellBorder)
	{
		this.ignoreCellBorder = ignoreCellBorder;
	}
	
	@Override
	public Boolean getIgnoreCellBackground()
	{
		return this.ignoreCellBackground;
	}
	
	public void setIgnoreCellBackground(final Boolean ignoreCellBackground)
	{
		this.ignoreCellBackground = ignoreCellBackground;
	}
	
	@Override
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(final String password)
	{
		this.password = password;
	}
	
	@Override
	public Boolean getIgnorePageMargins()
	{
		return this.ignorePageMargins;
	}
	
	public void setIgnorePageMargins(final Boolean ignorePageMargins)
	{
		this.ignorePageMargins = ignorePageMargins;
	}
	
	@Override
	public Boolean getWrapText()
	{
		return this.wrapText;
	}
	
	public void setWrapText(final Boolean wrapText)
	{
		this.wrapText = wrapText;
	}
	
	@Override
	public Boolean getCellLocked()
	{
		return this.cellLocked;
	}
	
	public void setCellLocked(final Boolean cellLocked)
	{
		this.cellLocked = cellLocked;
	}
	
	@Override
	public Boolean getCellHidden()
	{
		return this.cellHidden;
	}
	
	public void setCellHidden(final Boolean cellHidden)
	{
		this.cellHidden = cellHidden;
	}
	
	@Override
	public String getSheetHeaderLeft()
	{
		return this.sheetHeaderLeft;
	}
	
	public void setSheetHeaderLeft(final String sheetHeaderLeft)
	{
		this.sheetHeaderLeft = sheetHeaderLeft;
	}
	
	@Override
	public String getSheetHeaderCenter()
	{
		return this.sheetHeaderCenter;
	}
	
	public void setSheetHeaderCenter(final String sheetHeaderCenter)
	{
		this.sheetHeaderCenter = sheetHeaderCenter;
	}
	
	@Override
	public String getSheetHeaderRight()
	{
		return this.sheetHeaderRight;
	}
	
	public void setSheetHeaderRight(final String sheetHeaderRight)
	{
		this.sheetHeaderRight = sheetHeaderRight;
	}
	
	@Override
	public String getSheetFooterLeft()
	{
		return this.sheetFooterLeft;
	}
	
	public void setSheetFooterLeft(final String sheetFooterLeft)
	{
		this.sheetFooterLeft = sheetFooterLeft;
	}
	
	@Override
	public String getSheetFooterCenter()
	{
		return this.sheetFooterCenter;
	}
	
	public void setSheetFooterCenter(final String sheetFooterCenter)
	{
		this.sheetFooterCenter = sheetFooterCenter;
	}
	
	@Override
	public String getSheetFooterRight()
	{
		return this.sheetFooterRight;
	}
	
	public void setSheetFooterRight(final String sheetFooterRight)
	{
		this.sheetFooterRight = sheetFooterRight;
	}
	
	@Override
	public Map<String, String> getFormatPatternsMap()
	{
		return this.formatPatternsMap;
	}
	
	public void setFormatPatternsMap(final Map<String, String> formatPatternsMap)
	{
		this.formatPatternsMap = formatPatternsMap;
	}
	
	@Override
	public Boolean getIgnoreHyperLink()
	{
		return this.ignoreHyperLink;
	}
	
	public void setIgnoreHyperLink(final Boolean ignoreHyperLink)
	{
		this.ignoreHyperLink = ignoreHyperLink;
	}
	
	@Override
	public Boolean getIgnoreAnchors()
	{
		return this.ignoreAnchors;
	}
	
	public void setIgnoreAnchors(final Boolean ignoreAnchors)
	{
		this.ignoreAnchors = ignoreAnchors;
	}
	
	@Override
	public Integer getFitWidth()
	{
		return this.fitWidth;
	}
	
	public void setFitWidth(final Integer fitWidth)
	{
		this.fitWidth = fitWidth;
	}
	
	@Override
	public Integer getFitHeight()
	{
		return this.fitHeight;
	}
	
	public void setFitHeight(final Integer fitHeight)
	{
		this.fitHeight = fitHeight;
	}
	
	@Override
	public Integer getPageScale()
	{
		return this.pageScale;
	}
	
	public void setPageScale(final Integer pageScale)
	{
		this.pageScale = pageScale;
	}
	
	@Override
	public RunDirection getSheetDirection()
	{
		return this.sheetDirection;
	}
	
	public void setSheetDirection(final RunDirection sheetDirection)
	{
		this.sheetDirection = sheetDirection;
	}
	
	@Override
	public Float getColumnWidthRatio()
	{
		return this.columnWidthRatio;
	}
	
	public void setColumnWidthRatio(final Float columnWidthRatio)
	{
		this.columnWidthRatio = columnWidthRatio;
	}
	
	@Override
	public Boolean getUseTimeZone()
	{
		return this.useTimeZone;
	}
	
	public void setUseTimeZone(final Boolean useTimeZone)
	{
		this.useTimeZone = useTimeZone;
	}
	
	@Override
	public Integer getFirstPageNumber()
	{
		return this.firstPageNumber;
	}
	
	public void setFirstPageNumber(final Integer firstPageNumber)
	{
		this.firstPageNumber = firstPageNumber;
	}
	
	@Override
	public Boolean getShowGridLines()
	{
		return this.showGridLines;
	}
	
	public void setShowGridLines(final Boolean showGridLines)
	{
		this.showGridLines = showGridLines;
	}
	
	@Override
	public ImageAnchorType getImageAnchorType()
	{
		return this.imageAnchorType;
	}
	
	public void setImageAnchorType(final ImageAnchorType imageAnchorType)
	{
		this.imageAnchorType = imageAnchorType;
	}
	
	@Override
	public Boolean getCreateCustomPalette()
	{
		return this.createCustomPalette;
	}
	
	public void setCreateCustomPalette(final Boolean createCustomPalette)
	{
		this.createCustomPalette = createCustomPalette;
	}
	
	@Override
	public String getWorkbookTemplate()
	{
		return this.workbookTemplate;
	}
	
	public void setWorkbookTemplate(final String workbookTemplate)
	{
		this.workbookTemplate = workbookTemplate;
	}
	
	@Override
	public Boolean getKeepWorkbookTemplateSheets()
	{
		return this.keepWorkbookTemplateSheets;
	}
	
	public void setKeepWorkbookTemplateSheets(final Boolean keepWorkbookTemplateSheets)
	{
		this.keepWorkbookTemplateSheets = keepWorkbookTemplateSheets;
	}
}
