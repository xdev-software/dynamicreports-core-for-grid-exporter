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
package software.xdev.dynamicreports.test.design.position;

import org.junit.jupiter.api.Assertions;
import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.ListType;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grid;

/**
 * @author Ricardo Mariaca
 */
public class ColumnPosition2Test extends ColumnPosition1Test {

    @Override
    public void configureReport(ReportBuilder<?> rb) {
        TextColumnBuilder<Integer> column1, column2, column3, column4, column5;

        rb.columns(column1 = col.column("Column1", "field1", Integer.class), column2 = col.column("Column2", "field2", Integer.class), column3 = col.column("Column3", "field3", Integer.class),
                   column4 = col.column("Column4", "field4", Integer.class).setWidth(50), column5 = col.column("Column5", "field5", Integer.class))
          .columnGrid(column1, grid.horizontalColumnGridList().add(column2, column3).newRow().add(column4, column5));
    }

    @Override
    protected void testBand(DRDesignBand band) {
        DRDesignComponent component = band.getBandComponent();
        Assertions.assertTrue(component instanceof DRDesignList);
        DRDesignList list1 = (DRDesignList) component;
        Assertions.assertEquals(ListType.HORIZONTAL, list1.getType());
        Assertions.assertEquals(2, list1.getComponents().size());
        componentPositionTest(list1, 0, 0, 575, 32);
        Assertions.assertTrue(list1.getComponents().get(0) instanceof DRDesignTextField);
        Assertions.assertTrue(list1.getComponents().get(1) instanceof DRDesignList);

        DRDesignList list2 = (DRDesignList) list1.getComponents().get(1);
        Assertions.assertEquals(ListType.VERTICAL, list2.getType());
        Assertions.assertEquals(2, list2.getComponents().size());
        componentPositionTest(list2, 191, 0, 384, 32);
        Assertions.assertTrue(list2.getComponents().get(0) instanceof DRDesignList);
        Assertions.assertTrue(list2.getComponents().get(1) instanceof DRDesignList);

        DRDesignList list3 = (DRDesignList) list2.getComponents().get(0);
        Assertions.assertEquals(ListType.HORIZONTAL, list3.getType());
        Assertions.assertEquals(2, list3.getComponents().size());
        componentPositionTest(list3, 191, 0, 384, 16);
        Assertions.assertTrue(list3.getComponents().get(0) instanceof DRDesignTextField);
        Assertions.assertTrue(list3.getComponents().get(1) instanceof DRDesignTextField);

        DRDesignList list4 = (DRDesignList) list2.getComponents().get(1);
        Assertions.assertEquals(ListType.HORIZONTAL, list4.getType());
        Assertions.assertEquals(2, list4.getComponents().size());
        componentPositionTest(list4, 191, 16, 384, 16);
        Assertions.assertTrue(list4.getComponents().get(0) instanceof DRDesignTextField);
        Assertions.assertTrue(list4.getComponents().get(1) instanceof DRDesignTextField);

        // column1
        componentPositionTest(list1.getComponents().get(0), 0, 0, 191, 32);

        // column2
        componentPositionTest(list3.getComponents().get(0), 0, 0, 192, 16);

        // column3
        componentPositionTest(list3.getComponents().get(1), 192, 0, 192, 16);

        // column4
        componentPositionTest(list4.getComponents().get(0), 0, 0, 128, 16);

        // column5
        componentPositionTest(list4.getComponents().get(1), 128, 0, 256, 16);
    }
}
