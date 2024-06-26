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
import software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder;
import software.xdev.dynamicreports.report.builder.component.VerticalListBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class List3Test extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final VerticalListBuilder list1 = cmp.verticalList();
		list1.add(cmp.text(""));
		list1.add(cmp.text(""));
		list1.setFixedWidth(50);
		
		final HorizontalListBuilder list2 = cmp.horizontalList();
		list2.add(cmp.text(""));
		list2.add(cmp.text(""));
		list2.setFixedWidth(150);
		
		final VerticalListBuilder list3 = cmp.verticalList();
		list3.add(cmp.text(""));
		list3.add(cmp.text(""));
		list3.setFixedWidth(200);
		
		final HorizontalListBuilder list4 = cmp.horizontalList();
		list4.add(cmp.text(""));
		list4.add(cmp.text(""));
		list4.setFixedWidth(500);
		
		final VerticalListBuilder list5 = cmp.verticalList();
		list5.add(cmp.text(""));
		list5.add(cmp.text(""));
		list5.setFixedHeight(50);
		
		final HorizontalListBuilder list6 = cmp.horizontalList();
		list6.add(cmp.text(""));
		list6.add(cmp.text(""));
		list6.setFixedHeight(40);
		
		final HorizontalListBuilder list7 = cmp.horizontalList();
		list7.add(cmp.text("").setFixedWidth(30));
		list7.add(cmp.text(""));
		list7.setFixedWidth(150);
		
		final VerticalListBuilder list8 = cmp.verticalList();
		list8.add(cmp.text("").setFixedHeight(20));
		list8.add(cmp.text(""));
		list8.setFixedHeight(50);
		
		final VerticalListBuilder list9 = cmp.verticalList();
		list9.add(cmp.text(""));
		list9.add(cmp.text(""));
		list9.setFixedDimension(250, 40);
		
		final HorizontalListBuilder list10 = cmp.horizontalList();
		list10.add(cmp.text(""));
		list10.add(cmp.text(""));
		list10.setFixedDimension(250, 40);
		
		rb.title(list1, list2, list3, list4, list5, list6, list7, list8, list9, list10);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.textField1", 0, 10, 10, 50, 16);
		this.elementPositionTest("title.textField2", 0, 10, 26, 50, 16);
		
		this.elementPositionTest("title.list3", 0, 10, 42, 150, 16);
		this.elementPositionTest("title.textField3", 0, 0, 0, 75, 16);
		this.elementPositionTest("title.textField4", 0, 75, 0, 75, 16);
		
		this.elementPositionTest("title.textField5", 0, 10, 58, 200, 16);
		this.elementPositionTest("title.textField6", 0, 10, 74, 200, 16);
		
		this.elementPositionTest("title.list5", 0, 10, 90, 500, 16);
		this.elementPositionTest("title.textField7", 0, 0, 0, 250, 16);
		this.elementPositionTest("title.textField8", 0, 250, 0, 250, 16);
		
		this.elementPositionTest("title.textField9", 0, 10, 106, 575, 25);
		this.elementPositionTest("title.textField10", 0, 10, 131, 575, 25);
		
		this.elementPositionTest("title.list7", 0, 10, 156, 575, 40);
		this.elementPositionTest("title.textField11", 0, 0, 0, 287, 40);
		this.elementPositionTest("title.textField12", 0, 287, 0, 288, 40);
		
		this.elementPositionTest("title.list8", 0, 10, 196, 150, 16);
		this.elementPositionTest("title.textField13", 0, 0, 0, 30, 16);
		this.elementPositionTest("title.textField14", 0, 30, 0, 120, 16);
		
		this.elementPositionTest("title.textField15", 0, 10, 212, 575, 20);
		this.elementPositionTest("title.textField16", 0, 10, 232, 575, 30);
		
		this.elementPositionTest("title.textField17", 0, 10, 262, 250, 20);
		this.elementPositionTest("title.textField18", 0, 10, 282, 250, 20);
		
		this.elementPositionTest("title.list11", 0, 10, 302, 250, 40);
		this.elementPositionTest("title.textField19", 0, 0, 0, 125, 40);
		this.elementPositionTest("title.textField20", 0, 125, 0, 125, 40);
	}
}
