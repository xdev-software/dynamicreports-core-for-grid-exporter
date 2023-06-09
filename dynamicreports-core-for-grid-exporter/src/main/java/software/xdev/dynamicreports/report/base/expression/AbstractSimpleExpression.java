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
package software.xdev.dynamicreports.report.base.expression;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>Abstract AbstractSimpleExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractSimpleExpression<T> implements DRISimpleExpression<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;

    /**
     * <p>Constructor for AbstractSimpleExpression.</p>
     */
    protected AbstractSimpleExpression() {
        this.name = ReportUtils.generateUniqueName("simpleExpression");
    }

    /**
     * <p>Constructor for AbstractSimpleExpression.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    protected AbstractSimpleExpression(String name) {
        Validate.notEmpty(name, "name must not be empty");
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public Class<? super T> getValueClass() {
        return (Class<T>) ReportUtils.getGenericClass(this, 0);
    }
}
