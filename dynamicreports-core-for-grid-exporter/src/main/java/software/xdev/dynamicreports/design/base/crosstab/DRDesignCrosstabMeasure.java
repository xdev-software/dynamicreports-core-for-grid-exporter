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
package software.xdev.dynamicreports.design.base.crosstab;

import software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabMeasure;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;

/**
 * <p>DRDesignCrosstabMeasure class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignCrosstabMeasure implements DRIDesignCrosstabMeasure {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DRIDesignExpression valueExpression;
    private Calculation calculation;
    private CrosstabPercentageType percentageType;

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
    public DRIDesignExpression getValueExpression() {
        return valueExpression;
    }

    /**
     * <p>Setter for the field <code>valueExpression</code>.</p>
     *
     * @param valueExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setValueExpression(DRIDesignExpression valueExpression) {
        this.valueExpression = valueExpression;
    }

    /** {@inheritDoc} */
    @Override
    public Calculation getCalculation() {
        return calculation;
    }

    /**
     * <p>Setter for the field <code>calculation</code>.</p>
     *
     * @param calculation a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    public void setCalculation(Calculation calculation) {
        this.calculation = calculation;
    }

    /** {@inheritDoc} */
    @Override
    public CrosstabPercentageType getPercentageType() {
        return percentageType;
    }

    /**
     * <p>Setter for the field <code>percentageType</code>.</p>
     *
     * @param percentageType a {@link software.xdev.dynamicreports.report.constant.CrosstabPercentageType} object.
     */
    public void setPercentageType(CrosstabPercentageType percentageType) {
        this.percentageType = percentageType;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        if (percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL) && !calculation.equals(Calculation.COUNT) && !calculation.equals(Calculation.DISTINCT_COUNT)) {
            return Double.class;
        }
        return ReportUtils.getVariableValueClass(calculation, valueExpression.getValueClass());
    }
}
