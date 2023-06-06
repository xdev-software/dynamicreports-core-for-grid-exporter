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
package net.sf.dynamicreports.report.defaults;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;

import net.sf.dynamicreports.report.base.DRMargin;
import net.sf.dynamicreports.report.base.datatype.DRDataType;
import net.sf.dynamicreports.report.base.style.DRFont;
import net.sf.dynamicreports.report.base.style.DRPadding;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.base.style.DRStyle;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.CrosstabTotalPosition;
import net.sf.dynamicreports.report.constant.GroupFooterPosition;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.Language;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.Position;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.SplitType;
import net.sf.dynamicreports.report.constant.TableOfContentsPosition;
import net.sf.dynamicreports.report.constant.TextAdjust;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.style.DRIStyle;


/**
 * <p>Default class.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 */
public class Default
{
	private String reportName;
	private Locale locale;
	private boolean showColumnTitle;
	private boolean showColumnValues;
	private boolean ignorePagination;
	private WhenNoDataType whenNoDataType;
	private WhenResourceMissingType whenResourceMissingType;
	private boolean titleOnANewPage;
	private boolean summaryOnANewPage;
	private boolean summaryWithPageHeaderAndFooter;
	private boolean floatColumnFooter;
	private Orientation printOrder;
	private RunDirection columnDirection;
	private String language;
	private boolean useFieldNameAsDescription;
	// style
	private boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private boolean highlightDetailEvenRows;
	private DRSimpleStyle detailEvenRowStyle;
	private DRStyle textStyle;
	private DRStyle columnTitleStyle;
	private DRStyle columnStyle;
	private DRStyle groupTitleStyle;
	private DRStyle groupStyle;
	private DRStyle subtotalStyle;
	private DRStyle imageStyle;
	// page
	private int pageWidth;
	private int pageHeight;
	private PageOrientation pageOrientation;
	private DRMargin pageMargin;
	private DRMargin subreportPageMargin;
	private int pageColumnsPerPage;
	private int pageColumnSpace;
	private boolean ignorePageWidth;
	// column
	private boolean columnPrintRepeatedDetailValues;
	private int columnWidth;
	// component
	private boolean removeLineWhenBlank;
	private boolean printInFirstWholeBand;
	private boolean printWhenDetailOverflows;
	// group
	private GroupHeaderLayout groupHeaderLayout;
	private boolean groupHideColumn;
	private boolean groupShowColumnHeaderAndFooter;
	private int groupPadding;
	private boolean groupStartInNewPage;
	private boolean groupStartInNewColumn;
	private boolean groupReprintHeaderOnEachPage;
	private boolean groupResetPageNumber;
	private Integer groupMinHeightToStartNewPage;
	private GroupFooterPosition groupFooterPosition;
	private boolean groupKeepTogether;
	private boolean groupHeaderWithSubtotal;
	private boolean groupByDataType;
	// subtotal
	private Position subtotalLabelPosition;
	// table of contents
	private boolean tableOfContents;
	private DRITableOfContentsCustomizer tableOfContentsCustomizer;
	private boolean addGroupToTableOfContents;
	private TableOfContentsPosition tableOfContentsPosition;
	// text field
	private int textFieldWidth;
	private boolean textFieldPrintRepeatedValues;
	private boolean textFieldStretchWithOverflow;
	private TextAdjust textFieldTextAdjust;
	// image
	private int imageWidth;
	private int imageHeight;
	// filler
	private int fillerWidth;
	private int fillerHeight;
	// line
	private int lineWidth;
	private int lineHeight;
	// ellipse
	private int ellipseWidth;
	private int ellipseHeight;
	// rectangle
	private Integer rectangleRadius;
	private int rectangleWidth;
	private int rectangleHeight;
	// map
	private int mapWidth;
	private int mapHeight;
	// custom component
	private int customComponentWidth;
	private int customComponentHeight;
	// break
	private int breakWidth;
	private int breakHeight;
	// generic element
	private int genericElementWidth;
	private int genericElementHeight;
	// list
	private Integer listWidth;
	private Integer listHeight;
	private int listgap;
	private HorizontalCellComponentAlignment horizontalCellComponentAlignment;
	private VerticalCellComponentAlignment verticalCellComponentAlignment;
	// multi page list
	private int multiPageListWidth;
	private int multiPageListHeight;
	// subreport
	private int subreportWidth;
	private int subreportHeight;
	// crosstab
	private int crosstabWidth;
	private int crosstabHeight;
	private CrosstabTotalPosition crosstabColumnGroupTotalPosition;
	private CrosstabTotalPosition crosstabRowGroupTotalPosition;
	private boolean crosstabColumnGroupShowTotal;
	private boolean crosstabRowGroupShowTotal;
	private int crosstabColumnGroupTotalHeaderMaxWidth;
	private int crosstabRowGroupHeaderMaxWidth;
	private int crosstabCellMaxWidth;
	private boolean crosstabHighlightOddRows;
	private DRSimpleStyle crosstabOddRowStyle;
	private boolean crosstabHighlightEvenRows;
	private DRSimpleStyle crosstabEvenRowStyle;
	private DRStyle crosstabGroupStyle;
	private DRStyle crosstabGroupTotalStyle;
	private DRStyle crosstabGrandTotalStyle;
	private DRStyle crosstabCellStyle;
	private DRStyle crosstabMeasureTitleStyle;
	private CrosstabPercentageType crosstabPercentageType;
	// boolean
	private BooleanComponentType booleanComponentType;
	private boolean booleanEmptyWhenNullValue;
	private int booleanImageWidth;
	private int booleanImageHeight;
	private DRStyle booleanColumnStyle;
	private HorizontalImageAlignment booleanHorizontalImageAlignment;
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
	private DRIStyle bandStyle;
	// band background component
	private DRIComponent bandBackgroundComponent;
	// font
	private DRFont font;
	// datatype
	private DRDataType<Number, BigDecimal> bigDecimalType;
	private DRDataType<Number, BigInteger> bigIntegerType;
	private DRDataType<Number, Byte> byteType;
	private DRDataType<Number, Double> doubleType;
	private DRDataType<Number, Float> floatType;
	private DRDataType<Number, Integer> integerType;
	private DRDataType<Number, Long> longType;
	private DRDataType<Number, Short> shortType;
	private DRDataType<Date, Date> dateType;
	private DRDataType<Date, Date> dateYearToMonthType;
	private DRDataType<Date, Date> dateYearToHourType;
	private DRDataType<Date, Date> dateYearToMinuteType;
	private DRDataType<Date, Date> dateYearToSecondType;
	private DRDataType<Date, Date> dateYearToFractionType;
	private DRDataType<Date, Date> dateYearType;
	private DRDataType<Date, Date> dateMonthType;
	private DRDataType<Date, Date> dateDayType;
	private DRDataType<Date, Date> timeHourToMinuteType;
	private DRDataType<Date, Date> timeHourToSecondType;
	private DRDataType<Date, Date> timeHourToFractionType;
	private DRDataType<Number, Double> percentageType;
	private DRDataType<Boolean, Boolean> booleanType;
	private DRDataType<Character, Character> characterType;
	private DRDataType<String, String> stringType;
	private HorizontalTextAlignment pageXofYHorizontalTextAlignment;
	// system fonts
	private boolean loadSystemFonts;
	
