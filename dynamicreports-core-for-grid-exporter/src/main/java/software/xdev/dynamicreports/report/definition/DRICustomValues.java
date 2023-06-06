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
package software.xdev.dynamicreports.report.definition;

import software.xdev.dynamicreports.jasper.base.tableofcontents.JasperTocHeading;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>DRICustomValues interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRICustomValues extends Serializable {
    /**
     * Constant <code>NAME="CUSTOM_VALUES"</code>
     */
    public static final String NAME = "CUSTOM_VALUES";

    /**
     * <p>setSystemValue.</p>
     *
     * @param name  a {@link java.lang.String} object.
     * @param value a {@link java.lang.Object} object.
     */
    public void setSystemValue(String name, Object value);

    /**
     * <p>addTocHeading.</p>
     *
     * @param level       a int.
     * @param id          a {@link java.lang.String} object.
     * @param text        a {@link java.lang.String} object.
     * @param customValue a {@link java.lang.Object} object.
     */
    public void addTocHeading(int level, String id, String text, Object customValue);

    /**
     * <p>getTocHeadings.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, JasperTocHeading> getTocHeadings();

    /**
     * <p>setTocHeadings.</p>
     *
     * @param tocHeadings a {@link java.util.Map} object.
     */
    public void setTocHeadings(Map<String, JasperTocHeading> tocHeadings);

    /**
     * <p>setSubreportWidth.</p>
     *
     * @param subreportWidth a {@link java.lang.Integer} object.
     */
    public void setSubreportWidth(Integer subreportWidth);
}
