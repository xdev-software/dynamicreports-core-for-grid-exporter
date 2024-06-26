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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TextField1Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", Integer.class),
				this.column2 = col.column("Column2", "field2", Integer.class).setColumns(20),
				this.column3 = col.column("Column3", "field3", Integer.class).setRows(2))
			.subtotalsAtSummary(this.subtotal1 = sbt.sum(this.column2))
			.title(cmp.horizontalList(cmp.hListCell(cmp.text("").setColumns(10).setRows(5)).widthFixed()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.textField1", 0, 10, 10, 94, 62);
		
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 149, 16);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 149, 27);
		
		this.columnTitlePositionTest(this.column2, 0, 149, 0, 276, 16);
		this.columnDetailPositionTest(this.column2, 0, 149, 0, 276, 27);
		
		this.columnTitlePositionTest(this.column3, 0, 425, 0, 150, 16);
		this.columnDetailPositionTest(this.column3, 0, 425, 0, 150, 27);
		
		this.subtotalPositionTest(this.subtotal1, 0, 149, 0, 276, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add(i, i, i);
		}
		return dataSource;
	}
}
