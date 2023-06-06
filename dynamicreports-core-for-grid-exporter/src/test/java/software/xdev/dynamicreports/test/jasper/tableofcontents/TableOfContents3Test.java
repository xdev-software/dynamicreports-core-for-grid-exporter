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
package software.xdev.dynamicreports.test.jasper.tableofcontents;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.tableOfContentsHeading;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.builder.component.TextFieldBuilder;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;


/**
 * @author Ricardo Mariaca
 */
class TableOfContents3Test extends AbstractJasperValueTest
{
	private DRIExpression<String> labelExpression1;
	private DRIExpression<String> labelExpression2;
	private DRIExpression<String> labelExpression3;
	private DRIExpression<String> labelExpression4;
	private DRIExpression<String> labelExpression5;
	private DRIExpression<String> labelExpression6;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		final TextFieldBuilder<String> textField1 = cmp.text("text1");
		this.labelExpression1 = textField1.getComponent().getValueExpression();
		final TextFieldBuilder<String> textField6 = cmp.text("text6");
		this.labelExpression6 = textField6.getComponent().getValueExpression();
		
		rb.tableOfContents()
			.title(
				textField1.setTableOfContentsHeading(tableOfContentsHeading()),
				cmp.subreport(this.createSubreport1()))
			.summary(
				cmp.subreport(this.createSubreport3()),
				textField6.setTableOfContentsHeading(tableOfContentsHeading()));
	}
	
	private JasperReportBuilder createSubreport1()
	{
		final TextFieldBuilder<String> textField2 = cmp.text("text2");
		this.labelExpression2 = textField2.getComponent().getValueExpression();
		final TextFieldBuilder<String> textField4 = cmp.text("text4");
		this.labelExpression4 = textField4.getComponent().getValueExpression();
		
		final JasperReportBuilder report = report();
		report.title(textField2.setTableOfContentsHeading(tableOfContentsHeading()));
		report.title(cmp.subreport(this.createSubreport2()));
		report.title(textField4.setTableOfContentsHeading(tableOfContentsHeading()));
		report.detailHeader(cmp.verticalGap(10));
		report.setDataSource(new JREmptyDataSource(50));
		return report;
	}
	
	private JasperReportBuilder createSubreport2()
	{
		final TextFieldBuilder<String> textField3 = cmp.text("text3");
		this.labelExpression3 = textField3.getComponent().getValueExpression();
		
		final JasperReportBuilder report = report();
		report.title(textField3.setTableOfContentsHeading(tableOfContentsHeading()));
		report.detailHeader(cmp.verticalGap(10));
		report.setDataSource(new JREmptyDataSource(80));
		return report;
	}
	
	private JasperReportBuilder createSubreport3()
	{
		final TextFieldBuilder<String> textField5 = cmp.text("text5");
		this.labelExpression5 = textField5.getComponent().getValueExpression();
		
		final JasperReportBuilder report = report();
		report.title(textField5.setTableOfContentsHeading(tableOfContentsHeading()));
		report.detailHeader(cmp.verticalGap(10));
		report.setDataSource(new JREmptyDataSource(50));
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
		
		this.numberOfPagesTest(4);
		
		this.elementValueTest("title.textField1", "Table of contents");
		
		this.elementCountTest("detail.textField1", 6);
		
		String anchorName = this.labelExpression1.getName() + "_0";
		this.elementValueTest("detail.textField1", 0, "text1");
		JRPrintText text = (JRPrintText)this.getElementAt("detail.textField1", 0);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		JRPrintText dots = (JRPrintText)this.getElementAt("detail.textField2", 0);
		String value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		JRPrintText pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 0);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		anchorName = this.labelExpression2.getName() + "_0";
		this.elementValueTest("detail.textField1", 1, "text2");
		text = (JRPrintText)this.getElementAt("detail.textField1", 1);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText)this.getElementAt("detail.textField2", 1);
		value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 1);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		anchorName = this.labelExpression3.getName() + "_0";
		this.elementValueTest("detail.textField1", 2, "text3");
		text = (JRPrintText)this.getElementAt("detail.textField1", 2);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText)this.getElementAt("detail.textField2", 2);
		value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 2);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		anchorName = this.labelExpression4.getName() + "_0";
		this.elementValueTest("detail.textField1", 3, "text4");
		text = (JRPrintText)this.getElementAt("detail.textField1", 3);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText)this.getElementAt("detail.textField2", 3);
		value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 3);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		anchorName = this.labelExpression5.getName() + "_0";
		this.elementValueTest("detail.textField1", 4, "text5");
		text = (JRPrintText)this.getElementAt("detail.textField1", 4);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText)this.getElementAt("detail.textField2", 4);
		value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 4);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		anchorName = this.labelExpression6.getName() + "_0";
		this.elementValueTest("detail.textField1", 5, "text6");
		text = (JRPrintText)this.getElementAt("detail.textField1", 5);
		Assertions.assertEquals(anchorName, text.getHyperlinkAnchor());
		dots = (JRPrintText)this.getElementAt("detail.textField2", 5);
		value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
		Assertions.assertTrue(StringUtils.containsOnly(value, "."));
		Assertions.assertEquals(anchorName, dots.getHyperlinkAnchor());
		pageIndex = (JRPrintText)this.getElementAt("detail.textField3", 5);
		Assertions.assertEquals(anchorName, pageIndex.getHyperlinkAnchor());
		
		this.elementValueTest("detail.textField3", "1", "1", "1", "2", "2", "3");
		
		String name1 = this.labelExpression1.getName() + ".tocReference";
		String name2 = "title.textField1";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression1.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		JRPrintText reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 1, "text1");
		reference = (JRPrintText)this.getElementAt(name2, 1);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		
		name1 = this.labelExpression2.getName() + ".tocReference";
		name2 = "title.textField1";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression2.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 2, "text2");
		reference = (JRPrintText)this.getElementAt(name2, 2);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		
		name1 = this.labelExpression3.getName() + ".tocReference";
		name2 = "title.textField1";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression3.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 3, "text3");
		reference = (JRPrintText)this.getElementAt(name2, 3);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		
		name1 = this.labelExpression4.getName() + ".tocReference";
		name2 = "title.textField2";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression4.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 0, "text4");
		reference = (JRPrintText)this.getElementAt(name2, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		
		name1 = this.labelExpression5.getName() + ".tocReference";
		name2 = "title.textField1";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression5.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 4, "text5");
		reference = (JRPrintText)this.getElementAt(name2, 4);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		
		name1 = this.labelExpression6.getName() + ".tocReference";
		name2 = "summary.textField1";
		this.elementCountTest(name1, 1);
		anchorName = this.labelExpression6.getName() + "_0";
		this.elementValueTest(name1, 0, "");
		reference = (JRPrintText)this.getElementAt(name1, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
		this.elementValueTest(name2, 0, "text6");
		reference = (JRPrintText)this.getElementAt(name2, 0);
		Assertions.assertEquals(anchorName, reference.getAnchorName());
	}
}
