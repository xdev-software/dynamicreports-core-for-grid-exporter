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
package software.xdev.dynamicreports.report.builder.column;

import software.xdev.dynamicreports.report.base.column.DRColumn;
import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.component.DRDimensionComponent;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.definition.component.DRIDimensionComponent;
import software.xdev.dynamicreports.report.exception.DRReportException;


public class ComponentColumnBuilder extends ColumnBuilder<ComponentColumnBuilder, DRColumn<DRComponent>>
{
	protected ComponentColumnBuilder(final ComponentBuilder<?, ?> component)
	{
		super(new DRColumn<>(component.getComponent()));
	}
	
	public ComponentColumnBuilder setWidth(final Integer width)
	{
		this.getDimensionComponent().setWidth(width);
		return this;
	}
	
	public ComponentColumnBuilder setFixedWidth(final Integer width)
	{
		this.getDimensionComponent().setWidth(width);
		this.getDimensionComponent().setWidthType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ComponentColumnBuilder setMinWidth(final Integer width)
	{
		this.getDimensionComponent().setWidth(width);
		this.getDimensionComponent().setWidthType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	public ComponentColumnBuilder setHeight(final Integer height)
	{
		this.getDimensionComponent().setHeight(height);
		return this;
	}
	
	public ComponentColumnBuilder setFixedHeight(final Integer height)
	{
		this.getDimensionComponent().setHeight(height);
		this.getDimensionComponent().setHeightType(ComponentDimensionType.FIXED);
		return this;
	}
	
	public ComponentColumnBuilder setMinHeight(final Integer height)
	{
		this.getDimensionComponent().setHeight(height);
		this.getDimensionComponent().setHeightType(ComponentDimensionType.EXPAND);
		return this;
	}
	
	private DRDimensionComponent getDimensionComponent()
	{
		if(!(this.getObject().getComponent() instanceof DRIDimensionComponent))
		{
			throw new DRReportException(
				"Column component" + this.getObject().getComponent().getClass().getName()
					+ "is not a dimension component"
					+ ".");
		}
		return (DRDimensionComponent)this.getObject().getComponent();
	}
}
