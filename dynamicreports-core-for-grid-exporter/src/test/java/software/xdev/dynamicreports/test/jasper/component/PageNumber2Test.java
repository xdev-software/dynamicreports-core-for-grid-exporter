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
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class PageNumber2Test extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(col.column("Column1", "field1", Integer.class))
			.summaryOnANewPage()
			.summaryWithPageHeaderAndFooter()
			.summary(cmp.text("summary"))
			.pageFooter(cmp.pageNumber(), cmp.totalPages(), cmp.pageXslashY(), cmp.pageXofY());
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		this.elementCountTest("pageFooter.textField1", 2);
		this.elementValueTest("pageFooter.textField1", "1", "2");
		
		this.elementCountTest("pageFooter.textField2", 2);
		this.elementValueTest("pageFooter.textField2", "2", "2");
		
		this.elementCountTest("pageFooter.textField3", 2);
		this.elementValueTest("pageFooter.textField3", "1", "2");
		
		this.elementCountTest("pageFooter.textField4", 2);
		this.elementValueTest("pageFooter.textField4", "/2", "/2");
		
		this.elementCountTest("pageFooter.textField5", 2);
		this.elementValueTest("pageFooter.textField5", "1", "2");
		
		this.elementCountTest("pageFooter.textField6", 2);
		this.elementValueTest("pageFooter.textField6", " of 2", " of 2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
