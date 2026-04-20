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
package software.xdev.dynamicreports.report.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.style.DRSimpleStyle;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIReportTemplate;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.style.DRIFont;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;


public class DRReportTemplate implements DRIReportTemplate
{
	private Boolean tableOfContents;
	private DRITableOfContentsCustomizer tableOfContentsCustomizer;
	private List<DRIStyle> templateStyles;
	private Locale locale;
	private Boolean showColumnTitle;
	private Boolean showColumnValues;
	private Boolean ignorePagination;
	private WhenNoDataType whenNoDataType;
	private WhenResourceMissingType whenResourceMissingType;
	private Boolean titleOnANewPage;
	private Boolean summaryOnANewPage;
	private Boolean summaryWithPageHeaderAndFooter;
	private Boolean floatColumnFooter;
	private Orientation printOrder;
	private RunDirection columnDirection;
	private String language;
	private Boolean useFieldNameAsDescription;
	// style
	private Boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private Boolean highlightDetailEvenRows;
	private DRSimpleStyle detailEvenRowStyle;
	private DRIFont defaultFont;
	private DRIReportStyle textStyle;
	private DRIReportStyle columnTitleStyle;
	private DRIReportStyle columnStyle;
	private DRIReportStyle groupTitleStyle;
	private DRIReportStyle groupStyle;
	private DRIReportStyle subtotalStyle;
	private DRIReportStyle imageStyle;
	// page
	private Integer pageWidth;
	private Integer pageHeight;
	private PageOrientation pageOrientation;
	private DRMargin pageMargin;
	private Integer pageColumnsPerPage;
	private Integer pageColumnSpace;
	private Boolean ignorePageWidth;
	// column
	private Boolean columnPrintRepeatedDetailValues;
	private Integer columnWidth;
	// group
	private GroupHeaderLayout groupHeaderLayout;
	private Boolean groupHideColumn;
	private Boolean groupShowColumnHeaderAndFooter;
	private Integer groupPadding;
	private Boolean groupStartInNewPage;
	private Boolean groupStartInNewColumn;
	private Boolean groupReprintHeaderOnEachPage;
	private Boolean groupResetPageNumber;
	private GroupFooterPosition groupFooterPosition;
	private Boolean groupKeepTogether;
	private Boolean groupHeaderWithSubtotal;
	// subtotal
	private Position subtotalLabelPosition;
	// text field
	private Integer textFieldWidth;
	// image
	private Integer imageWidth;
	private Integer imageHeight;
	// list
	private Integer listgap;
	// multi page list
	private Integer multiPageListWidth;
	private Integer multiPageListHeight;
	// subreport
	private Integer subreportWidth;
	private Integer subreportHeight;
	// crosstab
	private Integer crosstabWidth;
	private Integer crosstabHeight;
	private Boolean crosstabHighlightOddRows;
	private DRSimpleStyle crosstabOddRowStyle;
	private Boolean crosstabHighlightEvenRows;
	private DRSimpleStyle crosstabEvenRowStyle;
	private DRIReportStyle crosstabGroupStyle;
	private DRIReportStyle crosstabGroupTotalStyle;
	private DRIReportStyle crosstabGrandTotalStyle;
	private DRIReportStyle crosstabCellStyle;
	private DRIReportStyle crosstabMeasureTitleStyle;
	// boolean
	private BooleanComponentType booleanComponentType;
	private Boolean booleanEmptyWhenNullValue;
	private Integer booleanImageWidth;
	private Integer booleanImageHeight;
	private DRIReportStyle booleanColumnStyle;
	// split
	private SplitType defaultSplitType;
	private SplitType titleSplitType;
	private SplitType pageHeaderSplitType;
	private SplitType pageFooterSplitType;
	private SplitType columnHeaderSplitType;
	private SplitType columnFooterSplitType;
	private SplitType groupHeaderSplitType;
	private SplitType groupFooterSplitType;
	private SplitType detailHeaderSplitType;
	private SplitType detailSplitType;
	private SplitType detailFooterSplitType;
	private SplitType lastPageFooterSplitType;
	private SplitType summarySplitType;
	private SplitType noDataSplitType;
	private SplitType backgroundSplitType;
	// band style
	private DRIReportStyle titleStyle;
	private DRIReportStyle pageHeaderStyle;
	private DRIReportStyle pageFooterStyle;
	private DRIReportStyle columnHeaderStyle;
	private DRIReportStyle columnFooterStyle;
	private DRIReportStyle groupHeaderStyle;
	private DRIReportStyle groupFooterStyle;
	private DRIReportStyle detailHeaderStyle;
	private DRIReportStyle detailStyle;
	private DRIReportStyle detailFooterStyle;
	private DRIReportStyle lastPageFooterStyle;
	private DRIReportStyle summaryStyle;
	private DRIReportStyle noDataStyle;
	private DRIReportStyle backgroundStyle;
	// band background component
	private DRComponent titleBackgroundComponent;
	private DRComponent pageHeaderBackgroundComponent;
	private DRComponent pageFooterBackgroundComponent;
	private DRComponent columnHeaderBackgroundComponent;
	private DRComponent columnFooterBackgroundComponent;
	private DRComponent groupHeaderBackgroundComponent;
	private DRComponent groupFooterBackgroundComponent;
	private DRComponent detailHeaderBackgroundComponent;
	private DRComponent detailBackgroundComponent;
	private DRComponent detailFooterBackgroundComponent;
	private DRComponent lastPageFooterBackgroundComponent;
	private DRComponent summaryBackgroundComponent;
	private DRComponent noDataBackgroundComponent;
	private DRComponent backgroundBackgroundComponent;
	
