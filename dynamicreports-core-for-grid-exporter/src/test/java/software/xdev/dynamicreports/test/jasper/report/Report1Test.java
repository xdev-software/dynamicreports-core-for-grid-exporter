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
package software.xdev.dynamicreports.test.jasper.report;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.exp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.parameter;

import java.math.BigDecimal;
import java.util.ListResourceBundle;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.fonts.FontUtil;
import net.sf.jasperreports.engine.type.OrientationEnum;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.AbstractScriptlet;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.constant.PageOrientation;
import software.xdev.dynamicreports.report.constant.PageType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class Report1Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<Integer> column1;
	private ReportScriptlet scriptlet;
	private BigDecimal parameter1;
	private BigDecimal parameter2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.columns(this.column1 = col.column("Column1", "field1", Integer.class))
			.title(
				cmp.text(exp.jasperSyntax("$R{bundleKey3}", String.class)),
				cmp.multiPageList(
					cmp.text(exp.jasperSyntax("$R{bundleKey3}", String.class)),
					cmp.text(exp.jasperSyntax("$R{bundleKey1}", String.class))))
			.setLocale(Locale.ENGLISH)
			.setResourceBundle(new ResourceBundle())
			.setWhenResourceMissingType(WhenResourceMissingType.KEY)
			.setShowColumnTitle(false)
			.setShowColumnValues(false)
			.setPageFormat(PageType.A3, PageOrientation.LANDSCAPE)
			.scriptlets(this.scriptlet = new ReportScriptlet())
			.parameters(parameter("parameter1", this.parameter1 = new BigDecimal(10)))
			.addParameter("parameter2", this.parameter2 = new BigDecimal(20));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		this.columnTitleCountTest(this.column1, 0);
		this.columnDetailCountTest(this.column1, 0);
		
		this.elementCountTest("title.textField1", 3);
		this.elementValueTest("title.textField1", "bundleKey3", "bundleKey3", "bundleValue");
		
		final FontUtil fontUtil = FontUtil.getInstance(DefaultJasperReportsContext.getInstance());
		Assertions.assertFalse(fontUtil.getFontFamilyNames().isEmpty());
		
		final JasperPrint jasperPrint = this.getJasperPrint();
		Assertions.assertEquals("Report", jasperPrint.getName());
		Assertions.assertEquals(OrientationEnum.LANDSCAPE, jasperPrint.getOrientationValue());
		Assertions.assertEquals(1190, jasperPrint.getPageWidth());
		Assertions.assertEquals(842, jasperPrint.getPageHeight());
		
		Assertions.assertEquals(50, this.scriptlet.count);
	}
	
	@Override
	protected boolean serializableTest()
	{
		return false;
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1");
		for(int i = 0; i < 50; i++)
		{
			dataSource.add(i);
		}
		return dataSource;
	}
	
	static class ResourceBundle extends ListResourceBundle
	{
		@Override
		protected Object[][] getContents()
		{
			return new Object[][]{{"bundleKey1", "bundleValue"}, {"bundleKey2", "bundleValue {0} - {1}"}};
		}
	}
	
	class ReportScriptlet extends AbstractScriptlet
	{
		private int count;
		
		@Override
		public void afterReportInit(final ReportParameters reportParameters)
		{
			super.afterReportInit(reportParameters);
			Assertions.assertEquals(Locale.ENGLISH, reportParameters.getLocale());
			Assertions.assertEquals("bundleValue", reportParameters.getMessage("bundleKey1"));
			Assertions.assertEquals(
				"bundleValue a - b",
				reportParameters.getMessage("bundleKey2", new Object[]{"a", "b"}));
			Assertions.assertEquals(Report1Test.this.parameter1, reportParameters.getValue("parameter1"));
			Assertions.assertEquals(Report1Test.this.parameter2, reportParameters.getValue("parameter2"));
			Assertions.assertEquals(this, reportParameters.getScriptlet(this.getName()));
		}
		
		@Override
		public void afterDetailEval(final ReportParameters reportParameters)
		{
			super.afterDetailEval(reportParameters);
			this.count++;
		}
	}
}
