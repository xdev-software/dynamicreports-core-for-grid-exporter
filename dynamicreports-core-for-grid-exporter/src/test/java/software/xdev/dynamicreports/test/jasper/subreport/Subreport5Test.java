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
package software.xdev.dynamicreports.test.jasper.subreport;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.Components;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Subreport5Test extends AbstractJasperValueTest implements Serializable
{

	private final TextColumnBuilder<String> column1 = col.column("field1", type.stringType());
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder subreport =
			Components.subreport(new SubreportExpression()).setDataSource(new SubreportDataSourceExpression());
		
		rb.detail(subreport);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementCountTest("title.textField1", 3);
		this.elementValueTest("title.textField1", "Subreport text1", "Subreport text2", "Subreport text3");
		
		this.columnDetailCountTest(this.column1, 6);
		this.columnDetailValueTest(this.column1, "text1a", "text1b", "text2a", "text2b", "text3a", "text3b");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("text1");
		dataSource.add("text2");
		dataSource.add("text3");
		return dataSource;
	}
	
	private class SubreportExpression extends AbstractComplexExpression<JasperReportBuilder>
	{

		public SubreportExpression()
		{
			this.addExpression(field("field1", String.class));
		}
		
		@Override
		public JasperReportBuilder evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final JasperReportBuilder report = report();
			report.title(cmp.text("Subreport " + values.get(0))).columns(Subreport5Test.this.column1);
			
			return report;
		}
	}
	
	
	private class SubreportDataSourceExpression extends AbstractComplexExpression<JRDataSource>
	{

		public SubreportDataSourceExpression()
		{
			this.addExpression(field("field1", String.class));
		}
		
		@Override
		public JRDataSource evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final DRDataSource dataSource = new DRDataSource("field1");
			dataSource.add(values.get(0) + "a");
			dataSource.add(values.get(0) + "b");
			return dataSource;
		}
	}
}
