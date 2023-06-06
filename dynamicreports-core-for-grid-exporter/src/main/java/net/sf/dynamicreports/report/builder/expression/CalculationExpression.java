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
package net.sf.dynamicreports.report.builder.expression;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Ricardo Mariaca
 */
abstract class CalculationExpression extends AbstractComplexExpression<BigDecimal> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    @SafeVarargs
    /**
     * <p>Constructor for CalculationExpression.</p>
     *
     * @param expressions a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CalculationExpression(DRIExpression<? extends Number>... expressions) {
        Validate.notNull(expressions, "expressions must not be null");
        Validate.noNullElements(expressions, "expressions must not contains null expression");
        for (DRIExpression<? extends Number> expression : expressions) {
            addExpression(expression);
        }
    }

    /** {@inheritDoc} */
    @Override
    public BigDecimal evaluate(List<?> values, ReportParameters reportParameters) {
        BigDecimal result = null;
        for (Object value : values) {
            BigDecimal bigDecimalValue;
            if (value instanceof BigDecimal) {
                bigDecimalValue = (BigDecimal) value;
            } else {
                bigDecimalValue = new BigDecimal(((Number) value).doubleValue());
            }
            if (result == null) {
                result = bigDecimalValue;
            } else {
                result = calculate(result, bigDecimalValue);
            }
        }
        return result;
    }

    /**
     * <p>calculate.</p>
     *
     * @param value1 a {@link java.math.BigDecimal} object.
     * @param value2 a {@link java.math.BigDecimal} object.
     * @return a {@link java.math.BigDecimal} object.
     */
    protected abstract BigDecimal calculate(BigDecimal value1, BigDecimal value2);
}
