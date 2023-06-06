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
package net.sf.dynamicreports.design.definition.component;

import net.sf.dynamicreports.design.definition.DRIDesignGroup;
import net.sf.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.StretchType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIDesignComponent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignComponent extends Serializable {
    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName();

    /**
     * <p>getUniqueName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUniqueName();

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.style.DRIDesignStyle} object.
     */
    public DRIDesignStyle getStyle();

    /**
     * <p>getX.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getX();

    /**
     * <p>getY.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getY();

    /**
     * <p>getWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHeight();

    /**
     * <p>getPrintWhenExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getPrintWhenExpression();

    /**
     * <p>isRemoveLineWhenBlank.</p>
     *
     * @return a boolean.
     */
    public boolean isRemoveLineWhenBlank();

    /**
     * <p>getPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignPropertyExpression> getPropertyExpressions();

    /**
     * <p>getPositionType.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.ComponentPositionType} object.
     */
    public ComponentPositionType getPositionType();

    /**
     * <p>getStretchType.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.constant.StretchType} object.
     */
    public StretchType getStretchType();

    /**
     * <p>isPrintInFirstWholeBand.</p>
     *
     * @return a boolean.
     */
    public boolean isPrintInFirstWholeBand();

    /**
     * <p>isPrintWhenDetailOverflows.</p>
     *
     * @return a boolean.
     */
    public boolean isPrintWhenDetailOverflows();

    /**
     * <p>getPrintWhenGroupChanges.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    public DRIDesignGroup getPrintWhenGroupChanges();

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.DRIDesignTableOfContentsHeading} object.
     */
    public DRIDesignTableOfContentsHeading getTableOfContentsHeading();
}
