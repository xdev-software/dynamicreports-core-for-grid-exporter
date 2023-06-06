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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.constant.ComponentGroupType;
import software.xdev.dynamicreports.design.definition.component.DRIDesignList;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRDesignList class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignList extends DRDesignComponent implements DRIDesignList {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private List<DRDesignListCell> listCells;
    private List<DRDesignComponent> components;
    private ListType type;
    private ComponentGroupType componentGroupType;
    private int gap;
    private boolean calculateComponents;
    private Boolean removable;
    private DRDesignComponent backgroundComponent;

    /**
     * <p>Constructor for DRDesignList.</p>
     */
    public DRDesignList() {
        this(ListType.HORIZONTAL);
    }

    /**
     * <p>Constructor for DRDesignList.</p>
     *
     * @param type a {@link software.xdev.dynamicreports.report.constant.ListType} object.
     */
    public DRDesignList(ListType type) {
        super("list");
        this.type = type;
        this.calculateComponents = true;
    }

    /** {@inheritDoc} */
    @Override
    protected void init() {
        super.init();
        this.listCells = new ArrayList<DRDesignListCell>();
        this.components = new ArrayList<DRDesignComponent>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRDesignComponent> getComponents() {
        return components;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(DRDesignComponent component) {
        components.add(component);
        listCells.add(new DRDesignListCell(component));
    }

    /**
     * <p>addComponent.</p>
     *
     * @param index     a int.
     * @param component a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(int index, DRDesignComponent component) {
        components.add(index, component);
        listCells.add(index, new DRDesignListCell(component));
    }

    /**
     * <p>addComponent.</p>
     *
     * @param index               a int.
     * @param horizontalAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     * @param verticalAlignment   a {@link software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     * @param component           a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(int index, HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRDesignComponent component) {
        components.add(index, component);
        listCells.add(index, new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
    }

    /**
     * <p>addComponent.</p>
     *
     * @param horizontalAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     * @param verticalAlignment   a {@link software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     * @param component           a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRDesignComponent component) {
        components.add(component);
        listCells.add(new DRDesignListCell(horizontalAlignment, verticalAlignment, component));
    }

    /**
     * <p>Getter for the field <code>listCells</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRDesignListCell> getListCells() {
        return listCells;
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty() {
        return components.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public ListType getType() {
        return type;
    }

    /**
     * <p>Setter for the field <code>type</code>.</p>
     *
     * @param type a {@link software.xdev.dynamicreports.report.constant.ListType} object.
     */
    public void setType(ListType type) {
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public ComponentGroupType getComponentGroupType() {
        return componentGroupType;
    }

    /**
     * <p>Setter for the field <code>componentGroupType</code>.</p>
     *
     * @param componentGroupType a {@link software.xdev.dynamicreports.design.constant.ComponentGroupType} object.
     */
    public void setComponentGroupType(ComponentGroupType componentGroupType) {
        this.componentGroupType = componentGroupType;
    }

    /**
     * <p>Getter for the field <code>gap</code>.</p>
     *
     * @return a int.
     */
    public int getGap() {
        return gap;
    }

    /**
     * <p>Setter for the field <code>gap</code>.</p>
     *
     * @param gap a int.
     */
    public void setGap(int gap) {
        this.gap = gap;
    }

    /**
     * <p>isCalculateComponents.</p>
     *
     * @return a boolean.
     */
    public boolean isCalculateComponents() {
        return calculateComponents;
    }

    /**
     * <p>Setter for the field <code>calculateComponents</code>.</p>
     *
     * @param calculateComponents a boolean.
     */
    public void setCalculateComponents(boolean calculateComponents) {
        this.calculateComponents = calculateComponents;
    }

    /**
     * <p>isRemovable.</p>
     *
     * @return a boolean.
     */
    public boolean isRemovable() {
        if (removable != null) {
            return removable;
        } else {
            return ListType.VERTICAL.equals(type);
        }
    }

    /**
     * <p>Setter for the field <code>removable</code>.</p>
     *
     * @param removable a {@link java.lang.Boolean} object.
     */
    public void setRemovable(Boolean removable) {
        this.removable = removable;
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignComponent getBackgroundComponent() {
        return backgroundComponent;
    }

    /**
     * <p>Setter for the field <code>backgroundComponent</code>.</p>
     *
     * @param backgroundComponent a {@link software.xdev.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void setBackgroundComponent(DRDesignComponent backgroundComponent) {
        this.backgroundComponent = backgroundComponent;
    }

}
