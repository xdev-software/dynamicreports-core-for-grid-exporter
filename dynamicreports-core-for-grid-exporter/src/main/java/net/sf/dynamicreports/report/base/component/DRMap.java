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
package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.component.DRIMap;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>DRMap class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRMap extends DRDimensionComponent implements DRIMap {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    public DRIExpression<Float> latitudeExpression;
    public DRIExpression<Float> longitudeExpression;
    public DRIExpression<Integer> zoomExpression;

    /** {@inheritDoc} */
    @Override
    public DRIExpression<Float> getLatitudeExpression() {
        return latitudeExpression;
    }

    /**
     * <p>Setter for the field <code>latitudeExpression</code>.</p>
     *
     * @param latitudeExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setLatitudeExpression(DRIExpression<Float> latitudeExpression) {
        this.latitudeExpression = latitudeExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<Float> getLongitudeExpression() {
        return longitudeExpression;
    }

    /**
     * <p>Setter for the field <code>longitudeExpression</code>.</p>
     *
     * @param longitudeExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setLongitudeExpression(DRIExpression<Float> longitudeExpression) {
        this.longitudeExpression = longitudeExpression;
    }

    /** {@inheritDoc} */
    @Override
    public DRIExpression<Integer> getZoomExpression() {
        return zoomExpression;
    }

    /**
     * <p>Setter for the field <code>zoomExpression</code>.</p>
     *
     * @param zoomExpression a {@link net.sf.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setZoomExpression(DRIExpression<Integer> zoomExpression) {
        this.zoomExpression = zoomExpression;
    }
}
