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

import java.util.ArrayList;
import java.util.List;

import software.xdev.dynamicreports.jasper.constant.PdfPermission;
import software.xdev.dynamicreports.jasper.constant.PdfVersion;
import software.xdev.dynamicreports.jasper.definition.export.JasperIPdfExporter;
import software.xdev.dynamicreports.report.constant.PdfPrintScaling;
import software.xdev.dynamicreports.report.constant.PdfaConformance;


public class JasperPdfExporter extends AbstractJasperExporter implements JasperIPdfExporter
{

	private Boolean creatingBatchModeBookmarks;
	private Boolean compressed;
	private Boolean encrypted;
	private Boolean bitKey128;
	private String userPassword;
	private String ownerPassword;
	private List<PdfPermission> permissions;
	private PdfVersion pdfVersion;
	private String metadataTitle;
	private String metadataAuthor;
	private String metadataSubject;
	private String metadataKeyWords;
	private String metadataCreator;
	private Boolean forceSvgShapes;
	private String pdfJavaScript;
	private Boolean tagged;
	private String tagLanguage;
	private Boolean collapseMissingBookmarkLevels;
	private Boolean sizePageToContent;
	private Boolean ignoreHyperLink;
	private Boolean forceLineBreakPolicy;
	private PdfPrintScaling printScaling;
	private PdfaConformance pdfaConformance;
	private String iccProfilePath;
	private String allowedPermissionsHint;
	private String deniedPermissionsHint;
	private Boolean displayMetadataTitle;
	
	public JasperPdfExporter()
	{
		this.permissions = new ArrayList<>();
	}
	
	@Override
	public Boolean getCreatingBatchModeBookmarks()
	{
		return this.creatingBatchModeBookmarks;
	}
	
	public void setCreatingBatchModeBookmarks(final Boolean creatingBatchModeBookmarks)
	{
		this.creatingBatchModeBookmarks = creatingBatchModeBookmarks;
	}
	
	@Override
	public Boolean getCompressed()
	{
		return this.compressed;
	}
	
	public void setCompressed(final Boolean compressed)
	{
		this.compressed = compressed;
	}
	
	@Override
	public Boolean getEncrypted()
	{
		return this.encrypted;
	}
	
	public void setEncrypted(final Boolean encrypted)
	{
		this.encrypted = encrypted;
	}
	
	@Override
	public Boolean getBitKey128()
	{
		return this.bitKey128;
	}
	
	public void setBitKey128(final Boolean bitKey128)
	{
		this.bitKey128 = bitKey128;
	}
	
	@Override
	public String getUserPassword()
	{
		return this.userPassword;
	}
	
	public void setUserPassword(final String userPassword)
	{
		this.userPassword = userPassword;
	}
	
	@Override
	public String getOwnerPassword()
	{
		return this.ownerPassword;
	}
	
	public void setOwnerPassword(final String ownerPassword)
	{
		this.ownerPassword = ownerPassword;
	}
	
	@Override
	public List<PdfPermission> getPermissions()
	{
		return this.permissions;
	}
	
	public void setPermissions(final List<PdfPermission> permissions)
	{
		this.permissions = permissions;
	}
	
	public void addPermission(final PdfPermission permission)
	{
		this.permissions.add(permission);
	}
	
	@Override
	public PdfVersion getPdfVersion()
	{
		return this.pdfVersion;
	}
	
	public void setPdfVersion(final PdfVersion pdfVersion)
	{
		this.pdfVersion = pdfVersion;
	}
	
	@Override
	public String getMetadataTitle()
	{
		return this.metadataTitle;
	}
	
	public void setMetadataTitle(final String metadataTitle)
	{
		this.metadataTitle = metadataTitle;
	}
	
	@Override
	public String getMetadataAuthor()
	{
		return this.metadataAuthor;
	}
	
	public void setMetadataAuthor(final String metadataAuthor)
	{
		this.metadataAuthor = metadataAuthor;
	}
	
