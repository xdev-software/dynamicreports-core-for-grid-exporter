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

import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;


public abstract class AbstractJasperCrosstabStyleTest extends AbstractJasperStyleTest
{
	private String crosstabBand;
	
	public void setCrosstabBand(final String crosstabBand)
	{
		this.crosstabBand = crosstabBand;
	}
	
	// header
	protected void crosstabHeaderElementStyleTest(
		final String name,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + "headercell." + name,
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	protected void crosstabHeaderElementBorderTest(
		final String name,
		final int index,
		final Color topColor,
		final LineStyleEnum topLineStyle,
		final float top,
		final Color bottomColor,
		final LineStyleEnum bottomLineStyle,
		final float bottom,
		final Color leftColor,
		final LineStyleEnum leftLineStyle,
		final float left,
		final Color rightColor,
		final LineStyleEnum rightLineStyle,
		final float right)
	{
		this.borderTest(
			this.getPrefix(1) + "headercell." + name,
			index,
			topColor,
			topLineStyle,
			top,
			bottomColor,
			bottomLineStyle,
			bottom,
			leftColor,
			leftLineStyle,
			left,
			rightColor,
			rightLineStyle,
			right);
	}
	
	protected void crosstabHeaderElementPaddingTest(
		final String name,
		final int index,
		final Integer top,
		final Integer bottom,
		final Integer left,
		final Integer right)
	{
		this.paddingTest(this.getPrefix(1) + "headercell." + name, index, top, bottom, left, right);
	}
	
	protected void crosstabHeaderElementHorizontalAlignmentTest(
		final String name,
		final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(this.getPrefix(1) + "headercell." + name, index, horizontalAlignment);
	}
	
	// group header
	protected void crosstabGroupHeaderStyleTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	protected void crosstabGroupHeaderBorderTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Color topColor,
		final LineStyleEnum topLineStyle,
		final float top,
		final Color bottomColor,
		final LineStyleEnum bottomLineStyle,
		final float bottom,
		final Color leftColor,
		final LineStyleEnum leftLineStyle,
		final float left,
		final Color rightColor,
		final LineStyleEnum rightLineStyle,
		final float right)
	{
		this.borderTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			index,
			topColor,
			topLineStyle,
			top,
			bottomColor,
			bottomLineStyle,
			bottom,
			leftColor,
			leftLineStyle,
			left,
			rightColor,
			rightLineStyle,
			right);
	}
	
	protected void crosstabGroupHeaderPaddingTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Integer top,
		final Integer bottom,
		final Integer left,
		final Integer right)
	{
		this.paddingTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			index,
			top,
			bottom,
			left,
			right);
	}
	
	protected void crosstabGroupHeaderHorizontalAlignmentTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			index,
			horizontalAlignment);
	}
	
	protected void crosstabGroupTitleHeaderStyleTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleHeaderName(group, measure),
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	// group total header
	protected void crosstabGroupTotalHeaderStyleTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	protected void crosstabGroupTotalHeaderBorderTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Color topColor,
		final LineStyleEnum topLineStyle,
		final float top,
		final Color bottomColor,
		final LineStyleEnum bottomLineStyle,
		final float bottom,
		final Color leftColor,
		final LineStyleEnum leftLineStyle,
		final float left,
		final Color rightColor,
		final LineStyleEnum rightLineStyle,
		final float right)
	{
		this.borderTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			index,
			topColor,
			topLineStyle,
			top,
			bottomColor,
			bottomLineStyle,
			bottom,
			leftColor,
			leftLineStyle,
			left,
			rightColor,
			rightLineStyle,
			right);
	}
	
	protected void crosstabGroupTotalHeaderPaddingTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final Integer top,
		final Integer bottom,
		final Integer left,
		final Integer right)
	{
		this.paddingTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			index,
			top,
			bottom,
			left,
			right);
	}
	
	protected void crosstabGroupTotalHeaderHorizontalAlignmentTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			index,
			horizontalAlignment);
	}
	
	protected void crosstabGroupTitleTotalHeaderStyleTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleTotalHeaderName(group, measure),
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	// cell
	protected void crosstabCellStyleTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int index,
		final Color foreColor,
		final Color backColor,
		final String fontName,
		final Float fontSize,
		final Boolean bold,
		final Boolean italic)
	{
		this.styleTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			index,
			foreColor,
			backColor,
			fontName,
			fontSize,
			bold,
			italic);
	}
	
	protected void crosstabCellBorderTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int index,
		final Color topColor,
		final LineStyleEnum topLineStyle,
		final float top,
		final Color bottomColor,
		final LineStyleEnum bottomLineStyle,
		final float bottom,
		final Color leftColor,
		final LineStyleEnum leftLineStyle,
		final float left,
		final Color rightColor,
		final LineStyleEnum rightLineStyle,
		final float right)
	{
		this.borderTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			index,
			topColor,
			topLineStyle,
			top,
			bottomColor,
			bottomLineStyle,
			bottom,
			leftColor,
			leftLineStyle,
			left,
			rightColor,
			rightLineStyle,
			right);
	}
	
	protected void crosstabCellPaddingTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int index,
		final Integer top,
		final Integer bottom,
		final Integer left,
		final Integer right)
	{
		this.paddingTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			index,
			top,
			bottom,
			left,
			right);
	}
	
	protected void crosstabCellHorizontalAlignmentTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int index,
		final HorizontalTextAlignEnum horizontalAlignment)
	{
		this.horizontalAlignmentTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			index,
			horizontalAlignment);
	}
	
	private String getPrefix(final int index)
	{
		return this.crosstabBand + ".crosstab" + index + ".";
	}
}
