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
package net.sf.dynamicreports.design.transformation.expressions;

import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRICustomValues;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import java.util.List;

/**
 * <p>TocReferenceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TocReferenceExpression extends AbstractComplexExpression<String> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int level;
    private String expressionName;
    private boolean customId;
    private boolean isCustomValue;

    /**
     * <p>Constructor for TocReferenceExpression.</p>
     *
     * @param level                 a int.
     * @param expressionName        a {@link java.lang.String} object.
     * @param labelExpression       a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param anchorNameExpression  a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param customValueExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public TocReferenceExpression(int level, String expressionName, DRIExpression<?> labelExpression, DRIExpression<String> anchorNameExpression, DRIExpression<?> customValueExpression) {
        this.level = level;
        this.expressionName = expressionName;
        customId = anchorNameExpression != null;
        addExpression(labelExpression);
        if (anchorNameExpression != null) {
            addExpression(anchorNameExpression);
        }
        isCustomValue = customValueExpression != null;
        if (customValueExpression != null) {
            addExpression(customValueExpression);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String id;
        Object customValue = null;
        if (customId) {
            id = (String) values.get(1);
            if (isCustomValue) {
                customValue = values.get(2);
            }
        } else {
            id = expressionName + "_" + reportParameters.getReportRowNumber();
            if (isCustomValue) {
                customValue = values.get(1);
            }
        }
        DRICustomValues customValues = (DRICustomValues) reportParameters.getParameterValue(DRICustomValues.NAME);
        String text = String.valueOf(values.get(0));
        customValues.addTocHeading(level, id, text, customValue);
        return null;
    }
}
