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
 * Thread safe implementation of {@link software.xdev.dynamicreports.jasper.base.JasperScriptletManager} used for filling cached reports concurrently. Note: use of this class can lead to memory leaks if the threads starting the report fill
 * are not terminated properly.
 *
 * @author edwin.njeru
 * @version 6.0.1-SNAPSHOT
 */
public class ThreadSafeJasperScriptletManager implements JasperScriptletManager {

    private ThreadLocal<JasperScriptlet> threadLocalScriptlet = new ThreadLocal<>();

    /** {@inheritDoc} */
    @Override
    public JasperScriptlet getJasperScriptlet() {
        return threadLocalScriptlet.get();
    }

    /** {@inheritDoc} */
    @Override
    public void setJasperScriptlet(JasperScriptlet jasperScriptlet) {
        threadLocalScriptlet.set(jasperScriptlet);

    }

}
