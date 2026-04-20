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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.component.DRIXyList;


public class DRXyList extends DRDimensionComponent implements DRIXyList
{

	private List<DRXyListCell> xyListCells;
	
	@Override
	protected void init()
	{
		super.init();
		this.xyListCells = new ArrayList<>();
	}
	
	@Override
	public List<DRXyListCell> getXyListCells()
	{
		return this.xyListCells;
	}
	
	public void addCell(final DRXyListCell cell)
	{
		Validate.notNull(cell, "cell must not be null");
		this.xyListCells.add(cell);
	}
	
	public void addComponent(final Integer x, final Integer y, final DRComponent component)
	{
		this.xyListCells.add(new DRXyListCell(x, y, component));
	}
}
