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
package net.sf.dynamicreports.design.transformation;

import net.sf.dynamicreports.design.base.DRDesignDataset;
import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.exception.DRDesignReportException;
import net.sf.dynamicreports.jasper.base.JasperScriptlet;
import net.sf.dynamicreports.report.definition.DRIDataset;
import net.sf.dynamicreports.report.exception.DRException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>DatasetTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DatasetTransform {
    private DesignTransformAccessor accessor;
    private Map<String, DRIDesignDataset> datasets;
    private Map<DRIDataset, DRDesignDataset> designDatasets;

    /**
     * <p>Constructor for DatasetTransform.</p>
     *
     * @param accessor a {@link net.sf.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public DatasetTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
        datasets = new HashMap<>();
        designDatasets = new HashMap<>();
    }

    /**
     * <p>transform.</p>
     *
     * @param dataset a {@link net.sf.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link net.sf.dynamicreports.design.base.DRDesignDataset} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public DRDesignDataset transform(DRIDataset dataset) throws DRException {
        if (dataset == null) {
            return null;
        }
        if (designDatasets.containsKey(dataset)) {
            return designDatasets.get(dataset);
        }

        DatasetExpressionTransform datasetExpressionTransform = new DatasetExpressionTransform(accessor, dataset);
        datasetExpressionTransform.transform();
        DRDesignDataset designDataset = new DRDesignDataset(datasetExpressionTransform);
        if (dataset.getQuery() != null) {
            designDataset.setQuery(accessor.getReportTransform().query(dataset.getQuery()));
        }
        designDataset.setConnectionExpression(accessor.getExpressionTransform().transformExpression(dataset.getConnectionExpression()));
        designDataset.setDataSourceExpression(accessor.getExpressionTransform().transformExpression(dataset.getDataSourceExpression()));
        designDataset.setFilterExpression(datasetExpressionTransform.transformExpression(dataset.getFilterExpression(), JasperScriptlet.SCRIPTLET_NAME));

        addDataset(dataset, designDataset);

        return designDataset;
    }

    /**
     * <p>getDatasetExpressionTransform.</p>
     *
     * @param dataset a {@link net.sf.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link net.sf.dynamicreports.design.transformation.DatasetExpressionTransform} object.
     */
    public DatasetExpressionTransform getDatasetExpressionTransform(DRIDataset dataset) {
        return designDatasets.get(dataset).getDatasetExpressionTransform();
    }

    /**
     * <p>getDesignDataset.</p>
     *
     * @param dataset a {@link net.sf.dynamicreports.report.definition.DRIDataset} object.
     * @return a {@link net.sf.dynamicreports.design.base.DRDesignDataset} object.
     */
    protected DRDesignDataset getDesignDataset(DRIDataset dataset) {
        return designDatasets.get(dataset);
    }

    private void addDataset(DRIDataset dataset, DRDesignDataset designDataset) {
        if (datasets.containsKey(designDataset.getName())) {
            throw new DRDesignReportException("Duplicate declaration of dataset \"" + designDataset.getName() + "\"");
        }
        datasets.put(designDataset.getName(), designDataset);
        designDatasets.put(dataset, designDataset);
    }

    /**
     * <p>Getter for the field <code>datasets</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<DRIDesignDataset> getDatasets() {
        return datasets.values();
    }
}
