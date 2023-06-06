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
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Locale;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;


/**
 * @author Ricardo Mariaca
 */
public class Subtotal1Test extends AbstractJasperValueTest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private AggregationSubtotalBuilder<BigDecimal> subtotal1;
	private AggregationSubtotalBuilder<BigDecimal> subtotal2;
	private AggregationSubtotalBuilder<BigDecimal> subtotal3;
	private AggregationSubtotalBuilder<BigDecimal> subtotal4;
	private AggregationSubtotalBuilder<BigDecimal> subtotal5;
	private AggregationSubtotalBuilder<BigDecimal> subtotal6;
	private AggregationSubtotalBuilder<BigDecimal> subtotal7;
	private AggregationSubtotalBuilder<BigDecimal> subtotal8;
	private AggregationSubtotalBuilder<Integer> subtotal9;
	private AggregationSubtotalBuilder<Integer> subtotal10;
	private AggregationSubtotalBuilder<Integer> subtotal11;
	private AggregationSubtotalBuilder<String> subtotal12;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<BigDecimal> column1;
		final TextColumnBuilder<String> column2;
		
		rb.setLocale(Locale.ENGLISH)
			.setPageColumnsPerPage(2)
			.columns(
				column1 = col.column("Column1", "field1", type.bigDecimalType()),
				column2 = col.column("Column2", "field2", String.class).setValueFormatter(new ColumnValueFormatter()))
			.subtotalsAtTitle(this.subtotal1 = sbt.sum(column1).setLabel("title sum"))
			.subtotalsAtPageHeader(this.subtotal2 = sbt.sum(column1).setLabel("page header sum"))
			.subtotalsAtPageFooter(this.subtotal3 = sbt.sum(column1).setLabel("page footer sum"))
			.subtotalsAtColumnHeader(this.subtotal4 = sbt.sum(column1).setLabel("column header sum"))
			.subtotalsAtColumnFooter(this.subtotal5 = sbt.sum(column1).setLabel("column footer sum"))
			.subtotalsAtLastPageFooter(this.subtotal6 = sbt.sum(column1).setLabel("last page footer sum"))
			.subtotalsAtSummary(
                this.subtotal7 = sbt.sum(column1).setLabel("summary sum"),
				(this.subtotal8 = sbt.aggregate(column1, Calculation.LOWEST)).setLabel("summary lowest"),
                this.subtotal9 = sbt.sum(new ColumnCalculationExpression(), column2)
					.setLabel("summary sum")
					.setValueFormatter(new ColumnValueFormatter2()),
                this.subtotal10 = sbt.aggregate(column1, Calculation.COUNT),
                this.subtotal11 = sbt.aggregate(column2, Calculation.COUNT),
                this.subtotal12 = sbt.text("total", column1));
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(2);
		// title
        this.subtotalLabelCountTest(this.subtotal1, 1);
        this.subtotalLabelValueTest(this.subtotal1, "title sum");
        this.subtotalCountTest(this.subtotal1, 1);
        this.subtotalValueTest(this.subtotal1, "101.00");
		// pageHeader
        this.subtotalLabelCountTest(this.subtotal2, 2);
        this.subtotalLabelValueTest(this.subtotal2, "page header sum", "page header sum");
        this.subtotalCountTest(this.subtotal2, 2);
        this.subtotalValueTest(this.subtotal2, "80.80", "20.20");
		// pageFooter
        this.subtotalLabelCountTest(this.subtotal3, 1);
        this.subtotalLabelValueTest(this.subtotal3, "page footer sum");
        this.subtotalCountTest(this.subtotal3, 1);
        this.subtotalValueTest(this.subtotal3, "80.80");
		// columnHeader
        this.subtotalLabelCountTest(this.subtotal4, 3);
        this.subtotalLabelValueTest(this.subtotal4, "column header sum", "column header sum", "column header sum");
        this.subtotalCountTest(this.subtotal4, 3);
        this.subtotalValueTest(this.subtotal4, "40.40", "40.40", "20.20");
		// columnFooter
        this.subtotalLabelCountTest(this.subtotal5, 3);
        this.subtotalLabelValueTest(this.subtotal5, "column footer sum", "column footer sum", "column footer sum");
        this.subtotalCountTest(this.subtotal5, 3);
        this.subtotalValueTest(this.subtotal5, "40.40", "40.40", "20.20");
		// lastPageFooter
        this.subtotalLabelCountTest(this.subtotal6, 1);
        this.subtotalLabelValueTest(this.subtotal6, "last page footer sum");
        this.subtotalCountTest(this.subtotal6, 1);
        this.subtotalValueTest(this.subtotal6, "101.00");
		// summary
        this.subtotalLabelCountTest(this.subtotal7, 1);
        this.subtotalLabelValueTest(this.subtotal7, "summary sum");
        this.subtotalCountTest(this.subtotal7, 1);
        this.subtotalValueTest(this.subtotal7, "101.00");
        
        this.subtotalLabelIndexCountTest(this.subtotal8, 2, 1);
        this.subtotalLabelIndexValueTest(this.subtotal8, 2, "summary lowest");
        this.subtotalIndexCountTest(this.subtotal8, 2, 1);
        this.subtotalIndexValueTest(this.subtotal8, 2, "1.01");
        
        this.subtotalIndexCountTest(this.subtotal10, 3, 1);
        this.subtotalIndexValueTest(this.subtotal10, 3, "100");
        
        this.subtotalLabelCountTest(this.subtotal9, 1);
        this.subtotalLabelValueTest(this.subtotal9, "summary sum");
        this.subtotalCountTest(this.subtotal9, 1);
        this.subtotalValueTest(this.subtotal9, "value = 200");
        
        this.subtotalIndexCountTest(this.subtotal11, 2, 1);
        this.subtotalIndexValueTest(this.subtotal11, 2, "100");
        
        this.subtotalIndexCountTest(this.subtotal12, 4, 1);
        this.subtotalIndexValueTest(this.subtotal12, 4, "total");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 100; i++)
		{
			dataSource.add(new BigDecimal(1.01), "v2");
		}
		return dataSource;
	}
	
	private class ColumnValueFormatter extends AbstractValueFormatter<String, String>
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String format(final String value, final ReportParameters reportParameters)
		{
			return "value = " + value;
		}
	}
	
	
	private class ColumnValueFormatter2 extends AbstractValueFormatter<String, Integer>
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String format(final Integer value, final ReportParameters reportParameters)
		{
			return "value = " + value;
		}
	}
	
	
	private class ColumnCalculationExpression extends AbstractSimpleExpression<Integer>
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public Integer evaluate(final ReportParameters reportParameters)
		{
			return Integer.parseInt(((String)reportParameters.getValue("field2")).substring(1));
		}
	}
}
