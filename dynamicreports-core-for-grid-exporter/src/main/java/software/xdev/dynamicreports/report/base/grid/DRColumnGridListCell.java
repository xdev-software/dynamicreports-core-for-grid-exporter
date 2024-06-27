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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridComponent;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGridListCell;


public class DRColumnGridListCell implements DRIColumnGridListCell
{

	private HorizontalCellComponentAlignment horizontalAlignment;
	private VerticalCellComponentAlignment verticalAlignment;
	private final DRIColumnGridComponent component;
	
	public DRColumnGridListCell(final DRIColumnGridComponent component)
	{
		Validate.notNull(component, "component must not be null");
		this.component = component;
	}
	
	public DRColumnGridListCell(
		final HorizontalCellComponentAlignment horizontalAlignment,
		final VerticalCellComponentAlignment verticalAlignment,
		final DRIColumnGridComponent component)
	{
		this(component);
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
	}
	
	@Override
	public HorizontalCellComponentAlignment getHorizontalAlignment()
	{
		return this.horizontalAlignment;
	}
	
	public void setHorizontalAlignment(final HorizontalCellComponentAlignment horizontalAlignment)
	{
		this.horizontalAlignment = horizontalAlignment;
	}
	
	@Override
	public VerticalCellComponentAlignment getVerticalAlignment()
	{
		return this.verticalAlignment;
	}
	
	public void setVerticalAlignment(final VerticalCellComponentAlignment verticalAlignment)
	{
		this.verticalAlignment = verticalAlignment;
	}
	
	@Override
	public DRIColumnGridComponent getComponent()
	{
		return this.component;
	}
}
