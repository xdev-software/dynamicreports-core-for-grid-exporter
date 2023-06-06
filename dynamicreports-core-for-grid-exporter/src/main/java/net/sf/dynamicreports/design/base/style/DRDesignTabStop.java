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
package net.sf.dynamicreports.design.base.style;

import net.sf.dynamicreports.design.definition.style.DRIDesignTabStop;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.TabStopAlignment;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * <p>DRDesignTabStop class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignTabStop implements DRIDesignTabStop {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int position;
    private TabStopAlignment alignment;

    /** {@inheritDoc} */
    @Override
    public int getPosition() {
        return position;
    }

    /**
     * <p>Setter for the field <code>position</code>.</p>
     *
     * @param position a int.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /** {@inheritDoc} */
    @Override
    public TabStopAlignment getAlignment() {
        return alignment;
    }

    /**
     * <p>Setter for the field <code>alignment</code>.</p>
     *
     * @param alignment a {@link net.sf.dynamicreports.report.constant.TabStopAlignment} object.
     */
    public void setAlignment(TabStopAlignment alignment) {
        this.alignment = alignment;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        DRDesignTabStop o = (DRDesignTabStop) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder().append(position, o.position).append(alignment, o.alignment);
        return equalsBuilder.isEquals();
    }
}
