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
package software.xdev.dynamicreports.report.builder.crosstab;

import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.definition.DRICrosstabValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>CrosstabVariableBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabVariableBuilder<T> extends AbstractBuilder<CrosstabVariableBuilder<T>, DRCrosstabVariable<T>> implements DRICrosstabValue<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param column      a {@link software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param calculation a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
        super(new DRCrosstabVariable<T>(column.build(), calculation));
    }

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param field       a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @param calculation a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(FieldBuilder<?> field, Calculation calculation) {
        super(new DRCrosstabVariable<T>(field.getField(), calculation));
    }

    /**
     * <p>Constructor for CrosstabVariableBuilder.</p>
     *
     * @param expression  a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param calculation a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected CrosstabVariableBuilder(DRIExpression<?> expression, Calculation calculation) {
        super(new DRCrosstabVariable<T>(expression, calculation));
    }

    /**
     * <p>setPercentageType.</p>
     *
     * @param percentageType a {@link software.xdev.dynamicreports.report.constant.CrosstabPercentageType} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder} object.
     */
    public CrosstabVariableBuilder<T> setPercentageType(CrosstabPercentageType percentageType) {
        getObject().setPercentageType(percentageType);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getObject().getName();
    }
}
