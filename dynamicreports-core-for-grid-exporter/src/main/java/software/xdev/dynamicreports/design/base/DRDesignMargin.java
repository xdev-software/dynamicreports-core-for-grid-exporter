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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.definition.DRIDesignMargin;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignMargin class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignMargin implements DRIDesignMargin {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int top;
    private int left;
    private int bottom;
    private int right;

    /** {@inheritDoc} */
    @Override
    public int getTop() {
        return top;
    }

    /**
     * <p>Setter for the field <code>top</code>.</p>
     *
     * @param top a int.
     */
    public void setTop(int top) {
        this.top = top;
    }

    /** {@inheritDoc} */
    @Override
    public int getLeft() {
        return left;
    }

    /**
     * <p>Setter for the field <code>left</code>.</p>
     *
     * @param left a int.
     */
    public void setLeft(int left) {
        this.left = left;
    }

    /** {@inheritDoc} */
    @Override
    public int getBottom() {
        return bottom;
    }

    /**
     * <p>Setter for the field <code>bottom</code>.</p>
     *
     * @param bottom a int.
     */
    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    /** {@inheritDoc} */
    @Override
    public int getRight() {
        return right;
    }

    /**
     * <p>Setter for the field <code>right</code>.</p>
     *
     * @param right a int.
     */
    public void setRight(int right) {
        this.right = right;
    }
}
