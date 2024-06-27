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

import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class GroupSubtotal1Test extends AbstractJasperValueTest
{
	private AggregationSubtotalBuilder<Double> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Double> subtotal3;
	private AggregationSubtotalBuilder<Integer> subtotal4;
	private AggregationSubtotalBuilder<Integer> subtotal5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		final TextColumnBuilder<String> column2;
		final TextColumnBuilder<Integer> column3;
		final ColumnGroupBuilder group1;
		final ColumnGroupBuilder group2;
		
		rb.setLocale(Locale.ENGLISH)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", String.class),
				column3 = col.column("Column3", "field3", Integer.class))
			.groupBy(group1 = grp.group(column1), group2 = grp.group(column2))
			.subtotalsAtGroupHeader(group1, this.subtotal1 = sbt.aggregate(column3, Calculation.AVERAGE))
			.subtotalsAtGroupFooter(group1, this.subtotal2 = sbt.sum(column3))
			.subtotalsAtGroupHeader(group2, this.subtotal3 = sbt.aggregate(column3, Calculation.AVERAGE))
			.subtotalsAtGroupFooter(group2, this.subtotal4 = sbt.sum(column3))
			.subtotalsAtSummary(this.subtotal5 = sbt.sum(column3));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// groupHeader
		this.subtotalCountTest(this.subtotal1, 2);
		this.subtotalValueTest(this.subtotal1, "3.5", "9.5");
		// groupFooter
		this.subtotalCountTest(this.subtotal2, 2);
		this.subtotalValueTest(this.subtotal2, "21", "57");
		
		// groupHeader
		this.subtotalIndexCountTest(this.subtotal3, 2, 4);
		this.subtotalIndexValueTest(this.subtotal3, 2, "2", "5", "8", "11");
		// groupFooter
		this.subtotalIndexCountTest(this.subtotal4, 2, 4);
		this.subtotalIndexValueTest(this.subtotal4, 2, "6", "15", "24", "33");
		
		this.subtotalCountTest(this.subtotal5, 1);
		this.subtotalValueTest(this.subtotal5, "78");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		int count = 1;
		for(int i = 0; i < 3; i++)
		{
			dataSource.add("group1", "group1_1", count++);
		}
		for(int i = 0; i < 3; i++)
		{
			dataSource.add("group1", "group1_2", count++);
		}
		for(int i = 0; i < 3; i++)
		{
			dataSource.add("group2", "group2_1", count++);
		}
		for(int i = 0; i < 3; i++)
		{
			dataSource.add("group2", "group2_2", count++);
		}
		return dataSource;
	}
}
