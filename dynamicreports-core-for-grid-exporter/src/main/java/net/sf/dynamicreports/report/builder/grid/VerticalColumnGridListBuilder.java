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
package net.sf.dynamicreports.report.builder.grid;

import net.sf.dynamicreports.report.base.grid.DRColumnGridList;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.ListType;
import org.apache.commons.lang3.Validate;

/**
 * <p>VerticalColumnGridListBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class VerticalColumnGridListBuilder extends AbstractBuilder<VerticalColumnGridListBuilder, DRColumnGridList> implements ColumnGridComponentBuilder {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for VerticalColumnGridListBuilder.</p>
     */
    protected VerticalColumnGridListBuilder() {
        super(new DRColumnGridList(ListType.VERTICAL));
    }

    /**
     * <p>add.</p>
     *
     * @param components a {@link net.sf.dynamicreports.report.builder.grid.ColumnGridComponentBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder add(ColumnGridComponentBuilder... components) {
        Validate.notNull(components, "components must not be null");
        Validate.noNullElements(components, "components must not contains null component");
        for (ColumnGridComponentBuilder component : components) {
            getObject().addComponent(component.build());
        }
        return this;
    }

    /**
     * <p>add.</p>
     *
     * @param cells a {@link net.sf.dynamicreports.report.builder.grid.VerticalColumnGridListCellBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder add(VerticalColumnGridListCellBuilder... cells) {
        Validate.notNull(cells, "cells must not be null");
        Validate.noNullElements(cells, "cells must not contains null cell");
        for (VerticalColumnGridListCellBuilder cell : cells) {
            getObject().addCell(cell.build());
        }
        return this;
    }

    /**
     * <p>setGap.</p>
     *
     * @param gap a int.
     * @return a {@link net.sf.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder} object.
     */
    public VerticalColumnGridListBuilder setGap(int gap) {
        getObject().setGap(gap);
        return this;
    }

    /**
     * <p>getColumnGridList.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.base.grid.DRColumnGridList} object.
     */
    public DRColumnGridList getColumnGridList() {
        return build();
    }
}
