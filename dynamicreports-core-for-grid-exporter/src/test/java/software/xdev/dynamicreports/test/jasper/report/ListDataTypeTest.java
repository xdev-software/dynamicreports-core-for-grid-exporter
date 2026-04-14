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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class ListDataTypeTest extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	@SuppressWarnings("rawtypes")
	private TextColumnBuilder<List> column2;
	@SuppressWarnings("rawtypes")
	private TextColumnBuilder<List> column3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		
		rb.columns(
			this.column1 = col.column("Column1", "field1", String.class),
			this.column2 = col.column("Column2", "field2", List.class),
			this.column3 = col.column("Column3", "field2", type.listType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnDetailValueTest(this.column1, "row1", "row2");
		this.columnDetailValueTest(this.column2, "comment1\ncomment2\ncomment3", "comment1\ncomment2");
		this.columnDetailValueTest(this.column3, "comment1\ncomment2\ncomment3", "comment1\ncomment2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		List<String> comments = new ArrayList<>();
		comments.add("comment1");
		comments.add("comment2");
		comments.add("comment3");
		dataSource.add("row1", comments);
		comments = new ArrayList<>();
		comments.add("comment1");
		comments.add("comment2");
		dataSource.add("row2", comments);
		return dataSource;
	}
}
