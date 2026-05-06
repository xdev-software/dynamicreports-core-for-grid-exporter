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
package software.xdev.dynamicreports.design.base;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignParameter;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.design.transformation.AbstractExpressionTransform;
import software.xdev.dynamicreports.design.transformation.BandTransform;
import software.xdev.dynamicreports.design.transformation.ColumnGridTransform;
import software.xdev.dynamicreports.design.transformation.ColumnTransform;
import software.xdev.dynamicreports.design.transformation.ComponentTransform;
import software.xdev.dynamicreports.design.transformation.CrosstabTransform;
import software.xdev.dynamicreports.design.transformation.DatasetTransform;
import software.xdev.dynamicreports.design.transformation.DesignTransformAccessor;
import software.xdev.dynamicreports.design.transformation.GroupTransform;
import software.xdev.dynamicreports.design.transformation.MainDatasetExpressionTransform;
import software.xdev.dynamicreports.design.transformation.PageTransform;
import software.xdev.dynamicreports.design.transformation.ReportTransform;
import software.xdev.dynamicreports.design.transformation.StyleTransform;
import software.xdev.dynamicreports.design.transformation.SubtotalTransform;
import software.xdev.dynamicreports.design.transformation.TableOfContentsTransform;
import software.xdev.dynamicreports.design.transformation.TemplateTransform;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.exception.DRException;


public class DRDesignReport implements DesignTransformAccessor, DRIDesignReport
{

	private final DRIReport report;
	private final Integer pageWidth;
	private final Map<String, JasperTocHeading> tocHeadings;
	private ReportTransform reportTransform;
	private TemplateTransform templateTransform;
	private PageTransform pageTransform;
	private MainDatasetExpressionTransform mainDatasetExpressionTransform;
	private BandTransform bandTransform;
	private ComponentTransform componentTransform;
	private GroupTransform groupTransform;
	private ColumnGridTransform columnGridTransform;
	private ColumnTransform columnTransform;
	private SubtotalTransform subtotalTransform;
	private StyleTransform styleTransform;
	private CrosstabTransform crosstabTransform;
	private DatasetTransform datasetTransform;
	private TableOfContentsTransform tableOfContentsTransform;
	private AbstractExpressionTransform expressionTransform;
	
	public DRDesignReport(final DRIReport report) throws DRException
	{
		this(report, null, null);
	}
	
	public DRDesignReport(
		final DRIReport report,
		final Integer pageWidth,
		final Map<String, JasperTocHeading> tocHeadings) throws DRException
	{
		this.report = report;
		this.pageWidth = pageWidth;
		this.tocHeadings = tocHeadings;
		this.init();
		this.transform();
	}
	
	private void init()
	{
		this.reportTransform = new ReportTransform(this);
		this.templateTransform = new TemplateTransform(this);
		this.pageTransform = new PageTransform(this);
		this.mainDatasetExpressionTransform = new MainDatasetExpressionTransform(this);
		this.groupTransform = new GroupTransform(this);
		this.bandTransform = new BandTransform(this);
		this.componentTransform = new ComponentTransform(this);
		this.columnGridTransform = new ColumnGridTransform(this);
		this.columnTransform = new ColumnTransform(this);
		this.subtotalTransform = new SubtotalTransform(this);
		this.styleTransform = new StyleTransform(this);
		this.crosstabTransform = new CrosstabTransform(this);
		this.datasetTransform = new DatasetTransform(this);
		this.tableOfContentsTransform = new TableOfContentsTransform(this);
		this.transformToMainDataset();
	}
	
	private void transform() throws DRException
	{
		this.reportTransform.transform();
		this.pageTransform.transform();
		this.groupTransform.transform();
		this.mainDatasetExpressionTransform.transform();
		this.bandTransform.transform();
		this.columnGridTransform.transform();
		this.columnTransform.transform();
		this.groupTransform.transformHeaderAndFooter();
		this.pageTransform.transformPageWidth();
		this.subtotalTransform.transform();
		this.bandTransform.prepareBands();
		this.styleTransform.transformTemplateStyles();
	}
	
