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
package software.xdev.dynamicreports.test.jasper.templatedesign;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.io.InputStream;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TemplateDesign2Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final SubreportBuilder titleSubreport =
			cmp.subreport(this.titleSubreport()).setDataSource(this.titleSubreportDataSource());
		
		final InputStream is = TemplateDesign2Test.class.getResourceAsStream("templatedesign2.jrxml");
		rb.setTemplateDesign(is).title(titleSubreport).columns(this.column1 = col.column("Column1", "field1",
			String.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailValueTest(this.column1, "row0", "row1");
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailValueTest(this.column2, "value1", "value2", "value3");
		this.elementValueTest("title.textField1", "subreport");
		this.elementValueTest("templateDesign.pageHeader", "pageHeader");
		this.elementValueTest("templateDesign.pageFooter", "pageFooter");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 2; i++)
		{
			dataSource.add("row" + i, i);
		}
		return dataSource;
	}
	
	private JasperReportBuilder titleSubreport() throws DRException
	{
		final InputStream is = TemplateDesign2Test.class.getResourceAsStream("templatedesign3.jrxml");
		final JasperReportBuilder report = report();
		report.setTemplateDesign(is)
			.title(cmp.text("subreport"))
			.columns(this.column2 = col.column("Column2", "field2", String.class));
		return report;
	}
	
	private JRDataSource titleSubreportDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field2");
		dataSource.add("value1");
		dataSource.add("value2");
		dataSource.add("value3");
		return dataSource;
	}
}
