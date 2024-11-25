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
package software.xdev.dynamicreports.report.definition;

import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public interface DRISubtotal<T> extends DRIExpression<T>, DRIValue<T>
{
	
	public DRIColumn<?> getShowInColumn();
	
	public DRITextField<T> getValueField();
	
	public DRIExpression<?> getLabelExpression();
	
	public DRIReportStyle getLabelStyle();
	
	public Position getLabelPosition();
	
	public Integer getLabelWidth();
	
	public ComponentDimensionType getLabelWidthType();
	
	public SubtotalPosition getPosition();
	
	public DRIGroup getGroup();
}
