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
package software.xdev.dynamicreports.jasper.transformation;

import java.io.File;

import net.sf.jasperreports.engine.export.FileHtmlResourceHandler;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.HtmlResourceHandler;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.AbstractXlsExporterConfiguration;
import net.sf.jasperreports.export.AbstractXlsReportConfiguration;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.SimpleOdsExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleOdtExporterConfiguration;
import net.sf.jasperreports.export.SimpleOdtReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimplePptxExporterConfiguration;
import net.sf.jasperreports.export.SimplePptxReportConfiguration;
import net.sf.jasperreports.export.SimpleReportExportConfiguration;
import net.sf.jasperreports.export.SimpleRtfExporterConfiguration;
import net.sf.jasperreports.export.SimpleRtfReportConfiguration;
import net.sf.jasperreports.export.SimpleTextExporterConfiguration;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;
import software.xdev.dynamicreports.design.transformation.StyleResolver;
import software.xdev.dynamicreports.jasper.definition.export.JasperICsvExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIDocxExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIExcelExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIHtmlExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIOdsExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIOdtExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIPdfExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIPptxExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIRtfExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperITextExporter;
import software.xdev.dynamicreports.jasper.definition.export.JasperIXlsxExporter;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.report.base.style.DRFont;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.exception.DRException;


/**
 * <p>ExporterTransform class.</p>
 *
 * @author Ricardo Mariaca
 */
public class ExporterTransform
{
    private final JasperIExporter jasperExporter;
    
    /**
     * <p>Constructor for ExporterTransform.</p>
     *
     * @param jasperExporter a {@link software.xdev.dynamicreports.jasper.definition.export.JasperIExporter} object.
     */
    public ExporterTransform(final JasperIExporter jasperExporter)
    {
        this.jasperExporter = jasperExporter;
    }
    
