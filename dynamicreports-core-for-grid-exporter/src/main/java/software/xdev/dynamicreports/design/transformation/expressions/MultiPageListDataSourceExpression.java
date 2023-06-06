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
package software.xdev.dynamicreports.design.transformation.expressions;

import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * <p>MultiPageListDataSourceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiPageListDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private int count;

    /**
     * <p>Constructor for MultiPageListDataSourceExpression.</p>
     *
     * @param count a int.
     */
    public MultiPageListDataSourceExpression(int count) {
        this.count = count;
    }

    /** {@inheritDoc} */
    @Override
    public JRDataSource evaluate(ReportParameters reportParameters) {
        return new JREmptyDataSource(count);
    }

    /** {@inheritDoc} */
    @Override
    public Class<JRDataSource> getValueClass() {
        return JRDataSource.class;
    }
}
