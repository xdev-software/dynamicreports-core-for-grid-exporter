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
package software.xdev.dynamicreports.test.jasper.component;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TextField5Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.detail(cmp.horizontalList(cmp.text(field("field1", type.stringType()))
			.setPrintRepeatedValues(false)
			.setPrintInFirstWholeBand(true), cmp.text(field("field2", type.integerType()))));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		// textField1
		this.elementCountTest("detail.textField1", 3);
		this.elementValueTest("detail.textField1", "test1", "test2", "test2");
		// textField2
		this.elementCountTest("detail.textField2", 60);
		this.elementValueTest("detail.textField2", 0, "0");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("test1", i);
		}
		for(int i = 0; i < 30; i++)
		{
			dataSource.add("test2", i);
		}
		return dataSource;
	}
}
