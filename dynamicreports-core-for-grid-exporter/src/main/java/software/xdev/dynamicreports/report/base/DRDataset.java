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

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRDataset implements DRIDataset
{

	private List<DRField<?>> fields;
	private List<DRVariable<?>> variables;
	private List<DRSort> sorts;
	private DRQuery query;
	private DRIExpression<Connection> connectionExpression;
	private DRIExpression<?> dataSourceExpression;
	private DRIExpression<Boolean> filterExpression;
	
	public DRDataset()
	{
		this.init();
	}
	
	private void init()
	{
		this.fields = new ArrayList<>();
		this.variables = new ArrayList<>();
		this.sorts = new ArrayList<>();
	}
	
	@Override
	public List<DRField<?>> getFields()
	{
		return this.fields;
	}
	
	public void setFields(final List<DRField<?>> fields)
	{
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		this.fields = fields;
	}
	
	public void addField(final DRField<?> field)
	{
		Validate.notNull(field, "field must not be null");
		this.fields.add(field);
	}
	
	@Override
	public List<DRVariable<?>> getVariables()
	{
		return this.variables;
	}
	
	public void setVariables(final List<DRVariable<?>> variables)
	{
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		this.variables = variables;
	}
	
	public void addVariable(final DRVariable<?> variable)
	{
		Validate.notNull(variable, "variable must not be null");
		this.variables.add(variable);
	}
	
	@Override
	public List<DRSort> getSorts()
	{
		return this.sorts;
	}
	
	public void setSorts(final List<DRSort> sorts)
	{
		Validate.notNull(sorts, "sorts must not be null");
		Validate.noNullElements(sorts, "sorts must not contains null sort");
		this.sorts = sorts;
	}
	
	public void addSort(final DRSort sort)
	{
		Validate.notNull(sort, "sort must not be null");
		this.sorts.add(sort);
	}
	
	@Override
	public DRQuery getQuery()
	{
		return this.query;
	}
	
	public void setQuery(final DRQuery query)
	{
		this.query = query;
	}
	
	@Override
	public DRIExpression<Connection> getConnectionExpression()
	{
		return this.connectionExpression;
	}
	
	public void setConnectionExpression(final DRIExpression<Connection> connectionExpression)
	{
		this.connectionExpression = connectionExpression;
	}
	
	@Override
	public DRIExpression<?> getDataSourceExpression()
	{
		return this.dataSourceExpression;
	}
	
	public void setDataSourceExpression(final DRIExpression<?> dataSourceExpression)
	{
		this.dataSourceExpression = dataSourceExpression;
	}
	
	@Override
	public DRIExpression<Boolean> getFilterExpression()
	{
		return this.filterExpression;
	}
	
	public void setFilterExpression(final DRIExpression<Boolean> filterExpression)
	{
		this.filterExpression = filterExpression;
	}
}
