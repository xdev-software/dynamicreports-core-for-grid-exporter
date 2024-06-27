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
package software.xdev.dynamicreports.report.base.grid;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnTitleGroup;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRColumnTitleGroup implements DRIColumnTitleGroup
{

	private final DRColumnGridList list;
	
	private DRIExpression<?> titleExpression;
	private DRIReportStyle titleStyle;
	private Integer titleWidth;
	private ComponentDimensionType titleWidthType;
	private Integer titleColumns;
	private Integer titleHeight;
	private ComponentDimensionType titleHeightType;
	private Integer titleRows;
	private TextAdjust titleTextAdjust;
	private List<DRIPropertyExpression> titlePropertyExpressions;
	
	public DRColumnTitleGroup()
	{
		this.list = new DRColumnGridList(ListType.HORIZONTAL);
		this.titlePropertyExpressions = new ArrayList<>();
	}
	
	@Override
	public DRColumnGridList getList()
	{
		return this.list;
	}
	
	public void addComponent(final DRIColumnGridComponent component)
	{
		this.list.addComponent(component);
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
	
	@Override
	public Integer getTitleWidth()
	{
		return this.titleWidth;
	}
	
	public void setTitleWidth(final Integer titleWidth)
	{
		if(titleWidth != null)
		{
			Validate.isTrue(titleWidth >= 0, "titleWidth must be >= 0");
		}
		this.titleWidth = titleWidth;
	}
	
	@Override
	public ComponentDimensionType getTitleWidthType()
	{
		return this.titleWidthType;
	}
	
	public void setTitleWidthType(final ComponentDimensionType titleWidthType)
	{
		this.titleWidthType = titleWidthType;
	}
	
	@Override
	public Integer getTitleColumns()
	{
		return this.titleColumns;
	}
	
	public void setTitleColumns(final Integer titleColumns)
	{
		if(titleColumns != null)
		{
			Validate.isTrue(titleColumns >= 0, "titleColumns must be >= 0");
		}
		this.titleColumns = titleColumns;
	}
	
	@Override
	public Integer getTitleHeight()
	{
		return this.titleHeight;
	}
	
	public void setTitleHeight(final Integer titleHeight)
	{
		if(titleHeight != null)
		{
			Validate.isTrue(titleHeight >= 0, "titleHeight must be >= 0");
		}
		this.titleHeight = titleHeight;
	}
	
	@Override
	public ComponentDimensionType getTitleHeightType()
	{
		return this.titleHeightType;
	}
	
	public void setTitleHeightType(final ComponentDimensionType titleHeightType)
	{
		this.titleHeightType = titleHeightType;
	}
	
	@Override
	public Integer getTitleRows()
	{
		return this.titleRows;
	}
	
	public void setTitleRows(final Integer titleRows)
	{
		if(titleRows != null)
		{
			Validate.isTrue(titleRows >= 0, "titleRows must be >= 0");
		}
		this.titleRows = titleRows;
	}
	
	@Override
	public TextAdjust getTitleTextAdjust()
	{
		return this.titleTextAdjust;
	}
	
	public void setTitleTextAdjust(final TextAdjust textAdjust)
	{
		this.titleTextAdjust = textAdjust;
	}
	
	@Override
	public List<DRIPropertyExpression> getTitlePropertyExpressions()
	{
		return this.titlePropertyExpressions;
	}
	
	public void setTitlePropertyExpressions(final List<DRIPropertyExpression> titlePropertyExpressions)
	{
		this.titlePropertyExpressions = titlePropertyExpressions;
	}
	
	public void addTitlePropertyExpression(final DRIPropertyExpression propertyExpression)
	{
		Validate.notNull(propertyExpression, "propertyExpression must not be null");
		this.titlePropertyExpressions.add(propertyExpression);
	}
}
