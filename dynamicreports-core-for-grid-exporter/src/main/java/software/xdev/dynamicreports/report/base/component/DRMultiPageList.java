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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRIMultiPageList;


public class DRMultiPageList extends DRDimensionComponent implements DRIMultiPageList
{

	private List<DRIComponent> components;
	private SplitType splitType;
	
	public DRMultiPageList()
	{
		this.components = new ArrayList<>();
	}
	
	@Override
	public List<DRIComponent> getComponents()
	{
		return this.components;
	}
	
	public void setComponents(final List<DRIComponent> components)
	{
		this.components = components;
	}
	
	public void addComponent(final DRIComponent component)
	{
		this.components.add(component);
	}
	
	@Override
	public SplitType getSplitType()
	{
		return this.splitType;
	}
	
	public void setSplitType(final SplitType splitType)
	{
		this.splitType = splitType;
	}
}
