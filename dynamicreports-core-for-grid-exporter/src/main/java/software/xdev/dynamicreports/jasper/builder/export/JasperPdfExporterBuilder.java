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

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.jasper.base.export.JasperPdfExporter;
import software.xdev.dynamicreports.jasper.constant.PdfPermission;
import software.xdev.dynamicreports.jasper.constant.PdfVersion;
import software.xdev.dynamicreports.report.constant.PdfPrintScaling;
import software.xdev.dynamicreports.report.constant.PdfaConformance;


public class JasperPdfExporterBuilder extends AbstractJasperExporterBuilder<JasperPdfExporterBuilder, JasperPdfExporter>
{

	protected JasperPdfExporterBuilder()
	{
		super(new JasperPdfExporter());
	}
	
	public JasperPdfExporterBuilder setCreatingBatchModeBookmarks(final Boolean creatingBatchModeBookmarks)
	{
		this.getObject().setCreatingBatchModeBookmarks(creatingBatchModeBookmarks);
		return this;
	}
	
	public JasperPdfExporterBuilder setCompressed(final Boolean compressed)
	{
		this.getObject().setCompressed(compressed);
		return this;
	}
	
	public JasperPdfExporterBuilder setEncrypted(final Boolean encrypted)
	{
		this.getObject().setEncrypted(encrypted);
		return this;
	}
	
	public JasperPdfExporterBuilder setBitKey128(final Boolean bitKey128)
	{
		this.getObject().setBitKey128(bitKey128);
		return this;
	}
	
	public JasperPdfExporterBuilder setUserPassword(final String userPassword)
	{
		this.getObject().setUserPassword(userPassword);
		return this;
	}
	
	public JasperPdfExporterBuilder setOwnerPassword(final String ownerPassword)
	{
		this.getObject().setOwnerPassword(ownerPassword);
		return this;
	}
	
	public JasperPdfExporterBuilder permissions(final PdfPermission... permissions)
	{
		return this.addPermission(permissions);
	}
	
	public JasperPdfExporterBuilder addPermission(final PdfPermission... permissions)
	{
		Validate.notNull(permissions, "permissions must not be null");
		Validate.noNullElements(permissions, "permissions must not contains null permission");
		for(final PdfPermission permission : permissions)
		{
			this.getObject().addPermission(permission);
		}
		return this;
	}
	
	public JasperPdfExporterBuilder setPdfVersion(final PdfVersion pdfVersion)
	{
		this.getObject().setPdfVersion(pdfVersion);
		return this;
	}
	
	public JasperPdfExporterBuilder setMetadataTitle(final String metadataTitle)
	{
		this.getObject().setMetadataTitle(metadataTitle);
		return this;
	}
	
	public JasperPdfExporterBuilder setMetadataAuthor(final String metadataAuthor)
	{
		this.getObject().setMetadataAuthor(metadataAuthor);
		return this;
	}
	
	public JasperPdfExporterBuilder setMetadataSubject(final String metadataSubject)
	{
		this.getObject().setMetadataSubject(metadataSubject);
		return this;
	}
	
	public JasperPdfExporterBuilder setMetadataKeyWords(final String metadataKeyWords)
	{
		this.getObject().setMetadataKeyWords(metadataKeyWords);
		return this;
	}
	
	public JasperPdfExporterBuilder setMetadataCreator(final String metadataCreator)
	{
		this.getObject().setMetadataCreator(metadataCreator);
		return this;
	}
	
	public JasperPdfExporterBuilder setForceSvgShapes(final Boolean forceSvgShapes)
	{
		this.getObject().setForceSvgShapes(forceSvgShapes);
		return this;
	}
	
	public JasperPdfExporterBuilder setPdfJavaScript(final String pdfJavaScript)
	{
		this.getObject().setPdfJavaScript(pdfJavaScript);
		return this;
	}
	
	public JasperPdfExporterBuilder setTagged(final Boolean tagged)
	{
		this.getObject().setTagged(tagged);
		return this;
	}
	
	public JasperPdfExporterBuilder setTagLanguage(final String tagLanguage)
	{
		this.getObject().setTagLanguage(tagLanguage);
		return this;
	}
	
	public JasperPdfExporterBuilder setCollapseMissingBookmarkLevels(final Boolean collapseMissingBookmarkLevels)
	{
		this.getObject().setCollapseMissingBookmarkLevels(collapseMissingBookmarkLevels);
		return this;
	}
	
	public JasperPdfExporterBuilder setSizePageToContent(final Boolean sizePageToContent)
	{
		this.getObject().setSizePageToContent(sizePageToContent);
		return this;
	}
	
	public JasperPdfExporterBuilder setIgnoreHyperLink(final Boolean ignoreHyperLink)
	{
		this.getObject().setIgnoreHyperLink(ignoreHyperLink);
		return this;
	}
	
	public JasperPdfExporterBuilder setForceLineBreakPolicy(final Boolean forceLineBreakPolicy)
	{
		this.getObject().setForceLineBreakPolicy(forceLineBreakPolicy);
		return this;
	}
	
	public JasperPdfExporterBuilder setPrintScaling(final PdfPrintScaling printScaling)
	{
		this.getObject().setPrintScaling(printScaling);
		return this;
	}
	
	public JasperPdfExporterBuilder setPdfaConformance(final PdfaConformance pdfaConformance)
	{
		this.getObject().setPdfaConformance(pdfaConformance);
		return this;
	}
	
	public JasperPdfExporterBuilder setIccProfilePath(final String iccProfilePath)
	{
		this.getObject().setIccProfilePath(iccProfilePath);
		return this;
	}
	
	public JasperPdfExporterBuilder setAllowedPermissionsHint(final String allowedPermissionsHint)
	{
		this.getObject().setAllowedPermissionsHint(allowedPermissionsHint);
		return this;
	}
	
	public JasperPdfExporterBuilder setDeniedPermissionsHint(final String deniedPermissionsHint)
	{
		this.getObject().setDeniedPermissionsHint(deniedPermissionsHint);
		return this;
	}
	
	public JasperPdfExporterBuilder setDisplayMetadataTitle(final Boolean displayMetadataTitle)
	{
		this.getObject().setDisplayMetadataTitle(displayMetadataTitle);
		return this;
	}
}
