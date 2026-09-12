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
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;

import java.io.Serializable;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintImage;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class BooleanColumn2Test extends AbstractJasperValueTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.setTemplate(template().setBooleanEmptyWhenNullValue(true))
			.columns(
				col.booleanColumn("field1").setComponentType(BooleanComponentType.TEXT_TRUE_FALSE),
				col.booleanColumn("field1")
					.setComponentType(BooleanComponentType.TEXT_TRUE_FALSE)
					.setEmptyWhenNullValue(false));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.elementCountTest("detail.column_field11", 3);
		this.elementValueTest("detail.column_field11", "True", "False", "");
		
		this.elementCountTest("detail.column_field12", 3);
		this.elementValueTest("detail.column_field12", "True", "False", "False");
		
		Assertions.assertNull(((JRPrintImage)this.getElementAt("detail.column_field13", 2)).getRenderer());
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add(true);
		dataSource.add(false);
		dataSource.add();
		return dataSource;
	}
}
