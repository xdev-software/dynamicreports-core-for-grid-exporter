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

import software.xdev.dynamicreports.report.base.column.DRColumn;
import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;


@SuppressWarnings("unchecked")
public abstract class ColumnBuilder<T extends ColumnBuilder<T, U>, U extends DRColumn<?>> extends AbstractBuilder<T, U>
	implements ColumnGridComponentBuilder
{

	protected ColumnBuilder(final U column)
	{
		super(column);
	}
	
	public T setTitle(final DRIExpression<?> titleExpression)
	{
		this.getObject().setTitleExpression(titleExpression);
		return (T)this;
	}
	
	public T setTitle(final String title)
	{
		this.getObject().setTitleExpression(Expressions.text(title));
		return (T)this;
	}
	
	public T setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		if(titleStyle != null)
		{
			this.getObject().setTitleStyle(titleStyle.getStyle());
		}
		else
		{
			this.getObject().setTitleStyle(null);
		}
		return (T)this;
	}
	
	public T setStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getComponent().setStyle(style.getStyle());
		}
		else
		{
			this.getComponent().setStyle(null);
		}
		return (T)this;
	}
	
	public T setPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getComponent().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setTitleRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		return (T)this;
	}
	
	public T setTitleFixedRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		this.getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setTitleMinRows(final Integer rows)
	{
		this.getObject().setTitleRows(rows);
		this.getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setTitleHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		return (T)this;
	}
	
	public T setTitleFixedHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		this.getObject().setTitleHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}

	public T setTitleMinHeight(final Integer height)
	{
		this.getObject().setTitleHeight(height);
		this.getObject().setTitleHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setTitleTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setTitleTextAdjust(textAdjust);
		return (T)this;
	}
	
	public T addTitleProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getObject().addTitlePropertyExpression(propertyExpression);
		return (T)this;
	}
	
	public T addTitleProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getObject().addTitlePropertyExpression(Expressions.property(name, valueExpression));
		return (T)this;
	}
	
	public T addTitleProperty(final String name, final String value)
	{
		this.getObject().addTitlePropertyExpression(Expressions.property(name, value));
		return (T)this;
	}
	
	protected DRComponent getComponent()
	{
		return (DRComponent)this.getObject().getComponent();
	}
	
	public U getColumn()
	{
		return this.build();
	}
}
