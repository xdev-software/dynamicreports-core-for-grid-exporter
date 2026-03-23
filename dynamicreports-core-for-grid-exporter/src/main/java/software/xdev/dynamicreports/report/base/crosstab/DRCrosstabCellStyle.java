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

import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRCrosstabCellStyle implements DRICrosstabCellStyle
{

	private DRICrosstabRowGroup<?> rowGroup;
	private DRICrosstabColumnGroup<?> columnGroup;
	private DRIReportStyle style;
	
	public DRCrosstabCellStyle(final DRIReportStyle style)
	{
		this(style, null, null);
	}
	
	public DRCrosstabCellStyle(
		final DRIReportStyle style,
		final DRICrosstabRowGroup<?> rowGroup,
		final DRICrosstabColumnGroup<?> columnGroup)
	{
		this.style = style;
		this.rowGroup = rowGroup;
		this.columnGroup = columnGroup;
	}
	
	@Override
	public DRICrosstabRowGroup<?> getRowGroup()
	{
		return this.rowGroup;
	}
	
	public void setRowGroup(final DRICrosstabRowGroup<?> rowGroup)
	{
		this.rowGroup = rowGroup;
	}
	
	@Override
	public DRICrosstabColumnGroup<?> getColumnGroup()
	{
		return this.columnGroup;
	}
	
	public void setColumnGroup(final DRICrosstabColumnGroup<?> columnGroup)
	{
		this.columnGroup = columnGroup;
	}
	
	@Override
	public DRIReportStyle getStyle()
	{
		return this.style;
	}
	
	public void setStyle(final DRIReportStyle style)
	{
		this.style = style;
	}
}
