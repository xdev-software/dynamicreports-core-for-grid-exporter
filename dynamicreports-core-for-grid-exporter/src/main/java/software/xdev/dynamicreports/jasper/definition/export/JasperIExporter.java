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
package software.xdev.dynamicreports.jasper.definition.export;

import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;

/**
 * <p>JasperIExporter interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface JasperIExporter extends Serializable {

    /**
     * <p>getOutputWriter.</p>
     *
     * @return a {@link java.io.Writer} object.
     */
    public Writer getOutputWriter();

    /**
     * <p>getOutputStream.</p>
     *
     * @return a {@link java.io.OutputStream} object.
     */
    public OutputStream getOutputStream();

    /**
     * <p>getOutputFile.</p>
     *
     * @return a {@link java.io.File} object.
     */
    public File getOutputFile();

    /**
     * <p>getOutputFileName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getOutputFileName();

    /**
     * <p>getPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getPageIndex();

    /**
     * <p>getStartPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getStartPageIndex();

    /**
     * <p>getEndPageIndex.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getEndPageIndex();

    /**
     * <p>getCharacterEncoding.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCharacterEncoding();

    /**
     * <p>getOffsetX.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getOffsetX();

    /**
     * <p>getOffsetY.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getOffsetY();

}
