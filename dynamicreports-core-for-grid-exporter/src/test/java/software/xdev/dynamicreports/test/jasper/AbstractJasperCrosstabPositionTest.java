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

import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;


public abstract class AbstractJasperCrosstabPositionTest extends AbstractJasperPositionTest
{
	private String crosstabBand;
	
	public void setCrosstabBand(final String crosstabBand)
	{
		this.crosstabBand = crosstabBand;
	}
	
	protected void crosstabWhenNoDataElementPositionTest(
		final String name,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(this.getPrefix(1) + "whennodatacell." + name, index, x, y, width, height);
	}
	
	protected void crosstabHeaderElementPositionTest(
		final String name,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(this.getPrefix(1) + "headercell." + name, index, x, y, width, height);
	}
	
	// group header
	protected void crosstabGroupHeaderPositionTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			index,
			x,
			y,
			width,
			height);
	}
	
	// group total header
	protected void crosstabGroupTotalHeaderPositionTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			index,
			x,
			y,
			width,
			height);
	}
	
	// group title header
	protected void crosstabGroupTitleHeaderPositionTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleHeaderName(group, measure),
			index,
			x,
			y,
			width,
			height);
	}
	
	// group title total header
	protected void crosstabGroupTitleTotalHeaderPositionTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleTotalHeaderName(group, measure),
			index,
			x,
			y,
			width,
			height);
	}
	
	// cell
	protected void crosstabCellPositionTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int index,
		final int x,
		final int y,
		final int width,
		final int height)
	{
		this.elementPositionTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			index,
			x,
			y,
			width,
			height);
	}
	
	private String getPrefix(final int index)
	{
		return this.crosstabBand + ".crosstab" + index + ".";
	}
}
