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
package software.xdev.dynamicreports.design.base.expression;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;

/**
 * <p>DRDesignSimpleExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignSimpleExpression extends AbstractDesignSimpleExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRISimpleExpression<?> simpleExpression;
    private String parameterName;

    /**
     * <p>Constructor for DRDesignSimpleExpression.</p>
     *
     * @param simpleExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression} object.
     * @param parameterName    a {@link java.lang.String} object.
     */
    public DRDesignSimpleExpression(DRISimpleExpression<?> simpleExpression, String parameterName) {
        this.simpleExpression = simpleExpression;
        this.parameterName = parameterName;
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(ReportParameters reportParameters) {
        return simpleExpression.evaluate(reportParameters);
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return simpleExpression.getValueClass();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return simpleExpression.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getParameterName() {
        return parameterName;
    }
}
