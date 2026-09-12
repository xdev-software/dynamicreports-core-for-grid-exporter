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
import software.xdev.dynamicreports.jasper.base.JasperReportDesign;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperTransform implements JasperTransformAccessor
{
	private final DRIDesignReport report;
	private final JasperReportDesign jasperReportDesign;
	private ReportTransform reportTransform;
	private MainDatasetExpressionTransform mainDatasetExpressionTransform;
	private BandTransform bandTransform;
	private ComponentTransform componentTransform;
	private GroupTransform groupTransform;
	private StyleTransform styleTransform;
	private CrosstabTransform crosstabTransform;
	private DatasetTransform datasetTransform;
	private AbstractExpressionTransform expressionTransform;
	
	public JasperTransform(final DRIDesignReport report, final JasperReportDesign jasperReportDesign) throws DRException
	{
		this.report = report;
		this.jasperReportDesign = jasperReportDesign;
		this.init();
	}
	
	private void init() throws DRException
	{
		this.reportTransform = new ReportTransform(this);
		this.mainDatasetExpressionTransform = new MainDatasetExpressionTransform(this);
		this.groupTransform = new GroupTransform(this);
		this.bandTransform = new BandTransform(this);
		this.componentTransform = new ComponentTransform(this);
		this.styleTransform = new StyleTransform(this);
		this.crosstabTransform = new CrosstabTransform(this);
		this.datasetTransform = new DatasetTransform(this);
		this.transformToMainDataset();
	}
	
	public void transform()
	{
		this.reportTransform.transform();
		this.datasetTransform.transform();
		this.groupTransform.transform();
		this.mainDatasetExpressionTransform.transform();
		this.reportTransform.transformExpressions();
		this.groupTransform.transformExpressions();
		this.styleTransform.transform();
		this.bandTransform.transform();
		this.reportTransform.addDependencies();
	}
	
	@Override
	public ReportTransform getReportTransform()
	{
		return this.reportTransform;
	}
	
	@Override
	public CrosstabTransform getCrosstabTransform()
	{
		return this.crosstabTransform;
	}
	
	@Override
	public ComponentTransform getComponentTransform()
	{
		return this.componentTransform;
	}
	
	@Override
	public void transformToMainDataset()
	{
		this.transformToDataset(null);
	}
	
	@Override
	public void transformToDataset(final DRIDesignDataset dataset)
	{
		if(dataset != null)
		{
			this.expressionTransform = this.datasetTransform.getDatasetExpressionTransform(dataset);
		}
		else
		{
			this.expressionTransform = this.mainDatasetExpressionTransform;
		}
	}
	
	@Override
	public AbstractExpressionTransform getExpressionTransform()
	{
		return this.expressionTransform;
	}
	
	@Override
	public AbstractExpressionTransform getExpressionTransform(final DRIDesignDataset dataset)
	{
		if(dataset == null)
		{
			return this.mainDatasetExpressionTransform;
		}
		else
		{
			return this.getDatasetTransform().getDatasetExpressionTransform(dataset);
		}
	}
	
	@Override
	public GroupTransform getGroupTransform()
	{
		return this.groupTransform;
	}
	
	@Override
	public StyleTransform getStyleTransform()
	{
		return this.styleTransform;
	}
	
	@Override
	public DatasetTransform getDatasetTransform()
	{
		return this.datasetTransform;
	}
	
	@Override
	public DRIDesignReport getReport()
	{
		return this.report;
	}
	
	@Override
	public JasperCustomValues getCustomValues()
	{
		return this.jasperReportDesign.getCustomValues();
	}
	
	@Override
	public JasperDesign getDesign()
	{
		return this.jasperReportDesign.getDesign();
	}
	
	@Override
	public Map<String, Object> getParameters()
	{
		return this.jasperReportDesign.getParameters();
	}
	
	@Override
	public Map<String, Object> getParameterValues()
	{
		return this.report.getParameterValues();
	}
	
	@Override
	public Integer getStartPageNumber()
	{
		return this.jasperReportDesign.getStartPageNumber();
	}
	
	@Override
	public ReportParameters getMasterReportParameters()
	{
		return this.jasperReportDesign.getMasterReportParameters();
	}
}
