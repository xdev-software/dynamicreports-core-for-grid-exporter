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
package software.xdev.dynamicreports.report.builder.datatype;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import software.xdev.dynamicreports.report.ReportUtils;
import software.xdev.dynamicreports.report.base.datatype.AbstractDataType;
import software.xdev.dynamicreports.report.exception.DRException;


public abstract class NumberType<T extends Number> extends AbstractDataType<Number, T>
{

	@Override
	public String valueToString(final Number value, final Locale locale)
	{
		if(value != null)
		{
			return new DecimalFormat(this.getPattern(), new DecimalFormatSymbols(locale)).format(value);
		}
		return null;
	}
	
	@Override
	public T stringToValue(final String value, final Locale locale) throws DRException
	{
		if(value != null)
		{
			try
			{
				return this.numberToValue(new DecimalFormat(this.getPattern(), new DecimalFormatSymbols(locale)).parse(
					value));
			}
			catch(final ParseException e)
			{
				throw new DRException("Unable to convert string value to date", e);
			}
		}
		return null;
	}
	
	protected abstract T numberToValue(Number number);
	
	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass()
	{
		return (Class<T>)ReportUtils.getGenericClass(this, 0);
	}
}
