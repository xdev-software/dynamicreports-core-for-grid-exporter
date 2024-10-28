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

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.base.DRReport;
import software.xdev.dynamicreports.report.base.grid.DRColumnGrid;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.builder.group.Groups;
import software.xdev.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.FontBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.SimpleStyleBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.PercentageSubtotalBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.SubtotalBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.QueryLanguage;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings("unchecked")
public class ReportBuilder<T extends ReportBuilder<T>> extends AbstractBuilder<T, DRReport>
{

	public ReportBuilder()
	{
		super(new DRReport());
	}
	
	public T setReportName(final String reportName)
	{
		this.getObject().setReportName(reportName);
		return (T)this;
	}
	
	public T setLocale(final Locale locale)
	{
		this.getObject().setLocale(locale);
		return (T)this;
	}
	
	public T setResourceBundle(final ResourceBundle resourceBundle)
	{
		this.getObject().setResourceBundle(resourceBundle);
		return (T)this;
	}
	
	public T setResourceBundle(final String resourceBundleName)
	{
		this.getObject().setResourceBundleName(resourceBundleName);
		return (T)this;
	}
	
	public T setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.getObject().setShowColumnTitle(showColumnTitle);
		return (T)this;
	}
	
	public T setShowColumnValues(final Boolean showColumnValues)
	{
		this.getObject().setShowColumnValues(showColumnValues);
		return (T)this;
	}
	
	public T setPageFormat(final PageType pageType)
	{
		return this.setPageFormat(pageType, PageOrientation.PORTRAIT);
	}
	
	public T setPageFormat(final PageType pageType, final PageOrientation orientation)
	{
		this.getObject().getPage().setPageFormat(pageType, orientation);
		return (T)this;
	}
	
	public T setPageFormat(final Integer width, final Integer height, final PageOrientation orientation)
	{
		this.getObject().getPage().setPageFormat(width, height, orientation);
		return (T)this;
	}
	
	public T setPageMargin(final MarginBuilder margin)
	{
		Validate.notNull(margin, "margin must not be null");
		this.getObject().getPage().setMargin(margin.build());
		return (T)this;
	}
	
	public T setPageColumnsPerPage(final Integer columnsPerPage)
	{
		this.getObject().getPage().setColumnsPerPage(columnsPerPage);
		return (T)this;
	}
	
	public T setPageColumnSpace(final Integer columnSpace)
	{
		this.getObject().getPage().setColumnSpace(columnSpace);
		return (T)this;
	}
	
	public T ignorePageWidth()
	{
		return this.setIgnorePageWidth(true);
	}
	
	public T setIgnorePageWidth(final Boolean ignorePageWidth)
	{
		this.getObject().getPage().setIgnorePageWidth(ignorePageWidth);
		return (T)this;
	}
	
	public T ignorePagination()
	{
		return this.setIgnorePagination(true);
	}
	
	public T setIgnorePagination(final Boolean ignorePagination)
	{
		this.getObject().setIgnorePagination(ignorePagination);
		return (T)this;
	}
	
	public T setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.getObject().setWhenNoDataType(whenNoDataType);
		return (T)this;
	}
	
	public T setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.getObject().setWhenResourceMissingType(whenResourceMissingType);
		return (T)this;
	}
	
	public T titleOnANewPage()
	{
		return this.setTitleOnANewPage(true);
	}
	
	public T setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.getObject().setTitleOnANewPage(titleOnANewPage);
		return (T)this;
	}
	
	public T summaryOnANewPage()
	{
		return this.setSummaryOnANewPage(true);
	}
	
	public T setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.getObject().setSummaryOnANewPage(summaryOnANewPage);
		return (T)this;
	}
	
	public T summaryWithPageHeaderAndFooter()
	{
		return this.setSummaryWithPageHeaderAndFooter(true);
	}
	
	public T setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.getObject().setSummaryWithPageHeaderAndFooter(summaryWithPageHeaderAndFooter);
		return (T)this;
	}
	
	public T floatColumnFooter()
	{
		return this.setFloatColumnFooter(true);
	}
	
	public T setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.getObject().setFloatColumnFooter(floatColumnFooter);
		return (T)this;
	}
	
	public T setPrintOrder(final Orientation printOrder)
	{
		this.getObject().setPrintOrder(printOrder);
		return (T)this;
	}
	
	public T setColumnDirection(final RunDirection columnDirection)
	{
		this.getObject().setColumnDirection(columnDirection);
		return (T)this;
	}
	
	public T setLanguage(final String language)
	{
		this.getObject().setLanguage(language);
		return (T)this;
	}
	
	public T setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.getObject().setUseFieldNameAsDescription(useFieldNameAsDescription);
		return (T)this;
	}
	
	public T scriptlets(final DRIScriptlet... scriptlets)
	{
		return this.addScriptlet(scriptlets);
	}
	
	public T addScriptlet(final DRIScriptlet... scriptlets)
	{
		Validate.notNull(scriptlets, "scriptlets must not be null");
		Validate.noNullElements(scriptlets, "scriptlets must not contains null scriptlet");
		for(final DRIScriptlet scriptlet : scriptlets)
		{
			this.getObject().addScriptlet(scriptlet);
		}
		return (T)this;
	}
	
	public T setProperties(final Properties properties)
	{
		this.getObject().setProperties(properties);
		return (T)this;
	}
	
	public T addProperty(final String key, final String value)
	{
		this.getObject().addProperty(key, value);
		return (T)this;
	}
	
	public T setQuery(final String text, final String language)
	{
		Validate.notNull(text, "text must not be null");
		Validate.notNull(language, "language must not be null");
		return this.setQuery(DynamicReports.query(text, language));
	}
	
	public T setQuery(final String sql)
	{
		Validate.notNull(sql, "sql must not be null");
		return this.setQuery(DynamicReports.query(sql, QueryLanguage.SQL));
	}
	
	public T setQuery(final QueryBuilder query)
	{
		Validate.notNull(query, "query must not be null");
		this.getObject().setQuery(query.build());
		return (T)this;
	}
	
	public T columnGrid(final ListType type)
	{
		this.getObject().setColumnGrid(new DRColumnGrid(type));
		return (T)this;
	}
	
	public T columnGrid(final ColumnGridComponentBuilder... components)
	{
		return this.columnGrid(ListType.HORIZONTAL, components);
	}
	
	public T columnGrid(final ListType type, final ColumnGridComponentBuilder... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		final DRColumnGrid columnGrid = new DRColumnGrid(type);
		for(final ColumnGridComponentBuilder component : components)
		{
			columnGrid.addComponent(component.build());
		}
		this.getObject().setColumnGrid(columnGrid);
		return (T)this;
	}
	
	// template
	public T setTemplate(final ReportTemplateBuilder template)
	{
		Validate.notNull(template, "template must not be null");
		this.getObject().setTemplate(template.build());
		return (T)this;
	}
	
	// template style
	public T templateStyles(final TemplateStylesBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	public T addTemplateStyle(final TemplateStylesBuilder... templateStyles)
	{
		Validate.notNull(templateStyles, "templateStyles must not be null");
		Validate.noNullElements(templateStyles, "templateStyles must not contains null templateStyle");
		for(final TemplateStylesBuilder templateStyle : templateStyles)
		{
			for(final StyleBuilder style : templateStyle.getStyles())
			{
				this.addTemplateStyle(style);
			}
		}
		return (T)this;
	}
	
	public T templateStyles(final StyleBuilder... templateStyles)
	{
		return this.addTemplateStyle(templateStyles);
	}
	
	public T addTemplateStyle(final StyleBuilder... templateStyles)
	{
		Validate.notNull(templateStyles, "templateStyles must not be null");
		Validate.noNullElements(templateStyles, "templateStyles must not contains null templateStyle");
		for(final StyleBuilder templateStyle : templateStyles)
		{
			this.getObject().addTemplateStyle(templateStyle.build());
		}
		return (T)this;
	}
	
	// parameter
	public T parameters(final ParameterBuilder<?>... parameters)
	{
		return this.addParameter(parameters);
	}
	
	public T addParameter(final String name, final Object value)
	{
		return this.addParameter(DynamicReports.parameter(name, value));
	}
	
	public T addParameter(final String name, final Class<?> valueClass)
	{
		return this.addParameter(DynamicReports.parameter(name, valueClass));
	}
	
	public T addParameter(final ParameterBuilder<?>... parameters)
	{
		Validate.notNull(parameters, "parameters must not be null");
		Validate.noNullElements(parameters, "parameters must not contains null parameter");
		for(final ParameterBuilder<?> parameter : parameters)
		{
			this.getObject().addParameter(parameter.build());
		}
		return (T)this;
	}
	
	public T setParameter(final String name, final Object value)
	{
		this.getObject().addParameterValue(name, value);
		return (T)this;
	}
	
	public T setParameters(final Map<String, Object> parameters)
	{
		this.getObject().setParameterValues(parameters);
		return (T)this;
	}
	
	// field
	public T fields(final FieldBuilder<?>... fields)
	{
		return this.addField(fields);
	}
	
	public T addField(final String name, final Class<?> valueClass)
	{
		return this.addField(DynamicReports.field(name, valueClass));
	}
	
	public <U> T addField(final String name, final DRIDataType<? super U, U> dataType)
	{
		return this.addField(DynamicReports.field(name, dataType));
	}
	
	public T addField(final FieldBuilder<?>... fields)
	{
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		for(final FieldBuilder<?> field : fields)
		{
			this.getObject().addField(field.build());
		}
		return (T)this;
	}
	
	// variable
	public T variables(final VariableBuilder<?>... variables)
	{
		return this.addVariable(variables);
	}
	
	public T addVariable(final VariableBuilder<?>... variables)
	{
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		for(final VariableBuilder<?> variable : variables)
		{
			this.getObject().addVariable(variable.getVariable());
		}
		return (T)this;
	}
	
	public T sortBy(final TextColumnBuilder<?>... sortColumns)
	{
		Validate.notNull(sortColumns, "sortColumns must not be null");
		Validate.noNullElements(sortColumns, "sortColumns must not contains null sortColumn");
		for(final TextColumnBuilder<?> sortColumn : sortColumns)
		{
			this.sortBy(DynamicReports.asc(sortColumn));
		}
		return (T)this;
	}
	
	public T sortBy(final SortBuilder... sorts)
	{
		return this.addSort(sorts);
	}
	
	public T addSort(final SortBuilder... sorts)
	{
		Validate.notNull(sorts, "sorts must not be null");
		Validate.noNullElements(sorts, "sorts must not contains null sort");
		for(final SortBuilder sort : sorts)
		{
			this.getObject().addSort(sort.build());
		}
		return (T)this;
	}
	
	// column
	public T columns(final ColumnBuilder<?, ?>... columns)
	{
		return this.addColumn(columns);
	}
	
	public T addColumn(final ColumnBuilder<?, ?>... columns)
	{
		Validate.notNull(columns, "columns must not be null");
		Validate.noNullElements(columns, "columns must not contains null column");
		for(final ColumnBuilder<?, ?> column : columns)
		{
			this.getObject().addColumn(column.build());
		}
		return (T)this;
	}
	
	// style
	public T setDefaultFont(final FontBuilder defaultFont)
	{
		if(defaultFont != null)
		{
			this.getObject().setDefaultFont(defaultFont.build());
		}
		else
		{
			this.getObject().setDefaultFont(null);
		}
		return (T)this;
	}
	
	public T setTextStyle(final ReportStyleBuilder textStyle)
	{
		if(textStyle != null)
		{
			this.getObject().setTextStyle(textStyle.build());
		}
		else
		{
			this.getObject().setTextStyle(null);
		}
		return (T)this;
	}
	
	public T setColumnTitleStyle(final ReportStyleBuilder columnTitleStyle)
	{
		if(columnTitleStyle != null)
		{
			this.getObject().setColumnTitleStyle(columnTitleStyle.build());
		}
		else
		{
			this.getObject().setColumnTitleStyle(null);
		}
		return (T)this;
	}
	
	public T setColumnStyle(final ReportStyleBuilder columnStyle)
	{
		if(columnStyle != null)
		{
			this.getObject().setColumnStyle(columnStyle.build());
		}
		else
		{
			this.getObject().setColumnStyle(null);
		}
		return (T)this;
	}
	
	public T setGroupTitleStyle(final ReportStyleBuilder groupTitleStyle)
	{
		if(groupTitleStyle != null)
		{
			this.getObject().setGroupTitleStyle(groupTitleStyle.build());
		}
		else
		{
			this.getObject().setGroupTitleStyle(null);
		}
		return (T)this;
	}
	
	public T setGroupStyle(final ReportStyleBuilder groupStyle)
	{
		if(groupStyle != null)
		{
			this.getObject().setGroupStyle(groupStyle.build());
		}
		else
		{
			this.getObject().setGroupStyle(null);
		}
		return (T)this;
	}
	
	public T setSubtotalStyle(final ReportStyleBuilder subtotalStyle)
	{
		if(subtotalStyle != null)
		{
			this.getObject().setSubtotalStyle(subtotalStyle.build());
		}
		else
		{
			this.getObject().setSubtotalStyle(null);
		}
		return (T)this;
	}
	
	public T setImageStyle(final ReportStyleBuilder imageStyle)
	{
		if(imageStyle != null)
		{
			this.getObject().setImageStyle(imageStyle.build());
		}
		else
		{
			this.getObject().setImageStyle(null);
		}
		return (T)this;
	}
	
	// row highlighter
	public T highlightDetailOddRows()
	{
		return this.setHighlightDetailOddRows(true);
	}
	
	public T setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.getObject().setHighlightDetailOddRows(highlightDetailOddRows);
		return (T)this;
	}
	
	public T setDetailOddRowStyle(final SimpleStyleBuilder detailOddRowStyle)
	{
		if(detailOddRowStyle != null)
		{
			this.getObject().setDetailOddRowStyle(detailOddRowStyle.build());
		}
		else
		{
			this.getObject().setDetailOddRowStyle(null);
		}
		return (T)this;
	}
	
	public T highlightDetailEvenRows()
	{
		return this.setHighlightDetailEvenRows(true);
	}
	
	public T setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.getObject().setHighlightDetailEvenRows(highlightDetailEvenRows);
		return (T)this;
	}
	
	public T setDetailEvenRowStyle(final SimpleStyleBuilder detailEvenRowStyle)
	{
		if(detailEvenRowStyle != null)
		{
			this.getObject().setDetailEvenRowStyle(detailEvenRowStyle.build());
		}
		else
		{
			this.getObject().setDetailEvenRowStyle(null);
		}
		return (T)this;
	}
	
	public T detailRowHighlighters(final ConditionalStyleBuilder... detailRowHighlighters)
	{
		return this.addDetailRowHighlighter(detailRowHighlighters);
	}
	
	public T addDetailRowHighlighter(final ConditionalStyleBuilder... detailRowHighlighters)
	{
		Validate.notNull(detailRowHighlighters, "detailRowHighlighters must not be null");
		Validate.noNullElements(
			detailRowHighlighters,
			"detailRowHighlighters must not contains null detailRowHighlighter");
		for(final ConditionalStyleBuilder conditionalStyleBuilder : detailRowHighlighters)
		{
			this.getObject().addDetailRowHighlighter(conditionalStyleBuilder.build());
		}
		return (T)this;
	}
	
	// subtotal
	public T subtotalsAtTitle(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtTitle(subtotals);
	}
	
	public T addSubtotalAtTitle(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.TITLE).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtPageHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtPageHeader(subtotals);
	}
	
	public T addSubtotalAtPageHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.PAGE_HEADER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtPageFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtPageFooter(subtotals);
	}
	
	public T addSubtotalAtPageFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.PAGE_FOOTER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtColumnHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtColumnHeader(subtotals);
	}
	
	public T addSubtotalAtColumnHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.COLUMN_HEADER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtColumnFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtColumnFooter(subtotals);
	}
	
	public T addSubtotalAtColumnFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.COLUMN_FOOTER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtGroupHeader(final GroupBuilder<?> group, final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtGroupHeader(group, subtotals);
	}
	
	public T addSubtotalAtGroupHeader(final GroupBuilder<?> group, final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_HEADER).setGroup(group).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtGroupFooter(final GroupBuilder<?> group, final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtGroupFooter(group, subtotals);
	}
	
	public T addSubtotalAtGroupFooter(final GroupBuilder<?> group, final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_FOOTER).setGroup(group).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtFirstGroupHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtFirstGroupHeader(subtotals);
	}
	
	public T addSubtotalAtFirstGroupHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.FIRST_GROUP_HEADER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtFirstGroupFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtFirstGroupFooter(subtotals);
	}
	
	public T addSubtotalAtFirstGroupFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.FIRST_GROUP_FOOTER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtLastGroupHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtLastGroupHeader(subtotals);
	}
	
	public T addSubtotalAtLastGroupHeader(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_GROUP_HEADER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtLastGroupFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtLastGroupFooter(subtotals);
	}
	
	public T addSubtotalAtLastGroupFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_GROUP_FOOTER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtLastPageFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtLastPageFooter(subtotals);
	}
	
	public T addSubtotalAtLastPageFooter(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.LAST_PAGE_FOOTER).build());
		}
		return (T)this;
	}
	
	public T subtotalsAtSummary(final SubtotalBuilder<?, ?>... subtotals)
	{
		return this.addSubtotalAtSummary(subtotals);
	}
	
	public T addSubtotalAtSummary(final SubtotalBuilder<?, ?>... subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final SubtotalBuilder<?, ?> subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.SUMMARY).build());
		}
		return (T)this;
	}
	
	public T subtotalsOfPercentageAtGroupHeader(
		final GroupBuilder<?> group,
		final PercentageSubtotalBuilder... subtotals)
	{
		return this.addSubtotalOfPercentageAtGroupHeader(group, subtotals);
	}
	
	public T addSubtotalOfPercentageAtGroupHeader(
		final GroupBuilder<?> group,
		final PercentageSubtotalBuilder... subtotals)
	{
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final PercentageSubtotalBuilder subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_HEADER).setGroup(group).build());
		}
		return (T)this;
	}
	
	public T subtotalsOfPercentageAtGroupFooter(
		final GroupBuilder<?> group,
		final PercentageSubtotalBuilder... subtotals)
	{
		return this.addSubtotalOfPercentageAtGroupFooter(group, subtotals);
	}
	
	public T addSubtotalOfPercentageAtGroupFooter(
		final GroupBuilder<?> group,
		final PercentageSubtotalBuilder... subtotals)
	{
		Validate.notNull(group, "group must not be null");
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		for(final PercentageSubtotalBuilder subtotal : subtotals)
		{
			this.getObject().addSubtotal(subtotal.setPosition(SubtotalPosition.GROUP_FOOTER).setGroup(group).build());
		}
		return (T)this;
	}
	
	// group
	public T groupBy(final ValueColumnBuilder<?, ?>... groupColumns)
	{
		Validate.notNull(groupColumns, "groupColumns must not be null");
		Validate.noNullElements(groupColumns, "groupColumns must not contains null groupColumn");
		for(final ValueColumnBuilder<?, ?> groupColumn : groupColumns)
		{
			this.addGroup(Groups.group(groupColumn));
		}
		return (T)this;
	}
	
	public T groupBy(final GroupBuilder<?>... groups)
	{
		return this.addGroup(groups);
	}
	
	public T addGroup(final GroupBuilder<?>... groups)
	{
		Validate.notNull(groups, "groups must not be null");
		Validate.noNullElements(groups, "groups must not contains null group");
		for(final GroupBuilder<?> group : groups)
		{
			this.getObject().addGroup(group.build());
		}
		return (T)this;
	}
	
	// table of contents
	public T tableOfContents()
	{
		return this.setTableOfContents(true);
	}
	
	public T tableOfContents(final TableOfContentsCustomizerBuilder tableOfContentsCustomizer)
	{
		return this.setTableOfContents(tableOfContentsCustomizer);
	}
	
	public T tableOfContents(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		return this.setTableOfContents(tableOfContentsCustomizer);
	}
	
	public T setTableOfContents(final Boolean tableOfContents)
	{
		this.getObject().setTableOfContents(tableOfContents);
		return (T)this;
	}
	
	public T setTableOfContents(final TableOfContentsCustomizerBuilder tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer.build());
		return this.setTableOfContents(true);
	}
	
	public T setTableOfContents(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.getObject().setTableOfContentsCustomizer(tableOfContentsCustomizer);
		return this.setTableOfContents(true);
	}
	
	public T setFilterExpression(final DRIExpression<Boolean> filterExpression)
	{
		this.getObject().setFilterExpression(filterExpression);
		return (T)this;
	}
	
	// title
	
	public T setTitleSplitType(final SplitType splitType)
	{
		this.getObject().getTitleBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setTitlePrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getTitleBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setTitleStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getTitleBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getTitleBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setTitleBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getTitleBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T title(final ComponentBuilder<?, ?>... components)
	{
		return this.addTitle(components);
	}
	
	public T addTitle(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getTitleBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// page header band
	
	public T setPageHeaderSplitType(final SplitType splitType)
	{
		this.getObject().getPageHeaderBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setPageHeaderPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getPageHeaderBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setPageHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getPageHeaderBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getPageHeaderBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setPageHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getPageHeaderBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T pageHeader(final ComponentBuilder<?, ?>... components)
	{
		return this.addPageHeader(components);
	}
	
	public T addPageHeader(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getPageHeaderBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// page footer band
	
	public T setPageFooterSplitType(final SplitType splitType)
	{
		this.getObject().getPageFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setPageFooterPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getPageFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setPageFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getPageFooterBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getPageFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getPageFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T pageFooter(final ComponentBuilder<?, ?>... components)
	{
		return this.addPageFooter(components);
	}
	
	public T addPageFooter(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getPageFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// column header band
	
	public T setColumnHeaderSplitType(final SplitType splitType)
	{
		this.getObject().getColumnHeaderBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setColumnHeaderPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getColumnHeaderBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setColumnHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getColumnHeaderBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getColumnHeaderBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setColumnHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getColumnHeaderBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T columnHeader(final ComponentBuilder<?, ?>... components)
	{
		return this.addColumnHeader(components);
	}
	
	public T addColumnHeader(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getColumnHeaderBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// column footer band
	
	public T setColumnFooterSplitType(final SplitType splitType)
	{
		this.getObject().getColumnFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setColumnFooterPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getColumnFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setColumnFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getColumnFooterBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getColumnFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setColumnFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getColumnFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T columnFooter(final ComponentBuilder<?, ?>... components)
	{
		return this.addColumnFooter(components);
	}
	
	public T addColumnFooter(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getColumnFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// group header band
	
	public T setGroupHeaderSplitType(final GroupBuilder<?> group, final SplitType splitType)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getHeaderBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setGroupHeaderPrintWhenExpression(
		final GroupBuilder<?> group,
		final DRIExpression<Boolean> printWhenExpression)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getHeaderBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setGroupHeaderStyle(final GroupBuilder<?> group, final ReportStyleBuilder style)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		if(style != null)
		{
			drGroup.getHeaderBand().getList().setStyle(style.build());
		}
		else
		{
			drGroup.getHeaderBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setGroupHeaderBackgroundComponent(
		final GroupBuilder<?> group,
		final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getHeaderBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T groupHeader(final GroupBuilder<?> group, final ComponentBuilder<?, ?>... components)
	{
		return this.addGroupHeader(group, components);
	}
	
	public T addGroupHeader(final GroupBuilder<?> group, final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		for(final ComponentBuilder<?, ?> component : components)
		{
			drGroup.getHeaderBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// group footer band
	
	public T setGroupFooterSplitType(final GroupBuilder<?> group, final SplitType splitType)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setGroupFooterPrintWhenExpression(
		final GroupBuilder<?> group,
		final DRIExpression<Boolean> printWhenExpression)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setGroupFooterStyle(final GroupBuilder<?> group, final ReportStyleBuilder style)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		if(style != null)
		{
			drGroup.getFooterBand().getList().setStyle(style.build());
		}
		else
		{
			drGroup.getFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setGroupFooterBackgroundComponent(
		final GroupBuilder<?> group,
		final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		drGroup.getFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T groupFooter(final GroupBuilder<?> group, final ComponentBuilder<?, ?>... components)
	{
		return this.addGroupFooter(group, components);
	}
	
	public T addGroupFooter(final GroupBuilder<?> group, final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(group, "group must not be null");
		final int index = this.getObject().getGroups().indexOf(group.getGroup());
		Validate.isTrue(index >= 0, "group must be registered");
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		final DRGroup drGroup = this.getObject().getGroups().get(index);
		for(final ComponentBuilder<?, ?> component : components)
		{
			drGroup.getFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// detail band
	
	public T setDetailSplitType(final SplitType splitType)
	{
		this.getObject().getDetailBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setDetailPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getDetailBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setDetailStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getDetailBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getDetailBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setDetailBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getDetailBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T detail(final ComponentBuilder<?, ?>... components)
	{
		return this.addDetail(components);
	}
	
	public T addDetail(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getDetailBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// detail header band
	
	public T setDetailHeaderSplitType(final SplitType splitType)
	{
		this.getObject().getDetailHeaderBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setDetailHeaderPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getDetailHeaderBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setDetailHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getDetailHeaderBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getDetailHeaderBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setDetailHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getDetailHeaderBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T detailHeader(final ComponentBuilder<?, ?>... components)
	{
		return this.addDetailHeader(components);
	}
	
	public T addDetailHeader(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getDetailHeaderBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// detail footer band
	
	public T setDetailFooterSplitType(final SplitType splitType)
	{
		this.getObject().getDetailFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setDetailFooterPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getDetailFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setDetailFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getDetailFooterBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getDetailFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setDetailFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getDetailFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}

	public T detailFooter(final ComponentBuilder<?, ?>... components)
	{
		return this.addDetailFooter(components);
	}
	
	public T addDetailFooter(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getDetailFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// last page footer band
	
	public T setLastPageFooterSplitType(final SplitType splitType)
	{
		this.getObject().getLastPageFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setLastPageFooterPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getLastPageFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setLastPageFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getLastPageFooterBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getLastPageFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setLastPageFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getLastPageFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T lastPageFooter(final ComponentBuilder<?, ?>... components)
	{
		return this.addLastPageFooter(components);
	}
	
	public T addLastPageFooter(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getLastPageFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// summary band
	
	public T setSummarySplitType(final SplitType splitType)
	{
		this.getObject().getSummaryBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setSummaryPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getSummaryBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setSummaryStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getSummaryBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getSummaryBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setSummaryBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getSummaryBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T summary(final ComponentBuilder<?, ?>... components)
	{
		return this.addSummary(components);
	}
	
	public T addSummary(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getSummaryBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// no data band
	
	public T setNoDataSplitType(final SplitType splitType)
	{
		this.getObject().getNoDataBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setNoDataPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getNoDataBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setNoDataStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getNoDataBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getNoDataBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setNoDataBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getNoDataBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T noData(final ComponentBuilder<?, ?>... components)
	{
		return this.addNoData(components);
	}
	
	public T addNoData(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getNoDataBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	// background band

	public T setBackgroundSplitType(final SplitType splitType)
	{
		this.getObject().getBackgroundBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setBackgroundPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getBackgroundBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setBackgroundStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getBackgroundBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getBackgroundBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setBackgroundBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getBackgroundBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T background(final ComponentBuilder<?, ?>... components)
	{
		return this.addBackground(components);
	}
	
	public T addBackground(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getBackgroundBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	public DRReport getReport()
	{
		return this.build();
	}
}
