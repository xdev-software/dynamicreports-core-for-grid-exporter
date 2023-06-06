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
package software.xdev.dynamicreports.jasper.transformation.expression;

import software.xdev.dynamicreports.design.base.expression.AbstractDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.jasper.base.JasperReportParameters;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>SubreportParametersExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class SubreportParametersExpression extends AbstractDesignComplexExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private SubreportExpression subreportExpression;

    /**
     * <p>Constructor for SubreportParametersExpression.</p>
     *
     * @param subreportExpression  a {@link software.xdev.dynamicreports.jasper.transformation.expression.SubreportExpression} object.
     * @param parametersExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public SubreportParametersExpression(SubreportExpression subreportExpression, DRIDesignExpression parametersExpression) {
        super(ReportUtils.generateUniqueName("subreportParametersExpression"));
        this.subreportExpression = subreportExpression;
        if (parametersExpression != null) {
            addExpression(parametersExpression);
        }
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public Object evaluate(List<?> values, ReportParameters reportParameters) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.putAll(subreportExpression.getReportDesign().getParameters());
        if (subreportExpression.getReportBuilder().getReport().getParameterValues() != null) {
            parameters.putAll(subreportExpression.getReportBuilder().getReport().getParameterValues());
        }
        if (!values.isEmpty()) {
            parameters.putAll((Map<String, Object>) values.get(0));
        }
        parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
        return parameters;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return Map.class;
    }
}
