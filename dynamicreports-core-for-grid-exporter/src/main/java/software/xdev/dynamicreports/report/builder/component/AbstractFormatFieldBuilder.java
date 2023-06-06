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

import software.xdev.dynamicreports.report.base.component.DRFormatField;
import software.xdev.dynamicreports.report.builder.expression.Expressions;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.HorizontalAlignment;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;

/**
 * <p>Abstract AbstractFormatFieldBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
@SuppressWarnings( {"unchecked", "deprecation"})
public abstract class AbstractFormatFieldBuilder<T extends AbstractFormatFieldBuilder<T, U>, U extends DRFormatField> extends HyperLinkComponentBuilder<T, U> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractFormatFieldBuilder.</p>
     *
     * @param formatField a U object.
     */
    protected AbstractFormatFieldBuilder(U formatField) {
        super(formatField);
    }

    /**
     * <p>setFormatExpression.</p>
     *
     * @param format a {@link java.lang.String} object.
     * @return a T object.
     */
    public T setFormatExpression(String format) {
        getObject().setFormatExpression(Expressions.text(format));
        return (T) this;
    }

    /**
     * <p>setFormatExpression.</p>
     *
     * @param formatExpression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     * @return a T object.
     */
    public T setFormatExpression(DRIExpression<String> formatExpression) {
        getObject().setFormatExpression(formatExpression);
        return (T) this;
    }

    /**
     * <p>setHorizontalAlignment.</p>
     *
     * @param horizontalAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalAlignment} object.
     * @return a T object.
     * @deprecated use setHorizontalTextAlignment instead
     */
    @Deprecated
    public T setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        if (horizontalAlignment != null) {
            getObject().setHorizontalTextAlignment(HorizontalTextAlignment.valueOf(horizontalAlignment.name()));
        } else {
            getObject().setHorizontalTextAlignment(null);
        }
        return (T) this;
    }

    /**
     * <p>setHorizontalTextAlignment.</p>
     *
     * @param horizontalTextAlignment a {@link software.xdev.dynamicreports.report.constant.HorizontalTextAlignment} object.
     * @return a T object.
     */
    public T setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
        getObject().setHorizontalTextAlignment(horizontalTextAlignment);
        return (T) this;
    }
}
