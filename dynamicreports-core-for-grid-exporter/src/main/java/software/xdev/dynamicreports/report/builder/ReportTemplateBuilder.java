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
package software.xdev.dynamicreports.report.builder;

import java.util.Locale;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRReportTemplate;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.style.FontBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
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
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;


public class ReportTemplateBuilder extends AbstractBuilder<ReportTemplateBuilder, DRReportTemplate>
{

	protected ReportTemplateBuilder()
	{
		super(new DRReportTemplate());
	}
	
	// template style
	
	public ReportTemplateBuilder templateStyles(final TemplateStylesBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	public ReportTemplateBuilder addTemplateStyle(final TemplateStylesBuilder... templateStyles)
	{
		Validate.notNull(templateStyles, "templateStyles must not be null");
		Validate.noNullElements(templateStyles, "templateStyles must not contains null templateStyle");
		for(final TemplateStylesBuilder templateStyle : templateStyles)
		{
			for(final StyleBuilder style : templateStyle.getStyles())
			{
				this.addTemplateStyle(style);
			}
		}
		return this;
	}
	
	public ReportTemplateBuilder templateStyles(final StyleBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	public ReportTemplateBuilder addTemplateStyle(final StyleBuilder... templateStyles)
	{
		Validate.notNull(templateStyles, "templateStyles must not be null");
		Validate.noNullElements(templateStyles, "templateStyles must not contains null templateStyle");
		for(final StyleBuilder templateStyle : templateStyles)
		{
			this.getObject().addTemplateStyle(templateStyle.build());
		}
		return this;
	}
	
	public ReportTemplateBuilder setLocale(final Locale locale)
	{
		this.getObject().setLocale(locale);
		return this;
	}
	
	public ReportTemplateBuilder setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.getObject().setShowColumnTitle(showColumnTitle);
		return this;
	}
	
	public ReportTemplateBuilder setShowColumnValues(final Boolean showColumnValues)
	{
		this.getObject().setShowColumnValues(showColumnValues);
		return this;
	}
	
	public ReportTemplateBuilder ignorePagination()
	{
		return this.setIgnorePagination(true);
	}
	
	public ReportTemplateBuilder setIgnorePagination(final Boolean ignorePagination)
	{
		this.getObject().setIgnorePagination(ignorePagination);
		return this;
	}
	
	public ReportTemplateBuilder setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.getObject().setWhenNoDataType(whenNoDataType);
		return this;
	}
	
