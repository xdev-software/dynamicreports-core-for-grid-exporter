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

import net.sf.jasperreports.export.type.HtmlBorderCollapseEnum;
import software.xdev.dynamicreports.jasper.base.export.JasperHtmlExporter;
import software.xdev.dynamicreports.jasper.constant.SizeUnit;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>JasperHtmlExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperHtmlExporterBuilder extends AbstractJasperExporterBuilder<JasperHtmlExporterBuilder, JasperHtmlExporter> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperHtmlExporterBuilder.</p>
     */
    protected JasperHtmlExporterBuilder() {
        super(new JasperHtmlExporter());
    }

    /**
     * <p>setOutputImagesToDir.</p>
     *
     * @param outputImagesToDir a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setOutputImagesToDir(final Boolean outputImagesToDir) {
        this.getObject().setOutputImagesToDir(outputImagesToDir);
        return this;
    }

    /**
     * <p>setImagesDirName.</p>
     *
     * @param imagesDirName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setImagesDirName(final String imagesDirName) {
        this.getObject().setImagesDirName(imagesDirName);
        return this;
    }

    /**
     * <p>setImagesURI.</p>
     *
     * @param imagesURI a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setImagesURI(final String imagesURI) {
        this.getObject().setImagesURI(imagesURI);
        return this;
    }

    /**
     * <p>setHtmlHeader.</p>
     *
     * @param htmlHeader a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setHtmlHeader(final String htmlHeader) {
        this.getObject().setHtmlHeader(htmlHeader);
        return this;
    }

    /**
     * <p>setBetweenPagesHtml.</p>
     *
     * @param betweenPagesHtml a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setBetweenPagesHtml(final String betweenPagesHtml) {
        this.getObject().setBetweenPagesHtml(betweenPagesHtml);
        return this;
    }

    /**
     * <p>setHtmlFooter.</p>
     *
     * @param htmlFooter a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setHtmlFooter(final String htmlFooter) {
        this.getObject().setHtmlFooter(htmlFooter);
        return this;
    }

    /**
     * <p>setRemoveEmptySpaceBetweenRows.</p>
     *
     * @param removeEmptySpaceBetweenRows a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setRemoveEmptySpaceBetweenRows(final Boolean removeEmptySpaceBetweenRows) {
        this.getObject().setRemoveEmptySpaceBetweenRows(removeEmptySpaceBetweenRows);
        return this;
    }

    /**
     * <p>setWhitePageBackground.</p>
     *
     * @param whitePageBackground a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setWhitePageBackground(final Boolean whitePageBackground) {
        this.getObject().setWhitePageBackground(whitePageBackground);
        return this;
    }

    /**
     * <p>setUsingImagesToAlign.</p>
     *
     * @param usingImagesToAlign a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     * @deprecated To be removed.
     */
    @Deprecated
    public JasperHtmlExporterBuilder setUsingImagesToAlign(final Boolean usingImagesToAlign) {
        this.getObject().setUsingImagesToAlign(usingImagesToAlign);
        return this;
    }

    /**
     * <p>setWrapBreakWord.</p>
     *
     * @param wrapBreakWord a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setWrapBreakWord(final Boolean wrapBreakWord) {
        this.getObject().setWrapBreakWord(wrapBreakWord);
        return this;
    }

    /**
     * <p>setSizeUnit.</p>
     *
     * @param sizeUnit a {@link software.xdev.dynamicreports.jasper.constant.SizeUnit} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setSizeUnit(final SizeUnit sizeUnit) {
        this.getObject().setSizeUnit(sizeUnit);
        return this;
    }

    /**
     * <p>setFramesAsNestedTables.</p>
     *
     * @param framesAsNestedTables a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     * @deprecated To be removed.
     */
    @Deprecated
    public JasperHtmlExporterBuilder setFramesAsNestedTables(final Boolean framesAsNestedTables) {
        this.getObject().setFramesAsNestedTables(framesAsNestedTables);
        return this;
    }

    /**
     * <p>setIgnorePageMargins.</p>
     *
     * @param ignorePageMargins a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setIgnorePageMargins(final Boolean ignorePageMargins) {
        this.getObject().setIgnorePageMargins(ignorePageMargins);
        return this;
    }

    /**
     * <p>setBorderCollapse.</p>
     *
     * @param borderCollapse a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setBorderCollapse(final HtmlBorderCollapseEnum borderCollapse)
    {
        this.getObject().setBorderCollapse(borderCollapse);
        return this;
    }

    /**
     * <p>setAccessibleHtml.</p>
     *
     * @param accessibleHtml a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setAccessibleHtml(final Boolean accessibleHtml) {
        this.getObject().setAccessibleHtml(accessibleHtml);
        return this;
    }

    /**
     * <p>setZoomRatio.</p>
     *
     * @param zoomRatio a {@link java.lang.Float} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setZoomRatio(final Float zoomRatio) {
        this.getObject().setZoomRatio(zoomRatio);
        return this;
    }

    /**
     * <p>setIgnoreHyperLink.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setIgnoreHyperLink(final Boolean ignoreHyperLink) {
        this.getObject().setIgnoreHyperLink(ignoreHyperLink);
        return this;
    }

    /**
     * <p>setFlushOutput.</p>
     *
     * @param flushOutput a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperHtmlExporterBuilder} object.
     */
    public JasperHtmlExporterBuilder setFlushOutput(final Boolean flushOutput) {
        this.getObject().setFlushOutput(flushOutput);
        return this;
    }
}
