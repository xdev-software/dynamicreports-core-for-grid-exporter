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
package software.xdev.dynamicreports.report.base;

import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.component.DRList;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRBand class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRBand implements DRIBand {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private SplitType splitType;
    private DRList list;
    private DRIExpression<Boolean> printWhenExpression;

    /**
     * <p>Constructor for DRBand.</p>
     */
    public DRBand() {
        this.list = new DRList(ListType.VERTICAL);
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

    /** {@inheritDoc} */
    @Override
    public DRList getList() {
        return list;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.base.component.DRComponent} object.
     */
    public void addComponent(DRComponent component) {
        list.addComponent(component);
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<Boolean> getPrintWhenExpression() {
        return printWhenExpression;
    }

    /**
     * <p>Setter for the field <code>printWhenExpression</code>.</p>
     *
     * @param printWhenExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setPrintWhenExpression(DRIExpression<Boolean> printWhenExpression) {
        this.printWhenExpression = printWhenExpression;
    }
}
