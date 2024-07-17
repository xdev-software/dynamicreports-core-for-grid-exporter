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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.datatype.DoubleType;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Column3Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Date> column2;
	private TextColumnBuilder<Date> column3;
	private TextColumnBuilder<Double> column4;
	private TextColumnBuilder<BigDecimal> column5;
	private TextColumnBuilder<Double> column6;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.columnGrid(ListType.HORIZONTAL_FLOW)
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.dateType()),
				this.column3 = col.column("Column3", "field3", type.dateYearToMinuteType()),
				this.column4 = col.column("Column4", "field4", type.doubleType()),
				this.column5 = col.column("Column5", "field5", type.bigDecimalType()),
				this.column6 = col.column("Column6", "field6", new CustomType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// column2
		this.columnDetailCountTest(this.column1, 10);
		this.columnDetailValueTest(this.column1, 1, "test");
		this.columnTitleCountTest(this.column1, 1);
		this.columnTitleValueTest(this.column1, "Column1");
		// column2
		this.columnDetailCountTest(this.column2, 10);
		this.columnDetailValueTest(
			this.column2,
			1,
			new SimpleDateFormat(type.dateType().getPattern()).format(new Date()));
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		// column3
		this.columnDetailCountTest(this.column3, 10);
		this.columnDetailValueTest(
			this.column3,
			1,
			new SimpleDateFormat(type.dateYearToMinuteType().getPattern()).format(new Date()));
		this.columnTitleCountTest(this.column3, 1);
		this.columnTitleValueTest(this.column3, "Column3");
		// column4
		this.columnDetailCountTest(this.column4, 10);
		this.columnDetailValueTest(this.column4, 1, "1,000.1");
		this.columnTitleCountTest(this.column4, 1);
		this.columnTitleValueTest(this.column4, "Column4");
		// column5
		this.columnDetailCountTest(this.column5, 10);
		this.columnDetailValueTest(this.column5, 1, "10.00");
		this.columnTitleCountTest(this.column5, 1);
		this.columnTitleValueTest(this.column5, "Column5");
		// column6
		this.columnDetailCountTest(this.column6, 10);
		this.columnDetailValueTest(this.column6, 1, "value = 1.0");
		this.columnTitleCountTest(this.column6, 1);
		this.columnTitleValueTest(this.column6, "Column6");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("test", new Date(), new Date(), 1000.1d, new BigDecimal(10), 1d);
		}
		return dataSource;
	}
	
	static class CustomType extends DoubleType
	{

		@Override
		public DRIValueFormatter<String, Double> getValueFormatter()
		{
			return new ColumnValueFormatter();
		}
	}
	
	
	static class ColumnValueFormatter extends AbstractValueFormatter<String, Double>
	{

		@Override
		public String format(final Double value, final ReportParameters reportParameters)
		{
			return "value = " + value;
		}
	}
}
