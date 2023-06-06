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
package software.xdev.dynamicreports.test.jasper.tableofcontents;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.ReportTemplateBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class TableOfContentsPosition4Test extends AbstractJasperPositionTest {
    private TextColumnBuilder<String> column1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer().setPosition(TableOfContentsPosition.BOTTOM).setTextFixedWidth(100).setPageIndexFixedWidth(30);

        ReportTemplateBuilder template = template().setTableOfContents(true).setTableOfContentsCustomizer(tableOfContentsCustomizer);

        rb.setTemplate(template).columns(column1 = col.column("Column1", "field1", type.stringType()), col.column("Column2", "field2", type.stringType())).groupBy(column1);
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(2);

        elementPositionTest("title.textField1", 0, 10, 10, 575, 19);

        for (int i = 0; i < 10; i++) {
            elementPositionTest("detail.list2", i, 10, 49 + 16 * i, 575, 16);
            elementPositionTest("detail.textField1", i, 0, 0, 100, 16);
            elementPositionTest("detail.textField2", i, 100, 0, 445, 16);
            elementPositionTest("detail.textField3", i, 545, 0, 30, 16);
        }

        containsElement("title.textField1", 1);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        for (int i = 0; i < 10; i++) {
            dataSource.add("value" + i, "text");
        }
        return dataSource;
    }
}
