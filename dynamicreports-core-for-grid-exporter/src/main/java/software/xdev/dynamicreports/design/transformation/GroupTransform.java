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
package software.xdev.dynamicreports.design.transformation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignFiller;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.design.transformation.expressions.GroupByDataTypeExpression;
import software.xdev.dynamicreports.report.base.DRVariable;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.SplitType;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRIReport;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class GroupTransform
{
	private final DesignTransformAccessor accessor;
	private List<? extends DRIGroup> groups;
	private Map<DRIGroup, DRDesignGroup> designGroups;
	private List<DRIValueColumn<?>> hideGroupColumns;
	private DRIGroup firstResetPageNumberGroup;
	private int groupPadding;
	
	public GroupTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.init();
	}
	
	private void init()
	{
		final DRIReport report = this.accessor.getReport();
		this.groups = report.getGroups();
		this.designGroups = new LinkedHashMap<>();
		this.hideGroupColumns = new ArrayList<>();
		this.groupPadding = 0;
	}
	
	public void transform() throws DRException
	{
		for(final DRIGroup group : this.groups)
		{
			final DRDesignGroup designGroup = this.group(group);
			this.addGroup(group, designGroup);
		}
	}
	
	private void addGroup(final DRIGroup group, final DRDesignGroup designGroup)
	{
		if(this.accessor.getTemplateTransform().isGroupHideColumn(group) && group.getValueField()
			.getValueExpression() instanceof DRIValueColumn<?>)
		{
			this.hideGroupColumns.add((DRIValueColumn<?>)group.getValueField().getValueExpression());
		}
		this.designGroups.put(group, designGroup);
		if(this.firstResetPageNumberGroup == null && designGroup.isResetPageNumber())
		{
			this.firstResetPageNumberGroup = group;
		}
		this.groupPadding += this.accessor.getTemplateTransform().getGroupPadding(group);
	}
	
	public void transformHeaderAndFooter() throws DRException
	{
		int groupPadding = 0;
		int level = 0;
		for(final DRIGroup group : this.groups)
		{
			this.headerAndFooter(group, this.designGroups.get(group), groupPadding, level++);
			groupPadding += this.accessor.getTemplateTransform().getGroupPadding(group);
		}
	}
	
	private void headerAndFooter(
		final DRIGroup group,
		final DRDesignGroup designGroup,
		final int groupPadding,
		final int level)
		throws DRException
	{
		final DRDesignList header = new DRDesignList();
		final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
		
		final DRDesignTableOfContentsHeading designTocHeading =
			this.accessor.getTableOfContentsTransform().groupHeading(group, level);
		if(designTocHeading != null)
		{
			header.addComponent(designTocHeading.getReferenceField());
		}
		
		switch(templateTransform.getGroupHeaderLayout(group))
		{
			case EMPTY:
				break;
			case TITLE_AND_VALUE:
				if(groupPadding > 0)
				{
					final DRDesignFiller filler = new DRDesignFiller();
					filler.setWidth(groupPadding);
					filler.setHeight(0);
					header.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
				}
				header.addComponent(HorizontalCellComponentAlignment.LEFT, null, this.titleComponent(group));
				DRDesignTextField valueComponent = this.valueComponent(group);
				header.addComponent(valueComponent);
				if(designTocHeading != null)
				{
					valueComponent.setAnchorNameExpression(designTocHeading.getReferenceField()
						.getAnchorNameExpression());
					valueComponent.setBookmarkLevel(designTocHeading.getReferenceField().getBookmarkLevel());
					valueComponent.setHyperLink(designTocHeading.getReferenceField().getHyperLink());
				}
				break;
			case VALUE:
				if(groupPadding > 0)
				{
					final DRDesignFiller filler = new DRDesignFiller();
					filler.setWidth(groupPadding);
					filler.setHeight(0);
					header.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
				}
				valueComponent = this.valueComponent(group);
				header.addComponent(valueComponent);
				if(designTocHeading != null)
				{
					valueComponent.setAnchorNameExpression(designTocHeading.getReferenceField()
						.getAnchorNameExpression());
					valueComponent.setBookmarkLevel(designTocHeading.getReferenceField().getBookmarkLevel());
					valueComponent.setHyperLink(designTocHeading.getReferenceField().getHyperLink());
				}
				break;
			default:
				throw new DRDesignReportException(
					"Group header layout " + templateTransform.getGroupHeaderLayout(group).name() + " not supported");
		}
		if(!header.isEmpty())
		{
			final DRIBand bnd = group.getHeaderBand();
			final DRDesignBand band = this.accessor.getBandTransform()
				.band(
					"groupHeaderTitleAndValue",
					bnd,
					templateTransform.getGroupHeaderSplitType(bnd),
					templateTransform.getGroupHeaderStyle(bnd),
					templateTransform.getGroupHeaderBackgroundComponent(bnd));
			band.addComponent(header);
			designGroup.addHeaderBand(band);
		}
		
		DRIBand band = group.getHeaderBand();
		designGroup.addHeaderBand(
			this.groupBand(
				"groupHeader",
				band,
				templateTransform.getGroupHeaderSplitType(band),
				templateTransform.getGroupHeaderStyle(band),
				templateTransform.getGroupHeaderBackgroundComponent(band),
				groupPadding,
				designGroup));
		band = group.getFooterBand();
		designGroup.addFooterBand(
			this.groupBand(
				"groupFooter",
				band,
				templateTransform.getGroupFooterSplitType(band),
				templateTransform.getGroupFooterStyle(band),
				templateTransform.getGroupFooterBackgroundComponent(band),
				groupPadding,
				designGroup));
		if(templateTransform.isGroupShowColumnHeaderAndFooter(group))
		{
			designGroup.addHeaderBand(this.accessor.getBandTransform().getColumnHeaderForGroupBand());
			designGroup.addFooterBand(this.accessor.getBandTransform().getColumnFooterBand());
		}
	}
	
	private DRDesignBand groupBand(
		final String name,
		final DRIBand band,
		final SplitType splitType,
		final DRIReportStyle defaultStyle,
		final DRIComponent defaultBackgroundComponent,
		final int groupPadding,
		final DRDesignGroup resetGroup)
		throws DRException
	{
		final DRDesignBand designBand = this.accessor.getBandTransform()
			.band(name, band, splitType, defaultStyle, defaultBackgroundComponent, ResetType.GROUP, resetGroup);
		if(groupPadding > 0)
		{
			final DRDesignFiller filler = new DRDesignFiller();
			filler.setWidth(groupPadding);
			filler.setHeight(0);
			final DRDesignList list = new DRDesignList();
			list.addComponent(HorizontalCellComponentAlignment.CENTER, null, filler);
			list.addComponent(designBand.getList());
			designBand.setList(list);
		}
		return designBand;
	}
	
	// title
	@SuppressWarnings("unchecked")
	private DRDesignComponent titleComponent(final DRIGroup group) throws DRException
	{
		@SuppressWarnings("rawtypes")
		final DRTextField titleField = new DRTextField();
		titleField.setValueExpression(group.getTitleExpression());
		titleField.setStyle(group.getTitleStyle());
		titleField.setWidth(group.getTitleWidth());
		final DRDesignTextField designTitleField =
			this.accessor.getComponentTransform().textField(titleField, DefaultStyleType.GROUP_TITLE);
		designTitleField.setUniqueName("group_" + group.getName() + ".title");
		return designTitleField;
	}
	
	// value
	private DRDesignTextField valueComponent(final DRIGroup group) throws DRException
	{
		final DRDesignTextField designValueField =
			this.accessor.getComponentTransform().textField(group.getValueField(), DefaultStyleType.GROUP);
		designValueField.setUniqueName("group_" + group.getName());
		return designValueField;
	}
	
	// group
	@SuppressWarnings({"unchecked", "rawtypes"})
	private DRDesignGroup group(final DRIGroup group) throws DRException
	{
		final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
		final DRDesignGroup designGroup = new DRDesignGroup(group.getName());
		designGroup.setStartInNewPage(templateTransform.isGroupStartInNewPage(group));
		designGroup.setStartInNewColumn(templateTransform.isGroupStartInNewColumn(group));
		designGroup.setReprintHeaderOnEachPage(templateTransform.isGroupReprintHeaderOnEachPage(group));
		designGroup.setResetPageNumber(templateTransform.isGroupResetPageNumber(group));
		designGroup.setMinHeightToStartNewPage(templateTransform.getGroupMinHeightToStartNewPage(group));
		designGroup.setFooterPosition(templateTransform.getGroupFooterPosition(group));
		designGroup.setKeepTogether(templateTransform.isGroupKeepTogether(group));
		designGroup.setHeaderWithSubtotal(templateTransform.isGroupHeaderWithSubtotal(group));
		DRIExpression<?> groupExpression = group.getValueField().getValueExpression();
		if(templateTransform.isGroupByDataType(group) && group.getValueField().getDataType() != null)
		{
			this.accessor.getExpressionTransform().transformExpression(groupExpression);
			final DRIDataType<?, ?> dataType = group.getValueField().getDataType();
			groupExpression = new GroupByDataTypeExpression(groupExpression, dataType);
		}
		groupExpression = new DRVariable(groupExpression, Calculation.NOTHING);
		designGroup.setGroupExpression(this.accessor.getExpressionTransform().transformExpression(groupExpression));
		return designGroup;
	}
	
	protected DRDesignGroup getGroup(final DRIGroup group)
	{
		return this.designGroups.get(group);
	}
	
	protected DRIGroup getFirstGroup()
	{
		if(!this.groups.isEmpty())
		{
			return this.groups.get(0);
		}
		return null;
	}
	
	protected DRIGroup getBeforeGroup(final DRIGroup group)
	{
		if(this.groups.size() > 1 && this.groups.indexOf(group) > 0)
		{
			return this.groups.get(this.groups.indexOf(group) - 1);
		}
		return null;
	}
	
	protected DRIGroup getLastGroup()
	{
		if(!this.groups.isEmpty())
		{
			return this.groups.get(this.groups.size() - 1);
		}
		return null;
	}
	
	protected DRIGroup getFirstResetPageNumberGroup()
	{
		return this.firstResetPageNumberGroup;
	}
	
	protected int getGroupPadding()
	{
		return this.groupPadding;
	}
	
	protected List<DRIValueColumn<?>> getHideGroupColumns()
	{
		return this.hideGroupColumns;
	}
	
	public Collection<DRDesignGroup> getGroups()
	{
		return this.designGroups.values();
	}
}
