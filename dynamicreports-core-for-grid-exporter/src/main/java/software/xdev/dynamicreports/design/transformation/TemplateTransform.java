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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCell;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabColumnGroup;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabRowGroup;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.definition.DRIDesignBand;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignPage;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRIMargin;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.DRIReportTemplate;
import software.xdev.dynamicreports.report.definition.DRISubtotal;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.definition.column.DRIBooleanColumn;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.component.DRIBooleanField;
import software.xdev.dynamicreports.report.definition.component.DRIBreak;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;
import software.xdev.dynamicreports.report.definition.component.DRIEllipse;
import software.xdev.dynamicreports.report.definition.component.DRIFiller;
import software.xdev.dynamicreports.report.definition.component.DRIGenericElement;
import software.xdev.dynamicreports.report.definition.component.DRIImage;
import software.xdev.dynamicreports.report.definition.component.DRILine;
import software.xdev.dynamicreports.report.definition.component.DRIList;
import software.xdev.dynamicreports.report.definition.component.DRIMap;
import software.xdev.dynamicreports.report.definition.component.DRIMultiPageList;
import software.xdev.dynamicreports.report.definition.component.DRIPageXofY;
import software.xdev.dynamicreports.report.definition.component.DRIRectangle;
import software.xdev.dynamicreports.report.definition.component.DRISubreport;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.component.DRIXyList;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRISimpleStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class TemplateTransform
{
	private final DRIReport report;
	private final DesignTransformAccessor accessor;
	private final DRIReportTemplate template;
	private final DRITemplateDesign<?> templateDesign;
	
	public TemplateTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.report = accessor.getReport();
		this.template = this.report.getTemplate();
		this.templateDesign = this.report.getTemplateDesign();
	}
	
	public String getReportName()
	{
		if(this.report.getReportName() != null)
		{
			return this.report.getReportName();
		}
		if(this.templateDesign.getReportName() != null)
		{
			return this.templateDesign.getReportName();
		}
		return Defaults.getDefaults().getReportName();
	}
	
	public Locale getLocale()
	{
		if(this.report.getLocale() != null)
		{
			return this.report.getLocale();
		}
		if(this.template.getLocale() != null)
		{
			return this.template.getLocale();
		}
		return Defaults.getDefaults().getLocale();
	}
	
	protected boolean isShowColumnTitle()
	{
		if(this.report.getShowColumnTitle() != null)
		{
			return this.report.getShowColumnTitle();
		}
		if(this.template.getShowColumnTitle() != null)
		{
			return this.template.getShowColumnTitle();
		}
		return Defaults.getDefaults().isShowColumnTitle();
	}
	
	protected boolean isShowColumnValues()
	{
		if(this.report.getShowColumnValues() != null)
		{
			return this.report.getShowColumnValues();
		}
		if(this.template.getShowColumnValues() != null)
		{
			return this.template.getShowColumnValues();
		}
		return Defaults.getDefaults().isShowColumnValues();
	}
	
	public String getResourceBundleName()
	{
		if(this.report.getResourceBundleName() != null)
		{
			return this.report.getResourceBundleName();
		}
		if(this.templateDesign.getResourceBundleName() != null)
		{
			return this.templateDesign.getResourceBundleName();
		}
		return null;
	}
	
	public boolean isIgnorePagination()
	{
		if(this.report.getIgnorePagination() != null)
		{
			return this.report.getIgnorePagination();
		}
		if(this.templateDesign.getIgnorePagination() != null)
		{
			return this.templateDesign.getIgnorePagination();
		}
		if(this.template.getIgnorePagination() != null)
		{
			return this.template.getIgnorePagination();
		}
		return Defaults.getDefaults().isIgnorePagination();
	}
	
	public WhenNoDataType getWhenNoDataType(final boolean emptyDetail, final DRIDesignBand noDataBand)
	{
		if(this.report.getWhenNoDataType() != null)
		{
			return this.report.getWhenNoDataType();
		}
		if(this.templateDesign.getWhenNoDataType() != null)
		{
			return this.templateDesign.getWhenNoDataType();
		}
		if(this.template.getWhenNoDataType() != null)
		{
			return this.template.getWhenNoDataType();
		}
		if(noDataBand != null)
		{
			return WhenNoDataType.NO_DATA_SECTION;
		}
		if(emptyDetail)
		{
			return WhenNoDataType.ALL_SECTIONS_NO_DETAIL;
		}
		return Defaults.getDefaults().getWhenNoDataType();
	}
	
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		if(this.report.getWhenResourceMissingType() != null)
		{
			return this.report.getWhenResourceMissingType();
		}
		if(this.templateDesign.getWhenResourceMissingType() != null)
		{
			return this.templateDesign.getWhenResourceMissingType();
		}
		if(this.template.getWhenResourceMissingType() != null)
		{
			return this.template.getWhenResourceMissingType();
		}
		return Defaults.getDefaults().getWhenResourceMissingType();
	}
	
	public boolean isTitleOnANewPage()
	{
		if(this.report.getTitleOnANewPage() != null)
		{
			return this.report.getTitleOnANewPage();
		}
		if(this.templateDesign.getTitleOnANewPage() != null)
		{
			return this.templateDesign.getTitleOnANewPage();
		}
		if(this.template.getTitleOnANewPage() != null)
		{
			return this.template.getTitleOnANewPage();
		}
		return Defaults.getDefaults().isTitleOnANewPage();
	}
	
	public boolean isSummaryOnANewPage()
	{
		if(this.report.getSummaryOnANewPage() != null)
		{
			return this.report.getSummaryOnANewPage();
		}
		if(this.templateDesign.getSummaryOnANewPage() != null)
		{
			return this.templateDesign.getSummaryOnANewPage();
		}
		if(this.template.getSummaryOnANewPage() != null)
		{
			return this.template.getSummaryOnANewPage();
		}
		return Defaults.getDefaults().isSummaryOnANewPage();
	}
	
	public boolean isSummaryWithPageHeaderAndFooter()
	{
		if(this.report.getSummaryWithPageHeaderAndFooter() != null)
		{
			return this.report.getSummaryWithPageHeaderAndFooter();
		}
		if(this.templateDesign.getSummaryWithPageHeaderAndFooter() != null)
		{
			return this.templateDesign.getSummaryWithPageHeaderAndFooter();
		}
		if(this.template.getSummaryWithPageHeaderAndFooter() != null)
		{
			return this.template.getSummaryWithPageHeaderAndFooter();
		}
		return Defaults.getDefaults().isSummaryWithPageHeaderAndFooter();
	}
	
	public boolean isFloatColumnFooter()
	{
		if(this.report.getFloatColumnFooter() != null)
		{
			return this.report.getFloatColumnFooter();
		}
		if(this.templateDesign.getFloatColumnFooter() != null)
		{
			return this.templateDesign.getFloatColumnFooter();
		}
		if(this.template.getFloatColumnFooter() != null)
		{
			return this.template.getFloatColumnFooter();
		}
		return Defaults.getDefaults().isFloatColumnFooter();
	}
	
	public Orientation getPrintOrder()
	{
		if(this.report.getPrintOrder() != null)
		{
			return this.report.getPrintOrder();
		}
		if(this.template.getPrintOrder() != null)
		{
			return this.template.getPrintOrder();
		}
		return Defaults.getDefaults().getPrintOrder();
	}
	
	public RunDirection getColumnDirection()
	{
		if(this.report.getColumnDirection() != null)
		{
			return this.report.getColumnDirection();
		}
		if(this.template.getColumnDirection() != null)
		{
			return this.template.getColumnDirection();
		}
		return Defaults.getDefaults().getColumnDirection();
	}
	
	public String getLanguage()
	{
		if(this.report.getLanguage() != null)
		{
			return this.report.getLanguage();
		}
		if(this.template.getLanguage() != null)
		{
			return this.template.getLanguage();
		}
		return Defaults.getDefaults().getLanguage();
	}
	
	public String getFieldDescription(final DRIField<?> field)
	{
		if(field.getDescription() != null)
		{
			return field.getDescription();
		}
		if(this.isUseFieldNameAsDescription())
		{
			return field.getName();
		}
		return null;
	}
	
	private boolean isUseFieldNameAsDescription()
	{
		if(this.report.getUseFieldNameAsDescription() != null)
		{
			return this.report.getUseFieldNameAsDescription();
		}
		if(this.template.getUseFieldNameAsDescription() != null)
		{
			return this.template.getUseFieldNameAsDescription();
		}
		return Defaults.getDefaults().isUseFieldNameAsDescription();
	}
	
	// table of contents
	
	public boolean isTableOfContents(final Map<String, JasperTocHeading> tocHeadings)
	{
		if(tocHeadings != null)
		{
			return true;
		}
		if(this.report.getTableOfContents() != null)
		{
			return this.report.getTableOfContents();
		}
		if(this.template.getTableOfContents() != null)
		{
			return this.template.getTableOfContents();
		}
		return Defaults.getDefaults().isTableOfContents();
	}
	
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		if(this.report.getTableOfContentsCustomizer() != null)
		{
			return this.report.getTableOfContentsCustomizer();
		}
		if(this.template.getTableOfContentsCustomizer() != null)
		{
			return this.template.getTableOfContentsCustomizer();
		}
		return Defaults.getDefaults().getTableOfContentsCustomizer();
	}
	
	public boolean isAddGroupToTableOfContents(final DRIGroup group)
	{
		if(group.getAddToTableOfContents() != null)
		{
			return group.getAddToTableOfContents();
		}
		return Defaults.getDefaults().isAddGroupToTableOfContents();
	}
	
	// style
	
	protected DRISimpleStyle getDetailOddRowStyle()
	{
		if(this.isHighlightDetailOddRows())
		{
			if(this.report.getDetailOddRowStyle() != null)
			{
				return this.report.getDetailOddRowStyle();
			}
			if(this.template.getDetailOddRowStyle() != null)
			{
				return this.template.getDetailOddRowStyle();
			}
			return Defaults.getDefaults().getDetailOddRowStyle();
		}
		return null;
	}
	
	protected DRISimpleStyle getDetailEvenRowStyle()
	{
		if(this.isHighlightDetailEvenRows())
		{
			if(this.report.getDetailEvenRowStyle() != null)
			{
				return this.report.getDetailEvenRowStyle();
			}
			if(this.template.getDetailEvenRowStyle() != null)
			{
				return this.template.getDetailEvenRowStyle();
			}
			return Defaults.getDefaults().getDetailEvenRowStyle();
		}
		return null;
	}
	
	private boolean isHighlightDetailOddRows()
	{
		if(this.report.getHighlightDetailOddRows() != null)
		{
			return this.report.getHighlightDetailOddRows();
		}
		if(this.template.getHighlightDetailOddRows() != null)
		{
			return this.template.getHighlightDetailOddRows();
		}
		return Defaults.getDefaults().isHighlightDetailOddRows();
	}
	
	private boolean isHighlightDetailEvenRows()
	{
		if(this.report.getHighlightDetailEvenRows() != null)
		{
			return this.report.getHighlightDetailEvenRows();
		}
		if(this.template.getHighlightDetailEvenRows() != null)
		{
			return this.template.getHighlightDetailEvenRows();
		}
		return Defaults.getDefaults().isHighlightDetailEvenRows();
	}
	
	protected String getDefaultFontName()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getFontName() != null)
		{
			return this.report.getDefaultFont().getFontName();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getFontName() != null)
		{
			return this.template.getDefaultFont().getFontName();
		}
		return Defaults.getDefaults().getFont().getFontName();
	}
	
	protected Integer getDefaultFontSize()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getFontSize() != null)
		{
			return this.report.getDefaultFont().getFontSize();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getFontSize() != null)
		{
			return this.template.getDefaultFont().getFontSize();
		}
		return Defaults.getDefaults().getFont().getFontSize();
	}
	
	protected Boolean getDefaultFontBold()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getBold() != null)
		{
			return this.report.getDefaultFont().getBold();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getBold() != null)
		{
			return this.template.getDefaultFont().getBold();
		}
		return Defaults.getDefaults().getFont().getBold();
	}
	
	protected Boolean getDefaultFontItalic()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getItalic() != null)
		{
			return this.report.getDefaultFont().getItalic();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getItalic() != null)
		{
			return this.template.getDefaultFont().getItalic();
		}
		return Defaults.getDefaults().getFont().getItalic();
	}
	
	protected Boolean getDefaultFontUnderline()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getUnderline() != null)
		{
			return this.report.getDefaultFont().getUnderline();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getUnderline() != null)
		{
			return this.template.getDefaultFont().getUnderline();
		}
		return Defaults.getDefaults().getFont().getUnderline();
	}
	
	protected Boolean getDefaultFontStrikeThrough()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getStrikeThrough() != null)
		{
			return this.report.getDefaultFont().getStrikeThrough();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getStrikeThrough() != null)
		{
			return this.template.getDefaultFont().getStrikeThrough();
		}
		return Defaults.getDefaults().getFont().getStrikeThrough();
	}
	
	protected String getDefaultFontPdfFontName()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getPdfFontName() != null)
		{
			return this.report.getDefaultFont().getPdfFontName();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getPdfFontName() != null)
		{
			return this.template.getDefaultFont().getPdfFontName();
		}
		return Defaults.getDefaults().getFont().getPdfFontName();
	}
	
	protected String getDefaultFontPdfEncoding()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getPdfEncoding() != null)
		{
			return this.report.getDefaultFont().getPdfEncoding();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getPdfEncoding() != null)
		{
			return this.template.getDefaultFont().getPdfEncoding();
		}
		return Defaults.getDefaults().getFont().getPdfEncoding();
	}
	
	protected Boolean getDefaultFontPdfEmbedded()
	{
		if(this.report.getDefaultFont() != null && this.report.getDefaultFont().getPdfEmbedded() != null)
		{
			return this.report.getDefaultFont().getPdfEmbedded();
		}
		if(this.template.getDefaultFont() != null && this.template.getDefaultFont().getPdfEmbedded() != null)
		{
			return this.template.getDefaultFont().getPdfEmbedded();
		}
		return Defaults.getDefaults().getFont().getPdfEmbedded();
	}
	
	protected DRIReportStyle getTextStyle()
	{
		if(this.report.getTextStyle() != null)
		{
			return this.report.getTextStyle();
		}
		if(this.template.getTextStyle() != null)
		{
			return this.template.getTextStyle();
		}
		return Defaults.getDefaults().getTextStyle();
	}
	
	protected DRIReportStyle getColumnTitleStyle()
	{
		if(this.report.getColumnTitleStyle() != null)
		{
			return this.report.getColumnTitleStyle();
		}
		if(this.template.getColumnTitleStyle() != null)
		{
			return this.template.getColumnTitleStyle();
		}
		if(Defaults.getDefaults().getColumnTitleStyle() != null)
		{
			return Defaults.getDefaults().getColumnTitleStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getColumnStyle(final boolean textStyle)
	{
		if(this.report.getColumnStyle() != null)
		{
			return this.report.getColumnStyle();
		}
		if(this.template.getColumnStyle() != null)
		{
			return this.template.getColumnStyle();
		}
		if(Defaults.getDefaults().getColumnStyle() != null)
		{
			return Defaults.getDefaults().getColumnStyle();
		}
		if(textStyle)
		{
			return this.getTextStyle();
		}
		return null;
	}
	
	protected DRIReportStyle getGroupTitleStyle()
	{
		if(this.report.getGroupTitleStyle() != null)
		{
			return this.report.getGroupTitleStyle();
		}
		if(this.template.getGroupTitleStyle() != null)
		{
			return this.template.getGroupTitleStyle();
		}
		if(Defaults.getDefaults().getGroupTitleStyle() != null)
		{
			return Defaults.getDefaults().getGroupTitleStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getGroupStyle()
	{
		if(this.report.getGroupStyle() != null)
		{
			return this.report.getGroupStyle();
		}
		if(this.template.getGroupStyle() != null)
		{
			return this.template.getGroupStyle();
		}
		if(Defaults.getDefaults().getGroupStyle() != null)
		{
			return Defaults.getDefaults().getGroupStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getSubtotalStyle()
	{
		if(this.report.getSubtotalStyle() != null)
		{
			return this.report.getSubtotalStyle();
		}
		if(this.template.getSubtotalStyle() != null)
		{
			return this.template.getSubtotalStyle();
		}
		if(Defaults.getDefaults().getSubtotalStyle() != null)
		{
			return Defaults.getDefaults().getSubtotalStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getImageStyle()
	{
		if(this.report.getImageStyle() != null)
		{
			return this.report.getImageStyle();
		}
		if(this.template.getImageStyle() != null)
		{
			return this.template.getImageStyle();
		}
		return Defaults.getDefaults().getImageStyle();
	}
	
	// page
	
	protected int getPageWidth()
	{
		if(this.isIgnorePageWidth())
		{
			return this.getDynamicPageWidth();
		}
		else
		{
			return this.getStaticPageWidth();
		}
	}
	
	private boolean isIgnorePageWidth()
	{
		if(this.report.getPage().getIgnorePageWidth() != null)
		{
			return this.report.getPage().getIgnorePageWidth();
		}
		if(this.template.getIgnorePageWidth() != null)
		{
			return this.template.getIgnorePageWidth();
		}
		return Defaults.getDefaults().isIgnorePageWidth();
	}
	
	private int getDynamicPageWidth()
	{
		int maxPageWidth = 0;
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getTitleBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getPageHeaderBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getPageFooterBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getColumnHeaderBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getColumnFooterBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getDetailBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getLastPageFooterBand(), maxPageWidth);
		maxPageWidth = this.getMaxBandWidth(this.accessor.getBandTransform().getSummaryBand(), maxPageWidth);
		
		return maxPageWidth + this.getPageMargin().getLeft() + this.getPageMargin().getRight();
	}
	
	private int getMaxBandWidth(final DRIDesignBand band, final int maxWidth)
	{
		if(band == null || band.getList() == null)
		{
			return maxWidth;
		}
		
		final int bandWidth = this.detectWidth(band.getList());
		if(bandWidth > maxWidth)
		{
			return bandWidth;
		}
		return maxWidth;
	}
	
	private int getStaticPageWidth()
	{
		if(this.accessor.getPageWidth() != null)
		{
			return this.accessor.getPageWidth();
		}
		if(this.report.getPage().getWidth() != null)
		{
			return this.report.getPage().getWidth();
		}
		if(this.templateDesign.getPageWidth() != null)
		{
			return this.templateDesign.getPageWidth();
		}
		if(this.template.getPageWidth() != null)
		{
			return this.template.getPageWidth();
		}
		return Defaults.getDefaults().getPageWidth();
	}
	
	protected int getPageHeight()
	{
		if(this.report.getPage().getHeight() != null)
		{
			return this.report.getPage().getHeight();
		}
		if(this.templateDesign.getPageHeight() != null)
		{
			return this.templateDesign.getPageHeight();
		}
		if(this.template.getPageHeight() != null)
		{
			return this.template.getPageHeight();
		}
		return Defaults.getDefaults().getPageHeight();
	}
	
	protected PageOrientation getPageOrientation()
	{
		if(this.report.getPage().getOrientation() != null)
		{
			return this.report.getPage().getOrientation();
		}
		if(this.templateDesign.getPageOrientation() != null)
		{
			return this.templateDesign.getPageOrientation();
		}
		if(this.template.getPageOrientation() != null)
		{
			return this.template.getPageOrientation();
		}
		return Defaults.getDefaults().getPageOrientation();
	}
	
	protected DRIMargin getPageMargin()
	{
		if(this.report.getPage().getMargin() != null)
		{
			return this.report.getPage().getMargin();
		}
		if(this.templateDesign.getPageMargin() != null)
		{
			return this.templateDesign.getPageMargin();
		}
		if(this.accessor.getPageWidth() != null)
		{
			return Defaults.getDefaults().getSubreportPageMargin();
		}
		if(this.template.getPageMargin() != null)
		{
			return this.template.getPageMargin();
		}
		return Defaults.getDefaults().getPageMargin();
	}
	
	protected int getPageColumnsPerPage()
	{
		if(this.report.getPage().getColumnsPerPage() != null)
		{
			return this.report.getPage().getColumnsPerPage();
		}
		if(this.templateDesign.getPageColumnsPerPage() != null)
		{
			return this.templateDesign.getPageColumnsPerPage();
		}
		if(this.template.getPageColumnsPerPage() != null)
		{
			return this.template.getPageColumnsPerPage();
		}
		return Defaults.getDefaults().getPageColumnsPerPage();
	}
	
	protected int getPageColumnSpace()
	{
		if(this.report.getPage().getColumnSpace() != null)
		{
			return this.report.getPage().getColumnSpace();
		}
		if(this.templateDesign.getPageColumnSpace() != null)
		{
			return this.templateDesign.getPageColumnSpace();
		}
		if(this.template.getPageColumnSpace() != null)
		{
			return this.template.getPageColumnSpace();
		}
		return Defaults.getDefaults().getPageColumnSpace();
	}
	
	protected int getPageColumnWidth(final DRIDesignPage page)
	{
		int columnWidth = page.getWidth() - page.getMargin().getLeft() - page.getMargin().getRight();
		columnWidth -= page.getColumnSpace() * (page.getColumnsPerPage() - 1);
		columnWidth = columnWidth / page.getColumnsPerPage();
		if(this.templateDesign.getPageColumnWidth() != null && this.templateDesign.getPageColumnWidth() > 0
			&& this.templateDesign.getPageColumnWidth() < columnWidth && !this.isIgnorePageWidth())
		{
			return this.templateDesign.getPageColumnWidth();
		}
		return columnWidth;
	}
	
	// column
	
	protected boolean isColumnPrintRepeatedDetailValues(final DRIValueColumn<?> column)
	{
		if(column.getPrintRepeatedDetailValues() != null)
		{
			return column.getPrintRepeatedDetailValues();
		}
		if(this.template.getColumnPrintRepeatedDetailValues() != null)
		{
			return this.template.getColumnPrintRepeatedDetailValues();
		}
		return Defaults.getDefaults().isColumnPrintRepeatedDetailValues();
	}
	
	protected int getColumnWidth(final DRIColumn<?> column, final DRDesignStyle style) throws DRException
	{
		final DRIComponent component = this.accessor.getColumnTransform().getColumnComponent(column);
		if(component != null)
		{
			if(component instanceof DRIList)
			{
				final DRDesignList list =
					this.accessor.getComponentTransform().list((DRIList)component, DefaultStyleType.COLUMN, null,
						null);
				return this.detectWidth(list);
			}
			else if(component instanceof DRIDimensionComponent)
			{
				if(((DRIDimensionComponent)component).getWidth() != null)
				{
					return ((DRIDimensionComponent)component).getWidth();
				}
				if(component instanceof DRITextField<?>)
				{
					if(((DRITextField<?>)component).getColumns() != null)
					{
						return StyleResolver.getFontWidth(style, ((DRITextField<?>)component).getColumns());
					}
				}
			}
			else
			{
				throw new DRDesignReportException("Component " + component.getClass().getName() + " not supported");
			}
		}
		return this.getColumnWidth();
	}
	
	protected int getColumnWidth()
	{
		if(this.template.getColumnWidth() != null)
		{
			return this.template.getColumnWidth();
		}
		return Defaults.getDefaults().getColumnWidth();
	}
	
	// component
	
	protected boolean getRemoveLineWhenBlank(final DRIComponent component)
	{
		if(component.getRemoveLineWhenBlank() != null)
		{
			return component.getRemoveLineWhenBlank();
		}
		return Defaults.getDefaults().isRemoveLineWhenBlank();
	}
	
	protected ComponentPositionType getPositionType(final DRIComponent component)
	{
		if(component instanceof DRIDimensionComponent && ((DRIDimensionComponent)component).getPositionType() != null)
		{
			return ((DRIDimensionComponent)component).getPositionType();
		}
		return null;
	}
	
	protected StretchType getStretchType(final DRIComponent component)
	{
		if(component instanceof DRIDimensionComponent && ((DRIDimensionComponent)component).getStretchType() != null)
		{
			return ((DRIDimensionComponent)component).getStretchType();
		}
		return null;
	}
	
	protected boolean getPrintInFirstWholeBand(final DRIComponent component)
	{
		if(component instanceof DRIDimensionComponent
			&& ((DRIDimensionComponent)component).getPrintInFirstWholeBand() != null)
		{
			return ((DRIDimensionComponent)component).getPrintInFirstWholeBand();
		}
		return Defaults.getDefaults().isPrintInFirstWholeBand();
	}
	
	protected boolean getPrintWhenDetailOverflows(final DRIComponent component)
	{
		if(component instanceof DRIDimensionComponent
			&& ((DRIDimensionComponent)component).getPrintWhenDetailOverflows() != null)
		{
			return ((DRIDimensionComponent)component).getPrintWhenDetailOverflows();
		}
		return Defaults.getDefaults().isPrintWhenDetailOverflows();
	}
	
	protected DRIDesignGroup getPrintWhenGroupChanges(final DRIComponent component)
	{
		if(component instanceof DRIDimensionComponent
			&& ((DRIDimensionComponent)component).getPrintWhenGroupChanges() != null)
		{
			return this.accessor.getGroupTransform()
				.getGroup(((DRIDimensionComponent)component).getPrintWhenGroupChanges());
		}
		return null;
	}
	
	// group
	
	protected GroupHeaderLayout getGroupHeaderLayout(final DRIGroup group)
	{
		if(group.getHeaderLayout() != null)
		{
			return group.getHeaderLayout();
		}
		if(this.template.getGroupHeaderLayout() != null)
		{
			return this.template.getGroupHeaderLayout();
		}
		return Defaults.getDefaults().getGroupHeaderLayout();
	}
	
	protected boolean isGroupHideColumn(final DRIGroup group)
	{
		if(group.getHideColumn() != null)
		{
			return group.getHideColumn();
		}
		if(this.template.getGroupHideColumn() != null)
		{
			return this.template.getGroupHideColumn();
		}
		return Defaults.getDefaults().isGroupHideColumn();
	}
	
	protected boolean isGroupShowColumnHeaderAndFooter(final DRIGroup group)
	{
		if(group.getShowColumnHeaderAndFooter() != null)
		{
			return group.getShowColumnHeaderAndFooter();
		}
		if(this.template.getGroupShowColumnHeaderAndFooter() != null)
		{
			return this.template.getGroupShowColumnHeaderAndFooter();
		}
		return Defaults.getDefaults().isGroupShowColumnHeaderAndFooter();
	}
	
	protected int getGroupPadding(final DRIGroup group)
	{
		if(group.getPadding() != null)
		{
			return group.getPadding();
		}
		if(this.template.getGroupPadding() != null)
		{
			return this.template.getGroupPadding();
		}
		return Defaults.getDefaults().getGroupPadding();
	}
	
	protected boolean isGroupStartInNewPage(final DRIGroup group)
	{
		if(group.getStartInNewPage() != null)
		{
			return group.getStartInNewPage();
		}
		if(this.template.getGroupStartInNewPage() != null)
		{
			return this.template.getGroupStartInNewPage();
		}
		return Defaults.getDefaults().isGroupStartInNewPage();
	}
	
	protected boolean isGroupStartInNewColumn(final DRIGroup group)
	{
		if(group.getStartInNewColumn() != null)
		{
			return group.getStartInNewColumn();
		}
		if(this.template.getGroupStartInNewColumn() != null)
		{
			return this.template.getGroupStartInNewColumn();
		}
		return Defaults.getDefaults().isGroupStartInNewColumn();
	}
	
	protected boolean isGroupReprintHeaderOnEachPage(final DRIGroup group)
	{
		if(group.getReprintHeaderOnEachPage() != null)
		{
			return group.getReprintHeaderOnEachPage();
		}
		if(this.template.getGroupReprintHeaderOnEachPage() != null)
		{
			return this.template.getGroupReprintHeaderOnEachPage();
		}
		return Defaults.getDefaults().isGroupReprintHeaderOnEachPage();
	}
	
	protected boolean isGroupResetPageNumber(final DRIGroup group)
	{
		if(group.getResetPageNumber() != null)
		{
			return group.getResetPageNumber();
		}
		if(this.template.getGroupResetPageNumber() != null)
		{
			return this.template.getGroupResetPageNumber();
		}
		return Defaults.getDefaults().isGroupResetPageNumber();
	}
	
	protected Integer getGroupMinHeightToStartNewPage(final DRIGroup group)
	{
		if(group.getMinHeightToStartNewPage() != null)
		{
			return group.getMinHeightToStartNewPage();
		}
		return Defaults.getDefaults().getGroupMinHeightToStartNewPage();
	}
	
	protected GroupFooterPosition getGroupFooterPosition(final DRIGroup group)
	{
		if(group.getFooterPosition() != null)
		{
			return group.getFooterPosition();
		}
		if(this.template.getGroupFooterPosition() != null)
		{
			return this.template.getGroupFooterPosition();
		}
		return Defaults.getDefaults().getGroupFooterPosition();
	}
	
	protected boolean isGroupKeepTogether(final DRIGroup group)
	{
		if(group.getKeepTogether() != null)
		{
			return group.getKeepTogether();
		}
		if(this.template.getGroupKeepTogether() != null)
		{
			return this.template.getGroupKeepTogether();
		}
		return Defaults.getDefaults().isGroupKeepTogether();
	}
	
	protected boolean isGroupHeaderWithSubtotal(final DRIGroup group)
	{
		if(group.getHeaderWithSubtotal() != null)
		{
			return group.getHeaderWithSubtotal();
		}
		if(this.template.getGroupHeaderWithSubtotal() != null)
		{
			return this.template.getGroupHeaderWithSubtotal();
		}
		return Defaults.getDefaults().isGroupHeaderWithSubtotal();
	}
	
	protected boolean isGroupByDataType(final DRIGroup group)
	{
		if(group.getGroupByDataType() != null)
		{
			return group.getGroupByDataType();
		}
		return Defaults.getDefaults().isGroupByDataType();
	}
	
	// subtotal
	
	public Position getSubtotalLabelPosition(final DRISubtotal<?> subtotal)
	{
		if(subtotal.getLabelPosition() != null)
		{
			return subtotal.getLabelPosition();
		}
		if(this.template.getSubtotalLabelPosition() != null)
		{
			return this.template.getSubtotalLabelPosition();
		}
		return Defaults.getDefaults().getSubtotalLabelPosition();
	}
	
	// text field
	
	protected int getTextFieldWidth(final DRITextField<?> textField, final DRDesignStyle style)
	{
		if(textField.getWidth() != null)
		{
			return textField.getWidth();
		}
		if(textField.getColumns() != null)
		{
			return StyleResolver.getFontWidth(style, textField.getColumns());
		}
		if(this.template.getTextFieldWidth() != null)
		{
			return this.template.getTextFieldWidth();
		}
		return Defaults.getDefaults().getTextFieldWidth();
	}
	
	protected int getTextFieldHeight(final DRITextField<?> textField, final DRDesignStyle style)
	{
		if(textField.getHeight() != null)
		{
			return textField.getHeight();
		}
		if(textField.getRows() != null)
		{
			return StyleResolver.getFontHeight(style, textField.getRows());
		}
		return StyleResolver.getFontHeight(style, 1);
	}
	
	protected String getTextFieldPattern(final DRITextField<?> textField, final DRDesignStyle style)
	{
		if(textField.getPattern() != null)
		{
			return textField.getPattern();
		}
		if(StyleResolver.getPattern(style) != null)
		{
			return null; // StyleResolver.getPattern(style);
		}
		if(textField.getDataType() != null)
		{
			return textField.getDataType().getPattern();
		}
		return null;
	}
	
	protected HorizontalTextAlignment getTextFieldHorizontalTextAlignment(
		final DRITextField<?> textField,
		final DRDesignStyle style)
	{
		if(textField.getHorizontalTextAlignment() != null)
		{
			return textField.getHorizontalTextAlignment();
		}
		if(StyleResolver.getHorizontalTextAlignment(style) != null)
		{
			return null; // StyleResolver.getHorizontalTextAlignment(style);
		}
		if(textField.getDataType() != null)
		{
			return textField.getDataType().getHorizontalTextAlignment();
		}
		return null;
	}
	
	protected DRIValueFormatter<?, ?> getTextFieldValueFormatter(final DRITextField<?> textField)
	{
		if(textField.getValueFormatter() != null)
		{
			return textField.getValueFormatter();
		}
		if(textField.getDataType() != null)
		{
			return textField.getDataType().getValueFormatter();
		}
		return null;
	}
	
	protected TextAdjust getTextFieldTextAdjust(final DRITextField<?> textField)
	{
		if(textField.getTextAdjust() != null)
		{
			return textField.getTextAdjust();
		}
		return Defaults.getDefaults().getTextFieldTextAdjust();
	}
	
	protected boolean isTextFieldPrintRepeatedValues(final DRITextField<?> textField)
	{
		if(textField.getPrintRepeatedValues() != null)
		{
			return textField.getPrintRepeatedValues();
		}
		return Defaults.getDefaults().isTextFieldPrintRepeatedValues();
	}
	
	// text field
	
	protected int getBooleanFieldWidth(final DRIBooleanField booleanField, final DRDesignStyle style)
	{
		if(booleanField.getWidth() != null)
		{
			return booleanField.getWidth();
		}
		if(this.template.getTextFieldWidth() != null)
		{
			return this.template.getTextFieldWidth();
		}
		return Defaults.getDefaults().getTextFieldWidth();
	}
	
	protected int getBooleanFieldHeight(final DRIBooleanField booleanField, final DRDesignStyle style)
	{
		if(booleanField.getHeight() != null)
		{
			return booleanField.getHeight();
		}
		return StyleResolver.getFontHeight(style, 1);
	}
	
	// page x of y
	
	protected int getPageXofYWidth(final DRIPageXofY pageXofY)
	{
		if(pageXofY.getWidth() != null)
		{
			return pageXofY.getWidth();
		}
		if(this.template.getTextFieldWidth() != null)
		{
			return this.template.getTextFieldWidth();
		}
		return Defaults.getDefaults().getTextFieldWidth();
	}
	
	protected int getPageXofYHeight(final DRIPageXofY pageXofY, final DRDesignStyle style)
	{
		if(pageXofY.getHeight() != null)
		{
			return pageXofY.getHeight();
		}
		return StyleResolver.getFontHeight(style, 1);
	}
	
	protected HorizontalTextAlignment getPageXofYHorizontalTextAlignment(
		final DRIPageXofY pageXofY,
		final DRDesignStyle style)
	{
		if(pageXofY.getHorizontalTextAlignment() != null)
		{
			return pageXofY.getHorizontalTextAlignment();
		}
		if(StyleResolver.getHorizontalTextAlignment(style) != null)
		{
			return StyleResolver.getHorizontalTextAlignment(style);
		}
		return Defaults.getDefaults().getPageXofYHorizontalTextAlignment();
	}
	
	// image
	
	protected int getImageWidth(final DRIImage image)
	{
		if(image.getWidth() != null)
		{
			return image.getWidth();
		}
		if(this.template.getImageWidth() != null)
		{
			return this.template.getImageWidth();
		}
		return Defaults.getDefaults().getImageWidth();
	}
	
	protected int getImageHeight(final DRIImage image, final Integer imageHeight, final DRDesignStyle style)
	{
		if(image.getHeight() != null)
		{
			return image.getHeight();
		}
		if(imageHeight != null)
		{
			return imageHeight + StyleResolver.getVerticalPadding(style);
		}
		if(this.template.getImageHeight() != null)
		{
			return this.template.getImageHeight();
		}
		return Defaults.getDefaults().getImageHeight();
	}
	
	// filler
	
	protected int getFillerWidth(final DRIFiller filler)
	{
		if(filler.getWidth() != null)
		{
			return filler.getWidth();
		}
		return Defaults.getDefaults().getFillerWidth();
	}
	
	protected int getFillerHeight(final DRIFiller filler)
	{
		if(filler.getHeight() != null)
		{
			return filler.getHeight();
		}
		return Defaults.getDefaults().getFillerHeight();
	}
	
	// line
	
	protected int getLineWidth(final DRILine line)
	{
		if(line.getWidth() != null)
		{
			return line.getWidth();
		}
		return Defaults.getDefaults().getLineWidth();
	}
	
	protected int getLineHeight(final DRILine line)
	{
		if(line.getHeight() != null)
		{
			return line.getHeight();
		}
		return Defaults.getDefaults().getLineHeight();
	}
	
	// ellipse
	
	protected int getEllipseWidth(final DRIEllipse ellipse)
	{
		if(ellipse.getWidth() != null)
		{
			return ellipse.getWidth();
		}
		return Defaults.getDefaults().getEllipseWidth();
	}
	
	protected int getEllipseHeight(final DRIEllipse ellipse)
	{
		if(ellipse.getHeight() != null)
		{
			return ellipse.getHeight();
		}
		return Defaults.getDefaults().getEllipseHeight();
	}
	
	// rectangle
	
	protected Integer getRectangleRadius(final DRIRectangle rectangle)
	{
		if(rectangle.getRadius() != null)
		{
			return rectangle.getRadius();
		}
		return Defaults.getDefaults().getRectangleRadius();
	}
	
	protected int getRectangleWidth(final DRIRectangle rectangle)
	{
		if(rectangle.getWidth() != null)
		{
			return rectangle.getWidth();
		}
		return Defaults.getDefaults().getRectangleWidth();
	}
	
	protected int getRectangleHeight(final DRIRectangle rectangle)
	{
		if(rectangle.getHeight() != null)
		{
			return rectangle.getHeight();
		}
		return Defaults.getDefaults().getRectangleHeight();
	}
	
	// map
	
	protected int getMapWidth(final DRIMap map)
	{
		if(map.getWidth() != null)
		{
			return map.getWidth();
		}
		return Defaults.getDefaults().getMapWidth();
	}
	
	protected int getMapHeight(final DRIMap map)
	{
		if(map.getHeight() != null)
		{
			return map.getHeight();
		}
		return Defaults.getDefaults().getMapHeight();
	}
	
	// custom component
	
	protected int getCustomComponentWidth(final DRIDimensionComponent component)
	{
		if(component.getWidth() != null)
		{
			return component.getWidth();
		}
		return Defaults.getDefaults().getCustomComponentWidth();
	}
	
	protected int getCustomComponentHeight(final DRIDimensionComponent component)
	{
		if(component.getHeight() != null)
		{
			return component.getHeight();
		}
		return Defaults.getDefaults().getCustomComponentHeight();
	}
	
	// break
	
	protected int getBreakWidth(final DRIBreak breakComponent)
	{
		return Defaults.getDefaults().getBreakWidth();
	}
	
	protected int getBreakHeight(final DRIBreak breakComponent)
	{
		return Defaults.getDefaults().getBreakHeight();
	}
	
	// generic element
	
	protected Integer getGenericElementWidth(final DRIGenericElement genericElement)
	{
		if(genericElement.getWidth() != null)
		{
			return genericElement.getWidth();
		}
		return Defaults.getDefaults().getGenericElementWidth();
	}
	
	protected Integer getGenericElementHeight(final DRIGenericElement genericElement)
	{
		if(genericElement.getHeight() != null)
		{
			return genericElement.getHeight();
		}
		return Defaults.getDefaults().getGenericElementHeight();
	}
	
	// list
	
	protected Integer getListWidth(final DRIList list)
	{
		if(list.getWidth() != null)
		{
			return list.getWidth();
		}
		return Defaults.getDefaults().getListWidth();
	}
	
	protected Integer getListHeight(final DRIList list)
	{
		if(list.getHeight() != null)
		{
			return list.getHeight();
		}
		return Defaults.getDefaults().getListHeight();
	}
	
	protected int getListGap(final DRIList list)
	{
		if(list.getGap() != null)
		{
			return list.getGap();
		}
		if(this.template.getListgap() != null)
		{
			return this.template.getListgap();
		}
		return Defaults.getDefaults().getListgap();
	}
	
	// xy list
	
	protected Integer getXyListWidth(final DRIXyList xyList)
	{
		if(xyList.getWidth() != null)
		{
			return xyList.getWidth();
		}
		return Defaults.getDefaults().getListWidth();
	}
	
	protected Integer getXyListHeight(final DRIXyList xyList)
	{
		if(xyList.getHeight() != null)
		{
			return xyList.getHeight();
		}
		return Defaults.getDefaults().getListHeight();
	}
	
	// multi page list
	
	protected int getMultiPageListWidth(final DRIMultiPageList multiPageList)
	{
		if(multiPageList.getWidth() != null)
		{
			return multiPageList.getWidth();
		}
		if(this.template.getMultiPageListWidth() != null)
		{
			return this.template.getMultiPageListWidth();
		}
		return Defaults.getDefaults().getMultiPageListWidth();
	}
	
	protected int getMultiPageListHeight(final DRIMultiPageList multiPageList)
	{
		if(multiPageList.getHeight() != null)
		{
			return multiPageList.getHeight();
		}
		if(this.template.getMultiPageListHeight() != null)
		{
			return this.template.getMultiPageListHeight();
		}
		return Defaults.getDefaults().getMultiPageListHeight();
	}
	
	// subreport
	
	protected int getSubreportWidth(final DRISubreport subreport)
	{
		if(subreport.getWidth() != null)
		{
			return subreport.getWidth();
		}
		if(this.template.getSubreportWidth() != null)
		{
			return this.template.getSubreportWidth();
		}
		return Defaults.getDefaults().getSubreportWidth();
	}
	
	protected int getSubreportHeight(final DRISubreport subreport)
	{
		if(subreport.getHeight() != null)
		{
			return subreport.getHeight();
		}
		if(this.template.getSubreportHeight() != null)
		{
			return this.template.getSubreportHeight();
		}
		return Defaults.getDefaults().getSubreportHeight();
	}
	
	// crosstab
	
	protected int getCrosstabWidth(final DRICrosstab crosstab)
	{
		if(crosstab.getWidth() != null)
		{
			return crosstab.getWidth();
		}
		if(this.template.getCrosstabWidth() != null)
		{
			return this.template.getCrosstabWidth();
		}
		return Defaults.getDefaults().getCrosstabWidth();
	}
	
	protected int getCrosstabHeight(final DRICrosstab crosstab, final DRDesignCrosstabCellContent whenNoDataCell)
	{
		final int height;
		if(crosstab.getHeight() != null)
		{
			height = crosstab.getHeight();
		}
		else if(this.template.getCrosstabHeight() != null)
		{
			height = this.template.getCrosstabHeight();
		}
		else
		{
			height = Defaults.getDefaults().getCrosstabHeight();
		}
		final int whenNoDataCellHeight = this.getCrosstabWhenNoDataCellHeight(whenNoDataCell);
		if(height == 0 && whenNoDataCellHeight > 0)
		{
			return whenNoDataCellHeight;
		}
		return height;
	}
	
	public CrosstabTotalPosition getCrosstabColumnGroupTotalPosition(final DRICrosstabColumnGroup<?> columnGroup)
	{
		if(!this.isCrosstabColumnGroupShowTotal(columnGroup))
		{
			return null;
		}
		if(columnGroup.getTotalPosition() != null)
		{
			return columnGroup.getTotalPosition();
		}
		return Defaults.getDefaults().getCrosstabColumnGroupTotalPosition();
	}
	
	public CrosstabTotalPosition getCrosstabRowGroupTotalPosition(final DRICrosstabRowGroup<?> rowGroup)
	{
		if(!this.isCrosstabRowGroupShowTotal(rowGroup))
		{
			return null;
		}
		if(rowGroup.getTotalPosition() != null)
		{
			return rowGroup.getTotalPosition();
		}
		return Defaults.getDefaults().getCrosstabRowGroupTotalPosition();
	}
	
	public boolean isCrosstabColumnGroupShowTotal(final DRICrosstabColumnGroup<?> columnGroup)
	{
		if(columnGroup.getShowTotal() != null)
		{
			return columnGroup.getShowTotal();
		}
		return Defaults.getDefaults().isCrosstabColumnGroupShowTotal();
	}
	
	public boolean isCrosstabRowGroupShowTotal(final DRICrosstabRowGroup<?> rowGroup)
	{
		if(rowGroup.getShowTotal() != null)
		{
			return rowGroup.getShowTotal();
		}
		return Defaults.getDefaults().isCrosstabRowGroupShowTotal();
	}
	
	public int getCrosstabColumnGroupHeaderHeight(
		final DRICrosstabColumnGroup<?> columnGroup,
		final DRDesignCrosstab designCrosstab,
		final int availableHeight)
	{
		if(columnGroup.getHeaderHeight() != null)
		{
			return columnGroup.getHeaderHeight();
		}
		int maxHeight = 0;
		for(final DRDesignCrosstabColumnGroup designColumnGroup : designCrosstab.getColumnGroups())
		{
			if(designColumnGroup.getName().equals(columnGroup.getName()))
			{
				int height = this.detectHeight(designColumnGroup.getHeader().getList());
				if(maxHeight < height)
				{
					maxHeight = height;
				}
				if(designColumnGroup.getTotalHeader() != null)
				{
					height = this.detectHeight(designColumnGroup.getTotalHeader().getList());
					if(maxHeight < height && height > availableHeight)
					{
						maxHeight = height;
					}
				}
				break;
			}
		}
		return maxHeight;
	}
	
	public int getCrosstabColumnGroupTotalHeaderWidth(
		final DRICrosstabColumnGroup<?> columnGroup,
		final Integer cellWidth,
		final DRDesignCrosstab designCrosstab)
	{
		if(columnGroup.getTotalHeaderWidth() != null)
		{
			return columnGroup.getTotalHeaderWidth();
		}
		if(cellWidth != null)
		{
			return cellWidth;
		}
		int maxWidth = 0;
		for(final DRDesignCrosstabColumnGroup designColumnGroup : designCrosstab.getColumnGroups())
		{
			if(designColumnGroup.getName().equals(columnGroup.getName()))
			{
				if(designColumnGroup.getTotalHeader() != null)
				{
					final int height = this.detectWidth(designColumnGroup.getTotalHeader().getList());
					if(maxWidth < height)
					{
						maxWidth = height;
					}
				}
				break;
			}
		}
		for(final DRDesignCrosstabCell designCell : designCrosstab.getCells())
		{
			if(designCell.getColumnTotalGroup() == columnGroup.getName())
			{
				final int height = this.detectWidth(designCell.getContent().getList());
				if(maxWidth < height)
				{
					maxWidth = height;
				}
			}
		}
		if(maxWidth > Defaults.getDefaults().getCrosstabColumnGroupTotalHeaderMaxWidth())
		{
			return Defaults.getDefaults().getCrosstabColumnGroupTotalHeaderMaxWidth();
		}
		return maxWidth;
	}
	
	public int getCrosstabRowGroupHeaderWidth(
		final DRICrosstabRowGroup<?> rowGroup,
		final DRDesignCrosstab designCrosstab)
	{
		if(rowGroup.getHeaderWidth() != null)
		{
			return rowGroup.getHeaderWidth();
		}
		int maxWidth = 0;
		for(final DRDesignCrosstabRowGroup designRowGroup : designCrosstab.getRowGroups())
		{
			if(designRowGroup.getName().equals(rowGroup.getName()))
			{
				int width = this.detectWidth(designRowGroup.getHeader().getList());
				if(maxWidth < width)
				{
					maxWidth = width;
				}
				if(designRowGroup.getTotalHeader() != null)
				{
					width = this.detectWidth(designRowGroup.getTotalHeader().getList());
					if(maxWidth < width)
					{
						maxWidth = width;
					}
				}
				break;
			}
		}
		if(maxWidth > Defaults.getDefaults().getCrosstabRowGroupHeaderMaxWidth())
		{
			return Defaults.getDefaults().getCrosstabRowGroupHeaderMaxWidth();
		}
		return maxWidth;
	}
	
	public int getCrosstabRowGroupTotalHeaderHeight(
		final DRICrosstabRowGroup<?> rowGroup,
		final Integer cellHeight,
		final DRDesignCrosstab designCrosstab)
	{
		if(rowGroup.getTotalHeaderHeight() != null)
		{
			return rowGroup.getTotalHeaderHeight();
		}
		if(cellHeight != null)
		{
			return cellHeight;
		}
		int maxHeight = 0;
		for(final DRDesignCrosstabRowGroup designRowGroup : designCrosstab.getRowGroups())
		{
			if(designRowGroup.getName().equals(rowGroup.getName()))
			{
				if(designRowGroup.getTotalHeader() != null)
				{
					final int height = this.detectHeight(designRowGroup.getTotalHeader().getList());
					if(maxHeight < height)
					{
						maxHeight = height;
					}
				}
				break;
			}
		}
		for(final DRDesignCrosstabCell designCell : designCrosstab.getCells())
		{
			if(designCell.getRowTotalGroup() == rowGroup.getName())
			{
				final int height = this.detectHeight(designCell.getContent().getList());
				if(maxHeight < height)
				{
					maxHeight = height;
				}
			}
		}
		return maxHeight;
	}
	
	public int getCrosstabCellWidth(final DRICrosstab crosstab, final DRDesignCrosstab designCrosstab)
	{
		if(crosstab.getCellWidth() != null)
		{
			return crosstab.getCellWidth();
		}
		int maxWidth = 0;
		for(final DRDesignCrosstabCell designCell : designCrosstab.getCells())
		{
			if(designCell.getColumnTotalGroup() == null)
			{
				final int width = this.detectWidth(designCell.getContent().getList());
				if(maxWidth < width)
				{
					maxWidth = width;
				}
			}
		}
		for(final DRDesignCrosstabColumnGroup designColumnGroup : designCrosstab.getColumnGroups())
		{
			final int width = this.detectWidth(designColumnGroup.getHeader().getList());
			if(maxWidth < width)
			{
				maxWidth = width;
			}
		}
		if(maxWidth > Defaults.getDefaults().getCrosstabCellMaxWidth())
		{
			return Defaults.getDefaults().getCrosstabCellMaxWidth();
		}
		return maxWidth;
	}
	
	public int getCrosstabCellHeight(final DRICrosstab crosstab, final DRDesignCrosstab designCrosstab)
	{
		if(crosstab.getCellHeight() != null)
		{
			return crosstab.getCellHeight();
		}
		int maxHeight = 0;
		for(final DRDesignCrosstabCell designCell : designCrosstab.getCells())
		{
			if(designCell.getRowTotalGroup() == null)
			{
				final int height = this.detectHeight(designCell.getContent().getList());
				if(maxHeight < height)
				{
					maxHeight = height;
				}
			}
		}
		for(final DRDesignCrosstabRowGroup designRowGroup : designCrosstab.getRowGroups())
		{
			final int height = this.detectHeight(designRowGroup.getHeader().getList());
			if(maxHeight < height)
			{
				maxHeight = height;
			}
		}
		return maxHeight;
	}
	
	public int getCrosstabWhenNoDataCellHeight(final DRDesignCrosstabCellContent whenNoDataCell)
	{
		return this.detectHeight(whenNoDataCell.getList());
	}
	
	protected DRISimpleStyle getCrosstabOddRowStyle(final DRICrosstab crosstab)
	{
		if(this.isCrosstabHighlightOddRows(crosstab))
		{
			if(crosstab.getOddRowStyle() != null)
			{
				return crosstab.getOddRowStyle();
			}
			if(this.template.getCrosstabOddRowStyle() != null)
			{
				return this.template.getCrosstabOddRowStyle();
			}
			return Defaults.getDefaults().getCrosstabOddRowStyle();
		}
		return null;
	}
	
	protected DRISimpleStyle getCrosstabEvenRowStyle(final DRICrosstab crosstab)
	{
		if(this.isCrosstabHighlightEvenRows(crosstab))
		{
			if(crosstab.getEvenRowStyle() != null)
			{
				return crosstab.getEvenRowStyle();
			}
			if(this.template.getCrosstabEvenRowStyle() != null)
			{
				return this.template.getCrosstabEvenRowStyle();
			}
			return Defaults.getDefaults().getCrosstabEvenRowStyle();
		}
		return null;
	}
	
	private boolean isCrosstabHighlightOddRows(final DRICrosstab crosstab)
	{
		if(crosstab.getHighlightOddRows() != null)
		{
			return crosstab.getHighlightOddRows();
		}
		if(this.template.getCrosstabHighlightOddRows() != null)
		{
			return this.template.getCrosstabHighlightOddRows();
		}
		return Defaults.getDefaults().isCrosstabHighlightOddRows();
	}
	
	private boolean isCrosstabHighlightEvenRows(final DRICrosstab crosstab)
	{
		if(crosstab.getHighlightEvenRows() != null)
		{
			return crosstab.getHighlightEvenRows();
		}
		if(this.template.getCrosstabHighlightEvenRows() != null)
		{
			return this.template.getCrosstabHighlightEvenRows();
		}
		return Defaults.getDefaults().isCrosstabHighlightEvenRows();
	}
	
	protected DRIReportStyle getCrosstabGroupStyle(final DRICrosstab crosstab)
	{
		if(crosstab.getGroupStyle() != null)
		{
			return crosstab.getGroupStyle();
		}
		if(this.template.getCrosstabGroupStyle() != null)
		{
			return this.template.getCrosstabGroupStyle();
		}
		if(Defaults.getDefaults().getCrosstabGroupStyle() != null)
		{
			return Defaults.getDefaults().getCrosstabGroupStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getCrosstabGroupTotalStyle(final DRICrosstab crosstab)
	{
		if(crosstab.getGroupTotalStyle() != null)
		{
			return crosstab.getGroupTotalStyle();
		}
		if(this.template.getCrosstabGroupTotalStyle() != null)
		{
			return this.template.getCrosstabGroupTotalStyle();
		}
		if(Defaults.getDefaults().getCrosstabGroupTotalStyle() != null)
		{
			return Defaults.getDefaults().getCrosstabGroupTotalStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getCrosstabGrandTotalStyle(final DRICrosstab crosstab)
	{
		if(crosstab.getGrandTotalStyle() != null)
		{
			return crosstab.getGrandTotalStyle();
		}
		if(this.template.getCrosstabGrandTotalStyle() != null)
		{
			return this.template.getCrosstabGrandTotalStyle();
		}
		if(Defaults.getDefaults().getCrosstabGrandTotalStyle() != null)
		{
			return Defaults.getDefaults().getCrosstabGrandTotalStyle();
		}
		return this.getTextStyle();
	}
	
	protected DRIReportStyle getCrosstabCellStyle(final DRICrosstab crosstab)
	{
		if(crosstab.getCellStyle() != null)
		{
			return crosstab.getCellStyle();
		}
		if(this.template.getCrosstabCellStyle() != null)
		{
			return this.template.getCrosstabCellStyle();
		}
		if(Defaults.getDefaults().getCrosstabCellStyle() != null)
		{
			return Defaults.getDefaults().getCrosstabCellStyle();
		}
		return this.getTextStyle();
	}
	
	public DRIReportStyle getCrosstabMeasureTitleStyle(final DRICrosstab crosstab, final DRICrosstabMeasure<?> measure)
	{
		if(measure.getTitleStyle() != null)
		{
			return measure.getTitleStyle();
		}
		if(crosstab.getMeasureTitleStyle() != null)
		{
			return crosstab.getMeasureTitleStyle();
		}
		if(this.template.getCrosstabMeasureTitleStyle() != null)
		{
			return this.template.getCrosstabMeasureTitleStyle();
		}
		return Defaults.getDefaults().getCrosstabMeasureTitleStyle();
	}
	
	protected CrosstabPercentageType getCrosstabPercentageType(final DRICrosstabVariable<?> variable)
	{
		if(variable.getPercentageType() != null)
		{
			return variable.getPercentageType();
		}
		return Defaults.getDefaults().getCrosstabPercentageType();
	}
	
	public BooleanComponentType getBooleanComponentType(final DRIBooleanField booleanField)
	{
		if(booleanField.getComponentType() != null)
		{
			return booleanField.getComponentType();
		}
		if(this.template.getBooleanComponentType() != null)
		{
			return this.template.getBooleanComponentType();
		}
		return Defaults.getDefaults().getBooleanComponentType();
	}
	
	public boolean getBooleanEmptyWhenNullValue(final DRIBooleanField booleanField)
	{
		if(booleanField.getEmptyWhenNullValue() != null)
		{
			return booleanField.getEmptyWhenNullValue();
		}
		if(this.template.getBooleanEmptyWhenNullValue() != null)
		{
			return this.template.getBooleanEmptyWhenNullValue();
		}
		return Defaults.getDefaults().isBooleanEmptyWhenNullValue();
	}
	
	public int getBooleanImageWidth(final DRIBooleanField booleanField)
	{
		if(booleanField.getImageWidth() != null)
		{
			return booleanField.getImageWidth();
		}
		if(this.template.getBooleanImageWidth() != null)
		{
			return this.template.getBooleanImageWidth();
		}
		return Defaults.getDefaults().getBooleanImageWidth();
	}
	
	public int getBooleanImageHeight(final DRIBooleanField booleanField)
	{
		if(booleanField.getImageHeight() != null)
		{
			return booleanField.getImageHeight();
		}
		if(this.template.getBooleanImageHeight() != null)
		{
			return this.template.getBooleanImageHeight();
		}
		return Defaults.getDefaults().getBooleanImageHeight();
	}
	
	protected HorizontalImageAlignment getBooleanHorizontalImageAlignment(
		final DRIBooleanField booleanField,
		final DRDesignStyle style)
	{
		if(booleanField.getHorizontalImageAlignment() != null)
		{
			return booleanField.getHorizontalImageAlignment();
		}
		if(StyleResolver.getHorizontalImageAlignment(style) != null)
		{
			return null; // StyleResolver.getHorizontalAlignment(style);
		}
		return Defaults.getDefaults().getBooleanHorizontalImageAlignment();
	}
	
	public DRIReportStyle getBooleanColumnStyle(final DRIBooleanColumn column)
	{
		if(column.getComponent().getStyle() != null)
		{
			return column.getComponent().getStyle();
		}
		if(this.template.getBooleanColumnStyle() != null)
		{
			return this.template.getBooleanColumnStyle();
		}
		return Defaults.getDefaults().getBooleanColumnStyle();
	}
	
	public Map<String, DRIStyle> getTemplateStyles()
	{
		final Map<String, DRIStyle> templateStyles = new HashMap<>();
		for(final DRIStyle style : this.template.getTemplateStyles())
		{
			templateStyles.put(style.getName(), style);
		}
		for(final DRIStyle style : this.report.getTemplateStyles())
		{
			templateStyles.put(style.getName(), style);
		}
		return templateStyles;
	}
	
	// split
	
	protected SplitType getTitleSplitType(final DRIBand band)
	{
		return this.getSplitType(band, this.template.getTitleSplitType(), Defaults.getDefaults().getTitleSplitType());
	}
	
	protected SplitType getPageHeaderSplitType(final DRIBand band)
	{
		return this.getSplitType(band,
			this.template.getPageHeaderSplitType(), Defaults.getDefaults().getPageHeaderSplitType());
	}
	
	protected SplitType getPageFooterSplitType(final DRIBand band)
	{
		return this.getSplitType(band,
			this.template.getPageFooterSplitType(), Defaults.getDefaults().getPageFooterSplitType());
	}
	
	protected SplitType getColumnHeaderSplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getColumnHeaderSplitType(),
			Defaults.getDefaults().getColumnHeaderSplitType());
	}
	
	protected SplitType getColumnFooterSplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getColumnFooterSplitType(),
			Defaults.getDefaults().getColumnFooterSplitType());
	}
	
	protected SplitType getGroupHeaderSplitType(final DRIBand band)
	{
		return this.getSplitType(band, this.template.getGroupHeaderSplitType(),
			Defaults.getDefaults().getGroupHeaderSplitType());
	}
	
	protected SplitType getGroupFooterSplitType(final DRIBand band)
	{
		return this.getSplitType(band, this.template.getGroupFooterSplitType(),
			Defaults.getDefaults().getGroupFooterSplitType());
	}
	
	protected SplitType getDetailHeaderSplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getDetailHeaderSplitType(),
			Defaults.getDefaults().getDetailHeaderSplitType());
	}
	
	protected SplitType getDetailSplitType(final DRIBand band)
	{
		return this.getSplitType(band, this.template.getDetailSplitType(),
			Defaults.getDefaults().getDetailSplitType());
	}
	
	protected SplitType getDetailFooterSplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getDetailFooterSplitType(),
			Defaults.getDefaults().getDetailFooterSplitType());
	}
	
	protected SplitType getLastPageFooterSplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getLastPageFooterSplitType(),
			Defaults.getDefaults().getLastPageFooterSplitType());
	}
	
	protected SplitType getSummarySplitType(final DRIBand band)
	{
		return this.getSplitType(
			band,
			this.template.getSummarySplitType(),
			Defaults.getDefaults().getSummarySplitType());
	}
	
	protected SplitType getNoDataSplitType(final DRIBand band)
	{
		return this.getSplitType(band, this.template.getNoDataSplitType(),
			Defaults.getDefaults().getNoDataSplitType());
	}
	
	protected SplitType getBackgroundSplitType(final DRIBand band)
	{
		return this.getSplitType(band,
			this.template.getBackgroundSplitType(), Defaults.getDefaults().getBackgroundSplitType());
	}
	
	private SplitType getSplitType(final DRIBand band, final SplitType templateSplitType, final SplitType splitType)
	{
		if(band.getSplitType() != null)
		{
			return band.getSplitType();
		}
		if(templateSplitType != null)
		{
			return templateSplitType;
		}
		if(splitType != null)
		{
			return splitType;
		}
		if(this.template.getDefaultSplitType() != null)
		{
			return this.template.getDefaultSplitType();
		}
		return Defaults.getDefaults().getDefaultSplitType();
	}
	
	// band style
	
	protected DRIReportStyle getTitleStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getTitleStyle());
	}
	
	protected DRIReportStyle getPageHeaderStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getPageHeaderStyle());
	}
	
	protected DRIReportStyle getPageFooterStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getPageFooterStyle());
	}
	
	protected DRIReportStyle getColumnHeaderStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getColumnHeaderStyle());
	}
	
	protected DRIReportStyle getColumnFooterStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getColumnFooterStyle());
	}
	
	protected DRIReportStyle getGroupHeaderStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getGroupHeaderStyle());
	}
	
	protected DRIReportStyle getGroupFooterStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getGroupFooterStyle());
	}
	
	protected DRIReportStyle getDetailHeaderStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getDetailHeaderStyle());
	}
	
	protected DRIReportStyle getDetailStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getDetailStyle());
	}
	
	protected DRIReportStyle getDetailFooterStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getDetailFooterStyle());
	}
	
	protected DRIReportStyle getLastPageFooterStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getLastPageFooterStyle());
	}
	
	protected DRIReportStyle getSummaryStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getSummaryStyle());
	}
	
	protected DRIReportStyle getNoDataStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getNoDataStyle());
	}
	
	protected DRIReportStyle getBackgroundStyle(final DRIBand band)
	{
		return this.getBandStyle(band, this.template.getBackgroundStyle());
	}
	
	private DRIReportStyle getBandStyle(final DRIBand band, final DRIReportStyle templateStyle)
	{
		if(band.getList().getStyle() != null)
		{
			return band.getList().getStyle();
		}
		if(templateStyle != null)
		{
			return templateStyle;
		}
		return Defaults.getDefaults().getBandStyle();
	}
	
	// band background component
	
	protected DRIComponent getTitleBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getTitleBackgroundComponent());
	}
	
	protected DRIComponent getPageHeaderBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getPageHeaderBackgroundComponent());
	}
	
	protected DRIComponent getPageFooterBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getPageFooterBackgroundComponent());
	}
	
	protected DRIComponent getColumnHeaderBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getColumnHeaderBackgroundComponent());
	}
	
	protected DRIComponent getColumnFooterBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getColumnFooterBackgroundComponent());
	}
	
	protected DRIComponent getGroupHeaderBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getGroupHeaderBackgroundComponent());
	}
	
	protected DRIComponent getGroupFooterBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getGroupFooterBackgroundComponent());
	}
	
	protected DRIComponent getDetailHeaderBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getDetailHeaderBackgroundComponent());
	}
	
	protected DRIComponent getDetailBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getDetailBackgroundComponent());
	}
	
	protected DRIComponent getDetailFooterBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getDetailFooterBackgroundComponent());
	}
	
	protected DRIComponent getLastPageFooterBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getLastPageFooterBackgroundComponent());
	}
	
	protected DRIComponent getSummaryBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getSummaryBackgroundComponent());
	}
	
	protected DRIComponent getNoDataBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getNoDataBackgroundComponent());
	}
	
	protected DRIComponent getBackgroundBackgroundComponent(final DRIBand band)
	{
		return this.getBandBackgroundComponent(band, this.template.getBackgroundBackgroundComponent());
	}
	
	private DRIComponent getBandBackgroundComponent(final DRIBand band, final DRIComponent templateBackgroundComponent)
	{
		if(band.getList().getBackgroundComponent() != null)
		{
			return band.getList().getBackgroundComponent();
		}
		if(templateBackgroundComponent != null)
		{
			return templateBackgroundComponent;
		}
		return Defaults.getDefaults().getBandBackgroundComponent();
	}
	
	private int detectWidth(final DRDesignList designList)
	{
		ComponentPosition.width(designList);
		return designList.getWidth();
	}
	
	private int detectHeight(final DRDesignList designList)
	{
		ComponentPosition.height(designList);
		return designList.getHeight();
	}
}