	@Override
	public DRIReport getReport()
	{
		return this.report;
	}
	
	@Override
	public Integer getPageWidth()
	{
		return this.pageWidth;
	}
	
	@Override
	public ReportTransform getReportTransform()
	{
		return this.reportTransform;
	}
	
	@Override
	public TemplateTransform getTemplateTransform()
	{
		return this.templateTransform;
	}
	
	@Override
	public PageTransform getPageTransform()
	{
		return this.pageTransform;
	}
	
	@Override
	public void transformToMainDataset()
	{
		this.transformToDataset(null);
	}
	
	@Override
	public void transformToDataset(final DRIDataset dataset)
	{
		if(dataset != null)
		{
			this.expressionTransform = this.datasetTransform.getDatasetExpressionTransform(dataset);
		}
		else
		{
			this.expressionTransform = this.mainDatasetExpressionTransform;
		}
	}
	
	@Override
	public AbstractExpressionTransform getExpressionTransform()
	{
		return this.expressionTransform;
	}
	
	@Override
	public BandTransform getBandTransform()
	{
		return this.bandTransform;
	}
	
	@Override
	public ComponentTransform getComponentTransform()
	{
		return this.componentTransform;
	}
	
	@Override
	public GroupTransform getGroupTransform()
	{
		return this.groupTransform;
	}
	
	@Override
	public ColumnTransform getColumnTransform()
	{
		return this.columnTransform;
	}
	
	@Override
	public ColumnGridTransform getColumnGridTransform()
	{
		return this.columnGridTransform;
	}
	
	@Override
	public StyleTransform getStyleTransform()
	{
		return this.styleTransform;
	}
	
	@Override
	public CrosstabTransform getCrosstabTransform()
	{
		return this.crosstabTransform;
	}
	
	@Override
	public DatasetTransform getDatasetTransform()
	{
		return this.datasetTransform;
	}
	
	@Override
	public TableOfContentsTransform getTableOfContentsTransform()
	{
		return this.tableOfContentsTransform;
	}
	
	@Override
	public DRIDesignTemplateDesign getTemplateDesign()
	{
		return this.reportTransform.getTemplateDesign();
	}
	
	@Override
	public String getReportName()
	{
		return this.templateTransform.getReportName();
	}
	
	@Override
	public Locale getLocale()
	{
		return this.templateTransform.getLocale();
	}
	
	@Override
	public ResourceBundle getResourceBundle()
	{
		return this.report.getResourceBundle();
	}
	
	@Override
	public String getResourceBundleName()
	{
		return this.templateTransform.getResourceBundleName();
	}
	
	@Override
	public boolean isIgnorePagination()
	{
		return this.templateTransform.isIgnorePagination();
	}
	
	@Override
	public Properties getProperties()
	{
		return this.report.getProperties();
	}
	
	@Override
	public DRDesignQuery getQuery()
	{
		return this.reportTransform.getQuery();
	}
	
