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
package software.xdev.dynamicreports.report.base;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.definition.DRIScriptlet;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import org.apache.commons.lang3.Validate;

/**
 * <p>Abstract AbstractScriptlet class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractScriptlet implements DRIScriptlet {
    private String name;

    /**
     * <p>Constructor for AbstractScriptlet.</p>
     */
    public AbstractScriptlet() {
        this.name = ReportUtils.generateUniqueName("scriptlet");
    }

    /**
     * <p>Constructor for AbstractScriptlet.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public AbstractScriptlet(String name) {
        Validate.notEmpty(name, "name must not be empty");
        this.name = name;
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public void afterColumnInit(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void afterDetailEval(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void afterGroupInit(String groupName, ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void afterPageInit(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void afterReportInit(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void beforeColumnInit(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void beforeDetailEval(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void beforeGroupInit(String groupName, ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void beforePageInit(ReportParameters reportParameters) {
    }

    /** {@inheritDoc} */
    @Override
    public void beforeReportInit(ReportParameters reportParameters) {
    }
}
