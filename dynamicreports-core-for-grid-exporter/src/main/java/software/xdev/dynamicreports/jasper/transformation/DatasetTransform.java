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

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.jasper.base.JasperScriptlet;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.jasper.transformation.expression.DatasetParametersExpression;


public class DatasetTransform
{
	private final JasperTransformAccessor accessor;
	private final Map<DRIDesignDataset, Map<String, Object>> datasetParameters;
	private final Map<DRIDesignDataset, DatasetExpressionTransform> datasetExpressions;
	
	public DatasetTransform(final JasperTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.datasetParameters = new HashMap<>();
		this.datasetExpressions = new HashMap<>();
	}
	
	public void transform()
	{
		for(final DRIDesignDataset dataset : this.accessor.getReport().getDatasets())
		{
			this.addDataset(dataset);
		}
	}
	
	private void addDataset(final DRIDesignDataset dataset)
	{
		try
		{
			this.accessor.getDesign().addDataset(this.dataset(dataset));
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for dataset \"" + dataset.getName() + "\"", e);
		}
	}
	
	// dataset
	private JRDesignDataset dataset(final DRIDesignDataset dataset)
	{
		final Map<String, Object> parameters = new HashMap<>();
		this.datasetParameters.put(dataset, parameters);
		final JRDesignDataset jrDataset = new JRDesignDataset(false);
		jrDataset.setName(dataset.getName());
		if(dataset.getQuery() != null)
		{
			jrDataset.setQuery(this.accessor.getReportTransform().query(dataset.getQuery()));
		}
		final JasperCustomValues customValues = new JasperCustomValues(this.accessor.getReport().getProperties());
		final DatasetExpressionTransform datasetExpressionTransform =
			new DatasetExpressionTransform(dataset, jrDataset, customValues);
		datasetExpressionTransform.transform();
		this.datasetExpressions.put(dataset, datasetExpressionTransform);
		if(!customValues.isEmpty())
		{
			this.addParameter(jrDataset, parameters, JasperCustomValues.NAME, JasperCustomValues.class, customValues);
			this.addScriptlet(jrDataset, parameters, JasperScriptlet.NAME);
		}
		jrDataset.setFilterExpression(datasetExpressionTransform.getExpression(dataset.getFilterExpression()));
		return jrDataset;
	}
	
	private <T> void addParameter(
		final JRDesignDataset jrDataset,
		final Map<String, Object> parameters,
		final String name,
		final Class<T> parameterClass,
		final T value)
	{
		final JRDesignParameter jrParameter = new JRDesignParameter();
		jrParameter.setName(name);
		jrParameter.setValueClass(parameterClass);
		try
		{
			jrDataset.addParameter(jrParameter);
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for parameter \"" + name + "\"", e);
		}
		parameters.put(jrParameter.getName(), value);
	}
	
	private void addScriptlet(final JRDesignDataset jrDataset, final Map<String, Object> parameters, final String name)
	{
		try
		{
			jrDataset.addScriptlet(this.accessor.getReportTransform().scriptlet(name, JasperScriptlet.class));
		}
		catch(final JRException e)
		{
			throw new JasperDesignException("Registration failed for scriptlet \"" + name + "\"", e);
		}
	}
	
	public JRDesignDatasetRun datasetRun(final DRIDesignDataset dataset)
	{
		if(dataset == null)
		{
			return null;
		}
		
		final JRDesignDatasetRun jrDatasetRun = new JRDesignDatasetRun();
		jrDatasetRun.setDatasetName(dataset.getName());
		jrDatasetRun.setConnectionExpression(this.accessor.getExpressionTransform()
			.getExpression(dataset.getConnectionExpression()));
		jrDatasetRun.setDataSourceExpression(this.accessor.getExpressionTransform()
			.getExpression(dataset.getDataSourceExpression()));
		final DatasetParametersExpression parametersExpression =
			new DatasetParametersExpression(this.datasetParameters.get(dataset));
		this.accessor.getExpressionTransform().addSimpleExpression(parametersExpression);
		jrDatasetRun.setParametersMapExpression(this.accessor.getExpressionTransform()
			.getExpression(parametersExpression));
		return jrDatasetRun;
	}
	
	protected Map<String, Object> getDatasetParameters(final DRIDesignDataset dataset)
	{
		return this.datasetParameters.get(dataset);
	}
	
	public DatasetExpressionTransform getDatasetExpressionTransform(final DRIDesignDataset dataset)
	{
		return this.datasetExpressions.get(dataset);
	}
}
