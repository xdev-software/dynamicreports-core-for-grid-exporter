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

import static software.xdev.dynamicreports.report.builder.DynamicReports.report;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import software.xdev.dynamicreports.jasper.builder.JasperReportBuilder;
import software.xdev.dynamicreports.report.base.DRBand;
import software.xdev.dynamicreports.report.base.component.DRComponent;
import software.xdev.dynamicreports.report.base.expression.AbstractSimpleExpression;
import software.xdev.dynamicreports.report.constant.WhenResourceMissingType;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.component.DRIComponent;
import software.xdev.dynamicreports.report.definition.style.DRIStyle;


public class MultiPageListSubreportExpression extends AbstractSimpleExpression<JasperReportBuilder>
{

	private final Locale locale;
	private final ResourceBundle resourceBundle;
	private final String resourceBundleName;
	private final WhenResourceMissingType whenResourceMissingType;
	private final List<DRIComponent> detailComponents;
	private final Map<String, DRIStyle> templateStyles;
	
	public MultiPageListSubreportExpression(
		final Locale locale,
		final ResourceBundle resourceBundle,
		final String resourceBundleName,
		final WhenResourceMissingType whenResourceMissingType,
		final List<DRIComponent> detailComponents,
		final Map<String, DRIStyle> templateStyles)
	{
		this.locale = locale;
		this.resourceBundle = resourceBundle;
		this.resourceBundleName = resourceBundleName;
		this.whenResourceMissingType = whenResourceMissingType;
		this.detailComponents = detailComponents;
		this.templateStyles = templateStyles;
	}
	
	@Override
	public JasperReportBuilder evaluate(final ReportParameters reportParameters)
	{
		final JasperReportBuilder report = report();
		report.setLocale(this.locale);
		report.setResourceBundle(this.resourceBundle);
		report.setResourceBundle(this.resourceBundleName);
		report.setWhenResourceMissingType(this.whenResourceMissingType);
		for(final DRIStyle style : this.templateStyles.values())
		{
			report.getReport().addTemplateStyle(style);
		}
		final DRBand titleBand = report.getReport().getTitleBand();
		final DRComponent detailComponent =
			(DRComponent)this.detailComponents.get(reportParameters.getReportRowNumber() - 1);
		titleBand.addComponent(detailComponent);
		return report;
	}
}
