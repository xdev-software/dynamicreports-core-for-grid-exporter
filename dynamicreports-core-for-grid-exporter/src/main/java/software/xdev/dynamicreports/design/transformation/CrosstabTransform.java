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

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstab;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCell;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabCellContent;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabColumnGroup;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabDataset;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabGroup;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabMeasure;
import software.xdev.dynamicreports.design.base.crosstab.DRDesignCrosstabRowGroup;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.constant.ResetType;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabExpression;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabMeasureExpression;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabPrintInEvenRow;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabPrintInOddRow;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabRowCount;
import software.xdev.dynamicreports.design.transformation.expressions.CrosstabRowCounter;
import software.xdev.dynamicreports.report.base.DRHyperLink;
import software.xdev.dynamicreports.report.base.component.DRFiller;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabCellContent;
import software.xdev.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import software.xdev.dynamicreports.report.base.style.DRConditionalStyle;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.expression.SystemMessageExpression;
import software.xdev.dynamicreports.report.constant.Calculation;
import software.xdev.dynamicreports.report.constant.CrosstabPercentageType;
import software.xdev.dynamicreports.report.constant.StretchType;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstab;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabCellContent;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabCellStyle;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabColumnGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabDataset;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabMeasure;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabRowGroup;
import software.xdev.dynamicreports.report.definition.crosstab.DRICrosstabVariable;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIJasperExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISimpleExpression;
import software.xdev.dynamicreports.report.definition.style.DRIConditionalStyle;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRISimpleStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class CrosstabTransform
{
	private final DesignTransformAccessor accessor;
	private Map<DRDesignCrosstab, DRICrosstab> crosstabs;
	private CrosstabRowCounter crosstabRowCounter;
	
	public CrosstabTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.init();
	}
	
	private void init()
	{
		this.crosstabs = new HashMap<>();
		this.crosstabRowCounter = new CrosstabRowCounter();
	}
	
	@SuppressWarnings("checkstyle:FinalParameters")
	protected DRDesignCrosstab transform(final DRICrosstab crosstab, ResetType resetType, DRDesignGroup resetGroup)
		throws DRException
	{
		final DRDesignCrosstab designCrosstab = new DRDesignCrosstab();
		designCrosstab.setDataset(this.dataset(crosstab.getDataset(), resetType, resetGroup));
		this.accessor.transformToDataset(crosstab.getDataset().getSubDataset());
		if(crosstab.getDataset() != null)
		{
			resetType = null;
			resetGroup = null;
		}
		final DRDesignCrosstabCellContent whenNoDataCell =
			this.cellContent(crosstab.getWhenNoDataCell(), resetType, resetGroup);
		designCrosstab.setWidth(this.accessor.getTemplateTransform().getCrosstabWidth(crosstab));
		designCrosstab.setHeight(this.accessor.getTemplateTransform().getCrosstabHeight(crosstab, whenNoDataCell));
		designCrosstab.setRepeatColumnHeaders(crosstab.isRepeatColumnHeaders());
		designCrosstab.setRepeatRowHeaders(crosstab.isRepeatRowHeaders());
		designCrosstab.setColumnBreakOffset(crosstab.getColumnBreakOffset());
		designCrosstab.setIgnoreWidth(crosstab.getIgnoreWidth());
		designCrosstab.setRunDirection(crosstab.getRunDirection());
		designCrosstab.setWhenNoDataCell(whenNoDataCell);
		designCrosstab.setHeaderCell(this.cellContent(crosstab.getHeaderCell(), resetType, resetGroup));
		for(final DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups())
		{
			this.addColumnGroup(crosstab, designCrosstab, columnGroup, resetType, resetGroup);
		}
		for(final DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups())
		{
			this.addRowGroup(crosstab, designCrosstab, rowGroup, resetType, resetGroup);
		}
		this.addCells(crosstab, designCrosstab, resetType, resetGroup);
		for(final DRICrosstabVariable<?> variable : crosstab.getVariables())
		{
			this.addMeasure(designCrosstab, variable);
		}
		for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
		{
			if(measure.getExpression() instanceof DRICrosstabVariable<?>)
			{
				this.addMeasure(designCrosstab, (DRICrosstabVariable<?>)measure.getExpression());
			}
		}
		this.accessor.getExpressionTransform().transformExpression(this.crosstabRowCounter);
		this.addRowCountExpression(designCrosstab);
		this.crosstabs.put(designCrosstab, crosstab);
		this.accessor.transformToMainDataset();
		
		return designCrosstab;
	}
	
	// dataset
	private DRDesignCrosstabDataset dataset(
		final DRICrosstabDataset dataset,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRDesignCrosstabDataset designDataset = new DRDesignCrosstabDataset();
		designDataset.setSubDataset(this.accessor.getDatasetTransform().transform(dataset.getSubDataset()));
		designDataset.setDataPreSorted(dataset.getDataPreSorted());
		if(resetType != null && resetType.equals(ResetType.NONE))
		{
			designDataset.setResetType(ResetType.REPORT);
		}
		else
		{
			designDataset.setResetType(resetType);
		}
		designDataset.setResetGroup(resetGroup);
		return designDataset;
	}
	
	private void addRowCountExpression(final DRDesignCrosstab designCrosstab) throws DRException
	{
		final DRDesignCrosstabRowGroup lastRowGroup = this.getLastValue(designCrosstab.getRowGroups());
		final DRFiller filler = new DRFiller();
		final CrosstabRowCount rowCountExpression = new CrosstabRowCount();
		filler.setPrintWhenExpression(rowCountExpression);
		final DRDesignComponent designTextField = this.accessor.getComponentTransform().filler(filler);
		lastRowGroup.getHeader().getList().addComponent(designTextField);
	}
	
	private DRDesignCrosstabCellContent cellContent(
		final DRICrosstabCellContent cellContent,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRDesignCrosstabCellContent designCellContents = new DRDesignCrosstabCellContent();
		designCellContents.setList(this.accessor.getComponentTransform()
			.list(cellContent.getList(), DefaultStyleType.TEXT, resetType, resetGroup));
		designCellContents.setStyle(this.accessor.getStyleTransform()
			.transformStyle(cellContent.getStyle(), false, DefaultStyleType.NONE));
		return designCellContents;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void group(
		final DRDesignCrosstabGroup designGroup,
		final DRICrosstab crosstab,
		final DRICrosstabGroup group,
		final boolean showTotal,
		final ResetType resetType,
		final DRDesignGroup resetGroup,
		final DRDesignComponent designTitleComponent,
		final DRDesignComponent designTotalTitleComponent) throws DRException
	{
		designGroup.setName(group.getName());
		designGroup.setOrderType(group.getOrderType());
		
		designGroup.setExpression(this.accessor.getExpressionTransform().transformExpression(group.getExpression()));
		if(group.getOrderByExpression() != null)
		{
			final DRIExpression orderByExpression = this.getCrosstabExpression(crosstab, group.getOrderByExpression());
			designGroup.setOrderByExpression(this.accessor.getExpressionTransform()
				.transformExpression(orderByExpression));
		}
		if(group.getComparatorExpression() != null)
		{
			final DRIExpression comparatorExpression = this.getCrosstabExpression(
				crosstab,
				group.getComparatorExpression());
			designGroup.setComparatorExpression(this.accessor.getExpressionTransform()
				.transformExpression(comparatorExpression));
		}
		
		DRTextField textField = new DRTextField();
		textField.setValueExpression(group);
		textField.setPattern(group.getHeaderPattern());
		textField.setHorizontalTextAlignment(group.getHeaderHorizontalTextAlignment());
		textField.setValueFormatter(group.getHeaderValueFormatter());
		textField.setDataType(group.getDataType());
		textField.setTextAdjust(group.getHeaderTextAdjust());
		textField.setHyperLink((DRHyperLink)group.getHeaderHyperLink());
		textField.setPropertyExpressions(group.getHeaderPropertyExpressions());
		
		boolean measureTitle = false;
		for(final DRICrosstabMeasure<?> crosstabMeasure : crosstab.getMeasures())
		{
			if(crosstabMeasure.getTitleExpression() != null)
			{
				measureTitle = true;
				break;
			}
		}
		
		if(group instanceof DRICrosstabRowGroup || group instanceof DRICrosstabColumnGroup && !measureTitle)
		{
			textField.setStretchType(StretchType.CONTAINER_HEIGHT);
		}
		DRIReportStyle groupStyle = group.getHeaderStyle();
		if(groupStyle == null)
		{
			groupStyle = this.accessor.getTemplateTransform().getCrosstabGroupStyle(crosstab);
		}
		textField.setStyle(groupStyle);
		final DRDesignCrosstabCellContent header =
			this.createCellContent(this.getCellStyle(groupStyle), resetType, resetGroup);
		DRDesignTextField designTextField =
			this.accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
		designTextField.setUniqueName("group_" + designGroup.getName() + ".header");
		header.getList().addComponent(designTextField);
		if(designTitleComponent != null)
		{
			header.getList().addComponent(designTitleComponent);
		}
		designGroup.setHeader(header);
		if(showTotal)
		{
			DRIReportStyle totalStyle = group.getTotalHeaderStyle();
			if(totalStyle == null)
			{
				DRICrosstabGroup<?> firstGroup = null;
				if(group instanceof DRICrosstabRowGroup)
				{
					firstGroup = this.getFirstValue(crosstab.getRowGroups());
				}
				else
				{
					firstGroup = this.getFirstValue(crosstab.getColumnGroups());
				}
				if(firstGroup == group)
				{
					totalStyle = this.accessor.getTemplateTransform().getCrosstabGrandTotalStyle(crosstab);
				}
				else
				{
					totalStyle = this.accessor.getTemplateTransform().getCrosstabGroupTotalStyle(crosstab);
				}
			}
			
			textField = new DRTextField();
			DRIExpression<?> totalHeaderExpression = group.getTotalHeaderExpression();
			if(totalHeaderExpression == null)
			{
				totalHeaderExpression = new SystemMessageExpression("total");
			}
			textField.setValueExpression(totalHeaderExpression);
			textField.setTextAdjust(group.getTotalHeaderTextAdjust());
			textField.setPropertyExpressions(group.getTotalHeaderPropertyExpressions());
			textField.setStyle(totalStyle);
			if(group instanceof DRICrosstabRowGroup || group instanceof DRICrosstabColumnGroup && !measureTitle)
			{
				textField.setStretchType(StretchType.CONTAINER_HEIGHT);
			}
			
			final DRDesignCrosstabCellContent totalHeader =
				this.createCellContent(this.getCellStyle(totalStyle), resetType, resetGroup);
			designTextField = this.accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			designTextField.setUniqueName("group_" + designGroup.getName() + ".totalheader");
			totalHeader.getList().addComponent(designTextField);
			if(designTotalTitleComponent != null)
			{
				totalHeader.getList().addComponent(designTotalTitleComponent);
			}
			designGroup.setTotalHeader(totalHeader);
		}
	}
	
	private void addColumnGroup(
		final DRICrosstab crosstab,
		final DRDesignCrosstab designCrosstab,
		final DRICrosstabColumnGroup<?> columnGroup,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRDesignCrosstabColumnGroup designColumnGroup = new DRDesignCrosstabColumnGroup();
		final boolean showTotal = this.accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup);
		
		DRDesignComponent designTitleComponent = null;
		DRDesignComponent designTotalTitleComponent = null;
		final boolean lastColumn = columnGroup == this.getLastValue(crosstab.getColumnGroups());
		if(showTotal || lastColumn)
		{
			boolean showTitle = false;
			for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
			{
				if(measure.getTitleExpression() != null)
				{
					showTitle = true;
					break;
				}
			}
			
			if(showTitle)
			{
				if(lastColumn)
				{
					DRIReportStyle groupStyle = columnGroup.getHeaderStyle();
					if(groupStyle == null)
					{
						groupStyle = this.accessor.getTemplateTransform().getCrosstabGroupStyle(crosstab);
					}
					final String name = "group_" + columnGroup.getName() + ".titleheader";
					designTitleComponent = this.getMeasureTitleComponent(name, crosstab, groupStyle);
				}
				if(showTotal)
				{
					DRIReportStyle totalStyle = columnGroup.getTotalHeaderStyle();
					if(totalStyle == null)
					{
						final DRICrosstabGroup<?> firstGroup = this.getFirstValue(crosstab.getColumnGroups());
						if(firstGroup == columnGroup)
						{
							totalStyle = this.accessor.getTemplateTransform().getCrosstabGrandTotalStyle(crosstab);
						}
						else
						{
							totalStyle = this.accessor.getTemplateTransform().getCrosstabGroupTotalStyle(crosstab);
						}
					}
					final String name = "group_" + columnGroup.getName() + ".titletotalheader";
					designTotalTitleComponent = this.getMeasureTitleComponent(name, crosstab, totalStyle);
				}
			}
		}
		
		this.group(
			designColumnGroup,
			crosstab,
			columnGroup,
			showTotal,
			resetType,
			resetGroup,
			designTitleComponent,
			designTotalTitleComponent);
		designColumnGroup.setTotalPosition(this.accessor.getTemplateTransform()
			.getCrosstabColumnGroupTotalPosition(columnGroup));
		designCrosstab.getColumnGroups().add(designColumnGroup);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private DRDesignComponent getMeasureTitleComponent(
		final String name,
		final DRICrosstab crosstab,
		final DRIReportStyle defaultStyle) throws DRException
	{
		final DRDesignList titleComponent = new DRDesignList();
		for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
		{
			final DRTextField textField = new DRTextField();
			if(measure.getTitleExpression() != null)
			{
				textField.setValueExpression(measure.getTitleExpression());
			}
			DRIReportStyle titleStyle = this.accessor.getTemplateTransform().getCrosstabMeasureTitleStyle(
				crosstab,
				measure);
			if(titleStyle == null)
			{
				titleStyle = defaultStyle;
			}
			textField.setStyle(titleStyle);
			final DRDesignTextField designTextField =
				this.accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			designTextField.setUniqueName(name + "." + measure.getName());
			titleComponent.addComponent(designTextField);
		}
		
		return titleComponent;
	}
	
	private void addRowGroup(
		final DRICrosstab crosstab,
		final DRDesignCrosstab designCrosstab,
		final DRICrosstabRowGroup<?> rowGroup,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRDesignCrosstabRowGroup designRowGroup = new DRDesignCrosstabRowGroup();
		final boolean showTotal = this.accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup);
		this.group(designRowGroup, crosstab, rowGroup, showTotal, resetType, resetGroup, null, null);
		designRowGroup.setTotalPosition(this.accessor.getTemplateTransform()
			.getCrosstabRowGroupTotalPosition(rowGroup));
		designCrosstab.getRowGroups().add(designRowGroup);
	}
	
	private void addCells(
		final DRICrosstab crosstab,
		final DRDesignCrosstab designCrosstab,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final MeasuresStyles measuresStyle = new MeasuresStyles(crosstab, designCrosstab);
		
		DRIReportStyle groupTotalStyle = this.accessor.getTemplateTransform().getCrosstabGroupTotalStyle(crosstab);
		groupTotalStyle = this.getCellStyle(groupTotalStyle);
		DRIReportStyle grandTotalStyle = this.accessor.getTemplateTransform().getCrosstabGrandTotalStyle(crosstab);
		grandTotalStyle = this.getCellStyle(grandTotalStyle);
		DRIReportStyle cellStyle = this.accessor.getTemplateTransform().getCrosstabCellStyle(crosstab);
		cellStyle = this.getCellStyle(cellStyle);
		
		final DRICrosstabGroup<?> firstColumnGroup = this.getFirstValue(crosstab.getColumnGroups());
		final DRICrosstabGroup<?> firstRowGroup = this.getFirstValue(crosstab.getRowGroups());
		
		DRDesignCrosstabCell designCell =
			this.cell(crosstab, cellStyle, measuresStyle, null, null, resetType, resetGroup);
		designCrosstab.getCells().add(designCell);
		
		DRIReportStyle style = null;
		for(final DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups())
		{
			if(columnGroup == firstColumnGroup)
			{
				style = grandTotalStyle;
			}
			else
			{
				style = groupTotalStyle;
			}
			if(this.accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup))
			{
				designCell = this.cell(crosstab, style, measuresStyle, null, columnGroup, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
			}
		}
		
		for(final DRICrosstabRowGroup<?> rowGroup : crosstab.getRowGroups())
		{
			if(rowGroup == firstRowGroup)
			{
				style = grandTotalStyle;
			}
			else
			{
				style = groupTotalStyle;
			}
			if(this.accessor.getTemplateTransform().isCrosstabRowGroupShowTotal(rowGroup))
			{
				designCell = this.cell(crosstab, style, measuresStyle, rowGroup, null, resetType, resetGroup);
				designCrosstab.getCells().add(designCell);
				
				for(final DRICrosstabColumnGroup<?> columnGroup : crosstab.getColumnGroups())
				{
					if(this.accessor.getTemplateTransform().isCrosstabColumnGroupShowTotal(columnGroup))
					{
						DRIReportStyle totalStyle = style;
						if(columnGroup == firstColumnGroup)
						{
							totalStyle = grandTotalStyle;
						}
						designCell =
							this.cell(
								crosstab,
								totalStyle,
								measuresStyle,
								rowGroup,
								columnGroup,
								resetType,
								resetGroup);
						designCrosstab.getCells().add(designCell);
					}
				}
			}
		}
	}
	
	private <T> T getFirstValue(final List<T> values)
	{
		if(!values.isEmpty())
		{
			return values.get(0);
		}
		
		return null;
	}
	
	private <T> T getLastValue(final List<T> values)
	{
		if(!values.isEmpty())
		{
			return values.get(values.size() - 1);
		}
		
		return null;
	}
	
	private DRIReportStyle getCellStyle(final DRIReportStyle reportStyle) throws DRException
	{
		final DRIStyle style = this.accessor.getStyleTransform().getStyle(reportStyle);
		if(style == null || style.getBackgroundColor() == null)
		{
			return null;
		}
		
		final DRStyle cellStyle = new DRStyle();
		cellStyle.setBackgroundColor(style.getBackgroundColor());
		return cellStyle;
	}
	
	private DRIReportStyle cellStyle(
		final DRICrosstab crosstab,
		final List<DRIConditionalStyle> rowHighlighters,
		final DRIReportStyle reportStyle) throws DRException
	{
		final DRIStyle style = this.accessor.getStyleTransform().getStyle(reportStyle);
		if(rowHighlighters != null && !rowHighlighters.isEmpty() || !style.getConditionalStyles().isEmpty())
		{
			final DRStyle newStyle = new DRStyle();
			if(style != null)
			{
				newStyle.setParentStyle(style);
				for(final DRIConditionalStyle conditionalStyle : style.getConditionalStyles())
				{
					final DRIExpression<Boolean> conditionalStyleExpression =
						this.getCrosstabExpression(crosstab, conditionalStyle.getConditionExpression());
					final DRConditionalStyle newConditionalStyle = new DRConditionalStyle(conditionalStyleExpression);
					this.accessor.getStyleTransform().copyStyle(newConditionalStyle, conditionalStyle);
					newStyle.addConditionalStyle(newConditionalStyle);
				}
			}
			if(rowHighlighters != null && !rowHighlighters.isEmpty())
			{
				final Color backgroundColor =
					StyleResolver.getBackgroundColor(style, this.accessor.getStyleTransform());
				for(final DRIConditionalStyle conditionalStyle : rowHighlighters)
				{
					if(backgroundColor != null && conditionalStyle.getBackgroundColor() != null)
					{
						final DRConditionalStyle newConditionalStyle =
							new DRConditionalStyle(conditionalStyle.getConditionExpression());
						this.accessor.getStyleTransform().copyStyle(newConditionalStyle, conditionalStyle);
						final Color mergedColor =
							StyleResolver.mergeColors(backgroundColor, conditionalStyle.getBackgroundColor(), 0.25f);
						newConditionalStyle.setBackgroundColor(mergedColor);
						newStyle.addConditionalStyle(newConditionalStyle);
					}
					else
					{
						newStyle.addConditionalStyle((DRConditionalStyle)conditionalStyle);
					}
				}
			}
			return newStyle;
		}
		
		return style;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private DRDesignCrosstabCell cell(
		final DRICrosstab crosstab,
		final DRIReportStyle cellStyle,
		final MeasuresStyles measuresStyle,
		final DRICrosstabRowGroup<?> rowGroup,
		final DRICrosstabColumnGroup<?> columnGroup,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRDesignCrosstabCell designCell = new DRDesignCrosstabCell();
		String rowTotalGroup = null;
		String columnTotalGroup = null;
		if(rowGroup != null)
		{
			rowTotalGroup = rowGroup.getName();
		}
		if(columnGroup != null)
		{
			columnTotalGroup = columnGroup.getName();
		}
		designCell.setRowTotalGroup(rowTotalGroup);
		designCell.setColumnTotalGroup(columnTotalGroup);
		
		final DRDesignList designList = new DRDesignList();
		designList.setStretchType(StretchType.CONTAINER_HEIGHT);
		for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
		{
			final DRTextField textField = new DRTextField();
			
			if(measure.getExpression() instanceof DRICrosstabVariable<?>)
			{
				textField.setValueExpression(measure.getExpression());
			}
			else
			{
				final DRIExpression valueExpression = this.getCrosstabExpression(crosstab, measure.getExpression());
				textField.setValueExpression(valueExpression);
			}
			
			textField.setPattern(measure.getPattern());
			textField.setHorizontalTextAlignment(measure.getHorizontalTextAlignment());
			textField.setValueFormatter(measure.getValueFormatter());
			textField.setDataType(measure.getDataType());
			textField.setTextAdjust(measure.getTextAdjust());
			textField.setStretchType(StretchType.CONTAINER_HEIGHT);
			textField.setHyperLink((DRHyperLink)measure.getHyperLink());
			textField.setPropertyExpressions(measure.getPropertyExpressions());
			textField.setStyle(measuresStyle.getStyle(measure, rowGroup, columnGroup));
			final DRDesignTextField designTextField =
				this.accessor.getComponentTransform().textField(textField, DefaultStyleType.TEXT);
			String name = "cell_measure[" + measure.getName() + "]";
			if(rowTotalGroup != null)
			{
				name += "_rowgroup[" + rowTotalGroup + "]";
			}
			if(columnTotalGroup != null)
			{
				name += "_columngroup[" + columnTotalGroup + "]";
			}
			designTextField.setUniqueName(name);
			designList.addComponent(designTextField);
		}
		
		final DRDesignCrosstabCellContent content = this.createCellContent(cellStyle, resetType, resetGroup);
		designCell.setContent(content);
		content.getList().addComponent(designList);
		
		return designCell;
	}
	
	private DRConditionalStyle detailRowConditionalStyle(
		final DRISimpleStyle style,
		final DRISimpleExpression<Boolean> expression)
	{
		final DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		this.accessor.getStyleTransform().copyStyle(conditionalStyle, style);
		return conditionalStyle;
	}
	
	private boolean equalsGroup(final DRICrosstabGroup<?> group1, final DRICrosstabGroup<?> group2)
	{
		if(group1 == null && group2 == null)
		{
			return true;
		}
		if(group1 != null && group2 != null)
		{
			return group1.equals(group2);
		}
		return false;
	}
	
	private DRDesignCrosstabCellContent createCellContent(
		final DRIReportStyle style,
		final ResetType resetType,
		final DRDesignGroup resetGroup) throws DRException
	{
		final DRCrosstabCellContent cellContent = new DRCrosstabCellContent();
		cellContent.setStyle(style);
		return this.cellContent(cellContent, resetType, resetGroup);
	}
	
	private void addMeasure(final DRDesignCrosstab designCrosstab, final DRICrosstabVariable<?> variable)
		throws DRException
	{
		final DRDesignCrosstabMeasure designMeasure = new DRDesignCrosstabMeasure();
		designMeasure.setName(variable.getName());
		final DRIExpression<?> expression;
		if(variable.getPercentageType() != null
			&& variable.getPercentageType().equals(CrosstabPercentageType.GRAND_TOTAL)
			&& !variable.getCalculation().equals(Calculation.COUNT)
			&& !variable.getCalculation().equals(Calculation.DISTINCT_COUNT))
		{
			expression = new CrosstabMeasureExpression(variable.getValueExpression());
		}
		else
		{
			expression = variable.getValueExpression();
		}
		designMeasure.setValueExpression(this.accessor.getExpressionTransform().transformExpression(expression));
		designMeasure.setCalculation(variable.getCalculation());
		designMeasure.setPercentageType(this.accessor.getTemplateTransform().getCrosstabPercentageType(variable));
		
		designCrosstab.getMeasures().add(designMeasure);
	}
	
	protected DRICrosstab getCrosstab(final DRDesignCrosstab designCrosstab)
	{
		return this.crosstabs.get(designCrosstab);
	}
	
	private <T> DRIExpression<T> getCrosstabExpression(final DRICrosstab crosstab, final DRIExpression<T> expression)
		throws DRException
	{
		if(expression instanceof DRIJasperExpression)
		{
			return expression;
		}
		this.accessor.getExpressionTransform().transformExpression(expression);
		return new CrosstabExpression<>(crosstab, expression);
	}
	
	class MeasuresStyles
	{
		private final Map<DRICrosstabMeasure<?>, List<DRICrosstabCellStyle>> measuresStyle;
		private DRIReportStyle defaultCellStyle;
		private DRIReportStyle defaultGroupTotalStyle;
		private DRIReportStyle defaultGrandTotalStyle;
		private DRIReportStyle defaultRowGroupTotalStyle;
		private DRIReportStyle defaultRowGrandTotalStyle;
		private DRICrosstabGroup<?> firstColumnGroup;
		private DRICrosstabGroup<?> firstRowGroup;
		
		MeasuresStyles(final DRICrosstab crosstab, final DRDesignCrosstab designCrosstab) throws DRException
		{
			this.measuresStyle = new HashMap<>();
			this.init(crosstab, designCrosstab);
		}
		
		private void init(final DRICrosstab crosstab, final DRDesignCrosstab designCrosstab) throws DRException
		{
			final List<DRIConditionalStyle> rowHighlighters = new ArrayList<>();
			final DRISimpleStyle detailOddRowStyle =
				CrosstabTransform.this.accessor.getTemplateTransform().getCrosstabOddRowStyle(crosstab);
			final DRISimpleStyle detailEvenRowStyle =
				CrosstabTransform.this.accessor.getTemplateTransform().getCrosstabEvenRowStyle(crosstab);
			if(detailOddRowStyle != null || detailEvenRowStyle != null)
			{
				if(detailOddRowStyle != null)
				{
					rowHighlighters.add(CrosstabTransform.this.detailRowConditionalStyle(
						detailOddRowStyle,
						new CrosstabPrintInOddRow()));
				}
				if(detailEvenRowStyle != null)
				{
					rowHighlighters.add(CrosstabTransform.this.detailRowConditionalStyle(
						detailEvenRowStyle,
						new CrosstabPrintInEvenRow()));
				}
			}
			
			for(final DRICrosstabMeasure<?> measure : crosstab.getMeasures())
			{
				final List<DRICrosstabCellStyle> styles = new ArrayList<>();
				this.measuresStyle.put(measure, styles);
				
				for(final DRICrosstabCellStyle cellStyle : measure.getStyles())
				{
					final DRIReportStyle newStyle =
						CrosstabTransform.this.cellStyle(crosstab, rowHighlighters, cellStyle.getStyle());
					styles.add(new DRCrosstabCellStyle(newStyle, cellStyle.getRowGroup(), cellStyle.getColumnGroup()));
				}
			}
			
			DRIReportStyle groupTotalStyle =
				CrosstabTransform.this.accessor.getTemplateTransform().getCrosstabGroupTotalStyle(crosstab);
			DRIReportStyle grandTotalStyle =
				CrosstabTransform.this.accessor.getTemplateTransform().getCrosstabGrandTotalStyle(crosstab);
			final DRIReportStyle cellStyle =
				CrosstabTransform.this.accessor.getTemplateTransform().getCrosstabCellStyle(crosstab);
			if(groupTotalStyle == null)
			{
				groupTotalStyle = cellStyle;
			}
			if(grandTotalStyle == null)
			{
				grandTotalStyle = cellStyle;
			}
			this.defaultGroupTotalStyle = CrosstabTransform.this.cellStyle(crosstab, rowHighlighters, groupTotalStyle);
			this.defaultGrandTotalStyle = CrosstabTransform.this.cellStyle(crosstab, rowHighlighters, grandTotalStyle);
			if(StyleResolver.getBackgroundColor(groupTotalStyle, CrosstabTransform.this.accessor.getStyleTransform())
				!= null)
			{
				this.defaultRowGroupTotalStyle = CrosstabTransform.this.cellStyle(crosstab, null, groupTotalStyle);
			}
			if(StyleResolver.getBackgroundColor(grandTotalStyle, CrosstabTransform.this.accessor.getStyleTransform())
				!= null)
			{
				this.defaultRowGrandTotalStyle = CrosstabTransform.this.cellStyle(crosstab, null, grandTotalStyle);
			}
			this.defaultCellStyle = CrosstabTransform.this.cellStyle(crosstab, rowHighlighters, cellStyle);
			this.firstColumnGroup = CrosstabTransform.this.getFirstValue(crosstab.getColumnGroups());
			this.firstRowGroup = CrosstabTransform.this.getFirstValue(crosstab.getRowGroups());
		}
		
		public DRIReportStyle getStyle(
			final DRICrosstabMeasure<?> measure,
			final DRICrosstabRowGroup<?> rowGroup,
			final DRICrosstabColumnGroup<?> columnGroup)
		{
			final List<DRICrosstabCellStyle> styles = this.measuresStyle.get(measure);
			for(final DRICrosstabCellStyle crosstabCellStyle : styles)
			{
				if(CrosstabTransform.this.equalsGroup(rowGroup, crosstabCellStyle.getRowGroup())
					&& CrosstabTransform.this.equalsGroup(
					columnGroup,
					crosstabCellStyle.getColumnGroup()))
				{
					return crosstabCellStyle.getStyle();
				}
			}
			for(final DRICrosstabCellStyle crosstabCellStyle : styles)
			{
				if(crosstabCellStyle.getRowGroup() == null && crosstabCellStyle.getColumnGroup() == null)
				{
					return crosstabCellStyle.getStyle();
				}
			}
			
			if(rowGroup == null && columnGroup == null)
			{
				return this.defaultCellStyle;
			}
			if(rowGroup != null)
			{
				if((columnGroup == this.firstColumnGroup || rowGroup == this.firstRowGroup)
					&& this.defaultRowGrandTotalStyle != null)
				{
					return this.defaultRowGrandTotalStyle;
				}
				if(this.defaultRowGroupTotalStyle != null)
				{
					return this.defaultRowGroupTotalStyle;
				}
			}
			if(columnGroup == this.firstColumnGroup || rowGroup == this.firstRowGroup)
			{
				return this.defaultGrandTotalStyle;
			}
			else
			{
				return this.defaultGroupTotalStyle;
			}
		}
	}
}
