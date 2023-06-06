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
package software.xdev.dynamicreports.design.transformation.expressions;

import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

import java.util.List;

/**
 * <p>TocReferenceLinkExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TocReferenceLinkExpression extends AbstractComplexExpression<String> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String expressionName;
    private boolean customId;

    /**
     * <p>Constructor for TocReferenceLinkExpression.</p>
     *
     * @param expressionName       a {@link java.lang.String} object.
     * @param anchorNameExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public TocReferenceLinkExpression(String expressionName, DRIExpression<String> anchorNameExpression) {
        this.expressionName = expressionName;
        customId = anchorNameExpression != null;
        if (anchorNameExpression != null) {
            addExpression(anchorNameExpression);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String id;
        if (customId) {
            id = (String) values.get(0);
        } else {
            id = expressionName + "_" + reportParameters.getReportRowNumber();
        }
        return id;
    }
}