	/**
	 * <p>Constructor for Default.</p>
	 */
	public Default()
	{
        this.init();
	}
	
	private void init()
	{
		this.reportName = "Report";
		this.locale = Locale.getDefault();
		this.showColumnTitle = true;
		this.showColumnValues = true;
		this.ignorePagination = false;
		this.whenNoDataType = WhenNoDataType.NO_PAGES;
		this.whenResourceMissingType = WhenResourceMissingType.NULL;
		this.titleOnANewPage = false;
		this.summaryOnANewPage = false;
		this.summaryWithPageHeaderAndFooter = false;
		this.floatColumnFooter = false;
		this.printOrder = Orientation.VERTICAL;
		this.columnDirection = RunDirection.LEFT_TO_RIGHT;
		this.language = Language.JAVA;
		this.useFieldNameAsDescription = true;
		
		this.highlightDetailOddRows = false;
		this.detailOddRowStyle = new DRSimpleStyle();
        this.detailOddRowStyle.setBackgroundColor(new Color(200, 200, 200));
		this.highlightDetailEvenRows = false;
		this.detailEvenRowStyle = new DRSimpleStyle();
        this.detailEvenRowStyle.setBackgroundColor(new Color(240, 240, 240));
		
		this.textStyle = new DRStyle();
        this.textStyle.setForegroundColor(Color.BLACK);
        this.textStyle.setPadding(new DRPadding(2));
		
		this.columnTitleStyle = null;
		this.columnStyle = null;
		this.groupTitleStyle = null;
		this.groupStyle = null;
		this.subtotalStyle = null;
		this.imageStyle = new DRStyle();
        this.imageStyle.setImageScale(ImageScale.RETAIN_SHAPE);
		
		this.pageWidth = PageType.A4.getWidth();
		this.pageHeight = PageType.A4.getHeight();
		this.pageOrientation = PageOrientation.PORTRAIT;
		this.pageMargin = new DRMargin(10);
		this.subreportPageMargin = new DRMargin(0);
		this.pageColumnsPerPage = 1;
		this.pageColumnSpace = 0;
		this.ignorePageWidth = false;
		
		this.columnPrintRepeatedDetailValues = true;
		this.columnWidth = 100;
		
		this.removeLineWhenBlank = false;
		this.printInFirstWholeBand = false;
		this.printWhenDetailOverflows = false;
		
		this.groupHeaderLayout = GroupHeaderLayout.VALUE;
		this.groupHideColumn = true;
		this.groupShowColumnHeaderAndFooter = false;
		this.groupPadding = 10;
		this.groupStartInNewPage = false;
		this.groupStartInNewColumn = false;
		this.groupReprintHeaderOnEachPage = false;
		this.groupResetPageNumber = false;
		this.groupMinHeightToStartNewPage = null;
		this.groupFooterPosition = GroupFooterPosition.NORMAL;
		this.groupKeepTogether = false;
		this.groupHeaderWithSubtotal = false;
		this.groupByDataType = false;
		
		this.subtotalLabelPosition = Position.TOP;
		
		this.tableOfContents = false;
		this.tableOfContentsCustomizer = new TableOfContentsCustomizer();
		this.addGroupToTableOfContents = true;
		this.tableOfContentsPosition = TableOfContentsPosition.TOP;
		
		this.textFieldWidth = 100;
		this.textFieldPrintRepeatedValues = true;
		this.textFieldStretchWithOverflow = true;
		this.textFieldTextAdjust = TextAdjust.STRETCH_HEIGHT;
		
		this.imageWidth = 100;
		this.imageHeight = 100;
		
		this.fillerWidth = 0;
		this.fillerHeight = 0;
		
		this.lineWidth = 1;
		this.lineHeight = 1;
		
		this.ellipseWidth = 100;
		this.ellipseHeight = 100;
		
		this.rectangleRadius = null;
		this.rectangleWidth = 100;
		this.rectangleHeight = 100;
		
		this.mapWidth = 200;
		this.mapHeight = 200;
		
		this.customComponentWidth = 200;
		this.customComponentHeight = 200;
		
		this.breakWidth = 1;
		this.breakHeight = 1;
		
		this.genericElementWidth = 100;
		this.genericElementHeight = 100;
		
		this.genericElementWidth = 100;
		this.genericElementHeight = 100;
		
		this.listWidth = null;
		this.listHeight = null;
		this.listgap = 0;
		this.horizontalCellComponentAlignment = HorizontalCellComponentAlignment.FLOAT;
		this.verticalCellComponentAlignment = VerticalCellComponentAlignment.EXPAND;
		
		this.multiPageListWidth = 200;
		this.multiPageListHeight = 0;
		
		this.subreportWidth = 200;
		this.subreportHeight = 0;
		
		this.crosstabWidth = 200;
		this.crosstabHeight = 0;
		this.crosstabColumnGroupTotalPosition = CrosstabTotalPosition.END;
		this.crosstabRowGroupTotalPosition = CrosstabTotalPosition.END;
		this.crosstabColumnGroupShowTotal = true;
		this.crosstabRowGroupShowTotal = true;
		this.crosstabColumnGroupTotalHeaderMaxWidth = 150;
		this.crosstabRowGroupHeaderMaxWidth = 150;
		this.crosstabCellMaxWidth = 150;
		this.crosstabHighlightOddRows = false;
		this.crosstabOddRowStyle = new DRSimpleStyle();
        this.crosstabOddRowStyle.setBackgroundColor(new Color(200, 200, 200));
		this.crosstabHighlightEvenRows = false;
		this.crosstabEvenRowStyle = new DRSimpleStyle();
        this.crosstabEvenRowStyle.setBackgroundColor(new Color(240, 240, 240));
		this.crosstabGroupStyle = null;
		this.crosstabGroupTotalStyle = null;
		this.crosstabGrandTotalStyle = null;
		this.crosstabCellStyle = null;
		this.crosstabMeasureTitleStyle = null;
		this.crosstabPercentageType = CrosstabPercentageType.NONE;
		
		this.booleanComponentType = BooleanComponentType.TEXT_TRUE_FALSE;
		this.booleanEmptyWhenNullValue = false;
		this.booleanImageWidth = 14;
		this.booleanImageHeight = 14;
		this.booleanColumnStyle = null;
		this.booleanHorizontalImageAlignment = HorizontalImageAlignment.CENTER;
		
		this.pageXofYHorizontalTextAlignment = HorizontalTextAlignment.CENTER;
		
		this.defaultSplitType = null;
		this.titleSplitType = null;
		this.pageHeaderSplitType = null;
		this.pageFooterSplitType = null;
		this.columnHeaderSplitType = null;
		this.columnFooterSplitType = null;
		this.groupHeaderSplitType = null;
		this.groupFooterSplitType = null;
		this.detailHeaderSplitType = null;
		this.detailSplitType = null;
		this.detailFooterSplitType = null;
		this.lastPageFooterSplitType = null;
		this.summarySplitType = null;
		this.noDataSplitType = null;
		this.backgroundSplitType = null;
		
		this.bandStyle = null;
		
		this.bandBackgroundComponent = null;
		
		this.font = new DRFont("SansSerif", 10);
		
		this.bigDecimalType = new DRDataType<>("#,##0.00#", HorizontalTextAlignment.RIGHT);
		this.bigIntegerType = new DRDataType<>("#,##0", HorizontalTextAlignment.RIGHT);
		this.byteType = new DRDataType<>("#,##0", HorizontalTextAlignment.RIGHT);
		this.doubleType = new DRDataType<>("#,##0.#", HorizontalTextAlignment.RIGHT);
		this.floatType = new DRDataType<>("#,##0.#", HorizontalTextAlignment.RIGHT);
		this.integerType = new DRDataType<>("#,##0", HorizontalTextAlignment.RIGHT);
		this.longType = new DRDataType<>("#,##0", HorizontalTextAlignment.RIGHT);
		this.shortType = new DRDataType<>("#,##0", HorizontalTextAlignment.RIGHT);
		this.dateType = new DRDataType<>("MM/dd/yyyy", HorizontalTextAlignment.RIGHT);
		this.dateYearToMonthType = new DRDataType<>("MM/yyyy", HorizontalTextAlignment.RIGHT);
		this.dateYearToHourType = new DRDataType<>("MM/dd/yyyy h a", HorizontalTextAlignment.RIGHT);
		this.dateYearToMinuteType = new DRDataType<>("MM/dd/yyyy h:mm a", HorizontalTextAlignment.RIGHT);
		this.dateYearToSecondType = new DRDataType<>("MM/dd/yyyy h:mm:ss a", HorizontalTextAlignment.RIGHT);
		this.dateYearToFractionType = new DRDataType<>("MM/dd/yyyy h:mm:ss,SSS a", HorizontalTextAlignment.RIGHT);
		this.dateYearType = new DRDataType<>("yyyy", HorizontalTextAlignment.RIGHT);
		this.dateMonthType = new DRDataType<>("MMMM", HorizontalTextAlignment.RIGHT);
		this.dateDayType = new DRDataType<>("dd", HorizontalTextAlignment.RIGHT);
		this.timeHourToMinuteType = new DRDataType<>("h:mm a", HorizontalTextAlignment.RIGHT);
		this.timeHourToSecondType = new DRDataType<>("h:mm:ss a", HorizontalTextAlignment.RIGHT);
		this.timeHourToFractionType = new DRDataType<>("h:mm:ss,SSS a", HorizontalTextAlignment.RIGHT);
		this.percentageType = new DRDataType<>("#,##0.00%", HorizontalTextAlignment.RIGHT);
		this.booleanType = new DRDataType<>(null, HorizontalTextAlignment.CENTER);
		this.characterType = new DRDataType<>(null, HorizontalTextAlignment.LEFT);
		this.stringType = new DRDataType<>(null, HorizontalTextAlignment.LEFT);
		
		this.loadSystemFonts = true;
	}
	
