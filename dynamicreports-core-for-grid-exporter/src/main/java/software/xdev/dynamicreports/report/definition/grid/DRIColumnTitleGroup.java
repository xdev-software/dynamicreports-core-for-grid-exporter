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
package software.xdev.dynamicreports.report.definition.grid;

import java.util.List;

import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * <p>DRIColumnTitleGroup interface.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public interface DRIColumnTitleGroup extends DRIColumnGridComponent {

    /**
     * <p>getList.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.grid.DRIColumnGridList} object.
     */
    DRIColumnGridList getList();

    /**
     * <p>getTitleExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<?> getTitleExpression();

    /**
     * <p>getTitleStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getTitleStyle();

    /**
     * <p>getTitleWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTitleWidth();

    /**
     * <p>getTitleWidthType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    ComponentDimensionType getTitleWidthType();

    /**
     * <p>getTitleColumns.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTitleColumns();

    /**
     * <p>getTitleHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTitleHeight();

    /**
     * <p>getTitleHeightType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.ComponentDimensionType} object.
     */
    ComponentDimensionType getTitleHeightType();

    /**
     * <p>getTitleRows.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getTitleRows();

    /**
     * <p>getTitleStretchWithOverflow.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     * @deprecated replaced by {@link #getTitleTextAdjust()}
     */
    @Deprecated
    Boolean getTitleStretchWithOverflow();

    /**
     * <p>getTitleTextAdjust.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.TextAdjust} object.
     */
    TextAdjust getTitleTextAdjust();

    /**
     * <p>getTitlePropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIPropertyExpression> getTitlePropertyExpressions();
}
