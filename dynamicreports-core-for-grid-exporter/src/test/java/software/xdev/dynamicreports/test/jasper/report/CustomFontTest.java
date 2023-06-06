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
package software.xdev.dynamicreports.test.jasper.report;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.Serializable;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class CustomFontTest extends AbstractJasperPositionTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;
    private AggregationSubtotalBuilder<String> subtotal1;

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        StyleBuilder style = stl.style().setFontName("FreeUniversal");

        rb.columns(column1 = col.column("Column1", "field1", type.stringType())).subtotalsAtColumnFooter(subtotal1 = sbt.text("text", column1).setStyle(style));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        // column1
        columnTitlePositionTest(column1, 0, 10, 10, 575, 16);
        columnDetailPositionTest(column1, 0, 10, 26, 575, 16);

        // subtotal1
        subtotalPositionTest(subtotal1, 0, 10, 818, 575, 14);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        dataSource.add("1");
        return dataSource;
    }
}
