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
package software.xdev.dynamicreports.test.jasper.crosstab;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

import java.io.Serializable;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import software.xdev.dynamicreports.test.jasper.JasperTestUtils;


public class Crosstab4Test extends AbstractJasperCrosstabValueTest implements Serializable
{

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setDataPreSorted(true)
			.rowGroups(this.rowGroup = ctab.rowGroup("field1", String.class))
			.columnGroups(this.columnGroup = ctab.columnGroup("field2", String.class))
			.measures(this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM));
		
		rb.setLocale(Locale.ENGLISH).summary(crosstab, crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "d", "c");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 3);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "c", "a", "b");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 6);
		this.crosstabCellValueTest(this.measure1, null, null, "1", "1", "1", "1", "1", "1");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 3);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "2", "2", "2");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "3", "3");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "6");
		
		// column group
		this.elementCountTest(this.getPrefix(2) + JasperTestUtils.getCrosstabGroupHeaderName(this.columnGroup), 2);
		this.elementCountTest(this.getPrefix(2) + JasperTestUtils.getCrosstabGroupTotalHeaderName(this.columnGroup), 1);
		
		// row group
		this.elementCountTest(this.getPrefix(2) + JasperTestUtils.getCrosstabGroupHeaderName(this.rowGroup), 3);
		this.elementCountTest(this.getPrefix(2) + JasperTestUtils.getCrosstabGroupTotalHeaderName(this.rowGroup), 1);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("c", "d", 1);
		dataSource.add("c", "c", 1);
		dataSource.add("a", "d", 1);
		dataSource.add("a", "c", 1);
		dataSource.add("b", "d", 1);
		dataSource.add("b", "c", 1);
		
		return dataSource;
	}
}
