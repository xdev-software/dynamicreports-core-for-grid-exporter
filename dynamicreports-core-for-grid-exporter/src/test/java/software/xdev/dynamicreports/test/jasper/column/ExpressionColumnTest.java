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
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class ExpressionColumnTest extends AbstractJasperValueTest implements Serializable
{

	private FieldBuilder<Double> field1;
	private TextColumnBuilder<Double> column2;
	private TextColumnBuilder<Double> expression1;
	private TextColumnBuilder<BigDecimal> calcExpression1;
	private TextColumnBuilder<BigDecimal> calcExpression2;
	private TextColumnBuilder<BigDecimal> calcExpression3;
	private TextColumnBuilder<BigDecimal> calcExpression4;
	private TextColumnBuilder<BigDecimal> calcExpression5;
	private TextColumnBuilder<BigDecimal> calcExpression6;
	private TextColumnBuilder<BigDecimal> calcExpression7;
	private TextColumnBuilder<BigDecimal> calcExpression8;
	private TextColumnBuilder<Double> expression2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.fields(this.field1 = field("field1", Double.class))
			.columns(
				this.column2 = col.column("Column2", "field2", Double.class)
					.setValueFormatter(new ColumnValueFormatter()),
				this.expression1 = col.column("Expression", new ValueExpression1()),
				this.calcExpression1 = this.expression1.multiply(this.column2),
				this.calcExpression2 = this.calcExpression1.multiply(5),
				this.calcExpression3 = this.calcExpression2.subtract(this.calcExpression1),
				this.calcExpression4 = this.calcExpression3.subtract(5),
				this.calcExpression5 = this.calcExpression4.add(this.calcExpression1),
				this.calcExpression6 = this.calcExpression5.add(5),
				this.calcExpression7 = this.calcExpression6.divide(2, this.expression1),
				this.calcExpression8 = this.calcExpression7.divide(2, 3),
				this.expression2 = col.column("Expression", new ValueExpression2()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// column2
		this.columnDetailCountTest(this.column2, 3);
		this.columnDetailValueTest(this.column2, "value = 4.0", "value = 5.0", "value = 6.0");
		// expression
		this.columnDetailCountTest(this.expression1, 3);
		this.columnDetailValueTest(this.expression1, "5.0", "7.0", "9.0");
		this.columnDetailCountTest(this.expression2, 3);
		this.columnDetailValueTest(this.expression2, "11.67", "15.33", "19.0");
		// calcExpression
		this.columnDetailCountTest(this.calcExpression1, 3);
		this.columnDetailValueTest(this.calcExpression1, "20.00", "35.00", "54.00");
		this.columnDetailCountTest(this.calcExpression2, 3);
		this.columnDetailValueTest(this.calcExpression2, "100.00", "175.00", "270.00");
		this.columnDetailCountTest(this.calcExpression3, 3);
		this.columnDetailValueTest(this.calcExpression3, "80.00", "140.00", "216.00");
		this.columnDetailCountTest(this.calcExpression4, 3);
		this.columnDetailValueTest(this.calcExpression4, "75.00", "135.00", "211.00");
		this.columnDetailCountTest(this.calcExpression5, 3);
		this.columnDetailValueTest(this.calcExpression5, "95.00", "170.00", "265.00");
		this.columnDetailCountTest(this.calcExpression6, 3);
		this.columnDetailValueTest(this.calcExpression6, "100.00", "175.00", "270.00");
		this.columnDetailCountTest(this.calcExpression7, 3);
		this.columnDetailValueTest(this.calcExpression7, "20.00", "25.00", "30.00");
		this.columnDetailCountTest(this.calcExpression8, 3);
		this.columnDetailValueTest(this.calcExpression8, "6.67", "8.33", "10.00");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(1d, 4d);
		dataSource.add(2d, 5d);
		dataSource.add(3d, 6d);
		return dataSource;
	}
	
	class ValueExpression1 extends AbstractSimpleExpression<Double>
	{

		@Override
		public Double evaluate(final ReportParameters reportParameters)
		{
			final double f1 = reportParameters.getValue(ExpressionColumnTest.this.field1);
			final double f2 = reportParameters.getValue(ExpressionColumnTest.this.column2);
			return f1 + f2;
		}
	}
	
	class ValueExpression2 extends AbstractSimpleExpression<Double>
	{

		@Override
		public Double evaluate(final ReportParameters reportParameters)
		{
			final double f1 = reportParameters.getValue(ExpressionColumnTest.this.expression1);
			final double f2 = reportParameters.getValue(ExpressionColumnTest.this.calcExpression8).doubleValue();
			return f1 + f2;
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
