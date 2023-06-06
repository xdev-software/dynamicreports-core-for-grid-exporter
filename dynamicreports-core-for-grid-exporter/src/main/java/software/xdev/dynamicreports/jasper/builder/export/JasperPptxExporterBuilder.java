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

import software.xdev.dynamicreports.jasper.base.export.JasperPptxExporter;
import software.xdev.dynamicreports.report.constant.Constants;

/**
 * <p>JasperPptxExporterBuilder class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperPptxExporterBuilder extends AbstractJasperExporterBuilder<JasperPptxExporterBuilder, JasperPptxExporter> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for JasperPptxExporterBuilder.</p>
     */
    protected JasperPptxExporterBuilder() {
        super(new JasperPptxExporter());
    }

    /**
     * <p>setIgnoreHyperLink.</p>
     *
     * @param ignoreHyperLink a {@link java.lang.Boolean} object.
     * @return a {@link software.xdev.dynamicreports.jasper.builder.export.JasperPptxExporterBuilder} object.
     */
    public JasperPptxExporterBuilder setIgnoreHyperLink(Boolean ignoreHyperLink) {
        this.getObject().setIgnoreHyperLink(ignoreHyperLink);
        return this;
    }
}
