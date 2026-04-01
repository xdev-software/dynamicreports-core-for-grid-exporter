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
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;
import software.xdev.dynamicreports.jasper.transformation.ExporterTransform;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperConcatenatedReportBuilder implements Serializable
{

	private final JasperReportHandler jasperReportHandler;
	
	public JasperConcatenatedReportBuilder()
	{
		this(new JasperReportBuilderHandler());
	}
	
	public JasperConcatenatedReportBuilder(final JasperReportHandler jasperReportHandler)
	{
		this.jasperReportHandler = jasperReportHandler;
	}
	
	public JasperConcatenatedReportBuilder concatenate(final JasperReportBuilder... jasperReportBuilders)
	{
		Validate.notNull(jasperReportBuilders, "jasperReportBuilders must not be null");
		Validate.noNullElements(
			jasperReportBuilders,
			"jasperReportBuilders must not contains null jasperReportBuilder");
		this.jasperReportHandler.concatenate(jasperReportBuilders);
		return this;
	}
	
	public JasperConcatenatedReportBuilder toPng(final OutputStream outputStream) throws DRException
	{
		return this.toPng(outputStream, 1);
	}
	
	@SuppressWarnings("java:S2184") // FP: int - int != float?
	public JasperConcatenatedReportBuilder toPng(final OutputStream outputStream, final float zoom) throws DRException
	{
		Validate.notNull(outputStream, "outputStream must not be null");
		Validate.isTrue(zoom > 0, "zoom must be > 0");
		
		int maxWidth = 0;
		int maxHeight = 0;
		
		for(final JasperPrint jasperPrint : this.jasperReportHandler.getPrintList())
		{
			final int pages = jasperPrint.getPages().size();
			final int pageWidth = (int)(jasperPrint.getPageWidth() * zoom);
			maxWidth += pageWidth * pages + pages - 1 + 2;
			final int height = (int)(jasperPrint.getPageHeight() * zoom) + 2;
			if(height > maxHeight)
			{
				maxHeight = height;
			}
		}
		
		final Image image = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_RGB);
		final Graphics2D g2d = (Graphics2D)image.getGraphics();
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Rectangle2D.Float(1, 1, maxWidth - 1, maxHeight - 1));
		
		int offset = 1;
		for(final JasperPrint jasperPrint : this.jasperReportHandler.getPrintList())
		{
			final int pageWidth = (int)(jasperPrint.getPageWidth() * zoom);
			final int pageHeight = (int)(jasperPrint.getPageHeight() * zoom);
			for(
				int i = 0; i < jasperPrint.getPages().size(); i++)
			{
				try
				{
					final SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
					final SimpleGraphics2DExporterOutput exporterOutput = new SimpleGraphics2DExporterOutput();
					final Image pageImage = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
					exporterOutput.setGraphics2D((Graphics2D)pageImage.getGraphics());
					final SimpleGraphics2DReportConfiguration reportExportConfiguration =
						new SimpleGraphics2DReportConfiguration();
					reportExportConfiguration.setPageIndex(i);
					reportExportConfiguration.setZoomRatio(zoom);
					final SimpleGraphics2DExporterConfiguration exporterConfiguration =
						new SimpleGraphics2DExporterConfiguration();
					
					final JRGraphics2DExporter jrExporter = new JRGraphics2DExporter();
					jrExporter.setExporterInput(exporterInput);
					jrExporter.setExporterOutput(exporterOutput);
					jrExporter.setConfiguration(reportExportConfiguration);
					jrExporter.setConfiguration(exporterConfiguration);
					
					jrExporter.exportReport();
					((Graphics2D)image.getGraphics()).drawImage(pageImage, offset, 1, null);
					offset += pageWidth + 1;
				}
				catch(final JRException e)
				{
					throw new DRException(e);
				}
			}
		}
		try
		{
			ImageIO.write((RenderedImage)image, "png", outputStream);
		}
		catch(final IOException e)
		{
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperConcatenatedReportBuilder continuousPageNumbering()
	{
		return this.setContinuousPageNumbering(true);
	}
	
	public JasperConcatenatedReportBuilder setContinuousPageNumbering(final boolean continuousPageNumbering)
	{
		this.jasperReportHandler.setContinuousPageNumbering(continuousPageNumbering);
		return this;
	}
	
	// csv
	
	public JasperConcatenatedReportBuilder toCsv(final OutputStream outputStream) throws DRException
	{
		return this.toCsv(Exporters.csvExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toCsv(final JasperCsvExporterBuilder csvExporterBuilder) throws DRException
	{
		return this.export(csvExporterBuilder);
	}
	
	// docx
	
	public JasperConcatenatedReportBuilder toDocx(final OutputStream outputStream) throws DRException
	{
		return this.toDocx(Exporters.docxExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toDocx(final JasperDocxExporterBuilder docxExporterBuilder)
		throws DRException
	{
		return this.export(docxExporterBuilder);
	}
	
	// html
	
	public JasperConcatenatedReportBuilder toHtml(final OutputStream outputStream) throws DRException
	{
		return this.toHtml(Exporters.htmlExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toHtml(final JasperHtmlExporterBuilder htmlExporterBuilder)
		throws DRException
	{
		return this.export(htmlExporterBuilder);
	}
	
	// ods
	
	public JasperConcatenatedReportBuilder toOds(final OutputStream outputStream) throws DRException
	{
		return this.toOds(Exporters.odsExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toOds(final JasperOdsExporterBuilder odsExporterBuilder) throws DRException
	{
		return this.export(odsExporterBuilder);
	}
	
	// odt
	
	public JasperConcatenatedReportBuilder toOdt(final OutputStream outputStream) throws DRException
	{
		return this.toOdt(Exporters.odtExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toOdt(final JasperOdtExporterBuilder odtExporterBuilder) throws DRException
	{
		return this.export(odtExporterBuilder);
	}
	
	// pdf
	
	public JasperConcatenatedReportBuilder toPdf(final OutputStream outputStream) throws DRException
	{
		return this.toPdf(Exporters.pdfExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toPdf(final JasperPdfExporterBuilder pdfExporterBuilder) throws DRException
	{
		return this.export(pdfExporterBuilder);
	}
	
	// rtf
	
	public JasperConcatenatedReportBuilder toRtf(final OutputStream outputStream) throws DRException
	{
		return this.toRtf(Exporters.rtfExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toRtf(final JasperRtfExporterBuilder rtfExporterBuilder) throws DRException
	{
		return this.export(rtfExporterBuilder);
	}
	
	// text
	
	public JasperConcatenatedReportBuilder toText(final OutputStream outputStream) throws DRException
	{
		return this.toText(Exporters.textExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toText(final JasperTextExporterBuilder textExporterBuilder)
		throws DRException
	{
		return this.export(textExporterBuilder);
	}
	
	// xlsx
	
	public JasperConcatenatedReportBuilder toXlsx(final OutputStream outputStream) throws DRException
	{
		return this.toXlsx(Exporters.xlsxExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toXlsx(final JasperXlsxExporterBuilder xlsxExporterBuilder)
		throws DRException
	{
		return this.export(xlsxExporterBuilder);
	}
	
	// pptx
	
	public JasperConcatenatedReportBuilder toPptx(final OutputStream outputStream) throws DRException
	{
		return this.toPptx(Exporters.pptxExporter(outputStream));
	}
	
	public JasperConcatenatedReportBuilder toPptx(final JasperPptxExporterBuilder pptxExporterBuilder)
		throws DRException
	{
		return this.export(pptxExporterBuilder);
	}
	
	public JasperConcatenatedReportBuilder export(
		final AbstractJasperExporterBuilder<?, ?
			extends AbstractJasperExporter> exporterBuilder)
		throws DRException
	{
		Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
		try
		{
			final ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
			@SuppressWarnings("unchecked")
			final Exporter<ExporterInput, ?, ?, ?> exporter =
				(Exporter<ExporterInput, ?, ?, ?>)exporterTransform.transform();
			exporter.setExporterInput(SimpleExporterInput.getInstance(this.jasperReportHandler.getPrintList()));
			exporter.exportReport();
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		return this;
	}
}
