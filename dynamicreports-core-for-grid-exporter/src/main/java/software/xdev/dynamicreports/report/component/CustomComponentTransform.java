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
package software.xdev.dynamicreports.report.component;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.component.DRIDesignComponent;
import software.xdev.dynamicreports.design.transformation.DesignTransformAccessor;
import software.xdev.dynamicreports.jasper.transformation.JasperTransformAccessor;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRComponentElement;

/**
 * <p>CustomComponentTransform interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface CustomComponentTransform<T extends DRIComponent, U extends DRIDesignComponent> {

    /**
     * <p>isTransform.</p>
     *
     * @param component a {@link java.lang.Object} object.
     * @return a boolean.
     */
    public boolean isTransform(Object component);

    /**
     * <p>designComponent.</p>
     *
     * @param accessor   a {@link software.xdev.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @param component  a T object.
     * @param resetType  a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     * @param resetGroup a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     * @return a U object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public U designComponent(DesignTransformAccessor accessor, T component, ResetType resetType, DRDesignGroup resetGroup) throws DRException;

    /**
     * <p>jasperComponent.</p>
     *
     * @param accessor  a {@link software.xdev.dynamicreports.jasper.transformation.JasperTransformAccessor} object.
     * @param component a U object.
     * @return a {@link net.sf.jasperreports.engine.JRComponentElement} object.
     */
    public JRComponentElement jasperComponent(JasperTransformAccessor accessor, U component);

}
