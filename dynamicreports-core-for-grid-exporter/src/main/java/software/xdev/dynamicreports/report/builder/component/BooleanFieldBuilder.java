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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRBooleanField;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("deprecation")
public class BooleanFieldBuilder extends HyperLinkComponentBuilder<BooleanFieldBuilder, DRBooleanField>
{

	protected BooleanFieldBuilder()
	{
		super(new DRBooleanField());
	}
	
	public BooleanFieldBuilder setValue(final FieldBuilder<Boolean> field)
	{
		Validate.notNull(field, "field must not be null");
		this.getObject().setValueExpression(field.getField());
		return this;
	}
	
	public BooleanFieldBuilder setValue(final DRIExpression<Boolean> valueExpression)
	{
		this.getObject().setValueExpression(valueExpression);
		return this;
	}
	
	public BooleanFieldBuilder setValue(final Boolean value)
	{
		this.getObject().setValueExpression(Expressions.value(value));
		return this;
	}
	
	public BooleanFieldBuilder setComponentType(final BooleanComponentType booleanComponentType)
	{
		this.getObject().setComponentType(booleanComponentType);
		return this;
	}
	
	public BooleanFieldBuilder setEmptyWhenNullValue(final Boolean emptyWhenNullValue)
	{
		this.getObject().setEmptyWhenNullValue(emptyWhenNullValue);
		return this;
	}
	
	public BooleanFieldBuilder setImageDimension(final Integer width, final Integer height)
	{
		this.getObject().setImageWidth(width);
		this.getObject().setImageHeight(height);
		return this;
	}
	
	public BooleanFieldBuilder setImageWidth(final Integer width)
	{
		this.getObject().setImageWidth(width);
		return this;
	}
	
	public BooleanFieldBuilder setImageHeight(final Integer height)
	{
		this.getObject().setImageHeight(height);
		return this;
	}
	
	public BooleanFieldBuilder setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.getObject().setHorizontalImageAlignment(horizontalImageAlignment);
		return this;
	}
	
	public BooleanFieldBuilder setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		return this;
	}
}
