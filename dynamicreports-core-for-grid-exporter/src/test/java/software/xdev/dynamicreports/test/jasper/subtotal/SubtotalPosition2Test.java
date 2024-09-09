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
import static software.xdev.dynamicreports.report.builder.DynamicReports.grid;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class SubtotalPosition2Test extends AbstractJasperPositionTest
{
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<Integer> column4;
	private TextColumnBuilder<Integer> column5;
	private TextColumnBuilder<String> column6;
	private TextColumnBuilder<Integer> column7;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder textStyle = stl.style(stl.pen1Point()).setPadding(2);
		final StyleBuilder columnStyle = stl.style(textStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
		
		rb.setTextStyle(textStyle)
			.setColumnStyle(columnStyle)
			.columns(
				this.column1 = col.column("", "field1", type.stringType()).setFixedWidth(200),
				this.column2 = col.column("", "field2", type.stringType()),
				this.column3 = col.column("", "field3", type.stringType()),
				this.column4 = col.column("", "field4", type.integerType()).setRows(2),
				this.column5 = col.column("", "field5", type.integerType()),
				this.column6 = col.column("", "field6", type.stringType()).setFixedColumns(3),
				this.column7 = col.column("", "field7", type.integerType()).setTitleRows(2))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(this.column4),
				this.subtotal2 = sbt.sum(this.column5).setRows(2),
				this.subtotal3 = sbt.sum(this.column7))
			.columnGrid(
				this.column1,
				this.column2,
				this.column3,
				grid.horizontalColumnGridList().add(this.column4)
					.newRow().add(this.column5, this.column6, this.column7));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// columns
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 43);
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 200, 43);
		this.columnTitlePositionTest(this.column2, 0, 200, 0, 87, 43);
		this.columnTitlePositionTest(this.column3, 0, 287, 0, 87, 43);
		// elementPositionTest("columnHeader.list2", 0, 374, 0, 201, 43);
		this.columnTitlePositionTest(this.column4, 0, 374, 0, 201, 16);
		this.elementPositionTest("columnHeader.list3", 0, 374, 16, 201, 27);
		this.columnTitlePositionTest(this.column5, 0, 0, 0, 85, 27);
		this.columnTitlePositionTest(this.column6, 0, 85, 0, 31, 27);
		this.columnTitlePositionTest(this.column7, 0, 116, 0, 85, 27);
		
		this.elementPositionTest("detail.list1", 0, 10, 53, 575, 43);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 200, 43);
		this.columnDetailPositionTest(this.column2, 0, 200, 0, 87, 43);
		this.columnDetailPositionTest(this.column3, 0, 287, 0, 87, 43);
		// elementPositionTest("detail.list2", 0, 374, 0, 201, 43);
		this.columnDetailPositionTest(this.column4, 0, 374, 0, 201, 27);
		this.elementPositionTest("detail.list3", 0, 374, 27, 201, 16);
		this.columnDetailPositionTest(this.column5, 0, 0, 0, 85, 16);
		this.columnDetailPositionTest(this.column6, 0, 85, 0, 31, 16);
		this.columnDetailPositionTest(this.column7, 0, 116, 0, 85, 16);
		
		// summary
		this.elementPositionTest("summary.list1", 0, 10, 96, 575, 43);
		// elementPositionTest("summary.list2", 0, 374, 0, 201, 43);
		this.subtotalPositionTest(this.subtotal1, 0, 374, 0, 201, 16);
		this.elementPositionTest("summary.list3", 0, 374, 16, 201, 27);
		this.subtotalPositionTest(this.subtotal2, 0, 0, 0, 85, 27);
		this.subtotalPositionTest(this.subtotal3, 0, 116, 0, 85, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource =
			new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7");
		dataSource.add("", "", "", 1, 1, "", 1);
		return dataSource;
	}
}
