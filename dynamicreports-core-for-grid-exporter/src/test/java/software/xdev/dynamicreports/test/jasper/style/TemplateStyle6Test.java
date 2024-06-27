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
package software.xdev.dynamicreports.test.jasper.style;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.template;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.ReportTemplateBuilder;
import software.xdev.dynamicreports.report.builder.style.StyleBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.test.jasper.AbstractJasperStyleTest;


public class TemplateStyle6Test extends AbstractJasperStyleTest implements Serializable
{

	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final StyleBuilder textStyle = stl.style().setName("textStyle").setPadding(2);
		final StyleBuilder boldStyle = stl.style(textStyle).setName("boldStyle").bold();
		
		final ReportTemplateBuilder template = template().templateStyles(textStyle, boldStyle);
		
		rb.setTemplate(template)
			.title(cmp.multiPageList(cmp.text("title").setStyle(stl.templateStyle("boldStyle"))));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(1);
		
		this.styleTest("title.textField1", 0, null, null, TEST_FONT_NAME, 10f, true, null);
		this.paddingTest("title.textField1", 0, 2, 2, 2, 2);
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2");
		dataSource.add(1, "1");
		return dataSource;
	}
}
