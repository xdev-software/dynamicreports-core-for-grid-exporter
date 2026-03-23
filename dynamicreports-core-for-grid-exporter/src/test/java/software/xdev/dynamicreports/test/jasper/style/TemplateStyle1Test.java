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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class TemplateStyle1Test extends AbstractJasperStyleTest implements Serializable
{

	private TextColumnBuilder<Integer> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<Integer> column3;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder textStyle =
			stl.style().setName("textStyle").setForegroundColor(Color.BLACK).setPadding(2);
		final StyleBuilder columnStyle = stl.style(textStyle).setName("columnStyle").italic();
		
		final StyleBuilder columnStyle1 = stl.style(columnStyle).setName("columnStyle1").conditionalStyles(
			stl.conditionalStyle(new ConditionExpression(2, 5, 6, 7)).bold(),
			stl.conditionalStyle(new ConditionExpression(16)).setBackgroundColor(Color.ORANGE));
		
		final StyleBuilder columnStyle2 = stl.style(stl.templateStyle("columnStyle")).bold();
		final StyleBuilder titleStyle0 = stl.style(textStyle).bold();
		final StyleBuilder titleStyle =
			stl.style(titleStyle0).setName("titleStyle").setBottomBorder(stl.pen2Point());
		final StyleBuilder columnTitleStyle3 =
			stl.style(titleStyle).setName("columnTitleStyle3").setBorder(stl.pen2Point());
		final StyleBuilder subtotalStyle = stl.style(stl.templateStyle("textStyle")).setName("subtotalStyle")
			.bold().setTopBorder(stl.pen1Point()).setBackgroundColor(Color.YELLOW);
		final StyleBuilder textFieldStyle =
			stl.style(textStyle).setName("textFieldStyle").setBold(true).setFontSize(15);
		
		rb.templateStyles(textStyle, columnStyle, columnStyle1, titleStyle, columnTitleStyle3,
				subtotalStyle, textFieldStyle)
			.columns(
				this.column1 = col.column("Column1", "field1", Integer.class)
					.setStyle(stl.templateStyle("columnStyle1")),
				this.column2 = col.column("Column2", "field2", Integer.class).setStyle(columnStyle2),
				this.column3 = col.column("Column3", "field3", Integer.class)
					.setTitleStyle(stl.templateStyle("columnTitleStyle3")))
			.setTextStyle(stl.templateStyle("textStyle"))
			.setColumnTitleStyle(stl.templateStyle("titleStyle"))
			.setColumnStyle(stl.templateStyle("columnStyle"))
			.setSubtotalStyle(stl.templateStyle("subtotalStyle")).setHighlightDetailOddRows(true)
			.setHighlightDetailEvenRows(true).subtotalsAtSummary(this.subtotal1 = sbt.sum(this.column1))
			.detailRowHighlighters(
				stl.conditionalStyle(new ConditionExpression(5, 16)).setBackgroundColor(Color.RED),
				stl.conditionalStyle(new ConditionExpression(2, 9)).setForegroundColor(Color.RED))
			.title(cmp.horizontalList().setStyle(stl.style(stl.pen1Point()))
				.add(cmp.text("text").setStyle(stl.templateStyle("textFieldStyle")))
				.add(cmp.text("text")));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		final Color color1 = new Color(240, 240, 240);
		final Color color2 = new Color(200, 200, 200);
		
		// column1
		this.columnTitleStyleTest(this.column1, 0, Color.BLACK, null, TEST_FONT_NAME, 10f, true, null);
		this.columnTitleBorderTest(
			this.column1, 0, Color.BLACK, LineStyleEnum.SOLID, 0, Color.BLACK,
			LineStyleEnum.SOLID, 2, Color.BLACK, LineStyleEnum.SOLID, 0, Color.BLACK,
			LineStyleEnum.SOLID, 0);
		this.columnTitlePaddingTest(this.column1, 0, 2, 2, 2, 2);
		
		this.columnDetailPaddingTest(this.column1, 0, 2, 2, 2, 2);
		this.columnDetailStyleTest(this.column1, 0, Color.BLACK, color1, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column1, 1, Color.BLACK, color2, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column1, 2, Color.RED, color1, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column1, 5, Color.BLACK, Color.RED, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column1, 6, Color.BLACK, color1, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column1, 9, Color.RED, color2, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column1, 16, Color.BLACK, Color.ORANGE, TEST_FONT_NAME, 10f, null, true);
		
		// column2
		this.columnTitleStyleTest(this.column2, 0, Color.BLACK, null, TEST_FONT_NAME, 10f, true, null);
		this.columnTitleBorderTest(
			this.column2, 0, Color.BLACK, LineStyleEnum.SOLID, 0, Color.BLACK,
			LineStyleEnum.SOLID, 2, Color.BLACK, LineStyleEnum.SOLID, 0, Color.BLACK,
			LineStyleEnum.SOLID, 0);
		this.columnTitlePaddingTest(this.column2, 0, 2, 2, 2, 2);
		
		this.columnDetailPaddingTest(this.column2, 0, 2, 2, 2, 2);
		this.columnDetailStyleTest(this.column2, 0, Color.BLACK, color1, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 1, Color.BLACK, color2, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 2, Color.RED, color1, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 5, Color.BLACK, Color.RED, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 6, Color.BLACK, color1, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 9, Color.RED, color2, TEST_FONT_NAME, 10f, true, true);
		this.columnDetailStyleTest(this.column2, 16, Color.BLACK, Color.RED, TEST_FONT_NAME, 10f, true, true);
		
		// column3
		this.columnTitleStyleTest(this.column3, 0, Color.BLACK, null, TEST_FONT_NAME, 10f, true, null);
		this.columnTitleBorderTest(
			this.column3, 0, Color.BLACK, LineStyleEnum.SOLID, 2, Color.BLACK,
			LineStyleEnum.SOLID, 2, Color.BLACK, LineStyleEnum.SOLID, 2, Color.BLACK,
			LineStyleEnum.SOLID, 2);
		this.columnTitlePaddingTest(this.column3, 0, 2, 2, 2, 2);
		
		this.columnDetailPaddingTest(this.column3, 0, 2, 2, 2, 2);
		this.columnDetailStyleTest(this.column3, 0, Color.BLACK, color1, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 1, Color.BLACK, color2, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 2, Color.RED, color1, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 5, Color.BLACK, Color.RED, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 6, Color.BLACK, color1, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 9, Color.RED, color2, TEST_FONT_NAME, 10f, null, true);
		this.columnDetailStyleTest(this.column3, 16, Color.BLACK, Color.RED, TEST_FONT_NAME, 10f, null, true);
		
		// subtotal1
		this.subtotalStyleTest(this.subtotal1, 0, Color.BLACK, Color.YELLOW, TEST_FONT_NAME, 10f, true, null);
		this.subtotalBorderTest(
			this.subtotal1, 0, Color.BLACK, LineStyleEnum.SOLID, 1, Color.BLACK,
			LineStyleEnum.SOLID, 0, Color.BLACK, LineStyleEnum.SOLID, 0, Color.BLACK,
			LineStyleEnum.SOLID, 0);
		this.subtotalPaddingTest(this.subtotal1, 0, 2, 2, 2, 2);
		
		// title
		this.styleTest("title.list1", 0, null, null, "SansSerif", null, null, null);
		this.borderTest("title.list1", 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1, null,
			LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		this.paddingTest("title.list1", 0, 0, 0, 0, 0);
		this.styleTest("title.textField1", 0, Color.BLACK, null, TEST_FONT_NAME, 15f, true, null);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for(int i = 0; i < 20; i++)
		{
			dataSource.add(i, i + 1, i + 2);
		}
		return dataSource;
	}
	
	static class ConditionExpression extends AbstractSimpleExpression<Boolean>
	{

		private final List<Integer> values;
		
		ConditionExpression(final Integer... values)
		{
			this.values = Arrays.asList(values);
		}
		
		@Override
		public Boolean evaluate(final ReportParameters reportParameters)
		{
			final Integer value = (Integer)reportParameters.getValue("field1");
			return this.values.contains(value);
		}
	}
}
