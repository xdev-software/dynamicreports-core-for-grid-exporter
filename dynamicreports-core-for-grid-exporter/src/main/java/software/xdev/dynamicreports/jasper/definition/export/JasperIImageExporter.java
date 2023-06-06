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
package software.xdev.dynamicreports.jasper.definition.export;

import software.xdev.dynamicreports.jasper.constant.ImageType;

/**
 * <p>JasperIImageExporter interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperIImageExporter extends JasperIExporter {

    /**
     * <p>getPageGap.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPageGap();

    /**
     * <p>getImageType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.jasper.constant.ImageType} object.
     */
    public ImageType getImageType();

    /**
     * <p>getZoomRatio.</p>
     *
     * @return a {@link java.lang.Float} object.
     */
    public Float getZoomRatio();
}
