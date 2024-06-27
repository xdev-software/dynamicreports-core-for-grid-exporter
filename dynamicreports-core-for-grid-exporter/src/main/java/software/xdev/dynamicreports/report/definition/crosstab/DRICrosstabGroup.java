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

import java.util.Comparator;
import java.util.List;

import software.xdev.dynamicreports.report.constant.CrosstabTotalPosition;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.OrderType;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIHyperLink;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIPropertyExpression;
import software.xdev.dynamicreports.report.definition.expression.DRISystemExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.definition.style.DRIReportStyle;


public interface DRICrosstabGroup<T> extends DRISystemExpression<T>
{
	
	@Override
	String getName();
	
	String getHeaderPattern();
	
	HorizontalTextAlignment getHeaderHorizontalTextAlignment();
	
	DRIValueFormatter<?, ? super T> getHeaderValueFormatter();
	
	@Deprecated
	Boolean getHeaderStretchWithOverflow();
	
	TextAdjust getHeaderTextAdjust();
	
	DRIHyperLink getHeaderHyperLink();
	
	DRIReportStyle getHeaderStyle();
	
	List<DRIPropertyExpression> getHeaderPropertyExpressions();
	
	Boolean getShowTotal();
	
	CrosstabTotalPosition getTotalPosition();
	
	DRIExpression<?> getTotalHeaderExpression();
	
	@Deprecated
	Boolean getTotalHeaderStretchWithOverflow();
	
	TextAdjust getTotalHeaderTextAdjust();
	
	DRIReportStyle getTotalHeaderStyle();
	
	List<DRIPropertyExpression> getTotalHeaderPropertyExpressions();
	
	DRIExpression<T> getExpression();
	
	DRIDataType<? super T, T> getDataType();
	
	DRIExpression<? extends Comparable<?>> getOrderByExpression();
	
	OrderType getOrderType();
	
	DRIExpression<? extends Comparator<?>> getComparatorExpression();
}
