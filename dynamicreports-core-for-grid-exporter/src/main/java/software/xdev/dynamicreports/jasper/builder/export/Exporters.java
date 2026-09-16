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


public final class Exporters
{
	private Exporters()
	{
	}
	
	// csv
	
	public static JasperCsvExporterBuilder csvExporter(final Writer outputWriter)
	{
		return new JasperCsvExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperCsvExporterBuilder csvExporter(final OutputStream outputStream)
	{
		return new JasperCsvExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperCsvExporterBuilder csvExporter(final File outputFile)
	{
		return new JasperCsvExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperCsvExporterBuilder csvExporter(final String outputFileName)
	{
		return new JasperCsvExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// docx
	
	public static JasperDocxExporterBuilder docxExporter(final Writer outputWriter)
	{
		return new JasperDocxExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperDocxExporterBuilder docxExporter(final OutputStream outputStream)
	{
		return new JasperDocxExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperDocxExporterBuilder docxExporter(final File outputFile)
	{
		return new JasperDocxExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperDocxExporterBuilder docxExporter(final String outputFileName)
	{
		return new JasperDocxExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// html
	
	public static JasperHtmlExporterBuilder htmlExporter(final Writer outputWriter)
	{
		return new JasperHtmlExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(final OutputStream outputStream)
	{
		return new JasperHtmlExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(final File outputFile)
	{
		return new JasperHtmlExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperHtmlExporterBuilder htmlExporter(final String outputFileName)
	{
		return new JasperHtmlExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// ods
	
	public static JasperOdsExporterBuilder odsExporter(final Writer outputWriter)
	{
		return new JasperOdsExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperOdsExporterBuilder odsExporter(final OutputStream outputStream)
	{
		return new JasperOdsExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperOdsExporterBuilder odsExporter(final File outputFile)
	{
		return new JasperOdsExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperOdsExporterBuilder odsExporter(final String outputFileName)
	{
		return new JasperOdsExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// odt
	
	public static JasperOdtExporterBuilder odtExporter(final Writer outputWriter)
	{
		return new JasperOdtExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperOdtExporterBuilder odtExporter(final OutputStream outputStream)
	{
		return new JasperOdtExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperOdtExporterBuilder odtExporter(final File outputFile)
	{
		return new JasperOdtExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperOdtExporterBuilder odtExporter(final String outputFileName)
	{
		return new JasperOdtExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// pdf
	
	public static JasperPdfExporterBuilder pdfExporter(final Writer outputWriter)
	{
		return new JasperPdfExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(final OutputStream outputStream)
	{
		return new JasperPdfExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(final File outputFile)
	{
		return new JasperPdfExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperPdfExporterBuilder pdfExporter(final String outputFileName)
	{
		return new JasperPdfExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// rtf
	
	public static JasperRtfExporterBuilder rtfExporter(final Writer outputWriter)
	{
		return new JasperRtfExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(final OutputStream outputStream)
	{
		return new JasperRtfExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(final File outputFile)
	{
		return new JasperRtfExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperRtfExporterBuilder rtfExporter(final String outputFileName)
	{
		return new JasperRtfExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// text
	
	public static JasperTextExporterBuilder textExporter(final Writer outputWriter)
	{
		return new JasperTextExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperTextExporterBuilder textExporter(final OutputStream outputStream)
	{
		return new JasperTextExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperTextExporterBuilder textExporter(final File outputFile)
	{
		return new JasperTextExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperTextExporterBuilder textExporter(final String outputFileName)
	{
		return new JasperTextExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// xlsx
	
	public static JasperXlsxExporterBuilder xlsxExporter(final Writer outputWriter)
	{
		return new JasperXlsxExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(final OutputStream outputStream)
	{
		return new JasperXlsxExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(final File outputFile)
	{
		return new JasperXlsxExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperXlsxExporterBuilder xlsxExporter(final String outputFileName)
	{
		return new JasperXlsxExporterBuilder().setOutputFileName(outputFileName);
	}
	
	// pptx
	
	public static JasperPptxExporterBuilder pptxExporter(final Writer outputWriter)
	{
		return new JasperPptxExporterBuilder().setOutputWriter(outputWriter);
	}
	
	public static JasperPptxExporterBuilder pptxExporter(final OutputStream outputStream)
	{
		return new JasperPptxExporterBuilder().setOutputStream(outputStream);
	}
	
	public static JasperPptxExporterBuilder pptxExporter(final File outputFile)
	{
		return new JasperPptxExporterBuilder().setOutputFile(outputFile);
	}
	
	public static JasperPptxExporterBuilder pptxExporter(final String outputFileName)
	{
		return new JasperPptxExporterBuilder().setOutputFileName(outputFileName);
	}
}
