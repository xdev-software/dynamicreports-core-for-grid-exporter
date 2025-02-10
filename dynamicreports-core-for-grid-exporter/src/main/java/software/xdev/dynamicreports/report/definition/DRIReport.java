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


public interface DRIReport extends Serializable
{
	
	public DRIReportTemplate getTemplate();
	
	public List<DRIStyle> getTemplateStyles();
	
	public DRITemplateDesign<?> getTemplateDesign();
	
	public String getReportName();
	
	public Locale getLocale();
	
	public ResourceBundle getResourceBundle();
	
	public String getResourceBundleName();
	
	public Boolean getShowColumnTitle();
	
	public Boolean getShowColumnValues();
	
	public List<? extends DRIColumn<?>> getColumns();
	
	public List<? extends DRIGroup> getGroups();
	
	public List<? extends DRIField<?>> getFields();
	
	public List<? extends DRIVariable<?>> getVariables();
	
	public List<? extends DRISort> getSorts();
	
	public List<? extends DRISubtotal<?>> getSubtotals();
	
	public List<? extends DRIParameter<?>> getParameters();
	
	public Map<String, Object> getParameterValues();
	
	public List<DRIScriptlet> getScriptlets();
	
	public Properties getProperties();
	
	public DRIQuery getQuery();
	
	public DRIPage getPage();
	
	public Boolean getIgnorePagination();
	
	public WhenNoDataType getWhenNoDataType();
	
	public WhenResourceMissingType getWhenResourceMissingType();
	
	public Boolean getTitleOnANewPage();
	
	public Boolean getSummaryOnANewPage();
	
	public Boolean getSummaryWithPageHeaderAndFooter();
	
	public Boolean getFloatColumnFooter();
	
	public Orientation getPrintOrder();
	
	public RunDirection getColumnDirection();
	
	public String getLanguage();
	
	public Boolean getUseFieldNameAsDescription();
	
	public DRIFont getDefaultFont();
	
	public DRIReportStyle getTextStyle();
	
	public DRIReportStyle getColumnTitleStyle();
	
	public DRIReportStyle getColumnStyle();
	
	public DRIReportStyle getGroupTitleStyle();
	
	public DRIReportStyle getGroupStyle();
	
	public DRIReportStyle getSubtotalStyle();
	
	public DRIReportStyle getImageStyle();
	
	public Boolean getHighlightDetailOddRows();
	
	public DRISimpleStyle getDetailOddRowStyle();
	
	public Boolean getHighlightDetailEvenRows();
	
	public DRISimpleStyle getDetailEvenRowStyle();
	
	public List<? extends DRIConditionalStyle> getDetailRowHighlighters();
	
	public DRIColumnGrid getColumnGrid();
	
	public Boolean getTableOfContents();
	
	public DRITableOfContentsCustomizer getTableOfContentsCustomizer();
	
	public DRIExpression<Boolean> getFilterExpression();
	
	public DRIBand getTitleBand();
	
	public DRIBand getPageHeaderBand();
	
	public DRIBand getPageFooterBand();
	
	public DRIBand getColumnHeaderBand();
	
	public DRIBand getColumnFooterBand();
	
	public DRIBand getDetailBand();
	
	public DRIBand getDetailHeaderBand();
	
	public DRIBand getDetailFooterBand();
	
	public DRIBand getLastPageFooterBand();
	
	public DRIBand getSummaryBand();
	
	public DRIBand getNoDataBand();
	
	public DRIBand getBackgroundBand();
}
