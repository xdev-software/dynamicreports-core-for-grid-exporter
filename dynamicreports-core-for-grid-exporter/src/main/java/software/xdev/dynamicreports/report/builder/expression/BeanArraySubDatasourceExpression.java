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
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public class BeanArraySubDatasourceExpression extends AbstractSubDatasourceExpression<Object[]>
{

	public BeanArraySubDatasourceExpression(final String fieldName)
	{
		super(fieldName);
	}
	
	public BeanArraySubDatasourceExpression(final DRIExpression<? extends Object[]> expression)
	{
		super(expression);
	}
	
	@Override
	protected JRDataSource createSubDatasource(final Object[] data)
	{
		return new JRBeanArrayDataSource(data);
	}
}
