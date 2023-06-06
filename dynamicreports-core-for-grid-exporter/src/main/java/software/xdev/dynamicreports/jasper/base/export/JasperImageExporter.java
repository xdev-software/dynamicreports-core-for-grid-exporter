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
package software.xdev.dynamicreports.jasper.base.export;

import software.xdev.dynamicreports.jasper.constant.ImageType;
import software.xdev.dynamicreports.jasper.definition.export.JasperIImageExporter;
import software.xdev.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.Validate;

/**
 * <p>JasperImageExporter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperImageExporter extends AbstractJasperExporter implements JasperIImageExporter {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Integer pageGap;
    private ImageType imageType;
    private Float zoomRatio;

    /** {@inheritDoc} */
    @Override
    public Integer getPageGap() {
        return pageGap;
    }

    /**
     * <p>Setter for the field <code>pageGap</code>.</p>
     *
     * @param pageGap a {@link java.lang.Integer} object.
     */
    public void setPageGap(Integer pageGap) {
        this.pageGap = pageGap;
    }

    /** {@inheritDoc} */
    @Override
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * <p>Setter for the field <code>imageType</code>.</p>
     *
     * @param imageType a {@link software.xdev.dynamicreports.jasper.constant.ImageType} object.
     */
    public void setImageType(ImageType imageType) {
        Validate.notNull(imageType, "imageType must not be null");
        this.imageType = imageType;
    }

    /** {@inheritDoc} */
    @Override
    public Float getZoomRatio() {
        return zoomRatio;
    }

    /**
     * <p>Setter for the field <code>zoomRatio</code>.</p>
     *
     * @param zoomRatio a {@link java.lang.Float} object.
     */
    public void setZoomRatio(Float zoomRatio) {
        this.zoomRatio = zoomRatio;
    }
}
