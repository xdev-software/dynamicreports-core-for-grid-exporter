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

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CrosstabBuilders
{
	
	public CrosstabBuilder crosstab()
	{
		return Crosstabs.crosstab();
	}
	
	// column group
	
	public <T> CrosstabColumnGroupBuilder<T> columnGroup(final ValueColumnBuilder<?, T> column)
	{
		return Crosstabs.columnGroup(column);
	}
	
	public <T> CrosstabColumnGroupBuilder<T> columnGroup(final FieldBuilder<T> field)
	{
		return Crosstabs.columnGroup(field);
	}
	
	public <T> CrosstabColumnGroupBuilder<T> columnGroup(final String fieldName, final Class<T> valueClass)
	{
		return Crosstabs.columnGroup(fieldName, valueClass);
	}
	
	public <T> CrosstabColumnGroupBuilder<T> columnGroup(final DRIExpression<T> expression)
	{
		return Crosstabs.columnGroup(expression);
	}
	
	// row group
	
	public <T> CrosstabRowGroupBuilder<T> rowGroup(final ValueColumnBuilder<?, T> column)
	{
		return Crosstabs.rowGroup(column);
	}
	
	public <T> CrosstabRowGroupBuilder<T> rowGroup(final FieldBuilder<T> field)
	{
		return Crosstabs.rowGroup(field);
	}
	
	public <T> CrosstabRowGroupBuilder<T> rowGroup(final String fieldName, final Class<T> valueClass)
	{
		return Crosstabs.rowGroup(fieldName, valueClass);
	}
	
	public <T> CrosstabRowGroupBuilder<T> rowGroup(final DRIExpression<T> expression)
	{
		return Crosstabs.rowGroup(expression);
	}
	
	// variable
	
	public <T> CrosstabVariableBuilder<T> variable(
		final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		return Crosstabs.variable(column, calculation);
	}
	
	public <T> CrosstabVariableBuilder<T> variable(final FieldBuilder<T> field, final Calculation calculation)
	{
		return Crosstabs.variable(field, calculation);
	}
	
	public <T> CrosstabVariableBuilder<T> variable(
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return Crosstabs.variable(fieldName, valueClass, calculation);
	}
	
	public <T> CrosstabVariableBuilder<T> variable(final DRIExpression<?> expression, final Calculation calculation)
	{
		return Crosstabs.variable(expression, calculation);
	}
	
	// measure
	
	public <T> CrosstabMeasureBuilder<T> measure(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		return Crosstabs.measure(column, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(
		final String title, final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		return Crosstabs.measure(title, column, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(final FieldBuilder<T> field, final Calculation calculation)
	{
		return Crosstabs.measure(field, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final FieldBuilder<T> field,
		final Calculation calculation)
	{
		return Crosstabs.measure(title, field, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return Crosstabs.measure(fieldName, valueClass, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return Crosstabs.measure(title, fieldName, valueClass, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(final DRIExpression<?> expression, final Calculation calculation)
	{
		return Crosstabs.measure(expression, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(
		final String title,
		final DRIExpression<?> expression,
		final Calculation calculation)
	{
		return Crosstabs.measure(title, expression, calculation);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(final DRIExpression<?> expression)
	{
		return Crosstabs.measure(expression);
	}
	
	public <T> CrosstabMeasureBuilder<T> measure(final String title, final DRIExpression<?> expression)
	{
		return Crosstabs.measure(title, expression);
	}
}
