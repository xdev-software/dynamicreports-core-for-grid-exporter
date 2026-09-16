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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public final class Columns
{
	private Columns()
	{
	}
	
	// text
	
	@SuppressWarnings("unchecked")
	public static <T> TextColumnBuilder<T> column(final String fieldName, final Class<T> valueClass)
	{
		return (TextColumnBuilder<T>)column(DynamicReports.field(fieldName, valueClass));
	}
	
	@SuppressWarnings("unchecked")
	public static <T> TextColumnBuilder<T> column(
		final String title, final String fieldName,
		final Class<T> valueClass)
	{
		return (TextColumnBuilder<T>)column(title, DynamicReports.field(fieldName, valueClass));
	}
	
	public static <T> TextColumnBuilder<T> column(final String fieldName, final DRIDataType<? super T, T> dataType)
	{
		Validate.notNull(dataType, "dataType must not be null");
		final TextColumnBuilder<T> textColumnBuilder =
			new TextColumnBuilder<>(DynamicReports.<T>field(fieldName, dataType.getValueClass()));
		textColumnBuilder.setDataType(dataType);
		return textColumnBuilder;
	}
	
	public static <T> TextColumnBuilder<T> column(
		final String title,
		final String fieldName,
		final DRIDataType<? super T, T> dataType)
	{
		final TextColumnBuilder<T> textColumnBuilder = column(fieldName, dataType);
		textColumnBuilder.setTitle(title);
		return textColumnBuilder;
	}
	
	public static <T> TextColumnBuilder<T> column(final FieldBuilder<T> field)
	{
		final TextColumnBuilder<T> textColumnBuilder = new TextColumnBuilder<>(field);
		if(field.getField().getDataType() != null)
		{
			textColumnBuilder.setDataType(field.getField().getDataType());
		}
		return textColumnBuilder;
	}
	
	public static <T> TextColumnBuilder<T> column(final String title, final FieldBuilder<T> field)
	{
		return column(field).setTitle(title);
	}
	
	// expression
	
	public static <T> TextColumnBuilder<T> column(final DRIExpression<T> expression)
	{
		final TextColumnBuilder<T> textColumnBuilder = new TextColumnBuilder<>(expression);
		if(expression instanceof DRIField && ((DRIField<T>)expression).getDataType() != null)
		{
			textColumnBuilder.setDataType(((DRIField<T>)expression).getDataType());
		}
		return textColumnBuilder;
	}
	
	public static <T> TextColumnBuilder<T> column(final String title, final DRIExpression<T> expression)
	{
		return column(expression).setTitle(title);
	}
	
	// percentage
	
	public static PercentageColumnBuilder percentageColumn(final ValueColumnBuilder<?, ? extends Number> column)
	{
		return new PercentageColumnBuilder(column);
	}
	
	public static PercentageColumnBuilder percentageColumn(
		final String title,
		final ValueColumnBuilder<?, ? extends Number> column)
	{
		return percentageColumn(column).setTitle(title);
	}
	
	public static PercentageColumnBuilder percentageColumn(
		final String fieldName,
		final Class<? extends Number> valueClass)
	{
		return percentageColumn(DynamicReports.<Number>field(fieldName, valueClass));
	}
	
	public static PercentageColumnBuilder percentageColumn(
		final String title,
		final String fieldName,
		final Class<? extends Number> valueClass)
	{
		return percentageColumn(fieldName, valueClass).setTitle(title);
	}
	
	public static PercentageColumnBuilder percentageColumn(final FieldBuilder<? extends Number> field)
	{
		return new PercentageColumnBuilder(field);
	}
	
	public static PercentageColumnBuilder percentageColumn(
		final String title,
		final FieldBuilder<? extends Number> field)
	{
		return percentageColumn(field).setTitle(title);
	}
	
	// column row number
	
	public static TextColumnBuilder<Integer> columnRowNumberColumn()
	{
		return column(Expressions.columnRowNumber());
	}
	
	public static TextColumnBuilder<Integer> columnRowNumberColumn(final String title)
	{
		return columnRowNumberColumn().setTitle(title);
	}
	
	// page row number
	
	public static TextColumnBuilder<Integer> pageRowNumberColumn()
	{
		return column(Expressions.pageRowNumber());
	}
	
	public static TextColumnBuilder<Integer> pageRowNumberColumn(final String title)
	{
		return pageRowNumberColumn().setTitle(title);
	}
	
	// report row number
	
	public static TextColumnBuilder<Integer> reportRowNumberColumn()
	{
		return column(Expressions.reportRowNumber());
	}
	
	public static TextColumnBuilder<Integer> reportRowNumberColumn(final String title)
	{
		return reportRowNumberColumn().setTitle(title);
	}
	
	// component
	
	public static ComponentColumnBuilder componentColumn(final ComponentBuilder<?, ?> component)
	{
		Validate.notNull(component, "component must not be null");
		return new ComponentColumnBuilder(component);
	}
	
	public static ComponentColumnBuilder componentColumn(final String title, final ComponentBuilder<?, ?> component)
	{
		return componentColumn(component).setTitle(title);
	}
	
	// boolean
	
	public static BooleanColumnBuilder booleanColumn(final String fieldName)
	{
		return booleanColumn(DynamicReports.<Boolean>field(fieldName, Boolean.class));
	}
	
	public static BooleanColumnBuilder booleanColumn(final String title, final String fieldName)
	{
		return booleanColumn(title, DynamicReports.<Boolean>field(fieldName, Boolean.class));
	}
	
	public static BooleanColumnBuilder booleanColumn(final FieldBuilder<Boolean> field)
	{
		return new BooleanColumnBuilder(field);
	}
	
	public static BooleanColumnBuilder booleanColumn(final String title, final FieldBuilder<Boolean> field)
	{
		return booleanColumn(field).setTitle(title);
	}
	
	public static BooleanColumnBuilder booleanColumn(final DRIExpression<Boolean> expression)
	{
		return new BooleanColumnBuilder(expression);
	}
	
	public static BooleanColumnBuilder booleanColumn(final String title, final DRIExpression<Boolean> expression)
	{
		return booleanColumn(expression).setTitle(title);
	}
	
	// empty column
	
	public static TextColumnBuilder<String> emptyColumn()
	{
		return emptyColumn(false, false);
	}
	
	public static TextColumnBuilder<String> emptyColumn(final boolean showTitle, final boolean showDetailRows)
	{
		final TextColumnBuilder<String> column = column(Expressions.text(null));
		if(showTitle)
		{
			column.setTitle("");
		}
		if(!showDetailRows)
		{
			column.setPrintWhenExpression(Expressions.value(false));
		}
		return column;
	}
}
