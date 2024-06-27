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
package software.xdev.dynamicreports.report.base.style;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.definition.style.DRIFont;


public class DRFont implements DRIFont
{

	private String fontName;
	private Integer fontSize;
	private Boolean bold;
	private Boolean italic;
	private Boolean underline;
	private Boolean strikeThrough;
	private String pdfFontName;
	private String pdfEncoding;
	private Boolean pdfEmbedded;
	
	public DRFont()
	{
	}
	
	public DRFont(final String fontName, final int fontSize)
	{
		this.fontName = fontName;
		this.setFontSize(fontSize);
	}
	
	public DRFont(final String fontName, final boolean bold, final boolean italic, final int fontSize)
	{
		this.fontName = fontName;
		this.bold = bold;
		this.italic = italic;
		this.setFontSize(fontSize);
	}
	
	@Override
	public String getFontName()
	{
		return this.fontName;
	}
	
	public void setFontName(final String fontName)
	{
		this.fontName = fontName;
	}
	
	@Override
	public Boolean getBold()
	{
		return this.bold;
	}
	
	public void setBold(final Boolean bold)
	{
		this.bold = bold;
	}
	
	@Override
	public Boolean getItalic()
	{
		return this.italic;
	}
	
	public void setItalic(final Boolean italic)
	{
		this.italic = italic;
	}
	
	@Override
	public Boolean getUnderline()
	{
		return this.underline;
	}
	
	public void setUnderline(final Boolean underline)
	{
		this.underline = underline;
	}
	
	@Override
	public Boolean getStrikeThrough()
	{
		return this.strikeThrough;
	}
	
	public void setStrikeThrough(final Boolean strikeThrough)
	{
		this.strikeThrough = strikeThrough;
	}
	
	@Override
	public Integer getFontSize()
	{
		return this.fontSize;
	}
	
	public void setFontSize(final Integer fontSize)
	{
		if(fontSize != null)
		{
			Validate.isTrue(fontSize >= 0, "fontSize must be >= 0");
		}
		this.fontSize = fontSize;
	}
	
	@Override
	public String getPdfFontName()
	{
		return this.pdfFontName;
	}
	
	@Deprecated
	public void setPdfFontName(final String pdfFontName)
	{
		this.pdfFontName = pdfFontName;
	}
	
	@Override
	public String getPdfEncoding()
	{
		return this.pdfEncoding;
	}
	
	@Deprecated
	public void setPdfEncoding(final String pdfEncoding)
	{
		this.pdfEncoding = pdfEncoding;
	}
	
	@Override
	public Boolean getPdfEmbedded()
	{
		return this.pdfEmbedded;
	}
	
	@Deprecated
	public void setPdfEmbedded(final Boolean pdfEmbedded)
	{
		this.pdfEmbedded = pdfEmbedded;
	}
}
