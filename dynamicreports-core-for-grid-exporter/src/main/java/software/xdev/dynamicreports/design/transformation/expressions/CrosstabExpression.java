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
package software.xdev.dynamicreports.design.transformation.expressions;

import java.util.List;

import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.definition.DRICustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIComplexExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


public class CrosstabExpression<T> extends AbstractComplexExpression<T>
{

	private final DRIExpression<T> expression;
	
	public CrosstabExpression(final DRICrosstab crosstab, final DRIExpression<T> expression) throws DRException
	{
		this.expression = expression;
		if(expression instanceof DRIComplexExpression)
		{
			for(final DRIExpression<?> express : ((DRIComplexExpression<?>)expression).getExpressions())
			{
				this.addExpression(express);
			}
		}
		for(final DRICrosstabVariable<?> variable : crosstab.getVariables())
		{
			this.addExpression(variable);
		}
		for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
		{
			if(measure.getExpression() instanceof DRICrosstabVariable<?>)
			{
				this.addExpression(measure.getExpression());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		final DRICustomValues customValues = (DRICustomValues)reportParameters.getParameterValue(DRICustomValues.NAME);
		for(int i = 0; i < this.getExpressions().size(); i++)
		{
			customValues.setSystemValue(this.getExpressions().get(i).getName(), values.get(i));
		}
		if(this.expression instanceof DRIComplexExpression)
		{
			final DRIComplexExpression<?> express = (DRIComplexExpression<?>)this.expression;
			return (T)express.evaluate(values, reportParameters);
		}
		else
		{
			return reportParameters.getValue(this.expression.getName());
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Class getValueClass()
	{
		return this.expression.getValueClass();
	}
}
