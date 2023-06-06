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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignField class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignField implements DRIDesignField {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private Class<?> valueClass;
    private boolean external;
    private String description;

    /**
     * <p>Constructor for DRDesignField.</p>
     */
    public DRDesignField() {
        this.external = false;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Setter for the field <code>name</code>.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return valueClass;
    }

    /**
     * <p>Setter for the field <code>valueClass</code>.</p>
     *
     * @param valueClass a {@link java.lang.Class} object.
     */
    public void setValueClass(Class<?> valueClass) {
        this.valueClass = valueClass;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExternal() {
        return external;
    }

    /**
     * <p>Setter for the field <code>external</code>.</p>
     *
     * @param external a boolean.
     */
    public void setExternal(boolean external) {
        this.external = external;
    }

    /** {@inheritDoc} */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * <p>Setter for the field <code>description</code>.</p>
     *
     * @param description a {@link java.lang.String} object.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
