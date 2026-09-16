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


public abstract class AbstractJasperCrosstabValueTest extends AbstractJasperValueTest
{
	private String crosstabBand;
	
	public void setCrosstabBand(final String crosstabBand)
	{
		this.crosstabBand = crosstabBand;
	}
	
	protected void crosstabHeaderElementCountTest(final String name, final int expectedNumberOfElements)
	{
		this.elementCountTest(this.getPrefix(1) + "headercell." + name, expectedNumberOfElements);
	}
	
	protected void crosstabHeaderElementValueTest(final String name, final String... values)
	{
		this.elementValueTest(this.getPrefix(1) + "headercell." + name, values);
	}
	
	// group header
	protected void crosstabGroupHeaderCountTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int expectedNumberOfElements)
	{
		this.elementCountTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group),
			expectedNumberOfElements);
	}
	
	protected void crosstabGroupHeaderValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final String... values)
	{
		this.elementValueTest(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group), values);
	}
	
	protected void crosstabGroupHeaderFullValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final String... values)
	{
		this.elementFullValueTest(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(group), values);
	}
	
	// group total header
	protected void crosstabGroupTotalHeaderCountTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final int expectedNumberOfElements)
	{
		this.elementCountTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group),
			expectedNumberOfElements);
	}
	
	protected void crosstabGroupTotalHeaderValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final String... values)
	{
		this.elementValueTest(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group), values);
	}
	
	protected void crosstabGroupTotalHeaderFullValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final String... values)
	{
		this.elementFullValueTest(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTotalHeaderName(group), values);
	}
	
	// group title header
	protected void crosstabGroupTitleHeaderCountTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int expectedNumberOfElements)
	{
		this.elementCountTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleHeaderName(group, measure),
			expectedNumberOfElements);
	}
	
	protected void crosstabGroupTitleHeaderValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final String... values)
	{
		this.elementValueTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleHeaderName(group, measure),
			values);
	}
	
	// group title total header
	protected void crosstabGroupTitleTotalHeaderCountTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final int expectedNumberOfElements)
	{
		this.elementCountTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleTotalHeaderName(group, measure),
			expectedNumberOfElements);
	}
	
	protected void crosstabGroupTitleTotalHeaderValueTest(
		final AbstractCrosstabGroupBuilder<?, ?, ?> group,
		final CrosstabMeasureBuilder<?> measure,
		final String... values)
	{
		this.elementValueTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabGroupTitleTotalHeaderName(group, measure),
			values);
	}
	
	// cell
	protected void crosstabCellCountTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final int expectedNumberOfElements)
	{
		this.elementCountTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			expectedNumberOfElements);
	}
	
	protected void crosstabCellValueTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final String... values)
	{
		this.elementValueTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			values);
	}
	
	protected void crosstabCellFullValueTest(
		final CrosstabMeasureBuilder<?> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup,
		final String... values)
	{
		this.elementFullValueTest(
			this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(measure, rowGroup, columnGroup),
			values);
	}
	
	protected String getPrefix(final int index)
	{
		return this.crosstabBand + ".crosstab" + index + ".";
	}
}
