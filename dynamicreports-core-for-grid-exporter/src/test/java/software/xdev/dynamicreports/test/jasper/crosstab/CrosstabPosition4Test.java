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


public class CrosstabPosition4Test extends AbstractJasperCrosstabPositionTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Double> measure2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
		final TextColumnBuilder<String> column2 = col.column("Column2", "field2", String.class);
		final TextColumnBuilder<Integer> column3 = col.column("Column3", "field3", Integer.class);
		final TextColumnBuilder<Double> column4 = col.column("Column4", "field4", Double.class);
		
		this.measure1 = ctab.measure(column3, Calculation.SUM);
		this.measure2 = ctab.measure(column4, Calculation.SUM);
		
		final CrosstabBuilder crosstab =
			ctab.crosstab()
				.setCellWidth(80)
				.setCellHeight(30)
				.rowGroups(this.rowGroup = ctab.rowGroup(column1))
				.columnGroups(this.columnGroup = ctab.columnGroup(column2))
				.measures(this.measure1, this.measure2);
		
		rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
			.columns(column1, column2, column3, column4)
			.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group
		this.crosstabGroupHeaderPositionTest(this.columnGroup, 0, 0, 0, 80, 16);
		this.crosstabGroupHeaderPositionTest(this.columnGroup, 1, 0, 0, 80, 16);
		this.crosstabGroupTotalHeaderPositionTest(this.columnGroup, 0, 0, 0, 80, 16);
		
		// row group
		this.crosstabGroupHeaderPositionTest(this.rowGroup, 0, 0, 0, 100, 30);
		this.crosstabGroupHeaderPositionTest(this.rowGroup, 1, 0, 0, 100, 30);
		this.crosstabGroupHeaderPositionTest(this.rowGroup, 2, 0, 0, 100, 30);
		this.crosstabGroupTotalHeaderPositionTest(this.rowGroup, 0, 0, 0, 100, 30);
		
		// measures
		for(int i = 0; i < 6; i++)
		{
			this.crosstabCellPositionTest(this.measure1, null, null, i, 0, 0, 40, 30);
			this.crosstabCellPositionTest(this.measure2, null, null, i, 40, 0, 40, 30);
		}
		for(int i = 0; i < 3; i++)
		{
			this.crosstabCellPositionTest(this.measure1, null, this.columnGroup, i, 0, 0, 40, 30);
			this.crosstabCellPositionTest(this.measure2, null, this.columnGroup, i, 40, 0, 40, 30);
		}
		for(int i = 0; i < 2; i++)
		{
			this.crosstabCellPositionTest(this.measure1, this.rowGroup, null, i, 0, 0, 40, 30);
			this.crosstabCellPositionTest(this.measure2, this.rowGroup, null, i, 40, 0, 40, 30);
		}
		this.crosstabCellPositionTest(this.measure1, this.rowGroup, this.columnGroup, 0, 0, 0, 40, 30);
		this.crosstabCellPositionTest(this.measure2, this.rowGroup, this.columnGroup, 0, 40, 0, 40, 30);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add("a", "c", 106, 2d);
		dataSource.add("a", "c", 252, 3d);
		dataSource.add("a", "d", 312, 4d);
		dataSource.add("a", "d", 456, 5d);
		dataSource.add("b", "c", 515, 6d);
		dataSource.add("b", "c", 678, 7d);
		dataSource.add("b", "d", 779, 8d);
		dataSource.add("b", "d", 823, 9d);
		dataSource.add("c", "c", 515, 6d);
		dataSource.add("c", "c", 678, 7d);
		dataSource.add("c", "d", 779, 8d);
		dataSource.add("c", "d", 823, 9d);
		return dataSource;
	}
}
