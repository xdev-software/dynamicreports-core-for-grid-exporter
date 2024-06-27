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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class Crosstab2Test extends AbstractJasperCrosstabValueTest
{
	private CrosstabRowGroupBuilder<String> rowGroup1;
	private CrosstabRowGroupBuilder<String> rowGroup2;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Integer> measure2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		final TextColumnBuilder<String> column3 = col.column("Column3", "field3", String.class);
		final TextColumnBuilder<String> column4 = col.column("Column4", "field4", String.class);
		final TextColumnBuilder<Integer> column5 = col.column("Column5", "field5", Integer.class);
		
		this.measure1 = ctab.measure("measure1", column5, Calculation.SUM);
		this.measure2 = ctab.measure("measure2", column5, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.headerCell(cmp.text("Header"))
			.setCellWidth(20)
			.rowGroups(this.rowGroup1 = ctab.rowGroup(column1), this.rowGroup2 = ctab.rowGroup(column2))
			.columnGroups(this.columnGroup1 = ctab.columnGroup(column3), this.columnGroup2 = ctab.columnGroup(column4))
			.measures(this.measure1, this.measure2);
		
		rb.setLocale(Locale.ENGLISH)
			.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
			.columns(column1, column2, column3, column4, column5)
			.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		this.crosstabHeaderElementCountTest("textField1", 1);
		this.crosstabHeaderElementValueTest("textField1", "Header");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup1, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup1, "g", "h");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup1, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup1, "Total");
		this.crosstabGroupHeaderCountTest(this.columnGroup2, 4);
		this.crosstabGroupHeaderValueTest(this.columnGroup2, "i", "j", "k", "l");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup2, 2);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup2, "Total", "Total");
		
		// column group title
		this.crosstabGroupTitleHeaderCountTest(this.columnGroup1, this.measure1, 0);
		this.crosstabGroupTitleTotalHeaderCountTest(this.columnGroup1, this.measure1, 1);
		this.crosstabGroupTitleTotalHeaderValueTest(this.columnGroup1, this.measure1, "measure1");
		this.crosstabGroupTitleHeaderCountTest(this.columnGroup2, this.measure1, 4);
		this.crosstabGroupTitleHeaderValueTest(this.columnGroup2,
			this.measure1, "measure1", "measure1", "measure1", "measure1");
		this.crosstabGroupTitleTotalHeaderCountTest(this.columnGroup2, this.measure1, 2);
		this.crosstabGroupTitleTotalHeaderValueTest(this.columnGroup2, this.measure1, "measure1", "measure1");
		
		this.crosstabGroupTitleHeaderCountTest(this.columnGroup1, this.measure2, 0);
		this.crosstabGroupTitleTotalHeaderCountTest(this.columnGroup1, this.measure2, 1);
		this.crosstabGroupTitleTotalHeaderValueTest(this.columnGroup1, this.measure2, "measure2");
		this.crosstabGroupTitleHeaderCountTest(this.columnGroup2, this.measure2, 4);
		this.crosstabGroupTitleHeaderValueTest(this.columnGroup2,
			this.measure2, "measure2", "measure2", "measure2", "measure2");
		this.crosstabGroupTitleTotalHeaderCountTest(this.columnGroup2, this.measure2, 2);
		this.crosstabGroupTitleTotalHeaderValueTest(this.columnGroup2, this.measure2, "measure2", "measure2");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup1, 2);
		this.crosstabGroupHeaderValueTest(this.rowGroup1, "a", "b");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup1, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup1, "Total");
		this.crosstabGroupHeaderCountTest(this.rowGroup2, 4);
		this.crosstabGroupHeaderValueTest(this.rowGroup2, "c", "d", "e", "f");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup2, 2);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup2, "Total", "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 16);
		this.crosstabCellValueTest(
			this.measure1,
			null,
			null,
			"1",
			"2",
			"0",
			"0",
			"3",
			"4",
			"0",
			"0",
			"0",
			"0",
			"5",
			"6",
			"0",
			"0",
			"7",
			"8");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup1, 4);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup1, "3", "7", "11", "15");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup2, 8);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup2, "3", "0", "7", "0", "0", "11", "0", "15");
		this.crosstabCellCountTest(this.measure1, this.rowGroup1, null, 4);
		this.crosstabCellValueTest(this.measure1, this.rowGroup1, null, "4", "6", "12", "14");
		this.crosstabCellCountTest(this.measure1, this.rowGroup2, null, 8);
		this.crosstabCellValueTest(this.measure1, this.rowGroup2, null, "4", "6", "0", "0", "0", "0", "12", "14");
		this.crosstabCellCountTest(this.measure1, this.rowGroup1, this.columnGroup1, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup1, this.columnGroup1, "36");
		this.crosstabCellCountTest(this.measure1, this.rowGroup2, this.columnGroup2, 4);
		this.crosstabCellValueTest(this.measure1, this.rowGroup2, this.columnGroup2, "10", "0", "0", "26");
		this.crosstabCellCountTest(this.measure1, this.rowGroup1, this.columnGroup2, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup1, this.columnGroup2, "10", "26");
		this.crosstabCellCountTest(this.measure1, this.rowGroup2, this.columnGroup1, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup2, this.columnGroup1, "10", "26");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		dataSource.add("a", "c", "g", "i", 1);
		dataSource.add("a", "c", "g", "j", 2);
		dataSource.add("a", "d", "g", "i", 3);
		dataSource.add("a", "d", "g", "j", 4);
		dataSource.add("b", "e", "h", "k", 5);
		dataSource.add("b", "e", "h", "l", 6);
		dataSource.add("b", "f", "h", "k", 7);
		dataSource.add("b", "f", "h", "l", 8);
		return dataSource;
	}
}
