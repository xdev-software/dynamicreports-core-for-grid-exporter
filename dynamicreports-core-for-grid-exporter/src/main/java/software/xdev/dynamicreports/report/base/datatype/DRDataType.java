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
package software.xdev.dynamicreports.report.base.datatype;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;

/**
 * <p>DRDataType class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDataType<U, T extends U> extends AbstractDataType<U, T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String pattern;
    private HorizontalTextAlignment horizontalTextAlignment;
    private DRIValueFormatter<?, ? extends U> valueFormatter;

    /**
     * <p>Constructor for DRDataType.</p>
     */
    public DRDataType() {
    }

    /**
     * <p>Constructor for DRDataType.</p>
     *
     * @param pattern a {@link java.lang.String} object.
     */
    public DRDataType(String pattern) {
        this.pattern = pattern;
    }

    /**
     * <p>Constructor for DRDataType.</p>
     *
     * @param pattern                 a {@link java.lang.String} object.
     * @param horizontalTextAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    public DRDataType(String pattern, HorizontalTextAlignment horizontalTextAlignment) {
        this.pattern = pattern;
        this.horizontalTextAlignment = horizontalTextAlignment;
    }

    /** {@inheritDoc} */
    @Override
    public String getPattern() {
        return pattern;
    }

    /**
     * <p>Setter for the field <code>pattern</code>.</p>
     *
     * @param pattern a {@link java.lang.String} object.
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalTextAlignment getHorizontalTextAlignment() {
        return horizontalTextAlignment;
    }

    /**
     * <p>Setter for the field <code>horizontalTextAlignment</code>.</p>
     *
     * @param horizontalTextAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    public void setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        this.horizontalTextAlignment = horizontalTextAlignment;
    }

    /** {@inheritDoc} */
    @Override
    public DRIValueFormatter<?, ? extends U> getValueFormatter() {
        return valueFormatter;
    }

    /**
     * <p>Setter for the field <code>valueFormatter</code>.</p>
     *
     * @param valueFormatter a {@link software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     */
    public void setValueFormatter(DRIValueFormatter<?, ? extends U> valueFormatter) {
        this.valueFormatter = valueFormatter;
    }

    /**
     * <p>toString.</p>
     *
     * @param value a T object.
     * @return a {@link java.lang.String} object.
     */
    public String toString(T value) {
        return String.valueOf(value);
    }
}
