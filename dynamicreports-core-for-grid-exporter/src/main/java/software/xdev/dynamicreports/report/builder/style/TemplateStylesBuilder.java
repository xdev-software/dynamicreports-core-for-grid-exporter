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
package software.xdev.dynamicreports.report.builder.style;

import software.xdev.dynamicreports.jasper.base.JasperTemplateStyleLoader;
import software.xdev.dynamicreports.report.base.style.DRStyle;
import software.xdev.dynamicreports.report.builder.AbstractBuilder;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.exception.DRException;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>TemplateStylesBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class TemplateStylesBuilder extends AbstractBuilder<TemplateStylesBuilder, List<StyleBuilder>> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for TemplateStylesBuilder.</p>
     */
    protected TemplateStylesBuilder() {
        super(new ArrayList<StyleBuilder>());
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     */
    public TemplateStylesBuilder loadStyles(InputStream inputStream) {
        return addStyles(JasperTemplateStyleLoader.loadStyles(inputStream));
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param file a {@link java.io.File} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     */
    public TemplateStylesBuilder loadStyles(File file) {
        return addStyles(JasperTemplateStyleLoader.loadStyles(file));
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     */
    public TemplateStylesBuilder loadStyles(String fileName) throws DRException {
        return addStyles(JasperTemplateStyleLoader.loadStyles(fileName));
    }

    /**
     * <p>loadStyles.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     */
    public TemplateStylesBuilder loadStyles(URL url) {
        return addStyles(JasperTemplateStyleLoader.loadStyles(url));
    }

    private TemplateStylesBuilder addStyles(DRStyle[] styles) {
        for (DRStyle style : styles) {
            this.getObject().add(new StyleBuilder(style));
        }
        return this;
    }

    /**
     * <p>styles.</p>
     *
     * @param styles a {@link software.xdev.dynamicreports.report.builder.style.StyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     */
    public TemplateStylesBuilder styles(StyleBuilder... styles) {
        return addStyle(styles);
    }

    /**
     * <p>addStyle.</p>
     *
     * @param styles a {@link software.xdev.dynamicreports.report.builder.style.StyleBuilder} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.TemplateStylesBuilder} object.
     */
    public TemplateStylesBuilder addStyle(StyleBuilder... styles) {
        Validate.notNull(styles, "styles must not be null");
        Validate.noNullElements(styles, "styles must not contains null style");
        for (StyleBuilder templateStyle : styles) {
            getObject().add(templateStyle);
        }
        return this;
    }

    /**
     * <p>getStyle.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @return a {@link software.xdev.dynamicreports.report.builder.style.StyleBuilder} object.
     */
    public StyleBuilder getStyle(String name) {
        Validate.notNull(name, "name must not be null");
        for (StyleBuilder style : getStyles()) {
            if (name.equals(style.getStyle().getName())) {
                return style;
            }
        }
        return null;
    }

    /**
     * <p>getStyles.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<StyleBuilder> getStyles() {
        return build();
    }
}
