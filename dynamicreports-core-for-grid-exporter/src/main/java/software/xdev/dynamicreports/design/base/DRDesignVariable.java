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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;


public class DRDesignVariable implements DRIDesignVariable
{

	private final String name;
	private DRIDesignExpression valueExpression;
	private DRIDesignExpression initialValueExpression;
	private Calculation calculation;
	private ResetType resetType;
	private DRDesignGroup resetGroup;
	
	public DRDesignVariable()
	{
		this.name = ReportUtils.generateUniqueName("variable");
	}
	
	public DRDesignVariable(final String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return this.name;
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
	public DRIDesignExpression getInitialValueExpression()
	{
		return this.initialValueExpression;
	}
	
	public void setInitialValueExpression(final DRIDesignExpression initialValueExpression)
	{
		this.initialValueExpression = initialValueExpression;
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
	public ResetType getResetType()
	{
		return this.resetType;
	}
	
	public void setResetType(final ResetType resetType)
	{
		this.resetType = resetType;
	}
	
	@Override
	public DRDesignGroup getResetGroup()
	{
		return this.resetGroup;
	}
	
	public void setResetGroup(final DRDesignGroup resetGroup)
	{
		this.resetGroup = resetGroup;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return ReportUtils.getVariableValueClass(this.calculation, this.valueExpression.getValueClass());
	}
}
