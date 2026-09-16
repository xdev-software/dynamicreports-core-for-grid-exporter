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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import software.xdev.dynamicreports.report.base.datatype.AbstractDataType;
import software.xdev.dynamicreports.report.constant.HorizontalTextAlignment;
import software.xdev.dynamicreports.report.defaults.Defaults;
import software.xdev.dynamicreports.report.exception.DRException;


public class DateType extends AbstractDataType<Date, Date>
{

	@Override
	public String getPattern()
	{
		return Defaults.getDefaults().getDateType().getPattern();
	}
	
	@Override
	public HorizontalTextAlignment getHorizontalTextAlignment()
	{
		return Defaults.getDefaults().getDateType().getHorizontalTextAlignment();
	}
	
	@Override
	public String valueToString(final Date value, final Locale locale)
	{
		if(value != null)
		{
			return new SimpleDateFormat(this.getPattern(), locale).format(value);
		}
		return null;
	}
	
	@Override
	public Date stringToValue(final String value, final Locale locale) throws DRException
	{
		if(value != null)
		{
			try
			{
				return new SimpleDateFormat(this.getPattern(), locale).parse(value);
			}
			catch(final ParseException e)
			{
				throw new DRException("Unable to convert string value to date", e);
			}
		}
		return null;
	}
}
