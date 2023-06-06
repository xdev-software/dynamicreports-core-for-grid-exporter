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
package software.xdev.dynamicreports.report.builder.component;

import software.xdev.dynamicreports.report.base.component.DRMap;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>MapBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MapBuilder extends DimensionComponentBuilder<MapBuilder, DRMap> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for MapBuilder.</p>
     */
    protected MapBuilder() {
        super(new DRMap());
    }

    /**
     * <p>setLatitude.</p>
     *
     * @param latitude a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLatitude(Float latitude) {
        getObject().setLatitudeExpression(Expressions.value(latitude));
        return this;
    }

    /**
     * <p>setLatitude.</p>
     *
     * @param latitudeExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLatitude(DRIExpression<Float> latitudeExpression) {
        getObject().setLatitudeExpression(latitudeExpression);
        return this;
    }

    /**
     * <p>setLongitude.</p>
     *
     * @param longitude a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLongitude(Float longitude) {
        getObject().setLongitudeExpression(Expressions.value(longitude));
        return this;
    }

    /**
     * <p>setLongitude.</p>
     *
     * @param longitudeExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setLongitude(DRIExpression<Float> longitudeExpression) {
        getObject().setLongitudeExpression(longitudeExpression);
        return this;
    }

    /**
     * <p>setZoom.</p>
     *
     * @param zoom a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setZoom(Integer zoom) {
        getObject().setZoomExpression(Expressions.value(zoom));
        return this;
    }

    /**
     * <p>setZoom.</p>
     *
     * @param zoomExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.MapBuilder} object.
     */
    public MapBuilder setZoom(DRIExpression<Integer> zoomExpression) {
        getObject().setZoomExpression(zoomExpression);
        return this;
    }
}
