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
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Subreport4Test extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final Map<String, Object> parameters1 = new HashMap<>();
		parameters1.put("parameter5", "value5");
		parameters1.put("parameter6", "value6");
		final Map<String, Object> parameters2 = new HashMap<>();
		parameters2.put("parameter5", "value7");
		
		final SubreportBuilder subreport1 = cmp.subreport(this.subreport1()).setParameters(parameters1);
		final SubreportBuilder subreport2 = cmp.subreport(this.subreport2()).setParameters(parameters2);
		
		rb.addParameter("parameter4", "value4").title(subreport1, subreport2);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementCountTest("title.textField1", 1);
		this.elementValueTest("title.textField1", "value1");
		this.elementCountTest("title.textField2", 1);
		this.elementValueTest("title.textField2", "value2");
		this.elementCountTest("title.textField3", 1);
		this.elementValueTest("title.textField3", "value6");
		this.elementCountTest("summary.textField1", 2);
		this.elementValueTest("summary.textField1", "value3", "");
		this.elementCountTest("summary.textField2", 2);
		this.elementValueTest("summary.textField2", "value4", "value4");
		this.elementCountTest("summary.textField3", 2);
		this.elementValueTest("summary.textField3", "value5", "value7");
	}
	
	private JasperReportBuilder subreport1() throws DRException
	{
		final JasperReportBuilder report = report();
		report.addParameter("parameter1", "value1")
			.addParameter("parameter2", String.class)
			.addParameter("parameter6", String.class)
			.setParameter("parameter2", "value2")
			.setParameter("parameter3", "value3")
			.setTemplateDesign(Subreport4Test.class.getResourceAsStream("subreport4.jrxml"))
			.title(
				cmp.text(exp.jasperSyntax("$P{parameter1}", String.class)),
				cmp.text(exp.jasperSyntax("$P{parameter2}", String.class)),
				cmp.text(exp.jasperSyntax("$P{parameter6}", String.class)));
		return report;
	}
	
	private JasperReport subreport2()
	{
		try
		{
			final InputStream is = Subreport4Test.class.getResourceAsStream("subreport4.jrxml");
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
