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
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class ExpressionCrosstabTest extends AbstractJasperCrosstabValueTest implements Serializable
{

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabVariableBuilder<Integer> variable1;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<Double> measure2;
	private CrosstabMeasureBuilder<Double> measure3;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.variable1 = ctab.variable("field4", Integer.class, Calculation.SUM);
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		this.measure2 = ctab.measure(new MeasureExpression1(), Calculation.SUM);
		this.measure3 = ctab.measure(new MeasureExpression2());
		
		final CrosstabBuilder crosstab = ctab.crosstab()
			.rowGroups(this.rowGroup = ctab.rowGroup("field1", String.class))
			.columnGroups(this.columnGroup = ctab.columnGroup("field2", String.class))
			.variables(this.variable1)
			.measures(this.measure1, this.measure2, this.measure3);
		
		rb.setLocale(Locale.ENGLISH).summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.setCrosstabBand("summary");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "c", "d");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 2);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "a", "b");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 4);
		this.crosstabCellValueTest(this.measure1, null, null, "15", "11", "7", "3");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "26", "10");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "22", "14");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "36");
		
		// measure2
		this.crosstabCellCountTest(this.measure2, null, null, 4);
		this.crosstabCellValueTest(this.measure2, null, null, "15.0", "11.0", "7.0", "3.0");
		this.crosstabCellCountTest(this.measure2, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure2, null, this.columnGroup, "26.0", "10.0");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, null, "22.0", "14.0");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, this.columnGroup, "36.0");
		
		// measure3
		this.crosstabCellCountTest(this.measure3, null, null, 4);
		this.crosstabCellValueTest(this.measure3, null, null, "7.5", "5.5", "3.5", "1.5");
		this.crosstabCellCountTest(this.measure3, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure3, null, this.columnGroup, "6.5", "2.5");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, null, "5.5", "3.5");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, this.columnGroup, "4.5");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4");
		dataSource.add("a", "c", 8, 1);
		dataSource.add("a", "c", 7, 1);
		dataSource.add("a", "d", 6, 1);
		dataSource.add("a", "d", 5, 1);
		dataSource.add("b", "c", 4, 1);
		dataSource.add("b", "c", 3, 1);
		dataSource.add("b", "d", 2, 1);
		dataSource.add("b", "d", 1, 1);
		return dataSource;
	}
	
	static class MeasureExpression1 extends AbstractSimpleExpression<Double>
	{

		@Override
		public Double evaluate(final ReportParameters reportParameters)
		{
			final Integer value1 = reportParameters.getValue("field3");
			final Integer value2 = reportParameters.getValue("field4");
			return value1.doubleValue() / value2.doubleValue();
		}
	}
	
	class MeasureExpression2 extends AbstractSimpleExpression<Double>
	{

		@Override
		public Double evaluate(final ReportParameters reportParameters)
		{
			final Integer value1 = reportParameters.getValue(ExpressionCrosstabTest.this.measure1);
			final Integer value2 = reportParameters.getValue(ExpressionCrosstabTest.this.variable1);
			return value1.doubleValue() / value2.doubleValue();
		}
	}
}
