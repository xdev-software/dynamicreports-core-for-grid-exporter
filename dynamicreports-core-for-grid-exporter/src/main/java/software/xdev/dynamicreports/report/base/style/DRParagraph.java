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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.report.constant.LineSpacing;
import software.xdev.dynamicreports.report.definition.style.DRIParagraph;
import software.xdev.dynamicreports.report.definition.style.DRITabStop;


public class DRParagraph implements DRIParagraph
{

	private LineSpacing lineSpacing;
	private Float lineSpacingSize;
	private Integer firstLineIndent;
	private Integer leftIndent;
	private Integer rightIndent;
	private Integer spacingBefore;
	private Integer spacingAfter;
	private Integer tabStopWidth;
	private List<DRITabStop> tabStops;
	
	public DRParagraph()
	{
		this.tabStops = new ArrayList<>();
	}
	
	@Override
	public LineSpacing getLineSpacing()
	{
		return this.lineSpacing;
	}
	
	public void setLineSpacing(final LineSpacing lineSpacing)
	{
		this.lineSpacing = lineSpacing;
	}
	
	@Override
	public Float getLineSpacingSize()
	{
		return this.lineSpacingSize;
	}
	
	public void setLineSpacingSize(final Float lineSpacingSize)
	{
		this.lineSpacingSize = lineSpacingSize;
	}
	
	@Override
	public Integer getFirstLineIndent()
	{
		return this.firstLineIndent;
	}
	
	public void setFirstLineIndent(final Integer firstLineIndent)
	{
		this.firstLineIndent = firstLineIndent;
	}
	
	@Override
	public Integer getLeftIndent()
	{
		return this.leftIndent;
	}
	
	public void setLeftIndent(final Integer leftIndent)
	{
		this.leftIndent = leftIndent;
	}
	
	@Override
	public Integer getRightIndent()
	{
		return this.rightIndent;
	}
	
	public void setRightIndent(final Integer rightIndent)
	{
		this.rightIndent = rightIndent;
	}
	
	@Override
	public Integer getSpacingBefore()
	{
		return this.spacingBefore;
	}
	
	public void setSpacingBefore(final Integer spacingBefore)
	{
		this.spacingBefore = spacingBefore;
	}
	
	@Override
	public Integer getSpacingAfter()
	{
		return this.spacingAfter;
	}
	
	public void setSpacingAfter(final Integer spacingAfter)
	{
		this.spacingAfter = spacingAfter;
	}
	
	@Override
	public Integer getTabStopWidth()
	{
		return this.tabStopWidth;
	}
	
	public void setTabStopWidth(final Integer tabStopWidth)
	{
		this.tabStopWidth = tabStopWidth;
	}
	
	@Override
	public List<DRITabStop> getTabStops()
	{
		return this.tabStops;
	}
	
	public void setTabStops(final List<DRITabStop> tabStops)
	{
		this.tabStops = tabStops;
	}
}
