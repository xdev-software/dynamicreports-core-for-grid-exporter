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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class IgnorePageWidthTest extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<String> column4;
	private TextColumnBuilder<String> column5;
	private TextColumnBuilder<String> column6;
	private TextColumnBuilder<String> column7;
	private TextColumnBuilder<String> column8;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.ignorePageWidth()
			.title(cmp.text("text"))
			.columns(
				this.column1 = col.column("Column2", "field1", String.class),
				this.column2 = col.column("Column2", "field2", String.class),
				this.column3 = col.column("Column3", "field3", String.class),
				this.column4 = col.column("Column4", "field4", String.class).setWidth(150),
				this.column5 = col.column("Column5", "field5", String.class).setFixedWidth(50),
				this.column6 = col.column("Column6", "field6", String.class),
				this.column7 = col.column("Column7", "field7", String.class),
				this.column8 = col.column("Column8", "field8", String.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.textField1", 0, 10, 10, 800, 16);
		this.elementPositionTest("columnHeader.list1", 0, 10, 26, 800, 16);
		
		// column1
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 100, 16);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 100, 16);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 100, 0, 100, 16);
		this.columnDetailPositionTest(this.column2, 0, 100, 0, 100, 16);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 200, 0, 100, 16);
		this.columnDetailPositionTest(this.column3, 0, 200, 0, 100, 16);
		// column4
		this.columnTitlePositionTest(this.column4, 0, 300, 0, 150, 16);
		this.columnDetailPositionTest(this.column4, 0, 300, 0, 150, 16);
		// column5
		this.columnTitlePositionTest(this.column5, 0, 450, 0, 50, 16);
		this.columnDetailPositionTest(this.column5, 0, 450, 0, 50, 16);
		// column6
		this.columnTitlePositionTest(this.column6, 0, 500, 0, 100, 16);
		this.columnDetailPositionTest(this.column6, 0, 500, 0, 100, 16);
		// column7
		this.columnTitlePositionTest(this.column7, 0, 600, 0, 100, 16);
		this.columnDetailPositionTest(this.column7, 0, 600, 0, 100, 16);
		// column8
		this.columnTitlePositionTest(this.column8, 0, 700, 0, 100, 16);
		this.columnDetailPositionTest(this.column8, 0, 700, 0, 100, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource =
			new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8");
		dataSource.add("text", "text", "text", "text", "text", "text", "text", "text");
		return dataSource;
	}
}
