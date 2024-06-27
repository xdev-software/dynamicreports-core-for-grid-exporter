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
package software.xdev.dynamicreports.jasper.base.reporthandler;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperReportBuilderHandler implements JasperReportHandler
{
	private final List<JasperReportBuilder> jasperReportBuilders;
	private boolean continuousPageNumbering;
	
	public JasperReportBuilderHandler()
	{
		this.jasperReportBuilders = new ArrayList<>();
		this.continuousPageNumbering = false;
	}
	
	@Override
	public void concatenate(final JasperReportBuilder... jasperReportBuilders)
	{
		for(final JasperReportBuilder jasperReportBuilder : jasperReportBuilders)
		{
			this.jasperReportBuilders.add(jasperReportBuilder);
		}
	}
	
	@Override
	public void setContinuousPageNumbering(final boolean continuousPageNumbering)
	{
		this.continuousPageNumbering = continuousPageNumbering;
	}
	
	@Override
	public List<JasperPrint> getPrintList() throws DRException
	{
		final List<JasperPrint> printList = new ArrayList<>();
		int pageNumber = 1;
		for(final JasperReportBuilder jasperReportBuilder : this.jasperReportBuilders)
		{
			if(this.continuousPageNumbering)
			{
				jasperReportBuilder.setStartPageNumber(pageNumber);
			}
			else
			{
				jasperReportBuilder.setStartPageNumber(null);
			}
			final JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
			printList.add(jasperPrint);
			pageNumber += jasperPrint.getPages().size();
		}
		return printList;
	}
}
