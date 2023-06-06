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
package software.xdev.dynamicreports.report.defaults;

import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import software.xdev.dynamicreports.report.defaults.xml.XmlDynamicReports;

/**
 * <p>Defaults class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class Defaults {
    private static final Log log = LogFactory.getLog(Defaults.class);

    private static final Default defaults;

    static {
        defaults = DefaultBinder.bind(load());
    }

    private static XmlDynamicReports load() {
        final String resource = "dynamicreports-defaults.xml";
        InputStream is = null;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader != null) {
            is = classLoader.getResourceAsStream(resource);
        }
        if (is == null) {
            classLoader = Defaults.class.getClassLoader();
            if (classLoader != null) {
                is = classLoader.getResourceAsStream(resource);
            }
            if (is == null) {
                is = Defaults.class.getResourceAsStream("/" + resource);
            }
        }
        if (is == null) {
            return null;
        }

        try {
            final Unmarshaller unmarshaller = JAXBContext.newInstance(XmlDynamicReports.class).createUnmarshaller();
            final JAXBElement<XmlDynamicReports> root = unmarshaller.unmarshal(new StreamSource(is), XmlDynamicReports.class);
            return root.getValue();
        } catch (final JAXBException e) {
            log.error("Could not load dynamic reports defaults", e);
            return null;
        }
    }

    /**
     * <p>Getter for the field <code>defaults</code>.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.defaults.Default} object.
     */
    public static Default getDefaults() {
        return defaults;
    }
}
