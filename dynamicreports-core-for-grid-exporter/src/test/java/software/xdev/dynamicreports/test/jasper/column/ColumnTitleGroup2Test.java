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
import software.xdev.dynamicreports.jasper.constant.JasperProperty;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class ColumnTitleGroup2Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.column1 = col.column("Column1", "field1", String.class);
		this.column2 = col.column("Column2", "field2", String.class).setFixedWidth(25);
		this.column3 = col.column("Column3", "field3", String.class).setFixedWidth(25);
		
		final ColumnTitleGroupBuilder titleGroup = grid.titleGroup("test test test", this.column2, this.column3)
			.addTitleProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true");
		rb.columnGrid(this.column1, titleGroup).columns(this.column1, this.column2, this.column3);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementValueTest("columnHeader.columngroup.title1", 0, "test test ");
		this.elementFullValueTest("columnHeader.columngroup.title1", 0, "test test test");
		// column1
		this.columnTitleValueTest(this.column1, 0, "Column1");
		this.columnDetailValueTest(this.column1, 0, "text");
		// column2
		this.columnTitleValueTest(this.column2, 0, "Column2");
		this.columnDetailValueTest(this.column2, 0, "text");
		// column3
		this.columnTitleValueTest(this.column3, 0, "Column3");
		this.columnDetailValueTest(this.column3, 0, "text");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("text", "text", "text");
		return dataSource;
	}
}
