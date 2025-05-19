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

import java.sql.Connection;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.report.base.DRDataset;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.QueryLanguage;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DatasetBuilder extends AbstractBuilder<DatasetBuilder, DRDataset>
{

	protected DatasetBuilder()
	{
		super(new DRDataset());
	}
	
	// field
	
	public DatasetBuilder fields(final FieldBuilder<?>... fields)
	{
		return this.addField(fields);
	}
	
	public DatasetBuilder addField(final String name, final Class<?> valueClass)
	{
		return this.addField(DynamicReports.field(name, valueClass));
	}
	
	public <U> DatasetBuilder addField(final String name, final DRIDataType<? super U, U> dataType)
	{
		return this.addField(DynamicReports.field(name, dataType));
	}
	
	public DatasetBuilder addField(final FieldBuilder<?>... fields)
	{
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		for(final FieldBuilder<?> field : fields)
		{
			this.getObject().addField(field.build());
		}
		return this;
	}
	
	// variable
	
	public DatasetBuilder variables(final VariableBuilder<?>... variables)
	{
		return this.addVariable(variables);
	}
	
	public DatasetBuilder addVariable(final VariableBuilder<?>... variables)
	{
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		for(final VariableBuilder<?> variable : variables)
		{
			this.getObject().addVariable(variable.getVariable());
		}
		return this;
	}
	
	// sort
	
	public DatasetBuilder sortBy(final TextColumnBuilder<?>... sortColumns)
	{
		Validate.notNull(sortColumns, "sortColumns must not be null");
		Validate.noNullElements(sortColumns, "sortColumns must not contains null sortColumn");
		for(final TextColumnBuilder<?> sortColumn : sortColumns)
		{
			this.sortBy(DynamicReports.asc(sortColumn));
		}
		return this;
	}
	
	public DatasetBuilder sortBy(final SortBuilder... sorts)
	{
		return this.addSort(sorts);
	}
	
	public DatasetBuilder addSort(final SortBuilder... sorts)
	{
		Validate.notNull(sorts, "sorts must not be null");
		Validate.noNullElements(sorts, "sorts must not contains null sort");
		for(final SortBuilder sort : sorts)
		{
			this.getObject().addSort(sort.build());
		}
		return this;
	}
	
	// query
	
	public DatasetBuilder setQuery(final String text, final String language)
	{
		Validate.notNull(text, "text must not be null");
		Validate.notNull(language, "language must not be null");
		return this.setQuery(DynamicReports.query(text, language));
	}
	
	public DatasetBuilder setQuery(final String sql)
	{
		Validate.notNull(sql, "sql must not be null");
		return this.setQuery(DynamicReports.query(sql, QueryLanguage.SQL));
	}
	
	public DatasetBuilder setQuery(final QueryBuilder query)
	{
		Validate.notNull(query, "query must not be null");
		this.getObject().setQuery(query.build());
		return this;
	}
	
	// connection
	
	public DatasetBuilder setConnection(final Connection connection)
	{
		this.getObject().setConnectionExpression(Expressions.value(connection));
		return this;
	}
	
	public DatasetBuilder setConnection(final DRIExpression<Connection> connectionExpression)
	{
		this.getObject().setConnectionExpression(connectionExpression);
		return this;
	}
	
	// datasource
	
	public DatasetBuilder setDataSource(final JRDataSource dataSource)
	{
		this.getObject().setDataSourceExpression(Expressions.dataSource(dataSource));
		return this;
	}
	
	public DatasetBuilder setDataSource(final DRIExpression<JRDataSource> dataSourceExpression)
	{
		this.getObject().setDataSourceExpression(dataSourceExpression);
		return this;
	}
	
	// filter
	
	public DatasetBuilder setFilterExpression(final DRIExpression<Boolean> filterExpression)
	{
		this.getObject().setFilterExpression(filterExpression);
		return this;
	}
	
	public DatasetBuilder setDataSource(final String sql, final Connection connection)
	{
		Validate.notNull(sql, "sql must not be null");
		return this.setDataSource(DynamicReports.query(sql, QueryLanguage.SQL), connection);
	}
	
	public DatasetBuilder setDataSource(final QueryBuilder query, final Connection connection)
	{
		Validate.notNull(query, "query must not be null");
		Validate.notNull(connection, "connection must not be null");
		this.setQuery(query);
		this.setConnection(connection);
		return this;
	}
}
