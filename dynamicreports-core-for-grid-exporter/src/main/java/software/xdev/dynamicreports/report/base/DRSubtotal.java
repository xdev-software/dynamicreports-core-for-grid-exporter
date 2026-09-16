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
package software.xdev.dynamicreports.report.base;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.column.DRColumn;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.definition.DRISubtotal;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRSubtotal<T> implements DRISubtotal<T>
{

	private DRColumn<?> showInColumn;
	private DRTextField<T> valueField;
	private SubtotalPosition position;
	private DRGroup group;
	
	private DRIExpression<?> labelExpression;
	private DRIReportStyle labelStyle;
	private Position labelPosition;
	private Integer labelWidth;
	private ComponentDimensionType labelWidthType;
	
	public DRSubtotal(final DRColumn<?> showInColumn)
	{
		this.setShowInColumn(showInColumn);
		this.init();
	}
	
	private void init()
	{
		this.valueField = new DRTextField<>();
	}
	
	@Override
	public DRColumn<?> getShowInColumn()
	{
		return this.showInColumn;
	}
	
	public void setShowInColumn(final DRColumn<?> showInColumn)
	{
		Validate.notNull(showInColumn, "showInColumn must not be null");
		this.showInColumn = showInColumn;
	}
	
	@Override
	public DRTextField<T> getValueField()
	{
		return this.valueField;
	}
	
	@Override
	public DRIExpression<?> getLabelExpression()
	{
		return this.labelExpression;
	}
	
	public void setLabelExpression(final DRIExpression<?> labelExpression)
	{
		this.labelExpression = labelExpression;
	}
	
	@Override
	public DRIReportStyle getLabelStyle()
	{
		return this.labelStyle;
	}
	
	public void setLabelStyle(final DRIReportStyle labelStyle)
	{
		this.labelStyle = labelStyle;
	}
	
	@Override
	public Position getLabelPosition()
	{
		return this.labelPosition;
	}
	
	public void setLabelPosition(final Position labelPosition)
	{
		this.labelPosition = labelPosition;
	}
	
	@Override
	public Integer getLabelWidth()
	{
		return this.labelWidth;
	}
	
	public void setLabelWidth(final Integer labelWidth)
	{
		this.labelWidth = labelWidth;
	}
	
	@Override
	public ComponentDimensionType getLabelWidthType()
	{
		return this.labelWidthType;
	}
	
	public void setLabelWidthType(final ComponentDimensionType labelWidthType)
	{
		this.labelWidthType = labelWidthType;
	}
	
	@Override
	public SubtotalPosition getPosition()
	{
		return this.position;
	}
	
	public void setPosition(final SubtotalPosition position)
	{
		Validate.notNull(position, "position must not be null");
		this.position = position;
	}
	
	@Override
	public DRGroup getGroup()
	{
		return this.group;
	}
	
	public void setGroup(final DRGroup group)
	{
		this.group = group;
	}
	
	@Override
	public String getName()
	{
		return this.valueField.getValueExpression().getName();
	}
	
	@Override
	public Class<? super T> getValueClass()
	{
		return this.valueField.getValueExpression().getValueClass();
	}
}
