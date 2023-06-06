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
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.Date;
import java.util.Locale;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class DataTypeGroupTest extends AbstractJasperValueTest {
    private TextColumnBuilder<Date> column1;
    private TextColumnBuilder<Date> column2;
    private ColumnGroupBuilder group1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setLocale(Locale.ENGLISH)
          .columns(column1 = col.column("Column1", "field1", type.dateMonthType()), column2 = col.column("Column2", "field1", type.dateType()))
          .groupBy(group1 = grp.group(column1).groupByDataType());
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // column2
        columnTitleCountTest(column2, 1);
        columnTitleValueTest(column2, "Column2");
        columnDetailCountTest(column2, 2);
        columnDetailValueTest(column2, "01/01/2010", "02/01/2010");

        // group1
        groupHeaderTitleCountTest(group1, 0);
        groupHeaderCountTest(group1, 2);
        groupHeaderValueTest(group1, "January", "February");
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 1; i++) {
            dataSource.add(toDate(2010, 1, 1));
            dataSource.add(toDate(2010, 2, 1));
        }
        return dataSource;
    }
}
