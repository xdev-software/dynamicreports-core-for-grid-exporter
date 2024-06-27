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
package software.xdev.dynamicreports.jasper.base;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRParagraph;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.report.base.style.DRBaseStyle;
import software.xdev.dynamicreports.report.base.style.DRBorder;
import software.xdev.dynamicreports.report.base.style.DRConditionalStyle;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.base.style.DRPadding;
import software.xdev.dynamicreports.report.base.style.DRParagraph;
import software.xdev.dynamicreports.report.base.style.DRPen;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.base.style.DRTabStop;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.LineSpacing;
import software.xdev.dynamicreports.report.constant.LineStyle;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.TabStopAlignment;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


public final class JasperTemplateStyleLoader
{
	private JasperTemplateStyleLoader()
	{
	}
	
	public static DRStyle[] loadStyles(final File file)
	{
		Validate.notNull(file, "file must not be null");
		return loadStyles(JRXmlTemplateLoader.load(file));
	}
	
	public static DRStyle[] loadStyles(final String fileName) throws DRException
	{
		Validate.notNull(fileName, "fileName must not be null");
		try
		{
			return loadStyles(JRXmlTemplateLoader.load(fileName));
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
	}
	
	public static DRStyle[] loadStyles(final InputStream inputStream)
	{
		Validate.notNull(inputStream, "inputStream must not be null");
		return loadStyles(JRXmlTemplateLoader.load(inputStream));
	}
	
	public static DRStyle[] loadStyles(final URL url)
	{
		Validate.notNull(url, "url must not be null");
		return loadStyles(JRXmlTemplateLoader.load(url));
	}
	
	private static DRStyle[] loadStyles(final JRTemplate template)
	{
		Validate.notNull(template, "template must not be null");
		final JRStyle[] jrStyles = template.getStyles();
		final DRStyle[] styles = new DRStyle[jrStyles.length];
		for(int i = 0; i < jrStyles.length; i++)
		{
			final JRStyle jrStyle = jrStyles[i];
			styles[i] = convertStyle(jrStyle);
		}
		return styles;
	}
	
	private static DRStyle convertStyle(final JRStyle jrStyle)
	{
		final DRStyle style = new DRStyle();
		abstractStyle(jrStyle, style);
		
		style.setName(jrStyle.getName());
		final JRStyle jrParentStyle = jrStyle.getStyle();
		if(jrParentStyle != null)
		{
			style.setParentStyle(convertStyle(jrParentStyle));
		}
		for(final JRConditionalStyle jrConditionalStyle : jrStyle.getConditionalStyles())
		{
			style.addConditionalStyle(conditionalStyle(jrConditionalStyle));
		}
		return style;
	}
	
	private static DRConditionalStyle conditionalStyle(final JRConditionalStyle jrConditionalStyle)
	{
		final DRIExpression<Boolean> expression =
			Expressions.jasperSyntax(jrConditionalStyle.getConditionExpression().getText(), Boolean.class);
		final DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		abstractStyle(jrConditionalStyle, conditionalStyle);
		return conditionalStyle;
	}
	
	private static void abstractStyle(final JRStyle jrStyle, final DRBaseStyle style)
	{
		style.setForegroundColor(jrStyle.getOwnForecolor());
		style.setBackgroundColor(jrStyle.getOwnBackcolor());
		style.setRadius(jrStyle.getOwnRadius());
		style.setImageScale(imageScale(jrStyle.getOwnScaleImageValue()));
		style.setHorizontalImageAlignment(horizontalImageAlignment(jrStyle.getOwnHorizontalImageAlign()));
		style.setVerticalImageAlignment(verticalImageAlignment(jrStyle.getOwnVerticalImageAlign()));
		style.setHorizontalTextAlignment(horizontalTextAlignment(jrStyle.getOwnHorizontalTextAlign()));
		style.setVerticalTextAlignment(verticalTextAlignment(jrStyle.getOwnVerticalTextAlign()));
		border(jrStyle.getLineBox(), style.getBorder());
		padding(jrStyle.getLineBox(), style.getPadding());
		font(jrStyle, style.getFont());
		style.setRotation(rotation(jrStyle.getOwnRotationValue()));
		style.setPattern(jrStyle.getOwnPattern());
		style.setMarkup(markup(jrStyle.getOwnMarkup()));
		paragraph(jrStyle.getParagraph(), style.getParagraph());
		pen(jrStyle.getLinePen(), style.getLinePen());
	}
	
	private static void paragraph(final JRParagraph jrParagraph, final DRParagraph paragraph)
	{
		paragraph.setLineSpacing(lineSpacing(jrParagraph.getOwnLineSpacing()));
		paragraph.setLineSpacingSize(jrParagraph.getOwnLineSpacingSize());
		paragraph.setFirstLineIndent(jrParagraph.getOwnFirstLineIndent());
		paragraph.setLeftIndent(jrParagraph.getOwnLeftIndent());
		paragraph.setRightIndent(jrParagraph.getOwnRightIndent());
		paragraph.setSpacingBefore(jrParagraph.getOwnSpacingBefore());
		paragraph.setSpacingAfter(jrParagraph.getOwnSpacingAfter());
		paragraph.setTabStopWidth(jrParagraph.getOwnTabStopWidth());
		if(jrParagraph.getOwnTabStops() != null)
		{
			for(final TabStop jrTabStop : jrParagraph.getOwnTabStops())
			{
				final DRTabStop tabStop = new DRTabStop();
				tabStop.setPosition(jrTabStop.getPosition());
				tabStop.setAlignment(tabStopAlignment(jrTabStop.getAlignment()));
				paragraph.getTabStops().add(tabStop);
			}
		}
	}
	
	protected static void pen(final JRPen jrPen, final DRPen pen)
	{
		pen.setLineColor(jrPen.getOwnLineColor());
		pen.setLineStyle(lineStyle(jrPen.getOwnLineStyleValue()));
		pen.setLineWidth(jrPen.getOwnLineWidth());
	}
	
	private static void border(final JRLineBox jrLineBox, final DRBorder border)
	{
		pen(jrLineBox.getLeftPen(), border.getLeftPen());
		pen(jrLineBox.getRightPen(), border.getRightPen());
		pen(jrLineBox.getTopPen(), border.getTopPen());
		pen(jrLineBox.getBottomPen(), border.getBottomPen());
	}
	
	private static void padding(final JRLineBox jrLineBox, final DRPadding padding)
	{
		padding.setLeft(jrLineBox.getOwnLeftPadding());
		padding.setRight(jrLineBox.getOwnRightPadding());
		padding.setTop(jrLineBox.getOwnTopPadding());
		padding.setBottom(jrLineBox.getOwnBottomPadding());
	}
	
	@SuppressWarnings("deprecation")
	private static void font(final JRStyle jrStyle, final DRFont font)
	{
		font.setFontName(jrStyle.getOwnFontName());
		font.setBold(jrStyle.isOwnBold());
		font.setItalic(jrStyle.isOwnItalic());
		font.setFontSize(jrStyle.getOwnFontsize() == null ? null : jrStyle.getOwnFontsize().intValue());
		font.setStrikeThrough(jrStyle.isOwnStrikeThrough());
		font.setUnderline(jrStyle.isOwnUnderline());
		font.setPdfFontName(jrStyle.getOwnPdfFontName());
		font.setPdfEncoding(jrStyle.getOwnPdfEncoding());
		font.setPdfEmbedded(jrStyle.isOwnPdfEmbedded());
	}
	
	private static LineStyle lineStyle(final LineStyleEnum lineStyle)
	{
		if(lineStyle == null)
		{
			return null;
		}
		
		switch(lineStyle)
		{
			case SOLID:
				return LineStyle.SOLID;
			case DASHED:
				return LineStyle.DASHED;
			case DOTTED:
				return LineStyle.DOTTED;
			case DOUBLE:
				return LineStyle.DOUBLE;
			default:
				throw new JasperDesignException("Line style " + lineStyle.name() + " not supported");
		}
	}
	
	private static ImageScale imageScale(final ScaleImageEnum imageScale)
	{
		if(imageScale == null)
		{
			return null;
		}
		
		switch(imageScale)
		{
			case CLIP:
				return ImageScale.CLIP;
			case FILL_FRAME:
				return ImageScale.FILL_FRAME;
			case RETAIN_SHAPE:
				return ImageScale.RETAIN_SHAPE;
			case REAL_HEIGHT:
				return ImageScale.REAL_HEIGHT;
			case REAL_SIZE:
				return ImageScale.REAL_SIZE;
			default:
				throw new JasperDesignException("Image scale " + imageScale.name() + " not supported");
		}
	}
	
	private static HorizontalImageAlignment horizontalImageAlignment(
		final HorizontalImageAlignEnum horizontalImageAlignment)
	{
		if(horizontalImageAlignment == null)
		{
			return null;
		}
		
		switch(horizontalImageAlignment)
		{
			case LEFT:
				return HorizontalImageAlignment.LEFT;
			case CENTER:
				return HorizontalImageAlignment.CENTER;
			case RIGHT:
				return HorizontalImageAlignment.RIGHT;
			default:
				throw new JasperDesignException(
					"Horizontal image alignment " + horizontalImageAlignment.name() + " not supported");
		}
	}
	
	private static VerticalImageAlignment verticalImageAlignment(final VerticalImageAlignEnum verticalImageAlignment)
	{
		if(verticalImageAlignment == null)
		{
			return null;
		}
		
		switch(verticalImageAlignment)
		{
			case TOP:
				return VerticalImageAlignment.TOP;
			case MIDDLE:
				return VerticalImageAlignment.MIDDLE;
			case BOTTOM:
				return VerticalImageAlignment.BOTTOM;
			default:
				throw new JasperDesignException(
					"Vertical image alignment " + verticalImageAlignment.name() + " not supported");
		}
	}
	
	private static HorizontalTextAlignment horizontalTextAlignment(
		final HorizontalTextAlignEnum horizontalTextAlignment)
	{
		if(horizontalTextAlignment == null)
		{
			return null;
		}
		
		switch(horizontalTextAlignment)
		{
			case LEFT:
				return HorizontalTextAlignment.LEFT;
			case CENTER:
				return HorizontalTextAlignment.CENTER;
			case RIGHT:
				return HorizontalTextAlignment.RIGHT;
			case JUSTIFIED:
				return HorizontalTextAlignment.JUSTIFIED;
			default:
				throw new JasperDesignException(
					"Horizontal text alignment " + horizontalTextAlignment.name() + " not supported");
		}
	}
	
	private static VerticalTextAlignment verticalTextAlignment(final VerticalTextAlignEnum verticalTextAlignment)
	{
		if(verticalTextAlignment == null)
		{
			return null;
		}
		
		switch(verticalTextAlignment)
		{
			case TOP:
				return VerticalTextAlignment.TOP;
			case MIDDLE:
				return VerticalTextAlignment.MIDDLE;
			case BOTTOM:
				return VerticalTextAlignment.BOTTOM;
			case JUSTIFIED:
				return VerticalTextAlignment.JUSTIFIED;
			default:
				throw new JasperDesignException(
					"Vertical text alignment " + verticalTextAlignment.name() + " not supported");
		}
	}
	
	private static Markup markup(final String markup)
	{
		if(markup == null)
		{
			return null;
		}
		
		if(markup.equals("none"))
		{
			return Markup.NONE;
		}
		if(markup.equals("styled"))
		{
			return Markup.STYLED;
		}
		if(markup.equals("rtf"))
		{
			return Markup.RTF;
		}
		if(markup.equals("html"))
		{
			return Markup.HTML;
		}
		throw new JasperDesignException("Markup " + markup + " not supported");
	}
	
	private static LineSpacing lineSpacing(final LineSpacingEnum lineSpacing)
	{
		if(lineSpacing == null)
		{
			return null;
		}
		
		switch(lineSpacing)
		{
			case SINGLE:
				return LineSpacing.SINGLE;
			case ONE_AND_HALF:
				return LineSpacing.ONE_AND_HALF;
			case DOUBLE:
				return LineSpacing.DOUBLE;
			case AT_LEAST:
				return LineSpacing.AT_LEAST;
			case FIXED:
				return LineSpacing.FIXED;
			case PROPORTIONAL:
				return LineSpacing.PROPORTIONAL;
			default:
				throw new JasperDesignException("LineSpacing " + lineSpacing.name() + " not supported");
		}
	}
	
	protected static Rotation rotation(final RotationEnum rotation)
	{
		if(rotation == null)
		{
			return null;
		}
		
		switch(rotation)
		{
			case NONE:
				return Rotation.NONE;
			case LEFT:
				return Rotation.LEFT;
			case RIGHT:
				return Rotation.RIGHT;
			case UPSIDE_DOWN:
				return Rotation.UPSIDE_DOWN;
			default:
				throw new JasperDesignException("Rotation " + rotation.name() + " not supported");
		}
	}
	
	private static TabStopAlignment tabStopAlignment(final TabStopAlignEnum alignment)
	{
		switch(alignment)
		{
			case LEFT:
				return TabStopAlignment.LEFT;
			case CENTER:
				return TabStopAlignment.CENTER;
			case RIGHT:
				return TabStopAlignment.RIGHT;
			default:
				throw new JasperDesignException("TabStopAlignment " + alignment.name() + " not supported");
		}
	}
}
