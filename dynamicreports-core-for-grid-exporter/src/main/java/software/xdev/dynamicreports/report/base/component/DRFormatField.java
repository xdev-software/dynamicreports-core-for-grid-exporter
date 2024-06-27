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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.component.DRIFormatField;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public abstract class DRFormatField extends DRHyperLinkComponent implements DRIFormatField
{

	private DRIExpression<String> formatExpression;
	private HorizontalTextAlignment horizontalTextAlignment;
	
	@Override
	public DRIExpression<String> getFormatExpression()
	{
		return this.formatExpression;
	}
	
	public void setFormatExpression(final DRIExpression<String> formatExpression)
	{
		Validate.notNull(formatExpression, "formatExpression must not be null");
		this.formatExpression = formatExpression;
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return this.horizontalTextAlignment;
	}
	
	public void setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.horizontalTextAlignment = horizontalTextAlignment;
	}
}
