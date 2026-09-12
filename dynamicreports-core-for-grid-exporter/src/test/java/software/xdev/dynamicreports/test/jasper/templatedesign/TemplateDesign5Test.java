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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TemplateDesign5Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final InputStream is = TemplateDesign5Test.class.getResourceAsStream("templatedesign5.jrxml");
		final JasperReportBuilder report = report();
		report.setTemplateDesign(is);
		report.addParameter("parameter", "parametertest");
		report.setDataSource(new JREmptyDataSource(1));
		report.setPageFormat(575, 842, PageOrientation.PORTRAIT);
		
		rb.title(cmp.subreport(report))
			.columns(
				this.column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class));
	}
	
	@Override
	protected boolean serializableTest()
	{
		return false;
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailValueTest(this.column1, "row0", "row1");
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailValueTest(this.column2, "0", "1");
		
		this.elementValueTest("templateDesign.title1", "title");
		this.elementValueTest("templateDesign.title2", "parametertest");
		this.elementValueTest("templateDesign.pageHeader", "pageHeader");
		this.elementValueTest("templateDesign.pageFooter", "pageFooter");
		this.elementValueTest("templateDesign.detail", "detail");
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
}
