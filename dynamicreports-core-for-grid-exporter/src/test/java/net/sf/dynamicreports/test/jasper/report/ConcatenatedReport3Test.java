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
package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.jasper.base.reporthandler.JasperPrintListFileHandler;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;


/**
 * @author Ricardo Mariaca
 */
class ConcatenatedReport3Test
{
	JasperConcatenatedReportBuilder concatenatedReport;
	
	@BeforeEach
	public void init()
	{
		final JasperReportBuilder report1 = report().title(cmp.text("text1")).pageFooter(cmp.pageNumber());
		final JasperReportBuilder report2 = report().title(cmp.text("text2")).pageFooter(cmp.pageNumber());
		final JasperReportBuilder report3 = report().title(cmp.text("text3")).pageFooter(cmp.pageNumber());
		
		final JRSwapFile swapFile = new JRSwapFile(System.getProperty("java.io.tmpdir"), 2000, 5000);
		this.concatenatedReport = concatenatedReport(new JasperPrintListFileHandler(
			System.getProperty("java.io.tmpdir"),
			new JRSwapFileVirtualizer(3, swapFile)));
		this.concatenatedReport.continuousPageNumbering();
		this.concatenatedReport.concatenate(report1, report2, report3);
	}
	
	@Test
	void test()
	{
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals(
				"text1\n1\ntext2\n2\ntext3\n3\n",
				new String(bos.toByteArray()));
			
			this.concatenatedReport.setContinuousPageNumbering(false);
			bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals(
				"text1\n1\ntext2\n2\ntext3\n3\n",
				new String(bos.toByteArray()));
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
}
