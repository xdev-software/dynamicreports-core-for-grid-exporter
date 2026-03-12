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
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.MultiPageListBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperPositionTest;


public class MultiPageList2Test extends AbstractJasperPositionTest
{
	private TextColumnBuilder<Integer> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final MultiPageListBuilder multiPageList1 = cmp.multiPageList();
		for(int i = 0; i < 3; i++)
		{
			final MultiPageListBuilder multiPageList2 = cmp.multiPageList();
			multiPageList1.add(multiPageList2);
			for(int j = 0; j < 50; j++)
			{
				multiPageList2.add(cmp.text("text"));
			}
		}
		rb.title(multiPageList1).columns(this.column1 = col.column("Column1", "field1", Integer.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		for(int i = 0; i < 51; i++)
		{
			this.elementPositionTest("title.textField1", i, 10, 10 + i * 16, 575, 16);
		}
		for(int i = 0; i < 51; i++)
		{
			this.elementPositionTest("title.textField1", 51 + i, 10, 10 + i * 16, 575, 16);
		}
		for(int i = 0; i < 48; i++)
		{
			this.elementPositionTest("title.textField1", 102 + i, 10, 10 + i * 16, 575, 16);
		}
		
		this.columnTitlePositionTest(this.column1, 0, 10, 778, 575, 16);
		this.columnDetailPositionTest(this.column1, 0, 10, 794, 575, 16);
		this.columnDetailPositionTest(this.column1, 1, 10, 810, 575, 16);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 2; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
}
