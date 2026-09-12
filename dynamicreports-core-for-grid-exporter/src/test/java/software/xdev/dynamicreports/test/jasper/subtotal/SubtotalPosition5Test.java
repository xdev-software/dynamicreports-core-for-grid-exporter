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
package software.xdev.dynamicreports.test.jasper.subtotal;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class SubtotalPosition5Test extends AbstractJasperPositionTest
{
	private AggregationSubtotalBuilder<Integer> subtotal1;
	private AggregationSubtotalBuilder<Integer> subtotal2;
	private AggregationSubtotalBuilder<Integer> subtotal3;
	private AggregationSubtotalBuilder<Integer> subtotal4;
	private AggregationSubtotalBuilder<Integer> subtotal5;
	private AggregationSubtotalBuilder<Integer> subtotal6;
	private AggregationSubtotalBuilder<Integer> subtotal7;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<Integer> column1;
		
		rb.columns(column1 = col.column("Column1", "field1", Integer.class).setWidth(600))
			.subtotalsAtSummary(
				this.subtotal1 = sbt.sum(column1).setLabel("subtotal"),
				this.subtotal2 = sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.TOP),
				this.subtotal3 = sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.BOTTOM),
				this.subtotal4 = sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.LEFT),
				this.subtotal5 = sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.RIGHT),
				this.subtotal6 =
					sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.LEFT).setLabelFixedWidth(100),
				this.subtotal7 =
					sbt.sum(column1).setLabel("subtotal").setLabelPosition(Position.RIGHT).setLabelFixedWidth(150));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// summary
		// TOP
		this.subtotalIndexPositionTest(this.subtotal1, 1, 0, 10, 58, 575, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal1, 1, 0, 10, 42, 575, 16);
		// TOP
		this.subtotalIndexPositionTest(this.subtotal2, 2, 0, 10, 90, 575, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal2, 2, 0, 10, 74, 575, 16);
		// BOTTOM
		this.subtotalIndexPositionTest(this.subtotal3, 3, 0, 10, 106, 575, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal3, 3, 0, 10, 122, 575, 16);
		// LEFT
		this.subtotalIndexPositionTest(this.subtotal4, 4, 0, 287, 0, 288, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal4, 4, 0, 0, 0, 287, 16);
		// RIGHT
		this.subtotalIndexPositionTest(this.subtotal5, 5, 0, 0, 0, 287, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal5, 5, 0, 287, 0, 288, 16);
		// LEFT
		this.subtotalIndexPositionTest(this.subtotal6, 6, 0, 100, 0, 475, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal6, 6, 0, 0, 0, 100, 16);
		// RIGHT
		this.subtotalIndexPositionTest(this.subtotal7, 7, 0, 0, 0, 425, 16);
		this.subtotalLabelIndexPositionTest(this.subtotal7, 7, 0, 425, 0, 150, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add(1);
		return dataSource;
	}
}
