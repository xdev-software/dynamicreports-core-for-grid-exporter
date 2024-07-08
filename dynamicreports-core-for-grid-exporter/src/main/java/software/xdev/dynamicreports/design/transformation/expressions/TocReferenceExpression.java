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
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class TocReferenceExpression extends AbstractComplexExpression<String>
{

	private final int level;
	private final String expressionName;
	private final boolean customId;
	private final boolean isCustomValue;
	
	public TocReferenceExpression(
		final int level,
		final String expressionName,
		final DRIExpression<?> labelExpression,
		final DRIExpression<String> anchorNameExpression,
		final DRIExpression<?> customValueExpression)
	{
		this.level = level;
		this.expressionName = expressionName;
		this.customId = anchorNameExpression != null;
		this.addExpression(labelExpression);
		if(anchorNameExpression != null)
		{
			this.addExpression(anchorNameExpression);
		}
		this.isCustomValue = customValueExpression != null;
		if(customValueExpression != null)
		{
			this.addExpression(customValueExpression);
		}
	}
	
	@Override
	public String evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		final String id;
		Object customValue = null;
		if(this.customId)
		{
			id = (String)values.get(1);
			if(this.isCustomValue)
			{
				customValue = values.get(2);
			}
		}
		else
		{
			id = this.expressionName + "_" + reportParameters.getReportRowNumber();
			if(this.isCustomValue)
			{
				customValue = values.get(1);
			}
		}
		final DRICustomValues customValues = (DRICustomValues)reportParameters.getParameterValue(DRICustomValues.NAME);
		final String text = String.valueOf(values.get(0));
		customValues.addTocHeading(this.level, id, text, customValue);
		return null;
	}
}
