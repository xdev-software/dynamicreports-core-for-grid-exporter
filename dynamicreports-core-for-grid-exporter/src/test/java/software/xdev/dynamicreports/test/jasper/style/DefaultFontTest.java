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
package software.xdev.dynamicreports.test.jasper.style;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.style.FontBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class DefaultFontTest extends AbstractJasperStyleTest implements Serializable
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final FontBuilder defaultFont = stl.font().setFontSize(12);
		
		rb.setDefaultFont(defaultFont).columns(
			this.column1 = col.column("Column1", "field1", type.stringType()).setStyle(stl.style().bold()),
			this.column2 = col.column("Column2", "field2", type.stringType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnDetailStyleTest(this.column1, 0, null, null, TEST_FONT_NAME, 12f, true, null);
		this.columnDetailStyleTest(this.column1, 1, null, null, TEST_FONT_NAME, 12f, true, null);
		this.columnDetailStyleTest(this.column1, 2, null, null, TEST_FONT_NAME, 12f, true, null);
		
		// column2
		this.columnDetailStyleTest(this.column2, 0, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
		this.columnDetailStyleTest(this.column2, 1, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
		this.columnDetailStyleTest(this.column2, 2, Color.BLACK, null, TEST_FONT_NAME, 12f, null, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("1", "1", "1");
		dataSource.add("1", "1", "1");
		dataSource.add("1", "1", "1");
		return dataSource;
	}
}
