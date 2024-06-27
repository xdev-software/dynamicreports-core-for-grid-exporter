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
package software.xdev.dynamicreports.report.definition.component;

import software.xdev.dynamicreports.report.constant.Evaluation;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.constant.Markup;
import software.xdev.dynamicreports.report.constant.TextAdjust;
import software.xdev.dynamicreports.report.definition.DRIGroup;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIExpression;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;


public interface DRITextField<T> extends DRIHyperLinkComponent
{
	
	DRIExpression<T> getValueExpression();
	
	String getPattern();
	
	DRIExpression<String> getPatternExpression();
	
	HorizontalTextAlignment getHorizontalTextAlignment();
	
	DRIValueFormatter<?, ? super T> getValueFormatter();
	
	DRIDataType<? super T, T> getDataType();
	
	Integer getColumns();
	
	Integer getRows();
	
	Evaluation getEvaluationTime();
	
	DRIGroup getEvaluationGroup();
	
	Markup getMarkup();
	
	Boolean getPrintRepeatedValues();
	
	TextAdjust getTextAdjust();
}
