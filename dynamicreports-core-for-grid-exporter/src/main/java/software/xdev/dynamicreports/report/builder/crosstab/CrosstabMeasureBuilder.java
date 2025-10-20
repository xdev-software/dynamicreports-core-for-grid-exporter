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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabMeasure;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.column.ValueColumnBuilder;
import software.xdev.dynamicreports.report.builder.datatype.DataTypes;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRICrosstabValue;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.exception.DRReportException;


@SuppressWarnings("deprecation")
public class CrosstabMeasureBuilder<T> extends AbstractBuilder<CrosstabMeasureBuilder<T>, DRCrosstabMeasure<T>>
	implements DRICrosstabValue<T>
{

	protected CrosstabMeasureBuilder(final ValueColumnBuilder<?, ?> column, final Calculation calculation)
	{
		super(new DRCrosstabMeasure<>(Crosstabs.variable(column, calculation).build()));
		if(calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT))
		{
			this.setDataType(DataTypes.longType());
		}
		else if(calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION)
			|| calculation.equals(Calculation.VARIANCE))
		{
			this.setDataType(DataTypes.doubleType());
		}
		else
		{
			final DRITextField<?> columnComponent = column.getColumn().getComponent();
			this.setDataType(columnComponent.getDataType());
			this.setPattern(columnComponent.getPattern());
		}
	}
	
	protected CrosstabMeasureBuilder(final FieldBuilder<?> field, final Calculation calculation)
	{
		super(new DRCrosstabMeasure<>(Crosstabs.variable(field, calculation).build()));
		if(calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT))
		{
			this.setDataType(DataTypes.longType());
		}
		else if(calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION)
			|| calculation.equals(Calculation.VARIANCE))
		{
			this.setDataType(DataTypes.doubleType());
		}
		else
		{
			this.setDataType(field.getField().getDataType());
		}
	}
	
	protected CrosstabMeasureBuilder(final DRIExpression<?> expression, final Calculation calculation)
	{
		super(new DRCrosstabMeasure<>(Crosstabs.variable(expression, calculation).build()));
		if(calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT))
		{
			this.setDataType(DataTypes.longType());
		}
		else if(calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION)
			|| calculation.equals(Calculation.VARIANCE))
		{
			this.setDataType(DataTypes.doubleType());
		}
		else if(expression instanceof DRIField)
		{
			this.setDataType(((DRIField<?>)expression).getDataType());
		}
	}
	
	protected CrosstabMeasureBuilder(final DRIExpression<?> expression)
	{
		super(new DRCrosstabMeasure<>(expression));
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public CrosstabMeasureBuilder<T> setDataType(final DRIDataType dataType)
	{
		this.getObject().setDataType(dataType);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setPercentageType(final CrosstabPercentageType percentageType)
	{
		if(this.getObject().getExpression() instanceof DRCrosstabVariable<?>)
		{
			final DRCrosstabVariable<?> variable = (DRCrosstabVariable<?>)this.getObject().getExpression();
			if(percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL)
				&& !variable.getCalculation().equals(Calculation.COUNT)
				&& !variable.getCalculation().equals(Calculation.DISTINCT_COUNT))
			{
				this.setDataType(DataTypes.doubleType());
			}
			variable.setPercentageType(percentageType);
		}
		else
		{
			throw new DRReportException("Percentage is not supported in this type of measure");
		}
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setPattern(final String pattern)
	{
		this.getObject().setPattern(pattern);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setValueFormatter(final DRIValueFormatter<?, ? super T> valueFormatter)
	{
		this.getObject().setValueFormatter(valueFormatter);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setTextAdjust(final TextAdjust textAdjust)
	{
		this.getObject().setTextAdjust(textAdjust);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setHyperLink(final HyperLinkBuilder hyperLink)
	{
		if(hyperLink != null)
		{
			this.getObject().setHyperLink(hyperLink.getHyperLink());
		}
		else
		{
			this.getObject().setHyperLink(null);
		}
		return this;
	}
	
	public CrosstabMeasureBuilder<T> addProperty(final DRIPropertyExpression propertyExpression)
	{
		this.getObject().addPropertyExpression(propertyExpression);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> addProperty(final String name, final DRIExpression<String> valueExpression)
	{
		this.getObject().addPropertyExpression(Expressions.property(name, valueExpression));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> addProperty(final String name, final String value)
	{
		this.getObject().addPropertyExpression(Expressions.property(name, value));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setStyle(final ReportStyleBuilder style)
	{
		Validate.notNull(style, "style must not be null");
		this.getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle()));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setStyle(
		final ReportStyleBuilder style,
		final CrosstabRowGroupBuilder<?> rowGroup)
	{
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		this.getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), null));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setStyle(
		final ReportStyleBuilder style,
		final CrosstabColumnGroupBuilder<?> columnGroup)
	{
		Validate.notNull(style, "style must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		this.getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), null, columnGroup.build()));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setStyle(
		final ReportStyleBuilder style,
		final CrosstabRowGroupBuilder<?> rowGroup,
		final CrosstabColumnGroupBuilder<?> columnGroup)
	{
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		this.getObject()
			.getStyles()
			.add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), columnGroup.build()));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setTitle(final DRIExpression<?> titleExpression)
	{
		this.getObject().setTitleExpression(titleExpression);
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setTitle(final String title)
	{
		this.getObject().setTitleExpression(Expressions.text(title));
		return this;
	}
	
	public CrosstabMeasureBuilder<T> setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		if(titleStyle != null)
		{
			this.getObject().setTitleStyle(titleStyle.getStyle());
		}
		else
		{
			this.getObject().setTitleStyle(null);
		}
		return this;
	}
	
	@Override
	public String getName()
	{
		return this.build().getExpression().getName();
	}
}
