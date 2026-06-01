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
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class JasperSubreport2Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder detailSubreport =
			cmp.subreport(this.detailSubreport()).setDataSource(new SubreportDataSourceExpression());
		
		final SubreportBuilder titleSubreport =
			cmp.subreport(this.titleSubreport()).setDataSource(this.titleSubreportDataSource());
		
		rb.title(titleSubreport).detail(detailSubreport);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// title subreport
		this.elementCountTest("detail.column_field11", 3);
		this.elementValueTest("detail.column_field11", "value1", "value2", "value3");
		
		// detail subreport
		this.elementCountTest("title.textField1", 3);
		this.elementValueTest("title.textField1", "Subreport1", "Subreport2", "Subreport3");
		
		this.elementCountTest("detail.column_simpleExpression_0_1", 6);
		this.elementValueTest("detail.column_simpleExpression_0_1", "1_1", "1_2", "2_1", "2_2", "3_1", "3_2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(3);
	}
	
	private JasperReport titleSubreport()
	{
		try
		{
			final InputStream is = JasperSubreportTest.class.getResourceAsStream("titlesubreport.jrxml");
			return JasperCompileManager.compileReport(is);
		}
		catch(final JRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
			return null;
		}
	}
	
	private JasperReport detailSubreport()
	{
		try
		{
			final InputStream is = JasperSubreportTest.class.getResourceAsStream("detailsubreport.jrxml");
			return JasperCompileManager.compileReport(is);
		}
		catch(final JRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
			return null;
		}
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
}
