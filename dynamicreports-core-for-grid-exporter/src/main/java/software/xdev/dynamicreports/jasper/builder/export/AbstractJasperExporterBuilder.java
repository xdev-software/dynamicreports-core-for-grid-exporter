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
package software.xdev.dynamicreports.jasper.builder.export;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import software.xdev.dynamicreports.jasper.base.export.AbstractJasperExporter;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;


@SuppressWarnings("unchecked")
public abstract class AbstractJasperExporterBuilder<T extends AbstractJasperExporterBuilder<T, U>,
	U extends AbstractJasperExporter>
	extends AbstractBuilder<T, U>
{

	protected AbstractJasperExporterBuilder(final U jasperExporter)
	{
		super(jasperExporter);
	}
	
	protected T setOutputWriter(final Writer outputWriter)
	{
		this.getObject().setOutputWriter(outputWriter);
		return (T)this;
	}
	
	protected T setOutputStream(final OutputStream outputStream)
	{
		this.getObject().setOutputStream(outputStream);
		return (T)this;
	}
	
	protected T setOutputFile(final File outputFile)
	{
		this.getObject().setOutputFile(outputFile);
		return (T)this;
	}
	
	protected T setOutputFileName(final String outputFileName)
	{
		this.getObject().setOutputFileName(outputFileName);
		return (T)this;
	}
	
	public T setPageIndex(final Integer pageIndex)
	{
		this.getObject().setPageIndex(pageIndex);
		return (T)this;
	}
	
	public T setStartPageIndex(final Integer startPageIndex)
	{
		this.getObject().setStartPageIndex(startPageIndex);
		return (T)this;
	}
	
	public T setEndPageIndex(final Integer endPageIndex)
	{
		this.getObject().setEndPageIndex(endPageIndex);
		return (T)this;
	}
	
	public T setCharacterEncoding(final String characterEncoding)
	{
		this.getObject().setCharacterEncoding(characterEncoding);
		return (T)this;
	}
	
	public T setOffsetX(final Integer offsetX)
	{
		this.getObject().setOffsetX(offsetX);
		return (T)this;
	}
	
	public T setOffsetY(final Integer offsetY)
	{
		this.getObject().setOffsetY(offsetY);
		return (T)this;
	}
	
	public U getExporter()
	{
		return this.build();
	}
}
