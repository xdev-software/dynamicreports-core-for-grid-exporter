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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public final class Subtotals
{
	private Subtotals()
	{
	}
	
	// calculation
	public static <T> AggregationSubtotalBuilder<T> aggregate(
		final ValueColumnBuilder<?, ?> subtotalColumn,
		final Calculation calculation)
	{
		Validate.notNull(subtotalColumn, "subtotalColumn must not be null");
		return new AggregationSubtotalBuilder<>(subtotalColumn, calculation);
	}
	
	public static <T> AggregationSubtotalBuilder<T> aggregate(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		return aggregate(DynamicReports.field(fieldName, valueClass), showInColumn, calculation);
	}
	
	public static <T> AggregationSubtotalBuilder<T> aggregate(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new AggregationSubtotalBuilder<>(field, showInColumn, calculation);
	}
	
	public static <T> AggregationSubtotalBuilder<T> aggregate(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn,
		final Calculation calculation)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new AggregationSubtotalBuilder<>(expression, showInColumn, calculation);
	}
	
	// sum
	public static <T extends Number> AggregationSubtotalBuilder<T> sum(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.SUM);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<T> sum(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.SUM);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<T> sum(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.SUM);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<T> sum(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.SUM);
	}
	
	// average
	public static <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.AVERAGE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.AVERAGE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.AVERAGE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> avg(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.AVERAGE);
	}
	
	// count
	public static AggregationSubtotalBuilder<Long> count(final ValueColumnBuilder<?, ?> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> count(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> count(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> count(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.COUNT);
	}
	
	// distinct count
	public static AggregationSubtotalBuilder<Long> distinctCount(final ValueColumnBuilder<?, ?> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.DISTINCT_COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> distinctCount(
		final String fieldName,
		final Class<?> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.DISTINCT_COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> distinctCount(
		final FieldBuilder<?> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.DISTINCT_COUNT);
	}
	
	public static AggregationSubtotalBuilder<Long> distinctCount(
		final DRIExpression<?> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.DISTINCT_COUNT);
	}
	
	// first
	public static <T> AggregationSubtotalBuilder<T> first(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.FIRST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> first(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.FIRST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> first(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.FIRST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> first(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.FIRST);
	}
	
	// highest
	public static <T> AggregationSubtotalBuilder<T> max(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.HIGHEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> max(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.HIGHEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> max(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.HIGHEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> max(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.HIGHEST);
	}
	
	// lowest
	public static <T> AggregationSubtotalBuilder<T> min(final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.LOWEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> min(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.LOWEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> min(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.LOWEST);
	}
	
	public static <T> AggregationSubtotalBuilder<T> min(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.LOWEST);
	}
	
	// standard deviation
	public static <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.STANDARD_DEVIATION);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.STANDARD_DEVIATION);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.STANDARD_DEVIATION);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> stdDev(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.STANDARD_DEVIATION);
	}
	
	// variance
	public static <T extends Number> AggregationSubtotalBuilder<Number> var(
		final ValueColumnBuilder<?, T> subtotalColumn)
	{
		return aggregate(subtotalColumn, Calculation.VARIANCE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> var(
		final String fieldName,
		final Class<T> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(fieldName, valueClass, showInColumn, Calculation.VARIANCE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> var(
		final FieldBuilder<T> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(field, showInColumn, Calculation.VARIANCE);
	}
	
	public static <T extends Number> AggregationSubtotalBuilder<Number> var(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(expression, showInColumn, Calculation.VARIANCE);
	}
	
	// custom
	public static <T> CustomSubtotalBuilder<T> customValue(
		final DRIExpression<T> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new CustomSubtotalBuilder<>(expression, showInColumn);
	}
	
	// percentage
	public static PercentageSubtotalBuilder percentage(final ValueColumnBuilder<?, ? extends Number> subtotalColumn)
	{
		Validate.notNull(subtotalColumn, "subtotalColumn must not be null");
		return new PercentageSubtotalBuilder(subtotalColumn);
	}
	
	public static PercentageSubtotalBuilder percentage(
		final String fieldName,
		final Class<? extends Number> valueClass,
		final ColumnBuilder<?, ?> showInColumn)
	{
		return percentage(DynamicReports.<Number>field(fieldName, valueClass), showInColumn);
	}
	
	public static PercentageSubtotalBuilder percentage(
		final FieldBuilder<? extends Number> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		return new PercentageSubtotalBuilder(field, showInColumn);
	}

    /*public static PercentageSubtotalBuilder percentage(DRISimpleExpression<? extends Number> expression,
    ColumnBuilder<?, ?> showInColumn) {
        Validate.notNull(showInColumn, "showInColumn must not be null");
        return new PercentageSubtotalBuilder(expression, showInColumn);
    }*/
	
	// text
	public static AggregationSubtotalBuilder<String> text(final String text, final ColumnBuilder<?, ?> showInColumn)
	{
		return aggregate(Expressions.text(text), showInColumn, Calculation.NOTHING);
	}
}
