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
package software.xdev.dynamicreports.test.jasper.style;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class BandStyle3Test extends AbstractJasperStyleTest implements Serializable
{

	Color color1 = new Color(240, 240, 240);
	Color color2 = new Color(230, 230, 230);
	Color color3 = new Color(220, 220, 220);
	Color color4 = new Color(210, 210, 210);
	Color color5 = new Color(190, 190, 190);
	Color color6 = new Color(180, 180, 180);
	Color color7 = new Color(170, 170, 170);
	Color color8 = new Color(160, 160, 160);
	Color color9 = new Color(150, 150, 150);
	Color color10 = new Color(140, 140, 140);
	Color color11 = new Color(130, 130, 130);
	Color color12 = new Color(120, 120, 120);
	Color color13 = new Color(110, 110, 110);
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		final TextColumnBuilder<Integer> column2;
		final ColumnGroupBuilder group1;
		
		rb.setPageColumnsPerPage(2)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				column2 = col.column("Column2", "field2", Integer.class))
			.groupBy(group1 = grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE))
			.setTitleStyle(stl.style().setBackgroundColor(this.color1))
			.setPageHeaderStyle(stl.style().setBackgroundColor(this.color2))
			.setPageFooterStyle(stl.style().setBackgroundColor(this.color3))
			.setColumnHeaderStyle(stl.style().setBackgroundColor(this.color4))
			.setColumnFooterStyle(stl.style().setBackgroundColor(this.color5))
			.setGroupHeaderStyle(group1, stl.style().setBackgroundColor(this.color6))
			.setGroupFooterStyle(group1, stl.style().setBackgroundColor(this.color7))
			.setDetailHeaderStyle(stl.style().setBackgroundColor(this.color8))
			.setDetailStyle(stl.style().setBackgroundColor(this.color9))
			.setDetailFooterStyle(stl.style().setBackgroundColor(this.color10))
			.setLastPageFooterStyle(stl.style().setBackgroundColor(this.color11))
			.setSummaryStyle(stl.style().setBackgroundColor(this.color12))
			.setBackgroundStyle(stl.style().setBackgroundColor(this.color13))
			.subtotalsAtGroupHeader(group1, sbt.sum(column2))
			.subtotalsAtGroupFooter(group1, sbt.sum(column2))
			.title(cmp.verticalGap(10), cmp.verticalGap(10))
			.pageHeader(cmp.verticalGap(10))
			.pageFooter(cmp.verticalGap(10))
			.columnHeader(cmp.verticalGap(10))
			.columnFooter(cmp.verticalGap(10))
			.groupHeader(group1, cmp.verticalGap(10))
			.groupFooter(group1, cmp.verticalGap(10))
			.detailHeader(cmp.verticalGap(10))
			.detail(cmp.verticalGap(10))
			.detailFooter(cmp.verticalGap(10))
			.lastPageFooter(cmp.verticalGap(10))
			.summary(cmp.verticalGap(10))
			.background(cmp.verticalGap(10));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		this.styleTest("title.list1", 0, null, this.color1, "SansSerif", null, null, null);
		this.styleTest("pageHeader.list1", 0, null, this.color2, "SansSerif", null, null, null);
		this.styleTest("pageFooter.list1", 0, null, this.color3, "SansSerif", null, null, null);
		this.styleTest("columnHeader.list1", 0, null, this.color4, "SansSerif", null, null, null);
		this.styleTest("columnFooter.list1", 0, null, this.color5, "SansSerif", null, null, null);
		this.styleTest("groupHeaderTitleAndValue.list1", 0, null, this.color6, "SansSerif", null, null, null);
		this.styleTest("groupHeader.list1", 0, null, this.color6, "SansSerif", null, null, null);
		this.styleTest("subtotalGroupHeader.list1", 0, null, this.color6, "SansSerif", null, null, null);
		this.styleTest("groupFooter.list1", 0, null, this.color7, "SansSerif", null, null, null);
		this.styleTest("subtotalGroupFooter.list1", 0, null, this.color7, "SansSerif", null, null, null);
		this.styleTest("detailHeader.list1", 0, null, this.color8, "SansSerif", null, null, null);
		this.styleTest("detail.list1", 0, null, this.color9, "SansSerif", null, null, null);
		this.styleTest("detailFooter.list1", 0, null, this.color10, "SansSerif", null, null, null);
		this.styleTest("lastPageFooter.list1", 0, null, this.color11, "SansSerif", null, null, null);
		this.styleTest("summary.list1", 0, null, this.color12, "SansSerif", null, null, null);
		this.styleTest("background.list1", 0, null, this.color13, "SansSerif", null, null, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group1", i);
		}
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group2", i);
		}
		return dataSource;
	}
}
