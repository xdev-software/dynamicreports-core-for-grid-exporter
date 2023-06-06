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

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsHeading;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRTableOfContentsHeading class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRTableOfContentsHeading implements DRITableOfContentsHeading {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRTableOfContentsHeading parentHeading;
    private DRIExpression<String> labelExpression;
    private DRIExpression<?> customValueExpression;

    /** {@inheritDoc} */
    @Override
    public DRTableOfContentsHeading getParentHeading() {
        return parentHeading;
    }

    /**
     * <p>Setter for the field <code>parentHeading</code>.</p>
     *
     * @param parentHeading a {@link software.xdev.dynamicreports.report.base.DRTableOfContentsHeading} object.
     */
    public void setParentHeading(DRTableOfContentsHeading parentHeading) {
        this.parentHeading = parentHeading;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<String> getLabelExpression() {
        return labelExpression;
    }

    /**
     * <p>Setter for the field <code>labelExpression</code>.</p>
     *
     * @param labelExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setLabelExpression(DRIExpression<String> labelExpression) {
        this.labelExpression = labelExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<?> getCustomValueExpression() {
        return customValueExpression;
    }

    /**
     * <p>Setter for the field <code>customValueExpression</code>.</p>
     *
     * @param customValueExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setCustomValueExpression(DRIExpression<?> customValueExpression) {
        this.customValueExpression = customValueExpression;
    }

}
