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

import software.xdev.dynamicreports.report.base.component.DREllipse;
import software.xdev.dynamicreports.report.builder.style.PenBuilder;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>EllipseBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class EllipseBuilder extends DimensionComponentBuilder<EllipseBuilder, DREllipse> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for EllipseBuilder.</p>
     */
    protected EllipseBuilder() {
        super(new DREllipse());
    }

    /**
     * <p>setPen.</p>
     *
     * @param pen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.EllipseBuilder} object.
     */
    public EllipseBuilder setPen(PenBuilder pen) {
        if (pen != null) {
            getObject().setPen(pen.build());
        } else {
            getObject().setPen(null);
        }
        return this;
    }
}
