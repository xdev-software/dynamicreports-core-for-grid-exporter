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
package software.xdev.dynamicreports.report.builder.style;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import software.xdev.dynamicreports.report.constant.LineStyle;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


public class StyleBuilders
{
	
	// font
	
	public FontBuilder font()
	{
		return Styles.font();
	}
	
	public FontBuilder font(final String fontName, final boolean bold, final boolean italic, final int fontSize)
	{
		return Styles.font(fontName, bold, italic, fontSize);
	}
	
	public FontBuilder fontArial()
	{
		return Styles.fontArial();
	}
	
	public FontBuilder fontArialBold()
	{
		return Styles.fontArialBold();
	}
	
	public FontBuilder fontTimesNewRoman()
	{
		return Styles.fontTimesNewRoman();
	}
	
	public FontBuilder fontTimesNewRomanBold()
	{
		return Styles.fontTimesNewRomanBold();
	}
	
	public FontBuilder fontCourierNew()
	{
		return Styles.fontCourierNew();
	}
	
	public FontBuilder fontCourierNewBold()
	{
		return Styles.fontCourierNewBold();
	}
	
	// pen
	
	public PenBuilder pen()
	{
		return Styles.pen();
	}
	
	public PenBuilder pen(final Float lineWidth, final LineStyle lineStyle)
	{
		return Styles.pen(lineWidth, lineStyle);
	}
	
	public PenBuilder penThin()
	{
		return Styles.penThin();
	}
	
	public PenBuilder pen1Point()
	{
		return Styles.pen1Point();
	}
	
	public PenBuilder pen2Point()
	{
		return Styles.pen2Point();
	}
	
	public PenBuilder penDotted()
	{
		return Styles.penDotted();
	}
	
	public PenBuilder penDashed()
	{
		return Styles.penDashed();
	}
	
	public PenBuilder penDouble()
	{
		return Styles.penDouble();
	}
	
	// border
	
	public BorderBuilder border()
	{
		return Styles.border();
	}
	
	public BorderBuilder border(final PenBuilder pen)
	{
		return Styles.border(pen);
	}
	
	// padding
	
	public PaddingBuilder padding()
	{
		return Styles.padding();
	}
	
	public PaddingBuilder padding(final int padding)
	{
		return Styles.padding(padding);
	}
	
	// style
	
	public StyleBuilder style()
	{
		return Styles.style();
	}
	
	public StyleBuilder style(final ReportStyleBuilder parentStyle)
	{
		return Styles.style(parentStyle);
	}
	
	public StyleBuilder style(final FontBuilder font)
	{
		return Styles.style(font);
	}
	
	public StyleBuilder style(final PenBuilder borderPen)
	{
		return Styles.style(borderPen);
	}
	
	public StyleBuilder style(final Integer padding)
	{
		return Styles.style(padding);
	}
	
	public SimpleStyleBuilder simpleStyle()
	{
		return Styles.simpleStyle();
	}
	
	public TemplateStyleBuilder templateStyle(final String name)
	{
		return Styles.templateStyle(name);
	}
	
	public ConditionalStyleBuilder conditionalStyle(final DRIExpression<Boolean> conditionExpression)
	{
		return Styles.conditionalStyle(conditionExpression);
	}
	
	public TemplateStylesBuilder templateStyles()
	{
		return Styles.templateStyles();
	}
	
	public TemplateStylesBuilder loadStyles(final InputStream inputStream)
	{
		return Styles.loadStyles(inputStream);
	}
	
	public TemplateStylesBuilder loadStyles(final File file)
	{
		return Styles.loadStyles(file);
	}
	
	public TemplateStylesBuilder loadStyles(final String fileName) throws DRException
	{
		return Styles.loadStyles(fileName);
	}
	
	public TemplateStylesBuilder loadStyles(final URL url)
	{
		return Styles.loadStyles(url);
	}
}
