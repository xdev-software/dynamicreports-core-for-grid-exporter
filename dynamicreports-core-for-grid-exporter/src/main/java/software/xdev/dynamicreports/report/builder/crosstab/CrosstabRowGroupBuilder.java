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

import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabRowGroup;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CrosstabRowGroupBuilder<T>
	extends AbstractCrosstabGroupBuilder<CrosstabRowGroupBuilder<T>, DRCrosstabRowGroup<T>, T>
{

	protected CrosstabRowGroupBuilder(final ValueColumnBuilder<?, T> column)
	{
		super(column, new DRCrosstabRowGroup<>());
	}
	
	protected CrosstabRowGroupBuilder(final FieldBuilder<T> field)
	{
		super(field, new DRCrosstabRowGroup<>());
	}
	
	protected CrosstabRowGroupBuilder(final DRIExpression<T> expression)
	{
		super(expression, new DRCrosstabRowGroup<>());
	}
	
	public CrosstabRowGroupBuilder<T> setHeaderWidth(final Integer headerWidth)
	{
		this.getObject().setHeaderWidth(headerWidth);
		return this;
	}
	
	public CrosstabRowGroupBuilder<T> setTotalHeaderHeight(final Integer totalHeaderHeight)
	{
		this.getObject().setTotalHeaderHeight(totalHeaderHeight);
		return this;
	}
}
