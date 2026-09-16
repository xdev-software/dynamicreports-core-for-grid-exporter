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

import software.xdev.dynamicreports.report.base.component.DRMultiPageList;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class MultiPageListBuilder extends DimensionComponentBuilder<MultiPageListBuilder, DRMultiPageList>
{

	protected MultiPageListBuilder()
	{
		super(new DRMultiPageList());
	}
	
	public MultiPageListBuilder add(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().addComponent(component.build());
		}
		return this;
	}
	
	public MultiPageListBuilder newPage()
	{
		final BreakBuilder pageBreak = Components.pageBreak();
		pageBreak.setPrintWhenExpression(new PageBreakExpression());
		this.getObject().addComponent(pageBreak.build());
		return this;
	}
	
	public MultiPageListBuilder setSplitType(final SplitType splitType)
	{
		this.getObject().setSplitType(splitType);
		return this;
	}
	
	static class PageBreakExpression extends AbstractSimpleExpression<Boolean>
	{

		@Override
		public Boolean evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getMasterParameters().getPageRowNumber() > 0;
		}
	}
}
