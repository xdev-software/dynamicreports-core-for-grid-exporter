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
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;


public class CrosstabStyle5Test extends AbstractJasperCrosstabStyleTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Integer> measure2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final FieldBuilder<String> field1 = field("field1", String.class);
		final FieldBuilder<String> field2 = field("field2", String.class);
		final StyleBuilder headerStyle = stl.style().setBackgroundColor(Color.LIGHT_GRAY);
		final StyleBuilder cellStyle = stl.style().bold();
		final StyleBuilder titleStyle = stl.style(cellStyle).setBackgroundColor(Color.BLUE);
		
		this.rowGroup = ctab.rowGroup(field1).setHeaderStyle(headerStyle).setTotalHeaderStyle(headerStyle);
		this.columnGroup1 =
			ctab.columnGroup(field1).setHeaderStyle(headerStyle).setTotalHeaderStyle(headerStyle);
		this.columnGroup2 =
			ctab.columnGroup(field2).setHeaderStyle(headerStyle).setTotalHeaderStyle(headerStyle);
		
		this.measure1 = ctab.measure("m1", "field3", Integer.class, Calculation.SUM);
		this.measure1.setTitleStyle(titleStyle);
		this.measure2 = ctab.measure("m2", "field3", Integer.class, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab().setCellWidth(50).highlightEvenRows()
			.setGroupTotalStyle(stl.style().setBackgroundColor(Color.RED)).rowGroups(this.rowGroup)
			.columnGroups(this.columnGroup1, this.columnGroup2).measures(this.measure1, this.measure2);
		
		rb.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		this.crosstabGroupTitleTotalHeaderStyleTest(
			this.columnGroup1, this.measure1, 0, null, Color.BLUE,
			TEST_FONT_NAME, 10f, true, null);
		this.crosstabGroupTitleTotalHeaderStyleTest(
			this.columnGroup1, this.measure2, 0, null, Color.LIGHT_GRAY,
			TEST_FONT_NAME, 10f, null, null);
		
		this.crosstabGroupTitleHeaderStyleTest(
			this.columnGroup2, this.measure1, 0, null, Color.BLUE, TEST_FONT_NAME,
			10f, true, null);
		this.crosstabGroupTitleTotalHeaderStyleTest(
			this.columnGroup2, this.measure1, 0, null, Color.BLUE,
			TEST_FONT_NAME, 10f, true, null);
		this.crosstabGroupTitleHeaderStyleTest(
			this.columnGroup2, this.measure2, 0, null, Color.LIGHT_GRAY,
			TEST_FONT_NAME, 10f, null, null);
		this.crosstabGroupTitleTotalHeaderStyleTest(
			this.columnGroup2, this.measure2, 0, null, Color.LIGHT_GRAY,
			TEST_FONT_NAME, 10f, null, null);
		
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup, null, 0, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup, null, 1, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup, this.columnGroup1, 0, null, Color.RED, TEST_FONT_NAME, 10f,
			null, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup, this.columnGroup2, 0, null, Color.RED, TEST_FONT_NAME, 10f,
			null, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("a", "c", 2);
		dataSource.add("a", "d", 3);
		dataSource.add("a", "d", 4);
		return dataSource;
	}
}
