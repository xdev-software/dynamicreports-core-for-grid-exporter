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
package software.xdev.dynamicreports.report.builder.component;

import software.xdev.dynamicreports.report.base.component.DRRectangle;
import software.xdev.dynamicreports.report.builder.style.PenBuilder;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>RectangleBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class RectangleBuilder extends DimensionComponentBuilder<RectangleBuilder, DRRectangle> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for RectangleBuilder.</p>
     */
    protected RectangleBuilder() {
        super(new DRRectangle());
    }

    /**
     * <p>setRadius.</p>
     *
     * @param radius a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.RectangleBuilder} object.
     */
    public RectangleBuilder setRadius(Integer radius) {
        getObject().setRadius(radius);
        return this;
    }

    /**
     * <p>setPen.</p>
     *
     * @param pen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.RectangleBuilder} object.
     */
    public RectangleBuilder setPen(PenBuilder pen) {
        if (pen != null) {
            getObject().setPen(pen.build());
        } else {
            getObject().setPen(null);
        }
        return this;
    }
}
