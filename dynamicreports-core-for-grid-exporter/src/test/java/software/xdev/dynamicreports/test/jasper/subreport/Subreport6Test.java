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
package software.xdev.dynamicreports.test.jasper.subreport;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.Components;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.io.Serializable;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

/**
 * @author Ricardo Mariaca
 */
public class Subreport6Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        SubreportBuilder subreport = Components.subreport(new Subreport1Expression());

        rb.detail(subreport);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 10);
        elementValueTest("title.textField1", "575", "565", "575", "555", "575", "545", "575", "535", "575", "525");
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(5);
    }

    private class Subreport1Expression extends AbstractSimpleExpression<JasperReportBuilder> {
        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            SubreportBuilder subreport = Components.subreport(new Subreport2Expression());

            JasperReportBuilder report = report();
            report.title(cmp.text(reportParameters.getSubreportWidth()), cmp.horizontalList(cmp.horizontalGap(10 * reportParameters.getReportRowNumber()), subreport));

            return report;
        }
    }

    private class Subreport2Expression extends AbstractSimpleExpression<JasperReportBuilder> {
        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            JasperReportBuilder report = report();
            report.title(cmp.text(reportParameters.getSubreportWidth()));

            return report;
        }
    }
}
