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
package software.xdev.dynamicreports.design.definition.component;

import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignParameterExpression;

import java.util.List;

/**
 * <p>DRIDesignGenericElement interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignGenericElement extends DRIDesignComponent {

    /**
     * <p>getGenericElementNamespace.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getGenericElementNamespace();

    /**
     * <p>getGenericElementName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getGenericElementName();

    /**
     * <p>getEvaluationTime.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.constant.EvaluationTime} object.
     */
    public EvaluationTime getEvaluationTime();

    /**
     * <p>getEvaluationGroup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.DRIDesignGroup} object.
     */
    public DRIDesignGroup getEvaluationGroup();

    /**
     * <p>getParameterExpressions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignParameterExpression> getParameterExpressions();

}
