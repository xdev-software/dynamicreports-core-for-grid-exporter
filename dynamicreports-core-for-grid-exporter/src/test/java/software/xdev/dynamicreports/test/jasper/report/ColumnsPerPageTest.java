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
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnsPerPageTest extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private TextColumnBuilder<Integer> column4;
	private TextColumnBuilder<Integer> column5;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<?> subtotal3;
	private AggregationSubtotalBuilder<Integer> subtotal4;
	private AggregationSubtotalBuilder<?> subtotal5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setTextStyle(stl.style().setPadding(1))
			.setPageColumnSpace(10)
			.setPageColumnsPerPage(3)
			.columnGrid(ListType.HORIZONTAL_FLOW)
			.columns(
				this.column1 = col.column("Column1", "field1", Integer.class).setWidth(50),
				this.column2 = col.column("Column2", "field2", Integer.class).setWidth(50),
				this.column3 = col.column("Column3", "field3", Integer.class).setWidth(50),
				this.column4 = col.column("Column4", "field4", Integer.class).setWidth(50),
				this.column5 = col.column("Column5", "field5", Integer.class).setWidth(50))
			.subtotalsAtColumnFooter(
				this.subtotal1 = sbt.sum(this.column1).setLabel("sum"),
				this.subtotal2 = sbt.sum(this.column3).setLabel("sum"),
				this.subtotal3 = sbt.aggregate(this.column3, Calculation.AVERAGE).setLabel("avg"),
				this.subtotal4 = sbt.sum(this.column4).setLabel("sum"),
				this.subtotal5 = sbt.aggregate(this.column4, Calculation.AVERAGE).setLabel("avg"))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(this.column1).setLabel("sum"),
				this.subtotal2 = sbt.sum(this.column3).setLabel("sum"),
				this.subtotal3 = sbt.aggregate(this.column3, Calculation.AVERAGE).setLabel("avg"),
				this.subtotal4 = sbt.sum(this.column4).setLabel("sum"),
				this.subtotal5 = sbt.aggregate(this.column4, Calculation.AVERAGE).setLabel("avg"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		// column header
		// elementPositionTest("columnHeader.list1", 0, 10, 10, 185, 28);
		// elementPositionTest("columnHeader.list1", 1, 205, 10, 185, 28);
		// elementPositionTest("columnHeader.list1", 2, 400, 10, 185, 28);
		// elementPositionTest("columnHeader.list1", 3, 10, 10, 185, 28);
		
		this.elementPositionTest("columnHeader.list2", 0, 10, 10, 185, 14);
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 61, 14);
		this.columnTitlePositionTest(this.column2, 0, 61, 0, 62, 14);
		this.columnTitlePositionTest(this.column3, 0, 123, 0, 62, 14);
		
		this.elementPositionTest("columnHeader.list3", 0, 10, 24, 185, 14);
		this.columnTitlePositionTest(this.column4, 0, 0, 0, 92, 14);
		this.columnTitlePositionTest(this.column5, 0, 92, 0, 93, 14);
		
		// detail
		// elementPositionTest("detail.list1", 0, 10, 38, 185, 28);
		// elementPositionTest("detail.list1", 24, 205, 38, 185, 28);
		// elementPositionTest("detail.list1", 48, 400, 38, 185, 28);
		// elementPositionTest("detail.list1", 72, 10, 38, 185, 28);
		
		this.elementPositionTest("detail.list2", 0, 10, 38, 185, 14);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 61, 14);
		this.columnDetailPositionTest(this.column2, 0, 61, 0, 62, 14);
		this.columnDetailPositionTest(this.column3, 0, 123, 0, 62, 14);
		
		this.elementPositionTest("detail.list3", 0, 10, 52, 185, 14);
		this.columnDetailPositionTest(this.column4, 0, 0, 0, 92, 14);
		this.columnDetailPositionTest(this.column5, 0, 92, 0, 93, 14);
		
		// subtotal at column footer
		// elementPositionTest("columnFooter.list1", 0, 10, 720, 185, 112);
		// elementPositionTest("columnFooter.list1", 1, 205, 720, 185, 112);
		// elementPositionTest("columnFooter.list1", 2, 400, 720, 185, 112);
		// elementPositionTest("columnFooter.list1", 3, 10, 720, 185, 112);
		
		this.elementPositionTest("columnFooter.list2", 0, 10, 720, 185, 56);
		// elementPositionTest("columnFooter.list3", 0, 0, 0, 61, 28);
		this.subtotalLabelPositionTest(this.subtotal1, 0, 0, 0, 61, 14);
		this.subtotalPositionTest(this.subtotal1, 0, 0, 14, 61, 14);
		
		// elementPositionTest("columnFooter.list4", 0, 123, 0, 62, 56);
		// elementPositionTest("columnFooter.list5", 0, 0, 0, 62, 28);
		this.subtotalLabelPositionTest(this.subtotal2, 0, 123, 0, 62, 14);
		this.subtotalPositionTest(this.subtotal2, 0, 123, 14, 62, 14);
		// elementPositionTest("columnFooter.list6", 0, 0, 28, 62, 28);
		this.subtotalLabelPositionTest(this.subtotal3, 0, 123, 0, 62, 14);
		this.subtotalPositionTest(this.subtotal3, 0, 123, 14, 62, 14);
		
		this.elementPositionTest("columnFooter.list7", 0, 10, 776, 185, 56);
		// elementPositionTest("columnFooter.list8", 0, 0, 0, 92, 56);
		// elementPositionTest("columnFooter.list9", 0, 0, 0, 92, 28);
		this.subtotalLabelPositionTest(this.subtotal4, 0, 0, 0, 92, 14);
		this.subtotalPositionTest(this.subtotal4, 0, 0, 14, 92, 14);
		// elementPositionTest("columnFooter.list10", 0, 0, 28, 92, 28);
		this.subtotalLabelPositionTest(this.subtotal5, 0, 0, 0, 92, 14);
		this.subtotalPositionTest(this.subtotal5, 0, 0, 14, 92, 14);
		
		// subtotal at summary
		this.elementPositionTest("summary.list1", 0, 10, 262, 575, 112);
		
		// elementPositionTest("summary.list2", 0, 0, 0, 185, 112);
		this.elementPositionTest("summary.list3", 0, 0, 0, 185, 56);
		this.subtotalLabelPositionTest(this.subtotal1, 0, 0, 0, 61, 14);
		this.subtotalPositionTest(this.subtotal1, 0, 0, 14, 61, 14);
		
		// elementPositionTest("summary.list4", 0, 0, 0, 61, 28);
		// elementPositionTest("summary.list5", 0, 123, 0, 62, 56);
		this.subtotalLabelPositionTest(this.subtotal2, 0, 123, 0, 62, 14);
		this.subtotalPositionTest(this.subtotal2, 0, 123, 14, 62, 14);
		// elementPositionTest("summary.list6", 0, 0, 0, 62, 28);
		this.subtotalLabelPositionTest(this.subtotal3, 0, 123, 0, 62, 14);
		this.subtotalPositionTest(this.subtotal3, 0, 123, 14, 62, 14);
		
		// elementPositionTest("summary.list7", 0, 0, 28, 62, 28);
		this.elementPositionTest("summary.list8", 0, 0, 56, 185, 56);
		// elementPositionTest("summary.list9", 0, 0, 0, 92, 56);
		this.subtotalLabelPositionTest(this.subtotal4, 0, 0, 0, 92, 14);
		this.subtotalPositionTest(this.subtotal4, 0, 0, 14, 92, 14);
		// elementPositionTest("summary.list10", 0, 0, 0, 92, 28);
		this.subtotalLabelPositionTest(this.subtotal5, 0, 0, 0, 92, 14);
		this.subtotalPositionTest(this.subtotal5, 0, 0, 14, 92, 14);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		for(int i = 0; i < 80; i++)
		{
			dataSource.add(1, 1, 1, 1, 1);
		}
		return dataSource;
	}
}
