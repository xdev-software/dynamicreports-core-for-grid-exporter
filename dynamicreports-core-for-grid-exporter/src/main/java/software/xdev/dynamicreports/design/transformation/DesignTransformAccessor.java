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

import java.util.Locale;
import java.util.ResourceBundle;

import software.xdev.dynamicreports.design.base.DRDesignPage;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.DRIDataset;
import software.xdev.dynamicreports.report.definition.DRIReport;


public interface DesignTransformAccessor
{
	
	public DRIReport getReport();
	
	public Integer getPageWidth();
	
	public ReportTransform getReportTransform();
	
	public TemplateTransform getTemplateTransform();
	
	public void transformToMainDataset();
	
	public void transformToDataset(DRIDataset dataset);
	
	public AbstractExpressionTransform getExpressionTransform();
	
	public PageTransform getPageTransform();
	
	public BandTransform getBandTransform();
	
	public ComponentTransform getComponentTransform();
	
	public GroupTransform getGroupTransform();
	
	public ColumnTransform getColumnTransform();
	
	public ColumnGridTransform getColumnGridTransform();
	
	public StyleTransform getStyleTransform();
	
	public CrosstabTransform getCrosstabTransform();
	
	public DatasetTransform getDatasetTransform();
	
	public TableOfContentsTransform getTableOfContentsTransform();
	
	public boolean isTableOfContents();
	
	public DRDesignPage getPage();
	
	public Locale getLocale();
	
	public ResourceBundle getResourceBundle();
	
	public String getResourceBundleName();
	
	public WhenResourceMissingType getWhenResourceMissingType();
}
