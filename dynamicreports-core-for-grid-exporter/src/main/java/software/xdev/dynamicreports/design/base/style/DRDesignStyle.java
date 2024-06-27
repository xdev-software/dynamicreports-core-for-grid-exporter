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
package software.xdev.dynamicreports.design.base.style;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;

import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.report.ReportUtils;


public class DRDesignStyle extends DRDesignBaseStyle implements DRIDesignStyle
{

	private final String name;
	private DRDesignStyle parentStyle;
	private List<DRDesignConditionalStyle> conditionalStyles;
	
	public DRDesignStyle()
	{
		this(ReportUtils.generateUniqueName("style"));
	}
	
	public DRDesignStyle(final String name)
	{
		this.name = Validate.notBlank(name);
		this.conditionalStyles = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRDesignStyle getParentStyle()
	{
		return this.parentStyle;
	}
	
	public void setParentStyle(final DRDesignStyle parentStyle)
	{
		this.parentStyle = parentStyle;
	}
	
	@Override
	public List<DRDesignConditionalStyle> getConditionalStyles()
	{
		return this.conditionalStyles;
	}
	
	public void setConditionalStyles(final List<DRDesignConditionalStyle> conditionalStyles)
	{
		this.conditionalStyles = conditionalStyles;
	}
	
	public void addConditionalStyle(final DRDesignConditionalStyle conditionalStyle)
	{
		this.conditionalStyles.add(conditionalStyle);
	}
	
	@SuppressWarnings("checkstyle:EqualsHashCode")
	@Override
	public boolean equals(final Object obj)
	{
		final EqualsBuilder equalsBuilder = new EqualsBuilder().appendSuper(super.equals(obj));
		if(equalsBuilder.isEquals())
		{
			final DRDesignStyle o = (DRDesignStyle)obj;
			if(!(this.parentStyle == null
				? o.getParentStyle() == null
				: this.parentStyle.getName().equals(o.getParentStyle().getName())))
			{
				return false;
			}
			equalsBuilder.append(this.name, o.name).append(this.conditionalStyles, o.conditionalStyles);
		}
		return equalsBuilder.isEquals();
	}
}
