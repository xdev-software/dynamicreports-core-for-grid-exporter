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
package net.sf.dynamicreports.test.jasper.crosstab;

import static net.sf.dynamicreports.report.builder.DynamicReports.ctab;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.crosstab.AbstractCrosstabGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabColumnGroupBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabMeasureBuilder;
import net.sf.dynamicreports.report.builder.crosstab.CrosstabRowGroupBuilder;
import net.sf.dynamicreports.report.builder.expression.AbstractComplexExpression;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.test.jasper.AbstractJasperCrosstabValueTest;
import net.sf.dynamicreports.test.jasper.JasperTestUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintText;


/**
 * @author Ricardo Mariaca
 */
public class HyperLinkCrosstabTest extends AbstractJasperCrosstabValueTest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private CrosstabRowGroupBuilder<String> rowGroup;
	private CrosstabColumnGroupBuilder<String> columnGroup;
	private CrosstabMeasureBuilder<Integer> measure;
	
	@Override
	protected void configureReport(final JasperReportBuilder rb)
	{
        this.rowGroup = ctab.rowGroup("field1", String.class);
        this.rowGroup.setHeaderHyperLink(hyperLink(new HyperLinkExpression1(this.rowGroup)));
        this.columnGroup = ctab.columnGroup("field2", String.class);
        this.columnGroup.setHeaderHyperLink(hyperLink(new HyperLinkExpression1(this.columnGroup)));
        this.measure = ctab.measure("field3", Integer.class, Calculation.SUM);
        this.measure.setHyperLink(hyperLink(new HyperLinkExpression2(this.rowGroup, this.columnGroup, this.measure)));
		
		final CrosstabBuilder crosstab = ctab.crosstab().rowGroups(this.rowGroup).columnGroups(this.columnGroup).measures(
            this.measure);
		
		rb.setLocale(Locale.ENGLISH).summary(crosstab);
	}
	
	@Override
	public void test()
	{
		super.test();
        
        this.numberOfPagesTest(1);
        
        this.setCrosstabBand("summary");
		
		List<JRPrintElement> elements =
            this.findElement(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(this.rowGroup));
		Assertions.assertEquals(2, elements.size());
		JRPrintText element = (JRPrintText)elements.get(0);
		Assertions.assertEquals("a", element.getHyperlinkReference());
		element = (JRPrintText)elements.get(1);
		Assertions.assertEquals("b", element.getHyperlinkReference());
		
		elements = this.findElement(this.getPrefix(1) + JasperTestUtils.getCrosstabGroupHeaderName(this.columnGroup));
		Assertions.assertEquals(2, elements.size());
		element = (JRPrintText)elements.get(0);
		Assertions.assertEquals("c", element.getHyperlinkReference());
		element = (JRPrintText)elements.get(1);
		Assertions.assertEquals( "d", element.getHyperlinkReference());
        
        elements = this.findElement(this.getPrefix(1) + JasperTestUtils.getCrosstabCellName(this.measure, null, null));
		Assertions.assertEquals(4, elements.size());
		element = (JRPrintText)elements.get(0);
		Assertions.assertEquals("a c 3", element.getHyperlinkReference());
		element = (JRPrintText)elements.get(1);
		Assertions.assertEquals("a d 7", element.getHyperlinkReference());
		element = (JRPrintText)elements.get(2);
		Assertions.assertEquals("b c 11", element.getHyperlinkReference());
		element = (JRPrintText)elements.get(3);
		Assertions.assertEquals("b d 15", element.getHyperlinkReference());
	}
	
	@Override
	protected JRDataSource createDataSource()
	{
		final DRDataSource dataSource = new DRDataSource("field1", "field2", "field3");
		dataSource.add("a", "c", 1);
		dataSource.add("a", "c", 2);
		dataSource.add("a", "d", 3);
		dataSource.add("a", "d", 4);
		dataSource.add("b", "c", 5);
		dataSource.add("b", "c", 6);
		dataSource.add("b", "d", 7);
		dataSource.add("b", "d", 8);
		return dataSource;
	}
	
	private static class HyperLinkExpression1 extends AbstractComplexExpression<String>
	{
		private static final long serialVersionUID = 1L;
		
		public HyperLinkExpression1(final AbstractCrosstabGroupBuilder<?, ?, ?> group)
		{
            this.addExpression(group);
		}
		
		@Override
		public String evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			return (String)values.get(0);
		}
	}
	
	
	private class HyperLinkExpression2 extends AbstractComplexExpression<String>
	{
		private static final long serialVersionUID = 1L;
		
		public HyperLinkExpression2(
			final AbstractCrosstabGroupBuilder<?, ?, ?> group1,
			final AbstractCrosstabGroupBuilder<?, ?, ?> group2,
			final CrosstabMeasureBuilder<?> measure)
		{
            this.addExpression(group1);
            this.addExpression(group2);
            this.addExpression(measure);
		}
		
		@Override
		public String evaluate(final List<?> values, final ReportParameters reportParameters)
		{
			return values.get(0) + " " + values.get(1) + " " + values.get(2);
		}
	}
}
