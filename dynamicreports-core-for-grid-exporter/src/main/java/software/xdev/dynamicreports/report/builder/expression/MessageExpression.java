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

import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;

/**
 * <p>MessageExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MessageExpression extends AbstractSimpleExpression<String> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String key;
    private Object[] arguments;

    /**
     * <p>Constructor for MessageExpression.</p>
     *
     * @param key a {@link java.lang.String} object.
     */
    public MessageExpression(String key) {
        this(key, null);
    }

    /**
     * <p>Constructor for MessageExpression.</p>
     *
     * @param key       a {@link java.lang.String} object.
     * @param arguments an array of {@link java.lang.Object} objects.
     */
    public MessageExpression(String key, Object[] arguments) {
        this.key = key;
        this.arguments = arguments;
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(ReportParameters reportParameters) {
        return reportParameters.getMessage(key, arguments);
    }
}
