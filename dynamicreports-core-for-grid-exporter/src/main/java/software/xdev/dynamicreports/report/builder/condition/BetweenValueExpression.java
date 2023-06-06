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

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRIValue;

/**
 * <p>BetweenValueExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class BetweenValueExpression<T extends Number> extends AbstractBetweenValueExpression<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BetweenValueExpression.</p>
     *
     * @param value a {@link software.xdev.dynamicreports.report.definition.DRIValue} object.
     * @param min   a {@link java.lang.Number} object.
     * @param max   a {@link java.lang.Number} object.
     */
    public BetweenValueExpression(DRIValue<T> value, Number min, Number max) {
        super(value, min, max);
    }

    /** {@inheritDoc} */
    @Override
    protected Boolean compare(Number actualValue, Number min, Number max) {
        return actualValue.doubleValue() >= min.doubleValue() && actualValue.doubleValue() <= max.doubleValue();
    }
}
