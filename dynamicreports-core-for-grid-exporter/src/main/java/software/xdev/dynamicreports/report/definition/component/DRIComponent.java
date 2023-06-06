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

import software.xdev.dynamicreports.report.definition.DRITableOfContentsHeading;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRIComponent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIComponent extends Serializable {

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getStyle();

    /**
     * <p>getPrintWhenExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Boolean> getPrintWhenExpression();

    /**
     * <p>getRemoveLineWhenBlank.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getRemoveLineWhenBlank();

    /**
     * <p>getPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIPropertyExpression> getPropertyExpressions();

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRITableOfContentsHeading} object.
     */
    public DRITableOfContentsHeading getTableOfContentsHeading();
}
