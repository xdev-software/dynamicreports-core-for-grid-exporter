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
package software.xdev.dynamicreports.report.base.crosstab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public abstract class DRCrosstabGroup<T> implements DRICrosstabGroup<T>
{

	private final String name;
	private String headerPattern;
	private HorizontalTextAlignment headerHorizontalTextAlignment;
	private DRIValueFormatter<?, ? super T> headerValueFormatter;
	private Boolean headerStretchWithOverflow;
	private TextAdjust headerTextAdjust;
	private DRHyperLink headerHyperLink;
	private DRIReportStyle headerStyle;
	private List<DRIPropertyExpression> headerPropertyExpressions;
	private Boolean showTotal;
	private CrosstabTotalPosition totalPosition;
	private DRIExpression<?> totalHeaderExpression;
	private Boolean totalHeaderStretchWithOverflow;
	private TextAdjust totalHeaderTextAdjust;
	private DRIReportStyle totalHeaderStyle;
	private List<DRIPropertyExpression> totalHeaderPropertyExpressions;
	private DRIExpression<T> expression;
	private DRIDataType<? super T, T> dataType;
	private DRIExpression<? extends Comparable<?>> orderByExpression;
	private OrderType orderType;
	private DRIExpression<? extends Comparator<?>> comparatorExpression;
	
	public DRCrosstabGroup()
	{
		this.name = ReportUtils.generateUniqueName("crosstabGroup");
		this.headerPropertyExpressions = new ArrayList<>();
		this.totalHeaderPropertyExpressions = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getHeaderPattern()
	{
		return this.headerPattern;
	}
	
	public void setHeaderPattern(final String headerPattern)
	{
		this.headerPattern = headerPattern;
	}
	
	@Override
	public HorizontalTextAlignment getHeaderHorizontalTextAlignment()
	{
		return this.headerHorizontalTextAlignment;
	}
	
	public void setHeaderHorizontalTextAlignment(final HorizontalTextAlignment headerHorizontalTextAlignment)
	{
		this.headerHorizontalTextAlignment = headerHorizontalTextAlignment;
	}
	
	@Override
	public DRIValueFormatter<?, ? super T> getHeaderValueFormatter()
	{
		return this.headerValueFormatter;
	}
	
	public void setHeaderValueFormatter(final DRIValueFormatter<?, ? super T> headerValueFormatter)
	{
		this.headerValueFormatter = headerValueFormatter;
	}
	
	@Override
	public Boolean getHeaderStretchWithOverflow()
	{
		return this.headerStretchWithOverflow;
	}
	
	@Deprecated
	public void setHeaderStretchWithOverflow(final Boolean headerStretchWithOverflow)
	{
		this.headerStretchWithOverflow = headerStretchWithOverflow;
	}
	
	@Override
	public TextAdjust getHeaderTextAdjust()
	{
		return this.headerTextAdjust;
	}
	
	public void setHeaderTextAdjust(final TextAdjust headerTextAdjust)
	{
		this.headerTextAdjust = headerTextAdjust;
	}
	
	@Override
	public DRHyperLink getHeaderHyperLink()
	{
		return this.headerHyperLink;
	}
	
	public void setHeaderHyperLink(final DRHyperLink headerHyperLink)
	{
		this.headerHyperLink = headerHyperLink;
	}
	
	@Override
	public DRIReportStyle getHeaderStyle()
	{
		return this.headerStyle;
	}
	
	public void setHeaderStyle(final DRIReportStyle headerStyle)
	{
		this.headerStyle = headerStyle;
	}
	
	@Override
	public List<DRIPropertyExpression> getHeaderPropertyExpressions()
	{
		return this.headerPropertyExpressions;
	}
	
	public void setHeaderPropertyExpressions(final List<DRIPropertyExpression> headerPropertyExpressions)
	{
		this.headerPropertyExpressions = headerPropertyExpressions;
	}
	
	public void addHeaderPropertyExpression(final DRIPropertyExpression headerPropertyExpression)
	{
		Validate.notNull(headerPropertyExpression, "headerPropertyExpression must not be null");
		this.headerPropertyExpressions.add(headerPropertyExpression);
	}
	
	@Override
	public Boolean getShowTotal()
	{
		return this.showTotal;
	}
	
	public void setShowTotal(final Boolean showTotal)
	{
		this.showTotal = showTotal;
	}
	
	@Override
	public CrosstabTotalPosition getTotalPosition()
	{
		return this.totalPosition;
	}
	
	public void setTotalPosition(final CrosstabTotalPosition totalPosition)
	{
		this.totalPosition = totalPosition;
	}
	
	@Override
	public DRIExpression<?> getTotalHeaderExpression()
	{
		return this.totalHeaderExpression;
	}
	
	public void setTotalHeaderExpression(final DRIExpression<?> totalHeaderExpression)
	{
		this.totalHeaderExpression = totalHeaderExpression;
	}
	
	@Override
	public Boolean getTotalHeaderStretchWithOverflow()
	{
		return this.totalHeaderStretchWithOverflow;
	}
	
	@Deprecated
	public void setTotalHeaderStretchWithOverflow(final Boolean totalHeaderStretchWithOverflow)
	{
		this.totalHeaderStretchWithOverflow = totalHeaderStretchWithOverflow;
	}
	
	@Override
	public TextAdjust getTotalHeaderTextAdjust()
	{
		return this.totalHeaderTextAdjust;
	}
	
	public void setTotalHeaderTextAdjust(final TextAdjust headerTextAdjust)
	{
		this.totalHeaderTextAdjust = headerTextAdjust;
	}
	
	@Override
	public DRIReportStyle getTotalHeaderStyle()
	{
		return this.totalHeaderStyle;
	}
	
	public void setTotalHeaderStyle(final DRIReportStyle totalHeaderStyle)
	{
		this.totalHeaderStyle = totalHeaderStyle;
	}
	
	@Override
	public List<DRIPropertyExpression> getTotalHeaderPropertyExpressions()
	{
		return this.totalHeaderPropertyExpressions;
	}
	
	public void setTotalHeaderPropertyExpressions(final List<DRIPropertyExpression> totalHeaderPropertyExpressions)
	{
		this.totalHeaderPropertyExpressions = totalHeaderPropertyExpressions;
	}
	
	public void addTotalHeaderPropertyExpression(final DRIPropertyExpression totalHeaderPropertyExpression)
	{
		Validate.notNull(totalHeaderPropertyExpression, "totalHeaderPropertyExpression must not be null");
		this.totalHeaderPropertyExpressions.add(totalHeaderPropertyExpression);
	}
	
	@Override
	public DRIExpression<T> getExpression()
	{
		return this.expression;
	}
	
	public void setExpression(final DRIExpression<T> expression)
	{
		Validate.notNull(expression, "expression must not be null");
		this.expression = expression;
	}
	
	@Override
	public DRIDataType<? super T, T> getDataType()
	{
		return this.dataType;
	}
	
	public void setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.dataType = dataType;
	}
	
	@Override
	public DRIExpression<? extends Comparable<?>> getOrderByExpression()
	{
		return this.orderByExpression;
	}
	
	public void setOrderByExpression(final DRIExpression<? extends Comparable<?>> orderByExpression)
	{
		this.orderByExpression = orderByExpression;
	}
	
	@Override
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	public void setOrderType(final OrderType orderType)
	{
		this.orderType = orderType;
	}
	
	@Override
	public DRIExpression<? extends Comparator<?>> getComparatorExpression()
	{
		return this.comparatorExpression;
	}
	
	public void setComparatorExpression(final DRIExpression<? extends Comparator<?>> comparatorExpression)
	{
		this.comparatorExpression = comparatorExpression;
	}
	
	@Override
	public Class<? super T> getValueClass()
	{
		return this.getExpression().getValueClass();
	}
}
