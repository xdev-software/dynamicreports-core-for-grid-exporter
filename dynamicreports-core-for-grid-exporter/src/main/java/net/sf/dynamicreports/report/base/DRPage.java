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
package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.DRIPage;
import org.apache.commons.lang3.Validate;

/**
 * <p>DRPage class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRPage implements DRIPage {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer width;
    private Integer height;
    private PageOrientation orientation;
    private DRMargin margin;
    private Integer columnsPerPage;
    private Integer columnSpace;
    private Boolean ignorePageWidth;

    /**
     * <p>setPageFormat.</p>
     *
     * @param pageType    a {@link net.sf.dynamicreports.report.constant.PageType} object.
     * @param orientation a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
     */
    public void setPageFormat(PageType pageType, PageOrientation orientation) {
        Validate.notNull(pageType, "pageType must not be null");
        setPageFormat(pageType.getWidth(), pageType.getHeight(), orientation);
    }

    /**
     * <p>setPageFormat.</p>
     *
     * @param width       a {@link java.lang.Integer} object.
     * @param height      a {@link java.lang.Integer} object.
     * @param orientation a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
     */
    public void setPageFormat(Integer width, Integer height, PageOrientation orientation) {
        Validate.isTrue(width >= 0, "width must be >= 0");
        Validate.isTrue(height >= 0, "height must be >= 0");
        Validate.notNull(orientation, "orientation must not be null");
        if (orientation.equals(PageOrientation.PORTRAIT)) {
            setWidth(width);
            setHeight(height);
        } else {
            setWidth(height);
            setHeight(width);
        }
        setOrientation(orientation);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the page width.
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * Sets the page width.
     *
     * @param width the page width >= 0
     * @throws java.lang.IllegalArgumentException if <code>width</code> is < 0
     * @see net.sf.dynamicreports.report.builder.Units
     */
    public void setWidth(Integer width) {
        if (width != null) {
            Validate.isTrue(width >= 0, "width must be >= 0");
        }
        this.width = width;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the page height.
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * Sets the page height.
     *
     * @param height the page height >= 0
     * @throws java.lang.IllegalArgumentException if <code>height</code> is < 0
     * @see net.sf.dynamicreports.report.builder.Units
     */
    public void setHeight(Integer height) {
        if (height != null) {
            Validate.isTrue(height >= 0, "height must be >= 0");
        }
        this.height = height;
    }

    /** {@inheritDoc} */
    @Override
    public PageOrientation getOrientation() {
        return orientation;
    }

    /**
     * <p>Setter for the field <code>orientation</code>.</p>
     *
     * @param orientation a {@link net.sf.dynamicreports.report.constant.PageOrientation} object.
     */
    public void setOrientation(PageOrientation orientation) {
        this.orientation = orientation;
    }

    /** {@inheritDoc} */
    @Override
    public DRMargin getMargin() {
        return margin;
    }

    /**
     * <p>Setter for the field <code>margin</code>.</p>
     *
     * @param margin a {@link net.sf.dynamicreports.report.base.DRMargin} object.
     */
    public void setMargin(DRMargin margin) {
        this.margin = margin;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getColumnsPerPage() {
        return columnsPerPage;
    }

    /**
     * <p>Setter for the field <code>columnsPerPage</code>.</p>
     *
     * @param columnsPerPage a {@link java.lang.Integer} object.
     */
    public void setColumnsPerPage(Integer columnsPerPage) {
        if (columnsPerPage != null) {
            Validate.isTrue(columnsPerPage >= 1, "columnsPerPage must be >= 1");
        }
        this.columnsPerPage = columnsPerPage;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getColumnSpace() {
        return columnSpace;
    }

    /**
     * <p>Setter for the field <code>columnSpace</code>.</p>
     *
     * @param columnSpace a {@link java.lang.Integer} object.
     */
    public void setColumnSpace(Integer columnSpace) {
        if (columnSpace != null) {
            Validate.isTrue(columnSpace >= 0, "columnSpace must be >= 0");
        }
        this.columnSpace = columnSpace;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getIgnorePageWidth() {
        return ignorePageWidth;
    }

    /**
     * <p>Setter for the field <code>ignorePageWidth</code>.</p>
     *
     * @param ignorePageWidth a {@link java.lang.Boolean} object.
     */
    public void setIgnorePageWidth(Boolean ignorePageWidth) {
        this.ignorePageWidth = ignorePageWidth;
    }
}
