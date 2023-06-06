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
 * <p>EqualExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class EqualExpression extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIValue<?> value;
    private Object[] values;

    @SafeVarargs
    /**
     * Constructor for EqualExpression.
     *
     * @param value a {@link software.xdev.dynamicreports.report.definition.DRIValue} object.
     * @param values a T object.
     * @param <T> a T object.
     */
    public <T> EqualExpression(DRIValue<T> value, T... values) {
        Validate.notNull(value, "value must not be null");
        Validate.noNullElements(values, "values must not contains null value");
        this.value = value;
        this.values = values;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
        Object actualValue = reportParameters.getValue(value);
        for (Object value : values) {
            if (value.equals(actualValue)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
