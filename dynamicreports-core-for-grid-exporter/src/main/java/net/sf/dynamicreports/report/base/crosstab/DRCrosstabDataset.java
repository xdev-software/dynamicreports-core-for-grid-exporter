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
package net.sf.dynamicreports.report.base.crosstab;

import net.sf.dynamicreports.report.base.DRDataset;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.crosstab.DRICrosstabDataset;

/**
 * <p>DRCrosstabDataset class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRCrosstabDataset implements DRICrosstabDataset {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRDataset subDataset;
    private Boolean dataPreSorted;

    /** {@inheritDoc} */
    @Override
    public DRDataset getSubDataset() {
        return subDataset;
    }

    /**
     * <p>Setter for the field <code>subDataset</code>.</p>
     *
     * @param subDataset a {@link net.sf.dynamicreports.report.base.DRDataset} object.
     */
    public void setSubDataset(DRDataset subDataset) {
        this.subDataset = subDataset;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getDataPreSorted() {
        return dataPreSorted;
    }

    /**
     * <p>Setter for the field <code>dataPreSorted</code>.</p>
     *
     * @param dataPreSorted a {@link java.lang.Boolean} object.
     */
    public void setDataPreSorted(Boolean dataPreSorted) {
        this.dataPreSorted = dataPreSorted;
    }
}
