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
package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabRowGroup;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>CrosstabRowGroupBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class CrosstabRowGroupBuilder<T> extends AbstractCrosstabGroupBuilder<CrosstabRowGroupBuilder<T>, DRCrosstabRowGroup<T>, T> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param column a {@link net.sf.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     */
    protected CrosstabRowGroupBuilder(ValueColumnBuilder<?, T> column) {
        super(column, new DRCrosstabRowGroup<T>());
    }

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param field a {@link net.sf.dynamicreports.report.builder.FieldBuilder} object.
     */
    protected CrosstabRowGroupBuilder(FieldBuilder<T> field) {
        super(field, new DRCrosstabRowGroup<T>());
    }

    /**
     * <p>Constructor for CrosstabRowGroupBuilder.</p>
     *
     * @param expression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected CrosstabRowGroupBuilder(DRIExpression<T> expression) {
        super(expression, new DRCrosstabRowGroup<T>());
    }

    /**
     * <p>setHeaderWidth.</p>
     *
     * @param headerWidth a {@link java.lang.Integer} object.
     * @return a {@link net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     */
    public CrosstabRowGroupBuilder<T> setHeaderWidth(Integer headerWidth) {
        getObject().setHeaderWidth(headerWidth);
        return this;
    }

    /**
     * <p>setTotalHeaderHeight.</p>
     *
     * @param totalHeaderHeight a {@link java.lang.Integer} object.
     * @return a {@link net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder} object.
     */
    public CrosstabRowGroupBuilder<T> setTotalHeaderHeight(Integer totalHeaderHeight) {
        getObject().setTotalHeaderHeight(totalHeaderHeight);
        return this;
    }
}
