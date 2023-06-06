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
package software.xdev.dynamicreports.design.definition.crosstab;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;

import java.io.Serializable;

/**
 * <p>DRIDesignCrosstabMeasure interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstabMeasure extends Serializable {

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
    public Class<?> getValueClass();

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getValueExpression();

    /**
     * <p>getCalculation.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    public Calculation getCalculation();

    /**
     * <p>getPercentageType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.CrosstabPercentageType} object.
     */
    public CrosstabPercentageType getPercentageType();
}
