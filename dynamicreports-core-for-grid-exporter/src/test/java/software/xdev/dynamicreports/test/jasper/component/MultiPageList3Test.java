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
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.MultiPageListBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class MultiPageList3Test extends AbstractJasperValueTest {
    private TextColumnBuilder<Integer> column1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        column1 = col.column("Column1", "field1", type.integerType());

        MultiPageListBuilder multiPageList = cmp.multiPageList();
        multiPageList.add(cmp.subreport(createSubreport(80)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(80)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(10)));
        multiPageList.newPage();
        multiPageList.add(cmp.subreport(createSubreport(10)));
        rb.title(multiPageList);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(4);

        columnDetailAtPageIndexTest(column1, 0);
        columnDetailAtPageIndexTest(column1, 1);
        columnDetailAtPageIndexTest(column1, 2);
        columnDetailAtPageIndexTest(column1, 3);
    }

    private JasperReportBuilder createSubreport(int numberOfRecords) {
        JasperReportBuilder report = report();
        report.title(cmp.verticalGap(6)).setPageColumnsPerPage(2).columns(column1).setDataSource(createSubreportDataSource(numberOfRecords));

        return report;
    }

    protected JRDataSource createSubreportDataSource(int numberOfRecords) {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < numberOfRecords; i++) {
            dataSource.add(i);
        }
        return dataSource;
    }
}
