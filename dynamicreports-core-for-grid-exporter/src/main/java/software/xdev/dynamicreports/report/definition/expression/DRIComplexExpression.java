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
package software.xdev.dynamicreports.report.definition.expression;

import software.xdev.dynamicreports.report.definition.ReportParameters;

import java.util.List;

/**
 * A complex implementation of an expression.<br/> The difference between a simple and complex expression is that a complex expression allows registering additional fields or variables that are not
 * defined in the report and are needed for calculating the value.
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIComplexExpression<T> extends DRIExpression<T> {

    /**
     * <p>getExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIExpression<?>> getExpressions();

    /**
     * Evaluates the expression.
     *
     * @param values           the values of the registered expressions
     * @param reportParameters access to report fields, variables, parameters, expressions, and other report values
     * @return the result of the expression evaluation
     */
    public T evaluate(List<?> values, ReportParameters reportParameters);
}
