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
package software.xdev.dynamicreports.report.builder.expression;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * <p>Abstract AbstractSubDatasourceExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractSubDatasourceExpression<T> extends AbstractComplexExpression<JRDataSource> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    /**
     * <p>Constructor for AbstractSubDatasourceExpression.</p>
     *
     * @param fieldName a {@link java.lang.String} object.
     */
    protected AbstractSubDatasourceExpression(String fieldName) {
        Validate.notNull(fieldName, "fieldName must not be null");
        addExpression(fieldName, getSubDatasourceDataClass());
    }

    /**
     * <p>Constructor for AbstractSubDatasourceExpression.</p>
     *
     * @param expression a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    protected AbstractSubDatasourceExpression(DRIExpression<? extends T> expression) {
        addExpression(expression);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public JRDataSource evaluate(List<?> values, ReportParameters reportParameters) {
        return createSubDatasource((T) values.get(0));
    }

    /** {@inheritDoc} */
    @Override
    public Class<? super JRDataSource> getValueClass() {
        return JRDataSource.class;
    }

    /**
     * <p>getSubDatasourceDataClass.</p>
     *
     * @return a {@link java.lang.Class} object.
     */
    @SuppressWarnings("unchecked")
    protected Class<T> getSubDatasourceDataClass() {
        return (Class<T>) ReportUtils.getGenericClass(this, 0);
    }

    /**
     * <p>createSubDatasource.</p>
     *
     * @param data a T object.
     * @return a {@link net.sf.jasperreports.engine.JRDataSource} object.
     */
    protected abstract JRDataSource createSubDatasource(T data);
}
