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
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Subreport2Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder detailSubreport =
			cmp.subreport(this.detailSubreport()).setDataSource(new SubreportDataSourceExpression());
		
		rb.title(cmp.subreport(this.titleSubreport())).detail(detailSubreport);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// title subreport
		this.columnDetailCountTest(this.column1, 3);
		this.columnDetailValueTest(this.column1, "value1", "value2", "value3");
		
		// detail subreport
		this.elementCountTest("title.textField1", 3);
		this.elementValueTest("title.textField1", "Subreport1", "Subreport2", "Subreport3");
		
		this.columnDetailCountTest(this.column2, 6);
		this.columnDetailValueTest(this.column2, "1_1", "1_2", "2_1", "2_2", "3_1", "3_2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(3);
	}
	
	private JasperReportBuilder titleSubreport()
	{
		final JasperReportBuilder report = report();
		this.column1 = col.column("Column1", "field1", type.stringType());
		report.columns(this.column1).setDataSource(this.titleSubreportDataSource());
		return report;
	}
	
	private JasperReportBuilder detailSubreport()
	{
		final JasperReportBuilder report = report();
		this.column2 = col.column(new ValueExpression());
		report.columns(this.column2).title(cmp.text(new SubreportTitleExpression()));
		return report;
	}
	
	private JRDataSource titleSubreportDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("value1");
		dataSource.add("value2");
		dataSource.add("value3");
		return dataSource;
	}
	
	static class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource>
	{

		@Override
		public JRDataSource evaluate(final ReportParameters reportParameters)
		{
			return new JREmptyDataSource(2);
		}
	}
	
	
	static class SubreportTitleExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return "Subreport" + reportParameters.getMasterParameters().getReportRowNumber();
		}
	}
	
	
	static class ValueExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getMasterParameters().getReportRowNumber() + "_"
				+ reportParameters.getReportRowNumber();
		}
	}
}
