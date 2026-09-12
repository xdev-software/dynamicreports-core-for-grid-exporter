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

import java.awt.Color;
import java.util.Objects;

import software.xdev.dynamicreports.design.definition.style.DRIDesignPen;
import software.xdev.dynamicreports.report.constant.LineStyle;


public class DRDesignPen implements DRIDesignPen
{

	private Float lineWidth;
	private LineStyle lineStyle;
	private Color lineColor;
	
	@Override
	public Float getLineWidth()
	{
		return this.lineWidth;
	}
	
	public void setLineWidth(final Float lineWidth)
	{
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
	
	@Override
	public boolean equals(final Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		final DRDesignPen that = (DRDesignPen)o;
		return Objects.equals(getLineWidth(), that.getLineWidth())
			&& getLineStyle() == that.getLineStyle()
			&& Objects.equals(getLineColor(), that.getLineColor());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(getLineWidth(), getLineStyle(), getLineColor());
	}
}
