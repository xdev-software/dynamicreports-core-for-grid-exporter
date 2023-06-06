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
package software.xdev.dynamicreports.jasper.base;

/**
 * Interface for managers of {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} used in {@link software.xdev.dynamicreports.jasper.base.JasperCustomValues}.
 *
 * @author edwin.njeru
 * @version 6.0.1-SNAPSHOT
 */
public interface JasperScriptletManager {

    /**
     * Property key used for selecting thread safe implementation.
     */
    public static final String USE_THREAD_SAFE_SCRIPLET_MANAGER_PROPERTY_KEY = "software.xdev.dynamicreports.useThreadSafeScriptletManager";

    /**
     * Getter for the {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} instance.
     *
     * @return the set {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} instance
     */
    JasperScriptlet getJasperScriptlet();

    /**
     * Setter for the {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} instance.
     *
     * @param jasperScriptlet the {@link software.xdev.dynamicreports.jasper.base.JasperScriptlet} instance to set
     */
    void setJasperScriptlet(JasperScriptlet jasperScriptlet);
}
