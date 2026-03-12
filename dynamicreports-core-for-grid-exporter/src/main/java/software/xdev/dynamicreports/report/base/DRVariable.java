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
package software.xdev.dynamicreports.report.base;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.definition.DRIVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRVariable<T> implements DRIVariable<T>
{

	private final String name;
	private final DRIExpression<?> valueExpression;
	private DRIExpression<?> initialValueExpression;
	private final Calculation calculation;
	private Evaluation resetType;
	private DRGroup resetGroup;
	
	public DRVariable(final DRIExpression<?> valueExpression, final Calculation calculation)
	{
		this(ReportUtils.generateUniqueName("variable"), valueExpression, calculation);
	}
	
	public DRVariable(final String name, final DRIExpression<?> valueExpression, final Calculation calculation)
	{
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueExpression, "valueExpression must not be null");
		Validate.notNull(calculation, "calculation must not be null");
		this.name = name;
		this.valueExpression = valueExpression;
		this.calculation = calculation;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRIExpression<?> getInitialValueExpression()
	{
		return this.initialValueExpression;
	}
	
	public void setInitialValueExpression(final DRIExpression<?> initialValueExpression)
	{
		this.initialValueExpression = initialValueExpression;
	}
	
	@Override
	public Calculation getCalculation()
	{
		return this.calculation;
	}
	
	@Override
	public Evaluation getResetType()
	{
		return this.resetType;
	}
	
	public void setResetType(final Evaluation resetType)
	{
		this.resetType = resetType;
	}
	
	@Override
	public DRGroup getResetGroup()
	{
		return this.resetGroup;
	}
	
	public void setResetGroup(final DRGroup resetGroup)
	{
		this.resetGroup = resetGroup;
	}
	
	@Override
	public DRIExpression<?> getValueExpression()
	{
		return this.valueExpression;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Class<? super T> getValueClass()
	{
		return (Class<? super T>)ReportUtils.getVariableValueClass(
			this.getCalculation(),
			this.valueExpression.getValueClass());
	}
}
