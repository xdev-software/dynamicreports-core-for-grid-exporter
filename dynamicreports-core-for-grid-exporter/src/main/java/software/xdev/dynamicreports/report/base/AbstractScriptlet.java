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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public abstract class AbstractScriptlet implements DRIScriptlet
{
	private final String name;
	
	public AbstractScriptlet()
	{
		this.name = ReportUtils.generateUniqueName("scriptlet");
	}
	
	public AbstractScriptlet(final String name)
	{
		Validate.notEmpty(name, "name must not be empty");
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void afterColumnInit(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void afterDetailEval(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void afterGroupInit(final String groupName, final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void afterPageInit(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void afterReportInit(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void beforeColumnInit(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void beforeDetailEval(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void beforeGroupInit(final String groupName, final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void beforePageInit(final ReportParameters reportParameters)
	{
	}
	
	@Override
	public void beforeReportInit(final ReportParameters reportParameters)
	{
	}
}
