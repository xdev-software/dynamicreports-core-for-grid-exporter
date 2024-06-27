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

import software.xdev.dynamicreports.jasper.base.export.JasperTextExporter;


public class JasperTextExporterBuilder
	extends AbstractJasperExporterBuilder<JasperTextExporterBuilder, JasperTextExporter>
{

	protected JasperTextExporterBuilder()
	{
		super(new JasperTextExporter());
	}
	
	@Deprecated
	public JasperTextExporterBuilder setCharacterWidth(final Integer characterWidth)
	{
		return this.setCharacterWidth(characterWidth != null ? characterWidth.floatValue() : null);
	}
	
	public JasperTextExporterBuilder setCharacterWidth(final Float characterWidth)
	{
		this.getObject().setCharacterWidth(characterWidth);
		return this;
	}
	
	@Deprecated
	public JasperTextExporterBuilder setCharacterHeight(final Integer characterHeight)
	{
		return this.setCharacterHeight(characterHeight != null ? characterHeight.floatValue() : null);
	}
	
	public JasperTextExporterBuilder setCharacterHeight(final Float characterHeight)
	{
		this.getObject().setCharacterHeight(characterHeight);
		return this;
	}
	
	@Deprecated
	public JasperTextExporterBuilder setPageWidth(final Integer pageWidth)
	{
		return this.setPageWidthInChars(pageWidth);
	}
	
	public JasperTextExporterBuilder setPageWidthInChars(final Integer pageWidth)
	{
		this.getObject().setPageWidthInChars(pageWidth);
		return this;
	}
	
	@Deprecated
	public JasperTextExporterBuilder setPageHeight(final Integer pageHeight)
	{
		return this.setPageHeightInChars(pageHeight);
	}
	
	public JasperTextExporterBuilder setPageHeightInChars(final Integer pageHeight)
	{
		this.getObject().setPageHeightInChars(pageHeight);
		return this;
	}
	
	@Deprecated
	public JasperTextExporterBuilder setBetweenPagesText(final String betweenPagesText)
	{
		return this.setPageSeparator(betweenPagesText);
	}
	
	public JasperTextExporterBuilder setPageSeparator(final String pageSeparator)
	{
		this.getObject().setPageSeparator(pageSeparator);
		return this;
	}
	
	public JasperTextExporterBuilder setLineSeparator(final String lineSeparator)
	{
		this.getObject().setLineSeparator(lineSeparator);
		return this;
	}
}
