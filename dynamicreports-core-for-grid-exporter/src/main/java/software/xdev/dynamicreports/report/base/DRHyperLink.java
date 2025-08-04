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
package software.xdev.dynamicreports.report.base;

import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRHyperLink implements DRIHyperLink
{

	private DRIExpression<String> anchorExpression;
	private DRIExpression<Integer> pageExpression;
	private DRIExpression<String> referenceExpression;
	private DRIExpression<String> tooltipExpression;
	private String hyperLinkType;
	private String hyperLinkTarget;
	
	@Override
	public DRIExpression<String> getAnchorExpression()
	{
		return this.anchorExpression;
	}
	
	public void setAnchorExpression(final DRIExpression<String> anchorExpression)
	{
		this.anchorExpression = anchorExpression;
	}
	
	@Override
	public DRIExpression<Integer> getPageExpression()
	{
		return this.pageExpression;
	}
	
	public void setPageExpression(final DRIExpression<Integer> pageExpression)
	{
		this.pageExpression = pageExpression;
	}
	
	@Override
	public DRIExpression<String> getReferenceExpression()
	{
		return this.referenceExpression;
	}
	
	public void setReferenceExpression(final DRIExpression<String> referenceExpression)
	{
		this.referenceExpression = referenceExpression;
	}
	
	@Override
	public DRIExpression<String> getTooltipExpression()
	{
		return this.tooltipExpression;
	}
	
	public void setTooltipExpression(final DRIExpression<String> tooltipExpression)
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
