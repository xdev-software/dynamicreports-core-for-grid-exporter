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


public class ExporterBuilders
{
	
	// csv
	
	public JasperCsvExporterBuilder csvExporter(final Writer outputWriter)
	{
		return Exporters.csvExporter(outputWriter);
	}
	
	public JasperCsvExporterBuilder csvExporter(final OutputStream outputStream)
	{
		return Exporters.csvExporter(outputStream);
	}
	
	public JasperCsvExporterBuilder csvExporter(final File outputFile)
	{
		return Exporters.csvExporter(outputFile);
	}
	
	public JasperCsvExporterBuilder csvExporter(final String outputFileName)
	{
		return Exporters.csvExporter(outputFileName);
	}
	
	// docx
	
	public JasperDocxExporterBuilder docxExporter(final Writer outputWriter)
	{
		return Exporters.docxExporter(outputWriter);
	}
	
	public JasperDocxExporterBuilder docxExporter(final OutputStream outputStream)
	{
		return Exporters.docxExporter(outputStream);
	}
	
	public JasperDocxExporterBuilder docxExporter(final File outputFile)
	{
		return Exporters.docxExporter(outputFile);
	}
	
	public JasperDocxExporterBuilder docxExporter(final String outputFileName)
	{
		return Exporters.docxExporter(outputFileName);
	}
	
	// html
	
	public JasperHtmlExporterBuilder htmlExporter(final Writer outputWriter)
	{
		return Exporters.htmlExporter(outputWriter);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(final OutputStream outputStream)
	{
		return Exporters.htmlExporter(outputStream);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(final File outputFile)
	{
		return Exporters.htmlExporter(outputFile);
	}
	
	public JasperHtmlExporterBuilder htmlExporter(final String outputFileName)
	{
		return Exporters.htmlExporter(outputFileName);
	}
	
	// ods
	
	public JasperOdsExporterBuilder odsExporter(final Writer outputWriter)
	{
		return Exporters.odsExporter(outputWriter);
	}
	
	public JasperOdsExporterBuilder odsExporter(final OutputStream outputStream)
	{
		return Exporters.odsExporter(outputStream);
	}
	
	public JasperOdsExporterBuilder odsExporter(final File outputFile)
	{
		return Exporters.odsExporter(outputFile);
	}
	
	public JasperOdsExporterBuilder odsExporter(final String outputFileName)
	{
		return Exporters.odsExporter(outputFileName);
	}
	
	// odt
	
	public JasperOdtExporterBuilder odtExporter(final Writer outputWriter)
	{
		return Exporters.odtExporter(outputWriter);
	}
	
	public JasperOdtExporterBuilder odtExporter(final OutputStream outputStream)
	{
		return Exporters.odtExporter(outputStream);
	}
	
	public JasperOdtExporterBuilder odtExporter(final File outputFile)
	{
		return Exporters.odtExporter(outputFile);
	}
	
	public JasperOdtExporterBuilder odtExporter(final String outputFileName)
	{
		return Exporters.odtExporter(outputFileName);
	}
	
	// pdf
	
	public JasperPdfExporterBuilder pdfExporter(final Writer outputWriter)
	{
		return Exporters.pdfExporter(outputWriter);
	}
	
	public JasperPdfExporterBuilder pdfExporter(final OutputStream outputStream)
	{
		return Exporters.pdfExporter(outputStream);
	}
	
	public JasperPdfExporterBuilder pdfExporter(final File outputFile)
	{
		return Exporters.pdfExporter(outputFile);
	}
	
	public JasperPdfExporterBuilder pdfExporter(final String outputFileName)
	{
		return Exporters.pdfExporter(outputFileName);
	}
	
	// rtf
	
	public JasperRtfExporterBuilder rtfExporter(final Writer outputWriter)
	{
		return Exporters.rtfExporter(outputWriter);
	}
	
	public JasperRtfExporterBuilder rtfExporter(final OutputStream outputStream)
	{
		return Exporters.rtfExporter(outputStream);
	}
	
	public JasperRtfExporterBuilder rtfExporter(final File outputFile)
	{
		return Exporters.rtfExporter(outputFile);
	}
	
	public JasperRtfExporterBuilder rtfExporter(final String outputFileName)
	{
		return Exporters.rtfExporter(outputFileName);
	}
	
	// text
	
	public JasperTextExporterBuilder textExporter(final Writer outputWriter)
	{
		return Exporters.textExporter(outputWriter);
	}
	
	public JasperTextExporterBuilder textExporter(final OutputStream outputStream)
	{
		return Exporters.textExporter(outputStream);
	}
	
	public JasperTextExporterBuilder textExporter(final File outputFile)
	{
		return Exporters.textExporter(outputFile);
	}
	
	public JasperTextExporterBuilder textExporter(final String outputFileName)
	{
		return Exporters.textExporter(outputFileName);
	}
	
	// xlsx
	
	public JasperXlsxExporterBuilder xlsxExporter(final Writer outputWriter)
	{
		return Exporters.xlsxExporter(outputWriter);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(final OutputStream outputStream)
	{
		return Exporters.xlsxExporter(outputStream);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(final File outputFile)
	{
		return Exporters.xlsxExporter(outputFile);
	}
	
	public JasperXlsxExporterBuilder xlsxExporter(final String outputFileName)
	{
		return Exporters.xlsxExporter(outputFileName);
	}
	
	// pptx
	
	public JasperPptxExporterBuilder pptxExporter(final Writer outputWriter)
	{
		return Exporters.pptxExporter(outputWriter);
	}
	
	public JasperPptxExporterBuilder pptxExporter(final OutputStream outputStream)
	{
		return Exporters.pptxExporter(outputStream);
	}
	
	public JasperPptxExporterBuilder pptxExporter(final File outputFile)
	{
		return Exporters.pptxExporter(outputFile);
	}
	
	public JasperPptxExporterBuilder pptxExporter(final String outputFileName)
	{
		return Exporters.pptxExporter(outputFileName);
	}
}
