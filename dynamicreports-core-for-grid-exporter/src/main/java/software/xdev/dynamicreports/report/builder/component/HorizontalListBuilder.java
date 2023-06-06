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
package software.xdev.dynamicreports.report.builder.component;

import software.xdev.dynamicreports.report.base.component.DRList;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import org.apache.commons.lang3.Validate;

/**
 * <p>HorizontalListBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class HorizontalListBuilder extends DimensionComponentBuilder<HorizontalListBuilder, DRList> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRList row;

    /**
     * <p>Constructor for HorizontalListBuilder.</p>
     */
    protected HorizontalListBuilder() {
        super(new DRList(ListType.VERTICAL));
        init();
    }

    /**
     * <p>init.</p>
     */
    protected void init() {
        newRow();
    }

    /**
     * <p>add.</p>
     *
     * @param components a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder add(ComponentBuilder<?, ?>... components) {
        Validate.notNull(components, "components must not be null");
        Validate.noNullElements(components, "components must not contains null component");
        for (ComponentBuilder<?, ?> component : components) {
            row.addComponent(component.getComponent());
        }
        return this;
    }

    /**
     * <p>add.</p>
     *
     * @param gap        a int.
     * @param components a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder add(int gap, ComponentBuilder<?, ?>... components) {
        Validate.notNull(components, "components must not be null");
        for (ComponentBuilder<?, ?> component : components) {
            add(Components.hListCell(Components.filler().setWidth(gap)).widthFixed());
            add(component);
        }
        return this;
    }

    /**
     * <p>add.</p>
     *
     * @param cells a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListCellBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder add(HorizontalListCellBuilder... cells) {
        Validate.notNull(cells, "cells must not be null");
        Validate.noNullElements(cells, "cells must not contains null cell");
        for (HorizontalListCellBuilder cell : cells) {
            row.addCell(cell.build());
        }
        return this;
    }

    /**
     * <p>add.</p>
     *
     * @param gap   a int.
     * @param cells a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListCellBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder add(int gap, HorizontalListCellBuilder... cells) {
        Validate.notNull(cells, "cells must not be null");
        for (HorizontalListCellBuilder cell : cells) {
            add(Components.hListCell(Components.filler().setWidth(gap)).widthFixed(), cell);
        }
        return this;
    }

    /**
     * <p>newRow.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder newRow() {
        return newRow(0);
    }

    /**
     * <p>newRow.</p>
     *
     * @param verticalGap a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder newRow(Integer verticalGap) {
        return newRow(verticalGap, ListType.HORIZONTAL);
    }

    /**
     * <p>newFlowRow.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder newFlowRow() {
        return newFlowRow(0);
    }

    /**
     * <p>newFlowRow.</p>
     *
     * @param verticalGap a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder newFlowRow(Integer verticalGap) {
        return newRow(verticalGap, ListType.HORIZONTAL_FLOW);
    }

    private HorizontalListBuilder newRow(Integer verticalGap, ListType listType) {
        if (verticalGap != null) {
            Validate.isTrue(verticalGap >= 0, "verticalGap must be >= 0");
        }
        if (verticalGap != null && verticalGap > 0) {
            getObject().addComponent(HorizontalCellComponentAlignment.CENTER, VerticalCellComponentAlignment.TOP, Components.filler().setHeight(verticalGap).build());
        }
        row = new DRList(listType);
        getObject().addComponent(row);
        return this;
    }

    /**
     * <p>setGap.</p>
     *
     * @param gap a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setGap(Integer gap) {
        row.setGap(gap);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalListBuilder setStyle(ReportStyleBuilder style) {
        if (style != null) {
            row.setStyle(style.build());
        } else {
            row.setStyle(null);
        }
        return this;
    }

    /**
     * <p>setBaseStretchType.</p>
     *
     * @param stretchType a {@link software.xdev.dynamicreports.report.constant.StretchType} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setBaseStretchType(StretchType stretchType) {
        row.setStretchType(stretchType);
        return this;
    }

    /**
     * <p>setBaseGap.</p>
     *
     * @param gap a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setBaseGap(Integer gap) {
        getObject().setGap(gap);
        return this;
    }

    /**
     * <p>setBaseStyle.</p>
     *
     * @param style a {@link software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setBaseStyle(ReportStyleBuilder style) {
        if (style != null) {
            getObject().setStyle(style.build());
        } else {
            getObject().setStyle(null);
        }
        return this;
    }

    /**
     * <p>setBaseBackgroundComponent.</p>
     *
     * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setBaseBackgroundComponent(ComponentBuilder<?, ?> backgroundComponent) {
        Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
        getObject().setBackgroundComponent(backgroundComponent.build());
        return this;
    }

    /**
     * <p>setBackgroundComponent.</p>
     *
     * @param backgroundComponent a {@link software.xdev.dynamicreports.report.builder.component.ComponentBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder} object.
     */
    public HorizontalListBuilder setBackgroundComponent(ComponentBuilder<?, ?> backgroundComponent) {
        Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
        row.setBackgroundComponent(backgroundComponent.build());
        return this;
    }

    /**
     * <p>getList.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.component.DRList} object.
     */
    public DRList getList() {
        return build();
    }
}
