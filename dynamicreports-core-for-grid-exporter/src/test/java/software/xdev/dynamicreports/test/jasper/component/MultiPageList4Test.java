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
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintText;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.MultiPageListBuilder;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class MultiPageList4Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.column1 = col.column("Column1", "field1", type.stringType());
		
		final MultiPageListBuilder multiPageList = cmp.multiPageList();
		multiPageList.setSplitType(SplitType.PREVENT);
		multiPageList.add(cmp.subreport(this.createSubreport()));
		multiPageList.add(cmp.verticalGap(730));
		multiPageList.add(cmp.subreport(this.createSubreport()));
		rb.title(multiPageList);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		int count = 0;
		for(final JRPrintElement element : this.getJasperPrint().getPages().get(0).getElements())
		{
			if(element instanceof JRPrintText)
			{
				count++;
			}
		}
		Assertions.assertEquals(3, count);
		count = 0;
		for(final JRPrintElement element : this.getJasperPrint().getPages().get(1).getElements())
		{
			if(element instanceof JRPrintText)
			{
				count++;
			}
		}
		Assertions.assertEquals(3, count);
	}
	
	private JasperReportBuilder createSubreport()
	{
		final JasperReportBuilder report = report();
		report.columns(this.column1).setDataSource(this.createSubreportDataSource());
		
		return report;
	}
	
	protected JRDataSource createSubreportDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("text");
		dataSource.add("text");
		return dataSource;
	}
}
