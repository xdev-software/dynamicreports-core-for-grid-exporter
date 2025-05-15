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

import java.util.HashMap;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class SerieValueExpression extends AbstractSimpleExpression<Number>
{

	private final DRIDesignExpression valueExpression;
	private final DRIDesignExpression serieExpression;
	private final ResetType resetType;
	private final DRDesignGroup resetGroup;
	private final String key;
	private Object resetValue;
	private Map<Object, Double> values;
	
	public SerieValueExpression(
		final DRIDesignExpression valueExpression,
		final DRIDesignExpression serieExpression,
		final ResetType resetType,
		final DRDesignGroup resetGroup,
		final String key)
	{
		this.valueExpression = valueExpression;
		this.serieExpression = serieExpression;
		this.resetType = resetType;
		this.resetGroup = resetGroup;
		this.key = key;
	}
	
	@Override
	public Number evaluate(final ReportParameters reportParameters)
	{
		if(reportParameters.getReportRowNumber() <= 1)
		{
			this.resetValue = null;
			this.values = new HashMap<>();
		}
		
		Object resetValue = null;
		switch(this.resetType)
		{
			case NONE:
			case REPORT:
				break;
			case PAGE:
				resetValue = reportParameters.getPageNumber();
				break;
			case COLUMN:
				resetValue = reportParameters.getColumnNumber();
				break;
			case GROUP:
				resetValue = reportParameters.getValue(this.resetGroup.getGroupExpression().getName());
				break;
			default:
				throw new DRDesignReportException("Reset type " + this.resetType.name() + " not supported");
		}
		if(this.resetValue != null && !this.resetValue.equals(resetValue))
		{
			this.values = new HashMap<>();
		}
		this.resetValue = resetValue;
		
		final Object keyValue;
		if(this.key != null)
		{
			keyValue =
				reportParameters.getValue(this.valueExpression.getName()) + "_" + reportParameters.getValue(this.key);
		}
		else
		{
			keyValue = reportParameters.getValue(this.valueExpression.getName());
		}
		final Number serieValue = reportParameters.getValue(this.serieExpression.getName());
		Double value = this.values.get(keyValue);
		if(serieValue != null)
		{
			if(value == null)
			{
				value = serieValue.doubleValue();
			}
			else
			{
				value += serieValue.doubleValue();
			}
			this.values.put(keyValue, value);
		}
		
		return value;
	}
}
