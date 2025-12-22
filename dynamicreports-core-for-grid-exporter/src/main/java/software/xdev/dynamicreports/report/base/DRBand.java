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
package software.xdev.dynamicreports.report.base;

import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.component.DRList;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRBand implements DRIBand
{

	private SplitType splitType;
	private final DRList list;
	private DRIExpression<Boolean> printWhenExpression;
	
	public DRBand()
	{
		this.list = new DRList(ListType.VERTICAL);
	}
	
	@Override
	public SplitType getSplitType()
	{
		return this.splitType;
	}
	
	public void setSplitType(final SplitType splitType)
	{
		this.splitType = splitType;
	}
	
	@Override
	public DRList getList()
	{
		return this.list;
	}
	
	public void addComponent(final DRComponent component)
	{
		this.list.addComponent(component);
	}
	
	@Override
	public DRIExpression<Boolean> getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}
	
	public void setPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.printWhenExpression = printWhenExpression;
	}
}
