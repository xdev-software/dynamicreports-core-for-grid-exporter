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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.component.DRIDesignTextField;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;


public class DRDesignTextField extends DRDesignHyperlinkComponent implements DRIDesignTextField
{

	private String pattern;
	private DRIDesignExpression patternExpression;
	private HorizontalTextAlignment horizontalTextAlignment;
	private DRIDesignExpression valueExpression;
	private boolean printRepeatedValues;
	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;
	private Markup markup;
	private TextAdjust textAdjust;
	
	public DRDesignTextField()
	{
		super("textField");
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
	public DRIDesignExpression getPatternExpression()
	{
		return this.patternExpression;
	}
	
	public void setPatternExpression(final DRIDesignExpression patternExpression)
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
	public DRIDesignExpression getValueExpression()
	{
		return this.valueExpression;
	}
	
	public void setValueExpression(final DRIDesignExpression valueExpression)
	{
		this.valueExpression = valueExpression;
	}
	
	@Override
	public boolean isPrintRepeatedValues()
	{
		return this.printRepeatedValues;
	}
	
	public void setPrintRepeatedValues(final boolean printRepeatedValues)
	{
		this.printRepeatedValues = printRepeatedValues;
	}
	
	@Override
	public EvaluationTime getEvaluationTime()
	{
		return this.evaluationTime;
	}
	
	public void setEvaluationTime(final EvaluationTime evaluationTime)
	{
		this.evaluationTime = evaluationTime;
	}
	
	@Override
	public DRDesignGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
	
	public void setEvaluationGroup(final DRDesignGroup evaluationGroup)
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
	public TextAdjust getTextAdjust()
	{
		return this.textAdjust;
	}
	
	public void setTextAdjust(final TextAdjust textAdjust)
	{
		this.textAdjust = textAdjust;
	}
}
