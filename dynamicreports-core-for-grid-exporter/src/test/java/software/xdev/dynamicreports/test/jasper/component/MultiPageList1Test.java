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
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class MultiPageList1Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<Integer> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final MultiPageListBuilder multiPageList = cmp.multiPageList();
		for(int i = 0; i < 150; i++)
		{
			multiPageList.add(cmp.text("text" + i));
		}
		rb.title(multiPageList).columns(this.column1 = col.column("Column1", "field1", Integer.class));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		for(int i = 0; i < 150; i++)
		{
			this.elementValueTest("title.textField1", i, "text" + i);
		}
		
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailValueTest(this.column1, "0", "1");
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
