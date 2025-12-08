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

import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.component.DRIBooleanField;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRBooleanField extends DRHyperLinkComponent implements DRIBooleanField
{

	private DRIExpression<Boolean> valueExpression;
	private BooleanComponentType componentType;
	private Boolean emptyWhenNullValue;
	private Integer imageWidth;
	private Integer imageHeight;
	private HorizontalImageAlignment horizontalImageAlignment;
	private HorizontalTextAlignment horizontalTextAlignment;
	
	@Override
	public DRIExpression<Boolean> getValueExpression()
	{
		return this.valueExpression;
	}
	
	public void setValueExpression(final DRIExpression<Boolean> valueExpression)
	{
		Validate.notNull(valueExpression, "valueExpression must not be null");
		this.valueExpression = valueExpression;
	}
	
	@Override
	public BooleanComponentType getComponentType()
	{
		return this.componentType;
	}
	
	public void setComponentType(final BooleanComponentType componentType)
	{
		this.componentType = componentType;
	}
	
	@Override
	public Boolean getEmptyWhenNullValue()
	{
		return this.emptyWhenNullValue;
	}
	
	public void setEmptyWhenNullValue(final Boolean emptyWhenNullValue)
	{
		this.emptyWhenNullValue = emptyWhenNullValue;
	}
	
	@Override
	public Integer getImageWidth()
	{
		return this.imageWidth;
	}
	
	public void setImageWidth(final Integer imageWidth)
	{
		if(imageWidth != null)
		{
			Validate.isTrue(imageWidth >= 0, "imageWidth must be >= 0");
		}
		this.imageWidth = imageWidth;
	}
	
	@Override
	public Integer getImageHeight()
	{
		return this.imageHeight;
	}
	
	public void setImageHeight(final Integer imageHeight)
	{
		if(imageHeight != null)
		{
			Validate.isTrue(imageHeight >= 0, "imageHeight must be >= 0");
		}
		this.imageHeight = imageHeight;
	}
	
	@Override
	public HorizontalImageAlignment getHorizontalImageAlignment()
	{
		return this.horizontalImageAlignment;
	}
	
	public void setHorizontalImageAlignment(final HorizontalImageAlignment horizontalImageAlignment)
	{
		this.horizontalImageAlignment = horizontalImageAlignment;
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
