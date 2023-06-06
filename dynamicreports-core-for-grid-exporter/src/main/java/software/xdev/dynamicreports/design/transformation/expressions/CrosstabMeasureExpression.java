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
package software.xdev.dynamicreports.design.transformation.expressions;

import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

import java.util.List;

/**
 * <p>CrosstabMeasureExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabMeasureExpression extends AbstractComplexExpression<Double> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabMeasureExpression.</p>
     *
     * @param expression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public CrosstabMeasureExpression(DRIExpression<?> expression) {
        addExpression(expression);
    }

    /** {@inheritDoc} */
    @Override
    public Double evaluate(List<?> values, ReportParameters reportParameters) {
        Number value = (Number) values.get(0);
        if (value != null) {
            return value.doubleValue();
        }
        return null;
    }
}
