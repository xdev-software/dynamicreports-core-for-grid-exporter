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
package software.xdev.dynamicreports.jasper.base.tableofcontents;

import software.xdev.dynamicreports.jasper.base.JasperCustomValues;
import software.xdev.dynamicreports.jasper.base.JasperReportDesign;
import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.jasper.exception.JasperDesignException;
import software.xdev.dynamicreports.jasper.transformation.ConstantTransform;
import software.xdev.dynamicreports.report.base.DRPage;
import software.xdev.dynamicreports.report.builder.MarginBuilder;
import software.xdev.dynamicreports.report.constant.TableOfContentsPosition;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer;
import software.xdev.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintFrame;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static software.xdev.dynamicreports.report.builder.DynamicReports.margin;
import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

/**
 * <p>JasperTocReport class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class JasperTocReport {

    /**
     * <p>createTocReport.</p>
     *
     * @param jasperReportDesign a {@link software.xdev.dynamicreports.jasper.base.JasperReportDesign} object.
     * @param jasperPrint        a {@link net.sf.jasperreports.engine.JasperPrint} object.
     * @param parameters         a {@link java.util.Map} object.
     * @throws software.xdev.dynamicreports.report.exception.DRException if any.
     * @throws net.sf.jasperreports.engine.JRException if any.
     */
    public static void createTocReport(JasperReportDesign jasperReportDesign, JasperPrint jasperPrint, Map<String, Object> parameters) throws DRException, JRException {
        JasperCustomValues customValues = jasperReportDesign.getCustomValues();
        Map<String, JasperTocHeading> headings = customValues.getTocHeadings();
        if (headings != null && !headings.isEmpty()) {
            JasperReportBuilder tocReport = report();

            List<JasperTocHeading> headingList = new ArrayList<JasperTocHeading>();
            int pageNumber = 1;
            for (JRPrintPage page : jasperPrint.getPages()) {
                for (JRPrintElement element : page.getElements()) {
                    addTocHeading(headings, headingList, element, pageNumber);
                }
                pageNumber++;
            }

            int levels = 0;
            for (JasperTocHeading heading : headingList) {
                if (heading.getLevel() > levels) {
                    levels = heading.getLevel();
                }
            }
            levels++;

            DRPage tocPage = tocReport.getReport().getPage();
            tocPage.setWidth(jasperReportDesign.getDesign().getPageWidth());
            tocPage.setHeight(jasperReportDesign.getDesign().getPageHeight());
            tocPage.setOrientation(ConstantTransform.pageOrientation(jasperReportDesign.getDesign().getOrientationValue()));
            MarginBuilder tocMargin = margin();
            tocMargin.setTop(jasperReportDesign.getDesign().getTopMargin());
            tocMargin.setLeft(jasperReportDesign.getDesign().getLeftMargin());
            tocMargin.setBottom(jasperReportDesign.getDesign().getBottomMargin());
            tocMargin.setRight(jasperReportDesign.getDesign().getRightMargin());
            tocReport.setLocale((Locale) parameters.get(JRParameter.REPORT_LOCALE));
            tocReport.setResourceBundle((ResourceBundle) parameters.get(JRParameter.REPORT_RESOURCE_BUNDLE));
            tocReport.setPageMargin(tocMargin);
            tocReport.setDataSource(new JRBeanCollectionDataSource(headingList));

            DRITableOfContentsCustomizer tableOfContents = jasperReportDesign.getTableOfContentsCustomizer();
            tableOfContents.setReport(tocReport);
            tableOfContents.setHeadingList(headingList);
            tableOfContents.setHeadings(headings.size());
            tableOfContents.setLevels(levels);
            tableOfContents.customize();

            TableOfContentsPosition tableOfContentsPosition = tableOfContents.getPosition();
            if (tableOfContentsPosition == null) {
                tableOfContentsPosition = Defaults.getDefaults().getTableOfContentsPosition();
            }
            JasperPrint tocJasperPrint = tocReport.toJasperPrint();
            for (
                int i = 0; i < tocJasperPrint.getPages().size(); i++) {
                JRPrintPage page = tocJasperPrint.getPages().get(i);
                switch (tableOfContentsPosition) {
                    case TOP:
                        jasperPrint.addPage(i, page);
                        break;
                    case BOTTOM:
                        jasperPrint.addPage(page);
                        break;
                    default:
                        throw new JasperDesignException("Table of contents position " + tableOfContentsPosition.name() + " not supported");
                }
            }
            for (JRStyle style : tocJasperPrint.getStyles()) {
                jasperPrint.addStyle(style);
            }
        }
    }

    private static void addTocHeading(Map<String, JasperTocHeading> headings, List<JasperTocHeading> headingList, JRPrintElement element, int pageNumber) {
        if (element instanceof JRPrintText && StringUtils.contains(element.getKey(), ".tocReference")) {
            String id = ((JRPrintText) element).getAnchorName();
            JasperTocHeading heading = headings.get(id);
            heading.setPageIndex(pageNumber);
            headingList.add(heading);
        }
        if (element instanceof JRPrintFrame) {
            for (JRPrintElement element2 : ((JRPrintFrame) element).getElements()) {
                addTocHeading(headings, headingList, element2, pageNumber);
            }
        }
    }

}
