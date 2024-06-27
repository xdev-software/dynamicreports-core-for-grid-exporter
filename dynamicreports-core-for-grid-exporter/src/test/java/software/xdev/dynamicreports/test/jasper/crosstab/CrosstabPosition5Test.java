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


public class CrosstabPosition5Test extends AbstractJasperCrosstabPositionTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Double> measure2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field1", String.class);
		final TextColumnBuilder<String> column3 = col.column("Column3", "field2", String.class);
		final TextColumnBuilder<Integer> column4 = col.column("Column4", "field3", Integer.class);
		final TextColumnBuilder<Double> column5 = col.column("Column5", "field4", Double.class);
		
		this.measure1 = ctab.measure("measure1", column4, Calculation.SUM);
		this.measure2 = ctab.measure("measure2", column5, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.headerCell(cmp.text("Header"))
			.setCellWidth(100)
			.rowGroups(this.rowGroup = ctab.rowGroup(column1).setHeaderWidth(50))
			.columnGroups(this.columnGroup1 = ctab.columnGroup(column2), this.columnGroup2 = ctab.columnGroup(column3))
			.measures(this.measure1, this.measure2);
		
		rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE).summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		this.crosstabHeaderElementPositionTest("textField1", 0, 0, 0, 50, 48);
		
		// column group 1
		this.crosstabGroupHeaderPositionTest(this.columnGroup1, 0, 0, 0, 300, 16);
		this.crosstabGroupTotalHeaderPositionTest(this.columnGroup1, 0, 0, 0, 100, 24);
		
		this.crosstabGroupTitleTotalHeaderPositionTest(this.columnGroup1, this.measure1, 0, 0, 0, 50, 24);
		this.crosstabGroupTitleTotalHeaderPositionTest(this.columnGroup1, this.measure2, 0, 50, 0, 50, 24);
		
		// column group 2
		this.crosstabGroupHeaderPositionTest(this.columnGroup2, 0, 0, 0, 100, 16);
		this.crosstabGroupHeaderPositionTest(this.columnGroup2, 1, 0, 0, 100, 16);
		this.crosstabGroupTotalHeaderPositionTest(this.columnGroup2, 0, 0, 0, 100, 16);
		
		this.crosstabGroupTitleHeaderPositionTest(this.columnGroup2, this.measure1, 0, 0, 0, 50, 16);
		this.crosstabGroupTitleHeaderPositionTest(this.columnGroup2, this.measure2, 0, 50, 0, 50, 16);
		this.crosstabGroupTitleHeaderPositionTest(this.columnGroup2, this.measure1, 1, 0, 0, 50, 16);
		this.crosstabGroupTitleHeaderPositionTest(this.columnGroup2, this.measure2, 1, 50, 0, 50, 16);
		this.crosstabGroupTitleTotalHeaderPositionTest(this.columnGroup2, this.measure1, 0, 0, 0, 50, 16);
		this.crosstabGroupTitleTotalHeaderPositionTest(this.columnGroup2, this.measure2, 0, 50, 0, 50, 16);
		
		// row group
		this.crosstabGroupHeaderPositionTest(this.rowGroup, 0, 0, 0, 50, 16);
		this.crosstabGroupTotalHeaderPositionTest(this.rowGroup, 0, 0, 0, 50, 16);
		
		// measures
		for(int i = 0; i < 2; i++)
		{
			this.crosstabCellPositionTest(this.measure1, null, null, i, 0, 0, 50, 16);
			this.crosstabCellPositionTest(this.measure2, null, null, i, 50, 0, 50, 16);
		}
		this.crosstabCellPositionTest(this.measure1, null, this.columnGroup1, 0, 0, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure2, null, this.columnGroup1, 0, 50, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure1, null, this.columnGroup2, 0, 0, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure2, null, this.columnGroup2, 0, 50, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure1, this.rowGroup, null, 0, 0, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup, null, 0, 50, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure1, this.rowGroup, this.columnGroup1, 0, 0, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup, this.columnGroup1, 0, 50, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure1, this.rowGroup, this.columnGroup2, 0, 0, 0, 50, 16);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup, this.columnGroup2, 0, 50, 0, 50, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add("a", "c", 1, 2d);
		dataSource.add("a", "c", 2, 3d);
		dataSource.add("a", "d", 3, 4d);
		dataSource.add("a", "d", 4, 5d);
		return dataSource;
	}
}
