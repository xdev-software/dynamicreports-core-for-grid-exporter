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
package software.xdev.dynamicreports.test.jasper.datasource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.util.Arrays;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class SubDatasourceTest extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<String> column4;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.stringType()))
			.detailFooter(cmp.subreport(this.createSubreport())
				.setDataSource(exp.subDatasourceBeanCollection("field3")));
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
		this.columnDetailValueTest(this.column1, "1", "2");
		// column2
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailCountTest(this.column2, 2);
		this.columnDetailValueTest(this.column2, "text1", "text2");
		
		// column3
		this.columnTitleCountTest(this.column3, 2);
		this.columnTitleValueTest(this.column3, "Column3", "Column3");
		this.columnDetailCountTest(this.column3, 3);
		this.columnDetailValueTest(this.column3, "texta1", "texta3", "textb1");
		// column4
		this.columnTitleCountTest(this.column4, 2);
		this.columnTitleValueTest(this.column4, "Column4", "Column4");
		this.columnDetailCountTest(this.column4, 3);
		this.columnDetailValueTest(this.column4, "texta2", "texta4", "textb2");
	}
	
	private JasperReportBuilder createSubreport()
	{
		final JasperReportBuilder report = report();
		report.columns(
			this.column3 = col.column("Column3", "field3", type.stringType()),
			this.column4 = col.column("Column4", "field4", type.stringType()));
		return report;
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("1", "text1", Arrays.asList(new SubData("texta1", "texta2"), new SubData("texta3", "texta4")));
		dataSource.add("2", "text2", Arrays.asList(new SubData("textb1", "textb2")));
		return dataSource;
	}
	
	static class SubData
	{
		private String field3;
		private String field4;
		
		SubData(final String field3, final String field4)
		{
			this.field3 = field3;
			this.field4 = field4;
		}
		
		public String getField3()
		{
			return this.field3;
		}
		
		public void setField3(final String field3)
		{
			this.field3 = field3;
		}
		
		public String getField4()
		{
			return this.field4;
		}
		
		public void setField4(final String field4)
		{
			this.field4 = field4;
		}
	}
}
