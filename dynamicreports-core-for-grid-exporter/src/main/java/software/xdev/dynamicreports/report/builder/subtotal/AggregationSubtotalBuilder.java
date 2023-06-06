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
package software.xdev.dynamicreports.report.builder.subtotal;

import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRReportException;

/**
 * <p>AggregationSubtotalBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class AggregationSubtotalBuilder<T> extends SubtotalBuilder<AggregationSubtotalBuilder<T>, T> implements DRIValue<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIExpression<?> expression;
    private Calculation calculation;

    // column

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param column      a {@link software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @param calculation a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
        this(column.getColumn(), column, calculation);
    }

    // field

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param field        a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @param showInColumn a {@link software.xdev.dynamicreports.report.builder.column.ColumnBuilder} object.
     * @param calculation  a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(FieldBuilder<?> field, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
        this(field.build(), showInColumn, calculation);
    }

    // expression

    /**
     * <p>Constructor for AggregationSubtotalBuilder.</p>
     *
     * @param expression   a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param showInColumn a {@link software.xdev.dynamicreports.report.builder.column.ColumnBuilder} object.
     * @param calculation  a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    protected AggregationSubtotalBuilder(DRIExpression<?> expression, ColumnBuilder<?, ?> showInColumn, Calculation calculation) {
        super(showInColumn);
        this.expression = expression;
        this.calculation = calculation;
        if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
            setDataType(DataTypes.longType());
        } else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) || calculation.equals(Calculation.VARIANCE)) {
            setDataType(DataTypes.doubleType());
        } else if (expression instanceof DRIValueColumn) {
            setDataType(((DRIValueColumn<?>) expression).getComponent().getDataType());
            setPattern(((DRIValueColumn<?>) expression).getComponent().getPattern());
        } else if (expression instanceof DRIField) {
            setDataType(((DRIField<?>) expression).getDataType());
        }
    }

    private static Evaluation subtotalPositionToEvaluation(SubtotalPosition position) {
        switch (position) {
            case PAGE_HEADER:
            case PAGE_FOOTER:
                return Evaluation.PAGE;
            case COLUMN_HEADER:
            case COLUMN_FOOTER:
                return Evaluation.COLUMN;
            case GROUP_HEADER:
            case GROUP_FOOTER:
                return Evaluation.GROUP;
            case FIRST_GROUP_HEADER:
            case FIRST_GROUP_FOOTER:
                return Evaluation.FIRST_GROUP;
            case LAST_GROUP_HEADER:
            case LAST_GROUP_FOOTER:
                return Evaluation.LAST_GROUP;
            case TITLE:
            case LAST_PAGE_FOOTER:
            case SUMMARY:
                return Evaluation.REPORT;
            default:
                throw new DRReportException("Subtotal position " + position.name() + " not supported");
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        DRVariable<T> subtotalVariable = new DRVariable<T>(expression, calculation);
        Evaluation resetType = subtotalPositionToEvaluation(getObject().getPosition());
        subtotalVariable.setResetType(resetType);
        subtotalVariable.setResetGroup(getObject().getGroup());
        setValueExpression(subtotalVariable);

        super.configure();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return getSubtotal().getName();
    }
}
