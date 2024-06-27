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
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.grp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;
import static software.xdev.dynamicreports.report.builder.DynamicReports.tableOfContentsHeading;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.column.TextColumnBuilder;
import software.xdev.dynamicreports.report.builder.group.CustomGroupBuilder;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizer;
import software.xdev.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder;
import software.xdev.dynamicreports.report.datasource.DRDataSource;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.test.jasper.AbstractJasperValueTest;


public class TableOfContents6Test extends AbstractJasperValueTest
{
	private TextColumnBuilder<String> column1;
	private TextColumnBuilder<String> column4;
	private CustomGroupBuilder group1;
	private CustomGroupBuilder group2;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
		rb.setTableOfContents(new CustomTableOfContentsCustomizer())
			.title(cmp.subreport(this.createSubreport1()))
			.columns(this.column4 = col.column("Column4", "field4", type.stringType()));
	}
	
	private JasperReportBuilder createSubreport1()
	{
		this.column1 = col.column("Column1", "field1", type.stringType());
		
		final DRTextField<String> columnComponent = (DRTextField<String>)this.column1.getColumn().getComponent();
		final TableOfContentsHeadingBuilder tocHeading = tableOfContentsHeading();
		tocHeading.setCustomValue(new TocCustomValueExpression());
		columnComponent.setTableOfContentsHeading(tocHeading.getTableOfContentsHeading());
		
		final JasperReportBuilder report = report();
		report.columns(
			this.column1,
			col.column("Column2", "field2", type.stringType()),
			col.column("Column3", "field3", type.stringType())).setDataSource(this.createSubreportDataSource1());
		
		return report;
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field4");
		dataSource.add("detail1");
		dataSource.add("detail2");
		return dataSource;
	}
	
	private JRDataSource createSubreportDataSource1()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("text1", "text1_1", "text2_1");
		dataSource.add("text2", "text1_1", "text2_1");
		dataSource.add("text3", "text1_2", "text2_2");
		dataSource.add("text4", "text1_2", "text2_2");
		return dataSource;
	}
	
	@Override
	public void test()
	{
		super.test();
		
		this.numberOfPagesTest(2);
		
		this.elementCountTest("detail.textField1", 8);
		this.elementValueTest(
			"detail.textField1",
			"text1", "text2", "text3", "text4", "text1", "text2", "text3", "text4");
		
		this.columnDetailCountTest(this.column1, 4);
		this.columnDetailValueTest(this.column1, "text1", "text2", "text3", "text4");
		
		this.columnDetailCountTest(this.column4, 2);
		this.columnDetailValueTest(this.column4, "detail1", "detail2");
		
		this.groupHeaderCountTest(this.group1, 2);
		this.groupHeaderValueTest(this.group1, "text1_1", "text1_2");
		
		this.groupHeaderCountTest(this.group2, 2);
		this.groupHeaderValueTest(this.group2, "text2_1", "text2_2");
	}
	
	@Override
	protected boolean serializableTest()
	{
		return false;
	}
	
	class CustomTableOfContentsCustomizer extends TableOfContentsCustomizer
	{

		@Override
		public void customize()
		{
			this.init();
			
			TableOfContents6Test.this.group1 = grp.group(field("customValue.field2", String.class));
			TableOfContents6Test.this.group2 = grp.group(field("customValue.field3", String.class));
			
			this.report.title(
				cmp.subreport(this.createSubreport2(TableOfContents6Test.this.group1)),
				cmp.subreport(this.createSubreport2(TableOfContents6Test.this.group2)));
		}
		
		private JasperReportBuilder createSubreport2(final CustomGroupBuilder group)
		{
			final JasperReportBuilder subreport = report();
			subreport.fields(this.levelField, this.textField, this.referenceField, this.pageIndexField)
				.groupBy(group)
				.detail(this.detailComponent())
				.setDataSource(new JRBeanCollectionDataSource(this.headingList));
			
			return subreport;
		}
	}
	
	
	class TocCustomValueExpression extends AbstractSimpleExpression<TocCustomValue>
	{

		@Override
		public TocCustomValue evaluate(final ReportParameters reportParameters)
		{
			final String field2 = reportParameters.getValue("field2");
			final String field3 = reportParameters.getValue("field3");
			return new TocCustomValue(field2, field3);
		}
	}
	
	
	public class TocCustomValue
	{
		private String field2;
		private String field3;
		
		public TocCustomValue(final String field2, final String field3)
		{
			this.field2 = field2;
			this.field3 = field3;
		}
		
		public String getField2()
		{
			return this.field2;
		}
		
		public void setField2(final String field2)
		{
			this.field2 = field2;
		}
		
		public String getField3()
		{
			return this.field3;
		}
		
		public void setField3(final String field3)
		{
			this.field3 = field3;
		}
	}
}
