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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.base.DRDesignHyperLink;
import software.xdev.dynamicreports.design.definition.component.DRIDesignHyperLinkComponent;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;


public abstract class DRDesignHyperlinkComponent extends DRDesignComponent implements DRIDesignHyperLinkComponent
{

	private DRIDesignExpression anchorNameExpression;
	private Integer bookmarkLevel;
	private DRDesignHyperLink hyperLink;
	
	public DRDesignHyperlinkComponent(final String name)
	{
		super(name);
	}
	
	@Override
	public DRIDesignExpression getAnchorNameExpression()
	{
		return this.anchorNameExpression;
	}
	
	public void setAnchorNameExpression(final DRIDesignExpression anchorNameExpression)
	{
		this.anchorNameExpression = anchorNameExpression;
	}
	
	@Override
	public Integer getBookmarkLevel()
	{
		return this.bookmarkLevel;
	}
	
	public void setBookmarkLevel(final Integer bookmarkLevel)
	{
		this.bookmarkLevel = bookmarkLevel;
	}
	
	@Override
	public DRDesignHyperLink getHyperLink()
	{
		return this.hyperLink;
	}
	
	public void setHyperLink(final DRDesignHyperLink hyperLink)
	{
		this.hyperLink = hyperLink;
	}
}
