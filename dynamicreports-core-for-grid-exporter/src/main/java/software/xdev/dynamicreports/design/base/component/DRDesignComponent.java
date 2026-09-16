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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.component.DRIDesignComponent;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;


public abstract class DRDesignComponent implements DRIDesignComponent
{

	private final String name;
	private String uniqueName;
	private DRDesignStyle style;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private DRIDesignExpression printWhenExpression;
	private boolean isRemoveLineWhenBlank;
	private List<DRIDesignPropertyExpression> propertyExpressions;
	private ComponentPositionType positionType;
	private StretchType stretchType;
	private boolean printInFirstWholeBand;
	private boolean printWhenDetailOverflows;
	private DRIDesignGroup printWhenGroupChanges;
	private DRDesignTableOfContentsHeading tableOfContentsHeading;
	
	protected DRDesignComponent(final String name)
	{
		this.name = name;
		this.uniqueName = name;
		this.init();
	}
	
	protected void init()
	{
		this.propertyExpressions = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getUniqueName()
	{
		return this.uniqueName;
	}
	
	public void setUniqueName(final String uniqueName)
	{
		this.uniqueName = uniqueName;
	}
	
	@Override
	public DRDesignStyle getStyle()
	{
		return this.style;
	}
	
	public void setStyle(final DRDesignStyle style)
	{
		this.style = style;
	}
	
	@Override
	public Integer getX()
	{
		return this.x;
	}
	
	public void setX(final Integer x)
	{
		this.x = x;
	}
	
	@Override
	public Integer getY()
	{
		return this.y;
	}
	
	public void setY(final Integer y)
	{
		this.y = y;
	}
	
	@Override
	public Integer getWidth()
	{
		return this.width;
	}
	
	public void setWidth(final Integer width)
	{
		this.width = width;
	}
	
	@Override
	public Integer getHeight()
	{
		return this.height;
	}
	
	public void setHeight(final Integer height)
	{
		this.height = height;
	}
	
	@Override
	public DRIDesignExpression getPrintWhenExpression()
	{
		return this.printWhenExpression;
	}
	
	public void setPrintWhenExpression(final DRIDesignExpression printWhenExpression)
	{
		this.printWhenExpression = printWhenExpression;
	}
	
	@Override
	public boolean isRemoveLineWhenBlank()
	{
		return this.isRemoveLineWhenBlank;
	}
	
	public void setRemoveLineWhenBlank(final boolean isRemoveLineWhenBlank)
	{
		this.isRemoveLineWhenBlank = isRemoveLineWhenBlank;
	}
	
	@Override
	public List<DRIDesignPropertyExpression> getPropertyExpressions()
	{
		return this.propertyExpressions;
	}
	
	public void setPropertyExpressions(final List<DRIDesignPropertyExpression> propertyExpressions)
	{
		this.propertyExpressions = propertyExpressions;
	}
	
	@Override
	public ComponentPositionType getPositionType()
	{
		return this.positionType;
	}
	
	public void setPositionType(final ComponentPositionType positionType)
	{
		this.positionType = positionType;
	}
	
	@Override
	public StretchType getStretchType()
	{
		return this.stretchType;
	}
	
	public void setStretchType(final StretchType stretchType)
	{
		this.stretchType = stretchType;
	}
	
	@Override
	public boolean isPrintInFirstWholeBand()
	{
		return this.printInFirstWholeBand;
	}
	
	public void setPrintInFirstWholeBand(final boolean printInFirstWholeBand)
	{
		this.printInFirstWholeBand = printInFirstWholeBand;
	}
	
	@Override
	public boolean isPrintWhenDetailOverflows()
	{
		return this.printWhenDetailOverflows;
	}
	
	public void setPrintWhenDetailOverflows(final boolean printWhenDetailOverflows)
	{
		this.printWhenDetailOverflows = printWhenDetailOverflows;
	}
	
	@Override
	public DRIDesignGroup getPrintWhenGroupChanges()
	{
		return this.printWhenGroupChanges;
	}
	
	public void setPrintWhenGroupChanges(final DRIDesignGroup printWhenGroupChanges)
	{
		this.printWhenGroupChanges = printWhenGroupChanges;
	}
	
	@Override
	public DRDesignTableOfContentsHeading getTableOfContentsHeading()
	{
		return this.tableOfContentsHeading;
	}
	
	public void setTableOfContentsHeading(final DRDesignTableOfContentsHeading tableOfContentsHeading)
	{
		this.tableOfContentsHeading = tableOfContentsHeading;
	}
}
