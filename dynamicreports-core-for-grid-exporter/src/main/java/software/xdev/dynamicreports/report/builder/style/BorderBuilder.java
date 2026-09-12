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

import software.xdev.dynamicreports.report.base.style.DRBorder;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;


public class BorderBuilder extends AbstractBuilder<BorderBuilder, DRBorder>
{

	protected BorderBuilder()
	{
		super(new DRBorder());
	}
	
	protected BorderBuilder(final PenBuilder pen)
	{
		super(new DRBorder(pen.build()));
	}
	
	public BorderBuilder setTopPen(final PenBuilder topPen)
	{
		if(topPen != null)
		{
			this.getObject().setTopPen(topPen.build());
		}
		else
		{
			this.getObject().setTopPen(null);
		}
		return this;
	}
	
	public BorderBuilder setLeftPen(final PenBuilder leftPen)
	{
		if(leftPen != null)
		{
			this.getObject().setLeftPen(leftPen.build());
		}
		else
		{
			this.getObject().setLeftPen(null);
		}
		return this;
	}
	
	public BorderBuilder setBottomPen(final PenBuilder bottomPen)
	{
		if(bottomPen != null)
		{
			this.getObject().setBottomPen(bottomPen.build());
		}
		else
		{
			this.getObject().setBottomPen(null);
		}
		return this;
	}
	
	public BorderBuilder setRightPen(final PenBuilder rightPen)
	{
		if(rightPen != null)
		{
			this.getObject().setRightPen(rightPen.build());
		}
		else
		{
			this.getObject().setRightPen(null);
		}
		return this;
	}
	
	public DRBorder getBorder()
	{
		return this.build();
	}
}
