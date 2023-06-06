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
package net.sf.dynamicreports.test.design.report;

import org.junit.jupiter.api.Assertions;
import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Evaluation;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.dynamicreports.test.design.DesignReportBuilder;
import org.junit.jupiter.api.Test;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.variable;

/**
 * @author Ricardo Mariaca
 */
public class ExceptionTest {

    @Test
    public void testComponentFixedWidth() {
        ReportBuilder<?> rb1 = new DesignReportBuilder();
        rb1.title(cmp.text("").setFixedWidth(600));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("component fixed width exception");
        } catch (DRException e) {
        }

        ReportBuilder<?> rb2 = new DesignReportBuilder();
        rb2.title(cmp.horizontalList(cmp.text("").setFixedWidth(600)));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("component fixed width exception");
        } catch (DRException e) {
        }

        ReportBuilder<?> rb3 = new DesignReportBuilder();
        rb3.title(cmp.horizontalFlowList(cmp.text("").setFixedWidth(600)));
        try {
            new DRDesignReport(rb3.getReport());
            Assertions.fail("component fixed width exception");
        } catch (DRException e) {
        }
    }

    @Test
    public void testComponentMinimumWidth() {
        ReportBuilder<?> rb1 = new DesignReportBuilder();
        rb1.title(cmp.text("").setMinWidth(600));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("component minimum width exception");
        } catch (DRException e) {
        }

        ReportBuilder<?> rb2 = new DesignReportBuilder();
        rb2.title(cmp.horizontalList(cmp.text("").setMinWidth(600)));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("component fixed width exception");
        } catch (DRException e) {
        }

        ReportBuilder<?> rb3 = new DesignReportBuilder();
        rb3.title(cmp.horizontalFlowList(cmp.text("").setMinWidth(600)));
        try {
            new DRDesignReport(rb3.getReport());
            Assertions.fail("component fixed width exception");
        } catch (DRException e) {
        }
    }

    @Test
    public void testVariable() {
        TextColumnBuilder<Object> column = col.column("fieldName", Object.class);
        ColumnGroupBuilder group = grp.group(column);
        ReportBuilder<?> rb1 =
            new DesignReportBuilder().columns(column).groupBy(group).variables(variable("fieldName", Object.class, Calculation.SUM).setResetGroup(group).setResetType(Evaluation.REPORT));
        try {
            new DRDesignReport(rb1.getReport());
            Assertions.fail("variable exception");
        } catch (DRException e) {
        }

        ReportBuilder<?> rb2 = new DesignReportBuilder().columns(column).groupBy(group).variables(variable("fieldName", Object.class, Calculation.SUM).setResetType(Evaluation.GROUP));
        try {
            new DRDesignReport(rb2.getReport());
            Assertions.fail("variable exception");
        } catch (DRException e) {
        }
    }
}
