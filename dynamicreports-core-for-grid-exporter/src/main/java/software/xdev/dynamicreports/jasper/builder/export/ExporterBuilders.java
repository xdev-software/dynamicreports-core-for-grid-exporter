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

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;


/**
 * A set of methods of creating exporters
 *
 * @author Ricardo Mariaca
 */
public class ExporterBuilders
{
    
    // csv
    
    /**
     * <p>csvExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     */
    public JasperCsvExporterBuilder csvExporter(final Writer outputWriter)
    {
        return Exporters.csvExporter(outputWriter);
    }
    
    /**
     * <p>csvExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     */
    public JasperCsvExporterBuilder csvExporter(final OutputStream outputStream)
    {
        return Exporters.csvExporter(outputStream);
    }
    
    /**
     * <p>csvExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     */
    public JasperCsvExporterBuilder csvExporter(final File outputFile)
    {
        return Exporters.csvExporter(outputFile);
    }
    
    /**
     * <p>csvExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperCsvExporterBuilder} object.
     */
    public JasperCsvExporterBuilder csvExporter(final String outputFileName)
    {
        return Exporters.csvExporter(outputFileName);
    }
    
    // docx
    
    /**
     * <p>docxExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder docxExporter(final Writer outputWriter)
    {
        return Exporters.docxExporter(outputWriter);
    }
    
    /**
     * <p>docxExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder docxExporter(final OutputStream outputStream)
    {
        return Exporters.docxExporter(outputStream);
    }
    
    /**
     * <p>docxExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder docxExporter(final File outputFile)
    {
        return Exporters.docxExporter(outputFile);
    }
    
    /**
     * <p>docxExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperDocxExporterBuilder} object.
     */
    public JasperDocxExporterBuilder docxExporter(final String outputFileName)
    {
        return Exporters.docxExporter(outputFileName);
    }
    
    // html
    
    /**
     * <p>htmlExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder htmlExporter(final Writer outputWriter)
    {
        return Exporters.htmlExporter(outputWriter);
    }
    
    /**
     * <p>htmlExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder htmlExporter(final OutputStream outputStream)
    {
        return Exporters.htmlExporter(outputStream);
    }
    
    /**
     * <p>htmlExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder htmlExporter(final File outputFile)
    {
        return Exporters.htmlExporter(outputFile);
    }
    
    /**
     * <p>htmlExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder htmlExporter(final String outputFileName)
    {
        return Exporters.htmlExporter(outputFileName);
    }
    
    // ods
    
    /**
     * <p>odsExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     */
    public JasperOdsExporterBuilder odsExporter(final Writer outputWriter)
    {
        return Exporters.odsExporter(outputWriter);
    }
    
    /**
     * <p>odsExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     */
    public JasperOdsExporterBuilder odsExporter(final OutputStream outputStream)
    {
        return Exporters.odsExporter(outputStream);
    }
    
    /**
     * <p>odsExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     */
    public JasperOdsExporterBuilder odsExporter(final File outputFile)
    {
        return Exporters.odsExporter(outputFile);
    }
    
    /**
     * <p>odsExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdsExporterBuilder} object.
     */
    public JasperOdsExporterBuilder odsExporter(final String outputFileName)
    {
        return Exporters.odsExporter(outputFileName);
    }
    
    // odt
    
    /**
     * <p>odtExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder odtExporter(final Writer outputWriter)
    {
        return Exporters.odtExporter(outputWriter);
    }
    
    /**
     * <p>odtExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder odtExporter(final OutputStream outputStream)
    {
        return Exporters.odtExporter(outputStream);
    }
    
    /**
     * <p>odtExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder odtExporter(final File outputFile)
    {
        return Exporters.odtExporter(outputFile);
    }
    
    /**
     * <p>odtExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperOdtExporterBuilder} object.
     */
    public JasperOdtExporterBuilder odtExporter(final String outputFileName)
    {
        return Exporters.odtExporter(outputFileName);
    }
    
