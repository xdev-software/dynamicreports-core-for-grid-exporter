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
package software.xdev.dynamicreports.design.base.crosstab;

import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;


public class DRDesignCrosstabMeasure implements DRIDesignCrosstabMeasure
{

	private String name;
	private DRIDesignExpression valueExpression;
	private Calculation calculation;
	private CrosstabPercentageType percentageType;
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	@Override
	public DRIDesignExpression getValueExpression()
	{
		return this.valueExpression;
	}
	
	public void setValueExpression(final DRIDesignExpression valueExpression)
	{
		this.valueExpression = valueExpression;
	}
	
	@Override
	public Calculation getCalculation()
	{
		return this.calculation;
	}
	
	public void setCalculation(final Calculation calculation)
	{
		this.calculation = calculation;
	}
	
	@Override
	public CrosstabPercentageType getPercentageType()
	{
		return this.percentageType;
	}
	
	public void setPercentageType(final CrosstabPercentageType percentageType)
	{
		this.percentageType = percentageType;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		if(this.percentageType != null && this.percentageType.equals(CrosstabPercentageType.GRAND_TOTAL)
			&& !this.calculation.equals(
			Calculation.COUNT) && !this.calculation.equals(Calculation.DISTINCT_COUNT))
		{
			return Double.class;
		}
		return ReportUtils.getVariableValueClass(this.calculation, this.valueExpression.getValueClass());
	}
}
