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

import software.xdev.dynamicreports.design.definition.DRIDesignHyperLink;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;


public class DRDesignHyperLink implements DRIDesignHyperLink
{

	private DRIDesignExpression anchorExpression;
	private DRIDesignExpression pageExpression;
	private DRIDesignExpression referenceExpression;
	private DRIDesignExpression tooltipExpression;
	private String hyperLinkType;
	private String hyperLinkTarget;
	
	@Override
	public DRIDesignExpression getAnchorExpression()
	{
		return this.anchorExpression;
	}
	
	public void setAnchorExpression(final DRIDesignExpression anchorExpression)
	{
		this.anchorExpression = anchorExpression;
	}
	
	@Override
	public DRIDesignExpression getPageExpression()
	{
		return this.pageExpression;
	}
	
	public void setPageExpression(final DRIDesignExpression pageExpression)
	{
		this.pageExpression = pageExpression;
	}
	
	@Override
	public DRIDesignExpression getReferenceExpression()
	{
		return this.referenceExpression;
	}
	
	public void setReferenceExpression(final DRIDesignExpression referenceExpression)
	{
		this.referenceExpression = referenceExpression;
	}
	
	@Override
	public DRIDesignExpression getTooltipExpression()
	{
		return this.tooltipExpression;
	}
	
	public void setTooltipExpression(final DRIDesignExpression tooltipExpression)
	{
		this.tooltipExpression = tooltipExpression;
	}
	
	@Override
	public String getType()
	{
		return this.hyperLinkType;
	}
	
	public void setType(final String hyperLinkType)
	{
		this.hyperLinkType = hyperLinkType;
	}
	
	@Override
	public String getTarget()
	{
		return this.hyperLinkTarget;
	}
	
	public void setTarget(final String hyperLinkTarget)
	{
		this.hyperLinkTarget = hyperLinkTarget;
	}
}
