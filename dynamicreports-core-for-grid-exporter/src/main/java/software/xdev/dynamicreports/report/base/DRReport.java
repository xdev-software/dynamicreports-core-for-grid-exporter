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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.column.DRColumn;
import software.xdev.dynamicreports.report.base.grid.DRColumnGrid;
import software.xdev.dynamicreports.report.base.style.DRConditionalStyle;
import software.xdev.dynamicreports.report.base.style.DRSimpleStyle;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIFont;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;


public class DRReport implements DRIReport
{

	private DRReportTemplate template;
	private List<DRIStyle> templateStyles;
	private DRITemplateDesign<?> templateDesign;
	private String reportName;
	private Locale locale;
	private ResourceBundle resourceBundle;
	private String resourceBundleName;
	private Boolean ignorePagination;
	private Boolean showColumnTitle;
	private Boolean showColumnValues;
	private List<DRColumn<?>> columns;
	private List<DRGroup> groups;
	private List<DRSubtotal<?>> subtotals;
	private List<DRField<?>> fields;
	private List<DRVariable<?>> variables;
	private List<DRSort> sorts;
	private List<DRParameter<?>> parameters;
	private Map<String, Object> parameterValues;
	private List<DRIScriptlet> scriptlets;
	private Properties properties;
	private DRQuery query;
	private DRPage page;
	private WhenNoDataType whenNoDataType;
	private WhenResourceMissingType whenResourceMissingType;
	private Boolean titleOnANewPage;
	private Boolean summaryOnANewPage;
	private Boolean summaryWithPageHeaderAndFooter;
	private Boolean floatColumnFooter;
	private Orientation printOrder;
	private RunDirection columnDirection;
	private String language;
	private Boolean useFieldNameAsDescription;
	private Boolean highlightDetailOddRows;
	private DRSimpleStyle detailOddRowStyle;
	private Boolean highlightDetailEvenRows;
	private DRSimpleStyle detailEvenRowStyle;
	private List<DRConditionalStyle> detailRowHighlighters;
	private DRColumnGrid columnGrid;
	private Boolean tableOfContents;
	private DRITableOfContentsCustomizer tableOfContentsCustomizer;
	private DRIExpression<Boolean> filterExpression;
	
	private DRIFont defaultFont;
	private DRIReportStyle textStyle;
	private DRIReportStyle columnTitleStyle;
	private DRIReportStyle columnStyle;
	private DRIReportStyle groupTitleStyle;
	private DRIReportStyle groupStyle;
	private DRIReportStyle subtotalStyle;
	private DRIReportStyle imageStyle;
	
	private DRBand titleBand;
	private DRBand pageHeaderBand;
	private DRBand pageFooterBand;
	private DRBand columnHeaderBand;
	private DRBand columnFooterBand;
	private DRBand detailBand;
	private DRBand detailHeaderBand;
	private DRBand detailFooterBand;
	private DRBand lastPageFooterBand;
	private DRBand summaryBand;
	private DRBand noDataBand;
	private DRBand backgroundBand;
	
	public DRReport()
	{
		this.init();
	}
	
	private void init()
	{
		this.template = new DRReportTemplate();
		this.templateStyles = new ArrayList<>();
		this.columns = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.subtotals = new ArrayList<>();
		this.fields = new ArrayList<>();
		this.variables = new ArrayList<>();
		this.sorts = new ArrayList<>();
		this.parameters = new ArrayList<>();
		this.scriptlets = new ArrayList<>();
		this.detailRowHighlighters = new ArrayList<>();
		this.properties = new Properties();
		this.page = new DRPage();
		
		this.titleBand = new DRBand();
		this.pageHeaderBand = new DRBand();
		this.pageFooterBand = new DRBand();
		this.columnHeaderBand = new DRBand();
		this.columnFooterBand = new DRBand();
		this.detailBand = new DRBand();
		this.detailHeaderBand = new DRBand();
		this.detailFooterBand = new DRBand();
		this.lastPageFooterBand = new DRBand();
		this.summaryBand = new DRBand();
		this.noDataBand = new DRBand();
		this.backgroundBand = new DRBand();
	}
	
	@Override
	public DRReportTemplate getTemplate()
	{
		return this.template;
	}
	
	public void setTemplate(final DRReportTemplate template)
	{
		Validate.notNull(template, "template must not be null");
		this.template = template;
	}
	
	@Override
	public List<DRIStyle> getTemplateStyles()
	{
		return this.templateStyles;
	}
	
	public void setTemplateStyles(final List<DRIStyle> templateStyles)
	{
		this.templateStyles = templateStyles;
	}
	
	public void addTemplateStyle(final DRIStyle templateStyle)
	{
		Validate.notNull(templateStyle, "templateStyle must not be null");
		Validate.notNull(templateStyle.getName(), "templateStyle name must not be null");
		this.templateStyles.add(templateStyle);
	}
	
	@Override
	public DRITemplateDesign<?> getTemplateDesign()
	{
		return this.templateDesign;
	}
	
	public void setTemplateDesign(final DRITemplateDesign<?> templateDesign)
	{
		Validate.notNull(templateDesign, "templateDesign must not be null");
		this.templateDesign = templateDesign;
	}
	
