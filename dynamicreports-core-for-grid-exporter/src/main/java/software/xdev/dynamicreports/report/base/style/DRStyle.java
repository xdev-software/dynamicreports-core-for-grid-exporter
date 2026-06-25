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
package software.xdev.dynamicreports.report.base.style;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;


public class DRStyle extends DRBaseStyle implements DRIStyle
{

	private String name;
	private DRIReportStyle parentStyle;
	private List<DRConditionalStyle> conditionalStyles;
	
	@Override
	protected void init()
	{
		super.init();
		this.conditionalStyles = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void setName(final String name)
	{
		this.name = name;
	}
	
	@Override
	public DRIReportStyle getParentStyle()
	{
		return this.parentStyle;
	}
	
	public void setParentStyle(final DRIReportStyle parentStyle)
	{
		this.parentStyle = parentStyle;
	}
	
	@Override
	public List<DRConditionalStyle> getConditionalStyles()
	{
		return this.conditionalStyles;
	}
	
	public void setConditionalStyles(final List<DRConditionalStyle> conditionalStyles)
	{
		Validate.notNull(conditionalStyles, "conditionalStyles must not be null");
		Validate.noNullElements(conditionalStyles, "conditionalStyles must not contains null conditionalStyle");
		this.conditionalStyles = conditionalStyles;
	}
	
	public void addConditionalStyle(final DRConditionalStyle conditionalStyle)
	{
		Validate.notNull(conditionalStyle, "conditionalStyle must not be null");
		this.conditionalStyles.add(conditionalStyle);
	}
}
