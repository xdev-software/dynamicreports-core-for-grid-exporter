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

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class BooleanColumnPosition3Test extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(
			col.booleanColumn("C1", "field1").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE).setFixedWidth(100),
			col.booleanColumn("C2", "field1").setFixedWidth(110).setFixedHeight(40));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementPositionTest("columnHeader.list1", 0, 10, 10, 210, 16);
		this.elementPositionTest("columnHeader.column_field1.title1", 0, 0, 0, 100, 16);
		this.elementPositionTest("columnHeader.column_field1.title2", 0, 100, 0, 110, 16);
		
		this.elementPositionTest("detail.list1", 0, 10, 26, 210, 40);
		this.elementPositionTest("detail.column_field11", 0, 0, 0, 100, 40);
		this.elementPositionTest("detail.column_field12", 0, 100, 0, 110, 40);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add(true);
		return dataSource;
	}
}
