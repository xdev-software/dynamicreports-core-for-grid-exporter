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
package software.xdev.dynamicreports.test.jasper;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRImageAlignment;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTextAlignment;
import net.sf.jasperreports.engine.base.JRBoxPen;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.SubtotalBuilder;


/**
 * Base class for jasper style tests.
 */
public abstract class AbstractJasperStyleTest extends AbstractJasperTest
{
	
	protected static final String TEST_FONT_NAME = "Arimo";
	
	protected void styleTest(
		final String name, final int index, final Color foreColor, final Color backColor,
		final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		final JRStyle style = this.getElementAt(name, index).getStyle();
		this.styleTest(style, foreColor, backColor, fontName, fontSize, bold, italic);
	}
	
	protected void styleTest(
		final JRStyle style, final Color foreColor, final Color backColor, final String fontName,
		final Float fontSize, final Boolean bold, final Boolean italic)
	{
		Assertions.assertNotNull(style);
		Assertions.assertEquals(foreColor, style.getForecolor());
		Assertions.assertEquals(backColor, style.getBackcolor());
		Assertions.assertEquals(fontName, style.getFontName());
		Assertions.assertEquals(fontSize, style.getFontsize());
		Assertions.assertEquals(bold, style.isBold());
		Assertions.assertEquals(italic, style.isItalic());
	}
	
	protected void borderTest(
		final String name, final int index, final Color topColor, final LineStyleEnum topLineStyle,
		final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle, final float bottom, final Color leftColor,
		final LineStyleEnum leftLineStyle, final float left, final Color rightColor, final LineStyleEnum rightLineStyle,
		final float right)
	{
		final JRStyle style = this.getElementAt(name, index).getStyle();
		
		JRBoxPen pen = style.getLineBox().getTopPen();
		Assertions.assertEquals(top, pen.getLineWidth().floatValue(), 0);
		Assertions.assertEquals(topColor, pen.getLineColor());
		Assertions.assertEquals(topLineStyle, pen.getLineStyleValue());
		
		pen = style.getLineBox().getBottomPen();
		Assertions.assertEquals(bottom, pen.getLineWidth().floatValue(), 0);
		Assertions.assertEquals(bottomColor, pen.getLineColor());
		Assertions.assertEquals(bottomLineStyle, pen.getLineStyleValue());
		
		pen = style.getLineBox().getLeftPen();
		Assertions.assertEquals(left, pen.getLineWidth().floatValue(), 0);
		Assertions.assertEquals(leftColor, pen.getLineColor());
		Assertions.assertEquals(leftLineStyle, pen.getLineStyleValue());
		
		pen = style.getLineBox().getRightPen();
		Assertions.assertEquals(right, pen.getLineWidth().floatValue(), 0);
		Assertions.assertEquals(rightColor, pen.getLineColor());
		Assertions.assertEquals(rightLineStyle, pen.getLineStyleValue());
	}
	
	protected void paddingTest(
		final String name, final int index, final Integer top, final Integer bottom, final Integer left,
		final Integer right)
	{
		final JRStyle style = this.getElementAt(name, index).getStyle();
		Assertions.assertEquals(top, style.getLineBox().getTopPadding());
		Assertions.assertEquals(bottom, style.getLineBox().getBottomPadding());
		Assertions.assertEquals(left, style.getLineBox().getLeftPadding());
		Assertions.assertEquals(right, style.getLineBox().getRightPadding());
	}
	
	protected void horizontalAlignmentTest(
		final String name, final int index,
		final HorizontalImageAlignEnum horizontalAlignment)
	{
		final JRImageAlignment element = (JRImageAlignment)this.getElementAt(name, index);
		if(horizontalAlignment == null)
		{
			Assertions.assertEquals(
				HorizontalImageAlignEnum.LEFT,
				element.getHorizontalImageAlign());
		}
		Assertions.assertEquals(
			horizontalAlignment,
			element.getHorizontalImageAlign());
	}
	
	protected void horizontalAlignmentTest(
		final String name, final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		final JRTextAlignment element = (JRTextAlignment)this.getElementAt(name, index);
		if(horizontalAlignment == null)
		{
			Assertions.assertEquals(
				HorizontalTextAlignEnum.LEFT,
				element.getHorizontalTextAlign());
		}
		Assertions.assertEquals(
			horizontalAlignment,
			element.getHorizontalTextAlign());
	}
	
	protected void verticalAlignmentTest(
		final String name, final int index,
		final VerticalImageAlignEnum verticalAlignment)
	{
		final JRImageAlignment element = (JRImageAlignment)this.getElementAt(name, index);
		if(verticalAlignment == null)
		{
			Assertions.assertEquals(
				VerticalImageAlignEnum.TOP,
				element.getVerticalImageAlign());
		}
		Assertions.assertEquals(verticalAlignment, element.getVerticalImageAlign());
	}
	
	protected void verticalAlignmentTest(
		final String name, final int index,
		final VerticalTextAlignEnum verticalAlignment)
	{
		final JRTextAlignment element = (JRTextAlignment)this.getElementAt(name, index);
		if(verticalAlignment == null)
		{
			Assertions.assertEquals(
				VerticalTextAlignEnum.TOP,
				element.getVerticalTextAlign());
		}
		Assertions.assertEquals(verticalAlignment, element.getVerticalTextAlign());
	}
	
