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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.concatenatedReport;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import software.xdev.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.exception.DRException;


class ConcatenatedReport5Test
{
	JasperConcatenatedReportBuilder concatenatedReport;
	
	@BeforeEach
	public void init()
	{
		final JasperReportBuilder report1 = report().title(cmp.text("text1"))
			.setWhenNoDataType(WhenNoDataType.BLANK_PAGE)
			.setDataSource(new DataSource());
		final JasperReportBuilder report2 = report().title(cmp.text("text2"))
			.setWhenNoDataType(WhenNoDataType.BLANK_PAGE)
			.setDataSource(new DataSource());
		final JasperReportBuilder report3 = report().title(cmp.text("text3"))
			.setWhenNoDataType(WhenNoDataType.BLANK_PAGE)
			.setDataSource(new DataSource());
		
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
			Assertions.assertEquals("text1\ntext2\ntext3\n", new String(bos.toByteArray()));
			
			bos = new ByteArrayOutputStream();
			this.concatenatedReport.toCsv(bos);
			Assertions.assertEquals("text1\ntext2\ntext3\n", new String(bos.toByteArray()));
		}
		catch(final DRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	static class DataSource implements JRDataSource
	{
		private boolean next = true;
		
		@Override
		public boolean next() throws JRException
		{
			if(this.next)
			{
				this.next = false;
				return true;
			}
			return this.next;
		}
		
		@Override
		public Object getFieldValue(final JRField jrField) throws JRException
		{
			return null;
		}
	}
}
