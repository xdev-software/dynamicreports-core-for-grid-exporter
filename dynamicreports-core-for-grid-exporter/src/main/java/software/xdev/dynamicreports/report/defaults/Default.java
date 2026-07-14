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
package software.xdev.dynamicreports.report.defaults;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Locale;

import software.xdev.dynamicreports.report.base.DRMargin;
import software.xdev.dynamicreports.report.base.datatype.DRDataType;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.base.style.DRPadding;
import software.xdev.dynamicreports.report.base.style.DRSimpleStyle;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.Language;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;



public class Default
{
	protected String reportName;
	protected Locale locale;
	protected boolean showColumnTitle;
	protected boolean showColumnValues;
	protected boolean ignorePagination;
	protected WhenNoDataType whenNoDataType;
	protected WhenResourceMissingType whenResourceMissingType;
	protected boolean titleOnANewPage;
	protected boolean summaryOnANewPage;
	protected boolean summaryWithPageHeaderAndFooter;
	protected boolean floatColumnFooter;
	protected Orientation printOrder;
	protected RunDirection columnDirection;
	protected String language;
	protected boolean useFieldNameAsDescription;
	// style
	protected boolean highlightDetailOddRows;
	protected DRSimpleStyle detailOddRowStyle;
	protected boolean highlightDetailEvenRows;
	protected DRSimpleStyle detailEvenRowStyle;
	protected DRStyle textStyle;
	protected DRStyle columnTitleStyle;
	protected DRStyle columnStyle;
	protected DRStyle groupTitleStyle;
	protected DRStyle groupStyle;
	protected DRStyle subtotalStyle;
	protected DRStyle imageStyle;
	// page
	protected int pageWidth;
	protected int pageHeight;
	protected PageOrientation pageOrientation;
	protected DRMargin pageMargin;
	protected DRMargin subreportPageMargin;
	protected int pageColumnsPerPage;
	protected int pageColumnSpace;
	protected boolean ignorePageWidth;
	// column
	protected boolean columnPrintRepeatedDetailValues;
	protected int columnWidth;
	// component
	protected boolean removeLineWhenBlank;
	protected boolean printInFirstWholeBand;
	protected boolean printWhenDetailOverflows;
	// group
	protected GroupHeaderLayout groupHeaderLayout;
	protected boolean groupHideColumn;
	protected boolean groupShowColumnHeaderAndFooter;
	protected int groupPadding;
	protected boolean groupStartInNewPage;
	protected boolean groupStartInNewColumn;
	protected boolean groupReprintHeaderOnEachPage;
	protected boolean groupResetPageNumber;
	protected Integer groupMinHeightToStartNewPage;
	protected GroupFooterPosition groupFooterPosition;
	protected boolean groupKeepTogether;
	protected boolean groupHeaderWithSubtotal;
	protected boolean groupByDataType;
	// subtotal
	protected Position subtotalLabelPosition;
	// table of contents
	protected boolean tableOfContents;
	protected DRITableOfContentsCustomizer tableOfContentsCustomizer;
	protected boolean addGroupToTableOfContents;
	protected TableOfContentsPosition tableOfContentsPosition;
	// text field
	protected int textFieldWidth;
	protected boolean textFieldPrintRepeatedValues;
	protected TextAdjust textFieldTextAdjust;
	// image
	protected int imageWidth;
	protected int imageHeight;
	// filler
	protected int fillerWidth;
	protected int fillerHeight;
	// line
	protected int lineWidth;
	protected int lineHeight;
	// ellipse
	protected int ellipseWidth;
	protected int ellipseHeight;
	// rectangle
	protected Integer rectangleRadius;
	protected int rectangleWidth;
	protected int rectangleHeight;
	// map
	protected int mapWidth;
	protected int mapHeight;
	// custom component
	protected int customComponentWidth;
	protected int customComponentHeight;
	// break
	protected int breakWidth;
	protected int breakHeight;
	// generic element
	protected int genericElementWidth;
	protected int genericElementHeight;
	// list
	protected Integer listWidth;
	protected Integer listHeight;
	protected int listgap;
	protected HorizontalCellComponentAlignment horizontalCellComponentAlignment;
	protected VerticalCellComponentAlignment verticalCellComponentAlignment;
	// multi page list
	protected int multiPageListWidth;
	protected int multiPageListHeight;
	// subreport
	protected int subreportWidth;
	protected int subreportHeight;
	// crosstab
	protected int crosstabWidth;
	protected int crosstabHeight;
	protected CrosstabTotalPosition crosstabColumnGroupTotalPosition;
	protected CrosstabTotalPosition crosstabRowGroupTotalPosition;
	protected boolean crosstabColumnGroupShowTotal;
	protected boolean crosstabRowGroupShowTotal;
	protected int crosstabColumnGroupTotalHeaderMaxWidth;
	protected int crosstabRowGroupHeaderMaxWidth;
	protected int crosstabCellMaxWidth;
	protected boolean crosstabHighlightOddRows;
	protected DRSimpleStyle crosstabOddRowStyle;
	protected boolean crosstabHighlightEvenRows;
	protected DRSimpleStyle crosstabEvenRowStyle;
	protected DRStyle crosstabGroupStyle;
	protected DRStyle crosstabGroupTotalStyle;
	protected DRStyle crosstabGrandTotalStyle;
	protected DRStyle crosstabCellStyle;
	protected DRStyle crosstabMeasureTitleStyle;
	protected CrosstabPercentageType crosstabPercentageType;
	// boolean
	protected BooleanComponentType booleanComponentType;
	protected boolean booleanEmptyWhenNullValue;
	protected int booleanImageWidth;
	protected int booleanImageHeight;
	protected DRStyle booleanColumnStyle;
	protected HorizontalImageAlignment booleanHorizontalImageAlignment;
	// split
	protected SplitType defaultSplitType;
	protected SplitType titleSplitType;
	protected SplitType pageHeaderSplitType;
	protected SplitType pageFooterSplitType;
	protected SplitType columnHeaderSplitType;
	protected SplitType columnFooterSplitType;
	protected SplitType groupHeaderSplitType;
	protected SplitType groupFooterSplitType;
	protected SplitType detailHeaderSplitType;
	protected SplitType detailSplitType;
	protected SplitType detailFooterSplitType;
	protected SplitType lastPageFooterSplitType;
	protected SplitType summarySplitType;
	protected SplitType noDataSplitType;
	protected SplitType backgroundSplitType;
	// band style
	protected DRIStyle bandStyle;
	// band background component
	protected DRIComponent bandBackgroundComponent;
	// font
	protected DRFont font;
	// datatype
	protected DRDataType<Number, BigDecimal> bigDecimalType;
	protected DRDataType<Number, BigInteger> bigIntegerType;
	protected DRDataType<Number, Byte> byteType;
	protected DRDataType<Number, Double> doubleType;
	protected DRDataType<Number, Float> floatType;
	protected DRDataType<Number, Integer> integerType;
	protected DRDataType<Number, Long> longType;
	protected DRDataType<Number, Short> shortType;
	protected DRDataType<Date, Date> dateType;
	protected DRDataType<Date, Date> dateYearToMonthType;
	protected DRDataType<Date, Date> dateYearToHourType;
	protected DRDataType<Date, Date> dateYearToMinuteType;
	protected DRDataType<Date, Date> dateYearToSecondType;
	protected DRDataType<Date, Date> dateYearToFractionType;
	protected DRDataType<Date, Date> dateYearType;
	protected DRDataType<Date, Date> dateMonthType;
	protected DRDataType<Date, Date> dateDayType;
	protected DRDataType<Date, Date> timeHourToMinuteType;
	protected DRDataType<Date, Date> timeHourToSecondType;
	protected DRDataType<Date, Date> timeHourToFractionType;
	protected DRDataType<Number, Double> percentageType;
	protected DRDataType<Boolean, Boolean> booleanType;
	protected DRDataType<Character, Character> characterType;
	protected DRDataType<String, String> stringType;
	protected HorizontalTextAlignment pageXofYHorizontalTextAlignment;
	// system fonts
	protected boolean loadSystemFonts;
	
