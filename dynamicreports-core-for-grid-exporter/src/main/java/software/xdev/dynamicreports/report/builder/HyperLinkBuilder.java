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
package software.xdev.dynamicreports.report.builder;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.HyperLinkTarget;
import software.xdev.dynamicreports.report.constant.HyperLinkType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class HyperLinkBuilder extends AbstractBuilder<HyperLinkBuilder, DRHyperLink>
{

	protected HyperLinkBuilder()
	{
		super(new DRHyperLink());
	}
	
	protected HyperLinkBuilder(final String link)
	{
		super(new DRHyperLink());
		this.setLink(link);
	}
	
	protected HyperLinkBuilder(final DRIExpression<String> linkExpression)
	{
		super(new DRHyperLink());
		this.setLink(linkExpression);
	}
	
	public HyperLinkBuilder setLink(final String link)
	{
		Validate.notNull(link, "link must not be null");
		return this.setLink(Expressions.text(link));
	}
	
	public HyperLinkBuilder setLink(final DRIExpression<String> linkExpression)
	{
		Validate.notNull(linkExpression, "linkExpression must not be null");
		this.getObject().setReferenceExpression(linkExpression);
		this.getObject().setType(HyperLinkType.REFERENCE.name());
		return this;
	}
	
	public HyperLinkBuilder setAnchor(final String anchor)
	{
		this.getObject().setAnchorExpression(Expressions.text(anchor));
		return this;
	}
	
	public HyperLinkBuilder setAnchor(final DRIExpression<String> anchorExpression)
	{
		this.getObject().setAnchorExpression(anchorExpression);
		return this;
	}
	
	public HyperLinkBuilder setPage(final Integer page)
	{
		this.getObject().setPageExpression(Expressions.value(page));
		return this;
	}
	
	public HyperLinkBuilder setPage(final DRIExpression<Integer> pageExpression)
	{
		this.getObject().setPageExpression(pageExpression);
		return this;
	}
	
	public HyperLinkBuilder setReference(final String reference)
	{
		this.getObject().setReferenceExpression(Expressions.text(reference));
		return this;
	}
	
	public HyperLinkBuilder setReference(final DRIExpression<String> referenceExpression)
	{
		this.getObject().setReferenceExpression(referenceExpression);
		return this;
	}
	
	public HyperLinkBuilder setTooltip(final String tooltip)
	{
		this.getObject().setTooltipExpression(Expressions.text(tooltip));
		return this;
	}
	
	public HyperLinkBuilder setTooltip(final DRIExpression<String> tooltipExpression)
	{
		this.getObject().setTooltipExpression(tooltipExpression);
		return this;
	}
	
	public HyperLinkBuilder setType(final HyperLinkType hyperLinkType)
	{
		this.getObject().setType(hyperLinkType.name());
		return this;
	}
	
	public HyperLinkBuilder setType(final String hyperLinkType)
	{
		this.getObject().setType(hyperLinkType);
		return this;
	}
	
	public HyperLinkBuilder setTarget(final HyperLinkTarget hyperLinkTarget)
	{
		this.getObject().setTarget(hyperLinkTarget.name());
		return this;
	}
	
	public HyperLinkBuilder setTarget(final String hyperLinkTarget)
	{
		this.getObject().setTarget(hyperLinkTarget);
		return this;
	}
	
	public DRHyperLink getHyperLink()
	{
		return this.build();
	}
}
