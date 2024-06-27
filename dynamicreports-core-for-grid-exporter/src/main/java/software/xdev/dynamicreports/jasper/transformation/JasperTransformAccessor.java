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

import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignDataset;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public interface JasperTransformAccessor
{
	
	public DRIDesignReport getReport();
	
	public JasperDesign getDesign();
	
	public JasperCustomValues getCustomValues();
	
	public Map<String, Object> getParameters();
	
	public Map<String, Object> getParameterValues();
	
	public Integer getStartPageNumber();
	
	public ReportParameters getMasterReportParameters();
	
	public ReportTransform getReportTransform();
	
	public void transformToMainDataset();
	
	public void transformToDataset(DRIDesignDataset dataset);
	
	public AbstractExpressionTransform getExpressionTransform();
	
	public AbstractExpressionTransform getExpressionTransform(DRIDesignDataset dataset);
	
	public GroupTransform getGroupTransform();
	
	public ComponentTransform getComponentTransform();
	
	public StyleTransform getStyleTransform();
	
	public CrosstabTransform getCrosstabTransform();
	
	public DatasetTransform getDatasetTransform();
}
