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
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;

import java.io.Serializable;
import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class ExpressionTest extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH).title(cmp.text(new Expression1()), cmp.text(new Expression2<String>("text2")), cmp.text(new Expression4()).setDataType(type.integerType()));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementValueTest("title.textField1", "text1");
        elementValueTest("title.textField2", "text2");
        elementValueTest("title.textField3", "1,000");
    }

    @SuppressWarnings("rawtypes")
    private class Expression1 extends AbstractSimpleExpression {
        private static final long serialVersionUID = 1L;

        @Override
        public Object evaluate(ReportParameters reportParameters) {
            return "text1";
        }
    }

    private class Expression2<T> extends AbstractSimpleExpression<T> {
        private static final long serialVersionUID = 1L;

        private T value;

        private Expression2(T value) {
            this.value = value;
        }

        @Override
        public T evaluate(ReportParameters reportParameters) {
            return value;
        }
    }

    private class Expression3 extends AbstractSimpleExpression<Integer> {
        private static final long serialVersionUID = 1L;

        @Override
        public Integer evaluate(ReportParameters reportParameters) {
            return 1000;
        }
    }

    private class Expression4 extends Expression3 {
        private static final long serialVersionUID = 1L;
    }
}
