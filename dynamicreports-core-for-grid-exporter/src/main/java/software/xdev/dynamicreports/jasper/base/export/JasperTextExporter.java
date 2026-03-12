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

import software.xdev.dynamicreports.jasper.definition.export.JasperITextExporter;


public class JasperTextExporter extends AbstractJasperExporter implements JasperITextExporter
{

	private Float characterWidth;
	private Float characterHeight;
	private Integer pageWidthInChars;
	private Integer pageHeightInChars;
	private String pageSeparator;
	private String lineSeparator;
	private Boolean trimLineRight;
	
	@Override
	public Float getCharacterWidth()
	{
		return this.characterWidth;
	}
	
	public void setCharacterWidth(final Float characterWidth)
	{
		this.characterWidth = characterWidth;
	}
	
	@Override
	public Float getCharacterHeight()
	{
		return this.characterHeight;
	}
	
	public void setCharacterHeight(final Float characterHeight)
	{
		this.characterHeight = characterHeight;
	}
	
	@Override
	public Integer getPageWidthInChars()
	{
		return this.pageWidthInChars;
	}
	
	public void setPageWidthInChars(final Integer pageWidthInChars)
	{
		this.pageWidthInChars = pageWidthInChars;
	}
	
	@Override
	public Integer getPageHeightInChars()
	{
		return this.pageHeightInChars;
	}
	
	public void setPageHeightInChars(final Integer pageHeightInChars)
	{
		this.pageHeightInChars = pageHeightInChars;
	}
	
	@Override
	public String getPageSeparator()
	{
		return this.pageSeparator;
	}
	
	public void setPageSeparator(final String pageSeparator)
	{
		this.pageSeparator = pageSeparator;
	}
	
	@Override
	public String getLineSeparator()
	{
		return this.lineSeparator;
	}
	
	public void setLineSeparator(final String lineSeparator)
	{
		this.lineSeparator = lineSeparator;
	}
	
	@Override
	public Boolean getTrimLineRight()
	{
		return this.trimLineRight;
	}
	
	public void setTrimLineRight(final Boolean trimLineRight)
	{
		this.trimLineRight = trimLineRight;
	}
}
