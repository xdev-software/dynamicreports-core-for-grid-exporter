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
package software.xdev.dynamicreports.design.transformation.expressions;

import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRICustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIComplexExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;

import java.util.List;

/**
 * <p>CrosstabExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabExpression<T> extends AbstractComplexExpression<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIExpression<T> expression;

    /**
     * <p>Constructor for CrosstabExpression.</p>
     *
     * @param crosstab   a {@link software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab} object.
     * @param expression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public CrosstabExpression(DRICrosstab crosstab, DRIExpression<T> expression) throws DRException {
        this.expression = expression;
        if (expression instanceof DRIComplexExpression) {
            for (DRIExpression<?> express : ((DRIComplexExpression<?>) expression).getExpressions()) {
                addExpression(express);
            }
        }
        for (DRICrosstabVariable<?> variable : crosstab.getVariables()) {
            addExpression(variable);
        }
        for (DRICrosstabMeasure<?> measure : crosstab.getMeasures()) {
            if (measure.getExpression() instanceof DRICrosstabVariable<?>) {
                addExpression(measure.getExpression());
            }
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public T evaluate(List<?> values, ReportParameters reportParameters) {
        DRICustomValues customValues = (DRICustomValues) reportParameters.getParameterValue(DRICustomValues.NAME);
        for (int i = 0; i < getExpressions().size(); i++) {
            customValues.setSystemValue(getExpressions().get(i).getName(), values.get(i));
        }
        if (expression instanceof DRIComplexExpression) {
            DRIComplexExpression<?> express = (DRIComplexExpression<?>) expression;
            return (T) express.evaluate(values, reportParameters);
        } else {
            return reportParameters.getValue(expression.getName());
        }
    }

    /** {@inheritDoc} */
    @SuppressWarnings( {"rawtypes", "unchecked"})
    @Override
    public Class getValueClass() {
        return expression.getValueClass();
    }
}
