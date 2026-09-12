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
package software.xdev.dynamicreports.test.jasper.column;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.ComponentColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.CustomSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ComponentColumnPositionTest extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private ComponentColumnBuilder column3;
	private CustomSubtotalBuilder<String> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				col.componentColumn(cmp.filler().setFixedWidth(5)),
				col.componentColumn(cmp.filler()).setFixedWidth(5),
				this.column3 = col.componentColumn(
					"Column3",
					cmp.verticalList(cmp.horizontalList(cmp.text("aa"), cmp.text("cc")), cmp.text("aa"))))
			.subtotalsAtSummary(this.subtotal1 = sbt.customValue(exp.text("subtotal"), this.column3));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
		this.elementPositionTest("detail.list1", 0, 10, 26, 575, 32);
		this.elementPositionTest("detail.list1", 1, 10, 58, 575, 32);
		
		// column1
		this.columnTitlePositionTest(this.column1, 0, 0, 0, 188, 16);
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 188, 32);
		this.columnDetailPositionTest(this.column1, 1, 0, 0, 188, 32);
		
		// column3
		this.columnTitlePositionTest(this.column3, 0, 198, 0, 377, 16);
		// columnDetailPositionTest(column3, 0, 198, 0, 377, 32);
		// columnDetailPositionTest(column3, 1, 198, 0, 377, 32);
		
		this.elementPositionTest("detail.list2", 0, 198, 0, 377, 16);
		this.elementPositionTest("detail.list2", 1, 198, 0, 377, 16);
		
		this.elementPositionTest("detail.textField1", 0, 0, 0, 188, 16);
		this.elementPositionTest("detail.textField1", 1, 0, 0, 188, 16);
		
		this.elementPositionTest("detail.textField2", 0, 188, 0, 189, 16);
		this.elementPositionTest("detail.textField2", 1, 188, 0, 189, 16);
		
		this.elementPositionTest("detail.textField3", 0, 198, 16, 377, 16);
		this.elementPositionTest("detail.textField3", 1, 198, 16, 377, 16);
		
		// summary
		this.elementPositionTest("summary.list1", 0, 10, 90, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 198, 0, 377, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 2; i++)
		{
			dataSource.add("test");
		}
		return dataSource;
	}
}
