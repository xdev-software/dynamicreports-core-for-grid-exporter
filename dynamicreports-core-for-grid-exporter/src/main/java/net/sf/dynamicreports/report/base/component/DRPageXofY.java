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
package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.component.DRIPageXofY;

/**
 * <p>DRPageXofY class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRPageXofY extends DRFormatField implements DRIPageXofY {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer pageXWidth;
    private ComponentDimensionType pageXWidthType;
    private Integer pageYWidth;
    private ComponentDimensionType pageYWidthType;

    /** {@inheritDoc} */
    @Override
    public Integer getPageXWidth() {
        return pageXWidth;
    }

    /**
     * <p>Setter for the field <code>pageXWidth</code>.</p>
     *
     * @param pageXWidth a {@link java.lang.Integer} object.
     */
    public void setPageXWidth(Integer pageXWidth) {
        this.pageXWidth = pageXWidth;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentDimensionType getPageXWidthType() {
        return pageXWidthType;
    }

    /**
     * <p>Setter for the field <code>pageXWidthType</code>.</p>
     *
     * @param pageXWidthType a {@link net.sf.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public void setPageXWidthType(ComponentDimensionType pageXWidthType) {
        this.pageXWidthType = pageXWidthType;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageYWidth() {
        return pageYWidth;
    }

    /**
     * <p>Setter for the field <code>pageYWidth</code>.</p>
     *
     * @param pageYWidth a {@link java.lang.Integer} object.
     */
    public void setPageYWidth(Integer pageYWidth) {
        this.pageYWidth = pageYWidth;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentDimensionType getPageYWidthType() {
        return pageYWidthType;
    }

    /**
     * <p>Setter for the field <code>pageYWidthType</code>.</p>
     *
     * @param pageYWidthType a {@link net.sf.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    public void setPageYWidthType(ComponentDimensionType pageYWidthType) {
        this.pageYWidthType = pageYWidthType;
    }

}