	/**
	 * <p>Getter for the field <code>reportName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getReportName()
	{
		return this.reportName;
	}
	
	/**
	 * <p>Getter for the field <code>locale</code>.</p>
	 *
	 * @return a {@link java.util.Locale} object.
	 */
	public Locale getLocale()
	{
		return this.locale;
	}
	
	/**
	 * <p>isShowColumnTitle.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	/**
	 * <p>isShowColumnValues.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	/**
	 * <p>isIgnorePagination.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	/**
	 * <p>Getter for the field <code>whenNoDataType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.WhenNoDataType} object.
	 */
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	/**
	 * <p>Getter for the field <code>whenResourceMissingType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.WhenResourceMissingType} object.
	 */
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	/**
	 * <p>isTitleOnANewPage.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	/**
	 * <p>isSummaryOnANewPage.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	/**
	 * <p>isSummaryWithPageHeaderAndFooter.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	/**
	 * <p>isFloatColumnFooter.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	/**
	 * <p>Getter for the field <code>printOrder</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.Orientation} object.
	 */
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	/**
	 * <p>Getter for the field <code>columnDirection</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.RunDirection} object.
	 */
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	/**
	 * <p>Getter for the field <code>language</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getLanguage()
	{
		return this.language;
	}
	
	/**
	 * <p>isUseFieldNameAsDescription.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	/**
	 * <p>isHighlightDetailOddRows.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	/**
	 * <p>Getter for the field <code>detailOddRowStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	/**
	 * <p>isHighlightDetailEvenRows.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	/**
	 * <p>Getter for the field <code>detailEvenRowStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	/**
	 * <p>Getter for the field <code>textStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	/**
	 * <p>Getter for the field <code>columnTitleStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	/**
	 * <p>Getter for the field <code>columnStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	/**
	 * <p>Getter for the field <code>groupTitleStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	/**
	 * <p>Getter for the field <code>groupStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	/**
	 * <p>Getter for the field <code>subtotalStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	/**
	 * <p>Getter for the field <code>imageStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	/**
	 * <p>Getter for the field <code>pageWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getPageWidth()
	{
		return this.pageWidth;
	}
	
	/**
	 * <p>Getter for the field <code>pageHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getPageHeight()
	{
		return this.pageHeight;
	}
	
	/**
	 * <p>Getter for the field <code>pageOrientation</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
	 */
	public PageOrientation getPageOrientation()
	{
		return this.pageOrientation;
	}
	
