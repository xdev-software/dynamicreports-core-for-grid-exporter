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
package software.xdev.dynamicreports.jasper.transformation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGenericElementParameter;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGenericElementParameter;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.jasper.constant.ValueType;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.report.constant.SystemExpression;


public abstract class AbstractExpressionTransform
{
	private static final String VALUE = "$P'{'{0}'}'.getValue(\"{1}\")";
	private static final String FIELD_VALUE = "$F'{'{0}'}'";
	private static final String VARIABLE_VALUE = "$V'{'{0}'}'";
	private static final String PARAMETER_VALUE = "$P'{'{0}'}'";
	private static final String COMPLEX_VALUE = "$P'{'{0}'}'.getValue(\"{1}\", new Object[]'{'{2}'}')";
	
	private final Map<String, JRDesignExpression> expressions;
	
	public AbstractExpressionTransform()
	{
		this.expressions = new HashMap<>();
	}
	
	public void transform()
	{
		for(final DRIDesignField field : this.getFields())
		{
			this.addField(field);
		}
		for(final DRIDesignSystemExpression expression : this.getSystemExpressions())
		{
			this.addSystemExpression(expression);
		}
		for(final DRIDesignJasperExpression expression : this.getJasperExpressions())
		{
			this.addJasperExpression(expression);
		}
		for(final DRIDesignSimpleExpression expression : this.getSimpleExpressions())
		{
			this.addSimpleExpression(expression);
		}
		for(final DRIDesignComplexExpression complexExpression : this.getComplexExpressions())
		{
			this.addComplexExpression(complexExpression);
		}
		for(final DRIDesignVariable variable : this.getVariables())
		{
			this.addVariable(variable);
		}
		for(final DRIDesignSort sort : this.getSorts())
		{
			this.addSort(sort);
		}
	}
	
	private void addSystemExpression(final DRIDesignSystemExpression systemExpression)
	{
		if(systemExpression == null)
		{
			return;
		}
		this.getCustomValues().addValueType(systemExpression.getName(), ValueType.SYSTEM_EXPRESSION);
		this.addExpression(systemExpression);
	}
	
	private void addJasperExpression(final DRIDesignJasperExpression jasperExpression)
	{
		if(jasperExpression == null)
		{
			return;
		}
		this.addExpression(jasperExpression);
	}
	
	protected void addSimpleExpression(final DRIDesignSimpleExpression simpleExpression)
	{
		if(simpleExpression == null)
		{
			return;
		}
		this.getCustomValues().addSimpleExpression(simpleExpression);
		this.addExpression(simpleExpression);
	}
	
