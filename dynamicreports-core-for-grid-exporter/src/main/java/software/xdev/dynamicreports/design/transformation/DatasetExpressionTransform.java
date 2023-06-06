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
package software.xdev.dynamicreports.design.transformation;

import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRISort;
import software.xdev.dynamicreports.report.definition.DRIVariable;

import java.util.List;

/**
 * <p>DatasetExpressionTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DatasetExpressionTransform extends AbstractExpressionTransform {
    private DRIDataset dataset;

    /**
     * <p>Constructor for DatasetExpressionTransform.</p>
     *
     * @param accessor a {@link software.xdev.dynamicreports.design.transformation.DesignTransformAccessor} object.
     * @param dataset  a {@link software.xdev.dynamicreports.report.definition.DRIDataset} object.
     */
    public DatasetExpressionTransform(DesignTransformAccessor accessor, DRIDataset dataset) {
        super(accessor);
        this.dataset = dataset;
    }

    /** {@inheritDoc} */
    @Override
    protected ResetType getVariableResetType(DRIVariable<?> variable) {
        return ResetType.REPORT;
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRIField<?>> transformFields() {
        return dataset.getFields();
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRIVariable<?>> transformVariables() {
        return dataset.getVariables();
    }

    /** {@inheritDoc} */
    @Override
    protected List<? extends DRISort> transformSorts() {
        return dataset.getSorts();
    }

    /** {@inheritDoc} */
    @Override
    protected DRIDesignDataset getDataset() {
        return accessor.getDatasetTransform().getDesignDataset(dataset);
    }
}
