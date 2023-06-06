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
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import software.xdev.dynamicreports.test.jasper.JasperTestUtils;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;
import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

/**
 * @author Ricardo Mariaca
 */
public class Crosstab4Test extends AbstractJasperCrosstabValueTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private CrosstabRowGroupBuilder<String> rowGroup;
    private CrosstabColumnGroupBuilder<String> columnGroup;
    private CrosstabMeasureBuilder<Integer> measure1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        CrosstabBuilder crosstab = ctab.crosstab()
                                       .setDataPreSorted(true)
                                       .rowGroups(rowGroup = ctab.rowGroup("field1", String.class))
                                       .columnGroups(columnGroup = ctab.columnGroup("field2", String.class))
                                       .measures(measure1 = ctab.measure("field3", Integer.class, Calculation.SUM));

        rb.setLocale(Locale.ENGLISH).summary(crosstab, crosstab);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        setCrosstabBand("summary");

        // column group
        crosstabGroupHeaderCountTest(columnGroup, 2);
        crosstabGroupHeaderValueTest(columnGroup, "d", "c");
        crosstabGroupTotalHeaderCountTest(columnGroup, 1);
        crosstabGroupTotalHeaderValueTest(columnGroup, "Total");

        // row group
        crosstabGroupHeaderCountTest(rowGroup, 3);
        crosstabGroupHeaderValueTest(rowGroup, "c", "a", "b");
        crosstabGroupTotalHeaderCountTest(rowGroup, 1);
        crosstabGroupTotalHeaderValueTest(rowGroup, "Total");

        // measure1
        crosstabCellCountTest(measure1, null, null, 6);
        crosstabCellValueTest(measure1, null, null, "1", "1", "1", "1", "1", "1");
        crosstabCellCountTest(measure1, null, columnGroup, 3);
        crosstabCellValueTest(measure1, null, columnGroup, "2", "2", "2");
        crosstabCellCountTest(measure1, rowGroup, null, 2);
        crosstabCellValueTest(measure1, rowGroup, null, "3", "3");
        crosstabCellCountTest(measure1, rowGroup, columnGroup, 1);
        crosstabCellValueTest(measure1, rowGroup, columnGroup, "6");

        // column group
        elementCountTest(getPrefix(2) + JasperTestUtils.getCrosstabGroupHeaderName(columnGroup), 2);
        elementCountTest(getPrefix(2) + JasperTestUtils.getCrosstabGroupTotalHeaderName(columnGroup), 1);

        // row group
        elementCountTest(getPrefix(2) + JasperTestUtils.getCrosstabGroupHeaderName(rowGroup), 3);
        elementCountTest(getPrefix(2) + JasperTestUtils.getCrosstabGroupTotalHeaderName(rowGroup), 1);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
        dataSource.add("c", "d", 1);
        dataSource.add("c", "c", 1);
        dataSource.add("a", "d", 1);
        dataSource.add("a", "c", 1);
        dataSource.add("b", "d", 1);
        dataSource.add("b", "c", 1);

        return dataSource;
    }
}