	public Default()
	{
		this.init();
	}
	
	@SuppressWarnings({"checkstyle:MethodLength", "checkstyle:MagicNumber"})
	protected void init()
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
	
	public String getReportName()
	{
		return this.reportName;
	}
	
	public Locale getLocale()
	{
		return this.locale;
	}
	
	public boolean isShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	public boolean isShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	public boolean isIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	public boolean isTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	public boolean isSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	public boolean isSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	public boolean isFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	public String getLanguage()
	{
		return this.language;
	}
	
	public boolean isUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	public boolean isHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	public boolean isHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	public DRStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	public DRStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	public DRStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	public DRStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	public DRStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	public DRStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	public DRStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	public int getPageWidth()
	{
		return this.pageWidth;
	}
	
	public int getPageHeight()
	{
		return this.pageHeight;
	}
	
	public PageOrientation getPageOrientation()
	{
		return this.pageOrientation;
	}
	
	public DRMargin getPageMargin()
	{
		return this.pageMargin;
	}
	
	public DRMargin getSubreportPageMargin()
	{
		return this.subreportPageMargin;
	}
	
	public int getPageColumnsPerPage()
	{
		return this.pageColumnsPerPage;
	}
	
	public int getPageColumnSpace()
	{
		return this.pageColumnSpace;
	}
	
