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
package software.xdev.dynamicreports.report.base.crosstab;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;

/**
 * <p>DRCrosstabColumnGroup class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRCrosstabColumnGroup<T> extends DRCrosstabGroup<T> implements DRICrosstabColumnGroup<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer headerHeight;
    private Integer totalHeaderWidth;

    /** {@inheritDoc} */
    @Override
    public Integer getHeaderHeight() {
        return headerHeight;
    }

    /**
     * <p>Setter for the field <code>headerHeight</code>.</p>
     *
     * @param headerHeight a {@link java.lang.Integer} object.
     */
    public void setHeaderHeight(Integer headerHeight) {
        this.headerHeight = headerHeight;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getTotalHeaderWidth() {
        return totalHeaderWidth;
    }

    /**
     * <p>Setter for the field <code>totalHeaderWidth</code>.</p>
     *
     * @param totalHeaderWidth a {@link java.lang.Integer} object.
     */
    public void setTotalHeaderWidth(Integer totalHeaderWidth) {
        this.totalHeaderWidth = totalHeaderWidth;
    }
}
