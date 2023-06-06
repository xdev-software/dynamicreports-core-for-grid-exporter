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
package net.sf.dynamicreports.test.jasper.report;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.field;
import static net.sf.dynamicreports.report.builder.DynamicReports.parameter;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;


/**
 * @author Ricardo Mariaca
 */
public class ValueTypeNamesTest extends AbstractJasperValueTest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(col.column("Column1", new ColumnExpression()))
			.fields(field("field1", type.stringType()))
			.parameters(parameter("field1", "parameterValue"));
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(1);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		dataSource.add("fieldValue");
		return dataSource;
	}
	
	private class ColumnExpression extends AbstractSimpleExpression<String>
	{
		private static final long serialVersionUID = 1L;
		
		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			Assertions.assertEquals("fieldValue", reportParameters.getValue("field1"));
			Assertions.assertEquals("fieldValue", reportParameters.getFieldValue("field1"));
			Assertions.assertEquals("parameterValue", reportParameters.getParameterValue("field1"));
			return "";
		}
	}
}
