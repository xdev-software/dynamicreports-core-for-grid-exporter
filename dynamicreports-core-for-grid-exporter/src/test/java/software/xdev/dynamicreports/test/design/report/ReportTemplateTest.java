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
package software.xdev.dynamicreports.test.design.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignImage;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import software.xdev.dynamicreports.design.definition.component.DRIDesignList;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.constant.Language;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.design.DesignReportBuilder;


class ReportTemplateTest
{
	
	private void configureReport(final ReportBuilder<?> rb)
	{
		final TextColumnBuilder<Integer> column1;
		
		rb.columns(column1 = col.column("Column1", "field1", Integer.class), col.booleanColumn("Column2", "field2"))
			.groupBy(grp.group(column1).header(cmp.horizontalList(cmp.hListCell(cmp.text("")).widthFixed())))
			.title(cmp.horizontalList(
				cmp.hListCell(cmp.image("")).widthFixed().heightFixedOnTop(),
				cmp.hListCell(ctab.crosstab()
						.rowGroups(ctab.rowGroup("f1", String.class))
						.columnGroups(
							ctab.columnGroup("f2", String.class),
							ctab.columnGroup("f3", String.class))
						.measures(ctab.measure("f4", "f4", Integer.class, Calculation.SUM)))
					.widthFixed()
					.heightFixedOnTop()))
			.setTemplate(template().setLocale(Locale.ENGLISH)
				.setShowColumnTitle(false)
				.setIgnorePagination(true)
				.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
				.setWhenResourceMissingType(WhenResourceMissingType.KEY)
				.setTitleOnANewPage(true)
				.setSummaryOnANewPage(true)
				.setSummaryWithPageHeaderAndFooter(true)
				.setFloatColumnFooter(true)
				.setPrintOrder(Orientation.HORIZONTAL)
				.setColumnDirection(RunDirection.RIGHT_TO_LEFT)
				.setLanguage(Language.GROOVY)
				
				.setHighlightDetailOddRows(true)
				.setDetailOddRowStyle(stl.simpleStyle().setBackgroundColor(Color.BLUE))
				.setHighlightDetailEvenRows(true)
				.setDetailEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.CYAN))
				.setDefaultFont(stl.font().setFontSize(12))
				.setTextStyle(stl.style().bold())
				
				.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
				.setPageMargin(margin(3))
				.setPageColumnsPerPage(3)
				.setPageColumnSpace(20)
				
				.setColumnPrintRepeatedDetailValues(false)
				.setColumnWidth(250)
				
				.setGroupHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
				.setGroupHideColumn(false)
				.setGroupShowColumnHeaderAndFooter(true)
				.setGroupPadding(20)
				.setGroupStartInNewPage(true)
				.setGroupStartInNewColumn(true)
				.setGroupReprintHeaderOnEachPage(true)
				.setGroupHeaderWithSubtotal(true)
				
				.setTextFieldWidth(150)
				
				.setImageWidth(110)
				.setImageHeight(120)
				
				.setListgap(10)
				
				.setCrosstabWidth(90)
				.setCrosstabHeight(101)
				.setCrosstabHighlightOddRows(true)
				.setCrosstabOddRowStyle(stl.simpleStyle().setBackgroundColor(Color.ORANGE))
				.setCrosstabHighlightEvenRows(true)
				.setCrosstabEvenRowStyle(stl.simpleStyle().setBackgroundColor(Color.MAGENTA))
				.setCrosstabGroupStyle(stl.style().setBackgroundColor(Color.RED))
				.setCrosstabGroupTotalStyle(stl.style().setBackgroundColor(Color.ORANGE))
				.setCrosstabGrandTotalStyle(stl.style().setBackgroundColor(Color.BLUE))
				.setCrosstabCellStyle(stl.style().setBackgroundColor(Color.CYAN))
				.setCrosstabMeasureTitleStyle(stl.style().setBackgroundColor(Color.YELLOW))
				
				.setBooleanColumnStyle(stl.style(stl.pen1Point()))
				
