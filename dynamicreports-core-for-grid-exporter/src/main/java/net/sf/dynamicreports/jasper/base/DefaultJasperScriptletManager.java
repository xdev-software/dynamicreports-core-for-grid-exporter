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
package net.sf.dynamicreports.jasper.base;

/**
 * Simple, default implementation of {@link net.sf.dynamicreports.jasper.base.JasperScriptletManager}.
 *
 * @author edwin.njeru
 * @version 6.0.1-SNAPSHOT
 */
public class DefaultJasperScriptletManager implements JasperScriptletManager {

    private JasperScriptlet jasperScriptlet;

    /** {@inheritDoc} */
    @Override
    public JasperScriptlet getJasperScriptlet() {
        return jasperScriptlet;
    }

    /** {@inheritDoc} */
    @Override
    public void setJasperScriptlet(JasperScriptlet jasperScriptlet) {
        this.jasperScriptlet = jasperScriptlet;
    }

}
