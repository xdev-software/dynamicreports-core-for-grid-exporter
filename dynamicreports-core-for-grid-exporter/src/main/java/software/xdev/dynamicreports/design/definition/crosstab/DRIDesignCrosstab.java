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
package software.xdev.dynamicreports.design.definition.crosstab;

import software.xdev.dynamicreports.design.definition.component.DRIDesignComponent;
import software.xdev.dynamicreports.report.constant.RunDirection;

import java.util.List;

/**
 * <p>DRIDesignCrosstab interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignCrosstab extends DRIDesignComponent {

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabDataset} object.
     */
    public DRIDesignCrosstabDataset getDataset();

    /**
     * <p>isRepeatColumnHeaders.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isRepeatColumnHeaders();

    /**
     * <p>isRepeatRowHeaders.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean isRepeatRowHeaders();

    /**
     * <p>getColumnBreakOffset.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getColumnBreakOffset();

    /**
     * <p>getIgnoreWidth.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIgnoreWidth();

    /**
     * <p>getRunDirection.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.RunDirection} object.
     */
    public RunDirection getRunDirection();

    /**
     * <p>getWhenNoDataCell.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    public DRIDesignCrosstabCellContent getWhenNoDataCell();

    /**
     * <p>getHeaderCell.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.crosstab.DRIDesignCrosstabCellContent} object.
     */
    public DRIDesignCrosstabCellContent getHeaderCell();

    /**
     * <p>getColumnGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIDesignCrosstabColumnGroup> getColumnGroups();

    /**
     * <p>getRowGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIDesignCrosstabRowGroup> getRowGroups();

    /**
     * <p>getCells.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIDesignCrosstabCell> getCells();

    /**
     * <p>getMeasures.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIDesignCrosstabMeasure> getMeasures();
}