	public ReportTemplateBuilder setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.getObject().setWhenResourceMissingType(whenResourceMissingType);
		return this;
	}
	
	public ReportTemplateBuilder titleOnANewPage()
	{
		return this.setTitleOnANewPage(true);
	}
	
	public ReportTemplateBuilder setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.getObject().setTitleOnANewPage(titleOnANewPage);
		return this;
	}
	
	public ReportTemplateBuilder summaryOnANewPage()
	{
		return this.setSummaryOnANewPage(true);
	}
	
	public ReportTemplateBuilder setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.getObject().setSummaryOnANewPage(summaryOnANewPage);
		return this;
	}
	
	public ReportTemplateBuilder summaryWithPageHeaderAndFooter()
	{
		return this.setSummaryWithPageHeaderAndFooter(true);
	}
	
	public ReportTemplateBuilder setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.getObject().setSummaryWithPageHeaderAndFooter(summaryWithPageHeaderAndFooter);
		return this;
	}
	
	public ReportTemplateBuilder floatColumnFooter()
	{
		return this.setFloatColumnFooter(true);
	}
	
	public ReportTemplateBuilder setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.getObject().setFloatColumnFooter(floatColumnFooter);
		return this;
	}
	
	public ReportTemplateBuilder setPrintOrder(final Orientation printOrder)
	{
		this.getObject().setPrintOrder(printOrder);
		return this;
	}
	
	public ReportTemplateBuilder setColumnDirection(final RunDirection columnDirection)
	{
		this.getObject().setColumnDirection(columnDirection);
		return this;
	}
	
	public ReportTemplateBuilder setLanguage(final String language)
	{
		this.getObject().setLanguage(language);
		return this;
	}
	
	public ReportTemplateBuilder setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.getObject().setUseFieldNameAsDescription(useFieldNameAsDescription);
		return this;
	}
	
	public ReportTemplateBuilder highlightDetailOddRows()
	{
		return this.setHighlightDetailOddRows(true);
	}
	
	public ReportTemplateBuilder setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.getObject().setHighlightDetailOddRows(highlightDetailOddRows);
		return this;
	}
	
	public ReportTemplateBuilder setDetailOddRowStyle(final SimpleStyleBuilder detailOddRowStyle)
	{
		if(detailOddRowStyle != null)
		{
			this.getObject().setDetailOddRowStyle(detailOddRowStyle.build());
		}
		else
		{
			this.getObject().setDetailOddRowStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder highlightDetailEvenRows()
	{
		return this.setHighlightDetailEvenRows(true);
	}
	
	public ReportTemplateBuilder setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.getObject().setHighlightDetailEvenRows(highlightDetailEvenRows);
		return this;
	}
	
	public ReportTemplateBuilder setDetailEvenRowStyle(final SimpleStyleBuilder detailEvenRowStyle)
	{
		if(detailEvenRowStyle != null)
		{
			this.getObject().setDetailEvenRowStyle(detailEvenRowStyle.build());
		}
		else
		{
			this.getObject().setDetailEvenRowStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setDefaultFont(final FontBuilder defaultFont)
	{
		if(defaultFont != null)
		{
			this.getObject().setDefaultFont(defaultFont.build());
		}
		else
		{
			this.getObject().setDefaultFont(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setTextStyle(final ReportStyleBuilder textStyle)
	{
		if(textStyle != null)
		{
			this.getObject().setTextStyle(textStyle.build());
		}
		else
		{
			this.getObject().setTextStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setColumnTitleStyle(final ReportStyleBuilder columnTitleStyle)
	{
		if(columnTitleStyle != null)
		{
			this.getObject().setColumnTitleStyle(columnTitleStyle.build());
		}
		else
		{
			this.getObject().setColumnTitleStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setColumnStyle(final ReportStyleBuilder columnStyle)
	{
		if(columnStyle != null)
		{
			this.getObject().setColumnStyle(columnStyle.build());
		}
		else
		{
			this.getObject().setColumnStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setGroupTitleStyle(final ReportStyleBuilder groupTitleStyle)
	{
		if(groupTitleStyle != null)
		{
			this.getObject().setGroupTitleStyle(groupTitleStyle.build());
		}
		else
		{
			this.getObject().setGroupTitleStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setGroupStyle(final ReportStyleBuilder groupStyle)
	{
		if(groupStyle != null)
		{
			this.getObject().setGroupStyle(groupStyle.build());
		}
		else
		{
			this.getObject().setGroupStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setSubtotalStyle(final ReportStyleBuilder subtotalStyle)
	{
		if(subtotalStyle != null)
		{
			this.getObject().setSubtotalStyle(subtotalStyle.build());
		}
		else
		{
			this.getObject().setSubtotalStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setImageStyle(final ReportStyleBuilder imageStyle)
	{
		if(imageStyle != null)
		{
			this.getObject().setImageStyle(imageStyle.build());
		}
		else
		{
			this.getObject().setImageStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setPageFormat(final PageType pageType)
	{
		return this.setPageFormat(pageType, PageOrientation.PORTRAIT);
	}
	
	public ReportTemplateBuilder setPageFormat(final PageType pageType, final PageOrientation orientation)
	{
		this.getObject().setPageFormat(pageType, orientation);
		return this;
	}
	
	public ReportTemplateBuilder setPageMargin(final MarginBuilder pageMargin)
	{
		if(pageMargin != null)
		{
			this.getObject().setPageMargin(pageMargin.build());
		}
		else
		{
			this.getObject().setPageMargin(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setPageColumnsPerPage(final Integer pageColumnsPerPage)
	{
		this.getObject().setPageColumnsPerPage(pageColumnsPerPage);
		return this;
	}
	
	public ReportTemplateBuilder setPageColumnSpace(final Integer pageColumnSpace)
	{
		this.getObject().setPageColumnSpace(pageColumnSpace);
		return this;
	}
	
	public ReportTemplateBuilder setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.getObject().setIgnorePageWidth(ignorePageWidth);
		return this;
	}
	
	public ReportTemplateBuilder setColumnPrintRepeatedDetailValues(final Boolean columnPrintRepeatedDetailValues)
	{
		this.getObject().setColumnPrintRepeatedDetailValues(columnPrintRepeatedDetailValues);
		return this;
	}
	
	public ReportTemplateBuilder setColumnWidth(final Integer columnWidth)
	{
		this.getObject().setColumnWidth(columnWidth);
		return this;
	}
	
	public ReportTemplateBuilder setGroupHeaderLayout(final GroupHeaderLayout groupHeaderLayout)
	{
		this.getObject().setGroupHeaderLayout(groupHeaderLayout);
		return this;
	}
	
	public ReportTemplateBuilder setGroupHideColumn(final Boolean groupHideColumn)
	{
		this.getObject().setGroupHideColumn(groupHideColumn);
		return this;
	}
	
	public ReportTemplateBuilder setGroupShowColumnHeaderAndFooter(final Boolean groupShowColumnHeaderAndFooter)
	{
		this.getObject().setGroupShowColumnHeaderAndFooter(groupShowColumnHeaderAndFooter);
		return this;
	}
	
	public ReportTemplateBuilder setGroupPadding(final Integer groupPadding)
	{
		this.getObject().setGroupPadding(groupPadding);
		return this;
	}
	
	public ReportTemplateBuilder setGroupStartInNewPage(final Boolean groupStartInNewPage)
	{
		this.getObject().setGroupStartInNewPage(groupStartInNewPage);
		return this;
	}
	
	public ReportTemplateBuilder setGroupStartInNewColumn(final Boolean groupStartInNewColumn)
	{
		this.getObject().setGroupStartInNewColumn(groupStartInNewColumn);
		return this;
	}
	
	public ReportTemplateBuilder setGroupReprintHeaderOnEachPage(final Boolean groupReprintHeaderOnEachPage)
	{
		this.getObject().setGroupReprintHeaderOnEachPage(groupReprintHeaderOnEachPage);
		return this;
	}
	
	public ReportTemplateBuilder setGroupResetPageNumber(final Boolean groupResetPageNumber)
	{
		this.getObject().setGroupResetPageNumber(groupResetPageNumber);
		return this;
	}
	
	public ReportTemplateBuilder setGroupFooterPosition(final GroupFooterPosition groupFooterPosition)
	{
		this.getObject().setGroupFooterPosition(groupFooterPosition);
		return this;
	}
	
	public ReportTemplateBuilder setGroupKeepTogether(final Boolean groupKeepTogether)
	{
		this.getObject().setGroupKeepTogether(groupKeepTogether);
		return this;
	}
	
	public ReportTemplateBuilder setGroupHeaderWithSubtotal(final Boolean groupHeaderWithSubtotal)
	{
		this.getObject().setGroupHeaderWithSubtotal(groupHeaderWithSubtotal);
		return this;
	}
	
	public ReportTemplateBuilder setSubtotalLabelPosition(final Position subtotalLabelPosition)
	{
		this.getObject().setSubtotalLabelPosition(subtotalLabelPosition);
		return this;
	}
	
	public ReportTemplateBuilder tableOfContents()
	{
		return this.setTableOfContents(true);
	}
	
	public ReportTemplateBuilder setTableOfContents(final Boolean tableOfContents)
	{
		this.getObject().setTableOfContents(tableOfContents);
		return this;
	}
	
	public ReportTemplateBuilder setTableOfContentsCustomizer(
		final TableOfContentsCustomizerBuilder tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer.build());
		return this;
	}
	
	public ReportTemplateBuilder setTableOfContentsCustomizer(
		final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer);
		return this;
	}
	
	public ReportTemplateBuilder setTextFieldWidth(final Integer textFieldWidth)
	{
		this.getObject().setTextFieldWidth(textFieldWidth);
		return this;
	}
	
	public ReportTemplateBuilder setImageHeight(final Integer imageHeight)
	{
		this.getObject().setImageHeight(imageHeight);
		return this;
	}
	
	public ReportTemplateBuilder setImageWidth(final Integer imageWidth)
	{
		this.getObject().setImageWidth(imageWidth);
		return this;
	}
	
	public ReportTemplateBuilder setListgap(final Integer listgap)
	{
		this.getObject().setListgap(listgap);
		return this;
	}
	
	public ReportTemplateBuilder setMultiPageListHeight(final Integer multiPageListHeight)
	{
		this.getObject().setMultiPageListHeight(multiPageListHeight);
		return this;
	}
	
	public ReportTemplateBuilder setMultiPageListWidth(final Integer multiPageListWidth)
	{
		this.getObject().setMultiPageListWidth(multiPageListWidth);
		return this;
	}
	
	public ReportTemplateBuilder setSubreportHeight(final Integer subreportHeight)
	{
		this.getObject().setSubreportHeight(subreportHeight);
		return this;
	}
	
	public ReportTemplateBuilder setSubreportWidth(final Integer subreportWidth)
	{
		this.getObject().setSubreportWidth(subreportWidth);
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabHeight(final Integer crosstabHeight)
	{
		this.getObject().setCrosstabHeight(crosstabHeight);
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabWidth(final Integer crosstabWidth)
	{
		this.getObject().setCrosstabWidth(crosstabWidth);
		return this;
	}
	
	public ReportTemplateBuilder crosstabHighlightOddRows()
	{
		return this.setCrosstabHighlightOddRows(true);
	}
	
	public ReportTemplateBuilder setCrosstabHighlightOddRows(final Boolean crosstabHighlightOddRows)
	{
		this.getObject().setCrosstabHighlightOddRows(crosstabHighlightOddRows);
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabOddRowStyle(final SimpleStyleBuilder crosstabOddRowStyle)
	{
		if(crosstabOddRowStyle != null)
		{
			this.getObject().setCrosstabOddRowStyle(crosstabOddRowStyle.build());
		}
		else
		{
			this.getObject().setCrosstabOddRowStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder crosstabHighlightEvenRows()
	{
		return this.setCrosstabHighlightEvenRows(true);
	}
	
	public ReportTemplateBuilder setCrosstabHighlightEvenRows(final Boolean crosstabHighlightEvenRows)
	{
		this.getObject().setCrosstabHighlightEvenRows(crosstabHighlightEvenRows);
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabEvenRowStyle(final SimpleStyleBuilder crosstabEvenRowStyle)
	{
		if(crosstabEvenRowStyle != null)
		{
			this.getObject().setCrosstabEvenRowStyle(crosstabEvenRowStyle.build());
		}
		else
		{
			this.getObject().setCrosstabEvenRowStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabGroupStyle(final ReportStyleBuilder crosstabGroupStyle)
	{
		if(crosstabGroupStyle != null)
		{
			this.getObject().setCrosstabGroupStyle(crosstabGroupStyle.build());
		}
		else
		{
			this.getObject().setCrosstabGroupStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabGroupTotalStyle(final ReportStyleBuilder crosstabGroupTotalStyle)
	{
		if(crosstabGroupTotalStyle != null)
		{
			this.getObject().setCrosstabGroupTotalStyle(crosstabGroupTotalStyle.build());
		}
		else
		{
			this.getObject().setCrosstabGroupTotalStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabGrandTotalStyle(final ReportStyleBuilder crosstabGrandTotalStyle)
	{
		if(crosstabGrandTotalStyle != null)
		{
			this.getObject().setCrosstabGrandTotalStyle(crosstabGrandTotalStyle.build());
		}
		else
		{
			this.getObject().setCrosstabGrandTotalStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabCellStyle(final ReportStyleBuilder crosstabCellStyle)
	{
		if(crosstabCellStyle != null)
		{
			this.getObject().setCrosstabCellStyle(crosstabCellStyle.build());
		}
		else
		{
			this.getObject().setCrosstabCellStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setCrosstabMeasureTitleStyle(final ReportStyleBuilder crosstabMeasureTitleStyle)
	{
		if(crosstabMeasureTitleStyle != null)
		{
			this.getObject().setCrosstabMeasureTitleStyle(crosstabMeasureTitleStyle.build());
		}
		else
		{
			this.getObject().setCrosstabMeasureTitleStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setBooleanComponentType(final BooleanComponentType booleanComponentType)
	{
		this.getObject().setBooleanComponentType(booleanComponentType);
		return this;
	}
	
	public ReportTemplateBuilder setBooleanEmptyWhenNullValue(final Boolean booleanEmptyWhenNullValue)
	{
		this.getObject().setBooleanEmptyWhenNullValue(booleanEmptyWhenNullValue);
		return this;
	}
	
	public ReportTemplateBuilder setBooleanImageWidth(final Integer booleanImageWidth)
	{
		this.getObject().setBooleanImageWidth(booleanImageWidth);
		return this;
	}
	
	public ReportTemplateBuilder setBooleanImageHeight(final Integer booleanImageHeight)
	{
		this.getObject().setBooleanImageHeight(booleanImageHeight);
		return this;
	}
	
	public ReportTemplateBuilder setBooleanColumnStyle(final ReportStyleBuilder cooleanColumnStyle)
	{
		if(cooleanColumnStyle != null)
		{
			this.getObject().setBooleanColumnStyle(cooleanColumnStyle.build());
		}
		else
		{
			this.getObject().setBooleanColumnStyle(null);
		}
		return this;
	}
	
	// band split type
	
	public ReportTemplateBuilder setDefaultSplitType(final SplitType defaultSplitType)
	{
		this.getObject().setDefaultSplitType(defaultSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setTitleSplitType(final SplitType titleSplitType)
	{
		this.getObject().setTitleSplitType(titleSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setPageHeaderSplitType(final SplitType pageHeaderSplitType)
	{
		this.getObject().setPageHeaderSplitType(pageHeaderSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setPageFooterSplitType(final SplitType pageFooterSplitType)
	{
		this.getObject().setPageFooterSplitType(pageFooterSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setColumnHeaderSplitType(final SplitType columnHeaderSplitType)
	{
		this.getObject().setColumnHeaderSplitType(columnHeaderSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setColumnFooterSplitType(final SplitType columnFooterSplitType)
	{
		this.getObject().setColumnFooterSplitType(columnFooterSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setGroupHeaderSplitType(final SplitType groupHeaderSplitType)
	{
		this.getObject().setGroupHeaderSplitType(groupHeaderSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setGroupFooterSplitType(final SplitType groupFooterSplitType)
	{
		this.getObject().setGroupFooterSplitType(groupFooterSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setDetailHeaderSplitType(final SplitType detailHeaderSplitType)
	{
		this.getObject().setDetailHeaderSplitType(detailHeaderSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setDetailSplitType(final SplitType detailSplitType)
	{
		this.getObject().setDetailSplitType(detailSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setDetailFooterSplitType(final SplitType detailFooterSplitType)
	{
		this.getObject().setDetailFooterSplitType(detailFooterSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setLastPageFooterSplitType(final SplitType lastPageFooterSplitType)
	{
		this.getObject().setLastPageFooterSplitType(lastPageFooterSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setSummarySplitType(final SplitType summarySplitType)
	{
		this.getObject().setSummarySplitType(summarySplitType);
		return this;
	}
	
	public ReportTemplateBuilder setNoDataSplitType(final SplitType noDataSplitType)
	{
		this.getObject().setNoDataSplitType(noDataSplitType);
		return this;
	}
	
	public ReportTemplateBuilder setBackgroundSplitType(final SplitType backgroundSplitType)
	{
		this.getObject().setBackgroundSplitType(backgroundSplitType);
		return this;
	}
	
	// band style
	
	public ReportTemplateBuilder setTitleStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setTitleStyle(style.build());
		}
		else
		{
			this.getObject().setTitleStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setPageHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setPageHeaderStyle(style.build());
		}
		else
		{
			this.getObject().setPageHeaderStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setPageFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setPageFooterStyle(style.build());
		}
		else
		{
			this.getObject().setPageFooterStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setColumnHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setColumnHeaderStyle(style.build());
		}
		else
		{
			this.getObject().setColumnHeaderStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setColumnFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setColumnFooterStyle(style.build());
		}
		else
		{
			this.getObject().setColumnFooterStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setGroupHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setGroupHeaderStyle(style.build());
		}
		else
		{
			this.getObject().setGroupHeaderStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setGroupFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setGroupFooterStyle(style.build());
		}
		else
		{
			this.getObject().setGroupFooterStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setDetailHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setDetailHeaderStyle(style.build());
		}
		else
		{
			this.getObject().setDetailHeaderStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setDetailStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setDetailStyle(style.build());
		}
		else
		{
			this.getObject().setDetailStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setDetailFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setDetailFooterStyle(style.build());
		}
		else
		{
			this.getObject().setDetailFooterStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setLastPageFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setLastPageFooterStyle(style.build());
		}
		else
		{
			this.getObject().setLastPageFooterStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setSummaryStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setSummaryStyle(style.build());
		}
		else
		{
			this.getObject().setSummaryStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setNoDataStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setNoDataStyle(style.build());
		}
		else
		{
			this.getObject().setNoDataStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setBackgroundStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setBackgroundStyle(style.build());
		}
		else
		{
			this.getObject().setBackgroundStyle(null);
		}
		return this;
	}
	
	public ReportTemplateBuilder setTitleBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setTitleBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setPageHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setPageHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setPageFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setColumnHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setColumnHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setColumnFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setColumnFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setGroupHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setGroupHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setGroupFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setGroupFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setDetailHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setDetailBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setDetailFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setLastPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setLastPageFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setSummaryBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setSummaryBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setNoDataBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setNoDataBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public ReportTemplateBuilder setBackgroundBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setBackgroundBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	public DRReportTemplate getReportTemplate()
	{
		return this.build();
	}
}
