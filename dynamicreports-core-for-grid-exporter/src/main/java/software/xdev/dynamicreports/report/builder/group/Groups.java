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
package software.xdev.dynamicreports.report.builder.group;

import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * A set of methods of creating report groups
 *
 * @author Ricardo Mariaca
 * 
 */
public class Groups {

    // column

    /**
     * <p>group.</p>
     *
     * @param groupColumn a {@link software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder} object.
     */
    public static ColumnGroupBuilder group(ValueColumnBuilder<?, ?> groupColumn) {
        return new ColumnGroupBuilder(groupColumn);
    }

    /**
     * <p>group.</p>
     *
     * @param name        a {@link java.lang.String} object.
     * @param groupColumn a {@link software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder} object.
     */
    public static ColumnGroupBuilder group(String name, ValueColumnBuilder<?, ?> groupColumn) {
        return new ColumnGroupBuilder(name, groupColumn);
    }

    // custom

    /**
     * <p>group.</p>
     *
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(String fieldName, Class<?> valueClass) {
        return group(DynamicReports.field(fieldName, valueClass));
    }

    /**
     * <p>group.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param fieldName  a {@link java.lang.String} object.
     * @param valueClass a {@link java.lang.Class} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(String name, String fieldName, Class<?> valueClass) {
        return group(name, DynamicReports.field(fieldName, valueClass));
    }

    /**
     * <p>group.</p>
     *
     * @param field a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(FieldBuilder<?> field) {
        return new CustomGroupBuilder(field);
    }

    /**
     * <p>group.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param field a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(String name, FieldBuilder<?> field) {
        return new CustomGroupBuilder(name, field);
    }

    /**
     * <p>group.</p>
     *
     * @param expression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(DRIExpression<?> expression) {
        return new CustomGroupBuilder(expression);
    }

    /**
     * <p>group.</p>
     *
     * @param name       a {@link java.lang.String} object.
     * @param expression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder} object.
     */
    public static CustomGroupBuilder group(String name, DRIExpression<?> expression) {
        return new CustomGroupBuilder(name, expression);
    }
}
