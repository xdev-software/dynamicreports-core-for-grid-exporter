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
package software.xdev.dynamicreports.design.base.expression;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISystemExpression;


public class DRDesignSystemExpression implements DRIDesignSystemExpression
{

	private final DRISystemExpression<?> systemExpression;
	
	public DRDesignSystemExpression(final DRISystemExpression<?> systemExpression)
	{
		this.systemExpression = systemExpression;
	}
	
	@Override
	public Class<?> getValueClass()
	{
		return this.systemExpression.getValueClass();
	}
	
	@Override
	public String getName()
	{
		return this.systemExpression.getName();
	}
}
