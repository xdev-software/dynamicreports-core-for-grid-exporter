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
package net.sf.dynamicreports.design.definition.crosstab;

import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.definition.style.DRIDesignStyle;

import java.io.Serializable;

/**
 * <p>DRIDesignCrosstabCellContent interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstabCellContent extends Serializable {

    /**
     * <p>getWidth.</p>
     *
     * @return a int.
     */
    public int getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a int.
     */
    public int getHeight();

    /**
     * <p>getComponent.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.component.DRIDesignComponent} object.
     */
    public DRIDesignComponent getComponent();

    /**
     * <p>getStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.style.DRIDesignStyle} object.
     */
    public DRIDesignStyle getStyle();
}
