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

import software.xdev.dynamicreports.report.definition.style.DRIBorder;


public class DRBorder implements DRIBorder
{

	private DRPen topPen;
	private DRPen leftPen;
	private DRPen bottomPen;
	private DRPen rightPen;
	
	public DRBorder()
	{
		this.topPen = new DRPen();
		this.leftPen = new DRPen();
		this.bottomPen = new DRPen();
		this.rightPen = new DRPen();
	}
	
	public DRBorder(final DRPen pen)
	{
		this.topPen = pen;
		this.leftPen = pen;
		this.bottomPen = pen;
		this.rightPen = pen;
	}
	
	@Override
	public DRPen getTopPen()
	{
		return this.topPen;
	}
	
	public void setTopPen(final DRPen topPen)
	{
		this.topPen = topPen;
	}
	
	@Override
	public DRPen getLeftPen()
	{
		return this.leftPen;
	}
	
	public void setLeftPen(final DRPen leftPen)
	{
		this.leftPen = leftPen;
	}
	
	@Override
	public DRPen getBottomPen()
	{
		return this.bottomPen;
	}
	
	public void setBottomPen(final DRPen bottomPen)
	{
		this.bottomPen = bottomPen;
	}
	
	@Override
	public DRPen getRightPen()
	{
		return this.rightPen;
	}
	
	public void setRightPen(final DRPen rightPen)
	{
		this.rightPen = rightPen;
	}
}
