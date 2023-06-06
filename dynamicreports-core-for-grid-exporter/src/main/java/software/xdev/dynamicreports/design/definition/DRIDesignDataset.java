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

import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>DRIDesignDataset interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignDataset extends Serializable {

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getName();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignField> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignVariable> getVariables();

    /**
     * <p>getSystemExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignSystemExpression> getSystemExpressions();

    /**
     * <p>getJasperExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignJasperExpression> getJasperExpressions();

    /**
     * <p>getSimpleExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignSimpleExpression> getSimpleExpressions();

    /**
     * <p>getComplexExpressions.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignComplexExpression> getComplexExpressions();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignSort> getSorts();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.DRIDesignQuery} object.
     */
    public DRIDesignQuery getQuery();

    /**
     * <p>getConnectionExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getConnectionExpression();

    /**
     * <p>getDataSourceExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getDataSourceExpression();

    /**
     * <p>getFilterExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public DRIDesignExpression getFilterExpression();
}
