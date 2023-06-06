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
package software.xdev.dynamicreports.test.jasper.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;


/**
 * @author Ricardo Mariaca
 */
class ExportTest
{
	JasperReportBuilder report;
	JasperConcatenatedReportBuilder concatenatedReport;
	
	@BeforeEach
	public void init()
	{
		this.report = report();
		this.report.columns(col.column("Column1", "field1", String.class));
		this.report.setDataSource(this.createDataSource());
		
		this.concatenatedReport = concatenatedReport();
		this.concatenatedReport.concatenate(this.report, this.report, this.report);
	}
	
	@Test
	void exportTest()
	{
		try
		{
			this.report.toJrXml(new ByteArrayOutputStream());
			this.report.toCsv(new ByteArrayOutputStream());
			this.report.toDocx(new ByteArrayOutputStream());
			this.report.toHtml(new ByteArrayOutputStream());
			this.report.toOds(new ByteArrayOutputStream());
			this.report.toOdt(new ByteArrayOutputStream());
			this.report.toPdf(new ByteArrayOutputStream());
			this.report.toRtf(new ByteArrayOutputStream());
			this.report.toText(new ByteArrayOutputStream());
			this.report.toXlsx(new ByteArrayOutputStream());
			this.report.toPptx(new ByteArrayOutputStream());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	@Test
	void concatenatedExportTest()
	{
		try
		{
			this.concatenatedReport.toPng(new ByteArrayOutputStream());
			this.concatenatedReport.toCsv(new ByteArrayOutputStream());
			this.concatenatedReport.toDocx(new ByteArrayOutputStream());
			this.concatenatedReport.toHtml(new ByteArrayOutputStream());
			this.concatenatedReport.toOds(new ByteArrayOutputStream());
			this.concatenatedReport.toOdt(new ByteArrayOutputStream());
			this.concatenatedReport.toPdf(new ByteArrayOutputStream());
			this.concatenatedReport.toRtf(new ByteArrayOutputStream());
			this.concatenatedReport.toText(new ByteArrayOutputStream());
			this.concatenatedReport.toXlsx(new ByteArrayOutputStream());
			this.concatenatedReport.toPptx(new ByteArrayOutputStream());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	private JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("field1");
		return dataSource;
	}
}
