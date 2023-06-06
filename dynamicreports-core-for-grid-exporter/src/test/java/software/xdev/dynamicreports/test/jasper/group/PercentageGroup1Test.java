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
package software.xdev.dynamicreports.test.jasper.group;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.PercentageColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.PercentageTotalType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
public class PercentageGroup1Test extends AbstractJasperValueTest {
    private PercentageColumnBuilder percentage1;
    private PercentageColumnBuilder percentage2;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TextColumnBuilder<String> column1;
        TextColumnBuilder<Integer> column2;

        rb.setLocale(Locale.ENGLISH)
          .columns(column1 = col.column("Column1", "field1", String.class), column2 = col.column("Column2", "field2", Integer.class), percentage1 = col.percentageColumn(column2),
                   percentage2 = col.percentageColumn(column2).setTotalType(PercentageTotalType.REPORT))
          .groupBy(column1);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);
        // percentage1
        columnDetailCountTest(percentage1, 6);
        columnDetailValueTest(percentage1, "16.67%", "33.33%", "50.00%", "26.67%", "33.33%", "40.00%");
        // percentage2
        columnDetailCountTest(percentage2, 6);
        columnDetailValueTest(percentage2, "4.76%", "9.52%", "14.29%", "19.05%", "23.81%", "28.57%");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 1; i <= 3; i++) {
            dataSource.add("group1", i);
        }
        for (int i = 4; i <= 6; i++) {
            dataSource.add("group2", i);
        }
        return dataSource;
    }
}