	@Override
	public DRDesignPage getPage()
	{
		return this.pageTransform.getPage();
	}
	
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return this.templateTransform.getWhenNoDataType(this.getDetailBands().isEmpty(), this.getNoDataBand());
	}
	
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return this.templateTransform.getWhenResourceMissingType();
	}
	
	@Override
	public boolean isTitleOnANewPage()
	{
		return this.templateTransform.isTitleOnANewPage();
	}
	
	@Override
	public boolean isSummaryOnANewPage()
	{
		return this.templateTransform.isSummaryOnANewPage();
	}
	
	@Override
	public boolean isSummaryWithPageHeaderAndFooter()
	{
		return this.templateTransform.isSummaryWithPageHeaderAndFooter();
	}
	
	@Override
	public boolean isFloatColumnFooter()
	{
		return this.templateTransform.isFloatColumnFooter();
	}
	
	@Override
	public Orientation getPrintOrder()
	{
		return this.templateTransform.getPrintOrder();
	}
	
	@Override
	public RunDirection getColumnDirection()
	{
		return this.templateTransform.getColumnDirection();
	}
	
	@Override
	public String getLanguage()
	{
		return this.templateTransform.getLanguage();
	}
	
	@Override
	public boolean isTableOfContents()
	{
		return this.templateTransform.isTableOfContents(this.tocHeadings);
	}
	
	@Override
	public Map<String, JasperTocHeading> getTableOfContentsHeadings()
	{
		return this.tocHeadings;
	}
	
	@Override
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.templateTransform.getTableOfContentsCustomizer();
	}
	
	@Override
	public DRIDesignExpression getFilterExpression()
	{
		return this.reportTransform.getFilterExpression();
	}
	
	@Override
	public Collection<DRIDesignParameter> getParameters()
	{
		return this.reportTransform.getParameters();
	}
	
	@Override
	public Map<String, Object> getParameterValues()
	{
		return this.report.getParameterValues();
	}
	
	@Override
	public Collection<DRIScriptlet> getScriptlets()
	{
		return this.report.getScriptlets();
	}
	
	@Override
	public Collection<DRIDesignField> getFields()
	{
		return this.mainDatasetExpressionTransform.getFields();
	}
	
	@Override
	public Collection<DRIDesignSystemExpression> getSystemExpressions()
	{
		return this.mainDatasetExpressionTransform.getSystemExpressions();
	}
	
	@Override
	public Collection<DRIDesignJasperExpression> getJasperExpressions()
	{
		return this.mainDatasetExpressionTransform.getJasperExpressions();
	}
	
	@Override
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions()
	{
		return this.mainDatasetExpressionTransform.getSimpleExpressions();
	}
	
	@Override
	public Collection<DRIDesignStyle> getStyles()
	{
		return this.styleTransform.getStyles();
	}
	
	@Override
	public Collection<DRDesignGroup> getGroups()
	{
		return this.groupTransform.getGroups();
	}
	
	@Override
	public Collection<DRIDesignVariable> getVariables()
	{
		return this.mainDatasetExpressionTransform.getVariables();
	}
	
	@Override
	public Collection<DRIDesignComplexExpression> getComplexExpressions()
	{
		return this.mainDatasetExpressionTransform.getComplexExpressions();
	}
	
	@Override
	public Collection<DRIDesignSort> getSorts()
	{
		return this.mainDatasetExpressionTransform.getSorts();
	}
	
	@Override
	public Collection<DRIDesignDataset> getDatasets()
	{
		return this.datasetTransform.getDatasets();
	}
	
	@Override
	public DRDesignBand getTitleBand()
	{
		return this.bandTransform.getTitleBand();
	}
	
	@Override
	public DRDesignBand getPageHeaderBand()
	{
		return this.bandTransform.getPageHeaderBand();
	}
	
	@Override
	public DRDesignBand getPageFooterBand()
	{
		return this.bandTransform.getPageFooterBand();
	}
	
	@Override
	public DRDesignBand getColumnHeaderBand()
	{
		return this.bandTransform.getColumnHeaderBand();
	}
	
	@Override
	public DRDesignBand getColumnFooterBand()
	{
		return this.bandTransform.getColumnFooterBand();
	}
	
	@Override
	public List<DRDesignBand> getDetailBands()
	{
		return this.bandTransform.getDetailBands();
	}
	
	@Override
	public DRDesignBand getLastPageFooterBand()
	{
		return this.bandTransform.getLastPageFooterBand();
	}
	
	@Override
	public DRDesignBand getSummaryBand()
	{
		return this.bandTransform.getSummaryBand();
	}
	
	@Override
	public DRDesignBand getNoDataBand()
	{
		return this.bandTransform.getNoDataBand();
	}
	
	@Override
	public DRDesignBand getBackgroundBand()
	{
		return this.bandTransform.getBackgroundBand();
	}
}
