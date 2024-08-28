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
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.report.base.AbstractTemplateDesign;
import software.xdev.dynamicreports.report.base.DRMargin;
import software.xdev.dynamicreports.report.builder.MarginBuilder;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.definition.DRIMargin;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.design.DesignReportBuilder;


class TemplateDesignTest
{
	@SuppressWarnings("checkstyle:MethodLength")
	@Test
	void testComponentFixedWidth()
	{
		ReportBuilder<?> rb1 = new DesignReportBuilder(new TestTemplateDesign());
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.setPageFormat(100, 100, PageOrientation.LANDSCAPE);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page width");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.setPageFormat(50, 200, PageOrientation.LANDSCAPE);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page height");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.setPageFormat(200, 100, PageOrientation.PORTRAIT);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page orientation");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		MarginBuilder margin = margin();
		margin.setLeft(10).setRight(6).setTop(7).setBottom(8);
		rb1.setPageMargin(margin);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page left margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		margin = margin();
		margin.setLeft(5).setRight(10).setTop(7).setBottom(8);
		rb1.setPageMargin(margin);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page right margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		margin = margin();
		margin.setLeft(5).setRight(6).setTop(10).setBottom(8);
		rb1.setPageMargin(margin);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page top margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		margin = margin();
		margin.setLeft(5).setRight(6).setTop(7).setBottom(10);
		rb1.setPageMargin(margin);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page bottom margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.setPageColumnsPerPage(5);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page columnsPerPage margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.setPageColumnSpace(5);
		try
		{
			new DRDesignReport(rb1.getReport());
		}
		catch(final DRException e)
		{
			Assertions.fail("template design failed - page columnSpace margin");
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.title(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - title band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.pageHeader(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - page header band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.pageFooter(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - page footer band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.columnHeader(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - column header band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.columnFooter(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - column footer band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.pageFooter(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - page footer band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.summary(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - summary band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.noData(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - no data band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		
		rb1 = new DesignReportBuilder(new TestTemplateDesign());
		rb1.background(cmp.text(""));
		try
		{
			new DRDesignReport(rb1.getReport());
			Assertions.fail("template design failed - background band");
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
	}
	
	public static class TestTemplateDesign extends AbstractTemplateDesign<Object>
	{

		@Override
		public Integer getPageWidth()
		{
			return 200;
		}
		
		@Override
		public Integer getPageHeight()
		{
			return 100;
		}
		
		@Override
		public PageOrientation getPageOrientation()
		{
			return PageOrientation.LANDSCAPE;
		}
		
		@Override
		public DRIMargin getPageMargin()
		{
			final DRMargin margin = new DRMargin();
			margin.setLeft(5);
			margin.setRight(6);
			margin.setTop(7);
			margin.setBottom(8);
			return margin;
		}
		
		@Override
		public Integer getPageColumnsPerPage()
		{
			return 2;
		}
		
		@Override
		public Integer getPageColumnSpace()
		{
			return 20;
		}
		
		@Override
		public Integer getPageColumnWidth()
		{
			return 100;
		}
		
		@Override
		public int getTitleComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getPageHeaderComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getPageFooterComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getColumnHeaderComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getColumnFooterComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getLastPageFooterComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getSummaryComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getNoDataComponentsCount()
		{
			return 1;
		}
		
		@Override
		public int getBackgroundComponentsCount()
		{
			return 1;
		}
	}
}
