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
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;
import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

/**
 * @author Ricardo Mariaca
 */
public class OrderCrosstabTest extends AbstractJasperCrosstabValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private CrosstabRowGroupBuilder<String> rowGroup;
    private CrosstabColumnGroupBuilder<String> columnGroup;
    private CrosstabMeasureBuilder<Integer> measure1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);

        CrosstabBuilder crosstab = ctab.crosstab()
                                       .rowGroups(rowGroup = ctab.rowGroup("field1", String.class).setOrderType(OrderType.DESCENDING).setShowTotal(false))
                                       .columnGroups(columnGroup = ctab.columnGroup("field2", String.class).setShowTotal(false).orderBy(measure1).setOrderType(OrderType.DESCENDING))
                                       .measures(measure1);

        rb.setLocale(Locale.ENGLISH).summary(crosstab);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("summary");

        // column group
        crosstabGroupHeaderCountTest(columnGroup, 2);
        crosstabGroupHeaderValueTest(columnGroup, "c", "d");
        crosstabGroupTotalHeaderCountTest(columnGroup, 0);

        // row group
        crosstabGroupHeaderCountTest(rowGroup, 2);
        crosstabGroupHeaderValueTest(rowGroup, "b", "a");
        crosstabGroupTotalHeaderCountTest(rowGroup, 0);

        // measure1
        crosstabCellCountTest(measure1, null, null, 4);
        crosstabCellValueTest(measure1, null, null, "7", "3", "15", "11");
        crosstabCellCountTest(measure1, null, columnGroup, 0);
        crosstabCellCountTest(measure1, rowGroup, null, 0);
        crosstabCellCountTest(measure1, rowGroup, columnGroup, 0);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("a", "c", 8);
        dataSource.add("a", "c", 7);
        dataSource.add("a", "d", 6);
        dataSource.add("a", "d", 5);
        dataSource.add("b", "c", 4);
        dataSource.add("b", "c", 3);
        dataSource.add("b", "d", 2);
        dataSource.add("b", "d", 1);
        return dataSource;
    }
}
