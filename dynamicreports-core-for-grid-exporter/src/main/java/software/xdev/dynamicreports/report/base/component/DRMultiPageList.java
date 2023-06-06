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
package software.xdev.dynamicreports.report.base.component;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRIMultiPageList;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRMultiPageList class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRMultiPageList extends DRDimensionComponent implements DRIMultiPageList {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private List<DRIComponent> components;
    private SplitType splitType;

    /**
     * <p>Constructor for DRMultiPageList.</p>
     */
    public DRMultiPageList() {
        components = new ArrayList<DRIComponent>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRIComponent> getComponents() {
        return components;
    }

    /**
     * <p>Setter for the field <code>components</code>.</p>
     *
     * @param components a {@link java.util.List} object.
     */
    public void setComponents(List<DRIComponent> components) {
        this.components = components;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.definition.component.DRIComponent} object.
     */
    public void addComponent(DRIComponent component) {
        components.add(component);
    }

    /** {@inheritDoc} */
    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * <p>Setter for the field <code>splitType</code>.</p>
     *
     * @param splitType a {@link software.xdev.dynamicreports.report.constant.SplitType} object.
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

}
