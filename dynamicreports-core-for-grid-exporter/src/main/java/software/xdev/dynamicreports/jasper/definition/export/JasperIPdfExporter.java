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

import java.util.List;

import software.xdev.dynamicreports.jasper.constant.PdfPermission;
import software.xdev.dynamicreports.jasper.constant.PdfVersion;
import software.xdev.dynamicreports.report.constant.PdfPrintScaling;
import software.xdev.dynamicreports.report.constant.PdfaConformance;


public interface JasperIPdfExporter extends JasperIExporter
{
	
	public Boolean getCreatingBatchModeBookmarks();
	
	public Boolean getCompressed();
	
	public Boolean getEncrypted();
	
	public Boolean getBitKey128();
	
	public String getUserPassword();
	
	public String getOwnerPassword();
	
	public List<PdfPermission> getPermissions();
	
	public PdfVersion getPdfVersion();
	
	public String getMetadataTitle();
	
	public String getMetadataAuthor();
	
	public String getMetadataSubject();
	
	public String getMetadataKeyWords();
	
	public String getMetadataCreator();
	
	public Boolean getForceSvgShapes();
	
	public String getPdfJavaScript();
	
	public Boolean getTagged();
	
	public String getTagLanguage();
	
	public Boolean getCollapseMissingBookmarkLevels();
	
	public Boolean getSizePageToContent();
	
	public Boolean getIgnoreHyperLink();
	
	public Boolean getForceLineBreakPolicy();
	
	public PdfPrintScaling getPrintScaling();
	
	public PdfaConformance getPdfaConformance();
	
	public String getIccProfilePath();
	
	public String getAllowedPermissionsHint();
	
	public String getDeniedPermissionsHint();
	
	public Boolean getDisplayMetadataTitle();
}
