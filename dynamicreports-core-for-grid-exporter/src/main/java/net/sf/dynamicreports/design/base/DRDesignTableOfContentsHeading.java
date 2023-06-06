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
package net.sf.dynamicreports.design.base;

import net.sf.dynamicreports.design.base.component.DRDesignTextField;
import net.sf.dynamicreports.design.definition.DRIDesignTableOfContentsHeading;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignTableOfContentsHeading class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignTableOfContentsHeading implements DRIDesignTableOfContentsHeading {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private DRDesignTextField referenceField;

    /** {@inheritDoc} */
    @Override
    public DRDesignTextField getReferenceField() {
        return referenceField;
    }

    /**
     * <p>Setter for the field <code>referenceField</code>.</p>
     *
     * @param referenceField a {@link net.sf.dynamicreports.design.base.component.DRDesignTextField} object.
     */
    public void setReferenceField(DRDesignTextField referenceField) {
        this.referenceField = referenceField;
    }

}
