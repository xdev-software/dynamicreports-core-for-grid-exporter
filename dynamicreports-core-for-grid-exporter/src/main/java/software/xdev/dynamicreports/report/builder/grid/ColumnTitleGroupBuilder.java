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
package software.xdev.dynamicreports.report.builder.grid;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.grid.DRColumnTitleGroup;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;


public class ColumnTitleGroupBuilder extends AbstractBuilder<ColumnTitleGroupBuilder, DRColumnTitleGroup>
	implements ColumnGridComponentBuilder
{

	protected ColumnTitleGroupBuilder()
	{
		super(new DRColumnTitleGroup());
	}
	
	public ColumnTitleGroupBuilder add(final ColumnGridComponentBuilder... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ColumnGridComponentBuilder component : components)
		{
			this.getObject().addComponent(component.build());
		}
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitle(final DRIExpression<?> titleExpression)
	{
		this.getObject().setTitleExpression(titleExpression);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitle(final String title)
	{
		this.getObject().setTitleExpression(Expressions.text(title));
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		if(titleStyle != null)
		{
			this.getObject().setTitleStyle(titleStyle.getStyle());
		}
		else
		{
			this.getObject().setTitleStyle(null);
		}
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleColumns(final Integer columns)
	{
		this.getObject().setTitleColumns(columns);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleFixedColumns(final Integer columns)
	{
		this.getObject().setTitleColumns(columns);
		this.getObject().setTitleWidthType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleMinColumns(final Integer columns)
	{
		this.getObject().setTitleColumns(columns);
		this.getObject().setTitleWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleWidth(final Integer width)
	{
		this.getObject().setTitleWidth(width);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleFixedWidth(final Integer width)
	{
		this.getObject().setTitleWidth(width);
		this.getObject().setTitleWidthType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleMinWidth(final Integer width)
	{
		this.getObject().setTitleWidth(width);
		this.getObject().setTitleWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleFixedRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		this.getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleMinRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		this.getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleFixedHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		this.getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleMinHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		this.getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	@Deprecated
	public ColumnTitleGroupBuilder setTitleStretchWithOverflow(final Boolean stretchWithOverflow)
	{
		this.getObject().setTitleStretchWithOverflow(stretchWithOverflow);
		return this;
	}
	
	public ColumnTitleGroupBuilder setTitleTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setTitleTextAdjust(textAdjust);
		return this;
	}
	
	public ColumnTitleGroupBuilder addTitleProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getObject().addTitlePropertyExpression(propertyExpression);
		return this;
	}
	
	public ColumnTitleGroupBuilder addTitleProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getObject().addTitlePropertyExpression(Expressions.property(name, valueExpression));
		return this;
	}
	
	public ColumnTitleGroupBuilder addTitleProperty(final String name, final String value)
	{
		this.getObject().addTitlePropertyExpression(Expressions.property(name, value));
		return this;
	}
	
	public DRColumnTitleGroup getColumnGridTitleGroup()
	{
		return this.build();
	}
}
