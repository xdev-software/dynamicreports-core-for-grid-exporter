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

import software.xdev.dynamicreports.report.base.column.DRBooleanColumn;
import software.xdev.dynamicreports.report.base.component.DRBooleanField;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("deprecation")
public class BooleanColumnBuilder extends ColumnBuilder<BooleanColumnBuilder, DRBooleanColumn>
{

	protected BooleanColumnBuilder(final FieldBuilder<Boolean> field)
	{
		super(new DRBooleanColumn(new DRBooleanField()));
		Validate.notNull(field, "field must not be null");
		this.getComponent().setValueExpression(field.getField());
	}
	
	protected BooleanColumnBuilder(final DRIExpression<Boolean> valueExpression)
	{
		super(new DRBooleanColumn(new DRBooleanField()));
		this.getComponent().setValueExpression(valueExpression);
	}
	
	public BooleanColumnBuilder setWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		return this;
	}
	
	public BooleanColumnBuilder setFixedWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		this.getComponent().setWidthType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public BooleanColumnBuilder setMinWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		this.getComponent().setWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public BooleanColumnBuilder setHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		return this;
	}
	
	public BooleanColumnBuilder setFixedHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		this.getComponent().setHeightType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public BooleanColumnBuilder setMinHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		this.getComponent().setHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public BooleanColumnBuilder setComponentType(final BooleanComponentType booleanComponentType)
	{
		this.getComponent().setComponentType(booleanComponentType);
		return this;
	}
	
	public BooleanColumnBuilder setEmptyWhenNullValue(final Boolean emptyWhenNullValue)
	{
		this.getComponent().setEmptyWhenNullValue(emptyWhenNullValue);
		return this;
	}
	
	public BooleanColumnBuilder setImageDimension(final Integer width, final Integer height)
	{
		this.getComponent().setImageWidth(width);
		this.getComponent().setImageHeight(height);
		return this;
	}
	
	public BooleanColumnBuilder setImageWidth(final Integer width)
	{
		this.getComponent().setImageWidth(width);
		return this;
	}
	
	public BooleanColumnBuilder setImageHeight(final Integer height)
	{
		this.getComponent().setImageHeight(height);
		return this;
	}
	
	
	public BooleanColumnBuilder setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.getComponent().setHorizontalImageAlignment(horizontalImageAlignment);
		return this;
	}
	
	public BooleanColumnBuilder setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getComponent().setHorizontalTextAlignment(horizontalTextAlignment);
		return this;
	}
	
	@Override
	protected DRBooleanField getComponent()
	{
		return (DRBooleanField)this.getObject().getComponent();
	}
}
