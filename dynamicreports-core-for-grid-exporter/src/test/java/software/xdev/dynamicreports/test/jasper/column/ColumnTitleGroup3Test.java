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
package software.xdev.dynamicreports.test.jasper.column;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grid;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.constant.JasperProperty;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * @author Ricardo Mariaca, Jan Moxter
 */
public class ColumnTitleGroup3Test extends AbstractJasperValueTest {
    private TextColumnBuilder<String> column1;
    private TextColumnBuilder<String> column2;
    private TextColumnBuilder<String> column3;

    @Override
    protected void configureReport(final JasperReportBuilder rb) {
        column1 = col.column("Column1", "field1", String.class);
        column2 = col.column("Column2", "field2", String.class).setFixedWidth(25);
        column3 = col.column("Column3", "field3", String.class).setFixedWidth(25);

        final ColumnTitleGroupBuilder titleGroup = grid.titleGroup("test test test", column2, column3)
                        .setTitleTextAdjust(TextAdjust.CUT_TEXT)
                        .addTitleProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true");
        rb.columnGrid(column1, titleGroup).columns(column1, column2, column3);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementValueTest("columnHeader.columngroup.title1", 0, "test test ");
        elementFullValueTest("columnHeader.columngroup.title1", 0, "test test test");
        // column1
        columnTitleValueTest(column1, 0, "Column1");
        columnDetailValueTest(column1, 0, "text");
        // column2
        columnTitleValueTest(column2, 0, "Column2");
        columnDetailValueTest(column2, 0, "text");
        // column3
        columnTitleValueTest(column3, 0, "Column3");
        columnDetailValueTest(column3, 0, "text");
    }

    @Override
    protected JRDataSource createDataSource() {
        final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("text", "text", "text");
        return dataSource;
    }
}
