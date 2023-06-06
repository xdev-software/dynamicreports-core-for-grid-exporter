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

import software.xdev.dynamicreports.report.base.component.DRBooleanField;
import software.xdev.dynamicreports.report.builder.FieldBuilder;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.BooleanComponentType;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalImageAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import org.apache.commons.lang3.Validate;

/**
 * <p>BooleanFieldBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
@SuppressWarnings("deprecation")
public class BooleanFieldBuilder extends HyperLinkComponentBuilder<BooleanFieldBuilder, DRBooleanField> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for BooleanFieldBuilder.</p>
     */
    protected BooleanFieldBuilder() {
        super(new DRBooleanField());
    }

    /**
     * <p>setValue.</p>
     *
     * @param field a {@link software.xdev.dynamicreports.report.builder.FieldBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(FieldBuilder<Boolean> field) {
        Validate.notNull(field, "field must not be null");
        getObject().setValueExpression(field.getField());
        return this;
    }

    /**
     * <p>setValue.</p>
     *
     * @param valueExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(DRIExpression<Boolean> valueExpression) {
        getObject().setValueExpression(valueExpression);
        return this;
    }

    /**
     * <p>setValue.</p>
     *
     * @param value a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setValue(Boolean value) {
        getObject().setValueExpression(Expressions.value(value));
        return this;
    }

    /**
     * <p>setComponentType.</p>
     *
     * @param booleanComponentType a {@link software.xdev.dynamicreports.report.constant.BooleanComponentType} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setComponentType(BooleanComponentType booleanComponentType) {
        getObject().setComponentType(booleanComponentType);
        return this;
    }

    /**
     * <p>setEmptyWhenNullValue.</p>
     *
     * @param emptyWhenNullValue a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setEmptyWhenNullValue(Boolean emptyWhenNullValue) {
        getObject().setEmptyWhenNullValue(emptyWhenNullValue);
        return this;
    }

    /**
     * Sets the boolean image dimension. Has effect only when the boolean value is presented as an image.
     *
     * @param width  the image width
     * @param height the image height
     * @return a column builder
     */
    public BooleanFieldBuilder setImageDimension(Integer width, Integer height) {
        getObject().setImageWidth(width);
        getObject().setImageHeight(height);
        return this;
    }

    /**
     * Sets the boolean image width. Has effect only when the boolean value is presented as an image.
     *
     * @param width the image width
     * @return a column builder
     */
    public BooleanFieldBuilder setImageWidth(Integer width) {
        getObject().setImageWidth(width);
        return this;
    }

    /**
     * Sets the boolean image height. Has effect only when the boolean value is presented as an image.
     *
     * @param height the image height
     * @return a column builder
     */
    public BooleanFieldBuilder setImageHeight(Integer height) {
        getObject().setImageHeight(height);
        return this;
    }

    /**
     * <p>setHorizontalImageAlignment.</p>
     *
     * @param horizontalImageAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalImageAlignment} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
        getObject().setHorizontalImageAlignment(horizontalImageAlignment);
        return this;
    }

    /**
     * <p>setHorizontalAlignment.</p>
     *
     * @param horizontalAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalAlignment} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     * @deprecated use setHorizontalImageAlignment instead
     */
    @Deprecated
    public BooleanFieldBuilder setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        if (horizontalAlignment != null) {
            getObject().setHorizontalImageAlignment(HorizontalImageAlignment.valueOf(horizontalAlignment.name()));
        } else {
            getObject().setHorizontalImageAlignment(null);
        }
        return this;
    }

    /**
     * <p>setHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.component.BooleanFieldBuilder} object.
     */
    public BooleanFieldBuilder setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHorizontalTextAlignment(horizontalTextAlignment);
        return this;
    }
}
