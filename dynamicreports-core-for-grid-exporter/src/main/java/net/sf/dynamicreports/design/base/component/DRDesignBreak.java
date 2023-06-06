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
package net.sf.dynamicreports.design.base.component;

import net.sf.dynamicreports.design.definition.component.DRIDesignBreak;
import net.sf.dynamicreports.report.constant.BreakType;
import net.sf.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignBreak class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignBreak extends DRDesignComponent implements DRIDesignBreak {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private BreakType breakType;

    /**
     * <p>Constructor for DRDesignBreak.</p>
     */
    public DRDesignBreak() {
        super("break");
    }

    /** {@inheritDoc} */
    @Override
    public BreakType getType() {
        return breakType;
    }

    /**
     * <p>setType.</p>
     *
     * @param breakType a {@link net.sf.dynamicreports.report.constant.BreakType} object.
     */
    public void setType(BreakType breakType) {
        this.breakType = breakType;
    }
}
