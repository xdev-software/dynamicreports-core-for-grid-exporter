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
package software.xdev.dynamicreports.report.builder.expression;

import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.definition.DRICrosstabValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;



public class ExpressionBuilders
{
	
	public PrintInFirstPageExpression printInFirstPage()
	{
		return Expressions.printInFirstPage();
	}
	
	public PrintNotInFirstPageExpression printNotInFirstPage()
	{
		return Expressions.printNotInFirstPage();
	}
	
	public PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(final String groupName)
	{
		return Expressions.printWhenGroupHasMoreThanOneRow(groupName);
	}
	
	public PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(final GroupBuilder<?> group)
	{
		return Expressions.printWhenGroupHasMoreThanOneRow(group);
	}
	
	public ReportRowNumberExpression reportRowNumber()
	{
		return Expressions.reportRowNumber();
	}
	
	public PageRowNumberExpression pageRowNumber()
	{
		return Expressions.pageRowNumber();
	}
	
	public ColumnRowNumberExpression columnRowNumber()
	{
		return Expressions.columnRowNumber();
	}
	
	public PageNumberExpression pageNumber()
	{
		return Expressions.pageNumber();
	}
	
	public ColumnNumberExpression columnNumber()
	{
		return Expressions.columnNumber();
	}
	
	public GroupRowNumberExpression groupRowNumber(final String groupName)
	{
		return Expressions.groupRowNumber(groupName);
	}
	
	public GroupRowNumberExpression groupRowNumber(final GroupBuilder<?> group)
	{
		return Expressions.groupRowNumber(group);
	}
	
	public ValueExpression<Date> date(final Date date)
	{
		return Expressions.date(date);
	}
	
	public ValueExpression<Number> number(final Number number)
	{
		return Expressions.number(number);
	}
	
	public ValueExpression<Image> image(final Image image)
	{
		return Expressions.image(image);
	}
	
	public ValueExpression<InputStream> inputStream(final InputStream inputStream)
	{
		return Expressions.inputStream(inputStream);
	}
	
	public ValueExpression<URL> url(final URL url)
	{
		return Expressions.url(url);
	}
	
	public <T> ValueExpression<T> value(final T value)
	{
		return Expressions.value(value);
	}
	
	public <T> ValueExpression<T> value(final T value, final Class<? super T> valueClass)
	{
		return Expressions.value(value, valueClass);
	}
	
	public ValueExpression<String> text(final String text)
	{
		return Expressions.text(text);
	}
	
	public DataSourceExpression dataSource(final JRDataSource dataSource)
	{
		return Expressions.dataSource(dataSource);
	}
	
	public MessageExpression message(final String key)
	{
		return Expressions.message(key);
	}
	
	public MessageExpression message(final String key, final Object[] arguments)
	{
		return Expressions.message(key, arguments);
	}
	
	// jasper
	
	public <T> JasperExpression<T> jasperSyntax(final String expression, final Class<? super T> valueClass)
	{
		return Expressions.jasperSyntax(expression, valueClass);
	}
	
	@SuppressWarnings("rawtypes")
	public JasperExpression jasperSyntax(final String expression)
	{
		return Expressions.jasperSyntax(expression);
	}
	
	// property
	
	public PropertyExpression property(final String name, final DRISimpleExpression<String> valueExpression)
	{
		return Expressions.property(name, valueExpression);
	}
	
	public PropertyExpression property(final String name, final String value)
	{
		return Expressions.property(name, value);
	}
	
	// parameter
	
	public ParameterExpression parameter(final String name, final DRISimpleExpression<?> valueExpression)
	{
		return Expressions.parameter(name, valueExpression);
	}
	
	public ParameterExpression parameter(final String name, final Object value)
	{
		return Expressions.parameter(name, value);
	}
	
	public PrintInOddRowExpression printInOddRow()
	{
		return Expressions.printInOddRow();
	}
	
	public PrintInEvenRowExpression printInEvenRow()
	{
		return Expressions.printInEvenRow();
	}
	
	// subdatasource
	
	public BeanCollectionSubDatasourceExpression subDatasourceBeanCollection(final String fieldName)
	{
		return Expressions.subDatasourceBeanCollection(fieldName);
	}
	
	public BeanCollectionSubDatasourceExpression subDatasourceBeanCollection(
		final DRIExpression<? extends Collection<
			?>> expression)
	{
		return Expressions.subDatasourceBeanCollection(expression);
	}
	
	public BeanArraySubDatasourceExpression subDatasourceBeanArray(final String fieldName)
	{
		return Expressions.subDatasourceBeanArray(fieldName);
	}
	
	public BeanArraySubDatasourceExpression subDatasourceBeanArray(final DRIExpression<? extends Object[]> expression)
	{
		return Expressions.subDatasourceBeanArray(expression);
	}
	
	public MapCollectionSubDatasourceExpression subDatasourceMapCollection(final String fieldName)
	{
		return Expressions.subDatasourceMapCollection(fieldName);
	}
	
	public MapCollectionSubDatasourceExpression subDatasourceMapCollection(
		final DRIExpression<?
			extends Collection<Map<String, ?>>> expression)
	{
		return Expressions.subDatasourceMapCollection(expression);
	}
	
	public MapArraySubDatasourceExpression subDatasourceMapArray(final String fieldName)
	{
		return Expressions.subDatasourceMapArray(fieldName);
	}
	
	public MapArraySubDatasourceExpression subDatasourceMapArray(final DRIExpression<? extends Object[]> expression)
	{
		return Expressions.subDatasourceMapArray(expression);
	}
	
	// crosstab
	
	public OrderByExpression orderBy(final CrosstabMeasureBuilder<? extends Comparable<?>> measure)
	{
		return Expressions.orderBy(measure);
	}
	
	public <T> CrosstabValueExpression<T> crosstabValue(final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return Expressions.crosstabValue(group);
	}
	
	public <T> CrosstabValueExpression<T> crosstabValue(final DRICrosstabValue<T> measure)
	{
		return Expressions.crosstabValue(measure);
	}
	
	public <T> CrosstabValueExpression<T> crosstabValue(
		final DRICrosstabValue<T> measure,
		final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return Expressions.crosstabValue(measure, group);
	}
	
	public <T> CrosstabValueExpression<T> crosstabValue(
		final DRICrosstabValue<T> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup)
	{
		return Expressions.crosstabValue(measure, rowGroup, columnGroup);
	}
}
