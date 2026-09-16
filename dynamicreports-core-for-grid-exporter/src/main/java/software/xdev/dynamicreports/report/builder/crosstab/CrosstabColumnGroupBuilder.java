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
package software.xdev.dynamicreports.report.builder.crosstab;

import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabColumnGroup;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CrosstabColumnGroupBuilder<T>
	extends AbstractCrosstabGroupBuilder<CrosstabColumnGroupBuilder<T>, DRCrosstabColumnGroup<T>, T>
{

	protected CrosstabColumnGroupBuilder(final ValueColumnBuilder<?, T> column)
	{
		super(column, new DRCrosstabColumnGroup<>());
	}
	
	protected CrosstabColumnGroupBuilder(final FieldBuilder<T> field)
	{
		super(field, new DRCrosstabColumnGroup<>());
	}
	
	protected CrosstabColumnGroupBuilder(final DRIExpression<T> expression)
	{
		super(expression, new DRCrosstabColumnGroup<>());
	}
	
	public CrosstabColumnGroupBuilder<T> setHeaderHeight(final Integer headerHeight)
	{
		this.getObject().setHeaderHeight(headerHeight);
		return this;
	}
	
	public CrosstabColumnGroupBuilder<T> setTotalHeaderWidth(final Integer totalHeaderWidth)
	{
		this.getObject().setTotalHeaderWidth(totalHeaderWidth);
		return this;
	}
}
