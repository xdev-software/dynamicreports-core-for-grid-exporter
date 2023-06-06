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
package net.sf.dynamicreports.test.jasper.component;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.constant.JasperProperty;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.TextAdjust;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Jan Moxter
 */
public class TextField7Test extends AbstractJasperValueTest {
    private TextColumnBuilder<String> column1;

    @Override
    protected void configureReport(final JasperReportBuilder rb) {
        rb.columns(column1 = col.column("test test", "field1", String.class)
                                .setFixedWidth(25)
                                .setTextAdjust(TextAdjust.CUT_TEXT)
                                .addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
                                .setTitleTextAdjust(TextAdjust.CUT_TEXT)
                                .addTitleProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true"))
          .title(cmp.text("test test")
                          .setFixedWidth(25)
                          .setTextAdjust(TextAdjust.CUT_TEXT)
                          .addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true"), cmp.text("test test")
                          .setFixedWidth(25),
                 cmp.text("test test")
                         .setFixedWidth(25)
                         .setTextAdjust(TextAdjust.CUT_TEXT));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementValueTest("title.textField1", 0, "test ");
        elementFullValueTest("title.textField1", 0, "test test");
        elementValueTest("title.textField2", 0, "test test");
        elementValueTest("title.textField3", 0, "test ");
        elementFullValueTest("title.textField3", 0, "test ");

        columnTitleValueTest(column1, 0, "test ");
        columnTitleFullValueTest(column1, 0, "test test");

        columnDetailValueTest(column1, 0, "test ");
        columnDetailFullValueTest(column1, 0, "test test");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("test test");
        return dataSource;
    }
}
