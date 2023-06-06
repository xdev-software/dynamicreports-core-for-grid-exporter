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
package software.xdev.dynamicreports.test.jasper.templatedesign;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.io.InputStream;

/**
 * @author Ricardo Mariaca
 */
public class TemplateDesign3Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) throws DRException {
        InputStream is = TemplateDesign3Test.class.getResourceAsStream("templatedesign4.jrxml");
        rb.setTemplateDesign(is);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementCountTest("templateDesign.detail.text1", 2);
        elementValueTest("templateDesign.detail.text1", "detail text 1");

        elementCountTest("templateDesign.detail.text2", 2);
        elementValueTest("templateDesign.detail.text2", "detail text 2");

        elementCountTest("title.textField1", 2);
        elementValueTest("title.textField1", "dynamic subreport", "dynamic subreport");

        elementCountTest("columnHeader.column_field1.title1", 2);
        elementValueTest("columnHeader.column_field1.title1", "Column1", "Column1");

        elementCountTest("detail.column_field11", 4);
        elementValueTest("detail.column_field11", "value1", "value2", "value1", "value2");

        elementCountTest("columnHeader.column_field2.title1", 2);
        elementValueTest("columnHeader.column_field2.title1", "Column2", "Column2");

        elementCountTest("detail.column_field21", 4);
        elementValueTest("detail.column_field21", "1", "5", "1", "5");
    }

    @Override
    public JRDataSource createDataSource() {
        return new JREmptyDataSource(2);
    }
}
