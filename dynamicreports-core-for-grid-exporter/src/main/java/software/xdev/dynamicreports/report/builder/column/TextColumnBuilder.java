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

import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.expression.AddExpression;
import software.xdev.dynamicreports.report.builder.expression.DivideExpression;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.expression.MultiplyExpression;
import software.xdev.dynamicreports.report.builder.expression.SubtractExpression;
import software.xdev.dynamicreports.report.builder.expression.ValueExpression;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class TextColumnBuilder<T> extends ValueColumnBuilder<TextColumnBuilder<T>, T> implements DRIValue<T>
{

	protected TextColumnBuilder(final FieldBuilder<T> field)
	{
		Validate.notNull(field, "field must not be null");
		this.setValueExpression(field.getField());
	}
	
	protected TextColumnBuilder(final DRIExpression<T> valueExpression)
	{
		this.setValueExpression(valueExpression);
	}
	
	// add
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> add(final TextColumnBuilder<? extends Number> column)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can multiply");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		final AddExpression exp = new AddExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> add(final Number number)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can multiply");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final ValueExpression<Number> value2Expression = Expressions.number(number);
		final AddExpression exp = new AddExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	// subtract
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> subtract(final TextColumnBuilder<? extends Number> column)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can subtract");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		final SubtractExpression exp = new SubtractExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> subtract(final Number number)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can subtract");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final ValueExpression<Number> value2Expression = Expressions.number(number);
		final SubtractExpression exp = new SubtractExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	// multiply
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> multiply(final TextColumnBuilder<? extends Number> column)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can multiply");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		final MultiplyExpression exp = new MultiplyExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> multiply(final Number number)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can multiply");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final ValueExpression<Number> value2Expression = Expressions.number(number);
		final MultiplyExpression exp = new MultiplyExpression(value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	// divide
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> divide(final int scale, final TextColumnBuilder<? extends Number> column)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can divide");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final DRIExpression<? extends Number> value2Expression = column.getComponent().getValueExpression();
		final DivideExpression exp = new DivideExpression(scale, value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	@SuppressWarnings("unchecked")
	public TextColumnBuilder<BigDecimal> divide(final int scale, final Number number)
	{
		Validate.isTrue(
			Number.class.isAssignableFrom(this.getObject().getValueClass()),
			"Only Number column can divide");
		final DRIExpression<? extends Number> value1Expression =
			(DRIExpression<? extends Number>)this.getComponent().getValueExpression();
		final ValueExpression<Number> value2Expression = Expressions.number(number);
		final DivideExpression exp = new DivideExpression(scale, value1Expression, value2Expression);
		return new TextColumnBuilder<>(exp).setDataType(type.bigDecimalType());
	}
	
	@Override
	public String getName()
	{
		return this.getObject().getName();
	}
}
