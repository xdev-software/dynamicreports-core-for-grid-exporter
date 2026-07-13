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
import static software.xdev.dynamicreports.report.builder.DynamicReports.col;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.tableOfContentsHeading;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.util.JRStyledTextUtil;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.ColumnGroupBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TableOfContents1Test extends AbstractJasperValueTest implements Serializable
{

	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column2;
	private TextColumnBuilder<String> column3;
	private ColumnGroupBuilder group1;
	private ColumnGroupBuilder group2;
	private LabelExpression labelExpression;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		this.labelExpression = new LabelExpression();
		final TableOfContentsHeadingBuilder tocHeading = tableOfContentsHeading().setLabel(this.labelExpression);
		
		rb.tableOfContents()
			.columns(
				this.column1 = col.column("Column1", "field1", type.stringType()),
				this.column2 = col.column("Column2", "field2", type.stringType()),
				this.column3 = col.column("Column3", "field3", type.stringType()))
			.groupBy(
				this.group1 =
					grp.group(this.column1).footer(cmp.text("group footer").setTableOfContentsHeading(tocHeading)),
				this.group2 = grp.group(this.column2));
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(3);
		
		this.elementValueTest("title.textField1", "Table of contents");
		
		this.elementCountTest("detail.textField1", 6);
		int index = 0;
		for(int i = 0; i < 3; i++)
		{
			String anchorName = this.group1.getGroup().getName() + "_" + i * 24;
			
			this.elementValueTest("detail.textField1", index, "value" + (i + 1));
			JRPrintText text = (JRPrintText)this.getElementAt("detail.textField1", index);
			Assertions.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());
			
			JRPrintText dots = (JRPrintText)this.getElementAt("detail.textField2", index);
			String value =
				JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
			Assertions.assertTrue(StringUtils.containsOnly(value, "."));
			Assertions.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());
			
			JRPrintText pageIndex = (JRPrintText)this.getElementAt("detail.textField3", index);
			Assertions.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());
			index++;
			
			anchorName = this.labelExpression.getName() + "_" + (i + 1) * 24;
			
			this.elementValueTest("detail.textField1", index, "group footer" + (i + 1));
			text = (JRPrintText)this.getElementAt("detail.textField1", index);
			Assertions.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());
			
			dots = (JRPrintText)this.getElementAt("detail.textField2", index);
			value = JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
			Assertions.assertTrue(StringUtils.containsOnly(value, "."));
			Assertions.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());
			
			pageIndex = (JRPrintText)this.getElementAt("detail.textField3", index);
			Assertions.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());
			index++;
		}
		this.elementValueTest("detail.textField3", "1", "1", "1", "2", "2", "2");
		
		this.elementCountTest("detail.textField4", 9);
		for(int i = 0; i < 9; i++)
		{
			final String anchorName = this.group2.getGroup().getName() + "_" + i * 8;
			
			final JRPrintText text = (JRPrintText)this.getElementAt("detail.textField4", i);
			Assertions.assertEquals("text anchor", anchorName, text.getHyperlinkAnchor());
			
			final JRPrintText dots = (JRPrintText)this.getElementAt("detail.textField5", i);
			final String value =
				JRStyledTextUtil.getInstance(DefaultJasperReportsContext.getInstance()).getTruncatedText(dots);
			Assertions.assertTrue(StringUtils.containsOnly(value, "."));
			Assertions.assertEquals("dots anchor", anchorName, dots.getHyperlinkAnchor());
			
			final JRPrintText pageIndex = (JRPrintText)this.getElementAt("detail.textField6", i);
			Assertions.assertEquals("pageIndex anchor", anchorName, pageIndex.getHyperlinkAnchor());
		}
		this.elementValueTest(
			"detail.textField4",
			"value1",
			"value2",
			"value3",
			"value1",
			"value2",
			"value3",
			"value1",
			"value2",
			"value3");
		this.elementValueTest("detail.textField6", "1", "1", "1", "1", "1", "1", "2", "2", "2");
		
		String name1 = "groupHeaderTitleAndValue.group_" + this.group1.getGroup().getName() + ".tocReference1";
		String name2 = "groupHeaderTitleAndValue.group_" + this.group1.getGroup().getName() + "1";
		this.elementCountTest(name1, 3);
		for(int i = 0; i < 3; i++)
		{
			final String anchorName = this.group1.getGroup().getName() + "_" + i * 24;
			
			this.elementValueTest(name1, i, "");
			JRPrintText reference = (JRPrintText)this.getElementAt(name1, i);
			Assertions.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());
			
			this.groupHeaderValueTest(this.group1, i, "value" + (i + 1));
			reference = (JRPrintText)this.getElementAt(name2, i);
			Assertions.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());
		}
		
		name1 = this.labelExpression.getName() + ".tocReference";
		name2 = "groupFooter.textField1";
		this.elementCountTest(name1, 3);
		for(int i = 0; i < 3; i++)
		{
			final String anchorName = this.labelExpression.getName() + "_" + (i + 1) * 24;
			
			this.elementValueTest(name1, i, "");
			JRPrintText reference = (JRPrintText)this.getElementAt(name1, i);
			Assertions.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());
			
			this.groupHeaderValueTest(this.group1, i, "value" + (i + 1));
			reference = (JRPrintText)this.getElementAt(name2, i);
			Assertions.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());
		}
		
		name1 = "groupHeaderTitleAndValue.group_" + this.group2.getGroup().getName() + ".tocReference1";
		name2 = "groupHeaderTitleAndValue.group_" + this.group2.getGroup().getName() + "1";
		this.elementCountTest(name1, 9);
		for(int i = 0; i < 9; i++)
		{
			final String anchorName = this.group2.getGroup().getName() + "_" + i * 8;
			
			this.elementValueTest(name1, i, "");
			JRPrintText reference = (JRPrintText)this.getElementAt(name1, i);
			Assertions.assertEquals("reference anchorName " + name1, anchorName, reference.getAnchorName());
			
			reference = (JRPrintText)this.getElementAt(name2, i);
			Assertions.assertEquals("reference anchorName " + name2, anchorName, reference.getAnchorName());
		}
		this.groupHeaderValueTest(
			this.group2,
			"value1",
			"value2",
			"value3",
			"value1",
			"value2",
			"value3",
			"value1",
			"value2",
			"value3");
		
		this.columnTitleCountTest(this.column3, 2);
		this.columnTitleValueTest(this.column3, "Column3", "Column3");
		this.columnDetailCountTest(this.column3, 72);
		this.columnDetailValueTest(this.column3, 71, "text");
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final String[] values = new String[]{"value1", "value2", "value3"};
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		for(final String field1 : values)
		{
			for(final String field2 : values)
			{
				for(int i = 0; i < 8; i++)
				{
					dataSource.add(field1, field2, "text");
				}
			}
		}
		return dataSource;
	}
	
	static class LabelExpression extends AbstractSimpleExpression<String>
	{

		private int index = 1;
		
		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return "group footer" + this.index++;
		}
	}
}
