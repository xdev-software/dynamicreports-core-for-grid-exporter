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

import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;

import java.util.List;

/**
 * <p>DRDesignValueFormatter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignValueFormatter extends AbstractDesignComplexExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIValueFormatter<?, Object> valueFormatter;

    @SuppressWarnings("unchecked")
    /**
     * Constructor for DRDesignValueFormatter.
     *
     * @param valueFormatter a {@link software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     * @param valueExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRDesignValueFormatter(DRIValueFormatter<?, ?> valueFormatter, DRIDesignExpression valueExpression) {
        this.valueFormatter = (DRIValueFormatter<?, Object>) valueFormatter;
        addExpression(valueExpression);
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(List<?> values, ReportParameters reportParameters) {
        return valueFormatter.format(values.get(0), reportParameters);
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return valueFormatter.getValueClass();
    }
}
