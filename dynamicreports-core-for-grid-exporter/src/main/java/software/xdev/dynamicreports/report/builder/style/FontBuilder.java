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
package software.xdev.dynamicreports.report.builder.style;

import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;


public class FontBuilder extends AbstractBuilder<FontBuilder, DRFont>
{

	protected FontBuilder()
	{
		super(new DRFont());
	}
	
	protected FontBuilder(final String fontName, final Boolean bold, final Boolean italic, final Integer fontSize)
	{
		super(new DRFont(fontName, bold, italic, fontSize));
	}
	
	public FontBuilder setFontName(final String fontName)
	{
		this.getObject().setFontName(fontName);
		return this;
	}
	
	public FontBuilder setFontSize(final Integer fontSize)
	{
		this.getObject().setFontSize(fontSize);
		return this;
	}
	
	public FontBuilder bold()
	{
		return this.setBold(true);
	}
	
	public FontBuilder setBold(final Boolean bold)
	{
		this.getObject().setBold(bold);
		return this;
	}
	
	public FontBuilder italic()
	{
		return this.setItalic(true);
	}
	
	public FontBuilder setItalic(final Boolean italic)
	{
		this.getObject().setItalic(italic);
		return this;
	}
	
	public FontBuilder boldItalic()
	{
		this.setBold(true);
		return this.setItalic(true);
	}
	
	@Deprecated
	public FontBuilder setPdfEmbedded(final Boolean pdfEmbedded)
	{
		this.getObject().setPdfEmbedded(pdfEmbedded);
		return this;
	}
	
	@Deprecated
	public FontBuilder setPdfEncoding(final String pdfEncoding)
	{
		this.getObject().setPdfEncoding(pdfEncoding);
		return this;
	}
	
	@Deprecated
	public FontBuilder setPdfFontName(final String pdfFontName)
	{
		this.getObject().setPdfFontName(pdfFontName);
		return this;
	}
	
	public FontBuilder strikeThrough()
	{
		return this.setStrikeThrough(true);
	}
	
	public FontBuilder setStrikeThrough(final Boolean strikeThrough)
	{
		this.getObject().setStrikeThrough(strikeThrough);
		return this;
	}
	
	public FontBuilder underline()
	{
		return this.setUnderline(true);
	}
	
	public FontBuilder setUnderline(final Boolean underline)
	{
		this.getObject().setUnderline(underline);
		return this;
	}
	
	public DRFont getFont()
	{
		return this.build();
	}
}
