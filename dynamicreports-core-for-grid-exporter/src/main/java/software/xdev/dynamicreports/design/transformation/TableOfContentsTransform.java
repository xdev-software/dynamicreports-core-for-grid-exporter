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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>TableOfContentsTransform class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TableOfContentsTransform {
    private DesignTransformAccessor accessor;
    private Map<DRITableOfContentsHeading, Integer> levels;

    /**
     * <p>Constructor for TableOfContentsTransform.</p>
     *
     * @param accessor a {@link software.xdev.dynamicreports.design.transformation.DesignTransformAccessor} object.
     */
    public TableOfContentsTransform(DesignTransformAccessor accessor) {
        this.accessor = accessor;
        this.levels = new HashMap<DRITableOfContentsHeading, Integer>();
    }

    /**
     * <p>componentHeading.</p>
     *
     * @param component a {@link software.xdev.dynamicreports.report.definition.component.DRIComponent} object.
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignTableOfContentsHeading componentHeading(DRIComponent component) throws DRException {
        DRITableOfContentsHeading tocHeading = component.getTableOfContentsHeading();
        boolean tableOfContents = accessor.isTableOfContents();
        if (tableOfContents && tocHeading != null) {
            DRTextField<String> referenceField = new DRTextField<String>();
            int level = getLevel(tocHeading);
            DRIExpression<?> labelExpression = tocHeading.getLabelExpression();
            if (labelExpression == null && component instanceof DRITextField) {
                labelExpression = ((DRITextField<?>) component).getValueExpression();
            }
            if (labelExpression == null) {
                labelExpression = Expressions.text("");
            }
            String expressionName = labelExpression.getName();
            DRIExpression<String> anchorNameExpression = null;
            if (component instanceof DRIHyperLinkComponent) {
                anchorNameExpression = ((DRIHyperLinkComponent) component).getAnchorNameExpression();
            }
            DRIExpression<?> customValueExpression = tocHeading.getCustomValueExpression();
            referenceField.setValueExpression(new TocReferenceExpression(level, expressionName, labelExpression, anchorNameExpression, customValueExpression));
            referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(expressionName, anchorNameExpression));
            referenceField.setPrintWhenExpression(component.getPrintWhenExpression());
            DRDesignTextField designReferenceField = accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
            designReferenceField.setWidth(1);
            designReferenceField.setHeight(1);
            designReferenceField.setUniqueName(expressionName + ".tocReference");

            DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
            designTocHeading.setReferenceField(designReferenceField);
            return designTocHeading;
        }

        return null;
    }

    private int getLevel(DRITableOfContentsHeading tocHeading) {
        if (levels.containsKey(tocHeading)) {
            return levels.get(tocHeading);
        }
        int level = 0;
        if (tocHeading.getParentHeading() != null) {
            level = getLevel(tocHeading.getParentHeading()) + 1;
        }
        levels.put(tocHeading, level);
        return level;
    }

    /**
     * <p>groupHeading.</p>
     *
     * @param group a {@link software.xdev.dynamicreports.report.definition.DRIGroup} object.
     * @param level a int.
     * @return a {@link software.xdev.dynamicreports.design.base.DRDesignTableOfContentsHeading} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    protected DRDesignTableOfContentsHeading groupHeading(DRIGroup group, int level) throws DRException {
        boolean tableOfContents = accessor.isTableOfContents();
        boolean isAddGroupToTableOfContents = accessor.getTemplateTransform().isAddGroupToTableOfContents(group);
        if (tableOfContents && isAddGroupToTableOfContents) {
            DRTextField<String> referenceField = new DRTextField<String>();
            DRITextField<?> valueField = group.getValueField();
            referenceField.setValueExpression(new TocReferenceExpression(level, group.getName(), valueField.getValueExpression(), valueField.getAnchorNameExpression(), null));
            referenceField.setAnchorNameExpression(new TocReferenceLinkExpression(group.getName(), valueField.getAnchorNameExpression()));
            referenceField.setPrintWhenExpression(new TocPrintWhenExpression(valueField.getValueExpression()));
            DRDesignTextField designReferenceField = accessor.getComponentTransform().textField(referenceField, DefaultStyleType.TEXT);
            designReferenceField.setWidth(0);
            designReferenceField.setHeight(0);
            designReferenceField.setUniqueName("group_" + group.getName() + ".tocReference");

            DRDesignTableOfContentsHeading designTocHeading = new DRDesignTableOfContentsHeading();
            designTocHeading.setReferenceField(designReferenceField);
            return designTocHeading;
        }

        return null;
    }

}
