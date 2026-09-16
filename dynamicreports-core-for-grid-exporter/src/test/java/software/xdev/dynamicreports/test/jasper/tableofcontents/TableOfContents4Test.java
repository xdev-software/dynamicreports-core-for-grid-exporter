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
package software.xdev.dynamicreports.test.jasper.tableofcontents;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.DRICustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TableOfContents4Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.tableOfContents()
			.pageHeader(cmp.text(new PageHeaderExpression()))
			.columns(
				column1 = col.column("Column1", "field1", type.stringType()),
				col.column("Column2", "field2", type.stringType()))
			.groupBy(column1);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(4);
		
		this.elementCountTest("pageHeader.textField1", 3);
		this.elementValueTest("pageHeader.textField1", "value1", "value3", "value5");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final String[] values = new String[]{"value1", "value2", "value3", "value4", "value5", "value6"};
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(final String value : values)
		{
			for(int i = 0; i < 20; i++)
			{
				dataSource.add(value, "text");
			}
		}
		return dataSource;
	}
	
	static class PageHeaderExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			final DRICustomValues customValues =
				(DRICustomValues)reportParameters.getParameterValue(DRICustomValues.NAME);
			final Map<String, JasperTocHeading> tocHeadings = customValues.getTocHeadings();
			if(tocHeadings.isEmpty())
			{
				return reportParameters.getValue("field1");
			}
			final List<JasperTocHeading> headings = new ArrayList<>(tocHeadings.values());
			final JasperTocHeading jasperTocHeading = headings.get(headings.size() - 1);
			return jasperTocHeading.getText();
		}
	}
}
