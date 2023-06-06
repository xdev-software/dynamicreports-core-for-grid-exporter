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
package net.sf.dynamicreports.report.definition.style;

import net.sf.dynamicreports.report.constant.LineStyle;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>DRIPen interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIPen extends Serializable {

    /**
     * <p>getLineWidth.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    public Float getLineWidth();

    /**
     * <p>getLineStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.LineStyle} object.
     */
    public LineStyle getLineStyle();

    /**
     * <p>getLineColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getLineColor();
}
