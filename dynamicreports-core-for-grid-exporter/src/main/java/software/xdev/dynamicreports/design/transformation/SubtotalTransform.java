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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import software.xdev.dynamicreports.design.base.DRDesignBand;
import software.xdev.dynamicreports.design.base.DRDesignGroup;
import software.xdev.dynamicreports.design.base.component.DRDesignComponent;
import software.xdev.dynamicreports.design.base.component.DRDesignList;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.constant.EvaluationTime;
import software.xdev.dynamicreports.design.exception.DRDesignReportException;
import software.xdev.dynamicreports.report.base.component.DRFiller;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.constant.HorizontalCellComponentAlignment;
import software.xdev.dynamicreports.report.constant.ListType;
import software.xdev.dynamicreports.report.constant.Position;
import software.xdev.dynamicreports.report.constant.SubtotalPosition;
import software.xdev.dynamicreports.report.constant.VerticalCellComponentAlignment;
import software.xdev.dynamicreports.report.definition.DRIBand;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRISubtotal;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


public class SubtotalTransform
{
	private final DesignTransformAccessor accessor;
	
	public SubtotalTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
	}
	
	// subtotals
	
	@SuppressWarnings("checkstyle:MethodLength")
	public void transform() throws DRException
	{
		final ColumnGrid title = this.accessor.getColumnGridTransform().createColumnGrid();
		final ColumnGrid pageHeader = this.accessor.getColumnGridTransform().createColumnGrid();
		final ColumnGrid pageFooter = this.accessor.getColumnGridTransform().createColumnGrid();
		final ColumnGrid columnHeader = this.accessor.getColumnGridTransform().createColumnGrid();
		final ColumnGrid columnFooter = this.accessor.getColumnGridTransform().createColumnGrid();
		final Map<DRIGroup, ColumnGrid> groupHeader = new HashMap<>();
		final Map<DRIGroup, ColumnGrid> groupFooter = new HashMap<>();
		final ColumnGrid lastPageFooter = this.accessor.getColumnGridTransform().createColumnGrid();
		final ColumnGrid summary = this.accessor.getColumnGridTransform().createColumnGrid();
		
		final HorizontalCellComponentAlignment horizontalAlignment = HorizontalCellComponentAlignment.FLOAT;
		final VerticalCellComponentAlignment verticalAlignment = VerticalCellComponentAlignment.TOP;
		for(final DRISubtotal<?> subtotal : this.accessor.getReport().getSubtotals())
		{
			final SubtotalPosition position = subtotal.getPosition();
			final DRIColumn<?> showInColumn = subtotal.getShowInColumn();
			final DRDesignTextField subtotalValueComponent = this.valueComponent(subtotal);
			DRDesignComponent subtotalComponent = subtotalValueComponent;
			if(subtotal.getLabelExpression() != null)
			{
				subtotalComponent = this.subtotalWithLabelComponent(subtotal, subtotalComponent);
			}
			switch(position)
			{
				case TITLE:
					title.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				case PAGE_HEADER:
					pageHeader.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				case PAGE_FOOTER:
					pageFooter.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				case COLUMN_HEADER:
					columnHeader.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				case COLUMN_FOOTER:
					columnFooter.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				case GROUP_HEADER:
					EvaluationTime evaluationTime = this.accessor.getComponentTransform()
						.detectEvaluationTime(subtotalValueComponent.getValueExpression());
					if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
					{
						subtotalValueComponent.setEvaluationTime(EvaluationTime.GROUP);
						subtotalValueComponent.setEvaluationGroup(this.accessor.getGroupTransform()
							.getGroup(subtotal.getGroup()));
					}
					this.getGroupGrid(subtotal.getGroup(), groupHeader).addComponent(
						showInColumn,
						horizontalAlignment,
						verticalAlignment,
						subtotalComponent);
					break;
				case GROUP_FOOTER:
					evaluationTime = this.accessor.getComponentTransform()
						.detectEvaluationTime(subtotalValueComponent.getValueExpression());
					if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
					{
						subtotalValueComponent.setEvaluationTime(EvaluationTime.NOW);
					}
					this.getGroupGrid(subtotal.getGroup(), groupFooter).addComponent(
						showInColumn,
						horizontalAlignment,
						verticalAlignment,
						subtotalComponent);
					break;
				case FIRST_GROUP_HEADER:
					DRIGroup firstGroup = this.accessor.getGroupTransform().getFirstGroup();
					evaluationTime = this.accessor.getComponentTransform()
						.detectEvaluationTime(subtotalValueComponent.getValueExpression());
					if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
					{
						subtotalValueComponent.setEvaluationTime(EvaluationTime.GROUP);
						subtotalValueComponent.setEvaluationGroup(this.accessor.getGroupTransform()
							.getGroup(firstGroup));
					}
					if(firstGroup != null)
					{
						this.getGroupGrid(firstGroup, groupHeader).addComponent(
							showInColumn,
							horizontalAlignment,
							verticalAlignment,
							subtotalComponent);
					}
					break;
				case FIRST_GROUP_FOOTER:
					firstGroup = this.accessor.getGroupTransform().getFirstGroup();
					if(firstGroup != null)
					{
						evaluationTime = this.accessor.getComponentTransform()
							.detectEvaluationTime(subtotalValueComponent.getValueExpression());
						if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
						{
							subtotalValueComponent.setEvaluationTime(EvaluationTime.NOW);
						}
						this.getGroupGrid(firstGroup, groupFooter).addComponent(
							showInColumn,
							horizontalAlignment,
							verticalAlignment,
							subtotalComponent);
					}
					break;
				case LAST_GROUP_HEADER:
					DRIGroup lastGroup = this.accessor.getGroupTransform().getLastGroup();
					evaluationTime = this.accessor.getComponentTransform()
						.detectEvaluationTime(subtotalValueComponent.getValueExpression());
					if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
					{
						subtotalValueComponent.setEvaluationTime(EvaluationTime.GROUP);
						subtotalValueComponent.setEvaluationGroup(this.accessor.getGroupTransform()
							.getGroup(lastGroup));
					}
					if(lastGroup != null)
					{
						this.getGroupGrid(lastGroup, groupHeader).addComponent(
							showInColumn,
							horizontalAlignment,
							verticalAlignment,
							subtotalComponent);
					}
					break;
				case LAST_GROUP_FOOTER:
					lastGroup = this.accessor.getGroupTransform().getLastGroup();
					if(lastGroup != null)
					{
						evaluationTime = this.accessor.getComponentTransform()
							.detectEvaluationTime(subtotalValueComponent.getValueExpression());
						if(evaluationTime == null || !evaluationTime.equals(EvaluationTime.AUTO))
						{
							subtotalValueComponent.setEvaluationTime(EvaluationTime.NOW);
						}
						this.getGroupGrid(lastGroup, groupFooter).addComponent(
							showInColumn,
							horizontalAlignment,
							verticalAlignment,
							subtotalComponent);
					}
					break;
				case LAST_PAGE_FOOTER:
					lastPageFooter.addComponent(
						showInColumn,
						horizontalAlignment,
						verticalAlignment,
						subtotalComponent);
					break;
				case SUMMARY:
					subtotalValueComponent.setEvaluationTime(EvaluationTime.NOW);
					summary.addComponent(showInColumn, horizontalAlignment, verticalAlignment, subtotalComponent);
					break;
				default:
					throw new DRDesignReportException("Subtotal position " + position.name() + " not supported");
			}
		}
		
		DRFiller filler = null;
		final TemplateTransform templateTransform = this.accessor.getTemplateTransform();
		if(templateTransform.getPageColumnsPerPage() > 1)
		{
			final int fillerWidth =
				this.accessor.getPageTransform().getMaxBandWidth() - this.accessor.getPageTransform()
					.getPage()
					.getColumnWidth();
			filler = new DRFiller();
			filler.setWidth(fillerWidth);
		}
		
		this.addAfterBandComponent(this.accessor.getBandTransform().getTitleBand(), title, filler);
		this.addAfterBandComponent(this.accessor.getBandTransform().getPageHeaderBand(), pageHeader, filler);
		this.addBeforeBandComponent(this.accessor.getBandTransform().getPageFooterBand(), pageFooter, filler);
		this.addAfterBandComponent(this.accessor.getBandTransform().getColumnHeaderBand(), columnHeader, null);
		this.addBeforeBandComponent(this.accessor.getBandTransform().getColumnFooterBand(), columnFooter, null);
		for(final Entry<DRIGroup, ColumnGrid> entry : groupHeader.entrySet())
		{
			final DRIGroup group = entry.getKey();
			final DRIBand bnd = group.getHeaderBand();
			final DRDesignGroup designGroup = this.accessor.getGroupTransform().getGroup(group);
			final DRDesignBand band = this.accessor.getBandTransform()
				.band(
					"subtotalGroupHeader",
					bnd,
					templateTransform.getGroupHeaderSplitType(bnd),
					templateTransform.getGroupHeaderStyle(bnd),
					templateTransform.getGroupHeaderBackgroundComponent(bnd));
			this.addAfterBandComponent(band, entry.getValue(), null);
			this.setPrintGroupSubtotalsWhenExpression(group, entry.getValue());
			designGroup.addHeaderBand(band);
		}
		for(final Entry<DRIGroup, ColumnGrid> entry : groupFooter.entrySet())
		{
			final DRIGroup group = entry.getKey();
			final DRIBand bnd = group.getFooterBand();
			final DRDesignGroup designGroup = this.accessor.getGroupTransform().getGroup(group);
			final DRDesignBand band = this.accessor.getBandTransform()
				.band(
					"subtotalGroupFooter",
					bnd,
					templateTransform.getGroupFooterSplitType(bnd),
					templateTransform.getGroupFooterStyle(bnd),
					templateTransform.getGroupFooterBackgroundComponent(bnd));
			this.addBeforeBandComponent(band, entry.getValue(), null);
			this.setPrintGroupSubtotalsWhenExpression(group, entry.getValue());
			designGroup.addFooterBand(0, band);
		}
		this.addBeforeBandComponent(this.accessor.getBandTransform().getLastPageFooterBand(), lastPageFooter, filler);
		this.addBeforeBandComponent(this.accessor.getBandTransform().getSummaryBand(), summary, filler);
	}
	
	private DRDesignComponent subtotalWithLabelComponent(
		final DRISubtotal<?> subtotal,
		final DRDesignComponent subtotalComponent)
		throws DRException
	{
		final HorizontalCellComponentAlignment horizontalAlignment = HorizontalCellComponentAlignment.FLOAT;
		final VerticalCellComponentAlignment verticalAlignment = VerticalCellComponentAlignment.TOP;
		final DRDesignList list = new DRDesignList();
		
		final Position labelPosition = this.accessor.getTemplateTransform().getSubtotalLabelPosition(subtotal);
		switch(labelPosition)
		{
			case TOP:
				list.setType(ListType.VERTICAL);
				list.addComponent(horizontalAlignment, verticalAlignment, this.labelComponent(subtotal));
				list.addComponent(horizontalAlignment, verticalAlignment, subtotalComponent);
				break;
			case BOTTOM:
				list.setType(ListType.VERTICAL);
				list.addComponent(horizontalAlignment, verticalAlignment, subtotalComponent);
				list.addComponent(horizontalAlignment, verticalAlignment, this.labelComponent(subtotal));
				break;
			case LEFT:
				list.setType(ListType.HORIZONTAL);
				DRDesignComponent labelComponent = this.labelComponent(subtotal);
				if(subtotal.getLabelWidth() != null)
				{
					labelComponent.setWidth(subtotal.getLabelWidth());
				}
				HorizontalCellComponentAlignment labelHorizontalAlignment = horizontalAlignment;
				if(subtotal.getLabelWidthType() != null)
				{
					labelHorizontalAlignment =
						ConstantTransform.toHorizontalCellComponentAlignment(subtotal.getLabelWidthType());
				}
				list.addComponent(labelHorizontalAlignment, VerticalCellComponentAlignment.EXPAND, labelComponent);
				list.addComponent(horizontalAlignment, VerticalCellComponentAlignment.EXPAND, subtotalComponent);
				break;
			case RIGHT:
				list.setType(ListType.HORIZONTAL);
				labelComponent = this.labelComponent(subtotal);
				if(subtotal.getLabelWidth() != null)
				{
					labelComponent.setWidth(subtotal.getLabelWidth());
				}
				labelHorizontalAlignment = horizontalAlignment;
				if(subtotal.getLabelWidthType() != null)
				{
					labelHorizontalAlignment =
						ConstantTransform.toHorizontalCellComponentAlignment(subtotal.getLabelWidthType());
				}
				list.addComponent(horizontalAlignment, VerticalCellComponentAlignment.EXPAND, subtotalComponent);
				list.addComponent(labelHorizontalAlignment, VerticalCellComponentAlignment.EXPAND, labelComponent);
				break;
			default:
				throw new DRDesignReportException("Subtotal label position " + labelPosition.name() + " not "
					+ "supported");
		}
		
		return list;
	}
	
	private ColumnGrid getGroupGrid(final DRIGroup group, final Map<DRIGroup, ColumnGrid> groupList) throws DRException
	{
		if(!groupList.containsKey(group))
		{
			groupList.put(group, this.accessor.getColumnGridTransform().createColumnGrid());
		}
		return groupList.get(group);
	}
	
	private void setPrintGroupSubtotalsWhenExpression(final DRIGroup group, final ColumnGrid grid) throws DRException
	{
		final DRIExpression<Boolean> printSubtotalsWhenExpression = group.getPrintSubtotalsWhenExpression();
		if(grid.isEmpty() || printSubtotalsWhenExpression == null)
		{
			return;
		}
		grid.getList()
			.setPrintWhenExpression(this.accessor.getExpressionTransform()
				.transformExpression(printSubtotalsWhenExpression));
	}
	
	private void addAfterBandComponent(final DRDesignBand band, final ColumnGrid grid, final DRFiller filler)
		throws DRException
	{
		if(grid.isEmpty())
		{
			return;
		}
		DRDesignList list = grid.getList();
		if(filler != null)
		{
			list = new DRDesignList();
			list.addComponent(grid.getList());
			list.addComponent(
				HorizontalCellComponentAlignment.LEFT,
				null,
				this.accessor.getComponentTransform().filler(filler));
		}
		
		band.addComponent(list);
	}
	
	private void addBeforeBandComponent(final DRDesignBand band, final ColumnGrid grid, final DRFiller filler)
		throws DRException
	{
		if(grid.isEmpty())
		{
			return;
		}
		DRDesignList list = grid.getList();
		if(filler != null)
		{
			list = new DRDesignList();
			list.addComponent(grid.getList());
			list.addComponent(
				HorizontalCellComponentAlignment.LEFT,
				null,
				this.accessor.getComponentTransform().filler(filler));
		}
		band.addComponent(0, list);
	}
	
	// label
	@SuppressWarnings("unchecked")
	private DRDesignComponent labelComponent(final DRISubtotal<?> subtotal) throws DRException
	{
		@SuppressWarnings("rawtypes")
		final DRTextField labelField = new DRTextField();
		labelField.setValueExpression(subtotal.getLabelExpression());
		labelField.setStyle(subtotal.getLabelStyle());
		labelField.setWidth(this.accessor.getTemplateTransform()
			.getColumnWidth(
				subtotal.getShowInColumn(),
				this.accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		final DRDesignTextField designLabelField =
			this.accessor.getComponentTransform().textField(labelField, DefaultStyleType.TEXT);
		designLabelField.setUniqueName("column_" + subtotal.getShowInColumn().getName() + ".subtotal.label");
		return designLabelField;
	}
	
	// value
	private DRDesignTextField valueComponent(final DRISubtotal<?> subtotal) throws DRException
	{
		final DRDesignTextField designValueField =
			this.accessor.getComponentTransform().textField(subtotal.getValueField(), DefaultStyleType.SUBTOTAL);
		designValueField.setUniqueName("column_" + subtotal.getShowInColumn().getName() + ".subtotal");
		designValueField.setWidth(this.accessor.getTemplateTransform()
			.getColumnWidth(
				subtotal.getShowInColumn(),
				this.accessor.getStyleTransform().getDefaultStyle(DefaultStyleType.COLUMN)));
		return designValueField;
	}
}
