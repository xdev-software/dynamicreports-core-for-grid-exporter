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
package net.sf.dynamicreports.test.jasper.component;

import net.sf.dynamicreports.report.exception.DRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

/**
 * @author Ricardo Mariaca
 */
public class PageNumber6Test {

    @Test
    public void test() {
        try {
            report().summary(cmp.pageXofY().setFormatExpression("{1} {0}")).toJasperPrint();
            Assertions.fail("Wrong page number");
        } catch (DRException e) {
        }

        try {
            report().summary(cmp.pageXofY().setFormatExpression("{1} {1}")).toJasperPrint();
            Assertions.fail("Wrong page number");
        } catch (DRException e) {
        }

        try {
            report().summary(cmp.pageXofY().setFormatExpression("{0} {0}")).toJasperPrint();
            Assertions.fail("Wrong page number");
        } catch (DRException e) {
        }

        try {
            report().summary(cmp.pageXofY().setFormatExpression("{0}")).toJasperPrint();
            Assertions.fail("Wrong page number");
        } catch (DRException e) {
        }

        try {
            report().summary(cmp.pageXofY().setFormatExpression("{1}")).toJasperPrint();
            Assertions.fail("Wrong page number");
        } catch (DRException e) {
        }
    }
}
