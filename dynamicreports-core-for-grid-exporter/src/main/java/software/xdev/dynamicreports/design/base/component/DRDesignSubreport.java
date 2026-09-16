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
package software.xdev.dynamicreports.design.base.component;

import software.xdev.dynamicreports.design.definition.component.DRIDesignSubreport;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;


public class DRDesignSubreport extends DRDesignComponent implements DRIDesignSubreport
{

	private DRIDesignExpression reportExpression;
	private DRIDesignExpression parametersExpression;
	private DRIDesignExpression connectionExpression;
	private DRIDesignExpression dataSourceExpression;
	private Boolean runToBottom;
	
	public DRDesignSubreport()
	{
		super("subreport");
	}
	
	@Override
	public DRIDesignExpression getReportExpression()
	{
		return this.reportExpression;
	}
	
	public void setReportExpression(final DRIDesignExpression reportExpression)
	{
		this.reportExpression = reportExpression;
	}
	
	@Override
	public DRIDesignExpression getParametersExpression()
	{
		return this.parametersExpression;
	}
	
	public void setParametersExpression(final DRIDesignExpression parametersExpression)
	{
		this.parametersExpression = parametersExpression;
	}
	
	@Override
	public DRIDesignExpression getConnectionExpression()
	{
		return this.connectionExpression;
	}
	
	public void setConnectionExpression(final DRIDesignExpression connectionExpression)
	{
		this.connectionExpression = connectionExpression;
	}
	
	@Override
	public DRIDesignExpression getDataSourceExpression()
	{
		return this.dataSourceExpression;
	}
	
	public void setDataSourceExpression(final DRIDesignExpression dataSourceExpression)
	{
		this.dataSourceExpression = dataSourceExpression;
	}
	
	@Override
	public Boolean getRunToBottom()
	{
		return this.runToBottom;
	}
	
	public void setRunToBottom(final Boolean runToBottom)
	{
		this.runToBottom = runToBottom;
	}
}
