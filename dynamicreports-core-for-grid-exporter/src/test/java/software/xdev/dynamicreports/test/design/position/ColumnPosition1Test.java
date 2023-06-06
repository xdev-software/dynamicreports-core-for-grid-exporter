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
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.test.design.AbstractBandTest;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

/**
 * @author Ricardo Mariaca
 */
public class ColumnPosition1Test extends AbstractBandTest {

    @Override
    public void configureReport(ReportBuilder<?> rb) {
        rb.columns(col.column("Column1", "field1", Integer.class), col.column("Column2", "field2", Integer.class));
    }

    @Override
    protected void columnHeaderBandTest(DRDesignBand band) {
        testBand(band);
    }

    @Override
    protected void detailBandTest(DRDesignBand band) {
        testBand(band);
    }

    protected void testBand(DRDesignBand band) {
        DRDesignComponent component = band.getBandComponent();
        Assertions.assertTrue(component instanceof DRDesignList);
        DRDesignList list = (DRDesignList) component;
        Assertions.assertEquals(ListType.HORIZONTAL, list.getType());
        Assertions.assertEquals(2, list.getComponents().size());
        componentPositionTest(list, 0, 0, 575, 16);
        Assertions.assertTrue(list.getComponents().get(0) instanceof DRDesignTextField);
        Assertions.assertTrue(list.getComponents().get(1) instanceof DRDesignTextField);

        // column1
        componentPositionTest(list.getComponents().get(0), 0, 0, 287, 16);

        // column2
        componentPositionTest(list.getComponents().get(1), 287, 0, 288, 16);
    }
}
