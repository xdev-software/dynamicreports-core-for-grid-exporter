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
package net.sf.dynamicreports.jasper.base.export;

import net.sf.dynamicreports.jasper.definition.export.JasperIDocxExporter;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>JasperDocxExporter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperDocxExporter extends AbstractJasperExporter implements JasperIDocxExporter {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Boolean framesAsNestedTables;
    private Boolean flexibleRowHeight;
    private Boolean ignoreHyperLink;

    /** {@inheritDoc} */
    @Override
    public Boolean getFramesAsNestedTables() {
        return framesAsNestedTables;
    }

    /**
     * <p>Setter for the field <code>framesAsNestedTables</code>.</p>
     *
     * @param framesAsNestedTables a {@link java.lang.Boolean} object.
     */
    public void setFramesAsNestedTables(Boolean framesAsNestedTables) {
        this.framesAsNestedTables = framesAsNestedTables;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getFlexibleRowHeight() {
        return flexibleRowHeight;
    }

    /**
     * <p>Setter for the field <code>flexibleRowHeight</code>.</p>
     *
     * @param flexibleRowHeight a {@link java.lang.Boolean} object.
     */
    public void setFlexibleRowHeight(Boolean flexibleRowHeight) {
        this.flexibleRowHeight = flexibleRowHeight;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getIgnoreHyperLink() {
        return ignoreHyperLink;
    }

    /**
     * <p>Setter for the field <code>ignoreHyperLink</code>.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     */
    public void setIgnoreHyperLink(Boolean ignoreHyperLink) {
        this.ignoreHyperLink = ignoreHyperLink;
    }

}
