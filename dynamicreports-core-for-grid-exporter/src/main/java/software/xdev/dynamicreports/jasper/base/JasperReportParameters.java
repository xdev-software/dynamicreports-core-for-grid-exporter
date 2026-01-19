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
package software.xdev.dynamicreports.jasper.base;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabRowCounter;
import software.xdev.dynamicreports.jasper.constant.ValueType;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.exception.DRReportException;


public class JasperReportParameters implements ReportParameters
{
	public static final String MASTER_REPORT_PARAMETERS = "MASTER_REPORT_PARAMETERS";
	
	private final JasperScriptlet jasperScriptlet;
	
	protected JasperReportParameters(final JasperScriptlet jasperScriptlet)
	{
		this.jasperScriptlet = jasperScriptlet;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(final String name)
	{
		final ValueType type = this.jasperScriptlet.getValueType(name);
		if(type != null)
		{
			switch(type)
			{
				case FIELD:
					return (T)this.getFieldValue(name);
				case VARIABLE:
					return (T)this.getVariableValue(name);
				case PARAMETER:
					return (T)this.getParameterValue(name);
				case SIMPLE_EXPRESSION:
					return (T)this.getSimpleExpressionValue(name);
				case COMPLEX_EXPRESSION:
					return (T)this.getComplexExpressionValue(name);
				case SYSTEM_EXPRESSION:
					return (T)this.getSystemExpressionValue(name);
				default:
					break;
			}
		}
		
		throw new DRReportException("Value " + name + " not found");
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getValue(final DRIValue<T> value)
	{
		return (T)this.getValue(value.getName());
	}
	
	// field
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getFieldValue(final String name)
	{
		try
		{
			return (T)this.jasperScriptlet.getFieldValue(name);
		}
		catch(final JRScriptletException e)
		{
			throw new DRReportException(e);
		}
	}
	
	// variable
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getVariableValue(final String name)
	{
		try
		{
			return (T)this.jasperScriptlet.getVariableValue(name);
		}
		catch(final JRScriptletException e)
		{
			throw new DRReportException(e);
		}
	}
	
	@Override
	public Integer getPageNumber()
	{
		return (Integer)this.getVariableValue(JRVariable.PAGE_NUMBER);
	}
	
	@Override
	public Integer getColumnNumber()
	{
		return (Integer)this.getVariableValue(JRVariable.COLUMN_NUMBER);
	}
	
	@Override
	public Integer getReportRowNumber()
	{
		return (Integer)this.getVariableValue(JRVariable.REPORT_COUNT);
	}
	
	@Override
	public Integer getPageRowNumber()
	{
		return (Integer)this.getVariableValue(JRVariable.PAGE_COUNT);
	}
	
	@Override
	public Integer getColumnRowNumber()
	{
		return (Integer)this.getVariableValue(JRVariable.COLUMN_COUNT);
	}
	
	@Override
	public Integer getCrosstabRowNumber()
	{
		final CrosstabRowCounter counter = (CrosstabRowCounter)this.getValue(CROSSTAB_ROW_COUNTER);
		if(counter != null)
		{
			return counter.getRowNumber();
		}
		return 0;
	}
	
	@Override
	public Integer getGroupCount(final String groupName)
	{
		return (Integer)this.getVariableValue(groupName + "_COUNT");
	}
	
	// parameter
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getParameterValue(final String name)
	{
		try
		{
			return (T)((Map<?, ?>)this.jasperScriptlet.getParameterValue(JRParameter.REPORT_PARAMETERS_MAP)).get(name);
		}
		catch(final JRScriptletException e)
		{
			throw new DRReportException(e);
		}
	}
	
	@Override
	public Connection getConnection()
	{
		return (Connection)this.getParameterValue(JRParameter.REPORT_CONNECTION);
	}
	
	@Override
	public Locale getLocale()
	{
		return (Locale)this.getParameterValue(JRParameter.REPORT_LOCALE);
	}
	
	@Override
	public DRIScriptlet getScriptlet(final String name)
	{
		return ((CustomScriptlet)this.getParameterValue(
			name + JRScriptlet.SCRIPTLET_PARAMETER_NAME_SUFFIX)).getScriptlet();
	}
	
	@Override
	public String getMessage(final String key)
	{
		return ((ResourceBundle)this.getParameterValue(JRParameter.REPORT_RESOURCE_BUNDLE)).getString(key);
	}
	
	@Override
	public String getMessage(final String key, final Object[] arguments)
	{
		String message = this.getMessage(key);
		if(arguments != null)
		{
			final MessageFormat format = new MessageFormat(message, this.getLocale());
			message = format.format(arguments);
		}
		return message;
	}
	
	// simple expression
	private Object getSimpleExpressionValue(final String name)
	{
		return this.jasperScriptlet.getSimpleExpression(name).evaluate(this);
	}
	
	// complex expression
	private Object getComplexExpressionValue(final String name)
	{
		final List<Object> values = new ArrayList<>();
		final DRIDesignComplexExpression complexExpression = this.jasperScriptlet.getComplexExpression(name);
		for(final DRIDesignExpression valueExpression : complexExpression.getExpressions())
		{
			values.add(this.getValue(valueExpression.getName()));
		}
		return complexExpression.evaluate(values, this);
	}
	
	// system expression
	private Object getSystemExpressionValue(final String name)
	{
		return this.jasperScriptlet.getSystemValue(name);
	}
	
	@Override
	public ReportParameters getMasterParameters()
	{
		return (ReportParameters)this.getParameterValue(MASTER_REPORT_PARAMETERS);
	}
	
	@Override
	public Integer getSubreportWidth()
	{
		return this.jasperScriptlet.getSubreportWidth();
	}
	
	@Override
	public String toString()
	{
		final StringBuilder result = new StringBuilder();
		
		result.append("FIELDS:\n");
		Collection<String> names = this.jasperScriptlet.getFields();
		for(final String name : names)
		{
			result.append(name + " = " + this.getFieldValue(name));
			result.append("\n");
		}
		
		result.append("VARIABLES:\n");
		names = this.jasperScriptlet.getVariables();
		for(final String name : names)
		{
			result.append(name + " = " + this.getVariableValue(name));
			result.append("\n");
		}
		
		result.append("PARAMETERS:\n");
		names = this.jasperScriptlet.getParameters();
		for(final String name : names)
		{
			result.append(name + " = " + this.getParameterValue(name));
			result.append("\n");
		}
		
		return result.toString();
	}
}
