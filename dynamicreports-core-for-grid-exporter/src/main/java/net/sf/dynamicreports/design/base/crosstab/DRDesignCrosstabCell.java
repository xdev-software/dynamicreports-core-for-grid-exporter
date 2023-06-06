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
package net.sf.dynamicreports.design.base.crosstab;

import net.sf.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCell;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignCrosstabCell class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignCrosstabCell implements DRIDesignCrosstabCell {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private String rowTotalGroup;
    private String columnTotalGroup;
    private DRDesignCrosstabCellContent content;

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getRowTotalGroup() {
        return rowTotalGroup;
    }

    /**
     * <p>Setter for the field <code>rowTotalGroup</code>.</p>
     *
     * @param rowTotalGroup a {@link java.lang.String} object.
     */
    public void setRowTotalGroup(String rowTotalGroup) {
        this.rowTotalGroup = rowTotalGroup;
    }

    /** {@inheritDoc} */
    @Override
    public String getColumnTotalGroup() {
        return columnTotalGroup;
    }

    /**
     * <p>Setter for the field <code>columnTotalGroup</code>.</p>
     *
     * @param columnTotalGroup a {@link java.lang.String} object.
     */
    public void setColumnTotalGroup(String columnTotalGroup) {
        this.columnTotalGroup = columnTotalGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignCrosstabCellContent getContent() {
        return content;
    }

    /**
     * <p>Setter for the field <code>content</code>.</p>
     *
     * @param content a {@link net.sf.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent} object.
     */
    public void setContent(DRDesignCrosstabCellContent content) {
        this.content = content;
    }
}
