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

import software.xdev.dynamicreports.report.base.style.DRPen;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.LineStyle;

import java.awt.Color;

/**
 * <p>PenBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PenBuilder extends AbstractBuilder<PenBuilder, DRPen> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for PenBuilder.</p>
     */
    protected PenBuilder() {
        super(new DRPen());
    }

    /**
     * <p>Constructor for PenBuilder.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     * @param lineStyle a {@link software.xdev.dynamicreports.report.constant.LineStyle} object.
     */
    protected PenBuilder(Float lineWidth, LineStyle lineStyle) {
        super(new DRPen(lineWidth, lineStyle));
    }

    /**
     * <p>setLineWidth.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineWidth(Float lineWidth) {
        getObject().setLineWidth(lineWidth);
        return this;
    }

    /**
     * <p>setLineStyle.</p>
     *
     * @param lineStyle a {@link software.xdev.dynamicreports.report.constant.LineStyle} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineStyle(LineStyle lineStyle) {
        getObject().setLineStyle(lineStyle);
        return this;
    }

    /**
     * <p>setLineColor.</p>
     *
     * @param lineColor a {@link java.awt.Color} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.PenBuilder} object.
     */
    public PenBuilder setLineColor(Color lineColor) {
        getObject().setLineColor(lineColor);
        return this;
    }

    /**
     * <p>getPen.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.style.DRPen} object.
     */
    public DRPen getPen() {
        return build();
    }
}
