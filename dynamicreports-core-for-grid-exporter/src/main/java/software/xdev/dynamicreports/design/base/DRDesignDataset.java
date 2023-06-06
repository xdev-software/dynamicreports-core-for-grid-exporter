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

import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.design.transformation.DatasetExpressionTransform;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;

import java.util.Collection;

/**
 * <p>DRDesignDataset class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignDataset implements DRIDesignDataset {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private DatasetExpressionTransform datasetExpressionTransform;
    private DRDesignQuery query;
    private DRIDesignExpression connectionExpression;
    private DRIDesignExpression dataSourceExpression;
    private DRIDesignExpression filterExpression;

    /**
     * <p>Constructor for DRDesignDataset.</p>
     *
     * @param datasetExpressionTransform a {@link software.xdev.dynamicreports.design.transformation.DatasetExpressionTransform} object.
     */
    public DRDesignDataset(DatasetExpressionTransform datasetExpressionTransform) {
        this.datasetExpressionTransform = datasetExpressionTransform;
        this.name = ReportUtils.generateUniqueName("dataset");
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <p>Getter for the field <code>datasetExpressionTransform</code>.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.transformation.DatasetExpressionTransform} object.
     */
    public DatasetExpressionTransform getDatasetExpressionTransform() {
        return datasetExpressionTransform;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignField> getFields() {
        return datasetExpressionTransform.getFields();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignVariable> getVariables() {
        return datasetExpressionTransform.getVariables();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSystemExpression> getSystemExpressions() {
        return datasetExpressionTransform.getSystemExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignJasperExpression> getJasperExpressions() {
        return datasetExpressionTransform.getJasperExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSimpleExpression> getSimpleExpressions() {
        return datasetExpressionTransform.getSimpleExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignComplexExpression> getComplexExpressions() {
        return datasetExpressionTransform.getComplexExpressions();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<DRIDesignSort> getSorts() {
        return datasetExpressionTransform.getSorts();
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignQuery getQuery() {
        return query;
    }

    /**
     * <p>Setter for the field <code>query</code>.</p>
     *
     * @param query a {@link software.xdev.dynamicreports.design.base.DRDesignQuery} object.
     */
    public void setQuery(DRDesignQuery query) {
        this.query = query;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getConnectionExpression() {
        return connectionExpression;
    }

    /**
     * <p>Setter for the field <code>connectionExpression</code>.</p>
     *
     * @param connectionExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setConnectionExpression(DRIDesignExpression connectionExpression) {
        this.connectionExpression = connectionExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getDataSourceExpression() {
        return dataSourceExpression;
    }

    /**
     * <p>Setter for the field <code>dataSourceExpression</code>.</p>
     *
     * @param dataSourceExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setDataSourceExpression(DRIDesignExpression dataSourceExpression) {
        this.dataSourceExpression = dataSourceExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getFilterExpression() {
        return filterExpression;
    }

    /**
     * <p>Setter for the field <code>filterExpression</code>.</p>
     *
     * @param filterExpression a {@link software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setFilterExpression(DRIDesignExpression filterExpression) {
        this.filterExpression = filterExpression;
    }
}
