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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class PageNumber1Test extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.pageFooter(cmp.pageNumber(), cmp.totalPages(), cmp.pageXslashY(), cmp.pageXofY());
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementCountTest("pageFooter.textField1", 1);
		this.elementValueTest("pageFooter.textField1", "1");
		
		this.elementCountTest("pageFooter.textField2", 1);
		this.elementValueTest("pageFooter.textField2", "1");
		
		this.elementCountTest("pageFooter.textField3", 1);
		this.elementValueTest("pageFooter.textField3", "1");
		
		this.elementCountTest("pageFooter.textField4", 1);
		this.elementValueTest("pageFooter.textField4", "/1");
		
		this.elementCountTest("pageFooter.textField5", 1);
		this.elementValueTest("pageFooter.textField5", "1");
		
		this.elementCountTest("pageFooter.textField6", 1);
		this.elementValueTest("pageFooter.textField6", " of 1");
	}
}
