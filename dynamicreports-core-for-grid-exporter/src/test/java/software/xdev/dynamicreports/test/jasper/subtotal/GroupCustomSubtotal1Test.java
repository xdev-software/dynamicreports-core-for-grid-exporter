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
import static software.xdev.dynamicreports.report.builder.DynamicReports.variable;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.VariableBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.builder.expression.PercentageExpression;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class GroupCustomSubtotal1Test extends AbstractJasperValueTest implements Serializable
{
	private TextColumnBuilder<Integer> column2;
	private VariableBuilder<Integer> variable1;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private CustomSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	private CustomSubtotalBuilder<String> subtotal4;
	private CustomSubtotalBuilder<String> subtotal5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.setLocale(Locale.ENGLISH)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class))
			.groupBy(column1)
			.subtotalsAtFirstGroupFooter(
				this.subtotal1 = sbt.sum(this.column2),
				this.subtotal2 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtLastGroupFooter(
				this.subtotal3 = sbt.sum(this.column2),
				this.subtotal4 = sbt.customValue(new ValueExpression2(), this.column2),
				this.subtotal5 = sbt.customValue(new ValueExpression3(), this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// groupFooter
		this.subtotalIndexCountTest(this.subtotal1, 1, 2);
		this.subtotalIndexValueTest(this.subtotal1, 1, "6", "15");
		this.subtotalIndexCountTest(this.subtotal2, 2, 2);
		this.subtotalIndexValueTest(this.subtotal2, 2, "6", "15");
		this.subtotalIndexCountTest(this.subtotal3, 3, 2);
		this.subtotalIndexValueTest(this.subtotal3, 3, "6", "15");
		this.subtotalIndexCountTest(this.subtotal4, 4, 2);
		this.subtotalIndexValueTest(this.subtotal4, 4, "6 (28.57%)", "15 (71.43%)");
		this.subtotalIndexCountTest(this.subtotal5, 5, 2);
		this.subtotalIndexValueTest(this.subtotal5, 5, "value1", "value2");
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
	
	class ValueExpression extends AbstractSimpleExpression<Integer>
	{

		@Override
		public Integer evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getValue(GroupCustomSubtotal1Test.this.variable1);
		}
	}
	
	
	private class ValueExpression2 extends AbstractComplexExpression<String>
	{

		public ValueExpression2()
		{
			GroupCustomSubtotal1Test.this.variable1 = variable(GroupCustomSubtotal1Test.this.column2, Calculation.SUM);
			GroupCustomSubtotal1Test.this.variable1.setResetType(Evaluation.LAST_GROUP);
			final VariableBuilder<Integer> variable2 = variable(GroupCustomSubtotal1Test.this.column2, Calculation.SUM);
			variable2.setResetType(Evaluation.REPORT);
			this.addExpression(new PercentageExpression(
				GroupCustomSubtotal1Test.this.variable1.getVariable(),
				variable2.getVariable()));
		}
		
		@Override
		public String evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final String percentage =
				new DecimalFormat("#,##0.00%", new DecimalFormatSymbols(Locale.ENGLISH)).format(values.get(0));
			return reportParameters.getValue(GroupCustomSubtotal1Test.this.variable1) + " (" + percentage + ")";
		}
	}
	
	static class ValueExpression3 extends AbstractComplexExpression<String>
	{
		private int count = 1;
		
		@Override
		public String evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			return "value" + this.count++;
		}
	}
}
