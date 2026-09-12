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
package software.xdev.dynamicreports.report.base.crosstab;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRCrosstabMeasure<T> implements DRICrosstabMeasure<T>
{

	private final String name;
	private final DRIExpression<?> expression;
	private DRIDataType<? super T, T> dataType;
	private String pattern;
	private HorizontalTextAlignment horizontalTextAlignment;
	private DRIValueFormatter<?, ? super T> valueFormatter;
	private TextAdjust textAdjust;
	private DRHyperLink hyperLink;
	private List<DRIPropertyExpression> propertyExpressions;
	private List<DRICrosstabCellStyle> styles;
	private DRIExpression<?> titleExpression;
	private DRIReportStyle titleStyle;
	
	public DRCrosstabMeasure(final DRIExpression<?> expression)
	{
		Validate.notNull(expression, "expression must not be null");
		this.expression = expression;
		this.name = ReportUtils.generateUniqueName("crosstabMeasure");
		this.styles = new ArrayList<>();
		this.propertyExpressions = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRIExpression<?> getExpression()
	{
		return this.expression;
	}
	
	@Override
	public DRIDataType<? super T, T> getDataType()
	{
		return this.dataType;
	}
	
	public void setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.dataType = dataType;
	}
	
	@Override
	public String getPattern()
	{
		return this.pattern;
	}
	
	public void setPattern(final String pattern)
	{
		this.pattern = pattern;
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return this.horizontalTextAlignment;
	}
	
	public void setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.horizontalTextAlignment = horizontalTextAlignment;
	}
	
	@Override
	public DRIValueFormatter<?, ? super T> getValueFormatter()
	{
		return this.valueFormatter;
	}
	
	public void setValueFormatter(final DRIValueFormatter<?, ? super T> valueFormatter)
	{
		this.valueFormatter = valueFormatter;
	}
	
	@Override
	public TextAdjust getTextAdjust()
	{
		return this.textAdjust;
	}
	
	public void setTextAdjust(final TextAdjust textAdjust)
	{
		this.textAdjust = textAdjust;
	}
	
	@Override
	public DRHyperLink getHyperLink()
	{
		return this.hyperLink;
	}
	
	public void setHyperLink(final DRHyperLink hyperLink)
	{
		this.hyperLink = hyperLink;
	}
	
	@Override
	public List<DRIPropertyExpression> getPropertyExpressions()
	{
		return this.propertyExpressions;
	}
	
	public void setPropertyExpressions(final List<DRIPropertyExpression> propertyExpressions)
	{
		this.propertyExpressions = propertyExpressions;
	}
	
	public void addPropertyExpression(final DRIPropertyExpression propertyExpression)
	{
		Validate.notNull(propertyExpression, "propertyExpression must not be null");
		this.propertyExpressions.add(propertyExpression);
	}
	
	@Override
	public List<DRICrosstabCellStyle> getStyles()
	{
		return this.styles;
	}
	
	public void setStyle(final List<DRICrosstabCellStyle> styles)
	{
		this.styles = styles;
	}
	
	@Override
	public DRIExpression<?> getTitleExpression()
	{
		return this.titleExpression;
	}
	
	public void setTitleExpression(final DRIExpression<?> titleExpression)
	{
		this.titleExpression = titleExpression;
	}
	
	@Override
	public DRIReportStyle getTitleStyle()
	{
		return this.titleStyle;
	}
	
	public void setTitleStyle(final DRIReportStyle titleStyle)
	{
		this.titleStyle = titleStyle;
	}
}
