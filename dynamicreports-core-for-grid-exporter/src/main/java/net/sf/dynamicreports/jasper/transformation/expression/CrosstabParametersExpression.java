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
package net.sf.dynamicreports.jasper.transformation.expression;

import net.sf.dynamicreports.design.base.expression.AbstractDesignSimpleExpression;
import net.sf.dynamicreports.jasper.base.JasperReportParameters;
import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>CrosstabParametersExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabParametersExpression extends AbstractDesignSimpleExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Map<String, Object> parameters;

    /**
     * <p>Constructor for CrosstabParametersExpression.</p>
     *
     * @param parameters a {@link java.util.Map} object.
     */
    public CrosstabParametersExpression(Map<String, Object> parameters) {
        super(ReportUtils.generateUniqueName("crosstabParametersExpression"));
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
