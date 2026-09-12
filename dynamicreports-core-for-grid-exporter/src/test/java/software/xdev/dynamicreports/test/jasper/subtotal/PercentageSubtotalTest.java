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
import software.xdev.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class PercentageSubtotalTest extends AbstractJasperValueTest
{
	private PercentageSubtotalBuilder subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		final TextColumnBuilder<Integer> column2;
		final ColumnGroupBuilder group1;
		
		rb.setLocale(Locale.ENGLISH)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class))
			.groupBy(group1 = grp.group(column1))
			.subtotalsOfPercentageAtGroupFooter(group1, this.subtotal1 = sbt.percentage(column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// groupFooter
		this.subtotalCountTest(this.subtotal1, 2);
		this.subtotalValueTest(this.subtotal1, "28.57%", "71.43%");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 1; i <= 3; i++)
		{
			dataSource.add("group1", i);
		}
		for(int i = 4; i <= 6; i++)
		{
			dataSource.add("group2", i);
		}
		return dataSource;
	}
}
