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
package net.sf.dynamicreports.test.jasper.style;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRStyle;


/**
 * Style tests.
 *
 * @author Ricardo Mariaca
 */
public class Style9Test extends AbstractJasperStyleTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder style = stl.style().bold().setForegroundColor(Color.BLUE);
		
		rb.title(cmp.rectangle().setStyle(style), cmp.text("text").setStyle(style));
	}
	
	@Override
	public void test()
	{
		super.test();
      
      this.numberOfPagesTest(1);
		
		final JRStyle style = this.getElementAt("title.rectangle1", 0).getStyle();
		Assertions.assertEquals(Color.BLUE, style.getForecolor());
      this.styleTest("title.textField1", 0, Color.BLUE, null, TEST_FONT_NAME, 10f, true, null);
	}
}
