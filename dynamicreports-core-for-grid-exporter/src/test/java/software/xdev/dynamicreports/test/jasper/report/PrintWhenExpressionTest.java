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
package software.xdev.dynamicreports.test.jasper.report;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;

/**
 * @author Ricardo Mariaca
 */
public class PrintWhenExpressionTest extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.title(cmp.text("title1").setPrintWhenExpression(exp.value(true)), cmp.text("title2").setPrintWhenExpression(exp.value(false)))
          .summary(cmp.text("summary1"))
          .setSummaryPrintWhenExpression(exp.value(true))
          .pageFooter(cmp.text("pageFooter1"))
          .setPageFooterPrintWhenExpression(exp.value(false));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 1);
        elementValueTest("title.textField1", "title1");
        elementCountTest("title.textField2", 0);

        elementCountTest("summary.textField1", 1);
        elementValueTest("summary.textField1", "summary1");

        elementCountTest("pageFooter.textField1", 0);
    }
}
