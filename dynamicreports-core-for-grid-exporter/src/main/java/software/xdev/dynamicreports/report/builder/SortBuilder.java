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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRSort;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class SortBuilder extends AbstractBuilder<SortBuilder, DRSort>
{

	protected SortBuilder(final TextColumnBuilder<?> column)
	{
		super(new DRSort());
		Validate.notNull(column, "column must not be null");
		this.getObject().setExpression(column.build());
	}
	
	protected SortBuilder(final FieldBuilder<?> field)
	{
		super(new DRSort());
		Validate.notNull(field, "field must not be null");
		this.getObject().setExpression(field.build());
	}
	
	protected SortBuilder(final VariableBuilder<?> variable)
	{
		super(new DRSort());
		Validate.notNull(variable, "variable must not be null");
		this.getObject().setExpression(variable.build());
	}
	
	protected SortBuilder(final DRIExpression<?> expression)
	{
		super(new DRSort());
		this.getObject().setExpression(expression);
	}
	
	public SortBuilder setOrderType(final OrderType orderType)
	{
		this.getObject().setOrderType(orderType);
		return this;
	}
}
