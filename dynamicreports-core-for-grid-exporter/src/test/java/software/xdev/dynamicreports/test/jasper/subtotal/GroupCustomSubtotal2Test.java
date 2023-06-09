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
package software.xdev.dynamicreports.test.jasper.subtotal;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

/**
 * @author Ricardo Mariaca
 */
public class GroupCustomSubtotal2Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<Integer> column2;
    private CustomSubtotalBuilder<Integer> subtotal1;
    private CustomSubtotalBuilder<Integer> subtotal2;
    private CustomSubtotalBuilder<Integer> subtotal3;
    private CustomSubtotalBuilder<Integer> subtotal4;
    private CustomSubtotalBuilder<Integer> subtotal5;
    private CustomSubtotalBuilder<Integer> subtotal6;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
        ColumnGroupBuilder group = grp.group(column1);

        rb.setLocale(Locale.ENGLISH)
          .columns(column1, column2 = col.column("Column2", "field2", Integer.class))
          .groupBy(group)
          .subtotalsAtGroupHeader(group, subtotal1 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtGroupFooter(group, subtotal2 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtFirstGroupHeader(subtotal3 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtFirstGroupFooter(subtotal4 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtLastGroupHeader(subtotal5 = sbt.customValue(new ValueExpression(), column2))
          .subtotalsAtLastGroupFooter(subtotal6 = sbt.customValue(new ValueExpression(), column2));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);
        // groupHeader
        subtotalIndexCountTest(subtotal1, 1, 2);
        subtotalIndexValueTest(subtotal1, 1, "3", "6");
        // groupFooter
        subtotalIndexCountTest(subtotal2, 1, 2);
        subtotalIndexValueTest(subtotal2, 1, "3", "6");
        // first groupHeader
        subtotalIndexCountTest(subtotal3, 1, 2);
        subtotalIndexValueTest(subtotal3, 1, "3", "6");
        // first groupFooter
        subtotalIndexCountTest(subtotal4, 1, 2);
        subtotalIndexValueTest(subtotal4, 1, "3", "6");
        // last groupHeader
        subtotalIndexCountTest(subtotal5, 1, 2);
        subtotalIndexValueTest(subtotal5, 1, "3", "6");
        // last groupFooter
        subtotalIndexCountTest(subtotal6, 1, 2);
        subtotalIndexValueTest(subtotal6, 1, "3", "6");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 1; i <= 3; i++) {
            dataSource.add("group1", i);
        }
        for (int i = 4; i <= 6; i++) {
            dataSource.add("group2", i);
        }
        return dataSource;
    }

    private class ValueExpression extends AbstractComplexExpression<Integer> {
        private static final long serialVersionUID = 1L;

        private ValueExpression() {
            addExpression(column2);
        }

        @Override
        public Integer evaluate(List<?> values, ReportParameters reportParameters) {
            return (Integer) values.get(0);
        }
    }
}