	public boolean isIgnorePageWidth()
	{
		return this.ignorePageWidth;
	}
	
	public boolean isColumnPrintRepeatedDetailValues()
	{
		return this.columnPrintRepeatedDetailValues;
	}
	
	public int getColumnWidth()
	{
		return this.columnWidth;
	}
	
	public boolean isRemoveLineWhenBlank()
	{
		return this.removeLineWhenBlank;
	}
	
	public boolean isPrintInFirstWholeBand()
	{
		return this.printInFirstWholeBand;
	}
	
	public boolean isPrintWhenDetailOverflows()
	{
		return this.printWhenDetailOverflows;
	}
	
	public GroupHeaderLayout getGroupHeaderLayout()
	{
		return this.groupHeaderLayout;
	}
	
	public boolean isGroupHideColumn()
	{
		return this.groupHideColumn;
	}
	
	public boolean isGroupShowColumnHeaderAndFooter()
	{
		return this.groupShowColumnHeaderAndFooter;
	}
	
	public int getGroupPadding()
	{
		return this.groupPadding;
	}
	
	public boolean isGroupStartInNewPage()
	{
		return this.groupStartInNewPage;
	}
	
	public boolean isGroupStartInNewColumn()
	{
		return this.groupStartInNewColumn;
	}
	
	public boolean isGroupReprintHeaderOnEachPage()
	{
		return this.groupReprintHeaderOnEachPage;
	}
	
	public boolean isGroupResetPageNumber()
	{
		return this.groupResetPageNumber;
	}
	
	public Integer getGroupMinHeightToStartNewPage()
	{
		return this.groupMinHeightToStartNewPage;
	}
	
	public GroupFooterPosition getGroupFooterPosition()
	{
		return this.groupFooterPosition;
	}
	
	public boolean isGroupKeepTogether()
	{
		return this.groupKeepTogether;
	}
	
	public boolean isGroupHeaderWithSubtotal()
	{
		return this.groupHeaderWithSubtotal;
	}
	
	public boolean isGroupByDataType()
	{
		return this.groupByDataType;
	}
	
	public Position getSubtotalLabelPosition()
	{
		return this.subtotalLabelPosition;
	}
	
	public boolean isTableOfContents()
	{
		return this.tableOfContents;
	}
	
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	public boolean isAddGroupToTableOfContents()
	{
		return this.addGroupToTableOfContents;
	}
	
	public TableOfContentsPosition getTableOfContentsPosition()
	{
		return this.tableOfContentsPosition;
	}
	
	public int getTextFieldWidth()
	{
		return this.textFieldWidth;
	}
	
	public boolean isTextFieldPrintRepeatedValues()
	{
		return this.textFieldPrintRepeatedValues;
	}
	
	public TextAdjust getTextFieldTextAdjust()
	{
		return this.textFieldTextAdjust;
	}
	
	public int getImageWidth()
	{
		return this.imageWidth;
	}
	
	public int getImageHeight()
	{
		return this.imageHeight;
	}
	
	public int getFillerWidth()
	{
		return this.fillerWidth;
	}
	
	public int getFillerHeight()
	{
		return this.fillerHeight;
	}
	
	public int getLineWidth()
	{
		return this.lineWidth;
	}
	
	public int getLineHeight()
	{
		return this.lineHeight;
	}
	
	public int getEllipseWidth()
	{
		return this.ellipseWidth;
	}
	
	public int getEllipseHeight()
	{
		return this.ellipseHeight;
	}
	
	public Integer getRectangleRadius()
	{
		return this.rectangleRadius;
	}
	
	public int getRectangleWidth()
	{
		return this.rectangleWidth;
	}
	
	public int getRectangleHeight()
	{
		return this.rectangleHeight;
	}
	
	public int getMapWidth()
	{
		return this.mapWidth;
	}
	
	public int getMapHeight()
	{
		return this.mapHeight;
	}
	
	public int getCustomComponentWidth()
	{
		return this.customComponentWidth;
	}
	
	public int getCustomComponentHeight()
	{
		return this.customComponentHeight;
	}
	
	public int getBreakWidth()
	{
		return this.breakWidth;
	}
	
