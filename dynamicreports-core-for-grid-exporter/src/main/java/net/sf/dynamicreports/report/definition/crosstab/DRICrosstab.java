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
package net.sf.dynamicreports.report.definition.crosstab;

import net.sf.dynamicreports.report.constant.RunDirection;
import net.sf.dynamicreports.report.definition.component.DRIDimensionComponent;
import net.sf.dynamicreports.report.definition.style.DRIReportStyle;
import net.sf.dynamicreports.report.definition.style.DRISimpleStyle;

import java.util.List;

/**
 * <p>DRICrosstab interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRICrosstab extends DRIDimensionComponent {

    /**
     * <p>getDataset.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabDataset} object.
     */
    public DRICrosstabDataset getDataset();

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
     * @return a {@link net.sf.dynamicreports.report.constant.RunDirection} object.
     */
    public RunDirection getRunDirection();

    /**
     * <p>getCellWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCellWidth();

    /**
     * <p>getCellHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getCellHeight();

    /**
     * <p>getHighlightOddRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getHighlightOddRows();

    /**
     * <p>getOddRowStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    public DRISimpleStyle getOddRowStyle();

    /**
     * <p>getHighlightEvenRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getHighlightEvenRows();

    /**
     * <p>getEvenRowStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    public DRISimpleStyle getEvenRowStyle();

    /**
     * <p>getGroupStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getGroupStyle();

    /**
     * <p>getGroupTotalStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getGroupTotalStyle();

    /**
     * <p>getGrandTotalStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getGrandTotalStyle();

    /**
     * <p>getCellStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getCellStyle();

    /**
     * <p>getMeasureTitleStyle.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getMeasureTitleStyle();

    /**
     * <p>getWhenNoDataCell.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellContent} object.
     */
    public DRICrosstabCellContent getWhenNoDataCell();

    /**
     * <p>getHeaderCell.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.definition.crosstab.DRICrosstabCellContent} object.
     */
    public DRICrosstabCellContent getHeaderCell();

    /**
     * <p>getColumnGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRICrosstabColumnGroup<?>> getColumnGroups();

    /**
     * <p>getRowGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRICrosstabRowGroup<?>> getRowGroups();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRICrosstabVariable<?>> getVariables();

    /**
     * <p>getMeasures.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRICrosstabMeasure<?>> getMeasures();
}
