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
package software.xdev.dynamicreports.test.jasper.subtotal;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class SubtotalPosition4Test extends AbstractJasperPositionTest
{
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private ColumnGroupBuilder group1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()).setFixedWidth(540),
				this.column2 = col.column("Column2", "field2", type.integerType()))
			.groupBy(this.group1 = grp.group(this.column1).setHeaderWithSubtotal(true))
			.subtotalsAtFirstGroupHeader(this.subtotal1 = sbt.sum(this.column2))
			.subtotalsAtSummary(this.subtotal2 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// columns
		this.columnDetailPositionTest(this.column2, 0, 10, 0, 565, 16);
		
		// group
		this.groupHeaderPositionTest(this.group1, 0, 10, 26, 575, 16);
		this.groupHeaderPositionTest(this.group1, 1, 10, 74, 575, 16);
		
		// group header
		this.elementPositionTest("subtotalGroupHeader.list1", 0, 10, 26, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 10, 0, 565, 16);
		this.elementPositionTest("subtotalGroupHeader.list1", 1, 10, 74, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 10, 0, 565, 16);
		
		// summary
		this.elementPositionTest("summary.list1", 0, 10, 122, 575, 16);
		this.subtotalPositionTest(this.subtotal2, 0, 10, 0, 565, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add("group1", 1);
		dataSource.add("group1", 2);
		dataSource.add("group2", 3);
		dataSource.add("group2", 4);
		return dataSource;
	}
}
