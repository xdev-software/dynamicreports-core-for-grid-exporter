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
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;

import java.io.Serializable;
import java.util.Locale;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Group1Test extends AbstractJasperValueTest implements Serializable
{

	private ColumnGroupBuilder group1;
	private ColumnGroupBuilder group2;
	private ColumnGroupBuilder group3;
	private CustomGroupBuilder group4;
	private CustomGroupBuilder group5;
	private ColumnGroupBuilder group6;
	private FieldBuilder<String> field3;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<String> column3;
	private TextColumnBuilder<Integer> column4;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextColumnBuilder<String> column1;
		
		rb.setLocale(Locale.ENGLISH)
			.columns(
				column1 = col.column("Column1", "field1", String.class),
				this.column2 = col.column("Column2", "field2", Integer.class),
				this.column3 = col.column("Column3", "field4", String.class),
				this.column4 = col.column("Column4", "field5", Integer.class).setPattern("#,###.00"))
			.groupBy(
				this.group1 = grp.group(column1),
				this.group2 = grp.group(this.column3).setHeaderLayout(GroupHeaderLayout.EMPTY).setHideColumn(false),
				this.group3 = grp.group(column1).setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE),
				this.group4 = grp.group(this.field3 = field("field3", String.class)),
				this.group5 = grp.group(new ValueExpression()).setTitle("Expression"),
				this.group6 = grp.group(this.column4));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		// group1
		this.groupHeaderTitleCountTest(this.group1, 0);
		this.groupHeaderCountTest(this.group1, 2);
		this.groupHeaderValueTest(this.group1, "group1", "group2");
		// group2
		this.groupHeaderTitleCountTest(this.group1, 0);
		this.groupHeaderCountTest(this.group2, 0);
		// group3
		this.groupHeaderTitleCountTest(this.group3, 2);
		this.groupHeaderTitleValueTest(this.group3, "Column1", "Column1");
		this.groupHeaderCountTest(this.group3, 2);
		this.groupHeaderValueTest(this.group3, "group1", "group2");
		// group4
		this.groupHeaderCountTest(this.group4, 2);
		this.groupHeaderValueTest(this.group4, "group1_1", "group2_1");
		// group5
		this.groupHeaderTitleCountTest(this.group5, 2);
		this.groupHeaderTitleValueTest(this.group5, "Expression", "Expression");
		this.groupHeaderCountTest(this.group5, 2);
		this.groupHeaderValueTest(this.group5, "group1_1_1", "group2_1_1");
		// group6
		this.groupHeaderTitleCountTest(this.group6, 0);
		this.groupHeaderCountTest(this.group6, 2);
		this.groupHeaderValueTest(this.group6, "1.00", "2.00");
		// column2
		this.columnDetailCountTest(this.column2, 20);
		this.columnDetailValueTest(this.column2, "0", "1");
		// column3
		this.columnDetailCountTest(this.column3, 20);
		this.columnDetailValueTest(this.column3, "group1", "group1");
		this.columnDetailValueTest(this.column3, 10, "group2");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3", "field4", "field5");
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group1", i, "group1_1", "group1", 1);
		}
		for(int i = 0; i < 10; i++)
		{
			dataSource.add("group2", i, "group2_1", "group2", 2);
		}
		return dataSource;
	}
	
	class ValueExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getValue(Group1Test.this.field3) + "_1";
		}
	}
}
