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
package software.xdev.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignField;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.DRDesignSort;
import software.xdev.dynamicreports.design.base.DRDesignVariable;
import software.xdev.dynamicreports.design.base.expression.DRDesignComplexExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignJasperExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignParameterExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignPropertyExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignSimpleExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignSystemExpression;
import software.xdev.dynamicreports.design.base.expression.DRDesignValueFormatter;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRISort;
import software.xdev.dynamicreports.report.definition.DRISubtotal;
import software.xdev.dynamicreports.report.definition.DRIVariable;
import software.xdev.dynamicreports.report.definition.column.DRIBooleanColumn;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.expression.DRIComplexExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIJasperExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIParameterExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISystemExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.exception.DRException;


public abstract class AbstractExpressionTransform
{
	protected DesignTransformAccessor accessor;
	private Map<String, DRIDesignField> fields;
	private Map<String, DRIDesignVariable> variables;
	private Map<String, DRIDesignSystemExpression> systemExpressions;
	private Map<String, DRIDesignJasperExpression> jasperExpressions;
	private Map<String, DRIDesignSimpleExpression> simpleExpressions;
	private Map<String, DRIDesignComplexExpression> complexExpressions;
	private Map<DRIExpression<?>, DRIDesignExpression> expressions;
	private List<DRIDesignSort> sorts;
	
