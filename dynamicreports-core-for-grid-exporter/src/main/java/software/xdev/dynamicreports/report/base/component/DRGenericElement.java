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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.component.DRIGenericElement;
import software.xdev.dynamicreports.report.definition.expression.DRIParameterExpression;


public class DRGenericElement extends DRDimensionComponent implements DRIGenericElement
{

	private String genericElementNamespace;
	private String genericElementName;
	private List<DRIParameterExpression> parameterExpressions;
	
	public DRGenericElement(final String namespace, final String name)
	{
		Validate.notEmpty(namespace, "namespace must not be empty");
		Validate.notEmpty(name, "name must not be empty");
		this.genericElementNamespace = namespace;
		this.genericElementName = name;
	}
	
	@Override
	protected void init()
	{
		super.init();
		this.parameterExpressions = new ArrayList<>();
	}
	
	@Override
	public String getGenericElementNamespace()
	{
		return this.genericElementNamespace;
	}
	
	public void setGenericElementNamespace(final String genericElementNamespace)
	{
		this.genericElementNamespace = genericElementNamespace;
	}
	
	@Override
	public String getGenericElementName()
	{
		return this.genericElementName;
	}
	
	public void setGenericElementName(final String genericElementName)
	{
		this.genericElementName = genericElementName;
	}
	
	@Override
	public List<DRIParameterExpression> getParameterExpressions()
	{
		return this.parameterExpressions;
	}
	
	public void setParameterExpressions(final List<DRIParameterExpression> parameterExpressions)
	{
		this.parameterExpressions = parameterExpressions;
	}
	
	public void addParameterExpression(final DRIParameterExpression parameterExpression)
	{
		Validate.notNull(parameterExpression, "parameterExpression must not be null");
		this.parameterExpressions.add(parameterExpression);
	}
}
