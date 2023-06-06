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
package software.xdev.dynamicreports.report.builder;

/**
 * A set of methods of converting unit values to pixels.
 *
 * @author Ricardo Mariaca
 * 
 */
public class Units {

    /**
     * Converts a value in centimeters to pixels.
     *
     * @param value - the value in centimeters
     * @return pixels
     */
    public static int cm(Number value) {
        return unit(value, 28.3464);
    }

    /**
     * Converts a value in inches to pixels.
     *
     * @param value - the value in inches
     * @return pixels
     */
    public static int inch(Number value) {
        return unit(value, 72);
    }

    /**
     * Converts a value in milimeters to pixels.
     *
     * @param value - the value in milimeters
     * @return pixels
     */
    public static int mm(Number value) {
        return unit(value, 2.83464);
    }

    private static int unit(Number value, double unitValue) {
        if (value != null) {
            return (int) ((value.doubleValue()) * unitValue);
        }
        return 0;
    }
}
