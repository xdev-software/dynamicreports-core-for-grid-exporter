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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class ParametersTest extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.title(cmp.text(new TitleExpression())).addParameter("title", String.class);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementValueTest("title.textField1", "");
		
		try
		{
			JasperReport jasperReport = this.getJasperReport();
			JasperPrint jasperPrint = this.getJasperPrint();
			this.getReportBuilder().setParameter("title", "1");
			this.build();
			this.elementValueTest("title.textField1", "1");
			Assertions.assertSame(jasperReport, this.getJasperReport());
			Assertions.assertNotSame(jasperPrint, this.getJasperPrint());
			
			jasperReport = this.getJasperReport();
			jasperPrint = this.getJasperPrint();
			this.getReportBuilder().setParameter("title", "2");
			this.build();
			this.elementValueTest("title.textField1", "2");
			Assertions.assertSame(jasperReport, this.getJasperReport());
			Assertions.assertNotSame(jasperPrint, this.getJasperPrint());
			
			jasperReport = this.getJasperReport();
			jasperPrint = this.getJasperPrint();
			final Map<String, Object> parameters = new HashMap<>();
			parameters.put("title", "3");
			this.getReportBuilder().setParameters(parameters);
			this.build();
			this.elementValueTest("title.textField1", "3");
			Assertions.assertSame(jasperReport, this.getJasperReport());
			Assertions.assertNotSame(jasperPrint, this.getJasperPrint());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	static class TitleExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getValue("title");
		}
	}
}
