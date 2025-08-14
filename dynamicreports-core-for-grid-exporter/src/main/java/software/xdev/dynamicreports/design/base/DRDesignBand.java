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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.definition.DRIDesignBand;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.SplitType;


public class DRDesignBand implements DRIDesignBand
{
	private final String name;
	private SplitType splitType;
	private DRDesignList list;
	private DRDesignComponent bandComponent;
	private Integer height;
	private DRIDesignExpression printWhenExpression;
	
	public DRDesignBand(final String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return this.name;
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
	public DRDesignList getList()
	{
		return this.list;
	}
	
	public void setList(final DRDesignList list)
	{
		this.list = list;
	}
	
	public void addComponent(final DRDesignComponent component)
	{
		this.list.addComponent(component);
	}
	
	public void addComponent(final int index, final DRDesignComponent component)
	{
		this.list.addComponent(index, component);
	}
	
	@Override
	public DRDesignComponent getBandComponent()
	{
		return this.bandComponent;
	}
	
	public void setBandComponent(final DRDesignComponent component)
	{
		this.bandComponent = component;
	}
	
	@Override
	public Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final Integer height)
	{
		this.height = height;
	}
	
	@Override
	public DRIDesignExpression getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}
	
	public void setPrintWhenExpression(final DRIDesignExpression printWhenExpression)
	{
		this.printWhenExpression = printWhenExpression;
	}
}
