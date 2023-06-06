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

import org.junit.jupiter.api.Assertions;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.HashMap;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

/**
 * @author Ricardo Mariaca
 */
public class Report5Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        SubreportBuilder detailSubreport = cmp.subreport(detailSubreport()).setParameters(new SubreportParametersExpression());

        rb.title(cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class))).detail(detailSubreport).setLocale(Locale.ENGLISH).setResourceBundle(new ResourceBundle());
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("title.textField1", 3);
        elementValueTest("title.textField1", "bundleValue1", "bundleValue1", "bundleValue1");

        JasperPrint jasperPrint = getJasperPrint();
        Assertions.assertEquals(Locale.ENGLISH.getLanguage(), jasperPrint.getLocaleCode());
    }

    @Override
    protected boolean serializableTest() {
        return false;
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(2);
    }

    private JasperReportBuilder detailSubreport() {
        JasperReportBuilder report = report();
        report.title(cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class)));
        return report;
    }

    private class SubreportParametersExpression extends AbstractSimpleExpression<Map<String, Object>> {
        private static final long serialVersionUID = 1L;

        @Override
        public Map<String, Object> evaluate(ReportParameters reportParameters) {
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put(JRParameter.REPORT_LOCALE, reportParameters.getParameterValue(JRParameter.REPORT_LOCALE));
            parameters.put(JRParameter.REPORT_RESOURCE_BUNDLE, reportParameters.getParameterValue(JRParameter.REPORT_RESOURCE_BUNDLE));
            return parameters;
        }
    }

    private class ResourceBundle extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][] {{"bundleKey1", "bundleValue1"}};
        }
    }
}
