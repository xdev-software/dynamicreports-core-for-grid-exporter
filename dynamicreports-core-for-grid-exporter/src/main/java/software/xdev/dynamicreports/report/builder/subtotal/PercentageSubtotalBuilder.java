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

import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.builder.expression.PercentageExpression;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.PercentageTotalType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRReportException;

/**
 * <p>PercentageSubtotalBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PercentageSubtotalBuilder extends BaseSubtotalBuilder<PercentageSubtotalBuilder, Double> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRIExpression<? extends Number> expression;
    private PercentageTotalType totalType;
    private DRGroup totalGroup;

    // column

    /**
     * <p>Constructor for PercentageSubtotalBuilder.</p>
     *
     * @param column a {@link software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected PercentageSubtotalBuilder(ValueColumnBuilder<?, ? extends Number> column) {
        this(column.build(), column);
    }

    // field

    /**
     * <p>Constructor for PercentageSubtotalBuilder.</p>
     *
     * @param field        a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @param showInColumn a {@link software.xdev.dynamicreports.report.builder.column.ColumnBuilder} object.
     */
    protected PercentageSubtotalBuilder(FieldBuilder<? extends Number> field, ColumnBuilder<?, ?> showInColumn) {
        this(field.getField(), showInColumn);
    }

    // expression

    /**
     * <p>Constructor for PercentageSubtotalBuilder.</p>
     *
     * @param expression   a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param showInColumn a {@link software.xdev.dynamicreports.report.builder.column.ColumnBuilder} object.
     */
    protected PercentageSubtotalBuilder(DRIExpression<? extends Number> expression, ColumnBuilder<?, ?> showInColumn) {
        super(showInColumn);
        this.expression = expression;
    }

    /**
     * <p>Setter for the field <code>totalType</code>.</p>
     *
     * @param totalType a {@link software.xdev.dynamicreports.report.constant.PercentageTotalType} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder} object.
     */
    public PercentageSubtotalBuilder setTotalType(PercentageTotalType totalType) {
        this.totalType = totalType;
        return this;
    }

    /**
     * <p>Setter for the field <code>totalGroup</code>.</p>
     *
     * @param totalGroup a {@link software.xdev.dynamicreports.report.builder.group.GroupBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder} object.
     */
    public PercentageSubtotalBuilder setTotalGroup(GroupBuilder<?> totalGroup) {
        if (totalGroup != null) {
            this.totalGroup = totalGroup.getGroup();
            setTotalType(PercentageTotalType.GROUP);
        } else {
            this.totalGroup = null;
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        if (getObject().getValueField().getDataType() == null) {
            getObject().getValueField().setDataType(DataTypes.percentageType());
        }

        DRVariable<Number> actualExpression = new DRVariable<Number>(expression, Calculation.SUM);
        actualExpression.setResetType(Evaluation.GROUP);
        actualExpression.setResetGroup(getObject().getGroup());

        DRVariable<Number> totalExpression = new DRVariable<Number>(expression, Calculation.SUM);
        if (totalType != null) {
            switch (totalType) {
                case REPORT:
                    totalExpression.setResetType(Evaluation.REPORT);
                    break;
                case GROUP:
                    totalExpression.setResetType(Evaluation.GROUP);
                    break;
                case FIRST_GROUP:
                    totalExpression.setResetType(Evaluation.FIRST_GROUP);
                    break;
                case LAST_GROUP:
                    totalExpression.setResetType(Evaluation.LAST_GROUP);
                    break;
                default:
                    throw new DRReportException("Percentage total type " + totalType.name() + " not supported.");
            }
        } else {
            totalExpression.setResetType(Evaluation.BEFORE_GROUP);
            totalGroup = getObject().getGroup();
        }
        totalExpression.setResetGroup(totalGroup);

        setValueExpression(new PercentageExpression(actualExpression, totalExpression));

        super.configure();
    }
}
