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

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.component.SubreportBuilder;
import software.xdev.dynamicreports.report.builder.expression.AbstractComplexExpression;
import software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class DatabaseSubreportTest extends AbstractJasperValueTest
{
	private Connection connection;
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<Integer> column2;
	private TextColumnBuilder<BigDecimal> column3;
	
	@Override
	public void init()
	{
		try
		{
			Class.forName("org.hsqldb.jdbcDriver");
			this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:test");
			this.createTable();
		}
		catch(final Exception e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
		super.init();
	}
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final SubreportBuilder subreport = cmp.subreport(new SubreportExpression());
		subreport.setConnection(this.connection);
		
		final CustomGroupBuilder group = grp.group(field("field4", Integer.class));
		group.footer(subreport);
		
		rb.groupBy(group).setDataSource("SELECT * FROM test_table2", this.connection);
	}
	
	@Override
	protected boolean serializableTest()
	{
		return false;
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		// column1
		this.columnTitleCountTest(this.column1, 2);
		this.columnTitleValueTest(this.column1, "Column1", "Column1");
		this.columnDetailCountTest(this.column1, 3);
		this.columnDetailValueTest(this.column1, "text", "text", "text");
		// column2
		this.columnTitleCountTest(this.column2, 2);
		this.columnTitleValueTest(this.column2, "Column2", "Column2");
		this.columnDetailCountTest(this.column2, 3);
		this.columnDetailValueTest(this.column2, "5", "6", "7");
		// column3
		this.columnTitleCountTest(this.column3, 2);
		this.columnTitleValueTest(this.column3, "Column3", "Column3");
		this.columnDetailCountTest(this.column3, 3);
		this.columnDetailValueTest(this.column3, "100.00", "200.00", "300.00");
	}
	
	private void createTable() throws SQLException
	{
		final Statement st = this.connection.createStatement();
		st.execute("CREATE TABLE test_table2 (field1 VARCHAR(50), field2 INTEGER, field3 DECIMAL, field4 INTEGER)");
		st.execute("INSERT INTO test_table2 VALUES ('text', 5, 100, 1)");
		st.execute("INSERT INTO test_table2 VALUES ('text', 6, 200, 1)");
		st.execute("INSERT INTO test_table2 VALUES ('text', 7, 300, 2)");
	}
	
	private class SubreportExpression extends AbstractComplexExpression<JasperReportBuilder>
	{

		public SubreportExpression()
		{
			this.addExpression(field("field4", Integer.class));
		}
		
		@Override
		public JasperReportBuilder evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			final JasperReportBuilder report = report();
			report.setLocale(Locale.ENGLISH)
				.columns(
					DatabaseSubreportTest.this.column1 = col.column("Column1", "field1", type.stringType()),
					DatabaseSubreportTest.this.column2 = col.column("Column2", "field2", type.integerType()),
					DatabaseSubreportTest.this.column3 = col.column("Column3", "field3", type.bigDecimalType()))
				.setQuery("SELECT * FROM test_table2 WHERE field4 = " + values.get(0));
			return report;
		}
	}
}
