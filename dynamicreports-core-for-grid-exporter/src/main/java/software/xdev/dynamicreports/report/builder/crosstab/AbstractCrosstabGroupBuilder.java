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
package software.xdev.dynamicreports.report.builder.crosstab;

import java.util.Comparator;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.column.DRValueColumn;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabGroup;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


@SuppressWarnings({"unchecked", "deprecation"})
public abstract class AbstractCrosstabGroupBuilder<T extends AbstractCrosstabGroupBuilder<T, U, V>,
	U extends DRCrosstabGroup<V>, V>
	extends AbstractBuilder<T, U> implements DRIValue<V>
{

	protected AbstractCrosstabGroupBuilder(final ValueColumnBuilder<?, V> column, final U crosstabGroup)
	{
		super(crosstabGroup);
		Validate.notNull(column, "column must not be null");
		final DRValueColumn<V> col = column.getColumn();
		this.getObject().setExpression(col);
		this.getObject().setDataType(col.getComponent().getDataType());
		this.getObject().setHeaderPattern(col.getComponent().getPattern());
	}
	
	protected AbstractCrosstabGroupBuilder(final FieldBuilder<V> field, final U crosstabGroup)
	{
		super(crosstabGroup);
		Validate.notNull(field, "field must not be null");
		this.getObject().setExpression(field.getField());
		this.getObject().setDataType(field.getField().getDataType());
	}
	
	protected AbstractCrosstabGroupBuilder(final DRIExpression<V> expression, final U crosstabGroup)
	{
		super(crosstabGroup);
		this.getObject().setExpression(expression);
		if(expression instanceof DRIField)
		{
			this.getObject().setDataType(((DRIField<V>)expression).getDataType());
		}
	}
	
	public T setHeaderPattern(final String pattern)
	{
		this.getObject().setHeaderPattern(pattern);
		return (T)this;
	}
	
	public T setHeaderHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().setHeaderHorizontalTextAlignment(horizontalTextAlignment);
		return (T)this;
	}
	
	public T setHeaderValueFormatter(final DRIValueFormatter<?, ? super V> valueFormatter)
	{
		this.getObject().setHeaderValueFormatter(valueFormatter);
		return (T)this;
	}
	
	@Deprecated
	public T setHeaderStretchWithOverflow(final Boolean stretchWithOverflow)
	{
		this.getObject().setHeaderStretchWithOverflow(stretchWithOverflow);
		return (T)this;
	}
	
	public T setHeaderTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setHeaderTextAdjust(textAdjust);
		return (T)this;
	}
	
	public T setHeaderHyperLink(final HyperLinkBuilder hyperLink)
	{
		if(hyperLink != null)
		{
			this.getObject().setHeaderHyperLink(hyperLink.getHyperLink());
		}
		else
		{
			this.getObject().setHeaderHyperLink(null);
		}
		return (T)this;
	}
	
	public T setHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setHeaderStyle(style.getStyle());
		}
		else
		{
			this.getObject().setHeaderStyle(null);
		}
		return (T)this;
	}
	
	public T addHeaderProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getObject().addHeaderPropertyExpression(propertyExpression);
		return (T)this;
	}
	
	public T addHeaderProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getObject().addHeaderPropertyExpression(Expressions.property(name, valueExpression));
		return (T)this;
	}
	
	public T addHeaderProperty(final String name, final String value)
	{
		this.getObject().addHeaderPropertyExpression(Expressions.property(name, value));
		return (T)this;
	}
	
	public T setShowTotal(final Boolean showTotal)
	{
		this.getObject().setShowTotal(showTotal);
		return (T)this;
	}
	
	public T setTotalPosition(final CrosstabTotalPosition totalPosition)
	{
		this.getObject().setTotalPosition(totalPosition);
		return (T)this;
	}
	
	public T setTotalHeader(final DRIExpression<?> totalHeaderExpression)
	{
		this.getObject().setTotalHeaderExpression(totalHeaderExpression);
		return (T)this;
	}
	
	public T setTotalHeader(final String totalHeader)
	{
		this.getObject().setTotalHeaderExpression(Expressions.text(totalHeader));
		return (T)this;
	}
	
	@Deprecated
	public T setTotalHeaderStretchWithOverflow(final Boolean stretchWithOverflow)
	{
		this.getObject().setTotalHeaderStretchWithOverflow(stretchWithOverflow);
		return (T)this;
	}
	
	public T setTotalHeaderTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setTotalHeaderTextAdjust(textAdjust);
		return (T)this;
	}
	
	public T setTotalHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setTotalHeaderStyle(style.getStyle());
		}
		else
		{
			this.getObject().setTotalHeaderStyle(null);
		}
		return (T)this;
	}
	
	public T addTotalHeaderProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getObject().addTotalHeaderPropertyExpression(propertyExpression);
		return (T)this;
	}
	
	public T addTotalHeaderProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getObject().addTotalHeaderPropertyExpression(Expressions.property(name, valueExpression));
		return (T)this;
	}
	
	public T addTotalHeaderProperty(final String name, final String value)
	{
		this.getObject().addTotalHeaderPropertyExpression(Expressions.property(name, value));
		return (T)this;
	}
	
	public T setDataType(final DRIDataType<? super V, V> dataType)
	{
		this.getObject().setDataType(dataType);
		return (T)this;
	}
	
	public T setOrderType(final OrderType orderType)
	{
		this.getObject().setOrderType(orderType);
		return (T)this;
	}
	
	public T setOrderByExpression(final DRIExpression<? extends Comparable<?>> orderByExpression)
	{
		return this.orderBy(orderByExpression);
	}
	
	public T orderBy(final CrosstabMeasureBuilder<? extends Comparable<?>> measure)
	{
		return this.orderBy(Expressions.orderBy(measure));
	}
	
	public T orderBy(final DRIExpression<? extends Comparable<?>> orderByExpression)
	{
		this.getObject().setOrderByExpression(orderByExpression);
		return (T)this;
	}
	
	public T setComparatorExpression(final DRIExpression<? extends Comparator<?>> comparatorExpression)
	{
		this.getObject().setComparatorExpression(comparatorExpression);
		return (T)this;
	}
	
	@Override
	public String getName()
	{
		return this.getObject().getName();
	}
}
