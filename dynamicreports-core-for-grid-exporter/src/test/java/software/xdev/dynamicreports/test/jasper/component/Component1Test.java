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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Component1Test extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.columns(col.column("Column1", "field1", Integer.class))
			.pageFooter(
				cmp.pageXofY(),
				cmp.pageXslashY(),
				cmp.currentDate().setPattern("dd.MM.yyyy"),
				cmp.totalPages(),
				cmp.pageNumber(),
				cmp.totalPages().setFormatExpression("total pages {0}"),
				cmp.pageNumber().setFormatExpression("page number {0}"),
				cmp.currentDate().setFormatExpression("current date {0}"),
				cmp.currentDate().setFormatExpression("current date {0}").setPattern("dd.MM.yyyy"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		this.elementCountTest("pageFooter.textField1", 3);
		this.elementValueTest("pageFooter.textField1", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField2", 3);
		this.elementValueTest("pageFooter.textField2", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField3", 3);
		this.elementValueTest("pageFooter.textField3", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField4", 3);
		this.elementValueTest("pageFooter.textField4", "/3", "/3", "/3");
		
		this.elementCountTest("pageFooter.textField5", 3);
		String date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		this.elementValueTest("pageFooter.textField5", date, date, date);
		
		this.elementCountTest("pageFooter.textField6", 3);
		this.elementValueTest("pageFooter.textField6", "3", "3", "3");
		
		this.elementCountTest("pageFooter.textField7", 3);
		this.elementValueTest("pageFooter.textField7", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField8", 3);
		this.elementValueTest("pageFooter.textField8", "total pages 3", "total pages 3", "total pages 3");
		
		this.elementCountTest("pageFooter.textField9", 3);
		this.elementValueTest("pageFooter.textField9", "page number 1", "page number 2", "page number 3");
		
		this.elementCountTest("pageFooter.textField10", 3);
		date = type.dateType().valueToString(new Date(), Locale.ENGLISH);
		this.elementValueTest(
			"pageFooter.textField10",
			"current date " + date,
			"current date " + date,
			"current date " + date);
		
		this.elementCountTest("pageFooter.textField11", 3);
		date = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
		this.elementValueTest(
			"pageFooter.textField11",
			"current date " + date,
			"current date " + date,
			"current date " + date);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 100; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
