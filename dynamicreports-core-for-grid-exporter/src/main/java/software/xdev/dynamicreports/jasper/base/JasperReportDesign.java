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
package software.xdev.dynamicreports.jasper.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperReportDesign implements Serializable
{

	private JasperCustomValues customValues;
	private JasperDesign design;
	private Map<String, Object> parameters;
	private final ReportParameters masterReportParameters;
	private final Integer startPageNumber;
	private boolean tableOfContents;
	private DRITableOfContentsCustomizer tableOfContentsCustomizer;
	
	public JasperReportDesign(final DRIDesignReport report, final Integer startPageNumber) throws DRException
	{
		this(report, null, startPageNumber);
	}
	
	public JasperReportDesign(
		final DRIDesignReport report,
		final ReportParameters masterReportParameters,
		final Integer startPageNumber)
		throws DRException
	{
		this.masterReportParameters = masterReportParameters;
		this.startPageNumber = startPageNumber;
		this.init(report);
	}
	
	private void init(final DRIDesignReport report) throws DRException
	{
		this.design = (JasperDesign)report.getTemplateDesign().getDesign();
		this.tableOfContents = report.isTableOfContents();
		this.tableOfContentsCustomizer = report.getTableOfContentsCustomizer();
		this.customValues = new JasperCustomValues(report.getProperties());
		this.parameters = new HashMap<>();
	}
	
	public JasperCustomValues getCustomValues()
	{
		return this.customValues;
	}
	
	public JasperDesign getDesign()
	{
		return this.design;
	}
	
	public Map<String, Object> getParameters()
	{
		return this.parameters;
	}
	
	public Integer getStartPageNumber()
	{
		return this.startPageNumber;
	}
	
	public ReportParameters getMasterReportParameters()
	{
		return this.masterReportParameters;
	}
	
	public boolean isTableOfContents()
	{
		return this.tableOfContents;
	}
	
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer()
	{
		return this.tableOfContentsCustomizer;
	}
}
