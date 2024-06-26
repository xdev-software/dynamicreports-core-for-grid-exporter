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
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.io.Serializable;

import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class Style5Test extends AbstractJasperStyleTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.setTextStyle(stl.style(stl.pen1Point()).setPadding(2)).pageFooter(cmp.pageXofY());
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.styleTest("pageFooter.textField1", 0, null, null, TEST_FONT_NAME, 10f, null, null);
		this.horizontalAlignmentTest("pageFooter.textField1", 0, HorizontalTextAlignEnum.RIGHT);
		this.paddingTest("pageFooter.textField1", 0, 2, 2, 2, 0);
		this.borderTest("pageFooter.textField1", 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID,
			1, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 0);
		
		this.styleTest("pageFooter.textField2", 0, null, null, TEST_FONT_NAME, 10f, null, null);
		this.horizontalAlignmentTest("pageFooter.textField2", 0, HorizontalTextAlignEnum.LEFT);
		this.paddingTest("pageFooter.textField2", 0, 2, 2, 0, 2);
		this.borderTest("pageFooter.textField2", 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID,
			1, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID, 1);
	}
}
