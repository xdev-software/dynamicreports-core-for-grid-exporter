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
import software.xdev.dynamicreports.report.constant.LineStyle;
import software.xdev.dynamicreports.report.definition.style.DRIPen;
import org.apache.commons.lang3.Validate;

import java.awt.Color;

/**
 * <p>DRPen class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRPen implements DRIPen {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Float lineWidth;
    private LineStyle lineStyle;
    private Color lineColor;

    /**
     * <p>Constructor for DRPen.</p>
     */
    public DRPen() {
    }

    /**
     * <p>Constructor for DRPen.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     * @param lineStyle a {@link software.xdev.dynamicreports.report.constant.LineStyle} object.
     */
    public DRPen(Float lineWidth, LineStyle lineStyle) {
        this.setLineWidth(lineWidth);
        this.lineStyle = lineStyle;
    }

    /** {@inheritDoc} */
    @Override
    public Float getLineWidth() {
        return lineWidth;
    }

    /**
     * <p>Setter for the field <code>lineWidth</code>.</p>
     *
     * @param lineWidth a {@link java.lang.Float} object.
     */
    public void setLineWidth(Float lineWidth) {
        if (lineWidth != null) {
            Validate.isTrue(lineWidth >= 0, "lineWidth must be >= 0");
        }
        this.lineWidth = lineWidth;
    }

    /** {@inheritDoc} */
    @Override
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * <p>Setter for the field <code>lineStyle</code>.</p>
     *
     * @param lineStyle a {@link software.xdev.dynamicreports.report.constant.LineStyle} object.
     */
    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    /** {@inheritDoc} */
    @Override
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * <p>Setter for the field <code>lineColor</code>.</p>
     *
     * @param lineColor a {@link java.awt.Color} object.
     */
    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
}
