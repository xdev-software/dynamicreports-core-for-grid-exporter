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

import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.Locale;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.fonts.FontUtil;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;


public final class StyleResolver
{
	private StyleResolver()
	{
	}
	
	private static final FontRenderContext CONTEXT =
		new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB).createGraphics().getFontRenderContext();
	
	public static int getFontWidth(final DRDesignStyle style, final int columns)
	{
		final double width = getFont(style).getStringBounds("m", CONTEXT).getWidth();
		return (int)Math.ceil(width * columns) + getHorizontalPadding(style);
	}
	
	public static int getFontHeight(final DRDesignStyle style, final int rows)
	{
		final double height = getFont(style).getMaxCharBounds(CONTEXT).getHeight();
		return (int)Math.ceil(height * rows) + getVerticalPadding(style);
	}
	
	public static double getFontWidth(final DRFont font)
	{
		final Font fnt = getFont(font.getFontName(), font.getBold(), font.getItalic(), font.getFontSize());
		return fnt.getStringBounds("m", CONTEXT).getWidth();
	}
	
	public static double getFontHeight(final DRFont font)
	{
		final Font fnt = getFont(font.getFontName(), font.getBold(), font.getItalic(), font.getFontSize());
		return fnt.getMaxCharBounds(CONTEXT).getHeight();
	}
	
	private static Font getFont(final DRDesignStyle style)
	{
		final String fontName = getFontName(style);
		final Integer fontSize = getFontSize(style);
		final Boolean bold = getFontBold(style);
		final Boolean italic = getFontItalic(style);
		return getFont(fontName, bold, italic, fontSize);
	}
	
	@SuppressWarnings("checkstyle:FinalParameters")
	private static Font getFont(final String fontName, Boolean bold, Boolean italic, final Integer fontSize)
	{
		if(bold == null)
		{
			bold = false;
		}
		if(italic == null)
		{
			italic = false;
		}
		final int fontStyle;
		if(bold && italic)
		{
			fontStyle = Font.BOLD | Font.ITALIC;
		}
		else if(bold)
		{
			fontStyle = Font.BOLD;
		}
		else if(italic)
		{
			fontStyle = Font.ITALIC;
		}
		else
		{
			fontStyle = Font.PLAIN;
		}
		
		final FontUtil fontUtil = FontUtil.getInstance(DefaultJasperReportsContext.getInstance());
		Font font = fontUtil.getAwtFontFromBundles(fontName, fontStyle, (float)fontSize, Locale.getDefault(), true);
		if(font == null)
		{
			font = new Font(fontName, fontStyle, fontSize);
		}
		return font;
	}
	
	public static String getFontName(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getFontName() != null)
		{
			return style.getFont().getFontName();
		}
		if(style.getParentStyle() != null)
		{
			return getFontName(style.getParentStyle());
		}
		return null;
	}
	
	public static Integer getFontSize(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getFontSize() != null)
		{
			return style.getFont().getFontSize();
		}
		if(style.getParentStyle() != null)
		{
			return getFontSize(style.getParentStyle());
		}
		return null;
	}
	
	public static Boolean getFontBold(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getBold() != null)
		{
			return style.getFont().getBold();
		}
		if(style.getParentStyle() != null)
		{
			return getFontBold(style.getParentStyle());
		}
		return null;
	}
	
	public static Boolean getFontItalic(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getItalic() != null)
		{
			return style.getFont().getItalic();
		}
		if(style.getParentStyle() != null)
		{
			return getFontItalic(style.getParentStyle());
		}
		return null;
	}
	
	public static Boolean getFontUnderline(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getUnderline() != null)
		{
			return style.getFont().getUnderline();
		}
		if(style.getParentStyle() != null)
		{
			return getFontUnderline(style.getParentStyle());
		}
		return null;
	}
	
	public static Boolean getFontStrikeThrough(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getStrikeThrough() != null)
		{
			return style.getFont().getStrikeThrough();
		}
		if(style.getParentStyle() != null)
		{
			return getFontStrikeThrough(style.getParentStyle());
		}
		return null;
	}
	
	public static String getPdfFontName(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getPdfFontName() != null)
		{
			return style.getFont().getPdfFontName();
		}
		if(style.getParentStyle() != null)
		{
			return getPdfFontName(style.getParentStyle());
		}
		return null;
	}
	
	public static String getPdfEncoding(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getPdfEncoding() != null)
		{
			return style.getFont().getPdfEncoding();
		}
		if(style.getParentStyle() != null)
		{
			return getPdfEncoding(style.getParentStyle());
		}
		return null;
	}
	
	public static Boolean getPdfEmbedded(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getFont().getPdfEmbedded() != null)
		{
			return style.getFont().getPdfEmbedded();
		}
		if(style.getParentStyle() != null)
		{
			return getPdfEmbedded(style.getParentStyle());
		}
		return null;
	}
	
	public static int getHorizontalPadding(final DRIDesignStyle style)
	{
		return getLeftPadding(style) + getRightPadding(style) + getLeftIndent(style) + getRightIndent(style);
	}
	
	public static int getVerticalPadding(final DRIDesignStyle style)
	{
		return getTopPadding(style) + getBottomPadding(style) + getFirstLineIndent(style);
	}
	
	private static Integer getTopPadding(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getPadding() != null && style.getPadding().getTop() != null)
		{
			return style.getPadding().getTop();
		}
		if(style.getParentStyle() != null)
		{
			return getTopPadding(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getBottomPadding(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getPadding() != null && style.getPadding().getBottom() != null)
		{
			return style.getPadding().getBottom();
		}
		if(style.getParentStyle() != null)
		{
			return getBottomPadding(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getLeftPadding(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getPadding() != null && style.getPadding().getLeft() != null)
		{
			return style.getPadding().getLeft();
		}
		if(style.getParentStyle() != null)
		{
			return getLeftPadding(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getRightPadding(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getPadding() != null && style.getPadding().getRight() != null)
		{
			return style.getPadding().getRight();
		}
		if(style.getParentStyle() != null)
		{
			return getRightPadding(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getFirstLineIndent(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getParagraph() != null && style.getParagraph().getFirstLineIndent() != null)
		{
			return style.getParagraph().getFirstLineIndent();
		}
		if(style.getParentStyle() != null)
		{
			return getFirstLineIndent(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getLeftIndent(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getParagraph() != null && style.getParagraph().getLeftIndent() != null)
		{
			return style.getParagraph().getLeftIndent();
		}
		if(style.getParentStyle() != null)
		{
			return getLeftIndent(style.getParentStyle());
		}
		return 0;
	}
	
	private static Integer getRightIndent(final DRIDesignStyle style)
	{
		if(style == null)
		{
			return 0;
		}
		if(style.getParagraph() != null && style.getParagraph().getRightIndent() != null)
		{
			return style.getParagraph().getRightIndent();
		}
		if(style.getParentStyle() != null)
		{
			return getRightIndent(style.getParentStyle());
		}
		return 0;
	}
	
	public static String getPattern(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getPattern() != null)
		{
			return style.getPattern();
		}
		if(style.getParentStyle() != null)
		{
			return getPattern(style.getParentStyle());
		}
		return null;
	}
	
	public static HorizontalTextAlignment getHorizontalTextAlignment(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getHorizontalTextAlignment() != null)
		{
			return style.getHorizontalTextAlignment();
		}
		if(style.getParentStyle() != null)
		{
			return getHorizontalTextAlignment(style.getParentStyle());
		}
		return null;
	}
	
	public static HorizontalImageAlignment getHorizontalImageAlignment(final DRDesignStyle style)
	{
		if(style == null)
		{
			return null;
		}
		if(style.getHorizontalImageAlignment() != null)
		{
			return style.getHorizontalImageAlignment();
		}
		if(style.getParentStyle() != null)
		{
			return getHorizontalImageAlignment(style.getParentStyle());
		}
		return null;
	}
	
	public static Color getBackgroundColor(final DRIReportStyle reportStyle, final StyleTransform transform)
	{
		if(reportStyle == null)
		{
			return null;
		}
		final DRIStyle style = transform.getStyle(reportStyle);
		if(style.getBackgroundColor() != null)
		{
			return style.getBackgroundColor();
		}
		if(style.getParentStyle() != null)
		{
			return getBackgroundColor(style.getParentStyle(), transform);
		}
		return null;
	}
	
	public static Color mergeColors(final Color color1, final Color color2, final float percent)
	{
		final float amount = 1.0f - percent;
		final int r = (int)(color1.getRed() * amount + color2.getRed() * percent);
		final int g = (int)(color1.getGreen() * amount + color2.getGreen() * percent);
		final int b = (int)(color1.getBlue() * amount + color2.getBlue() * percent);
		final int a = (int)(color1.getAlpha() * amount + color2.getAlpha() * percent);
		return new Color(r, g, b, a);
	}
}
