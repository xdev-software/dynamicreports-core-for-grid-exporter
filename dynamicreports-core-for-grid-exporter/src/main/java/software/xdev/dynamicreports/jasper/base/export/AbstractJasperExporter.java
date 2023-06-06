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

import software.xdev.dynamicreports.jasper.definition.export.JasperIExporter;
import software.xdev.dynamicreports.report.constant.Constants;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;

/**
 * <p>Abstract AbstractJasperExporter class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractJasperExporter implements JasperIExporter {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Writer outputWriter;
    private OutputStream outputStream;
    private File outputFile;
    private String outputFileName;

    private Integer pageIndex;
    private Integer startPageIndex;
    private Integer endPageIndex;
    private String characterEncoding;
    private Integer offsetX;
    private Integer offsetY;

    /** {@inheritDoc} */
    @Override
    public Writer getOutputWriter() {
        return outputWriter;
    }

    /**
     * <p>Setter for the field <code>outputWriter</code>.</p>
     *
     * @param outputWriter a {@link java.io.Writer} object.
     */
    public void setOutputWriter(Writer outputWriter) {
        Validate.notNull(outputWriter, "outputWriter must not be null");
        this.outputWriter = outputWriter;
    }

    /** {@inheritDoc} */
    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * <p>Setter for the field <code>outputStream</code>.</p>
     *
     * @param outputStream a {@link java.io.OutputStream} object.
     */
    public void setOutputStream(OutputStream outputStream) {
        Validate.notNull(outputStream, "outputStream must not be null");
        this.outputStream = outputStream;
    }

    /** {@inheritDoc} */
    @Override
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * <p>Setter for the field <code>outputFile</code>.</p>
     *
     * @param outputFile a {@link java.io.File} object.
     */
    public void setOutputFile(File outputFile) {
        Validate.notNull(outputFile, "outputFile must not be null");
        this.outputFile = outputFile;
    }

    /** {@inheritDoc} */
    @Override
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * <p>Setter for the field <code>outputFileName</code>.</p>
     *
     * @param outputFileName a {@link java.lang.String} object.
     */
    public void setOutputFileName(String outputFileName) {
        Validate.notNull(outputFileName, "outputFileName must not be null");
        this.outputFileName = outputFileName;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * <p>Setter for the field <code>pageIndex</code>.</p>
     *
     * @param pageIndex a {@link java.lang.Integer} object.
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getStartPageIndex() {
        return startPageIndex;
    }

    /**
     * <p>Setter for the field <code>startPageIndex</code>.</p>
     *
     * @param startPageIndex a {@link java.lang.Integer} object.
     */
    public void setStartPageIndex(Integer startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getEndPageIndex() {
        return endPageIndex;
    }

    /**
     * <p>Setter for the field <code>endPageIndex</code>.</p>
     *
     * @param endPageIndex a {@link java.lang.Integer} object.
     */
    public void setEndPageIndex(Integer endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    /** {@inheritDoc} */
    @Override
    public String getCharacterEncoding() {
        return characterEncoding;
    }

    /**
     * <p>Setter for the field <code>characterEncoding</code>.</p>
     *
     * @param characterEncoding a {@link java.lang.String} object.
     */
    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getOffsetX() {
        return offsetX;
    }

    /**
     * <p>Setter for the field <code>offsetX</code>.</p>
     *
     * @param offsetX a {@link java.lang.Integer} object.
     */
    public void setOffsetX(Integer offsetX) {
        this.offsetX = offsetX;
    }

    /** {@inheritDoc} */
    @Override
    public Integer getOffsetY() {
        return offsetY;
    }

    /**
     * <p>Setter for the field <code>offsetY</code>.</p>
     *
     * @param offsetY a {@link java.lang.Integer} object.
     */
    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

}
