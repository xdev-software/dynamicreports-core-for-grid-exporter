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
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRReportException;


public class AggregationSubtotalBuilder<T> extends SubtotalBuilder<AggregationSubtotalBuilder<T>, T>
	implements DRIValue<T>
{

	private final DRIExpression<?> expression;
	private final Calculation calculation;
	
	// column
	
	protected AggregationSubtotalBuilder(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		this(column.getColumn(), column, calculation);
	}
	
	// field
	
	protected AggregationSubtotalBuilder(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		this(field.build(), showInColumn, calculation);
	}
	
	// expression
	
	protected AggregationSubtotalBuilder(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		super(showInColumn);
		this.expression = expression;
		this.calculation = calculation;
		if(calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT))
		{
			this.setDataType(DataTypes.longType());
		}
		else if(calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION)
			|| calculation.equals(Calculation.VARIANCE))
		{
			this.setDataType(DataTypes.doubleType());
		}
		else if(expression instanceof DRIValueColumn)
		{
			this.setDataType(((DRIValueColumn<?>)expression).getComponent().getDataType());
			this.setPattern(((DRIValueColumn<?>)expression).getComponent().getPattern());
		}
		else if(expression instanceof DRIField)
		{
			this.setDataType(((DRIField<?>)expression).getDataType());
		}
	}
	
	private static Evaluation subtotalPositionToEvaluation(final SubtotalPosition position)
	{
		switch(position)
		{
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
	
	@Override
	protected void configure()
	{
		final DRVariable<T> subtotalVariable = new DRVariable<>(this.expression, this.calculation);
		final Evaluation resetType = subtotalPositionToEvaluation(this.getObject().getPosition());
		subtotalVariable.setResetType(resetType);
		subtotalVariable.setResetGroup(this.getObject().getGroup());
		this.setValueExpression(subtotalVariable);
		
		super.configure();
	}
	
	@Override
	public String getName()
	{
		return this.getSubtotal().getName();
	}
}
