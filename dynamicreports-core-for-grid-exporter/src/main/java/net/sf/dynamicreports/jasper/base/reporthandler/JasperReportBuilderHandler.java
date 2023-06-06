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
package net.sf.dynamicreports.jasper.base.reporthandler;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.definition.JasperReportHandler;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>JasperReportBuilderHandler class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperReportBuilderHandler implements JasperReportHandler {
    private List<JasperReportBuilder> jasperReportBuilders;
    private boolean continuousPageNumbering;

    /**
     * <p>Constructor for JasperReportBuilderHandler.</p>
     */
    public JasperReportBuilderHandler() {
        jasperReportBuilders = new ArrayList<JasperReportBuilder>();
        continuousPageNumbering = false;
    }

    /** {@inheritDoc} */
    @Override
    public void concatenate(JasperReportBuilder... jasperReportBuilders) {
        for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
            this.jasperReportBuilders.add(jasperReportBuilder);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setContinuousPageNumbering(boolean continuousPageNumbering) {
        this.continuousPageNumbering = continuousPageNumbering;
    }

    /** {@inheritDoc} */
    @Override
    public List<JasperPrint> getPrintList() throws DRException {
        List<JasperPrint> printList = new ArrayList<JasperPrint>();
        int pageNumber = 1;
        for (JasperReportBuilder jasperReportBuilder : jasperReportBuilders) {
            if (continuousPageNumbering) {
                jasperReportBuilder.setStartPageNumber(pageNumber);
            } else {
                jasperReportBuilder.setStartPageNumber(null);
            }
            JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
            printList.add(jasperPrint);
            pageNumber += jasperPrint.getPages().size();
        }
        return printList;
    }

}
