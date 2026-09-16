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
package software.xdev.dynamicreports.report.base.component;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.component.DRISubreport;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class DRSubreport extends DRDimensionComponent implements DRISubreport
{

	private DRIExpression<?> reportExpression;
	private DRIExpression<Map<String, Object>> parametersExpression;
	private DRIExpression<Connection> connectionExpression;
	private DRIExpression<?> dataSourceExpression;
	private Boolean runToBottom;
	
	@Override
	public DRIExpression<?> getReportExpression()
	{
		return this.reportExpression;
	}
	
	public void setReportExpression(final DRIExpression<?> reportExpression)
	{
		Validate.notNull(reportExpression, "reportExpression must not be null");
		this.reportExpression = reportExpression;
	}
	
	@Override
	public DRIExpression<Map<String, Object>> getParametersExpression()
	{
		return this.parametersExpression;
	}
	
	public void setParametersExpression(final DRIExpression<Map<String, Object>> parametersExpression)
	{
		this.parametersExpression = parametersExpression;
	}
	
	@Override
	public DRIExpression<Connection> getConnectionExpression()
	{
		return this.connectionExpression;
	}
	
	public void setConnectionExpression(final DRIExpression<Connection> connectionExpression)
	{
		this.connectionExpression = connectionExpression;
	}
	
	@Override
	public DRIExpression<?> getDataSourceExpression()
	{
		return this.dataSourceExpression;
	}
	
	public void setDataSourceExpression(final DRIExpression<?> dataSourceExpression)
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