	public AbstractExpressionTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.init();
	}
	
	private void init()
	{
		this.fields = new LinkedHashMap<>();
		this.variables = new LinkedHashMap<>();
		this.systemExpressions = new HashMap<>();
		this.jasperExpressions = new HashMap<>();
		this.simpleExpressions = new HashMap<>();
		this.complexExpressions = new HashMap<>();
		this.expressions = new HashMap<>();
		this.sorts = new ArrayList<>();
	}
	
	public void transform() throws DRException
	{
		for(final DRIField<?> field : this.transformFields())
		{
			this.transformExpression(field);
		}
		for(final DRIVariable<?> variable : this.transformVariables())
		{
			this.transformExpression(variable);
		}
		for(final DRISort sort : this.transformSorts())
		{
			this.transformSort(sort);
		}
	}
	
	public DRIDesignExpression transformExpression(final DRIExpression<?> expression) throws DRException
	{
		return this.transformExpression(expression, null, null);
	}
	
	protected DRIDesignExpression transformExpression(final DRIExpression<?> expression, final String parameterName)
		throws DRException
	{
		return this.transformExpression(expression, null, parameterName);
	}
	
	protected DRIDesignExpression transformExpression(
		final DRIExpression<?> expression,
		final DRIValueFormatter<?, ?> valueFormatter,
		final String parameterName) throws DRException
	{
		if(expression == null)
		{
			return null;
		}
		
		if(valueFormatter != null)
		{
			return this.addExpression(new DRDesignValueFormatter(
				valueFormatter,
				this.transformExpression(expression)));
		}
		if(this.expressions.containsKey(expression))
		{
			return this.expressions.get(expression);
		}
		
		DRIDesignExpression express;
		if(expression instanceof DRISystemExpression<?>)
		{
			express = new DRDesignSystemExpression((DRISystemExpression<?>)expression);
		}
		else if(expression instanceof DRIJasperExpression<?>)
		{
			express = new DRDesignJasperExpression((DRIJasperExpression<?>)expression);
		}
		else if(expression instanceof DRISimpleExpression<?>)
		{
			express = new DRDesignSimpleExpression((DRISimpleExpression<?>)expression, parameterName);
		}
		else if(expression instanceof DRIComplexExpression<?>)
		{
			express = this.transformComplexExpression((DRIComplexExpression<?>)expression, parameterName);
		}
		else if(expression instanceof DRIField<?>)
		{
			express = this.transformField((DRIField<?>)expression);
		}
		else if(expression instanceof DRIVariable<?>)
		{
			express = this.transformVariable((DRIVariable<?>)expression);
		}
		else if(expression instanceof DRIValueColumn<?>)
		{
			express = this.transformExpression(((DRIValueColumn<?>)expression).getComponent().getValueExpression());
		}
		else if(expression instanceof DRIBooleanColumn)
		{
			express = this.transformExpression(((DRIBooleanColumn)expression).getComponent().getValueExpression());
		}
		else if(expression instanceof DRISubtotal<?>)
		{
			express = this.transformExpression(((DRISubtotal<?>)expression).getValueField().getValueExpression());
		}
		else
		{
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
		express = this.addExpression(express);
		if(valueFormatter == null)
		{
			this.expressions.put(expression, express);
		}
		return express;
	}
	
	private DRDesignField transformField(final DRIField<?> field)
	{
		final DRDesignField designField = new DRDesignField();
		designField.setName(field.getName());
		designField.setValueClass(field.getValueClass());
		designField.setDescription(this.accessor.getTemplateTransform().getFieldDescription(field));
		return designField;
	}
	
	private DRIDesignExpression transformComplexExpression(
		final DRIComplexExpression<?> complexExpression,
		final String parameterName) throws DRException
	{
		final DRDesignComplexExpression designComplexExpression =
			new DRDesignComplexExpression(complexExpression, parameterName);
		for(final DRIExpression<?> expression : complexExpression.getExpressions())
		{
			designComplexExpression.addExpression(this.transformExpression(expression));
		}
		return designComplexExpression;
	}
	
	private DRIDesignExpression transformVariable(final DRIVariable<?> variable) throws DRException
	{
		final DRDesignVariable designVariable = new DRDesignVariable(variable.getName());
		designVariable.setValueExpression(this.transformExpression(variable.getValueExpression()));
		designVariable.setInitialValueExpression(this.transformExpression(variable.getInitialValueExpression()));
		designVariable.setCalculation(variable.getCalculation());
		designVariable.setResetType(this.getVariableResetType(variable));
		designVariable.setResetGroup(this.getVariableResetGroup(variable));
		return designVariable;
	}
	
	protected ResetType getVariableResetType(final DRIVariable<?> variable)
	{
		return null;
	}
	
	protected DRDesignGroup getVariableResetGroup(final DRIVariable<?> variable) throws DRException
	{
		return null;
	}
	
	private void transformSort(final DRISort sort) throws DRException
	{
		final DRIDesignExpression expression = this.transformExpression(sort.getExpression());
		final DRIDesignExpression sortExpression;
		if(expression instanceof DRIDesignField || expression instanceof DRIDesignVariable)
		{
			sortExpression = expression;
		}
		else
		{
			@SuppressWarnings({"rawtypes", "unchecked"})
			final DRVariable variable = new DRVariable(sort.getExpression(), Calculation.NOTHING);
			variable.setResetType(Evaluation.NONE);
			sortExpression = this.transformExpression(variable);
		}
		
		final DRDesignSort designSort = new DRDesignSort();
		designSort.setExpression(sortExpression);
		designSort.setOrderType(sort.getOrderType());
		
		this.sorts.add(designSort);
	}
	
	protected DRIDesignPropertyExpression transformPropertyExpression(final DRIPropertyExpression propertyExpression)
		throws DRException
	{
		final DRDesignPropertyExpression designPropertyExpression = new DRDesignPropertyExpression();
		designPropertyExpression.setName(propertyExpression.getName());
		designPropertyExpression.setValueExpression(this.transformExpression(propertyExpression.getValueExpression()));
		return designPropertyExpression;
	}
	
	protected DRIDesignParameterExpression transformParameterExpression(
		final DRIParameterExpression parameterExpression)
		throws DRException
	{
		final DRDesignParameterExpression designParameterExpression = new DRDesignParameterExpression();
		designParameterExpression.setName(parameterExpression.getName());
		designParameterExpression.setValueExpression(
			this.transformExpression(parameterExpression.getValueExpression()));
		return designParameterExpression;
	}
	
	private DRIDesignExpression addExpression(final DRIDesignExpression expression)
	{
		if(expression == null)
		{
			return null;
		}
		if(expression instanceof DRIDesignField)
		{
			return this.addField((DRIDesignField)expression);
		}
		else if(expression instanceof DRIDesignVariable)
		{
			this.addVariable((DRDesignVariable)expression);
		}
		else if(expression instanceof DRIDesignSystemExpression)
		{
			this.addSystemExpression((DRIDesignSystemExpression)expression);
		}
		else if(expression instanceof DRIDesignJasperExpression)
		{
			this.addJasperExpression((DRIDesignJasperExpression)expression);
		}
		else if(expression instanceof DRIDesignSimpleExpression)
		{
			this.addSimpleExpression((DRIDesignSimpleExpression)expression);
		}
		else if(expression instanceof DRIDesignComplexExpression)
		{
			this.addComplexExpression((DRIDesignComplexExpression)expression);
		}
		else
		{
			throw new DRDesignReportException("Expression " + expression.getClass().getName() + " not supported");
		}
		return expression;
	}
	
	private void addVariable(final DRDesignVariable variable)
	{
		if(this.variables.containsKey(variable.getName()))
		{
			if(!this.variables.get(variable.getName()).equals(variable))
			{
				throw new DRDesignReportException("Duplicate declaration of variable \"" + variable.getName() + "\"");
			}
			return;
		}
		this.variables.put(variable.getName(), variable);
	}
	
	private DRIDesignField addField(final DRIDesignField field)
	{
		if(this.fields.containsKey(field.getName()))
		{
			final DRIDesignField fld = this.fields.get(field.getName());
			if(!fld.getValueClass().equals(field.getValueClass()))
			{
				throw new DRDesignReportException("Duplicate declaration of field \"" + field.getName() + "\"");
			}
			return fld;
		}
		this.fields.put(field.getName(), field);
		return field;
	}
	
	private void addSystemExpression(final DRIDesignSystemExpression systemExpression)
	{
		if(this.systemExpressions.containsKey(systemExpression.getName()))
		{
			return;
		}
		this.systemExpressions.put(systemExpression.getName(), systemExpression);
	}
	
	private void addJasperExpression(final DRIDesignJasperExpression jasperExpression)
	{
		if(this.jasperExpressions.containsKey(jasperExpression.getName()))
		{
			return;
		}
		this.jasperExpressions.put(jasperExpression.getName(), jasperExpression);
	}
	
	private void addSimpleExpression(final DRIDesignSimpleExpression simpleExpression)
	{
		if(this.simpleExpressions.containsKey(simpleExpression.getName()))
		{
			if(!this.simpleExpressions.get(simpleExpression.getName()).equals(simpleExpression))
			{
				throw new DRDesignReportException(
					"Duplicate declaration of simple expression \"" + simpleExpression.getName() + "\"");
			}
			return;
		}
		this.simpleExpressions.put(simpleExpression.getName(), simpleExpression);
	}
	
	private void addComplexExpression(final DRIDesignComplexExpression complexExpression)
	{
		if(this.complexExpressions.containsKey(complexExpression.getName()))
		{
			if(!this.complexExpressions.get(complexExpression.getName()).equals(complexExpression))
			{
				throw new DRDesignReportException(
					"Duplicate declaration of complex expression \"" + complexExpression.getName() + "\"");
			}
			return;
		}
		this.complexExpressions.put(complexExpression.getName(), complexExpression);
	}
	
	public Collection<DRIDesignField> getFields()
	{
		return this.fields.values();
	}
	
	public Collection<DRIDesignVariable> getVariables()
	{
		return this.variables.values();
	}
	
	public Collection<DRIDesignSystemExpression> getSystemExpressions()
	{
		return this.systemExpressions.values();
	}
	
	public Collection<DRIDesignJasperExpression> getJasperExpressions()
	{
		return this.jasperExpressions.values();
	}
	
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions()
	{
		return this.simpleExpressions.values();
	}
	
	public Collection<DRIDesignComplexExpression> getComplexExpressions()
	{
		return this.complexExpressions.values();
	}
	
	public Collection<DRIDesignSort> getSorts()
	{
		return this.sorts;
	}
	
	protected abstract List<? extends DRIField<?>> transformFields();
	
	protected abstract List<? extends DRIVariable<?>> transformVariables();
	
	protected abstract List<? extends DRISort> transformSorts();
	
	protected abstract DRIDesignDataset getDataset();
}
