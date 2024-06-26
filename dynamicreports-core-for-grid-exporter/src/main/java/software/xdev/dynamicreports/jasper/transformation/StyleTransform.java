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
package software.xdev.dynamicreports.jasper.transformation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.ModeEnum;
import software.xdev.dynamicreports.design.definition.style.DRIDesignBaseStyle;
import software.xdev.dynamicreports.design.definition.style.DRIDesignBorder;
import software.xdev.dynamicreports.design.definition.style.DRIDesignConditionalStyle;
import software.xdev.dynamicreports.design.definition.style.DRIDesignFont;
import software.xdev.dynamicreports.design.definition.style.DRIDesignPadding;
import software.xdev.dynamicreports.design.definition.style.DRIDesignParagraph;
import software.xdev.dynamicreports.design.definition.style.DRIDesignPen;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.design.definition.style.DRIDesignTabStop;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;


public class StyleTransform
{
	private final JasperTransformAccessor accessor;
	
	public StyleTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	public void transform()
	{
		for(final DRIDesignStyle style : this.accessor.getReport().getStyles())
		{
			this.addStyle(style);
		}
	}
	
	private void addStyle(final DRIDesignStyle style)
	{
		try
		{
			this.accessor.getDesign().addStyle(this.style(style));
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for style \"" + style.getName() + "\"", e);
		}
	}
	
	// style
	private JRDesignStyle style(final DRIDesignStyle style)
	{
		final JRDesignStyle jrStyle = new JRDesignStyle();
		this.abstractStyle(jrStyle, style);
		
		jrStyle.setName(style.getName());
		final DRIDesignStyle parentStyle = style.getParentStyle();
		if(parentStyle != null)
		{
			this.style(parentStyle);
			jrStyle.setParentStyleNameReference(parentStyle.getName());
		}
		for(final DRIDesignConditionalStyle conditionalStyle : style.getConditionalStyles())
		{
			jrStyle.addConditionalStyle(this.conditionalStyle(conditionalStyle));
		}
		return jrStyle;
	}
	
	private JRDesignConditionalStyle conditionalStyle(final DRIDesignConditionalStyle conditionalStyle)
	{
		final JRDesignConditionalStyle jrConditionalStyle = new JRDesignConditionalStyle();
		this.abstractStyle(jrConditionalStyle, conditionalStyle);
		
		final AbstractExpressionTransform expressionTransform =
			this.accessor.getExpressionTransform(conditionalStyle.getDataset());
		final JRDesignExpression expression =
			expressionTransform.getExpression(conditionalStyle.getConditionExpression());
		jrConditionalStyle.setConditionExpression(expression);
		
		return jrConditionalStyle;
	}
	
	private void abstractStyle(final JRBaseStyle baseStyle, final DRIDesignBaseStyle style)
	{
		baseStyle.setForecolor(style.getForegroundColor());
		baseStyle.setBackcolor(style.getBackgroundColor());
		if(style.getBackgroundColor() != null)
		{
			baseStyle.setMode(ModeEnum.OPAQUE);
		}
		baseStyle.setRadius(style.getRadius());
		baseStyle.setScaleImage(ConstantTransform.imageScale(style.getImageScale()));
		baseStyle.setHorizontalTextAlign(ConstantTransform.horizontalTextAlignment(style.getHorizontalTextAlignment()));
		baseStyle.setVerticalTextAlign(ConstantTransform.verticalTextAlignment(style.getVerticalTextAlignment()));
		baseStyle.setHorizontalImageAlign(
			ConstantTransform.horizontalImageAlignment(style.getHorizontalImageAlignment()));
		baseStyle.setVerticalImageAlign(ConstantTransform.verticalImageAlignment(style.getVerticalImageAlignment()));
		this.border(baseStyle.getLineBox(), style.getBorder());
		this.padding(baseStyle.getLineBox(), style.getPadding());
		this.font(baseStyle, style.getFont());
		baseStyle.setRotation(ConstantTransform.rotation(style.getRotation()));
		baseStyle.setPattern(style.getPattern());
		baseStyle.setMarkup(ConstantTransform.markup(style.getMarkup()));
		baseStyle.setBlankWhenNull(Boolean.TRUE);
		this.paragraph(baseStyle.getParagraph(), style.getParagraph());
		this.pen(baseStyle.getLinePen(), style.getLinePen());
	}
	
	private void paragraph(final JRParagraph jrParagraph, final DRIDesignParagraph paragraph)
	{
		jrParagraph.setLineSpacing(ConstantTransform.lineSpacing(paragraph.getLineSpacing()));
		jrParagraph.setLineSpacingSize(paragraph.getLineSpacingSize());
		jrParagraph.setFirstLineIndent(paragraph.getFirstLineIndent());
		jrParagraph.setLeftIndent(paragraph.getLeftIndent());
		jrParagraph.setRightIndent(paragraph.getRightIndent());
		jrParagraph.setSpacingBefore(paragraph.getSpacingBefore());
		jrParagraph.setSpacingAfter(paragraph.getSpacingAfter());
		jrParagraph.setTabStopWidth(paragraph.getTabStopWidth());
		for(final DRIDesignTabStop tabStop : paragraph.getTabStops())
		{
			final TabStop jrTabStop = new TabStop();
			jrTabStop.setPosition(tabStop.getPosition());
			jrTabStop.setAlignment(ConstantTransform.tabStopAlignment(tabStop.getAlignment()));
			jrParagraph.addTabStop(jrTabStop);
		}
	}
	
	protected void pen(final JRPen jrPen, final DRIDesignPen pen)
	{
		if(pen == null)
		{
			return;
		}
		
		jrPen.setLineColor(pen.getLineColor());
		jrPen.setLineStyle(ConstantTransform.lineStyle(pen.getLineStyle()));
		jrPen.setLineWidth(pen.getLineWidth());
	}
	
	private void border(final JRLineBox lineBox, final DRIDesignBorder border)
	{
		if(border == null)
		{
			return;
		}
		
		this.pen(lineBox.getLeftPen(), border.getLeftPen());
		this.pen(lineBox.getRightPen(), border.getRightPen());
		this.pen(lineBox.getTopPen(), border.getTopPen());
		this.pen(lineBox.getBottomPen(), border.getBottomPen());
	}
	
	private void padding(final JRLineBox lineBox, final DRIDesignPadding padding)
	{
		if(padding == null)
		{
			return;
		}
		
		lineBox.setLeftPadding(padding.getLeft());
		lineBox.setRightPadding(padding.getRight());
		lineBox.setTopPadding(padding.getTop());
		lineBox.setBottomPadding(padding.getBottom());
	}
	
	private void font(final JRBaseStyle baseStyle, final DRIDesignFont font)
	{
		if(font == null)
		{
			return;
		}
		
		baseStyle.setFontName(font.getFontName());
		baseStyle.setBold(font.getBold());
		baseStyle.setItalic(font.getItalic());
		baseStyle.setFontSize(font.getFontSize() == null ? null : font.getFontSize().floatValue());
		baseStyle.setStrikeThrough(font.getStrikeThrough());
		baseStyle.setUnderline(font.getUnderline());
		baseStyle.setPdfFontName(font.getPdfFontName());
		baseStyle.setPdfEncoding(font.getPdfEncoding());
		baseStyle.setPdfEmbedded(font.getPdfEmbedded());
	}
	
	protected JRBaseFont font(final DRIDesignFont font)
	{
		if(font == null)
		{
			return null;
		}
		
		final JRBaseFont jrFont = new JRBaseFont();
		jrFont.setFontName(font.getFontName());
		jrFont.setBold(font.getBold());
		jrFont.setItalic(font.getItalic());
		jrFont.setFontSize(font.getFontSize() == null ? null : font.getFontSize().floatValue());
		jrFont.setStrikeThrough(font.getStrikeThrough());
		jrFont.setUnderline(font.getUnderline());
		jrFont.setPdfFontName(font.getPdfFontName());
		jrFont.setPdfEncoding(font.getPdfEncoding());
		jrFont.setPdfEmbedded(font.getPdfEmbedded());
		return jrFont;
	}
	
	protected JRDesignStyle getStyle(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(!this.accessor.getDesign().getStylesMap().containsKey(style.getName()))
		{
			throw new JasperDesignException("Style \"" + style.getName() + "\" is not registered");
		}
		return (JRDesignStyle)this.accessor.getDesign().getStylesMap().get(style.getName());
	}
}
