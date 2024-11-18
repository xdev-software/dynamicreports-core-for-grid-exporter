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
package software.xdev.dynamicreports.design.base;

import software.xdev.dynamicreports.design.definition.DRIDesignTemplateDesign;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.exception.DRException;


public class DRDesignTemplateDesign implements DRIDesignTemplateDesign
{

	private final DRITemplateDesign<?> templateDesign;
	
	public DRDesignTemplateDesign(final DRITemplateDesign<?> templateDesign)
	{
		this.templateDesign = templateDesign;
	}
	
	@Override
	public int getTitleComponentsCount()
	{
		return this.templateDesign.getTitleComponentsCount();
	}
	
	@Override
	public int getPageHeaderComponentsCount()
	{
		return this.templateDesign.getPageHeaderComponentsCount();
	}
	
	@Override
	public int getPageFooterComponentsCount()
	{
		return this.templateDesign.getPageFooterComponentsCount();
	}
	
	@Override
	public int getColumnHeaderComponentsCount()
	{
		return this.templateDesign.getColumnHeaderComponentsCount();
	}
	
	@Override
	public int getColumnFooterComponentsCount()
	{
		return this.templateDesign.getColumnFooterComponentsCount();
	}
	
	@Override
	public int getLastPageFooterComponentsCount()
	{
		return this.templateDesign.getLastPageFooterComponentsCount();
	}
	
	@Override
	public int getSummaryComponentsCount()
	{
		return this.templateDesign.getSummaryComponentsCount();
	}
	
	@Override
	public int getNoDataComponentsCount()
	{
		return this.templateDesign.getNoDataComponentsCount();
	}
	
	@Override
	public int getBackgroundComponentsCount()
	{
		return this.templateDesign.getBackgroundComponentsCount();
	}
	
	@Override
	public Object getDesign() throws DRException
	{
		return this.templateDesign.getDesign();
	}
}
