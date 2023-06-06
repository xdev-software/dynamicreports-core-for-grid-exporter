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
package net.sf.dynamicreports.jasper.builder;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.Validate;

import net.sf.dynamicreports.design.base.DRDesignReport;
import net.sf.dynamicreports.design.definition.DRIDesignReport;
import net.sf.dynamicreports.jasper.base.JasperReportDesign;
import net.sf.dynamicreports.jasper.base.export.AbstractJasperExporter;
import net.sf.dynamicreports.jasper.base.export.JasperImageExporter;
import net.sf.dynamicreports.jasper.base.tableofcontents.JasperTocReport;
import net.sf.dynamicreports.jasper.base.templatedesign.JasperEmptyTemplateDesign;
import net.sf.dynamicreports.jasper.base.templatedesign.JasperTemplateDesign;
import net.sf.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.Exporters;
import net.sf.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperImageExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperTextExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder;
import net.sf.dynamicreports.jasper.constant.ImageType;
import net.sf.dynamicreports.jasper.exception.JasperDesignException;
import net.sf.dynamicreports.jasper.transformation.ExporterTransform;
import net.sf.dynamicreports.jasper.transformation.JasperTransform;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.QueryBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.QueryLanguage;
import net.sf.dynamicreports.report.definition.DRITemplateDesign;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRRewindableDataSource;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterConfiguration;
import net.sf.jasperreports.export.SimpleGraphics2DExporterOutput;
import net.sf.jasperreports.export.SimpleGraphics2DReportConfiguration;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * The most used report builder for creating reports. It allows constructing and customizing the whole report content. A report consists of bands, columns, subtotals, groups, and other parts. Each
 * part is created and configured using a particular builder method and it's passed to the report builder instance.
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperReportBuilder extends ReportBuilder<JasperReportBuilder> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private JasperReportDesign reportDesign;
    private JasperDesign jasperDesign;
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private transient JRDataSource dataSource;
    private transient Connection connection;
    private transient JRVirtualizer virtualizer;
    private Integer startPageNumber;
    private Map<String, Object> parameters;

    /**
     * <p>Constructor for JasperReportBuilder.</p>
     */
    public JasperReportBuilder() {
        this.setTemplateDesign(new JasperEmptyTemplateDesign());
    }

    /**
     * <p>Setter for the field <code>startPageNumber</code>.</p>
     *
     * @param startPageNumber a {@link java.lang.Integer} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setStartPageNumber(final Integer startPageNumber) throws DRException {
        if (this.startPageNumber == startPageNumber) {
            return this;
        }
        this.startPageNumber = startPageNumber;
        this.rebuild();
        return this;
    }

    /**
     * Sets a data source object. Creates a new JRBeanCollectionDataSource data source object.
     *
     * @param collection - the collection values
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(final Collection<?> collection) {
        return this.setDataSource(new JRBeanCollectionDataSource(collection));
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param resultSet - the resultSet object
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(final ResultSet resultSet) {
        return this.setDataSource(new JRResultSetDataSource(resultSet));
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param sql        - the sql query
     * @param connection - the database connection
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(final String sql, final Connection connection) {
        Validate.notNull(sql, "sql must not be null");
        return this.setDataSource(DynamicReports.query(sql, QueryLanguage.SQL), connection);
    }

    /**
     * Sets a database data source. In this type of data source, data are retrieved from a database.
     *
     * @param query      - the query definition
     * @param connection - the database connection
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(final QueryBuilder query, final Connection connection) {
        Validate.notNull(query, "query must not be null");
        Validate.notNull(connection, "connection must not be null");
        this.getObject().setQuery(query.build());
        this.connection = connection;
        this.dataSource = null;
        return this;
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(final InputStream inputStream) throws DRException {
        return this.setTemplateDesign(new JasperTemplateDesign(inputStream));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param file a {@link java.io.File} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(final File file) throws DRException {
        return this.setTemplateDesign(new JasperTemplateDesign(file));
    }

    // template design

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(final String fileName) throws DRException {
        return this.setTemplateDesign(new JasperTemplateDesign(fileName));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param jasperDesign a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(final JasperDesign jasperDesign) throws DRException {
        return this.setTemplateDesign(new JasperTemplateDesign(jasperDesign));
    }

    /**
     * <p>setTemplateDesign.</p>
     *
     * @param jasperDesignUrl a {@link java.net.URL} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder setTemplateDesign(final URL jasperDesignUrl) throws DRException {
        return this.setTemplateDesign(new JasperTemplateDesign(jasperDesignUrl));
    }

    private JasperReportBuilder setTemplateDesign(final DRITemplateDesign<JasperDesign> templateDesign) {
        this.getObject().setTemplateDesign(templateDesign);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder setParameter(final String name, final Object value) {
        super.setParameter(name, value);
        this.parameters = null;
        this.jasperPrint = null;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder setParameters(final Map<String, Object> parameters) {
        super.setParameters(parameters);
        this.parameters = null;
        this.jasperPrint = null;
        return this;
    }

    /**
     * <p>rebuild.</p>
     *
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder rebuild() throws DRException {
        this.builded = false;
        this.reportDesign = null;
        this.jasperDesign = null;
        this.jasperReport = null;
        this.parameters = null;
        this.jasperPrint = null;
        if (this.dataSource != null && this.dataSource instanceof JRRewindableDataSource) {
            try {
                ((JRRewindableDataSource)this.dataSource).moveFirst();
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        return this;
    }

    private JasperReportDesign toJasperReportDesign() throws DRException {
        if (this.reportDesign == null) {
            final DRIDesignReport report = new DRDesignReport(this.build());
            this.reportDesign = new JasperReportDesign(report, this.startPageNumber);
            final JasperTransform jasperTransform = new JasperTransform(report, this.reportDesign);
            jasperTransform.transform();
        }
        return this.reportDesign;
    }

    /**
     * <p>toJasperDesign.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.design.JasperDesign} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperDesign toJasperDesign() throws DRException {
        if (this.jasperDesign == null) {
            this.jasperDesign = this.toJasperReportDesign().getDesign();
        }
        return this.jasperDesign;
    }

    /**
     * <p>toJasperReport.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JasperReport} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReport toJasperReport() throws DRException {
        if (this.jasperReport == null) {
            try {
                this.jasperReport = JasperCompileManager.compileReport(this.toJasperDesign());
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        return this.jasperReport;
    }

    /**
     * <p>getJasperParameters.</p>
     *
     * @return a {@link java.util.Map} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public Map<String, Object> getJasperParameters() throws DRException {
        if (this.parameters == null) {
            this.parameters = new HashMap<>();
            final JasperReportDesign jasperReportDesign = this.toJasperReportDesign();
            this.parameters.putAll(jasperReportDesign.getParameters());
            if (this.getReport().getParameterValues() != null) {
                this.parameters.putAll(this.getReport().getParameterValues());
            }
        }
        return this.parameters;
    }

    /**
     * <p>toJasperPrint.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JasperPrint} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperPrint toJasperPrint() throws DRException {
        if (this.jasperPrint == null) {
            final Map<String, Object> parameters = this.getJasperParameters();
            if (this.virtualizer != null) {
                parameters.put(JRParameter.REPORT_VIRTUALIZER, this.virtualizer);
            }

            try {
                if (this.connection != null && this.toJasperReport().getQuery() != null) {
                    this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters, this.connection);
                } else if (this.dataSource != null) {
                    this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters, this.dataSource);
                } else {
                    this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters);
                }

                if (this.toJasperReportDesign().isTableOfContents()) {
                    JasperTocReport.createTocReport(this.toJasperReportDesign(), this.jasperPrint, parameters);
                }
            } catch (final JRException e) {
                throw new DRException(e);
            }

        }
        return this.jasperPrint;
    }

    /**
     * <p>show.</p>
     *
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder show() throws DRException {
        JasperViewer.viewReport(this.toJasperPrint());
        return this;
    }

    /**
     * <p>show.</p>
     *
     * @param exitOnClose a boolean.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder show(final boolean exitOnClose) throws DRException {
        JasperViewer.viewReport(this.toJasperPrint(), exitOnClose, null);
        return this;
    }

    /**
     * <p>showJrXml.</p>
     *
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder showJrXml() throws DRException {
        try {
            JasperDesignViewer.viewReportDesign(this.toJasperDesign());
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>toJrXml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toJrXml(final OutputStream outputStream) throws DRException {
        Validate.notNull(outputStream, "outputStream must not be null");
        try {
            JRXmlWriter.writeReport(this.toJasperDesign(), outputStream, "UTF-8");
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>print.</p>
     *
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder print() throws DRException {
        return this.print(true);
    }

    /**
     * <p>print.</p>
     *
     * @param withPrintDialog a boolean.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder print(final boolean withPrintDialog) throws DRException {
        try {
            JasperPrintManager.printReport(this.toJasperPrint(), withPrintDialog);
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>Setter for the field <code>virtualizer</code>.</p>
     *
     * @param virtualizer a {@link net.sf.jasperreports.engine.JRVirtualizer} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     */
    public JasperReportBuilder setVirtualizer(final JRVirtualizer virtualizer) {
        this.virtualizer = virtualizer;
        return this;
    }

    /**
     * <p>toImage.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @param imageType    a {@link net.sf.dynamicreports.jasper.constant.ImageType} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toImage(final OutputStream outputStream, final ImageType imageType) throws DRException {
        return this.toImage(Exporters.imageExporter(outputStream, imageType));
    }

    /**
     * <p>toImage.</p>
     *
     * @param imageExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperImageExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toImage(final JasperImageExporterBuilder imageExporterBuilder) throws DRException {
        Validate.notNull(imageExporterBuilder, "imageExporterBuilder must not be null");

        final JasperImageExporter imageExporter = imageExporterBuilder.build();

        final JasperPrint jasperPrint = this.toJasperPrint();
        Integer fromPage = null;
        Integer toPage = null;
        float zoom = 1;
        final String imageType = imageExporter.getImageType().name().toLowerCase();
        int offsetX = 0;
        int offsetY = 0;
        int pageGap = 0;

        if (imageExporter.getZoomRatio() != null) {
            zoom = imageExporter.getZoomRatio();
        }
        if (imageExporter.getOffsetX() != null) {
            offsetX = imageExporter.getOffsetX();
        }
        if (imageExporter.getOffsetY() != null) {
            offsetY = imageExporter.getOffsetY();
        }
        if (imageExporter.getPageGap() != null) {
            pageGap = imageExporter.getPageGap();
        }

        if (imageExporter.getPageIndex() != null && imageExporter.getPageIndex() >= 0) {
            fromPage = imageExporter.getPageIndex();
            toPage = imageExporter.getPageIndex() + 1;
        } else {
            if (imageExporter.getStartPageIndex() != null) {
                fromPage = imageExporter.getStartPageIndex();
            }
            if (imageExporter.getEndPageIndex() != null) {
                toPage = imageExporter.getEndPageIndex();
            }
        }
        if (fromPage == null) {
            fromPage = 0;
        }
        if (toPage == null) {
            toPage = jasperPrint.getPages().size();
        }

        final int pages = toPage - fromPage;

        final int pageWidth = (int) (jasperPrint.getPageWidth() * zoom);
        final int pageHeight = (int) (jasperPrint.getPageHeight() * zoom);
        final int width = pageWidth * pages + pages - 1 + offsetX * 2;
        final int height = (int) (jasperPrint.getPageHeight() * zoom) + offsetY * 2;
        final Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int offset = offsetX;
        for (int i = 0; i < pages; i++) {
            try {
                final SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
                final SimpleGraphics2DExporterOutput exporterOutput = new SimpleGraphics2DExporterOutput();
                final Image pageImage = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
                exporterOutput.setGraphics2D((Graphics2D) pageImage.getGraphics());
                final SimpleGraphics2DReportConfiguration reportExportConfiguration = new SimpleGraphics2DReportConfiguration();
                reportExportConfiguration.setPageIndex(fromPage);
                reportExportConfiguration.setZoomRatio(zoom);
                final SimpleGraphics2DExporterConfiguration exporterConfiguration = new SimpleGraphics2DExporterConfiguration();

                final JRGraphics2DExporter jrExporter = new JRGraphics2DExporter();
                jrExporter.setExporterInput(exporterInput);
                jrExporter.setExporterOutput(exporterOutput);
                jrExporter.setConfiguration(reportExportConfiguration);
                jrExporter.setConfiguration(exporterConfiguration);

                jrExporter.exportReport();
                ((Graphics2D) image.getGraphics()).drawImage(pageImage, offset, offsetY, null);
                fromPage++;
                offset += pageWidth + pageGap;
            } catch (final JRException e) {
                throw new DRException(e);
            }
        }
        try {
            if (imageExporter.getOutputStream() != null) {
                ImageIO.write((RenderedImage) image, imageType, imageExporter.getOutputStream());
            } else if (imageExporter.getOutputFileName() != null) {
                ImageIO.write((RenderedImage) image, imageType, new File(imageExporter.getOutputFileName()));
            } else if (imageExporter.getOutputFile() != null) {
                ImageIO.write((RenderedImage) image, imageType, imageExporter.getOutputFile());
            } else {
                throw new JasperDesignException("ImageExporter output not supported");
            }
        } catch (final IOException e) {
            throw new DRException(e);
        }
        return this;
    }

    // image

    /**
     * <p>toCsv.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toCsv(final OutputStream outputStream) throws DRException {
        return this.toCsv(Exporters.csvExporter(outputStream));
    }

    /**
     * <p>toCsv.</p>
     *
     * @param csvExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toCsv(final JasperCsvExporterBuilder csvExporterBuilder) throws DRException {
        return this.export(csvExporterBuilder);
    }

    // csv

    /**
     * <p>toDocx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toDocx(final OutputStream outputStream) throws DRException {
        return this.toDocx(Exporters.docxExporter(outputStream));
    }

    /**
     * <p>toDocx.</p>
     *
     * @param docxExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toDocx(final JasperDocxExporterBuilder docxExporterBuilder) throws DRException {
        return this.export(docxExporterBuilder);
    }

    // docx

    /**
     * <p>toHtml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toHtml(final OutputStream outputStream) throws DRException {
        return this.toHtml(Exporters.htmlExporter(outputStream));
    }

    /**
     * <p>toHtml.</p>
     *
     * @param htmlExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toHtml(final JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException {
        return this.export(htmlExporterBuilder);
    }

    // html

    /**
     * <p>toOds.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOds(final OutputStream outputStream) throws DRException {
        return this.toOds(Exporters.odsExporter(outputStream));
    }

    /**
     * <p>toOds.</p>
     *
     * @param odsExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOds(final JasperOdsExporterBuilder odsExporterBuilder) throws DRException {
        return this.export(odsExporterBuilder);
    }

    // ods

    /**
     * <p>toOdt.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOdt(final OutputStream outputStream) throws DRException {
        return this.toOdt(Exporters.odtExporter(outputStream));
    }

    /**
     * <p>toOdt.</p>
     *
     * @param odtExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toOdt(final JasperOdtExporterBuilder odtExporterBuilder) throws DRException {
        return this.export(odtExporterBuilder);
    }

    // odt

    /**
     * <p>toPdf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPdf(final OutputStream outputStream) throws DRException {
        return this.toPdf(Exporters.pdfExporter(outputStream));
    }

    /**
     * <p>toPdf.</p>
     *
     * @param pdfExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPdf(final JasperPdfExporterBuilder pdfExporterBuilder) throws DRException {
        return this.export(pdfExporterBuilder);
    }

    // pdf

    /**
     * <p>toRtf.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toRtf(final OutputStream outputStream) throws DRException {
        return this.toRtf(Exporters.rtfExporter(outputStream));
    }

    /**
     * <p>toRtf.</p>
     *
     * @param rtfExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toRtf(final JasperRtfExporterBuilder rtfExporterBuilder) throws DRException {
        return this.export(rtfExporterBuilder);
    }

    // rtf

    /**
     * <p>toText.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toText(final OutputStream outputStream) throws DRException {
        return this.toText(Exporters.textExporter(outputStream));
    }

    /**
     * <p>toText.</p>
     *
     * @param textExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toText(final JasperTextExporterBuilder textExporterBuilder) throws DRException {
        return this.export(textExporterBuilder);
    }

    // text

    /**
     * <p>toXlsx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXlsx(final OutputStream outputStream) throws DRException {
        return this.toXlsx(Exporters.xlsxExporter(outputStream));
    }

    /**
     * <p>toXlsx.</p>
     *
     * @param xlsxExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXlsx(final JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException {
        return this.export(xlsxExporterBuilder);
    }

    // xlsx

    /**
     * <p>toXml.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXml(final OutputStream outputStream) throws DRException {
        return this.toXml(Exporters.xmlExporter(outputStream));
    }

    /**
     * <p>toXml.</p>
     *
     * @param xmlExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperXmlExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toXml(final JasperXmlExporterBuilder xmlExporterBuilder) throws DRException {
        return this.export(xmlExporterBuilder);
    }

    // xml

    /**
     * <p>toPptx.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPptx(final OutputStream outputStream) throws DRException {
        return this.toPptx(Exporters.pptxExporter(outputStream));
    }

    /**
     * <p>toPptx.</p>
     *
     * @param pptxExporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder toPptx(final JasperPptxExporterBuilder pptxExporterBuilder) throws DRException {
        return this.export(pptxExporterBuilder);
    }

    // pptx

    /**
     * <p>export.</p>
     *
     * @param exporterBuilder a {@link net.sf.dynamicreports.jasper.builder.export.AbstractJasperExporterBuilder} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     * @throws net.sf.dynamicreports.report.exception.DRException if any.
     */
    public JasperReportBuilder export(final AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder) throws DRException {
        Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
        try {
            final ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
            @SuppressWarnings("unchecked")
            final Exporter<ExporterInput, ?, ?, ?> exporter = (Exporter<ExporterInput, ?, ?, ?>) exporterTransform.transform();
            exporter.setExporterInput(new SimpleExporterInput(this.toJasperPrint()));
            exporter.exportReport();
        } catch (final JRException e) {
            throw new DRException(e);
        }
        return this;
    }

    /**
     * <p>Getter for the field <code>connection</code>.</p>
     *
     * @return a {@link java.sql.Connection} object.
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * <p>Setter for the field <code>connection</code>.</p>
     *
     * @param connection a {@link java.sql.Connection} object.
     * @return a {@link net.sf.dynamicreports.jasper.builder.JasperReportBuilder} object.
     */
    public JasperReportBuilder setConnection(final Connection connection) {
        Validate.notNull(connection, "connection must not be null");
        this.connection = connection;
        return this;
    }

    /**
     * <p>Getter for the field <code>dataSource</code>.</p>
     *
     * @return a {@link net.sf.jasperreports.engine.JRDataSource} object.
     */
    public JRDataSource getDataSource() {
        return this.dataSource;
    }

    /**
     * Sets a data source object.
     *
     * @param dataSource - the JRDataSource object
     * @return a report builder
     */
    public JasperReportBuilder setDataSource(final JRDataSource dataSource) {
        this.dataSource = dataSource;
        this.connection = null;
        return this;
    }
}
