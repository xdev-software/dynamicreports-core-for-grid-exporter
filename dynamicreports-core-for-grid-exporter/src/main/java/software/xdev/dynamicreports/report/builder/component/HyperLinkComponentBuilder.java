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
package software.xdev.dynamicreports.report.builder.component;

import software.xdev.dynamicreports.report.base.component.DRHyperLinkComponent;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("unchecked")
public abstract class HyperLinkComponentBuilder<T extends HyperLinkComponentBuilder<T, U>,
	U extends DRHyperLinkComponent>
	extends DimensionComponentBuilder<T, U>
{

	public HyperLinkComponentBuilder(final U component)
	{
		super(component);
	}
	
	public T setAnchorName(final String anchorName)
	{
		this.getObject().setAnchorNameExpression(Expressions.text(anchorName));
		return (T)this;
	}
	
	public T setAnchorName(final DRIExpression<String> anchorNameExpression)
	{
		this.getObject().setAnchorNameExpression(anchorNameExpression);
		return (T)this;
	}
	
	public T setBookmarkLevel(final Integer bookmarkLevel)
	{
		this.getObject().setBookmarkLevel(bookmarkLevel);
		return (T)this;
	}
	
	public T setHyperLink(final HyperLinkBuilder hyperLink)
	{
		if(hyperLink != null)
		{
			this.getObject().setHyperLink(hyperLink.getHyperLink());
		}
		else
		{
			this.getObject().setHyperLink(null);
		}
		return (T)this;
	}
}
