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
package software.xdev.dynamicreports.design.definition.style;

import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.ImageScale;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.Rotation;
import software.xdev.dynamicreports.report.constant.VerticalImageAlignment;
import software.xdev.dynamicreports.report.constant.VerticalTextAlignment;

import java.awt.Color;
import java.io.Serializable;

/**
 * <p>DRIDesignBaseStyle interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIDesignBaseStyle extends Serializable {

    /**
     * <p>getForegroundColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getForegroundColor();

    /**
     * <p>getBackgroundColor.</p>
     *
     * @return a {@link java.awt.Color} object.
     */
    public Color getBackgroundColor();

    /**
     * <p>getRadius.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getRadius();

    /**
     * <p>getImageScale.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.ImageScale} object.
     */
    public ImageScale getImageScale();

    /**
     * <p>getHorizontalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     */
    public HorizontalTextAlignment getHorizontalTextAlignment();

    /**
     * <p>getVerticalTextAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.VerticalTextAlignment} object.
     */
    public VerticalTextAlignment getVerticalTextAlignment();

    /**
     * <p>getHorizontalImageAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.HorizontalImageAlignment} object.
     */
    public HorizontalImageAlignment getHorizontalImageAlignment();

    /**
     * <p>getVerticalImageAlignment.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.VerticalImageAlignment} object.
     */
    public VerticalImageAlignment getVerticalImageAlignment();

    /**
     * <p>getBorder.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.style.DRIDesignBorder} object.
     */
    public DRIDesignBorder getBorder();

    /**
     * <p>getPadding.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.style.DRIDesignPadding} object.
     */
    public DRIDesignPadding getPadding();

    /**
     * <p>getFont.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.style.DRIDesignFont} object.
     */
    public DRIDesignFont getFont();

    /**
     * <p>getRotation.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Rotation} object.
     */
    public Rotation getRotation();

    /**
     * <p>getPattern.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPattern();

    /**
     * <p>getMarkup.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Markup} object.
     */
    public Markup getMarkup();

    /**
     * <p>getParagraph.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.style.DRIDesignParagraph} object.
     */
    public DRIDesignParagraph getParagraph();

    /**
     * <p>getLinePen.</p>
     *
     * @return a {@link software.xdev.dynamicreports.design.definition.style.DRIDesignPen} object.
     */
    public DRIDesignPen getLinePen();

}
