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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Column1Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<Date> column3;
	private TextColumnBuilder<Double> column4;
	private TextColumnBuilder<BigDecimal> column5;
	private TextColumnBuilder<Double> column6;
	private TextColumnBuilder<Object> column7;
	
	private final Date date = new Date();
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.addField("field1", Integer.class)
			.columns(
				this.column2 = col.column("Column2\nColumn2", "field2", String.class),
				this.column3 = col.column("Column3", "field3", Date.class).setPattern("dd.MM.yyyy"),
				this.column4 = col.column("Column4", "field4", Double.class).setPattern("#,###.00"),
				this.column5 =
					col.column("Column5", "field5", BigDecimal.class).setValueFormatter(new ColumnValueFormatter()),
				this.column6 = col.column("Column6", "field6", Double.class).setPattern(new PatternExpression()),
				this.column7 = col.column("Column7", field("field7", Date.class).build()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		// column2
		this.columnDetailCountTest(this.column2, 110);
		this.columnDetailValueTest(this.column2, 50, "test");
		this.columnTitleCountTest(this.column2, 3);
		this.columnTitleValueTest(this.column2, "Column2\nColumn2", "Column2\nColumn2", "Column2\nColumn2");
		// column3
		this.columnDetailCountTest(this.column3, 110);
		this.columnDetailValueTest(this.column3, 50, new SimpleDateFormat("dd.MM.yyyy").format(this.date));
		this.columnTitleCountTest(this.column3, 3);
		this.columnTitleValueTest(this.column3, "Column3", "Column3", "Column3");
		// column4
		this.columnDetailCountTest(this.column4, 110);
		this.columnDetailValueTest(this.column4, 50, "1.00");
		this.columnTitleCountTest(this.column4, 3);
		this.columnTitleValueTest(this.column4, "Column4", "Column4", "Column4");
		// column5
		this.columnDetailCountTest(this.column5, 110);
		this.columnDetailValueTest(this.column5, 50, "value = 10");
		this.columnTitleCountTest(this.column5, 3);
		this.columnTitleValueTest(this.column5, "Column5", "Column5", "Column5");
		// column6
		this.columnDetailCountTest(this.column6, 110);
		this.columnDetailValueTest(this.column6, 0, "1.00");
		this.columnDetailValueTest(this.column6, 1, "1.000");
		this.columnDetailValueTest(this.column6, 2, "1.0000");
		this.columnDetailValueTest(this.column6, 3, "1.0");
		this.columnDetailValueTest(this.column6, 50, "1.0");
		this.columnTitleCountTest(this.column6, 3);
		this.columnTitleValueTest(this.column6, "Column6", "Column6", "Column6");
		// column7
		this.columnDetailCountTest(this.column7, 110);
		this.columnDetailValueTest(this.column7, 50, new SimpleDateFormat("MM/dd/yyyy").format(this.date));
		this.columnTitleCountTest(this.column7, 3);
		this.columnTitleValueTest(this.column7, "Column7", "Column7", "Column7");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource =
			new DRDataSource("field1", "field2", "field3", "field4", "field5", "field6", "field7");
		for(int i = 0; i < 110; i++)
		{
			dataSource.add(1, "test", this.date, 1d, new BigDecimal(10), 1d, this.date);
		}
		return dataSource;
	}
	
	static class ColumnValueFormatter extends AbstractValueFormatter<String, BigDecimal>
	{

		@Override
		public String format(final BigDecimal value, final ReportParameters reportParameters)
		{
			return "value = " + value;
		}
	}
	
	
	static class PatternExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			String pattern = "#,###.0";
			final Integer reportRowNumber = reportParameters.getReportRowNumber();
			if(reportRowNumber < 4)
			{
				for(int i = 0; i < reportRowNumber; i++)
				{
					pattern += "0";
				}
			}
			return pattern;
		}
	}
}
