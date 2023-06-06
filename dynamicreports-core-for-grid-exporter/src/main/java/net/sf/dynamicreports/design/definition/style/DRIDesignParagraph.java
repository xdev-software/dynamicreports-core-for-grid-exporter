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
package net.sf.dynamicreports.design.definition.style;

import net.sf.dynamicreports.report.constant.LineSpacing;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIDesignParagraph interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignParagraph extends Serializable {

    /**
     * <p>getLineSpacing.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.LineSpacing} object.
     */
    public LineSpacing getLineSpacing();

    /**
     * <p>getLineSpacingSize.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    public Float getLineSpacingSize();

    /**
     * <p>getFirstLineIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getFirstLineIndent();

    /**
     * <p>getLeftIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getLeftIndent();

    /**
     * <p>getRightIndent.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getRightIndent();

    /**
     * <p>getSpacingBefore.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSpacingBefore();

    /**
     * <p>getSpacingAfter.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getSpacingAfter();

    /**
     * <p>getTabStopWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getTabStopWidth();

    /**
     * <p>getTabStops.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignTabStop> getTabStops();
}
