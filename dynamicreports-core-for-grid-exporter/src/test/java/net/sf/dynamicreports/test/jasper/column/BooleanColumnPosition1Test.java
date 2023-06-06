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
package net.sf.dynamicreports.test.jasper.column;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperPositionTest;
import net.sf.jasperreports.engine.JRDataSource;


/**
 * @author Ricardo Mariaca
 */
public class BooleanColumnPosition1Test extends AbstractJasperPositionTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(col.booleanColumn("field1").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE),
			col.booleanColumn("field1")
				.setStyle(stl.style().setPadding(0)), col.column("field2", String.class).setFixedWidth(20));
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(1);
        this.elementPositionTest("detail.column_field11", 0, 0, 0, 277, 38);
        this.elementPositionTest("detail.column_field12", 0, 277, 0, 278, 38);
        
        this.elementPositionTest("detail.column_field11", 1, 0, 0, 277, 16);
        this.elementPositionTest("detail.column_field12", 1, 277, 0, 278, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(true, "11111");
		dataSource.add(false, "");
		return dataSource;
	}
}
