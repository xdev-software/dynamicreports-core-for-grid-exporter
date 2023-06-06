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
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author Ricardo Mariaca
 */
public class ListGapTest extends AbstractJasperPositionTest {

    @Override
    protected void configureReport(JasperReportBuilder rb) {
        rb.setPageFormat(PageType.A5).setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL).title(cmp.horizontalList(cmp.text("a"), cmp.text("a")).setGap(250));
    }

    @Override
    public void test() {
        super.test();

        numberOfPagesTest(1);

        elementPositionTest("title.list1", 0, 10, 10, 401, 16);

        elementPositionTest("title.textField1", 0, 0, 0, 75, 16);
        elementPositionTest("title.textField2", 0, 325, 0, 76, 16);
    }
}
