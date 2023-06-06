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
package software.xdev.dynamicreports.report.builder;

import software.xdev.dynamicreports.report.base.DRParameter;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>ParameterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class ParameterBuilder<T> extends AbstractBuilder<ParameterBuilder<T>, DRParameter<T>> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for ParameterBuilder.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param value a T object.
     */
    protected ParameterBuilder(String name, T value) {
        super(new DRParameter<T>(name, value));
    }

    /**
     * <p>Constructor for ParameterBuilder.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    protected ParameterBuilder(String name, Class<T> valueClass) {
        super(new DRParameter<T>(name, valueClass));
    }

    /**
     * <p>getParameter.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.DRParameter} object.
     */
    public DRParameter<T> getParameter() {
        return build();
    }
}
