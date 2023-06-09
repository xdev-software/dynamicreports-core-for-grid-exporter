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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.base.style.DRDesignPen;
import software.xdev.dynamicreports.design.definition.component.DRIDesignRectangle;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignRectangle class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignRectangle extends DRDesignComponent implements DRIDesignRectangle {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer radius;
    private DRDesignPen pen;

    /**
     * <p>Constructor for DRDesignRectangle.</p>
     */
    public DRDesignRectangle() {
        super("rectangle");
    }

    /** {@inheritDoc} */
    @Override
    public Integer getRadius() {
        return radius;
    }

    /**
     * <p>Setter for the field <code>radius</code>.</p>
     *
     * @param radius a {@link java.lang.Integer} object.
     */
    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPen getPen() {
        return pen;
    }

    /**
     * <p>Setter for the field <code>pen</code>.</p>
     *
     * @param pen a {@link software.xdev.dynamicreports.design.base.style.DRDesignPen} object.
     */
    public void setPen(DRDesignPen pen) {
        this.pen = pen;
    }
}
