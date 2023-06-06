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
package software.xdev.dynamicreports.report.base.component;

import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.definition.component.DRIImage;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>DRImage class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRImage extends DRHyperLinkComponent implements DRIImage {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private ImageScale imageScale;
    private DRIExpression<?> imageExpression;
    private Boolean usingCache;
    private Boolean lazy;
    private HorizontalImageAlignment horizontalImageAlignment;

    /** {@inheritDoc} */
    @Override
    public DRIExpression<?> getImageExpression() {
        return imageExpression;
    }

    /**
     * <p>Setter for the field <code>imageExpression</code>.</p>
     *
     * @param imageExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public void setImageExpression(DRIExpression<?> imageExpression) {
        Validate.notNull(imageExpression, "imageExpression must not be null");
        this.imageExpression = imageExpression;
    }

    /** {@inheritDoc} */
    @Override
    public ImageScale getImageScale() {
        return imageScale;
    }

    /**
     * <p>Setter for the field <code>imageScale</code>.</p>
     *
     * @param imageScale a {@link software.xdev.dynamicreports.report.constant.ImageScale} object.
     */
    public void setImageScale(ImageScale imageScale) {
        this.imageScale = imageScale;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getUsingCache() {
        return usingCache;
    }

    /**
     * <p>Setter for the field <code>usingCache</code>.</p>
     *
     * @param usingCache a {@link java.lang.Boolean} object.
     */
    public void setUsingCache(Boolean usingCache) {
        this.usingCache = usingCache;
    }

    /** {@inheritDoc} */
    @Override
    public Boolean getLazy() {
        return lazy;
    }

    /**
     * <p>Setter for the field <code>lazy</code>.</p>
     *
     * @param lazy a {@link java.lang.Boolean} object.
     */
    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    /** {@inheritDoc} */
    @Override
    public HorizontalImageAlignment getHorizontalImageAlignment() {
        return horizontalImageAlignment;
    }

    /**
     * <p>Setter for the field <code>horizontalImageAlignment</code>.</p>
     *
     * @param horizontalImageAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalImageAlignment} object.
     */
    public void setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
        this.horizontalImageAlignment = horizontalImageAlignment;
    }
}
