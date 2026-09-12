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
package software.xdev.dynamicreports.report.base.component;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


public class DRTextField<T> extends DRHyperLinkComponent implements DRITextField<T>
{

	private DRIExpression<T> valueExpression;
	private String pattern;
	private DRIExpression<String> patternExpression;
	private HorizontalTextAlignment horizontalTextAlignment;
	private DRIValueFormatter<?, ? super T> valueFormatter;
	private DRIDataType<? super T, T> dataType;
	private Integer columns;
	private Integer rows;
	private Evaluation evaluationTime;
	private DRGroup evaluationGroup;
	private Markup markup;
	private Boolean printRepeatedValues;
	private TextAdjust textAdjust;
	
	@Override
	public DRIExpression<T> getValueExpression()
	{
		return this.valueExpression;
	}
	
	public void setValueExpression(final DRIExpression<T> valueExpression)
	{
		Validate.notNull(valueExpression, "valueExpression must not be null");
		this.valueExpression = valueExpression;
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
	public DRIExpression<String> getPatternExpression()
	{
		return this.patternExpression;
	}
	
	public void setPatternExpression(final DRIExpression<String> patternExpression)
	{
		this.patternExpression = patternExpression;
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
	public DRIDataType<? super T, T> getDataType()
	{
		return this.dataType;
	}
	
	public void setDataType(final DRIDataType<? super T, T> dataType)
	{
		this.dataType = dataType;
	}
	
	@Override
	public Integer getColumns()
	{
		return this.columns;
	}
	
	public void setColumns(final Integer columns)
	{
		if(columns != null)
		{
			Validate.isTrue(columns >= 0, "columns must be >= 0");
		}
		this.columns = columns;
	}
	
	@Override
	public Integer getRows()
	{
		return this.rows;
	}
	
	public void setRows(final Integer rows)
	{
		if(rows != null)
		{
			Validate.isTrue(rows >= 0, "rows must be >= 0");
		}
		this.rows = rows;
	}
	
	@Override
	public Evaluation getEvaluationTime()
	{
		return this.evaluationTime;
	}
	
	public void setEvaluationTime(final Evaluation evaluationTime)
	{
		this.evaluationTime = evaluationTime;
	}
	
	@Override
	public DRGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
	
	public void setEvaluationGroup(final DRGroup evaluationGroup)
	{
		this.evaluationGroup = evaluationGroup;
	}
	
	@Override
	public Markup getMarkup()
	{
		return this.markup;
	}
	
	public void setMarkup(final Markup markup)
	{
		this.markup = markup;
	}
	
	@Override
	public Boolean getPrintRepeatedValues()
	{
		return this.printRepeatedValues;
	}
	
	public void setPrintRepeatedValues(final Boolean printRepeatedValues)
	{
		this.printRepeatedValues = printRepeatedValues;
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
}
