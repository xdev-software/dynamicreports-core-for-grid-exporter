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
package software.xdev.dynamicreports.report.base;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRIParameter;
import org.apache.commons.lang3.Validate;

/**
 * <p>DRParameter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRParameter<T> implements DRIParameter<T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private Class<T> valueClass;
    private T value;

    @SuppressWarnings("unchecked")
    /**
     * <p>Constructor for DRParameter.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param value a T object.
     */
    public DRParameter(String name, T value) {
        Validate.notEmpty(name, "name must not be empty");
        Validate.notNull(value, "value must not be null");
        this.name = name;
        this.valueClass = (Class<T>) value.getClass();
        this.value = value;
    }

    /**
     * <p>Constructor for DRParameter.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     */
    public DRParameter(String name, Class<T> valueClass) {
        Validate.notEmpty(name, "name must not be empty");
        Validate.notNull(valueClass, "valueClass must not be null");
        this.name = name;
        this.valueClass = valueClass;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public Class<T> getValueClass() {
        return valueClass;
    }

    /** {@inheritDoc} */
    @Override
    public T getValue() {
        return value;
    }
}
