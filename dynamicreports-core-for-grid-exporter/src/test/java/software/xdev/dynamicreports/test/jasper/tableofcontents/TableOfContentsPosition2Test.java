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
import static software.xdev.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TableOfContentsPosition2Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TableOfContentsCustomizerBuilder tableOfContentsCustomizer =
			tableOfContentsCustomizer().setTextFixedWidth(100).setPageIndexFixedWidth(30);
		
		rb.tableOfContents(tableOfContentsCustomizer)
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.stringType()),
				col.column("Column3", "field3", type.stringType()))
			.groupBy(this.column1, this.column2);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		this.elementPositionTest("title.textField1", 0, 10, 10, 575, 19);
		
		for(int i = 0; i < 3; i++)
		{
			this.elementPositionTest("detail.list3", i, 10, 49 + 64 * i, 575, 16);
			this.elementPositionTest("detail.textField1", i, 0, 0, 100, 16);
			this.elementPositionTest("detail.textField2", i, 100, 0, 445, 16);
			this.elementPositionTest("detail.textField3", i, 545, 0, 30, 16);
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.elementPositionTest("detail.textField4", i, 10, 0, 100, 16);
			this.elementPositionTest("detail.textField5", i, 120, 0, 425, 16);
			this.elementPositionTest("detail.textField6", i, 545, 0, 30, 16);
		}
		
		for(int i = 0; i < 3; i++)
		{
			this.elementPositionTest("detail.list5", i, 10, 65 + 16 * i, 575, 16);
		}
		int index = 0;
		for(int i = 3; i < 6; i++)
		{
			this.elementPositionTest("detail.list5", i, 10, 129 + 16 * index++, 575, 16);
		}
		index = 0;
		for(int i = 6; i < 9; i++)
		{
			this.elementPositionTest("detail.list5", i, 10, 193 + 16 * index++, 575, 16);
		}
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final String[] values = new String[]{"value1", "value2", "value3"};
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for(final String field1 : values)
		{
			for(final String field2 : values)
			{
				for(int i = 0; i < 8; i++)
				{
					dataSource.add(field1, field2, "text");
				}
			}
		}
		return dataSource;
	}
}
