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

import software.xdev.dynamicreports.design.definition.style.DRIDesignBorder;
import software.xdev.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * <p>DRDesignBorder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignBorder implements DRIDesignBorder {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRDesignPen topPen;
    private DRDesignPen leftPen;
    private DRDesignPen bottomPen;
    private DRDesignPen rightPen;

    /** {@inheritDoc} */
    @Override
    public DRDesignPen getTopPen() {
        return topPen;
    }

    /**
     * <p>Setter for the field <code>topPen</code>.</p>
     *
     * @param topPen a {@link software.xdev.dynamicreports.design.base.style.DRDesignPen} object.
     */
    public void setTopPen(DRDesignPen topPen) {
        this.topPen = topPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPen getLeftPen() {
        return leftPen;
    }

    /**
     * <p>Setter for the field <code>leftPen</code>.</p>
     *
     * @param leftPen a {@link software.xdev.dynamicreports.design.base.style.DRDesignPen} object.
     */
    public void setLeftPen(DRDesignPen leftPen) {
        this.leftPen = leftPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPen getBottomPen() {
        return bottomPen;
    }

    /**
     * <p>Setter for the field <code>bottomPen</code>.</p>
     *
     * @param bottomPen a {@link software.xdev.dynamicreports.design.base.style.DRDesignPen} object.
     */
    public void setBottomPen(DRDesignPen bottomPen) {
        this.bottomPen = bottomPen;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPen getRightPen() {
        return rightPen;
    }

    /**
     * <p>Setter for the field <code>rightPen</code>.</p>
     *
     * @param rightPen a {@link software.xdev.dynamicreports.design.base.style.DRDesignPen} object.
     */
    public void setRightPen(DRDesignPen rightPen) {
        this.rightPen = rightPen;
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

        DRDesignBorder o = (DRDesignBorder) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder().append(topPen, o.topPen).append(leftPen, o.leftPen).append(bottomPen, o.bottomPen).append(rightPen, o.rightPen);
        return equalsBuilder.isEquals();
    }
}
