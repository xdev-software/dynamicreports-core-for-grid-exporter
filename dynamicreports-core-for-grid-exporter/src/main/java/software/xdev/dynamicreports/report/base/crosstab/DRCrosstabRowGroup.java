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
package software.xdev.dynamicreports.report.base.crosstab;

import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;


public class DRCrosstabRowGroup<T> extends DRCrosstabGroup<T> implements DRICrosstabRowGroup<T>
{

	private Integer headerWidth;
	private Integer totalHeaderHeight;
	
	@Override
	public Integer getHeaderWidth()
	{
		return this.headerWidth;
	}
	
	public void setHeaderWidth(final Integer headerWidth)
	{
		this.headerWidth = headerWidth;
	}
	
	@Override
	public Integer getTotalHeaderHeight()
	{
		return this.totalHeaderHeight;
	}
	
	public void setTotalHeaderHeight(final Integer totalHeaderHeight)
	{
		this.totalHeaderHeight = totalHeaderHeight;
	}
}
