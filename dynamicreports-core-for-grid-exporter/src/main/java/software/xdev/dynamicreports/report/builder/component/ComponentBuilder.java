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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;


@SuppressWarnings("unchecked")
public abstract class ComponentBuilder<T extends ComponentBuilder<T, U>, U extends DRComponent>
	extends AbstractBuilder<T, U>
{

	protected ComponentBuilder(final U object)
	{
		super(object);
	}
	
	public T setStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().setStyle(style.getStyle());
		}
		else
		{
			this.getObject().setStyle(null);
		}
		return (T)this;
	}
	
	public T setPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T removeLineWhenBlank()
	{
		return this.setRemoveLineWhenBlank(true);
	}
	
	public T setRemoveLineWhenBlank(final Boolean removeLineWhenBlank)
	{
		this.getObject().setRemoveLineWhenBlank(removeLineWhenBlank);
		return (T)this;
	}
	
	public T addProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getComponent().addPropertyExpression(propertyExpression);
		return (T)this;
	}
	
	public T addProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getComponent().addPropertyExpression(Expressions.property(name, valueExpression));
		return (T)this;
	}
	
	public T addProperty(final String name, final String value)
	{
		this.getComponent().addPropertyExpression(Expressions.property(name, value));
		return (T)this;
	}
	
	public T setTableOfContentsHeading(final String label)
	{
		final TableOfContentsHeadingBuilder tocHeading = DynamicReports.tableOfContentsHeading(label);
		return this.setTableOfContentsHeading(tocHeading);
	}
	
	public T setTableOfContentsHeading(final TableOfContentsHeadingBuilder tocHeading)
	{
		Validate.notNull(tocHeading, "tocHeading must not be null");
		this.getComponent().setTableOfContentsHeading(tocHeading.build());
		return (T)this;
	}
	
	public U getComponent()
	{
		return this.build();
	}
}
