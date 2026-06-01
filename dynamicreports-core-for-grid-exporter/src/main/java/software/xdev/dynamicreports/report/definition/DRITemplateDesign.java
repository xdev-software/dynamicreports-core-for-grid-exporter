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

import java.io.Serializable;
import java.util.List;

import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.exception.DRException;


public interface DRITemplateDesign<T> extends Serializable
{
	
	public String getReportName();
	
	public List<DRIField<?>> getFields();
	
	public boolean isDefinedParameter(String name);
	
	public String getResourceBundleName();
	
	public Boolean getIgnorePagination();
	
	public WhenNoDataType getWhenNoDataType();
	
	public WhenResourceMissingType getWhenResourceMissingType();
	
	public Boolean getTitleOnANewPage();
	
	public Boolean getSummaryOnANewPage();
	
	public Boolean getSummaryWithPageHeaderAndFooter();
	
	public Boolean getFloatColumnFooter();
	
	public Integer getPageWidth();
	
	public Integer getPageHeight();
	
	public PageOrientation getPageOrientation();
	
	public DRIMargin getPageMargin();
	
	public Integer getPageColumnsPerPage();
	
	public Integer getPageColumnSpace();
	
	public Integer getPageColumnWidth();
	
	public int getTitleComponentsCount();
	
	public int getPageHeaderComponentsCount();
	
	public int getPageFooterComponentsCount();
	
	public int getColumnHeaderComponentsCount();
	
	public int getColumnFooterComponentsCount();
	
	public int getLastPageFooterComponentsCount();
	
	public int getSummaryComponentsCount();
	
	public int getNoDataComponentsCount();
	
	public int getBackgroundComponentsCount();
	
	public T getDesign() throws DRException;
}
