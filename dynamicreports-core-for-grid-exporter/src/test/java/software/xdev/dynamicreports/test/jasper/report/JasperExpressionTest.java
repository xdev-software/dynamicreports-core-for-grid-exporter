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
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class JasperExpressionTest extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
			this.column2 = col.column("field2", Integer.class).setTitle(exp.jasperSyntax("\"Column2\"", String.class)),
			this.column3 = col.column(exp.jasperSyntax("$F{field1} - $F{field2}", Integer.class)));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		
		this.columnDetailValueTest(this.column3, "0", "8", "3");
		
		try
		{
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			this.getReportBuilder().toJrXml(bos);
			final String jrxml = bos.toString();
			Assertions.assertFalse(StringUtils.contains(jrxml, "software.xdev.dynamicreports"));
		}
		catch(final DRException e)
		{
			Assertions.fail(e.getMessage());
		}
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(1, 1);
		dataSource.add(10, 2);
		dataSource.add(5, 2);
		return dataSource;
	}
}
