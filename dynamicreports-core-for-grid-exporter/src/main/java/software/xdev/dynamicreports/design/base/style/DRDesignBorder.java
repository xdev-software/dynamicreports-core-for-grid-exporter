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

import software.xdev.dynamicreports.design.definition.style.DRIDesignBorder;


public class DRDesignBorder implements DRIDesignBorder
{

	private DRDesignPen topPen;
	private DRDesignPen leftPen;
	private DRDesignPen bottomPen;
	private DRDesignPen rightPen;
	
	@Override
	public DRDesignPen getTopPen()
	{
		return this.topPen;
	}
	
	public void setTopPen(final DRDesignPen topPen)
	{
		this.topPen = topPen;
	}
	
	@Override
	public DRDesignPen getLeftPen()
	{
		return this.leftPen;
	}
	
	public void setLeftPen(final DRDesignPen leftPen)
	{
		this.leftPen = leftPen;
	}
	
	@Override
	public DRDesignPen getBottomPen()
	{
		return this.bottomPen;
	}
	
	public void setBottomPen(final DRDesignPen bottomPen)
	{
		this.bottomPen = bottomPen;
	}
	
	@Override
	public DRDesignPen getRightPen()
	{
		return this.rightPen;
	}
	
	public void setRightPen(final DRDesignPen rightPen)
	{
		this.rightPen = rightPen;
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
		final DRDesignBorder that = (DRDesignBorder)o;
		return Objects.equals(this.getTopPen(), that.getTopPen()) && Objects.equals(
			this.getLeftPen(),
			that.getLeftPen()) && Objects.equals(this.getBottomPen(), that.getBottomPen()) && Objects.equals(
			this.getRightPen(),
			that.getRightPen());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(this.getTopPen(), this.getLeftPen(), this.getBottomPen(), this.getRightPen());
	}
}
