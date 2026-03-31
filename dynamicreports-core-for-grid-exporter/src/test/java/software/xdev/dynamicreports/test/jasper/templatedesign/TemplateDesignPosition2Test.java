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
package software.xdev.dynamicreports.test.jasper.templatedesign;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

import java.io.InputStream;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.exception.DRException;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class TemplateDesignPosition2Test extends AbstractJasperPositionTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb) throws DRException
	{
		final InputStream is = TemplateDesignPosition1Test.class.getResourceAsStream("templatedesign2.jrxml");
		rb.setTemplateDesign(is).columns(this.column1 = col.column("Column1", "field1", String.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnTitlePositionTest(this.column1, 0, 20, 134, 555, 16);
		this.columnDetailPositionTest(this.column1, 0, 20, 150, 555, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 1; i++)
		{
			dataSource.add("row");
		}
		return dataSource;
	}
}
