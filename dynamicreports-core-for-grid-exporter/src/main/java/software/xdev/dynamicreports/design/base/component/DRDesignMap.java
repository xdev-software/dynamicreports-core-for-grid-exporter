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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.component.DRIDesignMap;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;


public class DRDesignMap extends DRDesignComponent implements DRIDesignMap
{

	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;
	private DRIDesignExpression latitudeExpression;
	private DRIDesignExpression longitudeExpression;
	private DRIDesignExpression zoomExpression;
	
	public DRDesignMap()
	{
		super("map");
	}
	
	@Override
	public EvaluationTime getEvaluationTime()
	{
		return this.evaluationTime;
	}
	
	public void setEvaluationTime(final EvaluationTime evaluationTime)
	{
		this.evaluationTime = evaluationTime;
	}
	
	@Override
	public DRDesignGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
	
	public void setEvaluationGroup(final DRDesignGroup evaluationGroup)
	{
		this.evaluationGroup = evaluationGroup;
	}
	
	@Override
	public DRIDesignExpression getLatitudeExpression()
	{
		return this.latitudeExpression;
	}
	
	public void setLatitudeExpression(final DRIDesignExpression latitudeExpression)
	{
		this.latitudeExpression = latitudeExpression;
	}
	
	@Override
	public DRIDesignExpression getLongitudeExpression()
	{
		return this.longitudeExpression;
	}
	
	public void setLongitudeExpression(final DRIDesignExpression longitudeExpression)
	{
		this.longitudeExpression = longitudeExpression;
	}
	
	@Override
	public DRIDesignExpression getZoomExpression()
	{
		return this.zoomExpression;
	}
	
	public void setZoomExpression(final DRIDesignExpression zoomExpression)
	{
		this.zoomExpression = zoomExpression;
	}
}
