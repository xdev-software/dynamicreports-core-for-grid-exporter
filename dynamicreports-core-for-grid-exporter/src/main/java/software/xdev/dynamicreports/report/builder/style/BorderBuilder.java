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
package software.xdev.dynamicreports.report.builder.style;

import software.xdev.dynamicreports.report.base.style.DRBorder;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>BorderBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class BorderBuilder extends AbstractBuilder<BorderBuilder, DRBorder> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BorderBuilder.</p>
     */
    protected BorderBuilder() {
        super(new DRBorder());
    }

    /**
     * <p>Constructor for BorderBuilder.</p>
     *
     * @param pen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     */
    protected BorderBuilder(PenBuilder pen) {
        super(new DRBorder(pen.build()));
    }

    /**
     * <p>setTopPen.</p>
     *
     * @param topPen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.BorderBuilder} object.
     */
    public BorderBuilder setTopPen(PenBuilder topPen) {
        if (topPen != null) {
            getObject().setTopPen(topPen.build());
        } else {
            getObject().setTopPen(null);
        }
        return this;
    }

    /**
     * <p>setLeftPen.</p>
     *
     * @param leftPen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.BorderBuilder} object.
     */
    public BorderBuilder setLeftPen(PenBuilder leftPen) {
        if (leftPen != null) {
            getObject().setLeftPen(leftPen.build());
        } else {
            getObject().setLeftPen(null);
        }
        return this;
    }

    /**
     * <p>setBottomPen.</p>
     *
     * @param bottomPen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.BorderBuilder} object.
     */
    public BorderBuilder setBottomPen(PenBuilder bottomPen) {
        if (bottomPen != null) {
            getObject().setBottomPen(bottomPen.build());
        } else {
            getObject().setBottomPen(null);
        }
        return this;
    }

    /**
     * <p>setRightPen.</p>
     *
     * @param rightPen a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.BorderBuilder} object.
     */
    public BorderBuilder setRightPen(PenBuilder rightPen) {
        if (rightPen != null) {
            getObject().setRightPen(rightPen.build());
        } else {
            getObject().setRightPen(null);
        }
        return this;
    }

    /**
     * <p>getBorder.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.style.DRBorder} object.
     */
    public DRBorder getBorder() {
        return build();
    }
}
