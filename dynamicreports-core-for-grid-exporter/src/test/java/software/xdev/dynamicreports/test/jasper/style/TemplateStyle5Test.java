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

import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.InputStream;
import java.io.Serializable;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import software.xdev.dynamicreports.test.jasper.templatedesign.TemplateDesign1Test;


public class TemplateStyle5Test extends AbstractJasperStyleTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final TemplateStylesBuilder templateStyles =
			stl.templateStyles()
				.loadStyles(TemplateStyle4Test.class.getResource("StyleTemplate1.jrtx"))
				.styles(stl.style().setName("style1").setBackgroundColor(Color.RED));
		templateStyles.getStyle("columnTitleStyle3").setBackgroundColor(Color.LIGHT_GRAY);
		final InputStream is = TemplateDesign1Test.class.getResourceAsStream("templatedesign6.jrxml");
		rb.setTemplateDesign(is)
			.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
			.addTemplateStyle(templateStyles);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		final JasperPrint jasperPrint = this.getJasperPrint();
		Assertions.assertEquals("templatedesign6", jasperPrint.getName());
		
		this.styleTest("templateDesign.title1", 0, Color.BLACK, Color.LIGHT_GRAY, "SansSerif", null, true, null);
		this.borderTest(
			"templateDesign.title1",
			0,
			Color.BLACK,
			LineStyleEnum.SOLID,
			2,
			Color.BLACK,
			LineStyleEnum.SOLID,
			2,
			Color.BLACK,
			LineStyleEnum.SOLID,
			2,
			Color.BLACK,
			LineStyleEnum.SOLID,
			2);
		this.styleTest("templateDesign.title2", 0, null, Color.RED, "SansSerif", null, null, null);
	}
}
