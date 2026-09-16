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
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class FieldDataTypeTest extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<BigDecimal> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<Date> column4;
	
	private AggregationSubtotalBuilder<BigDecimal> subtotal1;
	private AggregationSubtotalBuilder<Number> subtotal2;
	private AggregationSubtotalBuilder<Long> subtotal3;
	private AggregationSubtotalBuilder<Long> subtotal4;
	private AggregationSubtotalBuilder<BigDecimal> subtotal5;
	private AggregationSubtotalBuilder<BigDecimal> subtotal6;
	private AggregationSubtotalBuilder<BigDecimal> subtotal7;
	private AggregationSubtotalBuilder<Number> subtotal8;
	private AggregationSubtotalBuilder<Number> subtotal9;
	
	private AggregationSubtotalBuilder<Integer> subtotal10;
	private AggregationSubtotalBuilder<Number> subtotal11;
	private AggregationSubtotalBuilder<Long> subtotal12;
	private AggregationSubtotalBuilder<Long> subtotal13;
	private AggregationSubtotalBuilder<Integer> subtotal14;
	private AggregationSubtotalBuilder<Integer> subtotal15;
	private AggregationSubtotalBuilder<Integer> subtotal16;
	private AggregationSubtotalBuilder<Number> subtotal17;
	private AggregationSubtotalBuilder<Number> subtotal18;
	
	private AggregationSubtotalBuilder<Long> subtotal19;
	private AggregationSubtotalBuilder<Long> subtotal20;
	private AggregationSubtotalBuilder<String> subtotal21;
	private AggregationSubtotalBuilder<String> subtotal22;
	private AggregationSubtotalBuilder<String> subtotal23;
	
	private AggregationSubtotalBuilder<Long> subtotal24;
	private AggregationSubtotalBuilder<Long> subtotal25;
	private AggregationSubtotalBuilder<Date> subtotal26;
	private AggregationSubtotalBuilder<Date> subtotal27;
	private AggregationSubtotalBuilder<Date> subtotal28;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final FieldBuilder<BigDecimal> field1;
		final FieldBuilder<Integer> field2;
		final FieldBuilder<String> field3;
		final FieldBuilder<Date> field4;
		
		rb.setLocale(Locale.ENGLISH)
			.fields(
				field1 = field("field1", type.bigDecimalType()),
				field2 = field("field2", type.integerType()),
				field3 = field("field3", type.stringType()),
				field4 = field("field4", type.dateType()))
			.columns(
				this.column1 = col.column("Column1", field1),
				this.column2 = col.column("Column2", field2),
				this.column3 = col.column("Column2", field3),
				this.column4 = col.column("Column3", field4))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(field1, this.column1),
				this.subtotal2 = sbt.avg(field1, this.column1),
				this.subtotal3 = sbt.count(field1, this.column1),
				this.subtotal4 = sbt.distinctCount(field1, this.column1),
				this.subtotal5 = sbt.first(field1, this.column1),
				this.subtotal6 = sbt.min(field1, this.column1),
				this.subtotal7 = sbt.max(field1, this.column1),
				this.subtotal8 = sbt.stdDev(field1, this.column1),
				this.subtotal9 = sbt.var(field1, this.column1),
				
				this.subtotal10 = sbt.sum(field2, this.column2),
				this.subtotal11 = sbt.avg(field2, this.column2),
				this.subtotal12 = sbt.count(field2, this.column2),
				this.subtotal13 = sbt.distinctCount(field2, this.column2),
				this.subtotal14 = sbt.first(field2, this.column2),
				this.subtotal15 = sbt.min(field2, this.column2),
				this.subtotal16 = sbt.max(field2, this.column2),
				this.subtotal17 = sbt.stdDev(field2, this.column2),
				this.subtotal18 = sbt.var(field2, this.column2),
				
				this.subtotal19 = sbt.count(field3, this.column3),
				this.subtotal20 = sbt.distinctCount(field3, this.column3),
				this.subtotal21 = sbt.first(field3, this.column3),
				this.subtotal22 = sbt.min(field3, this.column3),
				this.subtotal23 = sbt.max(field3, this.column3),
				
				this.subtotal24 = sbt.count(field4, this.column4),
				this.subtotal25 = sbt.distinctCount(field4, this.column4),
				this.subtotal26 = sbt.first(field4, this.column4),
				this.subtotal27 = sbt.min(field4, this.column4),
				this.subtotal28 = sbt.max(field4, this.column4));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column
		this.columnDetailCountTest(this.column1, 5);
		this.columnDetailValueTest(this.column1, "1.01", "2.01", "9.01", "11.01", "2.01");
		this.columnDetailCountTest(this.column2, 5);
		this.columnDetailValueTest(this.column2, "5", "5", "8", "12", "6");
		this.columnDetailCountTest(this.column3, 5);
		this.columnDetailValueTest(this.column3, "value8", "value2", "value3", "value3", "value1");
		this.columnDetailCountTest(this.column4, 5);
		this.columnDetailValueTest(this.column4, "01/01/2010", "02/01/2010", "02/01/2010", "03/01/2010", "04/01/2010");
		
		// summary
		this.subtotalIndexCountTest(this.subtotal1, 1, 1);
		this.subtotalIndexValueTest(this.subtotal1, 1, "25.05");
		this.subtotalIndexCountTest(this.subtotal2, 2, 1);
		this.subtotalIndexValueTest(this.subtotal2, 2, "5");
		this.subtotalIndexCountTest(this.subtotal3, 3, 1);
		this.subtotalIndexValueTest(this.subtotal3, 3, "5");
		this.subtotalIndexCountTest(this.subtotal4, 4, 1);
		this.subtotalIndexValueTest(this.subtotal4, 4, "4");
		this.subtotalIndexCountTest(this.subtotal5, 5, 1);
		this.subtotalIndexValueTest(this.subtotal5, 5, "1.01");
		this.subtotalIndexCountTest(this.subtotal6, 6, 1);
		this.subtotalIndexValueTest(this.subtotal6, 6, "1.01");
		this.subtotalIndexCountTest(this.subtotal7, 7, 1);
		this.subtotalIndexValueTest(this.subtotal7, 7, "11.01");
		this.subtotalIndexCountTest(this.subtotal8, 8, 1);
		this.subtotalIndexValueTest(this.subtotal8, 8, "4.1");
		this.subtotalIndexCountTest(this.subtotal9, 9, 1);
		this.subtotalIndexValueTest(this.subtotal9, 9, "17.2");
		
		this.subtotalIndexCountTest(this.subtotal10, 1, 1);
		this.subtotalIndexValueTest(this.subtotal10, 1, "36");
		this.subtotalIndexCountTest(this.subtotal11, 2, 1);
		this.subtotalIndexValueTest(this.subtotal11, 2, "7.2");
		this.subtotalIndexCountTest(this.subtotal12, 3, 1);
		this.subtotalIndexValueTest(this.subtotal12, 3, "5");
		this.subtotalIndexCountTest(this.subtotal13, 4, 1);
		this.subtotalIndexValueTest(this.subtotal13, 4, "4");
		this.subtotalIndexCountTest(this.subtotal14, 5, 1);
		this.subtotalIndexValueTest(this.subtotal14, 5, "5");
		this.subtotalIndexCountTest(this.subtotal15, 6, 1);
		this.subtotalIndexValueTest(this.subtotal15, 6, "5");
		this.subtotalIndexCountTest(this.subtotal16, 7, 1);
		this.subtotalIndexValueTest(this.subtotal16, 7, "12");
		this.subtotalIndexCountTest(this.subtotal17, 8, 1);
		this.subtotalIndexValueTest(this.subtotal17, 8, "2.6");
		this.subtotalIndexCountTest(this.subtotal18, 9, 1);
		this.subtotalIndexValueTest(this.subtotal18, 9, "7");
		
		this.subtotalIndexCountTest(this.subtotal19, 1, 1);
		this.subtotalIndexValueTest(this.subtotal19, 1, "5");
		this.subtotalIndexCountTest(this.subtotal20, 2, 1);
		this.subtotalIndexValueTest(this.subtotal20, 2, "4");
		this.subtotalIndexCountTest(this.subtotal21, 3, 1);
		this.subtotalIndexValueTest(this.subtotal21, 3, "value8");
		this.subtotalIndexCountTest(this.subtotal22, 4, 1);
		this.subtotalIndexValueTest(this.subtotal22, 4, "value1");
		this.subtotalIndexCountTest(this.subtotal23, 5, 1);
		this.subtotalIndexValueTest(this.subtotal23, 5, "value8");
		
		this.subtotalIndexCountTest(this.subtotal24, 1, 1);
		this.subtotalIndexValueTest(this.subtotal24, 1, "5");
		this.subtotalIndexCountTest(this.subtotal25, 2, 1);
		this.subtotalIndexValueTest(this.subtotal25, 2, "4");
		this.subtotalIndexCountTest(this.subtotal26, 3, 1);
		this.subtotalIndexValueTest(this.subtotal26, 3, "01/01/2010");
		this.subtotalIndexCountTest(this.subtotal27, 4, 1);
		this.subtotalIndexValueTest(this.subtotal27, 4, "01/01/2010");
		this.subtotalIndexCountTest(this.subtotal28, 5, 1);
		this.subtotalIndexValueTest(this.subtotal28, 5, "04/01/2010");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add(new BigDecimal(1.01), 5, "value8", this.toDate(2010, 1, 1));
		dataSource.add(new BigDecimal(2.01), 5, "value2", this.toDate(2010, 2, 1));
		dataSource.add(new BigDecimal(9.01), 8, "value3", this.toDate(2010, 2, 1));
		dataSource.add(new BigDecimal(11.01), 12, "value3", this.toDate(2010, 3, 1));
		dataSource.add(new BigDecimal(2.01), 6, "value1", this.toDate(2010, 4, 1));
		return dataSource;
	}
}
