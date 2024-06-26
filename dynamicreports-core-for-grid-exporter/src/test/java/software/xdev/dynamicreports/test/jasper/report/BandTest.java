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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class BandTest extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<Integer> column2;
	private ColumnGroupBuilder group1;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	private AggregationSubtotalBuilder<Integer> subtotal4;
	private AggregationSubtotalBuilder<Integer> subtotal5;
	private AggregationSubtotalBuilder<Integer> subtotal6;
	private AggregationSubtotalBuilder<Integer> subtotal7;
	private AggregationSubtotalBuilder<Integer> subtotal8;
	private AggregationSubtotalBuilder<Integer> subtotal9;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.setPageColumnsPerPage(2)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class).setWidth(500))
			.groupBy(this.group1 = grp.group(column1))
			
			.subtotalsAtTitle(this.subtotal1 = sbt.sum(this.column2))
			.subtotalsAtPageHeader(this.subtotal2 = sbt.sum(this.column2))
			.subtotalsAtPageFooter(this.subtotal3 = sbt.sum(this.column2))
			.subtotalsAtColumnHeader(this.subtotal4 = sbt.sum(this.column2))
			.subtotalsAtColumnFooter(this.subtotal5 = sbt.sum(this.column2))
			.subtotalsAtGroupHeader(this.group1, this.subtotal6 = sbt.sum(this.column2))
			.subtotalsAtGroupFooter(this.group1, this.subtotal7 = sbt.sum(this.column2))
			.subtotalsAtLastPageFooter(this.subtotal8 = sbt.sum(this.column2))
			.subtotalsAtSummary(this.subtotal9 = sbt.sum(this.column2))
			
			.title(cmp.text("title"))
			.pageHeader(cmp.text("pageHeader"))
			.pageFooter(cmp.text("pageFooter"))
			.columnHeader(cmp.text("columnHeader"))
			.columnFooter(cmp.text("columnFooter"))
			.groupHeader(this.group1, cmp.text("groupHeader"))
			.groupFooter(this.group1, cmp.text("groupFooter"))
			.detail(cmp.text("detail"))
			.lastPageFooter(cmp.text("lastPageFooter"))
			.summary(cmp.text("summary"))
			.background(cmp.text("background"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		// title
		// elementPositionTest("title.list1", 0, 10, 10, 575, 32);
		this.elementPositionTest("title.textField1", 0, 10, 10, 575, 16);
		this.elementPositionTest("title.list2", 0, 10, 26, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 10, 0, 277, 16);
		
		// page header
		// elementPositionTest("pageHeader.list1", 0, 10, 42, 575, 32);
		this.elementPositionTest("pageHeader.textField1", 0, 10, 42, 575, 16);
		this.elementPositionTest("pageHeader.list2", 0, 10, 58, 575, 16);
		this.subtotalPositionTest(this.subtotal2, 0, 10, 0, 277, 16);
		
		// page footer
		// elementPositionTest("pageFooter.list1", 0, 10, 800, 575, 32);
		this.elementPositionTest("pageFooter.list2", 0, 10, 800, 575, 16);
		this.subtotalPositionTest(this.subtotal3, 0, 10, 0, 277, 16);
		this.elementPositionTest("pageFooter.textField1", 0, 10, 816, 575, 16);
		
		// column header
		// elementPositionTest("columnHeader.list1", 0, 10, 74, 287, 48);
		this.elementPositionTest("columnHeader.list2", 0, 10, 74, 287, 16);
		this.columnTitlePositionTest(this.column2, 0, 10, 0, 277, 16);
		this.elementPositionTest("columnHeader.textField1", 0, 10, 90, 287, 16);
		this.elementPositionTest("columnHeader.list3", 0, 10, 106, 287, 16);
		this.subtotalPositionTest(this.subtotal4, 0, 10, 0, 277, 16);
		
		// column footer
		// elementPositionTest("columnFooter.list1", 0, 10, 768, 287, 32);
		this.elementPositionTest("columnFooter.list2", 0, 10, 768, 287, 16);
		this.subtotalPositionTest(this.subtotal5, 0, 10, 0, 277, 16);
		this.elementPositionTest("columnFooter.textField1", 0, 10, 784, 287, 16);
		
		// group header
		this.groupHeaderPositionTest(this.group1, 0, 10, 122, 287, 16);
		this.elementPositionTest("groupHeader.textField1", 0, 10, 138, 287, 16);
		this.elementPositionTest("subtotalGroupHeader.list1", 0, 10, 154, 287, 16);
		this.subtotalPositionTest(this.subtotal6, 0, 10, 0, 277, 16);
		
		// group footer
		this.elementPositionTest("subtotalGroupFooter.list1", 0, 297, 346, 287, 16);
		this.subtotalPositionTest(this.subtotal7, 0, 10, 0, 277, 16);
		this.elementPositionTest("groupFooter.textField1", 0, 297, 362, 287, 16);
		
		// detail
		// elementPositionTest("detail.list1", 0, 10, 170, 287, 32);
		this.elementPositionTest("detail.textField1", 0, 10, 170, 287, 16);
		this.elementPositionTest("detail.list2", 0, 10, 186, 287, 16);
		this.columnDetailPositionTest(this.column2, 0, 10, 0, 277, 16);
		
		// last page footer
		// elementPositionTest("lastPageFooter.list1", 0, 10, 800, 575, 32);
		this.elementPositionTest("lastPageFooter.list2", 0, 10, 800, 575, 16);
		this.subtotalPositionTest(this.subtotal8, 0, 10, 0, 277, 16);
		this.elementPositionTest("lastPageFooter.textField1", 0, 10, 816, 575, 16);
		
		// summary
		// elementPositionTest("summary.list1", 0, 10, 602, 575, 32);
		this.elementPositionTest("summary.list2", 0, 10, 602, 575, 16);
		this.subtotalPositionTest(this.subtotal9, 0, 10, 0, 277, 16);
		this.elementPositionTest("summary.textField1", 0, 10, 618, 575, 16);
		
		// background
		this.elementPositionTest("background.textField1", 0, 10, 10, 575, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 25; i++)
		{
			dataSource.add("group1", i);
		}
		for(int i = 0; i < 25; i++)
		{
			dataSource.add("group2", i);
		}
		return dataSource;
	}
}
