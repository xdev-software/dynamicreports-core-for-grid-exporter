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

import software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.builder.export.ExporterBuilders;
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilders;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilders;
import software.xdev.dynamicreports.report.builder.condition.ConditionBuilders;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilders;
import software.xdev.dynamicreports.report.builder.datatype.DataTypeBuilders;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.builder.expression.ExpressionBuilders;
import software.xdev.dynamicreports.report.builder.grid.GridBuilders;
import software.xdev.dynamicreports.report.builder.group.GroupBuilders;
import software.xdev.dynamicreports.report.builder.style.StyleBuilders;
import software.xdev.dynamicreports.report.builder.subtotal.SubtotalBuilders;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


@SuppressWarnings("checkstyle:ConstantName")
public final class DynamicReports
{
	private DynamicReports()
	{
	}
	
	public static final ColumnBuilders col = new ColumnBuilders();
	public static final GridBuilders grid = new GridBuilders();
	public static final GroupBuilders grp = new GroupBuilders();
	public static final SubtotalBuilders sbt = new SubtotalBuilders();
	public static final StyleBuilders stl = new StyleBuilders();
	public static final ComponentBuilders cmp = new ComponentBuilders();
	public static final ExpressionBuilders exp = new ExpressionBuilders();
	public static final ConditionBuilders cnd = new ConditionBuilders();
	public static final DataTypeBuilders type = new DataTypeBuilders();
	public static final ExporterBuilders export = new ExporterBuilders();
	public static final CrosstabBuilders ctab = new CrosstabBuilders();
	
	public static JasperReportBuilder report()
	{
		return new JasperReportBuilder();
	}
	
	public static JasperConcatenatedReportBuilder concatenatedReport()
	{
		return new JasperConcatenatedReportBuilder();
	}
	
	public static JasperConcatenatedReportBuilder concatenatedReport(final JasperReportHandler jasperReportHandler)
	{
		return new JasperConcatenatedReportBuilder(jasperReportHandler);
	}
	