	/**
	 * <p>Getter for the field <code>pageMargin</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.DRMargin} object.
	 */
	public DRMargin getPageMargin()
	{
		return this.pageMargin;
	}
	
	/**
	 * <p>Getter for the field <code>subreportPageMargin</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.DRMargin} object.
	 */
	public DRMargin getSubreportPageMargin()
	{
		return this.subreportPageMargin;
	}
	
	/**
	 * <p>Getter for the field <code>pageColumnsPerPage</code>.</p>
	 *
	 * @return a int.
	 */
	public int getPageColumnsPerPage()
	{
		return this.pageColumnsPerPage;
	}
	
	/**
	 * <p>Getter for the field <code>pageColumnSpace</code>.</p>
	 *
	 * @return a int.
	 */
	public int getPageColumnSpace()
	{
		return this.pageColumnSpace;
	}
	
	/**
	 * <p>isIgnorePageWidth.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isIgnorePageWidth()
	{
		return this.ignorePageWidth;
	}
	
	/**
	 * <p>isColumnPrintRepeatedDetailValues.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isColumnPrintRepeatedDetailValues()
	{
		return this.columnPrintRepeatedDetailValues;
	}
	
	/**
	 * <p>Getter for the field <code>columnWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getColumnWidth()
	{
		return this.columnWidth;
	}
	
	/**
	 * <p>isRemoveLineWhenBlank.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isRemoveLineWhenBlank()
	{
		return this.removeLineWhenBlank;
	}
	
	/**
	 * <p>isPrintInFirstWholeBand.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isPrintInFirstWholeBand()
	{
		return this.printInFirstWholeBand;
	}
	
	/**
	 * <p>isPrintWhenDetailOverflows.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isPrintWhenDetailOverflows()
	{
		return this.printWhenDetailOverflows;
	}
	
	/**
	 * <p>Getter for the field <code>groupHeaderLayout</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.GroupHeaderLayout} object.
	 */
	public GroupHeaderLayout getGroupHeaderLayout()
	{
		return this.groupHeaderLayout;
	}
	
