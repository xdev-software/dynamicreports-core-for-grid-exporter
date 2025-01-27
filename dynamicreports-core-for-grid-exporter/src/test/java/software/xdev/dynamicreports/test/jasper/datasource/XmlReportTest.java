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
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.QueryLanguage;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class XmlReportTest extends AbstractJasperValueTest
{
	private TextColumnBuilder<Object> column1;
	private TextColumnBuilder<Object> column2;
	private TextColumnBuilder<Object> column3;
	private TextColumnBuilder<Object> column4;
	private TextColumnBuilder<Object> column5;
	private TextColumnBuilder<Object> column6;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		try
		{
			rb.setLocale(Locale.ENGLISH)
				.columns(
					this.column1 = col.column("Column1", field("field1", type.stringType()).setDescription("@field1")),
					this.column2 = col.column("Column2", field("field2", type.integerType())),
					this.column3 = col.column("Column3", field("field3", type.bigDecimalType())))
				.setQuery("/data/row1", QueryLanguage.XPATH)
				.setParameter(
					JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT,
					JRXmlUtils.parse(XmlReportTest.class.getResourceAsStream("data.xml")))
				.summary(cmp.subreport(this.createSubreport()));
		}
		catch(final JRException e)
		{
			e.printStackTrace();
			Assertions.fail(e.getMessage());
		}
	}
	
	private JasperReportBuilder createSubreport() throws JRException
	{
		final JasperReportBuilder report = report();
		report.setLocale(Locale.ENGLISH)
			.columns(
				this.column4 = col.column("Column4", field("field4", type.stringType()).setDescription("@field4")),
				this.column5 = col.column("Column5", field("field5", type.integerType()).setDescription("field5")),
				this.column6 = col.column("Column6", field("field6", type.bigDecimalType())))
			.setUseFieldNameAsDescription(false)
			.setDataSource(new JRXmlDataSource(XmlReportTest.class.getResourceAsStream("data.xml"), "/data/row2"));
		return report;
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
		this.columnDetailValueTest(this.column1, 0, "text1");
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
		
		// column4
		this.columnTitleCountTest(this.column4, 1);
		this.columnTitleValueTest(this.column4, "Column4");
		this.columnDetailCountTest(this.column4, 1);
		this.columnDetailValueTest(this.column4, 0, "text2");
		// column5
		this.columnTitleCountTest(this.column5, 1);
		this.columnTitleValueTest(this.column5, "Column5");
		this.columnDetailCountTest(this.column5, 1);
		this.columnDetailValueTest(this.column5, 0, "1");
		// column6
		this.columnTitleCountTest(this.column6, 1);
		this.columnTitleValueTest(this.column6, "Column6");
		this.columnDetailCountTest(this.column6, 1);
		this.columnDetailValueTest(this.column6, 0, "10.00");
	}
}
