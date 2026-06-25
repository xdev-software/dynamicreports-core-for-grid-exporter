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


public final class Groups
{
	private Groups()
	{
	}
	
	// column
	
	public static ColumnGroupBuilder group(final ValueColumnBuilder<?, ?> groupColumn)
	{
		return new ColumnGroupBuilder(groupColumn);
	}
	
	public static ColumnGroupBuilder group(final String name, final ValueColumnBuilder<?, ?> groupColumn)
	{
		return new ColumnGroupBuilder(name, groupColumn);
	}
	
	// custom
	
	public static CustomGroupBuilder group(final String fieldName, final Class<?> valueClass)
	{
		return group(DynamicReports.field(fieldName, valueClass));
	}
	
	public static CustomGroupBuilder group(final String name, final String fieldName, final Class<?> valueClass)
	{
		return group(name, DynamicReports.field(fieldName, valueClass));
	}
	
	public static CustomGroupBuilder group(final FieldBuilder<?> field)
	{
		return new CustomGroupBuilder(field);
	}
	
	public static CustomGroupBuilder group(final String name, final FieldBuilder<?> field)
	{
		return new CustomGroupBuilder(name, field);
	}
	
	public static CustomGroupBuilder group(final DRIExpression<?> expression)
	{
		return new CustomGroupBuilder(expression);
	}
	
	public static CustomGroupBuilder group(final String name, final DRIExpression<?> expression)
	{
		return new CustomGroupBuilder(name, expression);
	}
}
