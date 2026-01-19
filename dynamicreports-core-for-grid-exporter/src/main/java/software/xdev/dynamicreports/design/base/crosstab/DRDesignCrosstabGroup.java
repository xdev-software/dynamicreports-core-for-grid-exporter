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
package software.xdev.dynamicreports.design.base.crosstab;

import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabGroup;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.OrderType;


public abstract class DRDesignCrosstabGroup implements DRIDesignCrosstabGroup
{

	private String name;
	private CrosstabTotalPosition totalPosition;
	private OrderType orderType;
	private DRIDesignExpression expression;
	private DRIDesignExpression orderByExpression;
	private DRIDesignExpression comparatorExpression;
	private DRDesignCrosstabCellContent header;
	private DRDesignCrosstabCellContent totalHeader;
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
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
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	public void setOrderType(final OrderType orderType)
	{
		this.orderType = orderType;
	}
	
	@Override
	public DRIDesignExpression getExpression()
	{
		return this.expression;
	}
	
	public void setExpression(final DRIDesignExpression expression)
	{
		this.expression = expression;
	}
	
	@Override
	public DRIDesignExpression getOrderByExpression()
	{
		return this.orderByExpression;
	}
	
	public void setOrderByExpression(final DRIDesignExpression orderByExpression)
	{
		this.orderByExpression = orderByExpression;
	}
	
	@Override
	public DRIDesignExpression getComparatorExpression()
	{
		return this.comparatorExpression;
	}
	
	public void setComparatorExpression(final DRIDesignExpression comparatorExpression)
	{
		this.comparatorExpression = comparatorExpression;
	}
	
	@Override
	public DRDesignCrosstabCellContent getHeader()
	{
		return this.header;
	}
	
	public void setHeader(final DRDesignCrosstabCellContent header)
	{
		this.header = header;
	}
	
	@Override
	public DRDesignCrosstabCellContent getTotalHeader()
	{
		return this.totalHeader;
	}
	
	public void setTotalHeader(final DRDesignCrosstabCellContent totalHeader)
	{
		this.totalHeader = totalHeader;
	}
}
