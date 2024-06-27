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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;


public class DRDesignGroup implements DRIDesignGroup
{

	private final String name;
	private DRIDesignExpression groupExpression;
	private List<DRDesignBand> headerBands;
	private List<DRDesignBand> footerBands;
	private boolean startInNewPage;
	private boolean startInNewColumn;
	private boolean reprintHeaderOnEachPage;
	private boolean resetPageNumber;
	private Integer minHeightToStartNewPage;
	private GroupFooterPosition footerPosition;
	private boolean keepTogether;
	private boolean headerWithSubtotal;
	
	public DRDesignGroup(final String name)
	{
		this.name = name;
		this.init();
	}
	
	private void init()
	{
		this.headerBands = new ArrayList<>();
		this.footerBands = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRIDesignExpression getGroupExpression()
	{
		return this.groupExpression;
	}
	
	public void setGroupExpression(final DRIDesignExpression groupExpression)
	{
		this.groupExpression = groupExpression;
	}
	
	@Override
	public List<DRDesignBand> getHeaderBands()
	{
		return this.headerBands;
	}
	
	public void setHeaderBands(final List<DRDesignBand> headerBands)
	{
		this.headerBands = headerBands;
	}
	
	public void addHeaderBand(final DRDesignBand headerBand)
	{
		this.headerBands.add(headerBand);
	}
	
	@Override
	public List<DRDesignBand> getFooterBands()
	{
		return this.footerBands;
	}
	
	public void setFooterBands(final List<DRDesignBand> footerBands)
	{
		this.footerBands = footerBands;
	}
	
	public void addFooterBand(final DRDesignBand footerBand)
	{
		this.footerBands.add(footerBand);
	}
	
	public void addFooterBand(final int index, final DRDesignBand footerBand)
	{
		this.footerBands.add(index, footerBand);
	}
	
	@Override
	public boolean isStartInNewPage()
	{
		return this.startInNewPage;
	}
	
	public void setStartInNewPage(final boolean startInNewPage)
	{
		this.startInNewPage = startInNewPage;
	}
	
	@Override
	public boolean isStartInNewColumn()
	{
		return this.startInNewColumn;
	}
	
	public void setStartInNewColumn(final boolean startInNewColumn)
	{
		this.startInNewColumn = startInNewColumn;
	}
	
	@Override
	public boolean isReprintHeaderOnEachPage()
	{
		return this.reprintHeaderOnEachPage;
	}
	
	public void setReprintHeaderOnEachPage(final boolean reprintHeaderOnEachPage)
	{
		this.reprintHeaderOnEachPage = reprintHeaderOnEachPage;
	}
	
	@Override
	public boolean isResetPageNumber()
	{
		return this.resetPageNumber;
	}
	
	public void setResetPageNumber(final boolean resetPageNumber)
	{
		this.resetPageNumber = resetPageNumber;
	}
	
	@Override
	public Integer getMinHeightToStartNewPage()
	{
		return this.minHeightToStartNewPage;
	}
	
	public void setMinHeightToStartNewPage(final Integer minHeightToStartNewPage)
	{
		this.minHeightToStartNewPage = minHeightToStartNewPage;
	}
	
	@Override
	public GroupFooterPosition getFooterPosition()
	{
		return this.footerPosition;
	}
	
	public void setFooterPosition(final GroupFooterPosition footerPosition)
	{
		this.footerPosition = footerPosition;
	}
	
	@Override
	public boolean isKeepTogether()
	{
		return this.keepTogether;
	}
	
	public void setKeepTogether(final boolean keepTogether)
	{
		this.keepTogether = keepTogether;
	}
	
	@Override
	public boolean isHeaderWithSubtotal()
	{
		return this.headerWithSubtotal;
	}
	
	public void setHeaderWithSubtotal(final boolean headerWithSubtotal)
	{
		this.headerWithSubtotal = headerWithSubtotal;
	}
}
