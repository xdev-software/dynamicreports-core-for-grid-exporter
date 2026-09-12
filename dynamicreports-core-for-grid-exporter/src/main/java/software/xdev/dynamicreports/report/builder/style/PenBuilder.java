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
package software.xdev.dynamicreports.report.builder.style;

import java.awt.Color;

import software.xdev.dynamicreports.report.base.style.DRPen;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.LineStyle;


public class PenBuilder extends AbstractBuilder<PenBuilder, DRPen>
{

	protected PenBuilder()
	{
		super(new DRPen());
	}
	
	protected PenBuilder(final Float lineWidth, final LineStyle lineStyle)
	{
		super(new DRPen(lineWidth, lineStyle));
	}
	
	public PenBuilder setLineWidth(final Float lineWidth)
	{
		this.getObject().setLineWidth(lineWidth);
		return this;
	}
	
	public PenBuilder setLineStyle(final LineStyle lineStyle)
	{
		this.getObject().setLineStyle(lineStyle);
		return this;
	}
	
	public PenBuilder setLineColor(final Color lineColor)
	{
		this.getObject().setLineColor(lineColor);
		return this;
	}
	
	public DRPen getPen()
	{
		return this.build();
	}
}
