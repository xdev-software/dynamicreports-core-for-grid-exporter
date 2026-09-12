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
package software.xdev.dynamicreports.test.jasper.subtotal;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class SubtotalPosition3Test extends AbstractJasperPositionTest
{
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()).setFixedWidth(540),
				this.column2 = col.column("Column2", "field2", type.integerType()))
			.groupBy(grp.group(this.column1).setHideColumn(false).setPadding(0)
				.setHeaderLayout(GroupHeaderLayout.EMPTY))
			.subtotalsAtFirstGroupFooter(this.subtotal1 = sbt.sum(this.column2))
			.subtotalsAtSummary(this.subtotal2 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// columns
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 540, 26);
		this.columnDetailPositionTest(this.column2, 0, 540, 0, 35, 26);
		
		// summary
		this.elementPositionTest("subtotalGroupFooter.list1", 0, 10, 88, 575, 26);
		this.subtotalPositionTest(this.subtotal1, 0, 540, 0, 35, 26);
		this.elementPositionTest("summary.list1", 0, 10, 114, 575, 26);
		this.subtotalPositionTest(this.subtotal2, 0, 540, 0, 35, 26);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add("text", 1000000);
		dataSource.add("text", 1000000);
		return dataSource;
	}
}
