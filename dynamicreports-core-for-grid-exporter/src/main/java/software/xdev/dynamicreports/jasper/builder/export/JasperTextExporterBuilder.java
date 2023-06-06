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
package software.xdev.dynamicreports.jasper.builder.export;

import software.xdev.dynamicreports.jasper.base.export.JasperTextExporter;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>JasperTextExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperTextExporterBuilder extends AbstractJasperExporterBuilder<JasperTextExporterBuilder, JasperTextExporter> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperTextExporterBuilder.</p>
     */
    protected JasperTextExporterBuilder() {
        super(new JasperTextExporter());
    }

    /**
     * <p>setCharacterWidth.</p>
     *
     * @param characterWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @deprecated To be removed. Use setCharacterWidth(Float characterWidth) instead.
     */
    @Deprecated
    public JasperTextExporterBuilder setCharacterWidth(Integer characterWidth) {
        return setCharacterWidth(characterWidth != null ? characterWidth.floatValue() : null);
    }

    /**
     * <p>setCharacterWidth.</p>
     *
     * @param characterWidth a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setCharacterWidth(Float characterWidth) {
        this.getObject().setCharacterWidth(characterWidth);
        return this;
    }

    /**
     * <p>setCharacterHeight.</p>
     *
     * @param characterHeight a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @deprecated To be removed. Use setCharacterHeight(Float characterHeight) instead.
     */
    @Deprecated
    public JasperTextExporterBuilder setCharacterHeight(Integer characterHeight) {
        return setCharacterHeight(characterHeight != null ? characterHeight.floatValue() : null);
    }

    /**
     * <p>setCharacterHeight.</p>
     *
     * @param characterHeight a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setCharacterHeight(Float characterHeight) {
        this.getObject().setCharacterHeight(characterHeight);
        return this;
    }

    /**
     * <p>setPageWidth.</p>
     *
     * @param pageWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @deprecated To be removed. Use setPageWidthInChars instead.
     */
    @Deprecated
    public JasperTextExporterBuilder setPageWidth(Integer pageWidth) {
        return setPageWidthInChars(pageWidth);
    }

    /**
     * <p>setPageWidthInChars.</p>
     *
     * @param pageWidth a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageWidthInChars(Integer pageWidth) {
        this.getObject().setPageWidthInChars(pageWidth);
        return this;
    }

    /**
     * <p>setPageHeight.</p>
     *
     * @param pageHeight a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @deprecated To be removed. Use setPageHeightInChars instead.
     */
    @Deprecated
    public JasperTextExporterBuilder setPageHeight(Integer pageHeight) {
        return setPageHeightInChars(pageHeight);
    }

    /**
     * <p>setPageHeightInChars.</p>
     *
     * @param pageHeight a {@link java.lang.Integer} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageHeightInChars(Integer pageHeight) {
        this.getObject().setPageHeightInChars(pageHeight);
        return this;
    }

    /**
     * <p>setBetweenPagesText.</p>
     *
     * @param betweenPagesText a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     * @deprecated To be removed. Use setPageSeparator instead.
     */
    @Deprecated
    public JasperTextExporterBuilder setBetweenPagesText(String betweenPagesText) {
        return setPageSeparator(betweenPagesText);
    }

    /**
     * <p>setPageSeparator.</p>
     *
     * @param pageSeparator a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setPageSeparator(String pageSeparator) {
        this.getObject().setPageSeparator(pageSeparator);
        return this;
    }

    /**
     * <p>setLineSeparator.</p>
     *
     * @param lineSeparator a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperTextExporterBuilder} object.
     */
    public JasperTextExporterBuilder setLineSeparator(String lineSeparator) {
        this.getObject().setLineSeparator(lineSeparator);
        return this;
    }
}
