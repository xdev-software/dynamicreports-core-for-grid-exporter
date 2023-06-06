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
package software.xdev.dynamicreports.report.definition.datatype;

import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.exception.DRException;

import java.io.Serializable;
import java.util.Locale;

/**
 * <p>DRIDataType interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDataType<U, T extends U> extends Serializable {

    /**
     * <p>getPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPattern();

    /**
     * <p>getValueFormatter.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter} object.
     */
    public DRIValueFormatter<?, ? extends U> getValueFormatter();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    public HorizontalTextAlignment getHorizontalTextAlignment();

    /**
     * <p>valueToString.</p>
     *
     * @param value  a U object.
     * @param locale a {@link java.util.Locale} object.
     * @return a {@link java.lang.String} object.
     */
    public String valueToString(U value, Locale locale);

    /**
     * <p>valueToString.</p>
     *
     * @param value            a {@link software.xdev.dynamicreports.report.definition.DRIValue} object.
     * @param reportParameters a {@link software.xdev.dynamicreports.report.definition.ReportParameters} object.
     * @return a {@link java.lang.String} object.
     */
    public String valueToString(DRIValue<? extends U> value, ReportParameters reportParameters);

    /**
     * <p>valueToString.</p>
     *
     * @param name             a {@link java.lang.String} object.
     * @param reportParameters a {@link software.xdev.dynamicreports.report.definition.ReportParameters} object.
     * @return a {@link java.lang.String} object.
     */
    public String valueToString(String name, ReportParameters reportParameters);

    /**
     * <p>stringToValue.</p>
     *
     * @param value  a {@link java.lang.String} object.
     * @param locale a {@link java.util.Locale} object.
     * @return a T object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public T stringToValue(String value, Locale locale) throws DRException;

    /**
     * <p>stringToValue.</p>
     *
     * @param value            a {@link software.xdev.dynamicreports.report.definition.DRIValue} object.
     * @param reportParameters a {@link software.xdev.dynamicreports.report.definition.ReportParameters} object.
     * @return a T object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public T stringToValue(DRIValue<String> value, ReportParameters reportParameters) throws DRException;

    /**
     * <p>stringToValue.</p>
     *
     * @param name             a {@link java.lang.String} object.
     * @param reportParameters a {@link software.xdev.dynamicreports.report.definition.ReportParameters} object.
     * @return a T object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public T stringToValue(String name, ReportParameters reportParameters) throws DRException;

    /**
     * <p>getValueClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<T> getValueClass();
}
