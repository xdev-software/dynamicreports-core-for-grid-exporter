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
package software.xdev.dynamicreports.test.jasper.column;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnGridTest extends AbstractJasperPositionTest
{
	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columnGrid(ListType.VERTICAL)
			.columns(
				this.column1 = col.column("Column1", "field1", Integer.class),
				this.column2 = col.column("Column2", "field2", Integer.class),
				this.column3 = col.column("Column3", "field3", Integer.class))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(this.column1),
				this.subtotal2 = sbt.sum(this.column2),
				this.subtotal3 = sbt.sum(this.column3));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnTitlePositionTest(this.column1, 0, 10, 10, 575, 16);
		this.columnDetailPositionTest(this.column1, 0, 10, 58, 575, 16);
		this.columnDetailPositionTest(this.column1, 1, 10, 106, 575, 16);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 10, 26, 575, 16);
		this.columnDetailPositionTest(this.column2, 0, 10, 74, 575, 16);
		this.columnDetailPositionTest(this.column2, 1, 10, 122, 575, 16);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 10, 42, 575, 16);
		this.columnDetailPositionTest(this.column3, 0, 10, 90, 575, 16);
		this.columnDetailPositionTest(this.column3, 1, 10, 138, 575, 16);
		// subtotal
		this.subtotalPositionTest(this.subtotal1, 0, 10, 154, 575, 16);
		this.subtotalPositionTest(this.subtotal2, 0, 10, 170, 575, 16);
		this.subtotalPositionTest(this.subtotal3, 0, 10, 186, 575, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add(1, 2, 3);
		dataSource.add(1, 2, 3);
		return dataSource;
	}
}
