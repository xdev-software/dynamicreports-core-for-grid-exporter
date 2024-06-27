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
import software.xdev.dynamicreports.report.builder.component.VerticalListBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class List2Test extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final VerticalListBuilder list1 = cmp.verticalList();
		list1.add(cmp.text(""));
		
		final VerticalListBuilder list2 = cmp.verticalList();
		list2.add(cmp.text(""));
		list2.add(cmp.filler().setFixedWidth(500));
		
		final VerticalListBuilder list3 = cmp.verticalList();
		list3.add(cmp.text(""));
		
		final VerticalListBuilder list4 = cmp.verticalList();
		list4.add(cmp.text(""));
		list4.add(cmp.filler().setFixedWidth(50));
		
		final VerticalListBuilder list5 = cmp.verticalList();
		list5.add(cmp.text(""));
		
		final VerticalListBuilder list6 = cmp.verticalList();
		list6.add(cmp.text(""));
		list6.add(cmp.filler().setFixedWidth(200));
		list6.add(cmp.text(""));
		list6.add(cmp.filler().setFixedWidth(500));
		
		rb.title(cmp.horizontalList(list1, list2), cmp.horizontalList(list3, list4), cmp.horizontalList(list5, list6));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("title.textField1", 0, 0, 0, 75, 16);
		this.elementPositionTest("title.textField2", 0, 75, 0, 500, 16);
		
		this.elementPositionTest("title.textField3", 0, 0, 0, 287, 16);
		this.elementPositionTest("title.textField4", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("title.textField5", 0, 0, 0, 75, 32);
		this.elementPositionTest("title.textField6", 0, 75, 0, 500, 16);
		this.elementPositionTest("title.textField7", 0, 75, 16, 500, 16);
	}
}
