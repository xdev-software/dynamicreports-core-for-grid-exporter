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
package net.sf.dynamicreports.report.builder.group;

import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * A set of methods of creating report groups
 *
 * @author Ricardo Mariaca
 * 
 */
public class GroupBuilders {

    // column

    /**
     * <p>group.</p>
     *
     * @param groupColumn a {@link net.sf.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder} object.
     */
    public ColumnGroupBuilder group(ValueColumnBuilder<?, ?> groupColumn) {
        return Groups.group(groupColumn);
    }

    /**
     * <p>group.</p>
     *
     * @param name        a {@link java.lang.String} object.
     * @param groupColumn a {@link net.sf.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder} object.
     */
    public ColumnGroupBuilder group(String name, ValueColumnBuilder<?, ?> groupColumn) {
        return Groups.group(name, groupColumn);
    }

    // custom

    /**
     * <p>group.</p>
     *
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(String fieldName, Class<?> valueClass) {
        return Groups.group(fieldName, valueClass);
    }

    /**
     * <p>group.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(String name, String fieldName, Class<?> valueClass) {
        return Groups.group(name, fieldName, valueClass);
    }

    /**
     * <p>group.</p>
     *
     * @param field a {@link net.sf.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(FieldBuilder<?> field) {
        return Groups.group(field);
    }

    /**
     * <p>group.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param field a {@link net.sf.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(String name, FieldBuilder<?> field) {
        return Groups.group(name, field);
    }

    /**
     * <p>group.</p>
     *
     * @param expression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(DRIExpression<?> expression) {
        return Groups.group(expression);
    }

    /**
     * <p>group.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param expression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link net.sf.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public CustomGroupBuilder group(String name, DRIExpression<?> expression) {
        return Groups.group(name, expression);
    }
}
