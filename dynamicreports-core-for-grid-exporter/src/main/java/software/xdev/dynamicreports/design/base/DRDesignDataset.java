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
package software.xdev.dynamicreports.design.base;

import java.util.Collection;

import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignField;
import software.xdev.dynamicreports.design.definition.DRIDesignSort;
import software.xdev.dynamicreports.design.definition.DRIDesignVariable;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignComplexExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignJasperExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.design.definition.expression.DRIDesignSystemExpression;
import software.xdev.dynamicreports.design.transformation.DatasetExpressionTransform;
import software.xdev.dynamicreports.report.ReportUtils;


public class DRDesignDataset implements DRIDesignDataset
{
	private final String name;
	private final DatasetExpressionTransform datasetExpressionTransform;
	private DRDesignQuery query;
	private DRIDesignExpression connectionExpression;
	private DRIDesignExpression dataSourceExpression;
	private DRIDesignExpression filterExpression;
	
	public DRDesignDataset(final DatasetExpressionTransform datasetExpressionTransform)
	{
		this.datasetExpressionTransform = datasetExpressionTransform;
		this.name = ReportUtils.generateUniqueName("dataset");
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public DatasetExpressionTransform getDatasetExpressionTransform()
	{
		return this.datasetExpressionTransform;
	}
	
	@Override
	public Collection<DRIDesignField> getFields()
	{
		return this.datasetExpressionTransform.getFields();
	}
	
	@Override
	public Collection<DRIDesignVariable> getVariables()
	{
		return this.datasetExpressionTransform.getVariables();
	}
	
	@Override
	public Collection<DRIDesignSystemExpression> getSystemExpressions()
	{
		return this.datasetExpressionTransform.getSystemExpressions();
	}
	
	@Override
	public Collection<DRIDesignJasperExpression> getJasperExpressions()
	{
		return this.datasetExpressionTransform.getJasperExpressions();
	}
	
	@Override
	public Collection<DRIDesignSimpleExpression> getSimpleExpressions()
	{
		return this.datasetExpressionTransform.getSimpleExpressions();
	}
	
	@Override
	public Collection<DRIDesignComplexExpression> getComplexExpressions()
	{
		return this.datasetExpressionTransform.getComplexExpressions();
	}
	
	@Override
	public Collection<DRIDesignSort> getSorts()
	{
		return this.datasetExpressionTransform.getSorts();
	}
	
	@Override
	public DRDesignQuery getQuery()
	{
		return this.query;
	}
	
	public void setQuery(final DRDesignQuery query)
	{
		this.query = query;
	}
	
	@Override
	public DRIDesignExpression getConnectionExpression()
	{
		return this.connectionExpression;
	}
	
	public void setConnectionExpression(final DRIDesignExpression connectionExpression)
	{
		this.connectionExpression = connectionExpression;
	}
	
	@Override
	public DRIDesignExpression getDataSourceExpression()
	{
		return this.dataSourceExpression;
	}
	
	public void setDataSourceExpression(final DRIDesignExpression dataSourceExpression)
	{
		this.dataSourceExpression = dataSourceExpression;
	}
	
	@Override
	public DRIDesignExpression getFilterExpression()
	{
		return this.filterExpression;
	}
	
	public void setFilterExpression(final DRIDesignExpression filterExpression)
	{
		this.filterExpression = filterExpression;
	}
}
