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

import software.xdev.dynamicreports.design.definition.DRIDesignMargin;


public class DRDesignMargin implements DRIDesignMargin
{

	private int top;
	private int left;
	private int bottom;
	private int right;
	
	@Override
	public int getTop()
	{
		return this.top;
	}
	
	public void setTop(final int top)
	{
		this.top = top;
	}
	
	@Override
	public int getLeft()
	{
		return this.left;
	}
	
	public void setLeft(final int left)
	{
		this.left = left;
	}
	
	@Override
	public int getBottom()
	{
		return this.bottom;
	}
	
	public void setBottom(final int bottom)
	{
		this.bottom = bottom;
	}
	
	@Override
	public int getRight()
	{
		return this.right;
	}
	
	public void setRight(final int right)
	{
		this.right = right;
	}
}
