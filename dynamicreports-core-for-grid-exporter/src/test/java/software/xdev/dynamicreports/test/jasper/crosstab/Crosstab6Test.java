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
package software.xdev.dynamicreports.test.jasper.crosstab;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.constant.JasperProperty;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

/**
 * @author Ricardo Mariaca
 */
public class Crosstab6Test extends AbstractJasperCrosstabValueTest {
    private CrosstabRowGroupBuilder<String> rowGroup;
    private CrosstabColumnGroupBuilder<String> columnGroup;
    private CrosstabMeasureBuilder<Integer> measure1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
        measure1.addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true");
        measure1.setStretchWithOverflow(false);

        CrosstabBuilder crosstab = ctab.crosstab()
                                       .setCellWidth(18)
                                       .rowGroups(rowGroup = ctab.rowGroup("field1", String.class)
                                                                 .addHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
                                                                 .addTotalHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
                                                                 .setHeaderStretchWithOverflow(false)
                                                                 .setTotalHeaderStretchWithOverflow(false)
                                                                 .setHeaderWidth(18))
                                       .columnGroups(columnGroup = ctab.columnGroup("field2", String.class)
                                                                       .addHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
                                                                       .addTotalHeaderProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
                                                                       .setHeaderStretchWithOverflow(false)
                                                                       .setTotalHeaderStretchWithOverflow(false))
                                       .measures(measure1);

        rb.summary(crosstab);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("summary");

        // column group
        crosstabGroupHeaderCountTest(columnGroup, 2);
        crosstabGroupHeaderValueTest(columnGroup, "c ", "d ");
        crosstabGroupHeaderFullValueTest(columnGroup, "c test test", "d test test");
        crosstabGroupTotalHeaderCountTest(columnGroup, 1);
        crosstabGroupTotalHeaderValueTest(columnGroup, "To");
        crosstabGroupTotalHeaderFullValueTest(columnGroup, "Total");

        // row group
        crosstabGroupHeaderCountTest(rowGroup, 2);
        crosstabGroupHeaderValueTest(rowGroup, "a ", "b ");
        crosstabGroupHeaderFullValueTest(rowGroup, "a test test", "b test test");
        crosstabGroupTotalHeaderCountTest(rowGroup, 1);
        crosstabGroupTotalHeaderValueTest(rowGroup, "To");
        crosstabGroupTotalHeaderFullValueTest(rowGroup, "Total");

        // measure1
        crosstabCellCountTest(measure1, null, null, 4);
        crosstabCellValueTest(measure1, null, null, "30", "70", "11", "15");
        crosstabCellFullValueTest(measure1, null, null, "30", "70", "110", "150");
        crosstabCellCountTest(measure1, null, columnGroup, 2);
        crosstabCellValueTest(measure1, null, columnGroup, "10", "26");
        crosstabCellFullValueTest(measure1, null, columnGroup, "100", "260");
        crosstabCellCountTest(measure1, rowGroup, null, 2);
        crosstabCellValueTest(measure1, rowGroup, null, "14", "22");
        crosstabCellFullValueTest(measure1, rowGroup, null, "140", "220");
        crosstabCellCountTest(measure1, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure1, rowGroup, columnGroup, "36");
        crosstabCellFullValueTest(measure1, rowGroup, columnGroup, "360");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("a test test", "c test test", 10);
        dataSource.add("a test test", "c test test", 20);
        dataSource.add("a test test", "d test test", 30);
        dataSource.add("a test test", "d test test", 40);
        dataSource.add("b test test", "c test test", 50);
        dataSource.add("b test test", "c test test", 60);
        dataSource.add("b test test", "d test test", 70);
        dataSource.add("b test test", "d test test", 80);
        return dataSource;
    }
}