	public DRReportTemplate()
	{
		this.templateStyles = new ArrayList<>();
	}
	
	@Override
	public List<DRIStyle> getTemplateStyles()
	{
		return this.templateStyles;
	}
	
	public void setTemplateStyles(final List<DRIStyle> templateStyles)
	{
		this.templateStyles = templateStyles;
	}
	
	public void addTemplateStyle(final DRIStyle templateStyle)
	{
		Validate.notNull(templateStyle, "templateStyle must not be null");
		Validate.notNull(templateStyle.getName(), "templateStyle name must not be null");
		this.templateStyles.add(templateStyle);
	}
	
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}
	
	public void setLocale(final Locale locale)
	{
		this.locale = locale;
	}
	
	@Override
	public Boolean getShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	public void setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.showColumnTitle = showColumnTitle;
	}
	
	@Override
	public Boolean getShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	public void setShowColumnValues(final Boolean showColumnValues)
	{
		this.showColumnValues = showColumnValues;
	}
	
	@Override
	public Boolean getIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	public void setIgnorePagination(final Boolean ignorePagination)
	{
		this.ignorePagination = ignorePagination;
	}
	
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	public void setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.whenNoDataType = whenNoDataType;
	}
	
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	public void setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.whenResourceMissingType = whenResourceMissingType;
	}
	
	@Override
	public Boolean getTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	public void setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.titleOnANewPage = titleOnANewPage;
	}
	
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	public void setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.summaryOnANewPage = summaryOnANewPage;
	}
	
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	public void setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}
	
	@Override
	public Boolean getFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	public void setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.floatColumnFooter = floatColumnFooter;
	}
	
	@Override
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	public void setPrintOrder(final Orientation printOrder)
	{
		this.printOrder = printOrder;
	}
	
	@Override
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	public void setColumnDirection(final RunDirection columnDirection)
	{
		this.columnDirection = columnDirection;
	}
	
	@Override
	public String getLanguage()
	{
		return this.language;
	}
	
	public void setLanguage(final String language)
	{
		this.language = language;
	}
	
	@Override
	public Boolean getUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	public void setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}
	
	@Override
	public Boolean getHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	public void setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.highlightDetailOddRows = highlightDetailOddRows;
	}
	
	@Override
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	public void setDetailOddRowStyle(final DRSimpleStyle detailOddRowStyle)
	{
		this.detailOddRowStyle = detailOddRowStyle;
	}
	
	@Override
	public Boolean getHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	public void setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}
	
	@Override
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	public void setDetailEvenRowStyle(final DRSimpleStyle detailEvenRowStyle)
	{
		this.detailEvenRowStyle = detailEvenRowStyle;
	}
	
	@Override
	public DRIFont getDefaultFont()
	{
		return this.defaultFont;
	}
	
	public void setDefaultFont(final DRIFont defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	@Override
	public DRIReportStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	public void setTextStyle(final DRIReportStyle textStyle)
	{
		this.textStyle = textStyle;
	}
	
	@Override
	public DRIReportStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	public void setColumnTitleStyle(final DRIReportStyle columnTitleStyle)
	{
		this.columnTitleStyle = columnTitleStyle;
	}
	
	@Override
	public DRIReportStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	public void setColumnStyle(final DRIReportStyle columnStyle)
	{
		this.columnStyle = columnStyle;
	}
	
	@Override
	public DRIReportStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	public void setGroupTitleStyle(final DRIReportStyle groupTitleStyle)
	{
		this.groupTitleStyle = groupTitleStyle;
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
	public DRIReportStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	public void setSubtotalStyle(final DRIReportStyle subtotalStyle)
	{
		this.subtotalStyle = subtotalStyle;
	}
	
	@Override
	public DRIReportStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	public void setImageStyle(final DRIReportStyle imageStyle)
	{
		this.imageStyle = imageStyle;
	}
	
	public void setPageFormat(final PageType pageType, final PageOrientation orientation)
	{
		Validate.notNull(pageType, "pageType must not be null");
		Validate.notNull(orientation, "orientation must not be null");
		if(orientation.equals(PageOrientation.PORTRAIT))
		{
			this.setPageWidth(pageType.getWidth());
			this.setPageHeight(pageType.getHeight());
		}
		else
		{
			this.setPageWidth(pageType.getHeight());
			this.setPageHeight(pageType.getWidth());
		}
		this.setPageOrientation(orientation);
	}
	
	@Override
	public Integer getPageWidth()
	{
		return this.pageWidth;
	}
	
	private void setPageWidth(final Integer pageWidth)
	{
		if(pageWidth != null)
		{
			Validate.isTrue(pageWidth >= 0, "pageWidth must be >= 0");
		}
		this.pageWidth = pageWidth;
	}
	
	@Override
	public Integer getPageHeight()
	{
		return this.pageHeight;
	}
	
	private void setPageHeight(final Integer pageHeight)
	{
		if(pageHeight != null)
		{
			Validate.isTrue(pageHeight >= 0, "pageHeight must be >= 0");
		}
		this.pageHeight = pageHeight;
	}
	
	@Override
	public PageOrientation getPageOrientation()
	{
		return this.pageOrientation;
	}
	
	private void setPageOrientation(final PageOrientation pageOrientation)
	{
		this.pageOrientation = pageOrientation;
	}
	
	@Override
	public DRMargin getPageMargin()
	{
		return this.pageMargin;
	}
	
	public void setPageMargin(final DRMargin pageMargin)
	{
		this.pageMargin = pageMargin;
	}
	
	@Override
	public Integer getPageColumnsPerPage()
	{
		return this.pageColumnsPerPage;
	}
	
	public void setPageColumnsPerPage(final Integer pageColumnsPerPage)
	{
		if(pageColumnsPerPage != null)
		{
			Validate.isTrue(pageColumnsPerPage >= 1, "pageColumnsPerPage must be >= 1");
		}
		this.pageColumnsPerPage = pageColumnsPerPage;
	}
	
	@Override
	public Integer getPageColumnSpace()
	{
		return this.pageColumnSpace;
	}
	
	public void setPageColumnSpace(final Integer pageColumnSpace)
	{
		if(pageColumnSpace != null)
		{
			Validate.isTrue(pageColumnSpace >= 0, "pageColumnSpace must be >= 0");
		}
		this.pageColumnSpace = pageColumnSpace;
	}
	
	@Override
	public Boolean getIgnorePageWidth()
	{
		return this.ignorePageWidth;
	}
	
	public void setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.ignorePageWidth = ignorePageWidth;
	}
	
	@Override
	public Boolean getColumnPrintRepeatedDetailValues()
	{
		return this.columnPrintRepeatedDetailValues;
	}
	
	public void setColumnPrintRepeatedDetailValues(final Boolean columnPrintRepeatedDetailValues)
	{
		this.columnPrintRepeatedDetailValues = columnPrintRepeatedDetailValues;
	}
	
	@Override
	public Integer getColumnWidth()
	{
		return this.columnWidth;
	}
	
	public void setColumnWidth(final Integer columnWidth)
	{
		if(columnWidth != null)
		{
			Validate.isTrue(columnWidth >= 0, "columnWidth must be >= 0");
		}
		this.columnWidth = columnWidth;
	}
	
	@Override
	public GroupHeaderLayout getGroupHeaderLayout()
	{
		return this.groupHeaderLayout;
	}
	
	public void setGroupHeaderLayout(final GroupHeaderLayout groupHeaderLayout)
	{
		this.groupHeaderLayout = groupHeaderLayout;
	}
	
	@Override
	public Boolean getGroupHideColumn()
	{
		return this.groupHideColumn;
	}
	
	public void setGroupHideColumn(final Boolean groupHideColumn)
	{
		this.groupHideColumn = groupHideColumn;
	}
	
	@Override
	public Boolean getGroupShowColumnHeaderAndFooter()
	{
		return this.groupShowColumnHeaderAndFooter;
	}
	
	public void setGroupShowColumnHeaderAndFooter(final Boolean groupShowColumnHeaderAndFooter)
	{
		this.groupShowColumnHeaderAndFooter = groupShowColumnHeaderAndFooter;
	}
	
	@Override
	public Integer getGroupPadding()
	{
		return this.groupPadding;
	}
	
	public void setGroupPadding(final Integer groupPadding)
	{
		if(groupPadding != null)
		{
			Validate.isTrue(groupPadding >= 0, "groupPadding must be >= 0");
		}
		this.groupPadding = groupPadding;
	}
	
	@Override
	public Boolean getGroupStartInNewPage()
	{
		return this.groupStartInNewPage;
	}
	
	public void setGroupStartInNewPage(final Boolean groupStartInNewPage)
	{
		this.groupStartInNewPage = groupStartInNewPage;
	}
	
	@Override
	public Boolean getGroupStartInNewColumn()
	{
		return this.groupStartInNewColumn;
	}
	
	public void setGroupStartInNewColumn(final Boolean groupStartInNewColumn)
	{
		this.groupStartInNewColumn = groupStartInNewColumn;
	}
	
	@Override
	public Boolean getGroupReprintHeaderOnEachPage()
	{
		return this.groupReprintHeaderOnEachPage;
	}
	
	public void setGroupReprintHeaderOnEachPage(final Boolean groupReprintHeaderOnEachPage)
	{
		this.groupReprintHeaderOnEachPage = groupReprintHeaderOnEachPage;
	}
	
	@Override
	public Boolean getGroupResetPageNumber()
	{
		return this.groupResetPageNumber;
	}
	
	public void setGroupResetPageNumber(final Boolean groupResetPageNumber)
	{
		this.groupResetPageNumber = groupResetPageNumber;
	}
	
	@Override
	public GroupFooterPosition getGroupFooterPosition()
	{
		return this.groupFooterPosition;
	}
	
	public void setGroupFooterPosition(final GroupFooterPosition groupFooterPosition)
	{
		this.groupFooterPosition = groupFooterPosition;
	}
	
	@Override
	public Boolean getGroupKeepTogether()
	{
		return this.groupKeepTogether;
	}
	
	public void setGroupKeepTogether(final Boolean groupKeepTogether)
	{
		this.groupKeepTogether = groupKeepTogether;
	}
	
	@Override
	public Boolean getGroupHeaderWithSubtotal()
	{
		return this.groupHeaderWithSubtotal;
	}
	
	public void setGroupHeaderWithSubtotal(final Boolean groupHeaderWithSubtotal)
	{
		this.groupHeaderWithSubtotal = groupHeaderWithSubtotal;
	}
	
	@Override
	public Position getSubtotalLabelPosition()
	{
		return this.subtotalLabelPosition;
	}
	
	public void setSubtotalLabelPosition(final Position subtotalLabelPosition)
	{
		this.subtotalLabelPosition = subtotalLabelPosition;
	}
	
	@Override
	public Boolean getTableOfContents()
	{
		return this.tableOfContents;
	}
	
	public void setTableOfContents(final Boolean tableOfContents)
	{
		this.tableOfContents = tableOfContents;
	}
	
	@Override
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	public void setTableOfContentsCustomizer(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
	}
	
	@Override
	public Integer getTextFieldWidth()
	{
		return this.textFieldWidth;
	}
	
	public void setTextFieldWidth(final Integer textFieldWidth)
	{
		if(textFieldWidth != null)
		{
			Validate.isTrue(textFieldWidth >= 0, "textFieldWidth must be >= 0");
		}
		this.textFieldWidth = textFieldWidth;
	}
	
	@Override
	public Integer getImageHeight()
	{
		return this.imageHeight;
	}
	
	public void setImageHeight(final Integer imageHeight)
	{
		if(imageHeight != null)
		{
			Validate.isTrue(imageHeight >= 0, "imageHeight must be >= 0");
		}
		this.imageHeight = imageHeight;
	}
	
	@Override
	public Integer getImageWidth()
	{
		return this.imageWidth;
	}
	
	public void setImageWidth(final Integer imageWidth)
	{
		if(imageWidth != null)
		{
			Validate.isTrue(imageWidth >= 0, "imageWidth must be >= 0");
		}
		this.imageWidth = imageWidth;
	}
	
	@Override
	public Integer getListgap()
	{
		return this.listgap;
	}
	
	public void setListgap(final Integer listgap)
	{
		if(listgap != null)
		{
			Validate.isTrue(listgap >= 0, "listgap must be >= 0");
		}
		this.listgap = listgap;
	}
	
	@Override
	public Integer getMultiPageListWidth()
	{
		return this.multiPageListWidth;
	}
	
	public void setMultiPageListWidth(final Integer multiPageListWidth)
	{
		this.multiPageListWidth = multiPageListWidth;
	}
	
	@Override
	public Integer getMultiPageListHeight()
	{
		return this.multiPageListHeight;
	}
	
	public void setMultiPageListHeight(final Integer multiPageListHeight)
	{
		this.multiPageListHeight = multiPageListHeight;
	}
	
	@Override
	public Integer getSubreportHeight()
	{
		return this.subreportHeight;
	}
	
	public void setSubreportHeight(final Integer subreportHeight)
	{
		if(subreportHeight != null)
		{
			Validate.isTrue(subreportHeight >= 0, "subreportHeight must be >= 0");
		}
		this.subreportHeight = subreportHeight;
	}
	
	@Override
	public Integer getSubreportWidth()
	{
		return this.subreportWidth;
	}
	
	public void setSubreportWidth(final Integer subreportWidth)
	{
		if(subreportWidth != null)
		{
			Validate.isTrue(subreportWidth >= 0, "subreportWidth must be >= 0");
		}
		this.subreportWidth = subreportWidth;
	}
	
	@Override
	public Integer getCrosstabHeight()
	{
		return this.crosstabHeight;
	}
	
	public void setCrosstabHeight(final Integer crosstabHeight)
	{
		if(crosstabHeight != null)
		{
			Validate.isTrue(crosstabHeight >= 0, "crosstabHeight must be >= 0");
		}
		this.crosstabHeight = crosstabHeight;
	}
	
	@Override
	public Integer getCrosstabWidth()
	{
		return this.crosstabWidth;
	}
	
	public void setCrosstabWidth(final Integer crosstabWidth)
	{
		if(crosstabWidth != null)
		{
			Validate.isTrue(crosstabWidth >= 0, "crosstabWidth must be >= 0");
		}
		this.crosstabWidth = crosstabWidth;
	}
	
	@Override
	public Boolean getCrosstabHighlightOddRows()
	{
		return this.crosstabHighlightOddRows;
	}
	
	public void setCrosstabHighlightOddRows(final Boolean crosstabHighlightOddRows)
	{
		this.crosstabHighlightOddRows = crosstabHighlightOddRows;
	}
	
	@Override
	public DRSimpleStyle getCrosstabOddRowStyle()
	{
		return this.crosstabOddRowStyle;
	}
	
	public void setCrosstabOddRowStyle(final DRSimpleStyle crosstabOddRowStyle)
	{
		this.crosstabOddRowStyle = crosstabOddRowStyle;
	}
	
	@Override
	public Boolean getCrosstabHighlightEvenRows()
	{
		return this.crosstabHighlightEvenRows;
	}
	
	public void setCrosstabHighlightEvenRows(final Boolean crosstabHighlightEvenRows)
	{
		this.crosstabHighlightEvenRows = crosstabHighlightEvenRows;
	}
	
	@Override
	public DRSimpleStyle getCrosstabEvenRowStyle()
	{
		return this.crosstabEvenRowStyle;
	}
	
	public void setCrosstabEvenRowStyle(final DRSimpleStyle crosstabEvenRowStyle)
	{
		this.crosstabEvenRowStyle = crosstabEvenRowStyle;
	}
	
	@Override
	public DRIReportStyle getCrosstabGroupStyle()
	{
		return this.crosstabGroupStyle;
	}
	
	public void setCrosstabGroupStyle(final DRIReportStyle crosstabGroupStyle)
	{
		this.crosstabGroupStyle = crosstabGroupStyle;
	}
	
	@Override
	public DRIReportStyle getCrosstabGroupTotalStyle()
	{
		return this.crosstabGroupTotalStyle;
	}
	
	public void setCrosstabGroupTotalStyle(final DRIReportStyle crosstabGroupTotalStyle)
	{
		this.crosstabGroupTotalStyle = crosstabGroupTotalStyle;
	}
	
	@Override
	public DRIReportStyle getCrosstabGrandTotalStyle()
	{
		return this.crosstabGrandTotalStyle;
	}
	
	public void setCrosstabGrandTotalStyle(final DRIReportStyle crosstabGrandTotalStyle)
	{
		this.crosstabGrandTotalStyle = crosstabGrandTotalStyle;
	}
	
	@Override
	public DRIReportStyle getCrosstabCellStyle()
	{
		return this.crosstabCellStyle;
	}
	
	public void setCrosstabCellStyle(final DRIReportStyle crosstabCellStyle)
	{
		this.crosstabCellStyle = crosstabCellStyle;
	}
	
	@Override
	public DRIReportStyle getCrosstabMeasureTitleStyle()
	{
		return this.crosstabMeasureTitleStyle;
	}
	
	public void setCrosstabMeasureTitleStyle(final DRIReportStyle crosstabMeasureTitleStyle)
	{
		this.crosstabMeasureTitleStyle = crosstabMeasureTitleStyle;
	}
	
	@Override
	public BooleanComponentType getBooleanComponentType()
	{
		return this.booleanComponentType;
	}
	
	public void setBooleanComponentType(final BooleanComponentType booleanComponentType)
	{
		this.booleanComponentType = booleanComponentType;
	}
	
	@Override
	public Boolean getBooleanEmptyWhenNullValue()
	{
		return this.booleanEmptyWhenNullValue;
	}
	
	public void setBooleanEmptyWhenNullValue(final Boolean booleanEmptyWhenNullValue)
	{
		this.booleanEmptyWhenNullValue = booleanEmptyWhenNullValue;
	}
	
	@Override
	public Integer getBooleanImageWidth()
	{
		return this.booleanImageWidth;
	}
	
	public void setBooleanImageWidth(final Integer booleanImageWidth)
	{
		this.booleanImageWidth = booleanImageWidth;
	}
	
	@Override
	public Integer getBooleanImageHeight()
	{
		return this.booleanImageHeight;
	}
	
	public void setBooleanImageHeight(final Integer booleanImageHeight)
	{
		this.booleanImageHeight = booleanImageHeight;
	}
	
	@Override
	public DRIReportStyle getBooleanColumnStyle()
	{
		return this.booleanColumnStyle;
	}
	
	public void setBooleanColumnStyle(final DRIReportStyle booleanColumnStyle)
	{
		this.booleanColumnStyle = booleanColumnStyle;
	}
	
	@Override
	public SplitType getDefaultSplitType()
	{
		return this.defaultSplitType;
	}
	
	public void setDefaultSplitType(final SplitType defaultSplitType)
	{
		this.defaultSplitType = defaultSplitType;
	}
	
	@Override
	public SplitType getTitleSplitType()
	{
		return this.titleSplitType;
	}
	
	public void setTitleSplitType(final SplitType titleSplitType)
	{
		this.titleSplitType = titleSplitType;
	}
	
	@Override
	public SplitType getPageHeaderSplitType()
	{
		return this.pageHeaderSplitType;
	}
	
	public void setPageHeaderSplitType(final SplitType pageHeaderSplitType)
	{
		this.pageHeaderSplitType = pageHeaderSplitType;
	}
	
	@Override
	public SplitType getPageFooterSplitType()
	{
		return this.pageFooterSplitType;
	}
	
	public void setPageFooterSplitType(final SplitType pageFooterSplitType)
	{
		this.pageFooterSplitType = pageFooterSplitType;
	}
	
	@Override
	public SplitType getColumnHeaderSplitType()
	{
		return this.columnHeaderSplitType;
	}
	
	public void setColumnHeaderSplitType(final SplitType columnHeaderSplitType)
	{
		this.columnHeaderSplitType = columnHeaderSplitType;
	}
	
	@Override
	public SplitType getColumnFooterSplitType()
	{
		return this.columnFooterSplitType;
	}
	
	public void setColumnFooterSplitType(final SplitType columnFooterSplitType)
	{
		this.columnFooterSplitType = columnFooterSplitType;
	}
	
	@Override
	public SplitType getGroupHeaderSplitType()
	{
		return this.groupHeaderSplitType;
	}
	
	public void setGroupHeaderSplitType(final SplitType groupHeaderSplitType)
	{
		this.groupHeaderSplitType = groupHeaderSplitType;
	}
	
	@Override
	public SplitType getGroupFooterSplitType()
	{
		return this.groupFooterSplitType;
	}
	
	public void setGroupFooterSplitType(final SplitType groupFooterSplitType)
	{
		this.groupFooterSplitType = groupFooterSplitType;
	}
	
	@Override
	public SplitType getDetailHeaderSplitType()
	{
		return this.detailHeaderSplitType;
	}
	
	public void setDetailHeaderSplitType(final SplitType detailHeaderSplitType)
	{
		this.detailHeaderSplitType = detailHeaderSplitType;
	}
	
	@Override
	public SplitType getDetailSplitType()
	{
		return this.detailSplitType;
	}
	
	public void setDetailSplitType(final SplitType detailSplitType)
	{
		this.detailSplitType = detailSplitType;
	}
	
	@Override
	public SplitType getDetailFooterSplitType()
	{
		return this.detailFooterSplitType;
	}
	
	public void setDetailFooterSplitType(final SplitType detailFooterSplitType)
	{
		this.detailFooterSplitType = detailFooterSplitType;
	}
	
	@Override
	public SplitType getLastPageFooterSplitType()
	{
		return this.lastPageFooterSplitType;
	}
	
	public void setLastPageFooterSplitType(final SplitType lastPageFooterSplitType)
	{
		this.lastPageFooterSplitType = lastPageFooterSplitType;
	}
	
	@Override
	public SplitType getSummarySplitType()
	{
		return this.summarySplitType;
	}
	
	public void setSummarySplitType(final SplitType summarySplitType)
	{
		this.summarySplitType = summarySplitType;
	}
	
	@Override
	public SplitType getNoDataSplitType()
	{
		return this.noDataSplitType;
	}
	
	public void setNoDataSplitType(final SplitType noDataSplitType)
	{
		this.noDataSplitType = noDataSplitType;
	}
	
	@Override
	public SplitType getBackgroundSplitType()
	{
		return this.backgroundSplitType;
	}
	
	public void setBackgroundSplitType(final SplitType backgroundSplitType)
	{
		this.backgroundSplitType = backgroundSplitType;
	}
	
	@Override
	public DRIReportStyle getTitleStyle()
	{
		return this.titleStyle;
	}
	
	public void setTitleStyle(final DRIReportStyle titleStyle)
	{
		this.titleStyle = titleStyle;
	}
	
	@Override
	public DRIReportStyle getPageHeaderStyle()
	{
		return this.pageHeaderStyle;
	}
	
	public void setPageHeaderStyle(final DRIReportStyle pageHeaderStyle)
	{
		this.pageHeaderStyle = pageHeaderStyle;
	}
	
	@Override
	public DRIReportStyle getPageFooterStyle()
	{
		return this.pageFooterStyle;
	}
	
	public void setPageFooterStyle(final DRIReportStyle pageFooterStyle)
	{
		this.pageFooterStyle = pageFooterStyle;
	}
	
	@Override
	public DRIReportStyle getColumnHeaderStyle()
	{
		return this.columnHeaderStyle;
	}
	
	public void setColumnHeaderStyle(final DRIReportStyle columnHeaderStyle)
	{
		this.columnHeaderStyle = columnHeaderStyle;
	}
	
	@Override
	public DRIReportStyle getColumnFooterStyle()
	{
		return this.columnFooterStyle;
	}
	
	public void setColumnFooterStyle(final DRIReportStyle columnFooterStyle)
	{
		this.columnFooterStyle = columnFooterStyle;
	}
	
	@Override
	public DRIReportStyle getGroupHeaderStyle()
	{
		return this.groupHeaderStyle;
	}
	
	public void setGroupHeaderStyle(final DRIReportStyle groupHeaderStyle)
	{
		this.groupHeaderStyle = groupHeaderStyle;
	}
	
	@Override
	public DRIReportStyle getGroupFooterStyle()
	{
		return this.groupFooterStyle;
	}
	
	public void setGroupFooterStyle(final DRIReportStyle groupFooterStyle)
	{
		this.groupFooterStyle = groupFooterStyle;
	}
	
	@Override
	public DRIReportStyle getDetailHeaderStyle()
	{
		return this.detailHeaderStyle;
	}
	
	public void setDetailHeaderStyle(final DRIReportStyle detailHeaderStyle)
	{
		this.detailHeaderStyle = detailHeaderStyle;
	}
	
	@Override
	public DRIReportStyle getDetailStyle()
	{
		return this.detailStyle;
	}
	
	public void setDetailStyle(final DRIReportStyle detailStyle)
	{
		this.detailStyle = detailStyle;
	}
	
	@Override
	public DRIReportStyle getDetailFooterStyle()
	{
		return this.detailFooterStyle;
	}
	
	public void setDetailFooterStyle(final DRIReportStyle detailFooterStyle)
	{
		this.detailFooterStyle = detailFooterStyle;
	}
	
	@Override
	public DRIReportStyle getLastPageFooterStyle()
	{
		return this.lastPageFooterStyle;
	}
	
	public void setLastPageFooterStyle(final DRIReportStyle lastPageFooterStyle)
	{
		this.lastPageFooterStyle = lastPageFooterStyle;
	}
	
	@Override
	public DRIReportStyle getSummaryStyle()
	{
		return this.summaryStyle;
	}
	
	public void setSummaryStyle(final DRIReportStyle summaryStyle)
	{
		this.summaryStyle = summaryStyle;
	}
	
	@Override
	public DRIReportStyle getNoDataStyle()
	{
		return this.noDataStyle;
	}
	
	public void setNoDataStyle(final DRIReportStyle noDataStyle)
	{
		this.noDataStyle = noDataStyle;
	}
	
	@Override
	public DRIReportStyle getBackgroundStyle()
	{
		return this.backgroundStyle;
	}
	
	public void setBackgroundStyle(final DRIReportStyle backgroundStyle)
	{
		this.backgroundStyle = backgroundStyle;
	}
	
	@Override
	public DRComponent getTitleBackgroundComponent()
	{
		return this.titleBackgroundComponent;
	}
	
	public void setTitleBackgroundComponent(final DRComponent titleBackgroundComponent)
	{
		this.titleBackgroundComponent = titleBackgroundComponent;
	}
	
	@Override
	public DRComponent getPageHeaderBackgroundComponent()
	{
		return this.pageHeaderBackgroundComponent;
	}
	
	public void setPageHeaderBackgroundComponent(final DRComponent pageHeaderBackgroundComponent)
	{
		this.pageHeaderBackgroundComponent = pageHeaderBackgroundComponent;
	}
	
	@Override
	public DRComponent getPageFooterBackgroundComponent()
	{
		return this.pageFooterBackgroundComponent;
	}
	
	public void setPageFooterBackgroundComponent(final DRComponent pageFooterBackgroundComponent)
	{
		this.pageFooterBackgroundComponent = pageFooterBackgroundComponent;
	}
	
	@Override
	public DRComponent getColumnHeaderBackgroundComponent()
	{
		return this.columnHeaderBackgroundComponent;
	}
	
	public void setColumnHeaderBackgroundComponent(final DRComponent columnHeaderBackgroundComponent)
	{
		this.columnHeaderBackgroundComponent = columnHeaderBackgroundComponent;
	}
	
	@Override
	public DRComponent getColumnFooterBackgroundComponent()
	{
		return this.columnFooterBackgroundComponent;
	}
	
	public void setColumnFooterBackgroundComponent(final DRComponent columnFooterBackgroundComponent)
	{
		this.columnFooterBackgroundComponent = columnFooterBackgroundComponent;
	}
	
	@Override
	public DRComponent getGroupHeaderBackgroundComponent()
	{
		return this.groupHeaderBackgroundComponent;
	}
	
	public void setGroupHeaderBackgroundComponent(final DRComponent groupHeaderBackgroundComponent)
	{
		this.groupHeaderBackgroundComponent = groupHeaderBackgroundComponent;
	}
	
	@Override
	public DRComponent getGroupFooterBackgroundComponent()
	{
		return this.groupFooterBackgroundComponent;
	}
	
	public void setGroupFooterBackgroundComponent(final DRComponent groupFooterBackgroundComponent)
	{
		this.groupFooterBackgroundComponent = groupFooterBackgroundComponent;
	}
	
	@Override
	public DRComponent getDetailHeaderBackgroundComponent()
	{
		return this.detailHeaderBackgroundComponent;
	}
	
	public void setDetailHeaderBackgroundComponent(final DRComponent detailHeaderBackgroundComponent)
	{
		this.detailHeaderBackgroundComponent = detailHeaderBackgroundComponent;
	}
	
	@Override
	public DRComponent getDetailBackgroundComponent()
	{
		return this.detailBackgroundComponent;
	}
	
	public void setDetailBackgroundComponent(final DRComponent detailBackgroundComponent)
	{
		this.detailBackgroundComponent = detailBackgroundComponent;
	}
	
	@Override
	public DRComponent getDetailFooterBackgroundComponent()
	{
		return this.detailFooterBackgroundComponent;
	}
	
	public void setDetailFooterBackgroundComponent(final DRComponent detailFooterBackgroundComponent)
	{
		this.detailFooterBackgroundComponent = detailFooterBackgroundComponent;
	}
	
	@Override
	public DRComponent getLastPageFooterBackgroundComponent()
	{
		return this.lastPageFooterBackgroundComponent;
	}
	
	public void setLastPageFooterBackgroundComponent(final DRComponent lastPageFooterBackgroundComponent)
	{
		this.lastPageFooterBackgroundComponent = lastPageFooterBackgroundComponent;
	}
	
	@Override
	public DRComponent getSummaryBackgroundComponent()
	{
		return this.summaryBackgroundComponent;
	}
	
	public void setSummaryBackgroundComponent(final DRComponent summaryBackgroundComponent)
	{
		this.summaryBackgroundComponent = summaryBackgroundComponent;
	}
	
	@Override
	public DRComponent getNoDataBackgroundComponent()
	{
		return this.noDataBackgroundComponent;
	}
	
	public void setNoDataBackgroundComponent(final DRComponent noDataBackgroundComponent)
	{
		this.noDataBackgroundComponent = noDataBackgroundComponent;
	}
	
	@Override
	public DRComponent getBackgroundBackgroundComponent()
	{
		return this.backgroundBackgroundComponent;
	}
	
	public void setBackgroundBackgroundComponent(final DRComponent backgroundBackgroundComponent)
	{
		this.backgroundBackgroundComponent = backgroundBackgroundComponent;
	}
}
