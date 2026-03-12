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
package software.xdev.dynamicreports.jasper.transformation;

import java.util.Collection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignGroup;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;


public class MainDatasetExpressionTransform extends AbstractExpressionTransform
{
	private final JasperTransformAccessor accessor;
	
	public MainDatasetExpressionTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	@Override
	protected JasperCustomValues getCustomValues()
	{
		return this.accessor.getCustomValues();
	}
	
	@Override
	protected JRGroup getGroup(final DRIDesignGroup group)
	{
		return this.accessor.getGroupTransform().getGroup(group);
	}
	
	@Override
	protected Collection<DRIDesignField> getFields()
	{
		return this.accessor.getReport().getFields();
	}
	
	@Override
	protected Collection<DRIDesignVariable> getVariables()
	{
		return this.accessor.getReport().getVariables();
	}
	
	@Override
	protected Collection<DRIDesignSystemExpression> getSystemExpressions()
	{
		return this.accessor.getReport().getSystemExpressions();
	}
	
	@Override
	protected Collection<DRIDesignJasperExpression> getJasperExpressions()
	{
		return this.accessor.getReport().getJasperExpressions();
	}
	
	@Override
	protected Collection<DRIDesignSimpleExpression> getSimpleExpressions()
	{
		return this.accessor.getReport().getSimpleExpressions();
	}
	
	@Override
	protected Collection<DRIDesignComplexExpression> getComplexExpressions()
	{
		return this.accessor.getReport().getComplexExpressions();
	}
	
	@Override
	protected Collection<DRIDesignSort> getSorts()
	{
		return this.accessor.getReport().getSorts();
	}
	
	@Override
	protected void addField(final JRDesignField field) throws JRException
	{
		this.accessor.getDesign().addField(field);
	}
	
	@Override
	protected void addVariable(final JRDesignVariable variable) throws JRException
	{
		this.accessor.getDesign().addVariable(variable);
	}
	
	@Override
	protected void addSort(final JRDesignSortField sort) throws JRException
	{
		this.accessor.getDesign().addSortField(sort);
	}
}
