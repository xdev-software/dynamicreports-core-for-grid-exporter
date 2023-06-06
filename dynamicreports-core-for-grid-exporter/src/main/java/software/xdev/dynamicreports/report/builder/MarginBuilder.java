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
package software.xdev.dynamicreports.report.builder;

import software.xdev.dynamicreports.report.base.DRMargin;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>MarginBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MarginBuilder extends AbstractBuilder<MarginBuilder, DRMargin> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for MarginBuilder.</p>
     */
    protected MarginBuilder() {
        super(new DRMargin());
    }

    /**
     * <p>Constructor for MarginBuilder.</p>
     *
     * @param margin a int.
     */
    protected MarginBuilder(int margin) {
        super(new DRMargin(margin));
    }

    /**
     * <p>setTop.</p>
     *
     * @param top a int.
     * @return a {@link software.xdev.dynamicreports.report.builder.MarginBuilder} object.
     */
    public MarginBuilder setTop(int top) {
        getObject().setTop(top);
        return this;
    }

    /**
     * <p>setLeft.</p>
     *
     * @param left a int.
     * @return a {@link software.xdev.dynamicreports.report.builder.MarginBuilder} object.
     */
    public MarginBuilder setLeft(int left) {
        getObject().setLeft(left);
        return this;
    }

    /**
     * <p>setBottom.</p>
     *
     * @param bottom a int.
     * @return a {@link software.xdev.dynamicreports.report.builder.MarginBuilder} object.
     */
    public MarginBuilder setBottom(int bottom) {
        getObject().setBottom(bottom);
        return this;
    }

    /**
     * <p>setRight.</p>
     *
     * @param right a int.
     * @return a {@link software.xdev.dynamicreports.report.builder.MarginBuilder} object.
     */
    public MarginBuilder setRight(int right) {
        getObject().setRight(right);
        return this;
    }

    /**
     * <p>getMargin.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.DRMargin} object.
     */
    public DRMargin getMargin() {
        return build();
    }
}
