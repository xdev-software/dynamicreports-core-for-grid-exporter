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
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class DatabaseReportTest extends AbstractJasperValueTest
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
		rb.setLocale(Locale.ENGLISH)
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.integerType()),
				this.column3 = col.column("Column3", "field3", type.bigDecimalType()))
			.setDataSource("SELECT * FROM test_table1", this.connection);
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
		this.columnTitleCountTest(this.column1, 1);
		this.columnTitleValueTest(this.column1, "Column1");
		this.columnDetailCountTest(this.column1, 1);
		this.columnDetailValueTest(this.column1, 0, "text");
		// column2
		this.columnTitleCountTest(this.column2, 1);
		this.columnTitleValueTest(this.column2, "Column2");
		this.columnDetailCountTest(this.column2, 1);
		this.columnDetailValueTest(this.column2, 0, "5");
		// column3
		this.columnTitleCountTest(this.column3, 1);
		this.columnTitleValueTest(this.column3, "Column3");
		this.columnDetailCountTest(this.column3, 1);
		this.columnDetailValueTest(this.column3, 0, "100.00");
	}
	
	private void createTable() throws SQLException
	{
		final Statement st = this.connection.createStatement();
		st.execute("CREATE TABLE test_table1 (field1 VARCHAR(50), field2 INTEGER, field3 DECIMAL)");
		st.execute("INSERT INTO test_table1 VALUES ('text', 5, 100)");
	}
}
