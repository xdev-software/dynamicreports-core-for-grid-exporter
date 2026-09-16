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
package software.xdev.dynamicreports.test.jasper.column;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.column.PercentageColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class PercentageColumnTest extends AbstractJasperValueTest implements Serializable
{

	private PercentageColumnBuilder percentage1;
	private PercentageColumnBuilder percentage2;
	private PercentageColumnBuilder percentage3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<Integer> column2;
		
		rb.setLocale(Locale.ENGLISH)
			.fields(field("field1", Integer.class))
			.columns(
				column2 = col.column("field2", Integer.class),
				this.percentage1 = col.percentageColumn("field1", Integer.class),
				this.percentage2 = col.percentageColumn(column2),
				this.percentage3 =
					col.percentageColumn("field3", Integer.class).setValueFormatter(new ColumnValueFormatter()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// percentage1
		this.columnDetailCountTest(this.percentage1, 3);
		this.columnDetailValueTest(this.percentage1, "16.67%", "33.33%", "50.00%");
		// percentage2
		this.columnDetailCountTest(this.percentage2, 3);
		this.columnDetailValueTest(this.percentage2, "26.67%", "33.33%", "40.00%");
		// percentage3
		this.columnDetailCountTest(this.percentage3, 3);
		this.columnDetailValueTest(this.percentage3, "value = 29.17%", "value = 33.33%", "value = 37.50%");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add(1, 4, 7);
		dataSource.add(2, 5, 8);
		dataSource.add(3, 6, 9);
		return dataSource;
	}
	
	static class ColumnValueFormatter extends AbstractValueFormatter<String, Double>
	{

		@Override
		public String format(final Double value, final ReportParameters reportParameters)
		{
			return "value = " + new DecimalFormat("#,##0.00%", new DecimalFormatSymbols(Locale.ENGLISH)).format(value);
		}
	}
}
