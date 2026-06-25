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

import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.definition.DRICrosstabValue;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class CrosstabVariableBuilder<T> extends AbstractBuilder<CrosstabVariableBuilder<T>, DRCrosstabVariable<T>>
	implements DRICrosstabValue<T>
{

	protected CrosstabVariableBuilder(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		super(new DRCrosstabVariable<>(column.build(), calculation));
	}
	
	protected CrosstabVariableBuilder(final FieldBuilder<?> field, final Calculation calculation)
	{
		super(new DRCrosstabVariable<>(field.getField(), calculation));
	}
	
	protected CrosstabVariableBuilder(final DRIExpression<?> expression, final Calculation calculation)
	{
		super(new DRCrosstabVariable<>(expression, calculation));
	}
	
	public CrosstabVariableBuilder<T> setPercentageType(final CrosstabPercentageType percentageType)
	{
		this.getObject().setPercentageType(percentageType);
		return this;
	}
	
	@Override
	public String getName()
	{
		return this.getObject().getName();
	}
}
