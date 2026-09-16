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
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TableOfContentsPosition3Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setPageFormat(PageType.A6, PageOrientation.LANDSCAPE)
			.setPageMargin(margin(10).setLeft(30))
			.tableOfContents()
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				col.column("Column2", "field2", type.stringType()))
			.groupBy(this.column1);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(5);
		
		this.elementPositionTest("title.textField1", 0, 30, 10, 381, 19);
		
		for(int i = 0; i < 14; i++)
		{
			this.elementPositionTest("detail.list2", i, 30, 49 + 16 * i, 381, 16);
			this.elementPositionTest("detail.textField1", i, 0, 0, 179, 16);
			this.elementPositionTest("detail.textField2", i, 179, 0, 180, 16);
			this.elementPositionTest("detail.textField3", i, 359, 0, 22, 16);
		}
		for(int i = 14; i < 20; i++)
		{
			this.elementPositionTest("detail.list2", i, 30, 10 + 16 * (i - 14), 381, 16);
			this.elementPositionTest("detail.textField1", i, 0, 0, 179, 16);
			this.elementPositionTest("detail.textField2", i, 179, 0, 180, 16);
			this.elementPositionTest("detail.textField3", i, 359, 0, 22, 16);
		}
		
		this.containsElement("title.textField1", 0);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 20; i++)
		{
			dataSource.add("value" + i, "text");
		}
		return dataSource;
	}
}
