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
package software.xdev.dynamicreports.report.builder.expression;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class DataSourceExpression extends AbstractSimpleExpression<JRDataSource>
{

	private final JRDataSource dataSource;
	private boolean moveFirst;
	
	public DataSourceExpression(final JRDataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public JRDataSource evaluate(final ReportParameters reportParameters)
	{
		if(this.moveFirst && this.dataSource != null && this.dataSource instanceof JRRewindableDataSource)
		{
			try
			{
				((JRRewindableDataSource)this.dataSource).moveFirst();
			}
			catch(final JRException e)
			{
				// Undocumented upstream
			}
		}
		this.moveFirst = true;
		return this.dataSource;
	}
	
	@Override
	public Class<JRDataSource> getValueClass()
	{
		return JRDataSource.class;
	}
}
