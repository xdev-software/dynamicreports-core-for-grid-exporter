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
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;

/**
 * @author Ricardo Mariaca
 */
public class PageNumber5Test extends AbstractJasperValueTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH)
          .columns(col.column("Column1", "field1", Integer.class))
          .groupBy(grp.group("field2", String.class).setResetPageNumber(true))
          .pageFooter(cmp.pageXofY(), cmp.pageXslashY(), cmp.totalPages(), cmp.pageNumber());
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(3);
        elementCountTest("pageFooter.textField1", 3);
        elementValueTest("pageFooter.textField1", "1", "2", "1");

        elementCountTest("pageFooter.textField2", 3);
        elementValueTest("pageFooter.textField2", " of 2", " of 2", " of 1");

        elementCountTest("pageFooter.textField3", 3);
        elementValueTest("pageFooter.textField3", "1", "2", "1");

        elementCountTest("pageFooter.textField4", 3);
        elementValueTest("pageFooter.textField4", "/2", "/2", "/1");

        elementCountTest("pageFooter.textField5", 3);
        elementValueTest("pageFooter.textField5", "2", "2", "1");

        elementCountTest("pageFooter.textField6", 3);
        elementValueTest("pageFooter.textField6", "1", "2", "1");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 90; i++) {
            dataSource.add(i, "text" + (i < 50 ? "1" : "2"));
        }
        return dataSource;
    }
}
