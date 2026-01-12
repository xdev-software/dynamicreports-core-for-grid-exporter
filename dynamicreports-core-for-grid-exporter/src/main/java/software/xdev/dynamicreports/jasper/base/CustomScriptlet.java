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
package software.xdev.dynamicreports.jasper.base;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;


public class CustomScriptlet extends JRAbstractScriptlet
{
	private final DRIScriptlet scriptlet;
	private JasperReportParameters reportParameters;
	
	public CustomScriptlet(final DRIScriptlet scriptlet)
	{
		this.scriptlet = scriptlet;
	}
	
	@Override
	public void afterColumnInit() throws JRScriptletException
	{
		this.scriptlet.afterColumnInit(this.getReportParameters());
	}
	
	@Override
	public void afterDetailEval() throws JRScriptletException
	{
		this.scriptlet.afterDetailEval(this.getReportParameters());
	}
	
	@Override
	public void afterGroupInit(final String groupName) throws JRScriptletException
	{
		this.scriptlet.afterGroupInit(groupName, this.getReportParameters());
	}
	
	@Override
	public void afterPageInit() throws JRScriptletException
	{
		this.scriptlet.afterPageInit(this.getReportParameters());
	}
	
	@Override
	public void afterReportInit() throws JRScriptletException
	{
		this.scriptlet.afterReportInit(this.getReportParameters());
	}
	
	@Override
	public void beforeColumnInit() throws JRScriptletException
	{
		this.scriptlet.beforeColumnInit(this.getReportParameters());
	}
	
	@Override
	public void beforeDetailEval() throws JRScriptletException
	{
		this.scriptlet.beforeDetailEval(this.getReportParameters());
	}
	
	@Override
	public void beforeGroupInit(final String groupName) throws JRScriptletException
	{
		this.scriptlet.beforeGroupInit(groupName, this.getReportParameters());
	}
	
	@Override
	public void beforePageInit() throws JRScriptletException
	{
		this.scriptlet.beforePageInit(this.getReportParameters());
	}
	
	@Override
	public void beforeReportInit() throws JRScriptletException
	{
		this.scriptlet.beforeReportInit(this.getReportParameters());
	}
	
	private JasperReportParameters getReportParameters()
	{
		if(this.reportParameters == null)
		{
			try
			{
				this.reportParameters =
					((JasperScriptlet)this.getParameterValue(JasperScriptlet.SCRIPTLET_NAME)).getReportParameters();
			}
			catch(final JRScriptletException e)
			{
				// Undocumented upstream
			}
		}
		return this.reportParameters;
	}
	
	protected DRIScriptlet getScriptlet()
	{
		return this.scriptlet;
	}
}
