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
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.component.XyListBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class XyListTest extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder style = stl.style(stl.pen1Point());
		
		final XyListBuilder list1 = cmp.xyList();
		list1.add(10, 10, cmp.text(""));
		list1.add(50, 15, cmp.text(""));
		
		final XyListBuilder list2 = cmp.xyList().setStyle(style);
		list2.add(10, 10, cmp.text(""));
		list2.add(50, 15, cmp.text(""));
		list2.add(200, 5, cmp.horizontalList(cmp.text(""), cmp.text("")).setWidth(100));
		list2.add(350, 5, cmp.verticalList(cmp.text(""), cmp.text("")).setWidth(150));
		
		final XyListBuilder list3 = cmp.xyList();
		list3.add(10, 10, cmp.horizontalList(cmp.text("").setWidth(50), cmp.text("")).newRow().add(cmp.text("")));
		list3.add(200, 10, cmp.xyList().add(5, 5, cmp.horizontalList(cmp.text(""), cmp.text(""))));
		
		final XyListBuilder list4 = cmp.xyList().setStyle(style).setFixedWidth(250);
		list4.add(100, 10, cmp.xyList().setStyle(style).add(5, 5, cmp.verticalList(cmp.text(""), cmp.text(""))));
		
		rb.title(list1, list2, list3, list4);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.textField1", 0, 20, 20, 100, 16);
		this.elementPositionTest("title.textField2", 0, 60, 25, 100, 16);
		
		this.elementPositionTest("title.list3", 0, 10, 41, 575, 37);
		this.elementPositionTest("title.textField3", 0, 10, 10, 100, 16);
		this.elementPositionTest("title.textField4", 0, 50, 15, 100, 16);
		this.elementPositionTest("title.list4", 0, 200, 5, 100, 16);
		this.elementPositionTest("title.textField5", 0, 0, 0, 50, 16);
		this.elementPositionTest("title.textField6", 0, 50, 0, 50, 16);
		this.elementPositionTest("title.textField7", 0, 350, 5, 150, 16);
		this.elementPositionTest("title.textField8", 0, 350, 21, 150, 16);
		
		this.elementPositionTest("title.list8", 0, 20, 88, 150, 16);
		this.elementPositionTest("title.textField9", 0, 0, 0, 50, 16);
		this.elementPositionTest("title.textField10", 0, 50, 0, 100, 16);
		this.elementPositionTest("title.textField11", 0, 20, 104, 150, 16);
		this.elementPositionTest("title.list9", 0, 215, 93, 200, 16);
		this.elementPositionTest("title.textField12", 0, 0, 0, 100, 16);
		this.elementPositionTest("title.textField13", 0, 100, 0, 100, 16);
		
		this.elementPositionTest("title.list10", 0, 10, 120, 250, 47);
		this.elementPositionTest("title.list11", 0, 100, 10, 105, 37);
		this.elementPositionTest("title.textField14", 0, 5, 5, 100, 16);
		this.elementPositionTest("title.textField15", 0, 5, 21, 100, 16);
	}
}
