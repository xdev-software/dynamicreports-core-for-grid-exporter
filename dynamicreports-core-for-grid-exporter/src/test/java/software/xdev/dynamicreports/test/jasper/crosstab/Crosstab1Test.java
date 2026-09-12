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
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class Crosstab1Test extends AbstractJasperCrosstabValueTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Double> measure2;
	private CrosstabMeasureBuilder<Double> measure3;
	private CrosstabMeasureBuilder<Double> measure4;
	private CrosstabMeasureBuilder<Integer> measure5;
	private CrosstabMeasureBuilder<Double> measure6;
	private CrosstabMeasureBuilder<Double> measure7;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);
		final TextColumnBuilder<Double> column4 = col.column("Column4", "field4", Double.class);
		final TextColumnBuilder<String> column5 = col.column("Column5", "field5", String.class);
		
		this.measure1 = ctab.measure(column3, Calculation.SUM);
		this.measure2 = ctab.measure(column3, Calculation.SUM);
		this.measure2.setPercentageType(CrosstabPercentageType.GRAND_TOTAL);
		this.measure3 = ctab.measure(column4, Calculation.SUM);
		this.measure4 = ctab.measure(column4, Calculation.SUM);
		this.measure4.setPercentageType(CrosstabPercentageType.GRAND_TOTAL);
		this.measure5 = ctab.measure(column5, Calculation.COUNT);
		this.measure6 = ctab.measure(column5, Calculation.COUNT);
		this.measure6.setPercentageType(CrosstabPercentageType.GRAND_TOTAL);
		this.measure7 = ctab.measure(column4, Calculation.AVERAGE);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.headerCell(cmp.text("Header"))
			.rowGroups(this.rowGroup = ctab.rowGroup(column1).setTotalHeader("Total for rowgroup"))
			.columnGroups(this.columnGroup = ctab.columnGroup(column2))
			.measures(this.measure1, this.measure2, this.measure3, this.measure4, this.measure5, this.measure6,
				this.measure7);
		
		rb.setLocale(Locale.ENGLISH).columns(column1, column2, column3, column4, column5).summary(crosstab);
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
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "c", "d");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 3);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "a", "b", "c");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total for rowgroup");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 6);
		this.crosstabCellValueTest(this.measure1, null, null, "358", "768", "1,193", "1,602", "1,193", "1,602");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "1,126", "2,795", "2,795");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "2,744", "3,972");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "6,716");
		
		// measure2
		this.crosstabCellCountTest(this.measure2, null, null, 6);
		this.crosstabCellValueTest(this.measure2, null, null, "5.3", "11.4", "17.8", "23.9", "17.8", "23.9");
		this.crosstabCellCountTest(this.measure2, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure2, null, this.columnGroup, "16.8", "41.6", "41.6");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, null, "40.9", "59.1");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, this.columnGroup, "100");
		
		// measure3
		this.crosstabCellCountTest(this.measure3, null, null, 6);
		this.crosstabCellValueTest(this.measure3, null, null, "5", "9", "13", "17", "13", "17");
		this.crosstabCellCountTest(this.measure3, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure3, null, this.columnGroup, "14", "30", "30");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, null, "31", "43");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, this.columnGroup, "74");
		
		// measure4
		this.crosstabCellCountTest(this.measure4, null, null, 6);
		this.crosstabCellValueTest(this.measure4, null, null, "6.8", "12.2", "17.6", "23", "17.6", "23");
		this.crosstabCellCountTest(this.measure4, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure4, null, this.columnGroup, "18.9", "40.5", "40.5");
		this.crosstabCellCountTest(this.measure4, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure4, this.rowGroup, null, "41.9", "58.1");
		this.crosstabCellCountTest(this.measure4, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure4, this.rowGroup, this.columnGroup, "100");
		
		// measure5
		this.crosstabCellCountTest(this.measure5, null, null, 6);
		this.crosstabCellValueTest(this.measure5, null, null, "2", "2", "2", "2", "2", "2");
		this.crosstabCellCountTest(this.measure5, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure5, null, this.columnGroup, "4", "4", "4");
		this.crosstabCellCountTest(this.measure5, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure5, this.rowGroup, null, "6", "6");
		this.crosstabCellCountTest(this.measure5, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure5, this.rowGroup, this.columnGroup, "12");
		
		// measure6
		this.crosstabCellCountTest(this.measure6, null, null, 6);
		this.crosstabCellValueTest(this.measure6, null, null, "16", "16", "16", "16", "16", "16");
		this.crosstabCellCountTest(this.measure6, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure6, null, this.columnGroup, "33", "33", "33");
		this.crosstabCellCountTest(this.measure6, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure6, this.rowGroup, null, "50", "50");
		this.crosstabCellCountTest(this.measure6, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure6, this.rowGroup, this.columnGroup, "100");
		
		// measure7
		this.crosstabCellCountTest(this.measure7, null, null, 6);
		this.crosstabCellValueTest(this.measure7, null, null, "2.5", "4.5", "6.5", "8.5", "6.5", "8.5");
		this.crosstabCellCountTest(this.measure7, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure7, null, this.columnGroup, "3.5", "7.5", "7.5");
		this.crosstabCellCountTest(this.measure7, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure7, this.rowGroup, null, "5.2", "7.2");
		this.crosstabCellCountTest(this.measure7, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure7, this.rowGroup, this.columnGroup, "6.2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		dataSource.add("a", "c", 106, 2d, "1");
		dataSource.add("a", "c", 252, 3d, "1");
		dataSource.add("a", "d", 312, 4d, "1");
		dataSource.add("a", "d", 456, 5d, "4");
		dataSource.add("b", "c", 515, 6d, "5");
		dataSource.add("b", "c", 678, 7d, "6");
		dataSource.add("b", "d", 779, 8d, "7");
		dataSource.add("b", "d", 823, 9d, "8");
		dataSource.add("c", "c", 515, 6d, "5");
		dataSource.add("c", "c", 678, 7d, "6");
		dataSource.add("c", "d", 779, 8d, "7");
		dataSource.add("c", "d", 823, 9d, "8");
		return dataSource;
	}
}
