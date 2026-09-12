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

import java.awt.Color;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class ComponentStyleTest extends AbstractJasperStyleTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder style = stl.style().setLinePen(stl.penDotted());
		rb.title(
			cmp.line().setStyle(style),
			cmp.filler().setFixedHeight(10),
			cmp.line().setPen(stl.pen2Point()),
			cmp.ellipse().setStyle(style),
			cmp.ellipse().setPen(stl.pen2Point()),
			cmp.rectangle().setStyle(style),
			cmp.rectangle().setPen(stl.pen2Point()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		JRPrintLine line = (JRPrintLine)this.getElementAt("title.line1", 0);
		this.penTest(line.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);
		
		line = (JRPrintLine)this.getElementAt("title.line2", 0);
		this.penTest(line.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);
		
		JRPrintEllipse ellipse = (JRPrintEllipse)this.getElementAt("title.ellipse1", 0);
		this.penTest(ellipse.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);
		ellipse = (JRPrintEllipse)this.getElementAt("title.ellipse2", 0);
		this.penTest(ellipse.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);
		
		JRPrintRectangle rectangle = (JRPrintRectangle)this.getElementAt("title.rectangle1", 0);
		this.penTest(rectangle.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);
		rectangle = (JRPrintRectangle)this.getElementAt("title.rectangle2", 0);
		this.penTest(rectangle.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);
	}
	
	private void penTest(final JRPen pen, final Float width, final Color color, final LineStyleEnum style)
	{
		Assertions.assertEquals(width, pen.getLineWidth());
		Assertions.assertEquals(color, pen.getLineColor());
		Assertions.assertEquals(style, pen.getLineStyle());
	}
}
