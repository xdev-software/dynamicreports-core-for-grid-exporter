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
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class Report2Test extends AbstractJasperPositionTest implements Serializable
{

	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<Integer> column1;
		
		rb.setTitleOnANewPage(true)
			.setSummaryOnANewPage(true)
			.setFloatColumnFooter(true)
			.columns(column1 = col.column("Column1", "field1", Integer.class))
			.subtotalsAtColumnFooter(this.subtotal1 = sbt.sum(column1))
			.subtotalsAtSummary(this.subtotal2 = sbt.sum(column1))
			.title(cmp.text("title"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		// title
		this.elementPositionTest("title.textField1", 0, 10, 10, 575, 16);
		
		// column footer
		this.subtotalPositionTest(this.subtotal1, 0, 10, 186, 575, 16);
		
		// summary
		this.subtotalPositionTest(this.subtotal2, 0, 10, 10, 575, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
