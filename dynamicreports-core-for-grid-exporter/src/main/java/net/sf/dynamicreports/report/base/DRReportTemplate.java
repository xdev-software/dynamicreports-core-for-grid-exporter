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
package net.sf.dynamicreports.report.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.Validate;

import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.dynamicreports.report.definition.DRIReportTemplate;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.style.DRIFont;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;


/**
 * <p>DRReportTemplate class.</p>
 *
 * @author Ricardo Mariaca
 */
public class DRReportTemplate implements DRIReportTemplate
{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	// table of contents
	public Boolean tableOfContents;
	public DRITableOfContentsCustomizer tableOfContentsCustomizer;
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
	
	/**
	 * <p>Constructor for DRReportTemplate.</p>
	 */
	public DRReportTemplate()
	{
		this.templateStyles = new ArrayList<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRIStyle> getTemplateStyles()
	{
		return this.templateStyles;
	}
	
	/**
	 * <p>Setter for the field <code>templateStyles</code>.</p>
	 *
	 * @param templateStyles a {@link java.util.List} object.
	 */
	public void setTemplateStyles(final List<DRIStyle> templateStyles)
	{
		this.templateStyles = templateStyles;
	}
	
	/**
	 * <p>addTemplateStyle.</p>
	 *
	 * @param templateStyle a {@link net.sf.dynamicreports.report.definition.style.DRIStyle} object.
	 */
	public void addTemplateStyle(final DRIStyle templateStyle)
	{
		Validate.notNull(templateStyle, "templateStyle must not be null");
		Validate.notNull(templateStyle.getName(), "templateStyle name must not be null");
		this.templateStyles.add(templateStyle);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}
	
	/**
	 * <p>Setter for the field <code>locale</code>.</p>
	 *
	 * @param locale a {@link java.util.Locale} object.
	 */
	public void setLocale(final Locale locale)
	{
		this.locale = locale;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	/**
	 * <p>Setter for the field <code>showColumnTitle</code>.</p>
	 *
	 * @param showColumnTitle a {@link java.lang.Boolean} object.
	 */
	public void setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.showColumnTitle = showColumnTitle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	/**
	 * <p>Setter for the field <code>showColumnValues</code>.</p>
	 *
	 * @param showColumnValues a {@link java.lang.Boolean} object.
	 */
	public void setShowColumnValues(final Boolean showColumnValues)
	{
		this.showColumnValues = showColumnValues;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	/**
	 * <p>Setter for the field <code>ignorePagination</code>.</p>
	 *
	 * @param ignorePagination a {@link java.lang.Boolean} object.
	 */
	public void setIgnorePagination(final Boolean ignorePagination)
	{
		this.ignorePagination = ignorePagination;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	/**
	 * <p>Setter for the field <code>whenNoDataType</code>.</p>
	 *
	 * @param whenNoDataType a {@link net.sf.dynamicreports.report.constant.WhenNoDataType} object.
	 */
	public void setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.whenNoDataType = whenNoDataType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	/**
	 * <p>Setter for the field <code>whenResourceMissingType</code>.</p>
	 *
	 * @param whenResourceMissingType a {@link net.sf.dynamicreports.report.constant.WhenResourceMissingType} object.
	 */
	public void setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.whenResourceMissingType = whenResourceMissingType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	/**
	 * <p>Setter for the field <code>titleOnANewPage</code>.</p>
	 *
	 * @param titleOnANewPage a {@link java.lang.Boolean} object.
	 */
	public void setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.titleOnANewPage = titleOnANewPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	/**
	 * <p>Setter for the field <code>summaryOnANewPage</code>.</p>
	 *
	 * @param summaryOnANewPage a {@link java.lang.Boolean} object.
	 */
	public void setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.summaryOnANewPage = summaryOnANewPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	/**
	 * <p>Setter for the field <code>summaryWithPageHeaderAndFooter</code>.</p>
	 *
	 * @param summaryWithPageHeaderAndFooter a {@link java.lang.Boolean} object.
	 */
	public void setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	/**
	 * <p>Setter for the field <code>floatColumnFooter</code>.</p>
	 *
	 * @param floatColumnFooter a {@link java.lang.Boolean} object.
	 */
	public void setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.floatColumnFooter = floatColumnFooter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	/**
	 * <p>Setter for the field <code>printOrder</code>.</p>
	 *
	 * @param printOrder a {@link net.sf.dynamicreports.report.constant.Orientation} object.
	 */
	public void setPrintOrder(final Orientation printOrder)
	{
		this.printOrder = printOrder;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	/**
	 * <p>Setter for the field <code>columnDirection</code>.</p>
	 *
	 * @param columnDirection a {@link net.sf.dynamicreports.report.constant.RunDirection} object.
	 */
	public void setColumnDirection(final RunDirection columnDirection)
	{
		this.columnDirection = columnDirection;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLanguage()
	{
		return this.language;
	}
	
	/**
	 * <p>Setter for the field <code>language</code>.</p>
	 *
	 * @param language a {@link java.lang.String} object.
	 */
	public void setLanguage(final String language)
	{
		this.language = language;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	/**
	 * <p>Setter for the field <code>useFieldNameAsDescription</code>.</p>
	 *
	 * @param useFieldNameAsDescription a {@link java.lang.Boolean} object.
	 */
	public void setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	/**
	 * <p>Setter for the field <code>highlightDetailOddRows</code>.</p>
	 *
	 * @param highlightDetailOddRows a {@link java.lang.Boolean} object.
	 */
	public void setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.highlightDetailOddRows = highlightDetailOddRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailOddRowStyle</code>.</p>
	 *
	 * @param detailOddRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setDetailOddRowStyle(final DRSimpleStyle detailOddRowStyle)
	{
		this.detailOddRowStyle = detailOddRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	/**
	 * <p>Setter for the field <code>highlightDetailEvenRows</code>.</p>
	 *
	 * @param highlightDetailEvenRows a {@link java.lang.Boolean} object.
	 */
	public void setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailEvenRowStyle</code>.</p>
	 *
	 * @param detailEvenRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setDetailEvenRowStyle(final DRSimpleStyle detailEvenRowStyle)
	{
		this.detailEvenRowStyle = detailEvenRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIFont getDefaultFont()
	{
		return this.defaultFont;
	}
	
	/**
	 * <p>Setter for the field <code>defaultFont</code>.</p>
	 *
	 * @param defaultFont a {@link net.sf.dynamicreports.report.definition.style.DRIFont} object.
	 */
	public void setDefaultFont(final DRIFont defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	/**
	 * <p>Setter for the field <code>textStyle</code>.</p>
	 *
	 * @param textStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setTextStyle(final DRIReportStyle textStyle)
	{
		this.textStyle = textStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnTitleStyle</code>.</p>
	 *
	 * @param columnTitleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnTitleStyle(final DRIReportStyle columnTitleStyle)
	{
		this.columnTitleStyle = columnTitleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnStyle</code>.</p>
	 *
	 * @param columnStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnStyle(final DRIReportStyle columnStyle)
	{
		this.columnStyle = columnStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupTitleStyle</code>.</p>
	 *
	 * @param groupTitleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupTitleStyle(final DRIReportStyle groupTitleStyle)
	{
		this.groupTitleStyle = groupTitleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupStyle</code>.</p>
	 *
	 * @param groupStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupStyle(final DRIReportStyle groupStyle)
	{
		this.groupStyle = groupStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	/**
	 * <p>Setter for the field <code>subtotalStyle</code>.</p>
	 *
	 * @param subtotalStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setSubtotalStyle(final DRIReportStyle subtotalStyle)
	{
		this.subtotalStyle = subtotalStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	/**
	 * <p>Setter for the field <code>imageStyle</code>.</p>
	 *
	 * @param imageStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setImageStyle(final DRIReportStyle imageStyle)
	{
		this.imageStyle = imageStyle;
	}
	
	/**
	 * <p>setPageFormat.</p>
	 *
	 * @param pageType    a {@link net.sf.dynamicreports.report.constant.PageType} object.
	 * @param orientation a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PageOrientation getPageOrientation()
	{
		return this.pageOrientation;
	}
	
	private void setPageOrientation(final PageOrientation pageOrientation)
	{
		this.pageOrientation = pageOrientation;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRMargin getPageMargin()
	{
		return this.pageMargin;
	}
	
	/**
	 * <p>Setter for the field <code>pageMargin</code>.</p>
	 *
	 * @param pageMargin a {@link net.sf.dynamicreports.report.base.DRMargin} object.
	 */
	public void setPageMargin(final DRMargin pageMargin)
	{
		this.pageMargin = pageMargin;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getPageColumnsPerPage()
	{
		return this.pageColumnsPerPage;
	}
	
	/**
	 * <p>Setter for the field <code>pageColumnsPerPage</code>.</p>
	 *
	 * @param pageColumnsPerPage a {@link java.lang.Integer} object.
	 */
	public void setPageColumnsPerPage(final Integer pageColumnsPerPage)
	{
		if(pageColumnsPerPage != null)
		{
			Validate.isTrue(pageColumnsPerPage >= 1, "pageColumnsPerPage must be >= 1");
		}
		this.pageColumnsPerPage = pageColumnsPerPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getPageColumnSpace()
	{
		return this.pageColumnSpace;
	}
	
	/**
	 * <p>Setter for the field <code>pageColumnSpace</code>.</p>
	 *
	 * @param pageColumnSpace a {@link java.lang.Integer} object.
	 */
	public void setPageColumnSpace(final Integer pageColumnSpace)
	{
		if(pageColumnSpace != null)
		{
			Validate.isTrue(pageColumnSpace >= 0, "pageColumnSpace must be >= 0");
		}
		this.pageColumnSpace = pageColumnSpace;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getIgnorePageWidth()
	{
		return this.ignorePageWidth;
	}
	
	/**
	 * <p>Setter for the field <code>ignorePageWidth</code>.</p>
	 *
	 * @param ignorePageWidth a {@link java.lang.Boolean} object.
	 */
	public void setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.ignorePageWidth = ignorePageWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getColumnPrintRepeatedDetailValues()
	{
		return this.columnPrintRepeatedDetailValues;
	}
	
	/**
	 * <p>Setter for the field <code>columnPrintRepeatedDetailValues</code>.</p>
	 *
	 * @param columnPrintRepeatedDetailValues a {@link java.lang.Boolean} object.
	 */
	public void setColumnPrintRepeatedDetailValues(final Boolean columnPrintRepeatedDetailValues)
	{
		this.columnPrintRepeatedDetailValues = columnPrintRepeatedDetailValues;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getColumnWidth()
	{
		return this.columnWidth;
	}
	
	/**
	 * <p>Setter for the field <code>columnWidth</code>.</p>
	 *
	 * @param columnWidth a {@link java.lang.Integer} object.
	 */
	public void setColumnWidth(final Integer columnWidth)
	{
		if(columnWidth != null)
		{
			Validate.isTrue(columnWidth >= 0, "columnWidth must be >= 0");
		}
		this.columnWidth = columnWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupHeaderLayout getGroupHeaderLayout()
	{
		return this.groupHeaderLayout;
	}
	
	/**
	 * <p>Setter for the field <code>groupHeaderLayout</code>.</p>
	 *
	 * @param groupHeaderLayout a {@link net.sf.dynamicreports.report.constant.GroupHeaderLayout} object.
	 */
	public void setGroupHeaderLayout(final GroupHeaderLayout groupHeaderLayout)
	{
		this.groupHeaderLayout = groupHeaderLayout;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupHideColumn()
	{
		return this.groupHideColumn;
	}
	
	/**
	 * <p>Setter for the field <code>groupHideColumn</code>.</p>
	 *
	 * @param groupHideColumn a {@link java.lang.Boolean} object.
	 */
	public void setGroupHideColumn(final Boolean groupHideColumn)
	{
		this.groupHideColumn = groupHideColumn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupShowColumnHeaderAndFooter()
	{
		return this.groupShowColumnHeaderAndFooter;
	}
	
	/**
	 * <p>Setter for the field <code>groupShowColumnHeaderAndFooter</code>.</p>
	 *
	 * @param groupShowColumnHeaderAndFooter a {@link java.lang.Boolean} object.
	 */
	public void setGroupShowColumnHeaderAndFooter(final Boolean groupShowColumnHeaderAndFooter)
	{
		this.groupShowColumnHeaderAndFooter = groupShowColumnHeaderAndFooter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getGroupPadding()
	{
		return this.groupPadding;
	}
	
	/**
	 * <p>Setter for the field <code>groupPadding</code>.</p>
	 *
	 * @param groupPadding a {@link java.lang.Integer} object.
	 */
	public void setGroupPadding(final Integer groupPadding)
	{
		if(groupPadding != null)
		{
			Validate.isTrue(groupPadding >= 0, "groupPadding must be >= 0");
		}
		this.groupPadding = groupPadding;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupStartInNewPage()
	{
		return this.groupStartInNewPage;
	}
	
	/**
	 * <p>Setter for the field <code>groupStartInNewPage</code>.</p>
	 *
	 * @param groupStartInNewPage a {@link java.lang.Boolean} object.
	 */
	public void setGroupStartInNewPage(final Boolean groupStartInNewPage)
	{
		this.groupStartInNewPage = groupStartInNewPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupStartInNewColumn()
	{
		return this.groupStartInNewColumn;
	}
	
	/**
	 * <p>Setter for the field <code>groupStartInNewColumn</code>.</p>
	 *
	 * @param groupStartInNewColumn a {@link java.lang.Boolean} object.
	 */
	public void setGroupStartInNewColumn(final Boolean groupStartInNewColumn)
	{
		this.groupStartInNewColumn = groupStartInNewColumn;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupReprintHeaderOnEachPage()
	{
		return this.groupReprintHeaderOnEachPage;
	}
	
	/**
	 * <p>Setter for the field <code>groupReprintHeaderOnEachPage</code>.</p>
	 *
	 * @param groupReprintHeaderOnEachPage a {@link java.lang.Boolean} object.
	 */
	public void setGroupReprintHeaderOnEachPage(final Boolean groupReprintHeaderOnEachPage)
	{
		this.groupReprintHeaderOnEachPage = groupReprintHeaderOnEachPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupResetPageNumber()
	{
		return this.groupResetPageNumber;
	}
	
	/**
	 * <p>Setter for the field <code>groupResetPageNumber</code>.</p>
	 *
	 * @param groupResetPageNumber a {@link java.lang.Boolean} object.
	 */
	public void setGroupResetPageNumber(final Boolean groupResetPageNumber)
	{
		this.groupResetPageNumber = groupResetPageNumber;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GroupFooterPosition getGroupFooterPosition()
	{
		return this.groupFooterPosition;
	}
	
	/**
	 * <p>Setter for the field <code>groupFooterPosition</code>.</p>
	 *
	 * @param groupFooterPosition a {@link net.sf.dynamicreports.report.constant.GroupFooterPosition} object.
	 */
	public void setGroupFooterPosition(final GroupFooterPosition groupFooterPosition)
	{
		this.groupFooterPosition = groupFooterPosition;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupKeepTogether()
	{
		return this.groupKeepTogether;
	}
	
	/**
	 * <p>Setter for the field <code>groupKeepTogether</code>.</p>
	 *
	 * @param groupKeepTogether a {@link java.lang.Boolean} object.
	 */
	public void setGroupKeepTogether(final Boolean groupKeepTogether)
	{
		this.groupKeepTogether = groupKeepTogether;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getGroupHeaderWithSubtotal()
	{
		return this.groupHeaderWithSubtotal;
	}
	
	/**
	 * <p>Setter for the field <code>groupHeaderWithSubtotal</code>.</p>
	 *
	 * @param groupHeaderWithSubtotal a {@link java.lang.Boolean} object.
	 */
	public void setGroupHeaderWithSubtotal(final Boolean groupHeaderWithSubtotal)
	{
		this.groupHeaderWithSubtotal = groupHeaderWithSubtotal;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getSubtotalLabelPosition()
	{
		return this.subtotalLabelPosition;
	}
	
	/**
	 * <p>Setter for the field <code>subtotalLabelPosition</code>.</p>
	 *
	 * @param subtotalLabelPosition a {@link net.sf.dynamicreports.report.constant.Position} object.
	 */
	public void setSubtotalLabelPosition(final Position subtotalLabelPosition)
	{
		this.subtotalLabelPosition = subtotalLabelPosition;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getTableOfContents()
	{
		return this.tableOfContents;
	}
	
	/**
	 * <p>Setter for the field <code>tableOfContents</code>.</p>
	 *
	 * @param tableOfContents a {@link java.lang.Boolean} object.
	 */
	public void setTableOfContents(final Boolean tableOfContents)
	{
		this.tableOfContents = tableOfContents;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	/**
	 * <p>Setter for the field <code>tableOfContentsCustomizer</code>.</p>
	 *
	 * @param tableOfContentsCustomizer a {@link net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer}
	 *                                  object.
	 */
	public void setTableOfContentsCustomizer(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getTextFieldWidth()
	{
		return this.textFieldWidth;
	}
	
	/**
	 * <p>Setter for the field <code>textFieldWidth</code>.</p>
	 *
	 * @param textFieldWidth a {@link java.lang.Integer} object.
	 */
	public void setTextFieldWidth(final Integer textFieldWidth)
	{
		if(textFieldWidth != null)
		{
			Validate.isTrue(textFieldWidth >= 0, "textFieldWidth must be >= 0");
		}
		this.textFieldWidth = textFieldWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getImageHeight()
	{
		return this.imageHeight;
	}
	
	/**
	 * <p>Setter for the field <code>imageHeight</code>.</p>
	 *
	 * @param imageHeight a {@link java.lang.Integer} object.
	 */
	public void setImageHeight(final Integer imageHeight)
	{
		if(imageHeight != null)
		{
			Validate.isTrue(imageHeight >= 0, "imageHeight must be >= 0");
		}
		this.imageHeight = imageHeight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getImageWidth()
	{
		return this.imageWidth;
	}
	
	/**
	 * <p>Setter for the field <code>imageWidth</code>.</p>
	 *
	 * @param imageWidth a {@link java.lang.Integer} object.
	 */
	public void setImageWidth(final Integer imageWidth)
	{
		if(imageWidth != null)
		{
			Validate.isTrue(imageWidth >= 0, "imageWidth must be >= 0");
		}
		this.imageWidth = imageWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getListgap()
	{
		return this.listgap;
	}
	
	/**
	 * <p>Setter for the field <code>listgap</code>.</p>
	 *
	 * @param listgap a {@link java.lang.Integer} object.
	 */
	public void setListgap(final Integer listgap)
	{
		if(listgap != null)
		{
			Validate.isTrue(listgap >= 0, "listgap must be >= 0");
		}
		this.listgap = listgap;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getMultiPageListWidth()
	{
		return this.multiPageListWidth;
	}
	
	/**
	 * <p>Setter for the field <code>multiPageListWidth</code>.</p>
	 *
	 * @param multiPageListWidth a {@link java.lang.Integer} object.
	 */
	public void setMultiPageListWidth(final Integer multiPageListWidth)
	{
		this.multiPageListWidth = multiPageListWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getMultiPageListHeight()
	{
		return this.multiPageListHeight;
	}
	
	/**
	 * <p>Setter for the field <code>multiPageListHeight</code>.</p>
	 *
	 * @param multiPageListHeight a {@link java.lang.Integer} object.
	 */
	public void setMultiPageListHeight(final Integer multiPageListHeight)
	{
		this.multiPageListHeight = multiPageListHeight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getSubreportHeight()
	{
		return this.subreportHeight;
	}
	
	/**
	 * <p>Setter for the field <code>subreportHeight</code>.</p>
	 *
	 * @param subreportHeight a {@link java.lang.Integer} object.
	 */
	public void setSubreportHeight(final Integer subreportHeight)
	{
		if(subreportHeight != null)
		{
			Validate.isTrue(subreportHeight >= 0, "subreportHeight must be >= 0");
		}
		this.subreportHeight = subreportHeight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getSubreportWidth()
	{
		return this.subreportWidth;
	}
	
	/**
	 * <p>Setter for the field <code>subreportWidth</code>.</p>
	 *
	 * @param subreportWidth a {@link java.lang.Integer} object.
	 */
	public void setSubreportWidth(final Integer subreportWidth)
	{
		if(subreportWidth != null)
		{
			Validate.isTrue(subreportWidth >= 0, "subreportWidth must be >= 0");
		}
		this.subreportWidth = subreportWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCrosstabHeight()
	{
		return this.crosstabHeight;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabHeight</code>.</p>
	 *
	 * @param crosstabHeight a {@link java.lang.Integer} object.
	 */
	public void setCrosstabHeight(final Integer crosstabHeight)
	{
		if(crosstabHeight != null)
		{
			Validate.isTrue(crosstabHeight >= 0, "crosstabHeight must be >= 0");
		}
		this.crosstabHeight = crosstabHeight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCrosstabWidth()
	{
		return this.crosstabWidth;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabWidth</code>.</p>
	 *
	 * @param crosstabWidth a {@link java.lang.Integer} object.
	 */
	public void setCrosstabWidth(final Integer crosstabWidth)
	{
		if(crosstabWidth != null)
		{
			Validate.isTrue(crosstabWidth >= 0, "crosstabWidth must be >= 0");
		}
		this.crosstabWidth = crosstabWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getCrosstabHighlightOddRows()
	{
		return this.crosstabHighlightOddRows;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabHighlightOddRows</code>.</p>
	 *
	 * @param crosstabHighlightOddRows a {@link java.lang.Boolean} object.
	 */
	public void setCrosstabHighlightOddRows(final Boolean crosstabHighlightOddRows)
	{
		this.crosstabHighlightOddRows = crosstabHighlightOddRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getCrosstabOddRowStyle()
	{
		return this.crosstabOddRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabOddRowStyle</code>.</p>
	 *
	 * @param crosstabOddRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setCrosstabOddRowStyle(final DRSimpleStyle crosstabOddRowStyle)
	{
		this.crosstabOddRowStyle = crosstabOddRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getCrosstabHighlightEvenRows()
	{
		return this.crosstabHighlightEvenRows;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabHighlightEvenRows</code>.</p>
	 *
	 * @param crosstabHighlightEvenRows a {@link java.lang.Boolean} object.
	 */
	public void setCrosstabHighlightEvenRows(final Boolean crosstabHighlightEvenRows)
	{
		this.crosstabHighlightEvenRows = crosstabHighlightEvenRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getCrosstabEvenRowStyle()
	{
		return this.crosstabEvenRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabEvenRowStyle</code>.</p>
	 *
	 * @param crosstabEvenRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setCrosstabEvenRowStyle(final DRSimpleStyle crosstabEvenRowStyle)
	{
		this.crosstabEvenRowStyle = crosstabEvenRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getCrosstabGroupStyle()
	{
		return this.crosstabGroupStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabGroupStyle</code>.</p>
	 *
	 * @param crosstabGroupStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setCrosstabGroupStyle(final DRIReportStyle crosstabGroupStyle)
	{
		this.crosstabGroupStyle = crosstabGroupStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getCrosstabGroupTotalStyle()
	{
		return this.crosstabGroupTotalStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabGroupTotalStyle</code>.</p>
	 *
	 * @param crosstabGroupTotalStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setCrosstabGroupTotalStyle(final DRIReportStyle crosstabGroupTotalStyle)
	{
		this.crosstabGroupTotalStyle = crosstabGroupTotalStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getCrosstabGrandTotalStyle()
	{
		return this.crosstabGrandTotalStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabGrandTotalStyle</code>.</p>
	 *
	 * @param crosstabGrandTotalStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setCrosstabGrandTotalStyle(final DRIReportStyle crosstabGrandTotalStyle)
	{
		this.crosstabGrandTotalStyle = crosstabGrandTotalStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getCrosstabCellStyle()
	{
		return this.crosstabCellStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabCellStyle</code>.</p>
	 *
	 * @param crosstabCellStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setCrosstabCellStyle(final DRIReportStyle crosstabCellStyle)
	{
		this.crosstabCellStyle = crosstabCellStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getCrosstabMeasureTitleStyle()
	{
		return this.crosstabMeasureTitleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>crosstabMeasureTitleStyle</code>.</p>
	 *
	 * @param crosstabMeasureTitleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setCrosstabMeasureTitleStyle(final DRIReportStyle crosstabMeasureTitleStyle)
	{
		this.crosstabMeasureTitleStyle = crosstabMeasureTitleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BooleanComponentType getBooleanComponentType()
	{
		return this.booleanComponentType;
	}
	
	/**
	 * <p>Setter for the field <code>booleanComponentType</code>.</p>
	 *
	 * @param booleanComponentType a {@link net.sf.dynamicreports.report.constant.BooleanComponentType} object.
	 */
	public void setBooleanComponentType(final BooleanComponentType booleanComponentType)
	{
		this.booleanComponentType = booleanComponentType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getBooleanEmptyWhenNullValue()
	{
		return this.booleanEmptyWhenNullValue;
	}
	
	/**
	 * <p>Setter for the field <code>booleanEmptyWhenNullValue</code>.</p>
	 *
	 * @param booleanEmptyWhenNullValue a {@link java.lang.Boolean} object.
	 */
	public void setBooleanEmptyWhenNullValue(final Boolean booleanEmptyWhenNullValue)
	{
		this.booleanEmptyWhenNullValue = booleanEmptyWhenNullValue;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getBooleanImageWidth()
	{
		return this.booleanImageWidth;
	}
	
	/**
	 * <p>Setter for the field <code>booleanImageWidth</code>.</p>
	 *
	 * @param booleanImageWidth a {@link java.lang.Integer} object.
	 */
	public void setBooleanImageWidth(final Integer booleanImageWidth)
	{
		this.booleanImageWidth = booleanImageWidth;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getBooleanImageHeight()
	{
		return this.booleanImageHeight;
	}
	
	/**
	 * <p>Setter for the field <code>booleanImageHeight</code>.</p>
	 *
	 * @param booleanImageHeight a {@link java.lang.Integer} object.
	 */
	public void setBooleanImageHeight(final Integer booleanImageHeight)
	{
		this.booleanImageHeight = booleanImageHeight;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getBooleanColumnStyle()
	{
		return this.booleanColumnStyle;
	}
	
	/**
	 * <p>Setter for the field <code>booleanColumnStyle</code>.</p>
	 *
	 * @param booleanColumnStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setBooleanColumnStyle(final DRIReportStyle booleanColumnStyle)
	{
		this.booleanColumnStyle = booleanColumnStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getDefaultSplitType()
	{
		return this.defaultSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>defaultSplitType</code>.</p>
	 *
	 * @param defaultSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setDefaultSplitType(final SplitType defaultSplitType)
	{
		this.defaultSplitType = defaultSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getTitleSplitType()
	{
		return this.titleSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>titleSplitType</code>.</p>
	 *
	 * @param titleSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setTitleSplitType(final SplitType titleSplitType)
	{
		this.titleSplitType = titleSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getPageHeaderSplitType()
	{
		return this.pageHeaderSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>pageHeaderSplitType</code>.</p>
	 *
	 * @param pageHeaderSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setPageHeaderSplitType(final SplitType pageHeaderSplitType)
	{
		this.pageHeaderSplitType = pageHeaderSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getPageFooterSplitType()
	{
		return this.pageFooterSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>pageFooterSplitType</code>.</p>
	 *
	 * @param pageFooterSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setPageFooterSplitType(final SplitType pageFooterSplitType)
	{
		this.pageFooterSplitType = pageFooterSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getColumnHeaderSplitType()
	{
		return this.columnHeaderSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>columnHeaderSplitType</code>.</p>
	 *
	 * @param columnHeaderSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setColumnHeaderSplitType(final SplitType columnHeaderSplitType)
	{
		this.columnHeaderSplitType = columnHeaderSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getColumnFooterSplitType()
	{
		return this.columnFooterSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>columnFooterSplitType</code>.</p>
	 *
	 * @param columnFooterSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setColumnFooterSplitType(final SplitType columnFooterSplitType)
	{
		this.columnFooterSplitType = columnFooterSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getGroupHeaderSplitType()
	{
		return this.groupHeaderSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>groupHeaderSplitType</code>.</p>
	 *
	 * @param groupHeaderSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setGroupHeaderSplitType(final SplitType groupHeaderSplitType)
	{
		this.groupHeaderSplitType = groupHeaderSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getGroupFooterSplitType()
	{
		return this.groupFooterSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>groupFooterSplitType</code>.</p>
	 *
	 * @param groupFooterSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setGroupFooterSplitType(final SplitType groupFooterSplitType)
	{
		this.groupFooterSplitType = groupFooterSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getDetailHeaderSplitType()
	{
		return this.detailHeaderSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>detailHeaderSplitType</code>.</p>
	 *
	 * @param detailHeaderSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setDetailHeaderSplitType(final SplitType detailHeaderSplitType)
	{
		this.detailHeaderSplitType = detailHeaderSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getDetailSplitType()
	{
		return this.detailSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>detailSplitType</code>.</p>
	 *
	 * @param detailSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setDetailSplitType(final SplitType detailSplitType)
	{
		this.detailSplitType = detailSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getDetailFooterSplitType()
	{
		return this.detailFooterSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>detailFooterSplitType</code>.</p>
	 *
	 * @param detailFooterSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setDetailFooterSplitType(final SplitType detailFooterSplitType)
	{
		this.detailFooterSplitType = detailFooterSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getLastPageFooterSplitType()
	{
		return this.lastPageFooterSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>lastPageFooterSplitType</code>.</p>
	 *
	 * @param lastPageFooterSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setLastPageFooterSplitType(final SplitType lastPageFooterSplitType)
	{
		this.lastPageFooterSplitType = lastPageFooterSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getSummarySplitType()
	{
		return this.summarySplitType;
	}
	
	/**
	 * <p>Setter for the field <code>summarySplitType</code>.</p>
	 *
	 * @param summarySplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setSummarySplitType(final SplitType summarySplitType)
	{
		this.summarySplitType = summarySplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getNoDataSplitType()
	{
		return this.noDataSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>noDataSplitType</code>.</p>
	 *
	 * @param noDataSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setNoDataSplitType(final SplitType noDataSplitType)
	{
		this.noDataSplitType = noDataSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SplitType getBackgroundSplitType()
	{
		return this.backgroundSplitType;
	}
	
	/**
	 * <p>Setter for the field <code>backgroundSplitType</code>.</p>
	 *
	 * @param backgroundSplitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public void setBackgroundSplitType(final SplitType backgroundSplitType)
	{
		this.backgroundSplitType = backgroundSplitType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getTitleStyle()
	{
		return this.titleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>titleStyle</code>.</p>
	 *
	 * @param titleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setTitleStyle(final DRIReportStyle titleStyle)
	{
		this.titleStyle = titleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getPageHeaderStyle()
	{
		return this.pageHeaderStyle;
	}
	
	/**
	 * <p>Setter for the field <code>pageHeaderStyle</code>.</p>
	 *
	 * @param pageHeaderStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setPageHeaderStyle(final DRIReportStyle pageHeaderStyle)
	{
		this.pageHeaderStyle = pageHeaderStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getPageFooterStyle()
	{
		return this.pageFooterStyle;
	}
	
	/**
	 * <p>Setter for the field <code>pageFooterStyle</code>.</p>
	 *
	 * @param pageFooterStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setPageFooterStyle(final DRIReportStyle pageFooterStyle)
	{
		this.pageFooterStyle = pageFooterStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnHeaderStyle()
	{
		return this.columnHeaderStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnHeaderStyle</code>.</p>
	 *
	 * @param columnHeaderStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnHeaderStyle(final DRIReportStyle columnHeaderStyle)
	{
		this.columnHeaderStyle = columnHeaderStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnFooterStyle()
	{
		return this.columnFooterStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnFooterStyle</code>.</p>
	 *
	 * @param columnFooterStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnFooterStyle(final DRIReportStyle columnFooterStyle)
	{
		this.columnFooterStyle = columnFooterStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupHeaderStyle()
	{
		return this.groupHeaderStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupHeaderStyle</code>.</p>
	 *
	 * @param groupHeaderStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupHeaderStyle(final DRIReportStyle groupHeaderStyle)
	{
		this.groupHeaderStyle = groupHeaderStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupFooterStyle()
	{
		return this.groupFooterStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupFooterStyle</code>.</p>
	 *
	 * @param groupFooterStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupFooterStyle(final DRIReportStyle groupFooterStyle)
	{
		this.groupFooterStyle = groupFooterStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getDetailHeaderStyle()
	{
		return this.detailHeaderStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailHeaderStyle</code>.</p>
	 *
	 * @param detailHeaderStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setDetailHeaderStyle(final DRIReportStyle detailHeaderStyle)
	{
		this.detailHeaderStyle = detailHeaderStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getDetailStyle()
	{
		return this.detailStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailStyle</code>.</p>
	 *
	 * @param detailStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setDetailStyle(final DRIReportStyle detailStyle)
	{
		this.detailStyle = detailStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getDetailFooterStyle()
	{
		return this.detailFooterStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailFooterStyle</code>.</p>
	 *
	 * @param detailFooterStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setDetailFooterStyle(final DRIReportStyle detailFooterStyle)
	{
		this.detailFooterStyle = detailFooterStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getLastPageFooterStyle()
	{
		return this.lastPageFooterStyle;
	}
	
	/**
	 * <p>Setter for the field <code>lastPageFooterStyle</code>.</p>
	 *
	 * @param lastPageFooterStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setLastPageFooterStyle(final DRIReportStyle lastPageFooterStyle)
	{
		this.lastPageFooterStyle = lastPageFooterStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getSummaryStyle()
	{
		return this.summaryStyle;
	}
	
	/**
	 * <p>Setter for the field <code>summaryStyle</code>.</p>
	 *
	 * @param summaryStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setSummaryStyle(final DRIReportStyle summaryStyle)
	{
		this.summaryStyle = summaryStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getNoDataStyle()
	{
		return this.noDataStyle;
	}
	
	/**
	 * <p>Setter for the field <code>noDataStyle</code>.</p>
	 *
	 * @param noDataStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setNoDataStyle(final DRIReportStyle noDataStyle)
	{
		this.noDataStyle = noDataStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getBackgroundStyle()
	{
		return this.backgroundStyle;
	}
	
	/**
	 * <p>Setter for the field <code>backgroundStyle</code>.</p>
	 *
	 * @param backgroundStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setBackgroundStyle(final DRIReportStyle backgroundStyle)
	{
		this.backgroundStyle = backgroundStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getTitleBackgroundComponent()
	{
		return this.titleBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>titleBackgroundComponent</code>.</p>
	 *
	 * @param titleBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setTitleBackgroundComponent(final DRComponent titleBackgroundComponent)
	{
		this.titleBackgroundComponent = titleBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getPageHeaderBackgroundComponent()
	{
		return this.pageHeaderBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>pageHeaderBackgroundComponent</code>.</p>
	 *
	 * @param pageHeaderBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setPageHeaderBackgroundComponent(final DRComponent pageHeaderBackgroundComponent)
	{
		this.pageHeaderBackgroundComponent = pageHeaderBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getPageFooterBackgroundComponent()
	{
		return this.pageFooterBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>pageFooterBackgroundComponent</code>.</p>
	 *
	 * @param pageFooterBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setPageFooterBackgroundComponent(final DRComponent pageFooterBackgroundComponent)
	{
		this.pageFooterBackgroundComponent = pageFooterBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getColumnHeaderBackgroundComponent()
	{
		return this.columnHeaderBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>columnHeaderBackgroundComponent</code>.</p>
	 *
	 * @param columnHeaderBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setColumnHeaderBackgroundComponent(final DRComponent columnHeaderBackgroundComponent)
	{
		this.columnHeaderBackgroundComponent = columnHeaderBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getColumnFooterBackgroundComponent()
	{
		return this.columnFooterBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>columnFooterBackgroundComponent</code>.</p>
	 *
	 * @param columnFooterBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setColumnFooterBackgroundComponent(final DRComponent columnFooterBackgroundComponent)
	{
		this.columnFooterBackgroundComponent = columnFooterBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getGroupHeaderBackgroundComponent()
	{
		return this.groupHeaderBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>groupHeaderBackgroundComponent</code>.</p>
	 *
	 * @param groupHeaderBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setGroupHeaderBackgroundComponent(final DRComponent groupHeaderBackgroundComponent)
	{
		this.groupHeaderBackgroundComponent = groupHeaderBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getGroupFooterBackgroundComponent()
	{
		return this.groupFooterBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>groupFooterBackgroundComponent</code>.</p>
	 *
	 * @param groupFooterBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setGroupFooterBackgroundComponent(final DRComponent groupFooterBackgroundComponent)
	{
		this.groupFooterBackgroundComponent = groupFooterBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getDetailHeaderBackgroundComponent()
	{
		return this.detailHeaderBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>detailHeaderBackgroundComponent</code>.</p>
	 *
	 * @param detailHeaderBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setDetailHeaderBackgroundComponent(final DRComponent detailHeaderBackgroundComponent)
	{
		this.detailHeaderBackgroundComponent = detailHeaderBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getDetailBackgroundComponent()
	{
		return this.detailBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>detailBackgroundComponent</code>.</p>
	 *
	 * @param detailBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setDetailBackgroundComponent(final DRComponent detailBackgroundComponent)
	{
		this.detailBackgroundComponent = detailBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getDetailFooterBackgroundComponent()
	{
		return this.detailFooterBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>detailFooterBackgroundComponent</code>.</p>
	 *
	 * @param detailFooterBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setDetailFooterBackgroundComponent(final DRComponent detailFooterBackgroundComponent)
	{
		this.detailFooterBackgroundComponent = detailFooterBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getLastPageFooterBackgroundComponent()
	{
		return this.lastPageFooterBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>lastPageFooterBackgroundComponent</code>.</p>
	 *
	 * @param lastPageFooterBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent}
	 *                                          object.
	 */
	public void setLastPageFooterBackgroundComponent(final DRComponent lastPageFooterBackgroundComponent)
	{
		this.lastPageFooterBackgroundComponent = lastPageFooterBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getSummaryBackgroundComponent()
	{
		return this.summaryBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>summaryBackgroundComponent</code>.</p>
	 *
	 * @param summaryBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setSummaryBackgroundComponent(final DRComponent summaryBackgroundComponent)
	{
		this.summaryBackgroundComponent = summaryBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getNoDataBackgroundComponent()
	{
		return this.noDataBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>noDataBackgroundComponent</code>.</p>
	 *
	 * @param noDataBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setNoDataBackgroundComponent(final DRComponent noDataBackgroundComponent)
	{
		this.noDataBackgroundComponent = noDataBackgroundComponent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRComponent getBackgroundBackgroundComponent()
	{
		return this.backgroundBackgroundComponent;
	}
	
	/**
	 * <p>Setter for the field <code>backgroundBackgroundComponent</code>.</p>
	 *
	 * @param backgroundBackgroundComponent a {@link net.sf.dynamicreports.report.base.component.DRComponent} object.
	 */
	public void setBackgroundBackgroundComponent(final DRComponent backgroundBackgroundComponent)
	{
		this.backgroundBackgroundComponent = backgroundBackgroundComponent;
	}
}
