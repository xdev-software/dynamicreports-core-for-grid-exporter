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

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;


/**
 * @author Ricardo Mariaca
 */
class TitleTest extends AbstractJasperValueTest
{
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setResourceBundle("net.sf.dynamicreports.test.jasper.report.test")
			.columns(col.column("Column1", "field1", Integer.class))
			.title(cmp.text("title 1").setHyperLink(hyperLink("link").setTooltip("tooltip")),
				cmp.text("title 2"),
				cmp.text("title 3"),
				cmp.text(exp.message("title")),
				cmp.multiPageList(cmp.text(exp.message("title"))));
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(2);
        this.elementCountTest("title.textField1", 2);
        this.elementValueTest("title.textField1", "title 1", "test title");
		
		final JRPrintText textField = (JRPrintText)this.getElementAt("title.textField1", 0);
		Assertions.assertEquals("link", textField.getHyperlinkReference());
		Assertions.assertEquals("tooltip", textField.getHyperlinkTooltip());
		Assertions.assertEquals(HyperlinkTypeEnum.REFERENCE, textField.getHyperlinkTypeValue());
        
        this.elementCountTest("title.textField2", 1);
        this.elementValueTest("title.textField2", "title 2");
        
        this.elementCountTest("title.textField3", 1);
        this.elementValueTest("title.textField3", "title 3");
        
        this.elementCountTest("title.textField4", 1);
        this.elementValueTest("title.textField4", "test title");
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
}
