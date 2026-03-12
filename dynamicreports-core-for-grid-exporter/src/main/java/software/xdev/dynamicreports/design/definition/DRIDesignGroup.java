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
package software.xdev.dynamicreports.design.definition;

import java.io.Serializable;
import java.util.List;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;


public interface DRIDesignGroup extends Serializable
{
	
	public String getName();
	
	public DRIDesignExpression getGroupExpression();
	
	public List<? extends DRIDesignBand> getHeaderBands();
	
	public List<? extends DRIDesignBand> getFooterBands();
	
	public boolean isStartInNewPage();
	
	public boolean isStartInNewColumn();
	
	public boolean isReprintHeaderOnEachPage();
	
	public boolean isResetPageNumber();
	
	public Integer getMinHeightToStartNewPage();
	
	public GroupFooterPosition getFooterPosition();
	
	public boolean isKeepTogether();
	
	public boolean isHeaderWithSubtotal();
}
