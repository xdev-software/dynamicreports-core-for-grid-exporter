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
package software.xdev.dynamicreports.report.builder.subtotal;

import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CustomSubtotalBuilder<T> extends SubtotalBuilder<CustomSubtotalBuilder<T>, T> implements DRIValue<T>
{

	protected CustomSubtotalBuilder(final DRIExpression<T> expression, final ColumnBuilder<?, ?> showInColumn)
	{
		super(showInColumn);
		this.setValueExpression(expression);
	}
	
	@Override
	public String getName()
	{
		return this.getSubtotal().getName();
	}
}
