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

import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

/**
 * <p>DRIDataset interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDataset extends Serializable {

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIField<?>> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIVariable<?>> getVariables();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRISort> getSorts();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIQuery} object.
     */
    public DRIQuery getQuery();

    /**
     * <p>getConnectionExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Connection> getConnectionExpression();

    /**
     * <p>getDataSourceExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<?> getDataSourceExpression();

    /**
     * <p>getFilterExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Boolean> getFilterExpression();
}
