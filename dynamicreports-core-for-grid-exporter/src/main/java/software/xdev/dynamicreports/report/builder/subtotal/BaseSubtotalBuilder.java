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
package software.xdev.dynamicreports.report.builder.subtotal;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRSubtotal;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


@SuppressWarnings("unchecked")
public abstract class BaseSubtotalBuilder<T extends BaseSubtotalBuilder<T, U>, U>
	extends AbstractBuilder<T, DRSubtotal<U>>
{

	private DRIExpression<U> valueExpression;
	
	protected BaseSubtotalBuilder(final ColumnBuilder<?, ?> showInColumn)
	{
		super(new DRSubtotal<>(showInColumn.build()));
	}
	
	protected void setValueExpression(final DRIExpression<U> valueExpression)
	{
		this.valueExpression = valueExpression;
	}
	
	public T setShowInColumn(final ColumnBuilder<?, ?> showInColumn)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		this.getObject().setShowInColumn(showInColumn.build());
		return (T)this;
	}
	
	public T setLabel(final DRIExpression<?> labelExpression)
	{
		this.getObject().setLabelExpression(labelExpression);
		return (T)this;
	}
	
	public T setLabel(final String label)
	{
		this.getObject().setLabelExpression(Expressions.text(label));
		return (T)this;
	}
	
	public T setLabelStyle(final ReportStyleBuilder labelStyle)
	{
		if(labelStyle != null)
		{
			this.getObject().setLabelStyle(labelStyle.getStyle());
		}
		else
		{
			this.getObject().setLabelStyle(null);
		}
		return (T)this;
	}
	
	public T setLabelPosition(final Position labelPosition)
	{
		this.getObject().setLabelPosition(labelPosition);
		return (T)this;
	}
	
	public T setLabelWidth(final Integer width)
	{
		this.getObject().setLabelWidth(width);
		return (T)this;
	}
	
	public T setLabelFixedWidth(final Integer width)
	{
		this.getObject().setLabelWidth(width);
		this.getObject().setLabelWidthType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setLabelMinWidth(final Integer width)
	{
		this.getObject().setLabelWidth(width);
		this.getObject().setLabelWidthType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setPattern(final String pattern)
	{
		this.getObject().getValueField().setPattern(pattern);
		return (T)this;
	}
	
	public T setPattern(final DRIExpression<String> patternExpression)
	{
		this.getObject().getValueField().setPatternExpression(patternExpression);
		return (T)this;
	}
	
	public T setValueFormatter(final DRIValueFormatter<?, ? super U> valueFormatter)
	{
		this.getObject().getValueField().setValueFormatter(valueFormatter);
		return (T)this;
	}
	
	@SuppressWarnings("rawtypes")
	public T setDataType(final DRIDataType dataType)
	{
		this.getObject().getValueField().setDataType(dataType);
		return (T)this;
	}
	
	public T setAnchorName(final String anchorName)
	{
		this.getObject().getValueField().setAnchorNameExpression(Expressions.text(anchorName));
		return (T)this;
	}
	
	public T setAnchorName(final DRIExpression<String> anchorNameExpression)
	{
		this.getObject().getValueField().setAnchorNameExpression(anchorNameExpression);
		return (T)this;
	}
	
	public T setBookmarkLevel(final Integer bookmarkLevel)
	{
		this.getObject().getValueField().setBookmarkLevel(bookmarkLevel);
		return (T)this;
	}
	
	public T setHyperLink(final HyperLinkBuilder hyperLink)
	{
		if(hyperLink != null)
		{
			this.getObject().getValueField().setHyperLink(hyperLink.getHyperLink());
		}
		else
		{
			this.getObject().getValueField().setHyperLink(null);
		}
		return (T)this;
	}
	
	public T setRows(final Integer rows)
	{
		this.getObject().getValueField().setRows(rows);
		return (T)this;
	}
	
	public T setFixedRows(final Integer rows)
	{
		this.getObject().getValueField().setRows(rows);
		this.getObject().getValueField().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinRows(final Integer rows)
	{
		this.getObject().getValueField().setRows(rows);
		this.getObject().getValueField().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setHeight(final Integer height)
	{
		this.getObject().getValueField().setHeight(height);
		return (T)this;
	}
	
	public T setFixedHeight(final Integer height)
	{
		this.getObject().getValueField().setHeight(height);
		this.getObject().getValueField().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinHeight(final Integer height)
	{
		this.getObject().getValueField().setHeight(height);
		this.getObject().getValueField().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getValueField().setStyle(style.getStyle());
		}
		else
		{
			this.getObject().getValueField().setStyle(null);
		}
		return (T)this;
	}
	
	public T setPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getValueField().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setGroup(final GroupBuilder<?> group)
	{
		if(group != null)
		{
			this.getObject().setGroup(group.getGroup());
		}
		else
		{
			this.getObject().setGroup(null);
		}
		return (T)this;
	}
	
	public T setPosition(final SubtotalPosition position)
	{
		this.getObject().setPosition(position);
		return (T)this;
	}
	
	@Override
	protected void configure()
	{
		super.configure();
		this.getObject().getValueField().setValueExpression(this.valueExpression);
	}
	
	public DRSubtotal<U> getSubtotal()
	{
		return this.build();
	}
}
