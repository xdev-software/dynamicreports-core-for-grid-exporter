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
package software.xdev.dynamicreports.report.definition.component;

import software.xdev.dynamicreports.report.constant.ComponentDimensionType;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.definition.DRIGroup;


public interface DRIDimensionComponent extends DRIComponent
{
	
	public Integer getWidth();
	
	public Integer getHeight();
	
	public ComponentDimensionType getWidthType();
	
	public ComponentDimensionType getHeightType();
	
	public ComponentPositionType getPositionType();
	
	public StretchType getStretchType();
	
	public Boolean getPrintInFirstWholeBand();
	
	public Boolean getPrintWhenDetailOverflows();
	
	public DRIGroup getPrintWhenGroupChanges();
}
