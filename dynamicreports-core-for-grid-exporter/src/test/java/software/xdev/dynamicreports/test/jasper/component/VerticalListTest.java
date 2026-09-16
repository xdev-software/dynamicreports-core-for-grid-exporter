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

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class VerticalListTest extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setPageFormat(PageType.A8)
			.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.title(cmp.text("text"), cmp.text("text text text text text text text"),
				cmp.horizontalList(cmp.verticalList(
					cmp.text("text"),
					cmp.text("text text text text text text"),
					cmp.text("text")), cmp.text("text")), cmp.text("text"));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// elementPositionTest("title.list1", 0, 10, 10, 128, 116);
		this.elementPositionTest("title.list2", 0, 10, 52, 128, 58);
		// elementPositionTest("title.list3", 0, 0, 0, 64, 58);
		
		this.elementPositionTest("title.textField1", 0, 10, 10, 128, 16);
		this.elementPositionTest("title.textField2", 0, 10, 26, 128, 26);
		this.elementPositionTest("title.textField3", 0, 0, 0, 64, 16);
		this.elementPositionTest("title.textField4", 0, 0, 16, 64, 26);
		this.elementPositionTest("title.textField5", 0, 0, 42, 64, 16);
		this.elementPositionTest("title.textField6", 0, 64, 0, 64, 58);
		this.elementPositionTest("title.textField7", 0, 10, 110, 128, 16);
	}
}
