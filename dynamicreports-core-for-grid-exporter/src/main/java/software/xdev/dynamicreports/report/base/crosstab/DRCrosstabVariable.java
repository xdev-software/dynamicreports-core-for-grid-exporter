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
package software.xdev.dynamicreports.report.base.crosstab;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRCrosstabVariable<T> implements DRICrosstabVariable<T>
{

	private final String name;
	private final DRIExpression<?> valueExpression;
	private final Calculation calculation;
	private CrosstabPercentageType percentageType;
	
	public DRCrosstabVariable(final DRIExpression<?> valueExpression, final Calculation calculation)
	{
		Validate.notNull(valueExpression, "valueExpression must not be null");
		Validate.notNull(calculation, "calculation must not be null");
		this.valueExpression = valueExpression;
		this.calculation = calculation;
		this.name = ReportUtils.generateUniqueName("crosstabMeasure");
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRIExpression<?> getValueExpression()
	{
		return this.valueExpression;
	}
	
	@Override
	public Calculation getCalculation()
	{
		return this.calculation;
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
	@SuppressWarnings("unchecked")
	public Class<? super T> getValueClass()
	{
		if(this.percentageType != null && this.percentageType.equals(CrosstabPercentageType.GRAND_TOTAL)
			&& !this.calculation.equals(
			Calculation.COUNT) && !this.calculation.equals(Calculation.DISTINCT_COUNT))
		{
			return (Class<? super T>)Double.class;
		}
		return (Class<? super T>)ReportUtils.getVariableValueClass(
			this.getCalculation(),
			this.valueExpression.getValueClass());
	}
}
