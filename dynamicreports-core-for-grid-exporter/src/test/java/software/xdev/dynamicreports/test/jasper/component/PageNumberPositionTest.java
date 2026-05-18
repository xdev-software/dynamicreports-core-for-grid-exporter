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
import software.xdev.dynamicreports.report.builder.component.PageXofYBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class PageNumberPositionTest extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder style = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		final PageXofYBuilder pageXofYLeft = cmp.pageXofY().setStyle(stl.style(style));
		final PageXofYBuilder pageXofYRight =
			cmp.pageXofY().setStyle(stl.style(style).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));
		
		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.pageFooter(
				cmp.pageXofY(),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED),
				cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
				cmp.horizontalList(pageXofYLeft, pageXofYRight, pageXofYLeft, pageXofYRight),
				cmp.pageXofY().setPageXFixedWidth(20),
				cmp.pageXofY().setPageYFixedWidth(25),
				cmp.pageXslashY().setPageXFixedWidth(30),
				cmp.pageXslashY().setPageYFixedWidth(35),
				cmp.pageXofY().setPageXFixedWidth(18).setPageYFixedWidth(22));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementPositionTest("pageFooter.textField1", 0, 0, 0, 287, 16);
		this.elementPositionTest("pageFooter.textField2", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("pageFooter.textField3", 0, 0, 0, 40, 16);
		this.elementPositionTest("pageFooter.textField4", 0, 40, 0, 535, 16);
		
		this.elementPositionTest("pageFooter.textField5", 0, 0, 0, 517, 16);
		this.elementPositionTest("pageFooter.textField6", 0, 517, 0, 58, 16);
		
		this.elementPositionTest("pageFooter.textField7", 0, 0, 0, 287, 16);
		this.elementPositionTest("pageFooter.textField8", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("pageFooter.textField9", 0, 0, 0, 287, 16);
		this.elementPositionTest("pageFooter.textField10", 0, 287, 0, 288, 16);
		
		this.elementPositionTest("pageFooter.textField11", 0, 0, 0, 36, 12);
		this.elementPositionTest("pageFooter.textField12", 0, 36, 0, 107, 12);
		
		this.elementPositionTest("pageFooter.textField13", 0, 0, 0, 90, 12);
		this.elementPositionTest("pageFooter.textField14", 0, 90, 0, 54, 12);
		
		this.elementPositionTest("pageFooter.textField15", 0, 0, 0, 36, 12);
		this.elementPositionTest("pageFooter.textField16", 0, 36, 0, 108, 12);
		
		this.elementPositionTest("pageFooter.textField17", 0, 0, 0, 90, 12);
		this.elementPositionTest("pageFooter.textField18", 0, 90, 0, 54, 12);
		
		this.elementPositionTest("pageFooter.textField19", 0, 0, 0, 20, 16);
		this.elementPositionTest("pageFooter.textField20", 0, 20, 0, 555, 16);
		
		this.elementPositionTest("pageFooter.textField21", 0, 0, 0, 550, 16);
		this.elementPositionTest("pageFooter.textField22", 0, 550, 0, 25, 16);
		
		this.elementPositionTest("pageFooter.textField23", 0, 0, 0, 30, 16);
		this.elementPositionTest("pageFooter.textField24", 0, 30, 0, 545, 16);
		
		this.elementPositionTest("pageFooter.textField25", 0, 0, 0, 540, 16);
		this.elementPositionTest("pageFooter.textField26", 0, 540, 0, 35, 16);
		
		this.elementPositionTest("pageFooter.textField27", 0, 0, 0, 18, 16);
		this.elementPositionTest("pageFooter.textField28", 0, 18, 0, 22, 16);
	}
}
