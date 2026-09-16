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
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;


public class CrosstabStyle4Test extends AbstractJasperCrosstabStyleTest
{
	private CrosstabRowGroupBuilder<String> rowGroup1;
	private CrosstabRowGroupBuilder<String> rowGroup2;
	private CrosstabColumnGroupBuilder<String> columnGroup1;
	private CrosstabColumnGroupBuilder<String> columnGroup2;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final FieldBuilder<String> field1 = field("field1", String.class);
		final FieldBuilder<String> field2 = field("field2", String.class);
		final FieldBuilder<String> field3 = field("field3", String.class);
		final FieldBuilder<String> field4 = field("field4", String.class);
		
		this.rowGroup1 = ctab.rowGroup(field1);
		this.rowGroup2 = ctab.rowGroup(field2);
		this.columnGroup1 = ctab.columnGroup(field3);
		this.columnGroup2 = ctab.columnGroup(field4);
		
		this.measure1 = ctab.measure("field5", Integer.class, Calculation.SUM);
		
		final CrosstabBuilder crosstab = ctab.crosstab().highlightEvenRows().setCellWidth(50)
			.setEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.LIGHT_GRAY).bold())
			.setGroupStyle(stl.style(1).setBackgroundColor(Color.BLUE).bold())
			.setGroupTotalStyle(stl.style(1).setBackgroundColor(Color.RED))
			.setGrandTotalStyle(stl.style(1).setBackgroundColor(Color.MAGENTA).bold())
			.setCellStyle(stl.style().setBackgroundColor(Color.YELLOW).italic())
			.rowGroups(this.rowGroup1, this.rowGroup2).columnGroups(this.columnGroup1, this.columnGroup2)
			.measures(this.measure1);
		
		rb.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		final Color color1 = new Color(239, 239, 48);
		final Color color2 = new Color(239, 48, 239);
		final Color color3 = new Color(239, 48, 48);
		
		this.crosstabGroupHeaderStyleTest(this.rowGroup1, 0, null, Color.BLUE, TEST_FONT_NAME, 10f, true, null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.rowGroup1, 0, null, Color.MAGENTA, TEST_FONT_NAME, 10f, true,
			null);
		
		this.crosstabGroupHeaderStyleTest(this.rowGroup2, 0, null, Color.BLUE, TEST_FONT_NAME, 10f, true, null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.rowGroup2, 0, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		
		this.crosstabGroupHeaderStyleTest(
			this.columnGroup1, 0, null, Color.BLUE, TEST_FONT_NAME, 10f, true,
			null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.columnGroup1, 0, null, Color.MAGENTA, TEST_FONT_NAME, 10f,
			true, null);
		
		this.crosstabGroupHeaderStyleTest(
			this.columnGroup2, 0, null, Color.BLUE, TEST_FONT_NAME, 10f, true,
			null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.columnGroup2, 0, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		
		this.crosstabCellStyleTest(
			this.measure1, null, null, 0, null, Color.YELLOW, TEST_FONT_NAME, 10f, null,
			true);
		this.crosstabCellStyleTest(
			this.measure1, null, null, 1, null, Color.YELLOW, TEST_FONT_NAME, 10f, null,
			true);
		this.crosstabCellStyleTest(this.measure1, null, null, 2, null, color1, TEST_FONT_NAME, 10f, true, true);
		this.crosstabCellStyleTest(this.measure1, null, null, 3, null, color1, TEST_FONT_NAME, 10f, true, true);
		this.crosstabCellStyleTest(
			this.measure1, null, null, 4, null, Color.YELLOW, TEST_FONT_NAME, 10f, null,
			true);
		this.crosstabCellStyleTest(
			this.measure1, null, null, 5, null, Color.YELLOW, TEST_FONT_NAME, 10f, null,
			true);
		this.crosstabCellStyleTest(this.measure1, null, null, 6, null, color1, TEST_FONT_NAME, 10f, true, true);
		this.crosstabCellStyleTest(this.measure1, null, null, 7, null, color1, TEST_FONT_NAME, 10f, true, true);
		
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup1, null, 0, null, Color.MAGENTA, TEST_FONT_NAME, 10f,
			true, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup1, null, 1, null, Color.MAGENTA, TEST_FONT_NAME, 10f,
			true, null);
		
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, null, 0, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, null, 1, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, null, 2, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, null, 3, null, Color.RED, TEST_FONT_NAME, 10f, null,
			null);
		
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup1, 0, null, Color.MAGENTA, TEST_FONT_NAME, 10f,
			true, null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup1, 1, null, color2, TEST_FONT_NAME, 10f, true,
			null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup1, 2, null, Color.MAGENTA, TEST_FONT_NAME, 10f,
			true, null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup1, 3, null, color2, TEST_FONT_NAME, 10f, true,
			null);
		
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup2, 0, null, Color.RED, TEST_FONT_NAME, 10f,
			null, null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup2, 1, null, color3, TEST_FONT_NAME, 10f, true,
			null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup2, 2, null, Color.RED, TEST_FONT_NAME, 10f,
			null, null);
		this.crosstabCellStyleTest(
			this.measure1, null, this.columnGroup2, 3, null, color3, TEST_FONT_NAME, 10f, true,
			null);
		
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup1, this.columnGroup1, 0, null, Color.MAGENTA, TEST_FONT_NAME,
			10f, true, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup1, this.columnGroup2, 0, null, Color.MAGENTA, TEST_FONT_NAME,
			10f, true, null);
		
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, this.columnGroup1, 0, null, Color.MAGENTA, TEST_FONT_NAME,
			10f, true, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, this.columnGroup2, 0, null, Color.RED, TEST_FONT_NAME,
			10f, null, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, this.columnGroup1, 1, null, Color.MAGENTA, TEST_FONT_NAME,
			10f, true, null);
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup2, this.columnGroup2, 1, null, Color.RED, TEST_FONT_NAME,
			10f, null, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		dataSource.add("a", "a", "a", "a", 1);
		dataSource.add("a", "b", "a", "b", 1);
		dataSource.add("b", "a", "a", "a", 1);
		dataSource.add("b", "b", "a", "b", 1);
		return dataSource;
	}
}
