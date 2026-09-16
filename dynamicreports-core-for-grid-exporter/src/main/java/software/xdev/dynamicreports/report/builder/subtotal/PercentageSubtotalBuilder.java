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

import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.ColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.builder.expression.PercentageExpression;
import software.xdev.dynamicreports.report.builder.group.GroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.PercentageTotalType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRReportException;


public class PercentageSubtotalBuilder extends BaseSubtotalBuilder<PercentageSubtotalBuilder, Double>
{

	private final DRIExpression<? extends Number> expression;
	private PercentageTotalType totalType;
	private DRGroup totalGroup;
	
	// column
	
	protected PercentageSubtotalBuilder(final ValueColumnBuilder<?, ? extends Number> column)
	{
		this(column.build(), column);
	}
	
	// field
	
	protected PercentageSubtotalBuilder(
		final FieldBuilder<? extends Number> field,
		final ColumnBuilder<?, ?> showInColumn)
	{
		this(field.getField(), showInColumn);
	}
	
	// expression
	
	protected PercentageSubtotalBuilder(
		final DRIExpression<? extends Number> expression,
		final ColumnBuilder<?, ?> showInColumn)
	{
		super(showInColumn);
		this.expression = expression;
	}
	
	public PercentageSubtotalBuilder setTotalType(final PercentageTotalType totalType)
	{
		this.totalType = totalType;
		return this;
	}
	
	public PercentageSubtotalBuilder setTotalGroup(final GroupBuilder<?> totalGroup)
	{
		if(totalGroup != null)
		{
			this.totalGroup = totalGroup.getGroup();
			this.setTotalType(PercentageTotalType.GROUP);
		}
		else
		{
			this.totalGroup = null;
		}
		return this;
	}
	
	@Override
	protected void configure()
	{
		if(this.getObject().getValueField().getDataType() == null)
		{
			this.getObject().getValueField().setDataType(DataTypes.percentageType());
		}
		
		final DRVariable<Number> actualExpression = new DRVariable<>(this.expression, Calculation.SUM);
		actualExpression.setResetType(Evaluation.GROUP);
		actualExpression.setResetGroup(this.getObject().getGroup());
		
		final DRVariable<Number> totalExpression = new DRVariable<>(this.expression, Calculation.SUM);
		if(this.totalType != null)
		{
			switch(this.totalType)
			{
				case REPORT:
					totalExpression.setResetType(Evaluation.REPORT);
					break;
				case GROUP:
					totalExpression.setResetType(Evaluation.GROUP);
					break;
				case FIRST_GROUP:
					totalExpression.setResetType(Evaluation.FIRST_GROUP);
					break;
				case LAST_GROUP:
					totalExpression.setResetType(Evaluation.LAST_GROUP);
					break;
				default:
					throw new DRReportException("Percentage total type " + this.totalType.name() + " not supported.");
			}
		}
		else
		{
			totalExpression.setResetType(Evaluation.BEFORE_GROUP);
			this.totalGroup = this.getObject().getGroup();
		}
		totalExpression.setResetGroup(this.totalGroup);
		
		this.setValueExpression(new PercentageExpression(actualExpression, totalExpression));
		
		super.configure();
	}
}
