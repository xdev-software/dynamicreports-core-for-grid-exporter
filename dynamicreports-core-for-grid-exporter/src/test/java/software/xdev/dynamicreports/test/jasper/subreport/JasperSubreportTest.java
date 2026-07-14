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

import java.io.InputStream;
import java.io.Serializable;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.Components;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class JasperSubreportTest extends AbstractJasperValueTest implements Serializable
{

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
		
		// title
		this.elementCountTest("title.textField1", 3);
		this.elementValueTest("title.textField1", "Subreport1", "Subreport2", "Subreport3");
		
		// column title
		this.elementCountTest("columnHeader.column_column1.title1", 3);
		this.elementValueTest("columnHeader.column_column1.title1", "Column1", "Column1", "Column1");
		
		this.elementCountTest("columnHeader.column_column2.title1", 2);
		this.elementValueTest("columnHeader.column_column2.title1", "Column2", "Column2");
		
		this.elementCountTest("columnHeader.column_column3.title1", 1);
		this.elementValueTest("columnHeader.column_column3.title1", "Column3");
		
		// column detail
		this.elementCountTest("detail.column_column11", 6);
		this.elementValueTest(
			"detail.column_column11",
			"row1_column1",
			"row1_column1",
			"row2_column1",
			"row1_column1",
			"row2_column1",
			"row3_column1");
		
		this.elementCountTest("detail.column_column21", 5);
		this.elementValueTest(
			"detail.column_column21",
			"row1_column2",
			"row2_column2",
			"row1_column2",
			"row2_column2",
			"row3_column2");
		
		this.elementCountTest("detail.column_column31", 3);
		this.elementValueTest("detail.column_column31", "row1_column3", "row2_column3", "row3_column3");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(3);
	}
	
	static class SubreportExpression extends AbstractSimpleExpression<JasperReport>
	{

		@Override
		public JasperReport evaluate(final ReportParameters reportParameters)
		{
			try
			{
				final InputStream is = JasperSubreportTest.class.getResourceAsStream(
					"subreport" + reportParameters.getReportRowNumber() + ".jrxml");
				return JasperCompileManager.compileReport(is);
			}
			catch(final JRException e)
			{
				e.printStackTrace();
				Assertions.fail(e.getMessage());
				return null;
			}
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
