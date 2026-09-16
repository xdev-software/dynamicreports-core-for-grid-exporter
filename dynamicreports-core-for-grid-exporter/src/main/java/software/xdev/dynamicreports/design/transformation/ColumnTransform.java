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

import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.base.style.DRDesignStyle;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.report.base.component.DRBooleanField;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.base.style.DRConditionalStyle;
import software.xdev.dynamicreports.report.base.style.DRPadding;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.column.DRIBooleanColumn;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.column.DRIValueColumn;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.style.DRIConditionalStyle;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRISimpleStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;
import software.xdev.dynamicreports.report.exception.DRException;


public class ColumnTransform
{
	private final DesignTransformAccessor accessor;
	private Map<DRIColumn<?>, DRIComponent> columnComponents;
	
	public ColumnTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	// columns
	
	public void transform() throws DRException
	{
		this.columnComponents = this.getColumnComponents();
		final boolean showColumnTitle = this.accessor.getTemplateTransform().isShowColumnTitle();
		final boolean showColumnValues = this.accessor.getTemplateTransform().isShowColumnValues();
		final boolean showColumnTitleForGroup = this.accessor.getBandTransform().getColumnHeaderForGroupBand() != null;
		
		ColumnGrid columnTitle = null;
		if(showColumnTitle)
		{
			columnTitle = this.accessor.getColumnGridTransform()
				.createColumnTitleGrid(this.accessor.getStyleTransform()
					.getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
		}
		ColumnGrid columnTitleForGroup = null;
		if(showColumnTitleForGroup)
		{
			columnTitleForGroup = this.accessor.getColumnGridTransform()
				.createColumnTitleGrid(this.accessor.getStyleTransform()
					.getDefaultStyle(DefaultStyleType.COLUMN_TITLE));
		}
		final ColumnGrid detail = this.accessor.getColumnGridTransform().createColumnGrid();
		
		for(final DRIColumn<?> column : this.accessor.getReport().getColumns())
		{
			if(!this.accessor.getGroupTransform().getHideGroupColumns().contains(column))
			{
				if(column.getTitleExpression() != null)
				{
					if(showColumnTitle)
					{
						columnTitle.addComponent(column, this.titleComponent(column));
					}
					if(showColumnTitleForGroup)
					{
						columnTitleForGroup.addComponent(column, this.titleComponent(column));
					}
				}
				DRDesignComponent detailComponent = null;
				if(column instanceof DRIValueColumn<?>)
				{
					detailComponent = this.detailValueComponent((DRIValueColumn<?>)column);
				}
				else if(column instanceof DRIBooleanColumn)
				{
					detailComponent = this.detailBooleanComponent((DRIBooleanColumn)column);
				}
				else
				{
					detailComponent = this.detailComponent(column);
				}
				detail.addComponent(column, detailComponent);
			}
		}
		
		if(showColumnTitle && !columnTitle.isEmpty())
		{
			this.accessor.getBandTransform().getColumnHeaderBand().addComponent(0, columnTitle.getList());
		}
		if(showColumnTitleForGroup && !columnTitleForGroup.isEmpty())
		{
			this.accessor.getBandTransform()
				.getColumnHeaderForGroupBand()
				.addComponent(0, columnTitleForGroup.getList());
		}
		if(showColumnValues)
		{
			this.accessor.getBandTransform().getDetailBand().addComponent(detail.getList());
		}
	}
	
	private Map<DRIColumn<?>, DRIComponent> getColumnComponents() throws DRException
	{
		final Map<DRIColumn<?>, DRIComponent> columnComponents = new HashMap<>();
		for(final DRIColumn<?> column : this.accessor.getReport().getColumns())
		{
			if(!this.accessor.getGroupTransform().getHideGroupColumns().contains(column))
			{
				DRIComponent component = column.getComponent();
				if(column instanceof DRIBooleanColumn)
				{
					component = this.createBooleanComponent((DRIBooleanColumn)column);
				}
				columnComponents.put(column, component);
			}
		}
		return columnComponents;
	}
	
	private DRIComponent createBooleanComponent(final DRIBooleanColumn column) throws DRException
	{
		final DRIReportStyle booleanColumnStyle = this.accessor.getTemplateTransform().getBooleanColumnStyle(column);
		if(booleanColumnStyle == null)
		{
			return column.getComponent();
		}
		final DRBooleanField booleanField = new DRBooleanField();
		booleanField.setComponentType(column.getComponent().getComponentType());
		booleanField.setEmptyWhenNullValue(column.getComponent().getEmptyWhenNullValue());
		booleanField.setValueExpression(column.getComponent().getValueExpression());
		booleanField.setWidth(column.getComponent().getWidth());
		booleanField.setWidthType(column.getComponent().getWidthType());
		booleanField.setHeight(column.getComponent().getHeight());
		booleanField.setHeightType(column.getComponent().getHeightType());
		booleanField.setImageWidth(column.getComponent().getImageWidth());
		booleanField.setImageHeight(column.getComponent().getImageHeight());
		booleanField.setHorizontalImageAlignment(column.getComponent().getHorizontalImageAlignment());
		booleanField.setHorizontalTextAlignment(column.getComponent().getHorizontalTextAlignment());
		booleanField.setStyle(booleanColumnStyle);
		booleanField.setPrintWhenExpression(column.getComponent().getPrintWhenExpression());
		return booleanField;
	}
	
	// title
	@SuppressWarnings("unchecked")
	private DRDesignComponent titleComponent(final DRIColumn<?> column) throws DRException
	{
		@SuppressWarnings("rawtypes")
		final DRTextField titleField = new DRTextField();
		titleField.setValueExpression(column.getTitleExpression());
		titleField.setStyle(column.getTitleStyle());
		titleField.setWidth(this.accessor.getTemplateTransform()
			.getColumnWidth(column, this.accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		titleField.setHeight(column.getTitleHeight());
		titleField.setHeightType(column.getTitleHeightType());
		titleField.setRows(column.getTitleRows());
		titleField.setTextAdjust(column.getTitleTextAdjust());
		titleField.setPropertyExpressions(column.getTitlePropertyExpressions());
		final DRDesignTextField designTitleField =
			this.accessor.getComponentTransform().textField(titleField, DefaultStyleType.COLUMN_TITLE);
		designTitleField.setUniqueName("column_" + column.getName() + ".title");
		return designTitleField;
	}
	
	// detail
	private DRDesignComponent detailValueComponent(final DRIValueColumn<?> column) throws DRException
	{
		final DRDesignComponent detailComponent = this.detailComponent(column);
		((DRDesignTextField)detailComponent).setPrintRepeatedValues(this.accessor.getTemplateTransform()
			.isColumnPrintRepeatedDetailValues(column));
		return detailComponent;
	}
	
	private DRDesignComponent detailBooleanComponent(final DRIBooleanColumn column) throws DRException
	{
		final DRDesignComponent detailComponent = this.detailComponent(column);
		
		return detailComponent;
	}
	
	private DRDesignComponent detailComponent(final DRIColumn<?> column) throws DRException
	{
		final DRDesignComponent designComponent =
			this.accessor.getComponentTransform().component(
				this.getColumnComponent(column), DefaultStyleType.COLUMN, null,
				null);
		designComponent.setUniqueName("column_" + column.getName());
		
		final List<DRIConditionalStyle> rowHighlighters = new ArrayList<>();
		rowHighlighters.addAll(this.getDetailRowHighlighters());
		final DRISimpleStyle detailOddRowStyle = this.accessor.getTemplateTransform().getDetailOddRowStyle();
		if(detailOddRowStyle != null)
		{
			rowHighlighters.add(this.detailRowConditionalStyle(detailOddRowStyle, Expressions.printInOddRow()));
		}
		final DRISimpleStyle detailEvenRowStyle = this.accessor.getTemplateTransform().getDetailEvenRowStyle();
		if(detailEvenRowStyle != null)
		{
			rowHighlighters.add(this.detailRowConditionalStyle(detailEvenRowStyle, Expressions.printInEvenRow()));
		}
		if(!rowHighlighters.isEmpty())
		{
			DRIReportStyle style = this.getColumnComponent(column).getStyle();
			if(style == null)
			{
				style = this.accessor.getTemplateTransform().getColumnStyle(column instanceof DRIValueColumn<?>);
			}
			final DRStyle newStyle = new DRStyle();
			newStyle.setParentStyle(style);
			if(!(column instanceof DRIValueColumn<?>))
			{
				newStyle.setPadding(new DRPadding(0));
			}
			final List<DRIConditionalStyle> conditionalStyles = new ArrayList<>();
			if(style != null)
			{
				final DRIStyle stl = this.accessor.getStyleTransform().getStyle(style);
				for(final DRIConditionalStyle conditionalStyle : stl.getConditionalStyles())
				{
					conditionalStyles.add(conditionalStyle);
				}
			}
			for(final DRIConditionalStyle conditionalStyle : rowHighlighters)
			{
				conditionalStyles.add(conditionalStyle);
			}
			final Color backgroundColor = StyleResolver.getBackgroundColor(style, this.accessor.getStyleTransform());
			for(final DRIConditionalStyle conditionalStyle : conditionalStyles)
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
			designComponent.setStyle(this.accessor.getStyleTransform()
				.transformStyle(newStyle, true, DefaultStyleType.COLUMN));
		}
		else
		{
			if(designComponent.getStyle() == null && !(column instanceof DRIValueColumn<?>))
			{
				final DRIReportStyle columnStyle = this.accessor.getTemplateTransform().getColumnStyle(false);
				DRStyle newStyle = null;
				if(columnStyle != null)
				{
					newStyle = new DRStyle();
					newStyle.setParentStyle(columnStyle);
					newStyle.setPadding(new DRPadding(0));
				}
				final DRDesignStyle designColumnStyle =
					this.accessor.getStyleTransform().transformStyle(newStyle, false, DefaultStyleType.NONE);
				designComponent.setStyle(designColumnStyle);
			}
		}
		
		return designComponent;
	}
	
	private List<? extends DRIConditionalStyle> getDetailRowHighlighters()
	{
		return this.accessor.getReport().getDetailRowHighlighters();
	}
	
	private DRConditionalStyle detailRowConditionalStyle(
		final DRISimpleStyle style,
		final DRIExpression<Boolean> expression)
	{
		final DRConditionalStyle conditionalStyle = new DRConditionalStyle(expression);
		this.accessor.getStyleTransform().copyStyle(conditionalStyle, style);
		return conditionalStyle;
	}
	
	public DRIComponent getColumnComponent(final DRIColumn<?> column)
	{
		return this.columnComponents.get(column);
	}
}