	/**
	 * <p>isGroupHideColumn.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupHideColumn()
	{
		return this.groupHideColumn;
	}
	
	/**
	 * <p>isGroupShowColumnHeaderAndFooter.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupShowColumnHeaderAndFooter()
	{
		return this.groupShowColumnHeaderAndFooter;
	}
	
	/**
	 * <p>Getter for the field <code>groupPadding</code>.</p>
	 *
	 * @return a int.
	 */
	public int getGroupPadding()
	{
		return this.groupPadding;
	}
	
	/**
	 * <p>isGroupStartInNewPage.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupStartInNewPage()
	{
		return this.groupStartInNewPage;
	}
	
	/**
	 * <p>isGroupStartInNewColumn.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupStartInNewColumn()
	{
		return this.groupStartInNewColumn;
	}
	
	/**
	 * <p>isGroupReprintHeaderOnEachPage.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupReprintHeaderOnEachPage()
	{
		return this.groupReprintHeaderOnEachPage;
	}
	
	/**
	 * <p>isGroupResetPageNumber.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupResetPageNumber()
	{
		return this.groupResetPageNumber;
	}
	
	/**
	 * <p>Getter for the field <code>groupMinHeightToStartNewPage</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getGroupMinHeightToStartNewPage()
	{
		return this.groupMinHeightToStartNewPage;
	}
	
	/**
	 * <p>Getter for the field <code>groupFooterPosition</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.GroupFooterPosition} object.
	 */
	public GroupFooterPosition getGroupFooterPosition()
	{
		return this.groupFooterPosition;
	}
	
	/**
	 * <p>isGroupKeepTogether.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupKeepTogether()
	{
		return this.groupKeepTogether;
	}
	
	/**
	 * <p>isGroupHeaderWithSubtotal.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupHeaderWithSubtotal()
	{
		return this.groupHeaderWithSubtotal;
	}
	
	/**
	 * <p>isGroupByDataType.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isGroupByDataType()
	{
		return this.groupByDataType;
	}
	
	/**
	 * <p>Getter for the field <code>subtotalLabelPosition</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.Position} object.
	 */
	public Position getSubtotalLabelPosition()
	{
		return this.subtotalLabelPosition;
	}
	
	/**
	 * <p>isTableOfContents.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isTableOfContents()
	{
		return this.tableOfContents;
	}
	
	/**
	 * <p>Getter for the field <code>tableOfContentsCustomizer</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer} object.
	 */
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	/**
	 * <p>isAddGroupToTableOfContents.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isAddGroupToTableOfContents()
	{
		return this.addGroupToTableOfContents;
	}
	
	/**
	 * <p>Getter for the field <code>tableOfContentsPosition</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.TableOfContentsPosition} object.
	 */
	public TableOfContentsPosition getTableOfContentsPosition()
	{
		return this.tableOfContentsPosition;
	}
	
