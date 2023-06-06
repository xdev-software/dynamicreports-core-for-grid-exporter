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
package net.sf.dynamicreports.design.definition.style;

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;

/**
 * <p>DRIDesignConditionalStyle interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignConditionalStyle extends DRIDesignBaseStyle {

    /**
     * <p>getConditionExpression.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getConditionExpression();

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.definition.DRIDesignDataset} object.
     */
    public DRIDesignDataset getDataset();
}
