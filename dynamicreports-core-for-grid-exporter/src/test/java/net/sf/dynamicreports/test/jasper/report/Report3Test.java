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
package net.sf.dynamicreports.test.jasper.report;

import org.junit.jupiter.api.Assertions;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.Serializable;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author Ricardo Mariaca
 */
public class Report3Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setReportName("report1")
          .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
          .setSummaryOnANewPage(true)
          .setSummaryWithPageHeaderAndFooter(true)
          .pageHeader(cmp.text("page header"))
          .pageFooter(cmp.text("page footer"))
          .summary(cmp.text("summary"));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(2);

        JasperPrint jasperPrint = getJasperPrint();
        Assertions.assertEquals("report1", jasperPrint.getName());

        // page header
        elementCountTest("pageHeader.textField1", 2);
        elementValueTest("pageHeader.textField1", "page header", "page header");

        // page footer
        elementCountTest("pageFooter.textField1", 2);
        elementValueTest("pageFooter.textField1", "page footer", "page footer");

        // summary
        elementCountTest("summary.textField1", 1);
        elementValueTest("summary.textField1", "summary");
    }
}