	/**
	 * <p>Getter for the field <code>textFieldWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getTextFieldWidth()
	{
		return this.textFieldWidth;
	}
	
	/**
	 * <p>isTextFieldPrintRepeatedValues.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isTextFieldPrintRepeatedValues()
	{
		return this.textFieldPrintRepeatedValues;
	}
	
	/**
	 * <p>isTextFieldStretchWithOverflow.</p>
	 *
	 * @return a boolean.
	 * @deprecated replaced by {@link #getTextFieldTextAdjust(DRITextField)}
	 */
	@Deprecated
	public boolean isTextFieldStretchWithOverflow()
	{
		return this.textFieldStretchWithOverflow;
	}
	
	/**
	 * <p>getTextFieldTextAdjust.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.TextAdjust} object.
	 */
	public TextAdjust getTextFieldTextAdjust()
	{
		return null;
	}
	
	/**
	 * <p>Getter for the field <code>imageWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getImageWidth()
	{
		return this.imageWidth;
	}
	
	/**
	 * <p>Getter for the field <code>imageHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getImageHeight()
	{
		return this.imageHeight;
	}
	
	/**
	 * <p>Getter for the field <code>fillerWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getFillerWidth()
	{
		return this.fillerWidth;
	}
	
	/**
	 * <p>Getter for the field <code>fillerHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getFillerHeight()
	{
		return this.fillerHeight;
	}
	
	/**
	 * <p>Getter for the field <code>lineWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getLineWidth()
	{
		return this.lineWidth;
	}
	
	/**
	 * <p>Getter for the field <code>lineHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getLineHeight()
	{
		return this.lineHeight;
	}
	
	/**
	 * <p>Getter for the field <code>ellipseWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getEllipseWidth()
	{
		return this.ellipseWidth;
	}
	
	/**
	 * <p>Getter for the field <code>ellipseHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getEllipseHeight()
	{
		return this.ellipseHeight;
	}
	
	/**
	 * <p>Getter for the field <code>rectangleRadius</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getRectangleRadius()
	{
		return this.rectangleRadius;
	}
	
	/**
	 * <p>Getter for the field <code>rectangleWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getRectangleWidth()
	{
		return this.rectangleWidth;
	}
	
	/**
	 * <p>Getter for the field <code>rectangleHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getRectangleHeight()
	{
		return this.rectangleHeight;
	}
	
	/**
	 * <p>Getter for the field <code>mapWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getMapWidth()
	{
		return this.mapWidth;
	}
	
	/**
	 * <p>Getter for the field <code>mapHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getMapHeight()
	{
		return this.mapHeight;
	}
	
	/**
	 * <p>Getter for the field <code>customComponentWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCustomComponentWidth()
	{
		return this.customComponentWidth;
	}
	
	/**
	 * <p>Getter for the field <code>customComponentHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCustomComponentHeight()
	{
		return this.customComponentHeight;
	}
	
	/**
	 * <p>Getter for the field <code>breakWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getBreakWidth()
	{
		return this.breakWidth;
	}
	
	/**
	 * <p>Getter for the field <code>breakHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getBreakHeight()
	{
		return this.breakHeight;
	}
	
	/**
	 * <p>Getter for the field <code>genericElementWidth</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getGenericElementWidth()
	{
		return this.genericElementWidth;
	}
	
	/**
	 * <p>Getter for the field <code>genericElementHeight</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getGenericElementHeight()
	{
		return this.genericElementHeight;
	}
	
	/**
	 * <p>Getter for the field <code>listWidth</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getListWidth()
	{
		return this.listWidth;
	}
	
	/**
	 * <p>Getter for the field <code>listHeight</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getListHeight()
	{
		return this.listHeight;
	}
	
	/**
	 * <p>Getter for the field <code>listgap</code>.</p>
	 *
	 * @return a int.
	 */
	public int getListgap()
	{
		return this.listgap;
	}
	
	/**
	 * <p>Getter for the field <code>horizontalCellComponentAlignment</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
	 */
	public HorizontalCellComponentAlignment getHorizontalCellComponentAlignment()
	{
		return this.horizontalCellComponentAlignment;
	}
	
	/**
	 * <p>Getter for the field <code>verticalCellComponentAlignment</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
	 */
	public VerticalCellComponentAlignment getVerticalCellComponentAlignment()
	{
		return this.verticalCellComponentAlignment;
	}
	
	/**
	 * <p>Getter for the field <code>multiPageListWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getMultiPageListWidth()
	{
		return this.multiPageListWidth;
	}
	
	/**
	 * <p>Getter for the field <code>multiPageListHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getMultiPageListHeight()
	{
		return this.multiPageListHeight;
	}
	
	/**
	 * <p>Getter for the field <code>subreportWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getSubreportWidth()
	{
		return this.subreportWidth;
	}
	
	/**
	 * <p>Getter for the field <code>subreportHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getSubreportHeight()
	{
		return this.subreportHeight;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCrosstabWidth()
	{
		return this.crosstabWidth;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCrosstabHeight()
	{
		return this.crosstabHeight;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabColumnGroupTotalPosition</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.CrosstabTotalPosition} object.
	 */
	public CrosstabTotalPosition getCrosstabColumnGroupTotalPosition()
	{
		return this.crosstabColumnGroupTotalPosition;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabRowGroupTotalPosition</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.CrosstabTotalPosition} object.
	 */
	public CrosstabTotalPosition getCrosstabRowGroupTotalPosition()
	{
		return this.crosstabRowGroupTotalPosition;
	}
	
