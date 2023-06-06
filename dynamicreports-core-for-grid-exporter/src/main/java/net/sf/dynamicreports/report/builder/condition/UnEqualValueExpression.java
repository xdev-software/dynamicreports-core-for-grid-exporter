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
package net.sf.dynamicreports.report.builder.condition;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIValue;

/**
 * <p>UnEqualValueExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class UnEqualValueExpression<T extends Number> extends AbstractValuesExpression<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for UnEqualValueExpression.</p>
     *
     * @param value   a {@link net.sf.dynamicreports.report.definition.DRIValue} object.
     * @param numbers a {@link java.lang.Number} object.
     */
    public UnEqualValueExpression(DRIValue<T> value, Number... numbers) {
        super(value, numbers);
    }

    /** {@inheritDoc} */
    @Override
    protected Boolean compare(Number actualValue, Number[] numbers) {
        for (Number number : numbers) {
            if (actualValue.doubleValue() == number.doubleValue()) {
                return false;
            }
        }
        return true;
    }
}
