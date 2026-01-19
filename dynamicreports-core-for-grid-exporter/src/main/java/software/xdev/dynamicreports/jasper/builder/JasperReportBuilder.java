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

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.Validate;

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
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
import software.xdev.dynamicreports.design.base.DRDesignReport;
import software.xdev.dynamicreports.design.definition.DRIDesignReport;
import software.xdev.dynamicreports.jasper.base.JasperReportDesign;
import software.xdev.dynamicreports.jasper.base.export.AbstractJasperExporter;
import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocReport;
import software.xdev.dynamicreports.jasper.base.templatedesign.JasperEmptyTemplateDesign;
import software.xdev.dynamicreports.jasper.base.templatedesign.JasperTemplateDesign;
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
import software.xdev.dynamicreports.jasper.transformation.ExporterTransform;
import software.xdev.dynamicreports.jasper.transformation.JasperTransform;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.QueryBuilder;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.constant.QueryLanguage;
import software.xdev.dynamicreports.report.definition.DRITemplateDesign;
import software.xdev.dynamicreports.report.exception.DRException;


public class JasperReportBuilder extends ReportBuilder<JasperReportBuilder>
{

	private JasperReportDesign reportDesign;
	private JasperDesign jasperDesign;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	private transient JRDataSource dataSource;
	private transient Connection connection;
	private transient JRVirtualizer virtualizer;
	private Integer startPageNumber;
	private Map<String, Object> parameters;
	
	public JasperReportBuilder()
	{
		this.setTemplateDesign(new JasperEmptyTemplateDesign());
	}
	
	public JasperReportBuilder setStartPageNumber(final Integer startPageNumber) throws DRException
	{
		if(Objects.equals(this.startPageNumber, startPageNumber))
		{
			return this;
		}
		this.startPageNumber = startPageNumber;
		this.rebuild();
		return this;
	}
	
	public JasperReportBuilder setDataSource(final Collection<?> collection)
	{
		return this.setDataSource(new JRBeanCollectionDataSource(collection));
	}
	
	public JasperReportBuilder setDataSource(final ResultSet resultSet)
	{
		return this.setDataSource(new JRResultSetDataSource(resultSet));
	}
	
	public JasperReportBuilder setDataSource(final String sql, final Connection connection)
	{
		Validate.notNull(sql, "sql must not be null");
		return this.setDataSource(DynamicReports.query(sql, QueryLanguage.SQL), connection);
	}
	
	public JasperReportBuilder setDataSource(final QueryBuilder query, final Connection connection)
	{
		Validate.notNull(query, "query must not be null");
		Validate.notNull(connection, "connection must not be null");
		this.getObject().setQuery(query.build());
		this.connection = connection;
		this.dataSource = null;
		return this;
	}
	
	public JasperReportBuilder setTemplateDesign(final InputStream inputStream) throws DRException
	{
		return this.setTemplateDesign(new JasperTemplateDesign(inputStream));
	}
	
	public JasperReportBuilder setTemplateDesign(final File file) throws DRException
	{
		return this.setTemplateDesign(new JasperTemplateDesign(file));
	}
	
	// template design
	
	public JasperReportBuilder setTemplateDesign(final String fileName) throws DRException
	{
		return this.setTemplateDesign(new JasperTemplateDesign(fileName));
	}
	
	public JasperReportBuilder setTemplateDesign(final JasperDesign jasperDesign) throws DRException
	{
		return this.setTemplateDesign(new JasperTemplateDesign(jasperDesign));
	}
	
	public JasperReportBuilder setTemplateDesign(final URL jasperDesignUrl) throws DRException
	{
		return this.setTemplateDesign(new JasperTemplateDesign(jasperDesignUrl));
	}
	
	private JasperReportBuilder setTemplateDesign(final DRITemplateDesign<JasperDesign> templateDesign)
	{
		this.getObject().setTemplateDesign(templateDesign);
		return this;
	}
	
	@Override
	public JasperReportBuilder setParameter(final String name, final Object value)
	{
		super.setParameter(name, value);
		this.parameters = null;
		this.jasperPrint = null;
		return this;
	}
	
	@Override
	public JasperReportBuilder setParameters(final Map<String, Object> parameters)
	{
		super.setParameters(parameters);
		this.parameters = null;
		this.jasperPrint = null;
		return this;
	}
	
