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
package software.xdev.dynamicreports.design.definition;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.OrderType;

import java.io.Serializable;

/**
 * <p>DRIDesignSort interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignSort extends Serializable {

    /**
     * <p>getExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getExpression();

    /**
     * <p>getOrderType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.OrderType} object.
     */
    public OrderType getOrderType();
}