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
package software.xdev.dynamicreports.jasper.builder.export;

import java.util.Map;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.jasper.base.export.AbstractJasperExcelExporter;
import software.xdev.dynamicreports.report.constant.ImageAnchorType;
import software.xdev.dynamicreports.report.constant.RunDirection;


@SuppressWarnings("unchecked")
public abstract class AbstractJasperExcelExporterBuilder<T extends AbstractJasperExcelExporterBuilder<T, U>,
	U extends AbstractJasperExcelExporter>
	extends AbstractJasperExporterBuilder<T, U>
{

	protected AbstractJasperExcelExporterBuilder(final U exporter)
	{
		super(exporter);
	}
	
	public T setOnePagePerSheet(final Boolean onePagePerSheet)
	{
		this.getObject().setOnePagePerSheet(onePagePerSheet);
		return (T)this;
	}
	
	public T setRemoveEmptySpaceBetweenRows(final Boolean removeEmptySpaceBetweenRows)
	{
		this.getObject().setRemoveEmptySpaceBetweenRows(removeEmptySpaceBetweenRows);
		return (T)this;
	}
	
	public T setRemoveEmptySpaceBetweenColumns(final Boolean removeEmptySpaceBetweenColumns)
	{
		this.getObject().setRemoveEmptySpaceBetweenColumns(removeEmptySpaceBetweenColumns);
		return (T)this;
	}
	
	public T setWhitePageBackground(final Boolean whitePageBackground)
	{
		this.getObject().setWhitePageBackground(whitePageBackground);
		return (T)this;
	}
	
	public T setDetectCellType(final Boolean detectCellType)
	{
		this.getObject().setDetectCellType(detectCellType);
		return (T)this;
	}
	
	public T sheetNames(final String... sheetNames)
	{
		return this.addSheetName(sheetNames);
	}
	
	public T addSheetName(final String... sheetNames)
	{
		Validate.notNull(sheetNames, "sheetNames must not be null");
		Validate.noNullElements(sheetNames, "sheetNames must not contains null sheetName");
		for(final String sheetName : sheetNames)
		{
			this.getObject().addSheetName(sheetName);
		}
		return (T)this;
	}
	
	public T setFontSizeFixEnabled(final Boolean fontSizeFixEnabled)
	{
		this.getObject().setFontSizeFixEnabled(fontSizeFixEnabled);
		return (T)this;
	}
	
	public T setImageBorderFixEnabled(final Boolean imageBorderFixEnabled)
	{
		this.getObject().setImageBorderFixEnabled(imageBorderFixEnabled);
		return (T)this;
	}
	
	public T setMaxRowsPerSheet(final Integer maxRowsPerSheet)
	{
		this.getObject().setMaxRowsPerSheet(maxRowsPerSheet);
		return (T)this;
	}
	
	public T setIgnoreGraphics(final Boolean ignoreGraphics)
	{
		this.getObject().setIgnoreGraphics(ignoreGraphics);
		return (T)this;
	}
	
	public T setCollapseRowSpan(final Boolean collapseRowSpan)
	{
		this.getObject().setCollapseRowSpan(collapseRowSpan);
		return (T)this;
	}
	
	public T setIgnoreCellBorder(final Boolean ignoreCellBorder)
	{
		this.getObject().setIgnoreCellBorder(ignoreCellBorder);
		return (T)this;
	}
	
	public T setIgnoreCellBackground(final Boolean ignoreCellBackground)
	{
		this.getObject().setIgnoreCellBackground(ignoreCellBackground);
		return (T)this;
	}
	
	public T setPassword(final String password)
	{
		this.getObject().setPassword(password);
		return (T)this;
	}
	
	public T setIgnorePageMargins(final Boolean ignorePageMargins)
	{
		this.getObject().setIgnorePageMargins(ignorePageMargins);
		return (T)this;
	}
	
	public T setWrapText(final Boolean wrapText)
	{
		this.getObject().setWrapText(wrapText);
		return (T)this;
	}
	
	public T setCellLocked(final Boolean cellLocked)
	{
		this.getObject().setCellLocked(cellLocked);
		return (T)this;
	}
	
	public T setCellHidden(final Boolean cellHidden)
	{
		this.getObject().setCellHidden(cellHidden);
		return (T)this;
	}
	
	public T setSheetHeaderLeft(final String sheetHeaderLeft)
	{
		this.getObject().setSheetHeaderLeft(sheetHeaderLeft);
		return (T)this;
	}
	
	public T setSheetHeaderCenter(final String sheetHeaderCenter)
	{
		this.getObject().setSheetHeaderCenter(sheetHeaderCenter);
		return (T)this;
	}
	
	public T setSheetHeaderRight(final String sheetHeaderRight)
	{
		this.getObject().setSheetHeaderRight(sheetHeaderRight);
		return (T)this;
	}
	
	public T setSheetFooterLeft(final String sheetFooterLeft)
	{
		this.getObject().setSheetFooterLeft(sheetFooterLeft);
		return (T)this;
	}
	
	public T setSheetFooterCenter(final String sheetFooterCenter)
	{
		this.getObject().setSheetFooterCenter(sheetFooterCenter);
		return (T)this;
	}
	
	public T setSheetFooterRight(final String sheetFooterRight)
	{
		this.getObject().setSheetFooterRight(sheetFooterRight);
		return (T)this;
	}
	
	public T setFormatPatternsMap(final Map<String, String> formatPatternsMap)
	{
		this.getObject().setFormatPatternsMap(formatPatternsMap);
		return (T)this;
	}
	
	public T setIgnoreHyperLink(final Boolean ignoreHyperLink)
	{
		this.getObject().setIgnoreHyperLink(ignoreHyperLink);
		return (T)this;
	}
	
	public T setIgnoreAnchors(final Boolean ignoreAnchors)
	{
		this.getObject().setIgnoreAnchors(ignoreAnchors);
		return (T)this;
	}
	
	public T setFitWidth(final Integer fitWidth)
	{
		this.getObject().setFitWidth(fitWidth);
		return (T)this;
	}
	
	public T setFitHeight(final Integer fitHeight)
	{
		this.getObject().setFitHeight(fitHeight);
		return (T)this;
	}
	
	public T setPageScale(final Integer pageScale)
	{
		this.getObject().setPageScale(pageScale);
		return (T)this;
	}
	
	public T setSheetDirection(final RunDirection sheetDirection)
	{
		this.getObject().setSheetDirection(sheetDirection);
		return (T)this;
	}
	
	public T setColumnWidthRatio(final Float columnWidthRatio)
	{
		this.getObject().setColumnWidthRatio(columnWidthRatio);
		return (T)this;
	}
	
	public T setUseTimeZone(final Boolean useTimeZone)
	{
		this.getObject().setUseTimeZone(useTimeZone);
		return (T)this;
	}
	
	public T setFirstPageNumber(final Integer firstPageNumber)
	{
		this.getObject().setFirstPageNumber(firstPageNumber);
		return (T)this;
	}
	
	public T setShowGridLines(final Boolean showGridLines)
	{
		this.getObject().setShowGridLines(showGridLines);
		return (T)this;
	}
	
	public T setImageAnchorType(final ImageAnchorType imageAnchorType)
	{
		this.getObject().setImageAnchorType(imageAnchorType);
		return (T)this;
	}
	
	public T setCreateCustomPalette(final Boolean createCustomPalette)
	{
		this.getObject().setCreateCustomPalette(createCustomPalette);
		return (T)this;
	}
	
	public T setWorkbookTemplate(final String workbookTemplate)
	{
		this.getObject().setWorkbookTemplate(workbookTemplate);
		return (T)this;
	}
	
	public T setKeepWorkbookTemplateSheets(final Boolean keepWorkbookTemplateSheets)
	{
		this.getObject().setKeepWorkbookTemplateSheets(keepWorkbookTemplateSheets);
		return (T)this;
	}
}
