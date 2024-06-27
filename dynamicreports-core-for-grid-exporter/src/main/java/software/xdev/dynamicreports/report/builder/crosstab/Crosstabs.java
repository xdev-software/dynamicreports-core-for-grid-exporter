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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public final class Crosstabs
{
	private Crosstabs()
	{
	}
	
	public static CrosstabBuilder crosstab()
	{
		return new CrosstabBuilder();
	}
	
	// column group
	
	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(final ValueColumnBuilder<?, T> column)
	{
		return new CrosstabColumnGroupBuilder<>(column);
	}
	
	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(final FieldBuilder<T> field)
	{
		return new CrosstabColumnGroupBuilder<>(field);
	}
	
	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(final String fieldName, final Class<T> valueClass)
	{
		return new CrosstabColumnGroupBuilder<>(DynamicReports.<T>field(fieldName, valueClass));
	}
	
	public static <T> CrosstabColumnGroupBuilder<T> columnGroup(final DRIExpression<T> expression)
	{
		return new CrosstabColumnGroupBuilder<>(expression);
	}
	
	// row group
	
	public static <T> CrosstabRowGroupBuilder<T> rowGroup(final ValueColumnBuilder<?, T> column)
	{
		return new CrosstabRowGroupBuilder<>(column);
	}
	
	public static <T> CrosstabRowGroupBuilder<T> rowGroup(final FieldBuilder<T> field)
	{
		return new CrosstabRowGroupBuilder<>(field);
	}
	
	public static <T> CrosstabRowGroupBuilder<T> rowGroup(final String fieldName, final Class<T> valueClass)
	{
		return new CrosstabRowGroupBuilder<>(DynamicReports.<T>field(fieldName, valueClass));
	}
	
	public static <T> CrosstabRowGroupBuilder<T> rowGroup(final DRIExpression<T> expression)
	{
		return new CrosstabRowGroupBuilder<>(expression);
	}
	
	// variable
	
	public static <T> CrosstabVariableBuilder<T> variable(
		final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		Validate.notNull(column, "column must not be null");
		return new CrosstabVariableBuilder<>(column, calculation);
	}
	
	public static <T> CrosstabVariableBuilder<T> variable(final FieldBuilder<T> field, final Calculation calculation)
	{
		Validate.notNull(field, "field must not be null");
		return new CrosstabVariableBuilder<>(field, calculation);
	}
	
	public static <T> CrosstabVariableBuilder<T> variable(
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return new CrosstabVariableBuilder<>(DynamicReports.field(fieldName, valueClass), calculation);
	}
	
	public static <T> CrosstabVariableBuilder<T> variable(
		final DRIExpression<?> expression,
		final Calculation calculation)
	{
		return new CrosstabVariableBuilder<>(expression, calculation);
	}
	
	// measure
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		Validate.notNull(column, "column must not be null");
		return new CrosstabMeasureBuilder<>(column, calculation);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		final CrosstabMeasureBuilder<T> measure = measure(column, calculation);
		return measure.setTitle(title);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(final FieldBuilder<T> field, final Calculation calculation)
	{
		Validate.notNull(field, "field must not be null");
		return new CrosstabMeasureBuilder<>(field, calculation);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final FieldBuilder<T> field,
		final Calculation calculation)
	{
		return measure(field, calculation).setTitle(title);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return new CrosstabMeasureBuilder<>(DynamicReports.field(fieldName, valueClass), calculation);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		final CrosstabMeasureBuilder<T> measure = measure(fieldName, valueClass, calculation);
		return measure.setTitle(title);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final DRIExpression<?> expression,
		final Calculation calculation)
	{
		return new CrosstabMeasureBuilder<>(expression, calculation);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final DRIExpression<?> expression,
		final Calculation calculation)
	{
		final CrosstabMeasureBuilder<T> measure = measure(expression, calculation);
		return measure.setTitle(title);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(final DRIExpression<?> expression)
	{
		return new CrosstabMeasureBuilder<>(expression);
	}
	
	public static <T> CrosstabMeasureBuilder<T> measure(final String title, final DRIExpression<?> expression)
	{
		final CrosstabMeasureBuilder<T> measure = measure(expression);
		return measure.setTitle(title);
	}
}