	// field
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T> FieldBuilder<T> field(final String name, final Class valueClass)
	{
		final FieldBuilder<T> fieldBuilder = new FieldBuilder<>(name, valueClass);
		try
		{
			final DRIDataType<? super T, T> dataType = DataTypes.detectType(valueClass);
			fieldBuilder.setDataType(dataType);
		}
		catch(final DRException e)
		{
			// Undocumented upstream
		}
		return fieldBuilder;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static <T> FieldBuilder<T> field(final String name, final DRIDataType dataType)
	{
		Validate.notNull(dataType, "dataType must not be null");
		final FieldBuilder<T> fieldBuilder = new FieldBuilder<T>(name, dataType.getValueClass());
		fieldBuilder.setDataType(dataType);
		return fieldBuilder;
	}
	
	// variable
	
	public static <T> VariableBuilder<T> variable(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		Validate.notNull(column, "column must not be null");
		return new VariableBuilder<>(column, calculation);
	}
	
	public static <T> VariableBuilder<T> variable(
		final String name,
		final ValueColumnBuilder<?, ?> column,
		final Calculation calculation)
	{
		Validate.notNull(column, "column must not be null");
		return new VariableBuilder<>(name, column, calculation);
	}
	
	public static <T> VariableBuilder<T> variable(final FieldBuilder<T> field, final Calculation calculation)
	{
		Validate.notNull(field, "field must not be null");
		return new VariableBuilder<>(field, calculation);
	}
	
	public static <T> VariableBuilder<T> variable(
		final String name,
		final FieldBuilder<T> field,
		final Calculation calculation)
	{
		return new VariableBuilder<>(name, field, calculation);
	}
	
	public static <T> VariableBuilder<T> variable(
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return new VariableBuilder<>(field(fieldName, valueClass), calculation);
	}
	
	public static <T> VariableBuilder<T> variable(
		final String name,
		final String fieldName,
		final Class<?> valueClass,
		final Calculation calculation)
	{
		return new VariableBuilder<>(name, field(fieldName, valueClass), calculation);
	}
	
	public static <T> VariableBuilder<T> variable(final DRIExpression<?> expression, final Calculation calculation)
	{
		return new VariableBuilder<>(expression, calculation);
	}
	
	public static <T> VariableBuilder<T> variable(
		final String name,
		final DRIExpression<?> expression,
		final Calculation calculation)
	{
		return new VariableBuilder<>(name, expression, calculation);
	}
	
	// sort
	
	public static SortBuilder asc(final TextColumnBuilder<?> column)
	{
		return new SortBuilder(column).setOrderType(OrderType.ASCENDING);
	}
	
	public static SortBuilder asc(final FieldBuilder<?> field)
	{
		return new SortBuilder(field).setOrderType(OrderType.ASCENDING);
	}
	
	public static SortBuilder asc(final String fieldName, final Class<?> valueClass)
	{
		return new SortBuilder(field(fieldName, valueClass)).setOrderType(OrderType.ASCENDING);
	}
	
	public static SortBuilder asc(final VariableBuilder<?> variable)
	{
		return new SortBuilder(variable).setOrderType(OrderType.ASCENDING);
	}
	
	public static SortBuilder asc(final DRIExpression<?> expression)
	{
		return new SortBuilder(expression).setOrderType(OrderType.ASCENDING);
	}
	
	public static SortBuilder desc(final TextColumnBuilder<?> column)
	{
		return new SortBuilder(column).setOrderType(OrderType.DESCENDING);
	}
	
	public static SortBuilder desc(final FieldBuilder<?> field)
	{
		return new SortBuilder(field).setOrderType(OrderType.DESCENDING);
	}
	
	public static SortBuilder desc(final String fieldName, final Class<?> valueClass)
	{
		return new SortBuilder(field(fieldName, valueClass)).setOrderType(OrderType.DESCENDING);
	}
	
	public static SortBuilder desc(final VariableBuilder<?> variable)
	{
		return new SortBuilder(variable).setOrderType(OrderType.DESCENDING);
	}
	
	public static SortBuilder desc(final DRIExpression<?> expression)
	{
		return new SortBuilder(expression).setOrderType(OrderType.DESCENDING);
	}
	
	// hyperLink
	
	public static HyperLinkBuilder hyperLink()
	{
		return new HyperLinkBuilder();
	}
	
	public static HyperLinkBuilder hyperLink(final String link)
	{
		return new HyperLinkBuilder(link);
	}
	
	public static HyperLinkBuilder hyperLink(final DRIExpression<String> linkExpression)
	{
		return new HyperLinkBuilder(linkExpression);
	}
	
	// margin
	
	public static MarginBuilder margin()
	{
		return new MarginBuilder();
	}
	
	public static MarginBuilder margin(final int margin)
	{
		return new MarginBuilder(margin);
	}
	
	// parameter
	
	public static <T> ParameterBuilder<T> parameter(final String name, final T value)
	{
		return new ParameterBuilder<>(name, value);
	}
	
	public static <T> ParameterBuilder<T> parameter(final String name, final Class<T> valueClass)
	{
		return new ParameterBuilder<>(name, valueClass);
	}
	
	// query
	
	public static QueryBuilder query(final String text, final String language)
	{
		return new QueryBuilder(text, language);
	}
	
	// units
	
	public static int cm(final Number value)
	{
		return Units.cm(value);
	}
	
	public static int inch(final Number value)
	{
		return Units.inch(value);
	}
	
	public static int mm(final Number value)
	{
		return Units.mm(value);
	}
	
	// template
	
	public static ReportTemplateBuilder template()
	{
		return new ReportTemplateBuilder();
	}
	
	// table of contents
	
	public static TableOfContentsCustomizerBuilder tableOfContentsCustomizer()
	{
		return new TableOfContentsCustomizerBuilder();
	}
	
	public static TableOfContentsHeadingBuilder tableOfContentsHeading()
	{
		return new TableOfContentsHeadingBuilder();
	}
	
	public static TableOfContentsHeadingBuilder tableOfContentsHeading(final String label)
	{
		return new TableOfContentsHeadingBuilder().setLabel(label);
	}
	
	// dataset
	
	public static DatasetBuilder dataset()
	{
		return new DatasetBuilder();
	}
}
