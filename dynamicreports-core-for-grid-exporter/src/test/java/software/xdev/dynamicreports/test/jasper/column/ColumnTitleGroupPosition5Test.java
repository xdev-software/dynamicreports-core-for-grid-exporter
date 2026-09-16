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
import static software.xdev.dynamicreports.report.builder.DynamicReports.grid;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnTitleGroupPosition5Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<String> column4;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.column1 = col.column("Col1", "field1", String.class);
		this.column2 = col.column("Col2", "field2", Integer.class);
		this.column3 = col.column("Col3", "field3", String.class).setFixedWidth(50);
		this.column4 = col.column("Col4", "field4", String.class);
		
		final ColumnTitleGroupBuilder titleGroup2 =
			grid.titleGroup("Group 2", this.column3, this.column4).setTitleFixedWidth(150);
		final ColumnTitleGroupBuilder titleGroup1 =
			grid.titleGroup("Group 1", this.column2, titleGroup2).setTitleFixedWidth(300);
		
		rb.columnGrid(this.column1, titleGroup1)
			.columns(this.column1, this.column2, this.column3, this.column4)
			.subtotalsAtSummary(this.subtotal1 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 48);
		this.elementPositionTest("columnHeader.list3", 0, 275, 16, 300, 32);
		this.elementPositionTest("columnHeader.list5", 0, 150, 16, 150, 16);
		this.elementPositionTest("columnHeader.columngroup.title1", 0, 275, 0, 300, 16);
		this.elementPositionTest("columnHeader.columngroup.title2", 0, 150, 0, 150, 16);
		// column1
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 275, 48);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 275, 16);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 0, 0, 150, 32);
		this.columnDetailPositionTest(this.column2, 0, 275, 0, 150, 16);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 0, 0, 50, 16);
		this.columnDetailPositionTest(this.column3, 0, 425, 0, 50, 16);
		// column4
		this.columnTitlePositionTest(this.column4, 0, 50, 0, 100, 16);
		this.columnDetailPositionTest(this.column4, 0, 475, 0, 100, 16);
		// subtotal
		this.subtotalPositionTest(this.subtotal1, 0, 275, 0, 150, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add("text", 1, "text", "text");
		return dataSource;
	}
}
