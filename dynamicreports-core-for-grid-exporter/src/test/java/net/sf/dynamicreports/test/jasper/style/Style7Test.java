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

import java.io.Serializable;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.LineSpacing;
import net.sf.dynamicreports.report.constant.TabStopAlignment;
import net.sf.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;


/**
 * @author Ricardo Mariaca
 */
class Style7Test extends AbstractJasperStyleTest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder style = stl.style()
			.setLineSpacing(LineSpacing.DOUBLE)
			.setLineSpacingSize(2f)
			.setFirstLineIndent(10)
			.setLeftIndent(15)
			.setRightIndent(20)
			.setSpacingBefore(5)
			.setSpacingAfter(6)
			.setTabStopWidth(9)
			.addTabStop(12, TabStopAlignment.CENTER);
		
		rb.setTextStyle(style).title(cmp.text(""));
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(1);
		
		final JRStyle style = this.getElementAt("title.textField1", 0).getStyle();
		Assertions.assertEquals(LineSpacingEnum.DOUBLE, style.getParagraph().getLineSpacing());
		Assertions.assertEquals(Float.valueOf(2), style.getParagraph().getLineSpacingSize());
		Assertions.assertEquals(Integer.valueOf(10), style.getParagraph().getFirstLineIndent());
		Assertions.assertEquals(Integer.valueOf(15), style.getParagraph().getLeftIndent());
		Assertions.assertEquals(Integer.valueOf(20), style.getParagraph().getRightIndent());
		Assertions.assertEquals(Integer.valueOf(5), style.getParagraph().getSpacingBefore());
		Assertions.assertEquals(Integer.valueOf(6), style.getParagraph().getSpacingAfter());
		Assertions.assertEquals(Integer.valueOf(9), style.getParagraph().getTabStopWidth());
		final TabStop[] tabStops = style.getParagraph().getTabStops();
		Assertions.assertNotNull(tabStops);
		Assertions.assertEquals(12, tabStops[0].getPosition());
		Assertions.assertEquals(TabStopAlignEnum.CENTER, tabStops[0].getAlignment());
	}
}
