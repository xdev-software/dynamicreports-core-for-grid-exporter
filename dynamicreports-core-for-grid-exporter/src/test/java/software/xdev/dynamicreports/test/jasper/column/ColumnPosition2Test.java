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

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnPosition2Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<Integer> column3;
	private TextColumnBuilder<Integer> column4;
	private TextColumnBuilder<Integer> column5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", Integer.class),
				this.column2 = col.column("Very very very very very very long Column2 title", "field2", String.class),
				this.column3 = col.column("Column3", "field3", Integer.class),
				this.column4 = col.column("Column4", "field4", Integer.class).setWidth(50),
				this.column5 = col.column("Column5", "field5", Integer.class))
			.columnGrid(this.column1,
				grid.horizontalColumnGridList().add(this.column2, this.column3)
					.newRow().add(this.column4, this.column5));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 42);
		// elementPositionTest("columnHeader.list2", 0, 191, 0, 384, 42);
		this.elementPositionTest("columnHeader.list3", 0, 191, 0, 384, 26);
		this.elementPositionTest("columnHeader.list4", 0, 191, 26, 384, 16);
		
		this.elementPositionTest("detail.list1", 0, 10, 52, 575, 32);
		// elementPositionTest("detail.list2", 0, 191, 0, 384, 32);
		this.elementPositionTest("detail.list3", 0, 191, 0, 384, 16);
		this.elementPositionTest("detail.list4", 0, 191, 16, 384, 16);
		
		this.elementPositionTest("detail.list1", 1, 10, 84, 575, 42);
		// elementPositionTest("detail.list2", 1, 191, 0, 384, 42);
		this.elementPositionTest("detail.list3", 1, 191, 0, 384, 26);
		this.elementPositionTest("detail.list4", 1, 191, 26, 384, 16);
		
		// column1
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 191, 42);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 191, 32);
		this.columnDetailPositionTest(this.column1, 1, 0, 0, 191, 42);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 0, 0, 192, 26);
		this.columnDetailPositionTest(this.column2, 0, 0, 0, 192, 16);
		this.columnDetailPositionTest(this.column2, 1, 0, 0, 192, 26);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 192, 0, 192, 26);
		this.columnDetailPositionTest(this.column3, 0, 192, 0, 192, 16);
		this.columnDetailPositionTest(this.column3, 1, 192, 0, 192, 26);
		// column4
		this.columnTitlePositionTest(this.column4, 0, 0, 0, 128, 16);
		this.columnDetailPositionTest(this.column4, 0, 0, 0, 128, 16);
		this.columnDetailPositionTest(this.column4, 1, 0, 0, 128, 16);
		// column5
		this.columnTitlePositionTest(this.column5, 0, 128, 0, 256, 16);
		this.columnDetailPositionTest(this.column5, 0, 128, 0, 256, 16);
		this.columnDetailPositionTest(this.column5, 1, 128, 0, 256, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		dataSource.add(1, "2", 3, 4, 5);
		dataSource.add(1, "very very very very very very very long value 2", 3, 4, 5);
		return dataSource;
	}
}
