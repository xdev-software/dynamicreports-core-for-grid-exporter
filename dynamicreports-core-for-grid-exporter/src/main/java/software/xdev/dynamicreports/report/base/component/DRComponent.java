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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRTableOfContentsHeading;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public abstract class DRComponent implements DRIComponent
{

	private DRIReportStyle style;
	private DRIExpression<Boolean> printWhenExpression;
	private Boolean removeLineWhenBlank;
	private List<DRIPropertyExpression> propertyExpressions;
	private DRTableOfContentsHeading tableOfContentsHeading;
	
	public DRComponent()
	{
		this.init();
	}
	
	protected void init()
	{
		this.propertyExpressions = new ArrayList<>();
	}
	
	@Override
	public DRIReportStyle getStyle()
	{
		return this.style;
	}
	
	public void setStyle(final DRIReportStyle style)
	{
		this.style = style;
	}
	
	@Override
	public DRIExpression<Boolean> getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}
	
	public void setPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.printWhenExpression = printWhenExpression;
	}
	
	@Override
	public Boolean getRemoveLineWhenBlank()
	{
		return this.removeLineWhenBlank;
	}
	
	public void setRemoveLineWhenBlank(final Boolean removeLineWhenBlank)
	{
		this.removeLineWhenBlank = removeLineWhenBlank;
	}
	
	@Override
	public List<DRIPropertyExpression> getPropertyExpressions()
	{
		return this.propertyExpressions;
	}
	
	public void setPropertyExpressions(final List<DRIPropertyExpression> propertyExpressions)
	{
		this.propertyExpressions = propertyExpressions;
	}
	
	public void addPropertyExpression(final DRIPropertyExpression propertyExpression)
	{
		Validate.notNull(propertyExpression, "propertyExpression must not be null");
		this.propertyExpressions.add(propertyExpression);
	}
	
	@Override
	public DRTableOfContentsHeading getTableOfContentsHeading()
	{
		return this.tableOfContentsHeading;
	}
	
	public void setTableOfContentsHeading(final DRTableOfContentsHeading tableOfContentsHeading)
	{
		this.tableOfContentsHeading = tableOfContentsHeading;
	}
}
