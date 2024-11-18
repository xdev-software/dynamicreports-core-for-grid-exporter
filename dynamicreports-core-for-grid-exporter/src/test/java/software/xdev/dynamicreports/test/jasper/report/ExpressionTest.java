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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.util.Locale;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class ExpressionTest extends AbstractJasperValueTest implements Serializable
{

	@SuppressWarnings("unchecked")
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.title(
				cmp.text(new Expression1()),
				cmp.text(new Expression2<>("text2")),
				cmp.text(new Expression4()).setDataType(type.integerType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementValueTest("title.textField1", "text1");
		this.elementValueTest("title.textField2", "text2");
		this.elementValueTest("title.textField3", "1,000");
	}
	
	@SuppressWarnings("rawtypes")
	static class Expression1 extends AbstractSimpleExpression
	{
		@Override
		public Object evaluate(final ReportParameters reportParameters)
		{
			return "text1";
		}
	}
	
	
	static class Expression2<T> extends AbstractSimpleExpression<T>
	{
		private final T value;
		
		Expression2(final T value)
		{
			this.value = value;
		}
		
		@Override
		public T evaluate(final ReportParameters reportParameters)
		{
			return this.value;
		}
	}
	
	
	static class Expression3 extends AbstractSimpleExpression<Integer>
	{
		@Override
		public Integer evaluate(final ReportParameters reportParameters)
		{
			return 1000;
		}
	}
	
	
	static class Expression4 extends Expression3
	{
	}
}
