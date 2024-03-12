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
package software.xdev.dynamicreports.report.definition;

import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRIVariable interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIVariable<T> extends DRIExpression<T>, DRIValue<T> {

    /**
     * <p>getCalculation.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Calculation} object.
     */
    public Calculation getCalculation();

    /**
     * <p>getResetType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Evaluation} object.
     */
    public Evaluation getResetType();

    /**
     * <p>getResetGroup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIGroup} object.
     */
    public DRIGroup getResetGroup();

    /**
     * <p>getValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<?> getValueExpression();

    /**
     * <p>getInitialValueExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<?> getInitialValueExpression();
}