	public JasperReportBuilder rebuild() throws DRException
	{
		this.builded = false;
		this.reportDesign = null;
		this.jasperDesign = null;
		this.jasperReport = null;
		this.parameters = null;
		this.jasperPrint = null;
		if(this.dataSource != null && this.dataSource instanceof JRRewindableDataSource)
		{
			try
			{
				((JRRewindableDataSource)this.dataSource).moveFirst();
			}
			catch(final JRException e)
			{
				throw new DRException(e);
			}
		}
		return this;
	}
	
	private JasperReportDesign toJasperReportDesign() throws DRException
	{
		if(this.reportDesign == null)
		{
			final DRIDesignReport report = new DRDesignReport(this.build());
			this.reportDesign = new JasperReportDesign(report, this.startPageNumber);
			final JasperTransform jasperTransform = new JasperTransform(report, this.reportDesign);
			jasperTransform.transform();
		}
		return this.reportDesign;
	}
	
	public JasperDesign toJasperDesign() throws DRException
	{
		if(this.jasperDesign == null)
		{
			this.jasperDesign = this.toJasperReportDesign().getDesign();
		}
		return this.jasperDesign;
	}
	
	public JasperReport toJasperReport() throws DRException
	{
		if(this.jasperReport == null)
		{
			try
			{
				this.jasperReport = JasperCompileManager.compileReport(this.toJasperDesign());
			}
			catch(final JRException e)
			{
				throw new DRException(e);
			}
		}
		return this.jasperReport;
	}
	
	public Map<String, Object> getJasperParameters() throws DRException
	{
		if(this.parameters == null)
		{
			this.parameters = new HashMap<>();
			final JasperReportDesign jasperReportDesign = this.toJasperReportDesign();
			this.parameters.putAll(jasperReportDesign.getParameters());
			if(this.getReport().getParameterValues() != null)
			{
				this.parameters.putAll(this.getReport().getParameterValues());
			}
		}
		return this.parameters;
	}
	
