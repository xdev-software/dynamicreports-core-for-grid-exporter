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
package net.sf.dynamicreports.report.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.Validate;

import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.grid.DRColumnGrid;
import net.sf.dynamicreports.report.base.style.DRConditionalStyle;
import net.sf.dynamicreports.report.base.style.DRSimpleStyle;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.style.DRIFont;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.dynamicreports.report.definition.style.DRIStyle;


/**
 * <p>DRReport class.</p>
 *
 * @author Ricardo Mariaca
 */
public class DRReport implements DRIReport
{
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
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
	
	/**
	 * <p>Constructor for DRReport.</p>
	 */
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRReportTemplate getTemplate()
	{
		return this.template;
	}
	
	/**
	 * <p>Setter for the field <code>template</code>.</p>
	 *
	 * @param template a {@link net.sf.dynamicreports.report.base.DRReportTemplate} object.
	 */
	public void setTemplate(final DRReportTemplate template)
	{
		Validate.notNull(template, "template must not be null");
		this.template = template;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRIStyle> getTemplateStyles()
	{
		return this.templateStyles;
	}
	
	/**
	 * <p>Setter for the field <code>templateStyles</code>.</p>
	 *
	 * @param templateStyles a {@link java.util.List} object.
	 */
	public void setTemplateStyles(final List<DRIStyle> templateStyles)
	{
		this.templateStyles = templateStyles;
	}
	
	/**
	 * <p>addTemplateStyle.</p>
	 *
	 * @param templateStyle a {@link net.sf.dynamicreports.report.definition.style.DRIStyle} object.
	 */
	public void addTemplateStyle(final DRIStyle templateStyle)
	{
		Validate.notNull(templateStyle, "templateStyle must not be null");
		Validate.notNull(templateStyle.getName(), "templateStyle name must not be null");
		this.templateStyles.add(templateStyle);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRITemplateDesign<?> getTemplateDesign()
	{
		return this.templateDesign;
	}
	
	/**
	 * <p>Setter for the field <code>templateDesign</code>.</p>
	 *
	 * @param templateDesign a {@link net.sf.dynamicreports.report.definition.DRITemplateDesign} object.
	 */
	public void setTemplateDesign(final DRITemplateDesign<?> templateDesign)
	{
		Validate.notNull(templateDesign, "templateDesign must not be null");
		this.templateDesign = templateDesign;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getReportName()
	{
		return this.reportName;
	}
	
	/**
	 * <p>Setter for the field <code>reportName</code>.</p>
	 *
	 * @param reportName a {@link java.lang.String} object.
	 */
	public void setReportName(final String reportName)
	{
		this.reportName = reportName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}
	
	/**
	 * <p>Setter for the field <code>locale</code>.</p>
	 *
	 * @param locale a {@link java.util.Locale} object.
	 */
	public void setLocale(final Locale locale)
	{
		this.locale = locale;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceBundle getResourceBundle()
	{
		return this.resourceBundle;
	}
	
	/**
	 * <p>Setter for the field <code>resourceBundle</code>.</p>
	 *
	 * @param resourceBundle a {@link java.util.ResourceBundle} object.
	 */
	public void setResourceBundle(final ResourceBundle resourceBundle)
	{
		this.resourceBundle = resourceBundle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getResourceBundleName()
	{
		return this.resourceBundleName;
	}
	
	/**
	 * <p>Setter for the field <code>resourceBundleName</code>.</p>
	 *
	 * @param resourceBundleName a {@link java.lang.String} object.
	 */
	public void setResourceBundleName(final String resourceBundleName)
	{
		this.resourceBundleName = resourceBundleName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getShowColumnTitle()
	{
		return this.showColumnTitle;
	}
	
	/**
	 * <p>Setter for the field <code>showColumnTitle</code>.</p>
	 *
	 * @param showColumnTitle a {@link java.lang.Boolean} object.
	 */
	public void setShowColumnTitle(final Boolean showColumnTitle)
	{
		this.showColumnTitle = showColumnTitle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getShowColumnValues()
	{
		return this.showColumnValues;
	}
	
	/**
	 * <p>Setter for the field <code>showColumnValues</code>.</p>
	 *
	 * @param showColumnValues a {@link java.lang.Boolean} object.
	 */
	public void setShowColumnValues(final Boolean showColumnValues)
	{
		this.showColumnValues = showColumnValues;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRColumn<?>> getColumns()
	{
		return this.columns;
	}
	
	/**
	 * <p>Setter for the field <code>columns</code>.</p>
	 *
	 * @param columns a {@link java.util.List} object.
	 */
	public void setColumns(final List<DRColumn<?>> columns)
	{
		Validate.notNull(columns, "columns must not be null");
		Validate.noNullElements(columns, "columns must not contains null column");
		this.columns = columns;
	}
	
	/**
	 * <p>addColumn.</p>
	 *
	 * @param column a {@link net.sf.dynamicreports.report.base.column.DRColumn} object.
	 */
	public void addColumn(final DRColumn<?> column)
	{
		Validate.notNull(column, "column must not be null");
		this.columns.add(column);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRGroup> getGroups()
	{
		return this.groups;
	}
	
	/**
	 * <p>Setter for the field <code>groups</code>.</p>
	 *
	 * @param groups a {@link java.util.List} object.
	 */
	public void setGroups(final List<DRGroup> groups)
	{
		Validate.notNull(groups, "groups must not be null");
		Validate.noNullElements(groups, "groups must not contains null group");
		this.groups = groups;
	}
	
	/**
	 * <p>addGroup.</p>
	 *
	 * @param group a {@link net.sf.dynamicreports.report.base.DRGroup} object.
	 */
	public void addGroup(final DRGroup group)
	{
		Validate.notNull(group, "group must not be null");
		this.groups.add(group);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRField<?>> getFields()
	{
		return this.fields;
	}
	
	/**
	 * <p>Setter for the field <code>fields</code>.</p>
	 *
	 * @param fields a {@link java.util.List} object.
	 */
	public void setFields(final List<DRField<?>> fields)
	{
		Validate.notNull(fields, "fields must not be null");
		Validate.noNullElements(fields, "fields must not contains null field");
		this.fields = fields;
	}
	
	/**
	 * <p>addField.</p>
	 *
	 * @param field a {@link net.sf.dynamicreports.report.base.DRField} object.
	 */
	public void addField(final DRField<?> field)
	{
		Validate.notNull(field, "field must not be null");
		this.fields.add(field);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRVariable<?>> getVariables()
	{
		return this.variables;
	}
	
	/**
	 * <p>Setter for the field <code>variables</code>.</p>
	 *
	 * @param variables a {@link java.util.List} object.
	 */
	public void setVariables(final List<DRVariable<?>> variables)
	{
		Validate.notNull(variables, "variables must not be null");
		Validate.noNullElements(variables, "variables must not contains null variable");
		this.variables = variables;
	}
	
	/**
	 * <p>addVariable.</p>
	 *
	 * @param variable a {@link net.sf.dynamicreports.report.base.DRVariable} object.
	 */
	public void addVariable(final DRVariable<?> variable)
	{
		Validate.notNull(variable, "variable must not be null");
		this.variables.add(variable);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRSort> getSorts()
	{
		return this.sorts;
	}
	
	/**
	 * <p>Setter for the field <code>sorts</code>.</p>
	 *
	 * @param sorts a {@link java.util.List} object.
	 */
	public void setSorts(final List<DRSort> sorts)
	{
		Validate.notNull(sorts, "sorts must not be null");
		Validate.noNullElements(sorts, "sorts must not contains null sort");
		this.sorts = sorts;
	}
	
	/**
	 * <p>addSort.</p>
	 *
	 * @param sort a {@link net.sf.dynamicreports.report.base.DRSort} object.
	 */
	public void addSort(final DRSort sort)
	{
		Validate.notNull(sort, "sort must not be null");
		this.sorts.add(sort);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRSubtotal<?>> getSubtotals()
	{
		return this.subtotals;
	}
	
	/**
	 * <p>Setter for the field <code>subtotals</code>.</p>
	 *
	 * @param subtotals a {@link java.util.List} object.
	 */
	public void setSubtotals(final List<DRSubtotal<?>> subtotals)
	{
		Validate.notNull(subtotals, "subtotals must not be null");
		Validate.noNullElements(subtotals, "subtotals must not contains null subtotal");
		this.subtotals = subtotals;
	}
	
	/**
	 * <p>addSubtotal.</p>
	 *
	 * @param subtotal a {@link net.sf.dynamicreports.report.base.DRSubtotal} object.
	 */
	public void addSubtotal(final DRSubtotal<?> subtotal)
	{
		Validate.notNull(subtotal, "subtotal must not be null");
		this.subtotals.add(subtotal);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRParameter<?>> getParameters()
	{
		return this.parameters;
	}
	
	/**
	 * <p>Setter for the field <code>parameters</code>.</p>
	 *
	 * @param parameters a {@link java.util.List} object.
	 */
	public void setParameters(final List<DRParameter<?>> parameters)
	{
		Validate.notNull(parameters, "parameters must not be null");
		Validate.noNullElements(parameters, "parameters must not contains null parameter");
		this.parameters = parameters;
	}
	
	/**
	 * <p>addParameter.</p>
	 *
	 * @param parameter a {@link net.sf.dynamicreports.report.base.DRParameter} object.
	 */
	public void addParameter(final DRParameter<?> parameter)
	{
		Validate.notNull(parameter, "parameter must not be null");
		this.parameters.add(parameter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Object> getParameterValues()
	{
		return this.parameterValues;
	}
	
	/**
	 * <p>Setter for the field <code>parameterValues</code>.</p>
	 *
	 * @param parameterValues a {@link java.util.Map} object.
	 */
	public void setParameterValues(final Map<String, Object> parameterValues)
	{
		this.parameterValues = parameterValues;
	}
	
	/**
	 * <p>addParameterValue.</p>
	 *
	 * @param name  a {@link java.lang.String} object.
	 * @param value a {@link java.lang.Object} object.
	 */
	public void addParameterValue(final String name, final Object value)
	{
		Validate.notNull(name, "parameter name must not be null");
		if(this.parameterValues == null)
		{
			this.parameterValues = new HashMap<>();
		}
		this.parameterValues.put(name, value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRIScriptlet> getScriptlets()
	{
		return this.scriptlets;
	}
	
	/**
	 * <p>Setter for the field <code>scriptlets</code>.</p>
	 *
	 * @param scriptlets a {@link java.util.List} object.
	 */
	public void setScriptlets(final List<DRIScriptlet> scriptlets)
	{
		Validate.notNull(scriptlets, "scriptlets must not be null");
		Validate.noNullElements(scriptlets, "scriptlets must not contains null scriptlet");
		this.scriptlets = scriptlets;
	}
	
	/**
	 * <p>addScriptlet.</p>
	 *
	 * @param scriptlet a {@link net.sf.dynamicreports.report.definition.DRIScriptlet} object.
	 */
	public void addScriptlet(final DRIScriptlet scriptlet)
	{
		Validate.notNull(scriptlet, "scriptlet must not be null");
		this.scriptlets.add(scriptlet);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties()
	{
		return this.properties;
	}
	
	/**
	 * <p>Setter for the field <code>properties</code>.</p>
	 *
	 * @param properties a {@link java.util.Properties} object.
	 */
	public void setProperties(final Properties properties)
	{
		Validate.notNull(properties, "properties must not be null");
		this.properties = properties;
	}
	
	/**
	 * <p>addProperty.</p>
	 *
	 * @param key   a {@link java.lang.String} object.
	 * @param value a {@link java.lang.String} object.
	 */
	public void addProperty(final String key, final String value)
	{
		Validate.notNull(key, "key must not be null");
		this.properties.setProperty(key, value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRQuery getQuery()
	{
		return this.query;
	}
	
	/**
	 * <p>Setter for the field <code>query</code>.</p>
	 *
	 * @param query a {@link net.sf.dynamicreports.report.base.DRQuery} object.
	 */
	public void setQuery(final DRQuery query)
	{
		this.query = query;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRPage getPage()
	{
		return this.page;
	}
	
	/**
	 * <p>Setter for the field <code>page</code>.</p>
	 *
	 * @param page a {@link net.sf.dynamicreports.report.base.DRPage} object.
	 */
	public void setPage(final DRPage page)
	{
		Validate.notNull(page, "page must not be null");
		this.page = page;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getIgnorePagination()
	{
		return this.ignorePagination;
	}
	
	/**
	 * <p>Setter for the field <code>ignorePagination</code>.</p>
	 *
	 * @param ignorePagination a {@link java.lang.Boolean} object.
	 */
	public void setIgnorePagination(final Boolean ignorePagination)
	{
		this.ignorePagination = ignorePagination;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return this.whenNoDataType;
	}
	
	/**
	 * <p>Setter for the field <code>whenNoDataType</code>.</p>
	 *
	 * @param whenNoDataType a {@link net.sf.dynamicreports.report.constant.WhenNoDataType} object.
	 */
	public void setWhenNoDataType(final WhenNoDataType whenNoDataType)
	{
		this.whenNoDataType = whenNoDataType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.whenResourceMissingType;
	}
	
	/**
	 * <p>Setter for the field <code>whenResourceMissingType</code>.</p>
	 *
	 * @param whenResourceMissingType a {@link net.sf.dynamicreports.report.constant.WhenResourceMissingType} object.
	 */
	public void setWhenResourceMissingType(final WhenResourceMissingType whenResourceMissingType)
	{
		this.whenResourceMissingType = whenResourceMissingType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getTitleOnANewPage()
	{
		return this.titleOnANewPage;
	}
	
	/**
	 * <p>Setter for the field <code>titleOnANewPage</code>.</p>
	 *
	 * @param titleOnANewPage a {@link java.lang.Boolean} object.
	 */
	public void setTitleOnANewPage(final Boolean titleOnANewPage)
	{
		this.titleOnANewPage = titleOnANewPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return this.summaryOnANewPage;
	}
	
	/**
	 * <p>Setter for the field <code>summaryOnANewPage</code>.</p>
	 *
	 * @param summaryOnANewPage a {@link java.lang.Boolean} object.
	 */
	public void setSummaryOnANewPage(final Boolean summaryOnANewPage)
	{
		this.summaryOnANewPage = summaryOnANewPage;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return this.summaryWithPageHeaderAndFooter;
	}
	
	/**
	 * <p>Setter for the field <code>summaryWithPageHeaderAndFooter</code>.</p>
	 *
	 * @param summaryWithPageHeaderAndFooter a {@link java.lang.Boolean} object.
	 */
	public void setSummaryWithPageHeaderAndFooter(final Boolean summaryWithPageHeaderAndFooter)
	{
		this.summaryWithPageHeaderAndFooter = summaryWithPageHeaderAndFooter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getFloatColumnFooter()
	{
		return this.floatColumnFooter;
	}
	
	/**
	 * <p>Setter for the field <code>floatColumnFooter</code>.</p>
	 *
	 * @param floatColumnFooter a {@link java.lang.Boolean} object.
	 */
	public void setFloatColumnFooter(final Boolean floatColumnFooter)
	{
		this.floatColumnFooter = floatColumnFooter;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Orientation getPrintOrder()
	{
		return this.printOrder;
	}
	
	/**
	 * <p>Setter for the field <code>printOrder</code>.</p>
	 *
	 * @param printOrder a {@link net.sf.dynamicreports.report.constant.Orientation} object.
	 */
	public void setPrintOrder(final Orientation printOrder)
	{
		this.printOrder = printOrder;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RunDirection getColumnDirection()
	{
		return this.columnDirection;
	}
	
	/**
	 * <p>Setter for the field <code>columnDirection</code>.</p>
	 *
	 * @param columnDirection a {@link net.sf.dynamicreports.report.constant.RunDirection} object.
	 */
	public void setColumnDirection(final RunDirection columnDirection)
	{
		this.columnDirection = columnDirection;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLanguage()
	{
		return this.language;
	}
	
	/**
	 * <p>Setter for the field <code>language</code>.</p>
	 *
	 * @param language a {@link java.lang.String} object.
	 */
	public void setLanguage(final String language)
	{
		this.language = language;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getUseFieldNameAsDescription()
	{
		return this.useFieldNameAsDescription;
	}
	
	/**
	 * <p>Setter for the field <code>useFieldNameAsDescription</code>.</p>
	 *
	 * @param useFieldNameAsDescription a {@link java.lang.Boolean} object.
	 */
	public void setUseFieldNameAsDescription(final Boolean useFieldNameAsDescription)
	{
		this.useFieldNameAsDescription = useFieldNameAsDescription;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIFont getDefaultFont()
	{
		return this.defaultFont;
	}
	
	/**
	 * <p>Setter for the field <code>defaultFont</code>.</p>
	 *
	 * @param defaultFont a {@link net.sf.dynamicreports.report.definition.style.DRIFont} object.
	 */
	public void setDefaultFont(final DRIFont defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getTextStyle()
	{
		return this.textStyle;
	}
	
	/**
	 * <p>Setter for the field <code>textStyle</code>.</p>
	 *
	 * @param textStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setTextStyle(final DRIReportStyle textStyle)
	{
		this.textStyle = textStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnTitleStyle()
	{
		return this.columnTitleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnTitleStyle</code>.</p>
	 *
	 * @param columnTitleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnTitleStyle(final DRIReportStyle columnTitleStyle)
	{
		this.columnTitleStyle = columnTitleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getColumnStyle()
	{
		return this.columnStyle;
	}
	
	/**
	 * <p>Setter for the field <code>columnStyle</code>.</p>
	 *
	 * @param columnStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setColumnStyle(final DRIReportStyle columnStyle)
	{
		this.columnStyle = columnStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupTitleStyle()
	{
		return this.groupTitleStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupTitleStyle</code>.</p>
	 *
	 * @param groupTitleStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupTitleStyle(final DRIReportStyle groupTitleStyle)
	{
		this.groupTitleStyle = groupTitleStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getGroupStyle()
	{
		return this.groupStyle;
	}
	
	/**
	 * <p>Setter for the field <code>groupStyle</code>.</p>
	 *
	 * @param groupStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setGroupStyle(final DRIReportStyle groupStyle)
	{
		this.groupStyle = groupStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getSubtotalStyle()
	{
		return this.subtotalStyle;
	}
	
	/**
	 * <p>Setter for the field <code>subtotalStyle</code>.</p>
	 *
	 * @param subtotalStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setSubtotalStyle(final DRIReportStyle subtotalStyle)
	{
		this.subtotalStyle = subtotalStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIReportStyle getImageStyle()
	{
		return this.imageStyle;
	}
	
	/**
	 * <p>Setter for the field <code>imageStyle</code>.</p>
	 *
	 * @param imageStyle a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
	 */
	public void setImageStyle(final DRIReportStyle imageStyle)
	{
		this.imageStyle = imageStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getHighlightDetailOddRows()
	{
		return this.highlightDetailOddRows;
	}
	
	/**
	 * <p>Setter for the field <code>highlightDetailOddRows</code>.</p>
	 *
	 * @param highlightDetailOddRows a {@link java.lang.Boolean} object.
	 */
	public void setHighlightDetailOddRows(final Boolean highlightDetailOddRows)
	{
		this.highlightDetailOddRows = highlightDetailOddRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getDetailOddRowStyle()
	{
		return this.detailOddRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailOddRowStyle</code>.</p>
	 *
	 * @param detailOddRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setDetailOddRowStyle(final DRSimpleStyle detailOddRowStyle)
	{
		this.detailOddRowStyle = detailOddRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getHighlightDetailEvenRows()
	{
		return this.highlightDetailEvenRows;
	}
	
	/**
	 * <p>Setter for the field <code>highlightDetailEvenRows</code>.</p>
	 *
	 * @param highlightDetailEvenRows a {@link java.lang.Boolean} object.
	 */
	public void setHighlightDetailEvenRows(final Boolean highlightDetailEvenRows)
	{
		this.highlightDetailEvenRows = highlightDetailEvenRows;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRSimpleStyle getDetailEvenRowStyle()
	{
		return this.detailEvenRowStyle;
	}
	
	/**
	 * <p>Setter for the field <code>detailEvenRowStyle</code>.</p>
	 *
	 * @param detailEvenRowStyle a {@link net.sf.dynamicreports.report.base.style.DRSimpleStyle} object.
	 */
	public void setDetailEvenRowStyle(final DRSimpleStyle detailEvenRowStyle)
	{
		this.detailEvenRowStyle = detailEvenRowStyle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DRConditionalStyle> getDetailRowHighlighters()
	{
		return this.detailRowHighlighters;
	}
	
	/**
	 * <p>Setter for the field <code>detailRowHighlighters</code>.</p>
	 *
	 * @param detailRowHighlighters a {@link java.util.List} object.
	 */
	public void setDetailRowHighlighters(final List<DRConditionalStyle> detailRowHighlighters)
	{
		Validate.notNull(detailRowHighlighters, "detailRowHighlighters must not be null");
		Validate.noNullElements(
			detailRowHighlighters,
			"detailRowHighlighters must not contains null detailRowHighlighter");
		this.detailRowHighlighters = detailRowHighlighters;
	}
	
	/**
	 * <p>addDetailRowHighlighter.</p>
	 *
	 * @param detailRowHighlighter a {@link net.sf.dynamicreports.report.base.style.DRConditionalStyle} object.
	 */
	public void addDetailRowHighlighter(final DRConditionalStyle detailRowHighlighter)
	{
		Validate.notNull(detailRowHighlighter, "detailRowHighlighter must not be null");
		this.detailRowHighlighters.add(detailRowHighlighter);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRColumnGrid getColumnGrid()
	{
		return this.columnGrid;
	}
	
	/**
	 * <p>Setter for the field <code>columnGrid</code>.</p>
	 *
	 * @param columnGrid a {@link net.sf.dynamicreports.report.base.grid.DRColumnGrid} object.
	 */
	public void setColumnGrid(final DRColumnGrid columnGrid)
	{
		this.columnGrid = columnGrid;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getTableOfContents()
	{
		return this.tableOfContents;
	}
	
	/**
	 * <p>Setter for the field <code>tableOfContents</code>.</p>
	 *
	 * @param tableOfContents a {@link java.lang.Boolean} object.
	 */
	public void setTableOfContents(final Boolean tableOfContents)
	{
		this.tableOfContents = tableOfContents;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
	
	/**
	 * <p>Setter for the field <code>tableOfContentsCustomizer</code>.</p>
	 *
	 * @param tableOfContentsCustomizer a {@link net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer}
	 *                                  object.
	 */
	public void setTableOfContentsCustomizer(final DRITableOfContentsCustomizer tableOfContentsCustomizer)
	{
		this.tableOfContentsCustomizer = tableOfContentsCustomizer;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRIExpression<Boolean> getFilterExpression()
	{
		return this.filterExpression;
	}
	
	/**
	 * <p>Setter for the field <code>filterExpression</code>.</p>
	 *
	 * @param filterExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
	 */
	public void setFilterExpression(final DRIExpression<Boolean> filterExpression)
	{
		this.filterExpression = filterExpression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getTitleBand()
	{
		return this.titleBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getPageHeaderBand()
	{
		return this.pageHeaderBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getPageFooterBand()
	{
		return this.pageFooterBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getColumnHeaderBand()
	{
		return this.columnHeaderBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getColumnFooterBand()
	{
		return this.columnFooterBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getDetailBand()
	{
		return this.detailBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getDetailHeaderBand()
	{
		return this.detailHeaderBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getDetailFooterBand()
	{
		return this.detailFooterBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getLastPageFooterBand()
	{
		return this.lastPageFooterBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getSummaryBand()
	{
		return this.summaryBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getNoDataBand()
	{
		return this.noDataBand;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DRBand getBackgroundBand()
	{
		return this.backgroundBand;
	}
}