				.setDetailSplitType(SplitType.IMMEDIATE));
	}
	
	@Test
	void test()
	{
		@SuppressWarnings("rawtypes")
		final ReportBuilder rb = new DesignReportBuilder();
		this.configureReport(rb);
		try
		{
			final DRDesignReport report = new DRDesignReport(rb.getReport());
			Assertions.assertEquals(Locale.ENGLISH, report.getLocale());
			Assertions.assertNull(report.getColumnHeaderBand());
			Assertions.assertTrue(report.isIgnorePagination());
			Assertions.assertEquals(
				WhenNoDataType.ALL_SECTIONS_NO_DETAIL,
				report.getWhenNoDataType());
			Assertions.assertEquals(
				WhenResourceMissingType.KEY,
				report.getWhenResourceMissingType());
			Assertions.assertTrue(report.isTitleOnANewPage());
			Assertions.assertTrue(report.isSummaryOnANewPage());
			Assertions.assertTrue(report.isSummaryWithPageHeaderAndFooter());
			Assertions.assertTrue(report.isFloatColumnFooter());
			Assertions.assertEquals(Orientation.HORIZONTAL, report.getPrintOrder());
			Assertions.assertEquals(RunDirection.RIGHT_TO_LEFT, report.getColumnDirection());
			Assertions.assertEquals(Language.GROOVY, report.getLanguage());
			
			final DRDesignTextField columnTextField1 =
				(DRDesignTextField)((DRDesignList)report.getDetailBands().get(0).getBandComponent()).getComponents()
					.get(0);
			DRIDesignStyle style = columnTextField1.getStyle();
			Assertions.assertEquals(
				Color.BLUE,
				style.getConditionalStyles().get(0).getBackgroundColor());
			Assertions.assertEquals(
				Color.CYAN,
				style.getConditionalStyles().get(1).getBackgroundColor());
			Assertions.assertEquals(
				12,
				style.getParentStyle().getFont().getFontSize());
			Assertions.assertTrue(style.getParentStyle().getFont().getBold());
			
			Assertions.assertEquals(1190, report.getPage().getWidth());
			Assertions.assertEquals(842, report.getPage().getHeight());
			Assertions.assertEquals(PageOrientation.LANDSCAPE, report.getPage().getOrientation());
			Assertions.assertEquals(3, report.getPage().getMargin().getLeft());
			Assertions.assertEquals(3, report.getPage().getColumnsPerPage());
			Assertions.assertEquals(20, report.getPage().getColumnSpace());
			
			Assertions.assertFalse(columnTextField1.isPrintRepeatedValues());
			Assertions.assertEquals(180, columnTextField1.getWidth());
			
			final DRDesignTextField columnTextField2 =
				(DRDesignTextField)((DRDesignList)report.getDetailBands().get(0).getBandComponent()).getComponents()
					.get(1);
			style = columnTextField2.getStyle();
			Assertions.assertEquals(
				Color.BLUE,
				style.getConditionalStyles().get(0).getBackgroundColor());
			Assertions.assertEquals(
				Color.CYAN,
				style.getConditionalStyles().get(1).getBackgroundColor());
			Assertions.assertEquals(
				Float.valueOf(1),
				style.getParentStyle().getBorder().getTopPen().getLineWidth());
			Assertions.assertEquals(181, columnTextField2.getWidth());
			
			final DRDesignGroup group = (DRDesignGroup)report.getGroups().toArray()[0];
			final DRDesignComponent textField = group.getHeaderBands().get(1).getBandComponent();
			Assertions.assertEquals(
				2,
				((DRDesignList)group.getHeaderBands().get(0).getBandComponent()).getComponents().size());
			Assertions.assertEquals("groupHeader.textField1", textField.getUniqueName());
			Assertions.assertEquals(20, columnTextField1.getX());
			Assertions.assertTrue(group.isStartInNewPage());
			Assertions.assertTrue(group.isStartInNewColumn());
			Assertions.assertTrue(group.isReprintHeaderOnEachPage());
			Assertions.assertTrue(group.isHeaderWithSubtotal());
			
			Assertions.assertEquals(150, textField.getWidth());
			
			final DRDesignList titleList = (DRDesignList)report.getTitleBand().getBandComponent();
			Assertions.assertEquals(10, titleList.getGap());
			
			final DRDesignComponent image = titleList.getComponents().get(0);
			Assertions.assertEquals(110, image.getWidth());
			Assertions.assertEquals(120, image.getHeight());
			
			final DRDesignCrosstab crosstab = (DRDesignCrosstab)titleList.getComponents().get(1);
			Assertions.assertEquals(Integer.valueOf(90), crosstab.getWidth());
			Assertions.assertEquals(Integer.valueOf(101), crosstab.getHeight());
			style = crosstab.getCells().get(0).getContent().getComponent().getStyle();
			Assertions.assertEquals(
				new Color(63, 241, 191),
				style.getConditionalStyles().get(0).getBackgroundColor());
			Assertions.assertEquals(
				new Color(63, 191, 255),
				style.getConditionalStyles().get(1).getBackgroundColor());
			Assertions.assertEquals(Color.CYAN, style.getParentStyle().getBackgroundColor());
			style = crosstab.getColumnGroups().get(0).getHeader().getComponent().getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getColumnGroups().get(0).getTotalHeader().getComponent()).getComponents()
				.get(0)
				.getStyle();
			Assertions.assertEquals(Color.BLUE, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getColumnGroups().get(1).getHeader().getComponent()).getComponents()
				.get(0)
				.getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getColumnGroups().get(1).getTotalHeader().getComponent()).getComponents()
				.get(0)
				.getStyle();
			Assertions.assertEquals(Color.ORANGE, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getRowGroups().get(0).getHeader().getComponent()).getComponents()
				.get(0)
				.getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = crosstab.getRowGroups().get(0).getTotalHeader().getComponent().getStyle();
			Assertions.assertEquals(Color.BLUE, style.getBackgroundColor());
			
			style = crosstab.getColumnGroups().get(0).getHeader().getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = crosstab.getColumnGroups().get(0).getTotalHeader().getStyle();
			Assertions.assertEquals(Color.BLUE, style.getBackgroundColor());
			style = crosstab.getColumnGroups().get(1).getHeader().getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = crosstab.getColumnGroups().get(1).getTotalHeader().getStyle();
			Assertions.assertEquals(Color.ORANGE, style.getBackgroundColor());
			style = crosstab.getRowGroups().get(0).getHeader().getStyle();
			Assertions.assertEquals(Color.RED, style.getBackgroundColor());
			style = crosstab.getRowGroups().get(0).getTotalHeader().getStyle();
			Assertions.assertEquals(Color.BLUE, style.getBackgroundColor());
			
			style = ((DRIDesignList)crosstab.getColumnGroups().get(0).getTotalHeader().getComponent()).getComponents()
				.get(1)
				.getStyle();
			Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getColumnGroups().get(1).getHeader().getComponent()).getComponents()
				.get(1)
				.getStyle();
			Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor());
			style = ((DRIDesignList)crosstab.getColumnGroups().get(1).getTotalHeader().getComponent()).getComponents()
				.get(1)
				.getStyle();
			Assertions.assertEquals(Color.YELLOW, style.getBackgroundColor());
			
			Assertions.assertEquals(
				SplitType.IMMEDIATE,
				report.getDetailBands().get(0).getSplitType());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	@Test
	void styleTest()
	{
		final ReportBuilder<?> rb = new DesignReportBuilder();
		final TextColumnBuilder<Integer> column1;
		
		rb.columns(column1 = col.column("Column1", "field1", Integer.class))
			.groupBy(grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE).setHideColumn(false))
			.subtotalsAtSummary(sbt.sum(column1))
			.title(cmp.image(""), cmp.image(""))
			.setTemplate(template().setColumnStyle(stl.style().setFontSize(1))
				.setColumnTitleStyle(stl.style().setFontSize(2))
				.setGroupStyle(stl.style().setFontSize(3))
				.setGroupTitleStyle(stl.style().setFontSize(4))
				.setSubtotalStyle(stl.style().setFontSize(5))
				.setImageStyle(stl.style().setBorder(stl.pen1Point())));
		try
		{
			final DRDesignReport report = new DRDesignReport(rb.getReport());
			
			DRDesignTextField textField =
				(DRDesignTextField)((DRDesignList)report.getDetailBands().get(0).getBandComponent()).getComponents()
					.get(0);
			Assertions.assertEquals(Integer.valueOf(1), textField.getStyle().getFont().getFontSize());
			
			textField =
				(DRDesignTextField)((DRDesignList)report.getColumnHeaderBand().getBandComponent()).getComponents()
					.get(1);
			Assertions.assertEquals(
				Integer.valueOf(2),
				textField.getStyle().getFont().getFontSize());
			
			final DRDesignList groupHeaderComponent =
				(DRDesignList)new ArrayList<>(report.getGroups()).get(0).getHeaderBands().get(0).getBandComponent();
			textField = (DRDesignTextField)groupHeaderComponent.getComponents().get(1);
			Assertions.assertEquals(Integer.valueOf(3), textField.getStyle().getFont().getFontSize());
			
			textField = (DRDesignTextField)groupHeaderComponent.getComponents().get(0);
			Assertions.assertEquals(
				4,
				textField.getStyle().getFont().getFontSize());
			
			textField =
				(DRDesignTextField)((DRDesignList)report.getSummaryBand().getBandComponent()).getComponents().get(0);
			Assertions.assertEquals(
				5,
				textField.getStyle().getFont().getFontSize());
			
			final DRDesignImage image =
				(DRDesignImage)((DRDesignList)report.getTitleBand().getBandComponent()).getComponents().get(0);
			Assertions.assertEquals(
				Float.valueOf(1),
				image.getStyle().getBorder().getTopPen().getLineWidth());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
}
