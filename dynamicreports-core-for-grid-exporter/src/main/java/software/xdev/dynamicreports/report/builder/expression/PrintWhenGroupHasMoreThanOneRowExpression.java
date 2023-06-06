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
package software.xdev.dynamicreports.report.builder.expression;

import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;

/**
 * <p>PrintWhenGroupHasMoreThanOneRowExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PrintWhenGroupHasMoreThanOneRowExpression extends AbstractSimpleExpression<Boolean> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String groupName;

    /**
     * <p>Constructor for PrintWhenGroupHasMoreThanOneRowExpression.</p>
     *
     * @param groupName a {@link java.lang.String} object.
     */
    public PrintWhenGroupHasMoreThanOneRowExpression(String groupName) {
        this.groupName = groupName;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean evaluate(ReportParameters reportParameters) {
        return reportParameters.getGroupCount(groupName) > 1;
    }
}
