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

import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * <p>DRITextField interface.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 * 
 */
public interface DRITextField<T> extends DRIHyperLinkComponent {

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<T> getValueExpression();

    /**
     * <p>getPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPattern();

    /**
     * <p>getPatternExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    DRIExpression<String> getPatternExpression();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    HorizontalTextAlignment getHorizontalTextAlignment();

    /**
     * <p>getValueFormatter.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     */
    DRIValueFormatter<?, ? super T> getValueFormatter();

    /**
     * <p>getDataType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.datatype.DRIDataType} object.
     */
    DRIDataType<? super T, T> getDataType();

    /**
     * <p>getColumns.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getColumns();

    /**
     * <p>getRows.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    Integer getRows();

    /**
     * <p>getEvaluationTime.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Evaluation} object.
     */
    Evaluation getEvaluationTime();

    /**
     * <p>getEvaluationGroup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIGroup} object.
     */
    DRIGroup getEvaluationGroup();

    /**
     * <p>getMarkup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Markup} object.
     */
    Markup getMarkup();

    /**
     * <p>getStretchWithOverflow.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     * @deprecated replaced {@link #getTextAdjust()}
     */
    @Deprecated
    Boolean getStretchWithOverflow();

    /**
     * <p>getPrintRepeatedValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    Boolean getPrintRepeatedValues();

    /**
     * <p>getTextAdjust.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.TextAdjust} object.
     */
    TextAdjust getTextAdjust();
}
