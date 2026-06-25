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
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Column2Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setPageColumnsPerPage(2)
			.fields(field("field1", Integer.class))
			.columns(
				this.column1 = col.reportRowNumberColumn("Column1").setWidth(50),
				this.column2 = col.pageRowNumberColumn("Column2").setWidth(50),
				this.column3 = col.columnRowNumberColumn("Column3").setWidth(50));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		final List<String> rows = new ArrayList<>();
		for(int i = 0; i < 110; i++)
		{
			rows.add(String.valueOf(i + 1));
		}
		final List<String> pageRows = new ArrayList<>();
		for(int i = 0; i < 100; i++)
		{
			pageRows.add(String.valueOf(i + 1));
		}
		for(int i = 0; i < 10; i++)
		{
			pageRows.add(String.valueOf(i + 1));
		}
		final List<String> columnRows = new ArrayList<>();
		for(int i = 0; i < 50; i++)
		{
			columnRows.add(String.valueOf(i + 1));
		}
		for(int i = 0; i < 50; i++)
		{
			columnRows.add(String.valueOf(i + 1));
		}
		for(int i = 0; i < 10; i++)
		{
			columnRows.add(String.valueOf(i + 1));
		}
		
		this.numberOfPagesTest(2);
		// column1
		this.columnDetailCountTest(this.column1, 110);
		this.columnDetailValueTest(this.column1, rows.toArray(new String[]{}));
		this.columnTitleCountTest(this.column1, 3);
		this.columnTitleValueTest(this.column1, "Column1", "Column1", "Column1");
		// column2
		this.columnDetailCountTest(this.column2, 110);
		this.columnDetailValueTest(this.column2, pageRows.toArray(new String[]{}));
		this.columnTitleCountTest(this.column2, 3);
		this.columnTitleValueTest(this.column2, "Column2", "Column2", "Column2");
		// column3
		this.columnDetailCountTest(this.column3, 110);
		this.columnDetailValueTest(this.column3, columnRows.toArray(new String[]{}));
		this.columnTitleCountTest(this.column3, 3);
		this.columnTitleValueTest(this.column3, "Column3", "Column3", "Column3");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 110; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