	@Override
	public String getReportName()
	{
		return this.reportName;
	}
	
	public void setReportName(final String reportName)
	{
		this.reportName = reportName;
	}
	
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}
	
	public void setLocale(final Locale locale)
	{
		this.locale = locale;
	}
	
	@Override
	public ResourceBundle getResourceBundle()
	{
		return this.resourceBundle;
	}
	
	public void setResourceBundle(final ResourceBundle resourceBundle)
	{
		this.resourceBundle = resourceBundle;
	}
	
	@Override
	public String getResourceBundleName()
	{
		return this.resourceBundleName;
	}
	
	public void setResourceBundleName(final String resourceBundleName)
	{
		this.resourceBundleName = resourceBundleName;
	}
	
	@Override
	public Boolean getShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	public void setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.showColumnTitle = showColumnTitle;
	}
	
	@Override
	public Boolean getShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	public void setShowColumnValues(final Boolean showColumnValues)
	{
		this.showColumnValues = showColumnValues;
	}
	
	@Override
	public List<DRColumn<?>> getColumns()
	{
		return this.columns;
	}
	
	public void setColumns(final List<DRColumn<?>> columns)
	{
		Validate.notNull(columns, "columns must not be null");
		Validate.noNullElements(columns, "columns must not contains null column");
		this.columns = columns;
	}
	
	public void addColumn(final DRColumn<?> column)
	{
		Validate.notNull(column, "column must not be null");
		this.columns.add(column);
	}
	
	@Override
	public List<DRGroup> getGroups()
	{
		return this.groups;
	}
	
	public void setGroups(final List<DRGroup> groups)
	{
		Validate.notNull(groups, "groups must not be null");
		Validate.noNullElements(groups, "groups must not contains null group");
		this.groups = groups;
	}
	
	public void addGroup(final DRGroup group)
	{
		Validate.notNull(group, "group must not be null");
		this.groups.add(group);
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
	public List<DRSubtotal<?>> getSubtotals()
	{
		return this.subtotals;
	}
	
	public void setSubtotals(final List<DRSubtotal<?>> subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		this.subtotals = subtotals;
	}
	
	public void addSubtotal(final DRSubtotal<?> subtotal)
	{
		Validate.notNull(subtotal, "subtotal must not be null");
		this.subtotals.add(subtotal);
	}
	
	@Override
	public List<DRParameter<?>> getParameters()
	{
		return this.parameters;
	}
	
	public void setParameters(final List<DRParameter<?>> parameters)
	{
		Validate.notNull(parameters, "parameters must not be null");
		Validate.noNullElements(parameters, "parameters must not contains null parameter");
		this.parameters = parameters;
	}
	
	public void addParameter(final DRParameter<?> parameter)
	{
		Validate.notNull(parameter, "parameter must not be null");
		this.parameters.add(parameter);
	}
	
	@Override
	public Map<String, Object> getParameterValues()
	{
		return this.parameterValues;
	}
	
	public void setParameterValues(final Map<String, Object> parameterValues)
	{
		this.parameterValues = parameterValues;
	}
	
	public void addParameterValue(final String name, final Object value)
	{
		Validate.notNull(name, "parameter name must not be null");
		if(this.parameterValues == null)
		{
			this.parameterValues = new HashMap<>();
		}
		this.parameterValues.put(name, value);
	}
	
	@Override
	public List<DRIScriptlet> getScriptlets()
	{
		return this.scriptlets;
	}
	
	public void setScriptlets(final List<DRIScriptlet> scriptlets)
	{
		Validate.notNull(scriptlets, "scriptlets must not be null");
		Validate.noNullElements(scriptlets, "scriptlets must not contains null scriptlet");
		this.scriptlets = scriptlets;
	}
	
	public void addScriptlet(final DRIScriptlet scriptlet)
	{
		Validate.notNull(scriptlet, "scriptlet must not be null");
		this.scriptlets.add(scriptlet);
	}
	
	@Override
	public Properties getProperties()
	{
		return this.properties;
	}
	
	public void setProperties(final Properties properties)
	{
		Validate.notNull(properties, "properties must not be null");
		this.properties = properties;
	}
	
	public void addProperty(final String key, final String value)
	{
		Validate.notNull(key, "key must not be null");
		this.properties.setProperty(key, value);
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
	public DRPage getPage()
	{
		return this.page;
	}
	
	public void setPage(final DRPage page)
	{
		Validate.notNull(page, "page must not be null");
		this.page = page;
	}
	
	@Override
	public Boolean getIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	public void setIgnorePagination(final Boolean ignorePagination)
	{
		this.ignorePagination = ignorePagination;
	}
	
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	public void setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.whenNoDataType = whenNoDataType;
	}
	
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	public void setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.whenResourceMissingType = whenResourceMissingType;
	}
	
	@Override
	public Boolean getTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	public void setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.titleOnANewPage = titleOnANewPage;
	}
	
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	public void setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.summaryOnANewPage = summaryOnANewPage;
	}
	
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	public void setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}
	
	@Override
	public Boolean getFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	public void setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.floatColumnFooter = floatColumnFooter;
	}
	
	@Override
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	public void setPrintOrder(final Orientation printOrder)
	{
		this.printOrder = printOrder;
	}
	
	@Override
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	public void setColumnDirection(final RunDirection columnDirection)
	{
		this.columnDirection = columnDirection;
	}
	
	@Override
	public String getLanguage()
	{
		return this.language;
	}
	
	public void setLanguage(final String language)
	{
		this.language = language;
	}
	
	@Override
	public Boolean getUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	public void setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}
	
	@Override
	public DRIFont getDefaultFont()
	{
		return this.defaultFont;
	}
	
	public void setDefaultFont(final DRIFont defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	@Override
	public DRIReportStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	public void setTextStyle(final DRIReportStyle textStyle)
	{
		this.textStyle = textStyle;
	}
	
	@Override
	public DRIReportStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	public void setColumnTitleStyle(final DRIReportStyle columnTitleStyle)
	{
		this.columnTitleStyle = columnTitleStyle;
	}
	
	@Override
	public DRIReportStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	public void setColumnStyle(final DRIReportStyle columnStyle)
	{
		this.columnStyle = columnStyle;
	}
	
	@Override
	public DRIReportStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	public void setGroupTitleStyle(final DRIReportStyle groupTitleStyle)
	{
		this.groupTitleStyle = groupTitleStyle;
	}
	
	@Override
	public DRIReportStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	public void setGroupStyle(final DRIReportStyle groupStyle)
	{
		this.groupStyle = groupStyle;
	}
	
	@Override
	public DRIReportStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	public void setSubtotalStyle(final DRIReportStyle subtotalStyle)
	{
		this.subtotalStyle = subtotalStyle;
	}
	
	@Override
	public DRIReportStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	public void setImageStyle(final DRIReportStyle imageStyle)
	{
		this.imageStyle = imageStyle;
	}
	
	@Override
	public Boolean getHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	public void setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.highlightDetailOddRows = highlightDetailOddRows;
	}
	
	@Override
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	public void setDetailOddRowStyle(final DRSimpleStyle detailOddRowStyle)
	{
		this.detailOddRowStyle = detailOddRowStyle;
	}
	
	@Override
	public Boolean getHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	public void setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}
	
	@Override
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	public void setDetailEvenRowStyle(final DRSimpleStyle detailEvenRowStyle)
	{
		this.detailEvenRowStyle = detailEvenRowStyle;
	}
	
	@Override
	public List<DRConditionalStyle> getDetailRowHighlighters()
	{
		return this.detailRowHighlighters;
	}
	
	public void setDetailRowHighlighters(final List<DRConditionalStyle> detailRowHighlighters)
	{
		Validate.notNull(detailRowHighlighters, "detailRowHighlighters must not be null");
		Validate.noNullElements(
			detailRowHighlighters,
			"detailRowHighlighters must not contains null detailRowHighlighter");
		this.detailRowHighlighters = detailRowHighlighters;
	}
	
	public void addDetailRowHighlighter(final DRConditionalStyle detailRowHighlighter)
	{
		Validate.notNull(detailRowHighlighter, "detailRowHighlighter must not be null");
		this.detailRowHighlighters.add(detailRowHighlighter);
	}
	
	@Override
	public DRColumnGrid getColumnGrid()
	{
		return this.columnGrid;
	}
	
	public void setColumnGrid(final DRColumnGrid columnGrid)
	{
		this.columnGrid = columnGrid;
	}
	
	@Override
	public Boolean getTableOfContents()
	{
		return this.tableOfContents;
	}
	
	public void setTableOfContents(final Boolean tableOfContents)
	{
		this.tableOfContents = tableOfContents;
	}
	
	@Override
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	public void setTableOfContentsCustomizer(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
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
	
	@Override
	public DRBand getTitleBand()
	{
		return this.titleBand;
	}
	
	@Override
	public DRBand getPageHeaderBand()
	{
		return this.pageHeaderBand;
	}
	
	@Override
	public DRBand getPageFooterBand()
	{
		return this.pageFooterBand;
	}
	
	@Override
	public DRBand getColumnHeaderBand()
	{
		return this.columnHeaderBand;
	}
	
	@Override
	public DRBand getColumnFooterBand()
	{
		return this.columnFooterBand;
	}
	
	@Override
	public DRBand getDetailBand()
	{
		return this.detailBand;
	}
	
	@Override
	public DRBand getDetailHeaderBand()
	{
		return this.detailHeaderBand;
	}
	
	@Override
	public DRBand getDetailFooterBand()
	{
		return this.detailFooterBand;
	}
	
	@Override
	public DRBand getLastPageFooterBand()
	{
		return this.lastPageFooterBand;
	}
	
	@Override
	public DRBand getSummaryBand()
	{
		return this.summaryBand;
	}
	
	@Override
	public DRBand getNoDataBand()
	{
		return this.noDataBand;
	}
	
	@Override
	public DRBand getBackgroundBand()
	{
		return this.backgroundBand;
	}
}
