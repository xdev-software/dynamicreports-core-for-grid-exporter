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

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class GroupCustomSubtotal2Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<Integer> column2;
	private CustomSubtotalBuilder<Integer> subtotal1;
	private CustomSubtotalBuilder<Integer> subtotal2;
	private CustomSubtotalBuilder<Integer> subtotal3;
	private CustomSubtotalBuilder<Integer> subtotal4;
	private CustomSubtotalBuilder<Integer> subtotal5;
	private CustomSubtotalBuilder<Integer> subtotal6;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final ColumnGroupBuilder group = grp.group(column1);
		
		rb.setLocale(Locale.ENGLISH)
			.columns(column1, this.column2 = col.column("Column2", "field2", Integer.class))
			.groupBy(group)
			.subtotalsAtGroupHeader(group, this.subtotal1 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtGroupFooter(group, this.subtotal2 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtFirstGroupHeader(this.subtotal3 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtFirstGroupFooter(this.subtotal4 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtLastGroupHeader(this.subtotal5 = sbt.customValue(new ValueExpression(), this.column2))
			.subtotalsAtLastGroupFooter(this.subtotal6 = sbt.customValue(new ValueExpression(), this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// groupHeader
		this.subtotalIndexCountTest(this.subtotal1, 1, 2);
		this.subtotalIndexValueTest(this.subtotal1, 1, "3", "6");
		// groupFooter
		this.subtotalIndexCountTest(this.subtotal2, 1, 2);
		this.subtotalIndexValueTest(this.subtotal2, 1, "3", "6");
		// first groupHeader
		this.subtotalIndexCountTest(this.subtotal3, 1, 2);
		this.subtotalIndexValueTest(this.subtotal3, 1, "3", "6");
		// first groupFooter
		this.subtotalIndexCountTest(this.subtotal4, 1, 2);
		this.subtotalIndexValueTest(this.subtotal4, 1, "3", "6");
		// last groupHeader
		this.subtotalIndexCountTest(this.subtotal5, 1, 2);
		this.subtotalIndexValueTest(this.subtotal5, 1, "3", "6");
		// last groupFooter
		this.subtotalIndexCountTest(this.subtotal6, 1, 2);
		this.subtotalIndexValueTest(this.subtotal6, 1, "3", "6");
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
	
	class ValueExpression extends AbstractComplexExpression<Integer>
	{

		ValueExpression()
		{
			this.addExpression(GroupCustomSubtotal2Test.this.column2);
		}
		
		@Override
		public Integer evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			return (Integer)values.get(0);
		}
	}
}