	/**
	 * <p>isCrosstabColumnGroupShowTotal.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isCrosstabColumnGroupShowTotal()
	{
		return this.crosstabColumnGroupShowTotal;
	}
	
	/**
	 * <p>isCrosstabRowGroupShowTotal.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isCrosstabRowGroupShowTotal()
	{
		return this.crosstabRowGroupShowTotal;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabColumnGroupTotalHeaderMaxWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCrosstabColumnGroupTotalHeaderMaxWidth()
	{
		return this.crosstabColumnGroupTotalHeaderMaxWidth;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabRowGroupHeaderMaxWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCrosstabRowGroupHeaderMaxWidth()
	{
		return this.crosstabRowGroupHeaderMaxWidth;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabCellMaxWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getCrosstabCellMaxWidth()
	{
		return this.crosstabCellMaxWidth;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabOddRowStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public DRSimpleStyle getCrosstabOddRowStyle()
	{
		return this.crosstabOddRowStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabEvenRowStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public DRSimpleStyle getCrosstabEvenRowStyle()
	{
		return this.crosstabEvenRowStyle;
	}
	
	/**
	 * <p>isCrosstabHighlightOddRows.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isCrosstabHighlightOddRows()
	{
		return this.crosstabHighlightOddRows;
	}
	
	/**
	 * <p>isCrosstabHighlightEvenRows.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isCrosstabHighlightEvenRows()
	{
		return this.crosstabHighlightEvenRows;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabGroupStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getCrosstabGroupStyle()
	{
		return this.crosstabGroupStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabGroupTotalStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getCrosstabGroupTotalStyle()
	{
		return this.crosstabGroupTotalStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabGrandTotalStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getCrosstabGrandTotalStyle()
	{
		return this.crosstabGrandTotalStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabCellStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getCrosstabCellStyle()
	{
		return this.crosstabCellStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabMeasureTitleStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRStyle} object.
	 */
	public DRStyle getCrosstabMeasureTitleStyle()
	{
		return this.crosstabMeasureTitleStyle;
	}
	
	/**
	 * <p>Getter for the field <code>crosstabPercentageType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.CrosstabPercentageType} object.
	 */
	public CrosstabPercentageType getCrosstabPercentageType()
	{
		return this.crosstabPercentageType;
	}
	
	/**
	 * <p>Getter for the field <code>booleanComponentType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.BooleanComponentType} object.
	 */
	public BooleanComponentType getBooleanComponentType()
	{
		return this.booleanComponentType;
	}
	
	/**
	 * <p>isBooleanEmptyWhenNullValue.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isBooleanEmptyWhenNullValue()
	{
		return this.booleanEmptyWhenNullValue;
	}
	
	/**
	 * <p>Getter for the field <code>booleanImageWidth</code>.</p>
	 *
	 * @return a int.
	 */
	public int getBooleanImageWidth()
	{
		return this.booleanImageWidth;
	}
	
	/**
	 * <p>Getter for the field <code>booleanImageHeight</code>.</p>
	 *
	 * @return a int.
	 */
	public int getBooleanImageHeight()
	{
		return this.booleanImageHeight;
	}
	
	/**
	 * <p>Getter for the field <code>booleanColumnStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.definition.style.DRIStyle} object.
	 */
	public DRIStyle getBooleanColumnStyle()
	{
		return this.booleanColumnStyle;
	}
	
	/**
	 * <p>Getter for the field <code>booleanHorizontalImageAlignment</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.HorizontalImageAlignment} object.
	 */
	public HorizontalImageAlignment getBooleanHorizontalImageAlignment()
	{
		return this.booleanHorizontalImageAlignment;
	}
	
	/**
	 * <p>Getter for the field <code>defaultSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getDefaultSplitType()
	{
		return this.defaultSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>titleSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getTitleSplitType()
	{
		return this.titleSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>pageHeaderSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getPageHeaderSplitType()
	{
		return this.pageHeaderSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>pageFooterSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getPageFooterSplitType()
	{
		return this.pageFooterSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>columnHeaderSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getColumnHeaderSplitType()
	{
		return this.columnHeaderSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>columnFooterSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getColumnFooterSplitType()
	{
		return this.columnFooterSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>groupHeaderSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getGroupHeaderSplitType()
	{
		return this.groupHeaderSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>groupFooterSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getGroupFooterSplitType()
	{
		return this.groupFooterSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>detailHeaderSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getDetailHeaderSplitType()
	{
		return this.detailHeaderSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>detailSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getDetailSplitType()
	{
		return this.detailSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>detailFooterSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getDetailFooterSplitType()
	{
		return this.detailFooterSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>lastPageFooterSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getLastPageFooterSplitType()
	{
		return this.lastPageFooterSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>summarySplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getSummarySplitType()
	{
		return this.summarySplitType;
	}
	
	/**
	 * <p>Getter for the field <code>noDataSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getNoDataSplitType()
	{
		return this.noDataSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>backgroundSplitType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.SplitType} object.
	 */
	public SplitType getBackgroundSplitType()
	{
		return this.backgroundSplitType;
	}
	
