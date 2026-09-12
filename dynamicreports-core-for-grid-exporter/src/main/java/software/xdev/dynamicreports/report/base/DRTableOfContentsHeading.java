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

import software.xdev.dynamicreports.report.definition.DRITableOfContentsHeading;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRTableOfContentsHeading implements DRITableOfContentsHeading
{

	private DRTableOfContentsHeading parentHeading;
	private DRIExpression<String> labelExpression;
	private DRIExpression<?> customValueExpression;
	
	@Override
	public DRTableOfContentsHeading getParentHeading()
	{
		return this.parentHeading;
	}
	
	public void setParentHeading(final DRTableOfContentsHeading parentHeading)
	{
		this.parentHeading = parentHeading;
	}
	
	@Override
	public DRIExpression<String> getLabelExpression()
	{
		return this.labelExpression;
	}
	
	public void setLabelExpression(final DRIExpression<String> labelExpression)
	{
		this.labelExpression = labelExpression;
	}
	
	@Override
	public DRIExpression<?> getCustomValueExpression()
	{
		return this.customValueExpression;
	}
	
	public void setCustomValueExpression(final DRIExpression<?> customValueExpression)
	{
		this.customValueExpression = customValueExpression;
	}
}
