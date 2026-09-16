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
package software.xdev.dynamicreports.jasper.base.export;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.jasper.definition.export.JasperIExporter;


public abstract class AbstractJasperExporter implements JasperIExporter
{

	private Writer outputWriter;
	private OutputStream outputStream;
	private File outputFile;
	private String outputFileName;
	
	private Integer pageIndex;
	private Integer startPageIndex;
	private Integer endPageIndex;
	private String characterEncoding;
	private Integer offsetX;
	private Integer offsetY;
	
	@Override
	public Writer getOutputWriter()
	{
		return this.outputWriter;
	}
	
	public void setOutputWriter(final Writer outputWriter)
	{
		Validate.notNull(outputWriter, "outputWriter must not be null");
		this.outputWriter = outputWriter;
	}
	
	@Override
	public OutputStream getOutputStream()
	{
		return this.outputStream;
	}
	
	public void setOutputStream(final OutputStream outputStream)
	{
		Validate.notNull(outputStream, "outputStream must not be null");
		this.outputStream = outputStream;
	}
	
	@Override
	public File getOutputFile()
	{
		return this.outputFile;
	}
	
	public void setOutputFile(final File outputFile)
	{
		Validate.notNull(outputFile, "outputFile must not be null");
		this.outputFile = outputFile;
	}
	
	@Override
	public String getOutputFileName()
	{
		return this.outputFileName;
	}
	
	public void setOutputFileName(final String outputFileName)
	{
		Validate.notNull(outputFileName, "outputFileName must not be null");
		this.outputFileName = outputFileName;
	}
	
	@Override
	public Integer getPageIndex()
	{
		return this.pageIndex;
	}
	
	public void setPageIndex(final Integer pageIndex)
	{
		this.pageIndex = pageIndex;
	}
	
	@Override
	public Integer getStartPageIndex()
	{
		return this.startPageIndex;
	}
	
	public void setStartPageIndex(final Integer startPageIndex)
	{
		this.startPageIndex = startPageIndex;
	}
	
	@Override
	public Integer getEndPageIndex()
	{
		return this.endPageIndex;
	}
	
	public void setEndPageIndex(final Integer endPageIndex)
	{
		this.endPageIndex = endPageIndex;
	}
	
	@Override
	public String getCharacterEncoding()
	{
		return this.characterEncoding;
	}
	
	public void setCharacterEncoding(final String characterEncoding)
	{
		this.characterEncoding = characterEncoding;
	}
	
	@Override
	public Integer getOffsetX()
	{
		return this.offsetX;
	}
	
	public void setOffsetX(final Integer offsetX)
	{
		this.offsetX = offsetX;
	}
	
	@Override
	public Integer getOffsetY()
	{
		return this.offsetY;
	}
	
	public void setOffsetY(final Integer offsetY)
	{
		this.offsetY = offsetY;
	}
}