    // pdf
    
    /**
     * <p>pdfExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     */
    public JasperPdfExporterBuilder pdfExporter(final Writer outputWriter)
    {
        return Exporters.pdfExporter(outputWriter);
    }
    
    /**
     * <p>pdfExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     */
    public JasperPdfExporterBuilder pdfExporter(final OutputStream outputStream)
    {
        return Exporters.pdfExporter(outputStream);
    }
    
    /**
     * <p>pdfExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     */
    public JasperPdfExporterBuilder pdfExporter(final File outputFile)
    {
        return Exporters.pdfExporter(outputFile);
    }
    
    /**
     * <p>pdfExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder} object.
     */
    public JasperPdfExporterBuilder pdfExporter(final String outputFileName)
    {
        return Exporters.pdfExporter(outputFileName);
    }
    
    // rtf
    
    /**
     * <p>rtfExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     */
    public JasperRtfExporterBuilder rtfExporter(final Writer outputWriter)
    {
        return Exporters.rtfExporter(outputWriter);
    }
    
    /**
     * <p>rtfExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     */
    public JasperRtfExporterBuilder rtfExporter(final OutputStream outputStream)
    {
        return Exporters.rtfExporter(outputStream);
    }
    
    /**
     * <p>rtfExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     */
    public JasperRtfExporterBuilder rtfExporter(final File outputFile)
    {
        return Exporters.rtfExporter(outputFile);
    }
    
    /**
     * <p>rtfExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperRtfExporterBuilder} object.
     */
    public JasperRtfExporterBuilder rtfExporter(final String outputFileName)
    {
        return Exporters.rtfExporter(outputFileName);
    }
    
    // text
    
    /**
     * <p>textExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder textExporter(final Writer outputWriter)
    {
        return Exporters.textExporter(outputWriter);
    }
    
    /**
     * <p>textExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder textExporter(final OutputStream outputStream)
    {
        return Exporters.textExporter(outputStream);
    }
    
    /**
     * <p>textExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder textExporter(final File outputFile)
    {
        return Exporters.textExporter(outputFile);
    }
    
    /**
     * <p>textExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder textExporter(final String outputFileName)
    {
        return Exporters.textExporter(outputFileName);
    }
    
    // xlsx
    
    /**
     * <p>xlsxExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     */
    public JasperXlsxExporterBuilder xlsxExporter(final Writer outputWriter)
    {
        return Exporters.xlsxExporter(outputWriter);
    }
    
    /**
     * <p>xlsxExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     */
    public JasperXlsxExporterBuilder xlsxExporter(final OutputStream outputStream)
    {
        return Exporters.xlsxExporter(outputStream);
    }
    
    /**
     * <p>xlsxExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     */
    public JasperXlsxExporterBuilder xlsxExporter(final File outputFile)
    {
        return Exporters.xlsxExporter(outputFile);
    }
    
    /**
     * <p>xlsxExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder} object.
     */
    public JasperXlsxExporterBuilder xlsxExporter(final String outputFileName)
    {
        return Exporters.xlsxExporter(outputFileName);
    }
    
    // pptx
    
    /**
     * <p>pptxExporter.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     */
    public JasperPptxExporterBuilder pptxExporter(final Writer outputWriter)
    {
        return Exporters.pptxExporter(outputWriter);
    }
    
    /**
     * <p>pptxExporter.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     */
    public JasperPptxExporterBuilder pptxExporter(final OutputStream outputStream)
    {
        return Exporters.pptxExporter(outputStream);
    }
    
    /**
     * <p>pptxExporter.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     */
    public JasperPptxExporterBuilder pptxExporter(final File outputFile)
    {
        return Exporters.pptxExporter(outputFile);
    }
    
    /**
     * <p>pptxExporter.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     */
    public JasperPptxExporterBuilder pptxExporter(final String outputFileName)
    {
        return Exporters.pptxExporter(outputFileName);
    }
}
