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
package software.xdev.dynamicreports.test.jasper.group;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class CustomGroupTest extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private CustomGroupBuilder group1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final YearExpression yearExpression = new YearExpression();
		rb.columns(
			this.column1 = col.column("Column1", "field1", String.class),
			this.column2 = col.column("Column2", yearExpression)).groupBy(this.group1 = grp.group(yearExpression));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnTitleCountTest(this.column1, 1);
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailCountTest(this.column1, 2);
		this.columnDetailValueTest(this.column1, "2009-12-01", "2010-01-01");
		
		// column2
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailCountTest(this.column2, 2);
		this.columnDetailValueTest(this.column2, "2009", "2010");
		
		// group1
		this.groupHeaderTitleCountTest(this.group1, 0);
		this.groupHeaderCountTest(this.group1, 2);
		this.groupHeaderValueTest(this.group1, "2009", "2010");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 1; i++)
		{
			dataSource.add("2009-12-01");
			dataSource.add("2010-01-01");
		}
		return dataSource;
	}
	
	static class YearExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			final String orderDate = reportParameters.getValue("field1");
			return orderDate.split("-")[0];
		}
	}
}
