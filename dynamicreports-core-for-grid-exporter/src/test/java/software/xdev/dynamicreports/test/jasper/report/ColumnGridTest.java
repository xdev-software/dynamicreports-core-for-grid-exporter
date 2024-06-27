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
package software.xdev.dynamicreports.test.jasper.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnGridTest extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private TextColumnBuilder<Integer> column4;
	private TextColumnBuilder<Integer> column5;
	private TextColumnBuilder<Integer> column6;
	private TextColumnBuilder<Integer> column7;
	private TextColumnBuilder<Integer> column8;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Double> subtotal3;
	private AggregationSubtotalBuilder<Integer> subtotal4;
	private AggregationSubtotalBuilder<Double> subtotal5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setTextStyle(stl.style().setPadding(1))
			.columnGrid(ListType.HORIZONTAL_FLOW)
			.columns(
				this.column1 = col.column("Column1", "field1", Integer.class),
				this.column2 = col.column("Column2", "field2", Integer.class),
				this.column3 = col.column("Column3", "field3", Integer.class),
				this.column4 = col.column("Column4", "field4", Integer.class),
				this.column5 = col.column("Column5", "field5", Integer.class),
				this.column6 = col.column("Column6", "field6", Integer.class),
				this.column7 = col.column("Column7", "field7", Integer.class),
				this.column8 = col.column("Column8", "field8", Integer.class))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(this.column1),
				this.subtotal2 = sbt.sum(this.column5),
				this.subtotal3 = sbt.aggregate(this.column5, Calculation.AVERAGE),
				this.subtotal4 = sbt.sum(this.column7),
				this.subtotal5 = sbt.aggregate(this.column7, Calculation.AVERAGE));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// column header
		// elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 28);
		
		this.elementPositionTest("columnHeader.list2", 0, 10, 10, 575, 14);
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 115, 14);
		this.columnTitlePositionTest(this.column2, 0, 115, 0, 115, 14);
		this.columnTitlePositionTest(this.column3, 0, 230, 0, 115, 14);
		this.columnTitlePositionTest(this.column4, 0, 345, 0, 115, 14);
		this.columnTitlePositionTest(this.column5, 0, 460, 0, 115, 14);
		
		this.elementPositionTest("columnHeader.list3", 0, 10, 24, 575, 14);
		this.columnTitlePositionTest(this.column6, 0, 0, 0, 191, 14);
		this.columnTitlePositionTest(this.column7, 0, 191, 0, 192, 14);
		this.columnTitlePositionTest(this.column8, 0, 383, 0, 192, 14);
		
		// detail
		// elementPositionTest("detail.list1", 0, 10, 38, 575, 28);
		
		this.elementPositionTest("detail.list2", 0, 10, 38, 575, 14);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 115, 14);
		this.columnDetailPositionTest(this.column2, 0, 115, 0, 115, 14);
		this.columnDetailPositionTest(this.column3, 0, 230, 0, 115, 14);
		this.columnDetailPositionTest(this.column4, 0, 345, 0, 115, 14);
		this.columnDetailPositionTest(this.column5, 0, 460, 0, 115, 14);
		
		this.elementPositionTest("detail.list3", 0, 10, 52, 575, 14);
		this.columnDetailPositionTest(this.column6, 0, 0, 0, 191, 14);
		this.columnDetailPositionTest(this.column7, 0, 191, 0, 192, 14);
		this.columnDetailPositionTest(this.column8, 0, 383, 0, 192, 14);
		
		// subtotal
		// elementPositionTest("summary.list1", 0, 10, 318, 575, 56);
		
		this.elementPositionTest("summary.list2", 0, 10, 318, 575, 28);
		this.subtotalPositionTest(this.subtotal1, 0, 0, 0, 115, 14);
		// elementPositionTest("summary.list3", 0, 460, 0, 115, 28);
		this.subtotalPositionTest(this.subtotal2, 0, 460, 0, 115, 14);
		this.subtotalIndexPositionTest(this.subtotal3, 2, 0, 460, 14, 115, 14);
		
		this.elementPositionTest("summary.list4", 0, 10, 346, 575, 28);
		// elementPositionTest("summary.list5", 0, 191, 0, 192, 28);
		this.subtotalPositionTest(this.subtotal4, 0, 191, 0, 192, 14);
		this.subtotalIndexPositionTest(this.subtotal5, 2, 0, 191, 14, 192, 14);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource =
			new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add(1, 1, 1, 1, 1, 1, 1, 1);
		}
		return dataSource;
	}
}
