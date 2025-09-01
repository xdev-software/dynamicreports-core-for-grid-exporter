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
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class Style2Test extends AbstractJasperStyleTest implements Serializable
{

	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<String> column2;
	private ColumnGroupBuilder group1;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder textStyle = stl.style().setPadding(2);
		final StyleBuilder titleStyle = stl.style(textStyle).bold();
		final StyleBuilder subtotalStyle = stl.style(2).setTopBorder(stl.pen1Point()).bold();
		
		rb.setTextStyle(textStyle).setColumnTitleStyle(titleStyle).setSubtotalStyle(subtotalStyle)
			.columns(
				this.column1 = col.column("Column1", "field1", type.integerType()),
				this.column2 =
					col.column("Column2", "field2", type.stringType()).setStyle(stl.style().bold()))
			.groupBy(this.group1 = grp.group(this.column2)).subtotalsAtSummary(
				this.subtotal1 = sbt.sum(this.column1).setLabel("total").setLabelStyle(stl.style().bold()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnTitleStyleTest(this.column1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
		this.columnTitlePaddingTest(this.column1, 0, 2, 2, 2, 2);
		
		this.columnDetailStyleTest(this.column1, 0, null, null, TEST_FONT_NAME, 10f, null, null);
		this.columnDetailPaddingTest(this.column1, 0, 2, 2, 2, 2);
		
		// column2
		this.groupHeaderStyleTest(this.group1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
		
		// subtotal
		this.subtotalLabelStyleTest(this.subtotal1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
		this.subtotalLabelBorderTest(
			this.subtotal1, 0, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID,
			0, null, LineStyleEnum.SOLID, 0, null, LineStyleEnum.SOLID, 0);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(1, "1");
		return dataSource;
	}
}
