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
package software.xdev.dynamicreports.report.builder;

import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIJasperExpression;


public class VariableBuilder<T> extends AbstractBuilder<VariableBuilder<T>, DRVariable<T>> implements DRIValue<T>
{

	// column
	
	protected VariableBuilder(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		super(new DRVariable<>(column.build(), calculation));
	}
	
	protected VariableBuilder(final String name, final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		super(new DRVariable<>(name, column.build(), calculation));
	}
	
	// field
	
	protected VariableBuilder(final FieldBuilder<?> field, final Calculation calculation)
	{
		super(new DRVariable<>(field.getField(), calculation));
	}
	
	protected VariableBuilder(final String name, final FieldBuilder<?> field, final Calculation calculation)
	{
		super(new DRVariable<>(name, field.getField(), calculation));
	}
	
	// simple expression
	
	protected VariableBuilder(final DRIExpression<?> expression, final Calculation calculation)
	{
		super(new DRVariable<>(expression, calculation));
	}
	
	protected VariableBuilder(final String name, final DRIExpression<?> expression, final Calculation calculation)
	{
		super(new DRVariable<>(name, expression, calculation));
	}
	
	public VariableBuilder<T> setInitialValueExpression(final DRIJasperExpression<?> initialValueExpression)
	{
		this.getObject().setInitialValueExpression(initialValueExpression);
		return this;
	}
	
	public VariableBuilder<T> setResetType(final Evaluation resetType)
	{
		this.getObject().setResetType(resetType);
		return this;
	}
	
	public VariableBuilder<T> setResetGroup(final GroupBuilder<?> resetGroup)
	{
		if(resetGroup != null)
		{
			this.getObject().setResetGroup(resetGroup.getGroup());
			this.setResetType(Evaluation.GROUP);
		}
		else
		{
			this.getObject().setResetGroup(null);
		}
		return this;
	}
	
	public DRVariable<T> getVariable()
	{
		return this.build();
	}
	
	@Override
	public String getName()
	{
		return this.getVariable().getName();
	}
}
