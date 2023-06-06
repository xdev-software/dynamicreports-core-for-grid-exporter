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
package net.sf.dynamicreports.design.base;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.DRIDesignField;
import net.sf.dynamicreports.design.definition.DRIDesignParameter;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.design.definition.DRIDesignSort;
import net.sf.dynamicreports.design.definition.DRIDesignTemplateDesign;
import net.sf.dynamicreports.design.definition.DRIDesignVariable;
import net.sf.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import net.sf.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;
import net.sf.dynamicreports.design.transformation.AbstractExpressionTransform;
import net.sf.dynamicreports.design.transformation.BandTransform;
import net.sf.dynamicreports.design.transformation.ColumnGridTransform;
import net.sf.dynamicreports.design.transformation.ColumnTransform;
import net.sf.dynamicreports.design.transformation.ComponentTransform;
import net.sf.dynamicreports.design.transformation.CrosstabTransform;
import net.sf.dynamicreports.design.transformation.DatasetTransform;
import net.sf.dynamicreports.design.transformation.DesignTransformAccessor;
import net.sf.dynamicreports.design.transformation.GroupTransform;
import net.sf.dynamicreports.design.transformation.MainDatasetExpressionTransform;
import net.sf.dynamicreports.design.transformation.PageTransform;
import net.sf.dynamicreports.design.transformation.ReportTransform;
import net.sf.dynamicreports.design.transformation.StyleTransform;
import net.sf.dynamicreports.design.transformation.SubtotalTransform;
import net.sf.dynamicreports.design.transformation.TableOfContentsTransform;
import net.sf.dynamicreports.design.transformation.TemplateTransform;
import net.sf.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.constant.WhenResourceMissingType;
import net.sf.dynamicreports.report.definition.DRIDataset;
import net.sf.dynamicreports.report.definition.DRIReport;
import net.sf.dynamicreports.report.definition.DRIScriptlet;
import net.sf.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import net.sf.dynamicreports.report.exception.DRException;

