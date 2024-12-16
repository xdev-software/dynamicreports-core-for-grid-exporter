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
package software.xdev.dynamicreports.report.base.component;

import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public abstract class DRHyperLinkComponent extends DRDimensionComponent implements DRIHyperLinkComponent
{

	private DRIExpression<String> anchorNameExpression;
	private Integer bookmarkLevel;
	private DRHyperLink hyperLink;
	
	@Override
	public DRIExpression<String> getAnchorNameExpression()
	{
		return this.anchorNameExpression;
	}
	
	public void setAnchorNameExpression(final DRIExpression<String> anchorNameExpression)
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
	public DRHyperLink getHyperLink()
	{
		return this.hyperLink;
	}
	
	public void setHyperLink(final DRHyperLink hyperLink)
	{
		this.hyperLink = hyperLink;
	}
}
