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
package software.xdev.dynamicreports.test.jasper.datasource;

import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.ctab;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.DatasetBuilder;
import software.xdev.dynamicreports.report.builder.DynamicReports;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import software.xdev.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;


public class FilterTest extends AbstractJasperCrosstabValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure1;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.measure1 = ctab.measure("field3", Integer.class, Calculation.SUM);
		
		final DatasetBuilder dataset = DynamicReports.dataset();
		dataset.setDataSource(this.createCrosstabDataSource());
		dataset.setFilterExpression(new CrosstabFilterExpression());
		
		final CrosstabBuilder crosstab =
			ctab.crosstab()
				.setSubDataset(dataset)
				.rowGroups(this.rowGroup = ctab.rowGroup("field1", String.class))
				.columnGroups(this.columnGroup = ctab.columnGroup("field2", String.class))
				.measures(this.measure1);
		
		rb.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.stringType()))
			.title(crosstab)
			.setFilterExpression(new FilterExpression());
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnTitleCountTest(this.column1, 1);
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailCountTest(this.column1, 1);
		this.columnDetailValueTest(this.column1, "1");
		// column2
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailCountTest(this.column2, 1);
		this.columnDetailValueTest(this.column2, "text1");
		
		this.setCrosstabBand("title");
		
		// column group
		this.crosstabGroupHeaderCountTest(this.columnGroup, 2);
		this.crosstabGroupHeaderValueTest(this.columnGroup, "c", "d");
		this.crosstabGroupTotalHeaderCountTest(this.columnGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.columnGroup, "Total");
		
		// row group
		this.crosstabGroupHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupHeaderValueTest(this.rowGroup, "a");
		this.crosstabGroupTotalHeaderCountTest(this.rowGroup, 1);
		this.crosstabGroupTotalHeaderValueTest(this.rowGroup, "Total");
		
		// measure1
		this.crosstabCellCountTest(this.measure1, null, null, 2);
		this.crosstabCellValueTest(this.measure1, null, null, "3", "7");
		this.crosstabCellCountTest(this.measure1, null, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, null, this.columnGroup, "10");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, null, 2);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, null, "3", "7");
		this.crosstabCellCountTest(this.measure1, this.rowGroup, this.columnGroup, 1);
		this.crosstabCellValueTest(this.measure1, this.rowGroup, this.columnGroup, "10");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add("1", "text1");
		dataSource.add("2", "text2");
		dataSource.add("3", "text3");
		dataSource.add("4", "text4");
		return dataSource;
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
	
	static class FilterExpression extends AbstractSimpleExpression<Boolean>
	{

		@Override
		public Boolean evaluate(final ReportParameters reportParameters)
		{
			final String value = reportParameters.getValue("field1");
			return value.equals("1");
		}
	}
	
	
	static class CrosstabFilterExpression extends AbstractSimpleExpression<Boolean>
	{

		@Override
		public Boolean evaluate(final ReportParameters reportParameters)
		{
			final String value = reportParameters.getValue("field1");
			return value.equals("a");
		}
	}
}
