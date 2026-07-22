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
package software.xdev.dynamicreports.report.base;

import java.util.List;

import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIField;
import software.xdev.dynamicreports.report.definition.DRIMargin;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.exception.DRException;


public abstract class AbstractTemplateDesign<T> implements DRITemplateDesign<T>
{

	@Override
	public String getReportName()
	{
		return null;
	}
	
	@Override
	public List<DRIField<?>> getFields()
	{
		return null;
	}
	
	@Override
	public boolean isDefinedParameter(final String name)
	{
		return false;
	}
	
	@Override
	public String getResourceBundleName()
	{
		return null;
	}
	
	@Override
	public Boolean getIgnorePagination()
	{
		return null;
	}
	
	@Override
	public WhenNoDataType getWhenNoDataType()
	{
		return null;
	}
	
	@Override
	public WhenResourceMissingType getWhenResourceMissingType()
	{
		return null;
	}
	
	@Override
	public Boolean getTitleOnANewPage()
	{
		return null;
	}
	
	@Override
	public Boolean getSummaryOnANewPage()
	{
		return null;
	}
	
	@Override
	public Boolean getSummaryWithPageHeaderAndFooter()
	{
		return null;
	}
	
	@Override
	public Boolean getFloatColumnFooter()
	{
		return null;
	}
	
	@Override
	public Integer getPageWidth()
	{
		return null;
	}
	
	@Override
	public Integer getPageHeight()
	{
		return null;
	}
	
	@Override
	public PageOrientation getPageOrientation()
	{
		return null;
	}
	
	@Override
	public DRIMargin getPageMargin()
	{
		return null;
	}
	
	@Override
	public Integer getPageColumnsPerPage()
	{
		return null;
	}
	
	@Override
	public Integer getPageColumnSpace()
	{
		return null;
	}
	
	@Override
	public Integer getPageColumnWidth()
	{
		return null;
	}
	
	@Override
	public int getTitleComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getPageHeaderComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getPageFooterComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getColumnHeaderComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getColumnFooterComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getLastPageFooterComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getSummaryComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getNoDataComponentsCount()
	{
		return 0;
	}
	
	@Override
	public int getBackgroundComponentsCount()
	{
		return 0;
	}
	
	@Override
	public T getDesign() throws DRException
	{
		return null;
	}
}