	public int getBreakHeight()
	{
		return this.breakHeight;
	}
	
	public Integer getGenericElementWidth()
	{
		return this.genericElementWidth;
	}
	
	public Integer getGenericElementHeight()
	{
		return this.genericElementHeight;
	}
	
	public Integer getListWidth()
	{
		return this.listWidth;
	}
	
	public Integer getListHeight()
	{
		return this.listHeight;
	}
	
	public int getListgap()
	{
		return this.listgap;
	}
	
	public HorizontalCellComponentAlignment getHorizontalCellComponentAlignment()
	{
		return this.horizontalCellComponentAlignment;
	}
	
	public VerticalCellComponentAlignment getVerticalCellComponentAlignment()
	{
		return this.verticalCellComponentAlignment;
	}
	
	public int getMultiPageListWidth()
	{
		return this.multiPageListWidth;
	}
	
	public int getMultiPageListHeight()
	{
		return this.multiPageListHeight;
	}
	
	public int getSubreportWidth()
	{
		return this.subreportWidth;
	}
	
	public int getSubreportHeight()
	{
		return this.subreportHeight;
	}
	
	public int getCrosstabWidth()
	{
		return this.crosstabWidth;
	}
	
	public int getCrosstabHeight()
	{
		return this.crosstabHeight;
	}
	
	public CrosstabTotalPosition getCrosstabColumnGroupTotalPosition()
	{
		return this.crosstabColumnGroupTotalPosition;
	}
	
	public CrosstabTotalPosition getCrosstabRowGroupTotalPosition()
	{
		return this.crosstabRowGroupTotalPosition;
	}
	
	public boolean isCrosstabColumnGroupShowTotal()
	{
		return this.crosstabColumnGroupShowTotal;
	}
	
	public boolean isCrosstabRowGroupShowTotal()
	{
		return this.crosstabRowGroupShowTotal;
	}
	
	public int getCrosstabColumnGroupTotalHeaderMaxWidth()
	{
		return this.crosstabColumnGroupTotalHeaderMaxWidth;
	}
	
	public int getCrosstabRowGroupHeaderMaxWidth()
	{
		return this.crosstabRowGroupHeaderMaxWidth;
	}
	
	public int getCrosstabCellMaxWidth()
	{
		return this.crosstabCellMaxWidth;
	}
	
	public DRSimpleStyle getCrosstabOddRowStyle()
	{
		return this.crosstabOddRowStyle;
	}
	
	public DRSimpleStyle getCrosstabEvenRowStyle()
	{
		return this.crosstabEvenRowStyle;
	}
	
	public boolean isCrosstabHighlightOddRows()
	{
		return this.crosstabHighlightOddRows;
	}
	
	public boolean isCrosstabHighlightEvenRows()
	{
		return this.crosstabHighlightEvenRows;
	}
	
	public DRStyle getCrosstabGroupStyle()
	{
		return this.crosstabGroupStyle;
	}
	
	public DRStyle getCrosstabGroupTotalStyle()
	{
		return this.crosstabGroupTotalStyle;
	}
	
	public DRStyle getCrosstabGrandTotalStyle()
	{
		return this.crosstabGrandTotalStyle;
	}
	
	public DRStyle getCrosstabCellStyle()
	{
		return this.crosstabCellStyle;
	}
	
	public DRStyle getCrosstabMeasureTitleStyle()
	{
		return this.crosstabMeasureTitleStyle;
	}
	
	public CrosstabPercentageType getCrosstabPercentageType()
	{
		return this.crosstabPercentageType;
	}
	
	public BooleanComponentType getBooleanComponentType()
	{
		return this.booleanComponentType;
	}
	
	public boolean isBooleanEmptyWhenNullValue()
	{
		return this.booleanEmptyWhenNullValue;
	}
	
	public int getBooleanImageWidth()
	{
		return this.booleanImageWidth;
	}
	
	public int getBooleanImageHeight()
	{
		return this.booleanImageHeight;
	}
	
	public DRIStyle getBooleanColumnStyle()
	{
		return this.booleanColumnStyle;
	}
	
	public HorizontalImageAlignment getBooleanHorizontalImageAlignment()
	{
		return this.booleanHorizontalImageAlignment;
	}
	
	public SplitType getDefaultSplitType()
	{
		return this.defaultSplitType;
	}
	
	public SplitType getTitleSplitType()
	{
		return this.titleSplitType;
	}
	
	public SplitType getPageHeaderSplitType()
	{
		return this.pageHeaderSplitType;
	}
	
