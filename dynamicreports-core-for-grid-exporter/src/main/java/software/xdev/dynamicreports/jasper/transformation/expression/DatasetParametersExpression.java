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

import software.xdev.dynamicreports.design.base.expression.AbstractDesignSimpleExpression;
import software.xdev.dynamicreports.jasper.base.JasperReportParameters;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>DatasetParametersExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DatasetParametersExpression extends AbstractDesignSimpleExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Map<String, Object> parameters;

    /**
     * <p>Constructor for DatasetParametersExpression.</p>
     *
     * @param parameters a {@link java.util.Map} object.
     */
    public DatasetParametersExpression(Map<String, Object> parameters) {
        super(ReportUtils.generateUniqueName("datasetParametersExpression"));
        this.parameters = parameters;
    }

    /** {@inheritDoc} */
    @Override
    public Object evaluate(ReportParameters reportParameters) {
        Map<String, Object> parameters = new HashMap<String, Object>(this.parameters);
        parameters.put(JasperReportParameters.MASTER_REPORT_PARAMETERS, reportParameters);
        return parameters;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return Map.class;
    }
}
