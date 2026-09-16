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
package software.xdev.dynamicreports.test.jasper.tableofcontents;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TableOfContents5Test extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.tableOfContents()
			.columns(
				column1 = col.column("Column1", "field1", type.stringType()),
				col.column("Column2", "field2", type.stringType()))
			.groupBy(grp.group(column1).setAddToTableOfContents(false));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		for(int i = 0; i < 5; i++)
		{
			dataSource.add("text1", "value");
		}
		for(int i = 0; i < 5; i++)
		{
			dataSource.add("text2", "value");
		}
		return dataSource;
	}
}
