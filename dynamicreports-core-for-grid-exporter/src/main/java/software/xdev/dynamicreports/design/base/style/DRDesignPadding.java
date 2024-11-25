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
package software.xdev.dynamicreports.design.base.style;

import java.util.Objects;

import software.xdev.dynamicreports.design.definition.style.DRIDesignPadding;


public class DRDesignPadding implements DRIDesignPadding
{

	private Integer top;
	private Integer left;
	private Integer bottom;
	private Integer right;
	
	@Override
	public Integer getTop()
	{
		return this.top;
	}
	
	public void setTop(final Integer top)
	{
		this.top = top;
	}
	
	@Override
	public Integer getLeft()
	{
		return this.left;
	}
	
	public void setLeft(final Integer left)
	{
		this.left = left;
	}
	
	@Override
	public Integer getBottom()
	{
		return this.bottom;
	}
	
	public void setBottom(final Integer bottom)
	{
		this.bottom = bottom;
	}
	
	@Override
	public Integer getRight()
	{
		return this.right;
	}
	
	public void setRight(final Integer right)
	{
		this.right = right;
	}
	
	@Override
	public boolean equals(final Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || this.getClass() != o.getClass())
		{
			return false;
		}
		final DRDesignPadding that = (DRDesignPadding)o;
		return Objects.equals(this.getTop(), that.getTop())
			&& Objects.equals(this.getLeft(), that.getLeft())
			&& Objects.equals(this.getBottom(), that.getBottom())
			&& Objects.equals(this.getRight(), that.getRight());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getTop(), this.getLeft(), this.getBottom(), this.getRight());
	}
}
