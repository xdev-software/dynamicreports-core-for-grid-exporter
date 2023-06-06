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
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.component.DRIList;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>DRList class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRList extends DRDimensionComponent implements DRIList {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private List<DRListCell> listCells;
    private ListType type;
    private Integer gap;
    private DRComponent backgroundComponent;

    /**
     * <p>Constructor for DRList.</p>
     */
    public DRList() {
        this(ListType.HORIZONTAL);
    }

    /**
     * <p>Constructor for DRList.</p>
     *
     * @param type a {@link software.xdev.dynamicreports.report.constant.ListType} object.
     */
    public DRList(ListType type) {
        setType(type);
    }

    /** {@inheritDoc} */
    @Override
    protected void init() {
        super.init();
        this.listCells = new ArrayList<DRListCell>();
    }

    /** {@inheritDoc} */
    @Override
    public List<DRListCell> getListCells() {
        return listCells;
    }

    /**
     * <p>addComponent.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.base.component.DRComponent} object.
     */
    public void addComponent(DRComponent component) {
        listCells.add(new DRListCell(component));
    }

    /**
     * <p>addCell.</p>
     *
     * @param cell a {@link software.xdev.dynamicreports.report.base.component.DRListCell} object.
     */
    public void addCell(DRListCell cell) {
        Validate.notNull(cell, "cell must not be null");
        listCells.add(cell);
    }

    /**
     * <p>addComponent.</p>
     *
     * @param horizontalAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment} object.
     * @param verticalAlignment   a {@link software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment} object.
     * @param component           a {@link software.xdev.dynamicreports.report.base.component.DRComponent} object.
     */
    public void addComponent(HorizontalCellComponentAlignment horizontalAlignment, VerticalCellComponentAlignment verticalAlignment, DRComponent component) {
        listCells.add(new DRListCell(horizontalAlignment, verticalAlignment, component));
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
        Validate.notNull(type, "type must not be null");
        this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getGap() {
        return gap;
    }

    /**
     * <p>Setter for the field <code>gap</code>.</p>
     *
     * @param gap a {@link java.lang.Integer} object.
     */
    public void setGap(Integer gap) {
        if (gap != null) {
            Validate.isTrue(gap >= 0, "gap must be >= 0");
        }
        this.gap = gap;
    }

    /** {@inheritDoc} */
    @Override
    public DRComponent getBackgroundComponent() {
        return backgroundComponent;
    }

    /**
     * <p>Setter for the field <code>backgroundComponent</code>.</p>
     *
     * @param backgroundComponent a {@link software.xdev.dynamicreports.report.base.component.DRComponent} object.
     */
    public void setBackgroundComponent(DRComponent backgroundComponent) {
        this.backgroundComponent = backgroundComponent;
    }

}
