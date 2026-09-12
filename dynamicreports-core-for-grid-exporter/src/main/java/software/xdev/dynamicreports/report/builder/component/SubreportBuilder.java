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
package software.xdev.dynamicreports.report.builder.component;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import software.xdev.dynamicreports.report.base.component.DRSubreport;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class SubreportBuilder extends DimensionComponentBuilder<SubreportBuilder, DRSubreport>
{

	protected SubreportBuilder()
	{
		super(new DRSubreport());
	}
	
	// report
	
	public SubreportBuilder setReport(final ReportBuilder<?> reportBuilder)
	{
		this.getObject().setReportExpression(Expressions.value(reportBuilder));
		return this;
	}
	
	public SubreportBuilder setReport(final JasperReport jasperReport)
	{
		this.getObject().setReportExpression(Expressions.value(jasperReport));
		return this;
	}
	
	public SubreportBuilder setReport(final DRIExpression<?> reportExpression)
	{
		this.getObject().setReportExpression(reportExpression);
		return this;
	}
	
	// parameters
	
	public SubreportBuilder setParameters(final Map<String, Object> parameters)
	{
		this.getObject().setParametersExpression(Expressions.value(parameters));
		return this;
	}
	
	public SubreportBuilder setParameters(final DRIExpression<Map<String, Object>> parametersExpression)
	{
		this.getObject().setParametersExpression(parametersExpression);
		return this;
	}
	
	// connection
	
	public SubreportBuilder setConnection(final Connection connection)
	{
		this.getObject().setConnectionExpression(Expressions.value(connection));
		return this;
	}
	
	public SubreportBuilder setConnection(final DRIExpression<Connection> connectionExpression)
	{
		this.getObject().setConnectionExpression(connectionExpression);
		return this;
	}
	
	// datasource
	
	public SubreportBuilder setDataSource(final JRDataSource dataSource)
	{
		this.getObject().setDataSourceExpression(Expressions.dataSource(dataSource));
		return this;
	}
	
	public SubreportBuilder setDataSource(final DRIExpression<JRDataSource> dataSourceExpression)
	{
		this.getObject().setDataSourceExpression(dataSourceExpression);
		return this;
	}
	
	public SubreportBuilder setRunToBottom(final Boolean runToBottom)
	{
		this.getObject().setRunToBottom(runToBottom);
		return this;
	}
}
