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
package net.sf.dynamicreports.report.builder.datatype;

import net.sf.dynamicreports.report.base.datatype.AbstractDataType;
import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import java.util.List;

/**
 * <p>ListType class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
@SuppressWarnings("rawtypes")
public class ListType extends AbstractDataType<List, List> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private static ListFormatter listFormatter = new ListFormatter();

    /** {@inheritDoc} */
    @Override
    public DRIValueFormatter<?, ? extends List> getValueFormatter() {
        return listFormatter;
    }

    /** {@inheritDoc} */
    @Override
    public String getPattern() {
        return Defaults.getDefaults().getStringType().getPattern();
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalTextAlignment getHorizontalTextAlignment() {
        return Defaults.getDefaults().getStringType().getHorizontalTextAlignment();
    }

    private static class ListFormatter extends AbstractValueFormatter<String, List> {
        private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

        @Override
        public String format(List values, ReportParameters reportParameters) {
            StringBuilder result = new StringBuilder();
            for (Object value : values) {
                if (result.length() > 0) {
                    result.append("\n");
                }
                if (value != null) {
                    result.append(value.toString());
                }
            }
            return result.toString();
        }
    }
}
