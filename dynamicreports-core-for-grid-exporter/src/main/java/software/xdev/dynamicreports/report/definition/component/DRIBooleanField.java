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
package software.xdev.dynamicreports.report.definition.component;

import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRIBooleanField interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIBooleanField extends DRIHyperLinkComponent {

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Boolean> getValueExpression();

    /**
     * <p>getComponentType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.BooleanComponentType} object.
     */
    public BooleanComponentType getComponentType();

    /**
     * <p>getEmptyWhenNullValue.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getEmptyWhenNullValue();

    /**
     * <p>getImageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getImageWidth();

    /**
     * <p>getImageHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getImageHeight();

    /**
     * <p>getHorizontalImageAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalImageAlignment} object.
     */
    public HorizontalImageAlignment getHorizontalImageAlignment();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    public HorizontalTextAlignment getHorizontalTextAlignment();
}
