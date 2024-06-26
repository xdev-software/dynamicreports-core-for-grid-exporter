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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Column4Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
			this.column1 = col.column("Column1", "field1", type.stringType())
				.setPrintRepeatedDetailValues(false)
				.setPrintInFirstWholeBand(true),
			this.column2 = col.column("Column2", "field2", type.integerType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		// column1
		this.columnDetailCountTest(this.column1, 3);
		this.columnDetailValueTest(this.column1, "test1", "test2", "test2");
		this.columnTitleCountTest(this.column1, 2);
		this.columnTitleValueTest(this.column1, "Column1", "Column1");
		// column2
		this.columnDetailCountTest(this.column2, 60);
		this.columnDetailValueTest(this.column2, 0, "0");
		this.columnTitleCountTest(this.column2, 2);
		this.columnTitleValueTest(this.column2, "Column2", "Column2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("test1", i);
		}
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("test2", i);
		}
		return dataSource;
	}
}
