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

import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignVariable class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignVariable implements DRIDesignVariable {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DRIDesignExpression valueExpression;
    private DRIDesignExpression initialValueExpression;
    private Calculation calculation;
    private ResetType resetType;
    private DRDesignGroup resetGroup;

    /**
     * <p>Constructor for DRDesignVariable.</p>
     */
    public DRDesignVariable() {
        this.name = ReportUtils.generateUniqueName("variable");
    }

    /**
     * <p>Constructor for DRDesignVariable.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DRDesignVariable(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
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
    public DRIDesignExpression getInitialValueExpression() {
        return initialValueExpression;
    }

    /**
     * <p>Setter for the field <code>initialValueExpression</code>.</p>
     *
     * @param initialValueExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setInitialValueExpression(DRIDesignExpression initialValueExpression) {
        this.initialValueExpression = initialValueExpression;
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
    public ResetType getResetType() {
        return resetType;
    }

    /**
     * <p>Setter for the field <code>resetType</code>.</p>
     *
     * @param resetType a {@link software.xdev.dynamicreports.design.constant.ResetType} object.
     */
    public void setResetType(ResetType resetType) {
        this.resetType = resetType;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignGroup getResetGroup() {
        return resetGroup;
    }

    /**
     * <p>Setter for the field <code>resetGroup</code>.</p>
     *
     * @param resetGroup a {@link software.xdev.dynamicreports.design.base.DRDesignGroup} object.
     */
    public void setResetGroup(DRDesignGroup resetGroup) {
        this.resetGroup = resetGroup;
    }

    /** {@inheritDoc} */
    @Override
    public Class<?> getValueClass() {
        return ReportUtils.getVariableValueClass(calculation, valueExpression.getValueClass());
    }
}
