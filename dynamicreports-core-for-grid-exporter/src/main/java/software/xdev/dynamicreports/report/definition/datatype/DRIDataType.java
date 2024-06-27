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
package software.xdev.dynamicreports.report.definition.datatype;

import java.io.Serializable;
import java.util.Locale;

import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.exception.DRException;


public interface DRIDataType<U, T extends U> extends Serializable
{
	
	public String getPattern();
	
	public DRIValueFormatter<?, ? extends U> getValueFormatter();
	
	public HorizontalTextAlignment getHorizontalTextAlignment();
	
	public String valueToString(U value, Locale locale);
	
	public String valueToString(DRIValue<? extends U> value, ReportParameters reportParameters);
	
	public String valueToString(String name, ReportParameters reportParameters);
	
	public T stringToValue(String value, Locale locale) throws DRException;
	
	public T stringToValue(DRIValue<String> value, ReportParameters reportParameters) throws DRException;
	
	public T stringToValue(String name, ReportParameters reportParameters) throws DRException;
	
	public Class<T> getValueClass();
}
