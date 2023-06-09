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
package software.xdev.dynamicreports.design.exception;

import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>DRDesignReportException class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class DRDesignReportException extends RuntimeException {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for DRDesignReportException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public DRDesignReportException(String message) {
        super(message);
    }

    /**
     * <p>Constructor for DRDesignReportException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param cause   a {@link java.lang.Throwable} object.
     */
    public DRDesignReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
