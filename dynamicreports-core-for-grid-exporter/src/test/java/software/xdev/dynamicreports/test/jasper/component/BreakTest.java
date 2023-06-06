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
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.io.Serializable;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
public class BreakTest extends AbstractJasperPositionTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setPageColumnsPerPage(2)
          .columns(col.componentColumn("Column1",
                                       cmp.verticalList(cmp.text("value"), cmp.pageBreak().setPrintWhenExpression(new Expression1()), cmp.columnBreak().setPrintWhenExpression(new Expression2()))))
          .title(cmp.text("text1"), cmp.pageBreak(), cmp.text("text1"));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(3);

        elementPositionTest("title.textField1", 0, 10, 10, 575, 16);
        elementPositionTest("title.textField2", 0, 10, 10, 575, 16);
        elementPositionTest("detail.textField1", 0, 10, 42, 287, 16);
        elementPositionTest("detail.textField1", 1, 10, 26, 287, 16);
        elementPositionTest("detail.textField1", 2, 297, 26, 287, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(3);
    }

    public class Expression1 extends AbstractSimpleExpression<Boolean> {
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getReportRowNumber() == 1;
        }
    }

    public class Expression2 extends AbstractSimpleExpression<Boolean> {
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getReportRowNumber() == 2;
        }
    }
}
