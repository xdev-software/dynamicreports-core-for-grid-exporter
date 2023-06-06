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
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;


/**
 * @author Ricardo Mariaca
 */
class ConcatenatedReport2Test
{
	JasperConcatenatedReportBuilder concatenatedReport;
	
	@BeforeEach
	public void init()
	{
		final JasperReportBuilder report1 =
			report().columns(col.column("field1", String.class)).pageFooter(cmp.pageNumber()).setDataSource(
				this.createDataSource("text1"));
		final JasperReportBuilder report2 =
			report().columns(col.column("field1", String.class)).pageFooter(cmp.pageNumber()).setDataSource(
				this.createDataSource("text2"));
		final JasperReportBuilder report3 =
			report().columns(col.column("field1", String.class)).pageFooter(cmp.pageNumber()).setDataSource(
				this.createDataSource("text3"));
		
		this.concatenatedReport = concatenatedReport();
		this.concatenatedReport.concatenate(report1, report2, report3);
	}
	
	@Test
	void test()
	{
		try
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals(
				"text1\n1\ntext2\n1\ntext3\n1\n",
                bos.toString());
			
			this.concatenatedReport.continuousPageNumbering();
			bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals(
				"text1\n1\ntext2\n2\ntext3\n3\n",
                bos.toString());
			
			this.concatenatedReport.setContinuousPageNumbering(false);
			bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals(
				"text1\n1\ntext2\n1\ntext3\n1\n",
                bos.toString());
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	private JRDataSource createDataSource(final String text)
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add(text);
		return dataSource;
	}
}
