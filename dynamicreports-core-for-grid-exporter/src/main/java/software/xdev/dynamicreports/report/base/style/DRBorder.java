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
package software.xdev.dynamicreports.report.base.style;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.style.DRIBorder;

/**
 * <p>DRBorder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRBorder implements DRIBorder {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRPen topPen;
    private DRPen leftPen;
    private DRPen bottomPen;
    private DRPen rightPen;

    /**
     * <p>Constructor for DRBorder.</p>
     */
    public DRBorder() {
        topPen = new DRPen();
        leftPen = new DRPen();
        bottomPen = new DRPen();
        rightPen = new DRPen();
    }

    /**
     * <p>Constructor for DRBorder.</p>
     *
     * @param pen a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public DRBorder(DRPen pen) {
        topPen = pen;
        leftPen = pen;
        bottomPen = pen;
        rightPen = pen;
    }

    /** {@inheritDoc} */
    @Override
    public DRPen getTopPen() {
        return topPen;
    }

    /**
     * <p>Setter for the field <code>topPen</code>.</p>
     *
     * @param topPen a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public void setTopPen(DRPen topPen) {
        this.topPen = topPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRPen getLeftPen() {
        return leftPen;
    }

    /**
     * <p>Setter for the field <code>leftPen</code>.</p>
     *
     * @param leftPen a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public void setLeftPen(DRPen leftPen) {
        this.leftPen = leftPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRPen getBottomPen() {
        return bottomPen;
    }

    /**
     * <p>Setter for the field <code>bottomPen</code>.</p>
     *
     * @param bottomPen a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public void setBottomPen(DRPen bottomPen) {
        this.bottomPen = bottomPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRPen getRightPen() {
        return rightPen;
    }

    /**
     * <p>Setter for the field <code>rightPen</code>.</p>
     *
     * @param rightPen a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public void setRightPen(DRPen rightPen) {
        this.rightPen = rightPen;
    }
}
