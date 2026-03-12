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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import software.xdev.dynamicreports.design.base.style.DRDesignBaseStyle;
import software.xdev.dynamicreports.design.base.style.DRDesignBorder;
import software.xdev.dynamicreports.design.base.style.DRDesignConditionalStyle;
import software.xdev.dynamicreports.design.base.style.DRDesignFont;
import software.xdev.dynamicreports.design.base.style.DRDesignPadding;
import software.xdev.dynamicreports.design.base.style.DRDesignParagraph;
import software.xdev.dynamicreports.design.base.style.DRDesignPen;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.base.style.DRDesignTabStop;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.design.definition.style.DRIDesignTabStop;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.base.style.DRBaseStyle;
import software.xdev.dynamicreports.report.base.style.DRBorder;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.base.style.DRPadding;
import software.xdev.dynamicreports.report.base.style.DRParagraph;
import software.xdev.dynamicreports.report.base.style.DRPen;
import software.xdev.dynamicreports.report.definition.style.DRIBaseStyle;
import software.xdev.dynamicreports.report.definition.style.DRIBorder;
import software.xdev.dynamicreports.report.definition.style.DRIConditionalStyle;
import software.xdev.dynamicreports.report.definition.style.DRIFont;
import software.xdev.dynamicreports.report.definition.style.DRIPadding;
import software.xdev.dynamicreports.report.definition.style.DRIParagraph;
import software.xdev.dynamicreports.report.definition.style.DRIPen;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;
import software.xdev.dynamicreports.report.definition.style.DRITabStop;
import software.xdev.dynamicreports.report.definition.style.DRITemplateStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class StyleTransform
{
	private final DesignTransformAccessor accessor;
	private Map<String, DRIDesignStyle> styles;
	private Map<String, DRDesignStyle> designStyles;
	private Map<String, DRIStyle> templateStyles;
	
	public StyleTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.init();
	}
	
	private void init()
	{
		this.styles = new LinkedHashMap<>();
		this.designStyles = new HashMap<>();
		this.templateStyles = this.accessor.getTemplateTransform().getTemplateStyles();
	}
	
	private DRDesignStyle transformStyle(final DRIReportStyle style, final boolean textStyle) throws DRException
	{
		return this.transformStyle(style, textStyle, DefaultStyleType.NONE);
	}
	
	protected DRDesignStyle transformStyle(
		final DRIReportStyle style,
		final boolean textStyle,
		final DefaultStyleType defaultStyleType) throws DRException
	{
		if(style == null)
		{
			return this.getDefaultStyle(defaultStyleType);
		}
		final String styleName = style.hashCode() + "_" + textStyle;
		if(this.designStyles.containsKey(styleName))
		{
			return this.designStyles.get(styleName);
		}
		
		final DRDesignStyle designStyle = this.style(this.getStyle(style), textStyle, defaultStyleType);
		if(textStyle)
		{
			if(StyleResolver.getFontName(designStyle) == null)
			{
				designStyle.getFont().setFontName(this.accessor.getTemplateTransform().getDefaultFontName());
			}
			if(StyleResolver.getFontSize(designStyle) == null)
			{
				designStyle.getFont().setFontSize(this.accessor.getTemplateTransform().getDefaultFontSize());
			}
			if(StyleResolver.getFontBold(designStyle) == null)
			{
				designStyle.getFont().setBold(this.accessor.getTemplateTransform().getDefaultFontBold());
			}
			if(StyleResolver.getFontItalic(designStyle) == null)
			{
				designStyle.getFont().setItalic(this.accessor.getTemplateTransform().getDefaultFontItalic());
			}
			if(StyleResolver.getFontUnderline(designStyle) == null)
			{
				designStyle.getFont().setUnderline(this.accessor.getTemplateTransform().getDefaultFontUnderline());
			}
			if(StyleResolver.getFontStrikeThrough(designStyle) == null)
			{
				designStyle.getFont()
					.setStrikeThrough(this.accessor.getTemplateTransform().getDefaultFontStrikeThrough());
			}
			if(StyleResolver.getPdfFontName(designStyle) == null)
			{
				designStyle.getFont().setPdfFontName(this.accessor.getTemplateTransform().getDefaultFontPdfFontName());
			}
			if(StyleResolver.getPdfEncoding(designStyle) == null)
			{
				designStyle.getFont().setPdfEncoding(this.accessor.getTemplateTransform().getDefaultFontPdfEncoding());
			}
			if(StyleResolver.getPdfEmbedded(designStyle) == null)
			{
				designStyle.getFont().setPdfEmbedded(this.accessor.getTemplateTransform().getDefaultFontPdfEmbedded());
			}
		}
		this.addStyle(styleName, designStyle);
		return designStyle;
	}
	
	protected DRDesignFont transformFont(final DRIFont font)
	{
		if(font == null)
		{
			return null;
		}
		
		final DRDesignFont designFont = new DRDesignFont();
		designFont.setFontName(font.getFontName());
		designFont.setFontSize(font.getFontSize());
		designFont.setBold(font.getBold());
		designFont.setItalic(font.getItalic());
		designFont.setUnderline(font.getUnderline());
		designFont.setStrikeThrough(font.getStrikeThrough());
		designFont.setPdfFontName(font.getPdfFontName());
		designFont.setPdfEncoding(font.getPdfEncoding());
		designFont.setPdfEmbedded(font.getPdfEmbedded());
		return designFont;
	}
	
	private DRDesignStyle style(final DRIStyle style, final boolean textStyle, final DefaultStyleType defaultStyleType)
		throws DRException
	{
		final DRDesignStyle designStyle;
		if(StringUtils.isBlank(style.getName()))
		{
			designStyle = new DRDesignStyle();
		}
		else
		{
			designStyle = new DRDesignStyle(style.getName());
		}
		this.baseStyle(designStyle, style);
		if(style.getParentStyle() != null)
		{
			designStyle.setParentStyle(this.transformStyle(style.getParentStyle(), textStyle, defaultStyleType));
		}
		for(final DRIConditionalStyle conditionalStyle : style.getConditionalStyles())
		{
			designStyle.addConditionalStyle(this.conditionalStyle(conditionalStyle));
		}
		return designStyle;
	}
	
	protected DRIStyle getStyle(final DRIReportStyle reportStyle)
	{
		if(reportStyle == null)
		{
			return null;
		}
		if(reportStyle instanceof DRIStyle)
		{
			return (DRIStyle)reportStyle;
		}
		if(reportStyle instanceof DRITemplateStyle)
		{
			final String name = ((DRITemplateStyle)reportStyle).getName();
			final DRIStyle style = this.templateStyles.get(name);
			if(style == null)
			{
				throw new DRDesignReportException("Template style " + name + " not found");
			}
			return style;
		}
		throw new DRDesignReportException("Style " + reportStyle.getClass().getName() + " not supported");
	}
	
	public void transformTemplateStyles() throws DRException
	{
		for(final DRIStyle style : this.templateStyles.values())
		{
			if(this.styles.containsKey(style.getName()))
			{
				continue;
			}
			this.transformStyle(style, false);
		}
	}
	
	private DRDesignConditionalStyle conditionalStyle(final DRIConditionalStyle conditionalStyle) throws DRException
	{
		final DRDesignConditionalStyle designConditionalStyle = new DRDesignConditionalStyle();
		this.baseStyle(designConditionalStyle, conditionalStyle);
		designConditionalStyle.setConditionExpression(this.accessor.getExpressionTransform()
			.transformExpression(conditionalStyle.getConditionExpression()));
		designConditionalStyle.setDataset(this.accessor.getExpressionTransform().getDataset());
		return designConditionalStyle;
	}
	
	private void baseStyle(final DRDesignBaseStyle designBaseStyle, final DRIBaseStyle baseStyle) throws DRException
	{
		designBaseStyle.setForegroundColor(baseStyle.getForegroundColor());
		designBaseStyle.setBackgroundColor(baseStyle.getBackgroundColor());
		designBaseStyle.setRadius(baseStyle.getRadius());
		designBaseStyle.setImageScale(baseStyle.getImageScale());
		designBaseStyle.setHorizontalImageAlignment(baseStyle.getHorizontalImageAlignment());
		designBaseStyle.setVerticalImageAlignment(baseStyle.getVerticalImageAlignment());
		designBaseStyle.setHorizontalTextAlignment(baseStyle.getHorizontalTextAlignment());
		designBaseStyle.setVerticalTextAlignment(baseStyle.getVerticalTextAlignment());
		designBaseStyle.setBorder(this.border(baseStyle.getBorder()));
		designBaseStyle.setPadding(this.padding(baseStyle.getPadding()));
		designBaseStyle.setFont(this.transformFont(baseStyle.getFont()));
		designBaseStyle.setRotation(baseStyle.getRotation());
		designBaseStyle.setPattern(baseStyle.getPattern());
		designBaseStyle.setMarkup(baseStyle.getMarkup());
		designBaseStyle.setParagraph(this.paragraph(baseStyle.getParagraph()));
		designBaseStyle.setLinePen(this.pen(baseStyle.getLinePen()));
	}
	
	private DRDesignParagraph paragraph(final DRIParagraph paragraph)
	{
		final DRDesignParagraph designParagraph = new DRDesignParagraph();
		designParagraph.setLineSpacing(paragraph.getLineSpacing());
		designParagraph.setLineSpacingSize(paragraph.getLineSpacingSize());
		designParagraph.setFirstLineIndent(paragraph.getFirstLineIndent());
		designParagraph.setLeftIndent(paragraph.getLeftIndent());
		designParagraph.setRightIndent(paragraph.getRightIndent());
		designParagraph.setSpacingBefore(paragraph.getSpacingBefore());
		designParagraph.setSpacingAfter(paragraph.getSpacingAfter());
		designParagraph.setTabStopWidth(paragraph.getTabStopWidth());
		final List<DRIDesignTabStop> designTabStops = new ArrayList<>();
		for(final DRITabStop tabStop : paragraph.getTabStops())
		{
			final DRDesignTabStop designTabStop = new DRDesignTabStop();
			designTabStop.setPosition(tabStop.getPosition());
			designTabStop.setAlignment(tabStop.getAlignment());
			designTabStops.add(designTabStop);
		}
		designParagraph.setTabStops(designTabStops);
		return designParagraph;
	}
	
	private DRDesignBorder border(final DRIBorder border)
	{
		final DRDesignBorder designBorder = new DRDesignBorder();
		designBorder.setTopPen(this.pen(border.getTopPen()));
		designBorder.setLeftPen(this.pen(border.getLeftPen()));
		designBorder.setBottomPen(this.pen(border.getBottomPen()));
		designBorder.setRightPen(this.pen(border.getRightPen()));
		return designBorder;
	}
	
	private DRDesignPadding padding(final DRIPadding padding)
	{
		final DRDesignPadding designPadding = new DRDesignPadding();
		designPadding.setTop(padding.getTop());
		designPadding.setLeft(padding.getLeft());
		designPadding.setBottom(padding.getBottom());
		designPadding.setRight(padding.getRight());
		return designPadding;
	}
	
	protected DRDesignPen pen(final DRIPen pen)
	{
		if(pen == null)
		{
			return null;
		}
		final DRDesignPen designPen = new DRDesignPen();
		designPen.setLineWidth(pen.getLineWidth());
		designPen.setLineStyle(pen.getLineStyle());
		designPen.setLineColor(pen.getLineColor());
		return designPen;
	}
	
	protected DRDesignStyle getDefaultStyle(final DefaultStyleType defaultStyleType) throws DRException
	{
		final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
		switch(defaultStyleType)
		{
			case NONE:
				return null;
			case TEXT:
				return this.transformStyle(templateTransform.getTextStyle(), true);
			case COLUMN:
				return this.transformStyle(templateTransform.getColumnStyle(true), true);
			case COLUMN_TITLE:
				return this.transformStyle(templateTransform.getColumnTitleStyle(), true);
			case GROUP:
				return this.transformStyle(templateTransform.getGroupStyle(), true);
			case GROUP_TITLE:
				return this.transformStyle(templateTransform.getGroupTitleStyle(), true);
			case SUBTOTAL:
				return this.transformStyle(templateTransform.getSubtotalStyle(), true);
			case IMAGE:
				return this.transformStyle(templateTransform.getImageStyle(), false);
			default:
				throw new DRDesignReportException("Default style type " + defaultStyleType.name() + " not supported");
		}
	}
	
	private void addStyle(final String styleName, final DRDesignStyle designStyle)
	{
		if(designStyle == null)
		{
			return;
		}
		if(this.styles.containsKey(designStyle.getName()))
		{
			if(!this.styles.get(designStyle.getName()).equals(designStyle))
			{
				throw new DRDesignReportException("Duplicate declaration of style \"" + designStyle.getName() + "\"");
			}
			return;
		}
		this.styles.put(designStyle.getName(), designStyle);
		this.designStyles.put(styleName, designStyle);
	}
	
	public void copyStyle(final DRBaseStyle toStyle, final DRIBaseStyle fromStyle)
	{
		toStyle.setForegroundColor(fromStyle.getForegroundColor());
		toStyle.setBackgroundColor(fromStyle.getBackgroundColor());
		toStyle.setRadius(fromStyle.getRadius());
		toStyle.setImageScale(fromStyle.getImageScale());
		toStyle.setHorizontalImageAlignment(fromStyle.getHorizontalImageAlignment());
		toStyle.setVerticalImageAlignment(fromStyle.getVerticalImageAlignment());
		toStyle.setHorizontalTextAlignment(fromStyle.getHorizontalTextAlignment());
		toStyle.setVerticalTextAlignment(fromStyle.getVerticalTextAlignment());
		toStyle.setBorder((DRBorder)fromStyle.getBorder());
		toStyle.setPadding((DRPadding)fromStyle.getPadding());
		toStyle.setFont((DRFont)fromStyle.getFont());
		toStyle.setRotation(fromStyle.getRotation());
		toStyle.setPattern(fromStyle.getPattern());
		toStyle.setMarkup(fromStyle.getMarkup());
		toStyle.setParagraph((DRParagraph)fromStyle.getParagraph());
		toStyle.setLinePen((DRPen)fromStyle.getLinePen());
	}
	
	public Collection<DRIDesignStyle> getStyles()
	{
		return this.styles.values();
	}
}