	@Override
	public String getMetadataSubject()
	{
		return this.metadataSubject;
	}
	
	public void setMetadataSubject(final String metadataSubject)
	{
		this.metadataSubject = metadataSubject;
	}
	
	@Override
	public String getMetadataKeyWords()
	{
		return this.metadataKeyWords;
	}
	
	public void setMetadataKeyWords(final String metadataKeyWords)
	{
		this.metadataKeyWords = metadataKeyWords;
	}
	
	@Override
	public String getMetadataCreator()
	{
		return this.metadataCreator;
	}
	
	public void setMetadataCreator(final String metadataCreator)
	{
		this.metadataCreator = metadataCreator;
	}
	
	@Override
	public Boolean getForceSvgShapes()
	{
		return this.forceSvgShapes;
	}
	
	public void setForceSvgShapes(final Boolean forceSvgShapes)
	{
		this.forceSvgShapes = forceSvgShapes;
	}
	
	@Override
	public String getPdfJavaScript()
	{
		return this.pdfJavaScript;
	}
	
	public void setPdfJavaScript(final String pdfJavaScript)
	{
		this.pdfJavaScript = pdfJavaScript;
	}
	
	@Override
	public Boolean getTagged()
	{
		return this.tagged;
	}
	
	public void setTagged(final Boolean tagged)
	{
		this.tagged = tagged;
	}
	
	@Override
	public String getTagLanguage()
	{
		return this.tagLanguage;
	}
	
	public void setTagLanguage(final String tagLanguage)
	{
		this.tagLanguage = tagLanguage;
	}
	
	@Override
	public Boolean getCollapseMissingBookmarkLevels()
	{
		return this.collapseMissingBookmarkLevels;
	}
	
	public void setCollapseMissingBookmarkLevels(final Boolean collapseMissingBookmarkLevels)
	{
		this.collapseMissingBookmarkLevels = collapseMissingBookmarkLevels;
	}
	
	@Override
	public Boolean getSizePageToContent()
	{
		return this.sizePageToContent;
	}
	
	public void setSizePageToContent(final Boolean sizePageToContent)
	{
		this.sizePageToContent = sizePageToContent;
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
	public Boolean getForceLineBreakPolicy()
	{
		return this.forceLineBreakPolicy;
	}
	
	public void setForceLineBreakPolicy(final Boolean forceLineBreakPolicy)
	{
		this.forceLineBreakPolicy = forceLineBreakPolicy;
	}
	
	@Override
	public PdfPrintScaling getPrintScaling()
	{
		return this.printScaling;
	}
	
	public void setPrintScaling(final PdfPrintScaling printScaling)
	{
		this.printScaling = printScaling;
	}
	
	@Override
	public PdfaConformance getPdfaConformance()
	{
		return this.pdfaConformance;
	}
	
	public void setPdfaConformance(final PdfaConformance pdfaConformance)
	{
		this.pdfaConformance = pdfaConformance;
	}
	
	@Override
	public String getIccProfilePath()
	{
		return this.iccProfilePath;
	}
	
	public void setIccProfilePath(final String iccProfilePath)
	{
		this.iccProfilePath = iccProfilePath;
	}
	
	@Override
	public String getAllowedPermissionsHint()
	{
		return this.allowedPermissionsHint;
	}
	
	public void setAllowedPermissionsHint(final String allowedPermissionsHint)
	{
		this.allowedPermissionsHint = allowedPermissionsHint;
	}
	
	@Override
	public String getDeniedPermissionsHint()
	{
		return this.deniedPermissionsHint;
	}
	
	public void setDeniedPermissionsHint(final String deniedPermissionsHint)
	{
		this.deniedPermissionsHint = deniedPermissionsHint;
	}
	
	@Override
	public Boolean getDisplayMetadataTitle()
	{
		return this.displayMetadataTitle;
	}
	
	public void setDisplayMetadataTitle(final Boolean displayMetadataTitle)
	{
		this.displayMetadataTitle = displayMetadataTitle;
	}
}
