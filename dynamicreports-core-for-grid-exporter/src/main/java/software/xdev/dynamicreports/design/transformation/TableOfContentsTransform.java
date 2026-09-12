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

import software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading;
import software.xdev.dynamicreports.design.base.component.DRDesignTextField;
import software.xdev.dynamicreports.design.constant.DefaultStyleType;
import software.xdev.dynamicreports.design.transformation.expressions.TocPrintWhenExpression;
import software.xdev.dynamicreports.design.transformation.expressions.TocReferenceExpression;
import software.xdev.dynamicreports.design.transformation.expressions.TocReferenceLinkExpression;
import software.xdev.dynamicreports.report.base.component.DRTextField;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsHeading;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.component.DRIHyperLinkComponent;
import software.xdev.dynamicreports.report.definition.component.DRITextField;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.exception.DRException;


public class TableOfContentsTransform
{
	private final DesignTransformAccessor accessor;
	private final Map<DRITableOfContentsHeading, Integer> levels;
	
	public TableOfContentsTransform(final DesignTransformAccessor accessor)
	{
		this.accessor = accessor;
		this.levels = new HashMap<>();
	}
	
	protected DRDesignTableOfContentsHeading componentHeading(final DRIComponent component) throws DRException
	{
		final DRITableOfContentsHeading tocHeading = component.getTableOfContentsHeading();
		final boolean tableOfContents = this.accessor.isTableOfContents();
		if(tableOfContents && tocHeading != null)
		{
			final DRTextField<String> referenceField = new DRTextField<>();
			final int level = this.getLevel(tocHeading);
			DRIExpression<?> labelExpression = tocHeading.getLabelExpression();
			if(labelExpression == null && component instanceof DRITextField)
			{
				labelExpression = ((DRITextField<?>)component).getValueExpression();
			}
			if(labelExpression == null)
			{
				labelExpression = Expressions.text("");
			}
			final String expressionName = labelExpression.getName();
			DRIExpression<String> anchorNameExpression = null;
			if(component instanceof DRIHyperLinkComponent)
			{
				anchorNameExpression = ((DRIHyperLinkComponent)component).getAnchorNameExpression();
			}
			final DRIExpression<?> customValueExpression = tocHeading.getCustomValueExpression();
			referenceField.setValueExpression(new TocReferenceExpression(
				level,
				expressionName,
				labelExpression,
				anchorNameExpression,
				customValueExpression));
			referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(
				expressionName,
				anchorNameExpression));
			referenceField.setPrintWhenExpression(component.getPrintWhenExpression());
			final DRDesignTextField designReferenceField =
				this.accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
			designReferenceField.setWidth(1);
			designReferenceField.setHeight(1);
			designReferenceField.setUniqueName(expressionName + ".tocReference");
			
			final DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
			designTocHeading.setReferenceField(designReferenceField);
			return designTocHeading;
		}
		
		return null;
	}
	
	private int getLevel(final DRITableOfContentsHeading tocHeading)
	{
		if(this.levels.containsKey(tocHeading))
		{
			return this.levels.get(tocHeading);
		}
		int level = 0;
		if(tocHeading.getParentHeading() != null)
		{
			level = this.getLevel(tocHeading.getParentHeading()) + 1;
		}
		this.levels.put(tocHeading, level);
		return level;
	}
	
	protected DRDesignTableOfContentsHeading groupHeading(final DRIGroup group, final int level) throws DRException
	{
		final boolean tableOfContents = this.accessor.isTableOfContents();
		final boolean isAddGroupToTableOfContents =
			this.accessor.getTemplateTransform().isAddGroupToTableOfContents(group);
		if(tableOfContents && isAddGroupToTableOfContents)
		{
			final DRTextField<String> referenceField = new DRTextField<>();
			final DRITextField<?> valueField = group.getValueField();
			referenceField.setValueExpression(new TocReferenceExpression(
				level,
				group.getName(),
				valueField.getValueExpression(),
				valueField.getAnchorNameExpression(),
				null));
			referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(
				group.getName(),
				valueField.getAnchorNameExpression()));
			referenceField.setPrintWhenExpression(new TocPrintWhenExpression(valueField.getValueExpression()));
			final DRDesignTextField designReferenceField =
				this.accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
			designReferenceField.setWidth(0);
			designReferenceField.setHeight(0);
			designReferenceField.setUniqueName("group_" + group.getName() + ".tocReference");
			
			final DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
			designTocHeading.setReferenceField(designReferenceField);
			return designTocHeading;
		}
		
		return null;
	}
}
