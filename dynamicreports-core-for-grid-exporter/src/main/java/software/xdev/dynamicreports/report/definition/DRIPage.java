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
package software.xdev.dynamicreports.report.definition;

import software.xdev.dynamicreports.report.constant.PageOrientation;

import java.io.Serializable;

/**
 * <p>DRIPage interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIPage extends Serializable {

    /**
     * <p>getWidth.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getWidth();

    /**
     * <p>getHeight.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getHeight();

    /**
     * <p>getOrientation.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.PageOrientation} object.
     */
    public PageOrientation getOrientation();

    /**
     * <p>getMargin.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIMargin} object.
     */
    public DRIMargin getMargin();

    /**
     * <p>getColumnsPerPage.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getColumnsPerPage();

    /**
     * <p>getColumnSpace.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getColumnSpace();

    /**
     * <p>getIgnorePageWidth.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIgnorePageWidth();
}
