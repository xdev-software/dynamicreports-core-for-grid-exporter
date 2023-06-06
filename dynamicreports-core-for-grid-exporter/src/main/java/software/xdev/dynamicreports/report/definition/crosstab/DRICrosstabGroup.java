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
package software.xdev.dynamicreports.report.definition.crosstab;

import java.util.Comparator;
import java.util.List;

import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISystemExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;

/**
 * <p>DRICrosstabGroup interface.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public interface DRICrosstabGroup<T> extends DRISystemExpression<T> {

    /** {@inheritDoc} */
    @Override String getName();

    /**
     * <p>getHeaderPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getHeaderPattern();

    /**
     * <p>getHeaderHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    HorizontalTextAlignment getHeaderHorizontalTextAlignment();

    /**
     * <p>getHeaderValueFormatter.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     */
    DRIValueFormatter<?, ? super T> getHeaderValueFormatter();

    /**
     * <p>getHeaderStretchWithOverflow.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     * @deprecated replaced by {@link #getHeaderTextAdjust()}
     */
    @Deprecated
    Boolean getHeaderStretchWithOverflow();

    /**
     * <p>getHeaderTextAdjust.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.TextAdjust} object.
     */
    TextAdjust getHeaderTextAdjust();

    /**
     * <p>getHeaderHyperLink.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIHyperLink} object.
     */
    DRIHyperLink getHeaderHyperLink();

    /**
     * <p>getHeaderStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getHeaderStyle();

    /**
     * <p>getHeaderPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIPropertyExpression> getHeaderPropertyExpressions();

    /**
     * <p>getShowTotal.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getShowTotal();

    /**
     * <p>getTotalPosition.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.CrosstabTotalPosition} object.
     */
    CrosstabTotalPosition getTotalPosition();

    /**
     * <p>getTotalHeaderExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<?> getTotalHeaderExpression();

    /**
     * <p>getTotalHeaderStretchWithOverflow.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     * @deprecated replaced by {@link #getTotalHeaderTextAdjust()}
     */
    @Deprecated Boolean getTotalHeaderStretchWithOverflow();

    /**
     * <p>getTotalHeaderTextAdjust.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.TextAdjust} object.
     */
    TextAdjust getTotalHeaderTextAdjust();

    /**
     * <p>getTotalHeaderStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    DRIReportStyle getTotalHeaderStyle();

    /**
     * <p>getTotalHeaderPropertyExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    List<DRIPropertyExpression> getTotalHeaderPropertyExpressions();

    /**
     * <p>getExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<T> getExpression();

    /**
     * <p>getDataType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.datatype.DRIDataType} object.
     */
    DRIDataType<? super T, T> getDataType();

    /**
     * <p>getOrderByExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Comparable<?>> getOrderByExpression();

    /**
     * <p>getOrderType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.OrderType} object.
     */
    OrderType getOrderType();

    /**
     * <p>getComparatorExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<? extends Comparator<?>> getComparatorExpression();
}
