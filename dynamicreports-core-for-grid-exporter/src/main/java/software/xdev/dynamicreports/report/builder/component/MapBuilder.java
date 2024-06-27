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

import software.xdev.dynamicreports.report.base.component.DRMap;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class MapBuilder extends DimensionComponentBuilder<MapBuilder, DRMap>
{

	protected MapBuilder()
	{
		super(new DRMap());
	}
	
	public MapBuilder setLatitude(final Float latitude)
	{
		this.getObject().setLatitudeExpression(Expressions.value(latitude));
		return this;
	}
	
	public MapBuilder setLatitude(final DRIExpression<Float> latitudeExpression)
	{
		this.getObject().setLatitudeExpression(latitudeExpression);
		return this;
	}
	
	public MapBuilder setLongitude(final Float longitude)
	{
		this.getObject().setLongitudeExpression(Expressions.value(longitude));
		return this;
	}
	
	public MapBuilder setLongitude(final DRIExpression<Float> longitudeExpression)
	{
		this.getObject().setLongitudeExpression(longitudeExpression);
		return this;
	}
	
	public MapBuilder setZoom(final Integer zoom)
	{
		this.getObject().setZoomExpression(Expressions.value(zoom));
		return this;
	}
	
	public MapBuilder setZoom(final DRIExpression<Integer> zoomExpression)
	{
		this.getObject().setZoomExpression(zoomExpression);
		return this;
	}
}
