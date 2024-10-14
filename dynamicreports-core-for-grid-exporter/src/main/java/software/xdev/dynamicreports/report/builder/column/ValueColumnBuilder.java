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

import software.xdev.dynamicreports.report.base.column.DRValueColumn;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


@SuppressWarnings({"unchecked"})
public abstract class ValueColumnBuilder<T extends ValueColumnBuilder<T, U>, U>
	extends ColumnBuilder<T, DRValueColumn<U>>
{

	protected ValueColumnBuilder()
	{
		super(new DRValueColumn<>(new DRTextField<>()));
	}
	
	protected void setValueExpression(final DRIExpression<U> valueExpression)
	{
		this.getComponent().setValueExpression(valueExpression);
	}
	
	public T setPrintRepeatedDetailValues(final Boolean printRepeatedDetailValues)
	{
		this.getObject().setPrintRepeatedDetailValues(printRepeatedDetailValues);
		return (T)this;
	}
	
	public T setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getComponent().setHorizontalTextAlignment(horizontalTextAlignment);
		return (T)this;
	}
	
	public T setPattern(final String pattern)
	{
		this.getComponent().setPattern(pattern);
		return (T)this;
	}
	
	public T setPattern(final DRIExpression<String> patternExpression)
	{
		this.getComponent().setPatternExpression(patternExpression);
		return (T)this;
	}
	
	public T setValueFormatter(final DRIValueFormatter<?, ? super U> valueFormatter)
	{
		this.getComponent().setValueFormatter(valueFormatter);
		return (T)this;
	}
	
	public T setDataType(final DRIDataType<? super U, U> dataType)
	{
		this.getComponent().setDataType(dataType);
		return (T)this;
	}
	
	public T setColumns(final Integer columns)
	{
		this.getComponent().setColumns(columns);
		return (T)this;
	}
	
	public T setFixedColumns(final Integer columns)
	{
		this.getComponent().setColumns(columns);
		this.getComponent().setWidthType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinColumns(final Integer columns)
	{
		this.getComponent().setColumns(columns);
		this.getComponent().setWidthType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setRows(final Integer rows)
	{
		this.getComponent().setRows(rows);
		return (T)this;
	}
	
	public T setFixedRows(final Integer rows)
	{
		this.getComponent().setRows(rows);
		this.getComponent().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinRows(final Integer rows)
	{
		this.getComponent().setRows(rows);
		this.getComponent().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setAnchorName(final String anchorName)
	{
		this.getComponent().setAnchorNameExpression(Expressions.text(anchorName));
		return (T)this;
	}
	
	public T setAnchorName(final DRIExpression<String> anchorNameExpression)
	{
		this.getComponent().setAnchorNameExpression(anchorNameExpression);
		return (T)this;
	}
	
	public T setBookmarkLevel(final Integer bookmarkLevel)
	{
		this.getComponent().setBookmarkLevel(bookmarkLevel);
		return (T)this;
	}
	
	public T setHyperLink(final HyperLinkBuilder hyperLink)
	{
		if(hyperLink != null)
		{
			this.getComponent().setHyperLink(hyperLink.getHyperLink());
		}
		else
		{
			this.getComponent().setHyperLink(null);
		}
		return (T)this;
	}
	
	public T setWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		return (T)this;
	}
	
	public T setFixedWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		this.getComponent().setWidthType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinWidth(final Integer width)
	{
		this.getComponent().setWidth(width);
		this.getComponent().setWidthType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		return (T)this;
	}
	
	public T setFixedHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		this.getComponent().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinHeight(final Integer height)
	{
		this.getComponent().setHeight(height);
		this.getComponent().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setTextAdjust(final TextAdjust textAdjust)
	{
		this.getComponent().setTextAdjust(textAdjust);
		return (T)this;
	}
	
	public T printInFirstWholeBand()
	{
		return this.setPrintInFirstWholeBand(true);
	}
	
	public T setPrintInFirstWholeBand(final Boolean printInFirstWholeBand)
	{
		this.getComponent().setPrintInFirstWholeBand(printInFirstWholeBand);
		return (T)this;
	}
	
	public T printWhenDetailOverflows()
	{
		return this.setPrintWhenDetailOverflows(true);
	}
	
	public T setPrintWhenDetailOverflows(final Boolean printWhenDetailOverflows)
	{
		this.getComponent().setPrintWhenDetailOverflows(printWhenDetailOverflows);
		return (T)this;
	}
	
	public T addProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getComponent().addPropertyExpression(propertyExpression);
		return (T)this;
	}
	
	public T addProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getComponent().addPropertyExpression(Expressions.property(name, valueExpression));
		return (T)this;
	}
	
	public T addProperty(final String name, final String value)
	{
		this.getComponent().addPropertyExpression(Expressions.property(name, value));
		return (T)this;
	}
	
	@Override
	protected DRTextField<U> getComponent()
	{
		return (DRTextField<U>)this.getObject().getComponent();
	}
}
