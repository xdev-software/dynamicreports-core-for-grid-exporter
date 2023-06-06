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
package net.sf.dynamicreports.report.builder.subtotal;

import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>Abstract SubtotalBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class SubtotalBuilder<T extends SubtotalBuilder<T, U>, U> extends BaseSubtotalBuilder<T, U> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for SubtotalBuilder.</p>
     *
     * @param showInColumn a {@link net.sf.dynamicreports.report.builder.column.ColumnBuilder} object.
     */
    protected SubtotalBuilder(ColumnBuilder<?, ?> showInColumn) {
        super(showInColumn);
    }
}
