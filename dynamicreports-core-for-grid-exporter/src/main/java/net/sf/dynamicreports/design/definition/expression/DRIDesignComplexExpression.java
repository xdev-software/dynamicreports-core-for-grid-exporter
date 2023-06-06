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
package net.sf.dynamicreports.design.definition.expression;

import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.List;

/**
 * <p>DRIDesignComplexExpression interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignComplexExpression extends DRIDesignExpression {

    /**
     * <p>getExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignExpression> getExpressions();

    /**
     * <p>evaluate.</p>
     *
     * @param values           a {@link java.util.List} object.
     * @param reportParameters a {@link net.sf.dynamicreports.report.definition.ReportParameters} object.
     * @return a {@link java.lang.Object} object.
     */
    public Object evaluate(List<?> values, ReportParameters reportParameters);

    /**
     * <p>getParameterName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getParameterName();
}
