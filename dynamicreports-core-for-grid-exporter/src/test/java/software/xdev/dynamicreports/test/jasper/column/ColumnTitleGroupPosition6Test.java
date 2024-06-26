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

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grid;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class ColumnTitleGroupPosition6Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<String> column4;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.column1 = col.column("field1", String.class);
		this.column2 = col.column("field2", Integer.class);
		this.column3 = col.column("field3", String.class).setFixedWidth(50);
		this.column4 = col.column("field4", String.class);
		
		rb.columnGrid(this.column1, grid.titleGroup("Group 1", this.column2), grid.titleGroup("Group 2",
				this.column3, this.column4))
			.columns(this.column1, this.column2, this.column3, this.column4);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 575, 16);
		this.elementPositionTest("columnHeader.columngroup.title1", 0, 164, 0, 164, 16);
		this.elementPositionTest("columnHeader.columngroup.title2", 0, 328, 0, 247, 16);
		// column1
		this.columnDetailPositionTest(this.column1, 0, 0, 0, 164, 16);
		// column2
		this.columnDetailPositionTest(this.column2, 0, 164, 0, 164, 16);
		// column3
		this.columnDetailPositionTest(this.column3, 0, 328, 0, 50, 16);
		// column4
		this.columnDetailPositionTest(this.column4, 0, 378, 0, 197, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add("text", 1, "text", "text");
		return dataSource;
	}
}
