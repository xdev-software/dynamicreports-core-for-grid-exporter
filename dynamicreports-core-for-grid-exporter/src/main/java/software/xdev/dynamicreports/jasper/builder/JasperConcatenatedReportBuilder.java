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
package software.xdev.dynamicreports.jasper.builder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.Validate;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterConfiguration;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;
import software.xdev.dynamicreports.jasper.base.export.AbstractJasperExporter;
import software.xdev.dynamicreports.jasper.base.reporthandler.JasperReportBuilderHandler;
import software.xdev.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.Exporters;
import software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import software.xdev.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder;
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;
import software.xdev.dynamicreports.jasper.transformation.ExporterTransform;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.exception.DRException;

/**
 * This report builder allows concatenating several separated reports into one single document. Each report starts on a new page with its own page dimension.
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperConcatenatedReportBuilder implements Serializable {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private final JasperReportHandler jasperReportHandler;

    /**
     * <p>Constructor for JasperConcatenatedReportBuilder.</p>
     */
    public JasperConcatenatedReportBuilder() {
        this(new JasperReportBuilderHandler());
    }

    /**
     * <p>Constructor for JasperConcatenatedReportBuilder.</p>
     *
     * @param jasperReportHandler a {@link software.xdev.dynamicreports.jasper.definition.JasperReportHandler} object.
     */
    public JasperConcatenatedReportBuilder(final JasperReportHandler jasperReportHandler) {
        this.jasperReportHandler = jasperReportHandler;
    }

    /**
     * <p>concatenate.</p>
     *
     * @param jasperReportBuilders a {@link software.xdev.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     */
    public JasperConcatenatedReportBuilder concatenate(final JasperReportBuilder... jasperReportBuilders) {
        Validate.notNull(jasperReportBuilders, "jasperReportBuilders must not be null");
        Validate.noNullElements(jasperReportBuilders, "jasperReportBuilders must not contains null jasperReportBuilder");
        this.jasperReportHandler.concatenate(jasperReportBuilders);
        return this;
    }

    /**
     * <p>toPng.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toPng(final OutputStream outputStream) throws DRException {
        return this.toPng(outputStream, 1);
    }

    /**
     * <p>toPng.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @param zoom         a float.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    @SuppressWarnings("java:S2184") // FP: int - int != float?
    public JasperConcatenatedReportBuilder toPng(final OutputStream outputStream, final float zoom) throws DRException {
        Validate.notNull(outputStream, "outputStream must not be null");
        Validate.isTrue(zoom > 0, "zoom must be > 0");

        int maxWidth = 0;
        int maxHeight = 0;

        for (final JasperPrint jasperPrint : this.jasperReportHandler.getPrintList()) {
            final int pages = jasperPrint.getPages().size();
            final int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
            maxWidth += pageWidth * pages + pages - 1 + 2;
            final int height = (int) (jasperPrint.getPageHeight() * zoom) + 2;
            if (height > maxHeight) {
                maxHeight = height;
            }
        }

        final Image image = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(new Rectangle2D.Float(1, 1, maxWidth - 1, maxHeight - 1));

        int offset = 1;
        for (final JasperPrint jasperPrint : this.jasperReportHandler.getPrintList()) {
            final int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
            final int pageHeight = (int) (jasperPrint.getPageHeight() * zoom);
            for (
                int i = 0; i < jasperPrint.getPages().size(); i++) {
                try {
                    final SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
                    final SimpleGraphics2DExporterOutput exporterOutput = new SimpleGraphics2DExporterOutput();
                    final Image pageImage = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
                    exporterOutput.setGraphics2D((Graphics2D) pageImage.getGraphics());
                    final SimpleGraphics2DReportConfiguration reportExportConfiguration = new SimpleGraphics2DReportConfiguration();
                    reportExportConfiguration.setPageIndex(i);
                    reportExportConfiguration.setZoomRatio(zoom);
                    final SimpleGraphics2DExporterConfiguration exporterConfiguration = new SimpleGraphics2DExporterConfiguration();

                    final JRGraphics2DExporter jrExporter = new JRGraphics2DExporter();
                    jrExporter.setExporterInput(exporterInput);
                    jrExporter.setExporterOutput(exporterOutput);
                    jrExporter.setConfiguration(reportExportConfiguration);
                    jrExporter.setConfiguration(exporterConfiguration);

                    jrExporter.exportReport();
                    ((Graphics2D) image.getGraphics()).drawImage(pageImage, offset, 1, null);
                    offset += pageWidth + 1;
                } catch (final JRException e) {
                    throw new DRException(e);
                }
            }
        }
        try {
            ImageIO.write((RenderedImage) image, "png", outputStream);
        } catch (final IOException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>continuousPageNumbering.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     */
    public JasperConcatenatedReportBuilder continuousPageNumbering() {
        return this.setContinuousPageNumbering(true);
    }

    /**
     * <p>setContinuousPageNumbering.</p>
     *
     * @param continuousPageNumbering a boolean.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     */
    public JasperConcatenatedReportBuilder setContinuousPageNumbering(final boolean continuousPageNumbering) {
        this.jasperReportHandler.setContinuousPageNumbering(continuousPageNumbering);
        return this;
    }

    // csv

    /**
     * <p>toCsv.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toCsv(final OutputStream outputStream) throws DRException {
        return this.toCsv(Exporters.csvExporter(outputStream));
    }

    /**
     * <p>toCsv.</p>
     *
     * @param csvExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toCsv(final JasperCsvExporterBuilder csvExporterBuilder) throws DRException {
        return this.export(csvExporterBuilder);
    }

    // docx

    /**
     * <p>toDocx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toDocx(final OutputStream outputStream) throws DRException {
        return this.toDocx(Exporters.docxExporter(outputStream));
    }

    /**
     * <p>toDocx.</p>
     *
     * @param docxExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toDocx(final JasperDocxExporterBuilder docxExporterBuilder) throws DRException {
        return this.export(docxExporterBuilder);
    }

    // html

    /**
     * <p>toHtml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toHtml(final OutputStream outputStream) throws DRException {
        return this.toHtml(Exporters.htmlExporter(outputStream));
    }

    /**
     * <p>toHtml.</p>
     *
     * @param htmlExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toHtml(final JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException {
        return this.export(htmlExporterBuilder);
    }

    // ods

    /**
     * <p>toOds.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toOds(final OutputStream outputStream) throws DRException {
        return this.toOds(Exporters.odsExporter(outputStream));
    }

    /**
     * <p>toOds.</p>
     *
     * @param odsExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toOds(final JasperOdsExporterBuilder odsExporterBuilder) throws DRException {
        return this.export(odsExporterBuilder);
    }

    // odt

    /**
     * <p>toOdt.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toOdt(final OutputStream outputStream) throws DRException {
        return this.toOdt(Exporters.odtExporter(outputStream));
    }

    /**
     * <p>toOdt.</p>
     *
     * @param odtExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toOdt(final JasperOdtExporterBuilder odtExporterBuilder) throws DRException {
        return this.export(odtExporterBuilder);
    }

    // pdf

    /**
     * <p>toPdf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toPdf(final OutputStream outputStream) throws DRException {
        return this.toPdf(Exporters.pdfExporter(outputStream));
    }

    /**
     * <p>toPdf.</p>
     *
     * @param pdfExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toPdf(final JasperPdfExporterBuilder pdfExporterBuilder) throws DRException {
        return this.export(pdfExporterBuilder);
    }

    // rtf

    /**
     * <p>toRtf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toRtf(final OutputStream outputStream) throws DRException {
        return this.toRtf(Exporters.rtfExporter(outputStream));
    }

    /**
     * <p>toRtf.</p>
     *
     * @param rtfExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toRtf(final JasperRtfExporterBuilder rtfExporterBuilder) throws DRException {
        return this.export(rtfExporterBuilder);
    }

    // text

    /**
     * <p>toText.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toText(final OutputStream outputStream) throws DRException {
        return this.toText(Exporters.textExporter(outputStream));
    }

    /**
     * <p>toText.</p>
     *
     * @param textExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toText(final JasperTextExporterBuilder textExporterBuilder) throws DRException {
        return this.export(textExporterBuilder);
    }

    // xlsx

    /**
     * <p>toXlsx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toXlsx(final OutputStream outputStream) throws DRException {
        return this.toXlsx(Exporters.xlsxExporter(outputStream));
    }

    /**
     * <p>toXlsx.</p>
     *
     * @param xlsxExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toXlsx(final JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException {
        return this.export(xlsxExporterBuilder);
    }

    // xml

    /**
     * <p>toXml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toXml(final OutputStream outputStream) throws DRException {
        return this.toXml(Exporters.xmlExporter(outputStream));
    }

    /**
     * <p>toXml.</p>
     *
     * @param xmlExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toXml(final JasperXmlExporterBuilder xmlExporterBuilder) throws DRException {
        return this.export(xmlExporterBuilder);
    }

    // pptx

    /**
     * <p>toPptx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toPptx(final OutputStream outputStream) throws DRException {
        return this.toPptx(Exporters.pptxExporter(outputStream));
    }

    /**
     * <p>toPptx.</p>
     *
     * @param pptxExporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder toPptx(final JasperPptxExporterBuilder pptxExporterBuilder) throws DRException {
        return this.export(pptxExporterBuilder);
    }

    /**
     * <p>export.</p>
     *
     * @param exporterBuilder a {@link software.xdev.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public JasperConcatenatedReportBuilder export(final AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder) throws DRException {
        Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
        try {
            final ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
            @SuppressWarnings("unchecked")
            final Exporter<ExporterInput, ?, ?, ?> exporter = (Exporter<ExporterInput, ?, ?, ?>) exporterTransform.transform();
            exporter.setExporterInput(SimpleExporterInput.getInstance(this.jasperReportHandler.getPrintList()));
            exporter.exportReport();
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }
}
