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
package net.sf.dynamicreports.report.base.crosstab;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * <p>DRCrosstabCellStyle class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRCrosstabCellStyle implements DRICrosstabCellStyle {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRICrosstabRowGroup<?> rowGroup;
    private DRICrosstabColumnGroup<?> columnGroup;
    private DRIReportStyle style;

    /**
     * <p>Constructor for DRCrosstabCellStyle.</p>
     *
     * @param style a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRCrosstabCellStyle(DRIReportStyle style) {
        this(style, null, null);
    }

    /**
     * <p>Constructor for DRCrosstabCellStyle.</p>
     *
     * @param style       a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     * @param rowGroup    a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup} object.
     * @param columnGroup a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup} object.
     */
    public DRCrosstabCellStyle(DRIReportStyle style, DRICrosstabRowGroup<?> rowGroup, DRICrosstabColumnGroup<?> columnGroup) {
        this.style = style;
        this.rowGroup = rowGroup;
        this.columnGroup = columnGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRICrosstabRowGroup<?> getRowGroup() {
        return rowGroup;
    }

    /**
     * <p>Setter for the field <code>rowGroup</code>.</p>
     *
     * @param rowGroup a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup} object.
     */
    public void setRowGroup(DRICrosstabRowGroup<?> rowGroup) {
        this.rowGroup = rowGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRICrosstabColumnGroup<?> getColumnGroup() {
        return columnGroup;
    }

    /**
     * <p>Setter for the field <code>columnGroup</code>.</p>
     *
     * @param columnGroup a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup} object.
     */
    public void setColumnGroup(DRICrosstabColumnGroup<?> columnGroup) {
        this.columnGroup = columnGroup;
    }

    /** {@inheritDoc} */
    @Override
    public DRIReportStyle getStyle() {
        return style;
    }

    /**
     * <p>Setter for the field <code>style</code>.</p>
     *
     * @param style a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public void setStyle(DRIReportStyle style) {
        this.style = style;
    }
}
