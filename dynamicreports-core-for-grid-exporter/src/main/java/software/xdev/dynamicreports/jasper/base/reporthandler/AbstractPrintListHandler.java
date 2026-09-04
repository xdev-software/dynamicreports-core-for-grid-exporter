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

import net.sf.jasperreports.engine.JasperPrint;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;


public abstract class AbstractPrintListHandler implements JasperReportHandler
{
	private boolean continuousPageNumbering;
	private int pageNumber;
	
	public AbstractPrintListHandler()
	{
		this.continuousPageNumbering = false;
		this.pageNumber = 1;
	}
	
	@Override
	public void concatenate(final JasperReportBuilder... jasperReportBuilders)
	{
		for(final JasperReportBuilder jasperReportBuilder : jasperReportBuilders)
		{
			try
			{
				if(this.continuousPageNumbering)
				{
					jasperReportBuilder.setStartPageNumber(this.pageNumber);
				}
				else
				{
					jasperReportBuilder.setStartPageNumber(null);
				}
				final JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
				this.add(jasperPrint);
				this.pageNumber += jasperPrint.getPages().size();
				jasperReportBuilder.rebuild();
			}
			catch(final Exception e)
			{
				// Undocumented upstream
			}
		}
	}
	
	protected abstract void add(JasperPrint jasperPrint);
	
	@Override
	public void setContinuousPageNumbering(final boolean continuousPageNumbering)
	{
		this.continuousPageNumbering = continuousPageNumbering;
	}
}
