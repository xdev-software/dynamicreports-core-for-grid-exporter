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
package software.xdev.dynamicreports.jasper.base.reporthandler;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.definition.JasperReportHandler;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * <p>Abstract AbstractPrintListHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public abstract class AbstractPrintListHandler implements JasperReportHandler {
    private boolean continuousPageNumbering;
    private int pageNumber;

    /**
     * <p>Constructor for AbstractPrintListHandler.</p>
     */
    public AbstractPrintListHandler() {
        continuousPageNumbering = false;
        pageNumber = 1;
    }

    /** {@inheritDoc} */
    @Override
    public void concatenate(JasperReportBuilder... jasperReportBuilders) {
        for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
            try {
                if (continuousPageNumbering) {
                    jasperReportBuilder.setStartPageNumber(pageNumber);
                } else {
                    jasperReportBuilder.setStartPageNumber(null);
                }
                JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
                add(jasperPrint);
                pageNumber += jasperPrint.getPages().size();
                jasperReportBuilder.rebuild();
            } catch (Exception e) {
            }
        }
    }

    /**
     * <p>add.</p>
     *
     * @param jasperPrint a {@link net.sf.jasperreports.engine.JasperPrint} object.
     */
    protected abstract void add(JasperPrint jasperPrint);

    /** {@inheritDoc} */
    @Override
    public void setContinuousPageNumbering(boolean continuousPageNumbering) {
        this.continuousPageNumbering = continuousPageNumbering;
    }

}
