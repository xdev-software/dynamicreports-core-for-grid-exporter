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

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment;
import net.sf.dynamicreports.report.definition.column.DRIColumn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricardo Mariaca
 */
class ColumnGrid {
    private DRDesignList list;
    private Map<DRIColumn<?>, DRDesignList> columnsLists;
    private boolean isEmpty;

    /**
     * <p>Constructor for ColumnGrid.</p>
     */
    public ColumnGrid() {
        columnsLists = new HashMap<DRIColumn<?>, DRDesignList>();
        isEmpty = true;
    }

    /**
     * <p>addList.</p>
     *
     * @param column a {@link net.sf.dynamicreports.report.definition.column.DRIColumn} object.
     * @param list   a {@link net.sf.dynamicreports.design.base.component.DRDesignList} object.
     */
    public void addList(DRIColumn<?> column, DRDesignList list) {
        columnsLists.put(column, list);
    }

    /**
     * <p>addComponent.</p>
     *
     * @param column    a {@link net.sf.dynamicreports.report.definition.column.DRIColumn} object.
     * @param component a {@link net.sf.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(DRIColumn<?> column, DRDesignComponent component) {
        if (columnsLists.containsKey(column)) {
            columnsLists.get(column).addComponent(component);
        }
    }

    /**
     * <p>addComponent.</p>
     *
     * @param column              a {@link net.sf.dynamicreports.report.definition.column.DRIColumn} object.
     * @param horizontalAlignment a {@link net.sf.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     * @param verticalAlignment   a {@link net.sf.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     * @param component           a {@link net.sf.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(DRIColumn<?> column, HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRDesignComponent component) {
        if (columnsLists.containsKey(column)) {
            columnsLists.get(column).addComponent(horizontalAlignment, verticalAlignment, component);
        }
    }

    /**
     * <p>Getter for the field <code>list</code>.</p>
     *
     * @return a {@link net.sf.dynamicreports.design.base.component.DRDesignList} object.
     */
    public DRDesignList getList() {
        return list;
    }

    /**
     * <p>Setter for the field <code>list</code>.</p>
     *
     * @param list a {@link net.sf.dynamicreports.design.base.component.DRDesignList} object.
     */
    public void setList(DRDesignList list) {
        this.list = list;
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        for (DRDesignList list : columnsLists.values()) {
            if (!list.isEmpty()) {
                return false;
            }
        }
        return isEmpty;
    }

    /**
     * <p>setEmpty.</p>
     *
     * @param isEmpty a boolean.
     */
    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
