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
package software.xdev.dynamicreports.report.base.datatype;

import java.util.Locale;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.definition.DRIValue;
import software.xdev.dynamicreports.report.definition.ReportParameters;
import software.xdev.dynamicreports.report.definition.datatype.DRIDataType;
import software.xdev.dynamicreports.report.definition.expression.DRIValueFormatter;
import software.xdev.dynamicreports.report.exception.DRException;


public abstract class AbstractDataType<U, T extends U> implements DRIDataType<U, T>
{

	@Override
	public String getPattern()
	{
		return null;
	}
	
	@Override
	public DRIValueFormatter<?, ? extends U> getValueFormatter()
	{
		return null;
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return null;
	}
	
	@Override
	public String valueToString(final U value, final Locale locale)
	{
		if(value != null)
		{
			return value.toString();
		}
		return null;
	}
	
	@Override
	public String valueToString(final DRIValue<? extends U> value, final ReportParameters reportParameters)
	{
		return this.valueToString(reportParameters.getValue(value), reportParameters.getLocale());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public String valueToString(final String name, final ReportParameters reportParameters)
	{
		return this.valueToString((U)reportParameters.getValue(name), reportParameters.getLocale());
	}
	
	@Override
	public T stringToValue(final String value, final Locale locale) throws DRException
	{
		return null;
	}
	
	@Override
	public T stringToValue(final DRIValue<String> value, final ReportParameters reportParameters) throws DRException
	{
		return this.stringToValue(reportParameters.getValue(value), reportParameters.getLocale());
	}
	
	@Override
	public T stringToValue(final String name, final ReportParameters reportParameters) throws DRException
	{
		return this.stringToValue((String)reportParameters.getValue(name), reportParameters.getLocale());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Class<T> getValueClass()
	{
		return (Class<T>)ReportUtils.getGenericClass(this, 1);
	}
}