	private void addField(final DRIDesignField field)
	{
		try
		{
			if(!field.isExternal())
			{
				this.addField(this.field(field));
			}
			this.getCustomValues().addValueType(field.getName(), ValueType.FIELD);
			this.addExpression(field);
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for field \"" + field.getName() + "\"", e);
		}
	}
	
	private void addVariable(final DRIDesignVariable variable)
	{
		try
		{
			this.addVariable(this.variable(variable));
			this.getCustomValues().addValueType(variable.getName(), ValueType.VARIABLE);
			this.addExpression(variable);
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for variable \"" + variable.getName() + "\"", e);
		}
	}
	
	protected void addComplexExpression(final DRIDesignComplexExpression complexExpression)
	{
		if(complexExpression == null)
		{
			return;
		}
		this.getCustomValues().addComplexExpression(complexExpression);
		this.addExpression(complexExpression);
	}
	
	private void addExpression(final DRIDesignExpression expression)
	{
		if(this.expressions.containsKey(expression.getName()))
		{
			throw new JasperDesignException("Duplicate declaration of expression \"" + expression.getName() + "\"");
		}
		this.expressions.put(expression.getName(), this.expression(expression));
	}
	
	private void addSort(final DRIDesignSort sort)
	{
		try
		{
			this.addSort(this.sort(sort));
		}
		catch(final JRException e)
		{
			throw new JasperDesignException(
				"Registration failed for sort \"" + sort.getExpression().getName() + "\"",
				e);
		}
	}
	
	// field
	private JRDesignField field(final DRIDesignField field)
	{
		final JRDesignField jrField = new JRDesignField();
		jrField.setName(field.getName());
		jrField.setValueClass(field.getValueClass());
		jrField.setDescription(field.getDescription());
		return jrField;
	}
	
	// variable
	private JRDesignVariable variable(final DRIDesignVariable variable)
	{
		final JRDesignExpression expression = this.getExpression(variable.getValueExpression());
		final JRDesignExpression initialValueExpression = this.getExpression(variable.getInitialValueExpression());
		
		final JRDesignVariable jrVariable = new JRDesignVariable();
		jrVariable.setName(variable.getName());
		jrVariable.setExpression(expression);
		jrVariable.setInitialValueExpression(initialValueExpression);
		jrVariable.setValueClass(variable.getValueClass());
		jrVariable.setCalculation(ConstantTransform.calculation(variable.getCalculation()));
		final ResetType resetType = variable.getResetType();
		jrVariable.setResetType(ConstantTransform.variableResetType(resetType));
		if(resetType.equals(ResetType.GROUP) && variable.getResetGroup() != null)
		{
			jrVariable.setResetGroup(this.getGroup(variable.getResetGroup()));
		}
		return jrVariable;
	}
	
	protected JRGroup getGroup(final DRIDesignGroup group)
	{
		return null;
	}
	
	// simple expression
	private JRDesignExpression expression(final DRIDesignExpression simpleExpression)
	{
		final JRDesignExpression expression = new JRDesignExpression();
		expression.setText(this.getExpressionText(simpleExpression));
		return expression;
	}
	
	private String getExpressionText(final DRIDesignExpression expression)
	{
		if(expression instanceof DRIDesignField)
		{
			return this.toFieldValue(expression.getName());
		}
		else if(expression instanceof DRIDesignVariable)
		{
			return this.toVariableValue(expression.getName());
		}
		else if(expression instanceof DRIDesignComplexExpression)
		{
			final DRIDesignComplexExpression complexExpression = (DRIDesignComplexExpression)expression;
			String values = "";
			for(final DRIDesignExpression valueExpression : complexExpression.getExpressions())
			{
				values += ", " + this.getExpressionText(valueExpression);
			}
			if(values.length() > 0)
			{
				values = values.substring(2);
			}
			final String parameterName = this.getExpressionParameterName(complexExpression.getParameterName());
			return MessageFormat.format(COMPLEX_VALUE, parameterName, expression.getName(), values);
		}
		else if(expression instanceof DRIDesignSimpleExpression)
		{
			final String parameterName =
				this.getExpressionParameterName(((DRIDesignSimpleExpression)expression).getParameterName());
			return MessageFormat.format(VALUE, parameterName, expression.getName());
		}
		else if(expression instanceof DRIDesignSystemExpression)
		{
			final String name = ((DRIDesignSystemExpression)expression).getName();
			if(name.equals(SystemExpression.PAGE_NUMBER.name()))
			{
				return this.toVariableValue(JRVariable.PAGE_NUMBER);
			}
			else
			{
				return this.toVariableValue(name);
			}
			// throw new JasperDesignException("System expression \"" + name + "\" not supported");
		}
		else if(expression instanceof DRIDesignJasperExpression)
		{
			return ((DRIDesignJasperExpression)expression).getExpression();
		}
		else
		{
			throw new JasperDesignException("Expression " + expression.getClass().getName() + " not supported");
		}
	}
	
	private String getExpressionParameterName(final String parameterName)
	{
		if(parameterName == null)
		{
			return JasperCustomValues.NAME;
		}
		else
		{
			return parameterName;
		}
	}
	
	private String toFieldValue(final String expression)
	{
		return MessageFormat.format(FIELD_VALUE, expression);
	}
	
	private String toVariableValue(final String expression)
	{
		return MessageFormat.format(VARIABLE_VALUE, expression);
	}
	
	protected String toParameterValue(final String expression)
	{
		return MessageFormat.format(PARAMETER_VALUE, expression);
	}
	
	public JRDesignExpression getExpression(final DRIDesignExpression expression)
	{
		if(expression == null)
		{
			return null;
		}
		if(!this.expressions.containsKey(expression.getName()))
		{
			throw new JasperDesignException("Expression \"" + expression.getName() + "\" is not registered");
		}
		return this.expressions.get(expression.getName());
	}
	
	// sort
	private JRDesignSortField sort(final DRIDesignSort sort)
	{
		final DRIDesignExpression expression = sort.getExpression();
		final String name;
		final SortFieldTypeEnum type;
		if(expression instanceof DRIDesignField)
		{
			name = expression.getName();
			type = SortFieldTypeEnum.FIELD;
		}
		else if(expression instanceof DRIDesignVariable)
		{
			name = expression.getName();
			type = SortFieldTypeEnum.VARIABLE;
		}
		else
		{
			throw new JasperDesignException("Sort expression \"" + expression.getName() + "\" not supported");
		}
		
		final JRDesignSortField jrSort = new JRDesignSortField();
		jrSort.setName(name);
		jrSort.setOrder(ConstantTransform.orderType(sort.getOrderType()));
		jrSort.setType(type);
		return jrSort;
	}
	
	protected JRPropertyExpression getPropertyExpression(final DRIDesignPropertyExpression propertyExpression)
	{
		final JRDesignPropertyExpression jrPropertyExpression = new JRDesignPropertyExpression();
		jrPropertyExpression.setName(propertyExpression.getName());
		jrPropertyExpression.setValueExpression(this.getExpression(propertyExpression.getValueExpression()));
		return jrPropertyExpression;
	}
	
	protected JRGenericElementParameter getGenericElementParameterExpression(
		final DRIDesignParameterExpression parameterExpression)
	{
		final JRDesignGenericElementParameter jrParameterExpression = new JRDesignGenericElementParameter();
		jrParameterExpression.setName(parameterExpression.getName());
		jrParameterExpression.setValueExpression(this.getExpression(parameterExpression.getValueExpression()));
		return jrParameterExpression;
	}
	
	protected abstract JasperCustomValues getCustomValues();
	
	protected abstract Collection<DRIDesignField> getFields();
	
	protected abstract Collection<DRIDesignVariable> getVariables();
	
	protected abstract Collection<DRIDesignSystemExpression> getSystemExpressions();
	
	protected abstract Collection<DRIDesignJasperExpression> getJasperExpressions();
	
	protected abstract Collection<DRIDesignSimpleExpression> getSimpleExpressions();
	
	protected abstract Collection<DRIDesignComplexExpression> getComplexExpressions();
	
	protected abstract Collection<DRIDesignSort> getSorts();
	
	protected abstract void addField(JRDesignField field) throws JRException;
	
	protected abstract void addVariable(JRDesignVariable variable) throws JRException;
	
	protected abstract void addSort(JRDesignSortField sort) throws JRException;
}
