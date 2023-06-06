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
package software.xdev.dynamicreports.report.base.component;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.component.DRIXyList;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRXyList class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRXyList extends DRDimensionComponent implements DRIXyList {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private List<DRXyListCell> xyListCells;

    /** {@inheritDoc} */
    @Override
    protected void init() {
        super.init();
        this.xyListCells = new ArrayList<DRXyListCell>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRXyListCell> getXyListCells() {
        return xyListCells;
    }

    /**
     * <p>addCell.</p>
     *
     * @param cell a {@link software.xdev.dynamicreports.report.base.component.DRXyListCell} object.
     */
    public void addCell(DRXyListCell cell) {
        Validate.notNull(cell, "cell must not be null");
        xyListCells.add(cell);
    }

    /**
     * <p>addComponent.</p>
     *
     * @param x         a {@link java.lang.Integer} object.
     * @param y         a {@link java.lang.Integer} object.
     * @param component a {@link software.xdev.dynamicreports.report.base.component.DRComponent} object.
     */
    public void addComponent(Integer x, Integer y, DRComponent component) {
        xyListCells.add(new DRXyListCell(x, y, component));
    }

}
