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
package net.sf.dynamicreports.test.jasper.templatedesign;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;

import java.io.InputStream;
import java.io.Serializable;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
public class TemplateDesignPosition2Test extends AbstractJasperPositionTest implements Serializable {
    private static final long serialVersionUID = 1L;

    private TextColumnBuilder<String> column1;

    @Override
    protected void configureReport(JasperReportBuilder rb) throws DRException {
        InputStream is = TemplateDesignPosition1Test.class.getResourceAsStream("templatedesign2.jrxml");
        rb.setTemplateDesign(is).columns(column1 = col.column("Column1", "field1", String.class));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        columnTitlePositionTest(column1, 0, 20, 134, 555, 16);
        columnDetailPositionTest(column1, 0, 20, 150, 555, 16);
    }

    @Override
    protected JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1");
        for (int i = 0; i < 1; i++) {
            dataSource.add("row");
        }
        return dataSource;
    }
}
