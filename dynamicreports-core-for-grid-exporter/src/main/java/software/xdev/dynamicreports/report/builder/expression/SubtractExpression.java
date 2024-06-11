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
package software.xdev.dynamicreports.report.builder.expression;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

import java.math.BigDecimal;

/**
 * <p>SubtractExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class SubtractExpression extends CalculationExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    @SafeVarargs
    /**
     * <p>Constructor for SubtractExpression.</p>
     *
     * @param expressions a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public SubtractExpression(DRIExpression<? extends Number>... expressions) {
        super(expressions);
    }

    /** {@inheritDoc} */
    @Override
    protected BigDecimal calculate(BigDecimal value1, BigDecimal value2) {
        return value1.subtract(value2);
    }
}