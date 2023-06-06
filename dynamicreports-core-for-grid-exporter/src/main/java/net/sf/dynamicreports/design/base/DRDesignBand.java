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
package net.sf.dynamicreports.design.base;

import net.sf.dynamicreports.design.base.component.DRDesignComponent;
import net.sf.dynamicreports.design.base.component.DRDesignList;
import net.sf.dynamicreports.design.definition.DRIDesignBand;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.SplitType;

/**
 * <p>DRDesignBand class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignBand implements DRIDesignBand {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;
    private SplitType splitType;
    private DRDesignList list;
    private DRDesignComponent bandComponent;
    private Integer height;
    private DRIDesignExpression printWhenExpression;

    /**
     * <p>Constructor for DRDesignBand.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DRDesignBand(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * <p>Setter for the field <code>splitType</code>.</p>
     *
     * @param splitType a {@link net.sf.dynamicreports.report.constant.SplitType} object.
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    /** {@inheritDoc} */
    @Override
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
     * <p>addComponent.</p>
     *
     * @param component a {@link net.sf.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(DRDesignComponent component) {
        list.addComponent(component);
    }

    /**
     * <p>addComponent.</p>
     *
     * @param index     a int.
     * @param component a {@link net.sf.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void addComponent(int index, DRDesignComponent component) {
        list.addComponent(index, component);
    }

    /** {@inheritDoc} */
    @Override
    public DRDesignComponent getBandComponent() {
        return bandComponent;
    }

    /**
     * <p>Setter for the field <code>bandComponent</code>.</p>
     *
     * @param component a {@link net.sf.dynamicreports.design.base.component.DRDesignComponent} object.
     */
    public void setBandComponent(DRDesignComponent component) {
        this.bandComponent = component;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * <p>Setter for the field <code>height</code>.</p>
     *
     * @param height a {@link java.lang.Integer} object.
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /** {@inheritDoc} */
    @Override
    public DRIDesignExpression getPrintWhenExpression() {
        return printWhenExpression;
    }

    /**
     * <p>Setter for the field <code>printWhenExpression</code>.</p>
     *
     * @param printWhenExpression a {@link net.sf.dynamicreports.design.definition.expression.DRIDesignExpression} object.
     */
    public void setPrintWhenExpression(DRIDesignExpression printWhenExpression) {
        this.printWhenExpression = printWhenExpression;
    }
}
