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
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class PageNumber3Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder subreport =
			cmp.subreport(this.titleSubreport()).setDataSource(new SubreportDataSourceExpression());
		
		rb.detail(subreport)
			.pageFooter(
				cmp.pageNumber(),
				cmp.totalPages(),
				cmp.pageXslashY(),
				cmp.pageXofY(),
				cmp.pageXofY().setFormatExpression("Page {0} of {1}"),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		this.elementCountTest("pageFooter.textField1", 3);
		this.elementValueTest("pageFooter.textField1", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField2", 3);
		this.elementValueTest("pageFooter.textField2", "3", "3", "3");
		
		this.elementCountTest("pageFooter.textField3", 3);
		this.elementValueTest("pageFooter.textField3", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField4", 3);
		this.elementValueTest("pageFooter.textField4", "/3", "/3", "/3");
		
		this.elementCountTest("pageFooter.textField5", 3);
		this.elementValueTest("pageFooter.textField5", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField6", 3);
		this.elementValueTest("pageFooter.textField6", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField7", 3);
		this.elementValueTest("pageFooter.textField7", "Page 1", "Page 2", "Page 3");
		
		this.elementCountTest("pageFooter.textField8", 3);
		this.elementValueTest("pageFooter.textField8", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField9", 3);
		this.elementValueTest("pageFooter.textField9", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField10", 3);
		this.elementValueTest("pageFooter.textField10", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField11", 3);
		this.elementValueTest("pageFooter.textField11", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField12", 3);
		this.elementValueTest("pageFooter.textField12", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField13", 3);
		this.elementValueTest("pageFooter.textField13", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField14", 3);
		this.elementValueTest("pageFooter.textField14", " of 3", " of 3", " of 3");
		
		this.elementCountTest("pageFooter.textField15", 3);
		this.elementValueTest("pageFooter.textField15", "1", "2", "3");
		
		this.elementCountTest("pageFooter.textField16", 3);
		this.elementValueTest("pageFooter.textField16", " of 3", " of 3", " of 3");
	}
	
	private JasperReportBuilder titleSubreport()
	{
		final JasperReportBuilder report = report();
		report.columns(col.column("Column1", "field1", type.integerType()))
			.setDataSource(this.titleSubreportDataSource());
		return report;
	}
	
	private JRDataSource titleSubreportDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 50; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JREmptyDataSource(2);
	}
	
	static class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource>
	{

		@Override
		public JRDataSource evaluate(final ReportParameters reportParameters)
		{
			final DRDataSource dataSource = new DRDataSource("field1");
			for(int i = 0; i < 50; i++)
			{
				dataSource.add(i);
			}
			return dataSource;
		}
	}
}
