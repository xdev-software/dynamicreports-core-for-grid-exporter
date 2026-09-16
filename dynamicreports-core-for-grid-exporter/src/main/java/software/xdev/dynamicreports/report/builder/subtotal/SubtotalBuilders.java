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

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class SubtotalBuilders
{
	
	// calculation
	public <T> AggregationSubtotalBuilder<T> aggregate(
		final ValueColumnBuilder<?, ?> subtotalColumn,
		final Calculation calculation)
	{
		return Subtotals.aggregate(subtotalColumn, calculation);
	}
	
	public <T> AggregationSubtotalBuilder<T> aggregate(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		return Subtotals.aggregate(fieldName, valueClass, showInColumn, calculation);
	}
	
	public <T> AggregationSubtotalBuilder<T> aggregate(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		return Subtotals.aggregate(field, showInColumn, calculation);
	}
	
	public <T> AggregationSubtotalBuilder<T> aggregate(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		return Subtotals.aggregate(expression, showInColumn, calculation);
	}
	
	// sum
	public <T extends Number> AggregationSubtotalBuilder<T> sum(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.sum(subtotalColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<T> sum(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.sum(fieldName, valueClass, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<T> sum(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.sum(field, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<T> sum(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.sum(expression, showInColumn);
	}
	
	// average
	public <T extends Number> AggregationSubtotalBuilder<Number> avg(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.avg(subtotalColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.avg(fieldName, valueClass, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.avg(field, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.avg(expression, showInColumn);
	}
	
	// count
	public AggregationSubtotalBuilder<Long> count(final ValueColumnBuilder<?, ?> subtotalColumn)
	{
		return Subtotals.count(subtotalColumn);
	}
	
	public AggregationSubtotalBuilder<Long> count(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.count(fieldName, valueClass, showInColumn);
	}
	
	public AggregationSubtotalBuilder<Long> count(final FieldBuilder<?> field, final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.count(field, showInColumn);
	}
	
	public AggregationSubtotalBuilder<Long> count(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.count(expression, showInColumn);
	}
	
	// distinct count
	public AggregationSubtotalBuilder<Long> distinctCount(final ValueColumnBuilder<?, ?> subtotalColumn)
	{
		return Subtotals.distinctCount(subtotalColumn);
	}
	
	public AggregationSubtotalBuilder<Long> distinctCount(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.distinctCount(fieldName, valueClass, showInColumn);
	}
	
	public AggregationSubtotalBuilder<Long> distinctCount(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.distinctCount(field, showInColumn);
	}
	
	public AggregationSubtotalBuilder<Long> distinctCount(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.distinctCount(expression, showInColumn);
	}
	
	// first
	public <T> AggregationSubtotalBuilder<T> first(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.first(subtotalColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> first(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.first(fieldName, valueClass, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> first(final FieldBuilder<T> field, final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.first(field, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> first(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.first(expression, showInColumn);
	}
	
	// highest
	public <T> AggregationSubtotalBuilder<T> max(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.max(subtotalColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> max(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.max(fieldName, valueClass, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> max(final FieldBuilder<T> field, final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.max(field, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> max(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.max(expression, showInColumn);
	}
	
	// lowest
	public <T> AggregationSubtotalBuilder<T> min(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.min(subtotalColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> min(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.min(fieldName, valueClass, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> min(final FieldBuilder<T> field, final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.min(field, showInColumn);
	}
	
	public <T> AggregationSubtotalBuilder<T> min(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.min(expression, showInColumn);
	}
	
	// standard deviation
	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.stdDev(subtotalColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.stdDev(fieldName, valueClass, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.stdDev(field, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.stdDev(expression, showInColumn);
	}
	
	// variance
	public <T extends Number> AggregationSubtotalBuilder<Number> var(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return Subtotals.var(subtotalColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> var(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.var(fieldName, valueClass, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> var(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.var(field, showInColumn);
	}
	
	public <T extends Number> AggregationSubtotalBuilder<Number> var(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.var(expression, showInColumn);
	}
	
	// custom
	public <T> CustomSubtotalBuilder<T> customValue(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.customValue(expression, showInColumn);
	}
	
	// percentage
	public PercentageSubtotalBuilder percentage(final ValueColumnBuilder<?, ? extends Number> subtotalColumn)
	{
		return Subtotals.percentage(subtotalColumn);
	}
	
	public PercentageSubtotalBuilder percentage(
		final String fieldName,
		final Class<? extends Number> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.percentage(fieldName, valueClass, showInColumn);
	}
	
	public PercentageSubtotalBuilder percentage(
		final FieldBuilder<? extends Number> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.percentage(field, showInColumn);
	}

    /*public PercentageSubtotalBuilder percentage(DRISimpleExpression<? extends Number> expression, ColumnBuilder<?,
    ?> showInColumn) {
        return Subtotals.percentage(expression, showInColumn);
    }*/
	
	// text
	public AggregationSubtotalBuilder<String> text(final String text, final ColumnBuilder<?, ?> showInColumn)
	{
		return Subtotals.text(text, showInColumn);
	}
}
