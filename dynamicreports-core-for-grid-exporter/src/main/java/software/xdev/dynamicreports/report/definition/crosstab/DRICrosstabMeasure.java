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
package software.xdev.dynamicreports.report.definition.crosstab;

import java.io.Serializable;
import java.util.List;

import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public interface DRICrosstabMeasure<T> extends Serializable
{
	
	String getName();
	
	DRIDataType<? super T, T> getDataType();
	
	DRIExpression<?> getExpression();
	
	String getPattern();
	
	HorizontalTextAlignment getHorizontalTextAlignment();
	
	DRIValueFormatter<?, ? super T> getValueFormatter();
	
	TextAdjust getTextAdjust();
	
	DRIHyperLink getHyperLink();
	
	List<DRIPropertyExpression> getPropertyExpressions();
	
	List<DRICrosstabCellStyle> getStyles();
	
	DRIExpression<?> getTitleExpression();
	
	DRIReportStyle getTitleStyle();
}
