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

import java.util.List;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


public abstract class AbstractSubDatasourceExpression<T> extends AbstractComplexExpression<JRDataSource>
{

	protected AbstractSubDatasourceExpression(final String fieldName)
	{
		Validate.notNull(fieldName, "fieldName must not be null");
		this.addExpression(fieldName, this.getSubDatasourceDataClass());
	}
	
	protected AbstractSubDatasourceExpression(final DRIExpression<? extends T> expression)
	{
		this.addExpression(expression);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JRDataSource evaluate(final List<?> values, final ReportParameters reportParameters)
	{
		return this.createSubDatasource((T)values.get(0));
	}
	
	@Override
	public Class<? super JRDataSource> getValueClass()
	{
		return JRDataSource.class;
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getSubDatasourceDataClass()
	{
		return (Class<T>)ReportUtils.getGenericClass(this, 0);
	}
	
	protected abstract JRDataSource createSubDatasource(T data);
}
