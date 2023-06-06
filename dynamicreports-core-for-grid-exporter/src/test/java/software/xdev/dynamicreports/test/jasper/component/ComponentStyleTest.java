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

import org.junit.jupiter.api.Assertions;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintEllipse;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintRectangle;
import net.sf.jasperreports.engine.type.LineStyleEnum;

import java.awt.Color;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

/**
 * @author Ricardo Mariaca
 */
public class ComponentStyleTest extends AbstractJasperStyleTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        StyleBuilder style = stl.style().setLinePen(stl.penDotted());
        rb.title(cmp.line().setStyle(style), cmp.filler().setFixedHeight(10), cmp.line().setPen(stl.pen2Point()), cmp.ellipse().setStyle(style), cmp.ellipse().setPen(stl.pen2Point()),
                 cmp.rectangle().setStyle(style), cmp.rectangle().setPen(stl.pen2Point()));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        JRPrintLine line = (JRPrintLine) getElementAt("title.line1", 0);
        penTest(line.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);

        line = (JRPrintLine) getElementAt("title.line2", 0);
        penTest(line.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);

        JRPrintEllipse ellipse = (JRPrintEllipse) getElementAt("title.ellipse1", 0);
        penTest(ellipse.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);
        ellipse = (JRPrintEllipse) getElementAt("title.ellipse2", 0);
        penTest(ellipse.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);

        JRPrintRectangle rectangle = (JRPrintRectangle) getElementAt("title.rectangle1", 0);
        penTest(rectangle.getStyle().getLinePen(), 1f, null, LineStyleEnum.DOTTED);
        rectangle = (JRPrintRectangle) getElementAt("title.rectangle2", 0);
        penTest(rectangle.getLinePen(), 2f, Color.BLACK, LineStyleEnum.SOLID);

    }

    private void penTest(JRPen pen, Float width, Color color, LineStyleEnum style) {
        Assertions.assertEquals(width, pen.getLineWidth());
        Assertions.assertEquals(color, pen.getLineColor());
        Assertions.assertEquals(style, pen.getLineStyleValue());
    }
}
