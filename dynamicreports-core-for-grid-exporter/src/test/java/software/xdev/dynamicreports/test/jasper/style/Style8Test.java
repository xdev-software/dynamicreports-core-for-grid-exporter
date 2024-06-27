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
package software.xdev.dynamicreports.test.jasper.style;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.BooleanColumnBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class Style8Test extends AbstractJasperStyleTest
{
	private BooleanColumnBuilder column1;
	private BooleanColumnBuilder column2;
	private BooleanColumnBuilder column3;
	private TextColumnBuilder<String> column4;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setColumnStyle(stl.style(stl.pen1Point()).bold()).columns(
			this.column1 = col.booleanColumn("field1"),
			this.column2 = col.booleanColumn("field2")
				.setHorizontalImageAlignment(HorizontalImageAlignment.RIGHT),
			this.column3 = col.booleanColumn("field3")
				.setStyle(stl.style(stl.pen2Point())
					.setHorizontalImageAlignment(HorizontalImageAlignment.CENTER)
					.setVerticalImageAlignment(VerticalImageAlignment.MIDDLE)),
			this.column4 = col.column("field4", type.stringType()));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.columnDetailStyleTest(this.column1, 0, null, null, TEST_FONT_NAME, 10f, true, null);
		this.columnDetailBorderTest(
			this.column1, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1,
			null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		this.columnDetailBorderTest(
			this.column2, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1,
			null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		this.columnDetailBorderTest(
			this.column3, 0, null, LineStyleEnum.SOLID, 2, null, LineStyleEnum.SOLID, 2,
			null, LineStyleEnum.SOLID, 2, null, LineStyleEnum.SOLID, 2);
		this.columnDetailStyleTest(this.column4, 0, null, null, TEST_FONT_NAME, 10f, true, null);
		this.columnDetailBorderTest(
			this.column4, 0, null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1,
			null, LineStyleEnum.SOLID, 1, null, LineStyleEnum.SOLID, 1);
		
		this.columnDetailAlignmentTest(this.column1, 0, HorizontalTextAlignEnum.CENTER);
		this.columnDetailAlignmentTest(this.column2, 0, HorizontalImageAlignEnum.RIGHT);
		this.columnDetailAlignmentTest(this.column3, 0, HorizontalImageAlignEnum.CENTER);
		
		this.columnDetailAlignmentTest(this.column1, 0, VerticalTextAlignEnum.TOP);
		this.columnDetailAlignmentTest(this.column2, 0, VerticalImageAlignEnum.TOP);
		this.columnDetailAlignmentTest(this.column3, 0, VerticalImageAlignEnum.MIDDLE);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add(true, true, true, "text");
		return dataSource;
	}
}
