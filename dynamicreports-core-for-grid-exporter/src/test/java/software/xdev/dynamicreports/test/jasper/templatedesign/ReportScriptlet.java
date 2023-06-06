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
package software.xdev.dynamicreports.test.jasper.templatedesign;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JasperReport;

import java.util.Map;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author Ricardo Mariaca
 */
public class ReportScriptlet extends JRDefaultScriptlet {
    private JasperReportBuilder dynamicSubreport;

    @Override
    public void beforeDetailEval() throws JRScriptletException {
        super.beforeDetailEval();
        dynamicSubreport = report();
        dynamicSubreport.setPageFormat(515, PageType.A4.getHeight(), PageOrientation.PORTRAIT)
                        .setPageMargin(margin(0))
                        .columns(col.column("Column1", "field1", type.stringType()), col.column("Column2", "field2", type.integerType()))
                        .title(cmp.text("dynamic subreport"));
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("field1", "field2");
        dataSource.add("value1", 1);
        dataSource.add("value2", 5);
        return dataSource;
    }

    public JasperReport getDynamicSubreport() throws DRException {
        return dynamicSubreport.toJasperReport();
    }

    public Map<String, Object> getDynamicSubreportParameters() throws DRException {
        return dynamicSubreport.getJasperParameters();
    }

    public JRDataSource getDynamicSubreportDataSource() {
        return createDataSource();
    }
}
