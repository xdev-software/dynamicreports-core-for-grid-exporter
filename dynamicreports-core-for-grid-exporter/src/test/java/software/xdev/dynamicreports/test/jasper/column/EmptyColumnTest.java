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

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class EmptyColumnTest extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<String> column4;
	private TextColumnBuilder<String> column5;
	private TextColumnBuilder<String> column6;
	private TextColumnBuilder<String> column7;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
			this.column1 = col.column("Column1", "field1", String.class),
			this.column2 = col.emptyColumn(),
			this.column3 = col.column("Column3", "field3", String.class),
			this.column4 = col.emptyColumn(false, true),
			this.column5 = col.emptyColumn(true, false),
			this.column6 = col.emptyColumn(true, true),
			this.column7 = col.column("Column7", "field7", String.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnDetailCountTest(this.column1, 1);
		this.columnDetailValueTest(this.column1, "test1");
		this.columnTitleCountTest(this.column1, 1);
		this.columnTitleValueTest(this.column1, "Column1");
		
		// column2
		this.columnDetailCountTest(this.column2, 0);
		this.columnTitleCountTest(this.column2, 0);
		
		// column3
		this.columnDetailCountTest(this.column3, 1);
		this.columnDetailValueTest(this.column3, "test3");
		this.columnTitleCountTest(this.column3, 1);
		this.columnTitleValueTest(this.column3, "Column3");
		
		// column4
		this.columnDetailCountTest(this.column4, 1);
		this.columnDetailValueTest(this.column4, "");
		this.columnTitleCountTest(this.column4, 0);
		
		// column5
		this.columnDetailCountTest(this.column5, 0);
		this.columnTitleCountTest(this.column5, 1);
		this.columnTitleValueTest(this.column5, "");
		
		// column6
		this.columnDetailCountTest(this.column6, 1);
		this.columnDetailValueTest(this.column6, "");
		this.columnTitleCountTest(this.column6, 1);
		this.columnTitleValueTest(this.column6, "");
		
		// column7
		this.columnDetailCountTest(this.column7, 1);
		this.columnDetailValueTest(this.column7, "test7");
		this.columnTitleCountTest(this.column7, 1);
		this.columnTitleValueTest(this.column7, "Column7");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource =
			new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7");
		dataSource.add("test1", "test2", "test3", "test4", "test5", "test6", "test7");
		return dataSource;
	}
}
