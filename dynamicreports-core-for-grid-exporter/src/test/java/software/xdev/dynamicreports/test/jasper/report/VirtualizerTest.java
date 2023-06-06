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
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author Ricardo Mariaca
 */
public class VirtualizerTest extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.title(cmp.text("title")).setVirtualizer(new JRFileVirtualizer(2));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);
        elementCountTest("title.textField1", 1);
        elementValueTest("title.textField1", "title");
    }

    @Override
    protected boolean serializableTest() {
        return false;
    }

    @Override
    protected JRDataSource createDataSource() {
        return new JREmptyDataSource(10);
    }
}
