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
package software.xdev.dynamicreports.design.transformation;

import java.util.Locale;
import java.util.ResourceBundle;

import software.xdev.dynamicreports.design.base.DRDesignPage;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.definition.DRIReport;

/**
 * <p>DesignTransformAccessor interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DesignTransformAccessor {

    /**
     * <p>getReport.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIReport} object.
     */
    public DRIReport getReport();

    /**
     * <p>getPageWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPageWidth();

    /**
     * <p>getReportTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.ReportTransform} object.
     */
    public ReportTransform getReportTransform();

    /**
     * <p>getTemplateTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.TemplateTransform} object.
     */
    public TemplateTransform getTemplateTransform();

    /**
     * <p>transformToMainDataset.</p>
     */
    public void transformToMainDataset();

    /**
     * <p>transformToDataset.</p>
     *
     * @param dataset a {@link software.xdev.dynamicreports.report.definition.DRIDataset} object.
     */
    public void transformToDataset(DRIDataset dataset);

    /**
     * <p>getExpressionTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.AbstractExpressionTransform} object.
     */
    public AbstractExpressionTransform getExpressionTransform();

    /**
     * <p>getPageTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.PageTransform} object.
     */
    public PageTransform getPageTransform();

    /**
     * <p>getBandTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.BandTransform} object.
     */
    public BandTransform getBandTransform();

    /**
     * <p>getComponentTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.ComponentTransform} object.
     */
    public ComponentTransform getComponentTransform();

    /**
     * <p>getGroupTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.GroupTransform} object.
     */
    public GroupTransform getGroupTransform();

    /**
     * <p>getColumnTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.ColumnTransform} object.
     */
    public ColumnTransform getColumnTransform();

    /**
     * <p>getColumnGridTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.ColumnGridTransform} object.
     */
    public ColumnGridTransform getColumnGridTransform();

    /**
     * <p>getStyleTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.StyleTransform} object.
     */
    public StyleTransform getStyleTransform();

    /**
     * <p>getCrosstabTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.CrosstabTransform} object.
     */
    public CrosstabTransform getCrosstabTransform();

    /**
     * <p>getDatasetTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.DatasetTransform} object.
     */
    public DatasetTransform getDatasetTransform();

    /**
     * <p>getTableOfContentsTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.TableOfContentsTransform} object.
     */
    public TableOfContentsTransform getTableOfContentsTransform();

    /**
     * <p>isTableOfContents.</p>
     *
     * @return a boolean.
     */
    public boolean isTableOfContents();

    /**
     * <p>getPage.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignPage} object.
     */
    public DRDesignPage getPage();

    /**
     * <p>getLocale.</p>
     *
     * @return a {@link java.util.Locale} object.
     */
    public Locale getLocale();

    /**
     * <p>getResourceBundle.</p>
     *
     * @return a {@link java.util.ResourceBundle} object.
     */
    public ResourceBundle getResourceBundle();

    /**
     * <p>getResourceBundleName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getResourceBundleName();

    /**
     * <p>getWhenResourceMissingType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    public WhenResourceMissingType getWhenResourceMissingType();
}
