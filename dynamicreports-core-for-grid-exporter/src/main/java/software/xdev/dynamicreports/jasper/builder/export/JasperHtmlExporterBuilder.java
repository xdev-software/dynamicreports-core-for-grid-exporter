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

import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import software.xdev.dynamicreports.jasper.base.export.JasperHtmlExporter;
import software.xdev.dynamicreports.jasper.constant.SizeUnit;


public class JasperHtmlExporterBuilder
	extends AbstractJasperExporterBuilder<JasperHtmlExporterBuilder, JasperHtmlExporter>
{

	protected JasperHtmlExporterBuilder()
	{
		super(new JasperHtmlExporter());
	}
	
	public JasperHtmlExporterBuilder setOutputImagesToDir(final Boolean outputImagesToDir)
	{
		this.getObject().setOutputImagesToDir(outputImagesToDir);
		return this;
	}
	
	public JasperHtmlExporterBuilder setImagesDirName(final String imagesDirName)
	{
		this.getObject().setImagesDirName(imagesDirName);
		return this;
	}
	
	public JasperHtmlExporterBuilder setImagesURI(final String imagesURI)
	{
		this.getObject().setImagesURI(imagesURI);
		return this;
	}
	
	public JasperHtmlExporterBuilder setHtmlHeader(final String htmlHeader)
	{
		this.getObject().setHtmlHeader(htmlHeader);
		return this;
	}
	
	public JasperHtmlExporterBuilder setBetweenPagesHtml(final String betweenPagesHtml)
	{
		this.getObject().setBetweenPagesHtml(betweenPagesHtml);
		return this;
	}
	
	public JasperHtmlExporterBuilder setHtmlFooter(final String htmlFooter)
	{
		this.getObject().setHtmlFooter(htmlFooter);
		return this;
	}
	
	public JasperHtmlExporterBuilder setRemoveEmptySpaceBetweenRows(final Boolean removeEmptySpaceBetweenRows)
	{
		this.getObject().setRemoveEmptySpaceBetweenRows(removeEmptySpaceBetweenRows);
		return this;
	}
	
	public JasperHtmlExporterBuilder setWhitePageBackground(final Boolean whitePageBackground)
	{
		this.getObject().setWhitePageBackground(whitePageBackground);
		return this;
	}
	
	@Deprecated
	public JasperHtmlExporterBuilder setUsingImagesToAlign(final Boolean usingImagesToAlign)
	{
		this.getObject().setUsingImagesToAlign(usingImagesToAlign);
		return this;
	}
	
	public JasperHtmlExporterBuilder setWrapBreakWord(final Boolean wrapBreakWord)
	{
		this.getObject().setWrapBreakWord(wrapBreakWord);
		return this;
	}
	
	public JasperHtmlExporterBuilder setSizeUnit(final SizeUnit sizeUnit)
	{
		this.getObject().setSizeUnit(sizeUnit);
		return this;
	}
	
	@Deprecated
	public JasperHtmlExporterBuilder setFramesAsNestedTables(final Boolean framesAsNestedTables)
	{
		this.getObject().setFramesAsNestedTables(framesAsNestedTables);
		return this;
	}
	
	public JasperHtmlExporterBuilder setIgnorePageMargins(final Boolean ignorePageMargins)
	{
		this.getObject().setIgnorePageMargins(ignorePageMargins);
		return this;
	}
	
	public JasperHtmlExporterBuilder setBorderCollapse(final HtmlBorderCollapseEnum borderCollapse)
	{
		this.getObject().setBorderCollapse(borderCollapse);
		return this;
	}
	
	public JasperHtmlExporterBuilder setAccessibleHtml(final Boolean accessibleHtml)
	{
		this.getObject().setAccessibleHtml(accessibleHtml);
		return this;
	}
	
	public JasperHtmlExporterBuilder setZoomRatio(final Float zoomRatio)
	{
		this.getObject().setZoomRatio(zoomRatio);
		return this;
	}
	
	public JasperHtmlExporterBuilder setIgnoreHyperLink(final Boolean ignoreHyperLink)
	{
		this.getObject().setIgnoreHyperLink(ignoreHyperLink);
		return this;
	}
	
	public JasperHtmlExporterBuilder setFlushOutput(final Boolean flushOutput)
	{
		this.getObject().setFlushOutput(flushOutput);
		return this;
	}
}
