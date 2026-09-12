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
package software.xdev.dynamicreports.report.base;

import org.apache.commons.lang3.Validate;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.constant.GroupFooterPosition;
import software.xdev.dynamicreports.report.constant.GroupHeaderLayout;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public class DRGroup implements DRIGroup
{

	private final String name;
	private final DRTextField<?> valueField;
	private DRIExpression<?> titleExpression;
	private DRIReportStyle titleStyle;
	private Integer titleWidth;
	private GroupHeaderLayout headerLayout;
	private Boolean hideColumn;
	private Boolean groupByDataType;
	private Boolean showColumnHeaderAndFooter;
	private Boolean addToTableOfContents;
	private DRIExpression<Boolean> printSubtotalsWhenExpression;
	private Integer padding;
	private Boolean startInNewPage;
	private Boolean startInNewColumn;
	private Boolean reprintHeaderOnEachPage;
	private Boolean resetPageNumber;
	private Integer minHeightToStartNewPage;
	private GroupFooterPosition footerPosition;
	private Boolean keepTogether;
	private Boolean headerWithSubtotal;
	private DRBand headerBand;
	private DRBand footerBand;
	
	public DRGroup(final DRTextField<?> valueField)
	{
		this(ReportUtils.generateUniqueName("group"), valueField);
	}
	
	public DRGroup(final String name, final DRTextField<?> valueField)
	{
		Validate.notEmpty(name, "name must not be empty");
		Validate.notNull(valueField, "valueField must not be null");
		this.name = name;
		this.valueField = valueField;
		this.init();
	}
	
	private void init()
	{
		this.headerBand = new DRBand();
		this.footerBand = new DRBand();
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public DRTextField<?> getValueField()
	{
		return this.valueField;
	}
	
	@Override
	public DRIExpression<?> getTitleExpression()
	{
		return this.titleExpression;
	}
	
	public void setTitleExpression(final DRIExpression<?> titleExpression)
	{
		this.titleExpression = titleExpression;
	}
	
	@Override
	public DRIReportStyle getTitleStyle()
	{
		return this.titleStyle;
	}
	
	public void setTitleStyle(final DRIReportStyle titleStyle)
	{
		this.titleStyle = titleStyle;
	}
	
	@Override
	public Integer getTitleWidth()
	{
		return this.titleWidth;
	}
	
	public void setTitleWidth(final Integer titleWidth)
	{
		this.titleWidth = titleWidth;
	}
	
	@Override
	public GroupHeaderLayout getHeaderLayout()
	{
		return this.headerLayout;
	}
	
	public void setHeaderLayout(final GroupHeaderLayout headerLayout)
	{
		this.headerLayout = headerLayout;
	}
	
	@Override
	public Boolean getHideColumn()
	{
		return this.hideColumn;
	}
	
	public void setHideColumn(final Boolean hideColumn)
	{
		this.hideColumn = hideColumn;
	}
	
	@Override
	public Boolean getGroupByDataType()
	{
		return this.groupByDataType;
	}
	
	public void setGroupByDataType(final Boolean groupByDataType)
	{
		this.groupByDataType = groupByDataType;
	}
	
	@Override
	public Boolean getShowColumnHeaderAndFooter()
	{
		return this.showColumnHeaderAndFooter;
	}
	
	public void setShowColumnHeaderAndFooter(final Boolean showColumnHeaderAndFooter)
	{
		this.showColumnHeaderAndFooter = showColumnHeaderAndFooter;
	}
	
	@Override
	public Boolean getAddToTableOfContents()
	{
		return this.addToTableOfContents;
	}
	
	public void setAddToTableOfContents(final Boolean addToTableOfContents)
	{
		this.addToTableOfContents = addToTableOfContents;
	}
	
	@Override
	public DRIExpression<Boolean> getPrintSubtotalsWhenExpression()
	{
		return this.printSubtotalsWhenExpression;
	}
	
	public void setPrintSubtotalsWhenExpression(final DRIExpression<Boolean> printSubtotalsWhenExpression)
	{
		this.printSubtotalsWhenExpression = printSubtotalsWhenExpression;
	}
	
	@Override
	public Integer getPadding()
	{
		return this.padding;
	}
	
	public void setPadding(final Integer padding)
	{
		if(padding != null)
		{
			Validate.isTrue(padding >= 0, "padding must be >= 0");
		}
		this.padding = padding;
	}
	
	@Override
	public Boolean getStartInNewPage()
	{
		return this.startInNewPage;
	}
	
	public void setStartInNewPage(final Boolean startInNewPage)
	{
		this.startInNewPage = startInNewPage;
	}
	
	@Override
	public Boolean getStartInNewColumn()
	{
		return this.startInNewColumn;
	}
	
	public void setStartInNewColumn(final Boolean startInNewColumn)
	{
		this.startInNewColumn = startInNewColumn;
	}
	
	@Override
	public Boolean getReprintHeaderOnEachPage()
	{
		return this.reprintHeaderOnEachPage;
	}
	
	public void setReprintHeaderOnEachPage(final Boolean reprintHeaderOnEachPage)
	{
		this.reprintHeaderOnEachPage = reprintHeaderOnEachPage;
	}
	
	@Override
	public Boolean getResetPageNumber()
	{
		return this.resetPageNumber;
	}
	
	public void setResetPageNumber(final Boolean resetPageNumber)
	{
		this.resetPageNumber = resetPageNumber;
	}
	
	@Override
	public Integer getMinHeightToStartNewPage()
	{
		return this.minHeightToStartNewPage;
	}
	
	public void setMinHeightToStartNewPage(final Integer minHeightToStartNewPage)
	{
		this.minHeightToStartNewPage = minHeightToStartNewPage;
	}
	
	@Override
	public GroupFooterPosition getFooterPosition()
	{
		return this.footerPosition;
	}
	
	public void setFooterPosition(final GroupFooterPosition footerPosition)
	{
		this.footerPosition = footerPosition;
	}
	
	@Override
	public Boolean getKeepTogether()
	{
		return this.keepTogether;
	}
	
	public void setKeepTogether(final Boolean keepTogether)
	{
		this.keepTogether = keepTogether;
	}
	
	@Override
	public Boolean getHeaderWithSubtotal()
	{
		return this.headerWithSubtotal;
	}
	
	public void setHeaderWithSubtotal(final Boolean headerWithSubtotal)
	{
		this.headerWithSubtotal = headerWithSubtotal;
	}
	
	@Override
	public DRBand getHeaderBand()
	{
		return this.headerBand;
	}
	
	@Override
	public DRBand getFooterBand()
	{
		return this.footerBand;
	}
}
