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
package software.xdev.dynamicreports.report.builder.condition;

import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import org.apache.commons.lang3.Validate;

/**
 * <p>Abstract AbstractValuesExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractValuesExpression<T extends Number> extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIValue<T> value;
    private Number[] numbers;

    /**
     * <p>Constructor for AbstractValuesExpression.</p>
     *
     * @param value   a {@link software.xdev.dynamicreports.report.definition.DRIValue} object.
     * @param numbers a {@link java.lang.Number} object.
     */
    public AbstractValuesExpression(DRIValue<T> value, Number... numbers) {
        Validate.notNull(value, "value must not be null");
        Validate.noNullElements(numbers, "numbers must not contains null number");
        this.value = value;
        this.numbers = numbers;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
        Number actualValue = reportParameters.getValue(value);
        if (actualValue != null) {
            return compare(actualValue, numbers);
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }

    /**
     * <p>compare.</p>
     *
     * @param actualValue a {@link java.lang.Number} object.
     * @param numbers     an array of {@link java.lang.Number} objects.
     * @return a {@link java.lang.Boolean} object.
     */
    protected abstract Boolean compare(Number actualValue, Number[] numbers);
}