	public SplitType getPageFooterSplitType()
	{
		return this.pageFooterSplitType;
	}
	
	public SplitType getColumnHeaderSplitType()
	{
		return this.columnHeaderSplitType;
	}
	
	public SplitType getColumnFooterSplitType()
	{
		return this.columnFooterSplitType;
	}
	
	public SplitType getGroupHeaderSplitType()
	{
		return this.groupHeaderSplitType;
	}
	
	public SplitType getGroupFooterSplitType()
	{
		return this.groupFooterSplitType;
	}
	
	public SplitType getDetailHeaderSplitType()
	{
		return this.detailHeaderSplitType;
	}
	
	public SplitType getDetailSplitType()
	{
		return this.detailSplitType;
	}
	
	public SplitType getDetailFooterSplitType()
	{
		return this.detailFooterSplitType;
	}
	
	public SplitType getLastPageFooterSplitType()
	{
		return this.lastPageFooterSplitType;
	}
	
	public SplitType getSummarySplitType()
	{
		return this.summarySplitType;
	}
	
	public SplitType getNoDataSplitType()
	{
		return this.noDataSplitType;
	}
	
	public SplitType getBackgroundSplitType()
	{
		return this.backgroundSplitType;
	}
	
	public DRIStyle getBandStyle()
	{
		return this.bandStyle;
	}
	
	public DRIComponent getBandBackgroundComponent()
	{
		return this.bandBackgroundComponent;
	}
	
	public DRFont getFont()
	{
		return this.font;
	}
	
	public DRDataType<Number, BigDecimal> getBigDecimalType()
	{
		return this.bigDecimalType;
	}
	
	public DRDataType<Number, BigInteger> getBigIntegerType()
	{
		return this.bigIntegerType;
	}
	
	public DRDataType<Number, Byte> getByteType()
	{
		return this.byteType;
	}
	
	public DRDataType<Number, Double> getDoubleType()
	{
		return this.doubleType;
	}
	
	public DRDataType<Number, Float> getFloatType()
	{
		return this.floatType;
	}
	
	public DRDataType<Number, Integer> getIntegerType()
	{
		return this.integerType;
	}
	
	public DRDataType<Number, Long> getLongType()
	{
		return this.longType;
	}
	
	public DRDataType<Number, Short> getShortType()
	{
		return this.shortType;
	}
	
	public DRDataType<Date, Date> getDateType()
	{
		return this.dateType;
	}
	
	public DRDataType<Date, Date> getDateYearToMonthType()
	{
		return this.dateYearToMonthType;
	}
	
	public DRDataType<Date, Date> getDateYearToHourType()
	{
		return this.dateYearToHourType;
	}
	
	public DRDataType<Date, Date> getDateYearToMinuteType()
	{
		return this.dateYearToMinuteType;
	}
	
	public DRDataType<Date, Date> getDateYearToSecondType()
	{
		return this.dateYearToSecondType;
	}
	
	public DRDataType<Date, Date> getDateYearToFractionType()
	{
		return this.dateYearToFractionType;
	}
	
	public DRDataType<Date, Date> getDateYearType()
	{
		return this.dateYearType;
	}
	
	public DRDataType<Date, Date> getDateMonthType()
	{
		return this.dateMonthType;
	}
	
	public DRDataType<Date, Date> getDateDayType()
	{
		return this.dateDayType;
	}
	
	public DRDataType<Date, Date> getTimeHourToMinuteType()
	{
		return this.timeHourToMinuteType;
	}
	
	public DRDataType<Date, Date> getTimeHourToSecondType()
	{
		return this.timeHourToSecondType;
	}
	
	public DRDataType<Date, Date> getTimeHourToFractionType()
	{
		return this.timeHourToFractionType;
	}
	
	public DRDataType<Number, Double> getPercentageType()
	{
		return this.percentageType;
	}
	
	public DRDataType<Boolean, Boolean> getBooleanType()
	{
		return this.booleanType;
	}
	
	public DRDataType<Character, Character> getCharacterType()
	{
		return this.characterType;
	}
	
	public DRDataType<String, String> getStringType()
	{
		return this.stringType;
	}
	
	public HorizontalTextAlignment getPageXofYHorizontalTextAlignment()
	{
		return this.pageXofYHorizontalTextAlignment;
	}
	
	public boolean isLoadSystemFonts()
	{
		return this.loadSystemFonts;
	}
	
	protected void setLoadSystemFonts(final boolean loadSystemFonts)
	{
		this.loadSystemFonts = loadSystemFonts;
	}
}
