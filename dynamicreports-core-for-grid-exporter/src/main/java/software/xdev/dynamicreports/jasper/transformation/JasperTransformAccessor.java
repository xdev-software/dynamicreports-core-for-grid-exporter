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
package software.xdev.dynamicreports.jasper.transformation;

import java.util.Map;

import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * <p>JasperTransformAccessor interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperTransformAccessor {

    /**
     * <p>getReport.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.DRIDesignReport} object.
     */
    public DRIDesignReport getReport();

    /**
     * <p>getDesign.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     */
    public JasperDesign getDesign();

    /**
     * <p>getCustomValues.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.base.JasperCustomValues} object.
     */
    public JasperCustomValues getCustomValues();

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, Object> getParameters();

    /**
     * <p>getParameterValues.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, Object> getParameterValues();

    /**
     * <p>getStartPageNumber.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getStartPageNumber();

    /**
     * <p>getMasterReportParameters.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.ReportParameters} object.
     */
    public ReportParameters getMasterReportParameters();

    /**
     * <p>getReportTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.ReportTransform} object.
     */
    public ReportTransform getReportTransform();

    /**
     * <p>transformToMainDataset.</p>
     */
    public void transformToMainDataset();

    /**
     * <p>transformToDataset.</p>
     *
     * @param dataset a {@link software.xdev.dynamicreports.design.definition.DRIDesignDataset} object.
     */
    public void transformToDataset(DRIDesignDataset dataset);

    /**
     * <p>getExpressionTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.AbstractExpressionTransform} object.
     */
    public AbstractExpressionTransform getExpressionTransform();

    /**
     * <p>getExpressionTransform.</p>
     *
     * @param dataset a {@link software.xdev.dynamicreports.design.definition.DRIDesignDataset} object.
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.AbstractExpressionTransform} object.
     */
    public AbstractExpressionTransform getExpressionTransform(DRIDesignDataset dataset);

    /**
     * <p>getGroupTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.GroupTransform} object.
     */
    public GroupTransform getGroupTransform();

    /**
     * <p>getComponentTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.ComponentTransform} object.
     */
    public ComponentTransform getComponentTransform();

    /**
     * <p>getStyleTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.StyleTransform} object.
     */
    public StyleTransform getStyleTransform();

    /**
     * <p>getCrosstabTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.CrosstabTransform} object.
     */
    public CrosstabTransform getCrosstabTransform();

    /**
     * <p>getDatasetTransform.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.transformation.DatasetTransform} object.
     */
    public DatasetTransform getDatasetTransform();
}
