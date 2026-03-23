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

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.definition.DRICrosstabValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("checkstyle:ConstantName")
public final class Expressions
{
	private Expressions()
	{
	}
	
	private static final PrintInFirstPageExpression printInFirstPage = new PrintInFirstPageExpression();
	private static final PrintNotInFirstPageExpression printNotInFirstPage = new PrintNotInFirstPageExpression();
	private static final ReportRowNumberExpression reportRowNumber = new ReportRowNumberExpression();
	private static final PageRowNumberExpression pageRowNumber = new PageRowNumberExpression();
	private static final ColumnRowNumberExpression columnRowNumber = new ColumnRowNumberExpression();
	private static final PageNumberExpression pageNumber = new PageNumberExpression();
	private static final ColumnNumberExpression columnNumber = new ColumnNumberExpression();
	private static final PrintInOddRowExpression printInOddRow = new PrintInOddRowExpression();
	private static final PrintInEvenRowExpression printInEvenRow = new PrintInEvenRowExpression();
	
	public static PrintInFirstPageExpression printInFirstPage()
	{
		return printInFirstPage;
	}
	
	public static PrintNotInFirstPageExpression printNotInFirstPage()
	{
		return printNotInFirstPage;
	}
	
	public static PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(final String groupName)
	{
		return new PrintWhenGroupHasMoreThanOneRowExpression(groupName);
	}
	
	public static PrintWhenGroupHasMoreThanOneRowExpression printWhenGroupHasMoreThanOneRow(final GroupBuilder<?> group)
	{
		Validate.notNull(group, "group must not be null");
		return printWhenGroupHasMoreThanOneRow(group.getGroup().getName());
	}
	
	public static ReportRowNumberExpression reportRowNumber()
	{
		return reportRowNumber;
	}
	
	public static PageRowNumberExpression pageRowNumber()
	{
		return pageRowNumber;
	}
	
	public static ColumnRowNumberExpression columnRowNumber()
	{
		return columnRowNumber;
	}
	
	public static PageNumberExpression pageNumber()
	{
		return pageNumber;
	}
	
	public static ColumnNumberExpression columnNumber()
	{
		return columnNumber;
	}
	
	public static GroupRowNumberExpression groupRowNumber(final String groupName)
	{
		return new GroupRowNumberExpression(groupName);
	}
	
	public static GroupRowNumberExpression groupRowNumber(final GroupBuilder<?> group)
	{
		Validate.notNull(group, "group must not be null");
		return groupRowNumber(group.getGroup().getName());
	}
	
	public static ValueExpression<Date> date(final Date date)
	{
		return value(date, Date.class);
	}
	
	public static ValueExpression<Number> number(final Number number)
	{
		return value(number, Number.class);
	}
	
	public static ValueExpression<Image> image(final Image image)
	{
		return value(image, Image.class);
	}
	
	public static ValueExpression<InputStream> inputStream(final InputStream inputStream)
	{
		return value(inputStream, InputStream.class);
	}
	
	public static ValueExpression<URL> url(final URL url)
	{
		return value(url, URL.class);
	}
	
	public static <T> ValueExpression<T> value(final T value)
	{
		return new ValueExpression<>(value);
	}
	
	public static <T> ValueExpression<T> value(final T value, final Class<? super T> valueClass)
	{
		return new ValueExpression<>(value, valueClass);
	}
	
	public static ValueExpression<String> text(final String text)
	{
		return value(text, String.class);
	}
	
	public static DataSourceExpression dataSource(final JRDataSource dataSource)
	{
		return new DataSourceExpression(dataSource);
	}
	
	public static MessageExpression message(final String key)
	{
		if(key != null)
		{
			return new MessageExpression(key);
		}
		return null;
	}
	
	public static MessageExpression message(final String key, final Object[] arguments)
	{
		if(key != null)
		{
			return new MessageExpression(key, arguments);
		}
		return null;
	}
	
	// jasper
	
	public static <T> JasperExpression<T> jasperSyntax(final String expression, final Class<? super T> valueClass)
	{
		return new JasperExpression<>(expression, valueClass);
	}
	
	@SuppressWarnings("rawtypes")
	public static JasperExpression jasperSyntax(final String expression)
	{
		return jasperSyntax(expression, Object.class);
	}
	
	// property
	
	public static PropertyExpression property(final String name, final DRIExpression<String> valueExpression)
	{
		return new PropertyExpression(name, valueExpression);
	}
	
	public static PropertyExpression property(final String name, final String value)
	{
		return new PropertyExpression(name, text(value));
	}
	
	// parameter
	
	public static ParameterExpression parameter(final String name, final DRIExpression<?> valueExpression)
	{
		return new ParameterExpression(name, valueExpression);
	}
	
	public static ParameterExpression parameter(final String name, final Object value)
	{
		return new ParameterExpression(name, value(value));
	}
	
	public static PrintInOddRowExpression printInOddRow()
	{
		return printInOddRow;
	}
	
	public static PrintInEvenRowExpression printInEvenRow()
	{
		return printInEvenRow;
	}
	
	// subdatasource
	
	public static BeanCollectionSubDatasourceExpression subDatasourceBeanCollection(final String fieldName)
	{
		return new BeanCollectionSubDatasourceExpression(fieldName);
	}
	
	public static BeanCollectionSubDatasourceExpression subDatasourceBeanCollection(
		final DRIExpression<?
			extends Collection<?>> expression)
	{
		return new BeanCollectionSubDatasourceExpression(expression);
	}
	
	public static BeanArraySubDatasourceExpression subDatasourceBeanArray(final String fieldName)
	{
		return new BeanArraySubDatasourceExpression(fieldName);
	}
	
	public static BeanArraySubDatasourceExpression subDatasourceBeanArray(
		final DRIExpression<? extends Object[]> expression)
	{
		return new BeanArraySubDatasourceExpression(expression);
	}
	
	public static MapCollectionSubDatasourceExpression subDatasourceMapCollection(final String fieldName)
	{
		return new MapCollectionSubDatasourceExpression(fieldName);
	}
	
	public static MapCollectionSubDatasourceExpression subDatasourceMapCollection(
		final DRIExpression<?
			extends Collection<Map<String, ?>>> expression)
	{
		return new MapCollectionSubDatasourceExpression(expression);
	}
	
	public static MapArraySubDatasourceExpression subDatasourceMapArray(final String fieldName)
	{
		return new MapArraySubDatasourceExpression(fieldName);
	}
	
	public static MapArraySubDatasourceExpression subDatasourceMapArray(
		final DRIExpression<? extends Object[]> expression)
	{
		return new MapArraySubDatasourceExpression(expression);
	}
	
	// crosstab
	
	public static OrderByExpression orderBy(final CrosstabMeasureBuilder<? extends Comparable<?>> measure)
	{
		return new OrderByExpression(measure);
	}
	
	public static <T> CrosstabValueExpression<T> crosstabValue(final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return new CrosstabValueExpression<>(group);
	}
	
	public static <T> CrosstabValueExpression<T> crosstabValue(final DRICrosstabValue<T> measure)
	{
		return new CrosstabValueExpression<>(measure);
	}
	
	public static <T> CrosstabValueExpression<T> crosstabValue(
		final DRICrosstabValue<T> measure,
		final AbstractCrosstabGroupBuilder<?, ?, ?> group)
	{
		return new CrosstabValueExpression<>(measure, group);
	}
	
	public static <T> CrosstabValueExpression<T> crosstabValue(
		final DRICrosstabValue<T> measure,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup)
	{
		return new CrosstabValueExpression<>(measure, rowGroup, columnGroup);
	}
}
