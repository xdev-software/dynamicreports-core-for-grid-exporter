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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.expression.DRIJasperExpression;


public class JasperExpression<T> implements DRIJasperExpression<T>
{

	private final String name;
	private final String expression;
	private final Class<? super T> valueClass;
	
	protected JasperExpression(final String expression, final Class<? super T> valueClass)
	{
		Validate.notNull(expression, "expression must not be null");
		Validate.notNull(valueClass, "valueClass must not be null");
		this.expression = expression;
		this.valueClass = valueClass;
		this.name = ReportUtils.generateUniqueName("jasperExpression");
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getExpression()
	{
		return this.expression;
	}
	
	@Override
	public Class<? super T> getValueClass()
	{
		return this.valueClass;
	}
}
