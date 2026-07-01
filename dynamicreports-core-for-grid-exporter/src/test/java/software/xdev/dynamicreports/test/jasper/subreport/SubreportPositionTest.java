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
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.Components;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class SubreportPositionTest extends AbstractJasperPositionTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder subreport =
			Components.subreport(new SubreportExpression()).setDataSource(new SubreportDataSourceExpression());
		
		rb.detail(subreport, cmp.filler().setFixedHeight(20)).summary(cmp.subreport(this.subreport2()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// elementPositionTest("detail.list1", 0, 10, 10, 575, 68);
		// elementPositionTest("detail.subreport1", 0, 10, 10, 575, 48);
		
		// elementPositionTest("detail.list1", 1, 10, 78, 575, 84);
		// elementPositionTest("detail.subreport1", 1, 10, 78, 575, 64);
		
		// elementPositionTest("detail.list1", 4, 10, 162, 575, 100);
		// elementPositionTest("detail.subreport1", 2, 10, 162, 575, 80);
		
		// title
		this.elementPositionTest("title.textField1", 0, 10, 10, 575, 16);
		this.elementPositionTest("title.textField1", 1, 10, 78, 575, 16);
		this.elementPositionTest("title.textField1", 2, 10, 162, 575, 16);
		
		// column title
		this.elementPositionTest("columnHeader.column_column1.title1", 0, 10, 26, 575, 16);
		
		this.elementPositionTest("columnHeader.list1", 0, 10, 94, 575, 16);
		this.elementPositionTest("columnHeader.column_column1.title1", 1, 0, 0, 287, 16);
		this.elementPositionTest("columnHeader.column_column2.title1", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("columnHeader.list1", 1, 10, 178, 575, 16);
		this.elementPositionTest("columnHeader.column_column1.title1", 2, 0, 0, 191, 16);
		this.elementPositionTest("columnHeader.column_column2.title1", 1, 191, 0, 192, 16);
		this.elementPositionTest("columnHeader.column_column3.title1", 0, 383, 0, 192, 16);
		
		// column detail
		this.elementPositionTest("detail.column_column11", 0, 10, 42, 575, 16);
		
		this.elementPositionTest("detail.list1", 0, 10, 110, 575, 16);
		this.elementPositionTest("detail.column_column11", 1, 0, 0, 287, 16);
		this.elementPositionTest("detail.column_column21", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("detail.list1", 1, 10, 126, 575, 16);
		this.elementPositionTest("detail.column_column11", 2, 0, 0, 287, 16);
		this.elementPositionTest("detail.column_column21", 1, 287, 0, 288, 16);
		
		this.elementPositionTest("detail.list1", 2, 10, 194, 575, 16);
		this.elementPositionTest("detail.column_column11", 3, 0, 0, 191, 16);
		this.elementPositionTest("detail.column_column21", 2, 191, 0, 192, 16);
		this.elementPositionTest("detail.column_column31", 0, 383, 0, 192, 16);
		
		this.elementPositionTest("detail.list1", 3, 10, 210, 575, 16);
		this.elementPositionTest("detail.column_column11", 4, 0, 0, 191, 16);
		this.elementPositionTest("detail.column_column21", 3, 191, 0, 192, 16);
		this.elementPositionTest("detail.column_column31", 1, 383, 0, 192, 16);
		
		this.elementPositionTest("detail.list1", 4, 10, 226, 575, 16);
		this.elementPositionTest("detail.column_column11", 5, 0, 0, 191, 16);
		this.elementPositionTest("detail.column_column21", 4, 191, 0, 192, 16);
		this.elementPositionTest("detail.column_column31", 2, 383, 0, 192, 16);
		
		// summary
		this.elementPositionTest("summary.textField1", 0, 20, 272, 555, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(3);
	}
	
	private JasperReportBuilder subreport2()
	{
		final JasperReportBuilder report = report();
		report.setPageMargin(margin(10));
		report.summary(cmp.text("subreport2"));
		return report;
	}
	
	static class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder>
	{

		@Override
		public JasperReportBuilder evaluate(final ReportParameters reportParameters)
		{
			final int masterRowNumber = reportParameters.getReportRowNumber();
			final JasperReportBuilder report = report();
			report.title(cmp.text("Subreport" + masterRowNumber));
			
			for(int i = 1; i <= masterRowNumber; i++)
			{
				report.addColumn(col.column("Column" + i, "column" + i, type.stringType()));
			}
			
			return report;
		}
	}
	
	
	static class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource>
	{

		@Override
		public JRDataSource evaluate(final ReportParameters reportParameters)
		{
			final int masterRowNumber = reportParameters.getReportRowNumber();
			final String[] columns = new String[masterRowNumber];
			for(int i = 1; i <= masterRowNumber; i++)
			{
				columns[i - 1] = "column" + i;
			}
			final DRDataSource dataSource = new DRDataSource(columns);
			
			for(int i = 1; i <= masterRowNumber; i++)
			{
				final Object[] values = new Object[masterRowNumber];
				for(int j = 1; j <= masterRowNumber; j++)
				{
					values[j - 1] = "row" + i + "_column" + j;
				}
				dataSource.add(values);
			}
			
			return dataSource;
		}
	}
}
