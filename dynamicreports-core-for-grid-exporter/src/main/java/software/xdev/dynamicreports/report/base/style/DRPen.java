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

import java.awt.Color;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.LineStyle;
import software.xdev.dynamicreports.report.definition.style.DRIPen;


public class DRPen implements DRIPen
{

	private Float lineWidth;
	private LineStyle lineStyle;
	private Color lineColor;
	
	public DRPen()
	{
	}
	
	public DRPen(final Float lineWidth, final LineStyle lineStyle)
	{
		this.setLineWidth(lineWidth);
		this.lineStyle = lineStyle;
	}
	
	@Override
	public Float getLineWidth()
	{
		return this.lineWidth;
	}
	
	public void setLineWidth(final Float lineWidth)
	{
		if(lineWidth != null)
		{
			Validate.isTrue(lineWidth >= 0, "lineWidth must be >= 0");
		}
		this.lineWidth = lineWidth;
	}
	
	@Override
	public LineStyle getLineStyle()
	{
		return this.lineStyle;
	}
	
	public void setLineStyle(final LineStyle lineStyle)
	{
		this.lineStyle = lineStyle;
	}
	
	@Override
	public Color getLineColor()
	{
		return this.lineColor;
	}
	
	public void setLineColor(final Color lineColor)
	{
		this.lineColor = lineColor;
	}
}
