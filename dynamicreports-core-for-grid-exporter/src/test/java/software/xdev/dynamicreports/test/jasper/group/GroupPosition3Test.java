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

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.sbt;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRGroup;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.subtotal.AggregationSubtotalBuilder;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class GroupPosition3Test extends AbstractJasperPositionTest
{
	
	private ColumnGroupBuilder group1;
	private TextColumnBuilder<Integer> column2;
	private AggregationSubtotalBuilder<Integer> subtotal1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.columns(
				column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class))
			.groupBy(this.group1 = grp.group(column1)
				.setFooterPosition(GroupFooterPosition.FORCE_AT_BOTTOM)
				.keepTogether()
				.setMinHeightToStartNewPage(100))
			.subtotalsAtFirstGroupFooter(this.subtotal1 = sbt.sum(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
		this.elementPositionTest("columnHeader.filler1", 0, 0, 0, 10, 16);
		this.elementPositionTest("detail.list1", 0, 10, 42, 575, 16);
		this.elementPositionTest("detail.list1", 1, 10, 58, 575, 16);
		
		// group1
		this.groupHeaderPositionTest(this.group1, 0, 10, 26, 575, 16);
		// column2
		this.columnTitlePositionTest(this.column2, 0, 10, 0, 565, 16);
		this.columnDetailPositionTest(this.column2, 0, 10, 0, 565, 16);
		this.columnDetailPositionTest(this.column2, 1, 10, 0, 565, 16);
		// summary
		this.elementPositionTest("subtotalGroupFooter.list1", 0, 10, 816, 575, 16);
		this.subtotalPositionTest(this.subtotal1, 0, 10, 0, 565, 16);
		
		final JRGroup jrGroup = this.getJasperReport().getGroups()[0];
		Assertions.assertTrue(jrGroup.isKeepTogether());
		// Assertions.assertEquals("Min height to start new page", new Integer(100), jrGroup
		// .getMinHeightToStartNewPage()); // remove this ambiguous function call
		Assertions.assertEquals(100, jrGroup.getMinHeightToStartNewPage());
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group1", i);
		}
		return dataSource;
	}
}
