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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>PageXofYNumberExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class PageXofYNumberExpression extends AbstractComplexExpression<String> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int index;

    /**
     * <p>Constructor for PageXofYNumberExpression.</p>
     *
     * @param pageNumberFormatExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @param index                      a int.
     */
    public PageXofYNumberExpression(DRIExpression<String> pageNumberFormatExpression, int index) {
        addExpression(pageNumberFormatExpression);
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public String evaluate(List<?> values, ReportParameters reportParameters) {
        String pattern = (String) values.get(0);
        Validate.isTrue(StringUtils.contains(pattern, "{0}"), "Wrong format pattern \"" + pattern + "\", missing argument {0}");
        Validate.isTrue(StringUtils.contains(pattern, "{1}"), "Wrong format pattern \"" + pattern + "\", missing argument {1}");
        Validate.isTrue(pattern.indexOf("{0}") < pattern.indexOf("{1}"), "Wrong format pattern \"" + pattern + "\", argument {0} must be before {1}");
        int index1 = pattern.indexOf("{0}");
        if (index == 0) {
            pattern = pattern.substring(0, index1 + 3);
        } else {
            pattern = pattern.substring(index1 + 3);
        }
        MessageFormat format = new MessageFormat(pattern, reportParameters.getLocale());
        String result = format.format(new Object[] {reportParameters.getPageNumber(), reportParameters.getPageNumber()});
        return result;
    }
}
