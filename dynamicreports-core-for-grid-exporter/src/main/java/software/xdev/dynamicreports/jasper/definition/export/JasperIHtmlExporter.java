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
package software.xdev.dynamicreports.jasper.definition.export;

import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import software.xdev.dynamicreports.jasper.constant.SizeUnit;


public interface JasperIHtmlExporter extends JasperIExporter
{
	
	public Boolean getOutputImagesToDir();
	
	public String getImagesDirName();
	
	public String getImagesURI();
	
	public String getHtmlHeader();
	
	public String getBetweenPagesHtml();
	
	public String getHtmlFooter();
	
	public Boolean getRemoveEmptySpaceBetweenRows();
	
	public Boolean getWhitePageBackground();
	
	public Boolean getUsingImagesToAlign();
	
	public Boolean getWrapBreakWord();
	
	public SizeUnit getSizeUnit();
	
	public Boolean getFramesAsNestedTables();
	
	public Boolean getIgnorePageMargins();
	
	public HtmlBorderCollapseEnum getBorderCollapse();
	
	public Boolean getAccessibleHtml();
	
	public Float getZoomRatio();
	
	public Boolean getIgnoreHyperLink();
	
	public Boolean getFlushOutput();
}
