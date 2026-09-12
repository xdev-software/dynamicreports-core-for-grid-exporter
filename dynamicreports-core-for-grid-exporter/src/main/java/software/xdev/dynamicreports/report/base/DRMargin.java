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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.DRIMargin;


public class DRMargin implements DRIMargin
{

	private int top;
	private int left;
	private int bottom;
	private int right;
	
	public DRMargin()
	{
	}
	
	public DRMargin(final int margin)
	{
		Validate.isTrue(margin >= 0, "margin must be >= 0");
		this.top = margin;
		this.left = margin;
		this.bottom = margin;
		this.right = margin;
	}
	
	@Override
	public int getTop()
	{
		return this.top;
	}
	
	public void setTop(final int top)
	{
		Validate.isTrue(top >= 0, "top must be >= 0");
		this.top = top;
	}
	
	@Override
	public int getLeft()
	{
		return this.left;
	}
	
	public void setLeft(final int left)
	{
		Validate.isTrue(left >= 0, "left must be >= 0");
		this.left = left;
	}
	
	@Override
	public int getBottom()
	{
		return this.bottom;
	}
	
	public void setBottom(final int bottom)
	{
		Validate.isTrue(bottom >= 0, "bottom must be >= 0");
		this.bottom = bottom;
	}
	
	@Override
	public int getRight()
	{
		return this.right;
	}
	
	public void setRight(final int right)
	{
		Validate.isTrue(right >= 0, "right must be >= 0");
		this.right = right;
	}
}
