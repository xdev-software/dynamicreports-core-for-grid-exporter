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
package software.xdev.dynamicreports.report.definition;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import software.xdev.dynamicreports.report.constant.Orientation;
import software.xdev.dynamicreports.report.constant.RunDirection;
import software.xdev.dynamicreports.report.constant.WhenNoDataType;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.column.DRIColumn;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.grid.DRIColumnGrid;
import software.xdev.dynamicreports.report.definition.style.DRIConditionalStyle;
import software.xdev.dynamicreports.report.definition.style.DRIFont;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;
import software.xdev.dynamicreports.report.definition.style.DRISimpleStyle;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;

/**
 * <p>DRIReport interface.</p>
 *
 * @author Ricardo Mariaca
 * 
 */
public interface DRIReport extends Serializable {

    /**
     * <p>getTemplate.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIReportTemplate} object.
     */
    public DRIReportTemplate getTemplate();

    /**
     * <p>getTemplateStyles.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIStyle> getTemplateStyles();

    /**
     * <p>getTemplateDesign.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRITemplateDesign} object.
     */
    public DRITemplateDesign<?> getTemplateDesign();

    /**
     * <p>getReportName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getReportName();

    /**
     * <p>getLocale.</p>
     *
     * @return a {@link java.util.Locale} object.
     */
    public Locale getLocale();

    /**
     * <p>getResourceBundle.</p>
     *
     * @return a {@link java.util.ResourceBundle} object.
     */
    public ResourceBundle getResourceBundle();

    /**
     * <p>getResourceBundleName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getResourceBundleName();

    /**
     * <p>getShowColumnTitle.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShowColumnTitle();

    /**
     * <p>getShowColumnValues.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getShowColumnValues();

    /**
     * <p>getColumns.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIColumn<?>> getColumns();

    /**
     * <p>getGroups.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIGroup> getGroups();

    /**
     * <p>getFields.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIField<?>> getFields();

    /**
     * <p>getVariables.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIVariable<?>> getVariables();

    /**
     * <p>getSorts.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRISort> getSorts();

    /**
     * <p>getSubtotals.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRISubtotal<?>> getSubtotals();

    /**
     * <p>getParameters.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIParameter<?>> getParameters();

    /**
     * <p>getParameterValues.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    public Map<String, Object> getParameterValues();

    /**
     * <p>getScriptlets.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<DRIScriptlet> getScriptlets();

    /**
     * <p>getProperties.</p>
     *
     * @return a {@link java.util.Properties} object.
     */
    public Properties getProperties();

    /**
     * <p>getQuery.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIQuery} object.
     */
    public DRIQuery getQuery();

    /**
     * <p>getPage.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIPage} object.
     */
    public DRIPage getPage();

    /**
     * <p>getIgnorePagination.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getIgnorePagination();

    /**
     * <p>getWhenNoDataType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.WhenNoDataType} object.
     */
    public WhenNoDataType getWhenNoDataType();

    /**
     * <p>getWhenResourceMissingType.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.WhenResourceMissingType} object.
     */
    public WhenResourceMissingType getWhenResourceMissingType();

    /**
     * <p>getTitleOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getTitleOnANewPage();

    /**
     * <p>getSummaryOnANewPage.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getSummaryOnANewPage();

    /**
     * <p>getSummaryWithPageHeaderAndFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getSummaryWithPageHeaderAndFooter();

    /**
     * <p>getFloatColumnFooter.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getFloatColumnFooter();

    /**
     * <p>getPrintOrder.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.Orientation} object.
     */
    public Orientation getPrintOrder();

    /**
     * <p>getColumnDirection.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.constant.RunDirection} object.
     */
    public RunDirection getColumnDirection();

    /**
     * <p>getLanguage.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getLanguage();

    /**
     * <p>getUseFieldNameAsDescription.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getUseFieldNameAsDescription();

    /**
     * <p>getDefaultFont.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIFont} object.
     */
    public DRIFont getDefaultFont();

    /**
     * <p>getTextStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getTextStyle();

    /**
     * <p>getColumnTitleStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getColumnTitleStyle();

    /**
     * <p>getColumnStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getColumnStyle();

    /**
     * <p>getGroupTitleStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getGroupTitleStyle();

    /**
     * <p>getGroupStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getGroupStyle();

    /**
     * <p>getSubtotalStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getSubtotalStyle();

    /**
     * <p>getImageStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRIReportStyle} object.
     */
    public DRIReportStyle getImageStyle();

    /**
     * <p>getHighlightDetailOddRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getHighlightDetailOddRows();

    /**
     * <p>getDetailOddRowStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    public DRISimpleStyle getDetailOddRowStyle();

    /**
     * <p>getHighlightDetailEvenRows.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getHighlightDetailEvenRows();

    /**
     * <p>getDetailEvenRowStyle.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.style.DRISimpleStyle} object.
     */
    public DRISimpleStyle getDetailEvenRowStyle();

    /**
     * <p>getDetailRowHighlighters.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<? extends DRIConditionalStyle> getDetailRowHighlighters();

    /**
     * <p>getColumnGrid.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.grid.DRIColumnGrid} object.
     */
    public DRIColumnGrid getColumnGrid();

    /**
     * <p>getTableOfContents.</p>
     *
     * @return a {@link java.lang.Boolean} object.
     */
    public Boolean getTableOfContents();

    /**
     * <p>getTableOfContentsCustomizer.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRITableOfContentsCustomizer} object.
     */
    public DRITableOfContentsCustomizer getTableOfContentsCustomizer();

    /**
     * <p>getFilterExpression.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.expression.DRIExpression} object.
     */
    public DRIExpression<Boolean> getFilterExpression();

    /**
     * <p>getTitleBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getTitleBand();

    /**
     * <p>getPageHeaderBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getPageHeaderBand();

    /**
     * <p>getPageFooterBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getPageFooterBand();

    /**
     * <p>getColumnHeaderBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getColumnHeaderBand();

    /**
     * <p>getColumnFooterBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getColumnFooterBand();

    /**
     * <p>getDetailBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getDetailBand();

    /**
     * <p>getDetailHeaderBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getDetailHeaderBand();

    /**
     * <p>getDetailFooterBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getDetailFooterBand();

    /**
     * <p>getLastPageFooterBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getLastPageFooterBand();

    /**
     * <p>getSummaryBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getSummaryBand();

    /**
     * <p>getNoDataBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getNoDataBand();

    /**
     * <p>getBackgroundBand.</p>
     *
     * @return a {@link software.xdev.dynamicreports.report.definition.DRIBand} object.
     */
    public DRIBand getBackgroundBand();
}
