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
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabVariableBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class Crosstab5Test extends AbstractJasperCrosstabValueTest implements Serializable
{

	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabVariableBuilder<Integer> variable1;
	private CrosstabMeasureBuilder<Integer> measure1;
	private CrosstabMeasureBuilder<?> measure2;
	private CrosstabMeasureBuilder<BigDecimal> measure3;
	private CrosstabMeasureBuilder<BigDecimal> measure4;
	private CrosstabMeasureBuilder<BigDecimal> measure5;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.rowGroup = ctab.rowGroup("field1", String.class);
		this.columnGroup = ctab.columnGroup("field2", String.class);
		
		final FieldBuilder<Integer> field = field("field3", Integer.class);
		this.variable1 = ctab.variable(field, Calculation.SUM);
		this.measure1 = ctab.measure(field, Calculation.SUM);
		this.measure2 = ctab.measure(field, Calculation.SUM);
		this.measure2.setPercentageType(CrosstabPercentageType.GRAND_TOTAL);
		this.measure3 = ctab.measure(new PercentageExpression1());
		this.measure3.setDataType(type.doubleType());
		final String expression =
			"$V{" + this.measure1.getName() + "}.doubleValue()/$V{" + this.measure1.getName() + "_"
				+ this.rowGroup.getName() + "_" + this.columnGroup.getName() + "_ALL}.doubleValue() * 100";
		this.measure4 = ctab.measure(exp.jasperSyntax(expression, Double.class));
		this.measure4.setDataType(type.doubleType());
		this.measure5 = ctab.measure(new PercentageExpression2());
		this.measure5.setDataType(type.doubleType());
		
		final CrosstabBuilder crosstab =
			ctab.crosstab().rowGroups(this.rowGroup).columnGroups(this.columnGroup).variables(
				this.variable1).measures(this.measure1, this.measure2, this.measure3, this.measure4, this.measure5);
		
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
		this.crosstabCellValueTest(this.measure1, null, null, "3", "7", "11", "15");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "10", "26");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "14", "22");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "36");
		
		// measure2
		this.crosstabCellCountTest(this.measure2, null, null, 4);
		this.crosstabCellValueTest(this.measure2, null, null, "8.3", "19.4", "30.6", "41.7");
		this.crosstabCellCountTest(this.measure2, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure2, null, this.columnGroup, "27.8", "72.2");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, null, "38.9", "61.1");
		this.crosstabCellCountTest(this.measure2, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure2, this.rowGroup, this.columnGroup, "100");
		
		// measure3
		this.crosstabCellCountTest(this.measure3, null, null, 4);
		this.crosstabCellValueTest(this.measure3, null, null, "8.3", "19.4", "30.6", "41.7");
		this.crosstabCellCountTest(this.measure3, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure3, null, this.columnGroup, "27.8", "72.2");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, null, "38.9", "61.1");
		this.crosstabCellCountTest(this.measure3, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure3, this.rowGroup, this.columnGroup, "100");
		
		// measure4
		this.crosstabCellCountTest(this.measure4, null, null, 4);
		this.crosstabCellValueTest(this.measure4, null, null, "8.3", "19.4", "30.6", "41.7");
		this.crosstabCellCountTest(this.measure4, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure4, null, this.columnGroup, "27.8", "72.2");
		this.crosstabCellCountTest(this.measure4, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure4, this.rowGroup, null, "38.9", "61.1");
		this.crosstabCellCountTest(this.measure4, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure4, this.rowGroup, this.columnGroup, "100");
		
		// measure5
		this.crosstabCellCountTest(this.measure5, null, null, 4);
		this.crosstabCellValueTest(this.measure5, null, null, "21.4", "31.8", "78.6", "68.2");
		this.crosstabCellCountTest(this.measure5, null, this.columnGroup, 2);
		this.crosstabCellValueTest(this.measure5, null, this.columnGroup, "27.8", "72.2");
		this.crosstabCellCountTest(this.measure5, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure5, this.rowGroup, null, "100", "100");
		this.crosstabCellCountTest(this.measure5, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure5, this.rowGroup, this.columnGroup, "100");
	}
	
	@Override
	protected JRDataSource createDataSource()
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
	
	class PercentageExpression1 extends AbstractComplexExpression<BigDecimal>
	{

		PercentageExpression1()
		{
			this.addExpression(exp.crosstabValue(Crosstab5Test.this.measure1));
			this.addExpression(exp.crosstabValue(Crosstab5Test.this.measure1, Crosstab5Test.this.rowGroup,
				Crosstab5Test.this.columnGroup));
		}
		
		@Override
		public BigDecimal evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final Integer value1 = (Integer)values.get(0);
			final Integer value2 = (Integer)values.get(1);
			return new BigDecimal(value1).divide(new BigDecimal(value2), 3, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100));
		}
	}
	
	
	class PercentageExpression2 extends AbstractComplexExpression<BigDecimal>
	{

		PercentageExpression2()
		{
			this.addExpression(exp.crosstabValue(Crosstab5Test.this.variable1));
			this.addExpression(exp.crosstabValue(Crosstab5Test.this.measure1, Crosstab5Test.this.rowGroup));
			this.addExpression(exp.jasperSyntax(
				"$V{" + Crosstab5Test.this.measure1.getName() + "_" + Crosstab5Test.this.rowGroup.getName() + "_ALL}",
				Integer.class));
		}
		
		@Override
		public BigDecimal evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final Integer value1 = (Integer)values.get(0);
			final Integer value2 = (Integer)values.get(1);
			final Integer measure1Value = reportParameters.getValue(Crosstab5Test.this.measure1);
			Assertions.assertEquals(value1, measure1Value);
			Assertions.assertEquals(value2, values.get(2));
			return new BigDecimal(value1).divide(new BigDecimal(value2), 3, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(100));
		}
	}
}
