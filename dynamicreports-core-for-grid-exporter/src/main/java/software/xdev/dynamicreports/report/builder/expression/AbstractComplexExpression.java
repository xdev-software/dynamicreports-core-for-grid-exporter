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
package software.xdev.dynamicreports.report.builder.expression;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.base.DRField;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.VariableBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIComplexExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public abstract class AbstractComplexExpression<T> implements DRIComplexExpression<T>
{

	private final String name;
	private final List<DRIExpression<?>> expressions;
	
	protected AbstractComplexExpression()
	{
		this.name = ReportUtils.generateUniqueName("complexExpression");
		this.expressions = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	protected void addExpression(final FieldBuilder<?> field)
	{
		Validate.notNull(field, "field must not be null");
		this.expressions.add(field.getField());
	}
	
	protected void addExpression(final String fieldName, final Class<?> valueClass)
	{
		@SuppressWarnings({"unchecked", "rawtypes"})
		final DRField<?> field = new DRField(fieldName, valueClass);
		this.expressions.add(field);
	}
	
	protected void addExpression(final TextColumnBuilder<?> column)
	{
		Validate.notNull(column, "column must not be null");
		this.expressions.add(column.build());
	}
	
	protected void addExpression(final VariableBuilder<?> variable)
	{
		Validate.notNull(variable, "variable must not be null");
		this.expressions.add(variable.getVariable());
	}
	
	protected void addExpression(final DRIExpression<?> expression)
	{
		Validate.notNull(expression, "expression must not be null");
		this.expressions.add(expression);
	}
	
	protected void addExpression(final AbstractCrosstabGroupBuilder<?, ?, ?> crosstabGroup)
	{
		Validate.notNull(crosstabGroup, "crosstabGroup must not be null");
		this.expressions.add(Expressions.crosstabValue(crosstabGroup));
	}
	
	protected void addExpression(final CrosstabMeasureBuilder<?> crosstabMeasure)
	{
		Validate.notNull(crosstabMeasure, "crosstabMeasure must not be null");
		this.expressions.add(Expressions.crosstabValue(crosstabMeasure));
	}
	
	@Override
	public List<DRIExpression<?>> getExpressions()
	{
		return this.expressions;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Class<? super T> getValueClass()
	{
		return (Class<? super T>)ReportUtils.getGenericClass(this, 0);
	}
	
	@Override
	public abstract T evaluate(List<?> values, ReportParameters reportParameters);
}