	public JasperPrint toJasperPrint() throws DRException
	{
		if(this.jasperPrint == null)
		{
			final Map<String, Object> parameters = this.getJasperParameters();
			if(this.virtualizer != null)
			{
				parameters.put(JRParameter.REPORT_VIRTUALIZER, this.virtualizer);
			}
			
			try
			{
				if(this.connection != null && this.toJasperReport().getQuery() != null)
				{
					this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters,
						this.connection);
				}
				else if(this.dataSource != null)
				{
					this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters,
						this.dataSource);
				}
				else
				{
					this.jasperPrint = JasperFillManager.fillReport(this.toJasperReport(), parameters);
				}
				
				if(this.toJasperReportDesign().isTableOfContents())
				{
					JasperTocReport.createTocReport(this.toJasperReportDesign(), this.jasperPrint, parameters);
				}
			}
			catch(final JRException e)
			{
				throw new DRException(e);
			}
		}
		return this.jasperPrint;
	}
	
	public JasperReportBuilder show() throws DRException
	{
		JasperViewer.viewReport(this.toJasperPrint());
		return this;
	}
	
	public JasperReportBuilder show(final boolean exitOnClose) throws DRException
	{
		JasperViewer.viewReport(this.toJasperPrint(), exitOnClose, null);
		return this;
	}
	
	public JasperReportBuilder showJrXml() throws DRException
	{
		try
		{
			JasperDesignViewer.viewReportDesign(this.toJasperDesign());
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder toJrXml(final OutputStream outputStream) throws DRException
	{
		Validate.notNull(outputStream, "outputStream must not be null");
		try
		{
			JRXmlWriter.writeReport(this.toJasperDesign(), outputStream, "UTF-8");
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder print() throws DRException
	{
		return this.print(true);
	}
	
	public JasperReportBuilder print(final boolean withPrintDialog) throws DRException
	{
		try
		{
			JasperPrintManager.printReport(this.toJasperPrint(), withPrintDialog);
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		return this;
	}
	
	public JasperReportBuilder setVirtualizer(final JRVirtualizer virtualizer)
	{
		this.virtualizer = virtualizer;
		return this;
	}
	
	public JasperReportBuilder toCsv(final OutputStream outputStream) throws DRException
	{
		return this.toCsv(Exporters.csvExporter(outputStream));
	}
	
	public JasperReportBuilder toCsv(final JasperCsvExporterBuilder csvExporterBuilder) throws DRException
	{
		return this.export(csvExporterBuilder);
	}
	
	// csv
	
	public JasperReportBuilder toDocx(final OutputStream outputStream) throws DRException
	{
		return this.toDocx(Exporters.docxExporter(outputStream));
	}
	
	public JasperReportBuilder toDocx(final JasperDocxExporterBuilder docxExporterBuilder) throws DRException
	{
		return this.export(docxExporterBuilder);
	}
	
	// docx
	
	public JasperReportBuilder toHtml(final OutputStream outputStream) throws DRException
	{
		return this.toHtml(Exporters.htmlExporter(outputStream));
	}
	
	public JasperReportBuilder toHtml(final JasperHtmlExporterBuilder htmlExporterBuilder) throws DRException
	{
		return this.export(htmlExporterBuilder);
	}
	
	// html
	
	public JasperReportBuilder toOds(final OutputStream outputStream) throws DRException
	{
		return this.toOds(Exporters.odsExporter(outputStream));
	}
	
	public JasperReportBuilder toOds(final JasperOdsExporterBuilder odsExporterBuilder) throws DRException
	{
		return this.export(odsExporterBuilder);
	}
	
	// ods
	
	public JasperReportBuilder toOdt(final OutputStream outputStream) throws DRException
	{
		return this.toOdt(Exporters.odtExporter(outputStream));
	}
	
	public JasperReportBuilder toOdt(final JasperOdtExporterBuilder odtExporterBuilder) throws DRException
	{
		return this.export(odtExporterBuilder);
	}
	
	// odt
	
	public JasperReportBuilder toPdf(final OutputStream outputStream) throws DRException
	{
		return this.toPdf(Exporters.pdfExporter(outputStream));
	}
	
	public JasperReportBuilder toPdf(final JasperPdfExporterBuilder pdfExporterBuilder) throws DRException
	{
		return this.export(pdfExporterBuilder);
	}
	
	// pdf
	
	public JasperReportBuilder toRtf(final OutputStream outputStream) throws DRException
	{
		return this.toRtf(Exporters.rtfExporter(outputStream));
	}
	
	public JasperReportBuilder toRtf(final JasperRtfExporterBuilder rtfExporterBuilder) throws DRException
	{
		return this.export(rtfExporterBuilder);
	}
	
	// rtf
	
	public JasperReportBuilder toText(final OutputStream outputStream) throws DRException
	{
		return this.toText(Exporters.textExporter(outputStream));
	}
	
	public JasperReportBuilder toText(final JasperTextExporterBuilder textExporterBuilder) throws DRException
	{
		return this.export(textExporterBuilder);
	}
	
	// text
	
	public JasperReportBuilder toXlsx(final OutputStream outputStream) throws DRException
	{
		return this.toXlsx(Exporters.xlsxExporter(outputStream));
	}
	
	public JasperReportBuilder toXlsx(final JasperXlsxExporterBuilder xlsxExporterBuilder) throws DRException
	{
		return this.export(xlsxExporterBuilder);
	}
	
	// xlsx
	
	public JasperReportBuilder toPptx(final OutputStream outputStream) throws DRException
	{
		return this.toPptx(Exporters.pptxExporter(outputStream));
	}
	
	public JasperReportBuilder toPptx(final JasperPptxExporterBuilder pptxExporterBuilder) throws DRException
	{
		return this.export(pptxExporterBuilder);
	}
	
	// pptx
	
	public JasperReportBuilder export(
		final AbstractJasperExporterBuilder<?, ? extends AbstractJasperExporter> exporterBuilder)
		throws DRException
	{
		Validate.notNull(exporterBuilder, "exporterBuilder must not be null");
		try
		{
			final ExporterTransform exporterTransform = new ExporterTransform(exporterBuilder.build());
			@SuppressWarnings("unchecked")
			final Exporter<ExporterInput, ?, ?, ?> exporter =
				(Exporter<ExporterInput, ?, ?, ?>)exporterTransform.transform();
			exporter.setExporterInput(new SimpleExporterInput(this.toJasperPrint()));
			exporter.exportReport();
		}
		catch(final JRException e)
		{
			throw new DRException(e);
		}
		return this;
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	public JasperReportBuilder setConnection(final Connection connection)
	{
		Validate.notNull(connection, "connection must not be null");
		this.connection = connection;
		return this;
	}
	
	public JRDataSource getDataSource()
	{
		return this.dataSource;
	}
	
	public JasperReportBuilder setDataSource(final JRDataSource dataSource)
	{
		this.dataSource = dataSource;
		this.connection = null;
		return this;
	}
}
