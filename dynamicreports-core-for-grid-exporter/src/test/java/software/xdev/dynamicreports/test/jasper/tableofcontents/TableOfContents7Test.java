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
package software.xdev.dynamicreports.test.jasper.tableofcontents;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TableOfContents7Test extends AbstractJasperValueTest
{
	private ColumnGroupBuilder group1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", type.stringType());
		this.group1 = grp.group(column1).setReprintHeaderOnEachPage(true);
		
		rb.tableOfContents().columns(column1, col.column("Column2", "field2", type.stringType())).groupBy(this.group1);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		this.elementCountTest("detail.textField1", 3);
		this.elementValueTest("detail.textField1", "value1", "value2", "value3");
		
		this.groupHeaderCountTest(this.group1, 4);
		this.groupHeaderValueTest(this.group1, "value1", "value2", "value2", "value3");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("value1", "text");
		}
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("value2", "text");
		}
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("value3", "text");
		}
		return dataSource;
	}
}
