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
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabStyleTest;


public class CrosstabStyle1Test extends AbstractJasperCrosstabStyleTest
{
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final FieldBuilder<String> field1 = field("field1", String.class);
		final FieldBuilder<String> field2 = field("field2", String.class);
		
		final StyleBuilder titleStyle1 = stl.style().setFontSize(12).bold();
		final StyleBuilder titleStyle2 = stl.style(titleStyle1).setBackgroundColor(Color.LIGHT_GRAY);
		final StyleBuilder titleStyle3 = stl.style(titleStyle2).setBorder(stl.pen1Point())
			.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		final StyleBuilder cellStyle = stl.style().bold();
		final StyleBuilder rowGroupCellStyle = stl.style(cellStyle).setBackgroundColor(Color.BLUE);
		final StyleBuilder columnGroupCellStyle = stl.style(cellStyle).setBackgroundColor(Color.ORANGE);
		final StyleBuilder rowColumnGroupCellStyle = stl.style(cellStyle).setBackgroundColor(Color.RED);
		
		this.rowGroup = ctab.rowGroup(field1).setHeaderStyle(titleStyle2).setTotalHeaderStyle(titleStyle3);
		this.columnGroup =
			ctab.columnGroup(field2).setHeaderStyle(titleStyle2).setTotalHeaderStyle(titleStyle3);
		
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		this.measure1.setStyle(cellStyle);
		this.measure1.setStyle(rowGroupCellStyle, this.rowGroup);
		this.measure1.setStyle(columnGroupCellStyle, this.columnGroup);
		this.measure1.setStyle(rowColumnGroupCellStyle, this.rowGroup, this.columnGroup);
		
		final CrosstabBuilder crosstab = ctab.crosstab().headerCell(cmp.text("Header").setStyle(titleStyle1))
			.rowGroups(this.rowGroup).columnGroups(this.columnGroup).measures(this.measure1);
		
		rb.summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		this.crosstabHeaderElementStyleTest("textField1", 0, null, null, TEST_FONT_NAME, 12f, true, null);
		
		this.crosstabGroupHeaderStyleTest(
			this.rowGroup, 0, null, Color.LIGHT_GRAY, TEST_FONT_NAME, 12f, true,
			null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.rowGroup, 0, null, Color.LIGHT_GRAY, TEST_FONT_NAME, 12f,
			true, null);
		this.crosstabGroupTotalHeaderHorizontalAlignmentTest(this.rowGroup, 0, HorizontalTextAlignEnum.CENTER);
		
		this.crosstabGroupHeaderStyleTest(
			this.columnGroup, 0, null, Color.LIGHT_GRAY, TEST_FONT_NAME, 12f, true,
			null);
		this.crosstabGroupTotalHeaderStyleTest(
			this.columnGroup, 0, null, Color.LIGHT_GRAY, TEST_FONT_NAME, 12f,
			true, null);
		this.crosstabGroupTotalHeaderHorizontalAlignmentTest(this.columnGroup, 0, HorizontalTextAlignEnum.CENTER);
		
		for(int i = 0; i < 4; i++)
		{
			this.crosstabCellStyleTest(this.measure1, null, null, i, null, null, TEST_FONT_NAME, 10f, true, null);
		}
		for(int i = 0; i < 2; i++)
		{
			this.crosstabCellStyleTest(
				this.measure1, this.rowGroup, null, i, null, Color.BLUE, TEST_FONT_NAME, 10f,
				true, null);
			this.crosstabCellStyleTest(
				this.measure1, null, this.columnGroup, i, null, Color.ORANGE, TEST_FONT_NAME, 10f,
				true, null);
		}
		this.crosstabCellStyleTest(
			this.measure1, this.rowGroup, this.columnGroup, 0, null, Color.RED, TEST_FONT_NAME, 10f,
			true, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("a", "c", 2);
		dataSource.add("a", "d", 3);
		dataSource.add("a", "d", 4);
		dataSource.add("b", "c", 5);
		dataSource.add("b", "c", 6);
		dataSource.add("b", "d", 7);
		dataSource.add("b", "d", 8);
		return dataSource;
	}
}
