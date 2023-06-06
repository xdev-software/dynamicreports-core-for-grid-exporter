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
package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.EvaluationTime;
import net.sf.dynamicreports.design.definition.component.DRIDesignGenericElement;
import net.sf.dynamicreports.design.definition.expression.DRIDesignParameterExpression;
import net.sf.dynamicreports.report.constant.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRDesignGenericElement class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignGenericElement extends DRDesignComponent implements DRIDesignGenericElement {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String genericElementNamespace;
    private String genericElementName;
    private EvaluationTime evaluationTime;
    private DRDesignGroup evaluationGroup;
    private List<DRIDesignParameterExpression> parameterExpressions;

    /**
     * <p>Constructor for DRDesignGenericElement.</p>
     */
    public DRDesignGenericElement() {
        super("genericElement");
    }

    /** {@inheritDoc} */
    @Override
    protected void init() {
        super.init();
        parameterExpressions = new ArrayList<DRIDesignParameterExpression>();
    }

    /** {@inheritDoc} */
    @Override
    public String getGenericElementNamespace() {
        return genericElementNamespace;
    }

    /**
     * <p>Setter for the field <code>genericElementNamespace</code>.</p>
     *
     * @param genericElementNamespace a {@link java.lang.String} object.
     */
    public void setGenericElementNamespace(String genericElementNamespace) {
        this.genericElementNamespace = genericElementNamespace;
    }

    /** {@inheritDoc} */
    @Override
    public String getGenericElementName() {
        return genericElementName;
    }

    /**
     * <p>Setter for the field <code>genericElementName</code>.</p>
     *
     * @param genericElementName a {@link java.lang.String} object.
     */
    public void setGenericElementName(String genericElementName) {
        this.genericElementName = genericElementName;
    }

    /** {@inheritDoc} */
    @Override
    public EvaluationTime getEvaluationTime() {
        return evaluationTime;
    }

    /**
     * <p>Setter for the field <code>evaluationTime</code>.</p>
     *
     * @param evaluationTime a {@link net.sf.dynamicreports.design.constant.EvaluationTime} object.
     */
    public void setEvaluationTime(EvaluationTime evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignGroup getEvaluationGroup() {
        return evaluationGroup;
    }

    /**
     * <p>Setter for the field <code>evaluationGroup</code>.</p>
     *
     * @param evaluationGroup a {@link net.sf.dynamicreports.design.base.DRDesignGroup} object.
     */
    public void setEvaluationGroup(DRDesignGroup evaluationGroup) {
        this.evaluationGroup = evaluationGroup;
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIDesignParameterExpression> getParameterExpressions() {
        return parameterExpressions;
    }

    /**
     * <p>Setter for the field <code>parameterExpressions</code>.</p>
     *
     * @param parameterExpressions a {@link java.util.List} object.
     */
    public void setParameterExpressions(List<DRIDesignParameterExpression> parameterExpressions) {
        this.parameterExpressions = parameterExpressions;
    }
}
