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
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnTitleGroupPosition2Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<String> column3;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.column1 = col.column("Column1", "field1", String.class);
		this.column2 = col.column("Column2", "field2", Integer.class);
		this.column3 = col.column("Column3", "field3", String.class).setFixedWidth(50);
		
		rb.columnGrid(this.column1, grid.titleGroup("Group1", this.column2, this.column3))
			.columns(this.column1, this.column2, this.column3)
			.subtotalsAtSummary(this.subtotal1 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 32);
		this.elementPositionTest("columnHeader.list3", 0, 230, 16, 345, 16);
		this.elementPositionTest("columnHeader.columngroup.title1", 0, 230, 0, 345, 16);
		// column1
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 230, 32);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 230, 16);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 0, 0, 295, 16);
		this.columnDetailPositionTest(this.column2, 0, 230, 0, 295, 16);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 295, 0, 50, 16);
		this.columnDetailPositionTest(this.column3, 0, 525, 0, 50, 16);
		// subtotal
		this.subtotalPositionTest(this.subtotal1, 0, 230, 0, 295, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("text", 1, "text");
		return dataSource;
	}
}
