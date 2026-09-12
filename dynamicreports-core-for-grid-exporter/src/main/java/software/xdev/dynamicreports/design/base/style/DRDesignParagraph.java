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

import java.util.List;
import java.util.Objects;

import software.xdev.dynamicreports.design.definition.style.DRIDesignParagraph;
import software.xdev.dynamicreports.design.definition.style.DRIDesignTabStop;
import software.xdev.dynamicreports.report.constant.LineSpacing;


public class DRDesignParagraph implements DRIDesignParagraph
{

	private LineSpacing lineSpacing;
	private Float lineSpacingSize;
	private Integer firstLineIndent;
	private Integer leftIndent;
	private Integer rightIndent;
	private Integer spacingBefore;
	private Integer spacingAfter;
	private Integer tabStopWidth;
	private List<DRIDesignTabStop> tabStops;
	
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
	public List<DRIDesignTabStop> getTabStops()
	{
		return this.tabStops;
	}
	
	public void setTabStops(final List<DRIDesignTabStop> tabStops)
	{
		this.tabStops = tabStops;
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
		final DRDesignParagraph that = (DRDesignParagraph)o;
		return getLineSpacing() == that.getLineSpacing()
			&& Objects.equals(getLineSpacingSize(), that.getLineSpacingSize())
			&& Objects.equals(getFirstLineIndent(), that.getFirstLineIndent())
			&& Objects.equals(getLeftIndent(), that.getLeftIndent())
			&& Objects.equals(getRightIndent(), that.getRightIndent())
			&& Objects.equals(getSpacingBefore(), that.getSpacingBefore())
			&& Objects.equals(getSpacingAfter(), that.getSpacingAfter())
			&& Objects.equals(getTabStopWidth(), that.getTabStopWidth())
			&& Objects.equals(getTabStops(), that.getTabStops());
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(
			getLineSpacing(),
			getLineSpacingSize(),
			getFirstLineIndent(),
			getLeftIndent(),
			getRightIndent(),
			getSpacingBefore(),
			getSpacingAfter(),
			getTabStopWidth(),
			getTabStops());
	}
}
