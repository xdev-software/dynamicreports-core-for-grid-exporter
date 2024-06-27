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

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.constant.JasperProperty;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class Crosstab6Test extends AbstractJasperCrosstabValueTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		this.measure1.addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true");
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setCellWidth(18)
			.rowGroups(this.rowGroup = ctab.rowGroup("field1", String.class)
				.addHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
				.addTotalHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
				.setHeaderStretchWithOverflow(false)
				.setTotalHeaderStretchWithOverflow(false)
				.setHeaderWidth(18))
			.columnGroups(this.columnGroup = ctab.columnGroup("field2", String.class)
				.addHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
				.addTotalHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
				.setHeaderStretchWithOverflow(false)
				.setTotalHeaderStretchWithOverflow(false))
			.measures(this.measure1);
		
		rb.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "c ", "d ");
		this.crosstabGroupHeaderFullValueTest(this.columnGroup, "c test test", "d test test");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "To");
		this.crosstabGroupTotalHeaderFullValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 2);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "a ", "b ");
		this.crosstabGroupHeaderFullValueTest(this.rowGroup, "a test test", "b test test");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "To");
		this.crosstabGroupTotalHeaderFullValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 4);
		this.crosstabCellValueTest(this.measure1, null, null, "30", "70", "11", "15");
		this.crosstabCellFullValueTest(this.measure1, null, null, "30", "70", "110", "150");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "10", "26");
		this.crosstabCellFullValueTest(this.measure1, null, this.columnGroup, "100", "260");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "14", "22");
		this.crosstabCellFullValueTest(this.measure1, this.rowGroup, null, "140", "220");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "36");
		this.crosstabCellFullValueTest(this.measure1, this.rowGroup, this.columnGroup, "360");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a test test", "c test test", 10);
		dataSource.add("a test test", "c test test", 20);
		dataSource.add("a test test", "d test test", 30);
		dataSource.add("a test test", "d test test", 40);
		dataSource.add("b test test", "c test test", 50);
		dataSource.add("b test test", "c test test", 60);
		dataSource.add("b test test", "d test test", 70);
		dataSource.add("b test test", "d test test", 80);
		return dataSource;
	}
}
