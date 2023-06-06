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
package software.xdev.dynamicreports.design.base.expression;

import software.xdev.dynamicreports.design.definition.expression.DRIDesignSimpleExpression;
import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>Abstract AbstractDesignSimpleExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractDesignSimpleExpression implements DRIDesignSimpleExpression {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String name;

    /**
     * <p>Constructor for AbstractDesignSimpleExpression.</p>
     */
    protected AbstractDesignSimpleExpression() {
        this(ReportUtils.generateUniqueName("simpleExpression"));
    }

    /**
     * <p>Constructor for AbstractDesignSimpleExpression.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    protected AbstractDesignSimpleExpression(String name) {
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public String getParameterName() {
        return null;
    }
}
