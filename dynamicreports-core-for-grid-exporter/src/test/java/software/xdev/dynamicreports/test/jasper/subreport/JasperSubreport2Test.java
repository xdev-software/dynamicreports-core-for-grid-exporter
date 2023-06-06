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

import org.junit.jupiter.api.Assertions;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;
import java.io.Serializable;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author Ricardo Mariaca
 */
public class JasperSubreport2Test extends AbstractJasperValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        SubreportBuilder detailSubreport = cmp.subreport(detailSubreport()).setDataSource(new SubreportDataSourceExpression());

        SubreportBuilder titleSubreport = cmp.subreport(titleSubreport()).setDataSource(titleSubreportDataSource());

        rb.title(titleSubreport).detail(detailSubreport);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // title subreport
        elementCountTest("detail.column_field11", 3);
        elementValueTest("detail.column_field11", "value1", "value2", "value3");

        // detail subreport
        elementCountTest("title.textField1", 3);
        elementValueTest("title.textField1", "Subreport1", "Subreport2", "Subreport3");

        elementCountTest("detail.column_simpleExpression_0_1", 6);
        elementValueTest("detail.column_simpleExpression_0_1", "1_1", "1_2", "2_1", "2_2", "3_1", "3_2");
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(3);
    }

    private JasperReport titleSubreport() {
        try {
            InputStream is = JasperSubreportTest.class.getResourceAsStream("titlesubreport.jrxml");
            return JasperCompileManager.compileReport(is);
        } catch (JRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    private JasperReport detailSubreport() {
        try {
            InputStream is = JasperSubreportTest.class.getResourceAsStream("detailsubreport.jrxml");
            return JasperCompileManager.compileReport(is);
        } catch (JRException e) {
            e.printStackTrace();
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    private JRDataSource titleSubreportDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("value1");
        dataSource.add("value2");
        dataSource.add("value3");
        return dataSource;
    }

    private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
        private static final long serialVersionUID = 1L;

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            return new JREmptyDataSource(2);
        }
    }
}
