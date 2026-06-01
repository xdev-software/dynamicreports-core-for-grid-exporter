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

import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import software.xdev.dynamicreports.jasper.constant.SizeUnit;
import software.xdev.dynamicreports.jasper.definition.export.JasperIHtmlExporter;


public class JasperHtmlExporter extends AbstractJasperExporter implements JasperIHtmlExporter
{

	private Boolean outputImagesToDir;
	private String imagesDirName;
	private String imagesURI;
	private String htmlHeader;
	private String betweenPagesHtml;
	private String htmlFooter;
	private Boolean removeEmptySpaceBetweenRows;
	private Boolean whitePageBackground;
	private Boolean usingImagesToAlign;
	private Boolean wrapBreakWord;
	private SizeUnit sizeUnit;
	private Boolean framesAsNestedTables;
	private Boolean ignorePageMargins;
	private HtmlBorderCollapseEnum borderCollapse;
	private Boolean accessibleHtml;
	private Float zoomRatio;
	private Boolean ignoreHyperLink;
	private Boolean flushOutput;
	
	@Override
	public Boolean getOutputImagesToDir()
	{
		return this.outputImagesToDir;
	}
	
	public void setOutputImagesToDir(final Boolean outputImagesToDir)
	{
		this.outputImagesToDir = outputImagesToDir;
	}
	
	@Override
	public String getImagesDirName()
	{
		return this.imagesDirName;
	}
	
	public void setImagesDirName(final String imagesDirName)
	{
		this.imagesDirName = imagesDirName;
	}
	
	@Override
	public String getImagesURI()
	{
		return this.imagesURI;
	}
	
	public void setImagesURI(final String imagesURI)
	{
		this.imagesURI = imagesURI;
	}
	
	@Override
	public String getHtmlHeader()
	{
		return this.htmlHeader;
	}
	
	public void setHtmlHeader(final String htmlHeader)
	{
		this.htmlHeader = htmlHeader;
	}
	
	@Override
	public String getBetweenPagesHtml()
	{
		return this.betweenPagesHtml;
	}
	
	public void setBetweenPagesHtml(final String betweenPagesHtml)
	{
		this.betweenPagesHtml = betweenPagesHtml;
	}
	
	@Override
	public String getHtmlFooter()
	{
		return this.htmlFooter;
	}
	
	public void setHtmlFooter(final String htmlFooter)
	{
		this.htmlFooter = htmlFooter;
	}
	
	@Override
	public Boolean getRemoveEmptySpaceBetweenRows()
	{
		return this.removeEmptySpaceBetweenRows;
	}
	
	public void setRemoveEmptySpaceBetweenRows(final Boolean removeEmptySpaceBetweenRows)
	{
		this.removeEmptySpaceBetweenRows = removeEmptySpaceBetweenRows;
	}
	
	@Override
	public Boolean getWhitePageBackground()
	{
		return this.whitePageBackground;
	}
	
	public void setWhitePageBackground(final Boolean whitePageBackground)
	{
		this.whitePageBackground = whitePageBackground;
	}
	
	@Override
	public Boolean getUsingImagesToAlign()
	{
		return this.usingImagesToAlign;
	}
	
	public void setUsingImagesToAlign(final Boolean usingImagesToAlign)
	{
		this.usingImagesToAlign = usingImagesToAlign;
	}
	
	@Override
	public Boolean getWrapBreakWord()
	{
		return this.wrapBreakWord;
	}
	
	public void setWrapBreakWord(final Boolean wrapBreakWord)
	{
		this.wrapBreakWord = wrapBreakWord;
	}
	
	@Override
	public SizeUnit getSizeUnit()
	{
		return this.sizeUnit;
	}
	
	public void setSizeUnit(final SizeUnit sizeUnit)
	{
		this.sizeUnit = sizeUnit;
	}
	
	@Override
	public Boolean getFramesAsNestedTables()
	{
		return this.framesAsNestedTables;
	}
	
	public void setFramesAsNestedTables(final Boolean framesAsNestedTables)
	{
		this.framesAsNestedTables = framesAsNestedTables;
	}
	
	@Override
	public Boolean getIgnorePageMargins()
	{
		return this.ignorePageMargins;
	}
	
	public void setIgnorePageMargins(final Boolean ignorePageMargins)
	{
		this.ignorePageMargins = ignorePageMargins;
	}
	
	@Override
	public HtmlBorderCollapseEnum getBorderCollapse()
	{
		return this.borderCollapse;
	}
	
	public void setBorderCollapse(final HtmlBorderCollapseEnum borderCollapse)
	{
		this.borderCollapse = borderCollapse;
	}
	
	@Override
	public Boolean getAccessibleHtml()
	{
		return this.accessibleHtml;
	}
	
	public void setAccessibleHtml(final Boolean accessibleHtml)
	{
		this.accessibleHtml = accessibleHtml;
	}
	
	@Override
	public Float getZoomRatio()
	{
		return this.zoomRatio;
	}
	
	public void setZoomRatio(final Float zoomRatio)
	{
		this.zoomRatio = zoomRatio;
	}
	
	@Override
	public Boolean getIgnoreHyperLink()
	{
		return this.ignoreHyperLink;
	}
	
	public void setIgnoreHyperLink(final Boolean ignoreHyperLink)
	{
		this.ignoreHyperLink = ignoreHyperLink;
	}
	
	@Override
	public Boolean getFlushOutput()
	{
		return this.flushOutput;
	}
	
	public void setFlushOutput(final Boolean flushOutput)
	{
		this.flushOutput = flushOutput;
	}
}
