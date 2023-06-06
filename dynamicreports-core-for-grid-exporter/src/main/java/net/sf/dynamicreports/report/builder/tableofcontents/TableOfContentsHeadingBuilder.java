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
package net.sf.dynamicreports.report.builder.tableofcontents;

import net.sf.dynamicreports.report.base.DRTableOfContentsHeading;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>TableOfContentsHeadingBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TableOfContentsHeadingBuilder extends AbstractBuilder<TableOfContentsHeadingBuilder, DRTableOfContentsHeading> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for TableOfContentsHeadingBuilder.</p>
     */
    public TableOfContentsHeadingBuilder() {
        super(new DRTableOfContentsHeading());
    }

    /**
     * <p>setParentHeading.</p>
     *
     * @param parentHeading a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     * @return a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setParentHeading(TableOfContentsHeadingBuilder parentHeading) {
        if (parentHeading != null) {
            getObject().setParentHeading(parentHeading.build());
        } else {
            getObject().setParentHeading(null);
        }
        return this;
    }

    /**
     * <p>setLabel.</p>
     *
     * @param label a {@link java.lang.String} object.
     * @return a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setLabel(String label) {
        this.getObject().setLabelExpression(Expressions.text(label));
        return this;
    }

    /**
     * <p>setLabel.</p>
     *
     * @param labelExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setLabel(DRIExpression<String> labelExpression) {
        this.getObject().setLabelExpression(labelExpression);
        return this;
    }

    /**
     * <p>setCustomValue.</p>
     *
     * @param customValue a {@link java.lang.Object} object.
     * @return a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setCustomValue(Object customValue) {
        this.getObject().setCustomValueExpression(Expressions.value(customValue));
        return this;
    }

    /**
     * <p>setCustomValue.</p>
     *
     * @param customValueExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsHeadingBuilder} object.
     */
    public TableOfContentsHeadingBuilder setCustomValue(DRIExpression<?> customValueExpression) {
        this.getObject().setCustomValueExpression(customValueExpression);
        return this;
    }

    /**
     * <p>getTableOfContentsHeading.</p>
     *
     * @return a {@link net.sf.dynamicreports.report.base.DRTableOfContentsHeading} object.
     */
    public DRTableOfContentsHeading getTableOfContentsHeading() {
        return build();
    }
}
