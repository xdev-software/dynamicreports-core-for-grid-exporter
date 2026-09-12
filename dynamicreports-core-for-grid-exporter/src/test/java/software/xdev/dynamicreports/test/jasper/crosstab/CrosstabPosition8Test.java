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

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

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
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabPositionTest;


public class CrosstabPosition8Test extends AbstractJasperCrosstabPositionTest
{
	private CrosstabRowGroupBuilder<String> rowGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Integer> measure2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);
		
		this.rowGroup1 = ctab.rowGroup(column1).setHeaderWidth(22);
		this.measure1 = ctab.measure("measure1", column3, Calculation.SUM);
		this.measure2 = ctab.measure("measure2", column3, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setCellWidth(100)
			.rowGroups(this.rowGroup1)
			.columnGroups(this.columnGroup1 = ctab.columnGroup(column2))
			.measures(this.measure1, this.measure2);
		
		rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE).summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group 1
		this.crosstabGroupHeaderPositionTest(this.columnGroup1, 0, 0, 0, 100, 16);
		this.crosstabGroupTotalHeaderPositionTest(this.columnGroup1, 0, 0, 0, 100, 16);
		
		// row group 1
		this.crosstabGroupHeaderPositionTest(this.rowGroup1, 0, 0, 0, 22, 26);
		this.crosstabGroupTotalHeaderPositionTest(this.rowGroup1, 0, 0, 0, 22, 26);
		
		// measures
		this.crosstabCellPositionTest(this.measure1, null, null, 0, 0, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure1, this.rowGroup1, null, 0, 0, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure1, null, this.columnGroup1, 0, 0, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure1, this.rowGroup1, this.columnGroup1, 0, 0, 0, 50, 26);
		
		this.crosstabCellPositionTest(this.measure2, null, null, 0, 50, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup1, null, 0, 50, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure2, null, this.columnGroup1, 0, 50, 0, 50, 26);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup1, this.columnGroup1, 0, 50, 0, 50, 26);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("text text", "a", 1);
		return dataSource;
	}
}
