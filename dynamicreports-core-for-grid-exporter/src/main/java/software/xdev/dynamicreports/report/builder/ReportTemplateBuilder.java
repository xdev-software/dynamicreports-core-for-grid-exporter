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
import software.xdev.dynamicreports.report.constant.Constants;
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


/**
 * <p>ReportTemplateBuilder class.</p>
 *
 * @author Ricardo Mariaca
 */
public class ReportTemplateBuilder extends AbstractBuilder<ReportTemplateBuilder, DRReportTemplate>
{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	/**
	 * <p>Constructor for ReportTemplateBuilder.</p>
	 */
	protected ReportTemplateBuilder()
	{
		super(new DRReportTemplate());
	}
	
	// template style
	
	/**
	 * <p>templateStyles.</p>
	 *
	 * @param templateStyles a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder templateStyles(final TemplateStylesBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	/**
	 * <p>addTemplateStyle.</p>
	 *
	 * @param templateStyles a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>templateStyles.</p>
	 *
	 * @param templateStyles a {@link software.xdev.dynamicreports.report.builder.style.StyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder templateStyles(final StyleBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	/**
	 * <p>addTemplateStyle.</p>
	 *
	 * @param templateStyles a {@link software.xdev.dynamicreports.report.builder.style.StyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setLocale.</p>
	 *
	 * @param locale a {@link java.util.Locale} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setLocale(final Locale locale)
	{
		this.getObject().setLocale(locale);
		return this;
	}
	
	/**
	 * <p>setShowColumnTitle.</p>
	 *
	 * @param showColumnTitle a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.getObject().setShowColumnTitle(showColumnTitle);
		return this;
	}
	
	/**
	 * <p>setShowColumnValues.</p>
	 *
	 * @param showColumnValues a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setShowColumnValues(final Boolean showColumnValues)
	{
		this.getObject().setShowColumnValues(showColumnValues);
		return this;
	}
	
	/**
	 * <p>ignorePagination.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder ignorePagination()
	{
		return this.setIgnorePagination(true);
	}
	
	/**
	 * <p>setIgnorePagination.</p>
	 *
	 * @param ignorePagination a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setIgnorePagination(final Boolean ignorePagination)
	{
		this.getObject().setIgnorePagination(ignorePagination);
		return this;
	}
	
	/**
	 * <p>setWhenNoDataType.</p>
	 *
	 * @param whenNoDataType a {@link software.xdev.dynamicreports.report.constant.WhenNoDataType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.getObject().setWhenNoDataType(whenNoDataType);
		return this;
	}
	
	/**
	 * <p>setWhenResourceMissingType.</p>
	 *
	 * @param whenResourceMissingType a {@link software.xdev.dynamicreports.report.constant.WhenResourceMissingType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.getObject().setWhenResourceMissingType(whenResourceMissingType);
		return this;
	}
	
	/**
	 * <p>titleOnANewPage.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder titleOnANewPage()
	{
		return this.setTitleOnANewPage(true);
	}
	
	/**
	 * <p>setTitleOnANewPage.</p>
	 *
	 * @param titleOnANewPage a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.getObject().setTitleOnANewPage(titleOnANewPage);
		return this;
	}
	
	/**
	 * <p>summaryOnANewPage.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder summaryOnANewPage()
	{
		return this.setSummaryOnANewPage(true);
	}
	
	/**
	 * <p>setSummaryOnANewPage.</p>
	 *
	 * @param summaryOnANewPage a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.getObject().setSummaryOnANewPage(summaryOnANewPage);
		return this;
	}
	
	/**
	 * <p>summaryWithPageHeaderAndFooter.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder summaryWithPageHeaderAndFooter()
	{
		return this.setSummaryWithPageHeaderAndFooter(true);
	}
	
	/**
	 * <p>setSummaryWithPageHeaderAndFooter.</p>
	 *
	 * @param summaryWithPageHeaderAndFooter a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.getObject().setSummaryWithPageHeaderAndFooter(summaryWithPageHeaderAndFooter);
		return this;
	}
	
	/**
	 * <p>floatColumnFooter.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder floatColumnFooter()
	{
		return this.setFloatColumnFooter(true);
	}
	
	/**
	 * <p>setFloatColumnFooter.</p>
	 *
	 * @param floatColumnFooter a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.getObject().setFloatColumnFooter(floatColumnFooter);
		return this;
	}
	
	/**
	 * <p>setPrintOrder.</p>
	 *
	 * @param printOrder a {@link software.xdev.dynamicreports.report.constant.Orientation} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPrintOrder(final Orientation printOrder)
	{
		this.getObject().setPrintOrder(printOrder);
		return this;
	}
	
	/**
	 * <p>setColumnDirection.</p>
	 *
	 * @param columnDirection a {@link software.xdev.dynamicreports.report.constant.RunDirection} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnDirection(final RunDirection columnDirection)
	{
		this.getObject().setColumnDirection(columnDirection);
		return this;
	}
	
	/**
	 * <p>setLanguage.</p>
	 *
	 * @param language a {@link java.lang.String} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setLanguage(final String language)
	{
		this.getObject().setLanguage(language);
		return this;
	}
	
	/**
	 * <p>setUseFieldNameAsDescription.</p>
	 *
	 * @param useFieldNameAsDescription a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.getObject().setUseFieldNameAsDescription(useFieldNameAsDescription);
		return this;
	}
	
	/**
	 * <p>highlightDetailOddRows.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder highlightDetailOddRows()
	{
		return this.setHighlightDetailOddRows(true);
	}
	
	/**
	 * <p>setHighlightDetailOddRows.</p>
	 *
	 * @param highlightDetailOddRows a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.getObject().setHighlightDetailOddRows(highlightDetailOddRows);
		return this;
	}
	
	/**
	 * <p>setDetailOddRowStyle.</p>
	 *
	 * @param detailOddRowStyle a {@link software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>highlightDetailEvenRows.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder highlightDetailEvenRows()
	{
		return this.setHighlightDetailEvenRows(true);
	}
	
	/**
	 * <p>setHighlightDetailEvenRows.</p>
	 *
	 * @param highlightDetailEvenRows a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.getObject().setHighlightDetailEvenRows(highlightDetailEvenRows);
		return this;
	}
	
	/**
	 * <p>setDetailEvenRowStyle.</p>
	 *
	 * @param detailEvenRowStyle a {@link software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setDefaultFont.</p>
	 *
	 * @param defaultFont a {@link software.xdev.dynamicreports.report.builder.style.FontBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setTextStyle.</p>
	 *
	 * @param textStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setColumnTitleStyle.</p>
	 *
	 * @param columnTitleStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setColumnStyle.</p>
	 *
	 * @param columnStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setGroupTitleStyle.</p>
	 *
	 * @param groupTitleStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setGroupStyle.</p>
	 *
	 * @param groupStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setSubtotalStyle.</p>
	 *
	 * @param subtotalStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setImageStyle.</p>
	 *
	 * @param imageStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setPageFormat.</p>
	 *
	 * @param pageType a {@link software.xdev.dynamicreports.report.constant.PageType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageFormat(final PageType pageType)
	{
		return this.setPageFormat(pageType, PageOrientation.PORTRAIT);
	}
	
	/**
	 * <p>setPageFormat.</p>
	 *
	 * @param pageType    a {@link software.xdev.dynamicreports.report.constant.PageType} object.
	 * @param orientation a {@link software.xdev.dynamicreports.report.constant.PageOrientation} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageFormat(final PageType pageType, final PageOrientation orientation)
	{
		this.getObject().setPageFormat(pageType, orientation);
		return this;
	}
	
	/**
	 * <p>setPageMargin.</p>
	 *
	 * @param pageMargin a {@link software.xdev.dynamicreports.report.builder.MarginBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setPageColumnsPerPage.</p>
	 *
	 * @param pageColumnsPerPage a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageColumnsPerPage(final Integer pageColumnsPerPage)
	{
		this.getObject().setPageColumnsPerPage(pageColumnsPerPage);
		return this;
	}
	
	/**
	 * <p>setPageColumnSpace.</p>
	 *
	 * @param pageColumnSpace a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageColumnSpace(final Integer pageColumnSpace)
	{
		this.getObject().setPageColumnSpace(pageColumnSpace);
		return this;
	}
	
	/**
	 * <p>setIgnorePageWidth.</p>
	 *
	 * @param ignorePageWidth a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.getObject().setIgnorePageWidth(ignorePageWidth);
		return this;
	}
	
	/**
	 * <p>setColumnPrintRepeatedDetailValues.</p>
	 *
	 * @param columnPrintRepeatedDetailValues a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnPrintRepeatedDetailValues(final Boolean columnPrintRepeatedDetailValues)
	{
		this.getObject().setColumnPrintRepeatedDetailValues(columnPrintRepeatedDetailValues);
		return this;
	}
	
	/**
	 * <p>setColumnWidth.</p>
	 *
	 * @param columnWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnWidth(final Integer columnWidth)
	{
		this.getObject().setColumnWidth(columnWidth);
		return this;
	}
	
	/**
	 * <p>setGroupHeaderLayout.</p>
	 *
	 * @param groupHeaderLayout a {@link software.xdev.dynamicreports.report.constant.GroupHeaderLayout} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupHeaderLayout(final GroupHeaderLayout groupHeaderLayout)
	{
		this.getObject().setGroupHeaderLayout(groupHeaderLayout);
		return this;
	}
	
	/**
	 * <p>setGroupHideColumn.</p>
	 *
	 * @param groupHideColumn a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupHideColumn(final Boolean groupHideColumn)
	{
		this.getObject().setGroupHideColumn(groupHideColumn);
		return this;
	}
	
	/**
	 * <p>setGroupShowColumnHeaderAndFooter.</p>
	 *
	 * @param groupShowColumnHeaderAndFooter a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupShowColumnHeaderAndFooter(final Boolean groupShowColumnHeaderAndFooter)
	{
		this.getObject().setGroupShowColumnHeaderAndFooter(groupShowColumnHeaderAndFooter);
		return this;
	}
	
	/**
	 * <p>setGroupPadding.</p>
	 *
	 * @param groupPadding a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupPadding(final Integer groupPadding)
	{
		this.getObject().setGroupPadding(groupPadding);
		return this;
	}
	
	/**
	 * <p>setGroupStartInNewPage.</p>
	 *
	 * @param groupStartInNewPage a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupStartInNewPage(final Boolean groupStartInNewPage)
	{
		this.getObject().setGroupStartInNewPage(groupStartInNewPage);
		return this;
	}
	
	/**
	 * <p>setGroupStartInNewColumn.</p>
	 *
	 * @param groupStartInNewColumn a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupStartInNewColumn(final Boolean groupStartInNewColumn)
	{
		this.getObject().setGroupStartInNewColumn(groupStartInNewColumn);
		return this;
	}
	
	/**
	 * <p>setGroupReprintHeaderOnEachPage.</p>
	 *
	 * @param groupReprintHeaderOnEachPage a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupReprintHeaderOnEachPage(final Boolean groupReprintHeaderOnEachPage)
	{
		this.getObject().setGroupReprintHeaderOnEachPage(groupReprintHeaderOnEachPage);
		return this;
	}
	
	/**
	 * <p>setGroupResetPageNumber.</p>
	 *
	 * @param groupResetPageNumber a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupResetPageNumber(final Boolean groupResetPageNumber)
	{
		this.getObject().setGroupResetPageNumber(groupResetPageNumber);
		return this;
	}
	
	/**
	 * <p>setGroupFooterPosition.</p>
	 *
	 * @param groupFooterPosition a {@link software.xdev.dynamicreports.report.constant.GroupFooterPosition} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupFooterPosition(final GroupFooterPosition groupFooterPosition)
	{
		this.getObject().setGroupFooterPosition(groupFooterPosition);
		return this;
	}
	
	/**
	 * <p>setGroupKeepTogether.</p>
	 *
	 * @param groupKeepTogether a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupKeepTogether(final Boolean groupKeepTogether)
	{
		this.getObject().setGroupKeepTogether(groupKeepTogether);
		return this;
	}
	
	/**
	 * <p>setGroupHeaderWithSubtotal.</p>
	 *
	 * @param groupHeaderWithSubtotal a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupHeaderWithSubtotal(final Boolean groupHeaderWithSubtotal)
	{
		this.getObject().setGroupHeaderWithSubtotal(groupHeaderWithSubtotal);
		return this;
	}
	
	/**
	 * <p>setSubtotalLabelPosition.</p>
	 *
	 * @param subtotalLabelPosition a {@link software.xdev.dynamicreports.report.constant.Position} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSubtotalLabelPosition(final Position subtotalLabelPosition)
	{
		this.getObject().setSubtotalLabelPosition(subtotalLabelPosition);
		return this;
	}
	
	/**
	 * <p>tableOfContents.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder tableOfContents()
	{
		return this.setTableOfContents(true);
	}
	
	/**
	 * <p>setTableOfContents.</p>
	 *
	 * @param tableOfContents a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTableOfContents(final Boolean tableOfContents)
	{
		this.getObject().setTableOfContents(tableOfContents);
		return this;
	}
	
	/**
	 * <p>setTableOfContentsCustomizer.</p>
	 *
	 * @param tableOfContentsCustomizer a
	 *                                  {@link
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *                           software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder}
	 *                                  object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTableOfContentsCustomizer(final TableOfContentsCustomizerBuilder tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer.build());
		return this;
	}
	
	/**
	 * <p>setTableOfContentsCustomizer.</p>
	 *
	 * @param tableOfContentsCustomizer a {@link software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer}
	 *                                  object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTableOfContentsCustomizer(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer);
		return this;
	}
	
	/**
	 * <p>setTextFieldWidth.</p>
	 *
	 * @param textFieldWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTextFieldWidth(final Integer textFieldWidth)
	{
		this.getObject().setTextFieldWidth(textFieldWidth);
		return this;
	}
	
	/**
	 * <p>setImageHeight.</p>
	 *
	 * @param imageHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setImageHeight(final Integer imageHeight)
	{
		this.getObject().setImageHeight(imageHeight);
		return this;
	}
	
	/**
	 * <p>setImageWidth.</p>
	 *
	 * @param imageWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setImageWidth(final Integer imageWidth)
	{
		this.getObject().setImageWidth(imageWidth);
		return this;
	}
	
	/**
	 * <p>setListgap.</p>
	 *
	 * @param listgap a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setListgap(final Integer listgap)
	{
		this.getObject().setListgap(listgap);
		return this;
	}
	
	/**
	 * <p>setMultiPageListHeight.</p>
	 *
	 * @param multiPageListHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setMultiPageListHeight(final Integer multiPageListHeight)
	{
		this.getObject().setMultiPageListHeight(multiPageListHeight);
		return this;
	}
	
	/**
	 * <p>setMultiPageListWidth.</p>
	 *
	 * @param multiPageListWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setMultiPageListWidth(final Integer multiPageListWidth)
	{
		this.getObject().setMultiPageListWidth(multiPageListWidth);
		return this;
	}
	
	/**
	 * <p>setSubreportHeight.</p>
	 *
	 * @param subreportHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSubreportHeight(final Integer subreportHeight)
	{
		this.getObject().setSubreportHeight(subreportHeight);
		return this;
	}
	
	/**
	 * <p>setSubreportWidth.</p>
	 *
	 * @param subreportWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSubreportWidth(final Integer subreportWidth)
	{
		this.getObject().setSubreportWidth(subreportWidth);
		return this;
	}
	
	/**
	 * <p>setCrosstabHeight.</p>
	 *
	 * @param crosstabHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setCrosstabHeight(final Integer crosstabHeight)
	{
		this.getObject().setCrosstabHeight(crosstabHeight);
		return this;
	}
	
	/**
	 * <p>setCrosstabWidth.</p>
	 *
	 * @param crosstabWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setCrosstabWidth(final Integer crosstabWidth)
	{
		this.getObject().setCrosstabWidth(crosstabWidth);
		return this;
	}
	
	/**
	 * <p>crosstabHighlightOddRows.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder crosstabHighlightOddRows()
	{
		return this.setCrosstabHighlightOddRows(true);
	}
	
	/**
	 * <p>setCrosstabHighlightOddRows.</p>
	 *
	 * @param crosstabHighlightOddRows a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setCrosstabHighlightOddRows(final Boolean crosstabHighlightOddRows)
	{
		this.getObject().setCrosstabHighlightOddRows(crosstabHighlightOddRows);
		return this;
	}
	
	/**
	 * <p>setCrosstabOddRowStyle.</p>
	 *
	 * @param crosstabOddRowStyle a {@link software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>crosstabHighlightEvenRows.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder crosstabHighlightEvenRows()
	{
		return this.setCrosstabHighlightEvenRows(true);
	}
	
	/**
	 * <p>setCrosstabHighlightEvenRows.</p>
	 *
	 * @param crosstabHighlightEvenRows a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setCrosstabHighlightEvenRows(final Boolean crosstabHighlightEvenRows)
	{
		this.getObject().setCrosstabHighlightEvenRows(crosstabHighlightEvenRows);
		return this;
	}
	
	/**
	 * <p>setCrosstabEvenRowStyle.</p>
	 *
	 * @param crosstabEvenRowStyle a {@link software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setCrosstabGroupStyle.</p>
	 *
	 * @param crosstabGroupStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setCrosstabGroupTotalStyle.</p>
	 *
	 * @param crosstabGroupTotalStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setCrosstabGrandTotalStyle.</p>
	 *
	 * @param crosstabGrandTotalStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setCrosstabCellStyle.</p>
	 *
	 * @param crosstabCellStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setCrosstabMeasureTitleStyle.</p>
	 *
	 * @param crosstabMeasureTitleStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setBooleanComponentType.</p>
	 *
	 * @param booleanComponentType a {@link software.xdev.dynamicreports.report.constant.BooleanComponentType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBooleanComponentType(final BooleanComponentType booleanComponentType)
	{
		this.getObject().setBooleanComponentType(booleanComponentType);
		return this;
	}
	
	/**
	 * <p>setBooleanEmptyWhenNullValue.</p>
	 *
	 * @param booleanEmptyWhenNullValue a {@link java.lang.Boolean} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBooleanEmptyWhenNullValue(final Boolean booleanEmptyWhenNullValue)
	{
		this.getObject().setBooleanEmptyWhenNullValue(booleanEmptyWhenNullValue);
		return this;
	}
	
	/**
	 * Use setBooleanImageWidth(Integer booleanImageWidth)
	 *
	 * @param booleanColumnImageWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	@Deprecated
	public ReportTemplateBuilder setBooleanColumnImageWidth(final Integer booleanColumnImageWidth)
	{
		return this.setBooleanImageWidth(booleanColumnImageWidth);
	}
	
	/**
	 * <p>setBooleanImageWidth.</p>
	 *
	 * @param booleanImageWidth a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBooleanImageWidth(final Integer booleanImageWidth)
	{
		this.getObject().setBooleanImageWidth(booleanImageWidth);
		return this;
	}
	
	/**
	 * Use setBooleanImageHeight(Integer booleanImageHeight)
	 *
	 * @param booleanColumnImageHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	@Deprecated
	public ReportTemplateBuilder setBooleanColumnImageHeight(final Integer booleanColumnImageHeight)
	{
		return this.setBooleanImageHeight(booleanColumnImageHeight);
	}
	
	/**
	 * <p>setBooleanImageHeight.</p>
	 *
	 * @param booleanImageHeight a {@link java.lang.Integer} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBooleanImageHeight(final Integer booleanImageHeight)
	{
		this.getObject().setBooleanImageHeight(booleanImageHeight);
		return this;
	}
	
	/**
	 * <p>setBooleanColumnStyle.</p>
	 *
	 * @param cooleanColumnStyle a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setDefaultSplitType.</p>
	 *
	 * @param defaultSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDefaultSplitType(final SplitType defaultSplitType)
	{
		this.getObject().setDefaultSplitType(defaultSplitType);
		return this;
	}
	
	/**
	 * <p>setTitleSplitType.</p>
	 *
	 * @param titleSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTitleSplitType(final SplitType titleSplitType)
	{
		this.getObject().setTitleSplitType(titleSplitType);
		return this;
	}
	
	/**
	 * <p>setPageHeaderSplitType.</p>
	 *
	 * @param pageHeaderSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageHeaderSplitType(final SplitType pageHeaderSplitType)
	{
		this.getObject().setPageHeaderSplitType(pageHeaderSplitType);
		return this;
	}
	
	/**
	 * <p>setPageFooterSplitType.</p>
	 *
	 * @param pageFooterSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageFooterSplitType(final SplitType pageFooterSplitType)
	{
		this.getObject().setPageFooterSplitType(pageFooterSplitType);
		return this;
	}
	
	/**
	 * <p>setColumnHeaderSplitType.</p>
	 *
	 * @param columnHeaderSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnHeaderSplitType(final SplitType columnHeaderSplitType)
	{
		this.getObject().setColumnHeaderSplitType(columnHeaderSplitType);
		return this;
	}
	
	/**
	 * <p>setColumnFooterSplitType.</p>
	 *
	 * @param columnFooterSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnFooterSplitType(final SplitType columnFooterSplitType)
	{
		this.getObject().setColumnFooterSplitType(columnFooterSplitType);
		return this;
	}
	
	/**
	 * <p>setGroupHeaderSplitType.</p>
	 *
	 * @param groupHeaderSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupHeaderSplitType(final SplitType groupHeaderSplitType)
	{
		this.getObject().setGroupHeaderSplitType(groupHeaderSplitType);
		return this;
	}
	
	/**
	 * <p>setGroupFooterSplitType.</p>
	 *
	 * @param groupFooterSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupFooterSplitType(final SplitType groupFooterSplitType)
	{
		this.getObject().setGroupFooterSplitType(groupFooterSplitType);
		return this;
	}
	
	/**
	 * <p>setDetailHeaderSplitType.</p>
	 *
	 * @param detailHeaderSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailHeaderSplitType(final SplitType detailHeaderSplitType)
	{
		this.getObject().setDetailHeaderSplitType(detailHeaderSplitType);
		return this;
	}
	
	/**
	 * <p>setDetailSplitType.</p>
	 *
	 * @param detailSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailSplitType(final SplitType detailSplitType)
	{
		this.getObject().setDetailSplitType(detailSplitType);
		return this;
	}
	
	/**
	 * <p>setDetailFooterSplitType.</p>
	 *
	 * @param detailFooterSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailFooterSplitType(final SplitType detailFooterSplitType)
	{
		this.getObject().setDetailFooterSplitType(detailFooterSplitType);
		return this;
	}
	
	/**
	 * <p>setLastPageFooterSplitType.</p>
	 *
	 * @param lastPageFooterSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setLastPageFooterSplitType(final SplitType lastPageFooterSplitType)
	{
		this.getObject().setLastPageFooterSplitType(lastPageFooterSplitType);
		return this;
	}
	
	/**
	 * <p>setSummarySplitType.</p>
	 *
	 * @param summarySplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSummarySplitType(final SplitType summarySplitType)
	{
		this.getObject().setSummarySplitType(summarySplitType);
		return this;
	}
	
	/**
	 * <p>setNoDataSplitType.</p>
	 *
	 * @param noDataSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setNoDataSplitType(final SplitType noDataSplitType)
	{
		this.getObject().setNoDataSplitType(noDataSplitType);
		return this;
	}
	
	/**
	 * <p>setBackgroundSplitType.</p>
	 *
	 * @param backgroundSplitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBackgroundSplitType(final SplitType backgroundSplitType)
	{
		this.getObject().setBackgroundSplitType(backgroundSplitType);
		return this;
	}
	
	// band style
	
	/**
	 * <p>setTitleStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setPageHeaderStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setPageFooterStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setColumnHeaderStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setColumnFooterStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setGroupHeaderStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setGroupFooterStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setDetailHeaderStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setDetailStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setDetailFooterStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setLastPageFooterStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setSummaryStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setNoDataStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setBackgroundStyle.</p>
	 *
	 * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
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
	
	/**
	 * <p>setTitleBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setTitleBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setTitleBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setPageHeaderBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setPageHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setPageFooterBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setPageFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setColumnHeaderBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setColumnHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setColumnFooterBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setColumnFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setColumnFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setGroupHeaderBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setGroupHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setGroupFooterBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setGroupFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setGroupFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setDetailHeaderBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailHeaderBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setDetailBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setDetailFooterBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setDetailFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setDetailFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setLastPageFooterBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setLastPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setLastPageFooterBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setSummaryBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setSummaryBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setSummaryBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setNoDataBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setNoDataBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setNoDataBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>setBackgroundBackgroundComponent.</p>
	 *
	 * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
	 * @return a {@link software.xdev.dynamicreports.report.builder.ReportTemplateBuilder} object.
	 */
	public ReportTemplateBuilder setBackgroundBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().setBackgroundBackgroundComponent(backgroundComponent.build());
		return this;
	}
	
	/**
	 * <p>getReportTemplate.</p>
	 *
	 * @return a {@link software.xdev.dynamicreports.report.base.DRReportTemplate} object.
	 */
	public DRReportTemplate getReportTemplate()
	{
		return this.build();
	}
}
