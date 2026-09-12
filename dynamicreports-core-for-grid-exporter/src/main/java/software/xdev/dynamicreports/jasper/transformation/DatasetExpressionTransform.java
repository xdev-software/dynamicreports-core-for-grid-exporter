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
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;


public class DatasetExpressionTransform extends AbstractExpressionTransform
{
	private final DRIDesignDataset dataset;
	private final JRDesignDataset jrDataset;
	private final JasperCustomValues customValues;
	
	public DatasetExpressionTransform(
		final DRIDesignDataset dataset,
		final JRDesignDataset jrDataset,
		final JasperCustomValues customValues)
	{
		this.dataset = dataset;
		this.jrDataset = jrDataset;
		this.customValues = customValues;
	}
	
	@Override
	protected JasperCustomValues getCustomValues()
	{
		return this.customValues;
	}
	
	@Override
	protected Collection<DRIDesignField> getFields()
	{
		return this.dataset.getFields();
	}
	
	@Override
	protected Collection<DRIDesignVariable> getVariables()
	{
		return this.dataset.getVariables();
	}
	
	@Override
	protected Collection<DRIDesignSystemExpression> getSystemExpressions()
	{
		return this.dataset.getSystemExpressions();
	}
	
	@Override
	protected Collection<DRIDesignJasperExpression> getJasperExpressions()
	{
		return this.dataset.getJasperExpressions();
	}
	
	@Override
	protected Collection<DRIDesignSimpleExpression> getSimpleExpressions()
	{
		return this.dataset.getSimpleExpressions();
	}
	
	@Override
	protected Collection<DRIDesignComplexExpression> getComplexExpressions()
	{
		return this.dataset.getComplexExpressions();
	}
	
	@Override
	protected Collection<DRIDesignSort> getSorts()
	{
		return this.dataset.getSorts();
	}
	
	@Override
	protected void addField(final JRDesignField field) throws JRException
	{
		this.jrDataset.addField(field);
	}
	
	@Override
	protected void addVariable(final JRDesignVariable variable) throws JRException
	{
		this.jrDataset.addVariable(variable);
	}
	
	@Override
	protected void addSort(final JRDesignSortField sort) throws JRException
	{
		this.jrDataset.addSortField(sort);
	}
}
