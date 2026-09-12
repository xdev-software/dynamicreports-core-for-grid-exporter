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
package software.xdev.dynamicreports.report.base.style;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.style.DRIPadding;


public class DRPadding implements DRIPadding
{

	private Integer top;
	private Integer left;
	private Integer bottom;
	private Integer right;
	
	public DRPadding()
	{
	}
	
	public DRPadding(final Integer padding)
	{
		if(padding != null)
		{
			Validate.isTrue(padding >= 0, "padding must be >= 0");
		}
		this.top = padding;
		this.left = padding;
		this.bottom = padding;
		this.right = padding;
	}
	
	@Override
	public Integer getTop()
	{
		return this.top;
	}
	
	public void setTop(final Integer top)
	{
		if(top != null)
		{
			Validate.isTrue(top >= 0, "top must be >= 0");
		}
		this.top = top;
	}
	
	@Override
	public Integer getLeft()
	{
		return this.left;
	}
	
	public void setLeft(final Integer left)
	{
		if(left != null)
		{
			Validate.isTrue(left >= 0, "left must be >= 0");
		}
		this.left = left;
	}
	
	@Override
	public Integer getBottom()
	{
		return this.bottom;
	}
	
	public void setBottom(final Integer bottom)
	{
		if(bottom != null)
		{
			Validate.isTrue(bottom >= 0, "bottom must be >= 0");
		}
		this.bottom = bottom;
	}
	
	@Override
	public Integer getRight()
	{
		return this.right;
	}
	
	public void setRight(final Integer right)
	{
		if(right != null)
		{
			Validate.isTrue(right >= 0, "right must be >= 0");
		}
		this.right = right;
	}
}
