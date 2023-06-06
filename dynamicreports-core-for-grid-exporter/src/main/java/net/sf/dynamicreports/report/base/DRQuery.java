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
package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIQuery;
import org.apache.commons.lang3.Validate;

/**
 * <p>DRQuery class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRQuery implements DRIQuery {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String text;
    private String language;

    /**
     * <p>Constructor for DRQuery.</p>
     *
     * @param text     a {@link java.lang.String} object.
     * @param language a {@link java.lang.String} object.
     */
    public DRQuery(String text, String language) {
        Validate.notNull(text, "text must not be null");
        Validate.notNull(language, "language must not be null");
        this.text = text;
        this.language = language;
    }

    /** {@inheritDoc} */
    @Override
    public String getText() {
        return text;
    }

    /** {@inheritDoc} */
    @Override
    public String getLanguage() {
        return language;
    }
}
