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
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.io.InputStream;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TemplateDesignPosition4Test extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final InputStream is = TemplateDesignPosition4Test.class.getResourceAsStream("templatedesign1.jrxml");
		final JasperReportBuilder report = report();
		report.setTemplateDesign(is)
			.setPageMargin(margin(25))
			.columns(
				this.column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class))
			.setDataSource(this.createSubreportDataSource());
		rb.setPageMargin(margin(25)).title(cmp.subreport(report));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("columnHeader.list1", 0, 50, 164, 495, 16);
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 247, 16);
		this.columnTitlePositionTest(this.column2, 0, 247, 0, 248, 16);
		this.elementPositionTest("detail.list1", 0, 50, 201, 495, 16);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 247, 16);
		this.columnDetailPositionTest(this.column2, 0, 247, 0, 248, 16);
		this.elementPositionTest("detail.list1", 1, 50, 238, 495, 16);
		this.columnDetailPositionTest(this.column1, 1, 0, 0, 247, 16);
		this.columnDetailPositionTest(this.column2, 1, 247, 0, 248, 16);
		
		this.elementPositionTest("templateDesign.title1", 0, 176, 78, 100, 20);
		this.elementPositionTest("templateDesign.pageHeader", 0, 176, 142, 100, 20);
		this.elementPositionTest("templateDesign.pageFooter", 0, 176, 358, 100, 20);
		this.elementPositionTest("templateDesign.detail", 0, 176, 180, 100, 20);
	}
	
	private JRDataSource createSubreportDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 2; i++)
		{
			dataSource.add("row" + i, i);
		}
		return dataSource;
	}
}
