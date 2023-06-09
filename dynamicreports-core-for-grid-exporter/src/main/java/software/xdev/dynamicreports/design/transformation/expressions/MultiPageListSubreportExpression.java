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

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.DRBand;
import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.Constants;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

/**
 * <p>MultiPageListSubreportExpression class.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public class MultiPageListSubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

    private Locale locale;
    private ResourceBundle resourceBundle;
    private String resourceBundleName;
    private WhenResourceMissingType whenResourceMissingType;
    private List<DRIComponent> detailComponents;
    private Map<String, DRIStyle> templateStyles;

    /**
     * <p>Constructor for MultiPageListSubreportExpression.</p>
     *
     * @param locale                  a {@link java.util.Locale} object.
     * @param resourceBundle          a {@link java.util.ResourceBundle} object.
     * @param resourceBundleName      a {@link java.lang.String} object.
     * @param resourceBundleName      a {@link java.lang.String} object.
     * @param whenResourceMissingType a {@link software.xdev.dynamicreports.report.constant.WhenResourceMissingType} object.
     * @param detailComponents        a {@link java.util.List} object.
     * @param templateStyles          a {@link java.util.Map} object.
     */
    public MultiPageListSubreportExpression(Locale locale, ResourceBundle resourceBundle, String resourceBundleName, WhenResourceMissingType whenResourceMissingType,
                                            List<DRIComponent> detailComponents, Map<String, DRIStyle> templateStyles) {
        this.locale = locale;
        this.resourceBundle = resourceBundle;
        this.resourceBundleName = resourceBundleName;
        this.whenResourceMissingType = whenResourceMissingType;
        this.detailComponents = detailComponents;
        this.templateStyles = templateStyles;
    }

    /** {@inheritDoc} */
    @Override
    public JasperReportBuilder evaluate(ReportParameters reportParameters) {
        JasperReportBuilder report = report();
        report.setLocale(locale);
        report.setResourceBundle(resourceBundle);
        report.setResourceBundle(resourceBundleName);
        report.setWhenResourceMissingType(whenResourceMissingType);
        for (DRIStyle style : templateStyles.values()) {
            report.getReport().addTemplateStyle(style);
        }
        DRBand titleBand = report.getReport().getTitleBand();
        DRComponent detailComponent = (DRComponent) detailComponents.get(reportParameters.getReportRowNumber() - 1);
        titleBand.addComponent(detailComponent);
        return report;
    }
}
