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

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import java.io.InputStream;
import java.io.Serializable;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TemplateDesign1Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final InputStream is = TemplateDesign1Test.class.getResourceAsStream("templatedesign1.jrxml");
		rb.setTemplateDesign(is)
			.addParameter("parameter", "parametertest")
			.columns(
				this.column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class))
			.subtotalsAtSummary(this.subtotal1 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		final JasperPrint jasperPrint = this.getJasperPrint();
		Assertions.assertEquals("templatedesign1", jasperPrint.getName());
		
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailValueTest(this.column1, "row0", "row1");
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailValueTest(this.column2, "0", "1");
		this.subtotalValueTest(this.subtotal1, "1");
		
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
