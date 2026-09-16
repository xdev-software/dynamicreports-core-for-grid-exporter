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
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TableOfContentsPosition1Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private ColumnGroupBuilder group1;
	private ColumnGroupBuilder group2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.tableOfContents()
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.stringType()),
				col.column("Column3", "field3", type.stringType()))
			.groupBy(this.group1 = grp.group(this.column1), this.group2 = grp.group(this.column2));
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
			this.elementPositionTest("detail.textField1", i, 0, 0, 276, 16);
			this.elementPositionTest("detail.textField2", i, 276, 0, 277, 16);
			this.elementPositionTest("detail.textField3", i, 553, 0, 22, 16);
		}
		
		for(int i = 0; i < 9; i++)
		{
			this.elementPositionTest("detail.textField4", i, 10, 0, 266, 16);
			this.elementPositionTest("detail.textField5", i, 286, 0, 267, 16);
			this.elementPositionTest("detail.textField6", i, 553, 0, 22, 16);
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
		
		String name = "groupHeaderTitleAndValue.group_" + this.group1.getGroup().getName() + ".tocReference1";
		for(int i = 0; i < 3; i++)
		{
			this.elementPositionTest(name, i, 0, 0, 0, 16);
			this.groupHeaderPositionTest(this.group1, i, 0, 0, 575, 16);
		}
		name = "groupHeaderTitleAndValue.list1";
		this.elementPositionTest(name, 0, 10, 26, 575, 16);
		this.elementPositionTest(name, 1, 10, 474, 575, 16);
		this.elementPositionTest(name, 2, 10, 122, 575, 16);
		
		name = "groupHeaderTitleAndValue.group_" + this.group2.getGroup().getName() + ".tocReference1";
		for(int i = 0; i < 9; i++)
		{
			this.elementPositionTest(name, i, 0, 0, 0, 16);
			this.groupHeaderPositionTest(this.group2, i, 10, 0, 565, 16);
		}
		name = "groupHeaderTitleAndValue.list2";
		this.elementPositionTest(name, 0, 10, 42, 575, 16);
		this.elementPositionTest(name, 1, 10, 186, 575, 16);
		this.elementPositionTest(name, 2, 10, 330, 575, 16);
		this.elementPositionTest(name, 3, 10, 490, 575, 16);
		this.elementPositionTest(name, 4, 10, 634, 575, 16);
		this.elementPositionTest(name, 5, 10, 778, 575, 16);
		this.elementPositionTest(name, 6, 10, 138, 575, 16);
		this.elementPositionTest(name, 7, 10, 282, 575, 16);
		this.elementPositionTest(name, 8, 10, 426, 575, 16);
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
