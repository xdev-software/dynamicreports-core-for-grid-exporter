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
package software.xdev.dynamicreports.report.builder.tableofcontents;

import software.xdev.dynamicreports.report.base.DRTableOfContentsHeading;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class TableOfContentsHeadingBuilder
	extends AbstractBuilder<TableOfContentsHeadingBuilder, DRTableOfContentsHeading>
{

	public TableOfContentsHeadingBuilder()
	{
		super(new DRTableOfContentsHeading());
	}
	
	public TableOfContentsHeadingBuilder setParentHeading(final TableOfContentsHeadingBuilder parentHeading)
	{
		if(parentHeading != null)
		{
			this.getObject().setParentHeading(parentHeading.build());
		}
		else
		{
			this.getObject().setParentHeading(null);
		}
		return this;
	}
	
	public TableOfContentsHeadingBuilder setLabel(final String label)
	{
		this.getObject().setLabelExpression(Expressions.text(label));
		return this;
	}
	
	public TableOfContentsHeadingBuilder setLabel(final DRIExpression<String> labelExpression)
	{
		this.getObject().setLabelExpression(labelExpression);
		return this;
	}
	
	public TableOfContentsHeadingBuilder setCustomValue(final Object customValue)
	{
		this.getObject().setCustomValueExpression(Expressions.value(customValue));
		return this;
	}
	
	public TableOfContentsHeadingBuilder setCustomValue(final DRIExpression<?> customValueExpression)
	{
		this.getObject().setCustomValueExpression(customValueExpression);
		return this;
	}
	
	public DRTableOfContentsHeading getTableOfContentsHeading()
	{
		return this.build();
	}
}
