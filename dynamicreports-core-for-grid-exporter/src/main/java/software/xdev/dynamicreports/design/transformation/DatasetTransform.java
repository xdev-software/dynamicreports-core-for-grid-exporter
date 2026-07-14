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
package software.xdev.dynamicreports.design.transformation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.jasper.base.JasperScriptlet;
import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.exception.DRException;


public class DatasetTransform
{
	private final DesignTransformAccessor accessor;
	private final Map<String, DRIDesignDataset> datasets;
	private final Map<DRIDataset, DRDesignDataset> designDatasets;
	
	public DatasetTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.datasets = new HashMap<>();
		this.designDatasets = new HashMap<>();
	}
	
	public DRDesignDataset transform(final DRIDataset dataset) throws DRException
	{
		if(dataset == null)
		{
			return null;
		}
		if(this.designDatasets.containsKey(dataset))
		{
			return this.designDatasets.get(dataset);
		}
		
		final DatasetExpressionTransform datasetExpressionTransform =
			new DatasetExpressionTransform(this.accessor, dataset);
		datasetExpressionTransform.transform();
		final DRDesignDataset designDataset = new DRDesignDataset(datasetExpressionTransform);
		if(dataset.getQuery() != null)
		{
			designDataset.setQuery(this.accessor.getReportTransform().query(dataset.getQuery()));
		}
		designDataset.setConnectionExpression(this.accessor.getExpressionTransform()
			.transformExpression(dataset.getConnectionExpression()));
		designDataset.setDataSourceExpression(this.accessor.getExpressionTransform()
			.transformExpression(dataset.getDataSourceExpression()));
		designDataset.setFilterExpression(datasetExpressionTransform.transformExpression(
			dataset.getFilterExpression(),
			JasperScriptlet.SCRIPTLET_NAME));
		
		this.addDataset(dataset, designDataset);
		
		return designDataset;
	}
	
	public DatasetExpressionTransform getDatasetExpressionTransform(final DRIDataset dataset)
	{
		return this.designDatasets.get(dataset).getDatasetExpressionTransform();
	}
	
	protected DRDesignDataset getDesignDataset(final DRIDataset dataset)
	{
		return this.designDatasets.get(dataset);
	}
	
	private void addDataset(final DRIDataset dataset, final DRDesignDataset designDataset)
	{
		if(this.datasets.containsKey(designDataset.getName()))
		{
			throw new DRDesignReportException("Duplicate declaration of dataset \"" + designDataset.getName() + "\"");
		}
		this.datasets.put(designDataset.getName(), designDataset);
		this.designDatasets.put(dataset, designDataset);
	}
	
	public Collection<DRIDesignDataset> getDatasets()
	{
		return this.datasets.values();
	}
}
