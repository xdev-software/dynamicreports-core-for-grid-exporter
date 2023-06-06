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
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>PropertyExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PropertyExpression implements DRIPropertyExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DRIExpression<String> valueExpression;

    /**
     * <p>Constructor for PropertyExpression.</p>
     *
     * @param name            a {@link java.lang.String} object.
     * @param valueExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public PropertyExpression(String name, DRIExpression<String> valueExpression) {
        Validate.notNull(name, "name must not be null");
        Validate.notNull(valueExpression, "valueExpression must not be null");
        this.name = name;
        this.valueExpression = valueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<String> getValueExpression() {
        return valueExpression;
    }

    /**
     * <p>Setter for the field <code>valueExpression</code>.</p>
     *
     * @param valueExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setValueExpression(DRIExpression<String> valueExpression) {
        this.valueExpression = valueExpression;
    }
}
