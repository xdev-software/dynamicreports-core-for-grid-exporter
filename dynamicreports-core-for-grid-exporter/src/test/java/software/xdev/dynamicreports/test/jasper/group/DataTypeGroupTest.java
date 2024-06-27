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
package software.xdev.dynamicreports.test.jasper.group;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.util.Date;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class DataTypeGroupTest extends AbstractJasperValueTest
{
	private TextColumnBuilder<Date> column1;
	private TextColumnBuilder<Date> column2;
	private ColumnGroupBuilder group1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setLocale(Locale.ENGLISH)
			.columns(
				this.column1 = col.column("Column1", "field1", type.dateMonthType()),
				this.column2 = col.column("Column2", "field1", type.dateType()))
			.groupBy(this.group1 = grp.group(this.column1).groupByDataType());
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column2
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailCountTest(this.column2, 2);
		this.columnDetailValueTest(this.column2, "01/01/2010", "02/01/2010");
		
		// group1
		this.groupHeaderTitleCountTest(this.group1, 0);
		this.groupHeaderCountTest(this.group1, 2);
		this.groupHeaderValueTest(this.group1, "January", "February");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 1; i++)
		{
			dataSource.add(this.toDate(2010, 1, 1));
			dataSource.add(this.toDate(2010, 2, 1));
		}
		return dataSource;
	}
}
