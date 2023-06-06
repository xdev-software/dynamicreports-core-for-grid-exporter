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
package software.xdev.dynamicreports.design.definition.component;

import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;

/**
 * <p>DRIDesignTextField interface.</p>
 *
 * @author Ricardo Mariaca, Jan Moxter
 *
 */
public interface DRIDesignTextField extends DRIDesignHyperLinkComponent {

    /**
     * <p>getPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getPattern();

    /**
     * <p>getPatternExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getPatternExpression();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    HorizontalTextAlignment getHorizontalTextAlignment();

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    DRIDesignExpression getValueExpression();

    /**
     * <p>isPrintRepeatedValues.</p>
     *
     * @return a boolean.
     */
    boolean isPrintRepeatedValues();

    /**
     * <p>getEvaluationTime.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.constant.EvaluationTime} object.
     */
    EvaluationTime getEvaluationTime();

    /**
     * <p>getEvaluationGroup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    DRIDesignGroup getEvaluationGroup();

    /**
     * <p>getMarkup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Markup} object.
     */
    Markup getMarkup();

    /**
     * <p>isStretchWithOverflow.</p>
     *
     * @return a boolean.
     * @deprecated Replaced by {@link #getTextAdjust()}.
     */
    @Deprecated
    boolean isStretchWithOverflow();

    /**
     * @return a {@link software.xdev.dynamicreports.report.constant.TextAdjust} object.
     */
    TextAdjust getTextAdjust();
}
