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
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.defaults.Defaults;
import net.sf.dynamicreports.report.exception.DRException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <p>DateType class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DateType extends AbstractDataType<Date, Date> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /** {@inheritDoc} */
    @Override
    public String getPattern() {
        return Defaults.getDefaults().getDateType().getPattern();
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalTextAlignment getHorizontalTextAlignment() {
        return Defaults.getDefaults().getDateType().getHorizontalTextAlignment();
    }

    /** {@inheritDoc} */
    @Override
    public String valueToString(Date value, Locale locale) {
        if (value != null) {
            return new SimpleDateFormat(getPattern(), locale).format(value);
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Date stringToValue(String value, Locale locale) throws DRException {
        if (value != null) {
            try {
                return new SimpleDateFormat(getPattern(), locale).parse(value);
            } catch (ParseException e) {
                throw new DRException("Unable to convert string value to date", e);
            }
        }
        return null;
    }
}
