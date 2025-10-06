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

import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;


public abstract class DRDimensionComponent extends DRComponent implements DRIDimensionComponent
{

	private Integer width;
	private Integer height;
	private ComponentDimensionType widthType;
	private ComponentDimensionType heightType;
	private ComponentPositionType positionType;
	private StretchType stretchType;
	private Boolean printInFirstWholeBand;
	private Boolean printWhenDetailOverflows;
	private DRIGroup printWhenGroupChanges;
	
	@Override
	public Integer getWidth()
	{
		return this.width;
	}
	
	public void setWidth(final Integer width)
	{
		if(width != null)
		{
			Validate.isTrue(width >= 0, "width must be >= 0");
		}
		this.width = width;
	}
	
	@Override
	public Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final Integer height)
	{
		if(height != null)
		{
			Validate.isTrue(height >= 0, "height must be >= 0");
		}
		this.height = height;
	}
	
	@Override
	public ComponentDimensionType getWidthType()
	{
		return this.widthType;
	}
	
	public void setWidthType(final ComponentDimensionType widthType)
	{
		this.widthType = widthType;
	}
	
	@Override
	public ComponentDimensionType getHeightType()
	{
		return this.heightType;
	}
	
	public void setHeightType(final ComponentDimensionType heightType)
	{
		this.heightType = heightType;
	}
	
	@Override
	public ComponentPositionType getPositionType()
	{
		return this.positionType;
	}
	
	public void setPositionType(final ComponentPositionType positionType)
	{
		this.positionType = positionType;
	}
	
	@Override
	public StretchType getStretchType()
	{
		return this.stretchType;
	}
	
	public void setStretchType(final StretchType stretchType)
	{
		this.stretchType = stretchType;
	}
	
	@Override
	public Boolean getPrintInFirstWholeBand()
	{
		return this.printInFirstWholeBand;
	}
	
	public void setPrintInFirstWholeBand(final Boolean printInFirstWholeBand)
	{
		this.printInFirstWholeBand = printInFirstWholeBand;
	}
	
	@Override
	public Boolean getPrintWhenDetailOverflows()
	{
		return this.printWhenDetailOverflows;
	}
	
	public void setPrintWhenDetailOverflows(final Boolean printWhenDetailOverflows)
	{
		this.printWhenDetailOverflows = printWhenDetailOverflows;
	}
	
	@Override
	public DRIGroup getPrintWhenGroupChanges()
	{
		return this.printWhenGroupChanges;
	}
	
	public void setPrintWhenGroupChanges(final DRIGroup printWhenGroupChanges)
	{
		this.printWhenGroupChanges = printWhenGroupChanges;
	}
}
