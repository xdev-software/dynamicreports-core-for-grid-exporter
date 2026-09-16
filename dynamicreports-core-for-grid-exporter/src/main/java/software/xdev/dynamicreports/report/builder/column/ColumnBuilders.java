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
package software.xdev.dynamicreports.report.builder.column;

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class ColumnBuilders
{
	
	// text
	
	public <T> TextColumnBuilder<T> column(final String fieldName, final Class<T> valueClass)
	{
		return Columns.column(fieldName, valueClass);
	}
	
	public <T> TextColumnBuilder<T> column(final String title, final String fieldName, final Class<T> valueClass)
	{
		return Columns.column(title, fieldName, valueClass);
	}
	
	public <T> TextColumnBuilder<T> column(final String fieldName, final DRIDataType<? super T, T> dataType)
	{
		return Columns.column(fieldName, dataType);
	}
	
	public <T> TextColumnBuilder<T> column(
		final String title,
		final String fieldName,
		final DRIDataType<? super T, T> dataType)
	{
		return Columns.column(title, fieldName, dataType);
	}
	
	public <T> TextColumnBuilder<T> column(final FieldBuilder<T> field)
	{
		return Columns.column(field);
	}
	
	public <T> TextColumnBuilder<T> column(final String title, final FieldBuilder<T> field)
	{
		return Columns.column(title, field);
	}
	
	// expression
	
	public <T> TextColumnBuilder<T> column(final DRIExpression<T> expression)
	{
		return Columns.column(expression);
	}
	
	public <T> TextColumnBuilder<T> column(final String title, final DRIExpression<T> expression)
	{
		return Columns.column(title, expression);
	}
	
	// percentage
	
	public PercentageColumnBuilder percentageColumn(final ValueColumnBuilder<?, ? extends Number> column)
	{
		return Columns.percentageColumn(column);
	}
	
	public PercentageColumnBuilder percentageColumn(
		final String title,
		final ValueColumnBuilder<?, ? extends Number> column)
	{
		return Columns.percentageColumn(title, column);
	}
	
	public PercentageColumnBuilder percentageColumn(final String fieldName, final Class<? extends Number> valueClass)
	{
		return Columns.percentageColumn(fieldName, valueClass);
	}
	
	public PercentageColumnBuilder percentageColumn(
		final String title,
		final String fieldName,
		final Class<? extends Number> valueClass)
	{
		return Columns.percentageColumn(title, fieldName, valueClass);
	}
	
	public PercentageColumnBuilder percentageColumn(final FieldBuilder<? extends Number> field)
	{
		return Columns.percentageColumn(field);
	}
	
	public PercentageColumnBuilder percentageColumn(final String title, final FieldBuilder<? extends Number> field)
	{
		return Columns.percentageColumn(title, field);
	}
	
	// column row number
	public TextColumnBuilder<Integer> columnRowNumberColumn()
	{
		return Columns.columnRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> columnRowNumberColumn(final String title)
	{
		return Columns.columnRowNumberColumn(title);
	}
	
	// page row number
	
	public TextColumnBuilder<Integer> pageRowNumberColumn()
	{
		return Columns.pageRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> pageRowNumberColumn(final String title)
	{
		return Columns.pageRowNumberColumn(title);
	}
	
	// report row number
	
	public TextColumnBuilder<Integer> reportRowNumberColumn()
	{
		return Columns.reportRowNumberColumn();
	}
	
	public TextColumnBuilder<Integer> reportRowNumberColumn(final String title)
	{
		return Columns.reportRowNumberColumn(title);
	}
	
	// component
	
	public ComponentColumnBuilder componentColumn(final ComponentBuilder<?, ?> component)
	{
		return Columns.componentColumn(component);
	}
	
	public ComponentColumnBuilder componentColumn(final String title, final ComponentBuilder<?, ?> component)
	{
		return Columns.componentColumn(title, component);
	}
	
	// boolean
	
	public BooleanColumnBuilder booleanColumn(final String fieldName)
	{
		return Columns.booleanColumn(fieldName);
	}
	
	public BooleanColumnBuilder booleanColumn(final String title, final String fieldName)
	{
		return Columns.booleanColumn(title, fieldName);
	}
	
	public BooleanColumnBuilder booleanColumn(final FieldBuilder<Boolean> field)
	{
		return Columns.booleanColumn(field);
	}
	
	public BooleanColumnBuilder booleanColumn(final String title, final FieldBuilder<Boolean> field)
	{
		return Columns.booleanColumn(title, field);
	}
	
	public BooleanColumnBuilder booleanColumn(final DRIExpression<Boolean> expression)
	{
		return Columns.booleanColumn(expression);
	}
	
	public BooleanColumnBuilder booleanColumn(final String title, final DRIExpression<Boolean> expression)
	{
		return Columns.booleanColumn(title, expression);
	}
	
	// empty column
	
	public TextColumnBuilder<String> emptyColumn()
	{
		return Columns.emptyColumn();
	}
	
	public TextColumnBuilder<String> emptyColumn(final boolean showTitle, final boolean showDetailRows)
	{
		return Columns.emptyColumn(showTitle, showDetailRows);
	}
}
