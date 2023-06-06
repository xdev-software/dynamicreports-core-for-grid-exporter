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

import software.xdev.dynamicreports.design.base.DRDesignHyperLink;
import software.xdev.dynamicreports.design.base.DRDesignParameter;
import software.xdev.dynamicreports.design.base.DRDesignQuery;
import software.xdev.dynamicreports.design.base.DRDesignTemplateDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignParameter;
import software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.jasper.base.JasperScriptlet;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.DRIParameter;
import software.xdev.dynamicreports.report.definition.DRIQuery;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.exception.DRException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ReportTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ReportTransform {
    private DesignTransformAccessor accessor;
    private DRIDesignTemplateDesign templateDesign;
    private DRDesignQuery query;
    private List<DRIDesignParameter> parameters;
    private DRIDesignExpression filterExpression;

    /**
     * <p>Constructor for ReportTransform.</p>
     *
     * @param accessor a {@link software.xdev.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public ReportTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
        parameters = new ArrayList<DRIDesignParameter>();
    }

    /**
     * <p>transform.</p>
     *
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public void transform() throws DRException {
        DRIReport report = accessor.getReport();
        templateDesign = new DRDesignTemplateDesign(report.getTemplateDesign());
        if (report.getQuery() != null) {
            query = query(report.getQuery());
        }
        for (DRIParameter<?> parameter : report.getParameters()) {
            parameters.add(parameter(parameter));
        }
        filterExpression = accessor.getExpressionTransform().transformExpression(report.getFilterExpression(), JasperScriptlet.SCRIPTLET_NAME);
    }

    /**
     * <p>query.</p>
     *
     * @param query a {@link software.xdev.dynamicreports.report.definition.DRIQuery} object.
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignQuery} object.
     */
    protected DRDesignQuery query(DRIQuery query) {
        DRDesignQuery designQuery = new DRDesignQuery();
        designQuery.setText(query.getText());
        designQuery.setLanguage(query.getLanguage());
        return designQuery;
    }

    private DRDesignParameter parameter(DRIParameter<?> parameter) {
        DRDesignParameter designParameter = new DRDesignParameter();
        designParameter.setName(parameter.getName());
        designParameter.setValueClass(parameter.getValueClass());
        designParameter.setValue(parameter.getValue());
        designParameter.setExternal(accessor.getReport().getTemplateDesign().isDefinedParameter(parameter.getName()));
        return designParameter;
    }

    /**
     * <p>hyperlink.</p>
     *
     * @param hyperLink a {@link software.xdev.dynamicreports.report.definition.DRIHyperLink} object.
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignHyperLink} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignHyperLink hyperlink(DRIHyperLink hyperLink) throws DRException {
        if (hyperLink == null) {
            return null;
        }

        DRDesignHyperLink designHyperLink = new DRDesignHyperLink();
        designHyperLink.setAnchorExpression(accessor.getExpressionTransform().transformExpression(hyperLink.getAnchorExpression()));
        designHyperLink.setPageExpression(accessor.getExpressionTransform().transformExpression(hyperLink.getPageExpression()));
        designHyperLink.setReferenceExpression(accessor.getExpressionTransform().transformExpression(hyperLink.getReferenceExpression()));
        designHyperLink.setTooltipExpression(accessor.getExpressionTransform().transformExpression(hyperLink.getTooltipExpression()));
        designHyperLink.setType(hyperLink.getType());
        designHyperLink.setTarget(hyperLink.getTarget());

        return designHyperLink;
    }

    /**
     * <p>Getter for the field <code>templateDesign</code>.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign} object.
     */
    public DRIDesignTemplateDesign getTemplateDesign() {
        return templateDesign;
    }

    /**
     * <p>Getter for the field <code>query</code>.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignQuery} object.
     */
    public DRDesignQuery getQuery() {
        return query;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignParameter> getParameters() {
        return parameters;
    }

    /**
     * <p>Getter for the field <code>filterExpression</code>.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getFilterExpression() {
        return filterExpression;
    }
}
