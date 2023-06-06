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
package net.sf.dynamicreports.test.jasper.tableofcontents;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class TableOfContents8Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.tableOfContents()
          .columns(col.column("Column1", "field1", type.stringType()))
          .detail(cmp.text("detail").setTableOfContentsHeading("detailToc").removeLineWhenBlank().setPrintWhenExpression(new PrintWhenExpression()));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(2);

        elementCountTest("detail.textField1", 2);
        elementValueTest("detail.textField1", "detailToc", "detail");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 5; i++) {
            dataSource.add("text");
        }
        return dataSource;
    }

    private class PrintWhenExpression extends AbstractSimpleExpression<Boolean> {
        private static final long serialVersionUID = 1L;

        @Override
        public Boolean evaluate(ReportParameters reportParameters) {
            return reportParameters.getReportRowNumber() == 1;
        }
    }
}
