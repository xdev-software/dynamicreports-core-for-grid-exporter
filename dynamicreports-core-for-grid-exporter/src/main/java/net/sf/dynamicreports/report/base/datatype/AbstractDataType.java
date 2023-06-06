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
package net.sf.dynamicreports.report.base.datatype;

import net.sf.dynamicreports.report.ReportUtils;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.DRIValue;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.exception.DRException;

import java.util.Locale;

/**
 * <p>Abstract AbstractDataType class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractDataType<U, T extends U> implements DRIDataType<U, T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /** {@inheritDoc} */
    @Override
    public String getPattern() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public DRIValueFormatter<?, ? extends U> getValueFormatter() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalTextAlignment getHorizontalTextAlignment() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String valueToString(U value, Locale locale) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String valueToString(DRIValue<? extends U> value, ReportParameters reportParameters) {
        return valueToString(reportParameters.getValue(value), reportParameters.getLocale());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public String valueToString(String name, ReportParameters reportParameters) {
        return valueToString((U) reportParameters.getValue(name), reportParameters.getLocale());
    }

    /** {@inheritDoc} */
    @Override
    public T stringToValue(String value, Locale locale) throws DRException {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public T stringToValue(DRIValue<String> value, ReportParameters reportParameters) throws DRException {
        return stringToValue(reportParameters.getValue(value), reportParameters.getLocale());
    }

    /** {@inheritDoc} */
    @Override
    public T stringToValue(String name, ReportParameters reportParameters) throws DRException {
        return stringToValue((String) reportParameters.getValue(name), reportParameters.getLocale());
    }

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getValueClass() {
        return (Class<T>) ReportUtils.getGenericClass(this, 1);
    }
}