/**
 * <p>DRDesignReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignReport implements DesignTransformAccessor, DRIDesignReport {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

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

    /**
     * <p>Constructor for DRDesignReport.</p>
     *
     * @param report a {@link net.sf.dynamicreports.report.definition.DRIReport} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignReport(final DRIReport report) throws DRException {
        this(report, null, null);
    }

    /**
     * <p>Constructor for DRDesignReport.</p>
     *
     * @param report      a {@link net.sf.dynamicreports.report.definition.DRIReport} object.
     * @param pageWidth   a {@link java.lang.Integer} object.
     * @param tocHeadings a {@link java.util.Map} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignReport(final DRIReport report, final Integer pageWidth, final Map<String, JasperTocHeading> tocHeadings) throws DRException {
        this.report = report;
        this.pageWidth = pageWidth;
        this.tocHeadings = tocHeadings;
        this.init();
        this.transform();
    }

    private void init() {
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

    private void transform() throws DRException {
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

    /** {@inheritDoc} */
    @Override
    public DRIReport getReport() {
        return this.report;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageWidth() {
        return this.pageWidth;
    }

    /** {@inheritDoc} */
    @Override
    public ReportTransform getReportTransform() {
        return this.reportTransform;
    }

    /** {@inheritDoc} */
    @Override
    public TemplateTransform getTemplateTransform() {
        return this.templateTransform;
    }

    /** {@inheritDoc} */
    @Override
    public PageTransform getPageTransform() {
        return this.pageTransform;
    }

    /** {@inheritDoc} */
    @Override
    public void transformToMainDataset() {
        this.transformToDataset(null);
    }

    /** {@inheritDoc} */
    @Override
    public void transformToDataset(final DRIDataset dataset) {
        if (dataset != null) {
            this.expressionTransform = this.datasetTransform.getDatasetExpressionTransform(dataset);
        } else {
            this.expressionTransform = this.mainDatasetExpressionTransform;
        }
    }

    /** {@inheritDoc} */
    @Override
    public AbstractExpressionTransform getExpressionTransform() {
        return this.expressionTransform;
    }

    /** {@inheritDoc} */
    @Override
    public BandTransform getBandTransform() {
        return this.bandTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentTransform getComponentTransform() {
        return this.componentTransform;
    }

    /** {@inheritDoc} */
    @Override
    public GroupTransform getGroupTransform() {
        return this.groupTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ColumnTransform getColumnTransform() {
        return this.columnTransform;
    }

    /** {@inheritDoc} */
    @Override
    public ColumnGridTransform getColumnGridTransform() {
        return this.columnGridTransform;
    }

    /** {@inheritDoc} */
    @Override
    public StyleTransform getStyleTransform() {
        return this.styleTransform;
    }

    /** {@inheritDoc} */
    @Override
    public CrosstabTransform getCrosstabTransform() {
        return this.crosstabTransform;
    }

    /** {@inheritDoc} */
    @Override
    public DatasetTransform getDatasetTransform() {
        return this.datasetTransform;
    }

    /** {@inheritDoc} */
    @Override
    public TableOfContentsTransform getTableOfContentsTransform() {
        return this.tableOfContentsTransform;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignTemplateDesign getTemplateDesign() {
        return this.reportTransform.getTemplateDesign();
    }

    /** {@inheritDoc} */
    @Override
    public String getReportName() {
        return this.templateTransform.getReportName();
    }

    /** {@inheritDoc} */
    @Override
    public Locale getLocale() {
        return this.templateTransform.getLocale();
    }

    /** {@inheritDoc} */
    @Override
    public ResourceBundle getResourceBundle() {
        return this.report.getResourceBundle();
    }

    /** {@inheritDoc} */
    @Override
    public String getResourceBundleName() {
        return this.templateTransform.getResourceBundleName();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIgnorePagination() {
        return this.templateTransform.isIgnorePagination();
    }

    /** {@inheritDoc} */
    @Override
    public Properties getProperties() {
        return this.report.getProperties();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignQuery getQuery() {
        return this.reportTransform.getQuery();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignPage getPage() {
        return this.pageTransform.getPage();
    }

    /** {@inheritDoc} */
    @Override
    public WhenNoDataType getWhenNoDataType() {
        return this.templateTransform.getWhenNoDataType(this.getDetailBands().isEmpty(), this.getNoDataBand());
    }

    /** {@inheritDoc} */
    @Override
    public WhenResourceMissingType getWhenResourceMissingType() {
        return this.templateTransform.getWhenResourceMissingType();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTitleOnANewPage() {
        return this.templateTransform.isTitleOnANewPage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSummaryOnANewPage() {
        return this.templateTransform.isSummaryOnANewPage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSummaryWithPageHeaderAndFooter() {
        return this.templateTransform.isSummaryWithPageHeaderAndFooter();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFloatColumnFooter() {
        return this.templateTransform.isFloatColumnFooter();
    }

    /** {@inheritDoc} */
    @Override
    public Orientation getPrintOrder() {
        return this.templateTransform.getPrintOrder();
    }

    /** {@inheritDoc} */
    @Override
    public RunDirection getColumnDirection() {
        return this.templateTransform.getColumnDirection();
    }

    /** {@inheritDoc} */
    @Override
    public String getLanguage() {
        return this.templateTransform.getLanguage();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isTableOfContents() {
        return this.templateTransform.isTableOfContents(this.tocHeadings);
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, JasperTocHeading> getTableOfContentsHeadings() {
        return this.tocHeadings;
    }

    /** {@inheritDoc} */
    @Override
    public DRITableOfContentsCustomizer getTableOfContentsCustomizer() {
        return this.templateTransform.getTableOfContentsCustomizer();
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getFilterExpression() {
        return this.reportTransform.getFilterExpression();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignParameter> getParameters() {
        return this.reportTransform.getParameters();
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> getParameterValues() {
        return this.report.getParameterValues();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIScriptlet> getScriptlets() {
        return this.report.getScriptlets();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignField> getFields() {
        return this.mainDatasetExpressionTransform.getFields();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSystemExpression> getSystemExpressions() {
        return this.mainDatasetExpressionTransform.getSystemExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignJasperExpression> getJasperExpressions() {
        return this.mainDatasetExpressionTransform.getJasperExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
        return this.mainDatasetExpressionTransform.getSimpleExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignStyle> getStyles() {
        return this.styleTransform.getStyles();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRDesignGroup> getGroups() {
        return this.groupTransform.getGroups();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignVariable> getVariables() {
        return this.mainDatasetExpressionTransform.getVariables();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignComplexExpression> getComplexExpressions() {
        return this.mainDatasetExpressionTransform.getComplexExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSort> getSorts() {
        return this.mainDatasetExpressionTransform.getSorts();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignDataset> getDatasets() {
        return this.datasetTransform.getDatasets();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getTitleBand() {
        return this.bandTransform.getTitleBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getPageHeaderBand() {
        return this.bandTransform.getPageHeaderBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getPageFooterBand() {
        return this.bandTransform.getPageFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getColumnHeaderBand() {
        return this.bandTransform.getColumnHeaderBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getColumnFooterBand() {
        return this.bandTransform.getColumnFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRDesignBand> getDetailBands() {
        return this.bandTransform.getDetailBands();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getLastPageFooterBand() {
        return this.bandTransform.getLastPageFooterBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getSummaryBand() {
        return this.bandTransform.getSummaryBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getNoDataBand() {
        return this.bandTransform.getNoDataBand();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignBand getBackgroundBand() {
        return this.bandTransform.getBackgroundBand();
    }
}
