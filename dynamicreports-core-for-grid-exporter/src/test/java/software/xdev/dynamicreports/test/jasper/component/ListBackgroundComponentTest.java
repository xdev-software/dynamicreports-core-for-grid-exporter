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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;

import java.util.List;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.ReportTemplateBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.RectangleBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


class ListBackgroundComponentTest extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final RectangleBuilder background = cmp.rectangle();
		final ReportTemplateBuilder template = template().setDetailBackgroundComponent(background);
		
		rb.setTemplate(template)
			.setTitleBackgroundComponent(background)
			.setSummaryBackgroundComponent(background)
			.title(cmp.text("title").setFixedHeight(300))
			.columns(this.column = col.column("field1", String.class))
			.summary(
				cmp.text("text\ntext"),
				cmp.horizontalList(
					cmp.text("text"),
					cmp.horizontalList(cmp.text("text"), cmp.text("text")).setBackgroundComponent(background),
					cmp.text("text")),
				cmp.verticalList(cmp.text("text"), cmp.text("text"))
					.setStyle(stl.style(5))
					.setBackgroundComponent(background));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.list1", 0, 10, 10, 575, 300);
		this.elementPositionTest("title.list1.background", 0, 0, 0, 575, 300);
		
		this.columnDetailPositionTest(this.column, 0, 0, 0, 575, 16);
		this.elementPositionTest("detail.list1", 0, 10, 310, 575, 16);
		this.elementPositionTest("detail.list1.background", 0, 0, 0, 575, 16);
		this.columnDetailPositionTest(this.column, 1, 0, 0, 575, 16);
		this.elementPositionTest("detail.list1", 1, 10, 326, 575, 16);
		this.elementPositionTest("detail.list1.background", 1, 0, 0, 575, 16);
		
		this.elementPositionTest("summary.list1", 0, 10, 342, 575, 84);
		this.elementPositionTest("summary.list1.background", 0, 0, 0, 575, 84);
		this.elementPositionTest("summary.list2", 0, 0, 26, 575, 16);
		final List<JRPrintElement> elements = this.findElement("summary.list2.background");
		Assertions.assertTrue(elements.isEmpty());
		this.elementPositionTest("summary.list3", 0, 143, 0, 288, 16);
		this.elementPositionTest("summary.list3.background", 0, 0, 0, 288, 16);
		this.elementPositionTest("summary.list4", 0, 0, 42, 575, 42);
		this.elementPositionTest("summary.list4.background", 0, 0, 0, 565, 32);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("value1");
		dataSource.add("value2");
		return dataSource;
	}
}
