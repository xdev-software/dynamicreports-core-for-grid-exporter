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
package software.xdev.dynamicreports.test.jasper.group;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Group2Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private TextColumnBuilder<Integer> column4;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		final ColumnGroupBuilder group1;
		
		rb.setLocale(Locale.ENGLISH)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class),
				this.column3 = col.column("Column3", "field3", Integer.class),
				this.column4 = col.column("Column4", "field4", Integer.class))
			.groupBy(
				grp.group(column1).setStartInNewPage(true).header(cmp.text("header1")).footer(cmp.text("footer1")),
				group1 = grp.group(column1)
					.header(cmp.text("header2"))
					.footer(cmp.text("footer2"))
					.setShowColumnHeaderAndFooter(true)
					.setReprintHeaderOnEachPage(true)
					.setPrintSubtotalsWhenExpression(exp.printNotInFirstPage()))
			.subtotalsAtGroupFooter(group1, this.subtotal1 = sbt.sum(this.column4));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		this.columnTitleCountTest(this.column2, 3);
		this.columnTitleValueTest(this.column2, "Column2", "Column2", "Column2");
		this.columnTitleCountTest(this.column3, 3);
		this.columnTitleValueTest(this.column3, "Column3", "Column3", "Column3");
		this.columnTitleCountTest(this.column4, 3);
		this.columnTitleValueTest(this.column4, "Column4", "Column4", "Column4");
		
		this.elementCountTest("columnHeaderForGroup.column_field2.title1", 3);
		this.elementValueTest("columnHeaderForGroup.column_field2.title1", "Column2", "Column2", "Column2");
		this.elementCountTest("columnHeaderForGroup.column_field3.title1", 3);
		this.elementValueTest("columnHeaderForGroup.column_field3.title1", "Column3", "Column3", "Column3");
		this.elementCountTest("columnHeaderForGroup.column_field4.title1", 3);
		this.elementValueTest("columnHeaderForGroup.column_field4.title1", "Column4", "Column4", "Column4");
		
		this.elementCountTest("groupHeader.textField1", 2);
		this.elementValueTest("groupHeader.textField1", "header1", "header1");
		
		this.elementCountTest("groupFooter.textField1", 2);
		this.elementValueTest("groupFooter.textField1", "footer1", "footer1");
		
		this.elementCountTest("groupHeader.textField2", 3);
		this.elementValueTest("groupHeader.textField2", "header2", "header2", "header2");
		
		this.elementCountTest("groupFooter.textField2", 2);
		this.elementValueTest("groupFooter.textField2", "footer2", "footer2");
		
		this.subtotalCountTest(this.subtotal1, 1);
		this.subtotalValueTest(this.subtotal1, "1,225");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group1", i, i, i);
		}
		for(int i = 0; i < 50; i++)
		{
			dataSource.add("group2", i, i, i);
		}
		return dataSource;
	}
}