    /**
     * <p>transform.</p>
     *
     * @return a {@link net.sf.jasperreports.export.Exporter} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public Exporter<?, ?, ?, ?> transform() throws DRException
    {
        final Exporter<?, ?, ?, ?> jrExporter;
        
        if(this.jasperExporter instanceof JasperICsvExporter)
        {
            jrExporter = this.csv((JasperICsvExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIDocxExporter)
        {
            jrExporter = this.docx((JasperIDocxExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIXlsxExporter)
        {
            jrExporter = this.xlsx((JasperIXlsxExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIHtmlExporter)
        {
            jrExporter = this.html((JasperIHtmlExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIOdsExporter)
        {
            jrExporter = this.ods((JasperIOdsExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIOdtExporter)
        {
            jrExporter = this.odt((JasperIOdtExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIPdfExporter)
        {
            jrExporter = this.pdf((JasperIPdfExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIRtfExporter)
        {
            jrExporter = this.rtf((JasperIRtfExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperITextExporter)
        {
            jrExporter = this.text((JasperITextExporter)this.jasperExporter);
        }
        else if(this.jasperExporter instanceof JasperIPptxExporter)
        {
            jrExporter = this.pptx((JasperIPptxExporter)this.jasperExporter);
        }
        else
        {
            throw new JasperDesignException("Exporter " + this.jasperExporter.getClass().getName() + " not supported");
        }
        
        return jrExporter;
    }
    
    private SimpleWriterExporterOutput simpleWriterExporterOutput(final JasperIExporter jasperExporter)
    {
        if(jasperExporter.getOutputWriter() != null)
        {
            return new SimpleWriterExporterOutput(jasperExporter.getOutputWriter());
        }
        if(jasperExporter.getOutputStream() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleWriterExporterOutput(
                    jasperExporter.getOutputStream(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleWriterExporterOutput(jasperExporter.getOutputStream());
            }
        }
        if(jasperExporter.getOutputFile() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleWriterExporterOutput(
                    jasperExporter.getOutputFile(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleWriterExporterOutput(jasperExporter.getOutputFile());
            }
        }
        if(jasperExporter.getOutputFileName() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleWriterExporterOutput(
                    jasperExporter.getOutputFileName(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleWriterExporterOutput(jasperExporter.getOutputFileName());
            }
        }
        return null;
    }
    
    private SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput(final JasperIExporter jasperExporter)
    {
        if(jasperExporter.getOutputStream() != null)
        {
            return new SimpleOutputStreamExporterOutput(jasperExporter.getOutputStream());
        }
        if(jasperExporter.getOutputFile() != null)
        {
            return new SimpleOutputStreamExporterOutput(jasperExporter.getOutputFile());
        }
        if(jasperExporter.getOutputFileName() != null)
        {
            return new SimpleOutputStreamExporterOutput(jasperExporter.getOutputFileName());
        }
        return null;
    }
    
    private SimpleHtmlExporterOutput simpleHtmlExporterOutput(final JasperIExporter jasperExporter)
    {
        if(jasperExporter.getOutputWriter() != null)
        {
            return new SimpleHtmlExporterOutput(jasperExporter.getOutputWriter());
        }
        if(jasperExporter.getOutputStream() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleHtmlExporterOutput(
                    jasperExporter.getOutputStream(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleHtmlExporterOutput(jasperExporter.getOutputStream());
            }
        }
        if(jasperExporter.getOutputFile() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleHtmlExporterOutput(
                    jasperExporter.getOutputFile(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleHtmlExporterOutput(jasperExporter.getOutputFile());
            }
        }
        if(jasperExporter.getOutputFileName() != null)
        {
            if(jasperExporter.getCharacterEncoding() != null)
            {
                return new SimpleHtmlExporterOutput(
                    jasperExporter.getOutputFileName(),
                    jasperExporter.getCharacterEncoding());
            }
            else
            {
                return new SimpleHtmlExporterOutput(jasperExporter.getOutputFileName());
            }
        }
        return null;
    }
    
    private void reportExportConfiguration(
        final SimpleReportExportConfiguration reportExportConfiguration,
        final JasperIExporter jasperExporter)
    {
        if(jasperExporter.getPageIndex() != null)
        {
            reportExportConfiguration.setPageIndex(jasperExporter.getPageIndex());
        }
        if(jasperExporter.getStartPageIndex() != null)
        {
            reportExportConfiguration.setStartPageIndex(jasperExporter.getStartPageIndex());
        }
        if(jasperExporter.getEndPageIndex() != null)
        {
            reportExportConfiguration.setEndPageIndex(jasperExporter.getEndPageIndex());
        }
        if(jasperExporter.getOffsetX() != null)
        {
            reportExportConfiguration.setOffsetX(jasperExporter.getOffsetX());
        }
        if(jasperExporter.getOffsetY() != null)
        {
            reportExportConfiguration.setOffsetY(jasperExporter.getOffsetY());
        }
    }
    
    private JRPptxExporter pptx(final JasperIPptxExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimplePptxReportConfiguration reportExportConfiguration = new SimplePptxReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        final SimplePptxExporterConfiguration exporterConfiguration = new SimplePptxExporterConfiguration();
        
        final JRPptxExporter jrExporter = new JRPptxExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JRTextExporter text(final JasperITextExporter jasperExporter)
    {
        final SimpleWriterExporterOutput exporterOutput = this.simpleWriterExporterOutput(jasperExporter);
        final SimpleTextReportConfiguration reportExportConfiguration = new SimpleTextReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getCharacterWidth() != null)
        {
            reportExportConfiguration.setCharWidth(jasperExporter.getCharacterWidth());
        }
        else
        {
            final DRFont font = Defaults.getDefaults().getFont();
            reportExportConfiguration.setCharWidth((float)StyleResolver.getFontWidth(font));
        }
        if(jasperExporter.getCharacterHeight() != null)
        {
            reportExportConfiguration.setCharHeight(jasperExporter.getCharacterHeight());
        }
        else
        {
            final DRFont font = Defaults.getDefaults().getFont();
            reportExportConfiguration.setCharHeight((float)StyleResolver.getFontHeight(font));
        }
        if(jasperExporter.getPageWidthInChars() != null)
        {
            reportExportConfiguration.setPageWidthInChars(jasperExporter.getPageWidthInChars());
        }
        if(jasperExporter.getPageHeightInChars() != null)
        {
            reportExportConfiguration.setPageHeightInChars(jasperExporter.getPageHeightInChars());
        }
        final SimpleTextExporterConfiguration exporterConfiguration = new SimpleTextExporterConfiguration();
        if(jasperExporter.getPageSeparator() != null)
        {
            exporterConfiguration.setPageSeparator(jasperExporter.getPageSeparator());
        }
        if(jasperExporter.getLineSeparator() != null)
        {
            exporterConfiguration.setLineSeparator(jasperExporter.getLineSeparator());
        }
        if(jasperExporter.getTrimLineRight() != null)
        {
            exporterConfiguration.setTrimLineRight(jasperExporter.getTrimLineRight());
        }
        
        final JRTextExporter jrExporter = new JRTextExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JRRtfExporter rtf(final JasperIRtfExporter jasperExporter)
    {
        final SimpleWriterExporterOutput exporterOutput = this.simpleWriterExporterOutput(jasperExporter);
        final SimpleRtfReportConfiguration reportExportConfiguration = new SimpleRtfReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        final SimpleRtfExporterConfiguration exporterConfiguration = new SimpleRtfExporterConfiguration();
        
        final JRRtfExporter jrExporter = new JRRtfExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JRPdfExporter pdf(final JasperIPdfExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimplePdfReportConfiguration reportExportConfiguration = new SimplePdfReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getForceSvgShapes() != null)
        {
            reportExportConfiguration.setForceSvgShapes(jasperExporter.getForceSvgShapes());
        }
        if(jasperExporter.getCollapseMissingBookmarkLevels() != null)
        {
            reportExportConfiguration.setCollapseMissingBookmarkLevels(jasperExporter.getCollapseMissingBookmarkLevels());
        }
        if(jasperExporter.getSizePageToContent() != null)
        {
            reportExportConfiguration.setSizePageToContent(jasperExporter.getSizePageToContent());
        }
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        if(jasperExporter.getForceLineBreakPolicy() != null)
        {
            reportExportConfiguration.setForceLineBreakPolicy(jasperExporter.getForceLineBreakPolicy());
        }
        final SimplePdfExporterConfiguration exporterConfiguration = new SimplePdfExporterConfiguration();
        if(jasperExporter.getCreatingBatchModeBookmarks() != null)
        {
            exporterConfiguration.setCreatingBatchModeBookmarks(jasperExporter.getCreatingBatchModeBookmarks());
        }
        if(jasperExporter.getCompressed() != null)
        {
            exporterConfiguration.setCompressed(jasperExporter.getCompressed());
        }
        if(jasperExporter.getEncrypted() != null)
        {
            exporterConfiguration.setEncrypted(jasperExporter.getEncrypted());
        }
        if(jasperExporter.getBitKey128() != null)
        {
            exporterConfiguration.set128BitKey(jasperExporter.getBitKey128());
        }
        if(jasperExporter.getUserPassword() != null)
        {
            exporterConfiguration.setUserPassword(jasperExporter.getUserPassword());
        }
        if(jasperExporter.getOwnerPassword() != null)
        {
            exporterConfiguration.setOwnerPassword(jasperExporter.getOwnerPassword());
        }
        if(jasperExporter.getPermissions() != null && !jasperExporter.getPermissions().isEmpty())
        {
            exporterConfiguration.setPermissions(ConstantTransform.pdfPermission(jasperExporter.getPermissions()));
        }
        if(jasperExporter.getPdfVersion() != null)
        {
            exporterConfiguration.setPdfVersion(ConstantTransform.pdfVersion(jasperExporter.getPdfVersion()));
        }
        if(jasperExporter.getMetadataTitle() != null)
        {
            exporterConfiguration.setMetadataTitle(jasperExporter.getMetadataTitle());
        }
        if(jasperExporter.getMetadataAuthor() != null)
        {
            exporterConfiguration.setMetadataAuthor(jasperExporter.getMetadataAuthor());
        }
        if(jasperExporter.getMetadataSubject() != null)
        {
            exporterConfiguration.setMetadataSubject(jasperExporter.getMetadataSubject());
        }
        if(jasperExporter.getMetadataKeyWords() != null)
        {
            exporterConfiguration.setMetadataKeywords(jasperExporter.getMetadataKeyWords());
        }
        if(jasperExporter.getMetadataCreator() != null)
        {
            exporterConfiguration.setMetadataCreator(jasperExporter.getMetadataCreator());
        }
        if(jasperExporter.getPdfJavaScript() != null)
        {
            exporterConfiguration.setPdfJavaScript(jasperExporter.getPdfJavaScript());
        }
        if(jasperExporter.getTagged() != null)
        {
            exporterConfiguration.setTagged(jasperExporter.getTagged());
        }
        if(jasperExporter.getTagLanguage() != null)
        {
            exporterConfiguration.setTagLanguage(jasperExporter.getTagLanguage());
        }
        if(jasperExporter.getPrintScaling() != null)
        {
            exporterConfiguration.setPrintScaling(ConstantTransform.pdfPrintScaling(jasperExporter.getPrintScaling()));
        }
        if(jasperExporter.getPdfaConformance() != null)
        {
            exporterConfiguration.setPdfaConformance(ConstantTransform.pdfaConformance(jasperExporter.getPdfaConformance()));
        }
        if(jasperExporter.getIccProfilePath() != null)
        {
            exporterConfiguration.setIccProfilePath(jasperExporter.getIccProfilePath());
        }
        if(jasperExporter.getAllowedPermissionsHint() != null)
        {
            exporterConfiguration.setAllowedPermissionsHint(jasperExporter.getAllowedPermissionsHint());
        }
        if(jasperExporter.getDeniedPermissionsHint() != null)
        {
            exporterConfiguration.setDeniedPermissionsHint(jasperExporter.getDeniedPermissionsHint());
        }
        if(jasperExporter.getDisplayMetadataTitle() != null)
        {
            exporterConfiguration.setDisplayMetadataTitle(jasperExporter.getDisplayMetadataTitle());
        }
        
        final JRPdfExporter jrExporter = new JRPdfExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JROdtExporter odt(final JasperIOdtExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimpleOdtReportConfiguration reportExportConfiguration = new SimpleOdtReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getFlexibleRowHeight() != null)
        {
            reportExportConfiguration.setFlexibleRowHeight(jasperExporter.getFlexibleRowHeight());
        }
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        final SimpleOdtExporterConfiguration exporterConfiguration = new SimpleOdtExporterConfiguration();
        
        final JROdtExporter jrExporter = new JROdtExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JROdsExporter ods(final JasperIOdsExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimpleOdsReportConfiguration reportExportConfiguration = new SimpleOdsReportConfiguration();
        this.reportExcelExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getFlexibleRowHeight() != null)
        {
            reportExportConfiguration.setFlexibleRowHeight(jasperExporter.getFlexibleRowHeight());
        }
        final SimpleOdsExporterConfiguration exporterConfiguration = new SimpleOdsExporterConfiguration();
        this.reportExcelExporterConfiguration(exporterConfiguration, jasperExporter);
        
        final JROdsExporter jrExporter = new JROdsExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private HtmlExporter html(final JasperIHtmlExporter jasperExporter)
    {
        final SimpleHtmlExporterOutput exporterOutput = this.simpleHtmlExporterOutput(jasperExporter);
        final Boolean outputImagesToDir = jasperExporter.getOutputImagesToDir();
        final String imagesUri = jasperExporter.getImagesURI();
        HtmlResourceHandler imageHandler = null;
        if(outputImagesToDir == null || outputImagesToDir)
        {
            File imagesDir = null;
            final String imagesDirName = jasperExporter.getImagesDirName();
            if(imagesDirName != null)
            {
                imagesDir = new File(imagesDirName);
            }
            if(imagesDir != null)
            {
                imageHandler = new FileHtmlResourceHandler(
                    imagesDir,
                    imagesUri == null ? imagesDir.getName() + "/{0}" : imagesUri + "{0}");
            }
        }
        if(imageHandler == null && imagesUri != null)
        {
            imageHandler = new WebHtmlResourceHandler(imagesUri + "{0}");
        }
        if(imageHandler != null)
        {
            exporterOutput.setImageHandler(imageHandler);
        }
        final SimpleHtmlReportConfiguration reportExportConfiguration = new SimpleHtmlReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getRemoveEmptySpaceBetweenRows() != null)
        {
            reportExportConfiguration.setRemoveEmptySpaceBetweenRows(jasperExporter.getRemoveEmptySpaceBetweenRows());
        }
        if(jasperExporter.getWhitePageBackground() != null)
        {
            reportExportConfiguration.setWhitePageBackground(jasperExporter.getWhitePageBackground());
        }
        if(jasperExporter.getWrapBreakWord() != null)
        {
            reportExportConfiguration.setWrapBreakWord(jasperExporter.getWrapBreakWord());
        }
        if(jasperExporter.getSizeUnit() != null)
        {
            reportExportConfiguration.setSizeUnit(ConstantTransform.sizeUnit(jasperExporter.getSizeUnit()));
        }
        if(jasperExporter.getIgnorePageMargins() != null)
        {
            reportExportConfiguration.setIgnorePageMargins(jasperExporter.getIgnorePageMargins());
        }
        if(jasperExporter.getBorderCollapse() != null)
        {
            reportExportConfiguration.setBorderCollapse(jasperExporter.getBorderCollapse());
        }
        if(jasperExporter.getAccessibleHtml() != null)
        {
            reportExportConfiguration.setAccessibleHtml(jasperExporter.getAccessibleHtml());
        }
        if(jasperExporter.getZoomRatio() != null)
        {
            reportExportConfiguration.setZoomRatio(jasperExporter.getZoomRatio());
        }
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        final SimpleHtmlExporterConfiguration exporterConfiguration = new SimpleHtmlExporterConfiguration();
        if(jasperExporter.getHtmlHeader() != null)
        {
            exporterConfiguration.setHtmlHeader(jasperExporter.getHtmlHeader());
        }
        if(jasperExporter.getBetweenPagesHtml() != null)
        {
            exporterConfiguration.setBetweenPagesHtml(jasperExporter.getBetweenPagesHtml());
        }
        if(jasperExporter.getHtmlFooter() != null)
        {
            exporterConfiguration.setHtmlFooter(jasperExporter.getHtmlFooter());
        }
        if(jasperExporter.getFlushOutput() != null)
        {
            exporterConfiguration.setFlushOutput(jasperExporter.getFlushOutput());
        }
        
        final HtmlExporter jrExporter = new HtmlExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private void reportExcelExportConfiguration(
        final AbstractXlsReportConfiguration reportExportConfiguration,
        final JasperIExcelExporter jasperExporter)
    {
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getOnePagePerSheet() != null)
        {
            reportExportConfiguration.setOnePagePerSheet(jasperExporter.getOnePagePerSheet());
        }
        if(jasperExporter.getRemoveEmptySpaceBetweenRows() != null)
        {
            reportExportConfiguration.setRemoveEmptySpaceBetweenRows(jasperExporter.getRemoveEmptySpaceBetweenRows());
        }
        if(jasperExporter.getRemoveEmptySpaceBetweenColumns() != null)
        {
            reportExportConfiguration.setRemoveEmptySpaceBetweenColumns(jasperExporter.getRemoveEmptySpaceBetweenColumns());
        }
        if(jasperExporter.getWhitePageBackground() != null)
        {
            reportExportConfiguration.setWhitePageBackground(jasperExporter.getWhitePageBackground());
        }
        if(jasperExporter.getDetectCellType() != null)
        {
            reportExportConfiguration.setDetectCellType(jasperExporter.getDetectCellType());
        }
        if(jasperExporter.getSheetNames() != null && !jasperExporter.getSheetNames().isEmpty())
        {
            final String[] sheetNames =
                jasperExporter.getSheetNames().toArray(new String[jasperExporter.getSheetNames().size()]);
            reportExportConfiguration.setSheetNames(sheetNames);
        }
        if(jasperExporter.getFontSizeFixEnabled() != null)
        {
            reportExportConfiguration.setFontSizeFixEnabled(jasperExporter.getFontSizeFixEnabled());
        }
        if(jasperExporter.getImageBorderFixEnabled() != null)
        {
            reportExportConfiguration.setImageBorderFixEnabled(jasperExporter.getImageBorderFixEnabled());
        }
        if(jasperExporter.getMaxRowsPerSheet() != null)
        {
            reportExportConfiguration.setMaxRowsPerSheet(jasperExporter.getMaxRowsPerSheet());
        }
        if(jasperExporter.getIgnoreGraphics() != null)
        {
            reportExportConfiguration.setIgnoreGraphics(jasperExporter.getIgnoreGraphics());
        }
        if(jasperExporter.getCollapseRowSpan() != null)
        {
            reportExportConfiguration.setCollapseRowSpan(jasperExporter.getCollapseRowSpan());
        }
        if(jasperExporter.getIgnoreCellBorder() != null)
        {
            reportExportConfiguration.setIgnoreCellBorder(jasperExporter.getIgnoreCellBorder());
        }
        if(jasperExporter.getIgnoreCellBackground() != null)
        {
            reportExportConfiguration.setIgnoreCellBackground(jasperExporter.getIgnoreCellBackground());
        }
        if(jasperExporter.getPassword() != null)
        {
            reportExportConfiguration.setPassword(jasperExporter.getPassword());
        }
        if(jasperExporter.getIgnorePageMargins() != null)
        {
            reportExportConfiguration.setIgnorePageMargins(jasperExporter.getIgnorePageMargins());
        }
        if(jasperExporter.getWrapText() != null)
        {
            reportExportConfiguration.setWrapText(jasperExporter.getWrapText());
        }
        if(jasperExporter.getCellLocked() != null)
        {
            reportExportConfiguration.setCellLocked(jasperExporter.getCellLocked());
        }
        if(jasperExporter.getCellHidden() != null)
        {
            reportExportConfiguration.setCellHidden(jasperExporter.getCellHidden());
        }
        if(jasperExporter.getSheetHeaderLeft() != null)
        {
            reportExportConfiguration.setSheetHeaderLeft(jasperExporter.getSheetHeaderLeft());
        }
        if(jasperExporter.getSheetHeaderCenter() != null)
        {
            reportExportConfiguration.setSheetHeaderCenter(jasperExporter.getSheetHeaderCenter());
        }
        if(jasperExporter.getSheetHeaderRight() != null)
        {
            reportExportConfiguration.setSheetHeaderRight(jasperExporter.getSheetHeaderRight());
        }
        if(jasperExporter.getSheetFooterLeft() != null)
        {
            reportExportConfiguration.setSheetFooterLeft(jasperExporter.getSheetFooterLeft());
        }
        if(jasperExporter.getSheetFooterCenter() != null)
        {
            reportExportConfiguration.setSheetFooterCenter(jasperExporter.getSheetFooterCenter());
        }
        if(jasperExporter.getSheetFooterRight() != null)
        {
            reportExportConfiguration.setSheetFooterRight(jasperExporter.getSheetFooterRight());
        }
        if(jasperExporter.getFormatPatternsMap() != null)
        {
            reportExportConfiguration.setFormatPatternsMap(jasperExporter.getFormatPatternsMap());
        }
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        if(jasperExporter.getIgnoreAnchors() != null)
        {
            reportExportConfiguration.setIgnoreAnchors(jasperExporter.getIgnoreAnchors());
        }
        if(jasperExporter.getFitWidth() != null)
        {
            reportExportConfiguration.setFitWidth(jasperExporter.getFitWidth());
        }
        if(jasperExporter.getFitHeight() != null)
        {
            reportExportConfiguration.setFitHeight(jasperExporter.getFitHeight());
        }
        if(jasperExporter.getPageScale() != null)
        {
            reportExportConfiguration.setPageScale(jasperExporter.getPageScale());
        }
        if(jasperExporter.getSheetDirection() != null)
        {
            reportExportConfiguration.setSheetDirection(ConstantTransform.runDirection(jasperExporter.getSheetDirection()));
        }
        if(jasperExporter.getColumnWidthRatio() != null)
        {
            reportExportConfiguration.setColumnWidthRatio(jasperExporter.getColumnWidthRatio());
        }
        if(jasperExporter.getUseTimeZone() != null)
        {
            reportExportConfiguration.setUseTimeZone(jasperExporter.getUseTimeZone());
        }
        if(jasperExporter.getFirstPageNumber() != null)
        {
            reportExportConfiguration.setFirstPageNumber(jasperExporter.getFirstPageNumber());
        }
        if(jasperExporter.getShowGridLines() != null)
        {
            reportExportConfiguration.setShowGridLines(jasperExporter.getShowGridLines());
        }
        if(jasperExporter.getImageAnchorType() != null)
        {
            reportExportConfiguration.setImageAnchorType(ConstantTransform.imageAnchorType(jasperExporter.getImageAnchorType()));
        }
    }
    
    private void reportExcelExporterConfiguration(
        final AbstractXlsExporterConfiguration exporterConfiguration,
        final JasperIExcelExporter jasperExporter)
    {
        if(jasperExporter.getCreateCustomPalette() != null)
        {
            exporterConfiguration.setCreateCustomPalette(jasperExporter.getCreateCustomPalette());
        }
        if(jasperExporter.getWorkbookTemplate() != null)
        {
            exporterConfiguration.setWorkbookTemplate(jasperExporter.getWorkbookTemplate());
        }
        if(jasperExporter.getKeepWorkbookTemplateSheets() != null)
        {
            exporterConfiguration.setKeepWorkbookTemplateSheets(jasperExporter.getKeepWorkbookTemplateSheets());
        }
    }
    
    private JRXlsxExporter xlsx(final JasperIXlsxExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimpleXlsxReportConfiguration reportExportConfiguration = new SimpleXlsxReportConfiguration();
        this.reportExcelExportConfiguration(reportExportConfiguration, jasperExporter);
        final SimpleXlsxExporterConfiguration exporterConfiguration = new SimpleXlsxExporterConfiguration();
        this.reportExcelExporterConfiguration(exporterConfiguration, jasperExporter);
        if(jasperExporter.getMacroTemplate() != null)
        {
            exporterConfiguration.setMacroTemplate(jasperExporter.getMacroTemplate());
        }
        
        final JRXlsxExporter jrExporter = new JRXlsxExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JRDocxExporter docx(final JasperIDocxExporter jasperExporter)
    {
        final SimpleOutputStreamExporterOutput exporterOutput = this.simpleOutputStreamExporterOutput(jasperExporter);
        final SimpleDocxReportConfiguration reportExportConfiguration = new SimpleDocxReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        if(jasperExporter.getFramesAsNestedTables() != null)
        {
            reportExportConfiguration.setFramesAsNestedTables(jasperExporter.getFramesAsNestedTables());
        }
        if(jasperExporter.getFlexibleRowHeight() != null)
        {
            reportExportConfiguration.setFlexibleRowHeight(jasperExporter.getFlexibleRowHeight());
        }
        if(jasperExporter.getIgnoreHyperLink() != null)
        {
            reportExportConfiguration.setIgnoreHyperlink(jasperExporter.getIgnoreHyperLink());
        }
        final SimpleDocxExporterConfiguration exporterConfiguration = new SimpleDocxExporterConfiguration();
        
        final JRDocxExporter jrExporter = new JRDocxExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
    
    private JRCsvExporter csv(final JasperICsvExporter jasperExporter)
    {
        final SimpleWriterExporterOutput exporterOutput = this.simpleWriterExporterOutput(jasperExporter);
        final SimpleCsvReportConfiguration reportExportConfiguration = new SimpleCsvReportConfiguration();
        this.reportExportConfiguration(reportExportConfiguration, jasperExporter);
        final SimpleCsvExporterConfiguration exporterConfiguration = new SimpleCsvExporterConfiguration();
        if(jasperExporter.getFieldDelimiter() != null)
        {
            exporterConfiguration.setFieldDelimiter(jasperExporter.getFieldDelimiter());
        }
        if(jasperExporter.getRecordDelimiter() != null)
        {
            exporterConfiguration.setRecordDelimiter(jasperExporter.getRecordDelimiter());
        }
        
        final JRCsvExporter jrExporter = new JRCsvExporter();
        jrExporter.setExporterOutput(exporterOutput);
        jrExporter.setConfiguration(reportExportConfiguration);
        jrExporter.setConfiguration(exporterConfiguration);
        return jrExporter;
    }
}
