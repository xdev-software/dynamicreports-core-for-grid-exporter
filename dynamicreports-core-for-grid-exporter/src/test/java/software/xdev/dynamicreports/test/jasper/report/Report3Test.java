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

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JasperPrint;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Report3Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setReportName("report1")
			.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.setSummaryOnANewPage(true)
			.setSummaryWithPageHeaderAndFooter(true)
			.pageHeader(cmp.text("page header"))
			.pageFooter(cmp.text("page footer"))
			.summary(cmp.text("summary"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		final JasperPrint jasperPrint = this.getJasperPrint();
		Assertions.assertEquals("report1", jasperPrint.getName());
		
		// page header
		this.elementCountTest("pageHeader.textField1", 2);
		this.elementValueTest("pageHeader.textField1", "page header", "page header");
		
		// page footer
		this.elementCountTest("pageFooter.textField1", 2);
		this.elementValueTest("pageFooter.textField1", "page footer", "page footer");
		
		// summary
		this.elementCountTest("summary.textField1", 1);
		this.elementValueTest("summary.textField1", "summary");
	}
}
