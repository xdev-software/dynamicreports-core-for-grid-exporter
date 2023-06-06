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
package software.xdev.dynamicreports.report.definition.expression;

import java.io.Serializable;

/**
 * Expressions are used to define various calculations, conditions, text field content, specific report groups, etc. Every expression can access the declared report fields, variables and other
 * expressions and get their values to calculate the expression value.
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIExpression<T> extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName();

    /**
     * <p>getValueClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    public Class<? super T> getValueClass();
}
