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
package software.xdev.dynamicreports.design.definition.component;

import java.io.Serializable;
import java.util.List;

import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignPropertyExpression;
import software.xdev.dynamicreports.design.definition.style.DRIDesignStyle;
import software.xdev.dynamicreports.report.constant.ComponentPositionType;
import software.xdev.dynamicreports.report.constant.StretchType;


public interface DRIDesignComponent extends Serializable
{
	public String getName();
	
	public String getUniqueName();
	
	public DRIDesignStyle getStyle();
	
	public Integer getX();
	
	public Integer getY();
	
	public Integer getWidth();
	
	public Integer getHeight();
	
	public DRIDesignExpression getPrintWhenExpression();
	
	public boolean isRemoveLineWhenBlank();
	
	public List<DRIDesignPropertyExpression> getPropertyExpressions();
	
	public ComponentPositionType getPositionType();
	
	public StretchType getStretchType();
	
	public boolean isPrintInFirstWholeBand();
	
	public boolean isPrintWhenDetailOverflows();
	
	public DRIDesignGroup getPrintWhenGroupChanges();
	
	public DRIDesignTableOfContentsHeading getTableOfContentsHeading();
}
