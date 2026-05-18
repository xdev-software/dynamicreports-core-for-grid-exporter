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
package software.xdev.dynamicreports.report.builder.component;

import java.util.Date;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.VariableBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


@SuppressWarnings("deprecation")
public class TextFieldBuilder<T> extends HyperLinkComponentBuilder<TextFieldBuilder<T>, DRTextField<T>>
{

	protected TextFieldBuilder()
	{
		super(new DRTextField<>());
	}
	
	public TextFieldBuilder<T> setText(final VariableBuilder<T> variable)
	{
		Validate.notNull(variable, "variable must not be null");
		this.getObject().setValueExpression(variable.getVariable());
		return this;
	}
	
	public TextFieldBuilder<T> setText(final FieldBuilder<T> field)
	{
		Validate.notNull(field, "field must not be null");
		this.getObject().setValueExpression(field.getField());
		if(this.getObject().getDataType() == null)
		{
			this.getObject().setDataType(field.getField().getDataType());
		}
		return this;
	}
	
	public TextFieldBuilder<T> setText(final DRIExpression<T> textExpression)
	{
		this.getObject().setValueExpression(textExpression);
		if(textExpression instanceof DRIField && this.getObject().getDataType() == null)
		{
			this.getObject().setDataType(((DRIField<T>)textExpression).getDataType());
		}
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public TextFieldBuilder<T> setText(final String text)
	{
		this.getObject().setValueExpression((DRIExpression<T>)Expressions.text(text));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public TextFieldBuilder<T> setText(final Number number)
	{
		this.getObject().setValueExpression((DRIExpression<T>)Expressions.number(number));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public TextFieldBuilder<T> setText(final Date date)
	{
		this.getObject().setValueExpression((DRIExpression<T>)Expressions.date(date));
		return this;
	}
	
	public TextFieldBuilder<T> setPattern(final String pattern)
	{
		this.getObject().setPattern(pattern);
		return this;
	}
	
	public TextFieldBuilder<T> setPattern(final DRIExpression<String> patternExpression)
	{
		this.getObject().setPatternExpression(patternExpression);
		return this;
	}
	
	public TextFieldBuilder<T> setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		return this;
	}
	
	public TextFieldBuilder<T> setValueFormatter(final DRIValueFormatter<?, ? super T> valueFormatter)
	{
		this.getObject().setValueFormatter(valueFormatter);
		return this;
	}
	
	public TextFieldBuilder<T> setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.getObject().setDataType(dataType);
		return this;
	}
	
	public TextFieldBuilder<T> setColumns(final Integer columns)
	{
		this.getObject().setColumns(columns);
		return this;
	}
	
	public TextFieldBuilder<T> setFixedColumns(final Integer columns)
	{
		this.getObject().setColumns(columns);
		this.getObject().setWidthType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public TextFieldBuilder<T> setMinColumns(final Integer columns)
	{
		this.getObject().setColumns(columns);
		this.getObject().setWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	

	public TextFieldBuilder<T> setRows(final Integer rows)
	{
		this.getObject().setRows(rows);
		return this;
	}
	
	public TextFieldBuilder<T> setFixedRows(final Integer rows)
	{
		this.getObject().setRows(rows);
		this.getObject().setHeightType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public TextFieldBuilder<T> setMinRows(final Integer rows)
	{
		this.getObject().setRows(rows);
		this.getObject().setHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public TextFieldBuilder<T> setEvaluationTime(final Evaluation evaluationTime)
	{
		this.getObject().setEvaluationTime(evaluationTime);
		return this;
	}
	
	public TextFieldBuilder<T> setEvaluationGroup(final GroupBuilder<?> evaluationGroup)
	{
		Validate.notNull(evaluationGroup, "evaluationGroup must not be null");
		this.getObject().setEvaluationGroup(evaluationGroup.build());
		this.getObject().setEvaluationTime(Evaluation.GROUP);
		return this;
	}
	
	public TextFieldBuilder<T> setMarkup(final Markup markup)
	{
		this.getObject().setMarkup(markup);
		return this;
	}
	
	public TextFieldBuilder<T> setTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setTextAdjust(textAdjust);
		return this;
	}
	
	public TextFieldBuilder<T> setPrintRepeatedValues(final Boolean printRepeatedValues)
	{
		this.getObject().setPrintRepeatedValues(printRepeatedValues);
		return this;
	}
}
