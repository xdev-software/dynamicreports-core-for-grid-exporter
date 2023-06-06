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
package software.xdev.dynamicreports.report.builder.grid;

/**
 * A set of methods of customizing columns layout
 *
 * @author Ricardo Mariaca
 * 
 */
public class GridBuilders {

    // horizontal

    /**
     * <p>horizontalColumnGridList.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalColumnGridList() {
        return Grids.horizontalColumnGridList();
    }

    /**
     * <p>horizontalColumnGridList.</p>
     *
     * @param components a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalColumnGridList(ColumnGridComponentBuilder... components) {
        return Grids.horizontalColumnGridList(components);
    }

    /**
     * <p>horizontalColumnGridList.</p>
     *
     * @param cells a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListCellBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalColumnGridList(HorizontalColumnGridListCellBuilder... cells) {
        return Grids.horizontalColumnGridList(cells);
    }

    /**
     * <p>hColumnGridListCell.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListCellBuilder} object.
     */
    public HorizontalColumnGridListCellBuilder hColumnGridListCell(ColumnGridComponentBuilder component) {
        return Grids.hColumnGridListCell(component);
    }

    // horizontal flow

    /**
     * <p>horizontalFlowColumnGridList.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalFlowColumnGridList() {
        return Grids.horizontalFlowColumnGridList();
    }

    /**
     * <p>horizontalFlowColumnGridList.</p>
     *
     * @param components a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalFlowColumnGridList(ColumnGridComponentBuilder... components) {
        return Grids.horizontalFlowColumnGridList(components);
    }

    /**
     * <p>horizontalFlowColumnGridList.</p>
     *
     * @param cells a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListCellBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder} object.
     */
    public HorizontalColumnGridListBuilder horizontalFlowColumnGridList(HorizontalColumnGridListCellBuilder... cells) {
        return Grids.horizontalFlowColumnGridList(cells);
    }

    // vertical

    /**
     * <p>verticalColumnGridList.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder verticalColumnGridList() {
        return Grids.verticalColumnGridList();
    }

    /**
     * <p>verticalColumnGridList.</p>
     *
     * @param components a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder verticalColumnGridList(ColumnGridComponentBuilder... components) {
        return Grids.verticalColumnGridList(components);
    }

    /**
     * <p>verticalColumnGridList.</p>
     *
     * @param cells a {@link software.xdev.dynamicreports.report.builder.grid.VerticalColumnGridListCellBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder verticalColumnGridList(VerticalColumnGridListCellBuilder... cells) {
        return Grids.verticalColumnGridList(cells);
    }

    /**
     * <p>vColumnGridListCell.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.VerticalColumnGridListCellBuilder} object.
     */
    public VerticalColumnGridListCellBuilder vColumnGridListCell(ColumnGridComponentBuilder component) {
        return Grids.vColumnGridListCell(component);
    }

    // title group

    /**
     * <p>titleGroup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder} object.
     */
    public ColumnTitleGroupBuilder titleGroup() {
        return Grids.titleGroup();
    }

    /**
     * <p>titleGroup.</p>
     *
     * @param components a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder} object.
     */
    public ColumnTitleGroupBuilder titleGroup(ColumnGridComponentBuilder... components) {
        return Grids.titleGroup(components);
    }

    /**
     * <p>titleGroup.</p>
     *
     * @param title      a {@link java.lang.String} object.
     * @param components a {@link software.xdev.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder} object.
     */
    public ColumnTitleGroupBuilder titleGroup(String title, ColumnGridComponentBuilder... components) {
        return Grids.titleGroup(title, components);
    }
}
