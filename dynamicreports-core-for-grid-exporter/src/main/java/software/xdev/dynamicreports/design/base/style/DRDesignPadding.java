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
package software.xdev.dynamicreports.design.base.style;

import software.xdev.dynamicreports.design.definition.style.DRIDesignPadding;
import software.xdev.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * <p>DRDesignPadding class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignPadding implements DRIDesignPadding {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer top;
    private Integer left;
    private Integer bottom;
    private Integer right;

    /** {@inheritDoc} */
    @Override
    public Integer getTop() {
        return top;
    }

    /**
     * <p>Setter for the field <code>top</code>.</p>
     *
     * @param top a {@link java.lang.Integer} object.
     */
    public void setTop(Integer top) {
        this.top = top;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getLeft() {
        return left;
    }

    /**
     * <p>Setter for the field <code>left</code>.</p>
     *
     * @param left a {@link java.lang.Integer} object.
     */
    public void setLeft(Integer left) {
        this.left = left;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getBottom() {
        return bottom;
    }

    /**
     * <p>Setter for the field <code>bottom</code>.</p>
     *
     * @param bottom a {@link java.lang.Integer} object.
     */
    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getRight() {
        return right;
    }

    /**
     * <p>Setter for the field <code>right</code>.</p>
     *
     * @param right a {@link java.lang.Integer} object.
     */
    public void setRight(Integer right) {
        this.right = right;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        DRDesignPadding o = (DRDesignPadding) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder().append(top, o.top).append(left, o.left).append(bottom, o.bottom).append(right, o.right);
        return equalsBuilder.isEquals();
    }
}
