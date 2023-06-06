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

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIJasperExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>JasperExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperExpression<T> implements DRIJasperExpression<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private String expression;
    private Class<? super T> valueClass;

    /**
     * <p>Constructor for JasperExpression.</p>
     *
     * @param expression a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    protected JasperExpression(String expression, Class<? super T> valueClass) {
        Validate.notNull(expression, "expression must not be null");
        Validate.notNull(valueClass, "valueClass must not be null");
        this.expression = expression;
        this.valueClass = valueClass;
        this.name = ReportUtils.generateUniqueName("jasperExpression");
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public String getExpression() {
        return expression;
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super T> getValueClass() {
        return valueClass;
    }
}
