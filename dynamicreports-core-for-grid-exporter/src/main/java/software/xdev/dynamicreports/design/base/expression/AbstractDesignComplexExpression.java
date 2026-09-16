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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.report.ReportUtils;


public abstract class AbstractDesignComplexExpression implements DRIDesignComplexExpression
{

	private final String name;
	private List<DRIDesignExpression> expressions;
	
	protected AbstractDesignComplexExpression()
	{
		this(ReportUtils.generateUniqueName("complexExpression"));
	}
	
	protected AbstractDesignComplexExpression(final String name)
	{
		this.name = name;
		this.expressions = new ArrayList<>();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public void addExpression(final DRIDesignExpression expression)
	{
		this.expressions.add(expression);
	}
	
	@Override
	public List<DRIDesignExpression> getExpressions()
	{
		return this.expressions;
	}
	
	public void setExpressions(final List<DRIDesignExpression> expressions)
	{
		this.expressions = expressions;
	}
	
	@Override
	public String getParameterName()
	{
		return null;
	}
}
