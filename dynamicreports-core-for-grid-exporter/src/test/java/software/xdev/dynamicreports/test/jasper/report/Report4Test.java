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

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.Language;
import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class Report4Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<Integer> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setPageColumnsPerPage(2)
			.setPrintOrder(Orientation.HORIZONTAL)
			.setColumnDirection(RunDirection.RIGHT_TO_LEFT)
			.setLanguage(Language.GROOVY)
			.columns(this.column1 = col.column("Column1", "field1", Integer.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnDetailPositionTest(this.column1, 0, 298, 26, 287, 16);
		this.columnDetailPositionTest(this.column1, 1, 11, 26, 287, 16);
		this.columnDetailPositionTest(this.column1, 2, 298, 42, 287, 16);
		this.columnDetailPositionTest(this.column1, 3, 11, 42, 287, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 4; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
