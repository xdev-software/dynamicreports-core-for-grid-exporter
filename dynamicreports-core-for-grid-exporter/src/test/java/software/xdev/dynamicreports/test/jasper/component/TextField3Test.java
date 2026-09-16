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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.constant.JasperProperty;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TextField3Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(this.column1 = col.column("test test", "field1", String.class)
				.setFixedWidth(25)
				.addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true")
				.addTitleProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true"))
			.title(cmp.text("test test")
					.setFixedWidth(25)
					.addProperty(JasperProperty.PRINT_KEEP_FULL_TEXT, "true"), cmp.text("test test").setFixedWidth(25),
				cmp.text("test test").setFixedWidth(25));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementValueTest("title.textField1", 0, "test ");
		this.elementFullValueTest("title.textField1", 0, "test test");
		this.elementValueTest("title.textField2", 0, "test test");
		this.elementValueTest("title.textField3", 0, "test ");
		this.elementFullValueTest("title.textField3", 0, "test ");
		
		this.columnTitleValueTest(this.column1, 0, "test ");
		this.columnTitleFullValueTest(this.column1, 0, "test test");
		
		this.columnDetailValueTest(this.column1, 0, "test ");
		this.columnDetailFullValueTest(this.column1, 0, "test test");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("test test");
		return dataSource;
	}
}
