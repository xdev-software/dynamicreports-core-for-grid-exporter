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
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

/**
 * @author Ricardo Mariaca
 */
public class CrosstabPosition7Test extends AbstractJasperCrosstabPositionTest {
    private CrosstabRowGroupBuilder<String> rowGroup1;
    private CrosstabRowGroupBuilder<String> rowGroup2;
    private CrosstabColumnGroupBuilder<String> columnGroup1;
    private CrosstabColumnGroupBuilder<String> columnGroup2;
    private CrosstabMeasureBuilder<Integer> measure;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1 = col.column("Column1", "field1", String.class);
        TextColumnBuilder<String> column2 = col.column("Column2", "field1", String.class);
        TextColumnBuilder<String> column3 = col.column("Column3", "field2", String.class);
        TextColumnBuilder<String> column4 = col.column("Column4", "field2", String.class);
        TextColumnBuilder<Integer> column5 = col.column("Column5", "field3", Integer.class);

        measure = ctab.measure("measure", column5, Calculation.SUM);

        CrosstabBuilder crosstab = ctab.crosstab()
                                       .rowGroups(rowGroup1 = ctab.rowGroup(column1), rowGroup2 = ctab.rowGroup(column2).setShowTotal(false))
                                       .columnGroups(columnGroup1 = ctab.columnGroup(column3), columnGroup2 = ctab.columnGroup(column4).setShowTotal(false))
                                       .measures(measure)
                                       .setCellWidth(50)
                                       .setCellHeight(20);

        rb.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE).summary(crosstab);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("summary");

        // column group 1
        crosstabGroupHeaderPositionTest(columnGroup1, 0, 0, 0, 50, 16);
        crosstabGroupTotalHeaderPositionTest(columnGroup1, 0, 0, 0, 50, 24);

        // column group 2
        crosstabGroupHeaderPositionTest(columnGroup2, 0, 0, 0, 50, 16);

        // row group 1
        crosstabGroupHeaderPositionTest(rowGroup1, 0, 0, 0, 100, 20);
        crosstabGroupTotalHeaderPositionTest(rowGroup1, 0, 0, 0, 200, 20);

        // row group 2
        crosstabGroupHeaderPositionTest(rowGroup2, 0, 0, 0, 100, 20);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("a", "c", 1);
        dataSource.add("a", "c", 2);
        dataSource.add("a", "d", 3);
        dataSource.add("a", "d", 4);
        return dataSource;
    }
}
