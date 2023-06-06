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
package software.xdev.dynamicreports.jasper.base.export;

import software.xdev.dynamicreports.jasper.definition.export.JasperICsvExporter;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>JasperCsvExporter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperCsvExporter extends AbstractJasperExporter implements JasperICsvExporter {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private String fieldDelimiter;
    private String recordDelimiter;

    /** {@inheritDoc} */
    @Override
    public String getFieldDelimiter() {
        return fieldDelimiter;
    }

    /**
     * <p>Setter for the field <code>fieldDelimiter</code>.</p>
     *
     * @param fieldDelimiter a {@link java.lang.String} object.
     */
    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    /** {@inheritDoc} */
    @Override
    public String getRecordDelimiter() {
        return recordDelimiter;
    }

    /**
     * <p>Setter for the field <code>recordDelimiter</code>.</p>
     *
     * @param recordDelimiter a {@link java.lang.String} object.
     */
    public void setRecordDelimiter(String recordDelimiter) {
        this.recordDelimiter = recordDelimiter;
    }
}
