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
package software.xdev.dynamicreports.test.jasper.group;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class GroupPosition2Test extends AbstractJasperPositionTest
{
	private ColumnGroupBuilder group1;
	private ColumnGroupBuilder group2;
	private TextColumnBuilder<Integer> column3;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		final TextColumnBuilder<String> column2;
		
		rb.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", String.class),
				this.column3 = col.column("Column3", "field3", Integer.class))
			.groupBy(
				this.group1 = grp.group(column1),
				this.group2 = grp.group(column2)
					.setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
					.header(cmp.text("header"))
					.footer(cmp.text("footer")))
			.subtotalsAtGroupFooter(this.group2, this.subtotal1 = sbt.sum(this.column3));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
		this.elementPositionTest("columnHeader.filler1", 0, 0, 0, 20, 16);
		this.elementPositionTest("detail.list1", 0, 10, 74, 575, 16);
		this.elementPositionTest("detail.list1", 1, 10, 90, 575, 16);
		
		// group1
		this.groupHeaderPositionTest(this.group1, 0, 10, 26, 575, 16);
		// group2
		this.elementPositionTest("groupHeaderTitleAndValue.list1", 0, 10, 42, 575, 16);
		this.groupHeaderTitlePositionTest(this.group2, 0, 10, 0, 100, 16);
		this.groupHeaderPositionTest(this.group2, 0, 110, 0, 465, 16);
		
		this.elementPositionTest("groupHeader.list1", 0, 10, 58, 575, 16);
		this.elementPositionTest("groupHeader.textField1", 0, 10, 0, 565, 16);
		
		this.elementPositionTest("subtotalGroupFooter.list1", 0, 10, 106, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 20, 0, 555, 16);
		
		this.elementPositionTest("groupFooter.list1", 0, 10, 122, 575, 16);
		this.elementPositionTest("groupFooter.textField1", 0, 10, 0, 565, 16);
		// column3
		this.columnTitlePositionTest(this.column3, 0, 20, 0, 555, 16);
		this.columnDetailPositionTest(this.column3, 0, 20, 0, 555, 16);
		this.columnDetailPositionTest(this.column3, 1, 20, 0, 555, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for(int i = 0; i < 2; i++)
		{
			dataSource.add("group1", "group2", i);
		}
		return dataSource;
	}
}
