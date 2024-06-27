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
package software.xdev.dynamicreports.test.jasper.dataset;

import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;

import java.io.Serializable;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractValueFormatter;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class CrosstabDataset2Test extends AbstractJasperCrosstabValueTest implements Serializable
{

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		this.measure1.setValueFormatter(new ValueFormatter1());
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.setDataSource(this.createCrosstabDataSource())
			.rowGroups(
				this.rowGroup = ctab.rowGroup("field1", String.class)
					.setHeaderValueFormatter(new ValueFormatter2()))
			.columnGroups(
				this.columnGroup = ctab.columnGroup("field2", String.class)
					.setHeaderValueFormatter(new ValueFormatter2()))
			.measures(this.measure1);
		
		rb.setLocale(Locale.ENGLISH).addParameter("parameter", "parameter_value").title(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("title");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "value = c", "value = d");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 2);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "value = a", "value = b");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 4);
		this.crosstabCellValueTest(this.measure1, null, null, "value = 3", "value = 7", "value = 11", "value = 15");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "value = 10", "value = 26");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "value = 14", "value = 22");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "value = 36");
	}
	
	private JRDataSource createCrosstabDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("a", "c", 2);
		dataSource.add("a", "d", 3);
		dataSource.add("a", "d", 4);
		dataSource.add("b", "c", 5);
		dataSource.add("b", "c", 6);
		dataSource.add("b", "d", 7);
		dataSource.add("b", "d", 8);
		return dataSource;
	}
	
	static class ValueFormatter1 extends AbstractValueFormatter<String, Integer>
	{

		@Override
		public String format(final Integer value, final ReportParameters reportParameters)
		{
			Assertions.assertNotNull(reportParameters.getMasterParameters());
			try
			{
				reportParameters.getValue("parameter");
				Assertions.fail("parameter is not null");
			}
			catch(final Exception e)
			{
				// Undocumented upstream
			}
			Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));
			return "value = " + value;
		}
	}
	
	static class ValueFormatter2 extends AbstractValueFormatter<String, String>
	{

		@Override
		public String format(final String value, final ReportParameters reportParameters)
		{
			Assertions.assertNotNull(reportParameters.getMasterParameters());
			try
			{
				reportParameters.getValue("parameter");
				Assertions.fail("parameter is not null");
			}
			catch(final Exception e)
			{
				// Undocumented upstream
			}
			Assertions.assertEquals("parameter_value", reportParameters.getMasterParameters().getValue("parameter"));
			return "value = " + value;
		}
	}
}
