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

import software.xdev.dynamicreports.report.base.DRQuery;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>QueryBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class QueryBuilder extends AbstractBuilder<QueryBuilder, DRQuery> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for QueryBuilder.</p>
     *
     * @param text     a {@link java.lang.String} object.
     * @param language a {@link java.lang.String} object.
     */
    protected QueryBuilder(String text, String language) {
        super(new DRQuery(text, language));
    }

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.base.DRQuery} object.
     */
    public DRQuery getQuery() {
        return build();
    }
}
