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

import software.xdev.dynamicreports.report.base.component.DRDimensionComponent;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;


@SuppressWarnings("unchecked")
public abstract class DimensionComponentBuilder<T extends DimensionComponentBuilder<T, U>,
	U extends DRDimensionComponent>
	extends ComponentBuilder<T, U>
{

	public DimensionComponentBuilder(final U component)
	{
		super(component);
	}
	
	public T setDimension(final Integer width, final Integer height)
	{
		this.getObject().setWidth(width);
		this.getObject().setHeight(height);
		return (T)this;
	}
	
	public T setFixedDimension(final Integer width, final Integer height)
	{
		this.getObject().setWidth(width);
		this.getObject().setHeight(height);
		this.getObject().setWidthType(ComponentDimensionType.FIXED);
		this.getObject().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinDimension(final Integer width, final Integer height)
	{
		this.getObject().setWidth(width);
		this.getObject().setHeight(height);
		this.getObject().setWidthType(ComponentDimensionType.EXPAND);
		this.getObject().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setWidth(final Integer width)
	{
		this.getObject().setWidth(width);
		return (T)this;
	}
	
	public T setFixedWidth(final Integer width)
	{
		this.getObject().setWidth(width);
		this.getObject().setWidthType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinWidth(final Integer width)
	{
		this.getObject().setWidth(width);
		this.getObject().setWidthType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setHeight(final Integer height)
	{
		this.getObject().setHeight(height);
		return (T)this;
	}
	
	public T setFixedHeight(final Integer height)
	{
		this.getObject().setHeight(height);
		this.getObject().setHeightType(ComponentDimensionType.FIXED);
		return (T)this;
	}
	
	public T setMinHeight(final Integer height)
	{
		this.getObject().setHeight(height);
		this.getObject().setHeightType(ComponentDimensionType.EXPAND);
		return (T)this;
	}
	
	public T setPositionType(final ComponentPositionType positionType)
	{
		this.getObject().setPositionType(positionType);
		return (T)this;
	}
	
	public T setStretchType(final StretchType stretchType)
	{
		this.getObject().setStretchType(stretchType);
		return (T)this;
	}
	
	public T setPrintInFirstWholeBand(final Boolean printInFirstWholeBand)
	{
		this.getObject().setPrintInFirstWholeBand(printInFirstWholeBand);
		return (T)this;
	}
	
	public T setPrintWhenDetailOverflows(final Boolean printWhenDetailOverflows)
	{
		this.getObject().setPrintWhenDetailOverflows(printWhenDetailOverflows);
		return (T)this;
	}
	
	public T setPrintWhenGroupChanges(final GroupBuilder<?> group)
	{
		Validate.notNull(group, "group must not be null");
		this.getObject().setPrintWhenGroupChanges(group.getGroup());
		return (T)this;
	}
}
