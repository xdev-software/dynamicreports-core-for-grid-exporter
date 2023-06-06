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

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.io.Serializable;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class PageNumber3Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        SubreportBuilder subreport = cmp.subreport(titleSubreport()).setDataSource(new SubreportDataSourceExpression());

        rb.detail(subreport)
          .pageFooter(cmp.pageNumber(), cmp.totalPages(), cmp.pageXslashY(), cmp.pageXofY(), cmp.pageXofY().setFormatExpression("Page {0} of {1}"),
                      cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT), cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT),
                      cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.JUSTIFIED), cmp.pageXofY().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(3);
        elementCountTest("pageFooter.textField1", 3);
        elementValueTest("pageFooter.textField1", "1", "2", "3");

        elementCountTest("pageFooter.textField2", 3);
        elementValueTest("pageFooter.textField2", "3", "3", "3");

        elementCountTest("pageFooter.textField3", 3);
        elementValueTest("pageFooter.textField3", "1", "2", "3");

        elementCountTest("pageFooter.textField4", 3);
        elementValueTest("pageFooter.textField4", "/3", "/3", "/3");

        elementCountTest("pageFooter.textField5", 3);
        elementValueTest("pageFooter.textField5", "1", "2", "3");

        elementCountTest("pageFooter.textField6", 3);
        elementValueTest("pageFooter.textField6", " of 3", " of 3", " of 3");

        elementCountTest("pageFooter.textField7", 3);
        elementValueTest("pageFooter.textField7", "Page 1", "Page 2", "Page 3");

        elementCountTest("pageFooter.textField8", 3);
        elementValueTest("pageFooter.textField8", " of 3", " of 3", " of 3");

        elementCountTest("pageFooter.textField9", 3);
        elementValueTest("pageFooter.textField9", "1", "2", "3");

        elementCountTest("pageFooter.textField10", 3);
        elementValueTest("pageFooter.textField10", " of 3", " of 3", " of 3");

        elementCountTest("pageFooter.textField11", 3);
        elementValueTest("pageFooter.textField11", "1", "2", "3");

        elementCountTest("pageFooter.textField12", 3);
        elementValueTest("pageFooter.textField12", " of 3", " of 3", " of 3");

        elementCountTest("pageFooter.textField13", 3);
        elementValueTest("pageFooter.textField13", "1", "2", "3");

        elementCountTest("pageFooter.textField14", 3);
        elementValueTest("pageFooter.textField14", " of 3", " of 3", " of 3");

        elementCountTest("pageFooter.textField15", 3);
        elementValueTest("pageFooter.textField15", "1", "2", "3");

        elementCountTest("pageFooter.textField16", 3);
        elementValueTest("pageFooter.textField16", " of 3", " of 3", " of 3");
    }

    private JasperReportBuilder titleSubreport() {
        JasperReportBuilder report = report();
        report.columns(col.column("Column1", "field1", type.integerType())).setDataSource(titleSubreportDataSource());
        return report;
    }

    private JRDataSource titleSubreportDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 50; i++) {
            dataSource.add(i);
        }
        return dataSource;
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(2);
    }

    private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
        private static final long serialVersionUID = 1L;

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            DRDataSource dataSource = new DRDataSource("field1");
            for (int i = 0; i < 50; i++) {
                dataSource.add(i);
            }
            return dataSource;
        }
    }
}
