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
package software.xdev.dynamicreports.report.builder.tableofcontents;

import static software.xdev.dynamicreports.report.builder.DynamicReports.cmp;
import static software.xdev.dynamicreports.report.builder.DynamicReports.field;
import static software.xdev.dynamicreports.report.builder.DynamicReports.hyperLink;
import static software.xdev.dynamicreports.report.builder.DynamicReports.stl;
import static software.xdev.dynamicreports.report.builder.DynamicReports.type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.HyperLinkBuilder;
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.component.HorizontalListBuilder;
import software.xdev.dynamicreports.report.builder.component.TextFieldBuilder;
import software.xdev.dynamicreports.report.builder.component.VerticalListBuilder;
import software.xdev.dynamicreports.report.builder.expression.SystemMessageExpression;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.HyperLinkType;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.definition.ReportParameters;


public class TableOfContentsCustomizer implements DRITableOfContentsCustomizer
{

	protected static String dots;
	
	static
	{
		final StringBuilder dots = new StringBuilder(200);
		for(int i = 0; i < dots.capacity(); i++)
		{
			dots.append(".");
		}
		TableOfContentsCustomizer.dots = dots.toString();
	}
	
	protected ReportBuilder<?> report;
	protected List<JasperTocHeading> headingList;
	protected int headings;
	protected int levels;
	protected FieldBuilder<Integer> levelField;
	protected FieldBuilder<String> textField;
	protected FieldBuilder<String> referenceField;
	protected FieldBuilder<Integer> pageIndexField;
	protected HyperLinkBuilder referenceHyperLink;
	protected int pageIndexDigits;
	protected ReportStyleBuilder titleStyle;
	protected ReportStyleBuilder headingStyle;
	protected Map<Integer, ReportStyleBuilder> headingStyles;
	protected Integer textFixedWidth;
	protected Integer dotsFixedWidth;
	protected Integer pageIndexFixedWidth;
	protected TableOfContentsPosition position;
	
	public TableOfContentsCustomizer()
	{
		this.headingStyles = new HashMap<>();
	}
	
	@SuppressWarnings("checkstyle:MagicNumber")
	protected void init()
	{
		this.levelField = field("level", type.integerType());
		this.textField = field("text", type.stringType());
		this.referenceField = field("reference", type.stringType());
		this.pageIndexField = field("pageIndex", type.integerType());
		
		this.referenceHyperLink = hyperLink();
		this.referenceHyperLink.setAnchor(new ReferenceExpression());
		this.referenceHyperLink.setType(HyperLinkType.LOCAL_ANCHOR);
		
		this.pageIndexDigits = String.valueOf(this.headings).length();
		
		if(this.titleStyle == null)
		{
			this.titleStyle =
				stl.style().bold().setFontSize(16).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
		}
	}
	
	@Override
	public void setReport(final ReportBuilder<?> report)
	{
		this.report = report;
	}
	
	@Override
	public void setHeadingList(final List<JasperTocHeading> headingList)
	{
		this.headingList = headingList;
	}
	
	@Override
	public void setHeadings(final int headings)
	{
		this.headings = headings;
	}
	
	@Override
	public void setLevels(final int levels)
	{
		this.levels = levels;
	}
	
	@SuppressWarnings("checkstyle:MagicNumber")
	@Override
	public void customize()
	{
		this.init();
		
		this.report.title(this.title(), cmp.filler().setFixedHeight(20))
			.fields(this.levelField, this.textField, this.referenceField, this.pageIndexField)
			.detail(this.detailComponent());
	}
	
	protected ComponentBuilder<?, ?> title()
	{
		return cmp.text(new SystemMessageExpression("table_of_contents")).setStyle(this.titleStyle);
	}
	
	protected ComponentBuilder<?, ?> detailComponent()
	{
		final VerticalListBuilder detailComponent = cmp.verticalList();
		for(int i = 0; i < this.levels; i++)
		{
			final ComponentBuilder<?, ?> headingComponent = this.headingComponent(i);
			headingComponent.setPrintWhenExpression(new PrintHeadingExpression(i));
			headingComponent.removeLineWhenBlank();
			detailComponent.add(headingComponent);
		}
		return detailComponent;
	}
	
	protected ComponentBuilder<?, ?> headingComponent(final int level)
	{
		final HorizontalListBuilder headingComponent = cmp.horizontalList();
		
		if(level > 0)
		{
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}
		
		final TextFieldBuilder<String> textComponent = cmp.text(this.textField).setHyperLink(this.referenceHyperLink);
		if(this.textFixedWidth != null)
		{
			textComponent.setFixedWidth(this.textFixedWidth);
		}
		headingComponent.add(textComponent);
		
		if(level > 0)
		{
			headingComponent.add(cmp.filler().setFixedWidth(level * 10));
		}
		
		final TextFieldBuilder<String> dotsComponent =
			cmp.text(dots.toString()).setTextAdjust(TextAdjust.CUT_TEXT).setHyperLink(this.referenceHyperLink);
		if(this.dotsFixedWidth != null)
		{
			dotsComponent.setFixedWidth(this.dotsFixedWidth);
		}
		headingComponent.add(dotsComponent);
		
		final TextFieldBuilder<Integer> pageIndexComponent =
			cmp.text(this.pageIndexField).setHyperLink(this.referenceHyperLink);
		if(this.pageIndexFixedWidth != null)
		{
			pageIndexComponent.setFixedWidth(this.pageIndexFixedWidth);
		}
		else
		{
			pageIndexComponent.setFixedColumns(this.pageIndexDigits);
		}
		headingComponent.add(pageIndexComponent);
		
		ReportStyleBuilder headingStyle = this.headingStyles.get(level);
		if(headingStyle == null)
		{
			headingStyle = this.headingStyle;
		}
		if(headingStyle != null)
		{
			textComponent.setStyle(headingStyle);
			dotsComponent.setStyle(headingStyle);
			pageIndexComponent.setStyle(headingStyle);
		}
		
		return headingComponent;
	}
	
	public void setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		this.titleStyle = titleStyle;
	}
	
	public void setHeadingStyle(final ReportStyleBuilder headingStyle)
	{
		this.headingStyle = headingStyle;
	}
	
	public void setHeadingStyle(final int level, final ReportStyleBuilder headingStyle)
	{
		this.headingStyles.put(level, headingStyle);
	}
	
	public void setTextFixedWidth(final Integer textFixedWidth)
	{
		this.textFixedWidth = textFixedWidth;
	}
	
	public void setDotsFixedWidth(final Integer dotsFixedWidth)
	{
		this.dotsFixedWidth = dotsFixedWidth;
	}
	
	public void setPageIndexFixedWidth(final Integer pageIndexFixedWidth)
	{
		this.pageIndexFixedWidth = pageIndexFixedWidth;
	}
	
	@Override
	public TableOfContentsPosition getPosition()
	{
		return this.position;
	}
	
	public void setPosition(final TableOfContentsPosition position)
	{
		this.position = position;
	}
	
	protected class ReferenceExpression extends AbstractSimpleExpression<String>
	{

		@Override
		public String evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getValue(TableOfContentsCustomizer.this.referenceField);
		}
	}
	
	
	protected class PrintHeadingExpression extends AbstractSimpleExpression<Boolean>
	{

		private final int level;
		
		public PrintHeadingExpression(final int level)
		{
			this.level = level;
		}
		
		@Override
		public Boolean evaluate(final ReportParameters reportParameters)
		{
			return reportParameters.getValue(TableOfContentsCustomizer.this.levelField) == this.level;
		}
	}
}
