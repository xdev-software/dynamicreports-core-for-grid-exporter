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
package software.xdev.dynamicreports.report.builder.group;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.base.DRGroup;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.builder.component.ComponentBuilder;
import software.xdev.dynamicreports.report.builder.style.ReportStyleBuilder;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;


@SuppressWarnings({"unchecked", "rawtypes", "deprecation"})
public abstract class GroupBuilder<T extends GroupBuilder<T>> extends AbstractBuilder<T, DRGroup>
{

	private DRIExpression valueExpression;
	
	protected GroupBuilder()
	{
		super(new DRGroup(new DRTextField()));
	}
	
	protected GroupBuilder(final String name)
	{
		super(new DRGroup(name, new DRTextField()));
	}
	
	protected void setValueExpression(final DRIExpression<?> valueExpression)
	{
		this.valueExpression = valueExpression;
	}
	
	public T setHeaderLayout(final GroupHeaderLayout headerLayout)
	{
		this.getObject().setHeaderLayout(headerLayout);
		return (T)this;
	}
	
	public T showColumnHeaderAndFooter()
	{
		return this.setShowColumnHeaderAndFooter(true);
	}
	
	public T setShowColumnHeaderAndFooter(final Boolean showColumnHeaderAndFooter)
	{
		this.getObject().setShowColumnHeaderAndFooter(showColumnHeaderAndFooter);
		return (T)this;
	}
	
	public T setAddToTableOfContents(final Boolean addToTableOfContents)
	{
		this.getObject().setAddToTableOfContents(addToTableOfContents);
		return (T)this;
	}
	
	public T setPrintSubtotalsWhenExpression(final DRIExpression<Boolean> printSubtotalsWhenExpression)
	{
		this.getObject().setPrintSubtotalsWhenExpression(printSubtotalsWhenExpression);
		return (T)this;
	}
	
	public T setPadding(final Integer padding)
	{
		this.getObject().setPadding(padding);
		return (T)this;
	}
	
	public T startInNewPage()
	{
		return this.setStartInNewPage(true);
	}
	
	public T setStartInNewPage(final Boolean startInNewPage)
	{
		this.getObject().setStartInNewPage(startInNewPage);
		return (T)this;
	}
	
	public T startInNewColumn()
	{
		return this.setStartInNewColumn(true);
	}
	
	public T setStartInNewColumn(final Boolean startInNewColumn)
	{
		this.getObject().setStartInNewColumn(startInNewColumn);
		return (T)this;
	}
	
	public T reprintHeaderOnEachPage()
	{
		return this.setReprintHeaderOnEachPage(true);
	}
	
	public T setReprintHeaderOnEachPage(final Boolean reprintHeaderOnEachPage)
	{
		this.getObject().setReprintHeaderOnEachPage(reprintHeaderOnEachPage);
		return (T)this;
	}
	
	public T resetPageNumber()
	{
		return this.setResetPageNumber(true);
	}
	
	public T setResetPageNumber(final Boolean resetPageNumber)
	{
		this.getObject().setResetPageNumber(resetPageNumber);
		return (T)this;
	}
	
	public T setMinHeightToStartNewPage(final Integer minHeightToStartNewPage)
	{
		this.getObject().setMinHeightToStartNewPage(minHeightToStartNewPage);
		return (T)this;
	}
	
	public T setFooterPosition(final GroupFooterPosition footerPosition)
	{
		this.getObject().setFooterPosition(footerPosition);
		return (T)this;
	}
	
	public T keepTogether()
	{
		return this.setKeepTogether(true);
	}
	
	public T setKeepTogether(final Boolean keepTogether)
	{
		this.getObject().setKeepTogether(keepTogether);
		return (T)this;
	}
	
	public T headerWithSubtotal()
	{
		return this.setHeaderWithSubtotal(true);
	}
	
	public T setHeaderWithSubtotal(final Boolean headerWithSubtotal)
	{
		this.getObject().setHeaderWithSubtotal(headerWithSubtotal);
		return (T)this;
	}
	
	public T groupByDataType()
	{
		return this.setGroupByDataType(true);
	}
	
	public T setGroupByDataType(final Boolean groupByDataType)
	{
		this.getObject().setGroupByDataType(groupByDataType);
		return (T)this;
	}
	
	public T setStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getValueField().setStyle(style.getStyle());
		}
		else
		{
			this.getObject().getValueField().setStyle(null);
		}
		return (T)this;
	}
	
	public T setHorizontalTextAlignment(final HorizontalTextAlignment horizontalTextAlignment)
	{
		this.getObject().getValueField().setHorizontalTextAlignment(horizontalTextAlignment);
		return (T)this;
	}
	
	public T setTitleStyle(final ReportStyleBuilder titleStyle)
	{
		if(titleStyle != null)
		{
			this.getObject().setTitleStyle(titleStyle.getStyle());
		}
		else
		{
			this.getObject().setTitleStyle(null);
		}
		return (T)this;
	}
	
	public T setTitleWidth(final Integer titleWidth)
	{
		this.getObject().setTitleWidth(titleWidth);
		return (T)this;
	}
	
	// header
	
	public T setHeaderSplitType(final SplitType splitType)
	{
		this.getObject().getHeaderBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setHeaderPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getHeaderBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setHeaderStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getHeaderBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getHeaderBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setHeaderBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getHeaderBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T addHeaderComponent(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getHeaderBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	public T header(final ComponentBuilder<?, ?>... components)
	{
		return this.addHeaderComponent(components);
	}
	
	// footer
	
	public T setFooterSplitType(final SplitType splitType)
	{
		this.getObject().getFooterBand().setSplitType(splitType);
		return (T)this;
	}
	
	public T setFooterPrintWhenExpression(final DRIExpression<Boolean> printWhenExpression)
	{
		this.getObject().getFooterBand().setPrintWhenExpression(printWhenExpression);
		return (T)this;
	}
	
	public T setFooterStyle(final ReportStyleBuilder style)
	{
		if(style != null)
		{
			this.getObject().getFooterBand().getList().setStyle(style.build());
		}
		else
		{
			this.getObject().getFooterBand().getList().setStyle(null);
		}
		return (T)this;
	}
	
	public T setFooterBackgroundComponent(final ComponentBuilder<?, ?> backgroundComponent)
	{
		Validate.notNull(backgroundComponent, "backgroundComponent must not be null");
		this.getObject().getFooterBand().getList().setBackgroundComponent(backgroundComponent.build());
		return (T)this;
	}
	
	public T addFooterComponent(final ComponentBuilder<?, ?>... components)
	{
		Validate.notNull(components, "components must not be null");
		Validate.noNullElements(components, "components must not contains null component");
		for(final ComponentBuilder<?, ?> component : components)
		{
			this.getObject().getFooterBand().addComponent(component.build());
		}
		return (T)this;
	}
	
	public T footer(final ComponentBuilder<?, ?>... components)
	{
		return this.addFooterComponent(components);
	}
	
	@Override
	protected void configure()
	{
		super.configure();
		this.getObject().getValueField().setValueExpression(this.valueExpression);
	}
	
	public DRGroup getGroup()
	{
		return this.build();
	}
}
