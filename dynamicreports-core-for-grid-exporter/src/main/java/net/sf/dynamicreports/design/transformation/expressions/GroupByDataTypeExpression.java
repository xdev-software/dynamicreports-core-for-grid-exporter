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
package net.sf.dynamicreports.design.transformation.expressions;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>GroupByDataTypeExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupByDataTypeExpression extends AbstractSimpleExpression<String> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
    DRIDataType<?, ?> dataType;
    private DRIExpression<?> valueExpression;

    /**
     * <p>Constructor for GroupByDataTypeExpression.</p>
     *
     * @param valueExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param dataType        a {@link net.sf.dynamicreports.report.definition.datatype.DRIDataType} object.
     */
    public GroupByDataTypeExpression(DRIExpression<?> valueExpression, DRIDataType<?, ?> dataType) {
        this.valueExpression = valueExpression;
        this.dataType = dataType;
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(ReportParameters reportParameters) {
        return dataType.valueToString(valueExpression.getName(), reportParameters);
    }
}
