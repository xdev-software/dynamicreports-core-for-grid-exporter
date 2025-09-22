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
package software.xdev.dynamicreports.test.jasper.crosstab;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class GroupExpressionCrosstabTest extends AbstractJasperCrosstabValueTest implements Serializable
{

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<Integer> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setCellWidth(50)
			.rowGroups(this.rowGroup = ctab.rowGroup("field1", String.class))
			.columnGroups(
				this.columnGroup1 = ctab.columnGroup(new GroupExpression1()),
				this.columnGroup2 = ctab.columnGroup(new GroupExpression2()))
			.measures(this.measure1);
		
		rb.setLocale(Locale.ENGLISH)
			.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			.fields(field("field2", Date.class))
			.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group 1
		this.crosstabGroupHeaderCountTest(this.columnGroup1, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup1, "2010", "2011");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup1, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup1, "Total");
		
		// column group 2
		this.crosstabGroupHeaderCountTest(this.columnGroup2, 5);
		this.crosstabGroupHeaderValueTest(this.columnGroup2, "Q1", "Q2", "Q3", "Q4", "Q1");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup2, 2);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup2, "Total", "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 2);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "a", "b");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 10);
		this.crosstabCellValueTest(this.measure1, null, null, "6", "15", "24", "33", "27", "39", "30", "21", "12", "3");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup1, 2);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup1, "105", "105");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup2, 4);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup2, "78", "27", "102", "3");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 5);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "45", "45", "45", "45", "30");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup1, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup1, "210");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup2, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup2, "180", "30");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a", this.toDate(2010, 1, 1), 1);
		dataSource.add("a", this.toDate(2010, 2, 1), 2);
		dataSource.add("a", this.toDate(2010, 3, 1), 3);
		dataSource.add("a", this.toDate(2010, 4, 1), 4);
		dataSource.add("a", this.toDate(2010, 5, 1), 5);
		dataSource.add("a", this.toDate(2010, 6, 1), 6);
		dataSource.add("a", this.toDate(2010, 7, 1), 7);
		dataSource.add("a", this.toDate(2010, 8, 1), 8);
		dataSource.add("a", this.toDate(2010, 9, 1), 9);
		dataSource.add("a", this.toDate(2010, 10, 1), 10);
		dataSource.add("a", this.toDate(2010, 11, 1), 11);
		dataSource.add("a", this.toDate(2010, 12, 1), 12);
		dataSource.add("a", this.toDate(2011, 1, 1), 13);
		dataSource.add("a", this.toDate(2011, 2, 1), 14);
		
		dataSource.add("b", this.toDate(2010, 1, 1), 14);
		dataSource.add("b", this.toDate(2010, 2, 1), 13);
		dataSource.add("b", this.toDate(2010, 3, 1), 12);
		dataSource.add("b", this.toDate(2010, 4, 1), 11);
		dataSource.add("b", this.toDate(2010, 5, 1), 10);
		dataSource.add("b", this.toDate(2010, 6, 1), 9);
		dataSource.add("b", this.toDate(2010, 7, 1), 8);
		dataSource.add("b", this.toDate(2010, 8, 1), 7);
		dataSource.add("b", this.toDate(2010, 9, 1), 6);
		dataSource.add("b", this.toDate(2010, 10, 1), 5);
		dataSource.add("b", this.toDate(2010, 11, 1), 4);
		dataSource.add("b", this.toDate(2010, 12, 1), 3);
		dataSource.add("b", this.toDate(2011, 1, 1), 2);
		dataSource.add("b", this.toDate(2011, 2, 1), 1);
		return dataSource;
	}
	
	static class GroupExpression1 extends AbstractSimpleExpression<Integer>
	{

		@Override
		public Integer evaluate(final ReportParameters reportParameters)
		{
			final Date date = reportParameters.getValue("field2");
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.YEAR);
		}
	}
	
	
	static class GroupExpression2 extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			final Date date = reportParameters.getValue("field2");
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			return switch(c.get(Calendar.MONTH))
			{
				case 0, 1, 2 -> "Q1";
				case 3, 4, 5 -> "Q2";
				case 6, 7, 8 -> "Q3";
				case 9, 10, 11 -> "Q4";
				default -> null;
			};
		}
	}
}