	// column detail
	protected void columnDetailStyleTest(
		final ColumnBuilder<?, ?> column, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getColumnDetailName(column), index, foreColor, backColor, fontName,
			fontSize, bold, italic);
	}
	
	protected void columnDetailPaddingTest(
		final ColumnBuilder<?, ?> column, final int index, final Integer top,
		final Integer bottom, final Integer left, final Integer right)
	{
		this.paddingTest(JasperTestUtils.getColumnDetailName(column), index, top, bottom, left, right);
	}
	
	protected void columnDetailAlignmentTest(
		final ColumnBuilder<?, ?> column, final int index,
		final HorizontalImageAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index,
			horizontalAlignment);
	}
	
	protected void columnDetailAlignmentTest(
		final ColumnBuilder<?, ?> column, final int index,
		final VerticalImageAlignEnum verticalAlignment)
	{
		this.verticalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index, verticalAlignment);
	}
	
	protected void columnDetailAlignmentTest(
		final ColumnBuilder<?, ?> column, final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index,
			horizontalAlignment);
	}
	
	protected void columnDetailAlignmentTest(
		final ColumnBuilder<?, ?> column, final int index,
		final VerticalTextAlignEnum verticalAlignment)
	{
		this.verticalAlignmentTest(JasperTestUtils.getColumnDetailName(column), index, verticalAlignment);
	}
	
	protected void columnDetailBorderTest(
		final ColumnBuilder<?, ?> column, final int index, final Color topColor,
		final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
		final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
		final LineStyleEnum rightLineStyle, final float right)
	{
		this.borderTest(JasperTestUtils.getColumnDetailName(column), index, topColor, topLineStyle, top,
			bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
			rightLineStyle, right);
	}
	
	// column title
	protected void columnTitleBorderTest(
		final ColumnBuilder<?, ?> column, final int index, final Color topColor,
		final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
		final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
		final LineStyleEnum rightLineStyle, final float right)
	{
		this.borderTest(JasperTestUtils.getColumnTitleName(column), index, topColor, topLineStyle, top,
			bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
			rightLineStyle, right);
	}
	
	protected void columnTitlePaddingTest(
		final ColumnBuilder<?, ?> column, final int index, final Integer top,
		final Integer bottom, final Integer left, final Integer right)
	{
		this.paddingTest(JasperTestUtils.getColumnTitleName(column), index, top, bottom, left, right);
	}
	
	protected void columnTitleStyleTest(
		final ColumnBuilder<?, ?> column, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getColumnTitleName(column), index, foreColor, backColor, fontName,
			fontSize, bold, italic);
	}
	
	protected void columnTitleAlignmentTest(
		final ColumnBuilder<?, ?> column, final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(JasperTestUtils.getColumnTitleName(column), index, horizontalAlignment);
	}
	
	// subtotal label
	protected void subtotalLabelBorderTest(
		final SubtotalBuilder<?, ?> subtotal, final int index, final Color topColor,
		final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
		final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
		final LineStyleEnum rightLineStyle, final float right)
	{
		this.borderTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, topColor, topLineStyle,
			top, bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
			rightLineStyle, right);
	}
	
	protected void subtotalLabelStyleTest(
		final SubtotalBuilder<?, ?> subtotal, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getSubtotalLabelName(subtotal, 1), index, foreColor, backColor,
			fontName, fontSize, bold, italic);
	}
	
	// subtotal
	protected void subtotalBorderTest(
		final SubtotalBuilder<?, ?> subtotal, final int index, final Color topColor,
		final LineStyleEnum topLineStyle, final float top, final Color bottomColor, final LineStyleEnum bottomLineStyle,
		final float bottom, final Color leftColor, final LineStyleEnum leftLineStyle, final float left, final Color rightColor,
		final LineStyleEnum rightLineStyle, final float right)
	{
		this.borderTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, topColor, topLineStyle, top,
			bottomColor, bottomLineStyle, bottom, leftColor, leftLineStyle, left, rightColor,
			rightLineStyle, right);
	}
	
	protected void subtotalPaddingTest(
		final SubtotalBuilder<?, ?> subtotal, final int index, final Integer top,
		final Integer bottom, final Integer left, final Integer right)
	{
		this.paddingTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, top, bottom, left, right);
	}
	
	protected void subtotalStyleTest(
		final SubtotalBuilder<?, ?> subtotal, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getSubtotalName(subtotal, 1), index, foreColor, backColor, fontName,
			fontSize, bold, italic);
	}
	
	// group header title
	protected void groupHeaderTitleStyleTest(
		final GroupBuilder<?> group, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getHeaderTitleGroupName(group), index, foreColor, backColor, fontName,
			fontSize, bold, italic);
	}
	
	// group header
	protected void groupHeaderStyleTest(
		final GroupBuilder<?> group, final int index, final Color foreColor,
		final Color backColor, final String fontName, final Float fontSize, final Boolean bold, final Boolean italic)
	{
		this.styleTest(JasperTestUtils.getHeaderGroupName(group), index, foreColor, backColor, fontName,
			fontSize, bold, italic);
	}
	
	protected void groupHeaderAlignmentTest(
		final GroupBuilder<?> group, final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(JasperTestUtils.getHeaderGroupName(group), index, horizontalAlignment);
	}
}
