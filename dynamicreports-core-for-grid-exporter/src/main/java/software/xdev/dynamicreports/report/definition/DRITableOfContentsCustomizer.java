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
import software.xdev.dynamicreports.report.builder.ReportBuilder;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;

import java.io.Serializable;
import java.util.List;

/**
 * <p>DRITableOfContentsCustomizer interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRITableOfContentsCustomizer extends Serializable {

    /**
     * <p>setReport.</p>
     *
     * @param report a {@link software.xdev.dynamicreports.report.builder.ReportBuilder} object.
     */
    public void setReport(ReportBuilder<?> report);

    /**
     * <p>setHeadingList.</p>
     *
     * @param headingList a {@link java.util.List} object.
     */
    public void setHeadingList(List<JasperTocHeading> headingList);

    /**
     * <p>setHeadings.</p>
     *
     * @param headings a int.
     */
    public void setHeadings(int headings);

    /**
     * <p>setLevels.</p>
     *
     * @param levels a int.
     */
    public void setLevels(int levels);

    /**
     * <p>customize.</p>
     */
    public void customize();

    /**
     * <p>getPosition.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.TableOfContentsPosition} object.
     */
    public TableOfContentsPosition getPosition();
}
