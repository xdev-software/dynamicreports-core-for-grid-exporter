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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.definition.component.DRIDesignGenericElement;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignParameterExpression;


public class DRDesignGenericElement extends DRDesignComponent implements DRIDesignGenericElement
{

	private String genericElementNamespace;
	private String genericElementName;
	private EvaluationTime evaluationTime;
	private DRDesignGroup evaluationGroup;
	private List<DRIDesignParameterExpression> parameterExpressions;
	
	public DRDesignGenericElement()
	{
		super("genericElement");
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
	public EvaluationTime getEvaluationTime()
	{
		return this.evaluationTime;
	}
	
	public void setEvaluationTime(final EvaluationTime evaluationTime)
	{
		this.evaluationTime = evaluationTime;
	}
	
	@Override
	public DRDesignGroup getEvaluationGroup()
	{
		return this.evaluationGroup;
	}
	
	public void setEvaluationGroup(final DRDesignGroup evaluationGroup)
	{
		this.evaluationGroup = evaluationGroup;
	}
	
	@Override
	public List<DRIDesignParameterExpression> getParameterExpressions()
	{
		return this.parameterExpressions;
	}
	
	public void setParameterExpressions(final List<DRIDesignParameterExpression> parameterExpressions)
	{
		this.parameterExpressions = parameterExpressions;
	}
}