	/**
	 * <p>Getter for the field <code>bandStyle</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.definition.style.DRIStyle} object.
	 */
	public DRIStyle getBandStyle()
	{
		return this.bandStyle;
	}
	
	/**
	 * <p>Getter for the field <code>bandBackgroundComponent</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.definition.component.DRIComponent} object.
	 */
	public DRIComponent getBandBackgroundComponent()
	{
		return this.bandBackgroundComponent;
	}
	
	/**
	 * <p>Getter for the field <code>font</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.style.DRFont} object.
	 */
	public DRFont getFont()
	{
		return this.font;
	}
	
	/**
	 * <p>Getter for the field <code>bigDecimalType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, BigDecimal> getBigDecimalType()
	{
		return this.bigDecimalType;
	}
	
	/**
	 * <p>Getter for the field <code>bigIntegerType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, BigInteger> getBigIntegerType()
	{
		return this.bigIntegerType;
	}
	
	/**
	 * <p>Getter for the field <code>byteType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Byte> getByteType()
	{
		return this.byteType;
	}
	
	/**
	 * <p>Getter for the field <code>doubleType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Double> getDoubleType()
	{
		return this.doubleType;
	}
	
	/**
	 * <p>Getter for the field <code>floatType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Float> getFloatType()
	{
		return this.floatType;
	}
	
	/**
	 * <p>Getter for the field <code>integerType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Integer> getIntegerType()
	{
		return this.integerType;
	}
	
	/**
	 * <p>Getter for the field <code>longType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Long> getLongType()
	{
		return this.longType;
	}
	
	/**
	 * <p>Getter for the field <code>shortType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Short> getShortType()
	{
		return this.shortType;
	}
	
	/**
	 * <p>Getter for the field <code>dateType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateType()
	{
		return this.dateType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearToMonthType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearToMonthType()
	{
		return this.dateYearToMonthType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearToHourType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearToHourType()
	{
		return this.dateYearToHourType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearToMinuteType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearToMinuteType()
	{
		return this.dateYearToMinuteType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearToSecondType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearToSecondType()
	{
		return this.dateYearToSecondType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearToFractionType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearToFractionType()
	{
		return this.dateYearToFractionType;
	}
	
	/**
	 * <p>Getter for the field <code>dateYearType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateYearType()
	{
		return this.dateYearType;
	}
	
	/**
	 * <p>Getter for the field <code>dateMonthType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateMonthType()
	{
		return this.dateMonthType;
	}
	
	/**
	 * <p>Getter for the field <code>dateDayType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getDateDayType()
	{
		return this.dateDayType;
	}
	
	/**
	 * <p>Getter for the field <code>timeHourToMinuteType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getTimeHourToMinuteType()
	{
		return this.timeHourToMinuteType;
	}
	
	/**
	 * <p>Getter for the field <code>timeHourToSecondType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getTimeHourToSecondType()
	{
		return this.timeHourToSecondType;
	}
	
	/**
	 * <p>Getter for the field <code>timeHourToFractionType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Date, Date> getTimeHourToFractionType()
	{
		return this.timeHourToFractionType;
	}
	
	/**
	 * <p>Getter for the field <code>percentageType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Number, Double> getPercentageType()
	{
		return this.percentageType;
	}
	
	/**
	 * <p>Getter for the field <code>booleanType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Boolean, Boolean> getBooleanType()
	{
		return this.booleanType;
	}
	
	/**
	 * <p>Getter for the field <code>characterType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<Character, Character> getCharacterType()
	{
		return this.characterType;
	}
	
	/**
	 * <p>Getter for the field <code>stringType</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.base.datatype.DRDataType} object.
	 */
	public DRDataType<String, String> getStringType()
	{
		return this.stringType;
	}
	
	/**
	 * <p>Getter for the field <code>pageXofYHorizontalTextAlignment</code>.</p>
	 *
	 * @return a {@link net.sf.dynamicreports.report.constant.HorizontalTextAlignment} object.
	 */
	public HorizontalTextAlignment getPageXofYHorizontalTextAlignment()
	{
		return this.pageXofYHorizontalTextAlignment;
	}
	
	/**
	 * <p>isLoadSystemFonts.</p>
	 *
	 * @return a boolean.
	 */
	public boolean isLoadSystemFonts()
	{
		return this.loadSystemFonts;
	}
	
	/**
	 * <p>Setter for the field <code>loadSystemFonts</code>.</p>
	 *
	 * @param loadSystemFonts a boolean.
	 */
	protected void setLoadSystemFonts(final boolean loadSystemFonts)
	{
		this.loadSystemFonts = loadSystemFonts;
	}
}
